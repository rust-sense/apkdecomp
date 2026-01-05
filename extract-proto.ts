#!/usr/bin/env bun

/**
 * Generic Protobuf Definition Extractor for Hermes Decompiled JavaScript
 * 
 * Extracts protobuf message and enum definitions from JavaScript code
 * that was decompiled from Hermes bytecode (React Native apps).
 * 
 * Features:
 * - Extracts messages with proper nesting structure
 * - Extracts fields from decode functions (more reliable than encode)
 * - Detects repeated (array) fields from error messages
 * - Detects enum type references in fields
 * - Extracts enums with proper value 0 handling
 * 
 * Usage: bun extract-proto.ts <decompiled.js> <package-name> [output.proto]
 */

import { readFileSync, writeFileSync, existsSync } from 'fs';
import { dirname } from 'path';
import { mkdir } from 'fs/promises';

// ============================================================================
// Type Definitions
// ============================================================================

interface FieldInfo {
  name: string;
  type: string;
  number: number;
  isOptional: boolean;
  isRepeated: boolean;
}

interface MessageInfo {
  name: string;
  fullName: string;       // Full path like "ClanInfo.Role"
  parentName: string | null;
  fields: FieldInfo[];
  nestedMessages: MessageInfo[];
  nestedEnums: EnumInfo[];
}

interface EnumInfo {
  name: string;
  fullName: string;       // Full path like "AppCameraRays.EntityType"
  parentName: string | null;
  values: Array<{ name: string; value: number }>;
}

// ============================================================================
// Global State for Extraction
// ============================================================================

// Track which fields are repeated (arrays)
const repeatedFields = new Map<string, Set<string>>();  // MessageName -> Set of field names

// Track field -> type mappings from ProtoBuf error messages
const fieldTypeHints = new Map<string, Map<string, string>>(); // MessageName -> (fieldName -> typeName)

// Track which fields use enum types
const enumTypeFields = new Map<string, Map<string, string>>(); // MessageName -> (fieldName -> enumTypeName)

// Track message nesting relationships
const messageNesting = new Map<string, string>(); // ChildMessage -> ParentMessage

// ============================================================================
// Pre-extraction: Parse error messages to discover structure
// ============================================================================

/**
 * Parse ProtoBuf error messages to discover:
 * - Which fields are arrays (repeated)
 * - Which fields reference other messages
 * - Which messages are nested inside others
 */
function parseProtobufErrorMessages(content: string): void {
  // Pattern: '.ProtoBuf.MessageName.fieldName: array/object expected'
  const errorPattern = /'\.(ProtoBuf\.([A-Za-z][A-Za-z0-9_.]*))\.([a-z][A-Za-z0-9_]*):\s*(array|object)\s+expected'/g;

  for (const match of content.matchAll(errorPattern)) {
    const fullPath = match[2];    // e.g., "ClanInfo" or "AppCameraRays.Entity"
    const fieldName = match[3];   // e.g., "members"
    const expectedType = match[4]; // "array" or "object"

    // Split path to get message name and check for nesting
    const pathParts = fullPath.split('.');
    const messageName = pathParts[pathParts.length - 1];

    // If path has multiple parts, record nesting relationship
    if (pathParts.length > 1) {
      const parentName = pathParts.slice(0, -1).join('.');
      messageNesting.set(messageName, parentName);
    }

    // Track repeated fields
    if (expectedType === 'array') {
      if (!repeatedFields.has(messageName)) {
        repeatedFields.set(messageName, new Set());
      }
      repeatedFields.get(messageName)!.add(fieldName);
    }
  }
}

/**
 * Discover message nesting relationships from encode function patterns.
 * Two patterns exist:
 * 
 * Pattern 1: .ProtoBuf; r? = r?.ParentMessage; r? = r?.NestedType; ... .encode
 * Example: .ProtoBuf; r2 = r2.AppCameraRays; r9 = r2.Entity; r8 = r9.encode
 * 
 * Pattern 2: .ProtoBuf; r? = r?.ParentMessage; r? = r?.NestedType; r? = r?.encode
 * Example: r2.ProtoBuf; r2 = r2.ClanInfo; r9 = r2.Role; r8 = r9.encode
 * 
 * This captures cases like ClanInfo.Role, AppMarker.SellOrder, etc.
 */
function discoverMessageNesting(content: string): void {
  // Pattern 1: Standard format
  const nestingPattern1 = /\.ProtoBuf;[\s\S]{0,30}?r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*);[\s\S]{0,30}?r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*);[\s\S]{0,100}?r\d+\s*=\s*r\d+\.encode/g;

  for (const match of content.matchAll(nestingPattern1)) {
    const parentType = match[1];
    const childType = match[2];

    // Avoid false positives
    if (parentType !== childType && childType !== 'encode' && !messageNesting.has(childType)) {
      messageNesting.set(childType, parentType);
    }
  }

  // Pattern 2: More compact - parent and child on consecutive lines
  // r?.ProtoBuf; r? = r?.ParentType; r? = r?.ChildType; r? = r?.encode
  const nestingPattern2 = /r\d+\.ProtoBuf;\s*r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*);\s*r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*);\s*r\d+\s*=\s*r\d+\.encode/g;

  for (const match of content.matchAll(nestingPattern2)) {
    const parentType = match[1];
    const childType = match[2];

    if (parentType !== childType && !messageNesting.has(childType)) {
      messageNesting.set(childType, parentType);
    }
  }
}

/**
 * Discover ENUM nesting relationships from toObject function patterns.
 * 
 * When an enum is nested inside a message, the code accesses it as:
 *   r? = r?.ProtoBuf;
 *   r? = r?.ParentMessage;
 *   r? = r?.EnumName;
 *   r? = r?[r?];  // array access to get enum value name
 * 
 * The key pattern is that after accessing ProtoBuf, we access ParentMessage,
 * then from that SAME register, we access the EnumName.
 * 
 * Example from decompiled code:
 *   r4 = r4.ProtoBuf;
 *   r4 = r4.AppCameraRays;
 *   r5 = r4.EntityType;
 *   r2 = r5[r4];
 */
function discoverEnumNesting(content: string): void {
  // Pattern: r? = r?.ProtoBuf; r? = r?.ParentMessage; r? = r?.EnumName; r? = r?[r?]
  // Note: The parent and enum access happen from the same register
  const enumNestingPattern = /r(\d+)\s*=\s*r\d+\.ProtoBuf;\s*r\1\s*=\s*r\1\.([A-Z][A-Za-z0-9_]*);\s*r\d+\s*=\s*r\1\.([A-Z][A-Za-z0-9_]*(?:Type|Kind|Status|State|Mode|Role|Level|Category|Flag|Icon|Index))/g;

  for (const match of content.matchAll(enumNestingPattern)) {
    const parentType = match[2];
    const enumName = match[3];

    // Only nest if we haven't already found a parent for this enum
    if (parentType !== enumName && !messageNesting.has(enumName)) {
      messageNesting.set(enumName, parentType);
    }
  }
}

/**
 * Parse ProtoBuf field -> message type relationships from decode patterns
 * Pattern: r? = r?.MessageType; r? = r?.decode;
 */
function parseFieldTypeHints(content: string, messages: Set<string>, enums: Set<string>): void {
  // Find decode function contexts and extract field -> type mappings
  // Pattern in decode: r? = r?.ProtoBuf; r? = r?.TypeName; r? = r?.decode; ... r0['fieldName'] = r?;

  const decodePattern = /r\d+\.ProtoBuf;[\s\S]{0,50}?r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*);[\s\S]{0,50}?r\d+\s*=\s*r\d+\.decode;[\s\S]{0,200}?r0\['([a-z][A-Za-z0-9_]*)'\]/g;

  for (const match of content.matchAll(decodePattern)) {
    const typeName = match[1];
    const fieldName = match[2];

    if (messages.has(typeName) || enums.has(typeName)) {
      // We can't know the message context here easily, so we'll use a global hint
      // This will be applied later when we know the message context
      if (!fieldTypeHints.has('_global')) {
        fieldTypeHints.set('_global', new Map());
      }
      fieldTypeHints.get('_global')!.set(fieldName, typeName);
    }
  }
}

/**
 * Parse enum type usage in fields
 * Pattern: r?.ProtoBuf; r? = r?.EnumTypeName; r? = r?[fieldValue]; ... in context of field access
 */
function parseEnumTypeUsage(content: string, enums: Set<string>): void {
  // Look for patterns where an enum is accessed in context of toObject/fromObject
  // Pattern: r?.ProtoBuf; r? = r?.EnumType; ... r0['fieldName'] = r?;
  const enumUsagePattern = /r\d+\.ProtoBuf;[\s\S]{0,30}?r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*Type);[\s\S]{0,100}?r\d+\['([a-z][A-Za-z0-9_]*)'\]/g;

  for (const match of content.matchAll(enumUsagePattern)) {
    const enumName = match[1];
    const fieldName = match[2];

    if (enums.has(enumName)) {
      if (!enumTypeFields.has('_global')) {
        enumTypeFields.set('_global', new Map());
      }
      enumTypeFields.get('_global')!.set(fieldName, enumName);
    }
  }
}

// ============================================================================
// Message Discovery
// ============================================================================

/**
 * Find protobuf-related message constructors
 */
function findProtobufMessages(content: string): Array<{ name: string; position: number }> {
  const messages: Array<{ name: string; position: number }> = [];
  const seenNames = new Set<string>();

  // Find all ".ProtoBuf" references to identify protobuf sections
  const protobufRefPattern = /(?:\.ProtoBuf|'\]ProtoBuf\[')/g;
  const protobufRefs: number[] = [];

  for (const match of content.matchAll(protobufRefPattern)) {
    protobufRefs.push(match.index!);
  }

  if (protobufRefs.length === 0) {
    return [];
  }

  const firstRef = Math.min(...protobufRefs);

  // Find the main protobuf section - look for a gap in references > 500KB
  // which would indicate we've left the protobuf section
  const sortedRefs = [...protobufRefs].sort((a, b) => a - b);
  let lastRef = sortedRefs[sortedRefs.length - 1];

  for (let i = 1; i < sortedRefs.length; i++) {
    const gap = sortedRefs[i] - sortedRefs[i - 1];
    if (gap > 500000) { // 500KB gap means likely different section
      lastRef = sortedRefs[i - 1];
      break;
    }
  }

  // Find end of protocol messages section - look for final ProtoBuf namespace assignment
  // Pattern: r?['ProtoBuf'] = r?; which ends the protocol messages module
  const protobufAssignPattern = /r\d+\['ProtoBuf'\]\s*=\s*r\d+/g;
  let protobufSectionEnd = lastRef + 10000;

  for (const match of content.matchAll(protobufAssignPattern)) {
    const pos = match.index!;
    // Must be after first ref but before library section
    if (pos > firstRef && pos < firstRef + 3000000) {
      protobufSectionEnd = pos;
    }
  }

  // Search window: before first ref to end of protocol messages section
  const searchStart = Math.max(0, firstRef - 20000);
  const searchEnd = protobufSectionEnd;
  const searchSection = content.slice(searchStart, searchEnd);

  // Find message constructors with encode method
  // Protobuf message names typically start with uppercase and don't have underscores
  const constructorPattern = /Original name:\s*([A-Z][A-Za-z0-9]*),\s*environment:/g;

  for (const match of searchSection.matchAll(constructorPattern)) {
    const name = match[1];
    const pos = searchStart + match.index!;

    if (seenNames.has(name)) continue;

    // Skip internal utility names
    if (name.startsWith('Buffer') || name.startsWith('_')) continue;

    // Check if this constructor has protobuf-specific methods
    // Real protobuf messages have: encode, decode, toObject, toJSON methods
    // before the message registration: r?['MessageName'] = r?

    // First find the registration pattern for this message
    const registrationPattern = new RegExp(`r\\d+\\['${name}'\\]\\s*=\\s*r\\d+`, 'g');
    const checkStart = match.index!;

    // Find the next registration for this message name
    const afterMatch = searchSection.slice(checkStart);
    const registrationMatch = afterMatch.match(registrationPattern);

    if (!registrationMatch) continue;

    // Find position of registration
    const regIndex = afterMatch.indexOf(registrationMatch[0]);
    if (regIndex === -1 || regIndex > 400000) continue; // Too far away

    // Check between constructor and registration for encode/decode
    const checkSection = afterMatch.slice(0, regIndex);

    // Check for protobuf-specific encode method (not encodeChunk or encodeUTF8)
    const hasEncode = checkSection.includes('Original name: encode,');
    const hasDecode = checkSection.includes('Original name: decode,');

    if (hasEncode || hasDecode) {
      seenNames.add(name);
      messages.push({ name, position: pos });
    }
  }

  return messages;
}

// ============================================================================
// Field Extraction - Using Encode Function
// ============================================================================

/**
 * Extract fields from the encode function of a message
 * 
 * Two patterns exist in encode functions:
 * 
 * 1. REGULAR FIELDS (scalar and non-repeated message):
 *    r? = r0.uint32;
 *    r? = WIRE_TAG;
 *    r? = r?.bind(r0)(r?);
 *    r? = r?.TYPE;
 *    r? = r1.fieldName;
 *    r? = r?.bind(r?)(r?);
 * 
 * 2. REPEATED FIELDS (arrays):
 *    r2 = r1.fieldName;           // Check existence
 *    r2 = r1.fieldName;
 *    r2 = r2.length;              // Check array has elements
 *    r6 = 0;                      // Loop counter
 *    r2 = r6 < r2;
 *    r4 = WIRE_TAG;               // Wire tag as separate assignment!
 *    // Loop body with r?.ProtoBuf; r?.ParentMessage; r?.NestedType; r?.encode
 *    r7 = r2[r6];                 // Array indexing
 */
function extractFieldsForMessage(content: string, messageName: string, messagePosition: number): FieldInfo[] {
  const fields: FieldInfo[] = [];
  const seenFieldNumbers = new Set<number>();
  const seenFieldNames = new Set<string>();

  // Get a large block to find encode function
  const blockEnd = Math.min(content.length, messagePosition + 50000);
  const block = content.slice(messagePosition, blockEnd);

  // Find encode function
  const encodeStart = block.indexOf('Original name: encode');
  if (encodeStart === -1) return fields;

  // Find end of encode function (next "Original name:" or function end)
  let encodeEnd = block.indexOf('Original name:', encodeStart + 25);
  if (encodeEnd === -1) encodeEnd = Math.min(block.length, encodeStart + 15000);

  const encodeBlock = block.slice(encodeStart, encodeEnd);

  // =========================================================================
  // PHASE 1: Extract REPEATED (array) fields
  // Pattern: r2 = r1.fieldName; r2 = r2.length; r6 = 0; r2 = r6 < r2; r4 = WIRE_TAG; ... .ProtoBuf; r?.ParentType; r?.NestedType; r?.encode
  // =========================================================================

  // Find all repeated field patterns - look for array length checks followed by loop initialization
  // The wire tag comes AFTER the `r6 = 0; r2 = r6 < r2;` pattern
  const repeatedPattern = /r\d+\s*=\s*r\d+\.([a-z][A-Za-z0-9_]*);\s*r\d+\s*=\s*r\d+\.length;\s*r\d+\s*=\s*0;\s*r\d+\s*=\s*r\d+\s*<\s*r\d+;\s*r\d+\s*=\s*(\d+);[\s\S]{0,300}?\.ProtoBuf;[\s\S]{0,100}?r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*);\s*r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*);[\s\S]{0,50}?r\d+\s*=\s*r\d+\.encode/g;

  for (const match of encodeBlock.matchAll(repeatedPattern)) {
    const fieldName = match[1];
    const wireTag = parseInt(match[2], 10);
    const parentType = match[3];
    const nestedType = match[4];

    if (!fieldName || isReservedName(fieldName)) continue;
    if (wireTag < 8 || wireTag >= 2048) continue;

    const fieldNumber = wireTag >> 3;
    if (fieldNumber < 1 || fieldNumber > 200) continue;
    if (seenFieldNumbers.has(fieldNumber) || seenFieldNames.has(fieldName)) continue;

    seenFieldNumbers.add(fieldNumber);
    seenFieldNames.add(fieldName);

    // Build the nested type reference
    let type: string;
    if (parentType === messageName) {
      // Nested inside current message
      type = `${parentType}.${nestedType}`;
    } else {
      // External type or different structure
      type = nestedType;
    }

    fields.push({
      name: fieldName,
      type,
      number: fieldNumber,
      isOptional: false,
      isRepeated: true,
    });
  }

  // =========================================================================
  // PHASE 2: Extract REGULAR fields (non-repeated)
  // Pattern: r? = r?.uint32... r? = WIRE_TAG;
  // Also handles simpler pattern: r? = r?.uint32; r? = WIRE_TAG;
  // =========================================================================

  const wireTagMatches: Array<{ wireTag: number; pos: number }> = [];

  // Pattern 1: uint32 followed immediately by wire tag (r? = r?.uint32; r? = NUMBER;)
  const wireTagPattern1 = /r\d+\s*=\s*r\d+\.uint32;?\s*r\d+\s*=\s*(\d+);/g;

  for (const match of encodeBlock.matchAll(wireTagPattern1)) {
    const wireTag = parseInt(match[1], 10);
    if (wireTag >= 8 && wireTag < 2048) {
      wireTagMatches.push({ wireTag, pos: match.index! });
    }
  }

  // Pattern 2: uint32 with intervening code (r? = r?.uint32[...]; ... r? = NUMBER;)
  const wireTagPattern2 = /r\d+\s*=\s*r\d+\.uint32[^;]*;\s*r\d+\s*=\s*(\d+);/g;

  for (const match of encodeBlock.matchAll(wireTagPattern2)) {
    const wireTag = parseInt(match[1], 10);
    if (wireTag >= 8 && wireTag < 2048) {
      // Avoid duplicates
      if (!wireTagMatches.some(m => m.wireTag === wireTag && Math.abs(m.pos - match.index!) < 100)) {
        wireTagMatches.push({ wireTag, pos: match.index! });
      }
    }
  }

  // For each wire tag, find the associated field
  for (const { wireTag, pos } of wireTagMatches) {
    const fieldNumber = wireTag >> 3;
    const wireType = wireTag & 0x7;

    if (fieldNumber < 1 || fieldNumber > 200) continue;
    if (seenFieldNumbers.has(fieldNumber)) continue;  // Skip if already found as repeated

    // Get context around this wire tag
    const contextBefore = encodeBlock.slice(Math.max(0, pos - 400), pos);
    const contextAfter = encodeBlock.slice(pos, Math.min(encodeBlock.length, pos + 400));

    // Skip if this looks like part of a repeated field loop (has array indexing nearby)
    if (contextAfter.match(/r\d+\[r\d+\]/) && contextBefore.includes('.length')) continue;

    // Find the field name
    let fieldName: string | null = null;
    let isOptionalField = false;

    // Pattern 1: Optional field - field name in quotes before hasOwnProperty
    // r? = 'fieldName'; ... hasOwnProperty ... if(!r?) ... r?.uint32... r? = WIRE_TAG
    const optionalFieldMatch = contextBefore.match(/r\d+\s*=\s*'([a-z][A-Za-z0-9_]*)';[^]*?hasOwnProperty/);
    if (optionalFieldMatch) {
      fieldName = optionalFieldMatch[1];
      isOptionalField = true;
    }

    // Pattern 2: Required field - r? = r5.fieldName; after the type
    // After wire tag: r?.bind ... r? = r?.TYPE; r? = r5.fieldName; r? = r?.bind
    if (!fieldName) {
      // The field access pattern: r?.bind(r?)(r?); r? = r?.TYPE; r? = r5.fieldName
      // Or for Vector types (wire type 5): r?.float; r? = r1.x
      const fieldAccessMatch = contextAfter.match(/\.bind\(r\d+\)\(r\d+\);\s*r\d+\s*=\s*r\d+\.(float|double|int32|uint32|sint32|int64|uint64|sint64|fixed32|fixed64|sfixed32|sfixed64|bool|string|bytes);\s*r\d+\s*=\s*r\d+\.([a-z][A-Za-z0-9_]*);/);
      if (fieldAccessMatch && !isReservedName(fieldAccessMatch[2])) {
        fieldName = fieldAccessMatch[2];
        isOptionalField = false;
      }
    }

    // Pattern 3: For nested messages, the field name is accessed before fork
    // r? = r5.fieldName; ... r?.uint32... r? = WIRE_TAG ... r?.fork
    if (!fieldName && (contextAfter.includes('.fork') || contextBefore.includes('.fork'))) {
      // Look for field access in context before
      const nestedFieldMatches = [...contextBefore.matchAll(/r\d+\s*=\s*r\d+\.([a-z][A-Za-z0-9_]*);/g)];
      for (let i = nestedFieldMatches.length - 1; i >= 0; i--) {
        const name = nestedFieldMatches[i][1];
        if (!isReservedName(name)) {
          fieldName = name;
          isOptionalField = contextBefore.includes(`'${name}'`) && contextBefore.includes('hasOwnProperty');
          break;
        }
      }
    }

    // Pattern 4: Simple messages - field access is r? = r?.fieldName; followed by bind
    // r?.uint32; r? = WIRE_TAG; r? = r?.bind...; r? = r?.TYPE; r? = a0; r? = r?.fieldName; r? = r?.bind
    if (!fieldName) {
      // Look for simpler field access pattern: r? = r?.fieldName; r? = r?.bind
      const simpleFieldMatch = contextAfter.match(/r\d+\s*=\s*r\d+\.([a-z][A-Za-z0-9_]*);\s*r\d+\s*=\s*r\d+\.bind/);
      if (simpleFieldMatch && !isReservedName(simpleFieldMatch[1])) {
        fieldName = simpleFieldMatch[1];
        isOptionalField = false;
      }
    }

    if (!fieldName || seenFieldNames.has(fieldName)) continue;

    // Determine the type
    let type: string;

    // Check for nested message (fork pattern)
    if (contextAfter.includes('.fork') || contextBefore.includes('.fork')) {
      // Look for ProtoBuf.MessageType.encode pattern
      const nestedTypeMatch = contextBefore.match(/r\d+\.ProtoBuf;[\s\S]{0,50}?r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*);[\s\S]{0,50}?r\d+\s*=\s*r\d+\.encode/);
      if (nestedTypeMatch) {
        type = nestedTypeMatch[1];
      } else {
        // Try broader search
        const nestedTypeMatch2 = encodeBlock.slice(Math.max(0, pos - 600), pos + 200).match(/\.ProtoBuf[\s\S]{0,150}?r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9_]*);[\s\S]{0,100}?\.encode/);
        type = nestedTypeMatch2 ? nestedTypeMatch2[1] : 'bytes';
      }
    } else {
      // Get type from the encoder call - pattern: r?.bind(r?)(r?); r? = r?.TYPE; r? = r?.fieldName
      const typeMatch = contextAfter.match(/\.bind\(r\d+\)\(r\d+\);\s*r\d+\s*=\s*r\d+\.(float|double|int32|uint32|sint32|int64|uint64|sint64|fixed32|fixed64|sfixed32|sfixed64|bool|string|bytes);/);
      if (typeMatch) {
        type = typeMatch[1];
      } else {
        type = inferTypeFromWireType(fieldName, wireType);
      }
    }

    // Check if field is repeated (from error messages)
    const isRepeated = repeatedFields.get(messageName)?.has(fieldName) || false;

    // Check for enum type hint
    const enumType = enumTypeFields.get('_global')?.get(fieldName);
    if (enumType && type === 'int32') {
      type = enumType;
    }

    // If required field check is present, mark as required (not optional)
    const hasRequiredCheck = block.includes(`missing required '${fieldName}'`);
    if (hasRequiredCheck) {
      isOptionalField = false;
    }

    seenFieldNumbers.add(fieldNumber);
    seenFieldNames.add(fieldName);

    fields.push({
      name: fieldName,
      type,
      number: fieldNumber,
      isOptional: isOptionalField,
      isRepeated,
    });
  }

  return fields.sort((a, b) => a.number - b.number);
}

function isReservedName(name: string): boolean {
  return ['uint32', 'uint64', 'int32', 'int64', 'float', 'double', 'bool', 'string',
    'bytes', 'sint32', 'sint64', 'fixed32', 'fixed64', 'sfixed32', 'sfixed64',
    'fork', 'ldelim', 'bind', 'call', 'apply', 'prototype', 'create', 'encode',
    'decode', 'length', 'push', 'pop', 'slice', 'hasOwnProperty', 'keys', 'verify',
    'fromObject', 'toObject'].includes(name);
}

/**
 * Fallback type inference based purely on protobuf wire type.
 * This is only used when the explicit type cannot be extracted from the encoder pattern.
 * 
 * Wire types: https://protobuf.dev/programming-guides/encoding/#structure
 * - 0: Varint (int32, int64, uint32, uint64, sint32, sint64, bool, enum)
 * - 1: 64-bit (fixed64, sfixed64, double)
 * - 2: Length-delimited (string, bytes, embedded messages, packed repeated fields)
 * - 5: 32-bit (fixed32, sfixed32, float)
 */
function inferTypeFromWireType(_fieldName: string, wireType: number): string {
  switch (wireType) {
    case 0: // Varint - default to int32 (most common)
      return 'int32';
    case 1: // 64-bit fixed - default to double
      return 'double';
    case 2: // Length-delimited - default to string (bytes/messages handled elsewhere)
      return 'string';
    case 5: // 32-bit fixed - always use float (Half3 uses this)
      return 'float';
    default:
      return 'string';
  }
}

// Track wrapper messages that should have repeated fields
const wrapperMessages = new Map<string, { fieldName: string; typeName: string }>();

/**
 * Discover wrapper messages that contain repeated fields of another message type.
 * This is done GENERICALLY by looking for patterns in the decompiled code:
 * 
 * 1. In constructor: `r0 = new Array(0); r4['fieldName'] = r0;`
 *    This tells us the message has an array field.
 * 
 * 2. In decode function: After the message constructor, look for:
 *    `r? = r?.messages; ... r? = r?.ProtoBuf; r? = r?.TypeName; r? = r?.decode;`
 *    This tells us the type of items in the repeated field.
 * 
 * This approach is generic and doesn't rely on hardcoded message names.
 */
function discoverWrapperMessages(content: string): void {
  // Step 1: Find all message constructors that initialize an empty array field
  // Pattern: Original name: MessageName, ... r0 = new Array(0); r4['fieldName'] = r0;
  const constructorPattern = /Original name:\s*([A-Z][A-Za-z0-9]*),\s*environment[\s\S]{0,300}?r\d+\s*=\s*new Array\(0\);\s*r\d+\['([a-z][A-Za-z0-9_]*)'\]\s*=\s*r\d+;/g;

  const candidateWrappers: Array<{ messageName: string; fieldName: string; position: number }> = [];

  for (const match of content.matchAll(constructorPattern)) {
    const messageName = match[1];
    const fieldName = match[2];
    const position = match.index!;

    // Skip if fieldName looks like something other than a protobuf field
    if (['constructor', 'prototype'].includes(fieldName)) continue;

    candidateWrappers.push({ messageName, fieldName, position });
  }

  // Step 2: For each candidate, look in decode function for the type being pushed
  // Pattern in decode: r0['fieldName'] = r1; ... r? = r?.push; ... r?.ProtoBuf; r? = r?.TypeName; r? = r?.decode
  for (const { messageName, fieldName, position } of candidateWrappers) {
    // Get a large block after the constructor to find decode function
    const blockEnd = Math.min(content.length, position + 40000);
    const block = content.slice(position, blockEnd);

    // Find decode function
    const decodeStart = block.indexOf('Original name: decode');
    if (decodeStart === -1) continue;

    // Find end of decode function
    let decodeEnd = block.indexOf('Original name:', decodeStart + 25);
    if (decodeEnd === -1) decodeEnd = Math.min(block.length, decodeStart + 15000);

    const decodeBlock = block.slice(decodeStart, decodeEnd);

    // Look for the push pattern: r? = r0.fieldName; ... r?.push; ... r?.ProtoBuf; r? = r?.TypeName; r? = r?.decode
    // More specifically: r9 = r0.messages; r8 = r9.push; ... r11 = r1.AppTeamMessage; r10 = r11.decode;
    const pushPattern = new RegExp(
      `r\\d+\\s*=\\s*r\\d+\\.${fieldName};[\\s\\S]{0,100}?r\\d+\\s*=\\s*r\\d+\\.push;[\\s\\S]{0,200}?r\\d+\\.ProtoBuf;[\\s\\S]{0,100}?r\\d+\\s*=\\s*r\\d+\\.([A-Z][A-Za-z0-9]*);\\s*r\\d+\\s*=\\s*r\\d+\\.decode`,
      'g'
    );

    const pushMatch = decodeBlock.match(pushPattern);
    if (pushMatch) {
      // Extract the type name from the pattern
      const typeMatch = pushMatch[0].match(/r\d+\s*=\s*r\d+\.([A-Z][A-Za-z0-9]*);[\s\S]{0,50}?r\d+\s*=\s*r\d+\.decode/);
      if (typeMatch) {
        const typeName = typeMatch[1];
        wrapperMessages.set(messageName, { fieldName, typeName });
      }
    }
  }
}

// ============================================================================
// Enum Extraction
// ============================================================================

/**
 * Find and extract enum definitions
 */
function extractEnums(content: string, protobufSectionStart: number, protobufSectionEnd: number): EnumInfo[] {
  const enums: EnumInfo[] = [];
  const seenEnums = new Set<string>();

  const section = content.slice(protobufSectionStart, protobufSectionEnd);

  // Find enum assignments (names ending with Type, Kind, etc. or nested enum pattern)
  const enumNamePattern = /r0\['([A-Z][A-Za-z0-9_]*)'\]\s*=\s*r(\d+);/g;

  // Collect potential enum assignments
  const enumMatches: Array<{ name: string; position: number; registerNum: string }> = [];

  for (const match of section.matchAll(enumNamePattern)) {
    const name = match[1];
    // Check if this looks like an enum (has Type/Kind/Status suffix OR is a known nested enum pattern)
    if (name.match(/(?:Type|Kind|Status|State|Mode|Role|Level|Category|Flag|Icon|Index)$/) ||
      messageNesting.has(name)) {
      enumMatches.push({ name, position: match.index!, registerNum: match[2] });
    }
  }

  // Sort by position
  enumMatches.sort((a, b) => a.position - b.position);

  // Track register values across enums
  let registerValues = new Map<string, number>();

  for (let i = 0; i < enumMatches.length; i++) {
    const { name: enumName, position: assignmentPos } = enumMatches[i];
    if (seenEnums.has(enumName)) continue;

    // Find the start boundary - look for the pattern that marks enum definition start:
    // r? = global; r? = r?.Object; r? = r?.create;
    // or Object.create pattern
    let startIdx: number;

    // Search backwards from the assignment (typically ~500 chars before)
    const searchStart = Math.max(0, assignmentPos - 1000);
    const searchSection = section.slice(searchStart, assignmentPos);

    // Look for 'r? = global;' which often precedes enum definitions
    const globalMatch = searchSection.lastIndexOf('= global;');
    // Look for Object.create
    const objectCreateMatch = searchSection.lastIndexOf('Object.create');
    // Look for r?.create (indirect call pattern)
    const createMatch = searchSection.lastIndexOf('.create;');

    // Use the closest marker that's not too far back
    const markers = [
      globalMatch !== -1 ? globalMatch : -1,
      objectCreateMatch !== -1 ? objectCreateMatch : -1,
      createMatch !== -1 ? createMatch : -1
    ].filter(m => m !== -1);

    if (markers.length > 0) {
      // Find the latest (closest to enum) marker, then go back a bit to include full line
      const latestMarker = Math.max(...markers);
      // Go back to find the start of that line (r? = {};)
      const lineStart = searchSection.lastIndexOf('\n', latestMarker);
      startIdx = searchStart + (lineStart !== -1 ? lineStart : latestMarker);
    } else if (i > 0) {
      // Fallback: use previous enum's position
      const prevEnumEnd = enumMatches[i - 1].position;
      const afterPrev = section.indexOf(';', prevEnumEnd);
      startIdx = afterPrev !== -1 ? afterPrev + 1 : prevEnumEnd + 50;
    } else {
      startIdx = Math.max(0, assignmentPos - 500);
    }

    const enumSection = section.slice(startIdx, assignmentPos + 100);

    const { values, newRegisterValues } = extractEnumValuesWithRegisters(enumSection, enumName, registerValues);

    registerValues = newRegisterValues;

    // Accept enums with at least 1 value (some may be small)
    if (values.length >= 1) {
      seenEnums.add(enumName);

      // Check if this enum should be nested
      const parentName = messageNesting.get(enumName) || null;

      enums.push({
        name: enumName,
        fullName: parentName ? `${parentName}.${enumName}` : enumName,
        parentName,
        values
      });
    }
  }

  return enums;
}

/**
 * Extract enum values from a code section, tracking register values
 */
function extractEnumValuesWithRegisters(
  section: string,
  enumName: string,
  inheritedRegisters: Map<string, number>
): { values: Array<{ name: string; value: number }>; newRegisterValues: Map<string, number> } {
  const values: Array<{ name: string; value: number }> = [];
  const seenNames = new Set<string>();

  const registerValues = new Map<string, number>(inheritedRegisters);
  const localRegisterAssignments = new Map<string, number>();

  // Track register assignments
  const regAssignPattern = /r(\d+)\s*=\s*(\d+);/g;
  for (const match of section.matchAll(regAssignPattern)) {
    const reg = `r${match[1]}`;
    const value = parseInt(match[2], 10);
    if (!isNaN(value) && value >= 0 && value <= 1000) {
      registerValues.set(reg, value);
      if (!localRegisterAssignments.has(reg) || match.index! < localRegisterAssignments.get(reg)!) {
        localRegisterAssignments.set(reg, match.index!);
      }
    }
  }

  // Pattern 1: Direct value assignment
  const pattern1 = /r(\d+)\s*=\s*(\d+);\s*r\d+\[r\1\]\s*=\s*r\d+;\s*r\d+\['([A-Za-z][A-Za-z0-9_]*)'\]\s*=\s*r\1;/g;

  for (const match of section.matchAll(pattern1)) {
    const value = parseInt(match[2], 10);
    const name = match[3];

    if (!seenNames.has(name) && !isNaN(value) && value >= 0 && value <= 1000 && name !== enumName) {
      seenNames.add(name);
      values.push({ name, value });
    }
  }

  // Pattern 2: String first
  const pattern2 = /r\d+\s*=\s*'([A-Za-z][A-Za-z0-9_]*)';\s*r(\d+)\s*=\s*(\d+);\s*r\d+\[r\2\]\s*=\s*r\d+;\s*r\d+\['\1'\]\s*=\s*r\2;/g;

  for (const match of section.matchAll(pattern2)) {
    const name = match[1];
    const value = parseInt(match[3], 10);

    if (!seenNames.has(name) && !isNaN(value) && value >= 0 && value <= 1000 && name !== enumName) {
      seenNames.add(name);
      values.push({ name, value });
    }
  }

  // Pattern 3: Register reuse (only when register was assigned BEFORE usage)
  // Do NOT use inherited registers if there's a local assignment after the usage
  const pattern3 = /r\d+\s*=\s*'([A-Za-z][A-Za-z0-9_]*)';\s*r\d+\[r(\d+)\]\s*=\s*r\d+;\s*r\d+\['\1'\]\s*=\s*r\2;/g;

  for (const match of section.matchAll(pattern3)) {
    const name = match[1];
    const reg = `r${match[2]}`;
    const matchPos = match.index!;

    const localAssignPos = localRegisterAssignments.get(reg);
    const wasLocallyAssignedBefore = localAssignPos !== undefined && localAssignPos < matchPos;
    const wasLocallyAssignedAfter = localAssignPos !== undefined && localAssignPos > matchPos;
    const isInherited = inheritedRegisters.has(reg);

    // Debug for EntityType
    // if (enumName === 'EntityType') {
    //   console.error(`  Pattern 3 for ${name}: reg=${reg}, matchPos=${matchPos}, localAssignPos=${localAssignPos}, wasLocallyAssignedBefore=${wasLocallyAssignedBefore}, wasLocallyAssignedAfter=${wasLocallyAssignedAfter}, isInherited=${isInherited}`);
    // }

    // Only use if locally assigned BEFORE, OR if inherited and NOT locally assigned at all
    // Do NOT use inherited value if there's a local assignment after (Pattern 5 will handle that)
    if (wasLocallyAssignedBefore || (isInherited && localAssignPos === undefined)) {
      const value = wasLocallyAssignedBefore ? registerValues.get(reg) : inheritedRegisters.get(reg);
      if (value !== undefined && !seenNames.has(name) && value >= 0 && value <= 1000 && name !== enumName) {
        seenNames.add(name);
        values.push({ name, value });
      }
    }
  }

  // Pattern 4: Direct literal
  const pattern4 = /r\d+\['([A-Za-z][A-Za-z0-9_]*)'\]\s*=\s*(\d+);/g;
  for (const match of section.matchAll(pattern4)) {
    const name = match[1];
    const value = parseInt(match[2], 10);
    if (!seenNames.has(name) && !isNaN(value) && value >= 0 && value < 100 && name !== enumName) {
      seenNames.add(name);
      values.push({ name, value });
    }
  }

  // Pattern 5: Register used before assignment (infer sequential values)
  // For enum values like Tree that use r3 before r3 is assigned to 2,
  // infer that Tree = 1 (one less than the first known value for that register)
  const pattern5 = /r\d+\s*=\s*'([A-Za-z][A-Za-z0-9_]*)';\s*r\d+\[r(\d+)\]\s*=\s*r\d+;\s*r\d+\['\1'\]\s*=\s*r\2;/g;

  for (const match of section.matchAll(pattern5)) {
    const name = match[1];
    const reg = `r${match[2]}`;
    const matchPos = match.index!;

    if (seenNames.has(name)) continue;

    // Check if this register was assigned AFTER this usage
    const localAssignPos = localRegisterAssignments.get(reg);
    const wasAssignedAfter = localAssignPos !== undefined && localAssignPos > matchPos;

    if (wasAssignedAfter) {
      // Get the value this register was later assigned to
      const laterValue = registerValues.get(reg);
      if (laterValue !== undefined && laterValue > 0) {
        // Count how many values already use this register at lower positions
        let inferredValue = laterValue - 1;

        // Make sure we don't duplicate existing values
        while (values.some(v => v.value === inferredValue) && inferredValue > 0) {
          inferredValue--;
        }

        if (inferredValue >= 0 && inferredValue < 100) {
          seenNames.add(name);
          values.push({ name, value: inferredValue });
        }
      }
    }
  }

  return {
    values: values.sort((a, b) => a.value - b.value),
    newRegisterValues: registerValues
  };
}

// ============================================================================
// Structure Building - Nest messages and enums properly
// ============================================================================

/**
 * Build the nested structure of messages and enums
 */
function buildNestedStructure(
  messages: Array<{ name: string; fields: FieldInfo[] }>,
  enums: EnumInfo[]
): { topLevelMessages: MessageInfo[]; topLevelEnums: EnumInfo[] } {

  // First, create MessageInfo for all messages
  const messageMap = new Map<string, MessageInfo>();

  for (const msg of messages) {
    messageMap.set(msg.name, {
      name: msg.name,
      fullName: msg.name,
      parentName: messageNesting.get(msg.name) || null,
      fields: msg.fields,
      nestedMessages: [],
      nestedEnums: [],
    });
  }

  // Nest enums into their parent messages
  const topLevelEnums: EnumInfo[] = [];
  for (const enumInfo of enums) {
    if (enumInfo.parentName && messageMap.has(enumInfo.parentName)) {
      messageMap.get(enumInfo.parentName)!.nestedEnums.push(enumInfo);
    } else {
      topLevelEnums.push(enumInfo);
    }
  }

  // Nest messages into their parent messages
  const topLevelMessages: MessageInfo[] = [];
  for (const msg of messageMap.values()) {
    if (msg.parentName && messageMap.has(msg.parentName)) {
      messageMap.get(msg.parentName)!.nestedMessages.push(msg);
    } else {
      topLevelMessages.push(msg);
    }
  }

  return { topLevelMessages, topLevelEnums };
}

// ============================================================================
// Proto File Generation
// ============================================================================

function generateProtoFile(
  topLevelMessages: MessageInfo[],
  topLevelEnums: EnumInfo[],
  packageName: string
): string {
  let proto = `syntax = "proto3";\n\n`;

  if (packageName) {
    proto += `package ${packageName};\n\n`;
  }

  // Top-level enums first
  for (const enumDef of topLevelEnums) {
    proto += generateEnum(enumDef, 0);
    proto += '\n';
  }

  // Messages
  for (const message of topLevelMessages) {
    proto += generateMessage(message, 0);
    proto += '\n';
  }

  return proto;
}

function generateEnum(enumDef: EnumInfo, indent: number): string {
  const pad = '  '.repeat(indent);
  let proto = `${pad}enum ${enumDef.name} {\n`;

  // Check if enum starts at 1 (common pattern) - add Reserved = 0 for proto3 compliance
  const hasZeroValue = enumDef.values.some(v => v.value === 0);
  const minValue = Math.min(...enumDef.values.map(v => v.value));

  // Add Reserved = 0 if enum starts at 1 and doesn't have a 0 value
  // Exception: AppMarkerType already has Undefined = 0
  if (!hasZeroValue && minValue === 1) {
    proto += `${pad}  Reserved = 0;\n`;
  }

  for (const value of enumDef.values) {
    proto += `${pad}  ${value.name} = ${value.value};\n`;
  }

  proto += `${pad}}\n`;
  return proto;
}

function generateMessage(message: MessageInfo, indent: number): string {
  const pad = '  '.repeat(indent);
  let proto = `${pad}message ${message.name} {\n`;

  // Nested enums first
  for (const nestedEnum of message.nestedEnums) {
    proto += generateEnum(nestedEnum, indent + 1);
    proto += '\n';
  }

  // Nested messages
  for (const nestedMsg of message.nestedMessages) {
    proto += generateMessage(nestedMsg, indent + 1);
    proto += '\n';
  }

  // Fields
  for (const field of message.fields) {
    let line = `${pad}  `;
    if (field.isRepeated) {
      line += 'repeated ';
    } else if (field.isOptional) {
      line += 'optional ';
    }
    line += `${field.type} ${field.name} = ${field.number};\n`;
    proto += line;
  }

  proto += `${pad}}\n`;
  return proto;
}

// ============================================================================
// Main
// ============================================================================

async function main() {
  const args = process.argv.slice(2);

  if (args.length < 2) {
    console.error('Usage: bun extract-proto.ts <decompiled.js> <package-name> [output.proto]');
    process.exit(1);
  }

  const inputPath = args[0];
  const packageName = args[1];
  let outputPath = args[2];

  if (!existsSync(inputPath)) {
    console.error(`Error: Input file not found: ${inputPath}`);
    process.exit(1);
  }

  if (!outputPath) {
    outputPath = inputPath.replace(/\.(js|decompiled\.js)$/i, '.proto');
    if (outputPath === inputPath) {
      outputPath = inputPath + '.proto';
    }
  }

  console.log(`Input: ${inputPath}`);
  console.log(`Package: ${packageName}`);
  console.log(`Output: ${outputPath}`);

  // Read file
  console.log('Reading input file...');
  const content = readFileSync(inputPath, 'utf-8');
  console.log(`Read ${content.split('\n').length} lines`);

  // Pre-extraction: Parse error messages for structure hints
  console.log('Analyzing protobuf structure...');
  parseProtobufErrorMessages(content);
  discoverMessageNesting(content);  // Discover nesting from encode patterns
  discoverEnumNesting(content);     // Discover enum nesting from toObject patterns
  discoverWrapperMessages(content);  // Find wrapper messages like AppTeamChat
  console.log(`Found ${repeatedFields.size} messages with repeated fields`);
  console.log(`Found ${messageNesting.size} nested type relationships`);
  console.log(`Found ${wrapperMessages.size} wrapper messages`);

  // Find protobuf messages
  console.log('Scanning for protobuf messages...');
  const messageRefs = findProtobufMessages(content);

  if (messageRefs.length === 0) {
    console.error('Error: No protobuf messages found');
    process.exit(1);
  }

  console.log(`Found ${messageRefs.length} message definitions`);

  // Create sets for type checking
  const messageNames = new Set(messageRefs.map(r => r.name));

  // Extract enums first (we need them for type hints)
  console.log('Extracting enums...');
  const firstPos = Math.min(...messageRefs.map(r => r.position));
  const lastPos = Math.max(...messageRefs.map(r => r.position));
  const enums = extractEnums(content, Math.max(0, firstPos - 10000), Math.min(content.length, lastPos + 30000));
  console.log(`Found ${enums.length} enums`);

  const enumNames = new Set(enums.map(e => e.name));

  // Parse additional hints
  parseFieldTypeHints(content, messageNames, enumNames);
  parseEnumTypeUsage(content, enumNames);

  // Extract fields for each message
  console.log('Extracting fields...');
  const messages: Array<{ name: string; fields: FieldInfo[] }> = [];

  for (const ref of messageRefs) {
    let fields = extractFieldsForMessage(content, ref.name, ref.position);

    // Add wrapper message fields if this is an empty wrapper
    if (fields.length === 0 && wrapperMessages.has(ref.name)) {
      const wrapper = wrapperMessages.get(ref.name)!;
      fields = [{
        name: wrapper.fieldName,
        type: wrapper.typeName,
        number: 1,
        isOptional: false,
        isRepeated: true,
      }];
    }

    messages.push({ name: ref.name, fields });
  }

  console.log(`Extracted ${messages.length} messages (${messages.filter(m => m.fields.length > 0).length} with fields)`);

  // Build nested structure
  console.log('Building nested structure...');
  const { topLevelMessages, topLevelEnums } = buildNestedStructure(messages, enums);

  console.log(`Top-level messages: ${topLevelMessages.length}`);
  console.log(`Top-level enums: ${topLevelEnums.length}`);

  // Generate proto file
  console.log('Generating proto file...');
  const protoContent = generateProtoFile(topLevelMessages, topLevelEnums, packageName);

  // Write output
  await mkdir(dirname(outputPath), { recursive: true });
  writeFileSync(outputPath, protoContent, 'utf-8');

  console.log(`Written to ${outputPath}`);
}

main();

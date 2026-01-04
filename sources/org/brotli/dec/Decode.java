package org.brotli.dec;

/* loaded from: classes3.dex */
final class Decode {
    private static final int CODE_LENGTH_CODES = 18;
    private static final int CODE_LENGTH_REPEAT_CODE = 16;
    private static final int DEFAULT_CODE_LENGTH = 8;
    private static final int DISTANCE_CONTEXT_BITS = 2;
    private static final int HUFFMAN_TABLE_BITS = 8;
    private static final int HUFFMAN_TABLE_MASK = 255;
    private static final int LITERAL_CONTEXT_BITS = 6;
    private static final int NUM_BLOCK_LENGTH_CODES = 26;
    private static final int NUM_DISTANCE_SHORT_CODES = 16;
    private static final int NUM_INSERT_AND_COPY_CODES = 704;
    private static final int NUM_LITERAL_CODES = 256;
    private static final int[] CODE_LENGTH_CODE_ORDER = {1, 2, 3, 4, 0, 5, 17, 6, 16, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static final int[] DISTANCE_SHORT_CODE_INDEX_OFFSET = {3, 2, 1, 0, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2};
    private static final int[] DISTANCE_SHORT_CODE_VALUE_OFFSET = {0, 0, 0, 0, -1, 1, -2, 2, -3, 3, -1, 1, -2, 2, -3, 3};
    private static final int[] FIXED_TABLE = {131072, 131076, 131075, 196610, 131072, 131076, 131075, 262145, 131072, 131076, 131075, 196610, 131072, 131076, 131075, 262149};

    Decode() {
    }

    private static int decodeVarLenUnsignedByte(BitReader bitReader) {
        if (BitReader.readBits(bitReader, 1) == 0) {
            return 0;
        }
        int readBits = BitReader.readBits(bitReader, 3);
        if (readBits == 0) {
            return 1;
        }
        return BitReader.readBits(bitReader, readBits) + (1 << readBits);
    }

    private static void decodeMetaBlockLength(BitReader bitReader, State state) {
        state.inputEnd = BitReader.readBits(bitReader, 1) == 1;
        state.metaBlockLength = 0;
        state.isUncompressed = false;
        state.isMetadata = false;
        if (!state.inputEnd || BitReader.readBits(bitReader, 1) == 0) {
            int readBits = BitReader.readBits(bitReader, 2) + 4;
            if (readBits == 7) {
                state.isMetadata = true;
                if (BitReader.readBits(bitReader, 1) != 0) {
                    throw new BrotliRuntimeException("Corrupted reserved bit");
                }
                int readBits2 = BitReader.readBits(bitReader, 2);
                if (readBits2 == 0) {
                    return;
                }
                for (int i = 0; i < readBits2; i++) {
                    int readBits3 = BitReader.readBits(bitReader, 8);
                    if (readBits3 == 0 && i + 1 == readBits2 && readBits2 > 1) {
                        throw new BrotliRuntimeException("Exuberant nibble");
                    }
                    state.metaBlockLength = (readBits3 << (i * 8)) | state.metaBlockLength;
                }
            } else {
                for (int i2 = 0; i2 < readBits; i2++) {
                    int readBits4 = BitReader.readBits(bitReader, 4);
                    if (readBits4 == 0 && i2 + 1 == readBits && readBits > 4) {
                        throw new BrotliRuntimeException("Exuberant nibble");
                    }
                    state.metaBlockLength = (readBits4 << (i2 * 4)) | state.metaBlockLength;
                }
            }
            state.metaBlockLength++;
            if (state.inputEnd) {
                return;
            }
            state.isUncompressed = BitReader.readBits(bitReader, 1) == 1;
        }
    }

    private static int readSymbol(int[] iArr, int i, BitReader bitReader) {
        int i2 = (int) (bitReader.accumulator >>> bitReader.bitOffset);
        int i3 = i + (i2 & 255);
        int i4 = iArr[i3];
        int i5 = i4 >> 16;
        int i6 = i4 & 65535;
        if (i5 <= 8) {
            bitReader.bitOffset += i5;
            return i6;
        }
        int i7 = i3 + i6 + ((i2 & ((1 << i5) - 1)) >>> 8);
        bitReader.bitOffset += (iArr[i7] >> 16) + 8;
        return iArr[i7] & 65535;
    }

    private static int readBlockLength(int[] iArr, int i, BitReader bitReader) {
        BitReader.fillBitWindow(bitReader);
        int readSymbol = readSymbol(iArr, i, bitReader);
        return Prefix.BLOCK_LENGTH_OFFSET[readSymbol] + BitReader.readBits(bitReader, Prefix.BLOCK_LENGTH_N_BITS[readSymbol]);
    }

    private static int translateShortCodes(int i, int[] iArr, int i2) {
        return i < 16 ? iArr[(i2 + DISTANCE_SHORT_CODE_INDEX_OFFSET[i]) & 3] + DISTANCE_SHORT_CODE_VALUE_OFFSET[i] : i - 15;
    }

    private static void moveToFront(int[] iArr, int i) {
        int i2 = iArr[i];
        while (i > 0) {
            iArr[i] = iArr[i - 1];
            i--;
        }
        iArr[0] = i2;
    }

    private static void inverseMoveToFrontTransform(byte[] bArr, int i) {
        int[] iArr = new int[256];
        for (int i2 = 0; i2 < 256; i2++) {
            iArr[i2] = i2;
        }
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = bArr[i3] & 255;
            bArr[i3] = (byte) iArr[i4];
            if (i4 != 0) {
                moveToFront(iArr, i4);
            }
        }
    }

    private static void readHuffmanCodeLengths(int[] iArr, int i, int[] iArr2, BitReader bitReader) {
        int[] iArr3 = new int[32];
        Huffman.buildHuffmanTable(iArr3, 0, 5, iArr, 18);
        int i2 = 8;
        int i3 = 32768;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < i && i3 > 0) {
            BitReader.readMoreInput(bitReader);
            BitReader.fillBitWindow(bitReader);
            int i7 = ((int) (bitReader.accumulator >>> bitReader.bitOffset)) & 31;
            bitReader.bitOffset += iArr3[i7] >> 16;
            int i8 = iArr3[i7] & 65535;
            if (i8 < 16) {
                int i9 = i4 + 1;
                iArr2[i4] = i8;
                if (i8 != 0) {
                    i3 -= 32768 >> i8;
                    i4 = i9;
                    i2 = i8;
                } else {
                    i4 = i9;
                }
                i6 = 0;
            } else {
                int i10 = i8 - 14;
                int i11 = i8 == 16 ? i2 : 0;
                if (i5 != i11) {
                    i6 = 0;
                    i5 = i11;
                }
                int readBits = (i6 > 0 ? (i6 - 2) << i10 : i6) + BitReader.readBits(bitReader, i10) + 3;
                int i12 = readBits - i6;
                if (i4 + i12 > i) {
                    throw new BrotliRuntimeException("symbol + repeatDelta > numSymbols");
                }
                int i13 = 0;
                while (i13 < i12) {
                    iArr2[i4] = i5;
                    i13++;
                    i4++;
                }
                if (i5 != 0) {
                    i3 -= i12 << (15 - i5);
                }
                i6 = readBits;
            }
        }
        if (i3 != 0) {
            throw new BrotliRuntimeException("Unused space");
        }
        Utils.fillWithZeroes(iArr2, i4, i - i4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00c1, code lost:
    
        if (r8 == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0073, code lost:
    
        if (r4 != r3) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void readHuffmanCode(int r16, int[] r17, int r18, org.brotli.dec.BitReader r19) {
        /*
            Method dump skipped, instructions count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.brotli.dec.Decode.readHuffmanCode(int, int[], int, org.brotli.dec.BitReader):void");
    }

    private static int decodeContextMap(int i, byte[] bArr, BitReader bitReader) {
        BitReader.readMoreInput(bitReader);
        int decodeVarLenUnsignedByte = decodeVarLenUnsignedByte(bitReader) + 1;
        if (decodeVarLenUnsignedByte == 1) {
            Utils.fillWithZeroes(bArr, 0, i);
            return decodeVarLenUnsignedByte;
        }
        int readBits = BitReader.readBits(bitReader, 1) == 1 ? BitReader.readBits(bitReader, 4) + 1 : 0;
        int[] iArr = new int[1080];
        readHuffmanCode(decodeVarLenUnsignedByte + readBits, iArr, 0, bitReader);
        int i2 = 0;
        while (i2 < i) {
            BitReader.readMoreInput(bitReader);
            BitReader.fillBitWindow(bitReader);
            int readSymbol = readSymbol(iArr, 0, bitReader);
            if (readSymbol == 0) {
                bArr[i2] = 0;
            } else if (readSymbol <= readBits) {
                for (int readBits2 = (1 << readSymbol) + BitReader.readBits(bitReader, readSymbol); readBits2 != 0; readBits2--) {
                    if (i2 >= i) {
                        throw new BrotliRuntimeException("Corrupted context map");
                    }
                    bArr[i2] = 0;
                    i2++;
                }
            } else {
                bArr[i2] = (byte) (readSymbol - readBits);
            }
            i2++;
        }
        if (BitReader.readBits(bitReader, 1) == 1) {
            inverseMoveToFrontTransform(bArr, i);
        }
        return decodeVarLenUnsignedByte;
    }

    private static void decodeBlockTypeAndLength(State state, int i) {
        int i2;
        BitReader bitReader = state.br;
        int[] iArr = state.blockTypeRb;
        int i3 = i * 2;
        BitReader.fillBitWindow(bitReader);
        int i4 = i * 1080;
        int readSymbol = readSymbol(state.blockTypeTrees, i4, bitReader);
        state.blockLength[i] = readBlockLength(state.blockLenTrees, i4, bitReader);
        if (readSymbol == 1) {
            i2 = iArr[i3 + 1] + 1;
        } else {
            i2 = readSymbol == 0 ? iArr[i3] : readSymbol - 2;
        }
        if (i2 >= state.numBlockTypes[i]) {
            i2 -= state.numBlockTypes[i];
        }
        int i5 = i3 + 1;
        iArr[i3] = iArr[i5];
        iArr[i5] = i2;
    }

    private static void decodeLiteralBlockSwitch(State state) {
        decodeBlockTypeAndLength(state, 0);
        int i = state.blockTypeRb[1];
        state.contextMapSlice = i << 6;
        state.literalTreeIndex = state.contextMap[state.contextMapSlice] & 255;
        state.literalTree = state.hGroup0.trees[state.literalTreeIndex];
        byte b = state.contextModes[i];
        state.contextLookupOffset1 = Context.LOOKUP_OFFSETS[b];
        state.contextLookupOffset2 = Context.LOOKUP_OFFSETS[b + 1];
    }

    private static void decodeCommandBlockSwitch(State state) {
        decodeBlockTypeAndLength(state, 1);
        state.treeCommandOffset = state.hGroup1.trees[state.blockTypeRb[3]];
    }

    private static void decodeDistanceBlockSwitch(State state) {
        decodeBlockTypeAndLength(state, 2);
        state.distContextMapSlice = state.blockTypeRb[5] << 2;
    }

    private static void maybeReallocateRingBuffer(State state) {
        int i;
        int i2;
        int i3 = state.maxRingBufferSize;
        if (i3 > state.expectedTotalSize) {
            while (true) {
                int i4 = i3 >> 1;
                if (i4 <= ((int) state.expectedTotalSize) + state.customDictionary.length) {
                    break;
                } else {
                    i3 = i4;
                }
            }
            if (!state.inputEnd && i3 < 16384 && state.maxRingBufferSize >= 16384) {
                i3 = 16384;
            }
        }
        if (i3 <= state.ringBufferSize) {
            return;
        }
        byte[] bArr = new byte[i3 + 37];
        if (state.ringBuffer != null) {
            System.arraycopy(state.ringBuffer, 0, bArr, 0, state.ringBufferSize);
        } else if (state.customDictionary.length != 0) {
            int length = state.customDictionary.length;
            if (length > state.maxBackwardDistance) {
                i2 = length - state.maxBackwardDistance;
                i = state.maxBackwardDistance;
            } else {
                i = length;
                i2 = 0;
            }
            System.arraycopy(state.customDictionary, i2, bArr, 0, i);
            state.pos = i;
            state.bytesToIgnore = i;
        }
        state.ringBuffer = bArr;
        state.ringBufferSize = i3;
    }

    private static void readMetablockInfo(State state) {
        BitReader bitReader = state.br;
        if (state.inputEnd) {
            state.nextRunningState = 10;
            state.bytesToWrite = state.pos;
            state.bytesWritten = 0;
            state.runningState = 12;
            return;
        }
        state.hGroup0.codes = null;
        state.hGroup0.trees = null;
        state.hGroup1.codes = null;
        state.hGroup1.trees = null;
        state.hGroup2.codes = null;
        state.hGroup2.trees = null;
        BitReader.readMoreInput(bitReader);
        decodeMetaBlockLength(bitReader, state);
        if (state.metaBlockLength != 0 || state.isMetadata) {
            if (state.isUncompressed || state.isMetadata) {
                BitReader.jumpToByteBoundary(bitReader);
                state.runningState = state.isMetadata ? 4 : 5;
            } else {
                state.runningState = 2;
            }
            if (state.isMetadata) {
                return;
            }
            state.expectedTotalSize += state.metaBlockLength;
            if (state.ringBufferSize < state.maxRingBufferSize) {
                maybeReallocateRingBuffer(state);
            }
        }
    }

    private static void readMetablockHuffmanCodesAndContextMaps(State state) {
        BitReader bitReader = state.br;
        for (int i = 0; i < 3; i++) {
            state.numBlockTypes[i] = decodeVarLenUnsignedByte(bitReader) + 1;
            state.blockLength[i] = 268435456;
            if (state.numBlockTypes[i] > 1) {
                int i2 = i * 1080;
                readHuffmanCode(state.numBlockTypes[i] + 2, state.blockTypeTrees, i2, bitReader);
                readHuffmanCode(26, state.blockLenTrees, i2, bitReader);
                state.blockLength[i] = readBlockLength(state.blockLenTrees, i2, bitReader);
            }
        }
        BitReader.readMoreInput(bitReader);
        state.distancePostfixBits = BitReader.readBits(bitReader, 2);
        state.numDirectDistanceCodes = (BitReader.readBits(bitReader, 4) << state.distancePostfixBits) + 16;
        state.distancePostfixMask = (1 << state.distancePostfixBits) - 1;
        int i3 = state.numDirectDistanceCodes + (48 << state.distancePostfixBits);
        state.contextModes = new byte[state.numBlockTypes[0]];
        int i4 = 0;
        while (i4 < state.numBlockTypes[0]) {
            int min = Math.min(i4 + 96, state.numBlockTypes[0]);
            while (i4 < min) {
                state.contextModes[i4] = (byte) (BitReader.readBits(bitReader, 2) << 1);
                i4++;
            }
            BitReader.readMoreInput(bitReader);
        }
        state.contextMap = new byte[state.numBlockTypes[0] << 6];
        int decodeContextMap = decodeContextMap(state.numBlockTypes[0] << 6, state.contextMap, bitReader);
        state.trivialLiteralContext = true;
        int i5 = 0;
        while (true) {
            if (i5 >= (state.numBlockTypes[0] << 6)) {
                break;
            }
            if (state.contextMap[i5] != (i5 >> 6)) {
                state.trivialLiteralContext = false;
                break;
            }
            i5++;
        }
        state.distContextMap = new byte[state.numBlockTypes[2] << 2];
        int decodeContextMap2 = decodeContextMap(state.numBlockTypes[2] << 2, state.distContextMap, bitReader);
        HuffmanTreeGroup.init(state.hGroup0, 256, decodeContextMap);
        HuffmanTreeGroup.init(state.hGroup1, NUM_INSERT_AND_COPY_CODES, state.numBlockTypes[1]);
        HuffmanTreeGroup.init(state.hGroup2, i3, decodeContextMap2);
        HuffmanTreeGroup.decode(state.hGroup0, bitReader);
        HuffmanTreeGroup.decode(state.hGroup1, bitReader);
        HuffmanTreeGroup.decode(state.hGroup2, bitReader);
        state.contextMapSlice = 0;
        state.distContextMapSlice = 0;
        state.contextLookupOffset1 = Context.LOOKUP_OFFSETS[state.contextModes[0]];
        state.contextLookupOffset2 = Context.LOOKUP_OFFSETS[state.contextModes[0] + 1];
        state.literalTreeIndex = 0;
        state.literalTree = state.hGroup0.trees[0];
        state.treeCommandOffset = state.hGroup1.trees[0];
        int[] iArr = state.blockTypeRb;
        int[] iArr2 = state.blockTypeRb;
        state.blockTypeRb[4] = 1;
        iArr2[2] = 1;
        iArr[0] = 1;
        int[] iArr3 = state.blockTypeRb;
        int[] iArr4 = state.blockTypeRb;
        state.blockTypeRb[5] = 0;
        iArr4[3] = 0;
        iArr3[1] = 0;
    }

    private static void copyUncompressedData(State state) {
        BitReader bitReader = state.br;
        byte[] bArr = state.ringBuffer;
        if (state.metaBlockLength <= 0) {
            BitReader.reload(bitReader);
            state.runningState = 1;
            return;
        }
        int min = Math.min(state.ringBufferSize - state.pos, state.metaBlockLength);
        BitReader.copyBytes(bitReader, bArr, state.pos, min);
        state.metaBlockLength -= min;
        state.pos += min;
        if (state.pos == state.ringBufferSize) {
            state.nextRunningState = 5;
            state.bytesToWrite = state.ringBufferSize;
            state.bytesWritten = 0;
            state.runningState = 12;
            return;
        }
        BitReader.reload(bitReader);
        state.runningState = 1;
    }

    private static boolean writeRingBuffer(State state) {
        if (state.bytesToIgnore != 0) {
            state.bytesWritten += state.bytesToIgnore;
            state.bytesToIgnore = 0;
        }
        int min = Math.min(state.outputLength - state.outputUsed, state.bytesToWrite - state.bytesWritten);
        if (min != 0) {
            System.arraycopy(state.ringBuffer, state.bytesWritten, state.output, state.outputOffset + state.outputUsed, min);
            state.outputUsed += min;
            state.bytesWritten += min;
        }
        return state.outputUsed < state.outputLength;
    }

    static void setCustomDictionary(State state, byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        state.customDictionary = bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x034c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0015 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0015 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x00e7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x014d A[LOOP:2: B:55:0x014d->B:136:?, LOOP_START] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x020f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void decompress(org.brotli.dec.State r19) {
        /*
            Method dump skipped, instructions count: 972
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.brotli.dec.Decode.decompress(org.brotli.dec.State):void");
    }
}

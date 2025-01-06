package org.bouncycastle.oer;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.Streams;

/* loaded from: classes3.dex */
public class OERInputStream extends FilterInputStream {
    private static final int[] bits = {1, 2, 4, 8, 16, 32, 64, 128};
    protected PrintWriter debugOutput;
    private int maxByteAllocation;

    /* renamed from: org.bouncycastle.oer.OERInputStream$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType;

        static {
            int[] iArr = new int[OERDefinition.BaseType.values().length];
            $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType = iArr;
            try {
                iArr[OERDefinition.BaseType.SEQ_OF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.SEQ.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.CHOICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.INT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.OCTET_STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.UTF8_STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.BIT_STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.NULL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[OERDefinition.BaseType.EXTENSION.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static class Choice extends OERInputStream {
        final int preamble;
        final int tag;
        final int tagClass;

        public Choice(InputStream inputStream) throws Exception {
            super(inputStream);
            int read;
            int read2 = read();
            this.preamble = read2;
            if (read2 < 0) {
                throw new EOFException("expecting preamble byte of choice");
            }
            this.tagClass = read2 & 192;
            int i = read2 & 63;
            if (i >= 63) {
                i = 0;
                do {
                    read = inputStream.read();
                    if (read < 0) {
                        throw new EOFException("expecting further tag bytes");
                    }
                    i = (i << 7) | (read & 127);
                } while ((read & 128) != 0);
            }
            this.tag = i;
        }

        public int getTag() {
            return this.tag;
        }

        public int getTagClass() {
            return this.tagClass;
        }

        public boolean isApplicationTagClass() {
            return this.tagClass == 64;
        }

        public boolean isContextSpecific() {
            return this.tagClass == 128;
        }

        public boolean isPrivateTagClass() {
            return this.tagClass == 192;
        }

        public boolean isUniversalTagClass() {
            return this.tagClass == 0;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("CHOICE(");
            int i = this.tagClass;
            if (i == 0) {
                str = "Universal ";
            } else if (i == 64) {
                str = "Application ";
            } else {
                if (i != 128) {
                    if (i == 192) {
                        str = "Private ";
                    }
                    sb.append("Tag = " + this.tag);
                    sb.append(")");
                    return sb.toString();
                }
                str = "ContextSpecific ";
            }
            sb.append(str);
            sb.append("Tag = " + this.tag);
            sb.append(")");
            return sb.toString();
        }
    }

    private final class LengthInfo {
        private final BigInteger length;
        private final boolean shortForm;

        public LengthInfo(BigInteger bigInteger, boolean z) {
            this.length = bigInteger;
            this.shortForm = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int intLength() {
            return this.length.intValue();
        }
    }

    public static class Sequence extends OERInputStream {
        private final boolean extensionFlagSet;
        private final boolean[] optionalPresent;
        final int preamble;

        public Sequence(InputStream inputStream, int i, boolean z, boolean z2) throws IOException {
            super(inputStream);
            if (i == 0 && !z2 && !z) {
                this.preamble = 0;
                this.optionalPresent = new boolean[0];
                this.extensionFlagSet = false;
                return;
            }
            int read = inputStream.read();
            this.preamble = read;
            if (read < 0) {
                throw new EOFException("expecting preamble byte of sequence");
            }
            this.extensionFlagSet = z2 && (read & 128) == 128;
            int i2 = z2 ? 6 : 7;
            this.optionalPresent = new boolean[i];
            for (int i3 = 0; i3 < this.optionalPresent.length; i3++) {
                if (i2 < 0) {
                    read = inputStream.read();
                    if (read < 0) {
                        throw new EOFException("expecting mask byte sequence");
                    }
                    i2 = 7;
                }
                this.optionalPresent[i3] = (OERInputStream.bits[i2] & read) > 0;
                i2--;
            }
        }

        public boolean hasExtension() {
            return this.extensionFlagSet;
        }

        public boolean hasOptional(int i) {
            return this.optionalPresent[i];
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("SEQ(");
            sb.append(hasExtension() ? "Ext " : "");
            int i = 0;
            while (true) {
                boolean[] zArr = this.optionalPresent;
                if (i >= zArr.length) {
                    sb.append(")");
                    return sb.toString();
                }
                sb.append(zArr[i] ? "1" : "0");
                i++;
            }
        }
    }

    public OERInputStream(InputStream inputStream) {
        super(inputStream);
        this.maxByteAllocation = 1048576;
        this.debugOutput = null;
    }

    public OERInputStream(InputStream inputStream, int i) {
        super(inputStream);
        this.debugOutput = null;
        this.maxByteAllocation = i;
    }

    private ASN1Encodable absent(OERDefinition.Element element) {
        debugPrint(element.appendLabel("Absent"));
        return OEROptional.ABSENT;
    }

    private byte[] allocateArray(int i) {
        if (i <= this.maxByteAllocation) {
            return new byte[i];
        }
        throw new IllegalArgumentException("required byte array size " + i + " was greater than " + this.maxByteAllocation);
    }

    private int countOptionalChildTypes(OERDefinition.Element element) {
        Iterator<OERDefinition.Element> it = element.children.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += !it.next().explicit ? 1 : 0;
        }
        return i;
    }

    public Choice choice() throws Exception {
        return new Choice(this);
    }

    protected void debugPrint(String str) {
        if (this.debugOutput == null) {
            return;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i = -1;
        for (int i2 = 0; i2 != stackTrace.length; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            if (stackTraceElement.getMethodName().equals("debugPrint")) {
                i = 0;
            } else if (stackTraceElement.getClassName().contains("OERInput")) {
                i++;
            }
        }
        while (true) {
            PrintWriter printWriter = this.debugOutput;
            if (i <= 0) {
                printWriter.append((CharSequence) str).append((CharSequence) "\n");
                this.debugOutput.flush();
                return;
            } else {
                printWriter.append((CharSequence) "    ");
                i--;
            }
        }
    }

    public BigInteger enumeration() throws Exception {
        int read = read();
        if (read == -1) {
            throw new EOFException("expecting prefix of enumeration");
        }
        if ((read & 128) != 128) {
            return BigInteger.valueOf(read);
        }
        int i = read & 127;
        if (i == 0) {
            return BigInteger.ZERO;
        }
        byte[] bArr = new byte[i];
        if (Streams.readFully(this, bArr) == i) {
            return new BigInteger(1, bArr);
        }
        throw new EOFException("unable to fully read integer component of enumeration");
    }

    public BigInteger int16() throws Exception {
        return parseInt(false, 2);
    }

    public BigInteger int32() throws Exception {
        return parseInt(false, 4);
    }

    public BigInteger int64() throws Exception {
        return parseInt(false, 8);
    }

    public BigInteger int8() throws Exception {
        return parseInt(false, 1);
    }

    public ASN1Object parse(OERDefinition.Element element) throws Exception {
        ASN1Encodable absent;
        byte[] allocateArray;
        BigInteger bigInteger;
        int i;
        long j;
        switch (AnonymousClass1.$SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType[element.baseType.ordinal()]) {
            case 1:
                byte[] allocateArray2 = allocateArray(readLength().intLength());
                if (Streams.readFully(this, allocateArray2) != allocateArray2.length) {
                    throw new IOException("could not read all of count of seq-of values");
                }
                int intValue = BigIntegers.fromUnsignedByteArray(allocateArray2).intValue();
                debugPrint(element.appendLabel("(len = " + intValue + ")"));
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                for (int i2 = 0; i2 < intValue; i2++) {
                    aSN1EncodableVector.add(parse(element.children.get(0)));
                }
                return new DERSequence(aSN1EncodableVector);
            case 2:
                Sequence sequence = sequence(countOptionalChildTypes(element), element.hasDefaultChildren(), element.extensionsInDefinition);
                debugPrint(element.appendLabel(sequence.toString()));
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                for (int i3 = 0; i3 < element.children.size(); i3++) {
                    OERDefinition.Element element2 = element.children.get(i3);
                    if (element2.explicit) {
                        absent = parse(element2);
                    } else if (sequence.hasOptional(element.optionalOrDefaultChildrenInOrder().indexOf(element2))) {
                        absent = OEROptional.getInstance(parse(element2));
                    } else if (element2.getDefaultValue() != null) {
                        aSN1EncodableVector2.add(element2.defaultValue);
                        debugPrint("Using default.");
                    } else {
                        absent = absent(element2);
                    }
                    aSN1EncodableVector2.add(absent);
                }
                return new DERSequence(aSN1EncodableVector2);
            case 3:
                Choice choice = choice();
                debugPrint(element.appendLabel(choice.toString()));
                if (choice.isContextSpecific()) {
                    element.children.get(choice.getTag());
                    return new DERTaggedObject(choice.tag, parse(element.children.get(choice.getTag())));
                }
                if (choice.isApplicationTagClass()) {
                    throw new IllegalStateException("Unimplemented tag type");
                }
                if (choice.isPrivateTagClass()) {
                    throw new IllegalStateException("Unimplemented tag type");
                }
                if (!choice.isUniversalTagClass()) {
                    throw new IllegalStateException("Unimplemented tag type");
                }
                choice.getTag();
                break;
            case 4:
                break;
            case 5:
                int intBytesForRange = element.intBytesForRange();
                if (intBytesForRange != 0) {
                    allocateArray = allocateArray(Math.abs(intBytesForRange));
                    Streams.readFully(this, allocateArray);
                    int length = allocateArray.length;
                    if (length == 1) {
                        i = allocateArray[0];
                    } else if (length == 2) {
                        i = Pack.bigEndianToShort(allocateArray, 0);
                    } else if (length == 4) {
                        i = Pack.bigEndianToInt(allocateArray, 0);
                    } else {
                        if (length != 8) {
                            throw new IllegalStateException("Unknown size");
                        }
                        j = Pack.bigEndianToLong(allocateArray, 0);
                        bigInteger = BigInteger.valueOf(j);
                    }
                    j = i;
                    bigInteger = BigInteger.valueOf(j);
                } else if (element.isLowerRangeZero()) {
                    allocateArray = allocateArray(readLength().intLength());
                    Streams.readFully(this, allocateArray);
                    if (allocateArray.length != 0) {
                        bigInteger = BigIntegers.fromUnsignedByteArray(allocateArray);
                    }
                    bigInteger = BigInteger.ZERO;
                } else {
                    allocateArray = allocateArray(readLength().intLength());
                    Streams.readFully(this, allocateArray);
                    if (allocateArray.length != 0) {
                        bigInteger = new BigInteger(allocateArray);
                    }
                    bigInteger = BigInteger.ZERO;
                }
                if (this.debugOutput != null) {
                    debugPrint(element.appendLabel("INTEGER(" + allocateArray.length + StringUtils.SPACE + bigInteger.toString(16) + ")"));
                }
                return new ASN1Integer(bigInteger);
            case 6:
                int intLength = (element.upperBound == null || !element.upperBound.equals(element.lowerBound)) ? readLength().intLength() : element.upperBound.intValue();
                byte[] allocateArray3 = allocateArray(intLength);
                if (Streams.readFully(this, allocateArray3) != intLength) {
                    throw new IOException("did not read all of " + element.label);
                }
                if (this.debugOutput != null) {
                    debugPrint(element.appendLabel("OCTET STRING (" + allocateArray3.length + ") = " + Hex.toHexString(allocateArray3, 0, Math.min(allocateArray3.length, 32))));
                }
                return new DEROctetString(allocateArray3);
            case 7:
                byte[] allocateArray4 = allocateArray(readLength().intLength());
                if (Streams.readFully(this, allocateArray4) != allocateArray4.length) {
                    throw new IOException("could not read all of utf 8 string");
                }
                String fromUTF8ByteArray = Strings.fromUTF8ByteArray(allocateArray4);
                if (this.debugOutput != null) {
                    debugPrint(element.appendLabel("UTF8 String (" + allocateArray4.length + ") = " + fromUTF8ByteArray));
                }
                return new DERUTF8String(fromUTF8ByteArray);
            case 8:
                byte[] allocateArray5 = element.isFixedLength() ? new byte[element.lowerBound.intValue() / 8] : allocateArray((BigInteger.ZERO.compareTo(element.upperBound) > 0 ? element.upperBound.intValue() : readLength().intLength()) / 8);
                Streams.readFully(this, allocateArray5);
                if (this.debugOutput != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("BIT STRING(" + (allocateArray5.length * 8) + ") = ");
                    for (int i4 = 0; i4 != allocateArray5.length; i4++) {
                        byte b = allocateArray5[i4];
                        for (int i5 = 0; i5 < 8; i5++) {
                            stringBuffer.append((b & 128) > 0 ? "1" : "0");
                            b = (byte) (b << 1);
                        }
                    }
                    debugPrint(element.appendLabel(stringBuffer.toString()));
                }
                return new DERBitString(allocateArray5);
            case 9:
                debugPrint(element.appendLabel("NULL"));
                return DERNull.INSTANCE;
            case 10:
                LengthInfo readLength = readLength();
                byte[] bArr = new byte[readLength.intLength()];
                if (Streams.readFully(this, bArr) != readLength.intLength()) {
                    throw new IOException("could not read all of count of open value in choice (...) ");
                }
                debugPrint("ext " + readLength.intLength() + StringUtils.SPACE + Hex.toHexString(bArr));
                return new DEROctetString(bArr);
            default:
                throw new IllegalStateException("Unhandled type " + element.baseType);
        }
        BigInteger enumeration = enumeration();
        debugPrint(element.appendLabel("ENUM(" + enumeration + ") = " + element.children.get(enumeration.intValue()).label));
        return new ASN1Enumerated(enumeration);
    }

    public BigInteger parseInt(boolean z, int i) throws Exception {
        byte[] bArr = new byte[i];
        if (Streams.readFully(this, bArr) == i) {
            return z ? new BigInteger(1, bArr) : new BigInteger(bArr);
        }
        throw new IllegalStateException("integer not fully read");
    }

    public LengthInfo readLength() throws Exception {
        int read = read();
        if (read == -1) {
            throw new EOFException("expecting length");
        }
        if ((read & 128) == 0) {
            return new LengthInfo(BigInteger.valueOf(read & 127), true);
        }
        int i = read & 127;
        byte[] bArr = new byte[i];
        if (Streams.readFully(this, bArr) != i) {
            throw new EOFException("did not read all bytes of length definition");
        }
        Hex.toHexString(bArr);
        return new LengthInfo(BigIntegers.fromUnsignedByteArray(bArr), false);
    }

    public Sequence sequence(int i, boolean z, boolean z2) throws Exception {
        return new Sequence(this, i, z, z2);
    }

    public BigInteger uint16() throws Exception {
        return parseInt(true, 2);
    }

    public BigInteger uint32() throws Exception {
        return parseInt(true, 4);
    }

    public BigInteger uint64() throws Exception {
        return parseInt(false, 8);
    }

    public BigInteger uint8() throws Exception {
        return parseInt(true, 1);
    }
}

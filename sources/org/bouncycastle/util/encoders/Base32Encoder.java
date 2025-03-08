package org.bouncycastle.util.encoders;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes3.dex */
public class Base32Encoder implements Encoder {
    private static final byte[] DEAULT_ENCODING_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 50, 51, 52, 53, 54, 55};
    private static final byte DEFAULT_PADDING = 61;
    private final byte[] decodingTable;
    private final byte[] encodingTable;
    private final byte padding;

    public Base32Encoder() {
        this.decodingTable = new byte[128];
        this.encodingTable = DEAULT_ENCODING_TABLE;
        this.padding = (byte) 61;
        initialiseDecodingTable();
    }

    public Base32Encoder(byte[] bArr, byte b) {
        this.decodingTable = new byte[128];
        if (bArr.length != 32) {
            throw new IllegalArgumentException("encoding table needs to be length 32");
        }
        this.encodingTable = Arrays.clone(bArr);
        this.padding = b;
        initialiseDecodingTable();
    }

    private int decodeLastBlock(OutputStream outputStream, char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) throws IOException {
        char c9 = this.padding;
        if (c8 != c9) {
            byte[] bArr = this.decodingTable;
            byte b = bArr[c];
            byte b2 = bArr[c2];
            byte b3 = bArr[c3];
            byte b4 = bArr[c4];
            byte b5 = bArr[c5];
            byte b6 = bArr[c6];
            byte b7 = bArr[c7];
            byte b8 = bArr[c8];
            if ((b | b2 | b3 | b4 | b5 | b6 | b7 | b8) < 0) {
                throw new IOException("invalid characters encountered at end of base32 data");
            }
            outputStream.write((b << 3) | (b2 >> 2));
            outputStream.write((b2 << 6) | (b3 << 1) | (b4 >> 4));
            outputStream.write((b4 << 4) | (b5 >> 1));
            outputStream.write((b5 << 7) | (b6 << 2) | (b7 >> 3));
            outputStream.write((b7 << 5) | b8);
            return 5;
        }
        if (c7 != c9) {
            byte[] bArr2 = this.decodingTable;
            byte b9 = bArr2[c];
            byte b10 = bArr2[c2];
            byte b11 = bArr2[c3];
            byte b12 = bArr2[c4];
            byte b13 = bArr2[c5];
            byte b14 = bArr2[c6];
            byte b15 = bArr2[c7];
            if ((b9 | b10 | b11 | b12 | b13 | b14 | b15) < 0) {
                throw new IOException("invalid characters encountered at end of base32 data");
            }
            outputStream.write((b9 << 3) | (b10 >> 2));
            outputStream.write((b10 << 6) | (b11 << 1) | (b12 >> 4));
            outputStream.write((b12 << 4) | (b13 >> 1));
            outputStream.write((b13 << 7) | (b14 << 2) | (b15 >> 3));
            return 4;
        }
        if (c6 != c9) {
            throw new IOException("invalid characters encountered at end of base32 data");
        }
        if (c5 != c9) {
            byte[] bArr3 = this.decodingTable;
            byte b16 = bArr3[c];
            byte b17 = bArr3[c2];
            byte b18 = bArr3[c3];
            byte b19 = bArr3[c4];
            byte b20 = bArr3[c5];
            if ((b16 | b17 | b18 | b19 | b20) < 0) {
                throw new IOException("invalid characters encountered at end of base32 data");
            }
            outputStream.write((b16 << 3) | (b17 >> 2));
            outputStream.write((b17 << 6) | (b18 << 1) | (b19 >> 4));
            outputStream.write((b19 << 4) | (b20 >> 1));
            return 3;
        }
        if (c4 == c9) {
            if (c3 != c9) {
                throw new IOException("invalid characters encountered at end of base32 data");
            }
            byte[] bArr4 = this.decodingTable;
            byte b21 = bArr4[c];
            byte b22 = bArr4[c2];
            if ((b21 | b22) < 0) {
                throw new IOException("invalid characters encountered at end of base32 data");
            }
            outputStream.write((b21 << 3) | (b22 >> 2));
            return 1;
        }
        byte[] bArr5 = this.decodingTable;
        byte b23 = bArr5[c];
        byte b24 = bArr5[c2];
        byte b25 = bArr5[c3];
        byte b26 = bArr5[c4];
        if ((b23 | b24 | b25 | b26) < 0) {
            throw new IOException("invalid characters encountered at end of base32 data");
        }
        outputStream.write((b23 << 3) | (b24 >> 2));
        outputStream.write((b24 << 6) | (b25 << 1) | (b26 >> 4));
        return 2;
    }

    private void encodeBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte b = bArr[i];
        int i3 = bArr[i + 1] & 255;
        int i4 = bArr[i + 2] & 255;
        int i5 = bArr[i + 3] & 255;
        byte b2 = bArr[i + 4];
        byte[] bArr3 = this.encodingTable;
        bArr2[i2] = bArr3[(b >>> 3) & 31];
        bArr2[i2 + 1] = bArr3[((b << 2) | (i3 >>> 6)) & 31];
        bArr2[i2 + 2] = bArr3[(i3 >>> 1) & 31];
        bArr2[i2 + 3] = bArr3[((i3 << 4) | (i4 >>> 4)) & 31];
        bArr2[i2 + 4] = bArr3[((i4 << 1) | (i5 >>> 7)) & 31];
        bArr2[i2 + 5] = bArr3[(i5 >>> 2) & 31];
        bArr2[i2 + 6] = bArr3[(((b2 & 255) >>> 5) | (i5 << 3)) & 31];
        bArr2[i2 + 7] = bArr3[b2 & Ascii.US];
    }

    private boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    private int nextI(byte[] bArr, int i, int i2) {
        while (i < i2 && ignore((char) bArr[i])) {
            i++;
        }
        return i;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        byte[] byteArray = Strings.toByteArray(str);
        return decode(byteArray, 0, byteArray.length, outputStream);
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        byte[] bArr2 = new byte[55];
        int i3 = i + i2;
        while (i3 > i && ignore((char) bArr[i3 - 1])) {
            i3--;
        }
        if (i3 == 0) {
            return 0;
        }
        int i4 = i3;
        int i5 = 0;
        while (i4 > i && i5 != 8) {
            if (!ignore((char) bArr[i4 - 1])) {
                i5++;
            }
            i4--;
        }
        int nextI = nextI(bArr, i, i4);
        int i6 = 0;
        int i7 = 0;
        while (nextI < i4) {
            int i8 = nextI + 1;
            byte b = this.decodingTable[bArr[nextI]];
            int nextI2 = nextI(bArr, i8, i4);
            int i9 = nextI2 + 1;
            byte b2 = this.decodingTable[bArr[nextI2]];
            int nextI3 = nextI(bArr, i9, i4);
            int i10 = nextI3 + 1;
            byte b3 = this.decodingTable[bArr[nextI3]];
            int nextI4 = nextI(bArr, i10, i4);
            int i11 = nextI4 + 1;
            byte b4 = this.decodingTable[bArr[nextI4]];
            int nextI5 = nextI(bArr, i11, i4);
            int i12 = nextI5 + 1;
            byte b5 = this.decodingTable[bArr[nextI5]];
            int nextI6 = nextI(bArr, i12, i4);
            int i13 = nextI6 + 1;
            byte b6 = this.decodingTable[bArr[nextI6]];
            int nextI7 = nextI(bArr, i13, i4);
            int i14 = i3;
            int i15 = nextI7 + 1;
            byte b7 = this.decodingTable[bArr[nextI7]];
            int nextI8 = nextI(bArr, i15, i4);
            int i16 = i4;
            int i17 = nextI8 + 1;
            byte b8 = this.decodingTable[bArr[nextI8]];
            if ((b | b2 | b3 | b4 | b5 | b6 | b7 | b8) < 0) {
                throw new IOException("invalid characters encountered in base32 data");
            }
            bArr2[i6] = (byte) ((b << 3) | (b2 >> 2));
            bArr2[i6 + 1] = (byte) ((b2 << 6) | (b3 << 1) | (b4 >> 4));
            bArr2[i6 + 2] = (byte) ((b4 << 4) | (b5 >> 1));
            int i18 = i6 + 4;
            bArr2[i6 + 3] = (byte) ((b6 << 2) | (b5 << 7) | (b7 >> 3));
            i6 += 5;
            bArr2[i18] = (byte) ((b7 << 5) | b8);
            if (i6 == 55) {
                outputStream.write(bArr2);
                i6 = 0;
            }
            i7 += 5;
            int nextI9 = nextI(bArr, i17, i16);
            i4 = i16;
            i3 = i14;
            nextI = nextI9;
        }
        int i19 = i3;
        if (i6 > 0) {
            outputStream.write(bArr2, 0, i6);
        }
        int nextI10 = nextI(bArr, nextI, i19);
        int nextI11 = nextI(bArr, nextI10 + 1, i19);
        int nextI12 = nextI(bArr, nextI11 + 1, i19);
        int nextI13 = nextI(bArr, nextI12 + 1, i19);
        int nextI14 = nextI(bArr, nextI13 + 1, i19);
        int nextI15 = nextI(bArr, nextI14 + 1, i19);
        int nextI16 = nextI(bArr, nextI15 + 1, i19);
        return i7 + decodeLastBlock(outputStream, (char) bArr[nextI10], (char) bArr[nextI11], (char) bArr[nextI12], (char) bArr[nextI13], (char) bArr[nextI14], (char) bArr[nextI15], (char) bArr[nextI16], (char) bArr[nextI(bArr, nextI16 + 1, i19)]);
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        if (i2 < 0) {
            return 0;
        }
        byte[] bArr2 = new byte[72];
        int i3 = i2;
        while (i3 > 0) {
            int min = Math.min(45, i3);
            outputStream.write(bArr2, 0, encode(bArr, i, min, bArr2, 0));
            i += min;
            i3 -= min;
        }
        return ((i2 + 2) / 3) * 4;
    }

    public int encode(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IOException {
        int i4 = (i + i2) - 4;
        int i5 = i;
        int i6 = i3;
        while (i5 < i4) {
            encodeBlock(bArr, i5, bArr2, i6);
            i5 += 5;
            i6 += 8;
        }
        int i7 = i2 - (i5 - i);
        if (i7 > 0) {
            byte[] bArr3 = new byte[5];
            System.arraycopy(bArr, i5, bArr3, 0, i7);
            encodeBlock(bArr3, 0, bArr2, i6);
            if (i7 == 1) {
                byte b = this.padding;
                bArr2[i6 + 2] = b;
                bArr2[i6 + 3] = b;
                bArr2[i6 + 4] = b;
                bArr2[i6 + 5] = b;
                bArr2[i6 + 6] = b;
                bArr2[i6 + 7] = b;
            } else if (i7 == 2) {
                byte b2 = this.padding;
                bArr2[i6 + 4] = b2;
                bArr2[i6 + 5] = b2;
                bArr2[i6 + 6] = b2;
                bArr2[i6 + 7] = b2;
            } else if (i7 == 3) {
                byte b3 = this.padding;
                bArr2[i6 + 5] = b3;
                bArr2[i6 + 6] = b3;
                bArr2[i6 + 7] = b3;
            } else if (i7 == 4) {
                bArr2[i6 + 7] = this.padding;
            }
            i6 += 8;
        }
        return i6 - i3;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getEncodedLength(int i) {
        return ((i + 4) / 5) * 8;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getMaxDecodedLength(int i) {
        return (i / 8) * 5;
    }

    protected void initialiseDecodingTable() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.decodingTable;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (i >= bArr2.length) {
                return;
            }
            this.decodingTable[bArr2[i]] = (byte) i;
            i++;
        }
    }
}

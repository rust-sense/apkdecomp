package org.brotli.dec;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
final class Transform {
    static final Transform[] TRANSFORMS = {new Transform("", 0, ""), new Transform("", 0, StringUtils.SPACE), new Transform(StringUtils.SPACE, 0, StringUtils.SPACE), new Transform("", 12, ""), new Transform("", 10, StringUtils.SPACE), new Transform("", 0, " the "), new Transform(StringUtils.SPACE, 0, ""), new Transform("s ", 0, StringUtils.SPACE), new Transform("", 0, " of "), new Transform("", 10, ""), new Transform("", 0, " and "), new Transform("", 13, ""), new Transform("", 1, ""), new Transform(", ", 0, StringUtils.SPACE), new Transform("", 0, ", "), new Transform(StringUtils.SPACE, 10, StringUtils.SPACE), new Transform("", 0, " in "), new Transform("", 0, " to "), new Transform("e ", 0, StringUtils.SPACE), new Transform("", 0, "\""), new Transform("", 0, "."), new Transform("", 0, "\">"), new Transform("", 0, "\n"), new Transform("", 3, ""), new Transform("", 0, "]"), new Transform("", 0, " for "), new Transform("", 14, ""), new Transform("", 2, ""), new Transform("", 0, " a "), new Transform("", 0, " that "), new Transform(StringUtils.SPACE, 10, ""), new Transform("", 0, ". "), new Transform(".", 0, ""), new Transform(StringUtils.SPACE, 0, ", "), new Transform("", 15, ""), new Transform("", 0, " with "), new Transform("", 0, "'"), new Transform("", 0, " from "), new Transform("", 0, " by "), new Transform("", 16, ""), new Transform("", 17, ""), new Transform(" the ", 0, ""), new Transform("", 4, ""), new Transform("", 0, ". The "), new Transform("", 11, ""), new Transform("", 0, " on "), new Transform("", 0, " as "), new Transform("", 0, " is "), new Transform("", 7, ""), new Transform("", 1, "ing "), new Transform("", 0, "\n\t"), new Transform("", 0, ":"), new Transform(StringUtils.SPACE, 0, ". "), new Transform("", 0, "ed "), new Transform("", 20, ""), new Transform("", 18, ""), new Transform("", 6, ""), new Transform("", 0, "("), new Transform("", 10, ", "), new Transform("", 8, ""), new Transform("", 0, " at "), new Transform("", 0, "ly "), new Transform(" the ", 0, " of "), new Transform("", 5, ""), new Transform("", 9, ""), new Transform(StringUtils.SPACE, 10, ", "), new Transform("", 10, "\""), new Transform(".", 0, "("), new Transform("", 11, StringUtils.SPACE), new Transform("", 10, "\">"), new Transform("", 0, "=\""), new Transform(StringUtils.SPACE, 0, "."), new Transform(".com/", 0, ""), new Transform(" the ", 0, " of the "), new Transform("", 10, "'"), new Transform("", 0, ". This "), new Transform("", 0, ","), new Transform(".", 0, StringUtils.SPACE), new Transform("", 10, "("), new Transform("", 10, "."), new Transform("", 0, " not "), new Transform(StringUtils.SPACE, 0, "=\""), new Transform("", 0, "er "), new Transform(StringUtils.SPACE, 11, StringUtils.SPACE), new Transform("", 0, "al "), new Transform(StringUtils.SPACE, 11, ""), new Transform("", 0, "='"), new Transform("", 11, "\""), new Transform("", 10, ". "), new Transform(StringUtils.SPACE, 0, "("), new Transform("", 0, "ful "), new Transform(StringUtils.SPACE, 10, ". "), new Transform("", 0, "ive "), new Transform("", 0, "less "), new Transform("", 11, "'"), new Transform("", 0, "est "), new Transform(StringUtils.SPACE, 10, "."), new Transform("", 11, "\">"), new Transform(StringUtils.SPACE, 0, "='"), new Transform("", 10, ","), new Transform("", 0, "ize "), new Transform("", 11, "."), new Transform("Â ", 0, ""), new Transform(StringUtils.SPACE, 0, ","), new Transform("", 10, "=\""), new Transform("", 11, "=\""), new Transform("", 0, "ous "), new Transform("", 11, ", "), new Transform("", 10, "='"), new Transform(StringUtils.SPACE, 10, ","), new Transform(StringUtils.SPACE, 11, "=\""), new Transform(StringUtils.SPACE, 11, ", "), new Transform("", 11, ","), new Transform("", 11, "("), new Transform("", 11, ". "), new Transform(StringUtils.SPACE, 11, "."), new Transform("", 11, "='"), new Transform(StringUtils.SPACE, 11, ". "), new Transform(StringUtils.SPACE, 10, "=\""), new Transform(StringUtils.SPACE, 11, "='"), new Transform(StringUtils.SPACE, 10, "='")};
    private final byte[] prefix;
    private final byte[] suffix;
    private final int type;

    Transform(String str, int i, String str2) {
        this.prefix = readUniBytes(str);
        this.type = i;
        this.suffix = readUniBytes(str2);
    }

    static byte[] readUniBytes(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    static int transformDictionaryWord(byte[] bArr, int i, byte[] bArr2, int i2, int i3, Transform transform) {
        int i4 = i;
        for (byte b : transform.prefix) {
            bArr[i4] = b;
            i4++;
        }
        int i5 = transform.type;
        int omitFirst = WordTransformType.getOmitFirst(i5);
        if (omitFirst > i3) {
            omitFirst = i3;
        }
        int i6 = i2 + omitFirst;
        int omitLast = (i3 - omitFirst) - WordTransformType.getOmitLast(i5);
        int i7 = omitLast;
        while (i7 > 0) {
            bArr[i4] = bArr2[i6];
            i7--;
            i4++;
            i6++;
        }
        if (i5 == 11 || i5 == 10) {
            int i8 = i4 - omitLast;
            if (i5 == 10) {
                omitLast = 1;
            }
            while (omitLast > 0) {
                byte b2 = bArr[i8];
                int i9 = b2 & 255;
                if (i9 < 192) {
                    if (i9 >= 97 && i9 <= 122) {
                        bArr[i8] = (byte) (b2 ^ 32);
                    }
                    i8++;
                    omitLast--;
                } else if (i9 < 224) {
                    int i10 = i8 + 1;
                    bArr[i10] = (byte) (bArr[i10] ^ 32);
                    i8 += 2;
                    omitLast -= 2;
                } else {
                    int i11 = i8 + 2;
                    bArr[i11] = (byte) (bArr[i11] ^ 5);
                    i8 += 3;
                    omitLast -= 3;
                }
            }
        }
        for (byte b3 : transform.suffix) {
            bArr[i4] = b3;
            i4++;
        }
        return i4 - i;
    }
}

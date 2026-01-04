package org.brotli.dec;

/* loaded from: classes3.dex */
final class Utils {
    private static final byte[] BYTE_ZEROES = new byte[1024];
    private static final int[] INT_ZEROES = new int[1024];

    Utils() {
    }

    static void fillWithZeroes(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int min = Math.min(i3 + 1024, i2) - i3;
            System.arraycopy(BYTE_ZEROES, 0, bArr, i + i3, min);
            i3 += min;
        }
    }

    static void fillWithZeroes(int[] iArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int min = Math.min(i3 + 1024, i2) - i3;
            System.arraycopy(INT_ZEROES, 0, iArr, i + i3, min);
            i3 += min;
        }
    }
}

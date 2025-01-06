package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
class WotsPlus {
    private final SPHINCSPlusEngine engine;
    private final int w;

    WotsPlus(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
        this.w = sPHINCSPlusEngine.WOTS_W;
    }

    int[] base_w(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i2];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            if (i3 == 0) {
                i6 = bArr[i4];
                i4++;
                i3 += 8;
            }
            i3 -= this.engine.WOTS_LOGW;
            iArr[i5] = (i6 >>> i3) & (i - 1);
            i5++;
        }
        return iArr;
    }

    byte[] chain(byte[] bArr, int i, int i2, byte[] bArr2, ADRS adrs) {
        if (i2 == 0) {
            return Arrays.clone(bArr);
        }
        int i3 = i + i2;
        if (i3 > this.w - 1) {
            return null;
        }
        byte[] chain = chain(bArr, i, i2 - 1, bArr2, adrs);
        adrs.setHashAddress(i3 - 1);
        return this.engine.F(bArr2, adrs, chain);
    }

    public byte[] pkFromSig(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int[] base_w = base_w(bArr2, this.w, this.engine.WOTS_LEN1);
        int i = 0;
        for (int i2 = 0; i2 < this.engine.WOTS_LEN1; i2++) {
            i += (this.w - 1) - base_w[i2];
        }
        int[] concatenate = Arrays.concatenate(base_w, base_w(Arrays.copyOfRange(Pack.intToBigEndian(i << (8 - ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) % 8))), 4 - (((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) + 7) / 8), 4), this.w, this.engine.WOTS_LEN2));
        byte[] bArr4 = new byte[this.engine.N];
        byte[][] bArr5 = new byte[this.engine.WOTS_LEN][];
        for (int i3 = 0; i3 < this.engine.WOTS_LEN; i3++) {
            adrs.setChainAddress(i3);
            System.arraycopy(bArr, this.engine.N * i3, bArr4, 0, this.engine.N);
            int i4 = concatenate[i3];
            bArr5[i3] = chain(bArr4, i4, (this.w - 1) - i4, bArr3, adrs);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr3, adrs2, Arrays.concatenate(bArr5));
    }

    byte[] pkGen(byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        byte[][] bArr3 = new byte[this.engine.WOTS_LEN][];
        for (int i = 0; i < this.engine.WOTS_LEN; i++) {
            ADRS adrs3 = new ADRS(adrs);
            adrs3.setChainAddress(i);
            adrs3.setHashAddress(0);
            bArr3[i] = chain(this.engine.PRF(bArr, adrs3), 0, this.w - 1, bArr2, adrs3);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr3));
    }

    public byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int[] base_w = base_w(bArr, this.w, this.engine.WOTS_LEN1);
        int i = 0;
        for (int i2 = 0; i2 < this.engine.WOTS_LEN1; i2++) {
            i += (this.w - 1) - base_w[i2];
        }
        if (this.engine.WOTS_LOGW % 8 != 0) {
            i <<= 8 - ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) % 8);
        }
        int i3 = ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) + 7) / 8;
        byte[] intToBigEndian = Pack.intToBigEndian(i);
        int[] concatenate = Arrays.concatenate(base_w, base_w(Arrays.copyOfRange(intToBigEndian, i3, intToBigEndian.length), this.w, this.engine.WOTS_LEN2));
        byte[][] bArr4 = new byte[this.engine.WOTS_LEN][];
        for (int i4 = 0; i4 < this.engine.WOTS_LEN; i4++) {
            adrs2.setChainAddress(i4);
            adrs2.setHashAddress(0);
            bArr4[i4] = chain(this.engine.PRF(bArr2, adrs2), 0, concatenate[i4], bArr3, adrs2);
        }
        return Arrays.concatenate(bArr4);
    }
}

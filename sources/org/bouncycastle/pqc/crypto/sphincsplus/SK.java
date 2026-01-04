package org.bouncycastle.pqc.crypto.sphincsplus;

/* loaded from: classes3.dex */
class SK {
    final byte[] prf;
    final byte[] seed;

    SK(byte[] bArr, byte[] bArr2) {
        this.seed = bArr;
        this.prf = bArr2;
    }
}

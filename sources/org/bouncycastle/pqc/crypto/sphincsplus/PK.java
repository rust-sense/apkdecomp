package org.bouncycastle.pqc.crypto.sphincsplus;

/* loaded from: classes3.dex */
class PK {
    final byte[] root;
    final byte[] seed;

    PK(byte[] bArr, byte[] bArr2) {
        this.seed = bArr;
        this.root = bArr2;
    }
}

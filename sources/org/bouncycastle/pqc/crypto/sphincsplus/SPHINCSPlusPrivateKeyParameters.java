package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class SPHINCSPlusPrivateKeyParameters extends SPHINCSPlusKeyParameters {
    final PK pk;
    final SK sk;

    SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, SK sk, PK pk) {
        super(true, sPHINCSPlusParameters);
        this.sk = sk;
        this.pk = pk;
    }

    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr) {
        super(true, sPHINCSPlusParameters);
        int i = sPHINCSPlusParameters.getEngine().N;
        int i2 = i * 4;
        if (bArr.length != i2) {
            throw new IllegalArgumentException("private key encoding does not match parameters");
        }
        int i3 = i * 2;
        this.sk = new SK(Arrays.copyOfRange(bArr, 0, i), Arrays.copyOfRange(bArr, i, i3));
        int i4 = i * 3;
        this.pk = new PK(Arrays.copyOfRange(bArr, i3, i4), Arrays.copyOfRange(bArr, i4, i2));
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.sk.seed, this.sk.prf, this.pk.seed, this.pk.root);
    }

    public byte[] getPrf() {
        return Arrays.clone(this.sk.prf);
    }

    public byte[] getPublicKey() {
        return Arrays.concatenate(this.pk.seed, this.pk.root);
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.pk.seed);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.sk.seed);
    }
}

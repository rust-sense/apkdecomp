package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class ECPrivateKeyParameters extends ECKeyParameters {
    private final BigInteger d;

    public ECPrivateKeyParameters(BigInteger bigInteger, ECDomainParameters eCDomainParameters) {
        super(true, eCDomainParameters);
        this.d = eCDomainParameters.validatePrivateScalar(bigInteger);
    }

    public BigInteger getD() {
        return this.d;
    }
}

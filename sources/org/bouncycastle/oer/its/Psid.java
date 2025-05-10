package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;

/* loaded from: classes3.dex */
public class Psid extends ASN1Integer {
    public Psid(long j) {
        super(j);
        validate();
    }

    public Psid(BigInteger bigInteger) {
        super(bigInteger);
        validate();
    }

    public Psid(byte[] bArr) {
        super(bArr);
        validate();
    }

    public static Psid getInstance(Object obj) {
        return obj instanceof Psid ? (Psid) obj : new Psid(ASN1Integer.getInstance(obj).getValue());
    }

    private void validate() {
        if (BigInteger.ZERO.compareTo(getValue()) >= 0) {
            throw new IllegalStateException("psid must be greater than zero");
        }
    }
}

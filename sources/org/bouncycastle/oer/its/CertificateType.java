package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Enumerated;

/* loaded from: classes3.dex */
public class CertificateType extends ASN1Enumerated {
    public static final CertificateType Explicit = new CertificateType(0);
    public static final CertificateType Implicit = new CertificateType(1);

    protected CertificateType(int i) {
        super(i);
    }

    public static CertificateType getInstance(Object obj) {
        if (obj instanceof CertificateType) {
            return (CertificateType) obj;
        }
        BigInteger value = ASN1Enumerated.getInstance(obj).getValue();
        int intValue = value.intValue();
        if (intValue == 0) {
            return Explicit;
        }
        if (intValue == 1) {
            return Implicit;
        }
        throw new IllegalArgumentException("unaccounted enum value " + value);
    }
}

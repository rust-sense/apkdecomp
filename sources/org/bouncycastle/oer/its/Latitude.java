package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;

/* loaded from: classes3.dex */
public class Latitude extends NinetyDegreeInt {
    public Latitude(long j) {
        super(j);
    }

    public Latitude(BigInteger bigInteger) {
        super(bigInteger);
    }

    public Latitude(byte[] bArr) {
        super(bArr);
    }

    public static Latitude getInstance(Object obj) {
        return obj instanceof Latitude ? (Latitude) obj : obj instanceof NinetyDegreeInt ? new Latitude(((NinetyDegreeInt) obj).getValue()) : new Latitude(ASN1Integer.getInstance(obj).getValue());
    }
}

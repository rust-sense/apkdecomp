package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;

/* loaded from: classes3.dex */
public class Longitude extends OneEightyDegreeInt {
    public Longitude(long j) {
        super(j);
    }

    public Longitude(BigInteger bigInteger) {
        super(bigInteger);
    }

    public Longitude(byte[] bArr) {
        super(bArr);
    }

    public static Longitude getInstance(Object obj) {
        return obj instanceof Longitude ? (Longitude) obj : obj instanceof OneEightyDegreeInt ? new Longitude(((OneEightyDegreeInt) obj).getValue()) : new Longitude(ASN1Integer.getInstance(obj).getValue());
    }
}

package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;

/* loaded from: classes3.dex */
public class HeaderInfoContributorId extends ASN1Integer {
    public HeaderInfoContributorId(long j) {
        super(j);
    }

    public HeaderInfoContributorId(BigInteger bigInteger) {
        super(bigInteger);
    }

    public HeaderInfoContributorId(byte[] bArr) {
        super(bArr);
    }

    public static HeaderInfoContributorId getInstance(Object obj) {
        return obj instanceof HeaderInfoContributorId ? (HeaderInfoContributorId) obj : new HeaderInfoContributorId(ASN1Integer.getInstance(obj).getValue());
    }
}

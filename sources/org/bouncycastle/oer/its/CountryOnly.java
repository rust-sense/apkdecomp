package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;

/* loaded from: classes3.dex */
public class CountryOnly extends Uint16 implements RegionInterface {
    public CountryOnly(int i) {
        super(i);
    }

    public CountryOnly(BigInteger bigInteger) {
        super(bigInteger);
    }

    public static CountryOnly getInstance(Object obj) {
        return obj instanceof CountryOnly ? (CountryOnly) obj : new CountryOnly(ASN1Integer.getInstance(obj).getValue());
    }
}

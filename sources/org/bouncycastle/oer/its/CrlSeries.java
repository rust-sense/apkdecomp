package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;

/* loaded from: classes3.dex */
public class CrlSeries extends Uint16 {
    public CrlSeries(int i) {
        super(i);
    }

    public CrlSeries(BigInteger bigInteger) {
        super(bigInteger);
    }

    public static CrlSeries getInstance(Object obj) {
        return obj instanceof CrlSeries ? (CrlSeries) obj : new CrlSeries(ASN1Integer.getInstance(obj).getValue());
    }
}

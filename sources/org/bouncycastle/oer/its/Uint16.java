package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

/* loaded from: classes3.dex */
public class Uint16 extends ASN1Object {
    private final int value;

    public Uint16(int i) {
        this.value = verify(i);
    }

    public Uint16(BigInteger bigInteger) {
        this.value = bigInteger.intValue();
    }

    public static Uint16 getInstance(Object obj) {
        return obj instanceof Uint16 ? (Uint16) obj : new Uint16(ASN1Integer.getInstance(obj).getValue());
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new ASN1Integer(this.value);
    }

    protected int verify(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Uint16 must be >= 0");
        }
        if (i <= 65535) {
            return i;
        }
        throw new IllegalArgumentException("Uint16 must be <= 0xFFFF");
    }
}

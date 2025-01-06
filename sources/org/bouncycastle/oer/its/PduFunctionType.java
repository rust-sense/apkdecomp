package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;

/* loaded from: classes3.dex */
public class PduFunctionType extends ASN1Integer {
    public static final PduFunctionType tlsHandshake = new PduFunctionType(1);
    public static final PduFunctionType iso21177ExtendedAuth = new PduFunctionType(2);

    public PduFunctionType(long j) {
        super(j);
    }

    public PduFunctionType(BigInteger bigInteger) {
        super(bigInteger);
    }

    public PduFunctionType(byte[] bArr) {
        super(bArr);
    }

    public static PduFunctionType getInstance(Object obj) {
        return obj instanceof PduFunctionType ? (PduFunctionType) obj : obj instanceof ASN1Integer ? new PduFunctionType(((ASN1Integer) obj).getValue()) : getInstance((Object) ASN1Integer.getInstance(obj));
    }
}

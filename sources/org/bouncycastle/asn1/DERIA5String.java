package org.bouncycastle.asn1;

/* loaded from: classes3.dex */
public class DERIA5String extends ASN1IA5String {
    public DERIA5String(String str) {
        this(str, false);
    }

    public DERIA5String(String str, boolean z) {
        super(str, z);
    }

    DERIA5String(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    public static DERIA5String getInstance(Object obj) {
        if (obj == null || (obj instanceof DERIA5String)) {
            return (DERIA5String) obj;
        }
        if (obj instanceof ASN1IA5String) {
            return new DERIA5String(((ASN1IA5String) obj).contents, false);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (DERIA5String) fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static DERIA5String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERIA5String)) ? getInstance((Object) object) : new DERIA5String(ASN1OctetString.getInstance(object).getOctets(), true);
    }
}

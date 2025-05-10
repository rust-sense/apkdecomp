package org.bouncycastle.asn1;

/* loaded from: classes3.dex */
public class DERBMPString extends ASN1BMPString {
    public DERBMPString(String str) {
        super(str);
    }

    DERBMPString(byte[] bArr) {
        super(bArr);
    }

    DERBMPString(char[] cArr) {
        super(cArr);
    }

    public static DERBMPString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERBMPString)) {
            return (DERBMPString) obj;
        }
        if (obj instanceof ASN1BMPString) {
            return new DERBMPString(((ASN1BMPString) obj).string);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (DERBMPString) fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static DERBMPString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERBMPString)) ? getInstance((Object) object) : new DERBMPString(ASN1OctetString.getInstance(object).getOctets());
    }
}

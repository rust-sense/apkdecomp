package org.bouncycastle.asn1;

/* loaded from: classes3.dex */
public class DERGraphicString extends ASN1GraphicString {
    public DERGraphicString(byte[] bArr) {
        this(bArr, true);
    }

    DERGraphicString(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    public static DERGraphicString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERGraphicString)) {
            return (DERGraphicString) obj;
        }
        if (obj instanceof ASN1GraphicString) {
            return new DERGraphicString(((ASN1GraphicString) obj).contents, false);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (DERGraphicString) fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static DERGraphicString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERGraphicString)) ? getInstance((Object) object) : new DERGraphicString(ASN1OctetString.getInstance(object).getOctets());
    }
}

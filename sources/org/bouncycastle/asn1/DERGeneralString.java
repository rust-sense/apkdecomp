package org.bouncycastle.asn1;

/* loaded from: classes3.dex */
public class DERGeneralString extends ASN1GeneralString {
    public DERGeneralString(String str) {
        super(str);
    }

    DERGeneralString(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    public static DERGeneralString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERGeneralString)) {
            return (DERGeneralString) obj;
        }
        if (obj instanceof ASN1GeneralString) {
            return new DERGeneralString(((ASN1GeneralString) obj).contents, false);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (DERGeneralString) fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static DERGeneralString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERGeneralString)) ? getInstance((Object) object) : new DERGeneralString(ASN1OctetString.getInstance(object).getOctets(), true);
    }
}

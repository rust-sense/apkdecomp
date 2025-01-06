package org.bouncycastle.asn1;

/* loaded from: classes3.dex */
public class DERUTF8String extends ASN1UTF8String {
    public DERUTF8String(String str) {
        super(str);
    }

    DERUTF8String(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    public static DERUTF8String getInstance(Object obj) {
        if (obj == null || (obj instanceof DERUTF8String)) {
            return (DERUTF8String) obj;
        }
        if (obj instanceof ASN1UTF8String) {
            return new DERUTF8String(((ASN1UTF8String) obj).contents, false);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (DERUTF8String) fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static DERUTF8String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERUTF8String)) ? getInstance((Object) object) : new DERUTF8String(ASN1OctetString.getInstance(object).getOctets(), true);
    }
}

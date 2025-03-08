package org.bouncycastle.asn1;

/* loaded from: classes3.dex */
public class DERT61String extends ASN1T61String {
    public DERT61String(String str) {
        super(str);
    }

    public DERT61String(byte[] bArr) {
        this(bArr, true);
    }

    DERT61String(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    public static DERT61String getInstance(Object obj) {
        if (obj == null || (obj instanceof DERT61String)) {
            return (DERT61String) obj;
        }
        if (obj instanceof ASN1T61String) {
            return new DERT61String(((ASN1T61String) obj).contents, false);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (DERT61String) fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static DERT61String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERT61String)) ? getInstance((Object) object) : new DERT61String(ASN1OctetString.getInstance(object).getOctets(), true);
    }
}

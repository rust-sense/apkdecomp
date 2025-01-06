package org.bouncycastle.oer.its;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;

/* loaded from: classes3.dex */
public class BasePublicEncryptionKey extends ASN1Object implements ASN1Choice {
    public static final int eciesBrainpoolP256r1 = 1;
    public static final int eciesNistP256 = 0;
    public static final int extension = 2;
    private final int choice;
    private final ASN1Encodable value;

    public static class Builder {
        private int choice;
        private ASN1Encodable value;

        public BasePublicEncryptionKey createBasePublicEncryptionKey() {
            return new BasePublicEncryptionKey(this.choice, this.value);
        }

        public Builder setChoice(int i) {
            this.choice = i;
            return this;
        }

        public Builder setValue(EccCurvePoint eccCurvePoint) {
            this.value = eccCurvePoint;
            return this;
        }
    }

    public BasePublicEncryptionKey(int i, ASN1Encodable aSN1Encodable) {
        this.choice = i;
        this.value = aSN1Encodable;
    }

    public static BasePublicEncryptionKey getInstance(Object obj) {
        ASN1Encodable eccP256CurvePoint;
        if (obj instanceof BasePublicEncryptionKey) {
            return (BasePublicEncryptionKey) obj;
        }
        ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(obj);
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo == 0 || tagNo == 1) {
            eccP256CurvePoint = EccP256CurvePoint.getInstance(aSN1TaggedObject.getObject());
        } else {
            if (tagNo != 2) {
                throw new IllegalStateException("unknown choice " + aSN1TaggedObject.getTagNo());
            }
            eccP256CurvePoint = DEROctetString.getInstance(aSN1TaggedObject.getObject());
        }
        return new BasePublicEncryptionKey(aSN1TaggedObject.getTagNo(), eccP256CurvePoint);
    }

    public int getChoice() {
        return this.choice;
    }

    public ASN1Encodable getValue() {
        return this.value;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(this.choice, this.value);
    }
}

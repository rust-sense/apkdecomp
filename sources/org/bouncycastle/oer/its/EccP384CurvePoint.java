package org.bouncycastle.oer.its;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class EccP384CurvePoint extends EccCurvePoint {
    public static final int compressedY0 = 2;
    public static final int compressedY1 = 3;
    public static final int fill = 1;
    public static final int uncompressedP384 = 4;
    public static final int xOnly = 0;
    private final int choice;
    private final ASN1Encodable value;

    public static class Builder {
        private int choice;
        private ASN1Encodable value;

        /* JADX INFO: Access modifiers changed from: private */
        public EccP384CurvePoint createEccP384CurvePoint() {
            return new EccP384CurvePoint(this.choice, this.value);
        }

        public EccP384CurvePoint createCompressedY0(BigInteger bigInteger) {
            this.choice = 2;
            throw new IllegalStateException("not fully implemented.");
        }

        public EccP384CurvePoint createCompressedY1(BigInteger bigInteger) {
            this.choice = 3;
            throw new IllegalStateException("not fully implemented.");
        }

        public EccP384CurvePoint createFill() {
            this.choice = 1;
            this.value = DERNull.INSTANCE;
            return createEccP384CurvePoint();
        }

        public EccP384CurvePoint createUncompressedP384(BigInteger bigInteger, BigInteger bigInteger2) {
            this.choice = 4;
            this.value = new DERSequence(new ASN1Encodable[]{new DEROctetString(BigIntegers.asUnsignedByteArray(48, bigInteger)), new DEROctetString(BigIntegers.asUnsignedByteArray(48, bigInteger2))});
            return createEccP384CurvePoint();
        }

        public EccP384CurvePoint createXOnly(BigInteger bigInteger) {
            this.choice = 0;
            this.value = new DEROctetString(BigIntegers.asUnsignedByteArray(bigInteger));
            return createEccP384CurvePoint();
        }

        Builder setChoice(int i) {
            this.choice = i;
            return this;
        }

        Builder setValue(ASN1Encodable aSN1Encodable) {
            this.value = aSN1Encodable;
            return this;
        }
    }

    public EccP384CurvePoint(int i, ASN1Encodable aSN1Encodable) {
        this.choice = i;
        this.value = aSN1Encodable;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static EccP384CurvePoint getInstance(Object obj) {
        ASN1Encodable aSN1OctetString;
        if (obj instanceof EccP384CurvePoint) {
            return (EccP384CurvePoint) obj;
        }
        ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(obj);
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo != 0) {
            if (tagNo == 1) {
                aSN1OctetString = ASN1Null.getInstance(aSN1TaggedObject.getObject());
            } else if (tagNo != 2 && tagNo != 3) {
                if (tagNo != 4) {
                    throw new IllegalArgumentException("unknown tag " + aSN1TaggedObject.getTagNo());
                }
                aSN1OctetString = ASN1Sequence.getInstance(aSN1TaggedObject.getObject());
            }
            return new Builder().setChoice(aSN1TaggedObject.getTagNo()).setValue(aSN1OctetString).createEccP384CurvePoint();
        }
        aSN1OctetString = ASN1OctetString.getInstance(aSN1TaggedObject.getObject());
        return new Builder().setChoice(aSN1TaggedObject.getTagNo()).setValue(aSN1OctetString).createEccP384CurvePoint();
    }

    public int getChoice() {
        return this.choice;
    }

    @Override // org.bouncycastle.oer.its.EccCurvePoint
    public byte[] getEncodedPoint() {
        byte[] bArr;
        int i = this.choice;
        if (i == 0) {
            throw new IllegalStateException("x Only not implemented");
        }
        if (i == 2) {
            byte[] octets = DEROctetString.getInstance(this.value).getOctets();
            bArr = new byte[octets.length + 1];
            bArr[0] = 2;
            System.arraycopy(octets, 0, bArr, 1, octets.length);
        } else {
            if (i != 3) {
                if (i != 4) {
                    throw new IllegalStateException("unknown point choice");
                }
                ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(this.value);
                return Arrays.concatenate(new byte[]{4}, DEROctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets(), DEROctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets());
            }
            byte[] octets2 = DEROctetString.getInstance(this.value).getOctets();
            bArr = new byte[octets2.length + 1];
            bArr[0] = 3;
            System.arraycopy(octets2, 0, bArr, 1, octets2.length);
        }
        return bArr;
    }

    public ASN1Encodable getValue() {
        return this.value;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(this.choice, this.value);
    }
}

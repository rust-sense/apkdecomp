package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes3.dex */
public class DLBitString extends ASN1BitString {
    public DLBitString(byte b, int i) {
        super(b, i);
    }

    public DLBitString(int i) {
        super(getBytes(i), getPadBits(i));
    }

    public DLBitString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER), 0);
    }

    public DLBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DLBitString(byte[] bArr, int i) {
        super(bArr, i);
    }

    DLBitString(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    static void encode(ASN1OutputStream aSN1OutputStream, boolean z, byte b, byte[] bArr, int i, int i2) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 3, b, bArr, i, i2);
    }

    static void encode(ASN1OutputStream aSN1OutputStream, boolean z, byte[] bArr, int i, int i2) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 3, bArr, i, i2);
    }

    static int encodedLength(boolean z, int i) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, i);
    }

    static DLBitString fromOctetString(ASN1OctetString aSN1OctetString) {
        return new DLBitString(aSN1OctetString.getOctets(), true);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 3, this.contents);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    boolean encodeConstructed() {
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, this.contents.length);
    }

    @Override // org.bouncycastle.asn1.ASN1BitString, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDLObject() {
        return this;
    }
}

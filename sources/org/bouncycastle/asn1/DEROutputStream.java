package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
class DEROutputStream extends DLOutputStream {
    DEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // org.bouncycastle.asn1.ASN1OutputStream
    DEROutputStream getDERSubStream() {
        return this;
    }

    @Override // org.bouncycastle.asn1.DLOutputStream, org.bouncycastle.asn1.ASN1OutputStream
    void writeElements(ASN1Encodable[] aSN1EncodableArr) throws IOException {
        for (ASN1Encodable aSN1Encodable : aSN1EncodableArr) {
            aSN1Encodable.toASN1Primitive().toDERObject().encode(this, true);
        }
    }

    @Override // org.bouncycastle.asn1.DLOutputStream, org.bouncycastle.asn1.ASN1OutputStream
    void writePrimitive(ASN1Primitive aSN1Primitive, boolean z) throws IOException {
        aSN1Primitive.toDERObject().encode(this, z);
    }

    @Override // org.bouncycastle.asn1.DLOutputStream, org.bouncycastle.asn1.ASN1OutputStream
    void writePrimitives(ASN1Primitive[] aSN1PrimitiveArr) throws IOException {
        for (ASN1Primitive aSN1Primitive : aSN1PrimitiveArr) {
            aSN1Primitive.toDERObject().encode(this, true);
        }
    }
}

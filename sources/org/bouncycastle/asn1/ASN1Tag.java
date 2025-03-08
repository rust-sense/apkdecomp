package org.bouncycastle.asn1;

/* loaded from: classes3.dex */
final class ASN1Tag {
    private final int tagClass;
    private final int tagNumber;

    private ASN1Tag(int i, int i2) {
        this.tagClass = i;
        this.tagNumber = i2;
    }

    static ASN1Tag create(int i, int i2) {
        return new ASN1Tag(i, i2);
    }

    int getTagClass() {
        return this.tagClass;
    }

    int getTagNumber() {
        return this.tagNumber;
    }
}

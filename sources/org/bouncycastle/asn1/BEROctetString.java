package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/* loaded from: classes3.dex */
public class BEROctetString extends ASN1OctetString {
    private static final int DEFAULT_SEGMENT_LIMIT = 1000;
    private final ASN1OctetString[] elements;
    private final int segmentLimit;

    public BEROctetString(byte[] bArr) {
        this(bArr, 1000);
    }

    public BEROctetString(byte[] bArr, int i) {
        this(bArr, null, i);
    }

    private BEROctetString(byte[] bArr, ASN1OctetString[] aSN1OctetStringArr, int i) {
        super(bArr);
        this.elements = aSN1OctetStringArr;
        this.segmentLimit = i;
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr) {
        this(aSN1OctetStringArr, 1000);
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr, int i) {
        this(flattenOctetStrings(aSN1OctetStringArr), aSN1OctetStringArr, i);
    }

    static byte[] flattenOctetStrings(ASN1OctetString[] aSN1OctetStringArr) {
        int length = aSN1OctetStringArr.length;
        if (length == 0) {
            return EMPTY_OCTETS;
        }
        if (length == 1) {
            return aSN1OctetStringArr[0].string;
        }
        int i = 0;
        for (ASN1OctetString aSN1OctetString : aSN1OctetStringArr) {
            i += aSN1OctetString.string.length;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (ASN1OctetString aSN1OctetString2 : aSN1OctetStringArr) {
            byte[] bArr2 = aSN1OctetString2.string;
            System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
            i2 += bArr2.length;
        }
        return bArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        if (!encodeConstructed()) {
            DEROctetString.encode(aSN1OutputStream, z, this.string, 0, this.string.length);
            return;
        }
        aSN1OutputStream.writeIdentifier(z, 36);
        aSN1OutputStream.write(128);
        ASN1OctetString[] aSN1OctetStringArr = this.elements;
        if (aSN1OctetStringArr != null) {
            aSN1OutputStream.writePrimitives(aSN1OctetStringArr);
        } else {
            int i = 0;
            while (i < this.string.length) {
                int min = Math.min(this.string.length - i, this.segmentLimit);
                DEROctetString.encode(aSN1OutputStream, true, this.string, i, min);
                i += min;
            }
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    boolean encodeConstructed() {
        return this.elements != null || this.string.length > this.segmentLimit;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    int encodedLength(boolean z) throws IOException {
        if (!encodeConstructed()) {
            return DEROctetString.encodedLength(z, this.string.length);
        }
        int i = z ? 4 : 3;
        if (this.elements == null) {
            int length = this.string.length;
            int i2 = this.segmentLimit;
            int i3 = length / i2;
            int encodedLength = i + (DEROctetString.encodedLength(true, i2) * i3);
            int length2 = this.string.length - (i3 * this.segmentLimit);
            return length2 > 0 ? encodedLength + DEROctetString.encodedLength(true, length2) : encodedLength;
        }
        int i4 = 0;
        while (true) {
            ASN1OctetString[] aSN1OctetStringArr = this.elements;
            if (i4 >= aSN1OctetStringArr.length) {
                return i;
            }
            i += aSN1OctetStringArr[i4].encodedLength(true);
            i4++;
        }
    }

    public Enumeration getObjects() {
        return this.elements == null ? new Enumeration() { // from class: org.bouncycastle.asn1.BEROctetString.1
            int pos = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.pos < BEROctetString.this.string.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                if (this.pos >= BEROctetString.this.string.length) {
                    throw new NoSuchElementException();
                }
                int min = Math.min(BEROctetString.this.string.length - this.pos, BEROctetString.this.segmentLimit);
                byte[] bArr = new byte[min];
                System.arraycopy(BEROctetString.this.string, this.pos, bArr, 0, min);
                this.pos += min;
                return new DEROctetString(bArr);
            }
        } : new Enumeration() { // from class: org.bouncycastle.asn1.BEROctetString.2
            int counter = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.counter < BEROctetString.this.elements.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                if (this.counter >= BEROctetString.this.elements.length) {
                    throw new NoSuchElementException();
                }
                ASN1OctetString[] aSN1OctetStringArr = BEROctetString.this.elements;
                int i = this.counter;
                this.counter = i + 1;
                return aSN1OctetStringArr[i];
            }
        };
    }
}

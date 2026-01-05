package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public abstract class ASN1TaggedObject extends ASN1Primitive implements ASN1TaggedObjectParser {
    private static final int DECLARED_EXPLICIT = 1;
    private static final int DECLARED_IMPLICIT = 2;
    private static final int PARSED_EXPLICIT = 3;
    private static final int PARSED_IMPLICIT = 4;
    final int explicitness;
    final ASN1Encodable obj;
    final int tagClass;
    final int tagNo;

    ASN1TaggedObject(int i, int i2, int i3, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable == null) {
            throw new NullPointerException("'obj' cannot be null");
        }
        if (i2 == 0 || (i2 & 192) != i2) {
            throw new IllegalArgumentException("invalid tag class: " + i2);
        }
        this.explicitness = aSN1Encodable instanceof ASN1Choice ? 1 : i;
        this.tagClass = i2;
        this.tagNo = i3;
        this.obj = aSN1Encodable;
    }

    protected ASN1TaggedObject(boolean z, int i, int i2, ASN1Encodable aSN1Encodable) {
        this(z ? 1 : 2, i, i2, aSN1Encodable);
    }

    protected ASN1TaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        this(z, 128, i, aSN1Encodable);
    }

    private static ASN1TaggedObject checkedCast(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1TaggedObject) {
            return (ASN1TaggedObject) aSN1Primitive;
        }
        throw new IllegalStateException("unexpected object: " + aSN1Primitive.getClass().getName());
    }

    static ASN1Primitive createConstructedDL(int i, int i2, ASN1EncodableVector aSN1EncodableVector) {
        DLTaggedObject dLTaggedObject = aSN1EncodableVector.size() == 1 ? new DLTaggedObject(3, i, i2, aSN1EncodableVector.get(0)) : new DLTaggedObject(4, i, i2, DLFactory.createSequence(aSN1EncodableVector));
        return i != 64 ? dLTaggedObject : new DLApplicationSpecific(dLTaggedObject);
    }

    static ASN1Primitive createConstructedIL(int i, int i2, ASN1EncodableVector aSN1EncodableVector) {
        BERTaggedObject bERTaggedObject = aSN1EncodableVector.size() == 1 ? new BERTaggedObject(3, i, i2, aSN1EncodableVector.get(0)) : new BERTaggedObject(4, i, i2, BERFactory.createSequence(aSN1EncodableVector));
        return i != 64 ? bERTaggedObject : new BERApplicationSpecific(bERTaggedObject);
    }

    static ASN1Primitive createPrimitive(int i, int i2, byte[] bArr) {
        DLTaggedObject dLTaggedObject = new DLTaggedObject(4, i, i2, new DEROctetString(bArr));
        return i != 64 ? dLTaggedObject : new DLApplicationSpecific(dLTaggedObject);
    }

    public static ASN1TaggedObject getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1TaggedObject)) {
            return (ASN1TaggedObject) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                return (ASN1TaggedObject) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return checkedCast(fromByteArray((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1TaggedObject getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (128 != aSN1TaggedObject.getTagClass()) {
            throw new IllegalStateException("this method only valid for CONTEXT_SPECIFIC tags");
        }
        if (z) {
            return aSN1TaggedObject.getExplicitBaseTagged();
        }
        throw new IllegalArgumentException("this method not valid for implicitly tagged tagged objects");
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1ApplicationSpecific) {
            return aSN1Primitive.equals((ASN1Primitive) this);
        }
        if (!(aSN1Primitive instanceof ASN1TaggedObject)) {
            return false;
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
        if (this.tagNo != aSN1TaggedObject.tagNo || this.tagClass != aSN1TaggedObject.tagClass) {
            return false;
        }
        if (this.explicitness != aSN1TaggedObject.explicitness && isExplicit() != aSN1TaggedObject.isExplicit()) {
            return false;
        }
        ASN1Primitive aSN1Primitive2 = this.obj.toASN1Primitive();
        ASN1Primitive aSN1Primitive3 = aSN1TaggedObject.obj.toASN1Primitive();
        if (aSN1Primitive2 == aSN1Primitive3) {
            return true;
        }
        if (isExplicit()) {
            return aSN1Primitive2.asn1Equals(aSN1Primitive3);
        }
        try {
            return Arrays.areEqual(getEncoded(), aSN1TaggedObject.getEncoded());
        } catch (IOException unused) {
            return false;
        }
    }

    abstract String getASN1Encoding();

    public ASN1Object getBaseObject() {
        ASN1Encodable aSN1Encodable = this.obj;
        return aSN1Encodable instanceof ASN1Object ? (ASN1Object) aSN1Encodable : aSN1Encodable.toASN1Primitive();
    }

    public ASN1Primitive getBaseUniversal(boolean z, int i) {
        ASN1UniversalType aSN1UniversalType = ASN1UniversalTypes.get(i);
        if (aSN1UniversalType != null) {
            return getBaseUniversal(z, aSN1UniversalType);
        }
        throw new IllegalArgumentException("unsupported UNIVERSAL tag number: " + i);
    }

    ASN1Primitive getBaseUniversal(boolean z, ASN1UniversalType aSN1UniversalType) {
        if (z) {
            if (isExplicit()) {
                return aSN1UniversalType.checkedCast(this.obj.toASN1Primitive());
            }
            throw new IllegalStateException("object explicit - implicit expected.");
        }
        if (1 == this.explicitness) {
            throw new IllegalStateException("object explicit - implicit expected.");
        }
        ASN1Primitive aSN1Primitive = this.obj.toASN1Primitive();
        int i = this.explicitness;
        return i != 3 ? i != 4 ? aSN1UniversalType.checkedCast(aSN1Primitive) : aSN1Primitive instanceof ASN1Sequence ? aSN1UniversalType.fromImplicitConstructed((ASN1Sequence) aSN1Primitive) : aSN1UniversalType.fromImplicitPrimitive((DEROctetString) aSN1Primitive) : aSN1UniversalType.fromImplicitConstructed(rebuildConstructed(aSN1Primitive));
    }

    byte[] getContents() {
        try {
            byte[] encoded = this.obj.toASN1Primitive().getEncoded(getASN1Encoding());
            if (isExplicit()) {
                return encoded;
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encoded);
            ASN1InputStream.readTagNumber(byteArrayInputStream, byteArrayInputStream.read());
            int readLength = ASN1InputStream.readLength(byteArrayInputStream, byteArrayInputStream.available(), false);
            int available = byteArrayInputStream.available();
            int i = readLength < 0 ? available - 2 : available;
            if (i < 0) {
                throw new ASN1ParsingException("failed to get contents");
            }
            byte[] bArr = new byte[i];
            System.arraycopy(encoded, encoded.length - available, bArr, 0, i);
            return bArr;
        } catch (IOException e) {
            throw new ASN1ParsingException("failed to get contents", e);
        }
    }

    public ASN1Object getExplicitBaseObject() {
        if (!isExplicit()) {
            throw new IllegalStateException("object implicit - explicit expected.");
        }
        ASN1Encodable aSN1Encodable = this.obj;
        return aSN1Encodable instanceof ASN1Object ? (ASN1Object) aSN1Encodable : aSN1Encodable.toASN1Primitive();
    }

    public ASN1TaggedObject getExplicitBaseTagged() {
        if (isExplicit()) {
            return checkedCast(this.obj.toASN1Primitive());
        }
        throw new IllegalStateException("object implicit - explicit expected.");
    }

    public ASN1TaggedObject getImplicitBaseTagged(int i, int i2) {
        if (i == 0 || (i & 192) != i) {
            throw new IllegalArgumentException("invalid base tag class: " + i);
        }
        int i3 = this.explicitness;
        if (i3 != 1) {
            return i3 != 2 ? replaceTag(i, i2) : ASN1Util.checkTag(checkedCast(this.obj.toASN1Primitive()), i, i2);
        }
        throw new IllegalStateException("object explicit - implicit expected.");
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public final ASN1Primitive getLoadedObject() {
        return this;
    }

    public ASN1Primitive getObject() {
        if (128 == getTagClass()) {
            return this.obj.toASN1Primitive();
        }
        throw new IllegalStateException("this method only valid for CONTEXT_SPECIFIC tags");
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable getObjectParser(int i, boolean z) throws IOException {
        if (128 == getTagClass()) {
            return parseBaseUniversal(z, i);
        }
        throw new ASN1Exception("this method only valid for CONTEXT_SPECIFIC tags");
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagClass() {
        return this.tagClass;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this.tagNo;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public boolean hasContextTag(int i) {
        return this.tagClass == 128 && this.tagNo == i;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public boolean hasTag(int i, int i2) {
        return this.tagClass == i && this.tagNo == i2;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return (((this.tagClass * 7919) ^ this.tagNo) ^ (isExplicit() ? 15 : 240)) ^ this.obj.toASN1Primitive().hashCode();
    }

    boolean isConstructed() {
        return encodeConstructed();
    }

    public boolean isExplicit() {
        int i = this.explicitness;
        return i == 1 || i == 3;
    }

    boolean isParsed() {
        int i = this.explicitness;
        return i == 3 || i == 4;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable parseBaseUniversal(boolean z, int i) throws IOException {
        ASN1Primitive baseUniversal = getBaseUniversal(z, i);
        return i != 3 ? i != 4 ? i != 16 ? i != 17 ? baseUniversal : ((ASN1Set) baseUniversal).parser() : ((ASN1Sequence) baseUniversal).parser() : ((ASN1OctetString) baseUniversal).parser() : ((ASN1BitString) baseUniversal).parser();
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable parseExplicitBaseObject() throws IOException {
        return getExplicitBaseObject();
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1TaggedObjectParser parseExplicitBaseTagged() throws IOException {
        return getExplicitBaseTagged();
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1TaggedObjectParser parseImplicitBaseTagged(int i, int i2) throws IOException {
        return getImplicitBaseTagged(i, i2);
    }

    abstract ASN1Sequence rebuildConstructed(ASN1Primitive aSN1Primitive);

    abstract ASN1TaggedObject replaceTag(int i, int i2);

    @Override // org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDERObject() {
        return new DERTaggedObject(this.explicitness, this.tagClass, this.tagNo, this.obj);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDLObject() {
        return new DLTaggedObject(this.explicitness, this.tagClass, this.tagNo, this.obj);
    }

    public String toString() {
        return ASN1Util.getTagText(this.tagClass, this.tagNo) + this.obj;
    }
}

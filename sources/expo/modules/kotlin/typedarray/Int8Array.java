package expo.modules.kotlin.typedarray;

import com.facebook.react.uimanager.ViewProps;
import expo.modules.kotlin.jni.JavaScriptTypedArray;
import expo.modules.kotlin.jni.TypedArrayKind;
import expo.modules.kotlin.typedarray.GenericTypedArray;
import io.sentry.SentryEnvelopeItemHeader;
import java.nio.ByteBuffer;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConcreteTypedArrays.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\tH\u0096\u0002¢\u0006\u0002\u0010\u0018J!\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tH\u0096\u0001J\u0011\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\tH\u0096\u0001J\u0011\u0010!\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH\u0096\u0001J\u0011\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\tH\u0096\u0001J\u0011\u0010$\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\tH\u0096\u0001J\u0011\u0010%\u001a\u00020&2\u0006\u0010\u001d\u001a\u00020\tH\u0096\u0001J\u0011\u0010'\u001a\u00020(2\u0006\u0010\u001d\u001a\u00020\tH\u0096\u0001J\u0019\u0010)\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010*\u001a\u00020\u0003H\u0096\u0002J\t\u0010+\u001a\u00020,H\u0096\u0001J!\u0010-\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tH\u0096\u0001J\u0019\u0010.\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010*\u001a\u00020 H\u0096\u0001J\u0019\u0010/\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010*\u001a\u00020\tH\u0096\u0001J\u0019\u00100\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010*\u001a\u00020#H\u0096\u0001J\u0019\u00101\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010*\u001a\u00020\u0003H\u0096\u0001J\u0019\u00102\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010*\u001a\u00020&H\u0096\u0001J\u0019\u00103\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010*\u001a\u00020(H\u0096\u0001R\u0012\u0010\b\u001a\u00020\tX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\tX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0012\u0010\u000e\u001a\u00020\u000fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\tX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u00064"}, d2 = {"Lexpo/modules/kotlin/typedarray/Int8Array;", "Lexpo/modules/kotlin/typedarray/TypedArray;", "Lexpo/modules/kotlin/typedarray/GenericTypedArray;", "", "Lexpo/modules/kotlin/typedarray/RawTypedArrayHolder;", "rawArray", "Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "(Lexpo/modules/kotlin/jni/JavaScriptTypedArray;)V", "byteLength", "", "getByteLength", "()I", "byteOffset", "getByteOffset", "kind", "Lexpo/modules/kotlin/jni/TypedArrayKind;", "getKind", "()Lexpo/modules/kotlin/jni/TypedArrayKind;", SentryEnvelopeItemHeader.JsonKeys.LENGTH, "getLength", "getRawArray", "()Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "get", "index", "(I)Ljava/lang/Byte;", "read", "", "buffer", "", ViewProps.POSITION, "size", "read2Byte", "", "read4Byte", "read8Byte", "", "readByte", "readDouble", "", "readFloat", "", "set", "value", "toDirectBuffer", "Ljava/nio/ByteBuffer;", "write", "write2Byte", "write4Byte", "write8Byte", "writeByte", "writeDouble", "writeFloat", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Int8Array implements TypedArray, GenericTypedArray<Byte>, RawTypedArrayHolder {
    private final JavaScriptTypedArray rawArray;

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int getByteLength() {
        return this.rawArray.getByteLength();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int getByteOffset() {
        return this.rawArray.getByteOffset();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public TypedArrayKind getKind() {
        return this.rawArray.getKind();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int getLength() {
        return this.rawArray.getLength();
    }

    @Override // expo.modules.kotlin.typedarray.RawTypedArrayHolder
    public JavaScriptTypedArray getRawArray() {
        return this.rawArray;
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void read(byte[] buffer, int position, int size) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.rawArray.read(buffer, position, size);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public short read2Byte(int position) {
        return this.rawArray.read2Byte(position);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public int read4Byte(int position) {
        return this.rawArray.read4Byte(position);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public long read8Byte(int position) {
        return this.rawArray.read8Byte(position);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public byte readByte(int position) {
        return this.rawArray.readByte(position);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public double readDouble(int position) {
        return this.rawArray.readDouble(position);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public float readFloat(int position) {
        return this.rawArray.readFloat(position);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public ByteBuffer toDirectBuffer() {
        return this.rawArray.toDirectBuffer();
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void write(byte[] buffer, int position, int size) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.rawArray.write(buffer, position, size);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void write2Byte(int position, short value) {
        this.rawArray.write2Byte(position, value);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void write4Byte(int position, int value) {
        this.rawArray.write4Byte(position, value);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void write8Byte(int position, long value) {
        this.rawArray.write8Byte(position, value);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void writeByte(int position, byte value) {
        this.rawArray.writeByte(position, value);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void writeDouble(int position, double value) {
        this.rawArray.writeDouble(position, value);
    }

    @Override // expo.modules.kotlin.typedarray.TypedArray
    public void writeFloat(int position, float value) {
        this.rawArray.writeFloat(position, value);
    }

    public Int8Array(JavaScriptTypedArray rawArray) {
        Intrinsics.checkNotNullParameter(rawArray, "rawArray");
        this.rawArray = rawArray;
    }

    @Override // expo.modules.kotlin.typedarray.GenericTypedArray, java.lang.Iterable
    public Iterator<Byte> iterator() {
        return GenericTypedArray.DefaultImpls.iterator(this);
    }

    @Override // expo.modules.kotlin.typedarray.GenericTypedArray
    public /* bridge */ /* synthetic */ void set(int i, Byte b) {
        set(i, b.byteValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // expo.modules.kotlin.typedarray.GenericTypedArray
    public Byte get(int index) {
        Int8Array int8Array = this;
        if (index >= 0 && index < int8Array.getLength()) {
            return Byte.valueOf(readByte(index));
        }
        throw new IndexOutOfBoundsException();
    }

    public void set(int index, byte value) {
        Int8Array int8Array = this;
        if (index >= 0 && index < int8Array.getLength()) {
            writeByte(index, value);
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}

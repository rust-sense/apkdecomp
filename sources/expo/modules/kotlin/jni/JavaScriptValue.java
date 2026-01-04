package expo.modules.kotlin.jni;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.jni.HybridData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: JavaScriptValue.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0004J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\tH\u0086 ¢\u0006\u0002\u0010\nJ\t\u0010\u000b\u001a\u00020\fH\u0086 J\t\u0010\r\u001a\u00020\u000eH\u0086 J\u0006\u0010\u000f\u001a\u00020\u0010J\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0007¢\u0006\u0002\b\u0013J\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0012\"\u0006\b\u0000\u0010\u0014\u0018\u0001H\u0086\bJ\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\t\u0010\u0019\u001a\u00020\u001aH\u0086 J\t\u0010\u001b\u001a\u00020\u001cH\u0086 J\t\u0010\u001d\u001a\u00020\u001eH\u0086 J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H 0\u0012\"\u0004\b\u0000\u0010 H\u0001J\t\u0010!\u001a\u00020\fH\u0086 J\t\u0010\"\u001a\u00020\fH\u0086 J\t\u0010#\u001a\u00020\fH\u0086 J\t\u0010$\u001a\u00020\fH\u0086 J\t\u0010%\u001a\u00020\fH\u0086 J\t\u0010&\u001a\u00020\fH\u0086 J\t\u0010'\u001a\u00020\fH\u0086 J\t\u0010(\u001a\u00020\fH\u0086 J\t\u0010)\u001a\u00020\fH\u0086 J\t\u0010*\u001a\u00020\fH\u0086 J\u0006\u0010+\u001a\u00020\fJ\u0015\u0010,\u001a\b\u0012\u0004\u0012\u0002H 0\u0012\"\u0004\b\u0000\u0010 H\u0082 J\t\u0010-\u001a\u00020\u001cH\u0086 R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lexpo/modules/kotlin/jni/JavaScriptValue;", "Lexpo/modules/kotlin/jni/Destructible;", "mHybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "deallocate", "", "finalize", "getArray", "", "()[Lexpo/modules/kotlin/jni/JavaScriptValue;", "getBool", "", "getDouble", "", "getFloat", "", "getFunction", "Lexpo/modules/kotlin/jni/JavaScriptFunction;", "getVoidFunction", "ReturnType", "getInt", "", "getLong", "", "getObject", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "getString", "", "getTypedArray", "Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "internalJniGetFunction", ExifInterface.GPS_DIRECTION_TRUE, "isArray", "isBool", "isFunction", "isNull", "isNumber", "isObject", "isString", "isSymbol", "isTypedArray", "isUndefined", "isValid", "jniGetFunction", "kind", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JavaScriptValue implements Destructible {
    private final HybridData mHybridData;

    private final native <T> JavaScriptFunction<T> jniGetFunction();

    public final native JavaScriptValue[] getArray();

    public final native boolean getBool();

    public final native double getDouble();

    public final native JavaScriptObject getObject();

    public final native String getString();

    public final native JavaScriptTypedArray getTypedArray();

    public final native boolean isArray();

    public final native boolean isBool();

    public final native boolean isFunction();

    public final native boolean isNull();

    public final native boolean isNumber();

    public final native boolean isObject();

    public final native boolean isString();

    public final native boolean isSymbol();

    public final native boolean isTypedArray();

    public final native boolean isUndefined();

    public final native String kind();

    private JavaScriptValue(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public final boolean isValid() {
        return this.mHybridData.isValid();
    }

    public final <T> JavaScriptFunction<T> internalJniGetFunction() {
        return jniGetFunction();
    }

    public final /* synthetic */ <ReturnType> JavaScriptFunction<ReturnType> getFunction() {
        JavaScriptFunction<ReturnType> internalJniGetFunction = internalJniGetFunction();
        Intrinsics.reifiedOperationMarker(6, "ReturnType");
        internalJniGetFunction.setReturnType(null);
        return internalJniGetFunction;
    }

    public final JavaScriptFunction<Unit> getVoidFunction() {
        JavaScriptFunction<Unit> internalJniGetFunction = internalJniGetFunction();
        internalJniGetFunction.setReturnType(Reflection.typeOf(Unit.class));
        return internalJniGetFunction;
    }

    public final int getInt() {
        return (int) getDouble();
    }

    public final long getLong() {
        return (long) getDouble();
    }

    public final float getFloat() {
        return (float) getDouble();
    }

    protected final void finalize() throws Throwable {
        deallocate();
    }

    @Override // expo.modules.kotlin.jni.Destructible
    public void deallocate() {
        this.mHybridData.resetNative();
    }
}

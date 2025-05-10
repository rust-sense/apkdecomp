package expo.modules.kotlin.jni;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import expo.modules.kotlin.CoreLoggerKt;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.sharedobjects.SharedRef;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JavaCallback.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0017\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u0082\bJ\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0004J\u0013\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0086\u0002J\u0011\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000eH\u0086\u0002J\u0011\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000fH\u0086\u0002J\u0011\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0010H\u0086\u0002J\u0011\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0011H\u0086\u0002J\u0011\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0012H\u0086\u0002J\u0019\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0086\u0002J\t\u0010\u0015\u001a\u00020\u0006H\u0082 J\u0011\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0016H\u0082 J\u0011\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0017H\u0082 J\u0015\u0010\u0015\u001a\u00020\u00062\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0082 J\u0011\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000eH\u0082 J\u0011\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000fH\u0082 J\u0011\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0010H\u0082 J\u0011\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0011H\u0082 J\u0011\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0012H\u0082 J\u0019\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0082 R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lexpo/modules/kotlin/jni/JavaCallback;", "Lexpo/modules/kotlin/jni/Destructible;", "mHybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "checkIfValid", "", "body", "Lkotlin/Function0;", "deallocate", "finalize", "invoke", "result", "", "", "", "", "", "", "code", "errorMessage", "invokeNative", "Lcom/facebook/react/bridge/WritableNativeArray;", "Lcom/facebook/react/bridge/WritableNativeMap;", "Lexpo/modules/kotlin/sharedobjects/SharedRef;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JavaCallback implements Destructible {
    private final HybridData mHybridData;

    private final native void invokeNative();

    private final native void invokeNative(double result);

    private final native void invokeNative(float result);

    private final native void invokeNative(int result);

    private final native void invokeNative(WritableNativeArray result);

    private final native void invokeNative(WritableNativeMap result);

    private final native void invokeNative(SharedRef<?> result);

    private final native void invokeNative(String result);

    private final native void invokeNative(String code, String errorMessage);

    private final native void invokeNative(boolean result);

    public JavaCallback(HybridData mHybridData) {
        Intrinsics.checkNotNullParameter(mHybridData, "mHybridData");
        this.mHybridData = mHybridData;
    }

    public final void invoke(Object result) {
        try {
            if (result == null) {
                invokeNative();
                return;
            }
            if (result instanceof Integer) {
                invokeNative(((Number) result).intValue());
                return;
            }
            if (result instanceof Boolean) {
                invokeNative(((Boolean) result).booleanValue());
                return;
            }
            if (result instanceof Double) {
                invokeNative(((Number) result).doubleValue());
                return;
            }
            if (result instanceof Float) {
                invokeNative(((Number) result).floatValue());
                return;
            }
            if (result instanceof String) {
                invokeNative((String) result);
                return;
            }
            if (result instanceof WritableNativeArray) {
                invokeNative((WritableNativeArray) result);
                return;
            }
            if (result instanceof WritableNativeMap) {
                invokeNative((WritableNativeMap) result);
            } else {
                if (result instanceof SharedRef) {
                    invokeNative((SharedRef<?>) result);
                    return;
                }
                throw new UnexpectedException("Unknown type: " + result.getClass());
            }
        } catch (Throwable th) {
            if (!this.mHybridData.isValid()) {
                CoreLoggerKt.getLogger().error("Invalidated JavaCallback was invoked", th);
                return;
            }
            throw th;
        }
    }

    public final void invoke(int result) {
        try {
            invokeNative(result);
        } catch (Throwable th) {
            if (!this.mHybridData.isValid()) {
                CoreLoggerKt.getLogger().error("Invalidated JavaCallback was invoked", th);
                return;
            }
            throw th;
        }
    }

    public final void invoke(boolean result) {
        try {
            invokeNative(result);
        } catch (Throwable th) {
            if (!this.mHybridData.isValid()) {
                CoreLoggerKt.getLogger().error("Invalidated JavaCallback was invoked", th);
                return;
            }
            throw th;
        }
    }

    public final void invoke(double result) {
        try {
            invokeNative(result);
        } catch (Throwable th) {
            if (!this.mHybridData.isValid()) {
                CoreLoggerKt.getLogger().error("Invalidated JavaCallback was invoked", th);
                return;
            }
            throw th;
        }
    }

    public final void invoke(float result) {
        try {
            invokeNative(result);
        } catch (Throwable th) {
            if (!this.mHybridData.isValid()) {
                CoreLoggerKt.getLogger().error("Invalidated JavaCallback was invoked", th);
                return;
            }
            throw th;
        }
    }

    public final void invoke(String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            invokeNative(result);
        } catch (Throwable th) {
            if (!this.mHybridData.isValid()) {
                CoreLoggerKt.getLogger().error("Invalidated JavaCallback was invoked", th);
                return;
            }
            throw th;
        }
    }

    public final void invoke(String code, String errorMessage) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        try {
            invokeNative(code, errorMessage);
        } catch (Throwable th) {
            if (!this.mHybridData.isValid()) {
                CoreLoggerKt.getLogger().error("Invalidated JavaCallback was invoked", th);
                return;
            }
            throw th;
        }
    }

    private final void checkIfValid(Function0<Unit> body) {
        try {
            body.invoke();
        } catch (Throwable th) {
            if (!this.mHybridData.isValid()) {
                CoreLoggerKt.getLogger().error("Invalidated JavaCallback was invoked", th);
                return;
            }
            throw th;
        }
    }

    protected final void finalize() throws Throwable {
        deallocate();
    }

    @Override // expo.modules.kotlin.jni.Destructible
    public void deallocate() {
        this.mHybridData.resetNative();
    }
}

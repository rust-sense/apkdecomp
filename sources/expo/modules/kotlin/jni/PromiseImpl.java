package expo.modules.kotlin.jni;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.PromiseAlreadySettledException;
import expo.modules.kotlin.types.JSTypeConverter;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PromiseImpl.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0017\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014H\u0082\bJ$\u0010\u0015\u001a\u00020\u00122\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bJ$\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\rH\u0016J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\"H\u0016J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010 \u001a\u00020#H\u0016J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u000bH\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006$"}, d2 = {"Lexpo/modules/kotlin/jni/PromiseImpl;", "Lexpo/modules/kotlin/Promise;", "callback", "Lexpo/modules/kotlin/jni/JavaCallback;", "(Lexpo/modules/kotlin/jni/JavaCallback;)V", "appContextHolder", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "getCallback$expo_modules_core_release", "()Lexpo/modules/kotlin/jni/JavaCallback;", "fullFunctionName", "", "<set-?>", "", "wasSettled", "getWasSettled$expo_modules_core_release", "()Z", "checkIfWasSettled", "", "body", "Lkotlin/Function0;", "decorateWithDebugInformation", "moduleName", "functionName", "reject", "code", "message", "cause", "", "resolve", "value", "", "result", "", "", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PromiseImpl implements Promise {
    private WeakReference<AppContext> appContextHolder;
    private final JavaCallback callback;
    private String fullFunctionName;
    private boolean wasSettled;

    /* renamed from: getCallback$expo_modules_core_release, reason: from getter */
    public final JavaCallback getCallback() {
        return this.callback;
    }

    /* renamed from: getWasSettled$expo_modules_core_release, reason: from getter */
    public final boolean getWasSettled() {
        return this.wasSettled;
    }

    public PromiseImpl(JavaCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    @Override // expo.modules.kotlin.Promise
    public void reject(CodedException codedException) {
        Promise.DefaultImpls.reject(this, codedException);
    }

    @Override // expo.modules.kotlin.Promise
    public void resolve() {
        Promise.DefaultImpls.resolve(this);
    }

    private final void checkIfWasSettled(Function0<Unit> body) {
        if (this.wasSettled) {
            String str = this.fullFunctionName;
            if (str == null) {
                str = "unknown";
            }
            PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str);
            WeakReference<AppContext> weakReference = this.appContextHolder;
            if (weakReference != null) {
                AppContext appContext = weakReference.get();
                if (appContext != null) {
                    appContext.getErrorManager();
                    throw promiseAlreadySettledException;
                }
                throw promiseAlreadySettledException;
            }
            throw promiseAlreadySettledException;
        }
        body.invoke();
        this.wasSettled = true;
    }

    public final void decorateWithDebugInformation(WeakReference<AppContext> appContextHolder, String moduleName, String functionName) {
        Intrinsics.checkNotNullParameter(appContextHolder, "appContextHolder");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(functionName, "functionName");
        this.appContextHolder = appContextHolder;
        this.fullFunctionName = moduleName + "." + functionName;
    }

    @Override // expo.modules.kotlin.Promise
    public void resolve(Object value) {
        if (!this.wasSettled) {
            this.callback.invoke(JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, value, null, 2, null));
            this.wasSettled = true;
            return;
        }
        String str = this.fullFunctionName;
        if (str == null) {
            str = "unknown";
        }
        PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str);
        WeakReference<AppContext> weakReference = this.appContextHolder;
        if (weakReference != null) {
            AppContext appContext = weakReference.get();
            if (appContext != null) {
                appContext.getErrorManager();
                throw promiseAlreadySettledException;
            }
            throw promiseAlreadySettledException;
        }
        throw promiseAlreadySettledException;
    }

    @Override // expo.modules.kotlin.Promise
    public void resolve(int result) {
        if (!this.wasSettled) {
            this.callback.invoke(result);
            this.wasSettled = true;
            return;
        }
        String str = this.fullFunctionName;
        if (str == null) {
            str = "unknown";
        }
        PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str);
        WeakReference<AppContext> weakReference = this.appContextHolder;
        if (weakReference != null) {
            AppContext appContext = weakReference.get();
            if (appContext != null) {
                appContext.getErrorManager();
                throw promiseAlreadySettledException;
            }
            throw promiseAlreadySettledException;
        }
        throw promiseAlreadySettledException;
    }

    @Override // expo.modules.kotlin.Promise
    public void resolve(boolean result) {
        if (!this.wasSettled) {
            this.callback.invoke(result);
            this.wasSettled = true;
            return;
        }
        String str = this.fullFunctionName;
        if (str == null) {
            str = "unknown";
        }
        PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str);
        WeakReference<AppContext> weakReference = this.appContextHolder;
        if (weakReference != null) {
            AppContext appContext = weakReference.get();
            if (appContext != null) {
                appContext.getErrorManager();
                throw promiseAlreadySettledException;
            }
            throw promiseAlreadySettledException;
        }
        throw promiseAlreadySettledException;
    }

    @Override // expo.modules.kotlin.Promise
    public void resolve(double result) {
        if (!this.wasSettled) {
            this.callback.invoke(result);
            this.wasSettled = true;
            return;
        }
        String str = this.fullFunctionName;
        if (str == null) {
            str = "unknown";
        }
        PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str);
        WeakReference<AppContext> weakReference = this.appContextHolder;
        if (weakReference != null) {
            AppContext appContext = weakReference.get();
            if (appContext != null) {
                appContext.getErrorManager();
                throw promiseAlreadySettledException;
            }
            throw promiseAlreadySettledException;
        }
        throw promiseAlreadySettledException;
    }

    @Override // expo.modules.kotlin.Promise
    public void resolve(float result) {
        if (!this.wasSettled) {
            this.callback.invoke(result);
            this.wasSettled = true;
            return;
        }
        String str = this.fullFunctionName;
        if (str == null) {
            str = "unknown";
        }
        PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str);
        WeakReference<AppContext> weakReference = this.appContextHolder;
        if (weakReference != null) {
            AppContext appContext = weakReference.get();
            if (appContext != null) {
                appContext.getErrorManager();
                throw promiseAlreadySettledException;
            }
            throw promiseAlreadySettledException;
        }
        throw promiseAlreadySettledException;
    }

    @Override // expo.modules.kotlin.Promise
    public void resolve(String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (!this.wasSettled) {
            this.callback.invoke(result);
            this.wasSettled = true;
            return;
        }
        String str = this.fullFunctionName;
        if (str == null) {
            str = "unknown";
        }
        PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str);
        WeakReference<AppContext> weakReference = this.appContextHolder;
        if (weakReference != null) {
            AppContext appContext = weakReference.get();
            if (appContext != null) {
                appContext.getErrorManager();
                throw promiseAlreadySettledException;
            }
            throw promiseAlreadySettledException;
        }
        throw promiseAlreadySettledException;
    }

    @Override // expo.modules.kotlin.Promise
    public void reject(String code, String message, Throwable cause) {
        Intrinsics.checkNotNullParameter(code, "code");
        if (!this.wasSettled) {
            JavaCallback javaCallback = this.callback;
            if (message == null) {
                message = cause != null ? cause.getMessage() : null;
                if (message == null) {
                    message = "unknown";
                }
            }
            javaCallback.invoke(code, message);
            this.wasSettled = true;
            return;
        }
        String str = this.fullFunctionName;
        PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str != null ? str : "unknown");
        WeakReference<AppContext> weakReference = this.appContextHolder;
        if (weakReference != null) {
            AppContext appContext = weakReference.get();
            if (appContext != null) {
                appContext.getErrorManager();
                throw promiseAlreadySettledException;
            }
            throw promiseAlreadySettledException;
        }
        throw promiseAlreadySettledException;
    }
}

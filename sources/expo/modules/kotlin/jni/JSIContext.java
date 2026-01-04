package expo.modules.kotlin.jni;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.soloader.SoLoader;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.ModuleRegistry;
import expo.modules.kotlin.UtilsKt;
import expo.modules.kotlin.defaultmodules.CoreModule;
import expo.modules.kotlin.exception.JavaScriptEvaluateException;
import expo.modules.kotlin.sharedobjects.ClassRegistry;
import expo.modules.kotlin.sharedobjects.SharedObject;
import expo.modules.kotlin.sharedobjects.SharedObjectId;
import expo.modules.kotlin.sharedobjects.SharedObjectRegistry;
import io.sentry.protocol.SentryStackFrame;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JSIContext.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\f\u001a\u00020\rH\u0086 J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\t\u0010\u0013\u001a\u00020\u000fH\u0086 J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086 J\b\u0010\u0018\u001a\u00020\u000fH\u0004J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0007J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u0017H\u0007J\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u001eH\u0007¢\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u0004\u0018\u00010\r2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\"H\u0007J\t\u0010#\u001a\u00020\rH\u0086 J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u0017H\u0007J\t\u0010&\u001a\u00020\u000bH\u0082 J&\u0010'\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J!\u0010'\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0082 J&\u0010/\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u00100\u001a\u000201J!\u0010/\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u00100\u001a\u000201H\u0082 J\u000e\u00102\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u0005J\u0016\u00102\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,J\u0011\u00102\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020,H\u0082 J\u001c\u00103\u001a\u00020\u000f2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\"2\u0006\u00104\u001a\u00020\rH\u0007J\u0018\u00105\u001a\u00020\u000f2\u0006\u0010!\u001a\u0002062\u0006\u00104\u001a\u00020\rH\u0007J\u0019\u00107\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u00104\u001a\u00020\rH\u0086 R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lexpo/modules/kotlin/jni/JSIContext;", "Lexpo/modules/kotlin/jni/Destructible;", "()V", "appContextHolder", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "getAppContextHolder$expo_modules_core_release", "()Ljava/lang/ref/WeakReference;", "setAppContextHolder$expo_modules_core_release", "(Ljava/lang/ref/WeakReference;)V", "mHybridData", "Lcom/facebook/jni/HybridData;", "createObject", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "deallocate", "", "deleteSharedObject", "id", "", "drainJSEventLoop", "evaluateScript", "Lexpo/modules/kotlin/jni/JavaScriptValue;", "script", "", "finalize", "getCoreModuleObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "getJavaScriptModuleObject", "name", "getJavaScriptModulesName", "", "()[Ljava/lang/String;", "getJavascriptClass", SentryStackFrame.JsonKeys.NATIVE, "Ljava/lang/Class;", "global", "hasModule", "", "initHybrid", "installJSI", "appContext", "jsRuntimePointer", "", "jniDeallocator", "Lexpo/modules/kotlin/jni/JNIDeallocator;", "jsInvokerHolder", "Lcom/facebook/react/turbomodule/core/CallInvokerHolderImpl;", "installJSIForBridgeless", "runtimeExecutor", "Lcom/facebook/react/bridge/RuntimeExecutor;", "installJSIForTests", "registerClass", "js", "registerSharedObject", "", "setNativeStateForSharedObject", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JSIContext implements Destructible {
    public WeakReference<AppContext> appContextHolder;
    private final HybridData mHybridData = initHybrid();

    private final native HybridData initHybrid();

    private final native void installJSI(long jsRuntimePointer, JNIDeallocator jniDeallocator, CallInvokerHolderImpl jsInvokerHolder);

    private final native void installJSIForBridgeless(long jsRuntimePointer, JNIDeallocator jniDeallocator, RuntimeExecutor runtimeExecutor);

    private final native void installJSIForTests(JNIDeallocator jniDeallocator);

    public final native JavaScriptObject createObject();

    public final native void drainJSEventLoop();

    public final native JavaScriptValue evaluateScript(String script) throws JavaScriptEvaluateException;

    public final native JavaScriptObject global();

    public final void setAppContextHolder$expo_modules_core_release(WeakReference<AppContext> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
        this.appContextHolder = weakReference;
    }

    public final native void setNativeStateForSharedObject(int id, JavaScriptObject js);

    public final WeakReference<AppContext> getAppContextHolder$expo_modules_core_release() {
        WeakReference<AppContext> weakReference = this.appContextHolder;
        if (weakReference != null) {
            return weakReference;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appContextHolder");
        return null;
    }

    public final void installJSI(AppContext appContext, long jsRuntimePointer, JNIDeallocator jniDeallocator, CallInvokerHolderImpl jsInvokerHolder) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(jniDeallocator, "jniDeallocator");
        Intrinsics.checkNotNullParameter(jsInvokerHolder, "jsInvokerHolder");
        setAppContextHolder$expo_modules_core_release(UtilsKt.weak(appContext));
        installJSI(jsRuntimePointer, jniDeallocator, jsInvokerHolder);
    }

    public final void installJSIForBridgeless(AppContext appContext, long jsRuntimePointer, JNIDeallocator jniDeallocator, RuntimeExecutor runtimeExecutor) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(jniDeallocator, "jniDeallocator");
        Intrinsics.checkNotNullParameter(runtimeExecutor, "runtimeExecutor");
        setAppContextHolder$expo_modules_core_release(UtilsKt.weak(appContext));
        installJSIForBridgeless(jsRuntimePointer, jniDeallocator, runtimeExecutor);
    }

    public final void installJSIForTests(AppContext appContext, JNIDeallocator jniDeallocator) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(jniDeallocator, "jniDeallocator");
        setAppContextHolder$expo_modules_core_release(UtilsKt.weak(appContext));
        installJSIForTests(jniDeallocator);
    }

    public final void installJSIForTests(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        setAppContextHolder$expo_modules_core_release(UtilsKt.weak(appContext));
        installJSIForTests(appContext.getJniDeallocator());
    }

    public final JavaScriptModuleObject getJavaScriptModuleObject(String name) {
        ModuleRegistry registry;
        ModuleHolder<?> moduleHolder;
        Intrinsics.checkNotNullParameter(name, "name");
        AppContext appContext = getAppContextHolder$expo_modules_core_release().get();
        if (appContext == null || (registry = appContext.getRegistry()) == null || (moduleHolder = registry.getModuleHolder(name)) == null) {
            return null;
        }
        return moduleHolder.getJsObject();
    }

    public final boolean hasModule(String name) {
        ModuleRegistry registry;
        Intrinsics.checkNotNullParameter(name, "name");
        AppContext appContext = getAppContextHolder$expo_modules_core_release().get();
        if (appContext == null || (registry = appContext.getRegistry()) == null) {
            return false;
        }
        return registry.hasModule(name);
    }

    public final String[] getJavaScriptModulesName() {
        ModuleRegistry registry;
        Map<String, ModuleHolder<?>> registry2;
        Set<String> keySet;
        String[] strArr;
        AppContext appContext = getAppContextHolder$expo_modules_core_release().get();
        return (appContext == null || (registry = appContext.getRegistry()) == null || (registry2 = registry.getRegistry()) == null || (keySet = registry2.keySet()) == null || (strArr = (String[]) keySet.toArray(new String[0])) == null) ? new String[0] : strArr;
    }

    public final void registerSharedObject(Object r2, JavaScriptObject js) {
        SharedObjectRegistry sharedObjectRegistry;
        Intrinsics.checkNotNullParameter(r2, "native");
        Intrinsics.checkNotNullParameter(js, "js");
        AppContext appContext = getAppContextHolder$expo_modules_core_release().get();
        if (appContext == null || (sharedObjectRegistry = appContext.getSharedObjectRegistry()) == null) {
            return;
        }
        SharedObjectId.m694boximpl(sharedObjectRegistry.m704add5WKnsLU$expo_modules_core_release((SharedObject) r2, js));
    }

    public final void deleteSharedObject(int id) {
        SharedObjectRegistry sharedObjectRegistry;
        AppContext appContext = getAppContextHolder$expo_modules_core_release().get();
        if (appContext == null || (sharedObjectRegistry = appContext.getSharedObjectRegistry()) == null) {
            return;
        }
        sharedObjectRegistry.m705deletekyJHjyY$expo_modules_core_release(SharedObjectId.m695constructorimpl(id));
    }

    public final void registerClass(Class<?> r2, JavaScriptObject js) {
        ClassRegistry classRegistry;
        Intrinsics.checkNotNullParameter(r2, "native");
        Intrinsics.checkNotNullParameter(js, "js");
        AppContext appContext = getAppContextHolder$expo_modules_core_release().get();
        if (appContext == null || (classRegistry = appContext.getClassRegistry()) == null) {
            return;
        }
        classRegistry.add$expo_modules_core_release(r2, js);
    }

    public final JavaScriptObject getJavascriptClass(Class<?> r2) {
        ClassRegistry classRegistry;
        Intrinsics.checkNotNullParameter(r2, "native");
        AppContext appContext = getAppContextHolder$expo_modules_core_release().get();
        if (appContext == null || (classRegistry = appContext.getClassRegistry()) == null) {
            return null;
        }
        return classRegistry.toJavaScriptObject$expo_modules_core_release(r2);
    }

    public final JavaScriptModuleObject getCoreModuleObject() {
        ModuleHolder<CoreModule> coreModule$expo_modules_core_release;
        AppContext appContext = getAppContextHolder$expo_modules_core_release().get();
        if (appContext == null || (coreModule$expo_modules_core_release = appContext.getCoreModule$expo_modules_core_release()) == null) {
            return null;
        }
        return coreModule$expo_modules_core_release.getJsObject();
    }

    protected final void finalize() throws Throwable {
        deallocate();
    }

    @Override // expo.modules.kotlin.jni.Destructible
    public void deallocate() {
        this.mHybridData.resetNative();
    }

    static {
        SoLoader.loadLibrary("expo-modules-core");
    }
}

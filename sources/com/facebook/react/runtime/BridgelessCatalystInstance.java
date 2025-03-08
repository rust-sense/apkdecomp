package com.facebook.react.runtime;

import android.content.res.AssetManager;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JSIModule;
import com.facebook.react.bridge.JSIModuleSpec;
import com.facebook.react.bridge.JSIModuleType;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeArrayInterface;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.internal.turbomodule.core.interfaces.TurboModuleRegistry;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.turbomodule.core.interfaces.NativeMethodCallInvokerHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.SentryEvent;
import io.sentry.protocol.Request;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BridgelessCatalystInstance.kt */
@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J&\u0010\t\u001a\u00020\u0006\"\b\b\u0000\u0010\n*\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u000e0\rH\u0017J \u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0006H\u0016J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0017J%\u0010 \u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\n0#H\u0016¢\u0006\u0002\u0010$J\n\u0010%\u001a\u0004\u0018\u00010&H\u0017J\b\u0010'\u001a\u00020(H\u0016J'\u0010)\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H\n0#H\u0016¢\u0006\u0002\u0010,J\u0012\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020\u0011H\u0016J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020*0/H\u0016J\b\u00100\u001a\u000201H\u0016J\n\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u000205H\u0016J\n\u00106\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u000209H\u0016J \u0010:\u001a\u00020;\"\b\b\u0000\u0010\n*\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H\n0#H\u0016J\b\u0010<\u001a\u00020;H\u0016J\b\u0010=\u001a\u00020\u0006H\u0017J\u0018\u0010>\u001a\u00020\u00062\u0006\u0010?\u001a\u0002092\u0006\u0010\u0013\u001a\u00020@H\u0017J\b\u0010A\u001a\u00020;H\u0016J \u0010B\u001a\u00020\u00062\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u00112\u0006\u0010F\u001a\u00020;H\u0016J \u0010G\u001a\u00020\u00062\u0006\u0010H\u001a\u00020\u00112\u0006\u0010I\u001a\u00020\u00112\u0006\u0010F\u001a\u00020;H\u0016J\u0018\u0010J\u001a\u00020\u00062\u0006\u0010H\u001a\u00020\u00112\u0006\u0010I\u001a\u00020\u0011H\u0016J\u0018\u0010K\u001a\u00020\u00062\u0006\u0010L\u001a\u0002092\u0006\u0010M\u001a\u00020\u0011H\u0016J\u0010\u0010N\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010O\u001a\u00020\u0006H\u0016J\u0010\u0010P\u001a\u00020\u00062\u0006\u0010Q\u001a\u00020\u001aH\u0016J\u0018\u0010R\u001a\u00020\u00062\u0006\u0010S\u001a\u00020\u00112\u0006\u0010T\u001a\u00020\u0011H\u0017J\u0018\u0010U\u001a\u00020\u00062\u0006\u0010V\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\u0011H\u0016J\u0010\u0010X\u001a\u00020\u00062\u0006\u0010Y\u001a\u00020\u000bH\u0017J\u0010\u0010Z\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\\H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006]"}, d2 = {"Lcom/facebook/react/runtime/BridgelessCatalystInstance;", "Lcom/facebook/react/bridge/CatalystInstance;", "reactHost", "Lcom/facebook/react/runtime/ReactHostImpl;", "(Lcom/facebook/react/runtime/ReactHostImpl;)V", "addBridgeIdleDebugListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;", "addJSIModules", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/facebook/react/bridge/JSIModule;", "jsiModules", "", "Lcom/facebook/react/bridge/JSIModuleSpec;", "callFunction", "module", "", Request.JsonKeys.METHOD, "arguments", "Lcom/facebook/react/bridge/NativeArray;", "destroy", "extendNativeModules", SentryEvent.JsonKeys.MODULES, "Lcom/facebook/react/bridge/NativeModuleRegistry;", "getFabricUIManager", "Lcom/facebook/react/bridge/UIManager;", "getJSCallInvokerHolder", "Lcom/facebook/react/turbomodule/core/interfaces/CallInvokerHolder;", "getJSIModule", "moduleType", "Lcom/facebook/react/bridge/JSIModuleType;", "getJSModule", "Lcom/facebook/react/bridge/JavaScriptModule;", "jsInterface", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lcom/facebook/react/bridge/JavaScriptModule;", "getJavaScriptContextHolder", "Lcom/facebook/react/bridge/JavaScriptContextHolder;", "getNativeMethodCallInvokerHolder", "Lcom/facebook/react/turbomodule/core/interfaces/NativeMethodCallInvokerHolder;", "getNativeModule", "Lcom/facebook/react/bridge/NativeModule;", "nativeModuleInterface", "(Ljava/lang/Class;)Lcom/facebook/react/bridge/NativeModule;", "moduleName", "getNativeModules", "", "getReactQueueConfiguration", "Lcom/facebook/react/bridge/queue/ReactQueueConfiguration;", "getRuntimeExecutor", "Lcom/facebook/react/bridge/RuntimeExecutor;", "getRuntimeScheduler", "Lcom/facebook/react/bridge/RuntimeScheduler;", "getSourceURL", "handleMemoryPressure", "level", "", "hasNativeModule", "", "hasRunJSBundle", "initialize", "invokeCallback", "callbackID", "Lcom/facebook/react/bridge/NativeArrayInterface;", "isDestroyed", "loadScriptFromAssets", "assetManager", "Landroid/content/res/AssetManager;", "assetURL", "loadSynchronously", "loadScriptFromFile", "fileName", "sourceURL", "loadSplitBundleFromFile", "registerSegment", "segmentId", "path", "removeBridgeIdleDebugListener", "runJSBundle", "setFabricUIManager", "fabricUIManager", "setGlobalVariable", "propName", "jsonValue", "setSourceURLs", "deviceURL", "remoteURL", "setTurboModuleManager", "getter", "setTurboModuleRegistry", "turboModuleRegistry", "Lcom/facebook/react/internal/turbomodule/core/interfaces/TurboModuleRegistry;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BridgelessCatalystInstance implements CatalystInstance {
    private final ReactHostImpl reactHost;

    public BridgelessCatalystInstance(ReactHostImpl reactHost) {
        Intrinsics.checkNotNullParameter(reactHost, "reactHost");
        this.reactHost = reactHost;
    }

    @Override // com.facebook.react.bridge.MemoryPressureListener
    public void handleMemoryPressure(int level) {
        throw new UnsupportedOperationException("Unimplemented method 'handleMemoryPressure'");
    }

    @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
    public void loadScriptFromAssets(AssetManager assetManager, String assetURL, boolean loadSynchronously) {
        Intrinsics.checkNotNullParameter(assetManager, "assetManager");
        Intrinsics.checkNotNullParameter(assetURL, "assetURL");
        throw new UnsupportedOperationException("Unimplemented method 'loadScriptFromAssets'");
    }

    @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
    public void loadScriptFromFile(String fileName, String sourceURL, boolean loadSynchronously) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(sourceURL, "sourceURL");
        throw new UnsupportedOperationException("Unimplemented method 'loadScriptFromFile'");
    }

    @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
    public void loadSplitBundleFromFile(String fileName, String sourceURL) {
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(sourceURL, "sourceURL");
        throw new UnsupportedOperationException("Unimplemented method 'loadSplitBundleFromFile'");
    }

    @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
    public void setSourceURLs(String deviceURL, String remoteURL) {
        Intrinsics.checkNotNullParameter(deviceURL, "deviceURL");
        Intrinsics.checkNotNullParameter(remoteURL, "remoteURL");
        throw new UnsupportedOperationException("Unimplemented method 'setSourceURLs'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void runJSBundle() {
        throw new UnsupportedOperationException("Unimplemented method 'runJSBundle'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public boolean hasRunJSBundle() {
        throw new UnsupportedOperationException("Unimplemented method 'hasRunJSBundle'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public String getSourceURL() {
        throw new UnsupportedOperationException("Unimplemented method 'getSourceURL'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance, com.facebook.react.bridge.JSInstance
    public void invokeCallback(int callbackID, NativeArrayInterface arguments) {
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        throw new UnsupportedOperationException("Unimplemented method 'invokeCallback'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void callFunction(String module, String method, NativeArray arguments) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        throw new UnsupportedOperationException("Unimplemented method 'callFunction'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    /* renamed from: destroy */
    public void lambda$onNativeException$6() {
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public boolean isDestroyed() {
        throw new UnsupportedOperationException("Unimplemented method 'isDestroyed'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    @VisibleForTesting
    public void initialize() {
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public ReactQueueConfiguration getReactQueueConfiguration() {
        ReactQueueConfiguration reactQueueConfiguration = this.reactHost.getReactQueueConfiguration();
        Intrinsics.checkNotNull(reactQueueConfiguration);
        return reactQueueConfiguration;
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public <T extends JavaScriptModule> T getJSModule(Class<T> jsInterface) {
        Intrinsics.checkNotNullParameter(jsInterface, "jsInterface");
        ReactContext currentReactContext = this.reactHost.getCurrentReactContext();
        T t = currentReactContext != null ? (T) currentReactContext.getJSModule(jsInterface) : null;
        Intrinsics.checkNotNull(t);
        return t;
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public <T extends NativeModule> boolean hasNativeModule(Class<T> nativeModuleInterface) {
        Intrinsics.checkNotNullParameter(nativeModuleInterface, "nativeModuleInterface");
        return this.reactHost.hasNativeModule(nativeModuleInterface);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public <T extends NativeModule> T getNativeModule(Class<T> nativeModuleInterface) {
        Intrinsics.checkNotNullParameter(nativeModuleInterface, "nativeModuleInterface");
        return (T) this.reactHost.getNativeModule(nativeModuleInterface);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public NativeModule getNativeModule(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        return this.reactHost.getNativeModule(moduleName);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    @Deprecated(message = "getJSIModule(JSIModuleType moduleType) is deprecated and will be deleted in the future. Please use ReactInstanceEventListener to subscribe for react instance events instead.")
    public JSIModule getJSIModule(JSIModuleType moduleType) {
        Intrinsics.checkNotNullParameter(moduleType, "moduleType");
        throw new UnsupportedOperationException("Unimplemented method 'getJSIModule'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public Collection<NativeModule> getNativeModules() {
        Collection<NativeModule> nativeModules = this.reactHost.getNativeModules();
        Intrinsics.checkNotNullExpressionValue(nativeModules, "getNativeModules(...)");
        return nativeModules;
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void extendNativeModules(NativeModuleRegistry modules) {
        Intrinsics.checkNotNullParameter(modules, "modules");
        throw new UnsupportedOperationException("Unimplemented method 'extendNativeModules'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        throw new UnsupportedOperationException("Unimplemented method 'addBridgeIdleDebugListener'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        throw new UnsupportedOperationException("Unimplemented method 'removeBridgeIdleDebugListener'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void registerSegment(int segmentId, String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        throw new UnsupportedOperationException("Unimplemented method 'registerSegment'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    @VisibleForTesting
    public void setGlobalVariable(String propName, String jsonValue) {
        Intrinsics.checkNotNullParameter(propName, "propName");
        Intrinsics.checkNotNullParameter(jsonValue, "jsonValue");
        throw new UnsupportedOperationException("Unimplemented method 'setGlobalVariable'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    @Deprecated(message = "This API is unsupported in the New Architecture.")
    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.reactHost.getJavaScriptContextHolder();
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public RuntimeExecutor getRuntimeExecutor() {
        return this.reactHost.getRuntimeExecutor();
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public RuntimeScheduler getRuntimeScheduler() {
        throw new UnsupportedOperationException("Unimplemented method 'getRuntimeScheduler'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    @Deprecated(message = "This API is unsupported in the New Architecture.")
    public <T extends JSIModule> void addJSIModules(List<? extends JSIModuleSpec<T>> jsiModules) {
        Intrinsics.checkNotNullParameter(jsiModules, "jsiModules");
        throw new UnsupportedOperationException("Unimplemented method 'addJSIModules'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public CallInvokerHolder getJSCallInvokerHolder() {
        return this.reactHost.getJSCallInvokerHolder();
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public NativeMethodCallInvokerHolder getNativeMethodCallInvokerHolder() {
        throw new UnsupportedOperationException("Unimplemented method 'getNativeMethodCallInvokerHolder'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    @Deprecated(message = "setTurboModuleManager(JSIModule getter) is deprecated and will be deleted in the future. Please use setTurboModuleRegistry(TurboModuleRegistry turboModuleRegistry) instead.", replaceWith = @ReplaceWith(expression = "setTurboModuleRegistry(turboModuleRegistry)", imports = {}))
    public void setTurboModuleManager(JSIModule getter) {
        Intrinsics.checkNotNullParameter(getter, "getter");
        throw new UnsupportedOperationException("Unimplemented method 'setTurboModuleManager'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void setTurboModuleRegistry(TurboModuleRegistry turboModuleRegistry) {
        Intrinsics.checkNotNullParameter(turboModuleRegistry, "turboModuleRegistry");
        throw new UnsupportedOperationException("Unimplemented method 'setTurboModuleRegistry'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void setFabricUIManager(UIManager fabricUIManager) {
        Intrinsics.checkNotNullParameter(fabricUIManager, "fabricUIManager");
        throw new UnsupportedOperationException("Unimplemented method 'setFabricUIManager'");
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public UIManager getFabricUIManager() {
        throw new UnsupportedOperationException("Unimplemented method 'getFabricUIManager'");
    }
}

package com.facebook.react.bridge;

import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.internal.turbomodule.core.interfaces.TurboModuleRegistry;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.turbomodule.core.interfaces.NativeMethodCallInvokerHolder;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public interface CatalystInstance extends MemoryPressureListener, JSInstance, JSBundleLoaderDelegate {
    void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    @Deprecated
    <T extends JSIModule> void addJSIModules(List<JSIModuleSpec<T>> list);

    void callFunction(String str, String str2, NativeArray nativeArray);

    void destroy();

    void extendNativeModules(NativeModuleRegistry nativeModuleRegistry);

    UIManager getFabricUIManager();

    CallInvokerHolder getJSCallInvokerHolder();

    @Deprecated(since = "getJSIModule(JSIModuleType moduleType) is deprecated and will be deleted in the future. Please use ReactInstanceEventListener to subscribe for react instance events instead.")
    JSIModule getJSIModule(JSIModuleType jSIModuleType);

    <T extends JavaScriptModule> T getJSModule(Class<T> cls);

    @Deprecated
    JavaScriptContextHolder getJavaScriptContextHolder();

    NativeMethodCallInvokerHolder getNativeMethodCallInvokerHolder();

    <T extends NativeModule> T getNativeModule(Class<T> cls);

    NativeModule getNativeModule(String str);

    Collection<NativeModule> getNativeModules();

    ReactQueueConfiguration getReactQueueConfiguration();

    RuntimeExecutor getRuntimeExecutor();

    RuntimeScheduler getRuntimeScheduler();

    String getSourceURL();

    <T extends NativeModule> boolean hasNativeModule(Class<T> cls);

    boolean hasRunJSBundle();

    @VisibleForTesting
    void initialize();

    @Override // com.facebook.react.bridge.JSInstance
    void invokeCallback(int i, NativeArrayInterface nativeArrayInterface);

    boolean isDestroyed();

    void registerSegment(int i, String str);

    void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    void runJSBundle();

    void setFabricUIManager(UIManager uIManager);

    @VisibleForTesting
    void setGlobalVariable(String str, String str2);

    @Deprecated(since = "setTurboModuleManager(JSIModule getter) is deprecated and will be deleted in the future. Please use setTurboModuleRegistry(TurboModuleRegistry turboModuleRegistry)instead.")
    void setTurboModuleManager(JSIModule jSIModule);

    void setTurboModuleRegistry(TurboModuleRegistry turboModuleRegistry);
}

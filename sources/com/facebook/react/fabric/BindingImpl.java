package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.common.mapbuffer.MapBufferSoLoader;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.uimanager.PixelUtil;

/* loaded from: classes.dex */
public class BindingImpl implements Binding {
    private final HybridData mHybridData = initHybrid();

    private static native HybridData initHybrid();

    private native void installFabricUIManager(RuntimeExecutor runtimeExecutor, RuntimeScheduler runtimeScheduler, FabricUIManager fabricUIManager, EventBeatManager eventBeatManager, ComponentFactory componentFactory, Object obj);

    private native void uninstallFabricUIManager();

    @Override // com.facebook.react.fabric.Binding
    public native void driveCxxAnimations();

    @Override // com.facebook.react.fabric.Binding
    public native ReadableNativeMap getInspectorDataForInstance(EventEmitterWrapper eventEmitterWrapper);

    @Override // com.facebook.react.fabric.Binding
    public native void registerSurface(SurfaceHandlerBinding surfaceHandlerBinding);

    @Override // com.facebook.react.fabric.Binding
    public native void reportMount(int i);

    @Override // com.facebook.react.fabric.Binding
    public native void setConstraints(int i, float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2);

    @Override // com.facebook.react.fabric.Binding
    public native void setPixelDensity(float f);

    @Override // com.facebook.react.fabric.Binding
    public native void startSurface(int i, String str, NativeMap nativeMap);

    @Override // com.facebook.react.fabric.Binding
    public native void startSurfaceWithConstraints(int i, String str, NativeMap nativeMap, float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2);

    @Override // com.facebook.react.fabric.Binding
    public native void stopSurface(int i);

    @Override // com.facebook.react.fabric.Binding
    public native void unregisterSurface(SurfaceHandlerBinding surfaceHandlerBinding);

    static {
        FabricSoLoader.staticInit();
        MapBufferSoLoader.staticInit();
    }

    @Override // com.facebook.react.fabric.Binding
    public void register(RuntimeExecutor runtimeExecutor, RuntimeScheduler runtimeScheduler, FabricUIManager fabricUIManager, EventBeatManager eventBeatManager, ComponentFactory componentFactory, ReactNativeConfig reactNativeConfig) {
        fabricUIManager.setBinding(this);
        installFabricUIManager(runtimeExecutor, runtimeScheduler, fabricUIManager, eventBeatManager, componentFactory, reactNativeConfig);
        setPixelDensity(PixelUtil.getDisplayMetricDensity());
    }

    @Override // com.facebook.react.fabric.Binding
    public void unregister() {
        uninstallFabricUIManager();
    }
}

package com.shopify.reactnative.skia;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.RuntimeExecutor;

/* loaded from: classes2.dex */
public class SkiaManager {
    private ReactContext mContext;
    private HybridData mHybridData;
    private PlatformContext mPlatformContext;

    private native HybridData initHybrid(long j, RuntimeExecutor runtimeExecutor, PlatformContext platformContext);

    private native void initializeRuntime();

    public PlatformContext getPlatformContext() {
        return this.mPlatformContext;
    }

    public native void invalidate();

    SkiaManager(ReactContext reactContext) {
        this.mContext = reactContext;
        RuntimeExecutor runtimeExecutor = ReactNativeCompatible.getRuntimeExecutor(reactContext);
        this.mPlatformContext = new PlatformContext(reactContext);
        this.mHybridData = initHybrid(reactContext.getJavaScriptContextHolder().get(), runtimeExecutor, this.mPlatformContext);
        initializeRuntime();
    }

    public void destroy() {
        this.mHybridData.resetNative();
    }

    public float getPixelDensity() {
        return this.mContext.getResources().getDisplayMetrics().density;
    }

    public void onHostResume() {
        this.mPlatformContext.onResume();
    }

    public void onHostPause() {
        this.mPlatformContext.onPause();
    }
}

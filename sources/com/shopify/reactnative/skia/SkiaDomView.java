package com.shopify.reactnative.skia;

import com.facebook.jni.HybridData;
import com.facebook.react.uimanager.ThemedReactContext;

/* loaded from: classes2.dex */
public class SkiaDomView extends SkiaBaseView {
    private HybridData mHybridData;

    private native HybridData initHybrid(SkiaManager skiaManager);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void registerView(int i);

    protected native void setBgColor(int i);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void setDebugMode(boolean z);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void setMode(String str);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void surfaceAvailable(Object obj, int i, int i2);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void surfaceDestroyed();

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void surfaceSizeChanged(int i, int i2);

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void unregisterView();

    @Override // com.shopify.reactnative.skia.SkiaBaseView
    protected native void updateTouchPoints(double[] dArr);

    public SkiaDomView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        this.mHybridData = initHybrid(((RNSkiaModule) themedReactContext.getReactApplicationContext().getNativeModule(RNSkiaModule.class)).getSkiaManager());
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.mHybridData.resetNative();
    }
}

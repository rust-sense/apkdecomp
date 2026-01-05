package com.shopify.reactnative.skia;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import com.shopify.reactnative.skia.SkiaBaseView;

/* loaded from: classes2.dex */
public abstract class SkiaBaseViewManager<T extends SkiaBaseView> extends ReactViewManager {
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    public void setNativeId(ReactViewGroup reactViewGroup, String str) {
        super.setNativeId((SkiaBaseViewManager<T>) reactViewGroup, str);
        ((SkiaBaseView) reactViewGroup).registerView(Integer.parseInt(str));
    }

    @ReactProp(name = "mode")
    public void setMode(T t, String str) {
        t.setMode(str);
    }

    @ReactProp(name = "debug")
    public void setDebug(T t, boolean z) {
        t.setDebugMode(z);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(ReactViewGroup reactViewGroup) {
        super.onDropViewInstance((SkiaBaseViewManager<T>) reactViewGroup);
        SkiaBaseView skiaBaseView = (SkiaBaseView) reactViewGroup;
        skiaBaseView.destroySurface();
        skiaBaseView.unregisterView();
    }
}

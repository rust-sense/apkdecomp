package com.shopify.reactnative.skia;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.SkiaDomViewManagerDelegate;
import com.facebook.react.viewmanagers.SkiaDomViewManagerInterface;

/* loaded from: classes2.dex */
public class SkiaDomViewManager extends SkiaBaseViewManager<SkiaDomView> implements SkiaDomViewManagerInterface<SkiaDomView> {
    protected SkiaDomViewManagerDelegate mDelegate = new SkiaDomViewManagerDelegate(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public SkiaDomViewManagerDelegate getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "SkiaDomView";
    }

    @Override // com.facebook.react.viewmanagers.SkiaDomViewManagerInterface
    @ReactProp(name = "debug")
    public /* bridge */ /* synthetic */ void setDebug(SkiaDomView skiaDomView, boolean z) {
        super.setDebug((SkiaDomViewManager) skiaDomView, z);
    }

    @Override // com.facebook.react.viewmanagers.SkiaDomViewManagerInterface
    @ReactProp(name = "mode")
    public /* bridge */ /* synthetic */ void setMode(SkiaDomView skiaDomView, String str) {
        super.setMode((SkiaDomViewManager) skiaDomView, str);
    }

    SkiaDomViewManager() {
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager
    public SkiaDomView createViewInstance(ThemedReactContext themedReactContext) {
        return new SkiaDomView(themedReactContext);
    }
}

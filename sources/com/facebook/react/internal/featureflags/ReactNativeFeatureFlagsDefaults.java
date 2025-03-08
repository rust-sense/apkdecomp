package com.facebook.react.internal.featureflags;

import kotlin.Metadata;

/* compiled from: ReactNativeFeatureFlagsDefaults.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsDefaults;", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "()V", "androidEnablePendingFabricTransactions", "", "batchRenderingUpdatesInEventLoop", "commonTestFlag", "destroyFabricSurfacesInReactInstanceManager", "enableBackgroundExecutor", "enableCustomDrawOrderFabric", "enableFixForClippedSubviewsCrash", "enableMicrotasks", "enableSpannableBuildingUnification", "inspectorEnableCxxInspectorPackagerConnection", "inspectorEnableModernCDPRegistry", "useModernRuntimeScheduler", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public class ReactNativeFeatureFlagsDefaults implements ReactNativeFeatureFlagsProvider {
    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean androidEnablePendingFabricTransactions() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean batchRenderingUpdatesInEventLoop() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean commonTestFlag() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean destroyFabricSurfacesInReactInstanceManager() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableBackgroundExecutor() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableCustomDrawOrderFabric() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableFixForClippedSubviewsCrash() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableMicrotasks() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean enableSpannableBuildingUnification() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean inspectorEnableCxxInspectorPackagerConnection() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean inspectorEnableModernCDPRegistry() {
        return false;
    }

    @Override // com.facebook.react.internal.featureflags.ReactNativeFeatureFlagsProvider
    public boolean useModernRuntimeScheduler() {
        return false;
    }
}

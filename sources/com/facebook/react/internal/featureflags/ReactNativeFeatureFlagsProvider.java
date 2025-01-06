package com.facebook.react.internal.featureflags;

import kotlin.Metadata;

/* compiled from: ReactNativeFeatureFlagsProvider.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\b\u0010\u0004\u001a\u00020\u0003H'J\b\u0010\u0005\u001a\u00020\u0003H'J\b\u0010\u0006\u001a\u00020\u0003H'J\b\u0010\u0007\u001a\u00020\u0003H'J\b\u0010\b\u001a\u00020\u0003H'J\b\u0010\t\u001a\u00020\u0003H'J\b\u0010\n\u001a\u00020\u0003H'J\b\u0010\u000b\u001a\u00020\u0003H'J\b\u0010\f\u001a\u00020\u0003H'J\b\u0010\r\u001a\u00020\u0003H'J\b\u0010\u000e\u001a\u00020\u0003H'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "", "androidEnablePendingFabricTransactions", "", "batchRenderingUpdatesInEventLoop", "commonTestFlag", "destroyFabricSurfacesInReactInstanceManager", "enableBackgroundExecutor", "enableCustomDrawOrderFabric", "enableFixForClippedSubviewsCrash", "enableMicrotasks", "enableSpannableBuildingUnification", "inspectorEnableCxxInspectorPackagerConnection", "inspectorEnableModernCDPRegistry", "useModernRuntimeScheduler", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ReactNativeFeatureFlagsProvider {
    boolean androidEnablePendingFabricTransactions();

    boolean batchRenderingUpdatesInEventLoop();

    boolean commonTestFlag();

    boolean destroyFabricSurfacesInReactInstanceManager();

    boolean enableBackgroundExecutor();

    boolean enableCustomDrawOrderFabric();

    boolean enableFixForClippedSubviewsCrash();

    boolean enableMicrotasks();

    boolean enableSpannableBuildingUnification();

    boolean inspectorEnableCxxInspectorPackagerConnection();

    boolean inspectorEnableModernCDPRegistry();

    boolean useModernRuntimeScheduler();
}

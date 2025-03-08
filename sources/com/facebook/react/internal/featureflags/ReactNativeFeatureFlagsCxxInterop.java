package com.facebook.react.internal.featureflags;

import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: ReactNativeFeatureFlagsCxxInterop.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0087 J\t\u0010\u0005\u001a\u00020\u0004H\u0087 J\t\u0010\u0006\u001a\u00020\u0004H\u0087 J\t\u0010\u0007\u001a\u00020\bH\u0087 J\t\u0010\t\u001a\u00020\u0004H\u0087 J\t\u0010\n\u001a\u00020\u0004H\u0087 J\t\u0010\u000b\u001a\u00020\u0004H\u0087 J\t\u0010\f\u001a\u00020\u0004H\u0087 J\t\u0010\r\u001a\u00020\u0004H\u0087 J\t\u0010\u000e\u001a\u00020\u0004H\u0087 J\t\u0010\u000f\u001a\u00020\u0004H\u0087 J\t\u0010\u0010\u001a\u00020\u0004H\u0087 J\u0011\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0001H\u0087 J\t\u0010\u0013\u001a\u00020\u0004H\u0087 ¨\u0006\u0014"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsCxxInterop;", "", "()V", "androidEnablePendingFabricTransactions", "", "batchRenderingUpdatesInEventLoop", "commonTestFlag", "dangerouslyReset", "", "destroyFabricSurfacesInReactInstanceManager", "enableBackgroundExecutor", "enableCustomDrawOrderFabric", "enableFixForClippedSubviewsCrash", "enableMicrotasks", "enableSpannableBuildingUnification", "inspectorEnableCxxInspectorPackagerConnection", "inspectorEnableModernCDPRegistry", "override", "provider", "useModernRuntimeScheduler", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ReactNativeFeatureFlagsCxxInterop {
    public static final ReactNativeFeatureFlagsCxxInterop INSTANCE = new ReactNativeFeatureFlagsCxxInterop();

    @JvmStatic
    public static final native boolean androidEnablePendingFabricTransactions();

    @JvmStatic
    public static final native boolean batchRenderingUpdatesInEventLoop();

    @JvmStatic
    public static final native boolean commonTestFlag();

    @JvmStatic
    public static final native void dangerouslyReset();

    @JvmStatic
    public static final native boolean destroyFabricSurfacesInReactInstanceManager();

    @JvmStatic
    public static final native boolean enableBackgroundExecutor();

    @JvmStatic
    public static final native boolean enableCustomDrawOrderFabric();

    @JvmStatic
    public static final native boolean enableFixForClippedSubviewsCrash();

    @JvmStatic
    public static final native boolean enableMicrotasks();

    @JvmStatic
    public static final native boolean enableSpannableBuildingUnification();

    @JvmStatic
    public static final native boolean inspectorEnableCxxInspectorPackagerConnection();

    @JvmStatic
    public static final native boolean inspectorEnableModernCDPRegistry();

    @JvmStatic
    public static final native void override(Object provider);

    @JvmStatic
    public static final native boolean useModernRuntimeScheduler();

    private ReactNativeFeatureFlagsCxxInterop() {
    }

    static {
        SoLoader.loadLibrary("react_featureflagsjni");
    }
}

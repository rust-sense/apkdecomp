package com.facebook.react.uimanager;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.soloader.SoLoader;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class UIConstantsProviderManager {
    private final HybridData mHybridData;

    public interface ConstantsForViewManagerProvider {
        @Nullable
        NativeMap getConstantsForViewManager(String str);
    }

    public interface ConstantsProvider {
        NativeMap getConstants();
    }

    public interface DefaultEventTypesProvider {
        NativeMap getDefaultEventTypes();
    }

    private native HybridData initHybrid(RuntimeExecutor runtimeExecutor, DefaultEventTypesProvider defaultEventTypesProvider, ConstantsForViewManagerProvider constantsForViewManagerProvider, ConstantsProvider constantsProvider);

    private native void installJSIBindings();

    static {
        staticInit();
    }

    public UIConstantsProviderManager(RuntimeExecutor runtimeExecutor, DefaultEventTypesProvider defaultEventTypesProvider, ConstantsForViewManagerProvider constantsForViewManagerProvider, ConstantsProvider constantsProvider) {
        this.mHybridData = initHybrid(runtimeExecutor, defaultEventTypesProvider, constantsForViewManagerProvider, constantsProvider);
        installJSIBindings();
    }

    private static void staticInit() {
        SoLoader.loadLibrary("uimanagerjni");
    }
}

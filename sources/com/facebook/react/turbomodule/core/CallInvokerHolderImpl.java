package com.facebook.react.turbomodule.core;

import com.facebook.jni.HybridData;
import com.facebook.react.common.annotations.FrameworkAPI;
import com.facebook.react.internal.turbomodule.core.NativeModuleSoLoader;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;

@FrameworkAPI
/* loaded from: classes.dex */
public class CallInvokerHolderImpl implements CallInvokerHolder {
    private final HybridData mHybridData;

    static {
        NativeModuleSoLoader.maybeLoadSoLibrary();
    }

    private CallInvokerHolderImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}

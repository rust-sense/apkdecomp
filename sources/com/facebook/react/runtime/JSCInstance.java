package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.soloader.SoLoader;

/* loaded from: classes.dex */
public class JSCInstance extends JSRuntimeFactory {
    protected static native HybridData initHybrid();

    static {
        SoLoader.loadLibrary("jscinstance");
    }

    public JSCInstance() {
        super(initHybrid());
    }
}

package com.facebook.imagepipeline.core;

/* loaded from: classes.dex */
public class NativeCodeSetup {
    private static boolean sUseNativeCode = true;

    public static boolean getUseNativeCode() {
        return sUseNativeCode;
    }

    public static void setUseNativeCode(boolean z) {
        sUseNativeCode = z;
    }

    private NativeCodeSetup() {
    }
}

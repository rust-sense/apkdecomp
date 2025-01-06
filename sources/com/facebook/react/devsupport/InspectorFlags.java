package com.facebook.react.devsupport;

/* loaded from: classes.dex */
public class InspectorFlags {
    public static native boolean getEnableCxxInspectorPackagerConnection();

    public static native boolean getEnableModernCDPRegistry();

    static {
        DevSupportSoLoader.staticInit();
    }

    private InspectorFlags() {
    }
}

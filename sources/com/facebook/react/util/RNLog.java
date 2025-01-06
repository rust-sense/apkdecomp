package com.facebook.react.util;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.google.firebase.messaging.Constants;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class RNLog {
    public static final int ADVICE = 4;
    public static final int ERROR = 6;
    public static final int LOG = 2;
    public static final int MINIMUM_LEVEL_FOR_UI = 5;
    public static final int TRACE = 3;
    public static final int WARN = 5;

    private static String levelToString(int i) {
        return (i == 2 || i == 3) ? "log" : (i == 4 || i == 5) ? "warn" : i != 6 ? "none" : Constants.IPC_BUNDLE_KEY_SEND_ERROR;
    }

    public static void l(String str) {
        FLog.i(ReactConstants.TAG, str);
    }

    public static void t(String str) {
        FLog.i(ReactConstants.TAG, str);
    }

    public static void a(String str) {
        FLog.w(ReactConstants.TAG, "(ADVICE)" + str);
    }

    public static void w(@Nullable ReactContext reactContext, String str) {
        logInternal(reactContext, str, 5);
        FLog.w(ReactConstants.TAG, str);
    }

    public static void e(@Nullable ReactContext reactContext, String str) {
        logInternal(reactContext, str, 6);
        FLog.e(ReactConstants.TAG, str);
    }

    public static void e(String str) {
        FLog.e(ReactConstants.TAG, str);
    }

    private static void logInternal(@Nullable ReactContext reactContext, @Nullable String str, int i) {
        if (i < 5 || reactContext == null || !reactContext.hasActiveReactInstance() || str == null) {
            return;
        }
        ((RCTLog) reactContext.getJSModule(RCTLog.class)).logIfNoNativeHook(levelToString(i), str);
    }
}

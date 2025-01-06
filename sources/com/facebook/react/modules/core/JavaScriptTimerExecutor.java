package com.facebook.react.modules.core;

import com.facebook.react.bridge.WritableArray;

/* loaded from: classes.dex */
public interface JavaScriptTimerExecutor {
    void callIdleCallbacks(double d);

    void callTimers(WritableArray writableArray);

    void emitTimeDriftWarning(String str);
}

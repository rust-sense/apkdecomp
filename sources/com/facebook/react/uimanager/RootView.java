package com.facebook.react.uimanager;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes.dex */
public interface RootView {
    void handleException(Throwable th);

    void onChildEndedNativeGesture(View view, MotionEvent motionEvent);

    @Deprecated
    void onChildStartedNativeGesture(MotionEvent motionEvent);

    void onChildStartedNativeGesture(View view, MotionEvent motionEvent);

    /* renamed from: com.facebook.react.uimanager.RootView$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}

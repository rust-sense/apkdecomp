package com.facebook.react.uimanager;

import android.view.Choreographer;
import com.facebook.react.bridge.ReactContext;

/* loaded from: classes.dex */
public abstract class GuardedFrameCallback implements Choreographer.FrameCallback {
    private final ReactContext mReactContext;

    protected abstract void doFrameGuarded(long j);

    protected GuardedFrameCallback(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        try {
            doFrameGuarded(j);
        } catch (RuntimeException e) {
            this.mReactContext.handleException(e);
        }
    }
}

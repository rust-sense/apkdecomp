package com.facebook.fresco.animation.backend;

/* loaded from: classes.dex */
public interface AnimationInformation {
    public static final int LOOP_COUNT_INFINITE = 0;

    int getFrameCount();

    int getFrameDurationMs(int i);

    int getLoopCount();

    int getLoopDurationMs();

    int height();

    int width();
}

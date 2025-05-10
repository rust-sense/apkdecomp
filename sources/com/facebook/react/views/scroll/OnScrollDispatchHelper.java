package com.facebook.react.views.scroll;

import android.os.SystemClock;

/* loaded from: classes.dex */
public class OnScrollDispatchHelper {
    private static final int MIN_EVENT_SEPARATION_MS = 10;
    private int mPrevX = Integer.MIN_VALUE;
    private int mPrevY = Integer.MIN_VALUE;
    private float mXFlingVelocity = 0.0f;
    private float mYFlingVelocity = 0.0f;
    private long mLastScrollEventTimeMs = -11;

    public float getXFlingVelocity() {
        return this.mXFlingVelocity;
    }

    public float getYFlingVelocity() {
        return this.mYFlingVelocity;
    }

    public boolean onScrollChanged(int i, int i2) {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = this.mLastScrollEventTimeMs;
        boolean z = (uptimeMillis - j <= 10 && this.mPrevX == i && this.mPrevY == i2) ? false : true;
        if (uptimeMillis - j != 0) {
            this.mXFlingVelocity = (i - this.mPrevX) / (uptimeMillis - j);
            this.mYFlingVelocity = (i2 - this.mPrevY) / (uptimeMillis - j);
        }
        this.mLastScrollEventTimeMs = uptimeMillis;
        this.mPrevX = i;
        this.mPrevY = i2;
        return z;
    }
}

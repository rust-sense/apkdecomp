package com.facebook.common.time;

/* loaded from: classes.dex */
public class AwakeTimeSinceBootClock implements MonotonicClock, MonotonicNanoClock {
    private static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    private AwakeTimeSinceBootClock() {
    }

    @Override // com.facebook.common.time.MonotonicClock
    public long now() {
        return android.os.SystemClock.uptimeMillis();
    }

    @Override // com.facebook.common.time.MonotonicNanoClock
    public long nowNanos() {
        return System.nanoTime();
    }
}

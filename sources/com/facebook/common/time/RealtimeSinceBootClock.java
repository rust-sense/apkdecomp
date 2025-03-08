package com.facebook.common.time;

/* loaded from: classes.dex */
public class RealtimeSinceBootClock implements MonotonicClock {
    private static final RealtimeSinceBootClock INSTANCE = new RealtimeSinceBootClock();

    public static RealtimeSinceBootClock get() {
        return INSTANCE;
    }

    private RealtimeSinceBootClock() {
    }

    @Override // com.facebook.common.time.MonotonicClock
    public long now() {
        return android.os.SystemClock.elapsedRealtime();
    }
}

package com.facebook.common.time;

/* loaded from: classes.dex */
public class SystemClock implements Clock {
    private static final SystemClock INSTANCE = new SystemClock();

    public static SystemClock get() {
        return INSTANCE;
    }

    private SystemClock() {
    }

    @Override // com.facebook.common.time.Clock
    public long now() {
        return System.currentTimeMillis();
    }
}

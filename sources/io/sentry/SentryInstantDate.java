package io.sentry;

import java.time.Instant;

/* loaded from: classes2.dex */
public final class SentryInstantDate extends SentryDate {
    private final Instant date;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SentryInstantDate() {
        /*
            r1 = this;
            java.time.Instant r0 = io.sentry.SentryWrapper$$ExternalSyntheticApiModelOutline0.m794m()
            r1.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.SentryInstantDate.<init>():void");
    }

    public SentryInstantDate(Instant instant) {
        this.date = instant;
    }

    @Override // io.sentry.SentryDate
    public long nanoTimestamp() {
        long epochSecond;
        int nano;
        epochSecond = this.date.getEpochSecond();
        long secondsToNanos = DateUtils.secondsToNanos(epochSecond);
        nano = this.date.getNano();
        return secondsToNanos + nano;
    }
}

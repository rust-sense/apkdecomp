package com.facebook.react.jstasks;

/* loaded from: classes.dex */
public class LinearCountingRetryPolicy implements HeadlessJsTaskRetryPolicy {
    private final int mDelayBetweenAttemptsInMs;
    private final int mRetryAttempts;

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public boolean canRetry() {
        return this.mRetryAttempts > 0;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public int getDelay() {
        return this.mDelayBetweenAttemptsInMs;
    }

    public LinearCountingRetryPolicy(int i, int i2) {
        this.mRetryAttempts = i;
        this.mDelayBetweenAttemptsInMs = i2;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public HeadlessJsTaskRetryPolicy update() {
        int i = this.mRetryAttempts - 1;
        if (i > 0) {
            return new LinearCountingRetryPolicy(i, this.mDelayBetweenAttemptsInMs);
        }
        return NoRetryPolicy.INSTANCE;
    }

    @Override // com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy
    public HeadlessJsTaskRetryPolicy copy() {
        return new LinearCountingRetryPolicy(this.mRetryAttempts, this.mDelayBetweenAttemptsInMs);
    }
}

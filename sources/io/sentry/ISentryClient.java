package io.sentry;

import io.sentry.protocol.Message;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.transport.RateLimiter;

/* loaded from: classes2.dex */
public interface ISentryClient {
    SentryId captureCheckIn(CheckIn checkIn, IScope iScope, Hint hint);

    SentryId captureEnvelope(SentryEnvelope sentryEnvelope);

    SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Hint hint);

    SentryId captureEvent(SentryEvent sentryEvent);

    SentryId captureEvent(SentryEvent sentryEvent, Hint hint);

    SentryId captureEvent(SentryEvent sentryEvent, IScope iScope);

    SentryId captureEvent(SentryEvent sentryEvent, IScope iScope, Hint hint);

    SentryId captureException(Throwable th);

    SentryId captureException(Throwable th, Hint hint);

    SentryId captureException(Throwable th, IScope iScope);

    SentryId captureException(Throwable th, IScope iScope, Hint hint);

    SentryId captureMessage(String str, SentryLevel sentryLevel);

    SentryId captureMessage(String str, SentryLevel sentryLevel, IScope iScope);

    void captureSession(Session session);

    void captureSession(Session session, Hint hint);

    SentryId captureTransaction(SentryTransaction sentryTransaction);

    SentryId captureTransaction(SentryTransaction sentryTransaction, IScope iScope, Hint hint);

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext);

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, IScope iScope, Hint hint);

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, IScope iScope, Hint hint, ProfilingTraceData profilingTraceData);

    void captureUserFeedback(UserFeedback userFeedback);

    void close();

    void close(boolean z);

    void flush(long j);

    IMetricsAggregator getMetricsAggregator();

    RateLimiter getRateLimiter();

    boolean isEnabled();

    boolean isHealthy();

    /* renamed from: io.sentry.ISentryClient$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$isHealthy(ISentryClient _this) {
            return true;
        }

        public static SentryId $default$captureMessage(ISentryClient _this, String str, SentryLevel sentryLevel, IScope iScope) {
            SentryEvent sentryEvent = new SentryEvent();
            Message message = new Message();
            message.setFormatted(str);
            sentryEvent.setMessage(message);
            sentryEvent.setLevel(sentryLevel);
            return _this.captureEvent(sentryEvent, iScope);
        }
    }
}

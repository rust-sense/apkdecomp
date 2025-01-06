package io.sentry;

import io.sentry.ISentryClient;
import io.sentry.metrics.NoopMetricsAggregator;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.transport.RateLimiter;

/* loaded from: classes2.dex */
final class NoOpSentryClient implements ISentryClient {
    private static final NoOpSentryClient instance = new NoOpSentryClient();

    public static NoOpSentryClient getInstance() {
        return instance;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureEnvelope(SentryEnvelope sentryEnvelope) {
        SentryId captureEnvelope;
        captureEnvelope = captureEnvelope(sentryEnvelope, null);
        return captureEnvelope;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureEvent(SentryEvent sentryEvent) {
        SentryId captureEvent;
        captureEvent = captureEvent(sentryEvent, null, null);
        return captureEvent;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureEvent(SentryEvent sentryEvent, Hint hint) {
        SentryId captureEvent;
        captureEvent = captureEvent(sentryEvent, null, hint);
        return captureEvent;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureEvent(SentryEvent sentryEvent, IScope iScope) {
        SentryId captureEvent;
        captureEvent = captureEvent(sentryEvent, iScope, null);
        return captureEvent;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureException(Throwable th) {
        SentryId captureException;
        captureException = captureException(th, null, null);
        return captureException;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureException(Throwable th, Hint hint) {
        SentryId captureException;
        captureException = captureException(th, null, hint);
        return captureException;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureException(Throwable th, IScope iScope) {
        SentryId captureException;
        captureException = captureException(th, iScope, null);
        return captureException;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureException(Throwable th, IScope iScope, Hint hint) {
        SentryId captureEvent;
        captureEvent = captureEvent(new SentryEvent(th), iScope, hint);
        return captureEvent;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureMessage(String str, SentryLevel sentryLevel) {
        SentryId captureMessage;
        captureMessage = captureMessage(str, sentryLevel, null);
        return captureMessage;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureMessage(String str, SentryLevel sentryLevel, IScope iScope) {
        return ISentryClient.CC.$default$captureMessage(this, str, sentryLevel, iScope);
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ void captureSession(Session session) {
        captureSession(session, null);
    }

    @Override // io.sentry.ISentryClient
    public void captureSession(Session session, Hint hint) {
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureTransaction(SentryTransaction sentryTransaction) {
        SentryId captureTransaction;
        captureTransaction = captureTransaction(sentryTransaction, null, null, null);
        return captureTransaction;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureTransaction(SentryTransaction sentryTransaction, IScope iScope, Hint hint) {
        SentryId captureTransaction;
        captureTransaction = captureTransaction(sentryTransaction, null, iScope, hint);
        return captureTransaction;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext) {
        SentryId captureTransaction;
        captureTransaction = captureTransaction(sentryTransaction, traceContext, null, null);
        return captureTransaction;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, IScope iScope, Hint hint) {
        SentryId captureTransaction;
        captureTransaction = captureTransaction(sentryTransaction, traceContext, iScope, hint, null);
        return captureTransaction;
    }

    @Override // io.sentry.ISentryClient
    public void captureUserFeedback(UserFeedback userFeedback) {
    }

    @Override // io.sentry.ISentryClient
    public void close() {
    }

    @Override // io.sentry.ISentryClient
    public void close(boolean z) {
    }

    @Override // io.sentry.ISentryClient
    public void flush(long j) {
    }

    @Override // io.sentry.ISentryClient
    public RateLimiter getRateLimiter() {
        return null;
    }

    @Override // io.sentry.ISentryClient
    public boolean isEnabled() {
        return false;
    }

    @Override // io.sentry.ISentryClient
    public /* synthetic */ boolean isHealthy() {
        return ISentryClient.CC.$default$isHealthy(this);
    }

    private NoOpSentryClient() {
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureEvent(SentryEvent sentryEvent, IScope iScope, Hint hint) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Hint hint) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, IScope iScope, Hint hint, ProfilingTraceData profilingTraceData) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.ISentryClient
    public SentryId captureCheckIn(CheckIn checkIn, IScope iScope, Hint hint) {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.ISentryClient
    public IMetricsAggregator getMetricsAggregator() {
        return NoopMetricsAggregator.getInstance();
    }
}

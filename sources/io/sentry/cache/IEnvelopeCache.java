package io.sentry.cache;

import io.sentry.Hint;
import io.sentry.SentryEnvelope;

/* loaded from: classes2.dex */
public interface IEnvelopeCache extends Iterable<SentryEnvelope> {
    void discard(SentryEnvelope sentryEnvelope);

    void store(SentryEnvelope sentryEnvelope);

    void store(SentryEnvelope sentryEnvelope, Hint hint);

    /* renamed from: io.sentry.cache.IEnvelopeCache$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}

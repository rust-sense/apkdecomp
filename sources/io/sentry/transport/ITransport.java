package io.sentry.transport;

import io.sentry.Hint;
import io.sentry.SentryEnvelope;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface ITransport extends Closeable {
    void close(boolean z) throws IOException;

    void flush(long j);

    RateLimiter getRateLimiter();

    boolean isHealthy();

    void send(SentryEnvelope sentryEnvelope) throws IOException;

    void send(SentryEnvelope sentryEnvelope, Hint hint) throws IOException;

    /* renamed from: io.sentry.transport.ITransport$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$isHealthy(ITransport _this) {
            return true;
        }
    }
}

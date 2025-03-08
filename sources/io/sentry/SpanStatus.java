package io.sentry;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;

/* loaded from: classes2.dex */
public enum SpanStatus implements JsonSerializable {
    OK(200, 299),
    CANCELLED(499),
    INTERNAL_ERROR(500),
    UNKNOWN(500),
    UNKNOWN_ERROR(500),
    INVALID_ARGUMENT(400),
    DEADLINE_EXCEEDED(HttpServletResponse.SC_GATEWAY_TIMEOUT),
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND),
    ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT),
    PERMISSION_DENIED(HttpServletResponse.SC_FORBIDDEN),
    RESOURCE_EXHAUSTED(429),
    FAILED_PRECONDITION(400),
    ABORTED(HttpServletResponse.SC_CONFLICT),
    OUT_OF_RANGE(400),
    UNIMPLEMENTED(HttpServletResponse.SC_NOT_IMPLEMENTED),
    UNAVAILABLE(HttpServletResponse.SC_SERVICE_UNAVAILABLE),
    DATA_LOSS(500),
    UNAUTHENTICATED(HttpServletResponse.SC_UNAUTHORIZED);

    private final int maxHttpStatusCode;
    private final int minHttpStatusCode;

    private boolean matches(int i) {
        return i >= this.minHttpStatusCode && i <= this.maxHttpStatusCode;
    }

    SpanStatus(int i) {
        this.minHttpStatusCode = i;
        this.maxHttpStatusCode = i;
    }

    SpanStatus(int i, int i2) {
        this.minHttpStatusCode = i;
        this.maxHttpStatusCode = i2;
    }

    public static SpanStatus fromHttpStatusCode(int i) {
        for (SpanStatus spanStatus : values()) {
            if (spanStatus.matches(i)) {
                return spanStatus;
            }
        }
        return null;
    }

    public static SpanStatus fromHttpStatusCode(Integer num, SpanStatus spanStatus) {
        SpanStatus fromHttpStatusCode = num != null ? fromHttpStatusCode(num.intValue()) : spanStatus;
        return fromHttpStatusCode != null ? fromHttpStatusCode : spanStatus;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.value(name().toLowerCase(Locale.ROOT));
    }

    public static final class Deserializer implements JsonDeserializer<SpanStatus> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SpanStatus deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            return SpanStatus.valueOf(jsonObjectReader.nextString().toUpperCase(Locale.ROOT));
        }
    }
}

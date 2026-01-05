package io.sentry.metrics;

import java.nio.charset.Charset;
import java.util.Map;

/* loaded from: classes2.dex */
public final class EncodedMetrics {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final Map<Long, Map<String, Metric>> buckets;

    Map<Long, Map<String, Metric>> getBuckets() {
        return this.buckets;
    }

    public EncodedMetrics(Map<Long, Map<String, Metric>> map) {
        this.buckets = map;
    }

    public byte[] encodeToStatsd() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Map<String, Metric>> entry : this.buckets.entrySet()) {
            MetricsHelper.encodeMetrics(entry.getKey().longValue(), entry.getValue().values(), sb);
        }
        return sb.toString().getBytes(UTF8);
    }
}

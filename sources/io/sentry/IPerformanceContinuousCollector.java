package io.sentry;

/* loaded from: classes2.dex */
public interface IPerformanceContinuousCollector extends IPerformanceCollector {
    void clear();

    void onSpanFinished(ISpan iSpan);

    void onSpanStarted(ISpan iSpan);
}

package io.sentry;

/* loaded from: classes2.dex */
public final class JavaMemoryCollector implements IPerformanceSnapshotCollector {
    private final Runtime runtime = Runtime.getRuntime();

    @Override // io.sentry.IPerformanceSnapshotCollector
    public void setup() {
    }

    @Override // io.sentry.IPerformanceSnapshotCollector
    public void collect(PerformanceCollectionData performanceCollectionData) {
        performanceCollectionData.addMemoryData(new MemoryCollectionData(System.currentTimeMillis(), this.runtime.totalMemory() - this.runtime.freeMemory()));
    }
}

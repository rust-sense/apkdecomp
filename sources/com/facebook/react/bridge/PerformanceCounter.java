package com.facebook.react.bridge;

import java.util.Map;

/* loaded from: classes.dex */
interface PerformanceCounter {
    Map<String, Long> getPerformanceCounters();

    void profileNextBatch();
}

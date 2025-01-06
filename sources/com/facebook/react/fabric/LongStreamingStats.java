package com.facebook.react.fabric;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.ToLongFunction;

/* loaded from: classes.dex */
class LongStreamingStats {
    private int len;
    private long max;
    private final Queue<Long> maxHeap;
    private final Queue<Long> minHeap;
    private double streamingAverage;

    public double getAverage() {
        return this.streamingAverage;
    }

    public long getMax() {
        return this.max;
    }

    LongStreamingStats() {
        Comparator comparingLong;
        comparingLong = Comparator.comparingLong(new ToLongFunction() { // from class: com.facebook.react.fabric.LongStreamingStats$$ExternalSyntheticLambda1
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                long longValue;
                longValue = ((Long) obj).longValue();
                return longValue;
            }
        });
        this.minHeap = new PriorityQueue(11, comparingLong);
        this.maxHeap = new PriorityQueue(11, new Comparator() { // from class: com.facebook.react.fabric.LongStreamingStats$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((Long) obj2).longValue(), ((Long) obj).longValue());
                return compare;
            }
        });
        this.streamingAverage = 0.0d;
        this.len = 0;
        this.max = 0L;
    }

    public void add(long j) {
        if (j != 0) {
            if (this.minHeap.size() == this.maxHeap.size()) {
                this.maxHeap.offer(Long.valueOf(j));
                this.minHeap.offer(this.maxHeap.poll());
            } else {
                this.minHeap.offer(Long.valueOf(j));
                this.maxHeap.offer(this.minHeap.poll());
            }
        }
        int i = this.len + 1;
        this.len = i;
        if (i == 1) {
            this.streamingAverage = j;
        } else {
            this.streamingAverage = (this.streamingAverage / (i / r0)) + (j / i);
        }
        long j2 = this.max;
        if (j <= j2) {
            j = j2;
        }
        this.max = j;
    }

    public double getMedian() {
        long longValue;
        if (this.minHeap.size() == 0 && this.maxHeap.size() == 0) {
            return 0.0d;
        }
        if (this.minHeap.size() > this.maxHeap.size()) {
            longValue = this.minHeap.peek().longValue();
        } else {
            longValue = (this.minHeap.peek().longValue() + this.maxHeap.peek().longValue()) / 2;
        }
        return longValue;
    }
}

package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimationLoaderExecutor.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\nJ\u0011\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0000H\u0096\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask;", "", "Ljava/lang/Runnable;", SentryThread.JsonKeys.PRIORITY, "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "getPriority", "()Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "compareTo", "", "other", "Priority", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface LoadFramePriorityTask extends Comparable<LoadFramePriorityTask>, Runnable {
    int compareTo(LoadFramePriorityTask other);

    Priority getPriority();

    /* compiled from: AnimationLoaderExecutor.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static int compareTo(LoadFramePriorityTask loadFramePriorityTask, LoadFramePriorityTask other) {
            Intrinsics.checkNotNullParameter(other, "other");
            return other.getPriority().compareTo(loadFramePriorityTask.getPriority());
        }
    }

    /* compiled from: AnimationLoaderExecutor.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "HIGH", "MEDIUM", "LOW", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum Priority {
        HIGH(10),
        MEDIUM(5),
        LOW(1);

        private final int value;

        public final int getValue() {
            return this.value;
        }

        Priority(int i) {
            this.value = i;
        }
    }
}

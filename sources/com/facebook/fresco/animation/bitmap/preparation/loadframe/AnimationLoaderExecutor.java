package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimationLoaderExecutor.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/AnimationLoaderExecutor;", "", "()V", "THREADS", "", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "uiThreadFactory", "Ljava/util/concurrent/ThreadFactory;", "execute", "", "task", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimationLoaderExecutor {
    public static final AnimationLoaderExecutor INSTANCE = new AnimationLoaderExecutor();
    private static final int THREADS;
    private static final ThreadPoolExecutor executor;
    private static final ThreadFactory uiThreadFactory;

    private AnimationLoaderExecutor() {
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors() * 2;
        THREADS = availableProcessors;
        ThreadFactory threadFactory = new ThreadFactory() { // from class: com.facebook.fresco.animation.bitmap.preparation.loadframe.AnimationLoaderExecutor$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread uiThreadFactory$lambda$0;
                uiThreadFactory$lambda$0 = AnimationLoaderExecutor.uiThreadFactory$lambda$0(runnable);
                return uiThreadFactory$lambda$0;
            }
        };
        uiThreadFactory = threadFactory;
        executor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), threadFactory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread uiThreadFactory$lambda$0(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(9);
        return thread;
    }

    public final void execute(LoadFramePriorityTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        executor.execute(task);
    }
}

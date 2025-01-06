package com.bumptech.glide.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class Executors {
    private static final Executor MAIN_THREAD_EXECUTOR = new Executor() { // from class: com.bumptech.glide.util.Executors.1
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            Util.postOnUiThread(runnable);
        }
    };
    private static final Executor DIRECT_EXECUTOR = new Executor() { // from class: com.bumptech.glide.util.Executors.2
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    };

    public static Executor directExecutor() {
        return DIRECT_EXECUTOR;
    }

    public static Executor mainThreadExecutor() {
        return MAIN_THREAD_EXECUTOR;
    }

    private Executors() {
    }

    public static void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdownNow();
        try {
            if (executorService.awaitTermination(5L, TimeUnit.SECONDS)) {
                return;
            }
            executorService.shutdownNow();
            if (executorService.awaitTermination(5L, TimeUnit.SECONDS)) {
            } else {
                throw new RuntimeException("Failed to shutdown");
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}

package com.facebook.react.runtime;

import com.facebook.react.runtime.internal.bolts.CancellationTokenSource;
import com.facebook.react.runtime.internal.bolts.Task;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
class BoltsFutureTask<T> implements Future<T> {
    private boolean isTaskCancelled;
    private final CancellationTokenSource mCancellationTokenSource;
    private final Task<T> mTask;

    private BoltsFutureTask(Task<T> task) {
        this(task, new CancellationTokenSource());
    }

    private BoltsFutureTask(Task<T> task, CancellationTokenSource cancellationTokenSource) {
        this.isTaskCancelled = false;
        this.mTask = task;
        this.mCancellationTokenSource = cancellationTokenSource;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        try {
            if (!isDone()) {
                this.mCancellationTokenSource.cancel();
            }
            return true;
        } finally {
            this.isTaskCancelled = true;
        }
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.isTaskCancelled || this.mTask.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.isTaskCancelled || this.mTask.isCancelled() || this.mTask.isFaulted() || this.mTask.isCompleted();
    }

    @Override // java.util.concurrent.Future
    public T get() throws ExecutionException, InterruptedException {
        this.mTask.waitForCompletion();
        return getResult(this.mTask);
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.mTask.waitForCompletion(j, timeUnit)) {
            return getResult(this.mTask);
        }
        throw new TimeoutException();
    }

    private T getResult(Task<T> task) throws ExecutionException {
        if (task.isFaulted()) {
            throw new ExecutionException("", new Throwable());
        }
        if (task.isCancelled()) {
            throw new CancellationException("");
        }
        return task.getResult();
    }
}

package com.facebook.datasource;

import android.util.Pair;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public abstract class AbstractDataSource<T> implements DataSource<T> {

    @Nullable
    private static volatile DataSourceInstrumenter sDataSourceInstrumenter;

    @Nullable
    private Map<String, Object> mExtras;

    @Nullable
    private T mResult = null;

    @Nullable
    private Throwable mFailureThrowable = null;
    private float mProgress = 0.0f;
    private boolean mIsClosed = false;
    private DataSourceStatus mDataSourceStatus = DataSourceStatus.IN_PROGRESS;
    private final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers = new ConcurrentLinkedQueue<>();

    public interface DataSourceInstrumenter {
        Runnable decorateRunnable(Runnable runnable, String str);
    }

    private enum DataSourceStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    @Nullable
    public static DataSourceInstrumenter getDataSourceInstrumenter() {
        return sDataSourceInstrumenter;
    }

    public static void provideInstrumenter(@Nullable DataSourceInstrumenter dataSourceInstrumenter) {
        sDataSourceInstrumenter = dataSourceInstrumenter;
    }

    protected void closeResult(@Nullable T t) {
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public Map<String, Object> getExtras() {
        return this.mExtras;
    }

    @Override // com.facebook.datasource.DataSource
    public boolean hasMultipleResults() {
        return false;
    }

    protected void setExtras(@Nullable Map<String, Object> map) {
        this.mExtras = map;
    }

    protected AbstractDataSource() {
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean isFinished() {
        return this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public synchronized T getResult() {
        return this.mResult;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == DataSourceStatus.FAILURE;
    }

    @Override // com.facebook.datasource.DataSource
    @Nullable
    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    @Override // com.facebook.datasource.DataSource
    public synchronized float getProgress() {
        return this.mProgress;
    }

    @Override // com.facebook.datasource.DataSource
    public boolean close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return false;
            }
            this.mIsClosed = true;
            T t = this.mResult;
            this.mResult = null;
            if (t != null) {
                closeResult(t);
            }
            if (!isFinished()) {
                notifyDataSubscribers();
            }
            synchronized (this) {
                this.mSubscribers.clear();
            }
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    @Override // com.facebook.datasource.DataSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void subscribe(com.facebook.datasource.DataSubscriber<T> r3, java.util.concurrent.Executor r4) {
        /*
            r2 = this;
            com.facebook.common.internal.Preconditions.checkNotNull(r3)
            com.facebook.common.internal.Preconditions.checkNotNull(r4)
            monitor-enter(r2)
            boolean r0 = r2.mIsClosed     // Catch: java.lang.Throwable -> L41
            if (r0 == 0) goto Ld
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L41
            return
        Ld:
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = r2.mDataSourceStatus     // Catch: java.lang.Throwable -> L41
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch: java.lang.Throwable -> L41
            if (r0 != r1) goto L1c
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<com.facebook.datasource.DataSubscriber<T>, java.util.concurrent.Executor>> r0 = r2.mSubscribers     // Catch: java.lang.Throwable -> L41
            android.util.Pair r1 = android.util.Pair.create(r3, r4)     // Catch: java.lang.Throwable -> L41
            r0.add(r1)     // Catch: java.lang.Throwable -> L41
        L1c:
            boolean r0 = r2.hasResult()     // Catch: java.lang.Throwable -> L41
            if (r0 != 0) goto L31
            boolean r0 = r2.isFinished()     // Catch: java.lang.Throwable -> L41
            if (r0 != 0) goto L31
            boolean r0 = r2.wasCancelled()     // Catch: java.lang.Throwable -> L41
            if (r0 == 0) goto L2f
            goto L31
        L2f:
            r0 = 0
            goto L32
        L31:
            r0 = 1
        L32:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L41
            if (r0 == 0) goto L40
            boolean r0 = r2.hasFailed()
            boolean r1 = r2.wasCancelled()
            r2.notifyDataSubscriber(r3, r4, r0, r1)
        L40:
            return
        L41:
            r3 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L41
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.subscribe(com.facebook.datasource.DataSubscriber, java.util.concurrent.Executor):void");
    }

    private void notifyDataSubscribers() {
        boolean hasFailed = hasFailed();
        boolean wasCancelled = wasCancelled();
        Iterator<Pair<DataSubscriber<T>, Executor>> it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair<DataSubscriber<T>, Executor> next = it.next();
            notifyDataSubscriber((DataSubscriber) next.first, (Executor) next.second, hasFailed, wasCancelled);
        }
    }

    protected void notifyDataSubscriber(final DataSubscriber<T> dataSubscriber, Executor executor, final boolean z, final boolean z2) {
        Runnable runnable = new Runnable() { // from class: com.facebook.datasource.AbstractDataSource.1
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    dataSubscriber.onFailure(AbstractDataSource.this);
                } else if (z2) {
                    dataSubscriber.onCancellation(AbstractDataSource.this);
                } else {
                    dataSubscriber.onNewResult(AbstractDataSource.this);
                }
            }
        };
        DataSourceInstrumenter dataSourceInstrumenter = getDataSourceInstrumenter();
        if (dataSourceInstrumenter != null) {
            runnable = dataSourceInstrumenter.decorateRunnable(runnable, "AbstractDataSource_notifyDataSubscriber");
        }
        executor.execute(runnable);
    }

    private synchronized boolean wasCancelled() {
        boolean z;
        if (isClosed()) {
            z = isFinished() ? false : true;
        }
        return z;
    }

    protected boolean setResult(@Nullable T t, boolean z, @Nullable Map<String, Object> map) {
        setExtras(map);
        boolean resultInternal = setResultInternal(t, z);
        if (resultInternal) {
            notifyDataSubscribers();
        }
        return resultInternal;
    }

    public boolean setResult(@Nullable T t, boolean z) {
        return setResult(t, z, null);
    }

    protected boolean setFailure(Throwable th) {
        return setFailure(th, null);
    }

    protected boolean setFailure(@Nullable Throwable th, @Nullable Map<String, Object> map) {
        boolean failureInternal = setFailureInternal(th, map);
        if (failureInternal) {
            notifyDataSubscribers();
        }
        return failureInternal;
    }

    protected boolean setProgress(float f) {
        boolean progressInternal = setProgressInternal(f);
        if (progressInternal) {
            notifyProgressUpdate();
        }
        return progressInternal;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0029, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:?, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x002c, code lost:
    
        if (r4 == null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x002e, code lost:
    
        closeResult(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0031, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:?, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean setResultInternal(@javax.annotation.Nullable T r4, boolean r5) {
        /*
            r3 = this;
            r0 = 0
            monitor-enter(r3)     // Catch: java.lang.Throwable -> L3a
            boolean r1 = r3.mIsClosed     // Catch: java.lang.Throwable -> L37
            if (r1 != 0) goto L2b
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = r3.mDataSourceStatus     // Catch: java.lang.Throwable -> L37
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r2 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch: java.lang.Throwable -> L37
            if (r1 == r2) goto Ld
            goto L2b
        Ld:
            if (r5 == 0) goto L17
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r5 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.SUCCESS     // Catch: java.lang.Throwable -> L37
            r3.mDataSourceStatus = r5     // Catch: java.lang.Throwable -> L37
            r5 = 1065353216(0x3f800000, float:1.0)
            r3.mProgress = r5     // Catch: java.lang.Throwable -> L37
        L17:
            T r5 = r3.mResult     // Catch: java.lang.Throwable -> L37
            if (r5 == r4) goto L22
            r3.mResult = r4     // Catch: java.lang.Throwable -> L1f
            r4 = r5
            goto L23
        L1f:
            r4 = move-exception
            r0 = r5
            goto L38
        L22:
            r4 = r0
        L23:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L33
            if (r4 == 0) goto L29
            r3.closeResult(r4)
        L29:
            r4 = 1
            return r4
        L2b:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L33
            if (r4 == 0) goto L31
            r3.closeResult(r4)
        L31:
            r4 = 0
            return r4
        L33:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L38
        L37:
            r4 = move-exception
        L38:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L37
            throw r4     // Catch: java.lang.Throwable -> L3a
        L3a:
            r4 = move-exception
            if (r0 == 0) goto L40
            r3.closeResult(r0)
        L40:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.setResultInternal(java.lang.Object, boolean):boolean");
    }

    private synchronized boolean setFailureInternal(@Nullable Throwable th, @Nullable Map<String, Object> map) {
        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
            this.mDataSourceStatus = DataSourceStatus.FAILURE;
            this.mFailureThrowable = th;
            this.mExtras = map;
            return true;
        }
        return false;
    }

    private synchronized boolean setProgressInternal(float f) {
        if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
            if (f < this.mProgress) {
                return false;
            }
            this.mProgress = f;
            return true;
        }
        return false;
    }

    protected void notifyProgressUpdate() {
        Iterator<Pair<DataSubscriber<T>, Executor>> it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair<DataSubscriber<T>, Executor> next = it.next();
            final DataSubscriber dataSubscriber = (DataSubscriber) next.first;
            ((Executor) next.second).execute(new Runnable() { // from class: com.facebook.datasource.AbstractDataSource.2
                @Override // java.lang.Runnable
                public void run() {
                    dataSubscriber.onProgressUpdate(AbstractDataSource.this);
                }
            });
        }
    }
}

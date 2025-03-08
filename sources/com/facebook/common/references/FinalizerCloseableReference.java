package com.facebook.common.references;

import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class FinalizerCloseableReference<T> extends CloseableReference<T> {
    private static final String TAG = "FinalizerCloseableReference";

    @Override // com.facebook.common.references.CloseableReference
    /* renamed from: clone */
    public CloseableReference<T> mo194clone() {
        return this;
    }

    @Override // com.facebook.common.references.CloseableReference, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    FinalizerCloseableReference(T t, ResourceReleaser<T> resourceReleaser, CloseableReference.LeakHandler leakHandler, @Nullable Throwable th) {
        super(t, resourceReleaser, leakHandler, th, true);
    }

    protected void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                T t = this.mSharedReference.get();
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(System.identityHashCode(this));
                objArr[1] = Integer.valueOf(System.identityHashCode(this.mSharedReference));
                objArr[2] = t == null ? null : t.getClass().getName();
                FLog.w(TAG, "Finalized without closing: %x %x (type = %s)", objArr);
                this.mSharedReference.deleteReference();
            }
        } finally {
            super.finalize();
        }
    }
}

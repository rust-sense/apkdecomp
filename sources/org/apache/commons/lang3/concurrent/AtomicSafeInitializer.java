package org.apache.commons.lang3.concurrent;

import com.facebook.jni.DestructorThread$DestructorStack$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes3.dex */
public abstract class AtomicSafeInitializer<T> implements ConcurrentInitializer<T> {
    private final AtomicReference<AtomicSafeInitializer<T>> factory = new AtomicReference<>();
    private final AtomicReference<T> reference = new AtomicReference<>();

    protected abstract T initialize() throws ConcurrentException;

    @Override // org.apache.commons.lang3.concurrent.ConcurrentInitializer
    public final T get() throws ConcurrentException {
        while (true) {
            T t = this.reference.get();
            if (t != null) {
                return t;
            }
            if (DestructorThread$DestructorStack$$ExternalSyntheticBackportWithForwarding0.m(this.factory, null, this)) {
                this.reference.set(initialize());
            }
        }
    }
}

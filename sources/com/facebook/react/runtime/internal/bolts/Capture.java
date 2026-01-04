package com.facebook.react.runtime.internal.bolts;

/* loaded from: classes.dex */
class Capture<T> {
    private T value;

    public T get() {
        return this.value;
    }

    public void set(T t) {
        this.value = t;
    }

    public Capture() {
    }

    public Capture(T t) {
        this.value = t;
    }
}

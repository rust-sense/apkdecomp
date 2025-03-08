package kotlin.reflect.jvm.internal.impl.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: ArrayMap.kt */
/* loaded from: classes3.dex */
public final class OneElementArrayMap$iterator$1<T> implements Iterator<T>, KMappedMarker {
    private boolean notVisited = true;
    final /* synthetic */ OneElementArrayMap<T> this$0;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.notVisited;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    OneElementArrayMap$iterator$1(OneElementArrayMap<T> oneElementArrayMap) {
        this.this$0 = oneElementArrayMap;
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.notVisited) {
            this.notVisited = false;
            return this.this$0.getValue();
        }
        throw new NoSuchElementException();
    }
}

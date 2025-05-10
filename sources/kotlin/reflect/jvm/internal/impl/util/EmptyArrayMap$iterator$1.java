package kotlin.reflect.jvm.internal.impl.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: ArrayMap.kt */
/* loaded from: classes3.dex */
public final class EmptyArrayMap$iterator$1 implements Iterator, KMappedMarker {
    @Override // java.util.Iterator
    public boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    EmptyArrayMap$iterator$1() {
    }

    @Override // java.util.Iterator
    public Void next() {
        throw new NoSuchElementException();
    }
}

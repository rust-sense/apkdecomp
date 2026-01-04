package expo.modules.kotlin;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: FilteredIterator.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0002J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\u000e\u0010\u0007\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\rR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u000e"}, d2 = {"Lexpo/modules/kotlin/FilteredIterator;", ExifInterface.LONGITUDE_EAST, "", "iterator", "filter", "Lexpo/modules/kotlin/Filter;", "(Ljava/util/Iterator;Lexpo/modules/kotlin/Filter;)V", "next", "Ljava/lang/Object;", "findNext", "", "hasNext", "", "()Ljava/lang/Object;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FilteredIterator<E> implements Iterator<E>, KMappedMarker {
    private final Filter<E> filter;
    private final Iterator<E> iterator;
    private E next;

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.next != null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FilteredIterator(Iterator<? extends E> iterator, Filter<E> filter) {
        Intrinsics.checkNotNullParameter(iterator, "iterator");
        Intrinsics.checkNotNullParameter(filter, "filter");
        this.iterator = iterator;
        this.filter = filter;
        findNext();
    }

    @Override // java.util.Iterator
    public E next() {
        E e = this.next;
        Intrinsics.checkNotNull(e);
        findNext();
        return e;
    }

    private final void findNext() {
        while (this.iterator.hasNext()) {
            E next = this.iterator.next();
            this.next = next;
            if (this.filter.apply(next)) {
                return;
            }
        }
        this.next = null;
    }
}

package expo.modules.kotlin.views;

import com.facebook.react.bridge.ReadableMapKeySetIterator;
import expo.modules.kotlin.Filter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FilteredReadableMap.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u0005H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lexpo/modules/kotlin/views/FilteredReadableMapKeySetIterator;", "Lcom/facebook/react/bridge/ReadableMapKeySetIterator;", "iterator", "filter", "Lexpo/modules/kotlin/Filter;", "", "(Lcom/facebook/react/bridge/ReadableMapKeySetIterator;Lexpo/modules/kotlin/Filter;)V", "next", "findNext", "", "hasNextKey", "", "nextKey", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FilteredReadableMapKeySetIterator implements ReadableMapKeySetIterator {
    private final Filter<String> filter;
    private final ReadableMapKeySetIterator iterator;
    private String next;

    @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
    public boolean hasNextKey() {
        return this.next != null;
    }

    public FilteredReadableMapKeySetIterator(ReadableMapKeySetIterator iterator, Filter<String> filter) {
        Intrinsics.checkNotNullParameter(iterator, "iterator");
        Intrinsics.checkNotNullParameter(filter, "filter");
        this.iterator = iterator;
        this.filter = filter;
        findNext();
    }

    @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
    public String nextKey() {
        String str = this.next;
        Intrinsics.checkNotNull(str);
        findNext();
        return str;
    }

    private final void findNext() {
        while (this.iterator.hasNextKey()) {
            String nextKey = this.iterator.nextKey();
            this.next = nextKey;
            Filter<String> filter = this.filter;
            Intrinsics.checkNotNull(nextKey);
            if (filter.apply(nextKey)) {
                return;
            }
        }
        this.next = null;
    }
}

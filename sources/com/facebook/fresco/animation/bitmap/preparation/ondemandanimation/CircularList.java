package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.firebase.messaging.Constants;
import io.sentry.SentryEnvelopeItemHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: CircularList.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003J\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/CircularList;", "", "size", "", "(I)V", "getSize", "()I", "getPosition", TouchesHelper.TARGET_KEY, "isTargetAhead", "", Constants.MessagePayloadKeys.FROM, SentryEnvelopeItemHeader.JsonKeys.LENGTH, "sublist", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CircularList {
    private final int size;

    public final int getSize() {
        return this.size;
    }

    public CircularList(int i) {
        this.size = i;
    }

    public final boolean isTargetAhead(int from, int target, int length) {
        int position = getPosition(length + from);
        if (from < position) {
            if (from <= target && target <= position) {
                return true;
            }
        } else {
            if (from <= target && target <= this.size) {
                return true;
            }
            if (target >= 0 && target <= position) {
                return true;
            }
        }
        return false;
    }

    public final int getPosition(int target) {
        int i = target % this.size;
        Integer valueOf = Integer.valueOf(i);
        if (valueOf.intValue() < 0) {
            valueOf = null;
        }
        return valueOf != null ? valueOf.intValue() : i + this.size;
    }

    public final List<Integer> sublist(int from, int length) {
        IntRange until = RangesKt.until(0, length);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(getPosition(((IntIterator) it).nextInt() + from)));
        }
        return arrayList;
    }
}

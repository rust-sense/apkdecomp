package com.google.common.collect;

/* loaded from: classes2.dex */
public enum BoundType {
    OPEN(false),
    CLOSED(true);

    final boolean inclusive;

    static BoundType forBoolean(boolean z) {
        return z ? CLOSED : OPEN;
    }

    BoundType(boolean z) {
        this.inclusive = z;
    }

    BoundType flip() {
        return forBoolean(!this.inclusive);
    }
}

package org.apache.commons.lang3.builder;

/* loaded from: classes3.dex */
final class IDKey {
    private final int id;
    private final Object value;

    public int hashCode() {
        return this.id;
    }

    IDKey(Object obj) {
        this.id = System.identityHashCode(obj);
        this.value = obj;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IDKey)) {
            return false;
        }
        IDKey iDKey = (IDKey) obj;
        return this.id == iDKey.id && this.value == iDKey.value;
    }
}

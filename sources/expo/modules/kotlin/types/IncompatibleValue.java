package expo.modules.kotlin.types;

import kotlin.Metadata;

/* compiled from: Either.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lexpo/modules/kotlin/types/IncompatibleValue;", "Lexpo/modules/kotlin/types/DeferredValue;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class IncompatibleValue extends DeferredValue {
    public static final IncompatibleValue INSTANCE = new IncompatibleValue();

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IncompatibleValue)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 1921661369;
    }

    public String toString() {
        return "IncompatibleValue";
    }

    private IncompatibleValue() {
        super(null);
    }
}

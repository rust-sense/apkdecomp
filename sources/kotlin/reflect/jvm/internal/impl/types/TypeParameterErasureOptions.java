package kotlin.reflect.jvm.internal.impl.types;

/* compiled from: TypeParameterErasureOptions.kt */
/* loaded from: classes3.dex */
public final class TypeParameterErasureOptions {
    private final boolean intersectUpperBounds;
    private final boolean leaveNonTypeParameterTypes;

    public final boolean getIntersectUpperBounds() {
        return this.intersectUpperBounds;
    }

    public final boolean getLeaveNonTypeParameterTypes() {
        return this.leaveNonTypeParameterTypes;
    }

    public TypeParameterErasureOptions(boolean z, boolean z2) {
        this.leaveNonTypeParameterTypes = z;
        this.intersectUpperBounds = z2;
    }
}

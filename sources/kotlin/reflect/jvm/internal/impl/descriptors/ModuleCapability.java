package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModuleCapability.kt */
/* loaded from: classes3.dex */
public final class ModuleCapability<T> {
    private final String name;

    public String toString() {
        return this.name;
    }

    public ModuleCapability(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }
}

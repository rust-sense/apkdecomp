package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;

/* compiled from: StdlibClassFinder.kt */
/* loaded from: classes3.dex */
public interface StdlibClassFinder {
    ClassDescriptor findEnumEntriesClass(ModuleDescriptor moduleDescriptor);
}

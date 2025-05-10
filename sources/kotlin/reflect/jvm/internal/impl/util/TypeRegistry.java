package kotlin.reflect.jvm.internal.impl.util;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: ArrayMapOwner.kt */
/* loaded from: classes3.dex */
public abstract class TypeRegistry<K, V> {
    private final ConcurrentHashMap<String, Integer> idPerType = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public abstract int customComputeIfAbsent(ConcurrentHashMap<String, Integer> concurrentHashMap, String str, Function1<? super String, Integer> function1);

    /* JADX WARN: Multi-variable type inference failed */
    public final <T extends V, KK extends K> NullableArrayMapAccessor<K, V, T> generateNullableAccessor(KClass<KK> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        return new NullableArrayMapAccessor<>(getId(kClass));
    }

    public final <T extends K> int getId(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        String qualifiedName = kClass.getQualifiedName();
        Intrinsics.checkNotNull(qualifiedName);
        return getId(qualifiedName);
    }

    public final int getId(String keyQualifiedName) {
        Intrinsics.checkNotNullParameter(keyQualifiedName, "keyQualifiedName");
        return customComputeIfAbsent(this.idPerType, keyQualifiedName, new Function1<String, Integer>(this) { // from class: kotlin.reflect.jvm.internal.impl.util.TypeRegistry$getId$1
            final /* synthetic */ TypeRegistry<K, V> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Integer invoke(String it) {
                AtomicInteger atomicInteger;
                Intrinsics.checkNotNullParameter(it, "it");
                atomicInteger = ((TypeRegistry) this.this$0).idCounter;
                return Integer.valueOf(atomicInteger.getAndIncrement());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Collection<Integer> getIndices() {
        Collection<Integer> values = this.idPerType.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        return values;
    }
}

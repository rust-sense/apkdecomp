package expo.modules.kotlin.allocators;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ObjectConstructorFactory.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007J$\u0010\b\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0002J\"\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0002¨\u0006\n"}, d2 = {"Lexpo/modules/kotlin/allocators/ObjectConstructorFactory;", "", "()V", "get", "Lexpo/modules/kotlin/allocators/ObjectConstructor;", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Ljava/lang/Class;", "tryToUseDefaultConstructor", "useUnsafeAllocator", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ObjectConstructorFactory {
    public final <T> ObjectConstructor<T> get(Class<T> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        ObjectConstructor<T> tryToUseDefaultConstructor = tryToUseDefaultConstructor(clazz);
        return tryToUseDefaultConstructor == null ? useUnsafeAllocator(clazz) : tryToUseDefaultConstructor;
    }

    private final <T> ObjectConstructor<T> tryToUseDefaultConstructor(Class<T> clazz) {
        try {
            final Constructor<T> declaredConstructor = clazz.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new ObjectConstructor() { // from class: expo.modules.kotlin.allocators.ObjectConstructorFactory$$ExternalSyntheticLambda0
                @Override // expo.modules.kotlin.allocators.ObjectConstructor
                public final Object construct() {
                    Object tryToUseDefaultConstructor$lambda$0;
                    tryToUseDefaultConstructor$lambda$0 = ObjectConstructorFactory.tryToUseDefaultConstructor$lambda$0(declaredConstructor);
                    return tryToUseDefaultConstructor$lambda$0;
                }
            };
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object tryToUseDefaultConstructor$lambda$0(Constructor constructor) {
        return constructor.newInstance(new Object[0]);
    }

    private final <T> ObjectConstructor<T> useUnsafeAllocator(Class<T> clazz) {
        final UnsafeAllocator<T> createAllocator = UnsafeAllocator.INSTANCE.createAllocator(clazz);
        return new ObjectConstructor() { // from class: expo.modules.kotlin.allocators.ObjectConstructorFactory$$ExternalSyntheticLambda1
            @Override // expo.modules.kotlin.allocators.ObjectConstructor
            public final Object construct() {
                Object useUnsafeAllocator$lambda$1;
                useUnsafeAllocator$lambda$1 = ObjectConstructorFactory.useUnsafeAllocator$lambda$1(UnsafeAllocator.this);
                return useUnsafeAllocator$lambda$1;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object useUnsafeAllocator$lambda$1(UnsafeAllocator allocator) {
        Intrinsics.checkNotNullParameter(allocator, "$allocator");
        return allocator.newInstance();
    }
}

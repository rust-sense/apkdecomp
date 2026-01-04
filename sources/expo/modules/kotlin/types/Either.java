package expo.modules.kotlin.types;

import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: Either.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B)\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u000b\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\u0015\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J\u001d\u0010\u000e\u001a\u00028\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u000e\u001a\u00028\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0007¢\u0006\u0004\b\u0016\u0010\u0015J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0019J\u001b\u0010\u0017\u001a\u00020\u00182\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0007¢\u0006\u0002\b\u001aJ\u001b\u0010\u0017\u001a\u00020\u00182\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0007¢\u0006\u0002\b\u001bJ\u000b\u0010\u001c\u001a\u00028\u0001¢\u0006\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lexpo/modules/kotlin/types/Either;", "FirstType", "", "SecondType", "bareValue", "deferredValue", "", "Lexpo/modules/kotlin/types/DeferredValue;", "types", "", "Lkotlin/reflect/KType;", "(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;)V", "first", "()Ljava/lang/Object;", "get", "index", "", "get$expo_modules_core_release", "type", "Lkotlin/reflect/KClass;", "getFirstType", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "getSecondType", "is", "", "is$expo_modules_core_release", "isFirstType", "isSecondType", "second", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class Either<FirstType, SecondType> {
    private final Object bareValue;
    private final List<DeferredValue> deferredValue;
    private final List<KType> types;

    /* JADX WARN: Multi-variable type inference failed */
    public Either(Object bareValue, List<DeferredValue> deferredValue, List<? extends KType> types) {
        Intrinsics.checkNotNullParameter(bareValue, "bareValue");
        Intrinsics.checkNotNullParameter(deferredValue, "deferredValue");
        Intrinsics.checkNotNullParameter(types, "types");
        this.bareValue = bareValue;
        this.deferredValue = deferredValue;
        this.types = types;
    }

    public final boolean is$expo_modules_core_release(int index) {
        DeferredValue deferredValue = this.deferredValue.get(index);
        if (deferredValue instanceof ConvertedValue) {
            return true;
        }
        if (!Intrinsics.areEqual(deferredValue, IncompatibleValue.INSTANCE)) {
            if (deferredValue instanceof UnconvertedValue) {
                try {
                    this.deferredValue.set(index, new ConvertedValue(((UnconvertedValue) deferredValue).getConvertedValue()));
                    return true;
                } catch (Throwable unused) {
                    this.deferredValue.set(index, IncompatibleValue.INSTANCE);
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        return false;
    }

    public final Object get$expo_modules_core_release(int index) {
        DeferredValue deferredValue = this.deferredValue.get(index);
        if (deferredValue instanceof ConvertedValue) {
            return ((ConvertedValue) deferredValue).getConvertedValue();
        }
        if (Intrinsics.areEqual(deferredValue, IncompatibleValue.INSTANCE)) {
            throw new TypeCastException("Cannot cast '" + this.bareValue + "' to '" + this.types.get(index) + "'");
        }
        if (!(deferredValue instanceof UnconvertedValue)) {
            throw new NoWhenBranchMatchedException();
        }
        Object convertedValue = ((UnconvertedValue) deferredValue).getConvertedValue();
        this.deferredValue.set(index, new ConvertedValue(convertedValue));
        return convertedValue;
    }

    public final boolean isFirstType(KClass<FirstType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return is$expo_modules_core_release(0);
    }

    public final boolean isSecondType(KClass<SecondType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return is$expo_modules_core_release(1);
    }

    public final FirstType getFirstType(KClass<FirstType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        FirstType firsttype = (FirstType) get$expo_modules_core_release(0);
        Intrinsics.checkNotNull(firsttype, "null cannot be cast to non-null type FirstType of expo.modules.kotlin.types.Either");
        return firsttype;
    }

    public final SecondType getSecondType(KClass<SecondType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        SecondType secondtype = (SecondType) get$expo_modules_core_release(1);
        Intrinsics.checkNotNull(secondtype, "null cannot be cast to non-null type SecondType of expo.modules.kotlin.types.Either");
        return secondtype;
    }

    public final FirstType first() {
        FirstType firsttype = (FirstType) get$expo_modules_core_release(0);
        Intrinsics.checkNotNull(firsttype, "null cannot be cast to non-null type FirstType of expo.modules.kotlin.types.Either");
        return firsttype;
    }

    public final SecondType second() {
        SecondType secondtype = (SecondType) get$expo_modules_core_release(1);
        Intrinsics.checkNotNull(secondtype, "null cannot be cast to non-null type SecondType of expo.modules.kotlin.types.Either");
        return secondtype;
    }
}

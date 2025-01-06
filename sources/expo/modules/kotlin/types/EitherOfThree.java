package expo.modules.kotlin.types;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: Either.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005B)\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00028\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00020\u0010H\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0013\u001a\u00020\u00142\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00020\u0010H\u0007¢\u0006\u0002\b\u0015J\u000b\u0010\u0016\u001a\u00028\u0002¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"Lexpo/modules/kotlin/types/EitherOfThree;", "FirstType", "", "SecondType", "ThirdType", "Lexpo/modules/kotlin/types/Either;", "bareValue", "deferredValue", "", "Lexpo/modules/kotlin/types/DeferredValue;", "types", "", "Lkotlin/reflect/KType;", "(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;)V", "get", "type", "Lkotlin/reflect/KClass;", "getThirdType", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "is", "", "isThirdType", "third", "()Ljava/lang/Object;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class EitherOfThree<FirstType, SecondType, ThirdType> extends Either<FirstType, SecondType> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EitherOfThree(Object bareValue, List<DeferredValue> deferredValue, List<? extends KType> types) {
        super(bareValue, deferredValue, types);
        Intrinsics.checkNotNullParameter(bareValue, "bareValue");
        Intrinsics.checkNotNullParameter(deferredValue, "deferredValue");
        Intrinsics.checkNotNullParameter(types, "types");
    }

    public final boolean isThirdType(KClass<ThirdType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return is$expo_modules_core_release(2);
    }

    public final ThirdType getThirdType(KClass<ThirdType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        ThirdType thirdtype = (ThirdType) get$expo_modules_core_release(3);
        Intrinsics.checkNotNull(thirdtype, "null cannot be cast to non-null type ThirdType of expo.modules.kotlin.types.EitherOfThree");
        return thirdtype;
    }

    public final ThirdType third() {
        ThirdType thirdtype = (ThirdType) get$expo_modules_core_release(3);
        Intrinsics.checkNotNull(thirdtype, "null cannot be cast to non-null type ThirdType of expo.modules.kotlin.types.EitherOfThree");
        return thirdtype;
    }
}

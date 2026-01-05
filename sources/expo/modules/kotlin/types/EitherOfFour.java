package expo.modules.kotlin.types;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

/* compiled from: Either.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u0002*\b\b\u0003\u0010\u0005*\u00020\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0006B)\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u000f\u001a\u00028\u0003¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00028\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00030\u0013H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0016\u001a\u00020\u00172\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00030\u0013H\u0007¢\u0006\u0002\b\u0018¨\u0006\u0019"}, d2 = {"Lexpo/modules/kotlin/types/EitherOfFour;", "FirstType", "", "SecondType", "ThirdType", "FourthType", "Lexpo/modules/kotlin/types/EitherOfThree;", "bareValue", "deferredValue", "", "Lexpo/modules/kotlin/types/DeferredValue;", "types", "", "Lkotlin/reflect/KType;", "(Ljava/lang/Object;Ljava/util/List;Ljava/util/List;)V", "fourth", "()Ljava/lang/Object;", "get", "type", "Lkotlin/reflect/KClass;", "getFourthType", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "is", "", "isFourthType", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EitherOfFour<FirstType, SecondType, ThirdType, FourthType> extends EitherOfThree<FirstType, SecondType, ThirdType> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EitherOfFour(Object bareValue, List<DeferredValue> deferredValue, List<? extends KType> types) {
        super(bareValue, deferredValue, types);
        Intrinsics.checkNotNullParameter(bareValue, "bareValue");
        Intrinsics.checkNotNullParameter(deferredValue, "deferredValue");
        Intrinsics.checkNotNullParameter(types, "types");
    }

    public final boolean isFourthType(KClass<FourthType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return is$expo_modules_core_release(3);
    }

    public final FourthType getFourthType(KClass<FourthType> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        FourthType fourthtype = (FourthType) get$expo_modules_core_release(3);
        Intrinsics.checkNotNull(fourthtype, "null cannot be cast to non-null type FourthType of expo.modules.kotlin.types.EitherOfFour");
        return fourthtype;
    }

    public final FourthType fourth() {
        FourthType fourthtype = (FourthType) get$expo_modules_core_release(3);
        Intrinsics.checkNotNull(fourthtype, "null cannot be cast to non-null type FourthType of expo.modules.kotlin.types.EitherOfFour");
        return fourthtype;
    }
}

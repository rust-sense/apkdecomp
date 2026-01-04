package expo.modules.kotlin.types;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.SingleType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

/* compiled from: EitherTypeConverter.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002\u001aL\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u001c\u0010\u000e\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u000f0\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\rH\u0002\u001a(\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002¨\u0006\u0013"}, d2 = {"createDeferredValue", "Lexpo/modules/kotlin/types/DeferredValue;", "value", "", "wasConverted", "", "typeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "expectedType", "Lexpo/modules/kotlin/jni/ExpectedType;", "context", "Lexpo/modules/kotlin/AppContext;", "createDeferredValues", "", "list", "Lkotlin/Pair;", "typeList", "Lkotlin/reflect/KType;", "tryToConvert", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EitherTypeConverterKt {
    private static final DeferredValue createDeferredValue(Object obj, boolean z, TypeConverter<?> typeConverter, ExpectedType expectedType, AppContext appContext) {
        for (SingleType singleType : expectedType.getInnerPossibleTypes()) {
            if (singleType.getExpectedCppType().getClazz().isInstance(obj)) {
                if (z) {
                    return new UnconvertedValue(obj, typeConverter, appContext);
                }
                Object tryToConvert = tryToConvert(typeConverter, obj, appContext);
                if (tryToConvert != null) {
                    return new ConvertedValue(tryToConvert);
                }
            }
        }
        return IncompatibleValue.INSTANCE;
    }

    private static final Object tryToConvert(TypeConverter<?> typeConverter, Object obj, AppContext appContext) {
        try {
            return typeConverter.isTrivial() ? obj : typeConverter.convert(obj, appContext);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<DeferredValue> createDeferredValues(Object obj, AppContext appContext, List<? extends Pair<ExpectedType, ? extends TypeConverter<?>>> list, List<? extends KType> list2) {
        List<? extends Pair<ExpectedType, ? extends TypeConverter<?>>> list3 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        Iterator<T> it = list3.iterator();
        boolean z = false;
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            DeferredValue createDeferredValue = createDeferredValue(obj, z, (TypeConverter) pair.component2(), (ExpectedType) pair.component1(), appContext);
            if (createDeferredValue instanceof ConvertedValue) {
                z = true;
            }
            arrayList.add(createDeferredValue);
        }
        ArrayList arrayList2 = arrayList;
        if (z) {
            return arrayList2;
        }
        throw new TypeCastException("Cannot cast '" + obj + "' to 'Either<" + CollectionsKt.joinToString$default(list2, ", ", null, null, 0, null, new Function1<KType, CharSequence>() { // from class: expo.modules.kotlin.types.EitherTypeConverterKt$createDeferredValues$1
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(KType it2) {
                Intrinsics.checkNotNullParameter(it2, "it");
                return it2.toString();
            }
        }, 30, null) + ">'");
    }
}

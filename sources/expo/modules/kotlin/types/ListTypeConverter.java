package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.CollectionElementCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: ListTypeConverter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0014\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\u0014\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lexpo/modules/kotlin/types/ListTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "listType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "elementConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "convertFromAny", "value", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "convertFromReadableArray", "jsArray", "Lcom/facebook/react/bridge/ReadableArray;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ListTypeConverter extends DynamicAwareTypeConverters<List<?>> {
    private final TypeConverter<?> elementConverter;
    private final KType listType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListTypeConverter(TypeConverterProvider converterProvider, KType listType) {
        super(listType.getIsMarkedNullable());
        Intrinsics.checkNotNullParameter(converterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(listType, "listType");
        this.listType = listType;
        KType type = ((KTypeProjection) CollectionsKt.first((List) listType.getArguments())).getType();
        if (type != null) {
            this.elementConverter = converterProvider.obtainTypeConverter(type);
            return;
        }
        throw new IllegalArgumentException("The list type should contain the type of elements.".toString());
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public List<?> convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ReadableArray asArray = value.asArray();
        Intrinsics.checkNotNull(asArray);
        return convertFromReadableArray(asArray);
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public List<?> convertFromAny(Object value) {
        UnexpectedException unexpectedException;
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.elementConverter.isTrivial()) {
            return (List) value;
        }
        List list = (List) value;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Object obj : list) {
            try {
                arrayList.add(TypeConverter.convert$default(this.elementConverter, obj, null, 2, null));
            } catch (Throwable th) {
                if (th instanceof CodedException) {
                    unexpectedException = (CodedException) th;
                } else if (th instanceof expo.modules.core.errors.CodedException) {
                    String code = ((expo.modules.core.errors.CodedException) th).getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                    unexpectedException = new CodedException(code, th.getMessage(), th.getCause());
                } else {
                    unexpectedException = new UnexpectedException(th);
                }
                KType kType = this.listType;
                KType type = ((KTypeProjection) CollectionsKt.first((List) kType.getArguments())).getType();
                Intrinsics.checkNotNull(type);
                Intrinsics.checkNotNull(obj);
                throw new CollectionElementCastException(kType, type, (KClass<?>) Reflection.getOrCreateKotlinClass(obj.getClass()), unexpectedException);
            }
        }
        return arrayList;
    }

    private final List<?> convertFromReadableArray(ReadableArray jsArray) {
        int size = jsArray.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Dynamic dynamic = jsArray.getDynamic(i);
            Intrinsics.checkNotNullExpressionValue(dynamic, "getDynamic(...)");
            try {
                Object convert$default = TypeConverter.convert$default(this.elementConverter, dynamic, null, 2, null);
                dynamic.recycle();
                arrayList.add(convert$default);
            } finally {
            }
        }
        return arrayList;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    /* renamed from: getCppRequiredTypes */
    public ExpectedType get$cppRequireType() {
        return ExpectedType.INSTANCE.forList(this.elementConverter.get$cppRequireType());
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return this.elementConverter.isTrivial();
    }
}

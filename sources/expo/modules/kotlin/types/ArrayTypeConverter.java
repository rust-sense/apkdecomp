package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.CollectionElementCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: ArrayTypeConverter.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0019\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lexpo/modules/kotlin/types/ArrayTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "arrayType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "arrayElementConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "convertFromAny", "value", "", "(Ljava/lang/Object;)[Ljava/lang/Object;", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "(Lcom/facebook/react/bridge/Dynamic;)[Ljava/lang/Object;", "createTypedArray", "size", "", "(I)[Ljava/lang/Object;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ArrayTypeConverter extends DynamicAwareTypeConverters<Object[]> {
    private final TypeConverter<?> arrayElementConverter;
    private final KType arrayType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArrayTypeConverter(TypeConverterProvider converterProvider, KType arrayType) {
        super(arrayType.getIsMarkedNullable());
        Intrinsics.checkNotNullParameter(converterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(arrayType, "arrayType");
        this.arrayType = arrayType;
        KType type = ((KTypeProjection) CollectionsKt.first((List) arrayType.getArguments())).getType();
        if (type != null) {
            this.arrayElementConverter = converterProvider.obtainTypeConverter(type);
            return;
        }
        throw new IllegalArgumentException("The array type should contain the type of the elements.".toString());
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Object[] convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ReadableArray asArray = value.asArray();
        Object[] createTypedArray = createTypedArray(asArray.size());
        int size = asArray.size();
        for (int i = 0; i < size; i++) {
            Dynamic dynamic = asArray.getDynamic(i);
            Intrinsics.checkNotNullExpressionValue(dynamic, "getDynamic(...)");
            try {
                Object convert$default = TypeConverter.convert$default(this.arrayElementConverter, dynamic, null, 2, null);
                dynamic.recycle();
                createTypedArray[i] = convert$default;
            } finally {
            }
        }
        return createTypedArray;
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Object[] convertFromAny(Object value) {
        UnexpectedException unexpectedException;
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.arrayElementConverter.isTrivial()) {
            return (Object[]) value;
        }
        Object[] objArr = (Object[]) value;
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            try {
                arrayList.add(TypeConverter.convert$default(this.arrayElementConverter, obj, null, 2, null));
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
                KType kType = this.arrayType;
                KType type = ((KTypeProjection) CollectionsKt.first((List) kType.getArguments())).getType();
                Intrinsics.checkNotNull(type);
                Intrinsics.checkNotNull(obj);
                throw new CollectionElementCastException(kType, type, (KClass<?>) Reflection.getOrCreateKotlinClass(obj.getClass()), unexpectedException);
            }
        }
        return arrayList.toArray(new Object[0]);
    }

    private final Object[] createTypedArray(int size) {
        KType type = ((KTypeProjection) CollectionsKt.first((List) this.arrayType.getArguments())).getType();
        Intrinsics.checkNotNull(type);
        KClassifier classifier = type.getClassifier();
        Intrinsics.checkNotNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
        Object newInstance = Array.newInstance((Class<?>) JvmClassMappingKt.getJavaClass((KClass) classifier), size);
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        return (Object[]) newInstance;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    /* renamed from: getCppRequiredTypes */
    public ExpectedType get$cppRequireType() {
        return ExpectedType.INSTANCE.forPrimitiveArray(this.arrayElementConverter.get$cppRequireType());
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return this.arrayElementConverter.isTrivial();
    }
}

package expo.modules.kotlin.records;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import expo.modules.kotlin.allocators.ObjectConstructor;
import expo.modules.kotlin.allocators.ObjectConstructorFactory;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.FieldCastException;
import expo.modules.kotlin.exception.FieldRequiredException;
import expo.modules.kotlin.exception.RecordCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.records.RecordTypeConverter;
import expo.modules.kotlin.types.DynamicAwareTypeConverters;
import expo.modules.kotlin.types.TypeConverter;
import expo.modules.kotlin.types.TypeConverterProvider;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.text.StringsKt;

/* compiled from: RecordTypeConverter.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001,B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u0018J\u0015\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u001aH\u0016¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001c\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020!H\u0016J\"\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00010#\"\u0004\b\u0001\u0010\u00012\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00010%H\u0002J&\u0010&\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030(0'2\u0012\u0010)\u001a\u000e\u0012\u0006\b\u0001\u0012\u00020\u000e\u0012\u0002\b\u00030\rH\u0002J\b\u0010*\u001a\u00020+H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010\u000b\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u000e\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u000f0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006-"}, d2 = {"Lexpo/modules/kotlin/records/RecordTypeConverter;", ExifInterface.GPS_DIRECTION_TRUE, "Lexpo/modules/kotlin/records/Record;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "type", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "objectConstructorFactory", "Lexpo/modules/kotlin/allocators/ObjectConstructorFactory;", "propertyDescriptors", "", "Lkotlin/reflect/KProperty1;", "", "Lexpo/modules/kotlin/records/RecordTypeConverter$PropertyDescriptor;", "getPropertyDescriptors", "()Ljava/util/Map;", "propertyDescriptors$delegate", "Lkotlin/Lazy;", "getType", "()Lkotlin/reflect/KType;", "convertFromAny", "value", "(Ljava/lang/Object;)Lexpo/modules/kotlin/records/Record;", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "(Lcom/facebook/react/bridge/Dynamic;)Lexpo/modules/kotlin/records/Record;", "convertFromReadableMap", "jsMap", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)Lexpo/modules/kotlin/records/Record;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "getObjectConstructor", "Lexpo/modules/kotlin/allocators/ObjectConstructor;", "clazz", "Ljava/lang/Class;", "getValidators", "", "Lexpo/modules/kotlin/records/FieldValidator;", "property", "isTrivial", "", "PropertyDescriptor", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RecordTypeConverter<T extends Record> extends DynamicAwareTypeConverters<T> {
    private final TypeConverterProvider converterProvider;
    private final ObjectConstructorFactory objectConstructorFactory;

    /* renamed from: propertyDescriptors$delegate, reason: from kotlin metadata */
    private final Lazy propertyDescriptors;
    private final KType type;

    public final KType getType() {
        return this.type;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecordTypeConverter(TypeConverterProvider converterProvider, KType type) {
        super(type.getIsMarkedNullable());
        Intrinsics.checkNotNullParameter(converterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(type, "type");
        this.converterProvider = converterProvider;
        this.type = type;
        this.objectConstructorFactory = new ObjectConstructorFactory();
        this.propertyDescriptors = LazyKt.lazy(new Function0<Map<KProperty1<? extends Object, ?>, ? extends PropertyDescriptor>>(this) { // from class: expo.modules.kotlin.records.RecordTypeConverter$propertyDescriptors$2
            final /* synthetic */ RecordTypeConverter<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Map<KProperty1<? extends Object, ?>, ? extends RecordTypeConverter.PropertyDescriptor> invoke() {
                Object obj;
                Object obj2;
                TypeConverterProvider typeConverterProvider;
                List validators;
                KClassifier classifier = this.this$0.getType().getClassifier();
                Intrinsics.checkNotNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
                Collection<KProperty1> memberProperties = KClasses.getMemberProperties((KClass) classifier);
                RecordTypeConverter<T> recordTypeConverter = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(memberProperties, 10));
                for (KProperty1 kProperty1 : memberProperties) {
                    KProperty1 kProperty12 = kProperty1;
                    Iterator<T> it = kProperty12.getAnnotations().iterator();
                    while (true) {
                        obj = null;
                        if (!it.hasNext()) {
                            obj2 = null;
                            break;
                        }
                        obj2 = it.next();
                        if (((Annotation) obj2) instanceof Field) {
                            break;
                        }
                    }
                    Field field = (Field) obj2;
                    if (field != null) {
                        typeConverterProvider = ((RecordTypeConverter) recordTypeConverter).converterProvider;
                        TypeConverter<?> obtainTypeConverter = typeConverterProvider.obtainTypeConverter(kProperty1.getReturnType());
                        Iterator<T> it2 = kProperty12.getAnnotations().iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            Object next = it2.next();
                            if (((Annotation) next) instanceof Required) {
                                obj = next;
                                break;
                            }
                        }
                        boolean z = ((Required) obj) != null;
                        validators = recordTypeConverter.getValidators(kProperty1);
                        obj = TuplesKt.to(kProperty1, new RecordTypeConverter.PropertyDescriptor(obtainTypeConverter, field, z, validators));
                    }
                    arrayList.add(obj);
                }
                return MapsKt.toMap(CollectionsKt.filterNotNull(arrayList));
            }
        });
    }

    private final Map<KProperty1<? extends Object, ?>, PropertyDescriptor> getPropertyDescriptors() {
        return (Map) this.propertyDescriptors.getValue();
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public T convertFromDynamic(Dynamic value) {
        UnexpectedException unexpectedException;
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            ReadableMap asMap = value.asMap();
            Intrinsics.checkNotNull(asMap);
            return convertFromReadableMap(asMap);
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
            throw new RecordCastException(this.type, unexpectedException);
        }
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public T convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof ReadableMap) {
            return convertFromReadableMap((ReadableMap) value);
        }
        return (T) value;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    /* renamed from: getCppRequiredTypes */
    public ExpectedType get$cppRequireType() {
        return new ExpectedType(CppType.READABLE_MAP);
    }

    private final T convertFromReadableMap(ReadableMap jsMap) {
        FieldCastException fieldCastException;
        KClassifier classifier = this.type.getClassifier();
        Intrinsics.checkNotNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
        T construct = getObjectConstructor(JvmClassMappingKt.getJavaClass((KClass) classifier)).construct();
        for (Map.Entry<KProperty1<? extends Object, ?>, PropertyDescriptor> entry : getPropertyDescriptors().entrySet()) {
            KProperty1<? extends Object, ?> key = entry.getKey();
            PropertyDescriptor value = entry.getValue();
            String key2 = value.getFieldAnnotation().key();
            if (StringsKt.isBlank(key2)) {
                key2 = null;
            }
            if (key2 == null) {
                key2 = key.getName();
            }
            if (!jsMap.hasKey(key2)) {
                if (value.isRequired()) {
                    throw new FieldRequiredException(key);
                }
            } else {
                Dynamic dynamic = jsMap.getDynamic(key2);
                Intrinsics.checkNotNullExpressionValue(dynamic, "getDynamic(...)");
                try {
                    java.lang.reflect.Field javaField = ReflectJvmMapping.getJavaField(key);
                    Intrinsics.checkNotNull(javaField);
                    try {
                        Object convert$default = TypeConverter.convert$default(value.getTypeConverter(), dynamic, null, 2, null);
                        if (convert$default != null) {
                            Iterator<T> it = value.getValidators().iterator();
                            while (it.hasNext()) {
                                FieldValidator fieldValidator = (FieldValidator) it.next();
                                Intrinsics.checkNotNull(fieldValidator, "null cannot be cast to non-null type expo.modules.kotlin.records.FieldValidator<kotlin.Any>");
                                fieldValidator.validate(convert$default);
                            }
                        }
                        javaField.setAccessible(true);
                        javaField.set(construct, convert$default);
                        Unit unit = Unit.INSTANCE;
                    } finally {
                    }
                } finally {
                    dynamic.recycle();
                }
            }
        }
        Intrinsics.checkNotNull(construct, "null cannot be cast to non-null type T of expo.modules.kotlin.records.RecordTypeConverter");
        return construct;
    }

    private final <T> ObjectConstructor<T> getObjectConstructor(Class<T> clazz) {
        return this.objectConstructorFactory.get(clazz);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<FieldValidator<?>> getValidators(KProperty1<? extends Object, ?> property) {
        Pair pair;
        Object obj;
        List<Annotation> annotations = property.getAnnotations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
        for (Annotation annotation : annotations) {
            Iterator<T> it = JvmClassMappingKt.getAnnotationClass(annotation).getAnnotations().iterator();
            while (true) {
                pair = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((Annotation) obj) instanceof BindUsing) {
                    break;
                }
            }
            BindUsing bindUsing = (BindUsing) obj;
            if (bindUsing != null) {
                pair = TuplesKt.to(annotation, bindUsing);
            }
            arrayList.add(pair);
        }
        List<Pair> filterNotNull = CollectionsKt.filterNotNull(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(filterNotNull, 10));
        for (Pair pair2 : filterNotNull) {
            Annotation annotation2 = (Annotation) pair2.component1();
            Object createInstance = KClasses.createInstance(Reflection.getOrCreateKotlinClass(((BindUsing) pair2.component2()).binder()));
            Intrinsics.checkNotNull(createInstance, "null cannot be cast to non-null type expo.modules.kotlin.records.ValidationBinder");
            arrayList2.add(((ValidationBinder) createInstance).bind(annotation2, property.getReturnType()));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RecordTypeConverter.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B3\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t¢\u0006\u0002\u0010\u000bJ\r\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u0013\u0010\u0016\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÆ\u0003J?\u0010\u0017\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0012\b\u0002\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lexpo/modules/kotlin/records/RecordTypeConverter$PropertyDescriptor;", "", "typeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "fieldAnnotation", "Lexpo/modules/kotlin/records/Field;", "isRequired", "", "validators", "", "Lexpo/modules/kotlin/records/FieldValidator;", "(Lexpo/modules/kotlin/types/TypeConverter;Lexpo/modules/kotlin/records/Field;ZLjava/util/List;)V", "getFieldAnnotation", "()Lexpo/modules/kotlin/records/Field;", "()Z", "getTypeConverter", "()Lexpo/modules/kotlin/types/TypeConverter;", "getValidators", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final /* data */ class PropertyDescriptor {
        private final Field fieldAnnotation;
        private final boolean isRequired;
        private final TypeConverter<?> typeConverter;
        private final List<FieldValidator<?>> validators;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PropertyDescriptor copy$default(PropertyDescriptor propertyDescriptor, TypeConverter typeConverter, Field field, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                typeConverter = propertyDescriptor.typeConverter;
            }
            if ((i & 2) != 0) {
                field = propertyDescriptor.fieldAnnotation;
            }
            if ((i & 4) != 0) {
                z = propertyDescriptor.isRequired;
            }
            if ((i & 8) != 0) {
                list = propertyDescriptor.validators;
            }
            return propertyDescriptor.copy(typeConverter, field, z, list);
        }

        public final TypeConverter<?> component1() {
            return this.typeConverter;
        }

        /* renamed from: component2, reason: from getter */
        public final Field getFieldAnnotation() {
            return this.fieldAnnotation;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getIsRequired() {
            return this.isRequired;
        }

        public final List<FieldValidator<?>> component4() {
            return this.validators;
        }

        public final PropertyDescriptor copy(TypeConverter<?> typeConverter, Field fieldAnnotation, boolean isRequired, List<? extends FieldValidator<?>> validators) {
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(fieldAnnotation, "fieldAnnotation");
            Intrinsics.checkNotNullParameter(validators, "validators");
            return new PropertyDescriptor(typeConverter, fieldAnnotation, isRequired, validators);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PropertyDescriptor)) {
                return false;
            }
            PropertyDescriptor propertyDescriptor = (PropertyDescriptor) other;
            return Intrinsics.areEqual(this.typeConverter, propertyDescriptor.typeConverter) && Intrinsics.areEqual(this.fieldAnnotation, propertyDescriptor.fieldAnnotation) && this.isRequired == propertyDescriptor.isRequired && Intrinsics.areEqual(this.validators, propertyDescriptor.validators);
        }

        public final Field getFieldAnnotation() {
            return this.fieldAnnotation;
        }

        public final TypeConverter<?> getTypeConverter() {
            return this.typeConverter;
        }

        public final List<FieldValidator<?>> getValidators() {
            return this.validators;
        }

        public int hashCode() {
            return (((((this.typeConverter.hashCode() * 31) + this.fieldAnnotation.hashCode()) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.isRequired)) * 31) + this.validators.hashCode();
        }

        public final boolean isRequired() {
            return this.isRequired;
        }

        public String toString() {
            return "PropertyDescriptor(typeConverter=" + this.typeConverter + ", fieldAnnotation=" + this.fieldAnnotation + ", isRequired=" + this.isRequired + ", validators=" + this.validators + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public PropertyDescriptor(TypeConverter<?> typeConverter, Field fieldAnnotation, boolean z, List<? extends FieldValidator<?>> validators) {
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(fieldAnnotation, "fieldAnnotation");
            Intrinsics.checkNotNullParameter(validators, "validators");
            this.typeConverter = typeConverter;
            this.fieldAnnotation = fieldAnnotation;
            this.isRequired = z;
            this.validators = validators;
        }
    }
}

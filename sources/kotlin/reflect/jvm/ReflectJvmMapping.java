package kotlin.reflect.jvm;

import androidx.exifinterface.media.ExifInterface;
import io.sentry.protocol.Request;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KPackageImpl;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;

/* compiled from: ReflectJvmMapping.kt */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a$\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0&2\u0006\u0010(\u001a\u00020\u000eH\u0002\u001a$\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0&2\u0006\u0010*\u001a\u00020\tH\u0002\u001a\u000e\u0010+\u001a\u0004\u0018\u00010,*\u00020-H\u0002\"/\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038F¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"\u001b\u0010\b\u001a\u0004\u0018\u00010\t*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0015\u0010\u0018\u001a\u00020\u0019*\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"-\u0010\u001d\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001e*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \"\u001b\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003*\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010!\"\u001b\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n*\u00020\t8F¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006."}, d2 = {"javaConstructor", "Ljava/lang/reflect/Constructor;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/reflect/KFunction;", "getJavaConstructor$annotations", "(Lkotlin/reflect/KFunction;)V", "getJavaConstructor", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Constructor;", "javaField", "Ljava/lang/reflect/Field;", "Lkotlin/reflect/KProperty;", "getJavaField", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Field;", "javaGetter", "Ljava/lang/reflect/Method;", "getJavaGetter", "(Lkotlin/reflect/KProperty;)Ljava/lang/reflect/Method;", "javaMethod", "getJavaMethod", "(Lkotlin/reflect/KFunction;)Ljava/lang/reflect/Method;", "javaSetter", "Lkotlin/reflect/KMutableProperty;", "getJavaSetter", "(Lkotlin/reflect/KMutableProperty;)Ljava/lang/reflect/Method;", "javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "kotlinFunction", "", "getKotlinFunction", "(Ljava/lang/reflect/Constructor;)Lkotlin/reflect/KFunction;", "(Ljava/lang/reflect/Method;)Lkotlin/reflect/KFunction;", "kotlinProperty", "getKotlinProperty", "(Ljava/lang/reflect/Field;)Lkotlin/reflect/KProperty;", "findKFunction", "", "Lkotlin/reflect/KCallable;", Request.JsonKeys.METHOD, "findKProperty", "field", "getKPackage", "Lkotlin/reflect/KDeclarationContainer;", "Ljava/lang/reflect/Member;", "kotlin-reflection"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReflectJvmMapping {

    /* compiled from: ReflectJvmMapping.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KotlinClassHeader.Kind.values().length];
            try {
                iArr[KotlinClassHeader.Kind.FILE_FACADE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KotlinClassHeader.Kind.MULTIFILE_CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KotlinClassHeader.Kind.MULTIFILE_CLASS_PART.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void getJavaConstructor$annotations(KFunction kFunction) {
    }

    public static final Field getJavaField(KProperty<?> kProperty) {
        Intrinsics.checkNotNullParameter(kProperty, "<this>");
        KPropertyImpl<?> asKPropertyImpl = UtilKt.asKPropertyImpl(kProperty);
        if (asKPropertyImpl != null) {
            return asKPropertyImpl.getJavaField();
        }
        return null;
    }

    public static final Method getJavaGetter(KProperty<?> kProperty) {
        Intrinsics.checkNotNullParameter(kProperty, "<this>");
        return getJavaMethod(kProperty.getGetter());
    }

    public static final Method getJavaSetter(KMutableProperty<?> kMutableProperty) {
        Intrinsics.checkNotNullParameter(kMutableProperty, "<this>");
        return getJavaMethod(kMutableProperty.getSetter());
    }

    public static final Method getJavaMethod(KFunction<?> kFunction) {
        Caller<?> caller;
        Intrinsics.checkNotNullParameter(kFunction, "<this>");
        KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(kFunction);
        Object mo2181getMember = (asKCallableImpl == null || (caller = asKCallableImpl.getCaller()) == null) ? null : caller.mo2181getMember();
        if (mo2181getMember instanceof Method) {
            return (Method) mo2181getMember;
        }
        return null;
    }

    public static final <T> Constructor<T> getJavaConstructor(KFunction<? extends T> kFunction) {
        Caller<?> caller;
        Intrinsics.checkNotNullParameter(kFunction, "<this>");
        KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(kFunction);
        Object mo2181getMember = (asKCallableImpl == null || (caller = asKCallableImpl.getCaller()) == null) ? null : caller.mo2181getMember();
        if (mo2181getMember instanceof Constructor) {
            return (Constructor) mo2181getMember;
        }
        return null;
    }

    public static final Type getJavaType(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        Type javaType = ((KTypeImpl) kType).getJavaType();
        return javaType == null ? TypesJVMKt.getJavaType(kType) : javaType;
    }

    public static final KProperty<?> getKotlinProperty(Field field) {
        KProperty<?> findKProperty;
        Intrinsics.checkNotNullParameter(field, "<this>");
        if (field.isSynthetic()) {
            return null;
        }
        if (Modifier.isStatic(field.getModifiers())) {
            KDeclarationContainer kPackage = getKPackage(field);
            if (kPackage != null) {
                return findKProperty(kPackage.getMembers(), field);
            }
            Class<?> declaringClass = field.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            KClass<?> companionObject = KClasses.getCompanionObject(JvmClassMappingKt.getKotlinClass(declaringClass));
            if (companionObject != null) {
                Class<?> declaringClass2 = field.getDeclaringClass();
                Intrinsics.checkNotNullExpressionValue(declaringClass2, "getDeclaringClass(...)");
                String name = field.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                Field declaredFieldOrNull = UtilKt.getDeclaredFieldOrNull(declaringClass2, name);
                if (declaredFieldOrNull != null && (findKProperty = findKProperty(KClasses.getMemberProperties(companionObject), declaredFieldOrNull)) != null) {
                    return findKProperty;
                }
            }
        }
        Class<?> declaringClass3 = field.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass3, "getDeclaringClass(...)");
        return findKProperty(KClasses.getMemberProperties(JvmClassMappingKt.getKotlinClass(declaringClass3)), field);
    }

    private static final KDeclarationContainer getKPackage(Member member) {
        KotlinClassHeader classHeader;
        ReflectKotlinClass.Factory factory = ReflectKotlinClass.Factory;
        Class<?> declaringClass = member.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        ReflectKotlinClass create = factory.create(declaringClass);
        KotlinClassHeader.Kind kind = (create == null || (classHeader = create.getClassHeader()) == null) ? null : classHeader.getKind();
        int i = kind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            return null;
        }
        Class<?> declaringClass2 = member.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass2, "getDeclaringClass(...)");
        return new KPackageImpl(declaringClass2);
    }

    public static final KFunction<?> getKotlinFunction(Method method) {
        KFunction<?> findKFunction;
        Intrinsics.checkNotNullParameter(method, "<this>");
        if (Modifier.isStatic(method.getModifiers())) {
            KDeclarationContainer kPackage = getKPackage(method);
            if (kPackage != null) {
                return findKFunction(kPackage.getMembers(), method);
            }
            Class<?> declaringClass = method.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            KClass<?> companionObject = KClasses.getCompanionObject(JvmClassMappingKt.getKotlinClass(declaringClass));
            if (companionObject != null) {
                Class javaClass = JvmClassMappingKt.getJavaClass((KClass) companionObject);
                String name = method.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                Class<?>[] parameterTypes = method.getParameterTypes();
                Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
                Method declaredMethodOrNull = UtilKt.getDeclaredMethodOrNull(javaClass, name, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
                if (declaredMethodOrNull != null && (findKFunction = findKFunction(KClasses.getFunctions(companionObject), declaredMethodOrNull)) != null) {
                    return findKFunction;
                }
            }
        }
        Class<?> declaringClass2 = method.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass2, "getDeclaringClass(...)");
        return findKFunction(KClasses.getFunctions(JvmClassMappingKt.getKotlinClass(declaringClass2)), method);
    }

    private static final KFunction<?> findKFunction(Collection<? extends KCallable<?>> collection, Method method) {
        for (KCallable<?> kCallable : collection) {
            if ((kCallable instanceof KFunction) && Intrinsics.areEqual(kCallable.getName(), method.getName())) {
                KFunction<?> kFunction = (KFunction) kCallable;
                if (Intrinsics.areEqual(getJavaMethod(kFunction), method)) {
                    return kFunction;
                }
            }
        }
        for (KCallable<?> kCallable2 : collection) {
            if ((kCallable2 instanceof KFunction) && !Intrinsics.areEqual(kCallable2.getName(), method.getName())) {
                KFunction<?> kFunction2 = (KFunction) kCallable2;
                if (Intrinsics.areEqual(getJavaMethod(kFunction2), method)) {
                    return kFunction2;
                }
            }
        }
        return null;
    }

    private static final KProperty<?> findKProperty(Collection<? extends KCallable<?>> collection, Field field) {
        for (KCallable<?> kCallable : collection) {
            if ((kCallable instanceof KProperty) && Intrinsics.areEqual(kCallable.getName(), field.getName())) {
                KProperty<?> kProperty = (KProperty) kCallable;
                if (Intrinsics.areEqual(getJavaField(kProperty), field)) {
                    return kProperty;
                }
            }
        }
        for (KCallable<?> kCallable2 : collection) {
            if ((kCallable2 instanceof KProperty) && !Intrinsics.areEqual(kCallable2.getName(), field.getName())) {
                KProperty<?> kProperty2 = (KProperty) kCallable2;
                if (Intrinsics.areEqual(getJavaField(kProperty2), field)) {
                    return kProperty2;
                }
            }
        }
        return null;
    }

    public static final <T> KFunction<T> getKotlinFunction(Constructor<T> constructor) {
        T t;
        Intrinsics.checkNotNullParameter(constructor, "<this>");
        Class<T> declaringClass = constructor.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        Iterator<T> it = JvmClassMappingKt.getKotlinClass(declaringClass).getConstructors().iterator();
        while (true) {
            if (!it.hasNext()) {
                t = null;
                break;
            }
            t = it.next();
            if (Intrinsics.areEqual(getJavaConstructor((KFunction) t), constructor)) {
                break;
            }
        }
        return (KFunction) t;
    }
}

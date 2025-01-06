package kotlin.reflect.jvm;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.Caller;

/* compiled from: KCallablesJvm.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\",\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"value", "", "isAccessible", "Lkotlin/reflect/KCallable;", "(Lkotlin/reflect/KCallable;)Z", "setAccessible", "(Lkotlin/reflect/KCallable;Z)V", "kotlin-reflection"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class KCallablesJvm {
    public static final boolean isAccessible(KCallable<?> kCallable) {
        Constructor javaConstructor;
        Caller<?> defaultCaller;
        Method javaMethod;
        Method javaMethod2;
        Method javaGetter;
        Method javaGetter2;
        Method javaSetter;
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        if (kCallable instanceof KMutableProperty) {
            KProperty kProperty = (KProperty) kCallable;
            Field javaField = ReflectJvmMapping.getJavaField(kProperty);
            if ((javaField == null || javaField.isAccessible()) && (((javaGetter2 = ReflectJvmMapping.getJavaGetter(kProperty)) == null || javaGetter2.isAccessible()) && ((javaSetter = ReflectJvmMapping.getJavaSetter((KMutableProperty) kCallable)) == null || javaSetter.isAccessible()))) {
                return true;
            }
        } else if (kCallable instanceof KProperty) {
            KProperty kProperty2 = (KProperty) kCallable;
            Field javaField2 = ReflectJvmMapping.getJavaField(kProperty2);
            if ((javaField2 == null || javaField2.isAccessible()) && ((javaGetter = ReflectJvmMapping.getJavaGetter(kProperty2)) == null || javaGetter.isAccessible())) {
                return true;
            }
        } else if (kCallable instanceof KProperty.Getter) {
            Field javaField3 = ReflectJvmMapping.getJavaField(((KProperty.Getter) kCallable).getProperty());
            if ((javaField3 == null || javaField3.isAccessible()) && ((javaMethod2 = ReflectJvmMapping.getJavaMethod((KFunction) kCallable)) == null || javaMethod2.isAccessible())) {
                return true;
            }
        } else if (kCallable instanceof KMutableProperty.Setter) {
            Field javaField4 = ReflectJvmMapping.getJavaField(((KMutableProperty.Setter) kCallable).getProperty());
            if ((javaField4 == null || javaField4.isAccessible()) && ((javaMethod = ReflectJvmMapping.getJavaMethod((KFunction) kCallable)) == null || javaMethod.isAccessible())) {
                return true;
            }
        } else if (kCallable instanceof KFunction) {
            KFunction kFunction = (KFunction) kCallable;
            Method javaMethod3 = ReflectJvmMapping.getJavaMethod(kFunction);
            if (javaMethod3 == null || javaMethod3.isAccessible()) {
                KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(kCallable);
                Object mo2181getMember = (asKCallableImpl == null || (defaultCaller = asKCallableImpl.getDefaultCaller()) == null) ? null : defaultCaller.mo2181getMember();
                AccessibleObject accessibleObject = mo2181getMember instanceof AccessibleObject ? (AccessibleObject) mo2181getMember : null;
                if ((accessibleObject == null || accessibleObject.isAccessible()) && ((javaConstructor = ReflectJvmMapping.getJavaConstructor(kFunction)) == null || javaConstructor.isAccessible())) {
                    return true;
                }
            }
        } else {
            throw new UnsupportedOperationException("Unknown callable: " + kCallable + " (" + kCallable.getClass() + ')');
        }
        return false;
    }

    public static final void setAccessible(KCallable<?> kCallable, boolean z) {
        Caller<?> defaultCaller;
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        if (kCallable instanceof KMutableProperty) {
            KProperty kProperty = (KProperty) kCallable;
            Field javaField = ReflectJvmMapping.getJavaField(kProperty);
            if (javaField != null) {
                javaField.setAccessible(z);
            }
            Method javaGetter = ReflectJvmMapping.getJavaGetter(kProperty);
            if (javaGetter != null) {
                javaGetter.setAccessible(z);
            }
            Method javaSetter = ReflectJvmMapping.getJavaSetter((KMutableProperty) kCallable);
            if (javaSetter == null) {
                return;
            }
            javaSetter.setAccessible(z);
            return;
        }
        if (kCallable instanceof KProperty) {
            KProperty kProperty2 = (KProperty) kCallable;
            Field javaField2 = ReflectJvmMapping.getJavaField(kProperty2);
            if (javaField2 != null) {
                javaField2.setAccessible(z);
            }
            Method javaGetter2 = ReflectJvmMapping.getJavaGetter(kProperty2);
            if (javaGetter2 == null) {
                return;
            }
            javaGetter2.setAccessible(z);
            return;
        }
        if (kCallable instanceof KProperty.Getter) {
            Field javaField3 = ReflectJvmMapping.getJavaField(((KProperty.Getter) kCallable).getProperty());
            if (javaField3 != null) {
                javaField3.setAccessible(z);
            }
            Method javaMethod = ReflectJvmMapping.getJavaMethod((KFunction) kCallable);
            if (javaMethod == null) {
                return;
            }
            javaMethod.setAccessible(z);
            return;
        }
        if (kCallable instanceof KMutableProperty.Setter) {
            Field javaField4 = ReflectJvmMapping.getJavaField(((KMutableProperty.Setter) kCallable).getProperty());
            if (javaField4 != null) {
                javaField4.setAccessible(z);
            }
            Method javaMethod2 = ReflectJvmMapping.getJavaMethod((KFunction) kCallable);
            if (javaMethod2 == null) {
                return;
            }
            javaMethod2.setAccessible(z);
            return;
        }
        if (kCallable instanceof KFunction) {
            KFunction kFunction = (KFunction) kCallable;
            Method javaMethod3 = ReflectJvmMapping.getJavaMethod(kFunction);
            if (javaMethod3 != null) {
                javaMethod3.setAccessible(z);
            }
            KCallableImpl<?> asKCallableImpl = UtilKt.asKCallableImpl(kCallable);
            Object mo2181getMember = (asKCallableImpl == null || (defaultCaller = asKCallableImpl.getDefaultCaller()) == null) ? null : defaultCaller.mo2181getMember();
            AccessibleObject accessibleObject = mo2181getMember instanceof AccessibleObject ? (AccessibleObject) mo2181getMember : null;
            if (accessibleObject != null) {
                accessibleObject.setAccessible(true);
            }
            Constructor javaConstructor = ReflectJvmMapping.getJavaConstructor(kFunction);
            if (javaConstructor == null) {
                return;
            }
            javaConstructor.setAccessible(z);
            return;
        }
        throw new UnsupportedOperationException("Unknown callable: " + kCallable + " (" + kCallable.getClass() + ')');
    }
}
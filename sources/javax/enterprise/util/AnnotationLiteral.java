package javax.enterprise.util;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public abstract class AnnotationLiteral<T extends Annotation> implements Annotation, Serializable {
    private static final long serialVersionUID = 1;
    private transient Class<T> annotationType;
    private transient Integer cachedHashCode;
    private transient Method[] members;

    protected AnnotationLiteral() {
        if (getMembers().length == 0) {
            this.cachedHashCode = 0;
        } else {
            this.cachedHashCode = null;
        }
    }

    private Method[] getMembers() {
        if (this.members == null) {
            Method[] declaredMethods = annotationType().getDeclaredMethods();
            this.members = declaredMethods;
            if (declaredMethods.length > 0 && !annotationType().isAssignableFrom(getClass())) {
                throw new RuntimeException(getClass() + " does not implement the annotation type with members " + annotationType().getName());
            }
        }
        return this.members;
    }

    private static Class<?> getAnnotationLiteralSubclass(Class<?> cls) {
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass.equals(AnnotationLiteral.class)) {
            return cls;
        }
        if (superclass.equals(Object.class)) {
            return null;
        }
        return getAnnotationLiteralSubclass(superclass);
    }

    private static <T> Class<T> getTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        if (parameterizedType.getActualTypeArguments().length == 1) {
            return (Class) parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }

    @Override // java.lang.annotation.Annotation
    public Class<? extends Annotation> annotationType() {
        if (this.annotationType == null) {
            Class<?> annotationLiteralSubclass = getAnnotationLiteralSubclass(getClass());
            if (annotationLiteralSubclass == null) {
                throw new RuntimeException(getClass() + " is not a subclass of AnnotationLiteral");
            }
            Class<T> typeParameter = getTypeParameter(annotationLiteralSubclass);
            this.annotationType = typeParameter;
            if (typeParameter == null) {
                throw new RuntimeException(getClass() + " does not specify the type parameter T of AnnotationLiteral<T>");
            }
        }
        return this.annotationType;
    }

    @Override // java.lang.annotation.Annotation
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('@');
        sb.append(annotationType().getName());
        sb.append('(');
        for (int i = 0; i < getMembers().length; i++) {
            sb.append(getMembers()[i].getName());
            sb.append('=');
            Object memberValue = getMemberValue(getMembers()[i], this);
            if (memberValue instanceof boolean[]) {
                appendInBraces(sb, Arrays.toString((boolean[]) memberValue));
            } else if (memberValue instanceof byte[]) {
                appendInBraces(sb, Arrays.toString((byte[]) memberValue));
            } else if (memberValue instanceof short[]) {
                appendInBraces(sb, Arrays.toString((short[]) memberValue));
            } else if (memberValue instanceof int[]) {
                appendInBraces(sb, Arrays.toString((int[]) memberValue));
            } else if (memberValue instanceof long[]) {
                appendInBraces(sb, Arrays.toString((long[]) memberValue));
            } else if (memberValue instanceof float[]) {
                appendInBraces(sb, Arrays.toString((float[]) memberValue));
            } else if (memberValue instanceof double[]) {
                appendInBraces(sb, Arrays.toString((double[]) memberValue));
            } else if (memberValue instanceof char[]) {
                appendInBraces(sb, Arrays.toString((char[]) memberValue));
            } else if (memberValue instanceof String[]) {
                String[] strArr = (String[]) memberValue;
                String[] strArr2 = new String[strArr.length];
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    strArr2[i2] = "\"" + strArr[i2] + "\"";
                }
                appendInBraces(sb, Arrays.toString(strArr2));
            } else if (memberValue instanceof Class[]) {
                Class[] clsArr = (Class[]) memberValue;
                String[] strArr3 = new String[clsArr.length];
                for (int i3 = 0; i3 < clsArr.length; i3++) {
                    strArr3[i3] = clsArr[i3].getName() + ".class";
                }
                appendInBraces(sb, Arrays.toString(strArr3));
            } else if (memberValue instanceof Object[]) {
                appendInBraces(sb, Arrays.toString((Object[]) memberValue));
            } else if (memberValue instanceof String) {
                sb.append(Typography.quote);
                sb.append(memberValue);
                sb.append(Typography.quote);
            } else if (memberValue instanceof Class) {
                sb.append(((Class) memberValue).getName());
                sb.append(".class");
            } else {
                sb.append(memberValue);
            }
            if (i < getMembers().length - 1) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }

    private void appendInBraces(StringBuilder sb, String str) {
        sb.append('{');
        sb.append(str.substring(1, str.length() - 1));
        sb.append('}');
    }

    @Override // java.lang.annotation.Annotation
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof Annotation)) {
            Annotation annotation = (Annotation) obj;
            if (annotationType().equals(annotation.annotationType())) {
                for (Method method : getMembers()) {
                    Object memberValue = getMemberValue(method, this);
                    Object memberValue2 = getMemberValue(method, annotation);
                    if ((memberValue instanceof byte[]) && (memberValue2 instanceof byte[])) {
                        if (!Arrays.equals((byte[]) memberValue, (byte[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof short[]) && (memberValue2 instanceof short[])) {
                        if (!Arrays.equals((short[]) memberValue, (short[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof int[]) && (memberValue2 instanceof int[])) {
                        if (!Arrays.equals((int[]) memberValue, (int[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof long[]) && (memberValue2 instanceof long[])) {
                        if (!Arrays.equals((long[]) memberValue, (long[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof float[]) && (memberValue2 instanceof float[])) {
                        if (!Arrays.equals((float[]) memberValue, (float[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof double[]) && (memberValue2 instanceof double[])) {
                        if (!Arrays.equals((double[]) memberValue, (double[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof char[]) && (memberValue2 instanceof char[])) {
                        if (!Arrays.equals((char[]) memberValue, (char[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof boolean[]) && (memberValue2 instanceof boolean[])) {
                        if (!Arrays.equals((boolean[]) memberValue, (boolean[]) memberValue2)) {
                            return false;
                        }
                    } else if ((memberValue instanceof Object[]) && (memberValue2 instanceof Object[])) {
                        if (!Arrays.equals((Object[]) memberValue, (Object[]) memberValue2)) {
                            return false;
                        }
                    } else if (!memberValue.equals(memberValue2)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.annotation.Annotation
    public int hashCode() {
        int hashCode;
        Integer num = this.cachedHashCode;
        if (num != null) {
            return num.intValue();
        }
        int i = 0;
        for (Method method : getMembers()) {
            int hashCode2 = method.getName().hashCode() * 127;
            Object memberValue = getMemberValue(method, this);
            if (memberValue instanceof boolean[]) {
                hashCode = Arrays.hashCode((boolean[]) memberValue);
            } else if (memberValue instanceof short[]) {
                hashCode = Arrays.hashCode((short[]) memberValue);
            } else if (memberValue instanceof int[]) {
                hashCode = Arrays.hashCode((int[]) memberValue);
            } else if (memberValue instanceof long[]) {
                hashCode = Arrays.hashCode((long[]) memberValue);
            } else if (memberValue instanceof float[]) {
                hashCode = Arrays.hashCode((float[]) memberValue);
            } else if (memberValue instanceof double[]) {
                hashCode = Arrays.hashCode((double[]) memberValue);
            } else if (memberValue instanceof byte[]) {
                hashCode = Arrays.hashCode((byte[]) memberValue);
            } else if (memberValue instanceof char[]) {
                hashCode = Arrays.hashCode((char[]) memberValue);
            } else if (memberValue instanceof Object[]) {
                hashCode = Arrays.hashCode((Object[]) memberValue);
            } else {
                hashCode = memberValue.hashCode();
            }
            i += hashCode ^ hashCode2;
        }
        return i;
    }

    private static Object getMemberValue(Method method, Annotation annotation) {
        Object invoke = invoke(method, annotation);
        if (invoke != null) {
            return invoke;
        }
        throw new IllegalArgumentException("Annotation member value " + annotation.getClass().getName() + "." + method.getName() + " must not be null");
    }

    private static Object invoke(Method method, Object obj) {
        try {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method.invoke(obj, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error checking value of member method " + method.getName() + " on " + method.getDeclaringClass(), e);
        } catch (IllegalArgumentException e2) {
            throw new RuntimeException("Error checking value of member method " + method.getName() + " on " + method.getDeclaringClass(), e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("Error checking value of member method " + method.getName() + " on " + method.getDeclaringClass(), e3);
        }
    }
}

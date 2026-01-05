package javax.el;

import java.beans.FeatureDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class StaticFieldELResolver extends ELResolver {
    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        return null;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (!(obj instanceof ELClass) || !(obj2 instanceof String)) {
            return null;
        }
        Class<?> klass = ((ELClass) obj).getKlass();
        String str = (String) obj2;
        try {
            eLContext.setPropertyResolved(obj, obj2);
            Field field = klass.getField(str);
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                return field.get(null);
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
        throw new PropertyNotFoundException(ELUtil.getExceptionMessageString(eLContext, "staticFieldReadError", new Object[]{klass.getName(), str}));
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.getClass();
        if ((obj instanceof ELClass) && (obj2 instanceof String)) {
            throw new PropertyNotWritableException(ELUtil.getExceptionMessageString(eLContext, "staticFieldWriteError", new Object[]{((ELClass) obj).getKlass().getName(), (String) obj2}));
        }
    }

    @Override // javax.el.ELResolver
    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        Object invokeMethod;
        eLContext.getClass();
        if (!(obj instanceof ELClass) || !(obj2 instanceof String)) {
            return null;
        }
        Class<?> klass = ((ELClass) obj).getKlass();
        String str = (String) obj2;
        if ("<init>".equals(str)) {
            invokeMethod = ELUtil.invokeConstructor(eLContext, ELUtil.findConstructor(klass, clsArr, objArr), objArr);
        } else {
            invokeMethod = ELUtil.invokeMethod(eLContext, ELUtil.findMethod(klass, str, clsArr, objArr, true), null, objArr);
        }
        eLContext.setPropertyResolved(obj, obj2);
        return invokeMethod;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (!(obj instanceof ELClass) || !(obj2 instanceof String)) {
            return null;
        }
        Class<?> klass = ((ELClass) obj).getKlass();
        String str = (String) obj2;
        try {
            eLContext.setPropertyResolved(true);
            Field field = klass.getField(str);
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                return field.getType();
            }
        } catch (NoSuchFieldException unused) {
        }
        throw new PropertyNotFoundException(ELUtil.getExceptionMessageString(eLContext, "staticFieldReadError", new Object[]{klass.getName(), str}));
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if ((obj instanceof ELClass) && (obj2 instanceof String)) {
            ((ELClass) obj).getKlass();
            eLContext.setPropertyResolved(true);
        }
        return true;
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        return String.class;
    }
}

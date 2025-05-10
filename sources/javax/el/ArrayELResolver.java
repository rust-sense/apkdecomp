package javax.el;

import java.beans.FeatureDescriptor;
import java.lang.reflect.Array;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class ArrayELResolver extends ELResolver {
    private boolean isReadOnly;

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        return null;
    }

    public ArrayELResolver() {
        this.isReadOnly = false;
    }

    public ArrayELResolver(boolean z) {
        this.isReadOnly = z;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || !obj.getClass().isArray()) {
            return null;
        }
        eLContext.setPropertyResolved(true);
        int integer = toInteger(obj2);
        if (integer < 0 || integer >= Array.getLength(obj)) {
            throw new PropertyNotFoundException();
        }
        return obj.getClass().getComponentType();
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || !obj.getClass().isArray()) {
            return null;
        }
        eLContext.setPropertyResolved(obj, obj2);
        int integer = toInteger(obj2);
        if (integer < 0 || integer >= Array.getLength(obj)) {
            return null;
        }
        return Array.get(obj, integer);
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.getClass();
        if (obj == null || !obj.getClass().isArray()) {
            return;
        }
        eLContext.setPropertyResolved(obj, obj2);
        if (this.isReadOnly) {
            throw new PropertyNotWritableException();
        }
        Class<?> componentType = obj.getClass().getComponentType();
        if (obj3 != null && !componentType.isAssignableFrom(obj3.getClass())) {
            throw new ClassCastException();
        }
        int integer = toInteger(obj2);
        if (integer < 0 || integer >= Array.getLength(obj)) {
            throw new PropertyNotFoundException();
        }
        Array.set(obj, integer, obj3);
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj != null && obj.getClass().isArray()) {
            eLContext.setPropertyResolved(true);
            int integer = toInteger(obj2);
            if (integer < 0 || integer >= Array.getLength(obj)) {
                throw new PropertyNotFoundException();
            }
        }
        return this.isReadOnly;
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj == null || !obj.getClass().isArray()) {
            return null;
        }
        return Integer.class;
    }

    private int toInteger(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof Character) {
            return ((Character) obj).charValue();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? 1 : 0;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof String) {
            return Integer.parseInt((String) obj);
        }
        throw new IllegalArgumentException();
    }
}

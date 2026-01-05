package javax.el;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ListELResolver extends ELResolver {
    private static Class<?> theUnmodifiableListClass = Collections.unmodifiableList(new ArrayList()).getClass();
    private boolean isReadOnly;

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        return null;
    }

    public ListELResolver() {
        this.isReadOnly = false;
    }

    public ListELResolver(boolean z) {
        this.isReadOnly = z;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || !(obj instanceof List)) {
            return null;
        }
        eLContext.setPropertyResolved(true);
        List list = (List) obj;
        int integer = toInteger(obj2);
        if (integer < 0 || integer >= list.size()) {
            throw new PropertyNotFoundException();
        }
        return Object.class;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj != null && (obj instanceof List)) {
            eLContext.setPropertyResolved(obj, obj2);
            List list = (List) obj;
            int integer = toInteger(obj2);
            if (integer >= 0 && integer < list.size()) {
                return list.get(integer);
            }
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.getClass();
        if (obj == null || !(obj instanceof List)) {
            return;
        }
        eLContext.setPropertyResolved(obj, obj2);
        List list = (List) obj;
        int integer = toInteger(obj2);
        if (this.isReadOnly) {
            throw new PropertyNotWritableException();
        }
        try {
            list.set(integer, obj3);
        } catch (ClassCastException e) {
            throw e;
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (IndexOutOfBoundsException unused) {
            throw new PropertyNotFoundException();
        } catch (NullPointerException e3) {
            throw e3;
        } catch (UnsupportedOperationException unused2) {
            throw new PropertyNotWritableException();
        }
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || !(obj instanceof List)) {
            return false;
        }
        eLContext.setPropertyResolved(true);
        List list = (List) obj;
        int integer = toInteger(obj2);
        if (integer < 0 || integer >= list.size()) {
            throw new PropertyNotFoundException();
        }
        return list.getClass() == theUnmodifiableListClass || this.isReadOnly;
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj == null || !(obj instanceof List)) {
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
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof String) {
            return Integer.parseInt((String) obj);
        }
        throw new IllegalArgumentException();
    }
}

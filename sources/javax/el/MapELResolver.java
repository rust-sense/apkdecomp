package javax.el;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
public class MapELResolver extends ELResolver {
    private static Class<?> theUnmodifiableMapClass = Collections.unmodifiableMap(new HashMap()).getClass();
    private boolean isReadOnly;

    public MapELResolver() {
        this.isReadOnly = false;
    }

    public MapELResolver(boolean z) {
        this.isReadOnly = z;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || !(obj instanceof Map)) {
            return null;
        }
        eLContext.setPropertyResolved(true);
        return Object.class;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || !(obj instanceof Map)) {
            return null;
        }
        eLContext.setPropertyResolved(obj, obj2);
        return ((Map) obj).get(obj2);
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.getClass();
        if (obj == null || !(obj instanceof Map)) {
            return;
        }
        eLContext.setPropertyResolved(obj, obj2);
        Map map = (Map) obj;
        if (this.isReadOnly || map.getClass() == theUnmodifiableMapClass) {
            throw new PropertyNotWritableException();
        }
        try {
            map.put(obj2, obj3);
        } catch (UnsupportedOperationException unused) {
            throw new PropertyNotWritableException();
        }
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || !(obj instanceof Map)) {
            return false;
        }
        eLContext.setPropertyResolved(true);
        return this.isReadOnly || ((Map) obj).getClass() == theUnmodifiableMapClass;
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        if (obj == null || !(obj instanceof Map)) {
            return null;
        }
        Iterator it = ((Map) obj).keySet().iterator();
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            Object next = it.next();
            FeatureDescriptor featureDescriptor = new FeatureDescriptor();
            String obj2 = next == null ? null : next.toString();
            featureDescriptor.setName(obj2);
            featureDescriptor.setDisplayName(obj2);
            featureDescriptor.setShortDescription("");
            featureDescriptor.setExpert(false);
            featureDescriptor.setHidden(false);
            featureDescriptor.setPreferred(true);
            if (next != null) {
                featureDescriptor.setValue("type", next.getClass());
            }
            featureDescriptor.setValue(ELResolver.RESOLVABLE_AT_DESIGN_TIME, Boolean.TRUE);
            arrayList.add(featureDescriptor);
        }
        return arrayList.iterator();
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj == null || !(obj instanceof Map)) {
            return null;
        }
        return Object.class;
    }
}

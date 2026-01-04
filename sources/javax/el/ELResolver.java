package javax.el;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class ELResolver {
    public static final String RESOLVABLE_AT_DESIGN_TIME = "resolvableAtDesignTime";
    public static final String TYPE = "type";

    public Object convertToType(ELContext eLContext, Object obj, Class<?> cls) {
        return null;
    }

    public abstract Class<?> getCommonPropertyType(ELContext eLContext, Object obj);

    public abstract Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj);

    public abstract Class<?> getType(ELContext eLContext, Object obj, Object obj2);

    public abstract Object getValue(ELContext eLContext, Object obj, Object obj2);

    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        return null;
    }

    public abstract boolean isReadOnly(ELContext eLContext, Object obj, Object obj2);

    public abstract void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3);
}

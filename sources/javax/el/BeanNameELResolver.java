package javax.el;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class BeanNameELResolver extends ELResolver {
    private BeanNameResolver beanNameResolver;

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        return null;
    }

    public BeanNameELResolver(BeanNameResolver beanNameResolver) {
        this.beanNameResolver = beanNameResolver;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj != null || !(obj2 instanceof String)) {
            return null;
        }
        String str = (String) obj2;
        if (!this.beanNameResolver.isNameResolved(str)) {
            return null;
        }
        eLContext.setPropertyResolved(obj, obj2);
        return this.beanNameResolver.getBean(str);
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.getClass();
        if (obj == null && (obj2 instanceof String)) {
            String str = (String) obj2;
            if (this.beanNameResolver.isNameResolved(str) || this.beanNameResolver.canCreateBean(str)) {
                this.beanNameResolver.setBeanValue(str, obj3);
                eLContext.setPropertyResolved(obj, obj2);
            }
        }
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj != null || !(obj2 instanceof String)) {
            return null;
        }
        String str = (String) obj2;
        if (!this.beanNameResolver.isNameResolved(str)) {
            return null;
        }
        eLContext.setPropertyResolved(true);
        return this.beanNameResolver.getBean(str).getClass();
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj != null || !(obj2 instanceof String)) {
            return false;
        }
        String str = (String) obj2;
        if (!this.beanNameResolver.isNameResolved(str)) {
            return false;
        }
        eLContext.setPropertyResolved(true);
        return this.beanNameResolver.isReadOnly(str);
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        return String.class;
    }
}

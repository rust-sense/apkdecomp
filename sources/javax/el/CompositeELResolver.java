package javax.el;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class CompositeELResolver extends ELResolver {
    private int size = 0;
    private ELResolver[] elResolvers = new ELResolver[16];

    public void add(ELResolver eLResolver) {
        eLResolver.getClass();
        int i = this.size;
        ELResolver[] eLResolverArr = this.elResolvers;
        if (i >= eLResolverArr.length) {
            ELResolver[] eLResolverArr2 = new ELResolver[i * 2];
            System.arraycopy(eLResolverArr, 0, eLResolverArr2, 0, i);
            this.elResolvers = eLResolverArr2;
        }
        ELResolver[] eLResolverArr3 = this.elResolvers;
        int i2 = this.size;
        this.size = i2 + 1;
        eLResolverArr3[i2] = eLResolver;
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.setPropertyResolved(false);
        for (int i = 0; i < this.size; i++) {
            Object value = this.elResolvers[i].getValue(eLContext, obj, obj2);
            if (eLContext.isPropertyResolved()) {
                return value;
            }
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        eLContext.setPropertyResolved(false);
        for (int i = 0; i < this.size; i++) {
            Object invoke = this.elResolvers[i].invoke(eLContext, obj, obj2, clsArr, objArr);
            if (eLContext.isPropertyResolved()) {
                return invoke;
            }
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.setPropertyResolved(false);
        for (int i = 0; i < this.size; i++) {
            Class<?> type = this.elResolvers[i].getType(eLContext, obj, obj2);
            if (eLContext.isPropertyResolved()) {
                return type;
            }
        }
        return null;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.setPropertyResolved(false);
        for (int i = 0; i < this.size; i++) {
            this.elResolvers[i].setValue(eLContext, obj, obj2, obj3);
            if (eLContext.isPropertyResolved()) {
                return;
            }
        }
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.setPropertyResolved(false);
        for (int i = 0; i < this.size; i++) {
            boolean isReadOnly = this.elResolvers[i].isReadOnly(eLContext, obj, obj2);
            if (eLContext.isPropertyResolved()) {
                return isReadOnly;
            }
        }
        return false;
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        return new CompositeIterator(this.elResolvers, this.size, eLContext, obj);
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        Class<?> cls = null;
        for (int i = 0; i < this.size; i++) {
            Class<?> commonPropertyType = this.elResolvers[i].getCommonPropertyType(eLContext, obj);
            if (commonPropertyType != null) {
                if (cls != null) {
                    if (cls.isAssignableFrom(commonPropertyType)) {
                        continue;
                    } else if (!commonPropertyType.isAssignableFrom(cls)) {
                        return null;
                    }
                }
                cls = commonPropertyType;
            }
        }
        return cls;
    }

    @Override // javax.el.ELResolver
    public Object convertToType(ELContext eLContext, Object obj, Class<?> cls) {
        eLContext.setPropertyResolved(false);
        for (int i = 0; i < this.size; i++) {
            Object convertToType = this.elResolvers[i].convertToType(eLContext, obj, cls);
            if (eLContext.isPropertyResolved()) {
                return convertToType;
            }
        }
        return null;
    }

    private static class CompositeIterator implements Iterator<FeatureDescriptor> {
        Object base;
        ELContext context;
        int index = 0;
        Iterator<FeatureDescriptor> propertyIter = null;
        ELResolver[] resolvers;
        int size;

        CompositeIterator(ELResolver[] eLResolverArr, int i, ELContext eLContext, Object obj) {
            this.resolvers = eLResolverArr;
            this.size = i;
            this.context = eLContext;
            this.base = obj;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Iterator<FeatureDescriptor> featureDescriptors;
            Iterator<FeatureDescriptor> it = this.propertyIter;
            if (it == null || !it.hasNext()) {
                do {
                    int i = this.index;
                    if (i >= this.size) {
                        return false;
                    }
                    ELResolver[] eLResolverArr = this.resolvers;
                    this.index = i + 1;
                    featureDescriptors = eLResolverArr[i].getFeatureDescriptors(this.context, this.base);
                    this.propertyIter = featureDescriptors;
                } while (featureDescriptors == null);
                return featureDescriptors.hasNext();
            }
            return this.propertyIter.hasNext();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public FeatureDescriptor next() {
            Iterator<FeatureDescriptor> featureDescriptors;
            Iterator<FeatureDescriptor> it = this.propertyIter;
            if (it == null || !it.hasNext()) {
                do {
                    int i = this.index;
                    if (i >= this.size) {
                        return null;
                    }
                    ELResolver[] eLResolverArr = this.resolvers;
                    this.index = i + 1;
                    featureDescriptors = eLResolverArr[i].getFeatureDescriptors(this.context, this.base);
                    this.propertyIter = featureDescriptors;
                } while (featureDescriptors == null);
                return featureDescriptors.next();
            }
            return this.propertyIter.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

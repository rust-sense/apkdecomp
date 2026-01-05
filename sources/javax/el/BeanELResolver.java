package javax.el;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class BeanELResolver extends ELResolver {
    private static final SoftConcurrentHashMap properties = new SoftConcurrentHashMap();
    private boolean isReadOnly;

    private static class BPSoftReference extends SoftReference<BeanProperties> {
        final Class<?> key;

        BPSoftReference(Class<?> cls, BeanProperties beanProperties, ReferenceQueue<BeanProperties> referenceQueue) {
            super(beanProperties, referenceQueue);
            this.key = cls;
        }
    }

    private static class SoftConcurrentHashMap extends ConcurrentHashMap<Class<?>, BeanProperties> {
        private static final int CACHE_INIT_SIZE = 1024;
        private ConcurrentHashMap<Class<?>, BPSoftReference> map;
        private ReferenceQueue<BeanProperties> refQ;

        private SoftConcurrentHashMap() {
            this.map = new ConcurrentHashMap<>(1024);
            this.refQ = new ReferenceQueue<>();
        }

        private void cleanup() {
            while (true) {
                BPSoftReference bPSoftReference = (BPSoftReference) this.refQ.poll();
                if (bPSoftReference == null) {
                    return;
                } else {
                    this.map.remove(bPSoftReference.key);
                }
            }
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public BeanProperties put(Class<?> cls, BeanProperties beanProperties) {
            cleanup();
            BPSoftReference put = this.map.put(cls, new BPSoftReference(cls, beanProperties, this.refQ));
            if (put == null) {
                return null;
            }
            return put.get();
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.Map, java.util.concurrent.ConcurrentMap
        public BeanProperties putIfAbsent(Class<?> cls, BeanProperties beanProperties) {
            cleanup();
            BPSoftReference putIfAbsent = this.map.putIfAbsent(cls, new BPSoftReference(cls, beanProperties, this.refQ));
            if (putIfAbsent == null) {
                return null;
            }
            return putIfAbsent.get();
        }

        @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
        public BeanProperties get(Object obj) {
            cleanup();
            BPSoftReference bPSoftReference = this.map.get(obj);
            if (bPSoftReference == null) {
                return null;
            }
            if (bPSoftReference.get() == null) {
                this.map.remove(obj);
                return null;
            }
            return bPSoftReference.get();
        }
    }

    static final class BeanProperty {
        private PropertyDescriptor descriptor;
        private Method readMethod;
        private Method writeMethod;

        public Method getReadMethod() {
            return this.readMethod;
        }

        public Method getWriteMethod() {
            return this.writeMethod;
        }

        public BeanProperty(Class<?> cls, PropertyDescriptor propertyDescriptor) {
            this.descriptor = propertyDescriptor;
            this.readMethod = BeanELResolver.getMethod(cls, propertyDescriptor.getReadMethod());
            this.writeMethod = BeanELResolver.getMethod(cls, propertyDescriptor.getWriteMethod());
        }

        public Class getPropertyType() {
            return this.descriptor.getPropertyType();
        }

        public boolean isReadOnly() {
            return getWriteMethod() == null;
        }
    }

    static final class BeanProperties {
        private final Map<String, BeanProperty> propertyMap = new HashMap();

        public BeanProperties(Class<?> cls) {
            try {
                for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(cls).getPropertyDescriptors()) {
                    this.propertyMap.put(propertyDescriptor.getName(), new BeanProperty(cls, propertyDescriptor));
                }
            } catch (IntrospectionException e) {
                throw new ELException((Throwable) e);
            }
        }

        public BeanProperty getBeanProperty(String str) {
            return this.propertyMap.get(str);
        }
    }

    public BeanELResolver() {
        this.isReadOnly = false;
    }

    public BeanELResolver(boolean z) {
        this.isReadOnly = z;
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || obj2 == null) {
            return null;
        }
        BeanProperty beanProperty = getBeanProperty(eLContext, obj, obj2);
        eLContext.setPropertyResolved(true);
        return beanProperty.getPropertyType();
    }

    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || obj2 == null) {
            return null;
        }
        Method readMethod = getBeanProperty(eLContext, obj, obj2).getReadMethod();
        if (readMethod == null) {
            throw new PropertyNotFoundException(ELUtil.getExceptionMessageString(eLContext, "propertyNotReadable", new Object[]{obj.getClass().getName(), obj2.toString()}));
        }
        try {
            Object invoke = readMethod.invoke(obj, new Object[0]);
            eLContext.setPropertyResolved(obj, obj2);
            return invoke;
        } catch (InvocationTargetException e) {
            throw new ELException(e.getCause());
        } catch (ELException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new ELException(e3);
        }
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.getClass();
        if (obj == null || obj2 == null) {
            return;
        }
        if (this.isReadOnly) {
            throw new PropertyNotWritableException(ELUtil.getExceptionMessageString(eLContext, "resolverNotwritable", new Object[]{obj.getClass().getName()}));
        }
        Method writeMethod = getBeanProperty(eLContext, obj, obj2).getWriteMethod();
        if (writeMethod == null) {
            throw new PropertyNotWritableException(ELUtil.getExceptionMessageString(eLContext, "propertyNotWritable", new Object[]{obj.getClass().getName(), obj2.toString()}));
        }
        try {
            writeMethod.invoke(obj, obj3);
            eLContext.setPropertyResolved(obj, obj2);
        } catch (InvocationTargetException e) {
            throw new ELException(e.getCause());
        } catch (ELException e2) {
            throw e2;
        } catch (Exception e3) {
            if (obj3 == null) {
                obj3 = "null";
            }
            throw new ELException(ELUtil.getExceptionMessageString(eLContext, "setPropertyFailed", new Object[]{obj2.toString(), obj.getClass().getName(), obj3}), e3);
        }
    }

    @Override // javax.el.ELResolver
    public Object invoke(ELContext eLContext, Object obj, Object obj2, Class<?>[] clsArr, Object[] objArr) {
        if (obj == null || obj2 == null) {
            return null;
        }
        Method findMethod = ELUtil.findMethod(obj.getClass(), obj2.toString(), clsArr, objArr, false);
        for (Object obj3 : objArr) {
            if (obj3 instanceof LambdaExpression) {
                ((LambdaExpression) obj3).setELContext(eLContext);
            }
        }
        Object invokeMethod = ELUtil.invokeMethod(eLContext, findMethod, obj, objArr);
        eLContext.setPropertyResolved(obj, obj2);
        return invokeMethod;
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (obj == null || obj2 == null) {
            return false;
        }
        eLContext.setPropertyResolved(true);
        if (this.isReadOnly) {
            return true;
        }
        return getBeanProperty(eLContext, obj, obj2).isReadOnly();
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        BeanInfo beanInfo;
        if (obj == null) {
            return null;
        }
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
        } catch (Exception unused) {
            beanInfo = null;
        }
        if (beanInfo == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(beanInfo.getPropertyDescriptors().length);
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            propertyDescriptor.setValue("type", propertyDescriptor.getPropertyType());
            propertyDescriptor.setValue(ELResolver.RESOLVABLE_AT_DESIGN_TIME, Boolean.TRUE);
            arrayList.add(propertyDescriptor);
        }
        return arrayList.iterator();
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj == null) {
            return null;
        }
        return Object.class;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Method getMethod(Class<?> cls, Method method) {
        Method method2;
        if (method == null) {
            return null;
        }
        if (Modifier.isPublic(cls.getModifiers())) {
            return method;
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            try {
                Method method3 = cls2.getMethod(method.getName(), method.getParameterTypes());
                method2 = getMethod(method3.getDeclaringClass(), method3);
            } catch (NoSuchMethodException unused) {
            }
            if (method2 != null) {
                return method2;
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            try {
                Method method4 = superclass.getMethod(method.getName(), method.getParameterTypes());
                Method method5 = getMethod(method4.getDeclaringClass(), method4);
                if (method5 != null) {
                    return method5;
                }
            } catch (NoSuchMethodException unused2) {
            }
        }
        return null;
    }

    private BeanProperty getBeanProperty(ELContext eLContext, Object obj, Object obj2) {
        String obj3 = obj2.toString();
        Class<?> cls = obj.getClass();
        SoftConcurrentHashMap softConcurrentHashMap = properties;
        BeanProperties beanProperties = softConcurrentHashMap.get((Object) cls);
        if (beanProperties == null) {
            beanProperties = new BeanProperties(cls);
            softConcurrentHashMap.put(cls, beanProperties);
        }
        BeanProperty beanProperty = beanProperties.getBeanProperty(obj3);
        if (beanProperty != null) {
            return beanProperty;
        }
        throw new PropertyNotFoundException(ELUtil.getExceptionMessageString(eLContext, "propertyNotFound", new Object[]{cls.getName(), obj3}));
    }
}

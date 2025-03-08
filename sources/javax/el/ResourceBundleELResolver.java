package javax.el;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: classes2.dex */
public class ResourceBundleELResolver extends ELResolver {
    @Override // javax.el.ELResolver
    public Object getValue(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (!(obj instanceof ResourceBundle)) {
            return null;
        }
        eLContext.setPropertyResolved(true);
        if (obj2 == null) {
            return null;
        }
        try {
            return ((ResourceBundle) obj).getObject(obj2.toString());
        } catch (MissingResourceException unused) {
            return "???" + obj2 + "???";
        }
    }

    @Override // javax.el.ELResolver
    public Class<?> getType(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (!(obj instanceof ResourceBundle)) {
            return null;
        }
        eLContext.setPropertyResolved(true);
        return null;
    }

    @Override // javax.el.ELResolver
    public void setValue(ELContext eLContext, Object obj, Object obj2, Object obj3) {
        eLContext.getClass();
        if (obj instanceof ResourceBundle) {
            eLContext.setPropertyResolved(true);
            throw new PropertyNotWritableException("ResourceBundles are immutable");
        }
    }

    @Override // javax.el.ELResolver
    public boolean isReadOnly(ELContext eLContext, Object obj, Object obj2) {
        eLContext.getClass();
        if (!(obj instanceof ResourceBundle)) {
            return false;
        }
        eLContext.setPropertyResolved(true);
        return true;
    }

    @Override // javax.el.ELResolver
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext eLContext, Object obj) {
        if (!(obj instanceof ResourceBundle)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Enumeration<String> keys = ((ResourceBundle) obj).getKeys();
        while (keys.hasMoreElements()) {
            String nextElement = keys.nextElement();
            FeatureDescriptor featureDescriptor = new FeatureDescriptor();
            featureDescriptor.setDisplayName(nextElement);
            featureDescriptor.setExpert(false);
            featureDescriptor.setHidden(false);
            featureDescriptor.setName(nextElement);
            featureDescriptor.setPreferred(true);
            featureDescriptor.setValue("type", String.class);
            featureDescriptor.setValue(ELResolver.RESOLVABLE_AT_DESIGN_TIME, Boolean.TRUE);
            arrayList.add(featureDescriptor);
        }
        return arrayList.iterator();
    }

    @Override // javax.el.ELResolver
    public Class<?> getCommonPropertyType(ELContext eLContext, Object obj) {
        if (obj instanceof ResourceBundle) {
            return String.class;
        }
        return null;
    }
}

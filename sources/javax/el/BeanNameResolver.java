package javax.el;

/* loaded from: classes2.dex */
public abstract class BeanNameResolver {
    public boolean canCreateBean(String str) {
        return false;
    }

    public Object getBean(String str) {
        return null;
    }

    public boolean isNameResolved(String str) {
        return false;
    }

    public boolean isReadOnly(String str) {
        return true;
    }

    public void setBeanValue(String str, Object obj) throws PropertyNotWritableException {
        throw new PropertyNotWritableException();
    }
}

package javax.enterprise.inject;

/* loaded from: classes2.dex */
public class CreationException extends InjectionException {
    private static final long serialVersionUID = 1002854668862145298L;

    public CreationException() {
    }

    public CreationException(String str) {
        super(str);
    }

    public CreationException(Throwable th) {
        super(th);
    }

    public CreationException(String str, Throwable th) {
        super(str, th);
    }
}

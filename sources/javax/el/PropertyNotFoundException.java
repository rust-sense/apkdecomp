package javax.el;

/* loaded from: classes2.dex */
public class PropertyNotFoundException extends ELException {
    public PropertyNotFoundException() {
    }

    public PropertyNotFoundException(String str) {
        super(str);
    }

    public PropertyNotFoundException(Throwable th) {
        super(th);
    }

    public PropertyNotFoundException(String str, Throwable th) {
        super(str, th);
    }
}

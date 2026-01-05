package javax.enterprise.event;

/* loaded from: classes2.dex */
public class ObserverException extends RuntimeException {
    private static final long serialVersionUID = -801836224808304381L;

    public ObserverException() {
    }

    public ObserverException(String str) {
        super(str);
    }

    public ObserverException(Throwable th) {
        super(th);
    }

    public ObserverException(String str, Throwable th) {
        super(str, th);
    }
}

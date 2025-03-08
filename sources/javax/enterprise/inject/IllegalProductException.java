package javax.enterprise.inject;

/* loaded from: classes2.dex */
public class IllegalProductException extends InjectionException {
    private static final long serialVersionUID = -6280627846071966243L;

    public IllegalProductException() {
    }

    public IllegalProductException(String str, Throwable th) {
        super(str, th);
    }

    public IllegalProductException(String str) {
        super(str);
    }

    public IllegalProductException(Throwable th) {
        super(th);
    }
}

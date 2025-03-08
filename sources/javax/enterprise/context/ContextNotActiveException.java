package javax.enterprise.context;

/* loaded from: classes2.dex */
public class ContextNotActiveException extends ContextException {
    private static final long serialVersionUID = -3599813072560026919L;

    public ContextNotActiveException() {
    }

    public ContextNotActiveException(String str) {
        super(str);
    }

    public ContextNotActiveException(Throwable th) {
        super(th);
    }

    public ContextNotActiveException(String str, Throwable th) {
        super(str, th);
    }
}

package javax.enterprise.context;

/* loaded from: classes2.dex */
public class ContextException extends RuntimeException {
    private static final long serialVersionUID = -3599813072560026919L;

    public ContextException() {
    }

    public ContextException(String str) {
        super(str);
    }

    public ContextException(Throwable th) {
        super(th);
    }

    public ContextException(String str, Throwable th) {
        super(str, th);
    }
}

package javax.enterprise.inject;

/* loaded from: classes2.dex */
public class InjectionException extends RuntimeException {
    private static final long serialVersionUID = -2132733164534544788L;

    public InjectionException() {
    }

    public InjectionException(String str, Throwable th) {
        super(str, th);
    }

    public InjectionException(String str) {
        super(str);
    }

    public InjectionException(Throwable th) {
        super(th);
    }
}

package javax.enterprise.inject;

/* loaded from: classes2.dex */
public class ResolutionException extends InjectionException {
    private static final long serialVersionUID = -6280627846071966243L;

    public ResolutionException() {
    }

    public ResolutionException(String str, Throwable th) {
        super(str, th);
    }

    public ResolutionException(String str) {
        super(str);
    }

    public ResolutionException(Throwable th) {
        super(th);
    }
}

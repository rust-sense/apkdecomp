package javax.enterprise.inject;

/* loaded from: classes2.dex */
public class AmbiguousResolutionException extends ResolutionException {
    private static final long serialVersionUID = -2132733164534544788L;

    public AmbiguousResolutionException() {
    }

    public AmbiguousResolutionException(String str, Throwable th) {
        super(str, th);
    }

    public AmbiguousResolutionException(String str) {
        super(str);
    }

    public AmbiguousResolutionException(Throwable th) {
        super(th);
    }
}

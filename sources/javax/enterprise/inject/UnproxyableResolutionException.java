package javax.enterprise.inject;

/* loaded from: classes2.dex */
public class UnproxyableResolutionException extends ResolutionException {
    private static final long serialVersionUID = 1667539354548135465L;

    public UnproxyableResolutionException() {
    }

    public UnproxyableResolutionException(String str, Throwable th) {
        super(str, th);
    }

    public UnproxyableResolutionException(String str) {
        super(str);
    }

    public UnproxyableResolutionException(Throwable th) {
        super(th);
    }
}

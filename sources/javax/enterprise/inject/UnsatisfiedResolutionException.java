package javax.enterprise.inject;

/* loaded from: classes2.dex */
public class UnsatisfiedResolutionException extends ResolutionException {
    private static final long serialVersionUID = 5350603312442756709L;

    public UnsatisfiedResolutionException() {
    }

    public UnsatisfiedResolutionException(String str, Throwable th) {
        super(str, th);
    }

    public UnsatisfiedResolutionException(String str) {
        super(str);
    }

    public UnsatisfiedResolutionException(Throwable th) {
        super(th);
    }
}

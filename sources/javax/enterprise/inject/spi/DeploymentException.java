package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public class DeploymentException extends RuntimeException {
    private static final long serialVersionUID = 2604707587772339984L;

    public DeploymentException(String str, Throwable th) {
        super(str, th);
    }

    public DeploymentException(String str) {
        super(str);
    }

    public DeploymentException(Throwable th) {
        super(th);
    }
}

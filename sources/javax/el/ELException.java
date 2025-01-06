package javax.el;

/* loaded from: classes2.dex */
public class ELException extends RuntimeException {
    public ELException() {
    }

    public ELException(String str) {
        super(str);
    }

    public ELException(Throwable th) {
        super(th);
    }

    public ELException(String str, Throwable th) {
        super(str, th);
    }
}

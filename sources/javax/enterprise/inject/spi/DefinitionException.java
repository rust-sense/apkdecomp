package javax.enterprise.inject.spi;

/* loaded from: classes2.dex */
public class DefinitionException extends RuntimeException {
    private static final long serialVersionUID = -2699170549782567339L;

    public DefinitionException(String str, Throwable th) {
        super(str, th);
    }

    public DefinitionException(String str) {
        super(str);
    }

    public DefinitionException(Throwable th) {
        super(th);
    }
}

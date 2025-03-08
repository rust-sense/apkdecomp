package javax.enterprise.context;

/* loaded from: classes2.dex */
public class NonexistentConversationException extends ContextException {
    private static final long serialVersionUID = -3599813072560026919L;

    public NonexistentConversationException() {
    }

    public NonexistentConversationException(String str) {
        super(str);
    }

    public NonexistentConversationException(Throwable th) {
        super(th);
    }

    public NonexistentConversationException(String str, Throwable th) {
        super(str, th);
    }
}

package javax.enterprise.context;

/* loaded from: classes2.dex */
public class BusyConversationException extends ContextException {
    private static final long serialVersionUID = -3599813072560026919L;

    public BusyConversationException() {
    }

    public BusyConversationException(String str) {
        super(str);
    }

    public BusyConversationException(Throwable th) {
        super(th);
    }

    public BusyConversationException(String str, Throwable th) {
        super(str, th);
    }
}

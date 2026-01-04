package javax.portlet;

/* loaded from: classes2.dex */
public class ReadOnlyException extends PortletException {
    private static final long serialVersionUID = 1;

    public ReadOnlyException(String str) {
        super(str);
    }

    public ReadOnlyException(String str, Throwable th) {
        super(str, th);
    }

    public ReadOnlyException(Throwable th) {
        super(th);
    }
}

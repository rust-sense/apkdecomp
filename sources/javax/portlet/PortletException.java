package javax.portlet;

/* loaded from: classes2.dex */
public class PortletException extends Exception {
    private static final long serialVersionUID = 1;

    public PortletException() {
    }

    public PortletException(String str) {
        super(str);
    }

    public PortletException(String str, Throwable th) {
        super(str, th);
    }

    public PortletException(Throwable th) {
        super(th);
    }
}

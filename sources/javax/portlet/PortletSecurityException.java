package javax.portlet;

/* loaded from: classes2.dex */
public class PortletSecurityException extends PortletException {
    private static final long serialVersionUID = 1;

    public PortletSecurityException(String str) {
        super(str);
    }

    public PortletSecurityException(String str, Throwable th) {
        super(str, th);
    }

    public PortletSecurityException(Throwable th) {
        super(th);
    }
}

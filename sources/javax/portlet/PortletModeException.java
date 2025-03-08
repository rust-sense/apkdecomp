package javax.portlet;

/* loaded from: classes2.dex */
public class PortletModeException extends PortletException {
    private static final long serialVersionUID = 1;
    private transient PortletMode _mode;

    public PortletMode getMode() {
        return this._mode;
    }

    public PortletModeException(String str, PortletMode portletMode) {
        super(str);
        this._mode = portletMode;
    }

    public PortletModeException(String str, Throwable th, PortletMode portletMode) {
        super(str, th);
        this._mode = portletMode;
    }

    public PortletModeException(Throwable th, PortletMode portletMode) {
        super(th);
        this._mode = portletMode;
    }
}

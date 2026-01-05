package javax.portlet;

import java.util.Locale;

/* loaded from: classes2.dex */
public class PortletMode {
    private String _name;
    public static final PortletMode UNDEFINED = new PortletMode("undefined");
    public static final PortletMode VIEW = new PortletMode("view");
    public static final PortletMode EDIT = new PortletMode("edit");
    public static final PortletMode HELP = new PortletMode("help");

    public String toString() {
        return this._name;
    }

    public PortletMode() {
        this._name = "undefined";
    }

    public PortletMode(String str) {
        if (str == null) {
            throw new IllegalArgumentException("PortletMode name can not be NULL");
        }
        this._name = str.toLowerCase(Locale.ENGLISH);
    }

    public int hashCode() {
        return this._name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof PortletMode) {
            return this._name.equals(((PortletMode) obj)._name);
        }
        return false;
    }
}

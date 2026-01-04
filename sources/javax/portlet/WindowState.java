package javax.portlet;

import io.sentry.ProfilingTraceData;
import java.util.Locale;

/* loaded from: classes2.dex */
public class WindowState {
    private String _name;
    public static final WindowState UNDEFINED = new WindowState("undefined");
    public static final WindowState NORMAL = new WindowState(ProfilingTraceData.TRUNCATION_REASON_NORMAL);
    public static final WindowState MAXIMIZED = new WindowState("maximized");
    public static final WindowState MINIMIZED = new WindowState("minimized");

    public String toString() {
        return this._name;
    }

    public WindowState() {
        this._name = "undefined";
    }

    public WindowState(String str) {
        if (str == null) {
            throw new IllegalArgumentException("WindowState name can not be NULL");
        }
        this._name = str.toLowerCase(Locale.ENGLISH);
    }

    public int hashCode() {
        return this._name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof WindowState) {
            return this._name.equals(((WindowState) obj)._name);
        }
        return false;
    }
}

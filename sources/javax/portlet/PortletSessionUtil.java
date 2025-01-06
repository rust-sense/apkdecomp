package javax.portlet;

/* loaded from: classes2.dex */
public class PortletSessionUtil {
    private static final String PORTLET_SCOPE_NAMESPACE = "javax.portlet.p.";

    public static String decodeAttributeName(String str) {
        int indexOf;
        return (!str.startsWith(PORTLET_SCOPE_NAMESPACE) || (indexOf = str.indexOf(63)) <= -1) ? str : str.substring(indexOf + 1);
    }

    public static int decodeScope(String str) {
        return (!str.startsWith(PORTLET_SCOPE_NAMESPACE) || str.indexOf(63) <= -1) ? 1 : 2;
    }
}

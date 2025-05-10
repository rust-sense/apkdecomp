package javax.portlet;

import java.util.Enumeration;
import java.util.Map;

/* loaded from: classes2.dex */
public interface PortletSession {
    public static final int APPLICATION_SCOPE = 1;
    public static final int PORTLET_SCOPE = 2;

    Object getAttribute(String str);

    Object getAttribute(String str, int i);

    Map<String, Object> getAttributeMap();

    Map<String, Object> getAttributeMap(int i);

    Enumeration<String> getAttributeNames();

    Enumeration<String> getAttributeNames(int i);

    long getCreationTime();

    String getId();

    long getLastAccessedTime();

    int getMaxInactiveInterval();

    PortletContext getPortletContext();

    void invalidate();

    boolean isNew();

    void removeAttribute(String str);

    void removeAttribute(String str, int i);

    void setAttribute(String str, Object obj);

    void setAttribute(String str, Object obj, int i);

    void setMaxInactiveInterval(int i);
}

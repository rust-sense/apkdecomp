package javax.servlet.http;

import java.util.Enumeration;
import javax.servlet.ServletContext;

/* loaded from: classes2.dex */
public interface HttpSession {
    Object getAttribute(String str);

    Enumeration<String> getAttributeNames();

    long getCreationTime();

    String getId();

    long getLastAccessedTime();

    int getMaxInactiveInterval();

    ServletContext getServletContext();

    @Deprecated
    HttpSessionContext getSessionContext();

    @Deprecated
    Object getValue(String str);

    @Deprecated
    String[] getValueNames();

    void invalidate();

    boolean isNew();

    @Deprecated
    void putValue(String str, Object obj);

    void removeAttribute(String str);

    @Deprecated
    void removeValue(String str);

    void setAttribute(String str, Object obj);

    void setMaxInactiveInterval(int i);
}

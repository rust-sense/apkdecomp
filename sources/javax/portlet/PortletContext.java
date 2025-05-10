package javax.portlet;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

/* loaded from: classes2.dex */
public interface PortletContext {
    Object getAttribute(String str);

    Enumeration<String> getAttributeNames();

    ClassLoader getClassLoader();

    Enumeration<String> getContainerRuntimeOptions();

    String getContextPath();

    int getEffectiveMajorVersion();

    int getEffectiveMinorVersion();

    String getInitParameter(String str);

    Enumeration<String> getInitParameterNames();

    int getMajorVersion();

    String getMimeType(String str);

    int getMinorVersion();

    PortletRequestDispatcher getNamedDispatcher(String str);

    String getPortletContextName();

    String getRealPath(String str);

    PortletRequestDispatcher getRequestDispatcher(String str);

    URL getResource(String str) throws MalformedURLException;

    InputStream getResourceAsStream(String str);

    Set<String> getResourcePaths(String str);

    String getServerInfo();

    void log(String str);

    void log(String str, Throwable th);

    void removeAttribute(String str);

    void setAttribute(String str, Object obj);
}

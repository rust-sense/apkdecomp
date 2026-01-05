package javax.portlet.filter;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequestDispatcher;

/* loaded from: classes2.dex */
public class PortletContextWrapper implements PortletContext {
    protected PortletContext wrapped;

    public PortletContext getPortletContext() {
        return this.wrapped;
    }

    public PortletContextWrapper(PortletContext portletContext) {
        if (portletContext == null) {
            throw new IllegalArgumentException("PortletContext to wrap is null");
        }
        this.wrapped = portletContext;
    }

    public void setPortletContext(PortletContext portletContext) {
        if (portletContext == null) {
            throw new IllegalArgumentException("PortletContext to wrap is null");
        }
        this.wrapped = portletContext;
    }

    @Override // javax.portlet.PortletContext
    public String getServerInfo() {
        return this.wrapped.getServerInfo();
    }

    @Override // javax.portlet.PortletContext
    public PortletRequestDispatcher getRequestDispatcher(String str) {
        return this.wrapped.getRequestDispatcher(str);
    }

    @Override // javax.portlet.PortletContext
    public PortletRequestDispatcher getNamedDispatcher(String str) {
        return this.wrapped.getNamedDispatcher(str);
    }

    @Override // javax.portlet.PortletContext
    public InputStream getResourceAsStream(String str) {
        return this.wrapped.getResourceAsStream(str);
    }

    @Override // javax.portlet.PortletContext
    public int getMajorVersion() {
        return this.wrapped.getMajorVersion();
    }

    @Override // javax.portlet.PortletContext
    public int getMinorVersion() {
        return this.wrapped.getMinorVersion();
    }

    @Override // javax.portlet.PortletContext
    public String getMimeType(String str) {
        return this.wrapped.getMimeType(str);
    }

    @Override // javax.portlet.PortletContext
    public String getRealPath(String str) {
        return this.wrapped.getRealPath(str);
    }

    @Override // javax.portlet.PortletContext
    public Set<String> getResourcePaths(String str) {
        return this.wrapped.getResourcePaths(str);
    }

    @Override // javax.portlet.PortletContext
    public URL getResource(String str) throws MalformedURLException {
        return this.wrapped.getResource(str);
    }

    @Override // javax.portlet.PortletContext
    public Object getAttribute(String str) {
        return this.wrapped.getAttribute(str);
    }

    @Override // javax.portlet.PortletContext
    public Enumeration<String> getAttributeNames() {
        return this.wrapped.getAttributeNames();
    }

    @Override // javax.portlet.PortletContext
    public String getInitParameter(String str) {
        return this.wrapped.getInitParameter(str);
    }

    @Override // javax.portlet.PortletContext
    public Enumeration<String> getInitParameterNames() {
        return this.wrapped.getInitParameterNames();
    }

    @Override // javax.portlet.PortletContext
    public void log(String str) {
        this.wrapped.log(str);
    }

    @Override // javax.portlet.PortletContext
    public void log(String str, Throwable th) {
        this.wrapped.log(str, th);
    }

    @Override // javax.portlet.PortletContext
    public void removeAttribute(String str) {
        this.wrapped.removeAttribute(str);
    }

    @Override // javax.portlet.PortletContext
    public void setAttribute(String str, Object obj) {
        this.wrapped.setAttribute(str, obj);
    }

    @Override // javax.portlet.PortletContext
    public String getPortletContextName() {
        return this.wrapped.getPortletContextName();
    }

    @Override // javax.portlet.PortletContext
    public Enumeration<String> getContainerRuntimeOptions() {
        return this.wrapped.getContainerRuntimeOptions();
    }

    @Override // javax.portlet.PortletContext
    public int getEffectiveMajorVersion() {
        return this.wrapped.getEffectiveMajorVersion();
    }

    @Override // javax.portlet.PortletContext
    public int getEffectiveMinorVersion() {
        return this.wrapped.getEffectiveMinorVersion();
    }

    @Override // javax.portlet.PortletContext
    public String getContextPath() {
        return this.wrapped.getContextPath();
    }

    @Override // javax.portlet.PortletContext
    public ClassLoader getClassLoader() {
        return this.wrapped.getClassLoader();
    }
}

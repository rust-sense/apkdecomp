package javax.portlet.filter;

import java.util.Enumeration;
import javax.portlet.PortalContext;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/* loaded from: classes2.dex */
public class PortalContextWrapper implements PortalContext {
    protected PortalContext wrapped;

    public PortalContext getWrapped() {
        return this.wrapped;
    }

    public PortalContextWrapper(PortalContext portalContext) {
        if (portalContext == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portalContext;
    }

    public void setWrapped(PortalContext portalContext) {
        if (portalContext == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portalContext;
    }

    @Override // javax.portlet.PortalContext
    public String getProperty(String str) {
        return this.wrapped.getProperty(str);
    }

    @Override // javax.portlet.PortalContext
    public Enumeration<String> getPropertyNames() {
        return this.wrapped.getPropertyNames();
    }

    @Override // javax.portlet.PortalContext
    public Enumeration<PortletMode> getSupportedPortletModes() {
        return this.wrapped.getSupportedPortletModes();
    }

    @Override // javax.portlet.PortalContext
    public Enumeration<WindowState> getSupportedWindowStates() {
        return this.wrapped.getSupportedWindowStates();
    }

    @Override // javax.portlet.PortalContext
    public String getPortalInfo() {
        return this.wrapped.getPortalInfo();
    }
}

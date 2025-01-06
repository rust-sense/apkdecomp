package javax.portlet.filter;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderParameters;
import javax.portlet.WindowState;
import javax.servlet.http.Cookie;

/* loaded from: classes2.dex */
public class PortletRequestWrapper extends RenderStateWrapper implements PortletRequest {
    public PortletRequestWrapper(PortletRequest portletRequest) {
        super(portletRequest);
    }

    public PortletRequest getRequest() {
        return (PortletRequest) super.getWrapped();
    }

    public void setRequest(PortletRequest portletRequest) {
        super.setWrapped(portletRequest);
    }

    @Override // javax.portlet.PortletRequest
    public Object getAttribute(String str) {
        return ((PortletRequest) this.wrapped).getAttribute(str);
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<String> getAttributeNames() {
        return ((PortletRequest) this.wrapped).getAttributeNames();
    }

    @Override // javax.portlet.PortletRequest
    public String getAuthType() {
        return ((PortletRequest) this.wrapped).getAuthType();
    }

    @Override // javax.portlet.PortletRequest
    public String getContextPath() {
        return ((PortletRequest) this.wrapped).getContextPath();
    }

    @Override // javax.portlet.PortletRequest
    public Locale getLocale() {
        return ((PortletRequest) this.wrapped).getLocale();
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<Locale> getLocales() {
        return ((PortletRequest) this.wrapped).getLocales();
    }

    @Override // javax.portlet.filter.RenderStateWrapper, javax.portlet.RenderState
    public RenderParameters getRenderParameters() {
        return ((PortletRequest) this.wrapped).getRenderParameters();
    }

    @Override // javax.portlet.PortletRequest
    @Deprecated
    public String getParameter(String str) {
        return ((PortletRequest) this.wrapped).getParameter(str);
    }

    @Override // javax.portlet.PortletRequest
    @Deprecated
    public Map<String, String[]> getParameterMap() {
        return ((PortletRequest) this.wrapped).getParameterMap();
    }

    @Override // javax.portlet.PortletRequest
    @Deprecated
    public Enumeration<String> getParameterNames() {
        return ((PortletRequest) this.wrapped).getParameterNames();
    }

    @Override // javax.portlet.PortletRequest
    @Deprecated
    public String[] getParameterValues(String str) {
        return ((PortletRequest) this.wrapped).getParameterValues(str);
    }

    @Override // javax.portlet.PortletRequest
    public PortalContext getPortalContext() {
        return ((PortletRequest) this.wrapped).getPortalContext();
    }

    @Override // javax.portlet.PortletRequest
    public PortletContext getPortletContext() {
        return ((PortletRequest) this.wrapped).getPortletContext();
    }

    @Override // javax.portlet.filter.RenderStateWrapper, javax.portlet.RenderState
    public PortletMode getPortletMode() {
        return ((PortletRequest) this.wrapped).getPortletMode();
    }

    @Override // javax.portlet.PortletRequest
    public PortletSession getPortletSession() {
        return ((PortletRequest) this.wrapped).getPortletSession();
    }

    @Override // javax.portlet.PortletRequest
    public PortletSession getPortletSession(boolean z) {
        return ((PortletRequest) this.wrapped).getPortletSession(z);
    }

    @Override // javax.portlet.PortletRequest
    public PortletPreferences getPreferences() {
        return ((PortletRequest) this.wrapped).getPreferences();
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<String> getProperties(String str) {
        return ((PortletRequest) this.wrapped).getProperties(str);
    }

    @Override // javax.portlet.PortletRequest
    public String getProperty(String str) {
        return ((PortletRequest) this.wrapped).getProperty(str);
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<String> getPropertyNames() {
        return ((PortletRequest) this.wrapped).getPropertyNames();
    }

    @Override // javax.portlet.PortletRequest
    public String getRemoteUser() {
        return ((PortletRequest) this.wrapped).getRemoteUser();
    }

    @Override // javax.portlet.PortletRequest
    public String getRequestedSessionId() {
        return ((PortletRequest) this.wrapped).getRequestedSessionId();
    }

    @Override // javax.portlet.PortletRequest
    public String getResponseContentType() {
        return ((PortletRequest) this.wrapped).getResponseContentType();
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<String> getResponseContentTypes() {
        return ((PortletRequest) this.wrapped).getResponseContentTypes();
    }

    @Override // javax.portlet.PortletRequest
    public String getScheme() {
        return ((PortletRequest) this.wrapped).getScheme();
    }

    @Override // javax.portlet.PortletRequest
    public String getServerName() {
        return ((PortletRequest) this.wrapped).getServerName();
    }

    @Override // javax.portlet.PortletRequest
    public int getServerPort() {
        return ((PortletRequest) this.wrapped).getServerPort();
    }

    @Override // javax.portlet.PortletRequest
    public Principal getUserPrincipal() {
        return ((PortletRequest) this.wrapped).getUserPrincipal();
    }

    @Override // javax.portlet.PortletRequest
    public String getWindowID() {
        return ((PortletRequest) this.wrapped).getWindowID();
    }

    @Override // javax.portlet.filter.RenderStateWrapper, javax.portlet.RenderState
    public WindowState getWindowState() {
        return ((PortletRequest) this.wrapped).getWindowState();
    }

    @Override // javax.portlet.PortletRequest
    public boolean isPortletModeAllowed(PortletMode portletMode) {
        return ((PortletRequest) this.wrapped).isPortletModeAllowed(portletMode);
    }

    @Override // javax.portlet.PortletRequest
    public boolean isRequestedSessionIdValid() {
        return ((PortletRequest) this.wrapped).isRequestedSessionIdValid();
    }

    @Override // javax.portlet.PortletRequest
    public boolean isSecure() {
        return ((PortletRequest) this.wrapped).isSecure();
    }

    @Override // javax.portlet.PortletRequest
    public boolean isUserInRole(String str) {
        return ((PortletRequest) this.wrapped).isUserInRole(str);
    }

    @Override // javax.portlet.PortletRequest
    public boolean isWindowStateAllowed(WindowState windowState) {
        return ((PortletRequest) this.wrapped).isWindowStateAllowed(windowState);
    }

    @Override // javax.portlet.PortletRequest
    public void removeAttribute(String str) {
        ((PortletRequest) this.wrapped).removeAttribute(str);
    }

    @Override // javax.portlet.PortletRequest
    public void setAttribute(String str, Object obj) {
        ((PortletRequest) this.wrapped).setAttribute(str, obj);
    }

    @Override // javax.portlet.PortletRequest
    public Cookie[] getCookies() {
        return ((PortletRequest) this.wrapped).getCookies();
    }

    @Override // javax.portlet.PortletRequest
    @Deprecated
    public Map<String, String[]> getPrivateParameterMap() {
        return ((PortletRequest) this.wrapped).getPrivateParameterMap();
    }

    @Override // javax.portlet.PortletRequest
    @Deprecated
    public Map<String, String[]> getPublicParameterMap() {
        return ((PortletRequest) this.wrapped).getPublicParameterMap();
    }

    @Override // javax.portlet.PortletRequest
    public String getUserAgent() {
        return ((PortletRequest) this.wrapped).getUserAgent();
    }
}

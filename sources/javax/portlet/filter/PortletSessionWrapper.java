package javax.portlet.filter;

import java.util.Enumeration;
import java.util.Map;
import javax.portlet.PortletContext;
import javax.portlet.PortletSession;

/* loaded from: classes2.dex */
public class PortletSessionWrapper implements PortletSession {
    protected PortletSession wrapped;

    public PortletSession getWrapped() {
        return this.wrapped;
    }

    public PortletSessionWrapper(PortletSession portletSession) {
        if (portletSession == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletSession;
    }

    public void setWrapped(PortletSession portletSession) {
        if (portletSession == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletSession;
    }

    @Override // javax.portlet.PortletSession
    public Object getAttribute(String str) {
        return this.wrapped.getAttribute(str);
    }

    @Override // javax.portlet.PortletSession
    public Object getAttribute(String str, int i) {
        return this.wrapped.getAttribute(str, i);
    }

    @Override // javax.portlet.PortletSession
    public Enumeration<String> getAttributeNames() {
        return this.wrapped.getAttributeNames();
    }

    @Override // javax.portlet.PortletSession
    public Enumeration<String> getAttributeNames(int i) {
        return this.wrapped.getAttributeNames(i);
    }

    @Override // javax.portlet.PortletSession
    public long getCreationTime() {
        return this.wrapped.getCreationTime();
    }

    @Override // javax.portlet.PortletSession
    public String getId() {
        return this.wrapped.getId();
    }

    @Override // javax.portlet.PortletSession
    public long getLastAccessedTime() {
        return this.wrapped.getLastAccessedTime();
    }

    @Override // javax.portlet.PortletSession
    public int getMaxInactiveInterval() {
        return this.wrapped.getMaxInactiveInterval();
    }

    @Override // javax.portlet.PortletSession
    public void invalidate() {
        this.wrapped.invalidate();
    }

    @Override // javax.portlet.PortletSession
    public boolean isNew() {
        return this.wrapped.isNew();
    }

    @Override // javax.portlet.PortletSession
    public void removeAttribute(String str) {
        this.wrapped.removeAttribute(str);
    }

    @Override // javax.portlet.PortletSession
    public void removeAttribute(String str, int i) {
        this.wrapped.removeAttribute(str, i);
    }

    @Override // javax.portlet.PortletSession
    public void setAttribute(String str, Object obj) {
        this.wrapped.setAttribute(str, obj);
    }

    @Override // javax.portlet.PortletSession
    public void setAttribute(String str, Object obj, int i) {
        this.wrapped.setAttribute(str, obj, i);
    }

    @Override // javax.portlet.PortletSession
    public void setMaxInactiveInterval(int i) {
        this.wrapped.setMaxInactiveInterval(i);
    }

    @Override // javax.portlet.PortletSession
    public PortletContext getPortletContext() {
        return this.wrapped.getPortletContext();
    }

    @Override // javax.portlet.PortletSession
    public Map<String, Object> getAttributeMap() {
        return this.wrapped.getAttributeMap();
    }

    @Override // javax.portlet.PortletSession
    public Map<String, Object> getAttributeMap(int i) {
        return this.wrapped.getAttributeMap(i);
    }
}

package javax.portlet.filter;

import javax.portlet.PortletAsyncContext;
import javax.portlet.PortletAsyncListener;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/* loaded from: classes2.dex */
public class PortletAsyncContextWrapper implements PortletAsyncContext {
    private PortletAsyncContext wrapped;

    public PortletAsyncContext getWrapped() {
        return this.wrapped;
    }

    public PortletAsyncContextWrapper(PortletAsyncContext portletAsyncContext) {
        if (portletAsyncContext == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletAsyncContext;
    }

    public void setWrapped(PortletAsyncContext portletAsyncContext) {
        if (portletAsyncContext == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletAsyncContext;
    }

    @Override // javax.portlet.PortletAsyncContext
    public void addListener(PortletAsyncListener portletAsyncListener) throws IllegalStateException {
        this.wrapped.addListener(portletAsyncListener);
    }

    @Override // javax.portlet.PortletAsyncContext
    public void addListener(PortletAsyncListener portletAsyncListener, ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IllegalStateException {
        this.wrapped.addListener(portletAsyncListener, resourceRequest, resourceResponse);
    }

    @Override // javax.portlet.PortletAsyncContext
    public void complete() throws IllegalStateException {
        this.wrapped.complete();
    }

    @Override // javax.portlet.PortletAsyncContext
    public <T extends PortletAsyncListener> T createPortletAsyncListener(Class<T> cls) throws PortletException {
        return (T) this.wrapped.createPortletAsyncListener(cls);
    }

    @Override // javax.portlet.PortletAsyncContext
    public void dispatch() throws IllegalStateException {
        this.wrapped.dispatch();
    }

    @Override // javax.portlet.PortletAsyncContext
    public void dispatch(String str) throws IllegalStateException {
        this.wrapped.dispatch(str);
    }

    @Override // javax.portlet.PortletAsyncContext
    public ResourceRequest getResourceRequest() throws IllegalStateException {
        return this.wrapped.getResourceRequest();
    }

    @Override // javax.portlet.PortletAsyncContext
    public ResourceResponse getResourceResponse() throws IllegalStateException {
        return this.wrapped.getResourceResponse();
    }

    @Override // javax.portlet.PortletAsyncContext
    public long getTimeout() {
        return this.wrapped.getTimeout();
    }

    @Override // javax.portlet.PortletAsyncContext
    public boolean hasOriginalRequestAndResponse() {
        return this.wrapped.hasOriginalRequestAndResponse();
    }

    @Override // javax.portlet.PortletAsyncContext
    public void setTimeout(long j) {
        this.wrapped.setTimeout(j);
    }

    @Override // javax.portlet.PortletAsyncContext
    public void start(Runnable runnable) throws IllegalStateException {
        this.wrapped.start(runnable);
    }
}

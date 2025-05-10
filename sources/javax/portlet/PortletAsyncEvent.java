package javax.portlet;

/* loaded from: classes2.dex */
public class PortletAsyncEvent {
    PortletAsyncContext context;
    ResourceRequest request;
    ResourceResponse response;
    Throwable throwable;

    public PortletAsyncContext getPortletAsyncContext() {
        return this.context;
    }

    public ResourceRequest getSuppliedRequest() {
        return this.request;
    }

    public ResourceResponse getSuppliedResponse() {
        return this.response;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public PortletAsyncEvent(PortletAsyncContext portletAsyncContext, ResourceRequest resourceRequest, ResourceResponse resourceResponse, Throwable th) {
        this.context = portletAsyncContext;
        this.request = resourceRequest;
        this.response = resourceResponse;
        this.throwable = th;
    }

    public PortletAsyncEvent(PortletAsyncContext portletAsyncContext, ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
        this.context = portletAsyncContext;
        this.request = resourceRequest;
        this.response = resourceResponse;
        this.throwable = null;
    }

    public PortletAsyncEvent(PortletAsyncContext portletAsyncContext, Throwable th) {
        this.context = portletAsyncContext;
        this.request = null;
        this.response = null;
        this.throwable = th;
    }

    public PortletAsyncEvent(PortletAsyncContext portletAsyncContext) {
        this.context = portletAsyncContext;
        this.request = null;
        this.response = null;
        this.throwable = null;
    }
}

package javax.portlet.filter;

import java.util.Map;
import javax.portlet.ClientDataRequest;
import javax.portlet.PortletAsyncContext;
import javax.portlet.ResourceParameters;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.DispatcherType;

/* loaded from: classes2.dex */
public class ResourceRequestWrapper extends ClientDataRequestWrapper implements ResourceRequest {
    public ResourceRequestWrapper(ResourceRequest resourceRequest) {
        super(resourceRequest);
    }

    @Override // javax.portlet.filter.ClientDataRequestWrapper, javax.portlet.filter.PortletRequestWrapper
    public ResourceRequest getRequest() {
        return (ResourceRequest) super.getRequest();
    }

    public void setRequest(ResourceRequest resourceRequest) {
        super.setRequest((ClientDataRequest) resourceRequest);
    }

    @Override // javax.portlet.ResourceRequest
    public String getETag() {
        return ((ResourceRequest) this.wrapped).getETag();
    }

    @Override // javax.portlet.ResourceRequest
    public String getResourceID() {
        return ((ResourceRequest) this.wrapped).getResourceID();
    }

    @Override // javax.portlet.ResourceRequest
    @Deprecated
    public Map<String, String[]> getPrivateRenderParameterMap() {
        return ((ResourceRequest) this.wrapped).getPrivateRenderParameterMap();
    }

    @Override // javax.portlet.ResourceRequest
    public String getCacheability() {
        return ((ResourceRequest) this.wrapped).getCacheability();
    }

    @Override // javax.portlet.ResourceRequest
    public ResourceParameters getResourceParameters() {
        return ((ResourceRequest) this.wrapped).getResourceParameters();
    }

    @Override // javax.portlet.ResourceRequest
    public PortletAsyncContext startPortletAsync() throws IllegalStateException {
        return ((ResourceRequest) this.wrapped).startPortletAsync();
    }

    @Override // javax.portlet.ResourceRequest
    public PortletAsyncContext startPortletAsync(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IllegalStateException {
        return ((ResourceRequest) this.wrapped).startPortletAsync(resourceRequest, resourceResponse);
    }

    @Override // javax.portlet.ResourceRequest
    public boolean isAsyncStarted() {
        return ((ResourceRequest) this.wrapped).isAsyncStarted();
    }

    @Override // javax.portlet.ResourceRequest
    public boolean isAsyncSupported() {
        return ((ResourceRequest) this.wrapped).isAsyncSupported();
    }

    @Override // javax.portlet.ResourceRequest
    public PortletAsyncContext getPortletAsyncContext() {
        return ((ResourceRequest) this.wrapped).getPortletAsyncContext();
    }

    @Override // javax.portlet.ResourceRequest
    public DispatcherType getDispatcherType() {
        return ((ResourceRequest) this.wrapped).getDispatcherType();
    }
}

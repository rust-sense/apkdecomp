package javax.portlet.filter;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

/* loaded from: classes2.dex */
public class RenderRequestWrapper extends PortletRequestWrapper implements RenderRequest {
    public RenderRequestWrapper(RenderRequest renderRequest) {
        super(renderRequest);
    }

    @Override // javax.portlet.filter.PortletRequestWrapper
    public RenderRequest getRequest() {
        return (RenderRequest) super.getRequest();
    }

    public void setRequest(RenderRequest renderRequest) {
        super.setRequest((PortletRequest) renderRequest);
    }

    @Override // javax.portlet.RenderRequest
    public String getETag() {
        return ((RenderRequest) this.wrapped).getETag();
    }
}

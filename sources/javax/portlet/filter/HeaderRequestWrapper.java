package javax.portlet.filter;

import javax.portlet.HeaderRequest;
import javax.portlet.RenderRequest;

/* loaded from: classes2.dex */
public class HeaderRequestWrapper extends RenderRequestWrapper implements HeaderRequest {
    public HeaderRequestWrapper(HeaderRequest headerRequest) {
        super(headerRequest);
    }

    @Override // javax.portlet.filter.RenderRequestWrapper, javax.portlet.filter.PortletRequestWrapper
    public HeaderRequest getRequest() {
        return (HeaderRequest) this.wrapped;
    }

    public void setRequest(HeaderRequest headerRequest) {
        super.setRequest((RenderRequest) headerRequest);
    }
}

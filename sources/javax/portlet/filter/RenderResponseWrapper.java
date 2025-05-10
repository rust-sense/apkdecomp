package javax.portlet.filter;

import java.util.Collection;
import javax.portlet.MimeResponse;
import javax.portlet.PortletMode;
import javax.portlet.RenderResponse;

/* loaded from: classes2.dex */
public class RenderResponseWrapper extends MimeResponseWrapper implements RenderResponse {
    public RenderResponseWrapper(RenderResponse renderResponse) {
        super(renderResponse);
    }

    @Override // javax.portlet.filter.MimeResponseWrapper, javax.portlet.filter.PortletResponseWrapper
    public RenderResponse getResponse() {
        return (RenderResponse) this.response;
    }

    public void setResponse(RenderResponse renderResponse) {
        super.setResponse((MimeResponse) renderResponse);
    }

    @Override // javax.portlet.RenderResponse
    @Deprecated
    public void setTitle(String str) {
        ((RenderResponse) this.response).setTitle(str);
    }

    @Override // javax.portlet.RenderResponse
    public void setNextPossiblePortletModes(Collection<? extends PortletMode> collection) {
        ((RenderResponse) this.response).setNextPossiblePortletModes(collection);
    }
}

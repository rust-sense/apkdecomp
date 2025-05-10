package javax.portlet.filter;

import java.io.IOException;
import javax.portlet.ActionResponse;
import javax.portlet.MimeResponse;
import javax.portlet.RenderURL;
import javax.portlet.StateAwareResponse;

/* loaded from: classes2.dex */
public class ActionResponseWrapper extends StateAwareResponseWrapper implements ActionResponse {
    public ActionResponseWrapper(ActionResponse actionResponse) {
        super(actionResponse);
    }

    @Override // javax.portlet.filter.StateAwareResponseWrapper, javax.portlet.filter.PortletResponseWrapper
    public ActionResponse getResponse() {
        return (ActionResponse) this.response;
    }

    public void setResponse(ActionResponse actionResponse) {
        super.setResponse((StateAwareResponse) actionResponse);
    }

    @Override // javax.portlet.ActionResponse
    public void sendRedirect(String str) throws IOException {
        ((ActionResponse) this.response).sendRedirect(str);
    }

    @Override // javax.portlet.ActionResponse
    public void sendRedirect(String str, String str2) throws IOException {
        ((ActionResponse) this.response).sendRedirect(str, str2);
    }

    @Override // javax.portlet.ActionResponse
    public RenderURL createRedirectURL(MimeResponse.Copy copy) throws IllegalStateException {
        return ((ActionResponse) this.response).createRedirectURL(copy);
    }
}

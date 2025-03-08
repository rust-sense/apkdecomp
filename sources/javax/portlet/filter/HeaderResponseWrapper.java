package javax.portlet.filter;

import javax.portlet.HeaderResponse;
import javax.portlet.MimeResponse;

/* loaded from: classes2.dex */
public class HeaderResponseWrapper extends MimeResponseWrapper implements HeaderResponse {
    public HeaderResponseWrapper(HeaderResponse headerResponse) {
        super(headerResponse);
    }

    @Override // javax.portlet.filter.MimeResponseWrapper, javax.portlet.filter.PortletResponseWrapper
    public HeaderResponse getResponse() {
        return (HeaderResponse) this.response;
    }

    public void setResponse(HeaderResponse headerResponse) {
        super.setResponse((MimeResponse) headerResponse);
    }

    @Override // javax.portlet.HeaderResponse
    public void setTitle(String str) {
        ((HeaderResponse) this.response).setTitle(str);
    }

    @Override // javax.portlet.HeaderResponse
    public void addDependency(String str, String str2, String str3) {
        ((HeaderResponse) this.response).addDependency(str, str2, str3);
    }

    @Override // javax.portlet.HeaderResponse
    public void addDependency(String str, String str2, String str3, String str4) {
        ((HeaderResponse) this.response).addDependency(str, str2, str3, str4);
    }
}

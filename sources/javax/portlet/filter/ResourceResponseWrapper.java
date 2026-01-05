package javax.portlet.filter;

import java.util.Locale;
import javax.portlet.MimeResponse;
import javax.portlet.ResourceResponse;

/* loaded from: classes2.dex */
public class ResourceResponseWrapper extends MimeResponseWrapper implements ResourceResponse {
    public ResourceResponseWrapper(ResourceResponse resourceResponse) {
        super(resourceResponse);
    }

    @Override // javax.portlet.filter.MimeResponseWrapper, javax.portlet.filter.PortletResponseWrapper
    public ResourceResponse getResponse() {
        return (ResourceResponse) this.response;
    }

    public void setResponse(ResourceResponse resourceResponse) {
        super.setResponse((MimeResponse) resourceResponse);
    }

    @Override // javax.portlet.ResourceResponse
    public void setCharacterEncoding(String str) {
        ((ResourceResponse) this.response).setCharacterEncoding(str);
    }

    @Override // javax.portlet.ResourceResponse
    public void setLocale(Locale locale) {
        ((ResourceResponse) this.response).setLocale(locale);
    }

    @Override // javax.portlet.ResourceResponse
    public void setContentLength(int i) {
        ((ResourceResponse) this.response).setContentLength(i);
    }

    @Override // javax.portlet.ResourceResponse
    public void setStatus(int i) {
        ((ResourceResponse) this.response).setStatus(i);
    }

    @Override // javax.portlet.ResourceResponse
    public int getStatus() {
        return ((ResourceResponse) this.response).getStatus();
    }

    @Override // javax.portlet.ResourceResponse
    public void setContentLengthLong(long j) {
        ((ResourceResponse) this.response).setContentLengthLong(j);
    }
}

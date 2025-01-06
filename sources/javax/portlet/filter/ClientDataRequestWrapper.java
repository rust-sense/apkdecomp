package javax.portlet.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import javax.portlet.ClientDataRequest;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.servlet.http.Part;

/* loaded from: classes2.dex */
public class ClientDataRequestWrapper extends PortletRequestWrapper implements ClientDataRequest {
    public ClientDataRequestWrapper(ClientDataRequest clientDataRequest) {
        super(clientDataRequest);
    }

    @Override // javax.portlet.filter.PortletRequestWrapper
    public ClientDataRequest getRequest() {
        return (ClientDataRequest) super.getRequest();
    }

    public void setRequest(ClientDataRequest clientDataRequest) {
        super.setRequest((PortletRequest) clientDataRequest);
    }

    @Override // javax.portlet.ClientDataRequest
    public InputStream getPortletInputStream() throws IOException {
        return ((ClientDataRequest) this.wrapped).getPortletInputStream();
    }

    @Override // javax.portlet.ClientDataRequest
    public void setCharacterEncoding(String str) throws UnsupportedEncodingException {
        ((ClientDataRequest) this.wrapped).setCharacterEncoding(str);
    }

    @Override // javax.portlet.ClientDataRequest
    public BufferedReader getReader() throws UnsupportedEncodingException, IOException {
        return ((ClientDataRequest) this.wrapped).getReader();
    }

    @Override // javax.portlet.ClientDataRequest
    public String getCharacterEncoding() {
        return ((ClientDataRequest) this.wrapped).getCharacterEncoding();
    }

    @Override // javax.portlet.ClientDataRequest
    public String getContentType() {
        return ((ClientDataRequest) this.wrapped).getContentType();
    }

    @Override // javax.portlet.ClientDataRequest
    public int getContentLength() {
        return ((ClientDataRequest) this.wrapped).getContentLength();
    }

    @Override // javax.portlet.ClientDataRequest
    public long getContentLengthLong() {
        return ((ClientDataRequest) this.wrapped).getContentLengthLong();
    }

    @Override // javax.portlet.ClientDataRequest
    public String getMethod() {
        return ((ClientDataRequest) this.wrapped).getMethod();
    }

    @Override // javax.portlet.ClientDataRequest
    public Part getPart(String str) throws IOException, PortletException {
        return ((ClientDataRequest) this.wrapped).getPart(str);
    }

    @Override // javax.portlet.ClientDataRequest
    public Collection<Part> getParts() throws IOException, PortletException {
        return ((ClientDataRequest) this.wrapped).getParts();
    }
}

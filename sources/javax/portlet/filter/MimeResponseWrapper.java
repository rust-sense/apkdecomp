package javax.portlet.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import javax.portlet.ActionURL;
import javax.portlet.CacheControl;
import javax.portlet.MimeResponse;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderURL;
import javax.portlet.ResourceURL;

/* loaded from: classes2.dex */
public class MimeResponseWrapper extends PortletResponseWrapper implements MimeResponse {
    public MimeResponseWrapper(MimeResponse mimeResponse) {
        super(mimeResponse);
    }

    @Override // javax.portlet.filter.PortletResponseWrapper
    public MimeResponse getResponse() {
        return (MimeResponse) super.getResponse();
    }

    public void setResponse(MimeResponse mimeResponse) {
        super.setResponse((PortletResponse) mimeResponse);
    }

    @Override // javax.portlet.MimeResponse
    public void flushBuffer() throws IOException {
        ((MimeResponse) this.response).flushBuffer();
    }

    @Override // javax.portlet.MimeResponse
    public int getBufferSize() {
        return ((MimeResponse) this.response).getBufferSize();
    }

    @Override // javax.portlet.MimeResponse
    public String getCharacterEncoding() {
        return ((MimeResponse) this.response).getCharacterEncoding();
    }

    @Override // javax.portlet.MimeResponse
    public String getContentType() {
        return ((MimeResponse) this.response).getContentType();
    }

    @Override // javax.portlet.MimeResponse
    public Locale getLocale() {
        return ((MimeResponse) this.response).getLocale();
    }

    @Override // javax.portlet.MimeResponse
    public OutputStream getPortletOutputStream() throws IOException {
        return ((MimeResponse) this.response).getPortletOutputStream();
    }

    @Override // javax.portlet.MimeResponse
    public PrintWriter getWriter() throws IOException {
        return ((MimeResponse) this.response).getWriter();
    }

    @Override // javax.portlet.MimeResponse
    public boolean isCommitted() {
        return ((MimeResponse) this.response).isCommitted();
    }

    @Override // javax.portlet.MimeResponse
    public void reset() {
        ((MimeResponse) this.response).reset();
    }

    @Override // javax.portlet.MimeResponse
    public void resetBuffer() {
        ((MimeResponse) this.response).resetBuffer();
    }

    @Override // javax.portlet.MimeResponse
    public void setBufferSize(int i) {
        ((MimeResponse) this.response).setBufferSize(i);
    }

    @Override // javax.portlet.MimeResponse
    public CacheControl getCacheControl() {
        return ((MimeResponse) this.response).getCacheControl();
    }

    @Override // javax.portlet.MimeResponse
    public <T extends PortletURL & ActionURL> T createActionURL() throws IllegalStateException {
        return (T) ((MimeResponse) this.response).createActionURL();
    }

    @Override // javax.portlet.MimeResponse
    public ActionURL createActionURL(MimeResponse.Copy copy) throws IllegalStateException {
        return ((MimeResponse) this.response).createActionURL(copy);
    }

    @Override // javax.portlet.MimeResponse
    public <T extends PortletURL & RenderURL> T createRenderURL() throws IllegalStateException {
        return (T) ((MimeResponse) this.response).createRenderURL();
    }

    @Override // javax.portlet.MimeResponse
    public RenderURL createRenderURL(MimeResponse.Copy copy) throws IllegalStateException {
        return ((MimeResponse) this.response).createRenderURL(copy);
    }

    @Override // javax.portlet.MimeResponse
    public ResourceURL createResourceURL() throws IllegalStateException {
        return ((MimeResponse) this.response).createResourceURL();
    }

    @Override // javax.portlet.MimeResponse
    public void setContentType(String str) {
        ((MimeResponse) this.response).setContentType(str);
    }
}

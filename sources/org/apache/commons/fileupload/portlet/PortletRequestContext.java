package org.apache.commons.fileupload.portlet;

import java.io.IOException;
import java.io.InputStream;
import javax.portlet.ActionRequest;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.UploadContext;

/* loaded from: classes3.dex */
public class PortletRequestContext implements UploadContext {
    private final ActionRequest request;

    public PortletRequestContext(ActionRequest actionRequest) {
        this.request = actionRequest;
    }

    @Override // org.apache.commons.fileupload.RequestContext
    public String getCharacterEncoding() {
        return this.request.getCharacterEncoding();
    }

    @Override // org.apache.commons.fileupload.RequestContext
    public String getContentType() {
        return this.request.getContentType();
    }

    @Override // org.apache.commons.fileupload.RequestContext
    @Deprecated
    public int getContentLength() {
        return this.request.getContentLength();
    }

    @Override // org.apache.commons.fileupload.UploadContext
    public long contentLength() {
        try {
            return Long.parseLong(this.request.getProperty(FileUploadBase.CONTENT_LENGTH));
        } catch (NumberFormatException unused) {
            return this.request.getContentLength();
        }
    }

    @Override // org.apache.commons.fileupload.RequestContext
    public InputStream getInputStream() throws IOException {
        return this.request.getPortletInputStream();
    }

    public String toString() {
        return String.format("ContentLength=%s, ContentType=%s", Long.valueOf(contentLength()), getContentType());
    }
}

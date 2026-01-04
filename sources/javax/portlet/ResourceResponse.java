package javax.portlet;

import java.util.Locale;
import javax.portlet.MimeResponse;

/* loaded from: classes2.dex */
public interface ResourceResponse extends MimeResponse {
    public static final String HTTP_STATUS_CODE = "portlet.http-status-code";

    @Override // javax.portlet.MimeResponse
    ActionURL createActionURL(MimeResponse.Copy copy);

    @Override // javax.portlet.MimeResponse
    <T extends PortletURL & ActionURL> T createActionURL();

    @Override // javax.portlet.MimeResponse
    <T extends PortletURL & RenderURL> T createRenderURL();

    @Override // javax.portlet.MimeResponse
    RenderURL createRenderURL(MimeResponse.Copy copy);

    @Override // javax.portlet.MimeResponse
    ResourceURL createResourceURL();

    int getStatus();

    void setCharacterEncoding(String str);

    void setContentLength(int i);

    void setContentLengthLong(long j);

    void setLocale(Locale locale);

    void setStatus(int i);
}

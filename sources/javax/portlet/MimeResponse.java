package javax.portlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

/* loaded from: classes2.dex */
public interface MimeResponse extends PortletResponse {
    public static final String CACHE_SCOPE = "portlet.cache-scope";
    public static final String ETAG = "portlet.ETag";
    public static final String EXPIRATION_CACHE = "portlet.expiration-cache";
    public static final String MARKUP_HEAD_ELEMENT = "javax.portlet.markup.head.element";
    public static final String NAMESPACED_RESPONSE = "X-JAVAX-PORTLET-NAMESPACED-RESPONSE";
    public static final String PRIVATE_SCOPE = "portlet.private-scope";
    public static final String PUBLIC_SCOPE = "portlet.public-scope";
    public static final String USE_CACHED_CONTENT = "portlet.use-cached-content";

    public enum Copy {
        NONE,
        ALL,
        PUBLIC
    }

    ActionURL createActionURL(Copy copy);

    <T extends PortletURL & ActionURL> T createActionURL();

    <T extends PortletURL & RenderURL> T createRenderURL();

    RenderURL createRenderURL(Copy copy);

    ResourceURL createResourceURL();

    void flushBuffer() throws IOException;

    int getBufferSize();

    CacheControl getCacheControl();

    String getCharacterEncoding();

    String getContentType();

    Locale getLocale();

    OutputStream getPortletOutputStream() throws IOException;

    PrintWriter getWriter() throws IOException;

    boolean isCommitted();

    void reset();

    void resetBuffer();

    void setBufferSize(int i);

    void setContentType(String str);
}

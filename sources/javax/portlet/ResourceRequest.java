package javax.portlet;

import java.util.Enumeration;
import java.util.Map;
import javax.servlet.DispatcherType;

/* loaded from: classes2.dex */
public interface ResourceRequest extends ClientDataRequest {
    public static final String ETAG = "portlet.ETag";

    String getCacheability();

    DispatcherType getDispatcherType();

    String getETag();

    PortletAsyncContext getPortletAsyncContext();

    @Override // javax.portlet.RenderState
    PortletMode getPortletMode();

    @Deprecated
    Map<String, String[]> getPrivateRenderParameterMap();

    String getResourceID();

    ResourceParameters getResourceParameters();

    @Override // javax.portlet.PortletRequest
    String getResponseContentType();

    @Override // javax.portlet.PortletRequest
    Enumeration<String> getResponseContentTypes();

    @Override // javax.portlet.RenderState
    WindowState getWindowState();

    boolean isAsyncStarted();

    boolean isAsyncSupported();

    PortletAsyncContext startPortletAsync() throws IllegalStateException;

    PortletAsyncContext startPortletAsync(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IllegalStateException;
}

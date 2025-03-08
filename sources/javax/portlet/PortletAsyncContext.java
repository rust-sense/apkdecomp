package javax.portlet;

/* loaded from: classes2.dex */
public interface PortletAsyncContext {
    void addListener(PortletAsyncListener portletAsyncListener) throws IllegalStateException;

    void addListener(PortletAsyncListener portletAsyncListener, ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IllegalStateException;

    void complete() throws IllegalStateException;

    <T extends PortletAsyncListener> T createPortletAsyncListener(Class<T> cls) throws PortletException;

    void dispatch() throws IllegalStateException;

    void dispatch(String str) throws IllegalStateException;

    ResourceRequest getResourceRequest() throws IllegalStateException;

    ResourceResponse getResourceResponse() throws IllegalStateException;

    long getTimeout();

    boolean hasOriginalRequestAndResponse();

    void setTimeout(long j);

    void start(Runnable runnable) throws IllegalStateException;
}

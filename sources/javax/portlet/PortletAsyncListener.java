package javax.portlet;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface PortletAsyncListener {
    void onComplete(PortletAsyncEvent portletAsyncEvent) throws IOException;

    void onError(PortletAsyncEvent portletAsyncEvent) throws IOException;

    void onStartAsync(PortletAsyncEvent portletAsyncEvent) throws IOException;

    void onTimeout(PortletAsyncEvent portletAsyncEvent) throws IOException;
}

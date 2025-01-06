package javax.portlet;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface EventPortlet {
    void processEvent(EventRequest eventRequest, EventResponse eventResponse) throws PortletException, IOException;
}

package javax.portlet;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface Portlet {
    void destroy();

    void init(PortletConfig portletConfig) throws PortletException;

    void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException, IOException;

    void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException;
}

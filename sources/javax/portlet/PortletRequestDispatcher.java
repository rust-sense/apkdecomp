package javax.portlet;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface PortletRequestDispatcher {
    void forward(PortletRequest portletRequest, PortletResponse portletResponse) throws PortletException, IOException;

    void include(PortletRequest portletRequest, PortletResponse portletResponse) throws PortletException, IOException;

    void include(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException;
}

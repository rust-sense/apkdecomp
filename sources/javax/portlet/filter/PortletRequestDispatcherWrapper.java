package javax.portlet.filter;

import java.io.IOException;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/* loaded from: classes2.dex */
public class PortletRequestDispatcherWrapper implements PortletRequestDispatcher {
    protected PortletRequestDispatcher wrapped;

    public PortletRequestDispatcher getWrapped() {
        return this.wrapped;
    }

    public PortletRequestDispatcherWrapper(PortletRequestDispatcher portletRequestDispatcher) {
        if (portletRequestDispatcher == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletRequestDispatcher;
    }

    public void setWrapped(PortletRequestDispatcher portletRequestDispatcher) {
        if (portletRequestDispatcher == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletRequestDispatcher;
    }

    @Override // javax.portlet.PortletRequestDispatcher
    public void include(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        this.wrapped.include(renderRequest, renderResponse);
    }

    @Override // javax.portlet.PortletRequestDispatcher
    public void include(PortletRequest portletRequest, PortletResponse portletResponse) throws PortletException, IOException {
        this.wrapped.include(portletRequest, portletResponse);
    }

    @Override // javax.portlet.PortletRequestDispatcher
    public void forward(PortletRequest portletRequest, PortletResponse portletResponse) throws PortletException, IOException {
        this.wrapped.forward(portletRequest, portletResponse);
    }
}

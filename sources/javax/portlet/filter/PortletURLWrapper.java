package javax.portlet.filter;

import javax.portlet.BaseURL;
import javax.portlet.MutableRenderParameters;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.portlet.annotations.PortletSerializable;

/* loaded from: classes2.dex */
public class PortletURLWrapper extends BaseURLWrapper implements PortletURL {
    public PortletURLWrapper(PortletURL portletURL) {
        super(portletURL);
    }

    @Override // javax.portlet.filter.BaseURLWrapper, javax.portlet.filter.RenderStateWrapper
    public PortletURL getWrapped() {
        return (PortletURL) this.wrapped;
    }

    public void setWrapped(PortletURL portletURL) {
        super.setWrapped((BaseURL) portletURL);
    }

    @Override // javax.portlet.filter.RenderStateWrapper, javax.portlet.RenderState
    public MutableRenderParameters getRenderParameters() {
        return ((PortletURL) this.wrapped).getRenderParameters();
    }

    @Override // javax.portlet.MutableRenderState
    public void setWindowState(WindowState windowState) throws WindowStateException {
        ((PortletURL) this.wrapped).setWindowState(windowState);
    }

    @Override // javax.portlet.MutableRenderState
    public void setPortletMode(PortletMode portletMode) throws PortletModeException {
        ((PortletURL) this.wrapped).setPortletMode(portletMode);
    }

    @Override // javax.portlet.PortletURL
    @Deprecated
    public void removePublicRenderParameter(String str) {
        ((PortletURL) this.wrapped).removePublicRenderParameter(str);
    }

    @Override // javax.portlet.PortletURL
    public void setBeanParameter(PortletSerializable portletSerializable) {
        ((PortletURL) this.wrapped).setBeanParameter(portletSerializable);
    }
}

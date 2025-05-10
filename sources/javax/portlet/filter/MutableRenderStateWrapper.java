package javax.portlet.filter;

import javax.portlet.Mutable;
import javax.portlet.MutableRenderParameters;
import javax.portlet.MutableRenderState;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.RenderState;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

/* loaded from: classes2.dex */
public class MutableRenderStateWrapper extends RenderStateWrapper implements MutableRenderState, Mutable {
    public MutableRenderStateWrapper(MutableRenderState mutableRenderState) {
        super(mutableRenderState);
    }

    @Override // javax.portlet.filter.RenderStateWrapper
    public MutableRenderState getWrapped() {
        return (MutableRenderState) this.wrapped;
    }

    public void setWrapped(MutableRenderState mutableRenderState) {
        super.setWrapped((RenderState) mutableRenderState);
    }

    @Override // javax.portlet.filter.RenderStateWrapper, javax.portlet.RenderState
    public MutableRenderParameters getRenderParameters() {
        return ((MutableRenderState) this.wrapped).getRenderParameters();
    }

    @Override // javax.portlet.MutableRenderState
    public void setWindowState(WindowState windowState) throws WindowStateException {
        ((MutableRenderState) this.wrapped).setWindowState(windowState);
    }

    @Override // javax.portlet.MutableRenderState
    public void setPortletMode(PortletMode portletMode) throws PortletModeException {
        ((MutableRenderState) this.wrapped).setPortletMode(portletMode);
    }
}

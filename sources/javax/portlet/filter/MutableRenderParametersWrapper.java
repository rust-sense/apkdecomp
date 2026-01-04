package javax.portlet.filter;

import javax.portlet.MutablePortletParameters;
import javax.portlet.MutableRenderParameters;

/* loaded from: classes2.dex */
public class MutableRenderParametersWrapper extends MutablePortletParametersWrapper implements MutableRenderParameters {
    public MutableRenderParametersWrapper(MutableRenderParameters mutableRenderParameters) {
        super(mutableRenderParameters);
    }

    @Override // javax.portlet.filter.MutablePortletParametersWrapper, javax.portlet.filter.PortletParametersWrapper
    public MutableRenderParameters getWrapped() {
        return (MutableRenderParameters) this.wrapped;
    }

    public void setWrapped(MutableRenderParameters mutableRenderParameters) {
        super.setWrapped((MutablePortletParameters) mutableRenderParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper, javax.portlet.PortletParameters
    public MutableRenderParameters clone() {
        return ((MutableRenderParameters) this.wrapped).clone();
    }

    @Override // javax.portlet.RenderParameters
    public boolean isPublic(String str) {
        return ((MutableRenderParameters) this.wrapped).isPublic(str);
    }

    @Override // javax.portlet.MutableRenderParameters
    public void clearPrivate() {
        ((MutableRenderParameters) this.wrapped).clearPrivate();
    }

    @Override // javax.portlet.MutableRenderParameters
    public void clearPublic() {
        ((MutableRenderParameters) this.wrapped).clearPublic();
    }
}

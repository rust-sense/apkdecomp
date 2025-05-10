package javax.portlet.filter;

import javax.portlet.MutableRenderParameters;
import javax.portlet.PortletParameters;
import javax.portlet.RenderParameters;

/* loaded from: classes2.dex */
public class RenderParametersWrapper extends PortletParametersWrapper implements RenderParameters {
    public RenderParametersWrapper(RenderParameters renderParameters) {
        super(renderParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper
    public RenderParameters getWrapped() {
        return (RenderParameters) this.wrapped;
    }

    public void setWrapped(RenderParameters renderParameters) {
        super.setWrapped((PortletParameters) renderParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper, javax.portlet.PortletParameters
    public MutableRenderParameters clone() {
        return ((RenderParameters) this.wrapped).clone();
    }

    @Override // javax.portlet.RenderParameters
    public boolean isPublic(String str) {
        return ((RenderParameters) this.wrapped).isPublic(str);
    }
}

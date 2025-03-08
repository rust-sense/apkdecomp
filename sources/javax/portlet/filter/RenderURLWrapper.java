package javax.portlet.filter;

import javax.portlet.PortletURL;
import javax.portlet.RenderURL;

/* loaded from: classes2.dex */
public class RenderURLWrapper extends PortletURLWrapper implements RenderURL {
    public RenderURLWrapper(RenderURL renderURL) {
        super(renderURL);
    }

    @Override // javax.portlet.filter.PortletURLWrapper, javax.portlet.filter.BaseURLWrapper, javax.portlet.filter.RenderStateWrapper
    public RenderURL getWrapped() {
        return (RenderURL) this.wrapped;
    }

    public void setWrapped(RenderURL renderURL) {
        super.setWrapped((PortletURL) renderURL);
    }

    @Override // javax.portlet.RenderURL
    public void setFragmentIdentifier(String str) {
        ((RenderURL) this.wrapped).setFragmentIdentifier(str);
    }

    @Override // javax.portlet.RenderURL
    public String getFragmentIdentifier() {
        return ((RenderURL) this.wrapped).getFragmentIdentifier();
    }
}

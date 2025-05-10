package javax.portlet.filter;

import javax.portlet.ActionURL;
import javax.portlet.MutableActionParameters;
import javax.portlet.PortletURL;

/* loaded from: classes2.dex */
public class ActionURLWrapper extends PortletURLWrapper implements ActionURL {
    public ActionURLWrapper(ActionURL actionURL) {
        super(actionURL);
    }

    @Override // javax.portlet.filter.PortletURLWrapper, javax.portlet.filter.BaseURLWrapper, javax.portlet.filter.RenderStateWrapper
    public ActionURL getWrapped() {
        return (ActionURL) this.wrapped;
    }

    public void setWrapped(ActionURL actionURL) {
        super.setWrapped((PortletURL) actionURL);
    }

    @Override // javax.portlet.ActionURL
    public MutableActionParameters getActionParameters() {
        return ((ActionURL) this.wrapped).getActionParameters();
    }
}

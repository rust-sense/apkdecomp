package javax.portlet.filter;

import javax.portlet.ActionParameters;
import javax.portlet.MutableActionParameters;
import javax.portlet.PortletParameters;

/* loaded from: classes2.dex */
public class ActionParametersWrapper extends PortletParametersWrapper implements ActionParameters {
    public ActionParametersWrapper(ActionParameters actionParameters) {
        super(actionParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper
    public ActionParameters getWrapped() {
        return (ActionParameters) this.wrapped;
    }

    public void setWrapped(ActionParameters actionParameters) {
        super.setWrapped((PortletParameters) actionParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper, javax.portlet.PortletParameters
    public MutableActionParameters clone() {
        return ((ActionParameters) this.wrapped).clone();
    }
}

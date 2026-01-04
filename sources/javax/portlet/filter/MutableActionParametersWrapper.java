package javax.portlet.filter;

import javax.portlet.MutableActionParameters;
import javax.portlet.MutablePortletParameters;

/* loaded from: classes2.dex */
public class MutableActionParametersWrapper extends MutablePortletParametersWrapper implements MutableActionParameters {
    public MutableActionParametersWrapper(MutableActionParameters mutableActionParameters) {
        super(mutableActionParameters);
    }

    @Override // javax.portlet.filter.MutablePortletParametersWrapper, javax.portlet.filter.PortletParametersWrapper
    public MutableActionParameters getWrapped() {
        return (MutableActionParameters) this.wrapped;
    }

    public void setWrapped(MutableActionParameters mutableActionParameters) {
        super.setWrapped((MutablePortletParameters) mutableActionParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper, javax.portlet.PortletParameters
    public MutableActionParameters clone() {
        return ((MutableActionParameters) this.wrapped).clone();
    }
}

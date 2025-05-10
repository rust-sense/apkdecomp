package javax.portlet.filter;

import javax.portlet.MutablePortletParameters;
import javax.portlet.MutableResourceParameters;

/* loaded from: classes2.dex */
public class MutableResourceParametersWrapper extends MutablePortletParametersWrapper implements MutableResourceParameters {
    public MutableResourceParametersWrapper(MutableResourceParameters mutableResourceParameters) {
        super(mutableResourceParameters);
    }

    @Override // javax.portlet.filter.MutablePortletParametersWrapper, javax.portlet.filter.PortletParametersWrapper
    public MutableResourceParameters getWrapped() {
        return (MutableResourceParameters) this.wrapped;
    }

    public void setWrapped(MutableResourceParameters mutableResourceParameters) {
        super.setWrapped((MutablePortletParameters) mutableResourceParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper, javax.portlet.PortletParameters
    public MutableResourceParameters clone() {
        return ((MutableResourceParameters) this.wrapped).clone();
    }
}

package javax.portlet.filter;

import javax.portlet.MutableResourceParameters;
import javax.portlet.PortletParameters;
import javax.portlet.ResourceParameters;

/* loaded from: classes2.dex */
public class ResourceParametersWrapper extends PortletParametersWrapper implements ResourceParameters {
    public ResourceParametersWrapper(ResourceParameters resourceParameters) {
        super(resourceParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper
    public ResourceParameters getWrapped() {
        return (ResourceParameters) this.wrapped;
    }

    public void setWrapped(ResourceParameters resourceParameters) {
        super.setWrapped((PortletParameters) resourceParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper, javax.portlet.PortletParameters
    public MutableResourceParameters clone() {
        return ((ResourceParameters) this.wrapped).clone();
    }
}

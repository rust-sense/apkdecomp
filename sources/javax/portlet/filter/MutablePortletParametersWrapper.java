package javax.portlet.filter;

import javax.portlet.Mutable;
import javax.portlet.MutablePortletParameters;
import javax.portlet.PortletParameters;

/* loaded from: classes2.dex */
public class MutablePortletParametersWrapper extends PortletParametersWrapper implements MutablePortletParameters, Mutable {
    public MutablePortletParametersWrapper(MutablePortletParameters mutablePortletParameters) {
        super(mutablePortletParameters);
    }

    @Override // javax.portlet.filter.PortletParametersWrapper
    public MutablePortletParameters getWrapped() {
        return (MutablePortletParameters) this.wrapped;
    }

    public void setWrapped(MutablePortletParameters mutablePortletParameters) {
        super.setWrapped((PortletParameters) mutablePortletParameters);
    }

    @Override // javax.portlet.MutablePortletParameters
    public String setValue(String str, String str2) {
        return ((MutablePortletParameters) this.wrapped).setValue(str, str2);
    }

    @Override // javax.portlet.MutablePortletParameters
    public String[] setValues(String str, String... strArr) {
        return ((MutablePortletParameters) this.wrapped).setValues(str, strArr);
    }

    @Override // javax.portlet.MutablePortletParameters
    public boolean removeParameter(String str) {
        return ((MutablePortletParameters) this.wrapped).removeParameter(str);
    }

    @Override // javax.portlet.MutablePortletParameters
    public MutablePortletParameters set(PortletParameters portletParameters) {
        return ((MutablePortletParameters) this.wrapped).set(portletParameters);
    }

    @Override // javax.portlet.MutablePortletParameters
    public MutablePortletParameters add(PortletParameters portletParameters) {
        return ((MutablePortletParameters) this.wrapped).add(portletParameters);
    }

    @Override // javax.portlet.MutablePortletParameters
    public void clear() {
        ((MutablePortletParameters) this.wrapped).clear();
    }
}

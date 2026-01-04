package javax.portlet.filter;

import java.util.Set;
import javax.portlet.MutablePortletParameters;
import javax.portlet.PortletParameters;

/* loaded from: classes2.dex */
public class PortletParametersWrapper implements PortletParameters {
    protected PortletParameters wrapped;

    public PortletParameters getWrapped() {
        return this.wrapped;
    }

    public PortletParametersWrapper(PortletParameters portletParameters) {
        if (portletParameters == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletParameters;
    }

    public void setWrapped(PortletParameters portletParameters) {
        if (portletParameters == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletParameters;
    }

    @Override // javax.portlet.PortletParameters
    public String getValue(String str) {
        return this.wrapped.getValue(str);
    }

    @Override // javax.portlet.PortletParameters
    public Set<String> getNames() {
        return this.wrapped.getNames();
    }

    @Override // javax.portlet.PortletParameters
    public String[] getValues(String str) {
        return this.wrapped.getValues(str);
    }

    @Override // javax.portlet.PortletParameters
    public boolean isEmpty() {
        return this.wrapped.isEmpty();
    }

    @Override // javax.portlet.PortletParameters
    public int size() {
        return this.wrapped.size();
    }

    @Override // 
    public MutablePortletParameters clone() {
        return this.wrapped.clone();
    }
}

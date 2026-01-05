package javax.portlet.filter;

import javax.portlet.BaseURL;
import javax.portlet.MutableResourceParameters;
import javax.portlet.ResourceURL;

/* loaded from: classes2.dex */
public class ResourceURLWrapper extends BaseURLWrapper implements ResourceURL {
    public ResourceURLWrapper(ResourceURL resourceURL) {
        super(resourceURL);
    }

    @Override // javax.portlet.filter.BaseURLWrapper, javax.portlet.filter.RenderStateWrapper
    public ResourceURL getWrapped() {
        return (ResourceURL) this.wrapped;
    }

    public void setWrapped(ResourceURL resourceURL) {
        super.setWrapped((BaseURL) resourceURL);
    }

    @Override // javax.portlet.ResourceURL
    public MutableResourceParameters getResourceParameters() {
        return ((ResourceURL) this.wrapped).getResourceParameters();
    }

    @Override // javax.portlet.ResourceURL
    public void setResourceID(String str) {
        ((ResourceURL) this.wrapped).setResourceID(str);
    }

    @Override // javax.portlet.ResourceURL
    public String getResourceID() {
        return ((ResourceURL) this.wrapped).getResourceID();
    }

    @Override // javax.portlet.ResourceURL
    public String getCacheability() {
        return ((ResourceURL) this.wrapped).getCacheability();
    }

    @Override // javax.portlet.ResourceURL
    public void setCacheability(String str) {
        ((ResourceURL) this.wrapped).setCacheability(str);
    }
}

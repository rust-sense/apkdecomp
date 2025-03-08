package javax.portlet.filter;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import javax.portlet.BaseURL;
import javax.portlet.PortletSecurityException;

/* loaded from: classes2.dex */
public class BaseURLWrapper extends RenderStateWrapper implements BaseURL {
    public BaseURLWrapper(BaseURL baseURL) {
        super(baseURL);
    }

    @Override // javax.portlet.filter.RenderStateWrapper
    public BaseURL getWrapped() {
        return (BaseURL) this.wrapped;
    }

    public void setWrapped(BaseURL baseURL) {
        if (baseURL == null) {
            throw new IllegalArgumentException("BaseURL to wrap is null");
        }
        this.wrapped = baseURL;
    }

    @Override // javax.portlet.BaseURL
    @Deprecated
    public void setParameter(String str, String str2) {
        ((BaseURL) this.wrapped).setParameter(str, str2);
    }

    @Override // javax.portlet.BaseURL
    @Deprecated
    public void setParameter(String str, String... strArr) {
        ((BaseURL) this.wrapped).setParameter(str, strArr);
    }

    @Override // javax.portlet.BaseURL
    @Deprecated
    public void setParameters(Map<String, String[]> map) {
        ((BaseURL) this.wrapped).setParameters(map);
    }

    @Override // javax.portlet.BaseURL
    public void setSecure(boolean z) throws PortletSecurityException {
        ((BaseURL) this.wrapped).setSecure(z);
    }

    @Override // javax.portlet.BaseURL
    @Deprecated
    public Map<String, String[]> getParameterMap() {
        return ((BaseURL) this.wrapped).getParameterMap();
    }

    @Override // javax.portlet.BaseURL
    public void write(Writer writer) throws IOException {
        ((BaseURL) this.wrapped).write(writer);
    }

    @Override // javax.portlet.BaseURL
    public void write(Writer writer, boolean z) throws IOException {
        ((BaseURL) this.wrapped).write(writer, z);
    }

    @Override // javax.portlet.BaseURL
    public Appendable append(Appendable appendable) throws IOException {
        return ((BaseURL) this.wrapped).append(appendable);
    }

    @Override // javax.portlet.BaseURL
    public Appendable append(Appendable appendable, boolean z) throws IOException {
        return ((BaseURL) this.wrapped).append(appendable, z);
    }

    @Override // javax.portlet.BaseURL
    public void addProperty(String str, String str2) {
        ((BaseURL) this.wrapped).addProperty(str, str2);
    }

    @Override // javax.portlet.BaseURL
    public void setProperty(String str, String str2) {
        ((BaseURL) this.wrapped).setProperty(str, str2);
    }

    @Override // javax.portlet.BaseURL
    public String toString() {
        return this.wrapped.toString();
    }
}

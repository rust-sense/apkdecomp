package javax.portlet.filter;

import java.util.Collection;
import javax.portlet.PortletResponse;
import javax.servlet.http.Cookie;
import org.w3c.dom.Element;

/* loaded from: classes2.dex */
public class PortletResponseWrapper implements PortletResponse {
    PortletResponse response;

    public PortletResponse getResponse() {
        return this.response;
    }

    public PortletResponseWrapper(PortletResponse portletResponse) {
        if (portletResponse == null) {
            throw new IllegalArgumentException("Response is null");
        }
        this.response = portletResponse;
    }

    @Override // javax.portlet.PortletResponse
    public void addProperty(String str, String str2) {
        this.response.addProperty(str, str2);
    }

    @Override // javax.portlet.PortletResponse
    public String encodeURL(String str) {
        return this.response.encodeURL(str);
    }

    @Override // javax.portlet.PortletResponse
    public String getNamespace() {
        return this.response.getNamespace();
    }

    @Override // javax.portlet.PortletResponse
    public void setProperty(String str, String str2) {
        this.response.setProperty(str, str2);
    }

    public void setResponse(PortletResponse portletResponse) {
        if (portletResponse == null) {
            throw new IllegalArgumentException("Response is null");
        }
        this.response = portletResponse;
    }

    @Override // javax.portlet.PortletResponse
    public void addProperty(String str, Element element) {
        this.response.addProperty(str, element);
    }

    @Override // javax.portlet.PortletResponse
    public Element createElement(String str) {
        return this.response.createElement(str);
    }

    @Override // javax.portlet.PortletResponse
    public void addProperty(Cookie cookie) {
        this.response.addProperty(cookie);
    }

    @Override // javax.portlet.PortletResponse
    public String getProperty(String str) {
        return this.response.getProperty(str);
    }

    @Override // javax.portlet.PortletResponse
    public Collection<String> getPropertyValues(String str) {
        return this.response.getPropertyValues(str);
    }

    @Override // javax.portlet.PortletResponse
    public Collection<String> getPropertyNames() {
        return this.response.getPropertyNames();
    }
}

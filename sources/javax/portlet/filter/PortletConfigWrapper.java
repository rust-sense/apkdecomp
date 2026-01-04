package javax.portlet.filter;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;
import javax.xml.namespace.QName;

/* loaded from: classes2.dex */
public class PortletConfigWrapper implements PortletConfig {
    protected PortletConfig wrapped;

    public PortletConfig getWrapped() {
        return this.wrapped;
    }

    public PortletConfigWrapper(PortletConfig portletConfig) {
        if (portletConfig == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletConfig;
    }

    public void setWrapped(PortletConfig portletConfig) {
        if (portletConfig == null) {
            throw new IllegalArgumentException("Object to wrap is null");
        }
        this.wrapped = portletConfig;
    }

    @Override // javax.portlet.PortletConfig
    public String getPortletName() {
        return this.wrapped.getPortletName();
    }

    @Override // javax.portlet.PortletConfig
    public PortletContext getPortletContext() {
        return this.wrapped.getPortletContext();
    }

    @Override // javax.portlet.PortletConfig
    public ResourceBundle getResourceBundle(Locale locale) {
        return this.wrapped.getResourceBundle(locale);
    }

    @Override // javax.portlet.PortletConfig
    public String getInitParameter(String str) {
        return this.wrapped.getInitParameter(str);
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<String> getInitParameterNames() {
        return this.wrapped.getInitParameterNames();
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<String> getPublicRenderParameterNames() {
        return this.wrapped.getPublicRenderParameterNames();
    }

    @Override // javax.portlet.PortletConfig
    public String getDefaultNamespace() {
        return this.wrapped.getDefaultNamespace();
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<QName> getPublishingEventQNames() {
        return this.wrapped.getPublishingEventQNames();
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<QName> getProcessingEventQNames() {
        return this.wrapped.getProcessingEventQNames();
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<Locale> getSupportedLocales() {
        return this.wrapped.getSupportedLocales();
    }

    @Override // javax.portlet.PortletConfig
    public Map<String, String[]> getContainerRuntimeOptions() {
        return this.wrapped.getContainerRuntimeOptions();
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<PortletMode> getPortletModes(String str) {
        return this.wrapped.getPortletModes(str);
    }

    @Override // javax.portlet.PortletConfig
    public Enumeration<WindowState> getWindowStates(String str) {
        return this.wrapped.getWindowStates(str);
    }

    @Override // javax.portlet.PortletConfig
    public Map<String, QName> getPublicRenderParameterDefinitions() {
        return this.wrapped.getPublicRenderParameterDefinitions();
    }
}

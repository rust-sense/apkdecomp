package javax.portlet;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.namespace.QName;

/* loaded from: classes2.dex */
public interface PortletConfig {
    Map<String, String[]> getContainerRuntimeOptions();

    String getDefaultNamespace();

    String getInitParameter(String str);

    Enumeration<String> getInitParameterNames();

    PortletContext getPortletContext();

    Enumeration<PortletMode> getPortletModes(String str);

    String getPortletName();

    Enumeration<QName> getProcessingEventQNames();

    Map<String, QName> getPublicRenderParameterDefinitions();

    Enumeration<String> getPublicRenderParameterNames();

    Enumeration<QName> getPublishingEventQNames();

    ResourceBundle getResourceBundle(Locale locale);

    Enumeration<Locale> getSupportedLocales();

    Enumeration<WindowState> getWindowStates(String str);
}

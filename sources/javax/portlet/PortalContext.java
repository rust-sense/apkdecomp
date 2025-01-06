package javax.portlet;

import java.util.Enumeration;

/* loaded from: classes2.dex */
public interface PortalContext {
    public static final String MARKUP_HEAD_ELEMENT_SUPPORT = "javax.portlet.markup.head.element.support";

    String getPortalInfo();

    String getProperty(String str);

    Enumeration<String> getPropertyNames();

    Enumeration<PortletMode> getSupportedPortletModes();

    Enumeration<WindowState> getSupportedWindowStates();
}

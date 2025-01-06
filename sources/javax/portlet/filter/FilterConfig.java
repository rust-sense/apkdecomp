package javax.portlet.filter;

import java.util.Enumeration;
import javax.portlet.PortletContext;

/* loaded from: classes2.dex */
public interface FilterConfig {
    String getFilterName();

    String getInitParameter(String str);

    Enumeration<String> getInitParameterNames();

    PortletContext getPortletContext();
}

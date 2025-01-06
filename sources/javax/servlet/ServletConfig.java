package javax.servlet;

import java.util.Enumeration;

/* loaded from: classes2.dex */
public interface ServletConfig {
    String getInitParameter(String str);

    Enumeration<String> getInitParameterNames();

    ServletContext getServletContext();

    String getServletName();
}

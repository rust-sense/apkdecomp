package javax.servlet;

import java.util.EventObject;

/* loaded from: classes2.dex */
public class ServletContextEvent extends EventObject {
    private static final long serialVersionUID = -7501701636134222423L;

    public ServletContextEvent(ServletContext servletContext) {
        super(servletContext);
    }

    public ServletContext getServletContext() {
        return (ServletContext) super.getSource();
    }
}

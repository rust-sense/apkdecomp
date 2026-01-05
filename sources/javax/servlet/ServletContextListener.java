package javax.servlet;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface ServletContextListener extends EventListener {

    /* renamed from: javax.servlet.ServletContextListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$contextDestroyed(ServletContextListener _this, ServletContextEvent servletContextEvent) {
        }

        public static void $default$contextInitialized(ServletContextListener _this, ServletContextEvent servletContextEvent) {
        }
    }

    void contextDestroyed(ServletContextEvent servletContextEvent);

    void contextInitialized(ServletContextEvent servletContextEvent);
}

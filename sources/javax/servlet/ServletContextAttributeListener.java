package javax.servlet;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface ServletContextAttributeListener extends EventListener {

    /* renamed from: javax.servlet.ServletContextAttributeListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$attributeAdded(ServletContextAttributeListener _this, ServletContextAttributeEvent servletContextAttributeEvent) {
        }

        public static void $default$attributeRemoved(ServletContextAttributeListener _this, ServletContextAttributeEvent servletContextAttributeEvent) {
        }

        public static void $default$attributeReplaced(ServletContextAttributeListener _this, ServletContextAttributeEvent servletContextAttributeEvent) {
        }
    }

    void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent);

    void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent);

    void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent);
}

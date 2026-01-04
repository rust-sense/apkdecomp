package javax.servlet;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface ServletRequestAttributeListener extends EventListener {

    /* renamed from: javax.servlet.ServletRequestAttributeListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$attributeAdded(ServletRequestAttributeListener _this, ServletRequestAttributeEvent servletRequestAttributeEvent) {
        }

        public static void $default$attributeRemoved(ServletRequestAttributeListener _this, ServletRequestAttributeEvent servletRequestAttributeEvent) {
        }

        public static void $default$attributeReplaced(ServletRequestAttributeListener _this, ServletRequestAttributeEvent servletRequestAttributeEvent) {
        }
    }

    void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent);

    void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent);

    void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent);
}

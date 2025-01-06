package javax.servlet;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface ServletRequestListener extends EventListener {

    /* renamed from: javax.servlet.ServletRequestListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$requestDestroyed(ServletRequestListener _this, ServletRequestEvent servletRequestEvent) {
        }

        public static void $default$requestInitialized(ServletRequestListener _this, ServletRequestEvent servletRequestEvent) {
        }
    }

    void requestDestroyed(ServletRequestEvent servletRequestEvent);

    void requestInitialized(ServletRequestEvent servletRequestEvent);
}

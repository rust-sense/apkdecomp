package javax.servlet.http;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface HttpSessionListener extends EventListener {

    /* renamed from: javax.servlet.http.HttpSessionListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$sessionCreated(HttpSessionListener _this, HttpSessionEvent httpSessionEvent) {
        }

        public static void $default$sessionDestroyed(HttpSessionListener _this, HttpSessionEvent httpSessionEvent) {
        }
    }

    void sessionCreated(HttpSessionEvent httpSessionEvent);

    void sessionDestroyed(HttpSessionEvent httpSessionEvent);
}

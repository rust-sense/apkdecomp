package javax.servlet.http;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface HttpSessionActivationListener extends EventListener {

    /* renamed from: javax.servlet.http.HttpSessionActivationListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$sessionDidActivate(HttpSessionActivationListener _this, HttpSessionEvent httpSessionEvent) {
        }

        public static void $default$sessionWillPassivate(HttpSessionActivationListener _this, HttpSessionEvent httpSessionEvent) {
        }
    }

    void sessionDidActivate(HttpSessionEvent httpSessionEvent);

    void sessionWillPassivate(HttpSessionEvent httpSessionEvent);
}

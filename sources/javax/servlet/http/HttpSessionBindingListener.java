package javax.servlet.http;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface HttpSessionBindingListener extends EventListener {

    /* renamed from: javax.servlet.http.HttpSessionBindingListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$valueBound(HttpSessionBindingListener _this, HttpSessionBindingEvent httpSessionBindingEvent) {
        }

        public static void $default$valueUnbound(HttpSessionBindingListener _this, HttpSessionBindingEvent httpSessionBindingEvent) {
        }
    }

    void valueBound(HttpSessionBindingEvent httpSessionBindingEvent);

    void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent);
}

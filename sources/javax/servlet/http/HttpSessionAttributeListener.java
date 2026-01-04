package javax.servlet.http;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface HttpSessionAttributeListener extends EventListener {

    /* renamed from: javax.servlet.http.HttpSessionAttributeListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$attributeAdded(HttpSessionAttributeListener _this, HttpSessionBindingEvent httpSessionBindingEvent) {
        }

        public static void $default$attributeRemoved(HttpSessionAttributeListener _this, HttpSessionBindingEvent httpSessionBindingEvent) {
        }

        public static void $default$attributeReplaced(HttpSessionAttributeListener _this, HttpSessionBindingEvent httpSessionBindingEvent) {
        }
    }

    void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent);

    void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent);

    void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent);
}

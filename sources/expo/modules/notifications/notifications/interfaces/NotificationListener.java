package expo.modules.notifications.notifications.interfaces;

import android.os.Bundle;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationResponse;

/* loaded from: classes2.dex */
public interface NotificationListener {

    /* renamed from: expo.modules.notifications.notifications.interfaces.NotificationListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onNotificationReceived(NotificationListener _this, Notification notification) {
        }

        public static void $default$onNotificationResponseIntentReceived(NotificationListener _this, Bundle bundle) {
        }

        public static boolean $default$onNotificationResponseReceived(NotificationListener _this, NotificationResponse notificationResponse) {
            return false;
        }

        public static void $default$onNotificationsDropped(NotificationListener _this) {
        }
    }

    void onNotificationReceived(Notification notification);

    void onNotificationResponseIntentReceived(Bundle bundle);

    boolean onNotificationResponseReceived(NotificationResponse notificationResponse);

    void onNotificationsDropped();
}

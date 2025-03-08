package expo.modules.notifications.notifications.interfaces;

import android.app.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;

/* loaded from: classes2.dex */
public interface NotificationBuilder {
    Notification build();

    NotificationBuilder setAllowedBehavior(NotificationBehavior notificationBehavior);

    NotificationBuilder setNotification(expo.modules.notifications.notifications.model.Notification notification);
}

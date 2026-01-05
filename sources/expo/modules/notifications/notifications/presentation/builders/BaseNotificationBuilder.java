package expo.modules.notifications.notifications.presentation.builders;

import android.content.Context;
import expo.modules.notifications.notifications.interfaces.NotificationBuilder;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.notifications.model.NotificationContent;

/* loaded from: classes2.dex */
public abstract class BaseNotificationBuilder implements NotificationBuilder {
    private Context mContext;
    private Notification mNotification;
    private NotificationBehavior mNotificationBehavior;

    protected Context getContext() {
        return this.mContext;
    }

    protected Notification getNotification() {
        return this.mNotification;
    }

    protected NotificationBehavior getNotificationBehavior() {
        return this.mNotificationBehavior;
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationBuilder
    public NotificationBuilder setAllowedBehavior(NotificationBehavior notificationBehavior) {
        this.mNotificationBehavior = notificationBehavior;
        return this;
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationBuilder
    public NotificationBuilder setNotification(Notification notification) {
        this.mNotification = notification;
        return this;
    }

    protected BaseNotificationBuilder(Context context) {
        this.mContext = context;
    }

    protected NotificationContent getNotificationContent() {
        return getNotification().getNotificationRequest().getContent();
    }
}

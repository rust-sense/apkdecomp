package expo.modules.notifications.service.interfaces;

import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.service.NotificationsService;
import kotlin.Metadata;

/* compiled from: HandlingDelegate.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lexpo/modules/notifications/service/interfaces/HandlingDelegate;", "", "handleNotification", "", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "handleNotificationResponse", NotificationsService.NOTIFICATION_RESPONSE_KEY, "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "handleNotificationsDropped", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface HandlingDelegate {
    void handleNotification(Notification notification);

    void handleNotificationResponse(NotificationResponse notificationResponse);

    void handleNotificationsDropped();
}

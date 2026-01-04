package expo.modules.notifications.service.interfaces;

import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.service.NotificationsService;
import java.util.Collection;
import kotlin.Metadata;

/* compiled from: PresentationDelegate.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H&J\u001a\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH&¨\u0006\u000e"}, d2 = {"Lexpo/modules/notifications/service/interfaces/PresentationDelegate;", "", "dismissAllNotifications", "", "dismissNotifications", NotificationsService.IDENTIFIERS_KEY, "", "", "getAllPresentedNotifications", "Lexpo/modules/notifications/notifications/model/Notification;", "presentNotification", NotificationsService.NOTIFICATION_KEY, "behavior", "Lexpo/modules/notifications/notifications/model/NotificationBehavior;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface PresentationDelegate {
    void dismissAllNotifications();

    void dismissNotifications(Collection<String> identifiers);

    Collection<Notification> getAllPresentedNotifications();

    void presentNotification(Notification notification, NotificationBehavior behavior);
}

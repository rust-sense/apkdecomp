package expo.modules.notifications.notifications.interfaces;

import android.app.Notification;

/* loaded from: classes2.dex */
public interface NotificationPresentationEffect {
    boolean onNotificationPresentationFailed(String str, int i, Notification notification);

    boolean onNotificationPresented(String str, int i, Notification notification);
}

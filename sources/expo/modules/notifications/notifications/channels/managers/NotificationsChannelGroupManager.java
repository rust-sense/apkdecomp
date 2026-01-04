package expo.modules.notifications.notifications.channels.managers;

import android.app.NotificationChannelGroup;
import expo.modules.core.arguments.ReadableArguments;
import java.util.List;

/* loaded from: classes2.dex */
public interface NotificationsChannelGroupManager {
    NotificationChannelGroup createNotificationChannelGroup(String str, CharSequence charSequence, ReadableArguments readableArguments);

    void deleteNotificationChannelGroup(String str);

    NotificationChannelGroup getNotificationChannelGroup(String str);

    List<NotificationChannelGroup> getNotificationChannelGroups();
}

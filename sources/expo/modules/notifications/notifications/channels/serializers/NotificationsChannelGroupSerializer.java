package expo.modules.notifications.notifications.channels.serializers;

import android.app.NotificationChannelGroup;
import android.os.Bundle;

/* loaded from: classes2.dex */
public interface NotificationsChannelGroupSerializer {
    public static final String CHANNELS_KEY = "channels";
    public static final String DESCRIPTION_KEY = "description";
    public static final String ID_KEY = "id";
    public static final String IS_BLOCKED_KEY = "isBlocked";
    public static final String NAME_KEY = "name";

    Bundle toBundle(NotificationChannelGroup notificationChannelGroup);
}

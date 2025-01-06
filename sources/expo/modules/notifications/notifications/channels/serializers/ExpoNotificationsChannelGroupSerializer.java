package expo.modules.notifications.notifications.channels.serializers;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Build;
import android.os.Bundle;
import expo.modules.haptics.HapticsModule$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ExpoNotificationsChannelGroupSerializer implements NotificationsChannelGroupSerializer {
    private NotificationsChannelSerializer mChannelSerializer;

    public ExpoNotificationsChannelGroupSerializer(NotificationsChannelSerializer notificationsChannelSerializer) {
        this.mChannelSerializer = notificationsChannelSerializer;
    }

    @Override // expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer
    public Bundle toBundle(NotificationChannelGroup notificationChannelGroup) {
        CharSequence name;
        List<NotificationChannel> channels;
        String description;
        boolean isBlocked;
        if (notificationChannelGroup == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", getId(notificationChannelGroup));
        name = notificationChannelGroup.getName();
        bundle.putString("name", name.toString());
        if (Build.VERSION.SDK_INT >= 28) {
            description = notificationChannelGroup.getDescription();
            bundle.putString("description", description);
            isBlocked = notificationChannelGroup.isBlocked();
            bundle.putBoolean(NotificationsChannelGroupSerializer.IS_BLOCKED_KEY, isBlocked);
        }
        channels = notificationChannelGroup.getChannels();
        bundle.putParcelableArrayList(NotificationsChannelGroupSerializer.CHANNELS_KEY, toList(channels));
        return bundle;
    }

    protected String getId(NotificationChannelGroup notificationChannelGroup) {
        String id;
        id = notificationChannelGroup.getId();
        return id;
    }

    private ArrayList<Bundle> toList(List<NotificationChannel> list) {
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        Iterator<NotificationChannel> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(this.mChannelSerializer.toBundle(HapticsModule$$ExternalSyntheticApiModelOutline0.m((Object) it.next())));
        }
        return arrayList;
    }
}

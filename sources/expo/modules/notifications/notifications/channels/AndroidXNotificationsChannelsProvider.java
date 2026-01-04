package expo.modules.notifications.notifications.channels;

import android.content.Context;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.ExpoNotificationsChannelGroupSerializer;
import expo.modules.notifications.notifications.channels.serializers.ExpoNotificationsChannelSerializer;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;

/* loaded from: classes2.dex */
public class AndroidXNotificationsChannelsProvider extends AbstractNotificationsChannelsProvider {
    public AndroidXNotificationsChannelsProvider(Context context) {
        super(context);
    }

    @Override // expo.modules.notifications.notifications.channels.AbstractNotificationsChannelsProvider
    protected NotificationsChannelManager createChannelManager() {
        return new AndroidXNotificationsChannelManager(this.mContext, getGroupManager());
    }

    @Override // expo.modules.notifications.notifications.channels.AbstractNotificationsChannelsProvider
    protected NotificationsChannelGroupManager createChannelGroupManager() {
        return new AndroidXNotificationsChannelGroupManager(this.mContext);
    }

    @Override // expo.modules.notifications.notifications.channels.AbstractNotificationsChannelsProvider
    protected NotificationsChannelSerializer createChannelSerializer() {
        return new ExpoNotificationsChannelSerializer();
    }

    @Override // expo.modules.notifications.notifications.channels.AbstractNotificationsChannelsProvider
    protected NotificationsChannelGroupSerializer createChannelGroupSerializer() {
        return new ExpoNotificationsChannelGroupSerializer(getChannelSerializer());
    }
}

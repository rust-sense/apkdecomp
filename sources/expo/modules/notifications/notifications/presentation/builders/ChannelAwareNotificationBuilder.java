package expo.modules.notifications.notifications.presentation.builders;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import expo.modules.haptics.HapticsModule$$ExternalSyntheticApiModelOutline0;
import expo.modules.notifications.R;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.service.NotificationsService;

/* loaded from: classes2.dex */
public abstract class ChannelAwareNotificationBuilder extends BaseNotificationBuilder {
    private static final String FALLBACK_CHANNEL_ID = "expo_notifications_fallback_notification_channel";
    private static final int FALLBACK_CHANNEL_IMPORTANCE = 4;

    public ChannelAwareNotificationBuilder(Context context) {
        super(context);
    }

    protected NotificationCompat.Builder createBuilder() {
        return new NotificationCompat.Builder(getContext(), getChannelId());
    }

    protected String getChannelId() {
        String id;
        String id2;
        String id3;
        String id4;
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        NotificationTrigger trigger = getTrigger();
        if (trigger == null) {
            Log.e(NotificationsService.NOTIFICATIONS_KEY, String.format("Couldn't get channel for the notifications - trigger is 'null'. Fallback to '%s' channel", FALLBACK_CHANNEL_ID));
            id4 = getFallbackNotificationChannel().getId();
            return id4;
        }
        String notificationChannel = trigger.getNotificationChannel();
        if (notificationChannel == null) {
            id3 = getFallbackNotificationChannel().getId();
            return id3;
        }
        NotificationChannel notificationChannel2 = getNotificationsChannelManager().getNotificationChannel(notificationChannel);
        if (notificationChannel2 == null) {
            Log.e(NotificationsService.NOTIFICATIONS_KEY, String.format("Channel '%s' doesn't exists. Fallback to '%s' channel", notificationChannel, FALLBACK_CHANNEL_ID));
            id2 = getFallbackNotificationChannel().getId();
            return id2;
        }
        id = notificationChannel2.getId();
        return id;
    }

    protected NotificationsChannelManager getNotificationsChannelManager() {
        return new AndroidXNotificationsChannelManager(getContext(), new AndroidXNotificationsChannelGroupManager(getContext()));
    }

    public NotificationChannel getFallbackNotificationChannel() {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        notificationChannel = getNotificationManager().getNotificationChannel(FALLBACK_CHANNEL_ID);
        return notificationChannel != null ? notificationChannel : createFallbackChannel();
    }

    protected NotificationChannel createFallbackChannel() {
        HapticsModule$$ExternalSyntheticApiModelOutline0.m673m();
        NotificationChannel m = HapticsModule$$ExternalSyntheticApiModelOutline0.m(FALLBACK_CHANNEL_ID, getFallbackChannelName(), 4);
        m.setShowBadge(true);
        m.enableVibration(true);
        getNotificationManager().createNotificationChannel(m);
        return m;
    }

    protected String getFallbackChannelName() {
        return getContext().getString(R.string.expo_notifications_fallback_channel_name);
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getContext().getSystemService(NotificationsService.NOTIFICATION_KEY);
    }

    private NotificationTrigger getTrigger() {
        NotificationRequest notificationRequest = getNotification().getNotificationRequest();
        if (notificationRequest == null) {
            return null;
        }
        return notificationRequest.getTrigger();
    }
}

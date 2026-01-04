package expo.modules.notifications.notifications.channels.serializers;

import android.app.NotificationChannel;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import com.facebook.hermes.intl.Constants;
import expo.modules.notifications.notifications.enums.AudioContentType;
import expo.modules.notifications.notifications.enums.AudioUsage;
import expo.modules.notifications.notifications.enums.NotificationImportance;
import expo.modules.notifications.notifications.enums.NotificationVisibility;

/* loaded from: classes2.dex */
public class ExpoNotificationsChannelSerializer implements NotificationsChannelSerializer {
    @Override // expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer
    public Bundle toBundle(NotificationChannel notificationChannel) {
        CharSequence name;
        int importance;
        boolean canBypassDnd;
        String description;
        int lightColor;
        Color valueOf;
        int argb;
        int lockscreenVisibility;
        boolean canShowBadge;
        Uri sound;
        AudioAttributes audioAttributes;
        long[] vibrationPattern;
        boolean shouldShowLights;
        boolean shouldVibrate;
        if (notificationChannel == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", getChannelId(notificationChannel));
        name = notificationChannel.getName();
        bundle.putString("name", name.toString());
        importance = notificationChannel.getImportance();
        bundle.putInt(NotificationsChannelSerializer.IMPORTANCE_KEY, NotificationImportance.fromNativeValue(importance).getEnumValue());
        canBypassDnd = notificationChannel.canBypassDnd();
        bundle.putBoolean(NotificationsChannelSerializer.BYPASS_DND_KEY, canBypassDnd);
        description = notificationChannel.getDescription();
        bundle.putString("description", description);
        bundle.putString(NotificationsChannelSerializer.GROUP_ID_KEY, getGroupId(notificationChannel));
        lightColor = notificationChannel.getLightColor();
        valueOf = Color.valueOf(lightColor);
        argb = valueOf.toArgb();
        bundle.putString(NotificationsChannelSerializer.LIGHT_COLOR_KEY, String.format("#%08x", Integer.valueOf(argb)).toUpperCase());
        lockscreenVisibility = notificationChannel.getLockscreenVisibility();
        bundle.putInt(NotificationsChannelSerializer.LOCKSCREEN_VISIBILITY_KEY, NotificationVisibility.fromNativeValue(lockscreenVisibility).getEnumValue());
        canShowBadge = notificationChannel.canShowBadge();
        bundle.putBoolean(NotificationsChannelSerializer.SHOW_BADGE_KEY, canShowBadge);
        sound = notificationChannel.getSound();
        bundle.putString(NotificationsChannelSerializer.SOUND_KEY, toString(sound));
        audioAttributes = notificationChannel.getAudioAttributes();
        bundle.putBundle(NotificationsChannelSerializer.SOUND_AUDIO_ATTRIBUTES_KEY, toBundle(audioAttributes));
        vibrationPattern = notificationChannel.getVibrationPattern();
        bundle.putDoubleArray(NotificationsChannelSerializer.VIBRATION_PATTERN_KEY, toArray(vibrationPattern));
        shouldShowLights = notificationChannel.shouldShowLights();
        bundle.putBoolean(NotificationsChannelSerializer.ENABLE_LIGHTS_KEY, shouldShowLights);
        shouldVibrate = notificationChannel.shouldVibrate();
        bundle.putBoolean(NotificationsChannelSerializer.ENABLE_VIBRATE_KEY, shouldVibrate);
        return bundle;
    }

    protected String getChannelId(NotificationChannel notificationChannel) {
        String id;
        id = notificationChannel.getId();
        return id;
    }

    protected String getGroupId(NotificationChannel notificationChannel) {
        String group;
        group = notificationChannel.getGroup();
        return group;
    }

    private String toString(Uri uri) {
        if (uri == null) {
            return null;
        }
        return Settings.System.DEFAULT_NOTIFICATION_URI.equals(uri) ? Constants.COLLATION_DEFAULT : "custom";
    }

    private Bundle toBundle(AudioAttributes audioAttributes) {
        if (audioAttributes == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("usage", AudioUsage.fromNativeValue(audioAttributes.getUsage()).getEnumValue());
        bundle.putInt(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY, AudioContentType.fromNativeValue(audioAttributes.getContentType()).getEnumValue());
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_HW_AV_SYNC_KEY, (audioAttributes.getFlags() & 16) > 0);
        bundle2.putBoolean(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_ENFORCE_AUDIBILITY_KEY, (audioAttributes.getFlags() & 1) > 0);
        bundle.putBundle(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY, bundle2);
        return bundle;
    }

    private double[] toArray(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        double[] dArr = new double[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            dArr[i] = jArr[i];
        }
        return dArr;
    }
}

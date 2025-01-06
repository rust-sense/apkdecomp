package expo.modules.notifications.notifications.channels.serializers;

import android.app.NotificationChannel;
import android.os.Bundle;

/* loaded from: classes2.dex */
public interface NotificationsChannelSerializer {
    public static final String AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY = "contentType";
    public static final String AUDIO_ATTRIBUTES_FLAGS_ENFORCE_AUDIBILITY_KEY = "enforceAudibility";
    public static final String AUDIO_ATTRIBUTES_FLAGS_HW_AV_SYNC_KEY = "requestHardwareAudioVideoSynchronization";
    public static final String AUDIO_ATTRIBUTES_FLAGS_KEY = "flags";
    public static final String AUDIO_ATTRIBUTES_USAGE_KEY = "usage";
    public static final String BYPASS_DND_KEY = "bypassDnd";
    public static final String DESCRIPTION_KEY = "description";
    public static final String ENABLE_LIGHTS_KEY = "enableLights";
    public static final String ENABLE_VIBRATE_KEY = "enableVibrate";
    public static final String GROUP_ID_KEY = "groupId";
    public static final String ID_KEY = "id";
    public static final String IMPORTANCE_KEY = "importance";
    public static final String LIGHT_COLOR_KEY = "lightColor";
    public static final String LOCKSCREEN_VISIBILITY_KEY = "lockscreenVisibility";
    public static final String NAME_KEY = "name";
    public static final String SHOW_BADGE_KEY = "showBadge";
    public static final String SOUND_AUDIO_ATTRIBUTES_KEY = "audioAttributes";
    public static final String SOUND_KEY = "sound";
    public static final String VIBRATION_PATTERN_KEY = "vibrationPattern";

    Bundle toBundle(NotificationChannel notificationChannel);
}

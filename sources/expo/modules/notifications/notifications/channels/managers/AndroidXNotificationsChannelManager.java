package expo.modules.notifications.notifications.channels.managers;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.provider.Settings;
import androidx.core.app.NotificationManagerCompat;
import expo.modules.core.arguments.MapArguments;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.haptics.HapticsModule$$ExternalSyntheticApiModelOutline0;
import expo.modules.notifications.notifications.SoundResolver;
import expo.modules.notifications.notifications.channels.InvalidVibrationPatternException;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.notifications.enums.AudioContentType;
import expo.modules.notifications.notifications.enums.AudioUsage;
import expo.modules.notifications.notifications.enums.NotificationVisibility;
import java.util.List;

/* loaded from: classes2.dex */
public class AndroidXNotificationsChannelManager implements NotificationsChannelManager {
    private final NotificationManagerCompat mNotificationManager;
    private NotificationsChannelGroupManager mNotificationsChannelGroupManager;
    private final SoundResolver mSoundResolver;

    public AndroidXNotificationsChannelManager(Context context, NotificationsChannelGroupManager notificationsChannelGroupManager) {
        this.mNotificationManager = NotificationManagerCompat.from(context);
        this.mSoundResolver = new SoundResolver(context);
        this.mNotificationsChannelGroupManager = notificationsChannelGroupManager;
    }

    @Override // expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager
    public NotificationChannel getNotificationChannel(String str) {
        return this.mNotificationManager.getNotificationChannel(str);
    }

    @Override // expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager
    public List<NotificationChannel> getNotificationChannels() {
        return this.mNotificationManager.getNotificationChannels();
    }

    @Override // expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager
    public void deleteNotificationChannel(String str) {
        this.mNotificationManager.deleteNotificationChannel(str);
    }

    @Override // expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager
    public NotificationChannel createNotificationChannel(String str, CharSequence charSequence, int i, ReadableArguments readableArguments) {
        NotificationChannel m = HapticsModule$$ExternalSyntheticApiModelOutline0.m(str, charSequence, i);
        configureChannelWithOptions(m, readableArguments);
        this.mNotificationManager.createNotificationChannel(m);
        return m;
    }

    protected void configureChannelWithOptions(Object obj, ReadableArguments readableArguments) {
        NotificationVisibility fromEnumValue;
        String id;
        if (HapticsModule$$ExternalSyntheticApiModelOutline0.m$1(obj)) {
            NotificationChannel m = HapticsModule$$ExternalSyntheticApiModelOutline0.m(obj);
            if (readableArguments.containsKey(NotificationsChannelSerializer.BYPASS_DND_KEY)) {
                m.setBypassDnd(readableArguments.getBoolean(NotificationsChannelSerializer.BYPASS_DND_KEY));
            }
            if (readableArguments.containsKey("description")) {
                m.setDescription(readableArguments.getString("description"));
            }
            if (readableArguments.containsKey(NotificationsChannelSerializer.LIGHT_COLOR_KEY)) {
                m.setLightColor(Color.parseColor(readableArguments.getString(NotificationsChannelSerializer.LIGHT_COLOR_KEY)));
            }
            if (readableArguments.containsKey(NotificationsChannelSerializer.GROUP_ID_KEY)) {
                String string = readableArguments.getString(NotificationsChannelSerializer.GROUP_ID_KEY);
                NotificationChannelGroup notificationChannelGroup = this.mNotificationsChannelGroupManager.getNotificationChannelGroup(string);
                if (notificationChannelGroup == null) {
                    notificationChannelGroup = this.mNotificationsChannelGroupManager.createNotificationChannelGroup(string, string, new MapArguments());
                }
                id = notificationChannelGroup.getId();
                m.setGroup(id);
            }
            if (readableArguments.containsKey(NotificationsChannelSerializer.LOCKSCREEN_VISIBILITY_KEY) && (fromEnumValue = NotificationVisibility.fromEnumValue(readableArguments.getInt(NotificationsChannelSerializer.LOCKSCREEN_VISIBILITY_KEY))) != null) {
                m.setLockscreenVisibility(fromEnumValue.getNativeValue());
            }
            if (readableArguments.containsKey(NotificationsChannelSerializer.SHOW_BADGE_KEY)) {
                m.setShowBadge(readableArguments.getBoolean(NotificationsChannelSerializer.SHOW_BADGE_KEY));
            }
            if (readableArguments.containsKey(NotificationsChannelSerializer.SOUND_KEY) || readableArguments.containsKey(NotificationsChannelSerializer.SOUND_AUDIO_ATTRIBUTES_KEY)) {
                m.setSound(createSoundUriFromArguments(readableArguments), createAttributesFromArguments(readableArguments.getArguments(NotificationsChannelSerializer.SOUND_AUDIO_ATTRIBUTES_KEY)));
            }
            if (readableArguments.containsKey(NotificationsChannelSerializer.VIBRATION_PATTERN_KEY)) {
                m.setVibrationPattern(createVibrationPatternFromList(readableArguments.getList(NotificationsChannelSerializer.VIBRATION_PATTERN_KEY)));
            }
            if (readableArguments.containsKey(NotificationsChannelSerializer.ENABLE_LIGHTS_KEY)) {
                m.enableLights(readableArguments.getBoolean(NotificationsChannelSerializer.ENABLE_LIGHTS_KEY));
            }
            if (readableArguments.containsKey(NotificationsChannelSerializer.ENABLE_VIBRATE_KEY)) {
                m.enableVibration(readableArguments.getBoolean(NotificationsChannelSerializer.ENABLE_VIBRATE_KEY));
            }
        }
    }

    protected Uri createSoundUriFromArguments(ReadableArguments readableArguments) {
        if (!readableArguments.containsKey(NotificationsChannelSerializer.SOUND_KEY)) {
            return Settings.System.DEFAULT_NOTIFICATION_URI;
        }
        String string = readableArguments.getString(NotificationsChannelSerializer.SOUND_KEY);
        if (string == null) {
            return null;
        }
        return this.mSoundResolver.resolve(string);
    }

    protected long[] createVibrationPatternFromList(List list) throws InvalidVibrationPatternException {
        if (list == null) {
            return null;
        }
        long[] jArr = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Number) {
                jArr[i] = ((Number) list.get(i)).longValue();
            } else {
                throw new InvalidVibrationPatternException(i, list.get(i));
            }
        }
        return jArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected AudioAttributes createAttributesFromArguments(ReadableArguments readableArguments) {
        if (readableArguments == null) {
            return null;
        }
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        if (readableArguments.containsKey("usage")) {
            builder.setUsage(AudioUsage.fromEnumValue(readableArguments.getInt("usage")).getNativeValue());
        }
        if (readableArguments.containsKey(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY)) {
            builder.setContentType(AudioContentType.fromEnumValue(readableArguments.getInt(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY)).getNativeValue());
        }
        if (readableArguments.containsKey(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY)) {
            ReadableArguments arguments = readableArguments.getArguments(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY);
            boolean z = arguments.getBoolean(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_ENFORCE_AUDIBILITY_KEY);
            int i = z;
            if (arguments.getBoolean(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_HW_AV_SYNC_KEY)) {
                i = (z ? 1 : 0) | 16;
            }
            builder.setFlags(i);
        }
        return builder.build();
    }
}

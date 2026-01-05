package expo.modules.notifications.notifications;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.notifications.notifications.channels.InvalidVibrationPatternException;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import expo.modules.notifications.notifications.model.NotificationContent;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ArgumentsNotificationContentBuilder extends NotificationContent.Builder {
    private static final String AUTO_DISMISS_KEY = "autoDismiss";
    private static final String BADGE_KEY = "badge";
    private static final String BODY_KEY = "data";
    private static final String CATEGORY_IDENTIFIER_KEY = "categoryIdentifier";
    private static final String COLOR_KEY = "color";
    private static final String PRIORITY_KEY = "priority";
    private static final String SOUND_KEY = "sound";
    private static final String STICKY_KEY = "sticky";
    private static final String SUBTITLE_KEY = "subtitle";
    private static final String TEXT_KEY = "body";
    private static final String TITLE_KEY = "title";
    private static final String VIBRATE_KEY = "vibrate";
    private SoundResolver mSoundResolver;

    public ArgumentsNotificationContentBuilder(Context context) {
        this.mSoundResolver = new SoundResolver(context);
    }

    public NotificationContent.Builder setPayload(ReadableArguments readableArguments) {
        setTitle(readableArguments.getString("title")).setSubtitle(readableArguments.getString(SUBTITLE_KEY)).setText(readableArguments.getString(TEXT_KEY)).setBody(getBody(readableArguments)).setPriority(getPriority(readableArguments)).setBadgeCount(getBadgeCount(readableArguments)).setColor(getColor(readableArguments)).setAutoDismiss(getAutoDismiss(readableArguments)).setCategoryId(getCategoryId(readableArguments)).setSticky(getSticky(readableArguments));
        if (shouldPlayDefaultSound(readableArguments)) {
            useDefaultSound();
        } else {
            setSound(getSound(readableArguments));
        }
        if (shouldUseDefaultVibrationPattern(readableArguments)) {
            useDefaultVibrationPattern();
        } else {
            setVibrationPattern(getVibrationPattern(readableArguments));
        }
        return this;
    }

    protected Number getBadgeCount(ReadableArguments readableArguments) {
        if (readableArguments.containsKey(BADGE_KEY)) {
            return Integer.valueOf(readableArguments.getInt(BADGE_KEY));
        }
        return null;
    }

    protected Number getColor(ReadableArguments readableArguments) {
        try {
            if (readableArguments.containsKey("color")) {
                return Integer.valueOf(Color.parseColor(readableArguments.getString("color")));
            }
            return null;
        } catch (IllegalArgumentException unused) {
            Log.e("expo-notifications", "Could not have parsed color passed in notification.");
            return null;
        }
    }

    protected boolean shouldPlayDefaultSound(ReadableArguments readableArguments) {
        if (readableArguments.get("sound") instanceof Boolean) {
            return readableArguments.getBoolean("sound");
        }
        return getSound(readableArguments) == null;
    }

    protected Uri getSound(ReadableArguments readableArguments) {
        return this.mSoundResolver.resolve(readableArguments.getString("sound"));
    }

    protected JSONObject getBody(ReadableArguments readableArguments) {
        try {
            Map map = readableArguments.getMap("data");
            if (map != null) {
                return new JSONObject(map);
            }
        } catch (NullPointerException unused) {
        }
        return null;
    }

    protected boolean shouldUseDefaultVibrationPattern(ReadableArguments readableArguments) {
        return !readableArguments.getBoolean(VIBRATE_KEY, true);
    }

    protected long[] getVibrationPattern(ReadableArguments readableArguments) {
        try {
            List list = readableArguments.getList(VIBRATE_KEY);
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
        } catch (InvalidVibrationPatternException e) {
            Log.w("expo-notifications", "Failed to set custom vibration pattern from the notification: " + e.getMessage());
            return null;
        }
    }

    protected NotificationPriority getPriority(ReadableArguments readableArguments) {
        return NotificationPriority.fromEnumValue(readableArguments.getString("priority"));
    }

    protected boolean getAutoDismiss(ReadableArguments readableArguments) {
        return readableArguments.getBoolean(AUTO_DISMISS_KEY, true);
    }

    protected String getCategoryId(ReadableArguments readableArguments) {
        return readableArguments.getString(CATEGORY_IDENTIFIER_KEY, null);
    }

    protected boolean getSticky(ReadableArguments readableArguments) {
        return readableArguments.getBoolean(STICKY_KEY, false);
    }
}

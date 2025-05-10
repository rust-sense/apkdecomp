package expo.modules.notifications.notifications;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import expo.modules.notifications.notifications.model.NotificationContent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class JSONNotificationContentBuilder extends NotificationContent.Builder {
    private static final String AUTO_DISMISS_KEY = "autoDismiss";
    private static final String BADGE_KEY = "badge";
    private static final String BODY_KEY = "body";
    private static final String CATEGORY_IDENTIFIER_KEY = "categoryId";
    private static final String COLOR_KEY = "color";
    private static final String PRIORITY_KEY = "priority";
    private static final String SOUND_KEY = "sound";
    private static final String STICKY_KEY = "sticky";
    private static final String SUBTITLE_KEY = "subtitle";
    private static final String TEXT_KEY = "message";
    private static final String TITLE_KEY = "title";
    private static final String VIBRATE_KEY = "vibrate";
    private SoundResolver mSoundResolver;

    public JSONNotificationContentBuilder(Context context) {
        this.mSoundResolver = new SoundResolver(context);
    }

    public NotificationContent.Builder setPayload(JSONObject jSONObject) {
        setTitle(getTitle(jSONObject)).setSubtitle(getSubtitle(jSONObject)).setText(getText(jSONObject)).setBody(getBody(jSONObject)).setPriority(getPriority(jSONObject)).setBadgeCount(getBadgeCount(jSONObject)).setColor(getColor(jSONObject)).setAutoDismiss(getAutoDismiss(jSONObject)).setCategoryId(getCategoryId(jSONObject)).setSticky(getSticky(jSONObject));
        if (shouldPlayDefaultSound(jSONObject)) {
            useDefaultSound();
        } else {
            setSound(getSound(jSONObject));
        }
        if (shouldUseDefaultVibrationPattern(jSONObject)) {
            useDefaultVibrationPattern();
        } else {
            setVibrationPattern(getVibrationPattern(jSONObject));
        }
        return this;
    }

    protected String getTitle(JSONObject jSONObject) {
        try {
            return jSONObject.getString("title");
        } catch (JSONException unused) {
            return null;
        }
    }

    protected String getText(JSONObject jSONObject) {
        try {
            return jSONObject.getString("message");
        } catch (JSONException unused) {
            return null;
        }
    }

    protected String getSubtitle(JSONObject jSONObject) {
        try {
            return jSONObject.getString(SUBTITLE_KEY);
        } catch (JSONException unused) {
            return null;
        }
    }

    protected Number getBadgeCount(JSONObject jSONObject) {
        try {
            if (jSONObject.has(BADGE_KEY)) {
                return Integer.valueOf(jSONObject.getInt(BADGE_KEY));
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    protected boolean shouldPlayDefaultSound(JSONObject jSONObject) {
        try {
            return jSONObject.getBoolean("sound");
        } catch (JSONException unused) {
            return getSound(jSONObject) == null;
        }
    }

    protected Uri getSound(JSONObject jSONObject) {
        try {
            try {
                jSONObject.getBoolean("sound");
                return null;
            } catch (JSONException unused) {
                return null;
            }
        } catch (JSONException unused2) {
            return this.mSoundResolver.resolve(jSONObject.getString("sound"));
        }
    }

    protected JSONObject getBody(JSONObject jSONObject) {
        try {
            return new JSONObject(jSONObject.optString(BODY_KEY));
        } catch (NullPointerException | JSONException unused) {
            return null;
        }
    }

    protected boolean shouldUseDefaultVibrationPattern(JSONObject jSONObject) {
        return !jSONObject.optBoolean(VIBRATE_KEY, true);
    }

    protected long[] getVibrationPattern(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(VIBRATE_KEY);
            if (optJSONArray == null) {
                return null;
            }
            long[] jArr = new long[optJSONArray.length()];
            for (int i = 0; i < optJSONArray.length(); i++) {
                jArr[i] = optJSONArray.getLong(i);
            }
            return jArr;
        } catch (JSONException e) {
            Log.w("expo-notifications", "Failed to set custom vibration pattern from the notification: " + e.getMessage());
            return null;
        }
    }

    protected NotificationPriority getPriority(JSONObject jSONObject) {
        return NotificationPriority.fromEnumValue(jSONObject.optString("priority"));
    }

    protected Number getColor(JSONObject jSONObject) {
        try {
            if (jSONObject.has("color")) {
                return Integer.valueOf(Color.parseColor(jSONObject.getString("color")));
            }
            return null;
        } catch (IllegalArgumentException unused) {
            Log.e("expo-notifications", "Could not have parsed color passed in notification.");
            return null;
        } catch (JSONException unused2) {
            Log.e("expo-notifications", "Could not have parsed a non-string color value passed in notification.");
            return null;
        }
    }

    protected boolean getAutoDismiss(JSONObject jSONObject) {
        if (!jSONObject.has(AUTO_DISMISS_KEY)) {
            return true;
        }
        try {
            return jSONObject.getBoolean(AUTO_DISMISS_KEY);
        } catch (JSONException unused) {
            Log.e("expo-notifications", "Could not have parsed a boolean autoDismiss value passed in notification, falling back to a default value.");
            return true;
        }
    }

    protected String getCategoryId(JSONObject jSONObject) {
        try {
            return jSONObject.getString(CATEGORY_IDENTIFIER_KEY);
        } catch (JSONException unused) {
            return null;
        }
    }

    protected boolean getSticky(JSONObject jSONObject) {
        if (!jSONObject.has(STICKY_KEY)) {
            return false;
        }
        try {
            return jSONObject.getBoolean(STICKY_KEY);
        } catch (JSONException unused) {
            Log.e("expo-notifications", "Could not have parsed a boolean sticky value passed in notification, falling back to a default value.");
            return false;
        }
    }
}

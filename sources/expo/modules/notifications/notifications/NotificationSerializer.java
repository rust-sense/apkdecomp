package expo.modules.notifications.notifications;

import android.os.Build;
import android.os.Bundle;
import com.facebook.common.util.UriUtil;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.messaging.Constants;
import expo.modules.core.arguments.MapArguments;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.notifications.model.TextInputNotificationResponse;
import expo.modules.notifications.notifications.model.triggers.FirebaseNotificationTrigger;
import expo.modules.notifications.notifications.triggers.DailyTrigger;
import expo.modules.notifications.notifications.triggers.DateTrigger;
import expo.modules.notifications.notifications.triggers.TimeIntervalTrigger;
import expo.modules.notifications.notifications.triggers.WeeklyTrigger;
import expo.modules.notifications.notifications.triggers.YearlyTrigger;
import expo.modules.notifications.service.NotificationsService;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.i18n.MessageBundle;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class NotificationSerializer {
    public static Bundle toBundle(NotificationResponse notificationResponse) {
        Bundle bundle = new Bundle();
        bundle.putString("actionIdentifier", notificationResponse.getActionIdentifier());
        bundle.putBundle(NotificationsService.NOTIFICATION_KEY, toBundle(notificationResponse.getNotification()));
        if (notificationResponse instanceof TextInputNotificationResponse) {
            bundle.putString("userText", ((TextInputNotificationResponse) notificationResponse).getUserText());
        }
        return bundle;
    }

    public static Bundle toBundle(Notification notification) {
        Bundle bundle = new Bundle();
        bundle.putBundle(SentryBaseEvent.JsonKeys.REQUEST, toBundle(notification.getNotificationRequest()));
        bundle.putLong("date", notification.getDate().getTime());
        return bundle;
    }

    public static Bundle toBundle(NotificationRequest notificationRequest) {
        Bundle bundle = new Bundle();
        bundle.putString("identifier", notificationRequest.getIdentifier());
        bundle.putBundle(UriUtil.LOCAL_CONTENT_SCHEME, toBundle(notificationRequest.getContent()));
        bundle.putBundle("trigger", toBundle(notificationRequest.getTrigger()));
        return bundle;
    }

    public static Bundle toBundle(NotificationContent notificationContent) {
        Bundle bundle = new Bundle();
        bundle.putString(MessageBundle.TITLE_ENTRY, notificationContent.getTitle());
        bundle.putString("subtitle", notificationContent.getSubtitle());
        bundle.putString("body", notificationContent.getText());
        if (notificationContent.getColor() != null) {
            bundle.putString(ViewProps.COLOR, String.format("#%08X", Integer.valueOf(notificationContent.getColor().intValue())));
        }
        bundle.putBundle("data", toBundle(notificationContent.getBody()));
        if (notificationContent.getBadgeCount() != null) {
            bundle.putInt("badge", notificationContent.getBadgeCount().intValue());
        } else {
            bundle.putString("badge", null);
        }
        if (notificationContent.shouldPlayDefaultSound()) {
            bundle.putString(NotificationsChannelSerializer.SOUND_KEY, Constants.COLLATION_DEFAULT);
        } else if (notificationContent.getSound() != null) {
            bundle.putString(NotificationsChannelSerializer.SOUND_KEY, "custom");
        } else {
            bundle.putString(NotificationsChannelSerializer.SOUND_KEY, null);
        }
        if (notificationContent.getPriority() != null) {
            bundle.putString(SentryThread.JsonKeys.PRIORITY, notificationContent.getPriority().getEnumValue());
        }
        if (notificationContent.getVibrationPattern() != null) {
            int length = notificationContent.getVibrationPattern().length;
            double[] dArr = new double[length];
            for (int i = 0; i < length; i++) {
                dArr[i] = notificationContent.getVibrationPattern()[i];
            }
            bundle.putDoubleArray(NotificationsChannelSerializer.VIBRATION_PATTERN_KEY, dArr);
        }
        bundle.putBoolean("autoDismiss", notificationContent.isAutoDismiss());
        if (notificationContent.getCategoryId() != null) {
            bundle.putString("categoryIdentifier", notificationContent.getCategoryId());
        }
        bundle.putBoolean("sticky", notificationContent.isSticky());
        return bundle;
    }

    public static Bundle toBundle(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap(jSONObject.length());
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (opt instanceof JSONObject) {
                hashMap.put(next, toBundle((JSONObject) opt));
            } else if (opt instanceof JSONArray) {
                hashMap.put(next, toList((JSONArray) opt));
            } else if (JSONObject.NULL.equals(opt)) {
                hashMap.put(next, null);
            } else {
                hashMap.put(next, opt);
            }
        }
        try {
            return new MapArguments(hashMap).toBundle();
        } catch (NullPointerException unused) {
            for (String str : hashMap.keySet()) {
                if (hashMap.get(str) == null) {
                    hashMap.remove(str);
                }
            }
            return new MapArguments(hashMap).toBundle();
        }
    }

    private static List<Object> toList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            if (jSONArray.isNull(i)) {
                arrayList.add(null);
            } else if (jSONArray.optJSONObject(i) != null) {
                arrayList.add(toBundle(jSONArray.optJSONObject(i)));
            } else if (jSONArray.optJSONArray(i) != null) {
                arrayList.add(toList(jSONArray.optJSONArray(i)));
            } else {
                arrayList.add(jSONArray.opt(i));
            }
        }
        return arrayList;
    }

    private static Bundle toBundle(NotificationTrigger notificationTrigger) {
        if (notificationTrigger == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        if (notificationTrigger instanceof FirebaseNotificationTrigger) {
            bundle.putString("type", "push");
            bundle.putBundle("remoteMessage", RemoteMessageSerializer.toBundle(((FirebaseNotificationTrigger) notificationTrigger).getRemoteMessage()));
        } else if (notificationTrigger instanceof TimeIntervalTrigger) {
            bundle.putString("type", "timeInterval");
            TimeIntervalTrigger timeIntervalTrigger = (TimeIntervalTrigger) notificationTrigger;
            bundle.putBoolean("repeats", timeIntervalTrigger.isRepeating());
            bundle.putLong("seconds", timeIntervalTrigger.getTimeInterval());
        } else if (notificationTrigger instanceof DateTrigger) {
            bundle.putString("type", "date");
            bundle.putBoolean("repeats", false);
            bundle.putLong("value", ((DateTrigger) notificationTrigger).getTriggerDate().getTime());
        } else if (notificationTrigger instanceof DailyTrigger) {
            bundle.putString("type", "daily");
            DailyTrigger dailyTrigger = (DailyTrigger) notificationTrigger;
            bundle.putInt("hour", dailyTrigger.getHour());
            bundle.putInt("minute", dailyTrigger.getMinute());
        } else if (notificationTrigger instanceof WeeklyTrigger) {
            bundle.putString("type", "weekly");
            WeeklyTrigger weeklyTrigger = (WeeklyTrigger) notificationTrigger;
            bundle.putInt("weekday", weeklyTrigger.getWeekday());
            bundle.putInt("hour", weeklyTrigger.getHour());
            bundle.putInt("minute", weeklyTrigger.getMinute());
        } else if (notificationTrigger instanceof YearlyTrigger) {
            bundle.putString("type", "yearly");
            YearlyTrigger yearlyTrigger = (YearlyTrigger) notificationTrigger;
            bundle.putInt("day", yearlyTrigger.getDay());
            bundle.putInt("month", yearlyTrigger.getMonth());
            bundle.putInt("hour", yearlyTrigger.getHour());
            bundle.putInt("minute", yearlyTrigger.getMinute());
        } else {
            bundle.putString("type", "unknown");
        }
        bundle.putString("channelId", getChannelId(notificationTrigger));
        return bundle;
    }

    private static String getChannelId(NotificationTrigger notificationTrigger) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notificationTrigger.getNotificationChannel();
        }
        return null;
    }

    public static Bundle toResponseBundleFromExtras(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(MessageBundle.TITLE_ENTRY, bundle.getString(MessageBundle.TITLE_ENTRY));
        bundle2.putString("body", bundle.getString("message"));
        bundle2.putString("dataString", bundle.getString("body"));
        Bundle bundle3 = new Bundle();
        bundle3.putString("type", "push");
        bundle3.putString("channelId", bundle.getString("channelId"));
        Bundle bundle4 = new Bundle();
        bundle4.putString("identifier", bundle.getString(Constants.MessagePayloadKeys.MSGID));
        bundle4.putBundle("trigger", bundle3);
        bundle4.putBundle(UriUtil.LOCAL_CONTENT_SCHEME, bundle2);
        Bundle bundle5 = new Bundle();
        bundle5.putLong("date", bundle.getLong(Constants.MessagePayloadKeys.SENT_TIME));
        bundle5.putBundle(SentryBaseEvent.JsonKeys.REQUEST, bundle4);
        Bundle bundle6 = new Bundle();
        bundle6.putString("actionIdentifier", NotificationResponse.DEFAULT_ACTION_IDENTIFIER);
        bundle6.putBundle(NotificationsService.NOTIFICATION_KEY, bundle5);
        return bundle6;
    }
}

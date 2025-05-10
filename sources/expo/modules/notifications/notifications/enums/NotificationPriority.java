package expo.modules.notifications.notifications.enums;

import com.facebook.hermes.intl.Constants;
import io.sentry.protocol.MetricSummary;

/* loaded from: classes2.dex */
public enum NotificationPriority {
    MIN(-2, MetricSummary.JsonKeys.MIN),
    LOW(-1, "low"),
    DEFAULT(0, Constants.COLLATION_DEFAULT),
    HIGH(1, "high"),
    MAX(2, MetricSummary.JsonKeys.MAX);

    private final String mEnumValue;
    private final int mNativePriority;

    public String getEnumValue() {
        return this.mEnumValue;
    }

    public int getNativeValue() {
        return this.mNativePriority;
    }

    NotificationPriority(int i, String str) {
        this.mNativePriority = i;
        this.mEnumValue = str;
    }

    public static NotificationPriority fromEnumValue(String str) {
        for (NotificationPriority notificationPriority : values()) {
            if (notificationPriority.getEnumValue().equalsIgnoreCase(str)) {
                return notificationPriority;
            }
        }
        return null;
    }

    public static NotificationPriority fromNativeValue(int i) {
        for (NotificationPriority notificationPriority : values()) {
            if (notificationPriority.getNativeValue() == i) {
                return notificationPriority;
            }
        }
        return null;
    }
}

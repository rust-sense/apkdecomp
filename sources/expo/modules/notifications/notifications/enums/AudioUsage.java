package expo.modules.notifications.notifications.enums;

/* loaded from: classes2.dex */
public enum AudioUsage {
    UNKNOWN(0, 0),
    MEDIA(1, 1),
    VOICE_COMMUNICATION(2, 2),
    VOICE_COMMUNICATION_SIGNALLING(3, 3),
    ALARM(4, 4),
    NOTIFICATION(5, 5),
    NOTIFICATION_RINGTONE(6, 6),
    NOTIFICATION_COMMUNICATION_REQUEST(7, 7),
    NOTIFICATION_COMMUNICATION_INSTANT(8, 8),
    NOTIFICATION_COMMUNICATION_DELAYED(9, 9),
    NOTIFICATION_EVENT(10, 10),
    ASSISTANCE_ACCESSIBILITY(11, 11),
    ASSISTANCE_NAVIGATION_GUIDANCE(12, 12),
    ASSISTANCE_SONIFICATION(13, 13),
    GAME(14, 14);

    private final int mEnumValue;
    private final int mNativeVisibility;

    public int getEnumValue() {
        return this.mEnumValue;
    }

    public int getNativeValue() {
        return this.mNativeVisibility;
    }

    AudioUsage(int i, int i2) {
        this.mNativeVisibility = i;
        this.mEnumValue = i2;
    }

    public static AudioUsage fromEnumValue(int i) {
        for (AudioUsage audioUsage : values()) {
            if (audioUsage.getEnumValue() == i) {
                return audioUsage;
            }
        }
        return UNKNOWN;
    }

    public static AudioUsage fromNativeValue(int i) {
        for (AudioUsage audioUsage : values()) {
            if (audioUsage.getEnumValue() == i) {
                return audioUsage;
            }
        }
        return UNKNOWN;
    }
}

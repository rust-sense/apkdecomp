package expo.modules.notifications.notifications.enums;

/* loaded from: classes2.dex */
public enum AudioContentType {
    UNKNOWN(0, 0),
    SPEECH(1, 1),
    MUSIC(2, 2),
    MOVIE(3, 3),
    SONIFICIATION(4, 4);

    private final int mEnumValue;
    private final int mNativeVisibility;

    public int getEnumValue() {
        return this.mEnumValue;
    }

    public int getNativeValue() {
        return this.mNativeVisibility;
    }

    AudioContentType(int i, int i2) {
        this.mNativeVisibility = i;
        this.mEnumValue = i2;
    }

    public static AudioContentType fromEnumValue(int i) {
        for (AudioContentType audioContentType : values()) {
            if (audioContentType.getEnumValue() == i) {
                return audioContentType;
            }
        }
        return UNKNOWN;
    }

    public static AudioContentType fromNativeValue(int i) {
        for (AudioContentType audioContentType : values()) {
            if (audioContentType.getEnumValue() == i) {
                return audioContentType;
            }
        }
        return UNKNOWN;
    }
}

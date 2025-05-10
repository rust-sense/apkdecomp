package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaExperimentalFeature {
    WEB_FLEX_BASIS(0);

    private final int mIntValue;

    public int intValue() {
        return this.mIntValue;
    }

    YogaExperimentalFeature(int i) {
        this.mIntValue = i;
    }

    public static YogaExperimentalFeature fromInt(int i) {
        if (i == 0) {
            return WEB_FLEX_BASIS;
        }
        throw new IllegalArgumentException("Unknown enum value: " + i);
    }
}

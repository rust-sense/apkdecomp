package com.facebook.react.uimanager.common;

/* loaded from: classes.dex */
public class ViewUtil {
    public static final int NO_SURFACE_ID = -1;

    public static int getUIManagerType(int i) {
        return i % 2 == 0 ? 2 : 1;
    }

    public static int getUIManagerType(int i, int i2) {
        int i3 = i2 == -1 ? 1 : 2;
        if (i3 == 1 && !isRootTag(i) && i % 2 == 0) {
            return 2;
        }
        return i3;
    }

    @Deprecated
    public static boolean isRootTag(int i) {
        return i % 10 == 1;
    }
}

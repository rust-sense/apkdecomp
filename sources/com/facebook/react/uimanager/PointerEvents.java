package com.facebook.react.uimanager;

import java.util.Locale;

/* loaded from: classes.dex */
public enum PointerEvents {
    NONE,
    BOX_NONE,
    BOX_ONLY,
    AUTO;

    public static boolean canBeTouchTarget(PointerEvents pointerEvents) {
        return pointerEvents == AUTO || pointerEvents == BOX_ONLY;
    }

    public static boolean canChildrenBeTouchTarget(PointerEvents pointerEvents) {
        return pointerEvents == AUTO || pointerEvents == BOX_NONE;
    }

    public static PointerEvents parsePointerEvents(String str) {
        return str == null ? AUTO : valueOf(str.toUpperCase(Locale.US).replace("-", "_"));
    }
}

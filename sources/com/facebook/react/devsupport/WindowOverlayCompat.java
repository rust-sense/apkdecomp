package com.facebook.react.devsupport;

import android.os.Build;

/* loaded from: classes.dex */
class WindowOverlayCompat {
    private static final int TYPE_APPLICATION_OVERLAY = 2038;
    static final int TYPE_SYSTEM_OVERLAY;

    static {
        TYPE_SYSTEM_OVERLAY = Build.VERSION.SDK_INT < 26 ? 2006 : TYPE_APPLICATION_OVERLAY;
    }

    WindowOverlayCompat() {
    }
}

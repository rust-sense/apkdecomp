package com.facebook.react.devsupport;

import com.facebook.soloader.SoLoader;

/* loaded from: classes.dex */
class DevSupportSoLoader {
    private static volatile boolean sDidInit = false;

    DevSupportSoLoader() {
    }

    public static synchronized void staticInit() {
        synchronized (DevSupportSoLoader.class) {
            if (sDidInit) {
                return;
            }
            SoLoader.loadLibrary("react_devsupportjni");
            sDidInit = true;
        }
    }
}

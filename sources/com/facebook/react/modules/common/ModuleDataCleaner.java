package com.facebook.react.modules.common;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import java.util.Collection;

/* loaded from: classes.dex */
public class ModuleDataCleaner {

    public interface Cleanable {
        void clearSensitiveData();
    }

    @Deprecated
    public static void cleanDataFromModules(CatalystInstance catalystInstance) {
        cleanDataFromModules(catalystInstance.getNativeModules());
    }

    public static void cleanDataFromModules(ReactContext reactContext) {
        cleanDataFromModules(reactContext.getNativeModules());
    }

    private static void cleanDataFromModules(Collection<NativeModule> collection) {
        for (NativeModule nativeModule : collection) {
            if (nativeModule instanceof Cleanable) {
                FLog.d(ReactConstants.TAG, "Cleaning data from " + nativeModule.getName());
                ((Cleanable) nativeModule).clearSensitiveData();
            }
        }
    }
}

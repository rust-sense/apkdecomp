package com.facebook.react.fabric.events;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.fabric.FabricSoLoader;

/* loaded from: classes.dex */
public class EventEmitterWrapper {
    private final HybridData mHybridData;

    private native void dispatchEvent(String str, NativeMap nativeMap, int i);

    private native void dispatchUniqueEvent(String str, NativeMap nativeMap);

    static {
        FabricSoLoader.staticInit();
    }

    private EventEmitterWrapper(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized void dispatch(String str, WritableMap writableMap, int i) {
        if (isValid()) {
            dispatchEvent(str, (NativeMap) writableMap, i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized void dispatchUnique(String str, WritableMap writableMap) {
        if (isValid()) {
            dispatchUniqueEvent(str, (NativeMap) writableMap);
        }
    }

    public synchronized void destroy() {
        HybridData hybridData = this.mHybridData;
        if (hybridData != null) {
            hybridData.resetNative();
        }
    }

    private boolean isValid() {
        HybridData hybridData = this.mHybridData;
        if (hybridData != null) {
            return hybridData.isValid();
        }
        return false;
    }
}

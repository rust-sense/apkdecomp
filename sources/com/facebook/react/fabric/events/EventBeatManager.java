package com.facebook.react.fabric.events;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.fabric.FabricSoLoader;
import com.facebook.react.uimanager.events.BatchEventDispatchedListener;

/* loaded from: classes.dex */
public final class EventBeatManager implements BatchEventDispatchedListener {
    private final HybridData mHybridData;

    private static native HybridData initHybrid();

    private native void tick();

    static {
        FabricSoLoader.staticInit();
    }

    @Deprecated(forRemoval = true, since = "Deprecated on v0.72.0 Use EventBeatManager() instead")
    public EventBeatManager(ReactApplicationContext reactApplicationContext) {
        this();
    }

    public EventBeatManager() {
        this.mHybridData = initHybrid();
    }

    @Override // com.facebook.react.uimanager.events.BatchEventDispatchedListener
    public void onBatchEventDispatched() {
        tick();
    }
}

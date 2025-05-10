package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

/* loaded from: classes.dex */
public abstract class NativeAnimationsDebugModuleSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "AnimationsDebugModule";

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public abstract void startRecordingFps();

    @ReactMethod
    public abstract void stopRecordingFps(double d);

    public NativeAnimationsDebugModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

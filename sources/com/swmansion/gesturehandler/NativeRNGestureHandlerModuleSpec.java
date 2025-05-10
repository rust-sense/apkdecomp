package com.swmansion.gesturehandler;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

/* loaded from: classes2.dex */
public abstract class NativeRNGestureHandlerModuleSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "RNGestureHandlerModule";

    @ReactMethod
    public abstract void attachGestureHandler(double d, double d2, double d3);

    @ReactMethod
    public abstract void createGestureHandler(String str, double d, ReadableMap readableMap);

    @ReactMethod
    public abstract void dropGestureHandler(double d);

    @ReactMethod
    public abstract void flushOperations();

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "RNGestureHandlerModule";
    }

    @ReactMethod
    public abstract void handleClearJSResponder();

    @ReactMethod
    public abstract void handleSetJSResponder(double d, boolean z);

    @ReactMethod
    public abstract boolean install();

    @ReactMethod
    public abstract void updateGestureHandler(double d, ReadableMap readableMap);

    public NativeRNGestureHandlerModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

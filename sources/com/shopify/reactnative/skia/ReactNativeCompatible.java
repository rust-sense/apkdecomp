package com.shopify.reactnative.skia;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.RuntimeExecutor;

/* loaded from: classes2.dex */
final class ReactNativeCompatible {
    ReactNativeCompatible() {
    }

    public static RuntimeExecutor getRuntimeExecutor(ReactContext reactContext) {
        return reactContext.getRuntimeExecutor();
    }
}

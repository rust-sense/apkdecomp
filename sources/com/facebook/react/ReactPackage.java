package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.uimanager.ViewManager;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface ReactPackage {

    /* renamed from: com.facebook.react.ReactPackage$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        @Nullable
        @UnstableReactNativeAPI
        public static NativeModule $default$getModule(ReactPackage _this, String str, ReactApplicationContext reactApplicationContext) {
            return null;
        }
    }

    List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext);

    List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext);

    @Nullable
    @UnstableReactNativeAPI
    NativeModule getModule(String str, ReactApplicationContext reactApplicationContext);
}

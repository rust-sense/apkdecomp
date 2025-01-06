package com.facebook.react.uimanager;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UIManager;

/* loaded from: classes.dex */
public class ThemedReactContext extends ReactContext {
    private final String mModuleName;
    private final ReactApplicationContext mReactApplicationContext;
    private final int mSurfaceId;

    public String getModuleName() {
        return this.mModuleName;
    }

    public ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }

    @Deprecated
    public String getSurfaceID() {
        return this.mModuleName;
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    @Deprecated
    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context) {
        this(reactApplicationContext, context, null, -1);
    }

    @Deprecated
    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context, String str) {
        this(reactApplicationContext, context, str, -1);
    }

    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context context, String str, int i) {
        super(context);
        if (reactApplicationContext.hasCatalystInstance()) {
            initializeWithInstance(reactApplicationContext.getCatalystInstance());
        }
        initializeInteropModules(reactApplicationContext);
        this.mReactApplicationContext = reactApplicationContext;
        this.mModuleName = str;
        this.mSurfaceId = i;
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void addLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.addLifecycleEventListener(lifecycleEventListener);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void removeLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mReactApplicationContext.removeLifecycleEventListener(lifecycleEventListener);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean hasCurrentActivity() {
        return this.mReactApplicationContext.hasCurrentActivity();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean isBridgeless() {
        return this.mReactApplicationContext.isBridgeless();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public UIManager getFabricUIManager() {
        if (isBridgeless()) {
            return this.mReactApplicationContext.getFabricUIManager();
        }
        return super.getFabricUIManager();
    }
}

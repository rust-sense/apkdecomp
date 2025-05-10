package com.facebook.react.modules.accessibilityinfo;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.facebook.fbreact.specs.NativeAccessibilityInfoSpec;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = NativeAccessibilityInfoSpec.NAME)
/* loaded from: classes.dex */
public class AccessibilityInfoModule extends NativeAccessibilityInfoSpec implements LifecycleEventListener {
    private static final String ACCESSIBILITY_SERVICE_EVENT_NAME = "accessibilityServiceDidChange";
    private static final String REDUCE_MOTION_EVENT_NAME = "reduceMotionDidChange";
    private static final String TOUCH_EXPLORATION_EVENT_NAME = "touchExplorationDidChange";
    private final ContentObserver animationScaleObserver;
    private AccessibilityManager mAccessibilityManager;
    private ReactAccessibilityServiceChangeListener mAccessibilityServiceChangeListener;
    private boolean mAccessibilityServiceEnabled;
    private final ContentResolver mContentResolver;
    private int mRecommendedTimeout;
    private boolean mReduceMotionEnabled;
    private boolean mTouchExplorationEnabled;
    private ReactTouchExplorationStateChangeListener mTouchExplorationStateChangeListener;

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.fbreact.specs.NativeAccessibilityInfoSpec
    public void setAccessibilityFocus(double d) {
    }

    private class ReactTouchExplorationStateChangeListener implements AccessibilityManager.TouchExplorationStateChangeListener {
        private ReactTouchExplorationStateChangeListener() {
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            AccessibilityInfoModule.this.updateAndSendTouchExplorationChangeEvent(z);
        }
    }

    private class ReactAccessibilityServiceChangeListener implements AccessibilityManager.AccessibilityStateChangeListener {
        private ReactAccessibilityServiceChangeListener() {
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean z) {
            AccessibilityInfoModule.this.updateAndSendAccessibilityServiceChangeEvent(z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AccessibilityInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.animationScaleObserver = new ContentObserver(UiThreadUtil.getUiThreadHandler()) { // from class: com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                onChange(z, null);
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                if (AccessibilityInfoModule.this.getReactApplicationContext().hasActiveReactInstance()) {
                    AccessibilityInfoModule.this.updateAndSendReduceMotionChangeEvent();
                }
            }
        };
        this.mReduceMotionEnabled = false;
        this.mTouchExplorationEnabled = false;
        this.mAccessibilityServiceEnabled = false;
        this.mAccessibilityManager = (AccessibilityManager) reactApplicationContext.getApplicationContext().getSystemService("accessibility");
        this.mContentResolver = getReactApplicationContext().getContentResolver();
        this.mTouchExplorationEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
        this.mAccessibilityServiceEnabled = this.mAccessibilityManager.isEnabled();
        this.mReduceMotionEnabled = getIsReduceMotionEnabledValue();
        this.mTouchExplorationStateChangeListener = new ReactTouchExplorationStateChangeListener();
        this.mAccessibilityServiceChangeListener = new ReactAccessibilityServiceChangeListener();
    }

    private boolean getIsReduceMotionEnabledValue() {
        String string = Settings.Global.getString(this.mContentResolver, "transition_animation_scale");
        return Float.valueOf(string != null ? Float.parseFloat(string) : 1.0f).floatValue() == 0.0f;
    }

    @Override // com.facebook.fbreact.specs.NativeAccessibilityInfoSpec
    public void isReduceMotionEnabled(Callback callback) {
        callback.invoke(Boolean.valueOf(this.mReduceMotionEnabled));
    }

    @Override // com.facebook.fbreact.specs.NativeAccessibilityInfoSpec
    public void isTouchExplorationEnabled(Callback callback) {
        callback.invoke(Boolean.valueOf(this.mTouchExplorationEnabled));
    }

    @Override // com.facebook.fbreact.specs.NativeAccessibilityInfoSpec
    public void isAccessibilityServiceEnabled(Callback callback) {
        callback.invoke(Boolean.valueOf(this.mAccessibilityServiceEnabled));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAndSendReduceMotionChangeEvent() {
        boolean isReduceMotionEnabledValue = getIsReduceMotionEnabledValue();
        if (this.mReduceMotionEnabled != isReduceMotionEnabledValue) {
            this.mReduceMotionEnabled = isReduceMotionEnabledValue;
            ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
            if (reactApplicationContextIfActiveOrWarn != null) {
                reactApplicationContextIfActiveOrWarn.emitDeviceEvent(REDUCE_MOTION_EVENT_NAME, Boolean.valueOf(this.mReduceMotionEnabled));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAndSendTouchExplorationChangeEvent(boolean z) {
        if (this.mTouchExplorationEnabled != z) {
            this.mTouchExplorationEnabled = z;
            if (getReactApplicationContextIfActiveOrWarn() != null) {
                getReactApplicationContext().emitDeviceEvent(TOUCH_EXPLORATION_EVENT_NAME, Boolean.valueOf(this.mTouchExplorationEnabled));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAndSendAccessibilityServiceChangeEvent(boolean z) {
        if (this.mAccessibilityServiceEnabled != z) {
            this.mAccessibilityServiceEnabled = z;
            if (getReactApplicationContextIfActiveOrWarn() != null) {
                getReactApplicationContext().emitDeviceEvent(ACCESSIBILITY_SERVICE_EVENT_NAME, Boolean.valueOf(this.mAccessibilityServiceEnabled));
            }
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.mAccessibilityManager.addTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
        this.mAccessibilityManager.addAccessibilityStateChangeListener(this.mAccessibilityServiceChangeListener);
        this.mContentResolver.registerContentObserver(Settings.Global.getUriFor("transition_animation_scale"), false, this.animationScaleObserver);
        updateAndSendTouchExplorationChangeEvent(this.mAccessibilityManager.isTouchExplorationEnabled());
        updateAndSendAccessibilityServiceChangeEvent(this.mAccessibilityManager.isEnabled());
        updateAndSendReduceMotionChangeEvent();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.mAccessibilityManager.removeTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
        this.mAccessibilityManager.removeAccessibilityStateChangeListener(this.mAccessibilityServiceChangeListener);
        this.mContentResolver.unregisterContentObserver(this.animationScaleObserver);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
        updateAndSendTouchExplorationChangeEvent(this.mAccessibilityManager.isTouchExplorationEnabled());
        updateAndSendAccessibilityServiceChangeEvent(this.mAccessibilityManager.isEnabled());
        updateAndSendReduceMotionChangeEvent();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        getReactApplicationContext().removeLifecycleEventListener(this);
        super.invalidate();
    }

    @Override // com.facebook.fbreact.specs.NativeAccessibilityInfoSpec
    public void announceForAccessibility(String str) {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
        obtain.getText().add(str);
        obtain.setClassName(AccessibilityInfoModule.class.getName());
        obtain.setPackageName(getReactApplicationContext().getPackageName());
        this.mAccessibilityManager.sendAccessibilityEvent(obtain);
    }

    @Override // com.facebook.fbreact.specs.NativeAccessibilityInfoSpec
    public void getRecommendedTimeoutMillis(double d, Callback callback) {
        int recommendedTimeoutMillis;
        if (Build.VERSION.SDK_INT < 29) {
            callback.invoke(Integer.valueOf((int) d));
            return;
        }
        recommendedTimeoutMillis = this.mAccessibilityManager.getRecommendedTimeoutMillis((int) d, 4);
        this.mRecommendedTimeout = recommendedTimeoutMillis;
        callback.invoke(Integer.valueOf(recommendedTimeoutMillis));
    }
}

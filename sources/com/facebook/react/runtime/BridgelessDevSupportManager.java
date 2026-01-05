package com.facebook.react.runtime;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.HMRClient;
import com.facebook.react.devsupport.ReactInstanceDevHelper;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
class BridgelessDevSupportManager extends DevSupportManagerBase {
    private final ReactHostImpl mReactHost;

    @Override // com.facebook.react.devsupport.DevSupportManagerBase
    protected String getUniqueTag() {
        return "Bridgeless";
    }

    public BridgelessDevSupportManager(ReactHostImpl reactHostImpl, Context context, @Nullable String str) {
        super(context.getApplicationContext(), createInstanceDevHelper(reactHostImpl), str, true, null, null, 2, null, null, null);
        this.mReactHost = reactHostImpl;
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void loadSplitBundleFromServer(final String str, final DevSplitBundleCallback devSplitBundleCallback) {
        fetchSplitBundleAndCreateBundleLoader(str, new DevSupportManagerBase.CallbackWithBundleLoader() { // from class: com.facebook.react.runtime.BridgelessDevSupportManager.1
            @Override // com.facebook.react.devsupport.DevSupportManagerBase.CallbackWithBundleLoader
            public void onSuccess(JSBundleLoader jSBundleLoader) {
                BridgelessDevSupportManager.this.mReactHost.loadBundle(jSBundleLoader).onSuccess(new Continuation<Boolean, Void>() { // from class: com.facebook.react.runtime.BridgelessDevSupportManager.1.1
                    @Override // com.facebook.react.runtime.internal.bolts.Continuation
                    public Void then(Task<Boolean> task) {
                        if (!task.getResult().equals(Boolean.TRUE)) {
                            return null;
                        }
                        String devServerSplitBundleURL = BridgelessDevSupportManager.this.getDevServerHelper().getDevServerSplitBundleURL(str);
                        ReactContext currentReactContext = BridgelessDevSupportManager.this.mReactHost.getCurrentReactContext();
                        if (currentReactContext != null) {
                            ((HMRClient) currentReactContext.getJSModule(HMRClient.class)).registerBundle(devServerSplitBundleURL);
                        }
                        devSplitBundleCallback.onSuccess();
                        return null;
                    }
                });
            }

            @Override // com.facebook.react.devsupport.DevSupportManagerBase.CallbackWithBundleLoader
            public void onError(String str2, Throwable th) {
                devSplitBundleCallback.onError(str2, th);
            }
        });
    }

    @Override // com.facebook.react.devsupport.interfaces.DevSupportManager
    public void handleReloadJS() {
        UiThreadUtil.assertOnUiThread();
        hideRedboxDialog();
        this.mReactHost.reload("BridgelessDevSupportManager.handleReloadJS()");
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from Server");
        reloadJSFromServer(getDevServerHelper().getDevServerBundleURL((String) Assertions.assertNotNull(getJSAppBundleName())));
    }

    private static ReactInstanceDevHelper createInstanceDevHelper(final ReactHostImpl reactHostImpl) {
        return new ReactInstanceDevHelper() { // from class: com.facebook.react.runtime.BridgelessDevSupportManager.2
            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public void destroyRootView(View view) {
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public void onJSBundleLoadedFromServer() {
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public void toggleElementInspector() {
                ReactContext currentReactContext = ReactHostImpl.this.getCurrentReactContext();
                if (currentReactContext != null) {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("toggleElementInspector", null);
                }
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public Activity getCurrentActivity() {
                return ReactHostImpl.this.getLastUsedActivity();
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
                throw new IllegalStateException("Not implemented for bridgeless mode");
            }

            @Override // com.facebook.react.devsupport.ReactInstanceDevHelper
            public View createRootView(String str) {
                Activity currentActivity = getCurrentActivity();
                if (currentActivity == null || ReactHostImpl.this.isSurfaceWithModuleNameAttached(str)) {
                    return null;
                }
                ReactSurfaceImpl createWithView = ReactSurfaceImpl.createWithView(currentActivity, str, new Bundle());
                createWithView.attach(ReactHostImpl.this);
                createWithView.start();
                return createWithView.getView();
            }
        };
    }
}

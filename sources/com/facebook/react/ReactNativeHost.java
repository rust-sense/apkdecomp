package com.facebook.react;

import android.app.Application;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerProvider;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.internal.ChoreographerProvider;
import expo.modules.updates.loader.EmbeddedLoader;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ReactNativeHost {
    private final Application mApplication;
    private ReactInstanceManager mReactInstanceManager;

    static /* synthetic */ UIManager lambda$getUIManagerProvider$0(ReactApplicationContext reactApplicationContext) {
        return null;
    }

    protected final Application getApplication() {
        return this.mApplication;
    }

    protected String getBundleAssetName() {
        return EmbeddedLoader.BARE_BUNDLE_FILENAME;
    }

    protected ChoreographerProvider getChoreographerProvider() {
        return null;
    }

    protected DevLoadingViewManager getDevLoadingViewManager() {
        return null;
    }

    protected DevSupportManagerFactory getDevSupportManagerFactory() {
        return null;
    }

    protected String getJSBundleFile() {
        return null;
    }

    protected JSEngineResolutionAlgorithm getJSEngineResolutionAlgorithm() {
        return null;
    }

    protected String getJSMainModuleName() {
        return "index.android";
    }

    protected JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
        return null;
    }

    public boolean getLazyViewManagersEnabled() {
        return false;
    }

    protected abstract List<ReactPackage> getPackages();

    protected ReactPackageTurboModuleManagerDelegate.Builder getReactPackageTurboModuleManagerDelegateBuilder() {
        return null;
    }

    protected RedBoxHandler getRedBoxHandler() {
        return null;
    }

    public boolean getShouldRequireActivity() {
        return true;
    }

    public abstract boolean getUseDeveloperSupport();

    public boolean hasInstance() {
        return this.mReactInstanceManager != null;
    }

    protected ReactNativeHost(Application application) {
        this.mApplication = application;
    }

    public ReactInstanceManager getReactInstanceManager() {
        if (this.mReactInstanceManager == null) {
            ReactMarker.logMarker(ReactMarkerConstants.INIT_REACT_RUNTIME_START);
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_START);
            this.mReactInstanceManager = createReactInstanceManager();
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_END);
        }
        return this.mReactInstanceManager;
    }

    public void clear() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.destroy();
            this.mReactInstanceManager = null;
        }
    }

    protected ReactInstanceManager createReactInstanceManager() {
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_START);
        ReactInstanceManagerBuilder choreographerProvider = ReactInstanceManager.builder().setApplication(this.mApplication).setJSMainModulePath(getJSMainModuleName()).setUseDeveloperSupport(getUseDeveloperSupport()).setDevSupportManagerFactory(getDevSupportManagerFactory()).setDevLoadingViewManager(getDevLoadingViewManager()).setRequireActivity(getShouldRequireActivity()).setSurfaceDelegateFactory(getSurfaceDelegateFactory()).setLazyViewManagersEnabled(getLazyViewManagersEnabled()).setRedBoxHandler(getRedBoxHandler()).setJavaScriptExecutorFactory(getJavaScriptExecutorFactory()).setUIManagerProvider(getUIManagerProvider()).setInitialLifecycleState(LifecycleState.BEFORE_CREATE).setReactPackageTurboModuleManagerDelegateBuilder(getReactPackageTurboModuleManagerDelegateBuilder()).setJSEngineResolutionAlgorithm(getJSEngineResolutionAlgorithm()).setChoreographerProvider(getChoreographerProvider());
        Iterator<ReactPackage> it = getPackages().iterator();
        while (it.hasNext()) {
            choreographerProvider.addPackage(it.next());
        }
        String jSBundleFile = getJSBundleFile();
        if (jSBundleFile != null) {
            choreographerProvider.setJSBundleFile(jSBundleFile);
        } else {
            choreographerProvider.setBundleAssetName((String) Assertions.assertNotNull(getBundleAssetName()));
        }
        ReactInstanceManager build = choreographerProvider.build();
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_END);
        return build;
    }

    protected UIManagerProvider getUIManagerProvider() {
        return new UIManagerProvider() { // from class: com.facebook.react.ReactNativeHost$$ExternalSyntheticLambda0
            @Override // com.facebook.react.bridge.UIManagerProvider
            public final UIManager createUIManager(ReactApplicationContext reactApplicationContext) {
                return ReactNativeHost.lambda$getUIManagerProvider$0(reactApplicationContext);
            }
        };
    }

    public SurfaceDelegateFactory getSurfaceDelegateFactory() {
        return new SurfaceDelegateFactory() { // from class: com.facebook.react.ReactNativeHost.1
            @Override // com.facebook.react.common.SurfaceDelegateFactory
            public SurfaceDelegate createSurfaceDelegate(String str) {
                return null;
            }
        };
    }
}

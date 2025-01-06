package com.facebook.react.runtime;

import android.content.res.AssetManager;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.react.DebugCorePackage;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSBundleLoaderDelegate;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.fabric.BindingImpl;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.ReactNativeConfig;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler;
import com.facebook.react.internal.AndroidChoreographerProvider;
import com.facebook.react.internal.turbomodule.core.TurboModuleManager;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.JavaTimerManager;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.NativeMethodCallInvokerHolderImpl;
import com.facebook.react.uimanager.ComponentNameResolver;
import com.facebook.react.uimanager.ComponentNameResolverManager;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.UIConstantsProviderManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModuleConstantsHelper;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.ViewManagerResolver;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
final class ReactInstance {
    private static final String TAG = "ReactInstance";
    private static volatile boolean sIsLibraryLoaded;
    private final BridgelessReactContext mBridgelessReactContext;

    @Nullable
    private ComponentNameResolverManager mComponentNameResolverManager;
    private final ReactHostDelegate mDelegate;
    private final FabricUIManager mFabricUIManager;
    private final HybridData mHybridData;
    private JavaScriptContextHolder mJavaScriptContextHolder;
    private final JavaTimerManager mJavaTimerManager;
    private final ReactQueueConfiguration mQueueConfiguration;
    private final List<ReactPackage> mReactPackages;
    private final TurboModuleManager mTurboModuleManager;

    @Nullable
    private UIConstantsProviderManager mUIConstantsProviderManager;
    private final BridgelessViewManagerResolver mViewManagerResolver;

    private static native JSTimerExecutor createJSTimerExecutor();

    private native long getJavaScriptContext();

    private native NativeMethodCallInvokerHolderImpl getNativeMethodCallInvokerHolder();

    private native RuntimeScheduler getRuntimeScheduler();

    private native RuntimeExecutor getUnbufferedRuntimeExecutor();

    private native void handleMemoryPressureJs(int i);

    private native HybridData initHybrid(JSRuntimeFactory jSRuntimeFactory, MessageQueueThread messageQueueThread, MessageQueueThread messageQueueThread2, JavaTimerManager javaTimerManager, JSTimerExecutor jSTimerExecutor, ReactJsExceptionHandler reactJsExceptionHandler, @Nullable BindingsInstaller bindingsInstaller, boolean z);

    private native void installGlobals(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void loadJSBundleFromAssets(AssetManager assetManager, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void loadJSBundleFromFile(String str, String str2);

    private native void registerSegmentNative(int i, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public native void callFunctionOnModule(String str, String str2, NativeArray nativeArray);

    native RuntimeExecutor getBufferedRuntimeExecutor();

    native CallInvokerHolderImpl getJSCallInvokerHolder();

    JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.mJavaScriptContextHolder;
    }

    public ReactQueueConfiguration getReactQueueConfiguration() {
        return this.mQueueConfiguration;
    }

    FabricUIManager getUIManager() {
        return this.mFabricUIManager;
    }

    static {
        loadLibraryIfNeeded();
    }

    ReactInstance(BridgelessReactContext bridgelessReactContext, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, DevSupportManager devSupportManager, QueueThreadExceptionHandler queueThreadExceptionHandler, ReactJsExceptionHandler reactJsExceptionHandler, boolean z) {
        this.mBridgelessReactContext = bridgelessReactContext;
        this.mDelegate = reactHostDelegate;
        Systrace.beginSection(0L, "ReactInstance.initialize");
        ReactQueueConfigurationImpl create = ReactQueueConfigurationImpl.create(ReactQueueConfigurationSpec.builder().setJSQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("v_js")).setNativeModulesQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("v_native")).build(), queueThreadExceptionHandler);
        this.mQueueConfiguration = create;
        FLog.d(TAG, "Calling initializeMessageQueueThreads()");
        bridgelessReactContext.initializeMessageQueueThreads(create);
        MessageQueueThread jSQueueThread = create.getJSQueueThread();
        MessageQueueThread nativeModulesQueueThread = create.getNativeModulesQueueThread();
        ReactChoreographer.initialize(AndroidChoreographerProvider.getInstance());
        if (z) {
            devSupportManager.startInspector();
        }
        JSTimerExecutor createJSTimerExecutor = createJSTimerExecutor();
        JavaTimerManager javaTimerManager = new JavaTimerManager(bridgelessReactContext, createJSTimerExecutor, ReactChoreographer.getInstance(), devSupportManager);
        this.mJavaTimerManager = javaTimerManager;
        bridgelessReactContext.addLifecycleEventListener(new LifecycleEventListener() { // from class: com.facebook.react.runtime.ReactInstance.1
            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostResume() {
                ReactInstance.this.mJavaTimerManager.onHostResume();
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostPause() {
                ReactInstance.this.mJavaTimerManager.onHostPause();
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostDestroy() {
                ReactInstance.this.mJavaTimerManager.onHostDestroy();
            }
        });
        this.mHybridData = initHybrid(reactHostDelegate.getJsRuntimeFactory(), jSQueueThread, nativeModulesQueueThread, javaTimerManager, createJSTimerExecutor, reactJsExceptionHandler, reactHostDelegate.getBindingsInstaller(), Systrace.isTracing(0L));
        this.mJavaScriptContextHolder = new JavaScriptContextHolder(getJavaScriptContext());
        Systrace.beginSection(0L, "ReactInstance.initialize#initTurboModules");
        ArrayList arrayList = new ArrayList();
        this.mReactPackages = arrayList;
        arrayList.add(new CoreReactPackage(bridgelessReactContext.getDevSupportManager(), bridgelessReactContext.getDefaultHardwareBackBtnHandler()));
        if (z) {
            arrayList.add(new DebugCorePackage());
        }
        arrayList.addAll(reactHostDelegate.getReactPackages());
        ReactPackageTurboModuleManagerDelegate build = reactHostDelegate.getTurboModuleManagerDelegateBuilder().setPackages(arrayList).setReactApplicationContext(bridgelessReactContext).build();
        RuntimeExecutor unbufferedRuntimeExecutor = getUnbufferedRuntimeExecutor();
        TurboModuleManager turboModuleManager = new TurboModuleManager(unbufferedRuntimeExecutor, build, getJSCallInvokerHolder(), getNativeMethodCallInvokerHolder());
        this.mTurboModuleManager = turboModuleManager;
        Iterator<String> it = turboModuleManager.getEagerInitModuleNames().iterator();
        while (it.hasNext()) {
            this.mTurboModuleManager.getModule(it.next());
        }
        Systrace.endSection(0L);
        Systrace.beginSection(0L, "ReactInstance.initialize#initFabric");
        BridgelessViewManagerResolver bridgelessViewManagerResolver = new BridgelessViewManagerResolver(this.mReactPackages, this.mBridgelessReactContext);
        this.mViewManagerResolver = bridgelessViewManagerResolver;
        this.mComponentNameResolverManager = new ComponentNameResolverManager(unbufferedRuntimeExecutor, new ComponentNameResolver() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda0
            @Override // com.facebook.react.uimanager.ComponentNameResolver
            public final String[] getComponentNames() {
                String[] lambda$new$0;
                lambda$new$0 = ReactInstance.this.lambda$new$0();
                return lambda$new$0;
            }
        });
        if (ReactFeatureFlags.useNativeViewConfigsInBridgelessMode) {
            final HashMap hashMap = new HashMap();
            this.mUIConstantsProviderManager = new UIConstantsProviderManager(unbufferedRuntimeExecutor, new UIConstantsProviderManager.DefaultEventTypesProvider() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda1
                @Override // com.facebook.react.uimanager.UIConstantsProviderManager.DefaultEventTypesProvider
                public final NativeMap getDefaultEventTypes() {
                    NativeMap makeNativeMap;
                    makeNativeMap = Arguments.makeNativeMap(UIManagerModuleConstantsHelper.getDefaultExportableEventTypes());
                    return makeNativeMap;
                }
            }, new UIConstantsProviderManager.ConstantsForViewManagerProvider() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda2
                @Override // com.facebook.react.uimanager.UIConstantsProviderManager.ConstantsForViewManagerProvider
                public final NativeMap getConstantsForViewManager(String str) {
                    NativeMap lambda$new$2;
                    lambda$new$2 = ReactInstance.this.lambda$new$2(hashMap, str);
                    return lambda$new$2;
                }
            }, new UIConstantsProviderManager.ConstantsProvider() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda3
                @Override // com.facebook.react.uimanager.UIConstantsProviderManager.ConstantsProvider
                public final NativeMap getConstants() {
                    NativeMap lambda$new$3;
                    lambda$new$3 = ReactInstance.this.lambda$new$3(hashMap);
                    return lambda$new$3;
                }
            });
        }
        EventBeatManager eventBeatManager = new EventBeatManager();
        FabricUIManager fabricUIManager = new FabricUIManager(this.mBridgelessReactContext, new ViewManagerRegistry(bridgelessViewManagerResolver), eventBeatManager);
        this.mFabricUIManager = fabricUIManager;
        ReactNativeConfig reactNativeConfig = this.mDelegate.getReactNativeConfig();
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(this.mBridgelessReactContext);
        new BindingImpl().register(getBufferedRuntimeExecutor(), getRuntimeScheduler(), fabricUIManager, eventBeatManager, componentFactory, reactNativeConfig);
        Systrace.endSection(0L);
        fabricUIManager.initialize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String[] lambda$new$0() {
        Collection<String> viewManagerNames = this.mViewManagerResolver.getViewManagerNames();
        if (viewManagerNames.size() < 1) {
            FLog.e(TAG, "No ViewManager names found");
            return new String[0];
        }
        return (String[]) viewManagerNames.toArray(new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ NativeMap lambda$new$2(Map map, String str) {
        ViewManager viewManager = this.mViewManagerResolver.getViewManager(str);
        if (viewManager == null) {
            return null;
        }
        return (NativeMap) UIManagerModule.getConstantsForViewManager(viewManager, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ NativeMap lambda$new$3(Map map) {
        Map<String, Object> createConstants = UIManagerModule.createConstants(new ArrayList(this.mViewManagerResolver.getEagerViewManagerMap().values()), null, map);
        Collection<String> lazyViewManagerNames = this.mViewManagerResolver.getLazyViewManagerNames();
        if (lazyViewManagerNames.size() > 0) {
            createConstants.put("ViewManagerNames", new ArrayList(lazyViewManagerNames));
            createConstants.put("LazyViewManagersEnabled", true);
        }
        return Arguments.makeNativeMap(createConstants);
    }

    private static synchronized void loadLibraryIfNeeded() {
        synchronized (ReactInstance.class) {
            if (!sIsLibraryLoaded) {
                SoLoader.loadLibrary("rninstance");
                sIsLibraryLoaded = true;
            }
        }
    }

    public void loadJSBundle(JSBundleLoader jSBundleLoader) {
        Systrace.beginSection(0L, "ReactInstance.loadJSBundle");
        jSBundleLoader.loadScript(new JSBundleLoaderDelegate() { // from class: com.facebook.react.runtime.ReactInstance.2
            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void loadScriptFromFile(String str, String str2, boolean z) {
                ReactInstance.this.mBridgelessReactContext.setSourceURL(str2);
                ReactInstance.this.loadJSBundleFromFile(str, str2);
            }

            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void loadSplitBundleFromFile(String str, String str2) {
                ReactInstance.this.loadJSBundleFromFile(str, str2);
            }

            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void loadScriptFromAssets(AssetManager assetManager, String str, boolean z) {
                ReactInstance.this.mBridgelessReactContext.setSourceURL(str);
                ReactInstance.this.loadJSBundleFromAssets(assetManager, str);
            }

            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void setSourceURLs(String str, String str2) {
                ReactInstance.this.mBridgelessReactContext.setSourceURL(str);
            }
        });
        Systrace.endSection(0L);
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return this.mTurboModuleManager.hasModule(reactModule.name());
        }
        return false;
    }

    public Collection<NativeModule> getNativeModules() {
        return new ArrayList(this.mTurboModuleManager.getModules());
    }

    @Nullable
    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return (T) getNativeModule(reactModule.name());
        }
        return null;
    }

    @Nullable
    public NativeModule getNativeModule(String str) {
        NativeModule module;
        synchronized (this.mTurboModuleManager) {
            module = this.mTurboModuleManager.getModule(str);
        }
        return module;
    }

    void prerenderSurface(ReactSurfaceImpl reactSurfaceImpl) {
        Systrace.beginSection(0L, "ReactInstance.prerenderSurface");
        FLog.d(TAG, "call prerenderSurface with surface: " + reactSurfaceImpl.getModuleName());
        this.mFabricUIManager.startSurface(reactSurfaceImpl.getSurfaceHandler(), reactSurfaceImpl.getContext(), null);
        Systrace.endSection(0L);
    }

    void startSurface(ReactSurfaceImpl reactSurfaceImpl) {
        String str = TAG;
        FLog.d(str, "startSurface() is called with surface: " + reactSurfaceImpl.getSurfaceID());
        Systrace.beginSection(0L, "ReactInstance.startSurface");
        ViewGroup view = reactSurfaceImpl.getView();
        if (view == null) {
            throw new IllegalStateException("Starting surface without a view is not supported, use prerenderSurface instead.");
        }
        if (view.getId() != -1) {
            ReactSoftExceptionLogger.logSoftException(str, new IllegalViewOperationException("surfaceView's is NOT equal to View.NO_ID before calling startSurface."));
            view.setId(-1);
        }
        if (reactSurfaceImpl.isRunning()) {
            this.mFabricUIManager.attachRootView(reactSurfaceImpl.getSurfaceHandler(), view);
        } else {
            this.mFabricUIManager.startSurface(reactSurfaceImpl.getSurfaceHandler(), reactSurfaceImpl.getContext(), view);
        }
        Systrace.endSection(0L);
    }

    void stopSurface(ReactSurfaceImpl reactSurfaceImpl) {
        FLog.d(TAG, "stopSurface() is called with surface: " + reactSurfaceImpl.getSurfaceID());
        this.mFabricUIManager.stopSurface(reactSurfaceImpl.getSurfaceHandler());
    }

    void destroy() {
        FLog.d(TAG, "ReactInstance.destroy() is called.");
        this.mQueueConfiguration.destroy();
        this.mTurboModuleManager.invalidate();
        this.mFabricUIManager.invalidate();
        this.mHybridData.resetNative();
        this.mComponentNameResolverManager = null;
        this.mUIConstantsProviderManager = null;
        this.mJavaScriptContextHolder.clear();
    }

    public void handleMemoryPressure(int i) {
        try {
            handleMemoryPressureJs(i);
        } catch (NullPointerException unused) {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Native method handleMemoryPressureJs is called earlier than librninstance.so got ready."));
        }
    }

    EventDispatcher getEventDispatcher() {
        return this.mFabricUIManager.getEventDispatcher();
    }

    public void registerSegment(int i, String str) {
        registerSegmentNative(i, str);
    }

    private static class BridgelessViewManagerResolver implements ViewManagerResolver {
        private BridgelessReactContext mBridgelessReactContext;
        private List<ReactPackage> mReactPackages;
        private Map<String, ViewManager> mLazyViewManagerMap = new HashMap();

        @Nullable
        private Map<String, ViewManager> mEagerViewManagerMap = null;

        public BridgelessViewManagerResolver(List<ReactPackage> list, BridgelessReactContext bridgelessReactContext) {
            this.mReactPackages = list;
            this.mBridgelessReactContext = bridgelessReactContext;
        }

        @Override // com.facebook.react.uimanager.ViewManagerResolver
        @Nullable
        public synchronized ViewManager getViewManager(String str) {
            ViewManager lazyViewManager = getLazyViewManager(str);
            if (lazyViewManager != null) {
                return lazyViewManager;
            }
            return getEagerViewManagerMap().get(str);
        }

        @Override // com.facebook.react.uimanager.ViewManagerResolver
        public synchronized Collection<String> getViewManagerNames() {
            HashSet hashSet;
            hashSet = new HashSet();
            hashSet.addAll(getLazyViewManagerNames());
            hashSet.addAll(getEagerViewManagerMap().keySet());
            return hashSet;
        }

        public synchronized Map<String, ViewManager> getEagerViewManagerMap() {
            Map<String, ViewManager> map = this.mEagerViewManagerMap;
            if (map != null) {
                return map;
            }
            HashMap hashMap = new HashMap();
            for (ReactPackage reactPackage : this.mReactPackages) {
                if (!(reactPackage instanceof ViewManagerOnDemandReactPackage)) {
                    for (ViewManager viewManager : reactPackage.createViewManagers(this.mBridgelessReactContext)) {
                        hashMap.put(viewManager.getName(), viewManager);
                    }
                }
            }
            this.mEagerViewManagerMap = hashMap;
            return hashMap;
        }

        @Nullable
        private ViewManager getLazyViewManager(String str) {
            ViewManager createViewManager;
            if (this.mLazyViewManagerMap.containsKey(str)) {
                return this.mLazyViewManagerMap.get(str);
            }
            for (ReactPackage reactPackage : this.mReactPackages) {
                if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (createViewManager = ((ViewManagerOnDemandReactPackage) reactPackage).createViewManager(this.mBridgelessReactContext, str)) != null) {
                    this.mLazyViewManagerMap.put(str, createViewManager);
                    return createViewManager;
                }
            }
            return null;
        }

        public synchronized Collection<String> getLazyViewManagerNames() {
            HashSet hashSet;
            Collection<String> viewManagerNames;
            hashSet = new HashSet();
            for (ReactPackage reactPackage : this.mReactPackages) {
                if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (viewManagerNames = ((ViewManagerOnDemandReactPackage) reactPackage).getViewManagerNames(this.mBridgelessReactContext)) != null) {
                    hashSet.addAll(viewManagerNames);
                }
            }
            return hashSet;
        }
    }
}

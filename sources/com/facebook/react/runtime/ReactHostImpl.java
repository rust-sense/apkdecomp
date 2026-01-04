package com.facebook.react.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.JSEngineResolutionAlgorithm;
import com.facebook.react.MemoryPressureRouter;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.MemoryPressureListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactNoCrashBridgeNotAllowedSoftException;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.DisabledDevSupportManager;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.runtime.BridgelessAtomicRef;
import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;
import com.facebook.react.runtime.internal.bolts.TaskCompletionSource;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.BlackHoleEventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public class ReactHostImpl implements ReactHost {
    private static final int BRIDGELESS_MARKER_INSTANCE_KEY = 1;
    private static final boolean DEV = false;
    private static final String TAG = "ReactHost";
    private static final AtomicInteger mCounter = new AtomicInteger(0);
    private final AtomicReference<Activity> mActivity;
    private final boolean mAllowPackagerServerAccess;
    private final Set<ReactSurfaceImpl> mAttachedSurfaces;
    private final Executor mBGExecutor;
    private final Set<Function0<Unit>> mBeforeDestroyListeners;
    private final BridgelessAtomicRef<BridgelessReactContext> mBridgelessReactContextRef;
    private final BridgelessReactStateTracker mBridgelessReactStateTracker;
    private final ComponentFactory mComponentFactory;
    private final Context mContext;
    private DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler;
    private Task<Void> mDestroyTask;
    private final DevSupportManager mDevSupportManager;
    private final int mId;
    private JSEngineResolutionAlgorithm mJSEngineResolutionAlgorithm;
    private final AtomicReference<WeakReference<Activity>> mLastUsedActivity;
    private MemoryPressureListener mMemoryPressureListener;
    private final MemoryPressureRouter mMemoryPressureRouter;
    private final QueueThreadExceptionHandler mQueueThreadExceptionHandler;
    private final ReactHostDelegate mReactHostDelegate;
    private final Collection<ReactInstanceEventListener> mReactInstanceEventListeners;
    private final BridgelessAtomicRef<Task<ReactInstance>> mReactInstanceTaskRef;
    private final ReactJsExceptionHandler mReactJsExceptionHandler;
    private final ReactLifecycleStateManager mReactLifecycleStateManager;
    private Task<ReactInstance> mReloadTask;
    private Task<Void> mStartTask;
    private final Executor mUIExecutor;
    private final boolean mUseDevSupport;

    /* JADX INFO: Access modifiers changed from: private */
    interface ReactInstanceTaskUnwrapper {
        ReactInstance unwrap(Task<ReactInstance> task, String str);
    }

    interface VeniceThenable<T> {
        void then(T t);
    }

    @Override // com.facebook.react.ReactHost
    public JSEngineResolutionAlgorithm getJsEngineResolutionAlgorithm() {
        return this.mJSEngineResolutionAlgorithm;
    }

    public MemoryPressureRouter getMemoryPressureRouter() {
        return this.mMemoryPressureRouter;
    }

    @Override // com.facebook.react.ReactHost
    public void setJsEngineResolutionAlgorithm(JSEngineResolutionAlgorithm jSEngineResolutionAlgorithm) {
        this.mJSEngineResolutionAlgorithm = jSEngineResolutionAlgorithm;
    }

    public ReactHostImpl(Context context, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, boolean z, ReactJsExceptionHandler reactJsExceptionHandler, boolean z2) {
        this(context, reactHostDelegate, componentFactory, Executors.newSingleThreadExecutor(), Task.UI_THREAD_EXECUTOR, reactJsExceptionHandler, z, z2);
    }

    public ReactHostImpl(Context context, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, Executor executor, Executor executor2, ReactJsExceptionHandler reactJsExceptionHandler, boolean z, boolean z2) {
        this.mAttachedSurfaces = Collections.synchronizedSet(new HashSet());
        this.mReactInstanceEventListeners = Collections.synchronizedList(new ArrayList());
        this.mReactInstanceTaskRef = new BridgelessAtomicRef<>(Task.forResult((ReactInstance) Assertions.nullsafeFIXME(null, "forResult parameter supports null, but is not annotated as @Nullable")));
        this.mBridgelessReactContextRef = new BridgelessAtomicRef<>();
        this.mActivity = new AtomicReference<>();
        this.mLastUsedActivity = new AtomicReference<>(new WeakReference(null));
        BridgelessReactStateTracker bridgelessReactStateTracker = new BridgelessReactStateTracker(false);
        this.mBridgelessReactStateTracker = bridgelessReactStateTracker;
        this.mReactLifecycleStateManager = new ReactLifecycleStateManager(bridgelessReactStateTracker);
        this.mId = mCounter.getAndIncrement();
        this.mJSEngineResolutionAlgorithm = null;
        this.mBeforeDestroyListeners = Collections.synchronizedSet(new HashSet());
        this.mStartTask = null;
        this.mReloadTask = null;
        this.mDestroyTask = null;
        this.mContext = context;
        this.mReactHostDelegate = reactHostDelegate;
        this.mComponentFactory = componentFactory;
        this.mBGExecutor = executor;
        this.mUIExecutor = executor2;
        this.mReactJsExceptionHandler = reactJsExceptionHandler;
        this.mQueueThreadExceptionHandler = new QueueThreadExceptionHandler() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda12
            @Override // com.facebook.react.bridge.queue.QueueThreadExceptionHandler
            public final void handleException(Exception exc) {
                ReactHostImpl.this.handleHostException(exc);
            }
        };
        this.mMemoryPressureRouter = new MemoryPressureRouter(context);
        this.mMemoryPressureListener = new MemoryPressureListener() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda13
            @Override // com.facebook.react.bridge.MemoryPressureListener
            public final void handleMemoryPressure(int i) {
                ReactHostImpl.this.lambda$new$1(i);
            }
        };
        this.mAllowPackagerServerAccess = z;
        this.mDevSupportManager = new DisabledDevSupportManager();
        this.mUseDevSupport = z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(final int i) {
        callWithExistingReactInstance("handleMemoryPressure(" + i + ")", new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda35
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                ((ReactInstance) obj).handleMemoryPressure(i);
            }
        });
    }

    @Override // com.facebook.react.ReactHost
    public LifecycleState getLifecycleState() {
        return this.mReactLifecycleStateManager.getLifecycleState();
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> start() {
        return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda34
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Task orCreateStartTask;
                orCreateStartTask = ReactHostImpl.this.getOrCreateStartTask();
                return orCreateStartTask;
            }
        }, this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda20());
    }

    TaskInterface<Void> prerenderSurface(final ReactSurfaceImpl reactSurfaceImpl) {
        final String str = "prerenderSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")";
        log(str, "Schedule");
        attachSurface(reactSurfaceImpl);
        return callAfterGetOrCreateReactInstance(str, new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda15
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                ReactHostImpl.this.lambda$prerenderSurface$2(str, reactSurfaceImpl, (ReactInstance) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prerenderSurface$2(String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.prerenderSurface(reactSurfaceImpl);
    }

    TaskInterface<Void> startSurface(final ReactSurfaceImpl reactSurfaceImpl) {
        final String str = "startSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")";
        log(str, "Schedule");
        attachSurface(reactSurfaceImpl);
        return callAfterGetOrCreateReactInstance(str, new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda23
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                ReactHostImpl.this.lambda$startSurface$3(str, reactSurfaceImpl, (ReactInstance) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startSurface$3(String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.startSurface(reactSurfaceImpl);
    }

    TaskInterface<Void> stopSurface(final ReactSurfaceImpl reactSurfaceImpl) {
        final String str = "stopSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")";
        log(str, "Schedule");
        detachSurface(reactSurfaceImpl);
        return callWithExistingReactInstance(str, new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda41
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                ReactHostImpl.this.lambda$stopSurface$4(str, reactSurfaceImpl, (ReactInstance) obj);
            }
        }).makeVoid();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$stopSurface$4(String str, ReactSurfaceImpl reactSurfaceImpl, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.stopSurface(reactSurfaceImpl);
    }

    @Override // com.facebook.react.ReactHost
    public void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        this.mDefaultHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        onHostResume(activity);
    }

    @Override // com.facebook.react.ReactHost
    public void onHostResume(Activity activity) {
        log("onHostResume(activity)");
        setCurrentActivity(activity);
        this.mReactLifecycleStateManager.moveToOnHostResume(getCurrentReactContext(), getCurrentActivity());
    }

    @Override // com.facebook.react.ReactHost
    public void onHostPause(Activity activity) {
        log("onHostPause(activity)");
        ReactContext currentReactContext = getCurrentReactContext();
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            String simpleName = currentActivity.getClass().getSimpleName();
            String simpleName2 = activity == null ? "null" : activity.getClass().getSimpleName();
            Assertions.assertCondition(activity == currentActivity, "Pausing an activity that is not the current activity, this is incorrect! Current activity: " + simpleName + " Paused activity: " + simpleName2);
        }
        this.mDefaultHardwareBackBtnHandler = null;
        this.mReactLifecycleStateManager.moveToOnHostPause(currentReactContext, currentActivity);
    }

    @Override // com.facebook.react.ReactHost
    public void onHostPause() {
        log("onHostPause()");
        ReactContext currentReactContext = getCurrentReactContext();
        this.mDefaultHardwareBackBtnHandler = null;
        this.mReactLifecycleStateManager.moveToOnHostPause(currentReactContext, getCurrentActivity());
    }

    @Override // com.facebook.react.ReactHost
    public void onHostDestroy() {
        log("onHostDestroy()");
        moveToHostDestroy(getCurrentReactContext());
    }

    @Override // com.facebook.react.ReactHost
    public void onHostDestroy(Activity activity) {
        log("onHostDestroy(activity)");
        if (getCurrentActivity() == activity) {
            moveToHostDestroy(getCurrentReactContext());
        }
    }

    @Override // com.facebook.react.ReactHost
    public ReactContext getCurrentReactContext() {
        return this.mBridgelessReactContextRef.getNullable();
    }

    @Override // com.facebook.react.ReactHost
    public DevSupportManager getDevSupportManager() {
        return (DevSupportManager) Assertions.assertNotNull(this.mDevSupportManager);
    }

    @Override // com.facebook.react.ReactHost
    public ReactSurface createSurface(Context context, String str, Bundle bundle) {
        ReactSurfaceImpl reactSurfaceImpl = new ReactSurfaceImpl(context, str, bundle);
        reactSurfaceImpl.attachView(new ReactSurfaceView(context, reactSurfaceImpl));
        reactSurfaceImpl.attach(this);
        return reactSurfaceImpl;
    }

    boolean isInstanceInitialized() {
        return this.mReactInstanceTaskRef.get().getResult() != null;
    }

    @Override // com.facebook.react.ReactHost
    public boolean onBackPressed() {
        DeviceEventManagerModule deviceEventManagerModule;
        UiThreadUtil.assertOnUiThread();
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result == null || (deviceEventManagerModule = (DeviceEventManagerModule) result.getNativeModule(DeviceEventManagerModule.class)) == null) {
            return false;
        }
        deviceEventManagerModule.emitHardwareBackPressed();
        return true;
    }

    @Override // com.facebook.react.ReactHost
    public ReactQueueConfiguration getReactQueueConfiguration() {
        synchronized (this.mReactInstanceTaskRef) {
            Task<ReactInstance> task = this.mReactInstanceTaskRef.get();
            if (task.isFaulted() || task.isCancelled() || task.getResult() == null) {
                return null;
            }
            return task.getResult().getReactQueueConfiguration();
        }
    }

    public void addReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.add(reactInstanceEventListener);
    }

    public void removeReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener) {
        this.mReactInstanceEventListeners.remove(reactInstanceEventListener);
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> reload(final String str) {
        return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda19
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Task lambda$reload$7;
                lambda$reload$7 = ReactHostImpl.this.lambda$reload$7(str);
                return lambda$reload$7;
            }
        }, this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda20());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$reload$7(final String str) throws Exception {
        Task<Void> makeVoid;
        if (this.mDestroyTask != null) {
            log("reload()", "Waiting for destroy to finish, before reloading React Native.");
            makeVoid = this.mDestroyTask.continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda17
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$reload$5;
                    lambda$reload$5 = ReactHostImpl.this.lambda$reload$5(str, task);
                    return lambda$reload$5;
                }
            }, this.mBGExecutor).makeVoid();
        } else {
            makeVoid = getOrCreateReloadTask(str).makeVoid();
        }
        return makeVoid.continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda18
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                Task lambda$reload$6;
                lambda$reload$6 = ReactHostImpl.this.lambda$reload$6(task);
                return lambda$reload$6;
            }
        }, this.mBGExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$reload$5(String str, Task task) throws Exception {
        return getOrCreateReloadTask(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$reload$6(Task task) throws Exception {
        if (!task.isFaulted()) {
            return task;
        }
        this.mReactHostDelegate.handleInstanceException(task.getError());
        return getOrCreateDestroyTask("Reload failed", task.getError());
    }

    @Override // com.facebook.react.ReactHost
    public TaskInterface<Void> destroy(final String str, final Exception exc) {
        return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda30
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Task lambda$destroy$9;
                lambda$destroy$9 = ReactHostImpl.this.lambda$destroy$9(str, exc);
                return lambda$destroy$9;
            }
        }, this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda20());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$destroy$9(final String str, final Exception exc) throws Exception {
        if (this.mReloadTask != null) {
            log("destroy()", "Reloading React Native. Waiting for reload to finish before destroying React Native.");
            return this.mReloadTask.continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda26
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$destroy$8;
                    lambda$destroy$8 = ReactHostImpl.this.lambda$destroy$8(str, exc, task);
                    return lambda$destroy$8;
                }
            }, this.mBGExecutor);
        }
        return getOrCreateDestroyTask(str, exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$destroy$8(String str, Exception exc, Task task) throws Exception {
        return getOrCreateDestroyTask(str, exc);
    }

    private MemoryPressureListener createMemoryPressureListener(ReactInstance reactInstance) {
        final WeakReference weakReference = new WeakReference(reactInstance);
        return new MemoryPressureListener() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda4
            @Override // com.facebook.react.bridge.MemoryPressureListener
            public final void handleMemoryPressure(int i) {
                ReactHostImpl.this.lambda$createMemoryPressureListener$11(weakReference, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createMemoryPressureListener$11(final WeakReference weakReference, final int i) {
        this.mBGExecutor.execute(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ReactHostImpl.lambda$createMemoryPressureListener$10(weakReference, i);
            }
        });
    }

    static /* synthetic */ void lambda$createMemoryPressureListener$10(WeakReference weakReference, int i) {
        ReactInstance reactInstance = (ReactInstance) weakReference.get();
        if (reactInstance != null) {
            reactInstance.handleMemoryPressure(i);
        }
    }

    Activity getCurrentActivity() {
        return this.mActivity.get();
    }

    Activity getLastUsedActivity() {
        WeakReference<Activity> weakReference = this.mLastUsedActivity.get();
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private void setCurrentActivity(Activity activity) {
        this.mActivity.set(activity);
        if (activity != null) {
            this.mLastUsedActivity.set(new WeakReference<>(activity));
        }
    }

    EventDispatcher getEventDispatcher() {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result == null) {
            return BlackHoleEventDispatcher.get();
        }
        return result.getEventDispatcher();
    }

    FabricUIManager getUIManager() {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result == null) {
            return null;
        }
        return result.getUIManager();
    }

    <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return result.hasNativeModule(cls);
        }
        return false;
    }

    Collection<NativeModule> getNativeModules() {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return result.getNativeModules();
        }
        return new ArrayList();
    }

    <T extends NativeModule> T getNativeModule(Class<T> cls) {
        if (cls == UIManagerModule.class) {
            ReactSoftExceptionLogger.logSoftExceptionVerbose(TAG, new ReactNoCrashBridgeNotAllowedSoftException("getNativeModule(UIManagerModule.class) cannot be called when the bridge is disabled"));
        }
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return (T) result.getNativeModule(cls);
        }
        return null;
    }

    NativeModule getNativeModule(String str) {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return result.getNativeModule(str);
        }
        return null;
    }

    RuntimeExecutor getRuntimeExecutor() {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return result.getBufferedRuntimeExecutor();
        }
        ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Tried to get runtime executor while instance is not ready"));
        return null;
    }

    CallInvokerHolder getJSCallInvokerHolder() {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return result.getJSCallInvokerHolder();
        }
        ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Tried to get JSCallInvokerHolder while instance is not ready"));
        return null;
    }

    @Override // com.facebook.react.ReactHost
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        log("onActivityResult(activity = \"" + activity + "\", requestCode = \"" + i + "\", resultCode = \"" + i2 + "\", data = \"" + intent + "\")");
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onActivityResult(activity, i, i2, intent);
        } else {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Tried to access onActivityResult while context is not ready"));
        }
    }

    @Override // com.facebook.react.ReactHost
    public void onWindowFocusChange(boolean z) {
        log("onWindowFocusChange(hasFocus = \"" + z + "\")");
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            currentReactContext.onWindowFocusChange(z);
        } else {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Tried to access onWindowFocusChange while context is not ready"));
        }
    }

    @Override // com.facebook.react.ReactHost
    public void onNewIntent(Intent intent) {
        DeviceEventManagerModule deviceEventManagerModule;
        log("onNewIntent()");
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null) {
            String action = intent.getAction();
            Uri data = intent.getData();
            if (data != null && (("android.intent.action.VIEW".equals(action) || "android.nfc.action.NDEF_DISCOVERED".equals(action)) && (deviceEventManagerModule = (DeviceEventManagerModule) currentReactContext.getNativeModule(DeviceEventManagerModule.class)) != null)) {
                deviceEventManagerModule.emitNewIntentReceived(data);
            }
            currentReactContext.onNewIntent(getCurrentActivity(), intent);
            return;
        }
        ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Tried to access onNewIntent while context is not ready"));
    }

    @Override // com.facebook.react.ReactHost
    public void onConfigurationChanged(Context context) {
        AppearanceModule appearanceModule;
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext == null || (appearanceModule = (AppearanceModule) currentReactContext.getNativeModule(AppearanceModule.class)) == null) {
            return;
        }
        appearanceModule.onConfigurationChanged(context);
    }

    JavaScriptContextHolder getJavaScriptContextHolder() {
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        if (result != null) {
            return result.getJavaScriptContextHolder();
        }
        return null;
    }

    DefaultHardwareBackBtnHandler getDefaultBackButtonHandler() {
        return new DefaultHardwareBackBtnHandler() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda27
            @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
            public final void invokeDefaultOnBackPressed() {
                ReactHostImpl.this.lambda$getDefaultBackButtonHandler$12();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getDefaultBackButtonHandler$12() {
        UiThreadUtil.assertOnUiThread();
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultHardwareBackBtnHandler;
        if (defaultHardwareBackBtnHandler != null) {
            defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
        }
    }

    Task<Boolean> loadBundle(final JSBundleLoader jSBundleLoader) {
        log("loadBundle()", "Schedule");
        return callWithExistingReactInstance("loadBundle()", new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda0
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                ReactHostImpl.this.lambda$loadBundle$13(jSBundleLoader, (ReactInstance) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBundle$13(JSBundleLoader jSBundleLoader, ReactInstance reactInstance) {
        log("loadBundle()", "Execute");
        reactInstance.loadJSBundle(jSBundleLoader);
    }

    Task<Boolean> registerSegment(final int i, final String str, final Callback callback) {
        final String str2 = "registerSegment(segmentId = \"" + i + "\", path = \"" + str + "\")";
        log(str2, "Schedule");
        return callWithExistingReactInstance(str2, new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda11
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                ReactHostImpl.this.lambda$registerSegment$14(str2, i, str, callback, (ReactInstance) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$registerSegment$14(String str, int i, String str2, Callback callback, ReactInstance reactInstance) {
        log(str, "Execute");
        reactInstance.registerSegment(i, str2);
        ((Callback) Assertions.assertNotNull(callback)).invoke(new Object[0]);
    }

    void handleHostException(Exception exc) {
        String str = "handleHostException(message = \"" + exc.getMessage() + "\")";
        log(str);
        destroy(str, exc);
        this.mReactHostDelegate.handleInstanceException(exc);
    }

    Task<Boolean> callFunctionOnModule(final String str, final String str2, final NativeArray nativeArray) {
        return callWithExistingReactInstance("callFunctionOnModule(\"" + str + "\", \"" + str2 + "\")", new VeniceThenable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda29
            @Override // com.facebook.react.runtime.ReactHostImpl.VeniceThenable
            public final void then(Object obj) {
                ((ReactInstance) obj).callFunctionOnModule(str, str2, nativeArray);
            }
        });
    }

    void attachSurface(ReactSurfaceImpl reactSurfaceImpl) {
        log("attachSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")");
        synchronized (this.mAttachedSurfaces) {
            this.mAttachedSurfaces.add(reactSurfaceImpl);
        }
    }

    void detachSurface(ReactSurfaceImpl reactSurfaceImpl) {
        log("detachSurface(surfaceId = " + reactSurfaceImpl.getSurfaceID() + ")");
        synchronized (this.mAttachedSurfaces) {
            this.mAttachedSurfaces.remove(reactSurfaceImpl);
        }
    }

    boolean isSurfaceAttached(ReactSurfaceImpl reactSurfaceImpl) {
        boolean contains;
        synchronized (this.mAttachedSurfaces) {
            contains = this.mAttachedSurfaces.contains(reactSurfaceImpl);
        }
        return contains;
    }

    boolean isSurfaceWithModuleNameAttached(String str) {
        synchronized (this.mAttachedSurfaces) {
            Iterator<ReactSurfaceImpl> it = this.mAttachedSurfaces.iterator();
            while (it.hasNext()) {
                if (it.next().getModuleName().equals(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.facebook.react.ReactHost
    public void addBeforeDestroyListener(Function0<Unit> function0) {
        synchronized (this.mBeforeDestroyListeners) {
            this.mBeforeDestroyListeners.add(function0);
        }
    }

    @Override // com.facebook.react.ReactHost
    public void removeBeforeDestroyListener(Function0<Unit> function0) {
        synchronized (this.mBeforeDestroyListeners) {
            this.mBeforeDestroyListeners.remove(function0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> getOrCreateStartTask() {
        if (this.mStartTask == null) {
            log("getOrCreateStartTask()", "Schedule");
            this.mStartTask = waitThenCallGetOrCreateReactInstanceTask().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda22
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateStartTask$17;
                    lambda$getOrCreateStartTask$17 = ReactHostImpl.this.lambda$getOrCreateStartTask$17(task);
                    return lambda$getOrCreateStartTask$17;
                }
            }, this.mBGExecutor);
        }
        return this.mStartTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateStartTask$17(final Task task) throws Exception {
        if (task.isFaulted()) {
            this.mReactHostDelegate.handleInstanceException(task.getError());
            return getOrCreateDestroyTask("getOrCreateStartTask() failure: " + task.getError().getMessage(), task.getError()).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda36
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task2) {
                    Task forError;
                    forError = Task.forError(Task.this.getError());
                    return forError;
                }
            }).makeVoid();
        }
        return task.makeVoid();
    }

    private void moveToHostDestroy(ReactContext reactContext) {
        this.mReactLifecycleStateManager.moveToOnHostDestroy(reactContext);
        setCurrentActivity(null);
    }

    private void raiseSoftException(String str, String str2) {
        raiseSoftException(str, str2, null);
    }

    private void raiseSoftException(String str, String str2, Throwable th) {
        log(str, str2);
        if (ReactFeatureFlags.enableBridgelessArchitectureSoftExceptions) {
            if (th != null) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(str + ": " + str2, th));
                return;
            }
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(str + ": " + str2));
        }
    }

    private Task<Boolean> callWithExistingReactInstance(String str, final VeniceThenable<ReactInstance> veniceThenable) {
        final String str2 = "callWithExistingReactInstance(" + str + ")";
        return this.mReactInstanceTaskRef.get().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda33
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                Boolean lambda$callWithExistingReactInstance$18;
                lambda$callWithExistingReactInstance$18 = ReactHostImpl.this.lambda$callWithExistingReactInstance$18(str2, veniceThenable, task);
                return lambda$callWithExistingReactInstance$18;
            }
        }, this.mBGExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$callWithExistingReactInstance$18(String str, VeniceThenable veniceThenable, Task task) throws Exception {
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        if (reactInstance == null) {
            raiseSoftException(str, "Execute: ReactInstance null. Dropping work.");
            return Boolean.FALSE;
        }
        veniceThenable.then(reactInstance);
        return Boolean.TRUE;
    }

    private Task<Void> callAfterGetOrCreateReactInstance(String str, final VeniceThenable<ReactInstance> veniceThenable) {
        final String str2 = "callAfterGetOrCreateReactInstance(" + str + ")";
        return getOrCreateReactInstance().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda31
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                Void lambda$callAfterGetOrCreateReactInstance$19;
                lambda$callAfterGetOrCreateReactInstance$19 = ReactHostImpl.this.lambda$callAfterGetOrCreateReactInstance$19(str2, veniceThenable, task);
                return lambda$callAfterGetOrCreateReactInstance$19;
            }
        }, this.mBGExecutor).continueWith((Continuation<TContinuationResult, TContinuationResult>) new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda32
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                Void lambda$callAfterGetOrCreateReactInstance$20;
                lambda$callAfterGetOrCreateReactInstance$20 = ReactHostImpl.this.lambda$callAfterGetOrCreateReactInstance$20(task);
                return lambda$callAfterGetOrCreateReactInstance$20;
            }
        }, this.mBGExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$callAfterGetOrCreateReactInstance$19(String str, VeniceThenable veniceThenable, Task task) throws Exception {
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        if (reactInstance == null) {
            raiseSoftException(str, "Execute: ReactInstance is null");
            return null;
        }
        veniceThenable.then(reactInstance);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$callAfterGetOrCreateReactInstance$20(Task task) throws Exception {
        if (!task.isFaulted()) {
            return null;
        }
        handleHostException(task.getError());
        return null;
    }

    private BridgelessReactContext getOrCreateReactContext() {
        return this.mBridgelessReactContextRef.getOrCreate(new BridgelessAtomicRef.Provider() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda16
            @Override // com.facebook.react.runtime.BridgelessAtomicRef.Provider
            public final Object get() {
                BridgelessReactContext lambda$getOrCreateReactContext$21;
                lambda$getOrCreateReactContext$21 = ReactHostImpl.this.lambda$getOrCreateReactContext$21();
                return lambda$getOrCreateReactContext$21;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ BridgelessReactContext lambda$getOrCreateReactContext$21() {
        log("getOrCreateReactContext()", "Creating BridgelessReactContext");
        return new BridgelessReactContext(this.mContext, this);
    }

    private Task<ReactInstance> getOrCreateReactInstance() {
        return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda38
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Task waitThenCallGetOrCreateReactInstanceTask;
                waitThenCallGetOrCreateReactInstanceTask = ReactHostImpl.this.waitThenCallGetOrCreateReactInstanceTask();
                return waitThenCallGetOrCreateReactInstanceTask;
            }
        }, this.mBGExecutor).continueWithTask(new ReactHostImpl$$ExternalSyntheticLambda20());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<ReactInstance> waitThenCallGetOrCreateReactInstanceTask() {
        return waitThenCallGetOrCreateReactInstanceTaskWithRetries(0, 4);
    }

    private Task<ReactInstance> waitThenCallGetOrCreateReactInstanceTaskWithRetries(final int i, final int i2) {
        if (this.mReloadTask != null) {
            log("waitThenCallGetOrCreateReactInstanceTaskWithRetries", "React Native is reloading. Return reload task.");
            return this.mReloadTask;
        }
        if (this.mDestroyTask != null) {
            if (i < i2) {
                log("waitThenCallGetOrCreateReactInstanceTaskWithRetries", "React Native is tearing down.Wait for teardown to finish, before trying again (try count = " + i + ").");
                return this.mDestroyTask.onSuccessTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda24
                    @Override // com.facebook.react.runtime.internal.bolts.Continuation
                    public final Object then(Task task) {
                        Task lambda$waitThenCallGetOrCreateReactInstanceTaskWithRetries$22;
                        lambda$waitThenCallGetOrCreateReactInstanceTaskWithRetries$22 = ReactHostImpl.this.lambda$waitThenCallGetOrCreateReactInstanceTaskWithRetries$22(i, i2, task);
                        return lambda$waitThenCallGetOrCreateReactInstanceTaskWithRetries$22;
                    }
                }, this.mBGExecutor);
            }
            raiseSoftException("waitThenCallGetOrCreateReactInstanceTaskWithRetries", "React Native is tearing down. Not wait for teardown to finish: reached max retries.");
        }
        return getOrCreateReactInstanceTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$waitThenCallGetOrCreateReactInstanceTaskWithRetries$22(int i, int i2, Task task) throws Exception {
        return waitThenCallGetOrCreateReactInstanceTaskWithRetries(i + 1, i2);
    }

    private Task<ReactInstance> getOrCreateReactInstanceTask() {
        log("getOrCreateReactInstanceTask()");
        return this.mReactInstanceTaskRef.getOrCreate(new BridgelessAtomicRef.Provider() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda37
            @Override // com.facebook.react.runtime.BridgelessAtomicRef.Provider
            public final Object get() {
                Task lambda$getOrCreateReactInstanceTask$26;
                lambda$getOrCreateReactInstanceTask$26 = ReactHostImpl.this.lambda$getOrCreateReactInstanceTask$26();
                return lambda$getOrCreateReactInstanceTask$26;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReactInstanceTask$26() {
        log("getOrCreateReactInstanceTask()", "Start");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGELESS_LOADING_START, 1);
        return getJsBundleLoader().onSuccess(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda39
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                ReactHostImpl.C1Result lambda$getOrCreateReactInstanceTask$24;
                lambda$getOrCreateReactInstanceTask$24 = ReactHostImpl.this.lambda$getOrCreateReactInstanceTask$24(task);
                return lambda$getOrCreateReactInstanceTask$24;
            }
        }, this.mBGExecutor).onSuccess((Continuation<TContinuationResult, TContinuationResult>) new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda40
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                ReactInstance lambda$getOrCreateReactInstanceTask$25;
                lambda$getOrCreateReactInstanceTask$25 = ReactHostImpl.this.lambda$getOrCreateReactInstanceTask$25(task);
                return lambda$getOrCreateReactInstanceTask$25;
            }
        }, this.mUIExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ C1Result lambda$getOrCreateReactInstanceTask$24(Task task) throws Exception {
        JSBundleLoader jSBundleLoader = (JSBundleLoader) task.getResult();
        BridgelessReactContext orCreateReactContext = getOrCreateReactContext();
        DevSupportManager devSupportManager = getDevSupportManager();
        orCreateReactContext.setJSExceptionHandler(devSupportManager);
        log("getOrCreateReactInstanceTask()", "Creating ReactInstance");
        ReactInstance reactInstance = new ReactInstance(orCreateReactContext, this.mReactHostDelegate, this.mComponentFactory, devSupportManager, this.mQueueThreadExceptionHandler, this.mReactJsExceptionHandler, this.mUseDevSupport);
        if (ReactFeatureFlags.unstable_bridgelessArchitectureMemoryPressureHackyBoltsFix) {
            this.mMemoryPressureListener = createMemoryPressureListener(reactInstance);
        }
        this.mMemoryPressureRouter.addMemoryPressureListener(this.mMemoryPressureListener);
        log("getOrCreateReactInstanceTask()", "Loading JS Bundle");
        reactInstance.loadJSBundle(jSBundleLoader);
        log("getOrCreateReactInstanceTask()", "Calling DevSupportManagerBase.onNewReactContextCreated(reactContext)");
        devSupportManager.onNewReactContextCreated(orCreateReactContext);
        orCreateReactContext.runOnJSQueueThread(new Runnable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                ReactMarker.logMarker(ReactMarkerConstants.REACT_BRIDGELESS_LOADING_END, 1);
            }
        });
        return new C1Result(reactInstance, orCreateReactContext);
    }

    /* renamed from: com.facebook.react.runtime.ReactHostImpl$1Result, reason: invalid class name */
    class C1Result {
        final ReactContext mContext;
        final ReactInstance mInstance;
        final boolean mIsReloading;
        final /* synthetic */ ReactInstance val$instance;
        final /* synthetic */ BridgelessReactContext val$reactContext;

        C1Result(ReactInstance reactInstance, BridgelessReactContext bridgelessReactContext) {
            this.val$instance = reactInstance;
            this.val$reactContext = bridgelessReactContext;
            this.mInstance = reactInstance;
            this.mContext = bridgelessReactContext;
            this.mIsReloading = ReactHostImpl.this.mReloadTask != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ReactInstance lambda$getOrCreateReactInstanceTask$25(Task task) throws Exception {
        ReactInstance reactInstance = ((C1Result) task.getResult()).mInstance;
        ReactContext reactContext = ((C1Result) task.getResult()).mContext;
        boolean z = ((C1Result) task.getResult()).mIsReloading;
        boolean z2 = this.mReactLifecycleStateManager.getLifecycleState() == LifecycleState.RESUMED;
        if (z && !z2) {
            this.mReactLifecycleStateManager.moveToOnHostResume(reactContext, getCurrentActivity());
        } else {
            this.mReactLifecycleStateManager.resumeReactContextIfHostResumed(reactContext, getCurrentActivity());
        }
        ReactInstanceEventListener[] reactInstanceEventListenerArr = (ReactInstanceEventListener[]) this.mReactInstanceEventListeners.toArray(new ReactInstanceEventListener[this.mReactInstanceEventListeners.size()]);
        log("getOrCreateReactInstanceTask()", "Executing ReactInstanceEventListeners");
        for (ReactInstanceEventListener reactInstanceEventListener : reactInstanceEventListenerArr) {
            if (reactInstanceEventListener != null) {
                reactInstanceEventListener.onReactContextInitialized(reactContext);
            }
        }
        return reactInstance;
    }

    private Task<JSBundleLoader> getJsBundleLoader() {
        log("getJSBundleLoader()");
        return Task.call(new Callable() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda28
            @Override // java.util.concurrent.Callable
            public final Object call() {
                JSBundleLoader lambda$getJsBundleLoader$28;
                lambda$getJsBundleLoader$28 = ReactHostImpl.this.lambda$getJsBundleLoader$28();
                return lambda$getJsBundleLoader$28;
            }
        });
    }

    private /* synthetic */ Task lambda$getJsBundleLoader$27(Task task) throws Exception {
        if (((Boolean) task.getResult()).booleanValue()) {
            return loadJSBundleFromMetro();
        }
        return Task.forResult(this.mReactHostDelegate.getJsBundleLoader());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ JSBundleLoader lambda$getJsBundleLoader$28() throws Exception {
        return this.mReactHostDelegate.getJsBundleLoader();
    }

    private Task<Boolean> isMetroRunning() {
        log("isMetroRunning()");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        getDevSupportManager().isPackagerRunning(new PackagerStatusCallback() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda21
            @Override // com.facebook.react.devsupport.interfaces.PackagerStatusCallback
            public final void onPackagerStatusFetched(boolean z) {
                ReactHostImpl.this.lambda$isMetroRunning$29(taskCompletionSource, z);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$isMetroRunning$29(TaskCompletionSource taskCompletionSource, boolean z) {
        log("isMetroRunning()", "Async result = " + z);
        taskCompletionSource.setResult(Boolean.valueOf(z));
    }

    private Task<JSBundleLoader> loadJSBundleFromMetro() {
        log("loadJSBundleFromMetro()");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final DevSupportManagerBase devSupportManagerBase = (DevSupportManagerBase) getDevSupportManager();
        final String devServerBundleURL = devSupportManagerBase.getDevServerHelper().getDevServerBundleURL((String) Assertions.assertNotNull(devSupportManagerBase.getJSAppBundleName()));
        devSupportManagerBase.reloadJSFromServer(devServerBundleURL, new BundleLoadCallback() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda25
            @Override // com.facebook.react.devsupport.interfaces.BundleLoadCallback
            public final void onSuccess() {
                ReactHostImpl.this.lambda$loadJSBundleFromMetro$30(devServerBundleURL, devSupportManagerBase, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadJSBundleFromMetro$30(String str, DevSupportManagerBase devSupportManagerBase, TaskCompletionSource taskCompletionSource) {
        log("loadJSBundleFromMetro()", "Creating BundleLoader");
        taskCompletionSource.setResult(JSBundleLoader.createCachedBundleFromNetworkLoader(str, devSupportManagerBase.getDownloadedJSBundleFile()));
    }

    private void log(String str, String str2) {
        this.mBridgelessReactStateTracker.enterState("ReactHost{" + this.mId + "}." + str + ": " + str2);
    }

    private void log(String str) {
        this.mBridgelessReactStateTracker.enterState("ReactHost{" + this.mId + "}." + str);
    }

    private void stopAttachedSurfaces(String str, ReactInstance reactInstance) {
        log(str, "Stopping all React Native surfaces");
        synchronized (this.mAttachedSurfaces) {
            for (ReactSurfaceImpl reactSurfaceImpl : this.mAttachedSurfaces) {
                reactInstance.stopSurface(reactSurfaceImpl);
                reactSurfaceImpl.clear();
            }
        }
    }

    private void startAttachedSurfaces(String str, ReactInstance reactInstance) {
        log(str, "Restarting previously running React Native Surfaces");
        synchronized (this.mAttachedSurfaces) {
            Iterator<ReactSurfaceImpl> it = this.mAttachedSurfaces.iterator();
            while (it.hasNext()) {
                reactInstance.startSurface(it.next());
            }
        }
    }

    private ReactInstanceTaskUnwrapper createReactInstanceUnwraper(final String str, final String str2, final String str3) {
        return new ReactInstanceTaskUnwrapper() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda42
            @Override // com.facebook.react.runtime.ReactHostImpl.ReactInstanceTaskUnwrapper
            public final ReactInstance unwrap(Task task, String str4) {
                ReactInstance lambda$createReactInstanceUnwraper$31;
                lambda$createReactInstanceUnwraper$31 = ReactHostImpl.this.lambda$createReactInstanceUnwraper$31(str, str3, str2, task, str4);
                return lambda$createReactInstanceUnwraper$31;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ReactInstance lambda$createReactInstanceUnwraper$31(String str, String str2, String str3, Task task, String str4) {
        ReactInstance reactInstance = (ReactInstance) task.getResult();
        ReactInstance result = this.mReactInstanceTaskRef.get().getResult();
        String str5 = "Stage: " + str4;
        String str6 = str + " reason: " + str2;
        if (task.isFaulted()) {
            raiseSoftException(str3, str + ": ReactInstance task faulted. " + str5 + ". " + ("Fault reason: " + task.getError().getMessage()) + ". " + str6);
            return result;
        }
        if (task.isCancelled()) {
            raiseSoftException(str3, str + ": ReactInstance task cancelled. " + str5 + ". " + str6);
            return result;
        }
        if (reactInstance == null) {
            raiseSoftException(str3, str + ": ReactInstance task returned null. " + str5 + ". " + str6);
            return result;
        }
        if (result != null && reactInstance != result) {
            raiseSoftException(str3, str + ": Detected two different ReactInstances. Returning old. " + str5 + ". " + str6);
        }
        return reactInstance;
    }

    private Task<ReactInstance> getOrCreateReloadTask(final String str) {
        log("getOrCreateReloadTask()");
        raiseSoftException("getOrCreateReloadTask()", str);
        final ReactInstanceTaskUnwrapper createReactInstanceUnwraper = createReactInstanceUnwraper("Reload", "getOrCreateReloadTask()", str);
        if (this.mReloadTask == null) {
            this.mReloadTask = this.mReactInstanceTaskRef.get().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda43
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateReloadTask$32;
                    lambda$getOrCreateReloadTask$32 = ReactHostImpl.this.lambda$getOrCreateReloadTask$32(createReactInstanceUnwraper, str, task);
                    return lambda$getOrCreateReloadTask$32;
                }
            }, this.mUIExecutor).continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda44
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateReloadTask$33;
                    lambda$getOrCreateReloadTask$33 = ReactHostImpl.this.lambda$getOrCreateReloadTask$33(createReactInstanceUnwraper, task);
                    return lambda$getOrCreateReloadTask$33;
                }
            }, this.mBGExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda45
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateReloadTask$34;
                    lambda$getOrCreateReloadTask$34 = ReactHostImpl.this.lambda$getOrCreateReloadTask$34(createReactInstanceUnwraper, task);
                    return lambda$getOrCreateReloadTask$34;
                }
            }, this.mUIExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda46
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateReloadTask$35;
                    lambda$getOrCreateReloadTask$35 = ReactHostImpl.this.lambda$getOrCreateReloadTask$35(createReactInstanceUnwraper, task);
                    return lambda$getOrCreateReloadTask$35;
                }
            }, this.mUIExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda47
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateReloadTask$36;
                    lambda$getOrCreateReloadTask$36 = ReactHostImpl.this.lambda$getOrCreateReloadTask$36(createReactInstanceUnwraper, task);
                    return lambda$getOrCreateReloadTask$36;
                }
            }, this.mBGExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda1
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateReloadTask$37;
                    lambda$getOrCreateReloadTask$37 = ReactHostImpl.this.lambda$getOrCreateReloadTask$37(createReactInstanceUnwraper, task);
                    return lambda$getOrCreateReloadTask$37;
                }
            }, this.mBGExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda2
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateReloadTask$38;
                    lambda$getOrCreateReloadTask$38 = ReactHostImpl.this.lambda$getOrCreateReloadTask$38(str, task);
                    return lambda$getOrCreateReloadTask$38;
                }
            }, this.mBGExecutor);
        }
        return this.mReloadTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$32(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str, Task task) throws Exception {
        log("getOrCreateReloadTask()", "Starting React Native reload");
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "1: Starting reload");
        BridgelessReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable == null) {
            raiseSoftException("getOrCreateReloadTask()", "ReactContext is null. Reload reason: " + str);
        }
        if (nullable != null && this.mReactLifecycleStateManager.getLifecycleState() == LifecycleState.RESUMED) {
            log("getOrCreateReloadTask()", "Calling ReactContext.onHostPause()");
            nullable.onHostPause();
        }
        return Task.forResult(unwrap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$33(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "2: Surface shutdown");
        if (unwrap == null) {
            raiseSoftException("getOrCreateReloadTask()", "Skipping surface shutdown: ReactInstance null");
            return task;
        }
        stopAttachedSurfaces("getOrCreateReloadTask()", unwrap);
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$34(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        HashSet hashSet;
        reactInstanceTaskUnwrapper.unwrap(task, "3: Executing Before Destroy Listeners");
        synchronized (this.mBeforeDestroyListeners) {
            hashSet = new HashSet(this.mBeforeDestroyListeners);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$35(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        reactInstanceTaskUnwrapper.unwrap(task, "4: Destroying ReactContext");
        log("getOrCreateReloadTask()", "Removing memory pressure listener");
        this.mMemoryPressureRouter.removeMemoryPressureListener(this.mMemoryPressureListener);
        BridgelessReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable != null) {
            log("getOrCreateReloadTask()", "Destroying ReactContext");
            nullable.destroy();
        }
        if (this.mUseDevSupport && nullable != null) {
            log("getOrCreateReloadTask()", "Calling DevSupportManager.onReactInstanceDestroyed(reactContext)");
            this.mDevSupportManager.onReactInstanceDestroyed(nullable);
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$36(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "5: Destroying ReactInstance");
        if (unwrap == null) {
            raiseSoftException("getOrCreateReloadTask()", "Skipping ReactInstance.destroy(): ReactInstance null");
        } else {
            log("getOrCreateReloadTask()", "Destroying ReactInstance");
            unwrap.destroy();
        }
        log("getOrCreateReloadTask()", "Resetting ReactContext ref");
        this.mBridgelessReactContextRef.reset();
        log("getOrCreateReloadTask()", "Resetting ReactInstance task ref");
        this.mReactInstanceTaskRef.reset();
        log("getOrCreateReloadTask()", "Resetting preload task ref");
        this.mStartTask = null;
        return getOrCreateReactInstanceTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$37(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "7: Restarting surfaces");
        if (unwrap == null) {
            raiseSoftException("getOrCreateReloadTask()", "Skipping surface restart: ReactInstance null");
            return task;
        }
        startAttachedSurfaces("getOrCreateReloadTask()", unwrap);
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateReloadTask$38(String str, Task task) throws Exception {
        if (task.isFaulted()) {
            raiseSoftException("getOrCreateReloadTask()", "Error during reload. ReactInstance task faulted. Fault reason: " + task.getError().getMessage() + ". Reload reason: " + str, task.getError());
        }
        if (task.isCancelled()) {
            raiseSoftException("getOrCreateReloadTask()", "Error during reload. ReactInstance task cancelled. Reload reason: " + str);
        }
        log("getOrCreateReloadTask()", "Resetting reload task ref");
        this.mReloadTask = null;
        return task;
    }

    private Task<Void> getOrCreateDestroyTask(final String str, Exception exc) {
        log("getOrCreateDestroyTask()");
        raiseSoftException("getOrCreateDestroyTask()", str, exc);
        final ReactInstanceTaskUnwrapper createReactInstanceUnwraper = createReactInstanceUnwraper("Destroy", "getOrCreateDestroyTask()", str);
        if (this.mDestroyTask == null) {
            this.mDestroyTask = this.mReactInstanceTaskRef.get().continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda5
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateDestroyTask$39;
                    lambda$getOrCreateDestroyTask$39 = ReactHostImpl.this.lambda$getOrCreateDestroyTask$39(createReactInstanceUnwraper, str, task);
                    return lambda$getOrCreateDestroyTask$39;
                }
            }, this.mUIExecutor).continueWithTask((Continuation<TContinuationResult, Task<TContinuationResult>>) new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda6
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateDestroyTask$40;
                    lambda$getOrCreateDestroyTask$40 = ReactHostImpl.this.lambda$getOrCreateDestroyTask$40(createReactInstanceUnwraper, task);
                    return lambda$getOrCreateDestroyTask$40;
                }
            }, this.mBGExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda7
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateDestroyTask$41;
                    lambda$getOrCreateDestroyTask$41 = ReactHostImpl.this.lambda$getOrCreateDestroyTask$41(createReactInstanceUnwraper, task);
                    return lambda$getOrCreateDestroyTask$41;
                }
            }, this.mUIExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda8
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateDestroyTask$42;
                    lambda$getOrCreateDestroyTask$42 = ReactHostImpl.this.lambda$getOrCreateDestroyTask$42(createReactInstanceUnwraper, str, task);
                    return lambda$getOrCreateDestroyTask$42;
                }
            }, this.mUIExecutor).continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda9
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Task lambda$getOrCreateDestroyTask$43;
                    lambda$getOrCreateDestroyTask$43 = ReactHostImpl.this.lambda$getOrCreateDestroyTask$43(createReactInstanceUnwraper, task);
                    return lambda$getOrCreateDestroyTask$43;
                }
            }, this.mBGExecutor).continueWith(new Continuation() { // from class: com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda10
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    Void lambda$getOrCreateDestroyTask$44;
                    lambda$getOrCreateDestroyTask$44 = ReactHostImpl.this.lambda$getOrCreateDestroyTask$44(str, task);
                    return lambda$getOrCreateDestroyTask$44;
                }
            });
        }
        return this.mDestroyTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateDestroyTask$39(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str, Task task) throws Exception {
        log("getOrCreateDestroyTask()", "Starting React Native destruction");
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "1: Starting destroy");
        if (this.mUseDevSupport) {
            log("getOrCreateDestroyTask()", "DevSupportManager cleanup");
            this.mDevSupportManager.stopInspector();
        }
        BridgelessReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable == null) {
            raiseSoftException("getOrCreateDestroyTask()", "ReactContext is null. Destroy reason: " + str);
        }
        log("getOrCreateDestroyTask()", "Move ReactHost to onHostDestroy()");
        this.mReactLifecycleStateManager.moveToOnHostDestroy(nullable);
        return Task.forResult(unwrap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateDestroyTask$40(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "2: Stopping surfaces");
        if (unwrap == null) {
            raiseSoftException("getOrCreateDestroyTask()", "Skipping surface shutdown: ReactInstance null");
            return task;
        }
        stopAttachedSurfaces("getOrCreateDestroyTask()", unwrap);
        synchronized (this.mAttachedSurfaces) {
            this.mAttachedSurfaces.clear();
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateDestroyTask$41(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        HashSet hashSet;
        reactInstanceTaskUnwrapper.unwrap(task, "3: Executing Before Destroy Listeners");
        synchronized (this.mBeforeDestroyListeners) {
            hashSet = new HashSet(this.mBeforeDestroyListeners);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateDestroyTask$42(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, String str, Task task) throws Exception {
        reactInstanceTaskUnwrapper.unwrap(task, "4: Destroying ReactContext");
        BridgelessReactContext nullable = this.mBridgelessReactContextRef.getNullable();
        if (nullable == null) {
            raiseSoftException("getOrCreateDestroyTask()", "ReactContext is null. Destroy reason: " + str);
        }
        log("getOrCreateDestroyTask()", "Destroying MemoryPressureRouter");
        this.mMemoryPressureRouter.destroy(this.mContext);
        if (nullable != null) {
            log("getOrCreateDestroyTask()", "Destroying ReactContext");
            nullable.destroy();
        }
        setCurrentActivity(null);
        ResourceDrawableIdHelper.getInstance().clear();
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task lambda$getOrCreateDestroyTask$43(ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper, Task task) throws Exception {
        ReactInstance unwrap = reactInstanceTaskUnwrapper.unwrap(task, "5: Destroying ReactInstance");
        if (unwrap == null) {
            raiseSoftException("getOrCreateDestroyTask()", "Skipping ReactInstance.destroy(): ReactInstance null");
        } else {
            log("getOrCreateDestroyTask()", "Destroying ReactInstance");
            unwrap.destroy();
        }
        log("getOrCreateDestroyTask()", "Resetting ReactContext ref ");
        this.mBridgelessReactContextRef.reset();
        log("getOrCreateDestroyTask()", "Resetting ReactInstance task ref");
        this.mReactInstanceTaskRef.reset();
        log("getOrCreateDestroyTask()", "Resetting Preload task ref");
        this.mStartTask = null;
        log("getOrCreateDestroyTask()", "Resetting destroy task ref");
        this.mDestroyTask = null;
        return task;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void lambda$getOrCreateDestroyTask$44(String str, Task task) throws Exception {
        if (task.isFaulted()) {
            raiseSoftException("getOrCreateDestroyTask()", "React destruction failed. ReactInstance task faulted. Fault reason: " + task.getError().getMessage() + ". Destroy reason: " + str, task.getError());
        }
        if (!task.isCancelled()) {
            return null;
        }
        raiseSoftException("getOrCreateDestroyTask()", "React destruction failed. ReactInstance task cancelled. Destroy reason: " + str);
        return null;
    }
}

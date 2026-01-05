package com.facebook.react.runtime;

import android.content.Context;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.JavaScriptModuleRegistry;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.common.annotations.FrameworkAPI;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcherProvider;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
class BridgelessReactContext extends ReactApplicationContext implements EventDispatcherProvider {
    private final String TAG;
    private final ReactHostImpl mReactHost;
    private final AtomicReference<String> mSourceURL;

    @Override // com.facebook.react.bridge.ReactContext
    public boolean isBridgeless() {
        return true;
    }

    BridgelessReactContext(Context context, ReactHostImpl reactHostImpl) {
        super(context);
        this.mSourceURL = new AtomicReference<>();
        this.TAG = getClass().getSimpleName();
        this.mReactHost = reactHostImpl;
        if (ReactFeatureFlags.unstable_useFabricInterop) {
            initializeInteropModules();
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcherProvider
    public EventDispatcher getEventDispatcher() {
        return this.mReactHost.getEventDispatcher();
    }

    public void setSourceURL(String str) {
        this.mSourceURL.set(str);
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Nullable
    public String getSourceURL() {
        return this.mSourceURL.get();
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Nullable
    public UIManager getFabricUIManager() {
        return this.mReactHost.getUIManager();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public CatalystInstance getCatalystInstance() {
        Log.w(this.TAG, "[WARNING] Bridgeless doesn't support CatalystInstance. Accessing an API that's not part of the new architecture is not encouraged usage.");
        return new BridgelessCatalystInstance(this.mReactHost);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public boolean hasActiveReactInstance() {
        return this.mReactHost.isInstanceInitialized();
    }

    DevSupportManager getDevSupportManager() {
        return this.mReactHost.getDevSupportManager();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void registerSegment(int i, String str, Callback callback) {
        this.mReactHost.registerSegment(i, str, callback);
    }

    private static class BridgelessJSModuleInvocationHandler implements InvocationHandler {
        private final Class<? extends JavaScriptModule> mJSModuleInterface;
        private final ReactHostImpl mReactHost;

        public BridgelessJSModuleInvocationHandler(ReactHostImpl reactHostImpl, Class<? extends JavaScriptModule> cls) {
            this.mReactHost = reactHostImpl;
            this.mJSModuleInterface = cls;
        }

        @Override // java.lang.reflect.InvocationHandler
        @Nullable
        public Object invoke(Object obj, Method method, @Nullable Object[] objArr) {
            this.mReactHost.callFunctionOnModule(JavaScriptModuleRegistry.getJSModuleName(this.mJSModuleInterface), method.getName(), objArr != null ? Arguments.fromJavaArgs(objArr) : new WritableNativeArray());
            return null;
        }
    }

    @Override // com.facebook.react.bridge.ReactContext
    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        if (this.mInteropModuleRegistry != null && this.mInteropModuleRegistry.shouldReturnInteropModule(cls)) {
            return (T) this.mInteropModuleRegistry.getInteropModule(cls);
        }
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new BridgelessJSModuleInvocationHandler(this.mReactHost, cls));
    }

    @Override // com.facebook.react.bridge.ReactContext
    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        return this.mReactHost.hasNativeModule(cls);
    }

    @Override // com.facebook.react.bridge.ReactContext
    public Collection<NativeModule> getNativeModules() {
        return this.mReactHost.getNativeModules();
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Nullable
    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        return (T) this.mReactHost.getNativeModule(cls);
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Nullable
    @FrameworkAPI
    @UnstableReactNativeAPI
    public RuntimeExecutor getRuntimeExecutor() {
        return this.mReactHost.getRuntimeExecutor();
    }

    @Override // com.facebook.react.bridge.ReactContext
    @Nullable
    @FrameworkAPI
    @UnstableReactNativeAPI
    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.mReactHost.getJavaScriptContextHolder();
    }

    @Override // com.facebook.react.bridge.ReactContext
    public void handleException(Exception exc) {
        this.mReactHost.handleHostException(exc);
    }

    DefaultHardwareBackBtnHandler getDefaultHardwareBackBtnHandler() {
        return this.mReactHost.getDefaultBackButtonHandler();
    }
}

package expo.modules;

import android.content.Context;
import com.facebook.react.JSEngineResolutionAlgorithm;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.defaults.DefaultComponentsRegistry;
import com.facebook.react.defaults.DefaultTurboModuleManagerDelegate;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.ReactNativeConfig;
import com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler;
import com.facebook.react.runtime.BindingsInstaller;
import com.facebook.react.runtime.JSCInstance;
import com.facebook.react.runtime.JSRuntimeFactory;
import com.facebook.react.runtime.ReactHostDelegate;
import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.hermes.HermesInstance;
import com.google.firebase.messaging.Constants;
import expo.modules.core.interfaces.ReactNativeHostHandler;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ExpoReactHostFactory.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lexpo/modules/ExpoReactHostFactory;", "", "()V", "reactHost", "Lcom/facebook/react/ReactHost;", "createFromReactNativeHost", "context", "Landroid/content/Context;", "reactNativeHost", "Lcom/facebook/react/ReactNativeHost;", "ExpoReactHostDelegate", "expo_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoReactHostFactory {
    public static final ExpoReactHostFactory INSTANCE = new ExpoReactHostFactory();
    private static ReactHost reactHost;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createFromReactNativeHost$lambda$1(ReadableMapBuffer readableMapBuffer) {
    }

    private ExpoReactHostFactory() {
    }

    /* compiled from: ExpoReactHostFactory.kt */
    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B;\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\b\u0010$\u001a\u00020\nH\u0016J\u0014\u0010%\u001a\u00020&2\n\u0010'\u001a\u00060(j\u0002`)H\u0016R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lexpo/modules/ExpoReactHostFactory$ExpoReactHostDelegate;", "Lcom/facebook/react/runtime/ReactHostDelegate;", "weakContext", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "reactNativeHostWrapper", "Lexpo/modules/ReactNativeHostWrapper;", "bindingsInstaller", "Lcom/facebook/react/runtime/BindingsInstaller;", "reactNativeConfig", "Lcom/facebook/react/fabric/ReactNativeConfig;", "turboModuleManagerDelegateBuilder", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "(Ljava/lang/ref/WeakReference;Lexpo/modules/ReactNativeHostWrapper;Lcom/facebook/react/runtime/BindingsInstaller;Lcom/facebook/react/fabric/ReactNativeConfig;Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;)V", "_jsBundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "getBindingsInstaller", "()Lcom/facebook/react/runtime/BindingsInstaller;", "jsBundleLoader", "getJsBundleLoader", "()Lcom/facebook/react/bridge/JSBundleLoader;", "jsMainModulePath", "", "getJsMainModulePath", "()Ljava/lang/String;", "jsRuntimeFactory", "Lcom/facebook/react/runtime/JSRuntimeFactory;", "getJsRuntimeFactory", "()Lcom/facebook/react/runtime/JSRuntimeFactory;", "reactPackages", "", "Lcom/facebook/react/ReactPackage;", "getReactPackages", "()Ljava/util/List;", "getTurboModuleManagerDelegateBuilder", "()Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getReactNativeConfig", "handleInstanceException", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Ljava/lang/Exception;", "Lkotlin/Exception;", "expo_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @UnstableReactNativeAPI
    private static final class ExpoReactHostDelegate implements ReactHostDelegate {
        private JSBundleLoader _jsBundleLoader;
        private final BindingsInstaller bindingsInstaller;
        private final ReactNativeConfig reactNativeConfig;
        private final ReactNativeHostWrapper reactNativeHostWrapper;
        private final ReactPackageTurboModuleManagerDelegate.Builder turboModuleManagerDelegateBuilder;
        private final WeakReference<Context> weakContext;

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public BindingsInstaller getBindingsInstaller() {
            return this.bindingsInstaller;
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public ReactNativeConfig getReactNativeConfig() {
            return this.reactNativeConfig;
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public ReactPackageTurboModuleManagerDelegate.Builder getTurboModuleManagerDelegateBuilder() {
            return this.turboModuleManagerDelegateBuilder;
        }

        public ExpoReactHostDelegate(WeakReference<Context> weakContext, ReactNativeHostWrapper reactNativeHostWrapper, BindingsInstaller bindingsInstaller, ReactNativeConfig reactNativeConfig, ReactPackageTurboModuleManagerDelegate.Builder turboModuleManagerDelegateBuilder) {
            Intrinsics.checkNotNullParameter(weakContext, "weakContext");
            Intrinsics.checkNotNullParameter(reactNativeHostWrapper, "reactNativeHostWrapper");
            Intrinsics.checkNotNullParameter(reactNativeConfig, "reactNativeConfig");
            Intrinsics.checkNotNullParameter(turboModuleManagerDelegateBuilder, "turboModuleManagerDelegateBuilder");
            this.weakContext = weakContext;
            this.reactNativeHostWrapper = reactNativeHostWrapper;
            this.bindingsInstaller = bindingsInstaller;
            this.reactNativeConfig = reactNativeConfig;
            this.turboModuleManagerDelegateBuilder = turboModuleManagerDelegateBuilder;
        }

        public /* synthetic */ ExpoReactHostDelegate(WeakReference weakReference, ReactNativeHostWrapper reactNativeHostWrapper, BindingsInstaller bindingsInstaller, ReactNativeConfig reactNativeConfig, DefaultTurboModuleManagerDelegate.Builder builder, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(weakReference, reactNativeHostWrapper, (i & 4) != 0 ? null : bindingsInstaller, (i & 8) != 0 ? ReactNativeConfig.DEFAULT_CONFIG : reactNativeConfig, (i & 16) != 0 ? new DefaultTurboModuleManagerDelegate.Builder() : builder);
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public JSBundleLoader getJsBundleLoader() {
            JSBundleLoader jSBundleLoader = this._jsBundleLoader;
            if (jSBundleLoader != null) {
                return jSBundleLoader;
            }
            Context context = this.weakContext.get();
            if (context == null) {
                throw new IllegalStateException("Unable to get concrete Context");
            }
            String jSBundleFile = this.reactNativeHostWrapper.getJSBundleFile();
            if (jSBundleFile != null) {
                if (StringsKt.startsWith$default(jSBundleFile, "assets://", false, 2, (Object) null)) {
                    JSBundleLoader createAssetLoader = JSBundleLoader.createAssetLoader(context, jSBundleFile, true);
                    Intrinsics.checkNotNullExpressionValue(createAssetLoader, "createAssetLoader(...)");
                    return createAssetLoader;
                }
                JSBundleLoader createFileLoader = JSBundleLoader.createFileLoader(jSBundleFile);
                Intrinsics.checkNotNullExpressionValue(createFileLoader, "createFileLoader(...)");
                return createFileLoader;
            }
            JSBundleLoader createAssetLoader2 = JSBundleLoader.createAssetLoader(context, "assets://" + this.reactNativeHostWrapper.getBundleAssetName(), true);
            Intrinsics.checkNotNullExpressionValue(createAssetLoader2, "createAssetLoader(...)");
            return createAssetLoader2;
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public String getJsMainModulePath() {
            return this.reactNativeHostWrapper.getJSMainModuleName();
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public JSRuntimeFactory getJsRuntimeFactory() {
            if (this.reactNativeHostWrapper.getJSEngineResolutionAlgorithm() == JSEngineResolutionAlgorithm.HERMES) {
                return new HermesInstance();
            }
            return new JSCInstance();
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public List<ReactPackage> getReactPackages() {
            return this.reactNativeHostWrapper.getPackages();
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public void handleInstanceException(Exception error) {
            Intrinsics.checkNotNullParameter(error, "error");
            boolean useDeveloperSupport = this.reactNativeHostWrapper.getUseDeveloperSupport();
            Iterator<T> it = this.reactNativeHostWrapper.getReactNativeHostHandlers$expo_release().iterator();
            while (it.hasNext()) {
                ((ReactNativeHostHandler) it.next()).onReactInstanceException(useDeveloperSupport, error);
            }
        }
    }

    @JvmStatic
    public static final ReactHost createFromReactNativeHost(Context context, final ReactNativeHost reactNativeHost) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reactNativeHost, "reactNativeHost");
        if (!(reactNativeHost instanceof ReactNativeHostWrapper)) {
            throw new IllegalArgumentException("You can call createFromReactNativeHost only with instances of ReactNativeHostWrapper".toString());
        }
        if (reactHost == null) {
            ReactNativeHostWrapper reactNativeHostWrapper = (ReactNativeHostWrapper) reactNativeHost;
            final boolean useDeveloperSupport = reactNativeHostWrapper.getUseDeveloperSupport();
            ExpoReactHostDelegate expoReactHostDelegate = new ExpoReactHostDelegate(new WeakReference(context), reactNativeHostWrapper, null, null, null, 28, null);
            ReactJsExceptionHandler reactJsExceptionHandler = new ReactJsExceptionHandler() { // from class: expo.modules.ExpoReactHostFactory$$ExternalSyntheticLambda0
                @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler
                public final void reportJsException(ReadableMapBuffer readableMapBuffer) {
                    ExpoReactHostFactory.createFromReactNativeHost$lambda$1(readableMapBuffer);
                }
            };
            ComponentFactory componentFactory = new ComponentFactory();
            DefaultComponentsRegistry.INSTANCE.register(componentFactory);
            Iterator<T> it = reactNativeHostWrapper.getReactNativeHostHandlers$expo_release().iterator();
            while (it.hasNext()) {
                ((ReactNativeHostHandler) it.next()).onWillCreateReactInstance(useDeveloperSupport);
            }
            ReactHostImpl reactHostImpl = new ReactHostImpl(context, expoReactHostDelegate, componentFactory, true, reactJsExceptionHandler, useDeveloperSupport);
            reactHostImpl.setJsEngineResolutionAlgorithm(reactNativeHostWrapper.getJSEngineResolutionAlgorithm());
            Iterator<T> it2 = reactNativeHostWrapper.getReactNativeHostHandlers$expo_release().iterator();
            while (it2.hasNext()) {
                ((ReactNativeHostHandler) it2.next()).onDidCreateDevSupportManager(reactHostImpl.getDevSupportManager());
            }
            reactHostImpl.addReactInstanceEventListener(new ReactInstanceEventListener() { // from class: expo.modules.ExpoReactHostFactory$createFromReactNativeHost$4
                @Override // com.facebook.react.ReactInstanceEventListener
                public void onReactContextInitialized(ReactContext context2) {
                    Intrinsics.checkNotNullParameter(context2, "context");
                    List<ReactNativeHostHandler> reactNativeHostHandlers$expo_release = ((ReactNativeHostWrapper) ReactNativeHost.this).getReactNativeHostHandlers$expo_release();
                    boolean z = useDeveloperSupport;
                    Iterator<T> it3 = reactNativeHostHandlers$expo_release.iterator();
                    while (it3.hasNext()) {
                        ((ReactNativeHostHandler) it3.next()).onDidCreateReactInstance(z, context2);
                    }
                }
            });
            reactHost = reactHostImpl;
        }
        ReactHost reactHost2 = reactHost;
        Intrinsics.checkNotNull(reactHost2, "null cannot be cast to non-null type com.facebook.react.ReactHost");
        return reactHost2;
    }
}

package com.facebook.react.runtime;

import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.fabric.ReactNativeConfig;
import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactHostDelegate.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001\"J\b\u0010\u001b\u001a\u00020\u001cH&J\u0014\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001f\u001a\u00060 j\u0002`!H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006#À\u0006\u0001"}, d2 = {"Lcom/facebook/react/runtime/ReactHostDelegate;", "", "bindingsInstaller", "Lcom/facebook/react/runtime/BindingsInstaller;", "getBindingsInstaller", "()Lcom/facebook/react/runtime/BindingsInstaller;", "jsBundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "getJsBundleLoader", "()Lcom/facebook/react/bridge/JSBundleLoader;", "jsMainModulePath", "", "getJsMainModulePath", "()Ljava/lang/String;", "jsRuntimeFactory", "Lcom/facebook/react/runtime/JSRuntimeFactory;", "getJsRuntimeFactory", "()Lcom/facebook/react/runtime/JSRuntimeFactory;", "reactPackages", "", "Lcom/facebook/react/ReactPackage;", "getReactPackages", "()Ljava/util/List;", "turboModuleManagerDelegateBuilder", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getTurboModuleManagerDelegateBuilder", "()Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getReactNativeConfig", "Lcom/facebook/react/fabric/ReactNativeConfig;", "handleInstanceException", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Ljava/lang/Exception;", "Lkotlin/Exception;", "ReactHostDelegateBase", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@UnstableReactNativeAPI
/* loaded from: classes.dex */
public interface ReactHostDelegate {
    BindingsInstaller getBindingsInstaller();

    JSBundleLoader getJsBundleLoader();

    String getJsMainModulePath();

    JSRuntimeFactory getJsRuntimeFactory();

    ReactNativeConfig getReactNativeConfig();

    List<ReactPackage> getReactPackages();

    ReactPackageTurboModuleManagerDelegate.Builder getTurboModuleManagerDelegateBuilder();

    void handleInstanceException(Exception error);

    /* compiled from: ReactHostDelegate.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001Bt\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012'\b\u0002\u0010\u0011\u001a!\u0012\u0017\u0012\u00150\u0013j\u0002`\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u0012¢\u0006\u0002\u0010\u0019J\b\u0010&\u001a\u00020\u0010H\u0016J\u0014\u0010'\u001a\u00020\u00182\n\u0010\u0017\u001a\u00060\u0013j\u0002`\u0014H\u0016R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR-\u0010\u0011\u001a!\u0012\u0017\u0012\u00150\u0013j\u0002`\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006("}, d2 = {"Lcom/facebook/react/runtime/ReactHostDelegate$ReactHostDelegateBase;", "Lcom/facebook/react/runtime/ReactHostDelegate;", "jsMainModulePath", "", "jsBundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "jsRuntimeFactory", "Lcom/facebook/react/runtime/JSRuntimeFactory;", "turboModuleManagerDelegateBuilder", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "reactPackages", "", "Lcom/facebook/react/ReactPackage;", "bindingsInstaller", "Lcom/facebook/react/runtime/BindingsInstaller;", "reactNativeConfig", "Lcom/facebook/react/fabric/ReactNativeConfig;", "exceptionHandler", "Lkotlin/Function1;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lkotlin/ParameterName;", "name", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "(Ljava/lang/String;Lcom/facebook/react/bridge/JSBundleLoader;Lcom/facebook/react/runtime/JSRuntimeFactory;Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;Ljava/util/List;Lcom/facebook/react/runtime/BindingsInstaller;Lcom/facebook/react/fabric/ReactNativeConfig;Lkotlin/jvm/functions/Function1;)V", "getBindingsInstaller", "()Lcom/facebook/react/runtime/BindingsInstaller;", "getJsBundleLoader", "()Lcom/facebook/react/bridge/JSBundleLoader;", "getJsMainModulePath", "()Ljava/lang/String;", "getJsRuntimeFactory", "()Lcom/facebook/react/runtime/JSRuntimeFactory;", "getReactPackages", "()Ljava/util/List;", "getTurboModuleManagerDelegateBuilder", "()Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getReactNativeConfig", "handleInstanceException", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @UnstableReactNativeAPI
    public static final class ReactHostDelegateBase implements ReactHostDelegate {
        private final BindingsInstaller bindingsInstaller;
        private final Function1<Exception, Unit> exceptionHandler;
        private final JSBundleLoader jsBundleLoader;
        private final String jsMainModulePath;
        private final JSRuntimeFactory jsRuntimeFactory;
        private final ReactNativeConfig reactNativeConfig;
        private final List<ReactPackage> reactPackages;
        private final ReactPackageTurboModuleManagerDelegate.Builder turboModuleManagerDelegateBuilder;

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public BindingsInstaller getBindingsInstaller() {
            return this.bindingsInstaller;
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public JSBundleLoader getJsBundleLoader() {
            return this.jsBundleLoader;
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public String getJsMainModulePath() {
            return this.jsMainModulePath;
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public JSRuntimeFactory getJsRuntimeFactory() {
            return this.jsRuntimeFactory;
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public ReactNativeConfig getReactNativeConfig() {
            return this.reactNativeConfig;
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public List<ReactPackage> getReactPackages() {
            return this.reactPackages;
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public ReactPackageTurboModuleManagerDelegate.Builder getTurboModuleManagerDelegateBuilder() {
            return this.turboModuleManagerDelegateBuilder;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ReactHostDelegateBase(String jsMainModulePath, JSBundleLoader jsBundleLoader, JSRuntimeFactory jsRuntimeFactory, ReactPackageTurboModuleManagerDelegate.Builder turboModuleManagerDelegateBuilder, List<? extends ReactPackage> reactPackages, BindingsInstaller bindingsInstaller, ReactNativeConfig reactNativeConfig, Function1<? super Exception, Unit> exceptionHandler) {
            Intrinsics.checkNotNullParameter(jsMainModulePath, "jsMainModulePath");
            Intrinsics.checkNotNullParameter(jsBundleLoader, "jsBundleLoader");
            Intrinsics.checkNotNullParameter(jsRuntimeFactory, "jsRuntimeFactory");
            Intrinsics.checkNotNullParameter(turboModuleManagerDelegateBuilder, "turboModuleManagerDelegateBuilder");
            Intrinsics.checkNotNullParameter(reactPackages, "reactPackages");
            Intrinsics.checkNotNullParameter(reactNativeConfig, "reactNativeConfig");
            Intrinsics.checkNotNullParameter(exceptionHandler, "exceptionHandler");
            this.jsMainModulePath = jsMainModulePath;
            this.jsBundleLoader = jsBundleLoader;
            this.jsRuntimeFactory = jsRuntimeFactory;
            this.turboModuleManagerDelegateBuilder = turboModuleManagerDelegateBuilder;
            this.reactPackages = reactPackages;
            this.bindingsInstaller = bindingsInstaller;
            this.reactNativeConfig = reactNativeConfig;
            this.exceptionHandler = exceptionHandler;
        }

        public /* synthetic */ ReactHostDelegateBase(String str, JSBundleLoader jSBundleLoader, JSRuntimeFactory jSRuntimeFactory, ReactPackageTurboModuleManagerDelegate.Builder builder, List list, BindingsInstaller bindingsInstaller, ReactNativeConfig reactNativeConfig, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, jSBundleLoader, jSRuntimeFactory, builder, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? null : bindingsInstaller, (i & 64) != 0 ? ReactNativeConfig.DEFAULT_CONFIG : reactNativeConfig, (i & 128) != 0 ? new Function1<Exception, Unit>() { // from class: com.facebook.react.runtime.ReactHostDelegate.ReactHostDelegateBase.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }
            } : function1);
        }

        @Override // com.facebook.react.runtime.ReactHostDelegate
        public void handleInstanceException(Exception error) {
            Intrinsics.checkNotNullParameter(error, "error");
            this.exceptionHandler.invoke(error);
        }
    }
}

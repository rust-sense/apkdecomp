package com.swmansion.gesturehandler;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager;
import com.swmansion.gesturehandler.react.RNGestureHandlerModule;
import com.swmansion.gesturehandler.react.RNGestureHandlerRootViewManager;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNGestureHandlerPackage.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\f\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u0016J\u001e\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\r0\u00122\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00192\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/swmansion/gesturehandler/RNGestureHandlerPackage;", "Lcom/facebook/react/TurboReactPackage;", "Lcom/facebook/react/ViewManagerOnDemandReactPackage;", "()V", "viewManagers", "", "", "Lcom/facebook/react/bridge/ModuleSpec;", "getViewManagers", "()Ljava/util/Map;", "viewManagers$delegate", "Lkotlin/Lazy;", "createViewManager", "Lcom/facebook/react/uimanager/ViewManager;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "viewManagerName", "createViewManagers", "", "getModule", "Lcom/facebook/react/bridge/NativeModule;", "name", "getReactModuleInfoProvider", "Lcom/facebook/react/module/model/ReactModuleInfoProvider;", "getViewManagerNames", "", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RNGestureHandlerPackage extends TurboReactPackage implements ViewManagerOnDemandReactPackage {

    /* renamed from: viewManagers$delegate, reason: from kotlin metadata */
    private final Lazy viewManagers = LazyKt.lazy(RNGestureHandlerPackage$viewManagers$2.INSTANCE);

    private final Map<String, ModuleSpec> getViewManagers() {
        return (Map) this.viewManagers.getValue();
    }

    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return CollectionsKt.listOf((Object[]) new ViewManager[]{new RNGestureHandlerRootViewManager(), new RNGestureHandlerButtonViewManager()});
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public List<String> getViewManagerNames(ReactApplicationContext reactContext) {
        return CollectionsKt.toList(getViewManagers().keySet());
    }

    @Override // com.facebook.react.BaseReactPackage
    protected List<ModuleSpec> getViewManagers(ReactApplicationContext reactContext) {
        return CollectionsKt.toMutableList((Collection) getViewManagers().values());
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public ViewManager<?, ?> createViewManager(ReactApplicationContext reactContext, String viewManagerName) {
        Provider<? extends NativeModule> provider;
        ModuleSpec moduleSpec = getViewManagers().get(viewManagerName);
        NativeModule nativeModule = (moduleSpec == null || (provider = moduleSpec.getProvider()) == null) ? null : provider.get();
        if (nativeModule instanceof ViewManager) {
            return (ViewManager) nativeModule;
        }
        return null;
    }

    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public NativeModule getModule(String name, ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        if (Intrinsics.areEqual(name, "RNGestureHandlerModule")) {
            return new RNGestureHandlerModule(reactContext);
        }
        return null;
    }

    @Override // com.facebook.react.BaseReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            Object newInstance = Class.forName("com.swmansion.gesturehandler.RNGestureHandlerPackage$$ReactModuleInfoProvider").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type com.facebook.react.module.model.ReactModuleInfoProvider");
            return (ReactModuleInfoProvider) newInstance;
        } catch (ClassNotFoundException unused) {
            return new ReactModuleInfoProvider() { // from class: com.swmansion.gesturehandler.RNGestureHandlerPackage$$ExternalSyntheticLambda0
                @Override // com.facebook.react.module.model.ReactModuleInfoProvider
                public final Map getReactModuleInfos() {
                    Map reactModuleInfoProvider$lambda$0;
                    reactModuleInfoProvider$lambda$0 = RNGestureHandlerPackage.getReactModuleInfoProvider$lambda$0();
                    return reactModuleInfoProvider$lambda$0;
                }
            };
        } catch (IllegalAccessException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for RNGestureHandlerPackage$$ReactModuleInfoProvider", e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for RNGestureHandlerPackage$$ReactModuleInfoProvider", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map getReactModuleInfoProvider$lambda$0() {
        Annotation annotation = RNGestureHandlerModule.class.getAnnotation(ReactModule.class);
        Intrinsics.checkNotNull(annotation);
        ReactModule reactModule = (ReactModule) annotation;
        return MapsKt.mutableMapOf(TuplesKt.to("RNGestureHandlerModule", new ReactModuleInfo(reactModule.name(), RNGestureHandlerModule.class.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), true, reactModule.isCxxModule(), true)));
    }
}

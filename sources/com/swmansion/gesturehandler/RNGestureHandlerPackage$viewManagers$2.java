package com.swmansion.gesturehandler;

import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager;
import com.swmansion.gesturehandler.react.RNGestureHandlerRootViewManager;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: RNGestureHandlerPackage.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n \u0004*\u0004\u0018\u00010\u00030\u00030\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "", "Lcom/facebook/react/bridge/ModuleSpec;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class RNGestureHandlerPackage$viewManagers$2 extends Lambda implements Function0<Map<String, ? extends ModuleSpec>> {
    public static final RNGestureHandlerPackage$viewManagers$2 INSTANCE = new RNGestureHandlerPackage$viewManagers$2();

    RNGestureHandlerPackage$viewManagers$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Map<String, ? extends ModuleSpec> invoke() {
        return MapsKt.mapOf(TuplesKt.to(RNGestureHandlerRootViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() { // from class: com.swmansion.gesturehandler.RNGestureHandlerPackage$viewManagers$2$$ExternalSyntheticLambda0
            @Override // javax.inject.Provider
            public final Object get() {
                NativeModule invoke$lambda$0;
                invoke$lambda$0 = RNGestureHandlerPackage$viewManagers$2.invoke$lambda$0();
                return invoke$lambda$0;
            }
        })), TuplesKt.to(RNGestureHandlerButtonViewManager.REACT_CLASS, ModuleSpec.viewManagerSpec(new Provider() { // from class: com.swmansion.gesturehandler.RNGestureHandlerPackage$viewManagers$2$$ExternalSyntheticLambda1
            @Override // javax.inject.Provider
            public final Object get() {
                NativeModule invoke$lambda$1;
                invoke$lambda$1 = RNGestureHandlerPackage$viewManagers$2.invoke$lambda$1();
                return invoke$lambda$1;
            }
        })));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NativeModule invoke$lambda$0() {
        return new RNGestureHandlerRootViewManager();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NativeModule invoke$lambda$1() {
        return new RNGestureHandlerButtonViewManager();
    }
}

package expo.modules;

import android.app.Application;
import android.content.Context;
import com.facebook.react.JSEngineResolutionAlgorithm;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.UIManagerProvider;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import expo.modules.core.interfaces.ReactNativeHostHandler;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: ReactNativeHostWrapper.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014¨\u0006\u0016"}, d2 = {"Lexpo/modules/ReactNativeHostWrapper;", "Lexpo/modules/ReactNativeHostWrapperBase;", "application", "Landroid/app/Application;", "host", "Lcom/facebook/react/ReactNativeHost;", "(Landroid/app/Application;Lcom/facebook/react/ReactNativeHost;)V", "getDevSupportManagerFactory", "Lcom/facebook/react/devsupport/DevSupportManagerFactory;", "getJSEngineResolutionAlgorithm", "Lcom/facebook/react/JSEngineResolutionAlgorithm;", "getReactPackageTurboModuleManagerDelegateBuilder", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getRedBoxHandler", "Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "getShouldRequireActivity", "", "getSurfaceDelegateFactory", "Lcom/facebook/react/common/SurfaceDelegateFactory;", "getUIManagerProvider", "Lcom/facebook/react/bridge/UIManagerProvider;", "Companion", "expo_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactNativeHostWrapper extends ReactNativeHostWrapperBase {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final ReactHost createReactHost(Context context, ReactNativeHost reactNativeHost) {
        return INSTANCE.createReactHost(context, reactNativeHost);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactNativeHostWrapper(Application application, ReactNativeHost host) {
        super(application, host);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(host, "host");
    }

    @Override // com.facebook.react.ReactNativeHost
    protected DevSupportManagerFactory getDevSupportManagerFactory() {
        DevSupportManagerFactory devSupportManagerFactory = (DevSupportManagerFactory) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(getReactNativeHostHandlers$expo_release()), new Function1<ReactNativeHostHandler, Object>() { // from class: expo.modules.ReactNativeHostWrapper$getDevSupportManagerFactory$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(ReactNativeHostHandler reactNativeHostHandler) {
                return reactNativeHostHandler.getDevSupportManagerFactory();
            }
        }));
        return devSupportManagerFactory == null ? (DevSupportManagerFactory) invokeDelegateMethod$expo_release("getDevSupportManagerFactory") : devSupportManagerFactory;
    }

    @Override // com.facebook.react.ReactNativeHost
    protected ReactPackageTurboModuleManagerDelegate.Builder getReactPackageTurboModuleManagerDelegateBuilder() {
        return (ReactPackageTurboModuleManagerDelegate.Builder) invokeDelegateMethod$expo_release("getReactPackageTurboModuleManagerDelegateBuilder");
    }

    @Override // com.facebook.react.ReactNativeHost
    protected UIManagerProvider getUIManagerProvider() {
        return (UIManagerProvider) invokeDelegateMethod$expo_release("getUIManagerProvider");
    }

    @Override // com.facebook.react.ReactNativeHost
    public JSEngineResolutionAlgorithm getJSEngineResolutionAlgorithm() {
        return (JSEngineResolutionAlgorithm) invokeDelegateMethod$expo_release("getJSEngineResolutionAlgorithm");
    }

    @Override // com.facebook.react.ReactNativeHost
    public boolean getShouldRequireActivity() {
        return getHost().getShouldRequireActivity();
    }

    @Override // com.facebook.react.ReactNativeHost
    public SurfaceDelegateFactory getSurfaceDelegateFactory() {
        SurfaceDelegateFactory surfaceDelegateFactory = getHost().getSurfaceDelegateFactory();
        Intrinsics.checkNotNullExpressionValue(surfaceDelegateFactory, "getSurfaceDelegateFactory(...)");
        return surfaceDelegateFactory;
    }

    @Override // com.facebook.react.ReactNativeHost
    protected RedBoxHandler getRedBoxHandler() {
        return (RedBoxHandler) invokeDelegateMethod$expo_release("getRedBoxHandler");
    }

    /* compiled from: ReactNativeHostWrapper.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lexpo/modules/ReactNativeHostWrapper$Companion;", "", "()V", "createReactHost", "Lcom/facebook/react/ReactHost;", "context", "Landroid/content/Context;", "reactNativeHost", "Lcom/facebook/react/ReactNativeHost;", "expo_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ReactHost createReactHost(Context context, ReactNativeHost reactNativeHost) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(reactNativeHost, "reactNativeHost");
            return ExpoReactHostFactory.createFromReactNativeHost(context, reactNativeHost);
        }
    }
}

package expo.modules.splashscreen;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import expo.modules.core.interfaces.ReactActivityHandler;
import expo.modules.splashscreen.singletons.SplashScreen;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreenReactActivityLifecycleListener.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenReactActivityHandler;", "Lexpo/modules/core/interfaces/ReactActivityHandler;", "()V", "getDelayLoadAppHandler", "Lexpo/modules/core/interfaces/ReactActivityHandler$DelayLoadAppHandler;", "activity", "Lcom/facebook/react/ReactActivity;", "reactNativeHost", "Lcom/facebook/react/ReactNativeHost;", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SplashScreenReactActivityHandler implements ReactActivityHandler {
    @Override // expo.modules.core.interfaces.ReactActivityHandler
    public /* synthetic */ ViewGroup createReactRootViewContainer(Activity activity) {
        return ReactActivityHandler.CC.$default$createReactRootViewContainer(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityHandler
    public /* synthetic */ ReactActivityDelegate onDidCreateReactActivityDelegate(ReactActivity reactActivity, ReactActivityDelegate reactActivityDelegate) {
        return ReactActivityHandler.CC.$default$onDidCreateReactActivityDelegate(this, reactActivity, reactActivityDelegate);
    }

    @Override // expo.modules.core.interfaces.ReactActivityHandler
    public /* synthetic */ boolean onKeyDown(int i, KeyEvent keyEvent) {
        return ReactActivityHandler.CC.$default$onKeyDown(this, i, keyEvent);
    }

    @Override // expo.modules.core.interfaces.ReactActivityHandler
    public /* synthetic */ boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return ReactActivityHandler.CC.$default$onKeyLongPress(this, i, keyEvent);
    }

    @Override // expo.modules.core.interfaces.ReactActivityHandler
    public /* synthetic */ boolean onKeyUp(int i, KeyEvent keyEvent) {
        return ReactActivityHandler.CC.$default$onKeyUp(this, i, keyEvent);
    }

    @Override // expo.modules.core.interfaces.ReactActivityHandler
    public ReactActivityHandler.DelayLoadAppHandler getDelayLoadAppHandler(ReactActivity activity, ReactNativeHost reactNativeHost) {
        SplashScreenImageResizeMode resizeMode;
        boolean statusBarTranslucent;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(reactNativeHost, "reactNativeHost");
        SplashScreen splashScreen = SplashScreen.INSTANCE;
        ReactActivity reactActivity = activity;
        ReactActivity reactActivity2 = activity;
        resizeMode = SplashScreenReactActivityLifecycleListenerKt.getResizeMode(reactActivity2);
        statusBarTranslucent = SplashScreenReactActivityLifecycleListenerKt.getStatusBarTranslucent(reactActivity2);
        splashScreen.ensureShown$expo_splash_screen_release(reactActivity, resizeMode, ReactRootView.class, statusBarTranslucent);
        return null;
    }
}

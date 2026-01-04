package expo.modules.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.ReactRootView;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.splashscreen.singletons.SplashScreen;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreenReactActivityLifecycleListener.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenReactActivityLifecycleListener;", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "()V", "onContentChanged", "", "activity", "Landroid/app/Activity;", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SplashScreenReactActivityLifecycleListener implements ReactActivityLifecycleListener {
    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ boolean onBackPressed() {
        return ReactActivityLifecycleListener.CC.$default$onBackPressed(this);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onCreate(Activity activity, Bundle bundle) {
        ReactActivityLifecycleListener.CC.$default$onCreate(this, activity, bundle);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onDestroy(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onDestroy(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ boolean onNewIntent(Intent intent) {
        return ReactActivityLifecycleListener.CC.$default$onNewIntent(this, intent);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onPause(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onPause(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onResume(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onResume(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public void onContentChanged(Activity activity) {
        SplashScreenImageResizeMode resizeMode;
        boolean statusBarTranslucent;
        Intrinsics.checkNotNullParameter(activity, "activity");
        SplashScreen splashScreen = SplashScreen.INSTANCE;
        Activity activity2 = activity;
        resizeMode = SplashScreenReactActivityLifecycleListenerKt.getResizeMode(activity2);
        statusBarTranslucent = SplashScreenReactActivityLifecycleListenerKt.getStatusBarTranslucent(activity2);
        splashScreen.ensureShown$expo_splash_screen_release(activity, resizeMode, ReactRootView.class, statusBarTranslucent);
    }
}

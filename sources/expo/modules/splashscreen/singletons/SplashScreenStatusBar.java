package expo.modules.splashscreen.singletons;

import android.app.Activity;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreenStatusBar.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/splashscreen/singletons/SplashScreenStatusBar;", "", "()V", "configureTranslucent", "", "activity", "Landroid/app/Activity;", "translucent", "", "(Landroid/app/Activity;Ljava/lang/Boolean;)V", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SplashScreenStatusBar {
    public static final SplashScreenStatusBar INSTANCE = new SplashScreenStatusBar();

    private SplashScreenStatusBar() {
    }

    public final void configureTranslucent(final Activity activity, Boolean translucent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (translucent != null) {
            final boolean booleanValue = translucent.booleanValue();
            activity.runOnUiThread(new Runnable() { // from class: expo.modules.splashscreen.singletons.SplashScreenStatusBar$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    SplashScreenStatusBar.configureTranslucent$lambda$2$lambda$1(activity, booleanValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void configureTranslucent$lambda$2$lambda$1(Activity activity, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        View decorView = activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        if (z) {
            decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: expo.modules.splashscreen.singletons.SplashScreenStatusBar$$ExternalSyntheticLambda0
                @Override // android.view.View.OnApplyWindowInsetsListener
                public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    WindowInsets configureTranslucent$lambda$2$lambda$1$lambda$0;
                    configureTranslucent$lambda$2$lambda$1$lambda$0 = SplashScreenStatusBar.configureTranslucent$lambda$2$lambda$1$lambda$0(view, windowInsets);
                    return configureTranslucent$lambda$2$lambda$1$lambda$0;
                }
            });
        } else {
            decorView.setOnApplyWindowInsetsListener(null);
        }
        ViewCompat.requestApplyInsets(decorView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsets configureTranslucent$lambda$2$lambda$1$lambda$0(View v, WindowInsets insets) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(insets, "insets");
        WindowInsets onApplyWindowInsets = v.onApplyWindowInsets(insets);
        return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
    }
}

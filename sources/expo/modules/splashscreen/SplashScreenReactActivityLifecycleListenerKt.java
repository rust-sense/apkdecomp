package expo.modules.splashscreen;

import android.content.Context;
import expo.modules.splashscreen.SplashScreenImageResizeMode;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreenReactActivityLifecycleListener.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0006"}, d2 = {"getResizeMode", "Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "context", "Landroid/content/Context;", "getStatusBarTranslucent", "", "expo-splash-screen_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SplashScreenReactActivityLifecycleListenerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final SplashScreenImageResizeMode getResizeMode(Context context) {
        SplashScreenImageResizeMode.Companion companion = SplashScreenImageResizeMode.INSTANCE;
        String string = context.getString(R.string.expo_splash_screen_resize_mode);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        SplashScreenImageResizeMode fromString = companion.fromString(lowerCase);
        return fromString == null ? SplashScreenImageResizeMode.CONTAIN : fromString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getStatusBarTranslucent(Context context) {
        return Boolean.parseBoolean(context.getString(R.string.expo_splash_screen_status_bar_translucent));
    }
}

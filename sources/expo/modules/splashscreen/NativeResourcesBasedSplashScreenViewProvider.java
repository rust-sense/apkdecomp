package expo.modules.splashscreen;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NativeResourcesBasedSplashScreenViewProvider.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lexpo/modules/splashscreen/NativeResourcesBasedSplashScreenViewProvider;", "Lexpo/modules/splashscreen/SplashScreenViewProvider;", ViewProps.RESIZE_MODE, "Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "(Lexpo/modules/splashscreen/SplashScreenImageResizeMode;)V", "createSplashScreenView", "Lexpo/modules/splashscreen/SplashScreenView;", "context", "Landroid/content/Context;", "getBackgroundColor", "", "getImageResource", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NativeResourcesBasedSplashScreenViewProvider implements SplashScreenViewProvider {
    private final SplashScreenImageResizeMode resizeMode;

    public NativeResourcesBasedSplashScreenViewProvider(SplashScreenImageResizeMode resizeMode) {
        Intrinsics.checkNotNullParameter(resizeMode, "resizeMode");
        this.resizeMode = resizeMode;
    }

    @Override // expo.modules.splashscreen.SplashScreenViewProvider
    public SplashScreenView createSplashScreenView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SplashScreenView splashScreenView = new SplashScreenView(context);
        splashScreenView.setBackgroundColor(getBackgroundColor(context));
        splashScreenView.getImageView().setImageResource(getImageResource());
        splashScreenView.configureImageViewResizeMode(this.resizeMode);
        return splashScreenView;
    }

    private final int getBackgroundColor(Context context) {
        return ContextCompat.getColor(context, R.color.splashscreen_background);
    }

    private final int getImageResource() {
        if (this.resizeMode == SplashScreenImageResizeMode.NATIVE) {
            return R.drawable.splashscreen;
        }
        return R.drawable.splashscreen_image;
    }
}

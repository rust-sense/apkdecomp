package expo.modules.image;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import expo.modules.image.blurhash.BlurhashDecoder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoImageComponentCallbacks.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lexpo/modules/image/ExpoImageComponentCallbacks;", "Landroid/content/ComponentCallbacks2;", "()V", "onConfigurationChanged", "", "newConfig", "Landroid/content/res/Configuration;", "onLowMemory", "onTrimMemory", "level", "", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoImageComponentCallbacks implements ComponentCallbacks2 {
    public static final ExpoImageComponentCallbacks INSTANCE = new ExpoImageComponentCallbacks();

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
    }

    private ExpoImageComponentCallbacks() {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        BlurhashDecoder.INSTANCE.clearCache();
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        if (level == 15) {
            onLowMemory();
        }
    }
}

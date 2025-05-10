package expo.modules.image;

import com.bumptech.glide.load.Option;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomOptions.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lexpo/modules/image/CustomOptions;", "", "()V", "tintColor", "Lcom/bumptech/glide/load/Option;", "", "kotlin.jvm.PlatformType", "getTintColor", "()Lcom/bumptech/glide/load/Option;", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CustomOptions {
    public static final CustomOptions INSTANCE = new CustomOptions();
    private static final Option<Integer> tintColor;

    public final Option<Integer> getTintColor() {
        return tintColor;
    }

    private CustomOptions() {
    }

    static {
        Option<Integer> memory = Option.memory("ExpoTintColor");
        Intrinsics.checkNotNullExpressionValue(memory, "memory(...)");
        tintColor = memory;
    }
}

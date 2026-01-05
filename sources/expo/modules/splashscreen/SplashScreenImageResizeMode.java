package expo.modules.splashscreen;

import android.widget.ImageView;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.SentryStackFrame;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: SplashScreenImageResizeMode.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "", "scaleType", "Landroid/widget/ImageView$ScaleType;", ViewProps.RESIZE_MODE, "", "(Ljava/lang/String;ILandroid/widget/ImageView$ScaleType;Ljava/lang/String;)V", "getScaleType", "()Landroid/widget/ImageView$ScaleType;", "CONTAIN", "COVER", "NATIVE", "Companion", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SplashScreenImageResizeMode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ SplashScreenImageResizeMode[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final String resizeMode;
    private final ImageView.ScaleType scaleType;
    public static final SplashScreenImageResizeMode CONTAIN = new SplashScreenImageResizeMode("CONTAIN", 0, ImageView.ScaleType.FIT_CENTER, "contain");
    public static final SplashScreenImageResizeMode COVER = new SplashScreenImageResizeMode("COVER", 1, ImageView.ScaleType.CENTER_CROP, "cover");
    public static final SplashScreenImageResizeMode NATIVE = new SplashScreenImageResizeMode("NATIVE", 2, ImageView.ScaleType.CENTER, SentryStackFrame.JsonKeys.NATIVE);

    private static final /* synthetic */ SplashScreenImageResizeMode[] $values() {
        return new SplashScreenImageResizeMode[]{CONTAIN, COVER, NATIVE};
    }

    @JvmStatic
    public static final SplashScreenImageResizeMode fromString(String str) {
        return INSTANCE.fromString(str);
    }

    public static EnumEntries<SplashScreenImageResizeMode> getEntries() {
        return $ENTRIES;
    }

    public static SplashScreenImageResizeMode valueOf(String str) {
        return (SplashScreenImageResizeMode) Enum.valueOf(SplashScreenImageResizeMode.class, str);
    }

    public static SplashScreenImageResizeMode[] values() {
        return (SplashScreenImageResizeMode[]) $VALUES.clone();
    }

    public final ImageView.ScaleType getScaleType() {
        return this.scaleType;
    }

    private SplashScreenImageResizeMode(String str, int i, ImageView.ScaleType scaleType, String str2) {
        this.scaleType = scaleType;
        this.resizeMode = str2;
    }

    static {
        SplashScreenImageResizeMode[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: SplashScreenImageResizeMode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenImageResizeMode$Companion;", "", "()V", "fromString", "Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", ViewProps.RESIZE_MODE, "", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SplashScreenImageResizeMode fromString(String resizeMode) {
            for (SplashScreenImageResizeMode splashScreenImageResizeMode : SplashScreenImageResizeMode.values()) {
                if (Intrinsics.areEqual(splashScreenImageResizeMode.resizeMode, resizeMode)) {
                    return splashScreenImageResizeMode;
                }
            }
            return null;
        }
    }
}

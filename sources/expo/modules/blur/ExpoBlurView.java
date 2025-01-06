package expo.modules.blur;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;
import expo.modules.blur.enums.BlurMethod;
import expo.modules.blur.enums.TintStyle;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.views.ExpoView;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoBlurView.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\nR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001c"}, d2 = {"Lexpo/modules/blur/ExpoBlurView;", "Lexpo/modules/kotlin/views/ExpoView;", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", "(Landroid/content/Context;Lexpo/modules/kotlin/AppContext;)V", "blurMethod", "Lexpo/modules/blur/enums/BlurMethod;", "blurRadius", "", "blurReduction", "blurView", "Leightbitlab/com/blurview/BlurView;", "tint", "Lexpo/modules/blur/enums/TintStyle;", "getTint$expo_blur_release", "()Lexpo/modules/blur/enums/TintStyle;", "setTint$expo_blur_release", "(Lexpo/modules/blur/enums/TintStyle;)V", "applyBlurReduction", "", "reductionFactor", "applyTint", "setBlurMethod", Request.JsonKeys.METHOD, "setBlurRadius", "radius", "expo-blur_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoBlurView extends ExpoView {
    private BlurMethod blurMethod;
    private float blurRadius;
    private float blurReduction;
    private final BlurView blurView;
    private TintStyle tint;

    /* compiled from: ExpoBlurView.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BlurMethod.values().length];
            try {
                iArr[BlurMethod.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BlurMethod.DIMEZIS_BLUR_VIEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* renamed from: getTint$expo_blur_release, reason: from getter */
    public final TintStyle getTint() {
        return this.tint;
    }

    public final void setTint$expo_blur_release(TintStyle tintStyle) {
        Intrinsics.checkNotNullParameter(tintStyle, "<set-?>");
        this.tint = tintStyle;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpoBlurView(Context context, AppContext appContext) {
        super(context, appContext);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.blurMethod = BlurMethod.NONE;
        this.blurReduction = 4.0f;
        this.blurRadius = 50.0f;
        this.tint = TintStyle.DEFAULT;
        BlurView blurView = new BlurView(context);
        blurView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        Activity currentActivity = appContext.getCurrentActivity();
        if (currentActivity == null) {
            throw new Exceptions.MissingActivity();
        }
        Window window = currentActivity.getWindow();
        View decorView = window != null ? window.getDecorView() : null;
        ViewGroup viewGroup = decorView != null ? (ViewGroup) decorView.findViewById(android.R.id.content) : null;
        if (viewGroup == null) {
            throw new Exceptions.MissingRootView();
        }
        Intrinsics.checkNotNull(viewGroup);
        if (Build.VERSION.SDK_INT >= 31) {
            blurView.setupWith(viewGroup, new RenderEffectBlur()).setFrameClearDrawable(decorView.getBackground());
        } else {
            blurView.setupWith(viewGroup, new RenderScriptBlur(context)).setFrameClearDrawable(decorView.getBackground());
        }
        addView(blurView);
        this.blurView = blurView;
    }

    public final void setBlurRadius(float radius) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.blurMethod.ordinal()];
        if (i == 1) {
            setBackgroundColor(this.tint.toBlurEffect(this.blurRadius));
        } else if (i == 2) {
            this.blurView.setBlurEnabled(true ^ (radius == 0.0f));
            if (radius > 0.0f) {
                this.blurView.setBlurRadius(radius / this.blurReduction);
                this.blurView.invalidate();
            }
        }
        this.blurRadius = radius;
    }

    public final void setBlurMethod(BlurMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        this.blurMethod = method;
        int i = WhenMappings.$EnumSwitchMapping$0[method.ordinal()];
        if (i == 1) {
            this.blurView.setBlurEnabled(false);
        } else if (i == 2) {
            this.blurView.setBlurEnabled(true);
            setBackgroundColor(0);
        }
        setBlurRadius(this.blurRadius);
    }

    public final void applyBlurReduction(float reductionFactor) {
        this.blurReduction = reductionFactor;
        setBlurRadius(this.blurRadius);
    }

    public final void applyTint() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.blurMethod.ordinal()];
        if (i == 1) {
            setBackgroundColor(this.tint.toBlurEffect(this.blurRadius));
        } else if (i == 2) {
            this.blurView.setOverlayColor(this.tint.toBlurEffect(this.blurRadius));
        }
        this.blurView.invalidate();
    }
}

package com.swmansion.gesturehandler.react;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupKt;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerDelegate;
import com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;
import com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* compiled from: RNGestureHandlerButtonViewManager.kt */
@ReactModule(name = RNGestureHandlerButtonViewManager.REACT_CLASS)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0007\u0018\u0000 32\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u000223B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0006H\u0014J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0014J\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0017J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0012H\u0017J\u001f\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0017¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0017J\u001a\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\fH\u0017J\u0018\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0012H\u0017J\u0018\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0012H\u0017J\u0018\u0010!\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0012H\u0017J\u0018\u0010#\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%H\u0017J\u0018\u0010&\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010'\u001a\u00020%H\u0017J\u0018\u0010(\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010)\u001a\u00020%H\u0017J\u0018\u0010*\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010+\u001a\u00020%H\u0017J\u001f\u0010,\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010-\u001a\u0004\u0018\u00010\u0017H\u0017¢\u0006\u0002\u0010\u0018J\u0018\u0010.\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010/\u001a\u00020\u0017H\u0017J\u0018\u00100\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u00101\u001a\u00020%H\u0017R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup;", "Lcom/facebook/react/viewmanagers/RNGestureHandlerButtonManagerInterface;", "()V", "mDelegate", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "createViewInstance", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getDelegate", "getName", "", "onAfterUpdateTransaction", "", "view", "setBorderBottomLeftRadius", ViewProps.BORDER_BOTTOM_LEFT_RADIUS, "", "setBorderBottomRightRadius", ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, "setBorderColor", ViewProps.BORDER_COLOR, "", "(Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup;Ljava/lang/Integer;)V", "setBorderRadius", "borderRadius", "setBorderStyle", "borderStyle", "setBorderTopLeftRadius", ViewProps.BORDER_TOP_LEFT_RADIUS, "setBorderTopRightRadius", ViewProps.BORDER_TOP_RIGHT_RADIUS, "setBorderWidth", ViewProps.BORDER_WIDTH, "setBorderless", "useBorderlessDrawable", "", "setEnabled", "enabled", "setExclusive", "exclusive", "setForeground", "useDrawableOnForeground", "setRippleColor", "rippleColor", "setRippleRadius", "rippleRadius", "setTouchSoundDisabled", "touchSoundDisabled", "ButtonViewGroup", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RNGestureHandlerButtonViewManager extends ViewGroupManager<ButtonViewGroup> implements RNGestureHandlerButtonManagerInterface<ButtonViewGroup> {
    public static final String REACT_CLASS = "RNGestureHandlerButton";
    private final ViewManagerDelegate<ButtonViewGroup> mDelegate = new RNGestureHandlerButtonManagerDelegate(this);

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<ButtonViewGroup> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ButtonViewGroup createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ButtonViewGroup(context);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "foreground")
    public void setForeground(ButtonViewGroup view, boolean useDrawableOnForeground) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setUseDrawableOnForeground(useDrawableOnForeground);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "borderless")
    public void setBorderless(ButtonViewGroup view, boolean useBorderlessDrawable) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setUseBorderlessDrawable(useBorderlessDrawable);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "enabled")
    public void setEnabled(ButtonViewGroup view, boolean enabled) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setEnabled(enabled);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = "borderRadius")
    public void setBorderRadius(ButtonViewGroup view, float borderRadius) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBorderRadius(borderRadius);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.BORDER_TOP_LEFT_RADIUS)
    public void setBorderTopLeftRadius(ButtonViewGroup view, float borderTopLeftRadius) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBorderTopLeftRadius(borderTopLeftRadius);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.BORDER_TOP_RIGHT_RADIUS)
    public void setBorderTopRightRadius(ButtonViewGroup view, float borderTopRightRadius) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBorderTopRightRadius(borderTopRightRadius);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.BORDER_BOTTOM_LEFT_RADIUS)
    public void setBorderBottomLeftRadius(ButtonViewGroup view, float borderBottomLeftRadius) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBorderBottomLeftRadius(borderBottomLeftRadius);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.BORDER_BOTTOM_RIGHT_RADIUS)
    public void setBorderBottomRightRadius(ButtonViewGroup view, float borderBottomRightRadius) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBorderBottomRightRadius(borderBottomRightRadius);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = ViewProps.BORDER_WIDTH)
    public void setBorderWidth(ButtonViewGroup view, float borderWidth) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBorderWidth(borderWidth);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = ViewProps.BORDER_COLOR)
    public void setBorderColor(ButtonViewGroup view, Integer borderColor) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBorderColor(borderColor);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ButtonViewGroup view, String borderStyle) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setBorderStyle(borderStyle);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "rippleColor")
    public void setRippleColor(ButtonViewGroup view, Integer rippleColor) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setRippleColor(rippleColor);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "rippleRadius")
    public void setRippleRadius(ButtonViewGroup view, int rippleRadius) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setRippleRadius(Integer.valueOf(rippleRadius));
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "exclusive")
    public void setExclusive(ButtonViewGroup view, boolean exclusive) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setExclusive(exclusive);
    }

    @Override // com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface
    @ReactProp(name = "touchSoundDisabled")
    public void setTouchSoundDisabled(ButtonViewGroup view, boolean touchSoundDisabled) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setSoundEffectsEnabled(!touchSoundDisabled);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ButtonViewGroup view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.updateBackground();
    }

    /* compiled from: RNGestureHandlerButtonViewManager.kt */
    @Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 w2\u00020\u00012\u00020\u0002:\u0001wB\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LH\u0016J\b\u0010M\u001a\u00020NH\u0002J\n\u0010O\u001a\u0004\u0018\u00010PH\u0002J\b\u0010Q\u001a\u00020.H\u0016J\n\u0010R\u001a\u0004\u0018\u00010SH\u0002J\u0018\u0010T\u001a\u00020J2\u0006\u0010U\u001a\u00020\t2\u0006\u0010V\u001a\u00020\tH\u0016J\u0018\u0010W\u001a\u00020J2\u0006\u0010U\u001a\u00020\t2\u0006\u0010V\u001a\u00020\tH\u0016J\n\u0010X\u001a\u0004\u0018\u00010YH\u0002J\u0018\u0010Z\u001a\u00020.2\u000e\b\u0002\u0010[\u001a\b\u0012\u0004\u0012\u00020]0\\H\u0002J\u0010\u0010^\u001a\u00020.2\u0006\u0010_\u001a\u00020LH\u0016J\u001a\u0010`\u001a\u00020.2\u0006\u0010a\u001a\u00020\u00072\b\u0010K\u001a\u0004\u0018\u00010bH\u0016J0\u0010c\u001a\u00020J2\u0006\u0010d\u001a\u00020.2\u0006\u0010e\u001a\u00020\u00072\u0006\u0010f\u001a\u00020\u00072\u0006\u0010g\u001a\u00020\u00072\u0006\u0010h\u001a\u00020\u0007H\u0014J\u0010\u0010i\u001a\u00020.2\u0006\u0010K\u001a\u00020LH\u0017J\b\u0010j\u001a\u00020.H\u0016J\u0010\u0010k\u001a\u00020J2\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u0010\u0010l\u001a\u00020J2\u0006\u0010m\u001a\u00020.H\u0016J\b\u0010n\u001a\u00020JH\u0002J\b\u0010o\u001a\u00020.H\u0002J\u0006\u0010p\u001a\u00020JJ\u001a\u0010q\u001a\u00020J2\u0006\u0010r\u001a\u00020\u00072\b\u0010s\u001a\u0004\u0018\u00010SH\u0002J\u0017\u0010t\u001a\u00020J2\f\u0010u\u001a\b\u0012\u0004\u0012\u00020J0vH\u0082\bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR*\u0010\u0013\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\f\"\u0004\b\u001b\u0010\u000eR(\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010#\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\f\"\u0004\b%\u0010\u000eR$\u0010&\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\f\"\u0004\b(\u0010\u000eR$\u0010*\u001a\u00020\t2\u0006\u0010)\u001a\u00020\t@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\f\"\u0004\b,\u0010\u000eR\u001a\u0010-\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0014\u00103\u001a\u00020.8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b4\u00100R\u001a\u00105\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00100\"\u0004\b6\u00102R\u000e\u00107\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010<\u001a\u0004\u0018\u00010\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b=\u0010\u0015\"\u0004\b>\u0010\u0017R*\u0010?\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b@\u0010\u0015\"\u0004\bA\u0010\u0017R\u001a\u0010B\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u00100\"\u0004\bD\u00102R$\u0010F\u001a\u00020.2\u0006\u0010E\u001a\u00020.@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u00100\"\u0004\bH\u00102¨\u0006x"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup;", "Landroid/view/ViewGroup;", "Lcom/swmansion/gesturehandler/core/NativeViewGestureHandler$NativeViewGestureHandlerHook;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_backgroundColor", "", "radius", "", ViewProps.BORDER_BOTTOM_LEFT_RADIUS, "getBorderBottomLeftRadius", "()F", "setBorderBottomLeftRadius", "(F)V", ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, "getBorderBottomRightRadius", "setBorderBottomRightRadius", ViewProps.COLOR, ViewProps.BORDER_COLOR, "getBorderColor", "()Ljava/lang/Integer;", "setBorderColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "borderRadius", "getBorderRadius", "setBorderRadius", "style", "", "borderStyle", "getBorderStyle", "()Ljava/lang/String;", "setBorderStyle", "(Ljava/lang/String;)V", ViewProps.BORDER_TOP_LEFT_RADIUS, "getBorderTopLeftRadius", "setBorderTopLeftRadius", ViewProps.BORDER_TOP_RIGHT_RADIUS, "getBorderTopRightRadius", "setBorderTopRightRadius", "width", ViewProps.BORDER_WIDTH, "getBorderWidth", "setBorderWidth", "exclusive", "", "getExclusive", "()Z", "setExclusive", "(Z)V", "hasBorderRadii", "getHasBorderRadii", "isTouched", "setTouched", "lastAction", "lastEventTime", "", "needBackgroundUpdate", "receivedKeyEvent", "rippleColor", "getRippleColor", "setRippleColor", "rippleRadius", "getRippleRadius", "setRippleRadius", "useBorderlessDrawable", "getUseBorderlessDrawable", "setUseBorderlessDrawable", "useForeground", "useDrawableOnForeground", "getUseDrawableOnForeground", "setUseDrawableOnForeground", "afterGestureEnd", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "buildBorderRadii", "", "buildBorderStyle", "Landroid/graphics/PathEffect;", "canBegin", "createSelectableDrawable", "Landroid/graphics/drawable/Drawable;", "dispatchDrawableHotspotChanged", ViewHierarchyNode.JsonKeys.X, ViewHierarchyNode.JsonKeys.Y, "drawableHotspotChanged", "findGestureHandlerRootView", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootView;", "isChildTouched", ViewHierarchyNode.JsonKeys.CHILDREN, "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "onInterceptTouchEvent", "ev", "onKeyUp", "keyCode", "Landroid/view/KeyEvent;", ViewProps.ON_LAYOUT, "changed", "l", "t", "r", "b", "onTouchEvent", "performClick", "setBackgroundColor", "setPressed", "pressed", "tryFreeingResponder", "tryGrabbingResponder", "updateBackground", "updateBackgroundColor", ViewProps.BACKGROUND_COLOR, "selectable", "withBackgroundUpdate", "block", "Lkotlin/Function0;", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ButtonViewGroup extends ViewGroup implements NativeViewGestureHandler.NativeViewGestureHandlerHook {
        private static ButtonViewGroup soundResponder;
        private static ButtonViewGroup touchResponder;
        private int _backgroundColor;
        private float borderBottomLeftRadius;
        private float borderBottomRightRadius;
        private Integer borderColor;
        private float borderRadius;
        private String borderStyle;
        private float borderTopLeftRadius;
        private float borderTopRightRadius;
        private float borderWidth;
        private boolean exclusive;
        private boolean isTouched;
        private int lastAction;
        private long lastEventTime;
        private boolean needBackgroundUpdate;
        private boolean receivedKeyEvent;
        private Integer rippleColor;
        private Integer rippleRadius;
        private boolean useBorderlessDrawable;
        private boolean useDrawableOnForeground;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static TypedValue resolveOutValue = new TypedValue();
        private static View.OnClickListener dummyClickListener = new View.OnClickListener() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RNGestureHandlerButtonViewManager.ButtonViewGroup.dummyClickListener$lambda$14(view);
            }
        };

        /* JADX INFO: Access modifiers changed from: private */
        public static final void dummyClickListener$lambda$14(View view) {
        }

        private final boolean getHasBorderRadii() {
            return (this.borderRadius == 0.0f && this.borderTopLeftRadius == 0.0f && this.borderTopRightRadius == 0.0f && this.borderBottomLeftRadius == 0.0f && this.borderBottomRightRadius == 0.0f) ? false : true;
        }

        private final void tryFreeingResponder() {
            if (touchResponder == this) {
                touchResponder = null;
                soundResponder = this;
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDrawableHotspotChanged(float x, float y) {
        }

        public final float getBorderBottomLeftRadius() {
            return this.borderBottomLeftRadius;
        }

        public final float getBorderBottomRightRadius() {
            return this.borderBottomRightRadius;
        }

        public final Integer getBorderColor() {
            return this.borderColor;
        }

        public final float getBorderRadius() {
            return this.borderRadius;
        }

        public final String getBorderStyle() {
            return this.borderStyle;
        }

        public final float getBorderTopLeftRadius() {
            return this.borderTopLeftRadius;
        }

        public final float getBorderTopRightRadius() {
            return this.borderTopRightRadius;
        }

        public final float getBorderWidth() {
            return this.borderWidth;
        }

        public final boolean getExclusive() {
            return this.exclusive;
        }

        public final Integer getRippleColor() {
            return this.rippleColor;
        }

        public final Integer getRippleRadius() {
            return this.rippleRadius;
        }

        public final boolean getUseBorderlessDrawable() {
            return this.useBorderlessDrawable;
        }

        public final boolean getUseDrawableOnForeground() {
            return this.useDrawableOnForeground;
        }

        /* renamed from: isTouched, reason: from getter */
        public final boolean getIsTouched() {
            return this.isTouched;
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
        }

        @Override // android.view.View
        public void setBackgroundColor(int color) {
            this._backgroundColor = color;
            this.needBackgroundUpdate = true;
        }

        public final void setBorderColor(Integer num) {
            this.borderColor = num;
            this.needBackgroundUpdate = true;
        }

        public final void setBorderStyle(String str) {
            this.borderStyle = str;
            this.needBackgroundUpdate = true;
        }

        public final void setExclusive(boolean z) {
            this.exclusive = z;
        }

        public final void setRippleColor(Integer num) {
            this.rippleColor = num;
            this.needBackgroundUpdate = true;
        }

        public final void setRippleRadius(Integer num) {
            this.rippleRadius = num;
            this.needBackgroundUpdate = true;
        }

        public final void setTouched(boolean z) {
            this.isTouched = z;
        }

        public final void setUseBorderlessDrawable(boolean z) {
            this.useBorderlessDrawable = z;
        }

        public final void setUseDrawableOnForeground(boolean z) {
            this.useDrawableOnForeground = z;
            this.needBackgroundUpdate = true;
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public void handleEventBeforeActivation(MotionEvent motionEvent) {
            NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.handleEventBeforeActivation(this, motionEvent);
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public boolean shouldCancelRootViewGestureHandlerIfNecessary() {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.shouldCancelRootViewGestureHandlerIfNecessary(this);
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public boolean shouldRecognizeSimultaneously(GestureHandler<?> gestureHandler) {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.shouldRecognizeSimultaneously(this, gestureHandler);
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public boolean wantsToHandleEventBeforeActivation() {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.wantsToHandleEventBeforeActivation(this);
        }

        public ButtonViewGroup(Context context) {
            super(context);
            this.borderStyle = "solid";
            this.exclusive = true;
            this.lastEventTime = -1L;
            this.lastAction = -1;
            setOnClickListener(dummyClickListener);
            setClickable(true);
            setFocusable(true);
            this.needBackgroundUpdate = true;
            setClipChildren(false);
        }

        public final void setBorderRadius(float f) {
            this.borderRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final void setBorderTopLeftRadius(float f) {
            this.borderTopLeftRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final void setBorderTopRightRadius(float f) {
            this.borderTopRightRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final void setBorderBottomLeftRadius(float f) {
            this.borderBottomLeftRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final void setBorderBottomRightRadius(float f) {
            this.borderBottomRightRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final void setBorderWidth(float f) {
            this.borderWidth = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        private final void withBackgroundUpdate(Function0<Unit> block) {
            block.invoke();
            this.needBackgroundUpdate = true;
        }

        private final PathEffect buildBorderStyle() {
            String str = this.borderStyle;
            if (Intrinsics.areEqual(str, "dotted")) {
                float f = this.borderWidth;
                return new DashPathEffect(new float[]{f, f, f, f}, 0.0f);
            }
            if (!Intrinsics.areEqual(str, "dashed")) {
                return null;
            }
            float f2 = this.borderWidth;
            float f3 = 3;
            return new DashPathEffect(new float[]{f2 * f3, f2 * f3, f2 * f3, f2 * f3}, 0.0f);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            Intrinsics.checkNotNullParameter(ev, "ev");
            if (super.onInterceptTouchEvent(ev)) {
                return true;
            }
            onTouchEvent(ev);
            return isPressed();
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            if (event.getAction() == 3) {
                tryFreeingResponder();
                return super.onTouchEvent(event);
            }
            long eventTime = event.getEventTime();
            int action = event.getAction();
            if (this.lastEventTime == eventTime && this.lastAction == action) {
                return false;
            }
            this.lastEventTime = eventTime;
            this.lastAction = action;
            return super.onTouchEvent(event);
        }

        private final void updateBackgroundColor(int backgroundColor, Drawable selectable) {
            PaintDrawable paintDrawable = new PaintDrawable(backgroundColor);
            PaintDrawable paintDrawable2 = new PaintDrawable(0);
            if (getHasBorderRadii()) {
                paintDrawable.setCornerRadii(buildBorderRadii());
                paintDrawable2.setCornerRadii(buildBorderRadii());
            }
            if (this.borderWidth > 0.0f) {
                Paint paint = paintDrawable2.getPaint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(this.borderWidth);
                Integer num = this.borderColor;
                paint.setColor(num != null ? num.intValue() : ViewCompat.MEASURED_STATE_MASK);
                paint.setPathEffect(buildBorderStyle());
            }
            setBackground(new LayerDrawable(selectable != null ? new Drawable[]{paintDrawable, selectable, paintDrawable2} : new PaintDrawable[]{paintDrawable, paintDrawable2}));
        }

        public final void updateBackground() {
            if (this.needBackgroundUpdate) {
                this.needBackgroundUpdate = false;
                if (this._backgroundColor == 0) {
                    setBackground(null);
                }
                setForeground(null);
                Drawable createSelectableDrawable = createSelectableDrawable();
                if (getHasBorderRadii() && (createSelectableDrawable instanceof RippleDrawable)) {
                    PaintDrawable paintDrawable = new PaintDrawable(-1);
                    paintDrawable.setCornerRadii(buildBorderRadii());
                    ((RippleDrawable) createSelectableDrawable).setDrawableByLayerId(R.id.mask, paintDrawable);
                }
                if (this.useDrawableOnForeground) {
                    setForeground(createSelectableDrawable);
                    int i = this._backgroundColor;
                    if (i != 0) {
                        updateBackgroundColor(i, null);
                        return;
                    }
                    return;
                }
                int i2 = this._backgroundColor;
                if (i2 == 0 && this.rippleColor == null) {
                    setBackground(createSelectableDrawable);
                } else {
                    updateBackgroundColor(i2, createSelectableDrawable);
                }
            }
        }

        private final Drawable createSelectableDrawable() {
            ColorStateList colorStateList;
            Integer num = this.rippleColor;
            if (num != null && num.intValue() == 0) {
                return null;
            }
            int[][] iArr = {new int[]{R.attr.state_enabled}};
            Integer num2 = this.rippleRadius;
            Integer num3 = this.rippleColor;
            if (num3 != null) {
                Intrinsics.checkNotNull(num3);
                colorStateList = new ColorStateList(iArr, new int[]{num3.intValue()});
            } else {
                getContext().getTheme().resolveAttribute(R.attr.colorControlHighlight, resolveOutValue, true);
                colorStateList = new ColorStateList(iArr, new int[]{resolveOutValue.data});
            }
            RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, null, this.useBorderlessDrawable ? null : new ShapeDrawable(new RectShape()));
            if (num2 != null) {
                rippleDrawable.setRadius((int) PixelUtil.toPixelFromDIP(num2.intValue()));
            }
            return rippleDrawable;
        }

        @Override // android.view.View
        public void drawableHotspotChanged(float x, float y) {
            ButtonViewGroup buttonViewGroup = touchResponder;
            if (buttonViewGroup == null || buttonViewGroup == this) {
                super.drawableHotspotChanged(x, y);
            }
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public boolean canBegin() {
            boolean tryGrabbingResponder = tryGrabbingResponder();
            if (tryGrabbingResponder) {
                this.isTouched = true;
            }
            return tryGrabbingResponder;
        }

        @Override // com.swmansion.gesturehandler.core.NativeViewGestureHandler.NativeViewGestureHandlerHook
        public void afterGestureEnd(MotionEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            tryFreeingResponder();
            this.isTouched = false;
        }

        private final boolean tryGrabbingResponder() {
            if (isChildTouched$default(this, null, 1, null)) {
                return false;
            }
            ButtonViewGroup buttonViewGroup = touchResponder;
            if (buttonViewGroup == null) {
                touchResponder = this;
                return true;
            }
            if (this.exclusive) {
                if (buttonViewGroup == this) {
                    return true;
                }
            } else if (buttonViewGroup == null || !buttonViewGroup.exclusive) {
                return true;
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        static /* synthetic */ boolean isChildTouched$default(ButtonViewGroup buttonViewGroup, Sequence sequence, int i, Object obj) {
            if ((i & 1) != 0) {
                sequence = ViewGroupKt.getChildren(buttonViewGroup);
            }
            return buttonViewGroup.isChildTouched(sequence);
        }

        private final boolean isChildTouched(Sequence<? extends View> children) {
            for (View view : children) {
                if (view instanceof ButtonViewGroup) {
                    ButtonViewGroup buttonViewGroup = (ButtonViewGroup) view;
                    if (buttonViewGroup.isTouched || buttonViewGroup.isPressed()) {
                        return true;
                    }
                }
                if ((view instanceof ViewGroup) && isChildTouched(ViewGroupKt.getChildren((ViewGroup) view))) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyUp(int keyCode, KeyEvent event) {
            this.receivedKeyEvent = true;
            return super.onKeyUp(keyCode, event);
        }

        @Override // android.view.View
        public boolean performClick() {
            if (isChildTouched$default(this, null, 1, null)) {
                return false;
            }
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            if (ExtensionsKt.isScreenReaderOn(context)) {
                RNGestureHandlerRootView findGestureHandlerRootView = findGestureHandlerRootView();
                if (findGestureHandlerRootView != null) {
                    findGestureHandlerRootView.activateNativeHandlers(this);
                }
            } else if (this.receivedKeyEvent) {
                RNGestureHandlerRootView findGestureHandlerRootView2 = findGestureHandlerRootView();
                if (findGestureHandlerRootView2 != null) {
                    findGestureHandlerRootView2.activateNativeHandlers(this);
                }
                this.receivedKeyEvent = false;
            }
            if (soundResponder != this) {
                return false;
            }
            tryFreeingResponder();
            soundResponder = null;
            return super.performClick();
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0016, code lost:
        
            if (r0.exclusive == true) goto L16;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x001e, code lost:
        
            if (isChildTouched$default(r3, null, 1, null) != false) goto L16;
         */
        @Override // android.view.View
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void setPressed(boolean r4) {
            /*
                r3 = this;
                if (r4 == 0) goto La
                boolean r0 = r3.tryGrabbingResponder()
                if (r0 == 0) goto La
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.soundResponder = r3
            La:
                boolean r0 = r3.exclusive
                r1 = 0
                if (r0 != 0) goto L21
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup r0 = com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.touchResponder
                r2 = 1
                if (r0 == 0) goto L19
                boolean r0 = r0.exclusive
                if (r0 != r2) goto L19
                goto L21
            L19:
                r0 = 0
                boolean r0 = isChildTouched$default(r3, r0, r2, r0)
                if (r0 != 0) goto L21
                goto L22
            L21:
                r2 = r1
            L22:
                if (r4 == 0) goto L2a
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup r0 = com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.touchResponder
                if (r0 == r3) goto L2a
                if (r2 == 0) goto L2f
            L2a:
                r3.isTouched = r4
                super.setPressed(r4)
            L2f:
                if (r4 != 0) goto L37
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup r4 = com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.touchResponder
                if (r4 != r3) goto L37
                r3.isTouched = r1
            L37:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.setPressed(boolean):void");
        }

        private final RNGestureHandlerRootView findGestureHandlerRootView() {
            RNGestureHandlerRootView rNGestureHandlerRootView = null;
            for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
                if (parent instanceof RNGestureHandlerRootView) {
                    rNGestureHandlerRootView = (RNGestureHandlerRootView) parent;
                }
            }
            return rNGestureHandlerRootView;
        }

        /* compiled from: RNGestureHandlerButtonViewManager.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014¨\u0006\u0018"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup$Companion;", "", "()V", "dummyClickListener", "Landroid/view/View$OnClickListener;", "getDummyClickListener", "()Landroid/view/View$OnClickListener;", "setDummyClickListener", "(Landroid/view/View$OnClickListener;)V", "resolveOutValue", "Landroid/util/TypedValue;", "getResolveOutValue", "()Landroid/util/TypedValue;", "setResolveOutValue", "(Landroid/util/TypedValue;)V", "soundResponder", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup;", "getSoundResponder", "()Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup;", "setSoundResponder", "(Lcom/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup;)V", "touchResponder", "getTouchResponder", "setTouchResponder", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final TypedValue getResolveOutValue() {
                return ButtonViewGroup.resolveOutValue;
            }

            public final void setResolveOutValue(TypedValue typedValue) {
                Intrinsics.checkNotNullParameter(typedValue, "<set-?>");
                ButtonViewGroup.resolveOutValue = typedValue;
            }

            public final ButtonViewGroup getTouchResponder() {
                return ButtonViewGroup.touchResponder;
            }

            public final void setTouchResponder(ButtonViewGroup buttonViewGroup) {
                ButtonViewGroup.touchResponder = buttonViewGroup;
            }

            public final ButtonViewGroup getSoundResponder() {
                return ButtonViewGroup.soundResponder;
            }

            public final void setSoundResponder(ButtonViewGroup buttonViewGroup) {
                ButtonViewGroup.soundResponder = buttonViewGroup;
            }

            public final View.OnClickListener getDummyClickListener() {
                return ButtonViewGroup.dummyClickListener;
            }

            public final void setDummyClickListener(View.OnClickListener onClickListener) {
                Intrinsics.checkNotNullParameter(onClickListener, "<set-?>");
                ButtonViewGroup.dummyClickListener = onClickListener;
            }
        }

        private final float[] buildBorderRadii() {
            float f = this.borderTopLeftRadius;
            float f2 = this.borderTopRightRadius;
            float f3 = this.borderBottomRightRadius;
            float f4 = this.borderBottomLeftRadius;
            float[] fArr = {f, f, f2, f2, f3, f3, f4, f4};
            ArrayList arrayList = new ArrayList(8);
            for (int i = 0; i < 8; i++) {
                float f5 = fArr[i];
                if (f5 == 0.0f) {
                    f5 = this.borderRadius;
                }
                arrayList.add(Float.valueOf(f5));
            }
            return CollectionsKt.toFloatArray(arrayList);
        }
    }
}

package com.swmansion.rnscreens;

import android.content.Context;
import android.graphics.Paint;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import com.caverock.androidsvg.SVGParser;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.events.HeaderHeightChangeEvent;
import io.sentry.protocol.Request;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Screen.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\u0005yz{|}B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020[\u0012\u0004\u0012\u00020[0ZH\u0002J\u000e\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u000206J\u0016\u0010_\u001a\u00020]2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020a0`H\u0014J\u0016\u0010b\u001a\u00020]2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020a0`H\u0014J\u0010\u0010c\u001a\u00020\u001f2\u0006\u0010d\u001a\u00020eH\u0002J\u0010\u0010f\u001a\u00020]2\u0006\u0010g\u001a\u00020[H\u0002J0\u0010h\u001a\u00020]2\u0006\u0010i\u001a\u00020\u001f2\u0006\u0010j\u001a\u0002062\u0006\u0010k\u001a\u0002062\u0006\u0010l\u001a\u0002062\u0006\u0010m\u001a\u000206H\u0014J\u000e\u0010n\u001a\u00020]2\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010o\u001a\u00020]2\u0006\u0010p\u001a\u0002062\b\u0010q\u001a\u0004\u0018\u00010rH\u0016J\u0010\u0010s\u001a\u00020]2\b\u0010B\u001a\u0004\u0018\u00010TJ\u000e\u0010t\u001a\u00020]2\u0006\u0010u\u001a\u00020\u001fJ\u0018\u0010v\u001a\u00020]2\u0006\u0010w\u001a\u0002062\u0006\u0010x\u001a\u000206H\u0002R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010 \"\u0004\b!\u0010\"R*\u0010$\u001a\u0004\u0018\u00010\u001f2\b\u0010#\u001a\u0004\u0018\u00010\u001f@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010(\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010)\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010(\u001a\u0004\b)\u0010%\"\u0004\b*\u0010'R*\u0010,\u001a\u0004\u0018\u00010\u001f2\b\u0010+\u001a\u0004\u0018\u00010\u001f@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010(\u001a\u0004\b,\u0010%\"\u0004\b-\u0010'R*\u0010/\u001a\u0004\u0018\u00010\u001f2\b\u0010.\u001a\u0004\u0018\u00010\u001f@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010(\u001a\u0004\b/\u0010%\"\u0004\b0\u0010'R\u000e\u00101\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010 \"\u0004\b4\u0010\"R*\u00105\u001a\u0004\u0018\u0001062\b\u00105\u001a\u0004\u0018\u000106@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010;\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010<\u001a\u00020=X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010B\u001a\u0004\u0018\u0001062\b\u0010\u0005\u001a\u0004\u0018\u000106@BX\u0086\u000e¢\u0006\n\n\u0002\u0010;\u001a\u0004\bC\u00108R\u001a\u0010D\u001a\u00020EX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001a\u0010J\u001a\u00020KX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR*\u0010P\u001a\u0004\u0018\u0001062\b\u0010P\u001a\u0004\u0018\u000106@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010;\u001a\u0004\bQ\u00108\"\u0004\bR\u0010:R(\u0010S\u001a\u0004\u0018\u00010T2\b\u0010S\u001a\u0004\u0018\u00010T@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010V\"\u0004\bW\u0010X¨\u0006~"}, d2 = {"Lcom/swmansion/rnscreens/Screen;", "Lcom/swmansion/rnscreens/FabricEnabledViewGroup;", "context", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "<set-?>", "Lcom/swmansion/rnscreens/Screen$ActivityState;", "activityState", "getActivityState", "()Lcom/swmansion/rnscreens/Screen$ActivityState;", "container", "Lcom/swmansion/rnscreens/ScreenContainer;", "getContainer", "()Lcom/swmansion/rnscreens/ScreenContainer;", "setContainer", "(Lcom/swmansion/rnscreens/ScreenContainer;)V", Request.JsonKeys.FRAGMENT, "Landroidx/fragment/app/Fragment;", "getFragment", "()Landroidx/fragment/app/Fragment;", "fragmentWrapper", "Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "getFragmentWrapper", "()Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "setFragmentWrapper", "(Lcom/swmansion/rnscreens/ScreenFragmentWrapper;)V", "headerConfig", "Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "getHeaderConfig", "()Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "isGestureEnabled", "", "()Z", "setGestureEnabled", "(Z)V", "navigationBarHidden", "isNavigationBarHidden", "()Ljava/lang/Boolean;", "setNavigationBarHidden", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "isStatusBarAnimated", "setStatusBarAnimated", "statusBarHidden", "isStatusBarHidden", "setStatusBarHidden", "statusBarTranslucent", "isStatusBarTranslucent", "setStatusBarTranslucent", "isTransitioning", "nativeBackButtonDismissalEnabled", "getNativeBackButtonDismissalEnabled", "setNativeBackButtonDismissalEnabled", "navigationBarColor", "", "getNavigationBarColor", "()Ljava/lang/Integer;", "setNavigationBarColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "replaceAnimation", "Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "getReplaceAnimation", "()Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "setReplaceAnimation", "(Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;)V", "screenOrientation", "getScreenOrientation", "stackAnimation", "Lcom/swmansion/rnscreens/Screen$StackAnimation;", "getStackAnimation", "()Lcom/swmansion/rnscreens/Screen$StackAnimation;", "setStackAnimation", "(Lcom/swmansion/rnscreens/Screen$StackAnimation;)V", "stackPresentation", "Lcom/swmansion/rnscreens/Screen$StackPresentation;", "getStackPresentation", "()Lcom/swmansion/rnscreens/Screen$StackPresentation;", "setStackPresentation", "(Lcom/swmansion/rnscreens/Screen$StackPresentation;)V", "statusBarColor", "getStatusBarColor", "setStatusBarColor", "statusBarStyle", "", "getStatusBarStyle", "()Ljava/lang/String;", "setStatusBarStyle", "(Ljava/lang/String;)V", "calculateHeaderHeight", "Lkotlin/Pair;", "", "changeAccessibilityMode", "", "mode", "dispatchRestoreInstanceState", "Landroid/util/SparseArray;", "Landroid/os/Parcelable;", "dispatchSaveInstanceState", "hasWebView", "viewGroup", "Landroid/view/ViewGroup;", "notifyHeaderHeightChange", "headerHeight", ViewProps.ON_LAYOUT, "changed", "l", "t", "r", "b", "setActivityState", "setLayerType", "layerType", "paint", "Landroid/graphics/Paint;", "setScreenOrientation", "setTransitioning", "transitioning", "updateScreenSizePaper", "width", "height", "ActivityState", "ReplaceAnimation", "StackAnimation", "StackPresentation", "WindowTraits", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Screen extends FabricEnabledViewGroup {
    private ActivityState activityState;
    private ScreenContainer container;
    private ScreenFragmentWrapper fragmentWrapper;
    private boolean isGestureEnabled;
    private Boolean isNavigationBarHidden;
    private Boolean isStatusBarAnimated;
    private Boolean isStatusBarHidden;
    private Boolean isStatusBarTranslucent;
    private boolean isTransitioning;
    private boolean nativeBackButtonDismissalEnabled;
    private Integer navigationBarColor;
    private ReplaceAnimation replaceAnimation;
    private Integer screenOrientation;
    private StackAnimation stackAnimation;
    private StackPresentation stackPresentation;
    private Integer statusBarColor;
    private String statusBarStyle;

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        Intrinsics.checkNotNullParameter(container, "container");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        Intrinsics.checkNotNullParameter(container, "container");
    }

    public final ActivityState getActivityState() {
        return this.activityState;
    }

    public final ScreenContainer getContainer() {
        return this.container;
    }

    public final ScreenFragmentWrapper getFragmentWrapper() {
        return this.fragmentWrapper;
    }

    public final boolean getNativeBackButtonDismissalEnabled() {
        return this.nativeBackButtonDismissalEnabled;
    }

    public final Integer getNavigationBarColor() {
        return this.navigationBarColor;
    }

    public final ReplaceAnimation getReplaceAnimation() {
        return this.replaceAnimation;
    }

    public final Integer getScreenOrientation() {
        return this.screenOrientation;
    }

    public final StackAnimation getStackAnimation() {
        return this.stackAnimation;
    }

    public final StackPresentation getStackPresentation() {
        return this.stackPresentation;
    }

    public final Integer getStatusBarColor() {
        return this.statusBarColor;
    }

    public final String getStatusBarStyle() {
        return this.statusBarStyle;
    }

    /* renamed from: isGestureEnabled, reason: from getter */
    public final boolean getIsGestureEnabled() {
        return this.isGestureEnabled;
    }

    /* renamed from: isNavigationBarHidden, reason: from getter */
    public final Boolean getIsNavigationBarHidden() {
        return this.isNavigationBarHidden;
    }

    /* renamed from: isStatusBarAnimated, reason: from getter */
    public final Boolean getIsStatusBarAnimated() {
        return this.isStatusBarAnimated;
    }

    /* renamed from: isStatusBarHidden, reason: from getter */
    public final Boolean getIsStatusBarHidden() {
        return this.isStatusBarHidden;
    }

    /* renamed from: isStatusBarTranslucent, reason: from getter */
    public final Boolean getIsStatusBarTranslucent() {
        return this.isStatusBarTranslucent;
    }

    public final void setContainer(ScreenContainer screenContainer) {
        this.container = screenContainer;
    }

    public final void setFragmentWrapper(ScreenFragmentWrapper screenFragmentWrapper) {
        this.fragmentWrapper = screenFragmentWrapper;
    }

    public final void setGestureEnabled(boolean z) {
        this.isGestureEnabled = z;
    }

    @Override // android.view.View
    public void setLayerType(int layerType, Paint paint) {
    }

    public final void setNativeBackButtonDismissalEnabled(boolean z) {
        this.nativeBackButtonDismissalEnabled = z;
    }

    public final void setReplaceAnimation(ReplaceAnimation replaceAnimation) {
        Intrinsics.checkNotNullParameter(replaceAnimation, "<set-?>");
        this.replaceAnimation = replaceAnimation;
    }

    public final void setStackAnimation(StackAnimation stackAnimation) {
        Intrinsics.checkNotNullParameter(stackAnimation, "<set-?>");
        this.stackAnimation = stackAnimation;
    }

    public final void setStackPresentation(StackPresentation stackPresentation) {
        Intrinsics.checkNotNullParameter(stackPresentation, "<set-?>");
        this.stackPresentation = stackPresentation;
    }

    public final void setStatusBarAnimated(Boolean bool) {
        this.isStatusBarAnimated = bool;
    }

    public Screen(ReactContext reactContext) {
        super(reactContext);
        this.stackPresentation = StackPresentation.PUSH;
        this.replaceAnimation = ReplaceAnimation.POP;
        this.stackAnimation = StackAnimation.DEFAULT;
        this.isGestureEnabled = true;
        setLayoutParams(new WindowManager.LayoutParams(2));
        this.nativeBackButtonDismissalEnabled = true;
    }

    public final Fragment getFragment() {
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            return screenFragmentWrapper.getFragment();
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if ((this.container instanceof ScreenStack) && changed) {
            Pair<Double, Double> calculateHeaderHeight = calculateHeaderHeight();
            double doubleValue = calculateHeaderHeight.getFirst().doubleValue() + calculateHeaderHeight.getSecond().doubleValue();
            updateScreenSizePaper(r - l, b - t);
            notifyHeaderHeightChange(doubleValue);
        }
    }

    private final void updateScreenSizePaper(final int width, final int height) {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        final ReactContext reactContext = (ReactContext) context;
        reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(this, width, height) { // from class: com.swmansion.rnscreens.Screen$updateScreenSizePaper$1
            final /* synthetic */ int $height;
            final /* synthetic */ int $width;
            final /* synthetic */ Screen this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(ReactContext.this);
                this.this$0 = this;
                this.$width = width;
                this.$height = height;
            }

            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                UIManagerModule uIManagerModule = (UIManagerModule) ReactContext.this.getNativeModule(UIManagerModule.class);
                if (uIManagerModule != null) {
                    uIManagerModule.updateNodeSize(this.this$0.getId(), this.$width, this.$height);
                }
            }
        });
    }

    public final ScreenStackHeaderConfig getHeaderConfig() {
        View view;
        Iterator<View> it = ViewGroupKt.getChildren(this).iterator();
        while (true) {
            if (!it.hasNext()) {
                view = null;
                break;
            }
            view = it.next();
            if (view instanceof ScreenStackHeaderConfig) {
                break;
            }
        }
        if (view instanceof ScreenStackHeaderConfig) {
            return (ScreenStackHeaderConfig) view;
        }
        return null;
    }

    public final void setTransitioning(boolean transitioning) {
        if (this.isTransitioning == transitioning) {
            return;
        }
        this.isTransitioning = transitioning;
        boolean hasWebView = hasWebView(this);
        if (!hasWebView || getLayerType() == 2) {
            super.setLayerType((!transitioning || hasWebView) ? 0 : 2, null);
        }
    }

    private final boolean hasWebView(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof WebView) {
                return true;
            }
            if ((childAt instanceof ViewGroup) && hasWebView((ViewGroup) childAt)) {
                return true;
            }
        }
        return false;
    }

    public final void setActivityState(ActivityState activityState) {
        Intrinsics.checkNotNullParameter(activityState, "activityState");
        if (activityState == this.activityState) {
            return;
        }
        this.activityState = activityState;
        ScreenContainer screenContainer = this.container;
        if (screenContainer != null) {
            screenContainer.notifyChildUpdate();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void setScreenOrientation(String screenOrientation) {
        int i;
        if (screenOrientation == null) {
            this.screenOrientation = null;
            return;
        }
        ScreenWindowTraits.INSTANCE.applyDidSetOrientation$react_native_screens_release();
        switch (screenOrientation.hashCode()) {
            case -1894896954:
                if (screenOrientation.equals("portrait_down")) {
                    i = 9;
                    break;
                }
                i = -1;
                break;
            case 96673:
                if (screenOrientation.equals(SVGParser.XML_STYLESHEET_ATTR_MEDIA_ALL)) {
                    i = 10;
                    break;
                }
                i = -1;
                break;
            case 729267099:
                if (screenOrientation.equals("portrait")) {
                    i = 7;
                    break;
                }
                i = -1;
                break;
            case 1430647483:
                if (screenOrientation.equals("landscape")) {
                    i = 6;
                    break;
                }
                i = -1;
                break;
            case 1651658175:
                if (screenOrientation.equals("portrait_up")) {
                    i = 1;
                    break;
                }
                i = -1;
                break;
            case 1730732811:
                if (screenOrientation.equals("landscape_left")) {
                    i = 8;
                    break;
                }
                i = -1;
                break;
            case 2118770584:
                if (screenOrientation.equals("landscape_right")) {
                    i = 0;
                    break;
                }
                i = -1;
                break;
            default:
                i = -1;
                break;
        }
        this.screenOrientation = i;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setOrientation$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    public final void changeAccessibilityMode(int mode) {
        setImportantForAccessibility(mode);
        ScreenStackHeaderConfig headerConfig = getHeaderConfig();
        CustomToolbar toolbar = headerConfig != null ? headerConfig.getToolbar() : null;
        if (toolbar == null) {
            return;
        }
        toolbar.setImportantForAccessibility(mode);
    }

    public final void setStatusBarStyle(String str) {
        if (str != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.statusBarStyle = str;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setStyle$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity(), screenFragmentWrapper.tryGetContext());
        }
    }

    public final void setStatusBarHidden(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.isStatusBarHidden = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setHidden$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    public final void setStatusBarTranslucent(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.isStatusBarTranslucent = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setTranslucent$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity(), screenFragmentWrapper.tryGetContext());
        }
    }

    public final void setStatusBarColor(Integer num) {
        if (num != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.statusBarColor = num;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setColor$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity(), screenFragmentWrapper.tryGetContext());
        }
    }

    public final void setNavigationBarColor(Integer num) {
        if (num != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.navigationBarColor = num;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setNavigationBarColor$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    public final void setNavigationBarHidden(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.isNavigationBarHidden = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setNavigationBarHidden$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    private final Pair<Double, Double> calculateHeaderHeight() {
        ScreenStackHeaderConfig headerConfig;
        ScreenStackHeaderConfig headerConfig2;
        TypedValue typedValue = new TypedValue();
        boolean resolveAttribute = getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true);
        Integer valueOf = Integer.valueOf(TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics()));
        valueOf.intValue();
        Integer num = null;
        if (!resolveAttribute || (((headerConfig = getHeaderConfig()) != null && headerConfig.getIsHeaderHidden()) || ((headerConfig2 = getHeaderConfig()) != null && headerConfig2.getIsHeaderTranslucent()))) {
            valueOf = null;
        }
        double dIPFromPixel = valueOf != null ? PixelUtil.toDIPFromPixel(valueOf.intValue()) : 0.0d;
        Integer valueOf2 = Integer.valueOf(getContext().getResources().getIdentifier("status_bar_height", "dimen", "android"));
        if (valueOf2.intValue() > 0 && !Intrinsics.areEqual((Object) this.isStatusBarHidden, (Object) true) && dIPFromPixel > 0.0d) {
            num = valueOf2;
        }
        return TuplesKt.to(Double.valueOf(dIPFromPixel), Double.valueOf(num != null ? PixelUtil.toDIPFromPixel(getContext().getResources().getDimensionPixelSize(num.intValue())) : 0.0d));
    }

    private final void notifyHeaderHeightChange(double headerHeight) {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        ReactContext reactContext = (ReactContext) context;
        int surfaceId = UIManagerHelper.getSurfaceId(reactContext);
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new HeaderHeightChangeEvent(surfaceId, getId(), headerHeight));
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/Screen$StackPresentation;", "", "(Ljava/lang/String;I)V", "PUSH", "MODAL", "TRANSPARENT_MODAL", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class StackPresentation {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ StackPresentation[] $VALUES;
        public static final StackPresentation PUSH = new StackPresentation("PUSH", 0);
        public static final StackPresentation MODAL = new StackPresentation("MODAL", 1);
        public static final StackPresentation TRANSPARENT_MODAL = new StackPresentation("TRANSPARENT_MODAL", 2);

        private static final /* synthetic */ StackPresentation[] $values() {
            return new StackPresentation[]{PUSH, MODAL, TRANSPARENT_MODAL};
        }

        public static EnumEntries<StackPresentation> getEntries() {
            return $ENTRIES;
        }

        public static StackPresentation valueOf(String str) {
            return (StackPresentation) Enum.valueOf(StackPresentation.class, str);
        }

        public static StackPresentation[] values() {
            return (StackPresentation[]) $VALUES.clone();
        }

        private StackPresentation(String str, int i) {
        }

        static {
            StackPresentation[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/swmansion/rnscreens/Screen$StackAnimation;", "", "(Ljava/lang/String;I)V", "DEFAULT", "NONE", "FADE", "SLIDE_FROM_BOTTOM", "SLIDE_FROM_RIGHT", "SLIDE_FROM_LEFT", "FADE_FROM_BOTTOM", "IOS", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class StackAnimation {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ StackAnimation[] $VALUES;
        public static final StackAnimation DEFAULT = new StackAnimation("DEFAULT", 0);
        public static final StackAnimation NONE = new StackAnimation("NONE", 1);
        public static final StackAnimation FADE = new StackAnimation("FADE", 2);
        public static final StackAnimation SLIDE_FROM_BOTTOM = new StackAnimation("SLIDE_FROM_BOTTOM", 3);
        public static final StackAnimation SLIDE_FROM_RIGHT = new StackAnimation("SLIDE_FROM_RIGHT", 4);
        public static final StackAnimation SLIDE_FROM_LEFT = new StackAnimation("SLIDE_FROM_LEFT", 5);
        public static final StackAnimation FADE_FROM_BOTTOM = new StackAnimation("FADE_FROM_BOTTOM", 6);
        public static final StackAnimation IOS = new StackAnimation("IOS", 7);

        private static final /* synthetic */ StackAnimation[] $values() {
            return new StackAnimation[]{DEFAULT, NONE, FADE, SLIDE_FROM_BOTTOM, SLIDE_FROM_RIGHT, SLIDE_FROM_LEFT, FADE_FROM_BOTTOM, IOS};
        }

        public static EnumEntries<StackAnimation> getEntries() {
            return $ENTRIES;
        }

        public static StackAnimation valueOf(String str) {
            return (StackAnimation) Enum.valueOf(StackAnimation.class, str);
        }

        public static StackAnimation[] values() {
            return (StackAnimation[]) $VALUES.clone();
        }

        private StackAnimation(String str, int i) {
        }

        static {
            StackAnimation[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "", "(Ljava/lang/String;I)V", "PUSH", "POP", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ReplaceAnimation {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ReplaceAnimation[] $VALUES;
        public static final ReplaceAnimation PUSH = new ReplaceAnimation("PUSH", 0);
        public static final ReplaceAnimation POP = new ReplaceAnimation("POP", 1);

        private static final /* synthetic */ ReplaceAnimation[] $values() {
            return new ReplaceAnimation[]{PUSH, POP};
        }

        public static EnumEntries<ReplaceAnimation> getEntries() {
            return $ENTRIES;
        }

        public static ReplaceAnimation valueOf(String str) {
            return (ReplaceAnimation) Enum.valueOf(ReplaceAnimation.class, str);
        }

        public static ReplaceAnimation[] values() {
            return (ReplaceAnimation[]) $VALUES.clone();
        }

        private ReplaceAnimation(String str, int i) {
        }

        static {
            ReplaceAnimation[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/Screen$ActivityState;", "", "(Ljava/lang/String;I)V", "INACTIVE", "TRANSITIONING_OR_BELOW_TOP", "ON_TOP", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ActivityState {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ActivityState[] $VALUES;
        public static final ActivityState INACTIVE = new ActivityState("INACTIVE", 0);
        public static final ActivityState TRANSITIONING_OR_BELOW_TOP = new ActivityState("TRANSITIONING_OR_BELOW_TOP", 1);
        public static final ActivityState ON_TOP = new ActivityState("ON_TOP", 2);

        private static final /* synthetic */ ActivityState[] $values() {
            return new ActivityState[]{INACTIVE, TRANSITIONING_OR_BELOW_TOP, ON_TOP};
        }

        public static EnumEntries<ActivityState> getEntries() {
            return $ENTRIES;
        }

        public static ActivityState valueOf(String str) {
            return (ActivityState) Enum.valueOf(ActivityState.class, str);
        }

        public static ActivityState[] values() {
            return (ActivityState[]) $VALUES.clone();
        }

        private ActivityState(String str, int i) {
        }

        static {
            ActivityState[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/swmansion/rnscreens/Screen$WindowTraits;", "", "(Ljava/lang/String;I)V", "ORIENTATION", "COLOR", "STYLE", "TRANSLUCENT", "HIDDEN", "ANIMATED", "NAVIGATION_BAR_COLOR", "NAVIGATION_BAR_HIDDEN", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class WindowTraits {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ WindowTraits[] $VALUES;
        public static final WindowTraits ORIENTATION = new WindowTraits("ORIENTATION", 0);
        public static final WindowTraits COLOR = new WindowTraits("COLOR", 1);
        public static final WindowTraits STYLE = new WindowTraits("STYLE", 2);
        public static final WindowTraits TRANSLUCENT = new WindowTraits("TRANSLUCENT", 3);
        public static final WindowTraits HIDDEN = new WindowTraits("HIDDEN", 4);
        public static final WindowTraits ANIMATED = new WindowTraits("ANIMATED", 5);
        public static final WindowTraits NAVIGATION_BAR_COLOR = new WindowTraits("NAVIGATION_BAR_COLOR", 6);
        public static final WindowTraits NAVIGATION_BAR_HIDDEN = new WindowTraits("NAVIGATION_BAR_HIDDEN", 7);

        private static final /* synthetic */ WindowTraits[] $values() {
            return new WindowTraits[]{ORIENTATION, COLOR, STYLE, TRANSLUCENT, HIDDEN, ANIMATED, NAVIGATION_BAR_COLOR, NAVIGATION_BAR_HIDDEN};
        }

        public static EnumEntries<WindowTraits> getEntries() {
            return $ENTRIES;
        }

        public static WindowTraits valueOf(String str) {
            return (WindowTraits) Enum.valueOf(WindowTraits.class, str);
        }

        public static WindowTraits[] values() {
            return (WindowTraits[]) $VALUES.clone();
        }

        private WindowTraits(String str, int i) {
        }

        static {
            WindowTraits[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }
}

package com.swmansion.rnscreens;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.events.StackFinishTransitioningEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: ScreenStack.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 F2\u00020\u0001:\u0002FGB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001dH\u0014J\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0007J\u0010\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020,H\u0014J\b\u0010-\u001a\u00020(H\u0002J\b\u0010.\u001a\u00020(H\u0002J \u0010/\u001a\u00020\u00122\u0006\u0010+\u001a\u00020,2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0014J\u0010\u00104\u001a\u00020(2\u0006\u00105\u001a\u000201H\u0016J\u0012\u00106\u001a\u00020\u00122\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u00109\u001a\u00020(H\u0014J\f\u0010:\u001a\u00060\nR\u00020\u0000H\u0002J\b\u0010;\u001a\u00020(H\u0016J\u0006\u0010<\u001a\u00020(J\u0014\u0010=\u001a\u00020(2\n\u0010>\u001a\u00060\nR\u00020\u0000H\u0002J\b\u0010?\u001a\u00020(H\u0016J\u0010\u0010@\u001a\u00020(2\u0006\u0010A\u001a\u00020\u0019H\u0016J\u0010\u0010B\u001a\u00020(2\u0006\u00105\u001a\u000201H\u0016J\u0010\u0010C\u001a\u00020(2\u0006\u00105\u001a\u000201H\u0016J\u0012\u0010D\u001a\u00020(2\b\u0010E\u001a\u0004\u0018\u000108H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\f\u0012\b\u0012\u00060\nR\u00020\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\f\u0012\b\u0012\u00060\nR\u00020\u00000\tX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00070\rj\b\u0012\u0004\u0012\u00020\u0007`\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u00070\rj\b\u0012\u0004\u0012\u00020\u0007`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\u0004\u0018\u00010\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001fR\u0010\u0010#\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStack;", "Lcom/swmansion/rnscreens/ScreenContainer;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dismissedWrappers", "", "Lcom/swmansion/rnscreens/ScreenStackFragmentWrapper;", "drawingOpPool", "", "Lcom/swmansion/rnscreens/ScreenStack$DrawingOp;", "drawingOps", "fragments", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getFragments", "()Ljava/util/ArrayList;", "goingForward", "", "getGoingForward", "()Z", "setGoingForward", "(Z)V", "isDetachingCurrentScreen", "previousChildrenCount", "", "removalTransitionStarted", "reverseLastTwoChildren", "rootScreen", "Lcom/swmansion/rnscreens/Screen;", "getRootScreen", "()Lcom/swmansion/rnscreens/Screen;", "stack", "topScreen", "getTopScreen", "topScreenWrapper", "adapt", "Lcom/swmansion/rnscreens/ScreenStackFragment;", "screen", "dismiss", "", "screenFragment", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "dispatchOnFinishTransitioning", "drawAndRelease", "drawChild", "child", "Landroid/view/View;", "drawingTime", "", "endViewTransition", "view", "hasScreen", "screenFragmentWrapper", "Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "notifyContainerUpdate", "obtainDrawingOp", "onUpdate", "onViewAppearTransitionEnd", "performDraw", "op", "removeAllScreens", "removeScreenAt", "index", "removeView", "startViewTransition", "turnOffA11yUnderTransparentScreen", "visibleBottom", "Companion", "DrawingOp", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ScreenStack extends ScreenContainer {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Set<ScreenStackFragmentWrapper> dismissedWrappers;
    private final List<DrawingOp> drawingOpPool;
    private List<DrawingOp> drawingOps;
    private boolean goingForward;
    private boolean isDetachingCurrentScreen;
    private int previousChildrenCount;
    private boolean removalTransitionStarted;
    private boolean reverseLastTwoChildren;
    private final ArrayList<ScreenStackFragmentWrapper> stack;
    private ScreenStackFragmentWrapper topScreenWrapper;

    /* compiled from: ScreenStack.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Screen.StackAnimation.values().length];
            try {
                iArr[Screen.StackAnimation.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Screen.StackAnimation.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Screen.StackAnimation.FADE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Screen.StackAnimation.SLIDE_FROM_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Screen.StackAnimation.SLIDE_FROM_LEFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Screen.StackAnimation.SLIDE_FROM_BOTTOM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Screen.StackAnimation.FADE_FROM_BOTTOM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Screen.StackAnimation.IOS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final ArrayList<ScreenStackFragmentWrapper> getFragments() {
        return this.stack;
    }

    public final boolean getGoingForward() {
        return this.goingForward;
    }

    public final void setGoingForward(boolean z) {
        this.goingForward = z;
    }

    public ScreenStack(Context context) {
        super(context);
        this.stack = new ArrayList<>();
        this.dismissedWrappers = new HashSet();
        this.drawingOpPool = new ArrayList();
        this.drawingOps = new ArrayList();
    }

    public final void dismiss(ScreenStackFragmentWrapper screenFragment) {
        Intrinsics.checkNotNullParameter(screenFragment, "screenFragment");
        this.dismissedWrappers.add(screenFragment);
        performUpdatesNow();
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public Screen getTopScreen() {
        ScreenStackFragmentWrapper screenStackFragmentWrapper = this.topScreenWrapper;
        if (screenStackFragmentWrapper != null) {
            return screenStackFragmentWrapper.getScreen();
        }
        return null;
    }

    public final Screen getRootScreen() {
        int screenCount = getScreenCount();
        for (int i = 0; i < screenCount; i++) {
            ScreenFragmentWrapper screenFragmentWrapperAt = getScreenFragmentWrapperAt(i);
            if (!CollectionsKt.contains(this.dismissedWrappers, screenFragmentWrapperAt)) {
                return screenFragmentWrapperAt.getScreen();
            }
        }
        throw new IllegalStateException("Stack has no root screen set");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.rnscreens.ScreenContainer
    public ScreenStackFragment adapt(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        return new ScreenStackFragment(screen);
    }

    @Override // android.view.ViewGroup
    public void startViewTransition(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.startViewTransition(view);
        this.removalTransitionStarted = true;
    }

    @Override // android.view.ViewGroup
    public void endViewTransition(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.endViewTransition(view);
        if (this.removalTransitionStarted) {
            this.removalTransitionStarted = false;
            dispatchOnFinishTransitioning();
        }
    }

    public final void onViewAppearTransitionEnd() {
        if (this.removalTransitionStarted) {
            return;
        }
        dispatchOnFinishTransitioning();
    }

    private final void dispatchOnFinishTransitioning() {
        int surfaceId = UIManagerHelper.getSurfaceId(this);
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new StackFinishTransitioningEvent(surfaceId, getId()));
        }
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public void removeScreenAt(int index) {
        Set<ScreenStackFragmentWrapper> set = this.dismissedWrappers;
        TypeIntrinsics.asMutableCollection(set).remove(getScreenFragmentWrapperAt(index));
        super.removeScreenAt(index);
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public void removeAllScreens() {
        this.dismissedWrappers.clear();
        super.removeAllScreens();
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    public boolean hasScreen(ScreenFragmentWrapper screenFragmentWrapper) {
        return super.hasScreen(screenFragmentWrapper) && !CollectionsKt.contains(this.dismissedWrappers, screenFragmentWrapper);
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x022b A[LOOP:4: B:113:0x0225->B:115:0x022b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0150 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01b3  */
    @Override // com.swmansion.rnscreens.ScreenContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onUpdate() {
        /*
            Method dump skipped, instructions count: 626
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenStack.onUpdate():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onUpdate$lambda$3$lambda$1(ScreenFragmentWrapper screenFragmentWrapper) {
        Screen screen;
        if (screenFragmentWrapper == null || (screen = screenFragmentWrapper.getScreen()) == null) {
            return;
        }
        screen.bringToFront();
    }

    private final void turnOffA11yUnderTransparentScreen(ScreenFragmentWrapper visibleBottom) {
        ScreenStackFragmentWrapper screenStackFragmentWrapper;
        if (this.screenWrappers.size() > 1 && visibleBottom != null && (screenStackFragmentWrapper = this.topScreenWrapper) != null && INSTANCE.isTransparent(screenStackFragmentWrapper)) {
            for (ScreenFragmentWrapper screenFragmentWrapper : CollectionsKt.asReversed(CollectionsKt.slice((List) this.screenWrappers, RangesKt.until(0, this.screenWrappers.size() - 1)))) {
                screenFragmentWrapper.getScreen().changeAccessibilityMode(4);
                if (Intrinsics.areEqual(screenFragmentWrapper, visibleBottom)) {
                    break;
                }
            }
        }
        Screen topScreen = getTopScreen();
        if (topScreen != null) {
            topScreen.changeAccessibilityMode(0);
        }
    }

    @Override // com.swmansion.rnscreens.ScreenContainer
    protected void notifyContainerUpdate() {
        Iterator<T> it = this.stack.iterator();
        while (it.hasNext()) {
            ((ScreenStackFragmentWrapper) it.next()).onContainerUpdate();
        }
    }

    @Override // com.swmansion.rnscreens.ScreenContainer, android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this.isDetachingCurrentScreen) {
            this.isDetachingCurrentScreen = false;
            this.reverseLastTwoChildren = true;
        }
        super.removeView(view);
    }

    private final void drawAndRelease() {
        List<DrawingOp> list = this.drawingOps;
        this.drawingOps = new ArrayList();
        for (DrawingOp drawingOp : list) {
            drawingOp.draw();
            this.drawingOpPool.add(drawingOp);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.dispatchDraw(canvas);
        if (this.drawingOps.size() < this.previousChildrenCount) {
            this.reverseLastTwoChildren = false;
        }
        this.previousChildrenCount = this.drawingOps.size();
        if (this.reverseLastTwoChildren && this.drawingOps.size() >= 2) {
            Collections.swap(this.drawingOps, r4.size() - 1, this.drawingOps.size() - 2);
        }
        drawAndRelease();
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(child, "child");
        List<DrawingOp> list = this.drawingOps;
        DrawingOp obtainDrawingOp = obtainDrawingOp();
        obtainDrawingOp.setCanvas(canvas);
        obtainDrawingOp.setChild(child);
        obtainDrawingOp.setDrawingTime(drawingTime);
        list.add(obtainDrawingOp);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void performDraw(DrawingOp op) {
        Canvas canvas = op.getCanvas();
        Intrinsics.checkNotNull(canvas);
        super.drawChild(canvas, op.getChild(), op.getDrawingTime());
    }

    private final DrawingOp obtainDrawingOp() {
        return this.drawingOpPool.isEmpty() ? new DrawingOp() : (DrawingOp) CollectionsKt.removeLast(this.drawingOpPool);
    }

    /* compiled from: ScreenStack.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStack$DrawingOp;", "", "(Lcom/swmansion/rnscreens/ScreenStack;)V", "canvas", "Landroid/graphics/Canvas;", "getCanvas", "()Landroid/graphics/Canvas;", "setCanvas", "(Landroid/graphics/Canvas;)V", "child", "Landroid/view/View;", "getChild", "()Landroid/view/View;", "setChild", "(Landroid/view/View;)V", "drawingTime", "", "getDrawingTime", "()J", "setDrawingTime", "(J)V", "draw", "", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private final class DrawingOp {
        private Canvas canvas;
        private View child;
        private long drawingTime;

        public final Canvas getCanvas() {
            return this.canvas;
        }

        public final View getChild() {
            return this.child;
        }

        public final long getDrawingTime() {
            return this.drawingTime;
        }

        public final void setCanvas(Canvas canvas) {
            this.canvas = canvas;
        }

        public final void setChild(View view) {
            this.child = view;
        }

        public final void setDrawingTime(long j) {
            this.drawingTime = j;
        }

        public DrawingOp() {
        }

        public final void draw() {
            ScreenStack.this.performDraw(this);
            this.canvas = null;
            this.child = null;
            this.drawingTime = 0L;
        }
    }

    /* compiled from: ScreenStack.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\b"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStack$Companion;", "", "()V", "isTransparent", "", "fragmentWrapper", "Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "needsDrawReordering", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isTransparent(ScreenFragmentWrapper fragmentWrapper) {
            return fragmentWrapper.getScreen().getStackPresentation() == Screen.StackPresentation.TRANSPARENT_MODAL;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean needsDrawReordering(ScreenFragmentWrapper fragmentWrapper) {
            return Build.VERSION.SDK_INT >= 33 || fragmentWrapper.getScreen().getStackAnimation() == Screen.StackAnimation.SLIDE_FROM_BOTTOM || fragmentWrapper.getScreen().getStackAnimation() == Screen.StackAnimation.FADE_FROM_BOTTOM || fragmentWrapper.getScreen().getStackAnimation() == Screen.StackAnimation.IOS;
        }
    }
}

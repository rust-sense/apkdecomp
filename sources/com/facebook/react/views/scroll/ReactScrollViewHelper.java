package com.facebook.react.views.scroll;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class ReactScrollViewHelper {
    public static final String AUTO = "auto";
    private static final String CONTENT_OFFSET_LEFT = "contentOffsetLeft";
    private static final String CONTENT_OFFSET_TOP = "contentOffsetTop";
    public static final long MOMENTUM_DELAY = 20;
    public static final String OVER_SCROLL_ALWAYS = "always";
    public static final String OVER_SCROLL_NEVER = "never";
    private static final String SCROLL_AWAY_PADDING_TOP = "scrollAwayPaddingTop";
    public static final int SNAP_ALIGNMENT_CENTER = 2;
    public static final int SNAP_ALIGNMENT_DISABLED = 0;
    public static final int SNAP_ALIGNMENT_END = 3;
    public static final int SNAP_ALIGNMENT_START = 1;
    private static String TAG = "ReactHorizontalScrollView";
    private static boolean DEBUG_MODE = false;
    private static final Set<ScrollListener> sScrollListeners = Collections.newSetFromMap(new WeakHashMap());
    private static int SMOOTH_SCROLL_DURATION = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    private static boolean mSmoothScrollDurationInitialized = false;

    public interface HasFlingAnimator {
        ValueAnimator getFlingAnimator();

        int getFlingExtrapolatedDistance(int i);

        void startFlingAnimator(int i, int i2);
    }

    public interface HasScrollEventThrottle {
        long getLastScrollDispatchTime();

        int getScrollEventThrottle();

        void setLastScrollDispatchTime(long j);

        void setScrollEventThrottle(int i);
    }

    public interface HasScrollState {
        ReactScrollViewScrollState getReactScrollViewScrollState();
    }

    public interface HasSmoothScroll {
        void reactSmoothScrollTo(int i, int i2);
    }

    public interface HasStateWrapper {
        StateWrapper getStateWrapper();
    }

    public interface ScrollListener {
        void onLayout(ViewGroup viewGroup);

        void onScroll(ViewGroup viewGroup, ScrollEventType scrollEventType, float f, float f2);
    }

    public static <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEvent(T t, float f, float f2) {
        emitScrollEvent(t, ScrollEventType.SCROLL, f, f2);
    }

    public static <T extends ViewGroup & HasScrollEventThrottle> void emitScrollBeginDragEvent(T t) {
        emitScrollEvent(t, ScrollEventType.BEGIN_DRAG);
    }

    public static <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEndDragEvent(T t, float f, float f2) {
        emitScrollEvent(t, ScrollEventType.END_DRAG, f, f2);
    }

    public static <T extends ViewGroup & HasScrollEventThrottle> void emitScrollMomentumBeginEvent(T t, int i, int i2) {
        emitScrollEvent(t, ScrollEventType.MOMENTUM_BEGIN, i, i2);
    }

    public static <T extends ViewGroup & HasScrollEventThrottle> void emitScrollMomentumEndEvent(T t) {
        emitScrollEvent(t, ScrollEventType.MOMENTUM_END);
    }

    private static <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEvent(T t, ScrollEventType scrollEventType) {
        emitScrollEvent(t, scrollEventType, 0.0f, 0.0f);
    }

    private static <T extends ViewGroup & HasScrollEventThrottle> void emitScrollEvent(T t, ScrollEventType scrollEventType, float f, float f2) {
        View childAt;
        long currentTimeMillis = System.currentTimeMillis();
        T t2 = t;
        if (t2.getScrollEventThrottle() < Math.max(17L, currentTimeMillis - t2.getLastScrollDispatchTime()) && (childAt = t.getChildAt(0)) != null) {
            Iterator<ScrollListener> it = sScrollListeners.iterator();
            while (it.hasNext()) {
                it.next().onScroll(t, scrollEventType, f, f2);
            }
            ReactContext reactContext = (ReactContext) t.getContext();
            int surfaceId = UIManagerHelper.getSurfaceId(reactContext);
            EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, t.getId());
            if (eventDispatcherForReactTag != null) {
                eventDispatcherForReactTag.dispatchEvent(ScrollEvent.obtain(surfaceId, t.getId(), scrollEventType, t.getScrollX(), t.getScrollY(), f, f2, childAt.getWidth(), childAt.getHeight(), t.getWidth(), t.getHeight()));
                t2.setLastScrollDispatchTime(currentTimeMillis);
            }
        }
    }

    public static void emitLayoutEvent(ViewGroup viewGroup) {
        Iterator<ScrollListener> it = sScrollListeners.iterator();
        while (it.hasNext()) {
            it.next().onLayout(viewGroup);
        }
    }

    public static int parseOverScrollMode(String str) {
        if (str != null && !str.equals("auto")) {
            if (str.equals("always")) {
                return 0;
            }
            if (str.equals("never")) {
                return 2;
            }
            FLog.w(ReactConstants.TAG, "wrong overScrollMode: " + str);
        }
        return 1;
    }

    public static int parseSnapToAlignment(String str) {
        if (str == null) {
            return 0;
        }
        if (ViewProps.START.equalsIgnoreCase(str)) {
            return 1;
        }
        if ("center".equalsIgnoreCase(str)) {
            return 2;
        }
        if (ViewProps.END.equals(str)) {
            return 3;
        }
        FLog.w(ReactConstants.TAG, "wrong snap alignment value: " + str);
        return 0;
    }

    public static int getDefaultScrollAnimationDuration(Context context) {
        if (!mSmoothScrollDurationInitialized) {
            mSmoothScrollDurationInitialized = true;
            try {
                SMOOTH_SCROLL_DURATION = new OverScrollerDurationGetter(context).getScrollAnimationDuration();
            } catch (Throwable unused) {
            }
        }
        return SMOOTH_SCROLL_DURATION;
    }

    private static class OverScrollerDurationGetter extends OverScroller {
        private int mScrollAnimationDuration;

        @Override // android.widget.OverScroller
        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            this.mScrollAnimationDuration = i5;
        }

        OverScrollerDurationGetter(Context context) {
            super(context);
            this.mScrollAnimationDuration = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        }

        public int getScrollAnimationDuration() {
            super.startScroll(0, 0, 0, 0);
            return this.mScrollAnimationDuration;
        }
    }

    public static void addScrollListener(ScrollListener scrollListener) {
        sScrollListeners.add(scrollListener);
    }

    public static void removeScrollListener(ScrollListener scrollListener) {
        sScrollListeners.remove(scrollListener);
    }

    public static class ReactScrollViewScrollState {
        private final int mLayoutDirection;
        private final Point mFinalAnimatedPositionScroll = new Point();
        private int mScrollAwayPaddingTop = 0;
        private final Point mLastStateUpdateScroll = new Point(-1, -1);
        private boolean mIsCanceled = false;
        private boolean mIsFinished = true;
        private float mDecelerationRate = 0.985f;

        public float getDecelerationRate() {
            return this.mDecelerationRate;
        }

        public Point getFinalAnimatedPositionScroll() {
            return this.mFinalAnimatedPositionScroll;
        }

        public boolean getIsCanceled() {
            return this.mIsCanceled;
        }

        public boolean getIsFinished() {
            return this.mIsFinished;
        }

        public Point getLastStateUpdateScroll() {
            return this.mLastStateUpdateScroll;
        }

        public int getLayoutDirection() {
            return this.mLayoutDirection;
        }

        public int getScrollAwayPaddingTop() {
            return this.mScrollAwayPaddingTop;
        }

        public ReactScrollViewScrollState setDecelerationRate(float f) {
            this.mDecelerationRate = f;
            return this;
        }

        public ReactScrollViewScrollState setIsCanceled(boolean z) {
            this.mIsCanceled = z;
            return this;
        }

        public ReactScrollViewScrollState setIsFinished(boolean z) {
            this.mIsFinished = z;
            return this;
        }

        public ReactScrollViewScrollState setScrollAwayPaddingTop(int i) {
            this.mScrollAwayPaddingTop = i;
            return this;
        }

        public ReactScrollViewScrollState(int i) {
            this.mLayoutDirection = i;
        }

        public ReactScrollViewScrollState setFinalAnimatedPositionScroll(int i, int i2) {
            this.mFinalAnimatedPositionScroll.set(i, i2);
            return this;
        }

        public ReactScrollViewScrollState setLastStateUpdateScroll(int i, int i2) {
            this.mLastStateUpdateScroll.set(i, i2);
            return this;
        }
    }

    public static <T extends ViewGroup & HasStateWrapper & HasScrollState & HasFlingAnimator> void smoothScrollTo(T t, int i, int i2) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "smoothScrollTo[%d] x %d y %d", Integer.valueOf(t.getId()), Integer.valueOf(i), Integer.valueOf(i2));
        }
        T t2 = t;
        ValueAnimator flingAnimator = t2.getFlingAnimator();
        if (flingAnimator.getListeners() == null || flingAnimator.getListeners().size() == 0) {
            registerFlingAnimator(t);
        }
        t.getReactScrollViewScrollState().setFinalAnimatedPositionScroll(i, i2);
        int scrollX = t.getScrollX();
        int scrollY = t.getScrollY();
        if (scrollX != i) {
            t2.startFlingAnimator(scrollX, i);
        }
        if (scrollY != i2) {
            t2.startFlingAnimator(scrollY, i2);
        }
        updateFabricScrollState(t, i, i2);
    }

    public static <T extends ViewGroup & HasScrollState & HasFlingAnimator> int getNextFlingStartValue(T t, int i, int i2, int i3) {
        ReactScrollViewScrollState reactScrollViewScrollState = t.getReactScrollViewScrollState();
        return (!reactScrollViewScrollState.getIsFinished() || (reactScrollViewScrollState.getIsCanceled() && ((i3 != 0 ? i3 / Math.abs(i3) : 0) * (i2 - i) > 0))) ? i2 : i;
    }

    public static <T extends ViewGroup & HasStateWrapper & HasScrollState & HasFlingAnimator> void updateFabricScrollState(T t) {
        updateFabricScrollState(t, t.getScrollX(), t.getScrollY());
    }

    public static <T extends ViewGroup & HasStateWrapper & HasScrollState & HasFlingAnimator> void updateFabricScrollState(T t, int i, int i2) {
        if (DEBUG_MODE) {
            FLog.i(TAG, "updateFabricScrollState[%d] scrollX %d scrollY %d", Integer.valueOf(t.getId()), Integer.valueOf(i), Integer.valueOf(i2));
        }
        if (ViewUtil.getUIManagerType(t.getId()) == 1) {
            return;
        }
        ReactScrollViewScrollState reactScrollViewScrollState = t.getReactScrollViewScrollState();
        if (reactScrollViewScrollState.getLastStateUpdateScroll().equals(i, i2)) {
            return;
        }
        reactScrollViewScrollState.setLastStateUpdateScroll(i, i2);
        forceUpdateState(t);
    }

    public static <T extends ViewGroup & HasScrollState & HasStateWrapper & HasFlingAnimator> void forceUpdateState(T t) {
        int i;
        ReactScrollViewScrollState reactScrollViewScrollState = t.getReactScrollViewScrollState();
        int scrollAwayPaddingTop = reactScrollViewScrollState.getScrollAwayPaddingTop();
        Point lastStateUpdateScroll = reactScrollViewScrollState.getLastStateUpdateScroll();
        int i2 = lastStateUpdateScroll.x;
        int i3 = lastStateUpdateScroll.y;
        if (reactScrollViewScrollState.getLayoutDirection() == 1) {
            View childAt = t.getChildAt(0);
            i = -(((childAt != null ? childAt.getWidth() : 0) - i2) - t.getWidth());
        } else {
            i = i2;
        }
        if (DEBUG_MODE) {
            FLog.i(TAG, "updateFabricScrollState[%d] scrollX %d scrollY %d fabricScrollX", Integer.valueOf(t.getId()), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i));
        }
        StateWrapper stateWrapper = t.getStateWrapper();
        if (stateWrapper != null) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble(CONTENT_OFFSET_LEFT, PixelUtil.toDIPFromPixel(i2));
            writableNativeMap.putDouble(CONTENT_OFFSET_TOP, PixelUtil.toDIPFromPixel(i3));
            writableNativeMap.putDouble(SCROLL_AWAY_PADDING_TOP, PixelUtil.toDIPFromPixel(scrollAwayPaddingTop));
            stateWrapper.updateState(writableNativeMap);
        }
    }

    public static <T extends ViewGroup & HasStateWrapper & HasScrollState & HasFlingAnimator & HasScrollEventThrottle> void updateStateOnScrollChanged(T t, float f, float f2) {
        updateFabricScrollState(t);
        emitScrollEvent(t, f, f2);
    }

    public static <T extends ViewGroup & HasStateWrapper & HasScrollState & HasFlingAnimator> void registerFlingAnimator(final T t) {
        t.getFlingAnimator().addListener(new Animator.AnimatorListener() { // from class: com.facebook.react.views.scroll.ReactScrollViewHelper.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                ReactScrollViewScrollState reactScrollViewScrollState = ((HasScrollState) t).getReactScrollViewScrollState();
                reactScrollViewScrollState.setIsCanceled(false);
                reactScrollViewScrollState.setIsFinished(false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ((HasScrollState) t).getReactScrollViewScrollState().setIsFinished(true);
                ReactScrollViewHelper.updateFabricScrollState(t);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                ((HasScrollState) t).getReactScrollViewScrollState().setIsCanceled(true);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends ViewGroup & HasScrollState & HasFlingAnimator> Point predictFinalScrollPosition(T t, int i, int i2, int i3, int i4) {
        ReactScrollViewScrollState reactScrollViewScrollState = t.getReactScrollViewScrollState();
        OverScroller overScroller = new OverScroller(t.getContext());
        overScroller.setFriction(1.0f - reactScrollViewScrollState.getDecelerationRate());
        int width = (t.getWidth() - ViewCompat.getPaddingStart(t)) - ViewCompat.getPaddingEnd(t);
        int height = (t.getHeight() - t.getPaddingBottom()) - t.getPaddingTop();
        Point finalAnimatedPositionScroll = reactScrollViewScrollState.getFinalAnimatedPositionScroll();
        overScroller.fling(getNextFlingStartValue(t, t.getScrollX(), finalAnimatedPositionScroll.x, i), getNextFlingStartValue(t, t.getScrollY(), finalAnimatedPositionScroll.y, i2), i, i2, 0, i3, 0, i4, width / 2, height / 2);
        return new Point(overScroller.getFinalX(), overScroller.getFinalY());
    }
}

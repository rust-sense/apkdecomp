package com.swmansion.gesturehandler.react;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.ThemedReactContext;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.GestureHandlerOrchestrator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNGestureHandlerRootHelper.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001f B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\fJ\u0006\u0010\u001c\u001a\u00020\u0012J\u0006\u0010\u001d\u001a\u00020\u0012J\b\u0010\u001e\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootHelper;", "", "context", "Lcom/facebook/react/bridge/ReactContext;", "wrappedView", "Landroid/view/ViewGroup;", "(Lcom/facebook/react/bridge/ReactContext;Landroid/view/ViewGroup;)V", "jsGestureHandler", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "orchestrator", "Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;", "passingTouch", "", "rootView", "getRootView", "()Landroid/view/ViewGroup;", "shouldIntercept", "activateNativeHandlers", "", "view", "Landroid/view/View;", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "handleSetJSResponder", "viewTag", "", "blockNativeResponder", "requestDisallowInterceptTouchEvent", "tearDown", "tryCancelAllHandlers", "Companion", "RootViewGestureHandler", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RNGestureHandlerRootHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float MIN_ALPHA_FOR_TOUCH = 0.1f;
    private final ReactContext context;
    private final GestureHandler<?> jsGestureHandler;
    private final GestureHandlerOrchestrator orchestrator;
    private boolean passingTouch;
    private final ViewGroup rootView;
    private boolean shouldIntercept;

    public final ViewGroup getRootView() {
        return this.rootView;
    }

    public RNGestureHandlerRootHelper(ReactContext context, ViewGroup wrappedView) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(wrappedView, "wrappedView");
        this.context = context;
        UiThreadUtil.assertOnUiThread();
        int id = wrappedView.getId();
        if (id < 1) {
            throw new IllegalStateException(("Expect view tag to be set for " + wrappedView).toString());
        }
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        NativeModule nativeModule = ((ThemedReactContext) context).getReactApplicationContext().getNativeModule(RNGestureHandlerModule.class);
        Intrinsics.checkNotNull(nativeModule);
        RNGestureHandlerModule rNGestureHandlerModule = (RNGestureHandlerModule) nativeModule;
        RNGestureHandlerRegistry registry = rNGestureHandlerModule.getRegistry();
        ViewGroup findRootViewTag = INSTANCE.findRootViewTag(wrappedView);
        this.rootView = findRootViewTag;
        Log.i(ReactConstants.TAG, "[GESTURE HANDLER] Initialize gesture handler for root view " + findRootViewTag);
        GestureHandlerOrchestrator gestureHandlerOrchestrator = new GestureHandlerOrchestrator(wrappedView, registry, new RNViewConfigurationHelper());
        gestureHandlerOrchestrator.setMinimumAlphaForTraversal(0.1f);
        this.orchestrator = gestureHandlerOrchestrator;
        RootViewGestureHandler rootViewGestureHandler = new RootViewGestureHandler();
        rootViewGestureHandler.setTag(-id);
        RootViewGestureHandler rootViewGestureHandler2 = rootViewGestureHandler;
        this.jsGestureHandler = rootViewGestureHandler2;
        registry.registerHandler(rootViewGestureHandler2);
        registry.attachHandlerToView(rootViewGestureHandler2.getTag(), id, 3);
        rNGestureHandlerModule.registerRootHelper(this);
    }

    public final void tearDown() {
        Log.i(ReactConstants.TAG, "[GESTURE HANDLER] Tearing down gesture handler registered for root view " + this.rootView);
        ReactContext reactContext = this.context;
        Intrinsics.checkNotNull(reactContext, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        NativeModule nativeModule = ((ThemedReactContext) reactContext).getReactApplicationContext().getNativeModule(RNGestureHandlerModule.class);
        Intrinsics.checkNotNull(nativeModule);
        RNGestureHandlerModule rNGestureHandlerModule = (RNGestureHandlerModule) nativeModule;
        RNGestureHandlerRegistry registry = rNGestureHandlerModule.getRegistry();
        GestureHandler<?> gestureHandler = this.jsGestureHandler;
        Intrinsics.checkNotNull(gestureHandler);
        registry.dropHandler(gestureHandler.getTag());
        rNGestureHandlerModule.unregisterRootHelper(this);
    }

    /* compiled from: RNGestureHandlerRootHelper.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0000R\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0014¨\u0006\n"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootHelper$RootViewGestureHandler;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootHelper;", "(Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootHelper;)V", "onCancel", "", "onHandle", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "sourceEvent", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private final class RootViewGestureHandler extends GestureHandler<RootViewGestureHandler> {
        public RootViewGestureHandler() {
        }

        @Override // com.swmansion.gesturehandler.core.GestureHandler
        protected void onHandle(MotionEvent event, MotionEvent sourceEvent) {
            GestureHandlerOrchestrator orchestrator;
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
            if (getState() == 0 && (!RNGestureHandlerRootHelper.this.shouldIntercept || (orchestrator = getOrchestrator()) == null || !orchestrator.isAnyHandlerActive())) {
                begin();
                RNGestureHandlerRootHelper.this.shouldIntercept = false;
            }
            if (event.getActionMasked() == 1) {
                end();
            }
        }

        @Override // com.swmansion.gesturehandler.core.GestureHandler
        protected void onCancel() {
            RNGestureHandlerRootHelper.this.shouldIntercept = true;
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            obtain.setAction(3);
            if (RNGestureHandlerRootHelper.this.getRootView() instanceof RootView) {
                ((RootView) RNGestureHandlerRootHelper.this.getRootView()).onChildStartedNativeGesture(RNGestureHandlerRootHelper.this.getRootView(), obtain);
            }
            obtain.recycle();
        }
    }

    public final void requestDisallowInterceptTouchEvent() {
        if (this.orchestrator == null || this.passingTouch) {
            return;
        }
        tryCancelAllHandlers();
    }

    public final boolean dispatchTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        this.passingTouch = true;
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        Intrinsics.checkNotNull(gestureHandlerOrchestrator);
        gestureHandlerOrchestrator.onTouchEvent(ev);
        this.passingTouch = false;
        return this.shouldIntercept;
    }

    private final void tryCancelAllHandlers() {
        GestureHandler<?> gestureHandler = this.jsGestureHandler;
        if (gestureHandler == null || gestureHandler.getState() != 2) {
            return;
        }
        gestureHandler.activate();
        gestureHandler.end();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void handleSetJSResponder$lambda$6(RNGestureHandlerRootHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.tryCancelAllHandlers();
    }

    public final void handleSetJSResponder(int viewTag, boolean blockNativeResponder) {
        if (blockNativeResponder) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RNGestureHandlerRootHelper.handleSetJSResponder$lambda$6(RNGestureHandlerRootHelper.this);
                }
            });
        }
    }

    public final void activateNativeHandlers(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        if (gestureHandlerOrchestrator != null) {
            gestureHandlerOrchestrator.activateNativeHandlersForView(view);
        }
    }

    /* compiled from: RNGestureHandlerRootHelper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootHelper$Companion;", "", "()V", "MIN_ALPHA_FOR_TOUCH", "", "findRootViewTag", "Landroid/view/ViewGroup;", "viewGroup", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ViewGroup findRootViewTag(ViewGroup viewGroup) {
            UiThreadUtil.assertOnUiThread();
            ViewGroup viewGroup2 = viewGroup;
            while (viewGroup2 != null && !(viewGroup2 instanceof RootView)) {
                viewGroup2 = viewGroup2.getParent();
            }
            if (viewGroup2 == null) {
                throw new IllegalStateException(("View " + viewGroup + " has not been mounted under ReactRootView").toString());
            }
            return (ViewGroup) viewGroup2;
        }
    }
}

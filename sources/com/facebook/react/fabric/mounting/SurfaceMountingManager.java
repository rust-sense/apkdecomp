package com.facebook.react.fabric.mounting;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.collection.SparseArrayCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.fabric.GuardedFrameCallback;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.IViewGroupManager;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.ReactOverflowViewWithInset;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class SurfaceMountingManager {
    private static final boolean SHOW_CHANGED_VIEW_HIERARCHIES = false;
    public static final String TAG = "SurfaceMountingManager";
    private JSResponderHandler mJSResponderHandler;
    private MountingManager.MountItemExecutor mMountItemExecutor;
    private RemoveDeleteTreeUIFrameCallback mRemoveDeleteTreeUIFrameCallback;
    private RootViewManager mRootViewManager;
    private final int mSurfaceId;
    private SparseArrayCompat<Object> mTagSetForStoppedSurface;
    private ThemedReactContext mThemedReactContext;
    private ViewManagerRegistry mViewManagerRegistry;
    private volatile boolean mIsStopped = false;
    private volatile boolean mRootViewAttached = false;
    private ConcurrentHashMap<Integer, ViewState> mTagToViewState = new ConcurrentHashMap<>();
    private Queue<MountItem> mOnViewAttachMountItems = new ArrayDeque();
    private final Stack<Integer> mReactTagsToRemove = new Stack<>();
    private final Set<Integer> mErroneouslyReaddedReactTags = new HashSet();

    public ThemedReactContext getContext() {
        return this.mThemedReactContext;
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public boolean isRootViewAttached() {
        return this.mRootViewAttached;
    }

    public boolean isStopped() {
        return this.mIsStopped;
    }

    public SurfaceMountingManager(int i, JSResponderHandler jSResponderHandler, ViewManagerRegistry viewManagerRegistry, RootViewManager rootViewManager, MountingManager.MountItemExecutor mountItemExecutor, ThemedReactContext themedReactContext) {
        this.mSurfaceId = i;
        this.mJSResponderHandler = jSResponderHandler;
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mRootViewManager = rootViewManager;
        this.mMountItemExecutor = mountItemExecutor;
        this.mThemedReactContext = themedReactContext;
    }

    public void attachRootView(View view, ThemedReactContext themedReactContext) {
        this.mThemedReactContext = themedReactContext;
        addRootView(view);
    }

    private static void logViewHierarchy(ViewGroup viewGroup, boolean z) {
        int id = viewGroup.getId();
        FLog.e(TAG, "  <ViewGroup tag=" + id + " class=" + viewGroup.getClass().toString() + ">");
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            FLog.e(TAG, "     <View idx=" + i + " tag=" + viewGroup.getChildAt(i).getId() + " class=" + viewGroup.getChildAt(i).getClass().toString() + ">");
        }
        String str = TAG;
        FLog.e(str, "  </ViewGroup tag=" + id + ">");
        if (z) {
            FLog.e(str, "Displaying Ancestors:");
            for (ViewParent parent = viewGroup.getParent(); parent != null; parent = parent.getParent()) {
                ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                int id2 = viewGroup2 == null ? -1 : viewGroup2.getId();
                FLog.e(TAG, "<ViewParent tag=" + id2 + " class=" + parent.getClass().toString() + ">");
            }
        }
    }

    public boolean getViewExists(int i) {
        SparseArrayCompat<Object> sparseArrayCompat = this.mTagSetForStoppedSurface;
        if (sparseArrayCompat != null && sparseArrayCompat.containsKey(i)) {
            return true;
        }
        ConcurrentHashMap<Integer, ViewState> concurrentHashMap = this.mTagToViewState;
        if (concurrentHashMap == null) {
            return false;
        }
        return concurrentHashMap.containsKey(Integer.valueOf(i));
    }

    public void scheduleMountItemOnViewAttach(MountItem mountItem) {
        this.mOnViewAttachMountItems.add(mountItem);
    }

    private void addRootView(final View view) {
        if (isStopped()) {
            return;
        }
        this.mTagToViewState.put(Integer.valueOf(this.mSurfaceId), new ViewState(this.mSurfaceId, view, this.mRootViewManager, true));
        Runnable runnable = new Runnable() { // from class: com.facebook.react.fabric.mounting.SurfaceMountingManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SurfaceMountingManager.this.lambda$addRootView$0(view);
            }
        };
        if (UiThreadUtil.isOnUiThread()) {
            runnable.run();
        } else {
            UiThreadUtil.runOnUiThread(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void lambda$addRootView$0(View view) {
        if (isStopped()) {
            return;
        }
        if (view.getId() == this.mSurfaceId) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalViewOperationException("Race condition in addRootView detected. Trying to set an id of [" + this.mSurfaceId + "] on the RootView, but that id has already been set. "));
        } else if (view.getId() != -1) {
            FLog.e(TAG, "Trying to add RootTag to RootView that already has a tag: existing tag: [%d] new tag: [%d]", Integer.valueOf(view.getId()), Integer.valueOf(this.mSurfaceId));
            throw new IllegalViewOperationException("Trying to add a root view with an explicit id already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
        }
        view.setId(this.mSurfaceId);
        if (view instanceof ReactRoot) {
            ((ReactRoot) view).setRootViewTag(this.mSurfaceId);
        }
        this.mRootViewAttached = true;
        executeMountItemsOnViewAttach();
    }

    private void executeMountItemsOnViewAttach() {
        this.mMountItemExecutor.executeItems(this.mOnViewAttachMountItems);
    }

    public void stopSurface() {
        FLog.e(TAG, "Stopping surface [" + this.mSurfaceId + "]");
        if (isStopped()) {
            return;
        }
        this.mIsStopped = true;
        for (ViewState viewState : this.mTagToViewState.values()) {
            if (viewState.mStateWrapper != null) {
                viewState.mStateWrapper.destroyState();
                viewState.mStateWrapper = null;
            }
            if (viewState.mEventEmitter != null) {
                viewState.mEventEmitter.destroy();
                viewState.mEventEmitter = null;
            }
        }
        Runnable runnable = new Runnable() { // from class: com.facebook.react.fabric.mounting.SurfaceMountingManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SurfaceMountingManager.this.lambda$stopSurface$1();
            }
        };
        if (UiThreadUtil.isOnUiThread()) {
            runnable.run();
        } else {
            UiThreadUtil.runOnUiThread(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$stopSurface$1() {
        this.mTagSetForStoppedSurface = new SparseArrayCompat<>();
        for (Map.Entry<Integer, ViewState> entry : this.mTagToViewState.entrySet()) {
            this.mTagSetForStoppedSurface.put(entry.getKey().intValue(), this);
            onViewStateDeleted(entry.getValue());
        }
        this.mTagToViewState = null;
        this.mJSResponderHandler = null;
        this.mRootViewManager = null;
        this.mMountItemExecutor = null;
        this.mThemedReactContext = null;
        this.mOnViewAttachMountItems.clear();
        if (ReactFeatureFlags.enableViewRecycling) {
            this.mViewManagerRegistry.onSurfaceStopped(this.mSurfaceId);
        }
        FLog.e(TAG, "Surface [" + this.mSurfaceId + "] was stopped on SurfaceMountingManager.");
    }

    public void addViewAt(int i, int i2, int i3) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(i);
        if (!(viewState.mView instanceof ViewGroup)) {
            String str = "Unable to add a view into a view that is not a ViewGroup. ParentTag: " + i + " - Tag: " + i2 + " - Index: " + i3;
            FLog.e(TAG, str);
            throw new IllegalStateException(str);
        }
        ViewGroup viewGroup = (ViewGroup) viewState.mView;
        ViewState viewState2 = getViewState(i2);
        View view = viewState2.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find view for viewState " + viewState2 + " and tag " + i2);
        }
        ViewParent parent = view.getParent();
        if (parent != null) {
            boolean z = parent instanceof ViewGroup;
            int id = z ? ((ViewGroup) parent).getId() : -1;
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("addViewAt: cannot insert view [" + i2 + "] into parent [" + i + "]: View already has a parent: [" + id + "]  Parent: " + parent.getClass().getSimpleName() + " View: " + view.getClass().getSimpleName()));
            if (ReactNativeFeatureFlags.enableFixForClippedSubviewsCrash()) {
                if (parent instanceof ReactViewGroup) {
                    ReactViewGroup reactViewGroup = (ReactViewGroup) parent;
                    if (reactViewGroup.getRemoveClippedSubviews()) {
                        reactViewGroup.removeViewWithSubviewClippingEnabled(view);
                    } else {
                        reactViewGroup.removeView(view);
                    }
                } else if (z) {
                    ((ViewGroup) parent).removeView(view);
                }
            } else if (z) {
                ((ViewGroup) parent).removeView(view);
            }
            this.mErroneouslyReaddedReactTags.add(Integer.valueOf(i2));
        }
        try {
            getViewGroupManager(viewState).addView(viewGroup, view, i3);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("addViewAt: failed to insert view [" + i2 + "] into parent [" + i + "] at index " + i3, e);
        }
    }

    public void removeViewAt(int i, int i2, int i3) {
        if (isStopped()) {
            return;
        }
        if (this.mErroneouslyReaddedReactTags.contains(Integer.valueOf(i))) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalViewOperationException("removeViewAt tried to remove a React View that was actually reused. This indicates a bug in the Differ (specifically instruction ordering). [" + i + "]"));
            return;
        }
        UiThreadUtil.assertOnUiThread();
        ViewState nullableViewState = getNullableViewState(i2);
        if (nullableViewState == null) {
            ReactSoftExceptionLogger.logSoftException(MountingManager.TAG, new IllegalStateException("Unable to find viewState for tag: [" + i2 + "] for removeViewAt"));
            return;
        }
        if (!(nullableViewState.mView instanceof ViewGroup)) {
            String str = "Unable to remove a view from a view that is not a ViewGroup. ParentTag: " + i2 + " - Tag: " + i + " - Index: " + i3;
            FLog.e(TAG, str);
            throw new IllegalStateException(str);
        }
        ViewGroup viewGroup = (ViewGroup) nullableViewState.mView;
        if (viewGroup == null) {
            throw new IllegalStateException("Unable to find view for tag [" + i2 + "]");
        }
        IViewGroupManager<ViewGroup> viewGroupManager = getViewGroupManager(nullableViewState);
        View childAt = viewGroupManager.getChildAt(viewGroup, i3);
        int id = childAt != null ? childAt.getId() : -1;
        if (id != i) {
            int childCount = viewGroup.getChildCount();
            int i4 = 0;
            while (true) {
                if (i4 >= childCount) {
                    i4 = -1;
                    break;
                } else if (viewGroup.getChildAt(i4).getId() == i) {
                    break;
                } else {
                    i4++;
                }
            }
            if (i4 == -1) {
                FLog.e(TAG, "removeViewAt: [" + i + "] -> [" + i2 + "] @" + i3 + ": view already removed from parent! Children in parent: " + childCount);
                return;
            }
            logViewHierarchy(viewGroup, true);
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Tried to remove view [" + i + "] of parent [" + i2 + "] at index " + i3 + ", but got view tag " + id + " - actual index of view: " + i4));
            i3 = i4;
        }
        try {
            viewGroupManager.removeViewAt(viewGroup, i3);
        } catch (RuntimeException e) {
            int childCount2 = viewGroupManager.getChildCount(viewGroup);
            logViewHierarchy(viewGroup, true);
            throw new IllegalStateException("Cannot remove child at index " + i3 + " from parent ViewGroup [" + viewGroup.getId() + "], only " + childCount2 + " children in parent. Warning: childCount may be incorrect!", e);
        }
    }

    public void removeDeleteTreeAt(int i, int i2, int i3) {
        if (isStopped()) {
            return;
        }
        UiThreadUtil.assertOnUiThread();
        ViewState nullableViewState = getNullableViewState(i2);
        if (nullableViewState == null) {
            ReactSoftExceptionLogger.logSoftException(MountingManager.TAG, new IllegalStateException("Unable to find viewState for tag: [" + i2 + "] for removeDeleteTreeAt"));
            return;
        }
        if (!(nullableViewState.mView instanceof ViewGroup)) {
            String str = "Unable to remove+delete a view from a view that is not a ViewGroup. ParentTag: " + i2 + " - Tag: " + i + " - Index: " + i3;
            FLog.e(TAG, str);
            throw new IllegalStateException(str);
        }
        ViewGroup viewGroup = (ViewGroup) nullableViewState.mView;
        if (viewGroup == null) {
            throw new IllegalStateException("Unable to find view for tag [" + i2 + "]");
        }
        IViewGroupManager<ViewGroup> viewGroupManager = getViewGroupManager(nullableViewState);
        View childAt = viewGroupManager.getChildAt(viewGroup, i3);
        int id = childAt != null ? childAt.getId() : -1;
        if (id != i) {
            int childCount = viewGroup.getChildCount();
            int i4 = 0;
            while (true) {
                if (i4 >= childCount) {
                    i4 = -1;
                    break;
                } else if (viewGroup.getChildAt(i4).getId() == i) {
                    break;
                } else {
                    i4++;
                }
            }
            if (i4 == -1) {
                FLog.e(TAG, "removeDeleteTreeAt: [" + i + "] -> [" + i2 + "] @" + i3 + ": view already removed from parent! Children in parent: " + childCount);
                return;
            }
            logViewHierarchy(viewGroup, true);
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Tried to remove+delete view [" + i + "] of parent [" + i2 + "] at index " + i3 + ", but got view tag " + id + " - actual index of view: " + i4));
            i3 = i4;
        }
        try {
            viewGroupManager.removeViewAt(viewGroup, i3);
            if (this.mReactTagsToRemove.empty()) {
                if (this.mRemoveDeleteTreeUIFrameCallback == null) {
                    this.mRemoveDeleteTreeUIFrameCallback = new RemoveDeleteTreeUIFrameCallback(this.mThemedReactContext);
                }
                ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this.mRemoveDeleteTreeUIFrameCallback);
            }
            this.mReactTagsToRemove.push(Integer.valueOf(i));
        } catch (RuntimeException e) {
            int childCount2 = viewGroupManager.getChildCount(viewGroup);
            logViewHierarchy(viewGroup, true);
            throw new IllegalStateException("Cannot remove child at index " + i3 + " from parent ViewGroup [" + viewGroup.getId() + "], only " + childCount2 + " children in parent. Warning: childCount may be incorrect!", e);
        }
    }

    public void createView(String str, int i, ReadableMap readableMap, StateWrapper stateWrapper, EventEmitterWrapper eventEmitterWrapper, boolean z) {
        if (isStopped()) {
            return;
        }
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState == null || nullableViewState.mView == null) {
            createViewUnsafe(str, i, readableMap, stateWrapper, eventEmitterWrapper, z);
        }
    }

    public void createViewUnsafe(String str, int i, ReadableMap readableMap, StateWrapper stateWrapper, EventEmitterWrapper eventEmitterWrapper, boolean z) {
        ViewManager viewManager;
        View view;
        ReactStylesDiffMap reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
        if (z) {
            viewManager = this.mViewManagerRegistry.get(str);
            view = viewManager.createView(i, this.mThemedReactContext, reactStylesDiffMap, stateWrapper, this.mJSResponderHandler);
        } else {
            viewManager = null;
            view = null;
        }
        ViewState viewState = new ViewState(i, view, viewManager);
        viewState.mCurrentProps = reactStylesDiffMap;
        viewState.mStateWrapper = stateWrapper;
        viewState.mEventEmitter = eventEmitterWrapper;
        this.mTagToViewState.put(Integer.valueOf(i), viewState);
    }

    public void updateProps(int i, ReadableMap readableMap) {
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(i);
        viewState.mCurrentProps = new ReactStylesDiffMap(readableMap);
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find view for tag [" + i + "]");
        }
        ((ViewManager) Assertions.assertNotNull(viewState.mViewManager)).updateProperties(view, viewState.mCurrentProps);
    }

    @Deprecated
    public void receiveCommand(int i, int i2, ReadableArray readableArray) {
        if (isStopped()) {
            return;
        }
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState == null) {
            throw new RetryableMountingLayerException("Unable to find viewState for tag: [" + i + "] for commandId: " + i2);
        }
        if (nullableViewState.mViewManager == null) {
            throw new RetryableMountingLayerException("Unable to find viewManager for tag " + i);
        }
        if (nullableViewState.mView == null) {
            throw new RetryableMountingLayerException("Unable to find viewState view for tag " + i);
        }
        nullableViewState.mViewManager.receiveCommand((ViewManager) nullableViewState.mView, i2, readableArray);
    }

    public void receiveCommand(int i, String str, ReadableArray readableArray) {
        if (isStopped()) {
            return;
        }
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState == null) {
            throw new RetryableMountingLayerException("Unable to find viewState for tag: " + i + " for commandId: " + str);
        }
        if (nullableViewState.mViewManager == null) {
            throw new RetryableMountingLayerException("Unable to find viewState manager for tag " + i);
        }
        if (nullableViewState.mView == null) {
            throw new RetryableMountingLayerException("Unable to find viewState view for tag " + i);
        }
        nullableViewState.mViewManager.receiveCommand((ViewManager) nullableViewState.mView, str, readableArray);
    }

    public void sendAccessibilityEvent(int i, int i2) {
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(i);
        if (viewState.mViewManager == null) {
            throw new RetryableMountingLayerException("Unable to find viewState manager for tag " + i);
        }
        if (viewState.mView == null) {
            throw new RetryableMountingLayerException("Unable to find viewState view for tag " + i);
        }
        viewState.mView.sendAccessibilityEvent(i2);
    }

    public void updateLayout(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(i);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find View for tag: " + i);
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i6, 1073741824));
        ViewParent parent = view.getParent();
        if (parent instanceof RootView) {
            parent.requestLayout();
        }
        ViewState viewState2 = getViewState(i2);
        IViewGroupManager iViewGroupManager = viewState2.mViewManager != null ? (IViewGroupManager) viewState2.mViewManager : null;
        if (iViewGroupManager == null || !iViewGroupManager.needsCustomLayoutForChildren()) {
            view.layout(i3, i4, i5 + i3, i6 + i4);
        }
        int i8 = i7 == 0 ? 4 : 0;
        if (view.getVisibility() != i8) {
            view.setVisibility(i8);
        }
    }

    public void updatePadding(int i, int i2, int i3, int i4, int i5) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(i);
        if (viewState.mIsRoot) {
            return;
        }
        View view = viewState.mView;
        if (view == null) {
            throw new IllegalStateException("Unable to find View for tag: " + i);
        }
        ViewManager viewManager = viewState.mViewManager;
        if (viewManager == null) {
            throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
        }
        viewManager.setPadding(view, i2, i3, i4, i5);
    }

    public void updateOverflowInset(int i, int i2, int i3, int i4, int i5) {
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(i);
        if (viewState.mIsRoot) {
            return;
        }
        KeyEvent.Callback callback = viewState.mView;
        if (callback == null) {
            throw new IllegalStateException("Unable to find View for tag: " + i);
        }
        if (callback instanceof ReactOverflowViewWithInset) {
            ((ReactOverflowViewWithInset) callback).setOverflowInset(i2, i3, i4, i5);
        }
    }

    public void updateState(int i, StateWrapper stateWrapper) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState viewState = getViewState(i);
        StateWrapper stateWrapper2 = viewState.mStateWrapper;
        viewState.mStateWrapper = stateWrapper;
        ViewManager viewManager = viewState.mViewManager;
        if (viewManager == null) {
            throw new IllegalStateException("Unable to find ViewManager for tag: " + i);
        }
        Object updateState = viewManager.updateState(viewState.mView, viewState.mCurrentProps, stateWrapper);
        if (updateState != null) {
            viewManager.updateExtraData(viewState.mView, updateState);
        }
        if (stateWrapper2 != null) {
            stateWrapper2.destroyState();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateEventEmitter(int i, EventEmitterWrapper eventEmitterWrapper) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i));
        View view = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        if (viewState == null) {
            viewState = new ViewState(i, view, (ViewManager) (objArr2 == true ? 1 : 0));
            this.mTagToViewState.put(Integer.valueOf(i), viewState);
        }
        EventEmitterWrapper eventEmitterWrapper2 = viewState.mEventEmitter;
        viewState.mEventEmitter = eventEmitterWrapper;
        if (eventEmitterWrapper2 != eventEmitterWrapper && eventEmitterWrapper2 != null) {
            eventEmitterWrapper2.destroy();
        }
        Queue<PendingViewEvent> queue = viewState.mPendingEventQueue;
        if (queue != null) {
            Iterator<PendingViewEvent> it = queue.iterator();
            while (it.hasNext()) {
                it.next().dispatch(eventEmitterWrapper);
            }
            viewState.mPendingEventQueue = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized void setJSResponder(int i, int i2, boolean z) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        if (!z) {
            this.mJSResponderHandler.setJSResponder(i2, null);
            return;
        }
        ViewState viewState = getViewState(i);
        View view = viewState.mView;
        if (i2 != i && (view instanceof ViewParent)) {
            this.mJSResponderHandler.setJSResponder(i2, (ViewParent) view);
            return;
        }
        if (view == 0) {
            SoftAssertions.assertUnreachable("Cannot find view for tag [" + i + "].");
            return;
        }
        if (viewState.mIsRoot) {
            SoftAssertions.assertUnreachable("Cannot block native responder on [" + i + "] that is a root view");
        }
        this.mJSResponderHandler.setJSResponder(i2, view.getParent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onViewStateDeleted(ViewState viewState) {
        if (viewState.mStateWrapper != null) {
            viewState.mStateWrapper.destroyState();
            viewState.mStateWrapper = null;
        }
        if (viewState.mEventEmitter != null) {
            viewState.mEventEmitter.destroy();
            viewState.mEventEmitter = null;
        }
        ViewManager viewManager = viewState.mViewManager;
        if (viewState.mIsRoot || viewManager == null) {
            return;
        }
        viewManager.onDropViewInstance(viewState.mView);
    }

    public void deleteView(int i) {
        UiThreadUtil.assertOnUiThread();
        if (isStopped()) {
            return;
        }
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState == null) {
            ReactSoftExceptionLogger.logSoftException(MountingManager.TAG, new IllegalStateException("Unable to find viewState for tag: " + i + " for deleteView"));
            return;
        }
        this.mTagToViewState.remove(Integer.valueOf(i));
        onViewStateDeleted(nullableViewState);
    }

    public void preallocateView(String str, int i, ReadableMap readableMap, StateWrapper stateWrapper, EventEmitterWrapper eventEmitterWrapper, boolean z) {
        UiThreadUtil.assertOnUiThread();
        if (!isStopped() && getNullableViewState(i) == null) {
            createViewUnsafe(str, i, readableMap, stateWrapper, eventEmitterWrapper, z);
        }
    }

    public EventEmitterWrapper getEventEmitter(int i) {
        ViewState nullableViewState = getNullableViewState(i);
        if (nullableViewState == null) {
            return null;
        }
        return nullableViewState.mEventEmitter;
    }

    public View getView(int i) {
        ViewState nullableViewState = getNullableViewState(i);
        View view = nullableViewState == null ? null : nullableViewState.mView;
        if (view != null) {
            return view;
        }
        throw new IllegalViewOperationException("Trying to resolve view with tag " + i + " which doesn't exist");
    }

    private ViewState getViewState(int i) {
        ViewState viewState = this.mTagToViewState.get(Integer.valueOf(i));
        if (viewState != null) {
            return viewState;
        }
        throw new RetryableMountingLayerException("Unable to find viewState for tag " + i + ". Surface stopped: " + isStopped());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ViewState getNullableViewState(int i) {
        ConcurrentHashMap<Integer, ViewState> concurrentHashMap = this.mTagToViewState;
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.get(Integer.valueOf(i));
    }

    private static IViewGroupManager<ViewGroup> getViewGroupManager(ViewState viewState) {
        if (viewState.mViewManager == null) {
            throw new IllegalStateException("Unable to find ViewManager for view: " + viewState);
        }
        return (IViewGroupManager) viewState.mViewManager;
    }

    public void printSurfaceState() {
        FLog.e(TAG, "Views created for surface {%d}:", Integer.valueOf(getSurfaceId()));
        for (ViewState viewState : this.mTagToViewState.values()) {
            Integer num = null;
            String name = viewState.mViewManager != null ? viewState.mViewManager.getName() : null;
            View view = viewState.mView;
            View view2 = view != null ? (View) view.getParent() : null;
            if (view2 != null) {
                num = Integer.valueOf(view2.getId());
            }
            FLog.e(TAG, "<%s id=%d parentTag=%s isRoot=%b />", name, Integer.valueOf(viewState.mReactTag), num, Boolean.valueOf(viewState.mIsRoot));
        }
    }

    public void enqueuePendingEvent(int i, String str, boolean z, WritableMap writableMap, int i2) {
        final ViewState viewState;
        ConcurrentHashMap<Integer, ViewState> concurrentHashMap = this.mTagToViewState;
        if (concurrentHashMap == null || (viewState = concurrentHashMap.get(Integer.valueOf(i))) == null) {
            return;
        }
        final PendingViewEvent pendingViewEvent = new PendingViewEvent(str, writableMap, i2, z);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.fabric.mounting.SurfaceMountingManager.4
            @Override // java.lang.Runnable
            public void run() {
                if (viewState.mEventEmitter != null) {
                    pendingViewEvent.dispatch(viewState.mEventEmitter);
                    return;
                }
                if (viewState.mPendingEventQueue == null) {
                    viewState.mPendingEventQueue = new LinkedList();
                }
                viewState.mPendingEventQueue.add(pendingViewEvent);
            }
        });
    }

    private static class ViewState {
        public ReadableMap mCurrentLocalData;
        public ReactStylesDiffMap mCurrentProps;
        public EventEmitterWrapper mEventEmitter;
        final boolean mIsRoot;
        public Queue<PendingViewEvent> mPendingEventQueue;
        final int mReactTag;
        public StateWrapper mStateWrapper;
        final View mView;
        final ViewManager mViewManager;

        private ViewState(int i, View view, ViewManager viewManager) {
            this(i, view, viewManager, false);
        }

        private ViewState(int i, View view, ViewManager viewManager, boolean z) {
            this.mCurrentProps = null;
            this.mCurrentLocalData = null;
            this.mStateWrapper = null;
            this.mEventEmitter = null;
            this.mPendingEventQueue = null;
            this.mReactTag = i;
            this.mView = view;
            this.mIsRoot = z;
            this.mViewManager = viewManager;
        }

        public String toString() {
            return "ViewState [" + this.mReactTag + "] - isRoot: " + this.mIsRoot + " - props: " + this.mCurrentProps + " - localData: " + this.mCurrentLocalData + " - viewManager: " + this.mViewManager + " - isLayoutOnly: " + (this.mViewManager == null);
        }
    }

    private static class PendingViewEvent {
        private final boolean mCanCoalesceEvent;
        private final int mEventCategory;
        private final String mEventName;
        private final WritableMap mParams;

        public PendingViewEvent(String str, WritableMap writableMap, int i, boolean z) {
            this.mEventName = str;
            this.mParams = writableMap;
            this.mEventCategory = i;
            this.mCanCoalesceEvent = z;
        }

        public void dispatch(EventEmitterWrapper eventEmitterWrapper) {
            if (this.mCanCoalesceEvent) {
                eventEmitterWrapper.dispatchUnique(this.mEventName, this.mParams);
            } else {
                eventEmitterWrapper.dispatch(this.mEventName, this.mParams, this.mEventCategory);
            }
        }
    }

    private class RemoveDeleteTreeUIFrameCallback extends GuardedFrameCallback {
        private static final long FRAME_TIME_MS = 16;
        private static final long MAX_TIME_IN_FRAME = 9;

        private RemoveDeleteTreeUIFrameCallback(ReactContext reactContext) {
            super(reactContext);
        }

        private boolean haveExceededNonBatchedFrameTime(long j) {
            return 16 - ((System.nanoTime() - j) / 1000000) < MAX_TIME_IN_FRAME;
        }

        @Override // com.facebook.react.fabric.GuardedFrameCallback
        public void doFrameGuarded(long j) {
            Stack stack = new Stack();
            int i = 0;
            while (!SurfaceMountingManager.this.mReactTagsToRemove.empty()) {
                try {
                    int intValue = ((Integer) SurfaceMountingManager.this.mReactTagsToRemove.pop()).intValue();
                    i++;
                    if (SurfaceMountingManager.this.mErroneouslyReaddedReactTags.contains(Integer.valueOf(intValue))) {
                        ReactSoftExceptionLogger.logSoftException(SurfaceMountingManager.TAG, new IllegalViewOperationException("RemoveDeleteTree recursively tried to remove a React View that was actually reused. This indicates a bug in the Differ. [" + intValue + "]"));
                    } else {
                        stack.clear();
                        ViewState nullableViewState = SurfaceMountingManager.this.getNullableViewState(intValue);
                        if (nullableViewState != null) {
                            View view = nullableViewState.mView;
                            NativeModule nativeModule = nullableViewState.mViewManager;
                            if (nativeModule instanceof IViewGroupManager) {
                                IViewGroupManager iViewGroupManager = (IViewGroupManager) nativeModule;
                                int i2 = 0;
                                boolean z = false;
                                while (true) {
                                    View childAt = iViewGroupManager.getChildAt(view, i2);
                                    if (childAt == null) {
                                        break;
                                    }
                                    int id = childAt.getId();
                                    if (!z && SurfaceMountingManager.this.getNullableViewState(id) == null) {
                                        z = false;
                                        stack.push(Integer.valueOf(childAt.getId()));
                                        i2++;
                                    }
                                    z = true;
                                    stack.push(Integer.valueOf(childAt.getId()));
                                    i2++;
                                }
                                if (z) {
                                    try {
                                        iViewGroupManager.removeAllViews(view);
                                    } catch (RuntimeException e) {
                                        ReactSoftExceptionLogger.logSoftException(SurfaceMountingManager.TAG, e);
                                    }
                                }
                                if (z) {
                                    SurfaceMountingManager.this.mReactTagsToRemove.addAll(stack);
                                }
                            }
                            SurfaceMountingManager.this.mTagToViewState.remove(Integer.valueOf(intValue));
                            SurfaceMountingManager.this.onViewStateDeleted(nullableViewState);
                            if (i % 20 == 0 && haveExceededNonBatchedFrameTime(j)) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                } finally {
                    if (!SurfaceMountingManager.this.mReactTagsToRemove.empty()) {
                        ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this);
                    } else {
                        SurfaceMountingManager.this.mErroneouslyReaddedReactTags.clear();
                        SurfaceMountingManager.this.mReactTagsToRemove.clear();
                    }
                }
            }
        }
    }
}

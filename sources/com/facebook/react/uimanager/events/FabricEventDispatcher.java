package com.facebook.react.uimanager.events;

import android.view.Choreographer;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.systrace.Systrace;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class FabricEventDispatcher implements EventDispatcher, LifecycleEventListener {
    private final ReactApplicationContext mReactContext;
    private final ReactEventEmitter mReactEventEmitter;
    private final CopyOnWriteArrayList<EventDispatcherListener> mListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<BatchEventDispatchedListener> mPostEventDispatchListeners = new CopyOnWriteArrayList<>();
    private final ScheduleDispatchFrameCallback mCurrentFrameCallback = new ScheduleDispatchFrameCallback();

    public FabricEventDispatcher(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mReactEventEmitter = new ReactEventEmitter(reactApplicationContext);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchEvent(Event event) {
        Iterator<EventDispatcherListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onEventDispatch(event);
        }
        event.dispatchModern(this.mReactEventEmitter);
        event.dispose();
        maybePostFrameCallbackFromNonUI();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void dispatchAllEvents() {
        maybePostFrameCallbackFromNonUI();
    }

    private void maybePostFrameCallbackFromNonUI() {
        if (this.mReactEventEmitter != null) {
            this.mCurrentFrameCallback.maybePostFromNonUI();
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addListener(EventDispatcherListener eventDispatcherListener) {
        this.mListeners.add(eventDispatcherListener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeListener(EventDispatcherListener eventDispatcherListener) {
        this.mListeners.remove(eventDispatcherListener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void addBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        this.mPostEventDispatchListeners.add(batchEventDispatchedListener);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void removeBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        this.mPostEventDispatchListeners.remove(batchEventDispatchedListener);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        maybePostFrameCallbackFromNonUI();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void onCatalystInstanceDestroyed() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.events.FabricEventDispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                FabricEventDispatcher.this.stopFrameCallback();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopFrameCallback() {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentFrameCallback.stop();
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void registerEventEmitter(int i, RCTEventEmitter rCTEventEmitter) {
        this.mReactEventEmitter.register(i, rCTEventEmitter);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void registerEventEmitter(int i, RCTModernEventEmitter rCTModernEventEmitter) {
        this.mReactEventEmitter.register(i, rCTModernEventEmitter);
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcher
    public void unregisterEventEmitter(int i) {
        this.mReactEventEmitter.unregister(i);
    }

    private class ScheduleDispatchFrameCallback implements Choreographer.FrameCallback {
        private volatile boolean mIsPosted;
        private boolean mShouldStop;

        public void stop() {
            this.mShouldStop = true;
        }

        private ScheduleDispatchFrameCallback() {
            this.mIsPosted = false;
            this.mShouldStop = false;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            UiThreadUtil.assertOnUiThread();
            if (this.mShouldStop) {
                this.mIsPosted = false;
            } else {
                post();
            }
            Systrace.beginSection(0L, "BatchEventDispatchedListeners");
            try {
                Iterator it = FabricEventDispatcher.this.mPostEventDispatchListeners.iterator();
                while (it.hasNext()) {
                    ((BatchEventDispatchedListener) it.next()).onBatchEventDispatched();
                }
            } finally {
                Systrace.endSection(0L);
            }
        }

        public void maybePost() {
            if (this.mIsPosted) {
                return;
            }
            this.mIsPosted = true;
            post();
        }

        private void post() {
            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, FabricEventDispatcher.this.mCurrentFrameCallback);
        }

        public void maybePostFromNonUI() {
            if (this.mIsPosted) {
                return;
            }
            if (FabricEventDispatcher.this.mReactContext.isOnUiQueueThread()) {
                maybePost();
            } else {
                FabricEventDispatcher.this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.events.FabricEventDispatcher.ScheduleDispatchFrameCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ScheduleDispatchFrameCallback.this.maybePost();
                    }
                });
            }
        }
    }
}

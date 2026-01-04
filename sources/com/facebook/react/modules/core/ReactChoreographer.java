package com.facebook.react.modules.core;

import android.view.Choreographer;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.internal.ChoreographerProvider;
import java.util.ArrayDeque;

/* loaded from: classes.dex */
public final class ReactChoreographer {
    private static ReactChoreographer sInstance;
    private ChoreographerProvider.Choreographer mChoreographer;
    private final Choreographer.FrameCallback mFrameCallback = new Choreographer.FrameCallback() { // from class: com.facebook.react.modules.core.ReactChoreographer.1
        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            synchronized (ReactChoreographer.this.mCallbackQueues) {
                ReactChoreographer.this.mHasPostedCallback = false;
                for (int i = 0; i < ReactChoreographer.this.mCallbackQueues.length; i++) {
                    ArrayDeque arrayDeque = ReactChoreographer.this.mCallbackQueues[i];
                    int size = arrayDeque.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        Choreographer.FrameCallback frameCallback = (Choreographer.FrameCallback) arrayDeque.pollFirst();
                        if (frameCallback != null) {
                            frameCallback.doFrame(j);
                            ReactChoreographer reactChoreographer = ReactChoreographer.this;
                            reactChoreographer.mTotalCallbacks--;
                        } else {
                            FLog.e(ReactConstants.TAG, "Tried to execute non-existent frame callback");
                        }
                    }
                }
                ReactChoreographer.this.maybeRemoveFrameCallback();
            }
        }
    };
    private int mTotalCallbacks = 0;
    private boolean mHasPostedCallback = false;
    private final ArrayDeque<Choreographer.FrameCallback>[] mCallbackQueues = new ArrayDeque[CallbackType.values().length];

    public enum CallbackType {
        PERF_MARKERS(0),
        DISPATCH_UI(1),
        NATIVE_ANIMATED_MODULE(2),
        TIMERS_EVENTS(3),
        IDLE_EVENT(4);

        private final int mOrder;

        int getOrder() {
            return this.mOrder;
        }

        CallbackType(int i) {
            this.mOrder = i;
        }
    }

    public static void initialize(ChoreographerProvider choreographerProvider) {
        if (sInstance == null) {
            sInstance = new ReactChoreographer(choreographerProvider);
        }
    }

    public static ReactChoreographer getInstance() {
        Assertions.assertNotNull(sInstance, "ReactChoreographer needs to be initialized.");
        return sInstance;
    }

    private ReactChoreographer(final ChoreographerProvider choreographerProvider) {
        int i = 0;
        while (true) {
            ArrayDeque<Choreographer.FrameCallback>[] arrayDequeArr = this.mCallbackQueues;
            if (i < arrayDequeArr.length) {
                arrayDequeArr[i] = new ArrayDeque<>();
                i++;
            } else {
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.core.ReactChoreographer$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ReactChoreographer.this.lambda$new$0(choreographerProvider);
                    }
                });
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ChoreographerProvider choreographerProvider) {
        this.mChoreographer = choreographerProvider.getChoreographer();
    }

    public void postFrameCallback(CallbackType callbackType, Choreographer.FrameCallback frameCallback) {
        synchronized (this.mCallbackQueues) {
            this.mCallbackQueues[callbackType.getOrder()].addLast(frameCallback);
            boolean z = true;
            int i = this.mTotalCallbacks + 1;
            this.mTotalCallbacks = i;
            if (i <= 0) {
                z = false;
            }
            Assertions.assertCondition(z);
            if (!this.mHasPostedCallback) {
                if (this.mChoreographer == null) {
                    UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.core.ReactChoreographer$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ReactChoreographer.this.lambda$postFrameCallback$1();
                        }
                    });
                } else {
                    postFrameCallbackOnChoreographer();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$postFrameCallback$1() {
        synchronized (this.mCallbackQueues) {
            postFrameCallbackOnChoreographer();
        }
    }

    private void postFrameCallbackOnChoreographer() {
        this.mChoreographer.postFrameCallback(this.mFrameCallback);
        this.mHasPostedCallback = true;
    }

    public void removeFrameCallback(CallbackType callbackType, Choreographer.FrameCallback frameCallback) {
        synchronized (this.mCallbackQueues) {
            if (this.mCallbackQueues[callbackType.getOrder()].removeFirstOccurrence(frameCallback)) {
                this.mTotalCallbacks--;
                maybeRemoveFrameCallback();
            } else {
                FLog.e(ReactConstants.TAG, "Tried to remove non-existent frame callback");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeRemoveFrameCallback() {
        Assertions.assertCondition(this.mTotalCallbacks >= 0);
        if (this.mTotalCallbacks == 0 && this.mHasPostedCallback) {
            ChoreographerProvider.Choreographer choreographer = this.mChoreographer;
            if (choreographer != null) {
                choreographer.removeFrameCallback(this.mFrameCallback);
            }
            this.mHasPostedCallback = false;
        }
    }
}

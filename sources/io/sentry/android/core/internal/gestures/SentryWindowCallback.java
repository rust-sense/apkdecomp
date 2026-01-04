package io.sentry.android.core.internal.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.Window;
import androidx.core.view.GestureDetectorCompat;
import io.sentry.SentryOptions;
import io.sentry.SpanStatus;

/* loaded from: classes2.dex */
public final class SentryWindowCallback extends WindowCallbackAdapter {
    private final Window.Callback delegate;
    private final GestureDetectorCompat gestureDetector;
    private final SentryGestureListener gestureListener;
    private final MotionEventObtainer motionEventObtainer;
    private final SentryOptions options;

    public Window.Callback getDelegate() {
        return this.delegate;
    }

    public SentryWindowCallback(Window.Callback callback, Context context, SentryGestureListener sentryGestureListener, SentryOptions sentryOptions) {
        this(callback, new GestureDetectorCompat(context, sentryGestureListener), sentryGestureListener, sentryOptions, new MotionEventObtainer() { // from class: io.sentry.android.core.internal.gestures.SentryWindowCallback.1
            @Override // io.sentry.android.core.internal.gestures.SentryWindowCallback.MotionEventObtainer
            public /* synthetic */ MotionEvent obtain(MotionEvent motionEvent) {
                return MotionEventObtainer.CC.$default$obtain(this, motionEvent);
            }
        });
    }

    SentryWindowCallback(Window.Callback callback, GestureDetectorCompat gestureDetectorCompat, SentryGestureListener sentryGestureListener, SentryOptions sentryOptions, MotionEventObtainer motionEventObtainer) {
        super(callback);
        this.delegate = callback;
        this.gestureListener = sentryGestureListener;
        this.options = sentryOptions;
        this.gestureDetector = gestureDetectorCompat;
        this.motionEventObtainer = motionEventObtainer;
    }

    @Override // io.sentry.android.core.internal.gestures.WindowCallbackAdapter, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null) {
            try {
                handleTouchEvent(this.motionEventObtainer.obtain(motionEvent));
            } finally {
                try {
                } finally {
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void handleTouchEvent(MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);
        if (motionEvent.getActionMasked() == 1) {
            this.gestureListener.onUp(motionEvent);
        }
    }

    public void stopTracking() {
        this.gestureListener.stopTracing(SpanStatus.CANCELLED);
    }

    interface MotionEventObtainer {
        MotionEvent obtain(MotionEvent motionEvent);

        /* renamed from: io.sentry.android.core.internal.gestures.SentryWindowCallback$MotionEventObtainer$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static MotionEvent $default$obtain(MotionEventObtainer _this, MotionEvent motionEvent) {
                return MotionEvent.obtain(motionEvent);
            }
        }
    }
}

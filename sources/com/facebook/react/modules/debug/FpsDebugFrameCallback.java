package com.facebook.react.modules.debug;

import android.view.Choreographer;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.Map;
import java.util.TreeMap;
import kotlin.time.DurationKt;

/* loaded from: classes.dex */
public class FpsDebugFrameCallback implements Choreographer.FrameCallback {
    private static final double DEFAULT_FPS = 60.0d;
    private Choreographer mChoreographer;
    private final ReactContext mReactContext;
    private TreeMap<Long, FpsInfo> mTimeToFps;
    private final UIManagerModule mUIManagerModule;
    private long mFirstFrameTime = -1;
    private long mLastFrameTime = -1;
    private int mNumFrameCallbacks = 0;
    private int mExpectedNumFramesPrev = 0;
    private int m4PlusFrameStutters = 0;
    private int mNumFrameCallbacksWithBatchDispatches = 0;
    private boolean mIsRecordingFpsInfoAtEachFrame = false;
    private double mTargetFps = DEFAULT_FPS;
    private final DidJSUpdateUiDuringFrameDetector mDidJSUpdateUiDuringFrameDetector = new DidJSUpdateUiDuringFrameDetector();

    public int get4PlusFrameStutters() {
        return this.m4PlusFrameStutters;
    }

    public int getNumFrames() {
        return this.mNumFrameCallbacks - 1;
    }

    public int getNumJSFrames() {
        return this.mNumFrameCallbacksWithBatchDispatches - 1;
    }

    public void reset() {
        this.mFirstFrameTime = -1L;
        this.mLastFrameTime = -1L;
        this.mNumFrameCallbacks = 0;
        this.m4PlusFrameStutters = 0;
        this.mNumFrameCallbacksWithBatchDispatches = 0;
        this.mIsRecordingFpsInfoAtEachFrame = false;
        this.mTimeToFps = null;
    }

    public static class FpsInfo {
        public final double fps;
        public final double jsFps;
        public final int total4PlusFrameStutters;
        public final int totalExpectedFrames;
        public final int totalFrames;
        public final int totalJsFrames;
        public final int totalTimeMs;

        public FpsInfo(int i, int i2, int i3, int i4, double d, double d2, int i5) {
            this.totalFrames = i;
            this.totalJsFrames = i2;
            this.totalExpectedFrames = i3;
            this.total4PlusFrameStutters = i4;
            this.fps = d;
            this.jsFps = d2;
            this.totalTimeMs = i5;
        }
    }

    public FpsDebugFrameCallback(ReactContext reactContext) {
        this.mReactContext = reactContext;
        this.mUIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        if (this.mFirstFrameTime == -1) {
            this.mFirstFrameTime = j;
        }
        long j2 = this.mLastFrameTime;
        this.mLastFrameTime = j;
        if (this.mDidJSUpdateUiDuringFrameDetector.getDidJSHitFrameAndCleanup(j2, j)) {
            this.mNumFrameCallbacksWithBatchDispatches++;
        }
        this.mNumFrameCallbacks++;
        int expectedNumFrames = getExpectedNumFrames();
        if ((expectedNumFrames - this.mExpectedNumFramesPrev) - 1 >= 4) {
            this.m4PlusFrameStutters++;
        }
        if (this.mIsRecordingFpsInfoAtEachFrame) {
            Assertions.assertNotNull(this.mTimeToFps);
            this.mTimeToFps.put(Long.valueOf(System.currentTimeMillis()), new FpsInfo(getNumFrames(), getNumJSFrames(), expectedNumFrames, this.m4PlusFrameStutters, getFPS(), getJSFPS(), getTotalTimeMS()));
        }
        this.mExpectedNumFramesPrev = expectedNumFrames;
        Choreographer choreographer = this.mChoreographer;
        if (choreographer != null) {
            choreographer.postFrameCallback(this);
        }
    }

    public void start() {
        start(this.mTargetFps);
    }

    public void start(double d) {
        if (!this.mReactContext.isBridgeless()) {
            this.mReactContext.getCatalystInstance().addBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        }
        UIManagerModule uIManagerModule = this.mUIManagerModule;
        if (uIManagerModule != null) {
            uIManagerModule.setViewHierarchyUpdateDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        }
        this.mTargetFps = d;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.debug.FpsDebugFrameCallback$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FpsDebugFrameCallback.this.lambda$start$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$start$0() {
        Choreographer choreographer = Choreographer.getInstance();
        this.mChoreographer = choreographer;
        choreographer.postFrameCallback(this);
    }

    public void startAndRecordFpsAtEachFrame() {
        this.mTimeToFps = new TreeMap<>();
        this.mIsRecordingFpsInfoAtEachFrame = true;
        start();
    }

    public void stop() {
        if (!this.mReactContext.isBridgeless()) {
            this.mReactContext.getCatalystInstance().removeBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        }
        UIManagerModule uIManagerModule = this.mUIManagerModule;
        if (uIManagerModule != null) {
            uIManagerModule.setViewHierarchyUpdateDebugListener(null);
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.debug.FpsDebugFrameCallback$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FpsDebugFrameCallback.this.lambda$stop$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$stop$1() {
        Choreographer choreographer = Choreographer.getInstance();
        this.mChoreographer = choreographer;
        choreographer.removeFrameCallback(this);
    }

    public double getFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0d;
        }
        return (getNumFrames() * 1.0E9d) / (this.mLastFrameTime - this.mFirstFrameTime);
    }

    public double getJSFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0d;
        }
        return (getNumJSFrames() * 1.0E9d) / (this.mLastFrameTime - this.mFirstFrameTime);
    }

    public int getExpectedNumFrames() {
        return (int) (((this.mTargetFps * getTotalTimeMS()) / 1000.0d) + 1.0d);
    }

    public int getTotalTimeMS() {
        return ((int) (this.mLastFrameTime - this.mFirstFrameTime)) / DurationKt.NANOS_IN_MILLIS;
    }

    public FpsInfo getFpsInfo(long j) {
        Assertions.assertNotNull(this.mTimeToFps, "FPS was not recorded at each frame!");
        Map.Entry<Long, FpsInfo> floorEntry = this.mTimeToFps.floorEntry(Long.valueOf(j));
        if (floorEntry == null) {
            return null;
        }
        return floorEntry.getValue();
    }
}

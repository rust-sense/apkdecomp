package com.facebook.fresco.animation.frame;

import com.facebook.fresco.animation.backend.AnimationInformation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DropFramesFrameScheduler.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0006H\u0007J\b\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/fresco/animation/frame/DropFramesFrameScheduler;", "Lcom/facebook/fresco/animation/frame/FrameScheduler;", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "(Lcom/facebook/fresco/animation/backend/AnimationInformation;)V", "_loopDurationMs", "", "getFrameNumberToRender", "", "animationTimeMs", "lastFrameTimeMs", "getFrameNumberWithinLoop", "timeInCurrentLoopMs", "getLoopDurationMs", "getTargetRenderTimeForNextFrameMs", "getTargetRenderTimeMs", "frameNumber", "isInfiniteAnimation", "", "Companion", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DropFramesFrameScheduler implements FrameScheduler {
    private static final int UNSET = -1;
    private long _loopDurationMs;
    private final AnimationInformation animationInformation;

    public DropFramesFrameScheduler(AnimationInformation animationInformation) {
        Intrinsics.checkNotNullParameter(animationInformation, "animationInformation");
        this.animationInformation = animationInformation;
        this._loopDurationMs = -1L;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public int getFrameNumberToRender(long animationTimeMs, long lastFrameTimeMs) {
        long loopDurationMs = getLoopDurationMs();
        if (loopDurationMs == 0) {
            return getFrameNumberWithinLoop(0L);
        }
        if (isInfiniteAnimation() || animationTimeMs / loopDurationMs < this.animationInformation.getLoopCount()) {
            return getFrameNumberWithinLoop(animationTimeMs % loopDurationMs);
        }
        return -1;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getLoopDurationMs() {
        long j = this._loopDurationMs;
        if (j != -1) {
            return j;
        }
        this._loopDurationMs = 0L;
        int frameCount = this.animationInformation.getFrameCount();
        for (int i = 0; i < frameCount; i++) {
            this._loopDurationMs += this.animationInformation.getFrameDurationMs(i);
        }
        return this._loopDurationMs;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getTargetRenderTimeMs(int frameNumber) {
        long j = 0;
        for (int i = 0; i < frameNumber; i++) {
            j += this.animationInformation.getFrameDurationMs(frameNumber);
        }
        return j;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getTargetRenderTimeForNextFrameMs(long animationTimeMs) {
        long loopDurationMs = getLoopDurationMs();
        long j = 0;
        if (loopDurationMs == 0) {
            return -1L;
        }
        if (!isInfiniteAnimation() && animationTimeMs / loopDurationMs >= this.animationInformation.getLoopCount()) {
            return -1L;
        }
        long j2 = animationTimeMs % loopDurationMs;
        int frameCount = this.animationInformation.getFrameCount();
        for (int i = 0; i < frameCount && j <= j2; i++) {
            j += this.animationInformation.getFrameDurationMs(i);
        }
        return animationTimeMs + (j - j2);
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public boolean isInfiniteAnimation() {
        return this.animationInformation.getLoopCount() == 0;
    }

    public final int getFrameNumberWithinLoop(long timeInCurrentLoopMs) {
        int i = 0;
        long j = 0;
        while (true) {
            j += this.animationInformation.getFrameDurationMs(i);
            int i2 = i + 1;
            if (timeInCurrentLoopMs < j) {
                return i;
            }
            i = i2;
        }
    }
}

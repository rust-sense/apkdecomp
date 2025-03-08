package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapFramePreparationStrategy.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J(\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J:\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\b2\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015H\u0016J(\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015H\u0016¨\u0006\u0016"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparationStrategy;", "", "clearFrames", "", "getBitmapFrame", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "frameNumber", "", "canvasWidth", "canvasHeight", "onStop", "prepareFrames", "bitmapFramePreparer", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;", "bitmapFrameCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "animationBackend", "Lcom/facebook/fresco/animation/backend/AnimationBackend;", "lastDrawnFrameNumber", "onAnimationLoaded", "Lkotlin/Function0;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface BitmapFramePreparationStrategy {
    void clearFrames();

    CloseableReference<Bitmap> getBitmapFrame(int frameNumber, int canvasWidth, int canvasHeight);

    void onStop();

    void prepareFrames(int canvasWidth, int canvasHeight, Function0<Unit> onAnimationLoaded);

    void prepareFrames(BitmapFramePreparer bitmapFramePreparer, BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int lastDrawnFrameNumber, Function0<Unit> onAnimationLoaded);

    /* compiled from: BitmapFramePreparationStrategy.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void clearFrames(BitmapFramePreparationStrategy bitmapFramePreparationStrategy) {
        }

        public static CloseableReference<Bitmap> getBitmapFrame(BitmapFramePreparationStrategy bitmapFramePreparationStrategy, int i, int i2, int i3) {
            return null;
        }

        public static void onStop(BitmapFramePreparationStrategy bitmapFramePreparationStrategy) {
        }

        public static void prepareFrames(BitmapFramePreparationStrategy bitmapFramePreparationStrategy, int i, int i2, Function0<Unit> function0) {
        }

        public static void prepareFrames(BitmapFramePreparationStrategy bitmapFramePreparationStrategy, BitmapFramePreparer bitmapFramePreparer, BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int i, Function0<Unit> function0) {
            Intrinsics.checkNotNullParameter(bitmapFramePreparer, "bitmapFramePreparer");
            Intrinsics.checkNotNullParameter(bitmapFrameCache, "bitmapFrameCache");
            Intrinsics.checkNotNullParameter(animationBackend, "animationBackend");
        }

        public static /* synthetic */ void prepareFrames$default(BitmapFramePreparationStrategy bitmapFramePreparationStrategy, BitmapFramePreparer bitmapFramePreparer, BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int i, Function0 function0, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: prepareFrames");
            }
            if ((i2 & 16) != 0) {
                function0 = null;
            }
            bitmapFramePreparationStrategy.prepareFrames(bitmapFramePreparer, bitmapFrameCache, animationBackend, i, function0);
        }
    }
}

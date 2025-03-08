package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFramePriorityTask;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoadFrameTaskFactory.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ&\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJF\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\n2\u001a\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00150\u00142\u001a\u0010\f\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00170\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTaskFactory;", "", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "(Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;)V", "createFirstFrameTask", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTask;", "width", "", "height", "output", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameOutput;", "createLoadFullAnimationTask", "frameCount", "createOnDemandTask", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadOnDemandFrameTask;", "frameNumber", "getCachedBitmap", "Lkotlin/Function1;", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LoadFrameTaskFactory {
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private final PlatformBitmapFactory platformBitmapFactory;

    public LoadFrameTaskFactory(PlatformBitmapFactory platformBitmapFactory, BitmapFrameRenderer bitmapFrameRenderer) {
        Intrinsics.checkNotNullParameter(platformBitmapFactory, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer, "bitmapFrameRenderer");
        this.platformBitmapFactory = platformBitmapFactory;
        this.bitmapFrameRenderer = bitmapFrameRenderer;
    }

    public final LoadFrameTask createFirstFrameTask(int width, int height, LoadFrameOutput output) {
        Intrinsics.checkNotNullParameter(output, "output");
        return new LoadFrameTask(width, height, 1, LoadFramePriorityTask.Priority.HIGH, output, this.platformBitmapFactory, this.bitmapFrameRenderer);
    }

    public final LoadFrameTask createLoadFullAnimationTask(int width, int height, int frameCount, LoadFrameOutput output) {
        Intrinsics.checkNotNullParameter(output, "output");
        return new LoadFrameTask(width, height, frameCount, LoadFramePriorityTask.Priority.LOW, output, this.platformBitmapFactory, this.bitmapFrameRenderer);
    }

    public final LoadOnDemandFrameTask createOnDemandTask(int frameNumber, Function1<? super Integer, ? extends CloseableReference<Bitmap>> getCachedBitmap, Function1<? super CloseableReference<Bitmap>, Unit> output) {
        Intrinsics.checkNotNullParameter(getCachedBitmap, "getCachedBitmap");
        Intrinsics.checkNotNullParameter(output, "output");
        return new LoadOnDemandFrameTask(frameNumber, getCachedBitmap, LoadFramePriorityTask.Priority.MEDIUM, output, this.platformBitmapFactory, this.bitmapFrameRenderer);
    }
}

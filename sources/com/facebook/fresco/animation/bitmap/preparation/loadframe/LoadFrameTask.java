package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFramePriorityTask;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import io.sentry.protocol.SentryThread;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: LoadFrameTask.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTask;", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask;", "width", "", "height", "untilFrame", SentryThread.JsonKeys.PRIORITY, "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "output", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameOutput;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "(IIILcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameOutput;Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;)V", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getPriority", "()Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "run", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LoadFrameTask implements LoadFramePriorityTask {
    private final Bitmap.Config bitmapConfig;
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private final int height;
    private final LoadFrameOutput output;
    private final PlatformBitmapFactory platformBitmapFactory;
    private final LoadFramePriorityTask.Priority priority;
    private final int untilFrame;
    private final int width;

    @Override // com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFramePriorityTask
    public LoadFramePriorityTask.Priority getPriority() {
        return this.priority;
    }

    public LoadFrameTask(int i, int i2, int i3, LoadFramePriorityTask.Priority priority, LoadFrameOutput output, PlatformBitmapFactory platformBitmapFactory, BitmapFrameRenderer bitmapFrameRenderer) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(platformBitmapFactory, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer, "bitmapFrameRenderer");
        this.width = i;
        this.height = i2;
        this.untilFrame = i3;
        this.priority = priority;
        this.output = output;
        this.platformBitmapFactory = platformBitmapFactory;
        this.bitmapFrameRenderer = bitmapFrameRenderer;
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(LoadFramePriorityTask loadFramePriorityTask) {
        return LoadFramePriorityTask.DefaultImpls.compareTo(this, loadFramePriorityTask);
    }

    @Override // java.lang.Runnable
    public void run() {
        Bitmap bitmap;
        boolean z;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        CloseableReference<Bitmap> createBitmap = this.platformBitmapFactory.createBitmap(this.width, this.height, this.bitmapConfig);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "platformBitmapFactory.cr…th, height, bitmapConfig)");
        Iterator<Integer> it = RangesKt.until(0, this.untilFrame).iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            if (CloseableReference.isValid(createBitmap)) {
                bitmap = createBitmap.get();
                z = this.bitmapFrameRenderer.renderFrame(nextInt, bitmap);
            } else {
                bitmap = null;
                z = false;
            }
            if (bitmap == null || !z) {
                CloseableReference.closeSafely(createBitmap);
                Iterator it2 = linkedHashMap.values().iterator();
                while (it2.hasNext()) {
                    CloseableReference.closeSafely((CloseableReference<?>) it2.next());
                }
                this.output.onFail();
            } else {
                CloseableReference<Bitmap> createBitmap2 = this.platformBitmapFactory.createBitmap(bitmap);
                Intrinsics.checkNotNullExpressionValue(createBitmap2, "platformBitmapFactory.createBitmap(currentFrame)");
                linkedHashMap.put(Integer.valueOf(nextInt), createBitmap2);
            }
        }
        CloseableReference.closeSafely(createBitmap);
        this.output.onSuccess(linkedHashMap);
    }
}

package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFramePriorityTask;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import io.sentry.protocol.SentryThread;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;

/* compiled from: LoadOnDemandFrameTask.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00060\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u001a\u0010\n\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u000b0\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0013\u001a\u00020\u000b2\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0002J\b\u0010\u0015\u001a\u00020\u000bH\u0016R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u000b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadOnDemandFrameTask;", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask;", "frameNumber", "", "getCachedBitmap", "Lkotlin/Function1;", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", SentryThread.JsonKeys.PRIORITY, "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "output", "", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "(ILkotlin/jvm/functions/Function1;Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;Lkotlin/jvm/functions/Function1;Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;)V", "getPriority", "()Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFramePriorityTask$Priority;", "exit", "bitmap", "run", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LoadOnDemandFrameTask implements LoadFramePriorityTask {
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private final int frameNumber;
    private final Function1<Integer, CloseableReference<Bitmap>> getCachedBitmap;
    private final Function1<CloseableReference<Bitmap>, Unit> output;
    private final PlatformBitmapFactory platformBitmapFactory;
    private final LoadFramePriorityTask.Priority priority;

    @Override // com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFramePriorityTask
    public LoadFramePriorityTask.Priority getPriority() {
        return this.priority;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LoadOnDemandFrameTask(int i, Function1<? super Integer, ? extends CloseableReference<Bitmap>> getCachedBitmap, LoadFramePriorityTask.Priority priority, Function1<? super CloseableReference<Bitmap>, Unit> output, PlatformBitmapFactory platformBitmapFactory, BitmapFrameRenderer bitmapFrameRenderer) {
        Intrinsics.checkNotNullParameter(getCachedBitmap, "getCachedBitmap");
        Intrinsics.checkNotNullParameter(priority, "priority");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(platformBitmapFactory, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer, "bitmapFrameRenderer");
        this.frameNumber = i;
        this.getCachedBitmap = getCachedBitmap;
        this.priority = priority;
        this.output = output;
        this.platformBitmapFactory = platformBitmapFactory;
        this.bitmapFrameRenderer = bitmapFrameRenderer;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(LoadFramePriorityTask loadFramePriorityTask) {
        return LoadFramePriorityTask.DefaultImpls.compareTo(this, loadFramePriorityTask);
    }

    @Override // java.lang.Runnable
    public void run() {
        Pair pair = (Pair) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(RangesKt.downTo(this.frameNumber, 0)), new Function1<Integer, Pair<? extends Integer, ? extends CloseableReference<Bitmap>>>() { // from class: com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadOnDemandFrameTask$run$nearestFrame$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends CloseableReference<Bitmap>> invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Pair<Integer, CloseableReference<Bitmap>> invoke(int i) {
                Function1 function1;
                function1 = LoadOnDemandFrameTask.this.getCachedBitmap;
                CloseableReference closeableReference = (CloseableReference) function1.invoke(Integer.valueOf(i));
                if (closeableReference == null) {
                    return null;
                }
                return new Pair<>(Integer.valueOf(i), closeableReference);
            }
        }));
        if (pair == null) {
            exit(null);
            return;
        }
        CloseableReference<Bitmap> createBitmap = this.platformBitmapFactory.createBitmap((Bitmap) ((CloseableReference) pair.getSecond()).get());
        Intrinsics.checkNotNullExpressionValue(createBitmap, "platformBitmapFactory.cr…earestFrame.second.get())");
        Iterator<Integer> it = new IntRange(((Number) pair.getFirst()).intValue() + 1, this.frameNumber).iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            BitmapFrameRenderer bitmapFrameRenderer = this.bitmapFrameRenderer;
            Bitmap bitmap = createBitmap.get();
            Intrinsics.checkNotNullExpressionValue(bitmap, "canvasBitmap.get()");
            bitmapFrameRenderer.renderFrame(nextInt, bitmap);
        }
        exit(createBitmap);
    }

    private final void exit(CloseableReference<Bitmap> bitmap) {
        this.output.invoke(bitmap);
    }
}

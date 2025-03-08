package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationInformation;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.AnimationLoaderExecutor;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.FpsCompressorInfo;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFramePriorityTask;
import com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: BufferFrameLoader.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 72\u00020\u0001:\u000267B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000fH\u0016J*\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u000fH\u0003J\u0012\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010!\u001a\u00020\u000fH\u0002J\u0010\u0010'\u001a\u00020(2\u0006\u0010!\u001a\u00020\u000fH\u0003J \u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0017J\u0018\u0010+\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0002J(\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0002J\b\u0010.\u001a\u00020\u001dH\u0016J&\u0010/\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000f2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u001d01H\u0017J\u0012\u0010\u001c\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020302H\u0002J\f\u0010\u001f\u001a\u00020\u000f*\u00020\tH\u0002J \u00104\u001a\b\u0012\u0004\u0012\u00020302*\b\u0012\u0004\u0012\u000203022\u0006\u00105\u001a\u000203H\u0002R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/BufferFrameLoader;", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoader;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "fpsCompressor", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo;", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "(Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo;Lcom/facebook/fresco/animation/backend/AnimationInformation;)V", "getAnimationInformation", "()Lcom/facebook/fresco/animation/backend/AnimationInformation;", "bufferFramesHash", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/BufferFrameLoader$BufferFrame;", "bufferSize", "compressionFrameMap", "", "frameSequence", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/CircularList;", "isFetching", "", "lastRenderedFrameNumber", "renderableFrameIndexes", "", "thresholdFrame", "clear", "", "compressToFps", "fps", "extractDemandedFrame", "targetFrame", "width", "height", "count", "findNearestFrame", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/AnimationBitmapFrame;", "findNearestToRender", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameResult;", "getFrame", "frameNumber", "loadNextFrames", "obtainFrame", "bufferFrame", "onStop", "prepareFrames", "onAnimationLoaded", "Lkotlin/Function0;", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "set", "src", "BufferFrame", "Companion", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BufferFrameLoader implements FrameLoader {
    private static final int BUFFER_SECOND_SIZE = 1;
    private static final float THRESHOLD_PERCENTAGE = 0.5f;
    private final AnimationInformation animationInformation;
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private final ConcurrentHashMap<Integer, BufferFrame> bufferFramesHash;
    private final int bufferSize;
    private Map<Integer, Integer> compressionFrameMap;
    private final FpsCompressorInfo fpsCompressor;
    private final CircularList frameSequence;
    private volatile boolean isFetching;
    private int lastRenderedFrameNumber;
    private final PlatformBitmapFactory platformBitmapFactory;
    private Set<Integer> renderableFrameIndexes;
    private volatile int thresholdFrame;

    @Override // com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoader
    public AnimationInformation getAnimationInformation() {
        return this.animationInformation;
    }

    public BufferFrameLoader(PlatformBitmapFactory platformBitmapFactory, BitmapFrameRenderer bitmapFrameRenderer, FpsCompressorInfo fpsCompressor, AnimationInformation animationInformation) {
        Intrinsics.checkNotNullParameter(platformBitmapFactory, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer, "bitmapFrameRenderer");
        Intrinsics.checkNotNullParameter(fpsCompressor, "fpsCompressor");
        Intrinsics.checkNotNullParameter(animationInformation, "animationInformation");
        this.platformBitmapFactory = platformBitmapFactory;
        this.bitmapFrameRenderer = bitmapFrameRenderer;
        this.fpsCompressor = fpsCompressor;
        this.animationInformation = animationInformation;
        int fps = fps(getAnimationInformation());
        this.bufferSize = fps;
        this.bufferFramesHash = new ConcurrentHashMap<>();
        this.frameSequence = new CircularList(getAnimationInformation().getFrameCount());
        this.lastRenderedFrameNumber = -1;
        this.compressionFrameMap = MapsKt.emptyMap();
        this.renderableFrameIndexes = SetsKt.emptySet();
        compressToFps(fps(getAnimationInformation()));
        this.thresholdFrame = (int) (fps * 0.5f);
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoader
    public FrameResult getFrame(int frameNumber, int width, int height) {
        Integer num = this.compressionFrameMap.get(Integer.valueOf(frameNumber));
        if (num == null) {
            return findNearestToRender(frameNumber);
        }
        int intValue = num.intValue();
        this.lastRenderedFrameNumber = intValue;
        BufferFrame bufferFrame = this.bufferFramesHash.get(Integer.valueOf(intValue));
        if (bufferFrame == null || !bufferFrame.isFrameAvailable()) {
            bufferFrame = null;
        }
        if (bufferFrame != null) {
            if (this.frameSequence.isTargetAhead(this.thresholdFrame, intValue, this.bufferSize)) {
                loadNextFrames(width, height);
            }
            return new FrameResult(bufferFrame.getBitmapRef().mo194clone(), FrameResult.FrameType.SUCCESS);
        }
        loadNextFrames(width, height);
        return findNearestToRender(intValue);
    }

    private final FrameResult findNearestToRender(int targetFrame) {
        AnimationBitmapFrame findNearestFrame = findNearestFrame(targetFrame);
        if (findNearestFrame != null) {
            CloseableReference<Bitmap> mo194clone = findNearestFrame.getBitmap().mo194clone();
            Intrinsics.checkNotNullExpressionValue(mo194clone, "nearestFrame.bitmap.clone()");
            this.lastRenderedFrameNumber = findNearestFrame.getFrameNumber();
            return new FrameResult(mo194clone, FrameResult.FrameType.NEAREST);
        }
        return new FrameResult(null, FrameResult.FrameType.MISSING);
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoader
    public void prepareFrames(int width, int height, Function0<Unit> onAnimationLoaded) {
        Intrinsics.checkNotNullParameter(onAnimationLoaded, "onAnimationLoaded");
        loadNextFrames(width, height);
        onAnimationLoaded.invoke();
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoader
    public void compressToFps(int fps) {
        Map<Integer, Integer> calculateReducedIndexes = this.fpsCompressor.calculateReducedIndexes(getAnimationInformation().getLoopDurationMs() * RangesKt.coerceAtLeast(getAnimationInformation().getLoopCount(), 1), getAnimationInformation().getFrameCount(), RangesKt.coerceAtMost(fps, fps(getAnimationInformation())));
        this.compressionFrameMap = calculateReducedIndexes;
        this.renderableFrameIndexes = CollectionsKt.toSet(calculateReducedIndexes.values());
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoader
    public void onStop() {
        AnimationBitmapFrame findNearestFrame = findNearestFrame(this.lastRenderedFrameNumber);
        Set<Integer> keySet = this.bufferFramesHash.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "bufferFramesHash.keys");
        for (Integer num : CollectionsKt.filterNotNull(SetsKt.minus(keySet, findNearestFrame != null ? Integer.valueOf(findNearestFrame.getFrameNumber()) : null))) {
            BufferFrame bufferFrame = this.bufferFramesHash.get(num);
            if (bufferFrame != null) {
                bufferFrame.release();
            }
            this.bufferFramesHash.remove(num);
        }
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoader
    public void clear() {
        Collection<BufferFrame> values = this.bufferFramesHash.values();
        Intrinsics.checkNotNullExpressionValue(values, "bufferFramesHash.values");
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            ((BufferFrame) it.next()).release();
        }
        this.bufferFramesHash.clear();
        this.lastRenderedFrameNumber = -1;
    }

    private final void loadNextFrames(final int width, final int height) {
        if (this.isFetching) {
            return;
        }
        this.isFetching = true;
        AnimationLoaderExecutor.INSTANCE.execute(new LoadFramePriorityTask() { // from class: com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.BufferFrameLoader$loadNextFrames$1
            private final LoadFramePriorityTask.Priority priority = LoadFramePriorityTask.Priority.HIGH;

            @Override // com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFramePriorityTask
            public LoadFramePriorityTask.Priority getPriority() {
                return this.priority;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.Comparable
            public int compareTo(LoadFramePriorityTask loadFramePriorityTask) {
                return LoadFramePriorityTask.DefaultImpls.compareTo(this, loadFramePriorityTask);
            }

            @Override // java.lang.Runnable
            public void run() {
                int i;
                do {
                    i = BufferFrameLoader.this.lastRenderedFrameNumber;
                } while (!BufferFrameLoader.extractDemandedFrame$default(BufferFrameLoader.this, RangesKt.coerceAtLeast(i, 0), width, height, 0, 8, null));
                BufferFrameLoader.this.isFetching = false;
            }
        });
    }

    static /* synthetic */ boolean extractDemandedFrame$default(BufferFrameLoader bufferFrameLoader, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        return bufferFrameLoader.extractDemandedFrame(i, i2, i3, i4);
    }

    private final boolean extractDemandedFrame(int targetFrame, int width, int height, int count) {
        int intValue;
        List<Integer> sublist = this.frameSequence.sublist(targetFrame, this.bufferSize);
        ArrayList arrayList = new ArrayList();
        for (Object obj : sublist) {
            if (this.renderableFrameIndexes.contains(Integer.valueOf(((Number) obj).intValue()))) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = arrayList2;
        Set set = CollectionsKt.toSet(arrayList3);
        Set<Integer> keySet = this.bufferFramesHash.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "bufferFramesHash.keys");
        ArrayDeque arrayDeque = new ArrayDeque(SetsKt.minus((Set) keySet, (Iterable) set));
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            int intValue2 = ((Number) it.next()).intValue();
            if (this.bufferFramesHash.get(Integer.valueOf(intValue2)) == null) {
                int i = this.lastRenderedFrameNumber;
                if (i != -1 && !set.contains(Integer.valueOf(i))) {
                    return false;
                }
                int i2 = (Integer) arrayDeque.pollFirst();
                if (i2 == null) {
                    i2 = -1;
                }
                Intrinsics.checkNotNullExpressionValue(i2, "oldFramesNumbers.pollFirst() ?: -1");
                int intValue3 = i2.intValue();
                BufferFrame bufferFrame = this.bufferFramesHash.get(Integer.valueOf(intValue3));
                if (bufferFrame == null) {
                    CloseableReference<Bitmap> createBitmap = this.platformBitmapFactory.createBitmap(width, height);
                    Intrinsics.checkNotNullExpressionValue(createBitmap, "platformBitmapFactory.createBitmap(width, height)");
                    bufferFrame = new BufferFrame(createBitmap);
                }
                Intrinsics.checkNotNullExpressionValue(bufferFrame, "bufferFramesHash[depreca…ateBitmap(width, height))");
                bufferFrame.setUpdatingFrame(true);
                obtainFrame(bufferFrame, intValue2, width, height);
                this.bufferFramesHash.remove(Integer.valueOf(intValue3));
                bufferFrame.setUpdatingFrame(false);
                this.bufferFramesHash.put(Integer.valueOf(intValue2), bufferFrame);
            }
        }
        if (arrayList2.isEmpty()) {
            intValue = (int) (this.bufferSize * 0.5f);
        } else {
            int size = arrayList2.size();
            intValue = ((Number) arrayList2.get(RangesKt.coerceIn((int) (size * 0.5f), 0, size - 1))).intValue();
        }
        this.thresholdFrame = intValue;
        return true;
    }

    private final void obtainFrame(BufferFrame bufferFrame, int targetFrame, int width, int height) {
        int frameNumber;
        AnimationBitmapFrame findNearestFrame = findNearestFrame(targetFrame);
        CloseableReference<Bitmap> bitmap = findNearestFrame != null ? findNearestFrame.getBitmap() : null;
        if (findNearestFrame != null && bitmap != null && (frameNumber = findNearestFrame.getFrameNumber()) < targetFrame) {
            CloseableReference<Bitmap> bitmapRef = bufferFrame.getBitmapRef();
            Bitmap bitmap2 = bitmap.get();
            Intrinsics.checkNotNullExpressionValue(bitmap2, "nearestBitmap.get()");
            set(bitmapRef, bitmap2);
            Iterator<Integer> it = new IntRange(frameNumber + 1, targetFrame).iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                BitmapFrameRenderer bitmapFrameRenderer = this.bitmapFrameRenderer;
                Bitmap bitmap3 = bitmapRef.get();
                Intrinsics.checkNotNullExpressionValue(bitmap3, "targetBitmap.get()");
                bitmapFrameRenderer.renderFrame(nextInt, bitmap3);
            }
            return;
        }
        CloseableReference<Bitmap> bitmapRef2 = bufferFrame.getBitmapRef();
        clear(bitmapRef2);
        Iterator<Integer> it2 = new IntRange(0, targetFrame).iterator();
        while (it2.hasNext()) {
            int nextInt2 = ((IntIterator) it2).nextInt();
            BitmapFrameRenderer bitmapFrameRenderer2 = this.bitmapFrameRenderer;
            Bitmap bitmap4 = bitmapRef2.get();
            Intrinsics.checkNotNullExpressionValue(bitmap4, "targetBitmap.get()");
            bitmapFrameRenderer2.renderFrame(nextInt2, bitmap4);
        }
    }

    private final AnimationBitmapFrame findNearestFrame(int targetFrame) {
        AnimationBitmapFrame animationBitmapFrame;
        Iterator<Integer> it = new IntRange(0, this.frameSequence.getSize()).iterator();
        do {
            animationBitmapFrame = null;
            if (!it.hasNext()) {
                break;
            }
            int position = this.frameSequence.getPosition(targetFrame - ((IntIterator) it).nextInt());
            BufferFrame bufferFrame = this.bufferFramesHash.get(Integer.valueOf(position));
            if (bufferFrame != null) {
                if (!bufferFrame.isFrameAvailable()) {
                    bufferFrame = null;
                }
                if (bufferFrame != null) {
                    animationBitmapFrame = new AnimationBitmapFrame(position, bufferFrame.getBitmapRef());
                }
            }
        } while (animationBitmapFrame == null);
        return animationBitmapFrame;
    }

    private final void clear(CloseableReference<Bitmap> closeableReference) {
        if (closeableReference.isValid()) {
            new Canvas(closeableReference.get()).drawColor(0, PorterDuff.Mode.CLEAR);
        }
    }

    private final CloseableReference<Bitmap> set(CloseableReference<Bitmap> closeableReference, Bitmap bitmap) {
        if (closeableReference.isValid() && !Intrinsics.areEqual(closeableReference.get(), bitmap)) {
            Canvas canvas = new Canvas(closeableReference.get());
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
        return closeableReference;
    }

    private final int fps(AnimationInformation animationInformation) {
        return (int) RangesKt.coerceAtLeast(TimeUnit.SECONDS.toMillis(1L) / (animationInformation.getLoopDurationMs() / animationInformation.getFrameCount()), 1L);
    }

    /* compiled from: BufferFrameLoader.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\n\"\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/BufferFrameLoader$BufferFrame;", "", "bitmapRef", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "(Lcom/facebook/common/references/CloseableReference;)V", "getBitmapRef", "()Lcom/facebook/common/references/CloseableReference;", "isFrameAvailable", "", "()Z", "isUpdatingFrame", "setUpdatingFrame", "(Z)V", "release", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class BufferFrame {
        private final CloseableReference<Bitmap> bitmapRef;
        private boolean isUpdatingFrame;

        public final CloseableReference<Bitmap> getBitmapRef() {
            return this.bitmapRef;
        }

        /* renamed from: isUpdatingFrame, reason: from getter */
        public final boolean getIsUpdatingFrame() {
            return this.isUpdatingFrame;
        }

        public final void setUpdatingFrame(boolean z) {
            this.isUpdatingFrame = z;
        }

        public BufferFrame(CloseableReference<Bitmap> bitmapRef) {
            Intrinsics.checkNotNullParameter(bitmapRef, "bitmapRef");
            this.bitmapRef = bitmapRef;
        }

        public final boolean isFrameAvailable() {
            return !this.isUpdatingFrame && this.bitmapRef.isValid();
        }

        public final void release() {
            CloseableReference.closeSafely(this.bitmapRef);
        }
    }
}

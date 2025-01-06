package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationInformation;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.AnimationLoaderExecutor;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameOutput;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameTask;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameTaskFactory;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: BalancedAnimationStrategy.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0002J\b\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$2\u0006\u0010&\u001a\u00020\u0005H\u0002J\u0017\u0010'\u001a\u0004\u0018\u00010\u00052\u0006\u0010(\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010)J(\u0010*\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$2\u0006\u0010+\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0017J\b\u0010,\u001a\u00020\u000bH\u0002J\u0010\u0010-\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u0005H\u0002J \u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u001e2\u000e\u00101\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u000102H\u0002J\b\u00103\u001a\u00020\"H\u0016J(\u00104\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u000e\u00105\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u000102H\u0017J\u0010\u00106\u001a\u00020\"2\u0006\u00107\u001a\u00020\u0005H\u0002R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/BalancedAnimationStrategy;", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparationStrategy;", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "onDemandPreparationMs", "", "loadFrameTaskFactory", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTaskFactory;", "bitmapCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "downscaleFrameToDrawableDimensions", "", "(Lcom/facebook/fresco/animation/backend/AnimationInformation;ILcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTaskFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;Z)V", "animationHeight", "animationWidth", "fetchingFrames", "Ljava/util/concurrent/atomic/AtomicBoolean;", "fetchingOnDemand", "frameCount", "framesCached", "getFramesCached", "()Z", "nextPrepareFrames", "", "onDemandBitmap", "Lcom/facebook/fresco/animation/bitmap/preparation/OnDemandFrame;", "onDemandFrames", "Ljava/util/SortedSet;", "onDemandRatio", "calculateFrameSize", "Lcom/facebook/fresco/animation/bitmap/preparation/Size;", "canvasWidth", "canvasHeight", "clearFrames", "", "findNearestFrame", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "fromFrame", "findNextOnDemandFrame", Constants.MessagePayloadKeys.FROM, "(I)Ljava/lang/Integer;", "getBitmapFrame", "frameNumber", "isFirstFrameReady", "isOnDemandFrame", "loadAllFrames", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTask;", "frameSize", "notifyOnLoad", "Lkotlin/Function0;", "onStop", "prepareFrames", "onAnimationLoaded", "prepareNextOnDemandFrame", "lastFrameRendered", "Companion", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BalancedAnimationStrategy implements BitmapFramePreparationStrategy {
    private static final int FETCH_FIRST_CACHE_DELAY_MS = 500;
    private static final long FETCH_FULL_ANIMATION_CACHE_DELAY_MS = TimeUnit.SECONDS.toMillis(5);
    private final int animationHeight;
    private final int animationWidth;
    private final BitmapFrameCache bitmapCache;
    private final boolean downscaleFrameToDrawableDimensions;
    private final AtomicBoolean fetchingFrames;
    private final AtomicBoolean fetchingOnDemand;
    private final int frameCount;
    private final LoadFrameTaskFactory loadFrameTaskFactory;
    private long nextPrepareFrames;
    private OnDemandFrame onDemandBitmap;
    private final SortedSet<Integer> onDemandFrames;
    private final int onDemandRatio;

    public BalancedAnimationStrategy(AnimationInformation animationInformation, int i, LoadFrameTaskFactory loadFrameTaskFactory, BitmapFrameCache bitmapCache, boolean z) {
        Intrinsics.checkNotNullParameter(animationInformation, "animationInformation");
        Intrinsics.checkNotNullParameter(loadFrameTaskFactory, "loadFrameTaskFactory");
        Intrinsics.checkNotNullParameter(bitmapCache, "bitmapCache");
        this.loadFrameTaskFactory = loadFrameTaskFactory;
        this.bitmapCache = bitmapCache;
        this.downscaleFrameToDrawableDimensions = z;
        this.fetchingFrames = new AtomicBoolean(false);
        this.fetchingOnDemand = new AtomicBoolean(false);
        this.onDemandFrames = SetsKt.sortedSetOf(new Integer[0]);
        this.nextPrepareFrames = SystemClock.uptimeMillis();
        this.frameCount = animationInformation.getFrameCount();
        this.animationWidth = animationInformation.width();
        this.animationHeight = animationInformation.height();
        this.onDemandRatio = RangesKt.coerceAtLeast((int) Math.ceil(i / (animationInformation.getLoopDurationMs() / r4)), 2);
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy
    public void prepareFrames(BitmapFramePreparer bitmapFramePreparer, BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int i, Function0<Unit> function0) {
        BitmapFramePreparationStrategy.DefaultImpls.prepareFrames(this, bitmapFramePreparer, bitmapFrameCache, animationBackend, i, function0);
    }

    private final boolean getFramesCached() {
        return this.bitmapCache.isAnimationReady();
    }

    private final boolean isFirstFrameReady() {
        CloseableReference<Bitmap> cachedFrame = this.bitmapCache.getCachedFrame(0);
        return cachedFrame != null && cachedFrame.isValid();
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy
    public void prepareFrames(int canvasWidth, int canvasHeight, final Function0<Unit> onAnimationLoaded) {
        LoadFrameTask loadAllFrames;
        if (canvasWidth <= 0 || canvasHeight <= 0 || this.animationWidth <= 0 || this.animationHeight <= 0) {
            return;
        }
        if (getFramesCached() || this.fetchingFrames.get() || SystemClock.uptimeMillis() < this.nextPrepareFrames) {
            if (!getFramesCached() || onAnimationLoaded == null) {
                return;
            }
            onAnimationLoaded.invoke();
            return;
        }
        this.fetchingFrames.set(true);
        final Size calculateFrameSize = calculateFrameSize(canvasWidth, canvasHeight);
        if (!isFirstFrameReady()) {
            loadAllFrames = this.loadFrameTaskFactory.createFirstFrameTask(calculateFrameSize.getWidth(), calculateFrameSize.getHeight(), new LoadFrameOutput() { // from class: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy$prepareFrames$task$1
                @Override // com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameOutput
                public void onSuccess(Map<Integer, ? extends CloseableReference<Bitmap>> frames) {
                    BitmapFrameCache bitmapFrameCache;
                    LoadFrameTask loadAllFrames2;
                    int i;
                    Intrinsics.checkNotNullParameter(frames, "frames");
                    bitmapFrameCache = BalancedAnimationStrategy.this.bitmapCache;
                    if (!bitmapFrameCache.onAnimationPrepared(frames)) {
                        BalancedAnimationStrategy balancedAnimationStrategy = BalancedAnimationStrategy.this;
                        long uptimeMillis = SystemClock.uptimeMillis();
                        i = BalancedAnimationStrategy.FETCH_FIRST_CACHE_DELAY_MS;
                        balancedAnimationStrategy.nextPrepareFrames = uptimeMillis + i;
                    }
                    AnimationLoaderExecutor animationLoaderExecutor = AnimationLoaderExecutor.INSTANCE;
                    loadAllFrames2 = BalancedAnimationStrategy.this.loadAllFrames(calculateFrameSize, onAnimationLoaded);
                    animationLoaderExecutor.execute(loadAllFrames2);
                }

                @Override // com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameOutput
                public void onFail() {
                    BitmapFrameCache bitmapFrameCache;
                    AtomicBoolean atomicBoolean;
                    bitmapFrameCache = BalancedAnimationStrategy.this.bitmapCache;
                    bitmapFrameCache.clear();
                    atomicBoolean = BalancedAnimationStrategy.this.fetchingFrames;
                    atomicBoolean.set(false);
                }
            });
        } else {
            loadAllFrames = loadAllFrames(calculateFrameSize, onAnimationLoaded);
        }
        AnimationLoaderExecutor.INSTANCE.execute(loadAllFrames);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LoadFrameTask loadAllFrames(Size frameSize, final Function0<Unit> notifyOnLoad) {
        return this.loadFrameTaskFactory.createLoadFullAnimationTask(frameSize.getWidth(), frameSize.getHeight(), this.frameCount, new LoadFrameOutput() { // from class: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy$loadAllFrames$1
            @Override // com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameOutput
            public void onSuccess(Map<Integer, ? extends CloseableReference<Bitmap>> frames) {
                SortedSet sortedSet;
                SortedSet sortedSet2;
                BitmapFrameCache bitmapFrameCache;
                AtomicBoolean atomicBoolean;
                long j;
                SortedSet sortedSet3;
                boolean isOnDemandFrame;
                Intrinsics.checkNotNullParameter(frames, "frames");
                sortedSet = BalancedAnimationStrategy.this.onDemandFrames;
                sortedSet.clear();
                sortedSet2 = BalancedAnimationStrategy.this.onDemandFrames;
                BalancedAnimationStrategy balancedAnimationStrategy = BalancedAnimationStrategy.this;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry<Integer, ? extends CloseableReference<Bitmap>> entry : frames.entrySet()) {
                    isOnDemandFrame = balancedAnimationStrategy.isOnDemandFrame(entry.getKey().intValue());
                    if (isOnDemandFrame) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                ArrayList arrayList = new ArrayList(linkedHashMap.size());
                Iterator it = linkedHashMap.entrySet().iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(((Number) ((Map.Entry) it.next()).getKey()).intValue()));
                }
                sortedSet2.addAll(arrayList);
                BalancedAnimationStrategy balancedAnimationStrategy2 = BalancedAnimationStrategy.this;
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (Map.Entry<Integer, ? extends CloseableReference<Bitmap>> entry2 : frames.entrySet()) {
                    sortedSet3 = balancedAnimationStrategy2.onDemandFrames;
                    if (!sortedSet3.contains(entry2.getKey())) {
                        linkedHashMap2.put(entry2.getKey(), entry2.getValue());
                    }
                }
                bitmapFrameCache = BalancedAnimationStrategy.this.bitmapCache;
                if (!bitmapFrameCache.onAnimationPrepared(linkedHashMap2)) {
                    BalancedAnimationStrategy balancedAnimationStrategy3 = BalancedAnimationStrategy.this;
                    long uptimeMillis = SystemClock.uptimeMillis();
                    j = BalancedAnimationStrategy.FETCH_FULL_ANIMATION_CACHE_DELAY_MS;
                    balancedAnimationStrategy3.nextPrepareFrames = uptimeMillis + j;
                }
                Function0<Unit> function0 = notifyOnLoad;
                if (function0 != null) {
                    function0.invoke();
                }
                atomicBoolean = BalancedAnimationStrategy.this.fetchingFrames;
                atomicBoolean.set(false);
            }

            @Override // com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameOutput
            public void onFail() {
                BitmapFrameCache bitmapFrameCache;
                AtomicBoolean atomicBoolean;
                bitmapFrameCache = BalancedAnimationStrategy.this.bitmapCache;
                bitmapFrameCache.clear();
                atomicBoolean = BalancedAnimationStrategy.this.fetchingFrames;
                atomicBoolean.set(false);
            }
        });
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy
    public CloseableReference<Bitmap> getBitmapFrame(int frameNumber, int canvasWidth, int canvasHeight) {
        CloseableReference<Bitmap> cachedFrame = this.bitmapCache.getCachedFrame(frameNumber);
        if (cachedFrame != null && cachedFrame.isValid()) {
            prepareNextOnDemandFrame(frameNumber);
            return cachedFrame;
        }
        if (!isOnDemandFrame(frameNumber)) {
            prepareFrames(canvasWidth, canvasHeight, new Function0<Unit>() { // from class: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy$getBitmapFrame$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            });
        }
        OnDemandFrame onDemandFrame = this.onDemandBitmap;
        if (onDemandFrame == null || !onDemandFrame.isValidFor(frameNumber)) {
            return findNearestFrame(frameNumber);
        }
        OnDemandFrame onDemandFrame2 = this.onDemandBitmap;
        if (onDemandFrame2 != null) {
            return onDemandFrame2.getBitmap();
        }
        return null;
    }

    private final void prepareNextOnDemandFrame(int lastFrameRendered) {
        OnDemandFrame onDemandFrame;
        if (this.fetchingOnDemand.getAndSet(true)) {
            return;
        }
        final Integer findNextOnDemandFrame = findNextOnDemandFrame(lastFrameRendered);
        if (findNextOnDemandFrame != null && ((onDemandFrame = this.onDemandBitmap) == null || !onDemandFrame.isValidFor(findNextOnDemandFrame.intValue()))) {
            AnimationLoaderExecutor.INSTANCE.execute(this.loadFrameTaskFactory.createOnDemandTask(findNextOnDemandFrame.intValue(), new Function1<Integer, CloseableReference<Bitmap>>() { // from class: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy$prepareNextOnDemandFrame$onDemandTask$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ CloseableReference<Bitmap> invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final CloseableReference<Bitmap> invoke(int i) {
                    BitmapFrameCache bitmapFrameCache;
                    bitmapFrameCache = BalancedAnimationStrategy.this.bitmapCache;
                    return bitmapFrameCache.getCachedFrame(i);
                }
            }, new Function1<CloseableReference<Bitmap>, Unit>() { // from class: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy$prepareNextOnDemandFrame$onDemandTask$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(CloseableReference<Bitmap> closeableReference) {
                    invoke2(closeableReference);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(CloseableReference<Bitmap> closeableReference) {
                    AtomicBoolean atomicBoolean;
                    if (closeableReference != null) {
                        BalancedAnimationStrategy.this.onDemandBitmap = new OnDemandFrame(findNextOnDemandFrame.intValue(), closeableReference);
                    }
                    atomicBoolean = BalancedAnimationStrategy.this.fetchingOnDemand;
                    atomicBoolean.set(false);
                }
            }));
        } else {
            this.fetchingOnDemand.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isOnDemandFrame(int frameNumber) {
        int i = this.onDemandRatio;
        return i <= this.frameCount && frameNumber % i == 1;
    }

    private final CloseableReference<Bitmap> findNearestFrame(int fromFrame) {
        CloseableReference<Bitmap> closeableReference;
        Iterator it = CollectionsKt.asSequence(RangesKt.downTo(fromFrame, 0)).iterator();
        do {
            closeableReference = null;
            if (!it.hasNext()) {
                break;
            }
            CloseableReference<Bitmap> cachedFrame = this.bitmapCache.getCachedFrame(((Number) it.next()).intValue());
            if (cachedFrame != null && cachedFrame.isValid()) {
                closeableReference = cachedFrame;
            }
        } while (closeableReference == null);
        return closeableReference;
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy
    public void onStop() {
        OnDemandFrame onDemandFrame = this.onDemandBitmap;
        if (onDemandFrame != null) {
            onDemandFrame.close();
        }
        this.bitmapCache.clear();
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy
    public void clearFrames() {
        this.bitmapCache.clear();
    }

    private final Integer findNextOnDemandFrame(int from) {
        Object obj = null;
        if (this.onDemandFrames.isEmpty()) {
            return null;
        }
        Iterator<T> it = this.onDemandFrames.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            Integer it2 = (Integer) next;
            Intrinsics.checkNotNullExpressionValue(it2, "it");
            if (it2.intValue() > from) {
                obj = next;
                break;
            }
        }
        Integer num = (Integer) obj;
        return num == null ? this.onDemandFrames.first() : num;
    }

    private final Size calculateFrameSize(int canvasWidth, int canvasHeight) {
        if (!this.downscaleFrameToDrawableDimensions) {
            return new Size(this.animationWidth, this.animationHeight);
        }
        int i = this.animationWidth;
        int i2 = this.animationHeight;
        if (canvasWidth < i || canvasHeight < i2) {
            double d = i / i2;
            if (canvasHeight > canvasWidth) {
                i2 = RangesKt.coerceAtMost(canvasHeight, i2);
                i = (int) (i2 * d);
            } else {
                i = RangesKt.coerceAtMost(canvasWidth, i);
                i2 = (int) (i / d);
            }
        }
        return new Size(i, i2);
    }
}

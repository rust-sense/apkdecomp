package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: FpsCompressorInfo.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J*\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003J0\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00032\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00062\u0006\u0010\t\u001a\u00020\u0003J6\u0010\u000f\u001a\u00020\u000b2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00062\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006H\u0002J\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo;", "", "maxFpsLimit", "", "(I)V", "calculateReducedIndexes", "", "durationMs", "frameCount", "targetFps", "compress", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo$CompressionResult;", "frameBitmaps", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "compressAnimation", "realToReducedIndex", "millisecondsToSeconds", "", "CompressionResult", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FpsCompressorInfo {
    private final int maxFpsLimit;

    public final float millisecondsToSeconds(int i) {
        return i / 1000.0f;
    }

    public FpsCompressorInfo(int i) {
        this.maxFpsLimit = i;
    }

    public final CompressionResult compress(int durationMs, Map<Integer, ? extends CloseableReference<Bitmap>> frameBitmaps, int targetFps) {
        Intrinsics.checkNotNullParameter(frameBitmaps, "frameBitmaps");
        return compressAnimation(frameBitmaps, calculateReducedIndexes(durationMs, frameBitmaps.size(), targetFps));
    }

    public final Map<Integer, Integer> calculateReducedIndexes(int durationMs, int frameCount, int targetFps) {
        float coerceAtLeast = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(RangesKt.coerceAtLeast(targetFps, 1), this.maxFpsLimit) * millisecondsToSeconds(durationMs), 0.0f);
        float f = frameCount;
        float coerceAtMost = f / RangesKt.coerceAtMost(coerceAtLeast, f);
        int i = 0;
        IntRange until = RangesKt.until(0, frameCount);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(until, 10)), 16));
        for (Integer num : until) {
            LinkedHashMap linkedHashMap2 = linkedHashMap;
            int intValue = num.intValue();
            if (((int) (intValue % coerceAtMost)) == 0) {
                i = intValue;
            }
            linkedHashMap2.put(num, Integer.valueOf(i));
        }
        return linkedHashMap;
    }

    private final CompressionResult compressAnimation(Map<Integer, ? extends CloseableReference<Bitmap>> frameBitmaps, Map<Integer, Integer> realToReducedIndex) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, ? extends CloseableReference<Bitmap>> entry : frameBitmaps.entrySet()) {
            int intValue = entry.getKey().intValue();
            CloseableReference<Bitmap> value = entry.getValue();
            Integer num = realToReducedIndex.get(Integer.valueOf(intValue));
            if (num != null) {
                int intValue2 = num.intValue();
                if (linkedHashMap.containsKey(Integer.valueOf(intValue2))) {
                    arrayList.add(value);
                } else {
                    linkedHashMap.put(Integer.valueOf(intValue2), value);
                }
            }
        }
        return new CompressionResult(linkedHashMap, realToReducedIndex, arrayList);
    }

    /* compiled from: FpsCompressorInfo.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u0001BG\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\t¢\u0006\u0002\u0010\nR#\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo$CompressionResult;", "", "compressedAnim", "", "", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "realToReducedIndex", "removedFrames", "", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/List;)V", "getCompressedAnim", "()Ljava/util/Map;", "getRealToReducedIndex", "getRemovedFrames", "()Ljava/util/List;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class CompressionResult {
        private final Map<Integer, CloseableReference<Bitmap>> compressedAnim;
        private final Map<Integer, Integer> realToReducedIndex;
        private final List<CloseableReference<Bitmap>> removedFrames;

        public final Map<Integer, CloseableReference<Bitmap>> getCompressedAnim() {
            return this.compressedAnim;
        }

        public final Map<Integer, Integer> getRealToReducedIndex() {
            return this.realToReducedIndex;
        }

        public final List<CloseableReference<Bitmap>> getRemovedFrames() {
            return this.removedFrames;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CompressionResult(Map<Integer, ? extends CloseableReference<Bitmap>> compressedAnim, Map<Integer, Integer> realToReducedIndex, List<? extends CloseableReference<Bitmap>> removedFrames) {
            Intrinsics.checkNotNullParameter(compressedAnim, "compressedAnim");
            Intrinsics.checkNotNullParameter(realToReducedIndex, "realToReducedIndex");
            Intrinsics.checkNotNullParameter(removedFrames, "removedFrames");
            this.compressedAnim = compressedAnim;
            this.realToReducedIndex = realToReducedIndex;
            this.removedFrames = removedFrames;
        }
    }
}

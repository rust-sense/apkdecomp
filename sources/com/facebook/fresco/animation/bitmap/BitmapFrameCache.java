package com.facebook.fresco.animation.bitmap;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapFrameCache.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u001dJ\b\u0010\u0006\u001a\u00020\u0007H&J\u0011\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H¦\u0002J(\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H&J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\n\u001a\u00020\u0003H&J\u0018\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\n\u001a\u00020\u0003H&J\b\u0010\u0012\u001a\u00020\tH\u0016J\"\u0010\u0013\u001a\u00020\t2\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0015H\u0016J&\u0010\u0016\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0018\u001a\u00020\u0003H&J&\u0010\u0019\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0018\u001a\u00020\u0003H&J\u0012\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u001e"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "", "sizeInBytes", "", "getSizeInBytes", "()I", "clear", "", "contains", "", "frameNumber", "getBitmapToReuseForFrame", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "width", "height", "getCachedFrame", "getFallbackFrame", "isAnimationReady", "onAnimationPrepared", "frameBitmaps", "", "onFramePrepared", "bitmapReference", "frameType", "onFrameRendered", "setFrameCacheListener", "frameCacheListener", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache$FrameCacheListener;", "FrameCacheListener", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface BitmapFrameCache {

    /* compiled from: BitmapFrameCache.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean isAnimationReady(BitmapFrameCache bitmapFrameCache) {
            return false;
        }

        public static boolean onAnimationPrepared(BitmapFrameCache bitmapFrameCache, Map<Integer, ? extends CloseableReference<Bitmap>> frameBitmaps) {
            Intrinsics.checkNotNullParameter(frameBitmaps, "frameBitmaps");
            return true;
        }
    }

    /* compiled from: BitmapFrameCache.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache$FrameCacheListener;", "", "onFrameCached", "", "bitmapFrameCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "frameNumber", "", "onFrameEvicted", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface FrameCacheListener {
        void onFrameCached(BitmapFrameCache bitmapFrameCache, int frameNumber);

        void onFrameEvicted(BitmapFrameCache bitmapFrameCache, int frameNumber);
    }

    void clear();

    boolean contains(int frameNumber);

    CloseableReference<Bitmap> getBitmapToReuseForFrame(int frameNumber, int width, int height);

    CloseableReference<Bitmap> getCachedFrame(int frameNumber);

    CloseableReference<Bitmap> getFallbackFrame(int frameNumber);

    int getSizeInBytes();

    boolean isAnimationReady();

    boolean onAnimationPrepared(Map<Integer, ? extends CloseableReference<Bitmap>> frameBitmaps);

    void onFramePrepared(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType);

    void onFrameRendered(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType);

    void setFrameCacheListener(FrameCacheListener frameCacheListener);
}

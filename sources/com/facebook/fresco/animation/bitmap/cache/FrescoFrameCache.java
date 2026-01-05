package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.imagepipeline.animated.impl.AnimatedFrameCache;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imageutils.BitmapUtil;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class FrescoFrameCache implements BitmapFrameCache {
    private static final Class<?> TAG = FrescoFrameCache.class;
    private final AnimatedFrameCache mAnimatedFrameCache;
    private final boolean mEnableBitmapReusing;

    @Nullable
    private CloseableReference<CloseableImage> mLastRenderedItem;
    private final SparseArray<CloseableReference<CloseableImage>> mPreparedPendingFrames = new SparseArray<>();

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public boolean isAnimationReady() {
        return false;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public boolean onAnimationPrepared(Map<Integer, ? extends CloseableReference<Bitmap>> map) {
        return true;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void setFrameCacheListener(@Nullable BitmapFrameCache.FrameCacheListener frameCacheListener) {
    }

    public FrescoFrameCache(AnimatedFrameCache animatedFrameCache, boolean z) {
        this.mAnimatedFrameCache = animatedFrameCache;
        this.mEnableBitmapReusing = z;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getCachedFrame(int i) {
        return convertToBitmapReferenceAndClose(this.mAnimatedFrameCache.get(i));
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getFallbackFrame(int i) {
        return convertToBitmapReferenceAndClose(CloseableReference.cloneOrNull(this.mLastRenderedItem));
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getBitmapToReuseForFrame(int i, int i2, int i3) {
        if (!this.mEnableBitmapReusing) {
            return null;
        }
        return convertToBitmapReferenceAndClose(this.mAnimatedFrameCache.getForReuse());
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized boolean contains(int i) {
        return this.mAnimatedFrameCache.contains(i);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized int getSizeInBytes() {
        return getBitmapSizeBytes(this.mLastRenderedItem) + getPreparedPendingFramesSizeBytes();
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void clear() {
        CloseableReference.closeSafely(this.mLastRenderedItem);
        this.mLastRenderedItem = null;
        for (int i = 0; i < this.mPreparedPendingFrames.size(); i++) {
            CloseableReference.closeSafely(this.mPreparedPendingFrames.valueAt(i));
        }
        this.mPreparedPendingFrames.clear();
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void onFrameRendered(int i, CloseableReference<Bitmap> closeableReference, int i2) {
        CloseableReference<CloseableImage> closeableReference2;
        Preconditions.checkNotNull(closeableReference);
        removePreparedReference(i);
        try {
            closeableReference2 = createImageReference(closeableReference);
            if (closeableReference2 != null) {
                try {
                    CloseableReference.closeSafely(this.mLastRenderedItem);
                    this.mLastRenderedItem = this.mAnimatedFrameCache.cache(i, closeableReference2);
                } catch (Throwable th) {
                    th = th;
                    CloseableReference.closeSafely(closeableReference2);
                    throw th;
                }
            }
            CloseableReference.closeSafely(closeableReference2);
        } catch (Throwable th2) {
            th = th2;
            closeableReference2 = null;
        }
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void onFramePrepared(int i, CloseableReference<Bitmap> closeableReference, int i2) {
        CloseableReference<CloseableImage> closeableReference2;
        Preconditions.checkNotNull(closeableReference);
        try {
            closeableReference2 = createImageReference(closeableReference);
            if (closeableReference2 != null) {
                try {
                    CloseableReference<CloseableImage> cache = this.mAnimatedFrameCache.cache(i, closeableReference2);
                    if (CloseableReference.isValid(cache)) {
                        CloseableReference.closeSafely(this.mPreparedPendingFrames.get(i));
                        this.mPreparedPendingFrames.put(i, cache);
                        FLog.v(TAG, "cachePreparedFrame(%d) cached. Pending frames: %s", Integer.valueOf(i), this.mPreparedPendingFrames);
                    }
                    CloseableReference.closeSafely(closeableReference2);
                    return;
                } catch (Throwable th) {
                    th = th;
                    CloseableReference.closeSafely(closeableReference2);
                    throw th;
                }
            }
            CloseableReference.closeSafely(closeableReference2);
        } catch (Throwable th2) {
            th = th2;
            closeableReference2 = null;
        }
    }

    private synchronized int getPreparedPendingFramesSizeBytes() {
        int i;
        i = 0;
        for (int i2 = 0; i2 < this.mPreparedPendingFrames.size(); i2++) {
            i += getBitmapSizeBytes(this.mPreparedPendingFrames.valueAt(i2));
        }
        return i;
    }

    private synchronized void removePreparedReference(int i) {
        CloseableReference<CloseableImage> closeableReference = this.mPreparedPendingFrames.get(i);
        if (closeableReference != null) {
            this.mPreparedPendingFrames.delete(i);
            CloseableReference.closeSafely(closeableReference);
            FLog.v(TAG, "removePreparedReference(%d) removed. Pending frames: %s", Integer.valueOf(i), this.mPreparedPendingFrames);
        }
    }

    @Nullable
    static CloseableReference<Bitmap> convertToBitmapReferenceAndClose(@Nullable CloseableReference<CloseableImage> closeableReference) {
        CloseableStaticBitmap closeableStaticBitmap;
        try {
            if (CloseableReference.isValid(closeableReference) && (closeableReference.get() instanceof CloseableStaticBitmap) && (closeableStaticBitmap = (CloseableStaticBitmap) closeableReference.get()) != null) {
                return closeableStaticBitmap.cloneUnderlyingBitmapReference();
            }
            CloseableReference.closeSafely(closeableReference);
            return null;
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    private static int getBitmapSizeBytes(@Nullable CloseableReference<CloseableImage> closeableReference) {
        if (CloseableReference.isValid(closeableReference)) {
            return getBitmapSizeBytes(closeableReference.get());
        }
        return 0;
    }

    private static int getBitmapSizeBytes(@Nullable CloseableImage closeableImage) {
        if (closeableImage instanceof CloseableBitmap) {
            return BitmapUtil.getSizeInBytes(((CloseableBitmap) closeableImage).getUnderlyingBitmap());
        }
        return 0;
    }

    @Nullable
    private static CloseableReference<CloseableImage> createImageReference(CloseableReference<Bitmap> closeableReference) {
        return CloseableReference.of(CloseableStaticBitmap.CC.of(closeableReference, ImmutableQualityInfo.FULL_QUALITY, 0));
    }
}

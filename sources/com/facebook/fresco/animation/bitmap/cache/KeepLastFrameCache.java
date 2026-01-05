package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.imageutils.BitmapUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KeepLastFrameCache.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0096\u0002J(\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u0016J\u0018\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0018\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\tH\u0016J&\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u001a\u001a\u00020\tH\u0016J&\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u001a\u001a\u00020\tH\u0016J\u0012\u0010\u001c\u001a\u00020\u000e2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/cache/KeepLastFrameCache;", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "()V", "frameCacheListener", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache$FrameCacheListener;", "lastBitmapReference", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "lastFrameNumber", "", "sizeInBytes", "getSizeInBytes", "()I", "clear", "", "closeAndResetLastBitmapReference", "contains", "", "frameNumber", "getBitmapToReuseForFrame", "width", "height", "getCachedFrame", "getFallbackFrame", "onFramePrepared", "bitmapReference", "frameType", "onFrameRendered", "setFrameCacheListener", "Companion", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class KeepLastFrameCache implements BitmapFrameCache {
    private static final int FRAME_NUMBER_UNSET = -1;
    private BitmapFrameCache.FrameCacheListener frameCacheListener;
    private CloseableReference<Bitmap> lastBitmapReference;
    private int lastFrameNumber = -1;

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void onFramePrepared(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
        Intrinsics.checkNotNullParameter(bitmapReference, "bitmapReference");
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void setFrameCacheListener(BitmapFrameCache.FrameCacheListener frameCacheListener) {
        this.frameCacheListener = frameCacheListener;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public boolean isAnimationReady() {
        return BitmapFrameCache.DefaultImpls.isAnimationReady(this);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public boolean onAnimationPrepared(Map<Integer, ? extends CloseableReference<Bitmap>> map) {
        return BitmapFrameCache.DefaultImpls.onAnimationPrepared(this, map);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized CloseableReference<Bitmap> getCachedFrame(int frameNumber) {
        return this.lastFrameNumber == frameNumber ? CloseableReference.cloneOrNull(this.lastBitmapReference) : null;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized CloseableReference<Bitmap> getFallbackFrame(int frameNumber) {
        return CloseableReference.cloneOrNull(this.lastBitmapReference);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized CloseableReference<Bitmap> getBitmapToReuseForFrame(int frameNumber, int width, int height) {
        try {
        } finally {
            closeAndResetLastBitmapReference();
        }
        return CloseableReference.cloneOrNull(this.lastBitmapReference);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized boolean contains(int frameNumber) {
        boolean z;
        if (frameNumber == this.lastFrameNumber) {
            z = CloseableReference.isValid(this.lastBitmapReference);
        }
        return z;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized int getSizeInBytes() {
        int sizeInBytes;
        CloseableReference<Bitmap> closeableReference = this.lastBitmapReference;
        if (closeableReference == null) {
            sizeInBytes = 0;
        } else {
            Intrinsics.checkNotNull(closeableReference);
            sizeInBytes = BitmapUtil.getSizeInBytes(closeableReference.get());
        }
        return sizeInBytes;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void clear() {
        closeAndResetLastBitmapReference();
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void onFrameRendered(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
        BitmapFrameCache.FrameCacheListener frameCacheListener;
        Intrinsics.checkNotNullParameter(bitmapReference, "bitmapReference");
        if (this.lastBitmapReference != null) {
            Bitmap bitmap = bitmapReference.get();
            CloseableReference<Bitmap> closeableReference = this.lastBitmapReference;
            if (Intrinsics.areEqual(bitmap, closeableReference != null ? closeableReference.get() : null)) {
                return;
            }
        }
        CloseableReference.closeSafely(this.lastBitmapReference);
        int i = this.lastFrameNumber;
        if (i != -1 && (frameCacheListener = this.frameCacheListener) != null) {
            frameCacheListener.onFrameEvicted(this, i);
        }
        this.lastBitmapReference = CloseableReference.cloneOrNull(bitmapReference);
        BitmapFrameCache.FrameCacheListener frameCacheListener2 = this.frameCacheListener;
        if (frameCacheListener2 != null) {
            frameCacheListener2.onFrameCached(this, frameNumber);
        }
        this.lastFrameNumber = frameNumber;
    }

    private final synchronized void closeAndResetLastBitmapReference() {
        BitmapFrameCache.FrameCacheListener frameCacheListener;
        int i = this.lastFrameNumber;
        if (i != -1 && (frameCacheListener = this.frameCacheListener) != null) {
            frameCacheListener.onFrameEvicted(this, i);
        }
        CloseableReference.closeSafely(this.lastBitmapReference);
        this.lastBitmapReference = null;
        this.lastFrameNumber = -1;
    }
}

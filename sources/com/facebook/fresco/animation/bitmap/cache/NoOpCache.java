package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NoOpCache.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0096\u0002J(\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J\u0018\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J&\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00042\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0015\u001a\u00020\u0004H\u0016J&\u0010\u0016\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00042\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0015\u001a\u00020\u0004H\u0016J\u0012\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001a"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/cache/NoOpCache;", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "()V", "sizeInBytes", "", "getSizeInBytes", "()I", "clear", "", "contains", "", "frameNumber", "getBitmapToReuseForFrame", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "width", "height", "getCachedFrame", "getFallbackFrame", "onFramePrepared", "bitmapReference", "frameType", "onFrameRendered", "setFrameCacheListener", "frameCacheListener", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache$FrameCacheListener;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NoOpCache implements BitmapFrameCache {
    private final int sizeInBytes;

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void clear() {
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public boolean contains(int frameNumber) {
        return false;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public CloseableReference<Bitmap> getBitmapToReuseForFrame(int frameNumber, int width, int height) {
        return null;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public CloseableReference<Bitmap> getCachedFrame(int frameNumber) {
        return null;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public CloseableReference<Bitmap> getFallbackFrame(int frameNumber) {
        return null;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public int getSizeInBytes() {
        return this.sizeInBytes;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void onFramePrepared(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
        Intrinsics.checkNotNullParameter(bitmapReference, "bitmapReference");
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void onFrameRendered(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
        Intrinsics.checkNotNullParameter(bitmapReference, "bitmapReference");
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void setFrameCacheListener(BitmapFrameCache.FrameCacheListener frameCacheListener) {
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public boolean isAnimationReady() {
        return BitmapFrameCache.DefaultImpls.isAnimationReady(this);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public boolean onAnimationPrepared(Map<Integer, ? extends CloseableReference<Bitmap>> map) {
        return BitmapFrameCache.DefaultImpls.onAnimationPrepared(this, map);
    }
}

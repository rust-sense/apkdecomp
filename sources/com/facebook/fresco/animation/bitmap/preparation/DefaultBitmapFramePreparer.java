package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultBitmapFramePreparer.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001aB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0016R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/DefaultBitmapFramePreparer;", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "executorService", "Ljava/util/concurrent/ExecutorService;", "(Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;Landroid/graphics/Bitmap$Config;Ljava/util/concurrent/ExecutorService;)V", "TAG", "Ljava/lang/Class;", "pendingFrameDecodeJobs", "Landroid/util/SparseArray;", "Ljava/lang/Runnable;", "getUniqueId", "", "backend", "Lcom/facebook/fresco/animation/backend/AnimationBackend;", "frameNumber", "prepareFrame", "", "bitmapFrameCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "animationBackend", "FrameDecodeRunnable", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DefaultBitmapFramePreparer implements BitmapFramePreparer {
    private final Class<DefaultBitmapFramePreparer> TAG;
    private final Bitmap.Config bitmapConfig;
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private final ExecutorService executorService;
    private final SparseArray<Runnable> pendingFrameDecodeJobs;
    private final PlatformBitmapFactory platformBitmapFactory;

    public DefaultBitmapFramePreparer(PlatformBitmapFactory platformBitmapFactory, BitmapFrameRenderer bitmapFrameRenderer, Bitmap.Config bitmapConfig, ExecutorService executorService) {
        Intrinsics.checkNotNullParameter(platformBitmapFactory, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer, "bitmapFrameRenderer");
        Intrinsics.checkNotNullParameter(bitmapConfig, "bitmapConfig");
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        this.platformBitmapFactory = platformBitmapFactory;
        this.bitmapFrameRenderer = bitmapFrameRenderer;
        this.bitmapConfig = bitmapConfig;
        this.executorService = executorService;
        this.TAG = DefaultBitmapFramePreparer.class;
        this.pendingFrameDecodeJobs = new SparseArray<>();
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparer
    public boolean prepareFrame(BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int frameNumber) {
        Intrinsics.checkNotNullParameter(bitmapFrameCache, "bitmapFrameCache");
        Intrinsics.checkNotNullParameter(animationBackend, "animationBackend");
        int uniqueId = getUniqueId(animationBackend, frameNumber);
        synchronized (this.pendingFrameDecodeJobs) {
            if (this.pendingFrameDecodeJobs.get(uniqueId) != null) {
                FLog.v(this.TAG, "Already scheduled decode job for frame %d", Integer.valueOf(frameNumber));
                return true;
            }
            if (bitmapFrameCache.contains(frameNumber)) {
                FLog.v(this.TAG, "Frame %d is cached already.", Integer.valueOf(frameNumber));
                return true;
            }
            FrameDecodeRunnable frameDecodeRunnable = new FrameDecodeRunnable(this, animationBackend, bitmapFrameCache, frameNumber, uniqueId);
            this.pendingFrameDecodeJobs.put(uniqueId, frameDecodeRunnable);
            this.executorService.execute(frameDecodeRunnable);
            Unit unit = Unit.INSTANCE;
            return true;
        }
    }

    /* compiled from: DefaultBitmapFramePreparer.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0002J(\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\u0007H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/DefaultBitmapFramePreparer$FrameDecodeRunnable;", "Ljava/lang/Runnable;", "animationBackend", "Lcom/facebook/fresco/animation/backend/AnimationBackend;", "bitmapFrameCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "frameNumber", "", "frameId", "(Lcom/facebook/fresco/animation/bitmap/preparation/DefaultBitmapFramePreparer;Lcom/facebook/fresco/animation/backend/AnimationBackend;Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;II)V", "prepareFrameAndCache", "", "frameType", "renderFrameAndCache", "bitmapReference", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "run", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class FrameDecodeRunnable implements Runnable {
        private final AnimationBackend animationBackend;
        private final BitmapFrameCache bitmapFrameCache;
        private final int frameId;
        private final int frameNumber;
        final /* synthetic */ DefaultBitmapFramePreparer this$0;

        public FrameDecodeRunnable(DefaultBitmapFramePreparer defaultBitmapFramePreparer, AnimationBackend animationBackend, BitmapFrameCache bitmapFrameCache, int i, int i2) {
            Intrinsics.checkNotNullParameter(animationBackend, "animationBackend");
            Intrinsics.checkNotNullParameter(bitmapFrameCache, "bitmapFrameCache");
            this.this$0 = defaultBitmapFramePreparer;
            this.animationBackend = animationBackend;
            this.bitmapFrameCache = bitmapFrameCache;
            this.frameNumber = i;
            this.frameId = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.bitmapFrameCache.contains(this.frameNumber)) {
                    FLog.v((Class<?>) this.this$0.TAG, "Frame %d is cached already.", Integer.valueOf(this.frameNumber));
                    SparseArray sparseArray = this.this$0.pendingFrameDecodeJobs;
                    DefaultBitmapFramePreparer defaultBitmapFramePreparer = this.this$0;
                    synchronized (sparseArray) {
                        defaultBitmapFramePreparer.pendingFrameDecodeJobs.remove(this.frameId);
                        Unit unit = Unit.INSTANCE;
                    }
                    return;
                }
                if (prepareFrameAndCache(this.frameNumber, 1)) {
                    FLog.v((Class<?>) this.this$0.TAG, "Prepared frame %d.", Integer.valueOf(this.frameNumber));
                } else {
                    FLog.e((Class<?>) this.this$0.TAG, "Could not prepare frame %d.", Integer.valueOf(this.frameNumber));
                }
                SparseArray sparseArray2 = this.this$0.pendingFrameDecodeJobs;
                DefaultBitmapFramePreparer defaultBitmapFramePreparer2 = this.this$0;
                synchronized (sparseArray2) {
                    defaultBitmapFramePreparer2.pendingFrameDecodeJobs.remove(this.frameId);
                    Unit unit2 = Unit.INSTANCE;
                }
            } catch (Throwable th) {
                SparseArray sparseArray3 = this.this$0.pendingFrameDecodeJobs;
                DefaultBitmapFramePreparer defaultBitmapFramePreparer3 = this.this$0;
                synchronized (sparseArray3) {
                    defaultBitmapFramePreparer3.pendingFrameDecodeJobs.remove(this.frameId);
                    Unit unit3 = Unit.INSTANCE;
                    throw th;
                }
            }
        }

        private final boolean prepareFrameAndCache(int frameNumber, int frameType) {
            CloseableReference<Bitmap> bitmapToReuseForFrame;
            int i = 2;
            try {
                if (frameType == 1) {
                    bitmapToReuseForFrame = this.bitmapFrameCache.getBitmapToReuseForFrame(frameNumber, this.animationBackend.getIntrinsicWidth(), this.animationBackend.getIntrinsicHeight());
                } else {
                    if (frameType != 2) {
                        return false;
                    }
                    bitmapToReuseForFrame = this.this$0.platformBitmapFactory.createBitmap(this.animationBackend.getIntrinsicWidth(), this.animationBackend.getIntrinsicHeight(), this.this$0.bitmapConfig);
                    i = -1;
                }
                boolean renderFrameAndCache = renderFrameAndCache(frameNumber, bitmapToReuseForFrame, frameType);
                CloseableReference.closeSafely(bitmapToReuseForFrame);
                return (renderFrameAndCache || i == -1) ? renderFrameAndCache : prepareFrameAndCache(frameNumber, i);
            } catch (RuntimeException e) {
                FLog.w((Class<?>) this.this$0.TAG, "Failed to create frame bitmap", e);
                return false;
            } finally {
                CloseableReference.closeSafely((CloseableReference<?>) null);
            }
        }

        private final boolean renderFrameAndCache(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
            if (CloseableReference.isValid(bitmapReference) && bitmapReference != null) {
                BitmapFrameRenderer bitmapFrameRenderer = this.this$0.bitmapFrameRenderer;
                Bitmap bitmap = bitmapReference.get();
                Intrinsics.checkNotNullExpressionValue(bitmap, "bitmapReference.get()");
                if (bitmapFrameRenderer.renderFrame(frameNumber, bitmap)) {
                    FLog.v((Class<?>) this.this$0.TAG, "Frame %d ready.", Integer.valueOf(frameNumber));
                    synchronized (this.this$0.pendingFrameDecodeJobs) {
                        this.bitmapFrameCache.onFramePrepared(frameNumber, bitmapReference, frameType);
                        Unit unit = Unit.INSTANCE;
                    }
                    return true;
                }
            }
            return false;
        }
    }

    private final int getUniqueId(AnimationBackend backend, int frameNumber) {
        return (backend.hashCode() * 31) + frameNumber;
    }
}

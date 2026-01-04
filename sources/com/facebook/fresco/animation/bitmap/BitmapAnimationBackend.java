package com.facebook.fresco.animation.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationBackendDelegateWithInactivityCheck;
import com.facebook.fresco.animation.backend.AnimationInformation;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparer;
import com.facebook.fresco.vito.options.RoundingOptions;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.protocol.ViewHierarchyNode;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.annotation.AnnotationRetention;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapAnimationBackend.kt */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u0000 U2\u00020\u00012\u00020\u0002:\u0003UVWBM\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013J\b\u0010*\u001a\u00020+H\u0016J \u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0002J0\u00102\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u00192\u000e\u00103\u001a\n\u0012\u0004\u0012\u00020/\u0018\u0001042\u0006\u00100\u001a\u0002012\u0006\u00105\u001a\u00020\u0019H\u0002J \u00106\u001a\u00020\f2\u0006\u00107\u001a\u0002082\u0006\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020\u0019H\u0016J \u00109\u001a\u00020\f2\u0006\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020\u00192\u0006\u00105\u001a\u00020\u0019H\u0002J\b\u0010:\u001a\u00020\u0019H\u0016J\u0010\u0010;\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u0019H\u0016J\b\u0010<\u001a\u00020\u0019H\u0016J\b\u0010=\u001a\u00020\u0019H\u0016J\b\u0010>\u001a\u00020\u0019H\u0016J\b\u0010?\u001a\u00020\u0019H\u0016J\b\u0010@\u001a\u00020\u0019H\u0016J\b\u0010A\u001a\u00020\u0019H\u0016J\b\u0010B\u001a\u00020+H\u0016J\b\u0010C\u001a\u00020+H\u0016J \u0010D\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u00192\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020/\u0018\u000104H\u0002J\u0012\u0010F\u001a\u00020+2\b\b\u0001\u0010G\u001a\u00020\u0019H\u0016J\u0012\u0010H\u001a\u00020+2\b\u0010I\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010J\u001a\u00020+2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0012\u0010K\u001a\u00020+2\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\u0010\u0010N\u001a\u00020+2\b\u0010!\u001a\u0004\u0018\u00010\"J\b\u0010O\u001a\u00020+H\u0002J(\u0010P\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020/2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020RH\u0002J\b\u0010T\u001a\u00020\u0019H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend;", "Lcom/facebook/fresco/animation/backend/AnimationBackend;", "Lcom/facebook/fresco/animation/backend/AnimationBackendDelegateWithInactivityCheck$InactivityListener;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "isNewRenderImplementation", "", "bitmapFramePreparationStrategy", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparationStrategy;", "bitmapFramePreparer", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;", "roundingOptions", "Lcom/facebook/fresco/vito/options/RoundingOptions;", "(Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;Lcom/facebook/fresco/animation/backend/AnimationInformation;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;ZLcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparationStrategy;Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;Lcom/facebook/fresco/vito/options/RoundingOptions;)V", "animationListener", "Lcom/facebook/fresco/animation/backend/AnimationBackend$Listener;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "bitmapHeight", "", "bitmapWidth", "bounds", "Landroid/graphics/Rect;", "cornerRadii", "", "getCornerRadii", "()[F", "frameListener", "Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend$FrameListener;", "matrix", "Landroid/graphics/Matrix;", "paint", "Landroid/graphics/Paint;", "path", "Landroid/graphics/Path;", "pathFrameNumber", "clear", "", "drawBitmap", "frameNumber", "bitmap", "Landroid/graphics/Bitmap;", "canvas", "Landroid/graphics/Canvas;", "drawBitmapAndCache", "bitmapReference", "Lcom/facebook/common/references/CloseableReference;", "frameType", "drawFrame", "parent", "Landroid/graphics/drawable/Drawable;", "drawFrameOrFallback", "getFrameCount", "getFrameDurationMs", "getIntrinsicHeight", "getIntrinsicWidth", "getLoopCount", "getLoopDurationMs", "getSizeInBytes", "height", "onInactive", "preloadAnimation", "renderFrameInBitmap", "targetBitmap", "setAlpha", ViewHierarchyNode.JsonKeys.ALPHA, "setAnimationListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setBounds", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "setFrameListener", "updateBitmapDimensions", "updatePath", "currentBoundsWidth", "", "currentBoundsHeight", "width", "Companion", "FrameListener", "FrameType", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BitmapAnimationBackend implements AnimationBackend, AnimationBackendDelegateWithInactivityCheck.InactivityListener {
    public static final int FRAME_TYPE_CACHED = 0;
    public static final int FRAME_TYPE_CREATED = 2;
    public static final int FRAME_TYPE_FALLBACK = 3;
    public static final int FRAME_TYPE_REUSED = 1;
    public static final int FRAME_TYPE_UNKNOWN = -1;
    private final AnimationInformation animationInformation;
    private AnimationBackend.Listener animationListener;
    private final Bitmap.Config bitmapConfig;
    private final BitmapFrameCache bitmapFrameCache;
    private final BitmapFramePreparationStrategy bitmapFramePreparationStrategy;
    private final BitmapFramePreparer bitmapFramePreparer;
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private int bitmapHeight;
    private int bitmapWidth;
    private Rect bounds;
    private final float[] cornerRadii;
    private FrameListener frameListener;
    private final boolean isNewRenderImplementation;
    private final Matrix matrix;
    private final Paint paint;
    private final Path path;
    private int pathFrameNumber;
    private final PlatformBitmapFactory platformBitmapFactory;
    private static final Class<BitmapAnimationBackend> TAG = BitmapAnimationBackend.class;

    /* compiled from: BitmapAnimationBackend.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u000b"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend$FrameListener;", "", "onDrawFrameStart", "", "backend", "Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend;", "frameNumber", "", "onFrameDrawn", "frameType", "onFrameDropped", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface FrameListener {
        void onDrawFrameStart(BitmapAnimationBackend backend, int frameNumber);

        void onFrameDrawn(BitmapAnimationBackend backend, int frameNumber, int frameType);

        void onFrameDropped(BitmapAnimationBackend backend, int frameNumber);
    }

    /* compiled from: BitmapAnimationBackend.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend$FrameType;", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface FrameType {
    }

    public final float[] getCornerRadii() {
        return this.cornerRadii;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    /* renamed from: getIntrinsicHeight, reason: from getter */
    public int getBitmapHeight() {
        return this.bitmapHeight;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    /* renamed from: getIntrinsicWidth, reason: from getter */
    public int getBitmapWidth() {
        return this.bitmapWidth;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setAnimationListener(AnimationBackend.Listener listener) {
        this.animationListener = listener;
    }

    public final void setFrameListener(FrameListener frameListener) {
        this.frameListener = frameListener;
    }

    public BitmapAnimationBackend(PlatformBitmapFactory platformBitmapFactory, BitmapFrameCache bitmapFrameCache, AnimationInformation animationInformation, BitmapFrameRenderer bitmapFrameRenderer, boolean z, BitmapFramePreparationStrategy bitmapFramePreparationStrategy, BitmapFramePreparer bitmapFramePreparer, RoundingOptions roundingOptions) {
        float[] fArr;
        Intrinsics.checkNotNullParameter(platformBitmapFactory, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameCache, "bitmapFrameCache");
        Intrinsics.checkNotNullParameter(animationInformation, "animationInformation");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer, "bitmapFrameRenderer");
        this.platformBitmapFactory = platformBitmapFactory;
        this.bitmapFrameCache = bitmapFrameCache;
        this.animationInformation = animationInformation;
        this.bitmapFrameRenderer = bitmapFrameRenderer;
        this.isNewRenderImplementation = z;
        this.bitmapFramePreparationStrategy = bitmapFramePreparationStrategy;
        this.bitmapFramePreparer = bitmapFramePreparer;
        if (roundingOptions == null) {
            fArr = null;
        } else if (roundingOptions.getCornerRadius() != 0.0f) {
            fArr = new float[8];
            ArraysKt.fill$default(fArr, roundingOptions.getCornerRadius(), 0, 0, 6, (Object) null);
        } else {
            fArr = roundingOptions.getCornerRadii();
        }
        this.cornerRadii = fArr;
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.paint = new Paint(6);
        this.path = new Path();
        this.matrix = new Matrix();
        this.pathFrameNumber = -1;
        updateBitmapDimensions();
    }

    public /* synthetic */ BitmapAnimationBackend(PlatformBitmapFactory platformBitmapFactory, BitmapFrameCache bitmapFrameCache, AnimationInformation animationInformation, BitmapFrameRenderer bitmapFrameRenderer, boolean z, BitmapFramePreparationStrategy bitmapFramePreparationStrategy, BitmapFramePreparer bitmapFramePreparer, RoundingOptions roundingOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(platformBitmapFactory, bitmapFrameCache, animationInformation, bitmapFrameRenderer, z, bitmapFramePreparationStrategy, bitmapFramePreparer, (i & 128) != 0 ? null : roundingOptions);
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getFrameCount() {
        return this.animationInformation.getFrameCount();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getFrameDurationMs(int frameNumber) {
        return this.animationInformation.getFrameDurationMs(frameNumber);
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int width() {
        return this.animationInformation.width();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int height() {
        return this.animationInformation.height();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getLoopDurationMs() {
        return this.animationInformation.getLoopDurationMs();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getLoopCount() {
        return this.animationInformation.getLoopCount();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public boolean drawFrame(Drawable parent, Canvas canvas, int frameNumber) {
        BitmapFramePreparer bitmapFramePreparer;
        BitmapFramePreparationStrategy bitmapFramePreparationStrategy;
        FrameListener frameListener;
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        FrameListener frameListener2 = this.frameListener;
        if (frameListener2 != null) {
            frameListener2.onDrawFrameStart(this, frameNumber);
        }
        boolean drawFrameOrFallback = drawFrameOrFallback(canvas, frameNumber, 0);
        if (!drawFrameOrFallback && (frameListener = this.frameListener) != null) {
            frameListener.onFrameDropped(this, frameNumber);
        }
        if (!this.isNewRenderImplementation && (bitmapFramePreparer = this.bitmapFramePreparer) != null && (bitmapFramePreparationStrategy = this.bitmapFramePreparationStrategy) != null) {
            BitmapFramePreparationStrategy.DefaultImpls.prepareFrames$default(bitmapFramePreparationStrategy, bitmapFramePreparer, this.bitmapFrameCache, this, frameNumber, null, 16, null);
        }
        return drawFrameOrFallback;
    }

    private final boolean drawFrameOrFallback(Canvas canvas, int frameNumber, int frameType) {
        CloseableReference<Bitmap> cachedFrame;
        boolean drawBitmapAndCache;
        CloseableReference<Bitmap> closeableReference = null;
        try {
            boolean z = false;
            int i = 1;
            if (this.isNewRenderImplementation) {
                BitmapFramePreparationStrategy bitmapFramePreparationStrategy = this.bitmapFramePreparationStrategy;
                CloseableReference<Bitmap> bitmapFrame = bitmapFramePreparationStrategy != null ? bitmapFramePreparationStrategy.getBitmapFrame(frameNumber, canvas.getWidth(), canvas.getHeight()) : null;
                if (bitmapFrame != null) {
                    try {
                        if (bitmapFrame.isValid()) {
                            Bitmap bitmap = bitmapFrame.get();
                            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmapReference.get()");
                            drawBitmap(frameNumber, bitmap, canvas);
                            CloseableReference.closeSafely(bitmapFrame);
                            return true;
                        }
                    } catch (Throwable th) {
                        th = th;
                        closeableReference = bitmapFrame;
                        CloseableReference.closeSafely(closeableReference);
                        throw th;
                    }
                }
                BitmapFramePreparationStrategy bitmapFramePreparationStrategy2 = this.bitmapFramePreparationStrategy;
                if (bitmapFramePreparationStrategy2 != null) {
                    bitmapFramePreparationStrategy2.prepareFrames(canvas.getWidth(), canvas.getHeight(), null);
                }
                CloseableReference.closeSafely(bitmapFrame);
                return false;
            }
            if (frameType == 0) {
                cachedFrame = this.bitmapFrameCache.getCachedFrame(frameNumber);
                drawBitmapAndCache = drawBitmapAndCache(frameNumber, cachedFrame, canvas, 0);
            } else if (frameType == 1) {
                cachedFrame = this.bitmapFrameCache.getBitmapToReuseForFrame(frameNumber, this.bitmapWidth, this.bitmapHeight);
                if (renderFrameInBitmap(frameNumber, cachedFrame) && drawBitmapAndCache(frameNumber, cachedFrame, canvas, 1)) {
                    z = true;
                }
                drawBitmapAndCache = z;
                i = 2;
            } else if (frameType == 2) {
                try {
                    cachedFrame = this.platformBitmapFactory.createBitmap(this.bitmapWidth, this.bitmapHeight, this.bitmapConfig);
                    if (renderFrameInBitmap(frameNumber, cachedFrame) && drawBitmapAndCache(frameNumber, cachedFrame, canvas, 2)) {
                        z = true;
                    }
                    drawBitmapAndCache = z;
                    i = 3;
                } catch (RuntimeException e) {
                    FLog.w(TAG, "Failed to create frame bitmap", e);
                    CloseableReference.closeSafely((CloseableReference<?>) null);
                    return false;
                }
            } else {
                if (frameType != 3) {
                    CloseableReference.closeSafely((CloseableReference<?>) null);
                    return false;
                }
                cachedFrame = this.bitmapFrameCache.getFallbackFrame(frameNumber);
                drawBitmapAndCache = drawBitmapAndCache(frameNumber, cachedFrame, canvas, 3);
                i = -1;
            }
            CloseableReference.closeSafely(cachedFrame);
            return (drawBitmapAndCache || i == -1) ? drawBitmapAndCache : drawFrameOrFallback(canvas, frameNumber, i);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setAlpha(int alpha) {
        this.paint.setAlpha(alpha);
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setBounds(Rect bounds) {
        this.bounds = bounds;
        this.bitmapFrameRenderer.setBounds(bounds);
        updateBitmapDimensions();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getSizeInBytes() {
        return this.bitmapFrameCache.getSizeInBytes();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void clear() {
        if (!this.isNewRenderImplementation) {
            this.bitmapFrameCache.clear();
            return;
        }
        BitmapFramePreparationStrategy bitmapFramePreparationStrategy = this.bitmapFramePreparationStrategy;
        if (bitmapFramePreparationStrategy != null) {
            bitmapFramePreparationStrategy.clearFrames();
        }
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void preloadAnimation() {
        BitmapFramePreparer bitmapFramePreparer;
        if (this.isNewRenderImplementation || (bitmapFramePreparer = this.bitmapFramePreparer) == null) {
            BitmapFramePreparationStrategy bitmapFramePreparationStrategy = this.bitmapFramePreparationStrategy;
            if (bitmapFramePreparationStrategy != null) {
                bitmapFramePreparationStrategy.prepareFrames(this.animationInformation.width(), this.animationInformation.height(), new Function0<Unit>() { // from class: com.facebook.fresco.animation.bitmap.BitmapAnimationBackend$preloadAnimation$2
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        AnimationBackend.Listener listener;
                        listener = BitmapAnimationBackend.this.animationListener;
                        if (listener != null) {
                            listener.onAnimationLoaded();
                        }
                    }
                });
                return;
            }
            return;
        }
        BitmapFramePreparationStrategy bitmapFramePreparationStrategy2 = this.bitmapFramePreparationStrategy;
        if (bitmapFramePreparationStrategy2 != null) {
            bitmapFramePreparationStrategy2.prepareFrames(bitmapFramePreparer, this.bitmapFrameCache, this, 0, new Function0<Unit>() { // from class: com.facebook.fresco.animation.bitmap.BitmapAnimationBackend$preloadAnimation$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AnimationBackend.Listener listener;
                    listener = BitmapAnimationBackend.this.animationListener;
                    if (listener != null) {
                        listener.onAnimationLoaded();
                    }
                }
            });
        }
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackendDelegateWithInactivityCheck.InactivityListener
    public void onInactive() {
        if (!this.isNewRenderImplementation) {
            clear();
            return;
        }
        BitmapFramePreparationStrategy bitmapFramePreparationStrategy = this.bitmapFramePreparationStrategy;
        if (bitmapFramePreparationStrategy != null) {
            bitmapFramePreparationStrategy.onStop();
        }
    }

    private final void updateBitmapDimensions() {
        int intrinsicWidth = this.bitmapFrameRenderer.getIntrinsicWidth();
        this.bitmapWidth = intrinsicWidth;
        if (intrinsicWidth == -1) {
            Rect rect = this.bounds;
            this.bitmapWidth = rect != null ? rect.width() : -1;
        }
        int intrinsicHeight = this.bitmapFrameRenderer.getIntrinsicHeight();
        this.bitmapHeight = intrinsicHeight;
        if (intrinsicHeight == -1) {
            Rect rect2 = this.bounds;
            this.bitmapHeight = rect2 != null ? rect2.height() : -1;
        }
    }

    private final boolean renderFrameInBitmap(int frameNumber, CloseableReference<Bitmap> targetBitmap) {
        if (targetBitmap == null || !targetBitmap.isValid()) {
            return false;
        }
        BitmapFrameRenderer bitmapFrameRenderer = this.bitmapFrameRenderer;
        Bitmap bitmap = targetBitmap.get();
        Intrinsics.checkNotNullExpressionValue(bitmap, "targetBitmap.get()");
        boolean renderFrame = bitmapFrameRenderer.renderFrame(frameNumber, bitmap);
        if (!renderFrame) {
            CloseableReference.closeSafely(targetBitmap);
        }
        return renderFrame;
    }

    private final boolean updatePath(int frameNumber, Bitmap bitmap, float currentBoundsWidth, float currentBoundsHeight) {
        if (this.cornerRadii == null) {
            return false;
        }
        if (frameNumber == this.pathFrameNumber) {
            return true;
        }
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.matrix.setRectToRect(new RectF(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight), new RectF(0.0f, 0.0f, currentBoundsWidth, currentBoundsHeight), Matrix.ScaleToFit.FILL);
        bitmapShader.setLocalMatrix(this.matrix);
        this.paint.setShader(bitmapShader);
        this.path.addRoundRect(new RectF(0.0f, 0.0f, currentBoundsWidth, currentBoundsHeight), this.cornerRadii, Path.Direction.CW);
        this.pathFrameNumber = frameNumber;
        return true;
    }

    private final void drawBitmap(int frameNumber, Bitmap bitmap, Canvas canvas) {
        Rect rect = this.bounds;
        if (rect == null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.paint);
        } else if (updatePath(frameNumber, bitmap, rect.width(), rect.height())) {
            canvas.drawPath(this.path, this.paint);
        } else {
            canvas.drawBitmap(bitmap, (Rect) null, rect, this.paint);
        }
    }

    private final boolean drawBitmapAndCache(int frameNumber, CloseableReference<Bitmap> bitmapReference, Canvas canvas, int frameType) {
        if (bitmapReference == null || !CloseableReference.isValid(bitmapReference)) {
            return false;
        }
        Bitmap bitmap = bitmapReference.get();
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmapReference.get()");
        drawBitmap(frameNumber, bitmap, canvas);
        if (frameType != 3 && !this.isNewRenderImplementation) {
            this.bitmapFrameCache.onFrameRendered(frameNumber, bitmapReference, frameType);
        }
        FrameListener frameListener = this.frameListener;
        if (frameListener == null) {
            return true;
        }
        frameListener.onFrameDrawn(this, frameNumber, frameType);
        return true;
    }
}

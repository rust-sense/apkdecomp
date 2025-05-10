package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BalancedAnimationStrategy.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/OnDemandFrame;", "Ljava/io/Closeable;", "frameNumber", "", "bitmap", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "(ILcom/facebook/common/references/CloseableReference;)V", "getBitmap", "()Lcom/facebook/common/references/CloseableReference;", "getFrameNumber", "()I", "close", "", "isValidFor", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class OnDemandFrame implements Closeable {
    private final CloseableReference<Bitmap> bitmap;
    private final int frameNumber;

    public final CloseableReference<Bitmap> getBitmap() {
        return this.bitmap;
    }

    public final int getFrameNumber() {
        return this.frameNumber;
    }

    public OnDemandFrame(int i, CloseableReference<Bitmap> bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.frameNumber = i;
        this.bitmap = bitmap;
    }

    public final boolean isValidFor(int frameNumber) {
        return this.frameNumber == frameNumber && this.bitmap.isValid();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.bitmap.close();
    }
}

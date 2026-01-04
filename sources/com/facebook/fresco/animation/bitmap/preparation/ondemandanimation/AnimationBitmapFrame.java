package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimationBitmapFrame.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/AnimationBitmapFrame;", "Ljava/io/Closeable;", "frameNumber", "", "bitmap", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "(ILcom/facebook/common/references/CloseableReference;)V", "getBitmap", "()Lcom/facebook/common/references/CloseableReference;", "getFrameNumber", "()I", "setFrameNumber", "(I)V", "close", "", "isValid", "", "isValidFor", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimationBitmapFrame implements Closeable {
    private final CloseableReference<Bitmap> bitmap;
    private int frameNumber;

    public final CloseableReference<Bitmap> getBitmap() {
        return this.bitmap;
    }

    public final int getFrameNumber() {
        return this.frameNumber;
    }

    public final void setFrameNumber(int i) {
        this.frameNumber = i;
    }

    public AnimationBitmapFrame(int i, CloseableReference<Bitmap> bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.frameNumber = i;
        this.bitmap = bitmap;
    }

    public final boolean isValidFor(int frameNumber) {
        return this.frameNumber == frameNumber && this.bitmap.isValid();
    }

    public final boolean isValid() {
        return this.bitmap.isValid();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.bitmap.close();
    }
}

package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameLoader.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\fB\u001d\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameResult;", "", "bitmapRef", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "type", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameResult$FrameType;", "(Lcom/facebook/common/references/CloseableReference;Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameResult$FrameType;)V", "getBitmapRef", "()Lcom/facebook/common/references/CloseableReference;", "getType", "()Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameResult$FrameType;", "FrameType", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FrameResult {
    private final CloseableReference<Bitmap> bitmapRef;
    private final FrameType type;

    /* compiled from: FrameLoader.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameResult$FrameType;", "", "(Ljava/lang/String;I)V", "SUCCESS", "NEAREST", "MISSING", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum FrameType {
        SUCCESS,
        NEAREST,
        MISSING
    }

    public final CloseableReference<Bitmap> getBitmapRef() {
        return this.bitmapRef;
    }

    public final FrameType getType() {
        return this.type;
    }

    public FrameResult(CloseableReference<Bitmap> closeableReference, FrameType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.bitmapRef = closeableReference;
        this.type = type;
    }
}

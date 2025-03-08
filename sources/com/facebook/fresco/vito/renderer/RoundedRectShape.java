package com.facebook.fresco.vito.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Shape.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/facebook/fresco/vito/renderer/RoundedRectShape;", "Lcom/facebook/fresco/vito/renderer/Shape;", "rect", "Landroid/graphics/RectF;", "rx", "", "ry", "(Landroid/graphics/RectF;FF)V", "getRect", "()Landroid/graphics/RectF;", "getRx", "()F", "getRy", "draw", "", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RoundedRectShape extends Shape {
    private final RectF rect;
    private final float rx;
    private final float ry;

    public final RectF getRect() {
        return this.rect;
    }

    public final float getRx() {
        return this.rx;
    }

    public final float getRy() {
        return this.ry;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoundedRectShape(RectF rect, float f, float f2) {
        super(null);
        Intrinsics.checkNotNullParameter(rect, "rect");
        this.rect = rect;
        this.rx = f;
        this.ry = f2;
    }

    @Override // com.facebook.fresco.vito.renderer.Shape
    public void draw(Canvas canvas, Paint paint) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(paint, "paint");
        canvas.drawRoundRect(this.rect, this.rx, this.ry, paint);
    }
}

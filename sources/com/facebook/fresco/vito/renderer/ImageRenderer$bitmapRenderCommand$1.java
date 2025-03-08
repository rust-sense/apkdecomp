package com.facebook.fresco.vito.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: ImageRenderer.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "canvas", "Landroid/graphics/Canvas;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* loaded from: classes.dex */
public final class ImageRenderer$bitmapRenderCommand$1 extends Lambda implements Function1<Canvas, Unit> {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ Matrix $imageTransformation;
    final /* synthetic */ Paint $paint;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageRenderer$bitmapRenderCommand$1(Matrix matrix, Bitmap bitmap, Paint paint) {
        super(1);
        this.$imageTransformation = matrix;
        this.$bitmap = bitmap;
        this.$paint = paint;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Canvas canvas) {
        invoke2(canvas);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.concat(this.$imageTransformation);
        canvas.drawBitmap(this.$bitmap, 0.0f, 0.0f, this.$paint);
    }
}

package com.facebook.fresco.vito.renderer;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: ImageRenderer.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "canvas", "Landroid/graphics/Canvas;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* loaded from: classes.dex */
public final class ImageRenderer$createRenderCommand$2 extends Lambda implements Function1<Canvas, Unit> {
    final /* synthetic */ Matrix $imageTransformation;
    final /* synthetic */ Paint $paint;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ DrawableImageDataModel $this_createRenderCommand;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageRenderer$createRenderCommand$2(DrawableImageDataModel drawableImageDataModel, Paint paint, Matrix matrix, Shape shape) {
        super(1);
        this.$this_createRenderCommand = drawableImageDataModel;
        this.$paint = paint;
        this.$imageTransformation = matrix;
        this.$shape = shape;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Canvas canvas) {
        invoke2(canvas);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.$this_createRenderCommand.getDrawable().setBounds(0, 0, this.$this_createRenderCommand.getWidth(), this.$this_createRenderCommand.getHeight());
        this.$this_createRenderCommand.getDrawable().setColorFilter(null);
        Bitmap createBitmap = Bitmap.createBitmap(this.$this_createRenderCommand.getWidth(), this.$this_createRenderCommand.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
        this.$this_createRenderCommand.getDrawable().draw(new Canvas(createBitmap));
        ImageRenderer imageRenderer = ImageRenderer.INSTANCE;
        Paint paint = this.$paint;
        Matrix matrix = this.$imageTransformation;
        paint.setShader(new BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.getShader().setLocalMatrix(matrix);
        this.$shape.draw(canvas, this.$paint);
    }
}

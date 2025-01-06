package com.facebook.fresco.vito.renderer;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageDataModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/facebook/fresco/vito/renderer/DrawableImageDataModel;", "Lcom/facebook/fresco/vito/renderer/ImageDataModel;", "drawable", "Landroid/graphics/drawable/Drawable;", "(Landroid/graphics/drawable/Drawable;)V", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "height", "", "getHeight", "()I", "width", "getWidth", "setCallback", "", "callback", "Landroid/graphics/drawable/Drawable$Callback;", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public class DrawableImageDataModel extends ImageDataModel {
    private final Drawable drawable;
    private final int height;
    private final int width;

    public final Drawable getDrawable() {
        return this.drawable;
    }

    @Override // com.facebook.fresco.vito.renderer.ImageDataModel
    public int getHeight() {
        return this.height;
    }

    @Override // com.facebook.fresco.vito.renderer.ImageDataModel
    public int getWidth() {
        return this.width;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DrawableImageDataModel(Drawable drawable) {
        super(null);
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        this.drawable = drawable;
        this.width = drawable instanceof NinePatchDrawable ? -1 : drawable.getIntrinsicWidth();
        this.height = drawable instanceof NinePatchDrawable ? -1 : drawable.getIntrinsicHeight();
    }

    @Override // com.facebook.fresco.vito.renderer.ImageDataModel
    public void setCallback(Drawable.Callback callback) {
        this.drawable.setCallback(callback);
    }
}

package com.facebook.fresco.vito.renderer;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageDataModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/facebook/fresco/vito/renderer/BitmapImageDataModel;", "Lcom/facebook/fresco/vito/renderer/ImageDataModel;", "bitmap", "Landroid/graphics/Bitmap;", "isBitmapCircular", "", "(Landroid/graphics/Bitmap;Z)V", "getBitmap", "()Landroid/graphics/Bitmap;", "defaultPaintFlags", "", "getDefaultPaintFlags", "()I", "height", "getHeight", "()Z", "width", "getWidth", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BitmapImageDataModel extends ImageDataModel {
    private final Bitmap bitmap;
    private final int defaultPaintFlags;
    private final int height;
    private final boolean isBitmapCircular;
    private final int width;

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    @Override // com.facebook.fresco.vito.renderer.ImageDataModel
    public int getDefaultPaintFlags() {
        return this.defaultPaintFlags;
    }

    @Override // com.facebook.fresco.vito.renderer.ImageDataModel
    public int getHeight() {
        return this.height;
    }

    @Override // com.facebook.fresco.vito.renderer.ImageDataModel
    public int getWidth() {
        return this.width;
    }

    /* renamed from: isBitmapCircular, reason: from getter */
    public final boolean getIsBitmapCircular() {
        return this.isBitmapCircular;
    }

    public /* synthetic */ BitmapImageDataModel(Bitmap bitmap, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bitmap, (i & 2) != 0 ? false : z);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BitmapImageDataModel(Bitmap bitmap, boolean z) {
        super(null);
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.bitmap = bitmap;
        this.isBitmapCircular = z;
        this.width = bitmap.getWidth();
        this.height = bitmap.getHeight();
        this.defaultPaintFlags = 6;
    }
}

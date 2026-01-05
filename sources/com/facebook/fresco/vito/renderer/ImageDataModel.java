package com.facebook.fresco.vito.renderer;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ImageDataModel.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\u0012\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006\u0082\u0001\u0003\u0011\u0012\u0013¨\u0006\u0014"}, d2 = {"Lcom/facebook/fresco/vito/renderer/ImageDataModel;", "", "()V", "defaultPaintFlags", "", "getDefaultPaintFlags", "()I", "height", "getHeight", "width", "getWidth", "onAttach", "", "onDetach", "setCallback", "callback", "Landroid/graphics/drawable/Drawable$Callback;", "Lcom/facebook/fresco/vito/renderer/BitmapImageDataModel;", "Lcom/facebook/fresco/vito/renderer/ColorIntImageDataModel;", "Lcom/facebook/fresco/vito/renderer/DrawableImageDataModel;", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ImageDataModel {
    private final int defaultPaintFlags;
    private final int height;
    private final int width;

    public /* synthetic */ ImageDataModel(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public int getDefaultPaintFlags() {
        return this.defaultPaintFlags;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void onAttach() {
    }

    public void onDetach() {
    }

    public void setCallback(Drawable.Callback callback) {
    }

    private ImageDataModel() {
        this.width = -1;
        this.height = -1;
        this.defaultPaintFlags = 1;
    }
}

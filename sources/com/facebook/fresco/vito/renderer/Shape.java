package com.facebook.fresco.vito.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Shape.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&\u0082\u0001\u0004\t\n\u000b\f¨\u0006\r"}, d2 = {"Lcom/facebook/fresco/vito/renderer/Shape;", "", "()V", "draw", "", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", "Lcom/facebook/fresco/vito/renderer/CircleShape;", "Lcom/facebook/fresco/vito/renderer/PathShape;", "Lcom/facebook/fresco/vito/renderer/RectShape;", "Lcom/facebook/fresco/vito/renderer/RoundedRectShape;", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class Shape {
    public /* synthetic */ Shape(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract void draw(Canvas canvas, Paint paint);

    private Shape() {
    }
}

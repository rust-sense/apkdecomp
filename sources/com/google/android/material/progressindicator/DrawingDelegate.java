package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

/* loaded from: classes2.dex */
abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    protected DrawableWithAnimatedVisibilityChange drawable;
    S spec;

    abstract void adjustCanvas(Canvas canvas, float f);

    abstract void fillIndicator(Canvas canvas, Paint paint, float f, float f2, int i);

    abstract void fillTrack(Canvas canvas, Paint paint);

    abstract int getPreferredHeight();

    abstract int getPreferredWidth();

    protected void registerDrawable(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange) {
        this.drawable = drawableWithAnimatedVisibilityChange;
    }

    public DrawingDelegate(S s) {
        this.spec = s;
    }

    void validateSpecAndAdjustCanvas(Canvas canvas, float f) {
        this.spec.validateSpec();
        adjustCanvas(canvas, f);
    }
}

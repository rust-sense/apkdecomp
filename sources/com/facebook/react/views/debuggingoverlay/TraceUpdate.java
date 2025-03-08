package com.facebook.react.views.debuggingoverlay;

import android.graphics.RectF;

/* loaded from: classes.dex */
public final class TraceUpdate {
    private final int mColor;
    private final int mId;
    private final RectF mRectangle;

    public int getColor() {
        return this.mColor;
    }

    public int getId() {
        return this.mId;
    }

    public RectF getRectangle() {
        return this.mRectangle;
    }

    public TraceUpdate(int i, RectF rectF, int i2) {
        this.mId = i;
        this.mRectangle = rectF;
        this.mColor = i2;
    }
}

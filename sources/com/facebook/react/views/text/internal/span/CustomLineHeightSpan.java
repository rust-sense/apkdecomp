package com.facebook.react.views.text.internal.span;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/* loaded from: classes.dex */
public class CustomLineHeightSpan implements LineHeightSpan, ReactSpan {
    private final int mHeight;

    public int getLineHeight() {
        return this.mHeight;
    }

    public CustomLineHeightSpan(float f) {
        this.mHeight = (int) Math.ceil(f);
    }

    @Override // android.text.style.LineHeightSpan
    public void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
        int i5 = fontMetricsInt.descent;
        int i6 = this.mHeight;
        if (i5 > i6) {
            int min = Math.min(i6, fontMetricsInt.descent);
            fontMetricsInt.descent = min;
            fontMetricsInt.bottom = min;
            fontMetricsInt.ascent = 0;
            fontMetricsInt.top = 0;
            return;
        }
        if ((-fontMetricsInt.ascent) + fontMetricsInt.descent > this.mHeight) {
            fontMetricsInt.bottom = fontMetricsInt.descent;
            int i7 = (-this.mHeight) + fontMetricsInt.descent;
            fontMetricsInt.ascent = i7;
            fontMetricsInt.top = i7;
            return;
        }
        if ((-fontMetricsInt.ascent) + fontMetricsInt.bottom > this.mHeight) {
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = fontMetricsInt.ascent + this.mHeight;
            return;
        }
        int i8 = (-fontMetricsInt.top) + fontMetricsInt.bottom;
        int i9 = this.mHeight;
        if (i8 > i9) {
            fontMetricsInt.top = fontMetricsInt.bottom - this.mHeight;
            return;
        }
        double d = (i9 - ((-fontMetricsInt.top) + fontMetricsInt.bottom)) / 2.0f;
        int ceil = (int) (fontMetricsInt.top - Math.ceil(d));
        int floor = (int) (fontMetricsInt.bottom + Math.floor(d));
        fontMetricsInt.top = ceil;
        fontMetricsInt.ascent = ceil;
        fontMetricsInt.descent = floor;
        fontMetricsInt.bottom = floor;
    }
}

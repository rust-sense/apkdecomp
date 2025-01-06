package com.facebook.react.views.text.internal.span;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes.dex */
public class CustomLetterSpacingSpan extends MetricAffectingSpan implements ReactSpan {
    private final float mLetterSpacing;

    public float getSpacing() {
        return this.mLetterSpacing;
    }

    public CustomLetterSpacingSpan(float f) {
        this.mLetterSpacing = f;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        apply(textPaint);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        apply(textPaint);
    }

    private void apply(TextPaint textPaint) {
        if (Float.isNaN(this.mLetterSpacing)) {
            return;
        }
        textPaint.setLetterSpacing(this.mLetterSpacing);
    }
}

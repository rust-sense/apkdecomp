package com.facebook.fresco.animation.drawable.animator;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedDrawableValueAnimatorHelper {
    @Nullable
    public static ValueAnimator createValueAnimator(Drawable drawable, int i) {
        if (drawable instanceof AnimatedDrawable2) {
            return AnimatedDrawable2ValueAnimatorHelper.createValueAnimator((AnimatedDrawable2) drawable, i);
        }
        return null;
    }

    @Nullable
    public static ValueAnimator createValueAnimator(Drawable drawable) {
        if (!(drawable instanceof AnimatedDrawable2)) {
            return null;
        }
        AnimatedDrawable2 animatedDrawable2 = (AnimatedDrawable2) drawable;
        return AnimatedDrawable2ValueAnimatorHelper.createValueAnimator(animatedDrawable2, animatedDrawable2.getLoopCount(), animatedDrawable2.getLoopDurationMs());
    }

    @Nullable
    public static ValueAnimator.AnimatorUpdateListener createAnimatorUpdateListener(Drawable drawable) {
        if (drawable instanceof AnimatedDrawable2) {
            return AnimatedDrawable2ValueAnimatorHelper.createAnimatorUpdateListener((AnimatedDrawable2) drawable);
        }
        return null;
    }

    private AnimatedDrawableValueAnimatorHelper() {
    }
}

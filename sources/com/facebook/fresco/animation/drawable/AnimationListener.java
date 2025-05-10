package com.facebook.fresco.animation.drawable;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;

/* compiled from: AnimationListener.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\r"}, d2 = {"Lcom/facebook/fresco/animation/drawable/AnimationListener;", "", "onAnimationFrame", "", "drawable", "Landroid/graphics/drawable/Drawable;", "frameNumber", "", "onAnimationLoaded", "onAnimationRepeat", "onAnimationReset", "onAnimationStart", "onAnimationStop", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface AnimationListener {

    /* compiled from: AnimationListener.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onAnimationLoaded(AnimationListener animationListener) {
        }
    }

    void onAnimationFrame(Drawable drawable, int frameNumber);

    void onAnimationLoaded();

    void onAnimationRepeat(Drawable drawable);

    void onAnimationReset(Drawable drawable);

    void onAnimationStart(Drawable drawable);

    void onAnimationStop(Drawable drawable);
}

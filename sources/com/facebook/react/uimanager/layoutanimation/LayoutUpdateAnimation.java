package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;

/* loaded from: classes.dex */
class LayoutUpdateAnimation extends AbstractLayoutAnimation {
    private static final boolean USE_TRANSLATE_ANIMATION = false;

    LayoutUpdateAnimation() {
    }

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    boolean isValid() {
        return this.mDurationMs > 0;
    }

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    Animation createAnimationImpl(View view, int i, int i2, int i3, int i4) {
        boolean z = (view.getX() == ((float) i) && view.getY() == ((float) i2)) ? false : true;
        boolean z2 = (view.getWidth() == i3 && view.getHeight() == i4) ? false : true;
        if (z || z2) {
            return new PositionAndSizeAnimation(view, i, i2, i3, i4);
        }
        return null;
    }
}

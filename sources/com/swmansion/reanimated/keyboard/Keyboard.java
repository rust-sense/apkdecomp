package com.swmansion.reanimated.keyboard;

import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.uimanager.PixelUtil;

/* loaded from: classes2.dex */
public class Keyboard {
    private static final int CONTENT_TYPE_MASK = WindowInsetsCompat.Type.ime();
    private static final int SYSTEM_BAR_TYPE_MASK = WindowInsetsCompat.Type.systemBars();
    private KeyboardState mState;
    private int mHeight = 0;
    private int mActiveTransitionCounter = 0;

    public int getHeight() {
        return this.mHeight;
    }

    public KeyboardState getState() {
        return this.mState;
    }

    public void updateHeight(WindowInsetsCompat windowInsetsCompat) {
        int dIPFromPixel = (int) PixelUtil.toDIPFromPixel(Math.max(0, windowInsetsCompat.getInsets(CONTENT_TYPE_MASK).bottom - windowInsetsCompat.getInsets(SYSTEM_BAR_TYPE_MASK).bottom));
        if (dIPFromPixel == 0 && this.mState == KeyboardState.OPEN) {
            return;
        }
        this.mHeight = dIPFromPixel;
    }

    public void onAnimationStart() {
        if (this.mActiveTransitionCounter > 0) {
            this.mState = this.mState == KeyboardState.OPENING ? KeyboardState.CLOSING : KeyboardState.OPENING;
        } else {
            this.mState = this.mHeight == 0 ? KeyboardState.OPENING : KeyboardState.CLOSING;
        }
        this.mActiveTransitionCounter++;
    }

    public void onAnimationEnd() {
        int i = this.mActiveTransitionCounter - 1;
        this.mActiveTransitionCounter = i;
        if (i == 0) {
            this.mState = this.mHeight == 0 ? KeyboardState.CLOSED : KeyboardState.OPEN;
        }
    }
}

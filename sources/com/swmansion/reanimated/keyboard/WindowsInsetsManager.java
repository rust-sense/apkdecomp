package com.swmansion.reanimated.keyboard;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.R;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.bridge.ReactApplicationContext;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class WindowsInsetsManager {
    private boolean mIsStatusBarTranslucent = false;
    private final Keyboard mKeyboard;
    private final NotifyAboutKeyboardChangeFunction mNotifyAboutKeyboardChange;
    private final WeakReference<ReactApplicationContext> mReactContext;

    public WindowsInsetsManager(WeakReference<ReactApplicationContext> weakReference, Keyboard keyboard, NotifyAboutKeyboardChangeFunction notifyAboutKeyboardChangeFunction) {
        this.mReactContext = weakReference;
        this.mKeyboard = keyboard;
        this.mNotifyAboutKeyboardChange = notifyAboutKeyboardChangeFunction;
    }

    private Window getWindow() {
        return this.mReactContext.get().getCurrentActivity().getWindow();
    }

    private View getRootView() {
        return getWindow().getDecorView();
    }

    public void startObservingChanges(KeyboardAnimationCallback keyboardAnimationCallback, boolean z) {
        this.mIsStatusBarTranslucent = z;
        updateWindowDecor(false);
        ViewCompat.setOnApplyWindowInsetsListener(getRootView(), new OnApplyWindowInsetsListener() { // from class: com.swmansion.reanimated.keyboard.WindowsInsetsManager$$ExternalSyntheticLambda2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat onApplyWindowInsetsListener;
                onApplyWindowInsetsListener = WindowsInsetsManager.this.onApplyWindowInsetsListener(view, windowInsetsCompat);
                return onApplyWindowInsetsListener;
            }
        });
        ViewCompat.setWindowInsetsAnimationCallback(getRootView(), keyboardAnimationCallback);
    }

    public void stopObservingChanges() {
        updateWindowDecor(!this.mIsStatusBarTranslucent);
        updateInsets(0, 0);
        View rootView = getRootView();
        ViewCompat.setWindowInsetsAnimationCallback(rootView, null);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, null);
    }

    private void updateWindowDecor(final boolean z) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swmansion.reanimated.keyboard.WindowsInsetsManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                WindowsInsetsManager.this.lambda$updateWindowDecor$0(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateWindowDecor$0(boolean z) {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindowInsetsCompat onApplyWindowInsetsListener(View view, WindowInsetsCompat windowInsetsCompat) {
        WindowInsetsCompat onApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
        if (this.mKeyboard.getState() == KeyboardState.OPEN) {
            this.mKeyboard.updateHeight(windowInsetsCompat);
            this.mNotifyAboutKeyboardChange.call();
        }
        setWindowInsets(onApplyWindowInsets);
        return onApplyWindowInsets;
    }

    private void setWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        int systemBars = WindowInsetsCompat.Type.systemBars();
        updateInsets(windowInsetsCompat.getInsets(systemBars).top, windowInsetsCompat.getInsets(systemBars).bottom);
    }

    private void updateInsets(final int i, final int i2) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.swmansion.reanimated.keyboard.WindowsInsetsManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WindowsInsetsManager.this.lambda$updateInsets$1(i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateInsets$1(int i, int i2) {
        getRootView().findViewById(R.id.action_bar_root).setLayoutParams(getLayoutParams(i, i2));
    }

    private FrameLayout.LayoutParams getLayoutParams(int i, int i2) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        if (this.mIsStatusBarTranslucent) {
            layoutParams.setMargins(0, 0, 0, i2);
        } else {
            layoutParams.setMargins(0, i, 0, i2);
        }
        return layoutParams;
    }
}

package com.shopify.reactnative.skia;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import com.facebook.react.views.view.ReactViewGroup;
import expo.modules.haptics.HapticsModule$$ExternalSyntheticApiModelOutline0;

/* loaded from: classes2.dex */
public abstract class SkiaBaseView extends ReactViewGroup implements TextureView.SurfaceTextureListener {
    private TextureView mTexture;
    private String tag;

    private static int motionActionToType(int i) {
        if (i != 0) {
            int i2 = 1;
            if (i == 1) {
                return 2;
            }
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    if (i != 5) {
                        if (i == 6) {
                            return 2;
                        }
                    }
                }
            }
            return i2;
        }
        return 0;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    protected abstract void registerView(int i);

    protected abstract void setDebugMode(boolean z);

    protected abstract void setMode(String str);

    protected abstract void surfaceAvailable(Object obj, int i, int i2);

    protected abstract void surfaceDestroyed();

    protected abstract void surfaceSizeChanged(int i, int i2);

    protected abstract void unregisterView();

    protected abstract void updateTouchPoints(double[] dArr);

    public SkiaBaseView(Context context) {
        super(context);
        this.tag = "SkiaView";
        TextureView textureView = new TextureView(context);
        this.mTexture = textureView;
        textureView.setSurfaceTextureListener(this);
        this.mTexture.setOpaque(false);
        addView(this.mTexture);
    }

    public void destroySurface() {
        Log.i(this.tag, "destroySurface");
        surfaceDestroyed();
    }

    private void createSurfaceTexture() {
        if (Build.VERSION.SDK_INT >= 26) {
            Log.i(this.tag, "Create SurfaceTexture");
            SurfaceTexture m = HapticsModule$$ExternalSyntheticApiModelOutline0.m(false);
            this.mTexture.setSurfaceTexture(m);
            onSurfaceTextureAvailable(m, getMeasuredWidth(), getMeasuredHeight());
        }
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getMeasuredWidth() == 0) {
            createSurfaceTexture();
        }
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Log.i(this.tag, "onLayout " + getMeasuredWidth() + "/" + getMeasuredHeight());
        super.onLayout(z, i, i2, i3, i4);
        this.mTexture.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        if (actionMasked == 5 || actionMasked == 6) {
            motionEvent.getPointerCoords(motionEvent.getActionIndex(), pointerCoords);
            updateTouchPoints(new double[]{pointerCoords.x, pointerCoords.y, motionEvent.getPressure(r5), motionActionToType(actionMasked), motionEvent.getPointerId(r5)});
        } else {
            int pointerCount = motionEvent.getPointerCount();
            double[] dArr = new double[pointerCount * 5];
            int i = 0;
            for (int i2 = 0; i2 < pointerCount; i2++) {
                motionEvent.getPointerCoords(i2, pointerCoords);
                dArr[i] = pointerCoords.x;
                dArr[i + 1] = pointerCoords.y;
                dArr[i + 2] = motionEvent.getPressure(i2);
                int i3 = i + 4;
                dArr[i + 3] = motionActionToType(actionMasked);
                i += 5;
                dArr[i3] = motionEvent.getPointerId(i2);
            }
            updateTouchPoints(dArr);
        }
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.i(this.tag, "onSurfaceTextureAvailable " + i + "/" + i2);
        surfaceAvailable(surfaceTexture, i, i2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Log.i(this.tag, "onSurfaceTextureSizeChanged " + i + "/" + i2);
        surfaceSizeChanged(i, i2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Log.i(this.tag, "onSurfaceTextureDestroyed");
        destroySurface();
        createSurfaceTexture();
        return false;
    }
}

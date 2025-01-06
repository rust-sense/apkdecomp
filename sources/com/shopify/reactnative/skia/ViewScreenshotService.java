package com.shopify.reactnative.skia;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.PixelCopy;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.views.view.ReactViewGroup;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class ViewScreenshotService {
    private static final long SURFACE_VIEW_READ_PIXELS_TIMEOUT = 5;
    private static final String TAG = "SkiaScreenshot";

    public static Bitmap makeViewScreenshotFromTag(ReactContext reactContext, int i) {
        View view;
        try {
            view = UIManagerHelper.getUIManagerForReactTag(reactContext, i).resolveView(i);
        } catch (RuntimeException e) {
            reactContext.handleException(e);
            view = null;
        }
        if (view == null) {
            return null;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint createPaint = createPaint();
        canvas.save();
        canvas.translate(-view.getLeft(), -view.getTop());
        renderViewToCanvas(canvas, view, createPaint, 1.0f);
        canvas.restore();
        return createBitmap;
    }

    private static Paint createPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        return paint;
    }

    private static void renderViewToCanvas(Canvas canvas, View view, Paint paint, float f) {
        float alpha = f * view.getAlpha();
        canvas.save();
        applyTransformations(canvas, view);
        if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            int scrollX = scrollView.getScrollX();
            int scrollY = scrollView.getScrollY();
            canvas.clipRect(scrollX, scrollY, scrollView.getWidth() + scrollX, scrollView.getHeight() + scrollY);
        }
        if (view instanceof ViewGroup) {
            drawBackgroundIfPresent(canvas, view, alpha);
            drawChildren(canvas, (ViewGroup) view, paint, alpha);
        } else {
            drawView(canvas, view, paint, alpha);
        }
        canvas.restore();
    }

    private static void drawBackgroundIfPresent(Canvas canvas, View view, float f) {
        Drawable background = view.getBackground();
        if (background != null) {
            canvas.saveLayerAlpha(null, Math.round(f * 255.0f));
            background.draw(canvas);
            canvas.restore();
        }
    }

    private static void drawChildren(Canvas canvas, ViewGroup viewGroup, Paint paint, float f) {
        if (viewGroup instanceof ReactViewGroup) {
            try {
                Method declaredMethod = ReactViewGroup.class.getDeclaredMethod("dispatchOverflowDraw", Canvas.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(viewGroup, canvas);
            } catch (Exception e) {
                Log.e(TAG, "couldn't invoke dispatchOverflowDraw() on ReactViewGroup", e);
            }
        }
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getVisibility() == 0) {
                if (childAt instanceof TextureView) {
                    drawTextureView(canvas, (TextureView) childAt, paint, f);
                } else if (childAt instanceof SurfaceView) {
                    drawSurfaceView(canvas, (SurfaceView) childAt, paint, f);
                } else {
                    renderViewToCanvas(canvas, childAt, paint, f);
                }
            }
        }
    }

    private static void drawView(Canvas canvas, View view, Paint paint, float f) {
        canvas.saveLayerAlpha(null, Math.round(f * 255.0f));
        view.draw(canvas);
        canvas.restore();
    }

    private static void drawTextureView(Canvas canvas, TextureView textureView, Paint paint, float f) {
        textureView.setOpaque(false);
        Bitmap bitmap = textureView.getBitmap(Bitmap.createBitmap(textureView.getWidth(), textureView.getHeight(), Bitmap.Config.ARGB_8888));
        canvas.save();
        applyTransformations(canvas, textureView);
        paint.setAlpha(Math.round(f * 255.0f));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.restore();
    }

    private static void drawSurfaceView(final Canvas canvas, final SurfaceView surfaceView, final Paint paint, final float f) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        if (Build.VERSION.SDK_INT >= 24) {
            final Bitmap createBitmap = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(), Bitmap.Config.ARGB_8888);
            try {
                PixelCopy.request(surfaceView, createBitmap, new PixelCopy.OnPixelCopyFinishedListener() { // from class: com.shopify.reactnative.skia.ViewScreenshotService$$ExternalSyntheticLambda1
                    @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
                    public final void onPixelCopyFinished(int i) {
                        ViewScreenshotService.lambda$drawSurfaceView$0(canvas, surfaceView, paint, f, createBitmap, countDownLatch, i);
                    }
                }, new Handler(Looper.getMainLooper()));
                countDownLatch.await(5L, TimeUnit.SECONDS);
                return;
            } catch (Exception e) {
                Log.e(TAG, "Cannot PixelCopy for " + surfaceView, e);
                drawSurfaceViewFromCache(canvas, surfaceView, paint, f);
                return;
            }
        }
        drawSurfaceViewFromCache(canvas, surfaceView, paint, f);
    }

    static /* synthetic */ void lambda$drawSurfaceView$0(Canvas canvas, SurfaceView surfaceView, Paint paint, float f, Bitmap bitmap, CountDownLatch countDownLatch, int i) {
        canvas.save();
        applyTransformations(canvas, surfaceView);
        paint.setAlpha(Math.round(f * 255.0f));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.restore();
        countDownLatch.countDown();
    }

    private static void drawSurfaceViewFromCache(Canvas canvas, SurfaceView surfaceView, Paint paint, float f) {
        Bitmap drawingCache = surfaceView.getDrawingCache();
        if (drawingCache != null) {
            canvas.save();
            applyTransformations(canvas, surfaceView);
            paint.setAlpha(Math.round(f * 255.0f));
            canvas.drawBitmap(drawingCache, 0.0f, 0.0f, paint);
            canvas.restore();
        }
    }

    private static void applyTransformations(Canvas canvas, View view) {
        Matrix matrix = view.getMatrix();
        Matrix matrix2 = new Matrix();
        matrix2.setTranslate((view.getLeft() + view.getPaddingLeft()) - view.getScrollX(), (view.getTop() + view.getPaddingTop()) - view.getScrollY());
        canvas.concat(matrix2);
        canvas.concat(matrix);
    }
}

package com.facebook.react.views.debuggingoverlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DebuggingOverlay extends View {
    private final Paint mHighlightedElementsPaint;
    private List<RectF> mHighlightedElementsRectangles;
    private HashMap<Integer, Runnable> mTraceUpdateIdToCleanupRunnableMap;
    private final Paint mTraceUpdatePaint;
    private HashMap<Integer, TraceUpdate> mTraceUpdatesToDisplayMap;

    public DebuggingOverlay(Context context) {
        super(context);
        Paint paint = new Paint();
        this.mTraceUpdatePaint = paint;
        this.mTraceUpdatesToDisplayMap = new HashMap<>();
        this.mTraceUpdateIdToCleanupRunnableMap = new HashMap<>();
        Paint paint2 = new Paint();
        this.mHighlightedElementsPaint = paint2;
        this.mHighlightedElementsRectangles = new ArrayList();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6.0f);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(-859248897);
    }

    public void setTraceUpdates(List<TraceUpdate> list) {
        for (TraceUpdate traceUpdate : list) {
            int id = traceUpdate.getId();
            if (this.mTraceUpdateIdToCleanupRunnableMap.containsKey(Integer.valueOf(id))) {
                UiThreadUtil.removeOnUiThread(this.mTraceUpdateIdToCleanupRunnableMap.get(Integer.valueOf(id)));
                this.mTraceUpdateIdToCleanupRunnableMap.remove(Integer.valueOf(id));
            }
            this.mTraceUpdatesToDisplayMap.put(Integer.valueOf(id), traceUpdate);
        }
        invalidate();
    }

    public void setHighlightedElementsRectangles(List<RectF> list) {
        this.mHighlightedElementsRectangles = list;
        invalidate();
    }

    public void clearElementsHighlights() {
        this.mHighlightedElementsRectangles.clear();
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (TraceUpdate traceUpdate : this.mTraceUpdatesToDisplayMap.values()) {
            this.mTraceUpdatePaint.setColor(traceUpdate.getColor());
            canvas.drawRect(traceUpdate.getRectangle(), this.mTraceUpdatePaint);
            final int id = traceUpdate.getId();
            Runnable runnable = new Runnable() { // from class: com.facebook.react.views.debuggingoverlay.DebuggingOverlay$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DebuggingOverlay.this.lambda$onDraw$0(id);
                }
            };
            if (!this.mTraceUpdateIdToCleanupRunnableMap.containsKey(Integer.valueOf(id))) {
                this.mTraceUpdateIdToCleanupRunnableMap.put(Integer.valueOf(id), runnable);
                UiThreadUtil.runOnUiThread(runnable, 2000L);
            }
        }
        Iterator<RectF> it = this.mHighlightedElementsRectangles.iterator();
        while (it.hasNext()) {
            canvas.drawRect(it.next(), this.mHighlightedElementsPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onDraw$0(int i) {
        this.mTraceUpdatesToDisplayMap.remove(Integer.valueOf(i));
        this.mTraceUpdateIdToCleanupRunnableMap.remove(Integer.valueOf(i));
        invalidate();
    }
}

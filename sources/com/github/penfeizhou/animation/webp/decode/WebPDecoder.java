package com.github.penfeizhou.animation.webp.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import com.github.penfeizhou.animation.decode.Frame;
import com.github.penfeizhou.animation.decode.FrameSeqDecoder;
import com.github.penfeizhou.animation.io.Reader;
import com.github.penfeizhou.animation.loader.Loader;
import com.github.penfeizhou.animation.webp.io.WebPReader;
import com.github.penfeizhou.animation.webp.io.WebPWriter;
import java.io.IOException;

/* loaded from: classes.dex */
public class WebPDecoder extends FrameSeqDecoder<WebPReader, WebPWriter> {
    private static final String TAG = "WebPDecoder";
    private boolean alpha;
    private int backgroundColor;
    private int canvasHeight;
    private int canvasWidth;
    private int loopCount;
    private final Paint mTransparentFillPaint;
    private WebPWriter mWriter;
    private Paint paint;

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected int getLoopCount() {
        return this.loopCount;
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected void release() {
    }

    public WebPDecoder(Loader loader, FrameSeqDecoder.RenderListener renderListener) {
        super(loader, renderListener);
        Paint paint = new Paint();
        this.mTransparentFillPaint = paint;
        paint.setColor(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public WebPWriter getWriter() {
        if (this.mWriter == null) {
            this.mWriter = new WebPWriter();
        }
        return this.mWriter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public WebPReader getReader(Reader reader) {
        return new WebPReader(reader);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public Rect read(WebPReader webPReader) throws IOException {
        boolean z = false;
        boolean z2 = false;
        for (BaseChunk baseChunk : WebPParser.parse(webPReader)) {
            if (baseChunk instanceof VP8XChunk) {
                VP8XChunk vP8XChunk = (VP8XChunk) baseChunk;
                this.canvasWidth = vP8XChunk.canvasWidth;
                this.canvasHeight = vP8XChunk.canvasHeight;
                this.alpha = vP8XChunk.alpha();
                z2 = true;
            } else if (baseChunk instanceof ANIMChunk) {
                ANIMChunk aNIMChunk = (ANIMChunk) baseChunk;
                this.backgroundColor = aNIMChunk.backgroundColor;
                this.loopCount = aNIMChunk.loopCount;
                z = true;
            } else if (baseChunk instanceof ANMFChunk) {
                this.frames.add(new AnimationFrame(webPReader, (ANMFChunk) baseChunk));
            }
        }
        if (!z) {
            if (!z2) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(webPReader.toInputStream(), null, options);
                this.canvasWidth = options.outWidth;
                this.canvasHeight = options.outHeight;
            }
            this.frames.add(new StillFrame(webPReader, this.canvasWidth, this.canvasHeight));
            this.loopCount = 1;
        }
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        if (!this.alpha) {
            this.mTransparentFillPaint.setColor(this.backgroundColor);
        }
        return new Rect(0, 0, this.canvasWidth, this.canvasHeight);
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected void renderFrame(Frame<WebPReader, WebPWriter> frame) {
        Bitmap obtainBitmap;
        if (frame == null || this.fullRect == null || this.fullRect.width() <= 0 || this.fullRect.height() <= 0 || (obtainBitmap = obtainBitmap(this.fullRect.width() / this.sampleSize, this.fullRect.height() / this.sampleSize)) == null) {
            return;
        }
        Canvas canvas = this.cachedCanvas.get(obtainBitmap);
        if (canvas == null) {
            canvas = new Canvas(obtainBitmap);
            this.cachedCanvas.put(obtainBitmap, canvas);
        }
        this.frameBuffer.rewind();
        obtainBitmap.copyPixelsFromBuffer(this.frameBuffer);
        if (this.frameIndex != 0) {
            Frame frame2 = (Frame) this.frames.get(this.frameIndex - 1);
            if ((frame2 instanceof AnimationFrame) && ((AnimationFrame) frame2).disposalMethod) {
                canvas.drawRect((frame2.frameX * 2.0f) / this.sampleSize, (frame2.frameY * 2.0f) / this.sampleSize, ((frame2.frameX * 2) + frame2.frameWidth) / this.sampleSize, ((frame2.frameY * 2) + frame2.frameHeight) / this.sampleSize, this.mTransparentFillPaint);
            }
        } else if (this.alpha) {
            canvas.drawColor(0, PorterDuff.Mode.SRC);
        } else {
            canvas.drawColor(this.backgroundColor, PorterDuff.Mode.SRC);
        }
        Bitmap obtainBitmap2 = (frame.frameWidth <= 0 || frame.frameHeight <= 0) ? null : obtainBitmap(frame.frameWidth / this.sampleSize, frame.frameHeight / this.sampleSize);
        recycleBitmap(frame.draw(canvas, this.paint, this.sampleSize, obtainBitmap2, getWriter()));
        recycleBitmap(obtainBitmap2);
        this.frameBuffer.rewind();
        obtainBitmap.copyPixelsToBuffer(this.frameBuffer);
        recycleBitmap(obtainBitmap);
    }
}

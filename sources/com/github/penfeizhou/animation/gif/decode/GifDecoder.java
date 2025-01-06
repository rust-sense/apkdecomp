package com.github.penfeizhou.animation.gif.decode;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import com.github.penfeizhou.animation.decode.Frame;
import com.github.penfeizhou.animation.decode.FrameSeqDecoder;
import com.github.penfeizhou.animation.gif.io.GifReader;
import com.github.penfeizhou.animation.gif.io.GifWriter;
import com.github.penfeizhou.animation.io.Reader;
import com.github.penfeizhou.animation.loader.Loader;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class GifDecoder extends FrameSeqDecoder<GifReader, GifWriter> {
    private int bgColor;
    private GifWriter mGifWriter;
    private int mLoopCount;
    private final Paint paint;
    private final SnapShot snapShot;

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected int getDesiredSample(int i, int i2) {
        return 1;
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected int getLoopCount() {
        return this.mLoopCount;
    }

    private static class SnapShot {
        ByteBuffer byteBuffer;

        private SnapShot() {
        }
    }

    public GifDecoder(Loader loader, FrameSeqDecoder.RenderListener renderListener) {
        super(loader, renderListener);
        this.mGifWriter = new GifWriter();
        Paint paint = new Paint();
        this.paint = paint;
        this.bgColor = 0;
        this.snapShot = new SnapShot();
        this.mLoopCount = 0;
        paint.setAntiAlias(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public GifWriter getWriter() {
        if (this.mGifWriter == null) {
            this.mGifWriter = new GifWriter();
        }
        return this.mGifWriter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public GifReader getReader(Reader reader) {
        return new GifReader(reader);
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected void release() {
        this.snapShot.byteBuffer = null;
        this.mGifWriter = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public Rect read(GifReader gifReader) throws IOException {
        ColorTable colorTable = null;
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        GraphicControlExtension graphicControlExtension = null;
        for (Block block : GifParser.parse(gifReader)) {
            if (block instanceof LogicalScreenDescriptor) {
                LogicalScreenDescriptor logicalScreenDescriptor = (LogicalScreenDescriptor) block;
                i = logicalScreenDescriptor.screenWidth;
                i2 = logicalScreenDescriptor.screenHeight;
                if (logicalScreenDescriptor.gColorTableFlag()) {
                    i3 = logicalScreenDescriptor.bgColorIndex & 255;
                }
            } else if (block instanceof ColorTable) {
                colorTable = (ColorTable) block;
            } else if (block instanceof GraphicControlExtension) {
                graphicControlExtension = (GraphicControlExtension) block;
            } else if (block instanceof ImageDescriptor) {
                this.frames.add(new GifFrame(gifReader, colorTable, graphicControlExtension, (ImageDescriptor) block));
            } else if (block instanceof ApplicationExtension) {
                ApplicationExtension applicationExtension = (ApplicationExtension) block;
                if ("NETSCAPE2.0".equals(applicationExtension.identifier)) {
                    this.mLoopCount = applicationExtension.loopCount;
                }
            }
        }
        int i4 = i * i2;
        this.frameBuffer = ByteBuffer.allocate(((i4 / (this.sampleSize * this.sampleSize)) + 1) * 4);
        this.snapShot.byteBuffer = ByteBuffer.allocate(((i4 / (this.sampleSize * this.sampleSize)) + 1) * 4);
        if (colorTable != null && i3 >= 0 && i3 < colorTable.getColorTable().length) {
            int i5 = colorTable.getColorTable()[i3];
            this.bgColor = Color.rgb(i5 & 255, (i5 >> 8) & 255, (i5 >> 16) & 255);
        }
        return new Rect(0, 0, i, i2);
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected void renderFrame(Frame<GifReader, GifWriter> frame) {
        GifFrame gifFrame = (GifFrame) frame;
        Bitmap obtainBitmap = obtainBitmap(this.fullRect.width() / this.sampleSize, this.fullRect.height() / this.sampleSize);
        Canvas canvas = this.cachedCanvas.get(obtainBitmap);
        if (canvas == null) {
            canvas = new Canvas(obtainBitmap);
            this.cachedCanvas.put(obtainBitmap, canvas);
        }
        Canvas canvas2 = canvas;
        this.frameBuffer.rewind();
        obtainBitmap.copyPixelsFromBuffer(this.frameBuffer);
        int i = !gifFrame.transparencyFlag() ? this.bgColor : 0;
        if (this.frameIndex == 0) {
            obtainBitmap.eraseColor(i);
        } else {
            GifFrame gifFrame2 = (GifFrame) this.frames.get(this.frameIndex - 1);
            canvas2.save();
            canvas2.clipRect(gifFrame2.frameX / this.sampleSize, gifFrame2.frameY / this.sampleSize, (gifFrame2.frameX + gifFrame2.frameWidth) / this.sampleSize, (gifFrame2.frameY + gifFrame2.frameHeight) / this.sampleSize);
            int i2 = gifFrame2.disposalMethod;
            if (i2 == 2) {
                canvas2.drawColor(this.bgColor, PorterDuff.Mode.CLEAR);
            } else if (i2 == 3) {
                this.snapShot.byteBuffer.rewind();
                canvas2.drawColor(this.bgColor, PorterDuff.Mode.CLEAR);
                Bitmap obtainBitmap2 = obtainBitmap(this.fullRect.width() / this.sampleSize, this.fullRect.height() / this.sampleSize);
                obtainBitmap2.copyPixelsFromBuffer(this.snapShot.byteBuffer);
                canvas2.drawBitmap(obtainBitmap2, 0.0f, 0.0f, this.paint);
                recycleBitmap(obtainBitmap2);
            }
            canvas2.restore();
            if (gifFrame.disposalMethod == 3 && gifFrame2.disposalMethod != 3) {
                this.frameBuffer.rewind();
                this.snapShot.byteBuffer.rewind();
                this.snapShot.byteBuffer.put(this.frameBuffer);
            }
        }
        Bitmap obtainBitmap3 = obtainBitmap(frame.frameWidth / this.sampleSize, frame.frameHeight / this.sampleSize);
        gifFrame.draw(canvas2, this.paint, this.sampleSize, obtainBitmap3, getWriter());
        canvas2.drawColor(i, PorterDuff.Mode.DST_OVER);
        recycleBitmap(obtainBitmap3);
        this.frameBuffer.rewind();
        obtainBitmap.copyPixelsToBuffer(this.frameBuffer);
        recycleBitmap(obtainBitmap);
    }
}

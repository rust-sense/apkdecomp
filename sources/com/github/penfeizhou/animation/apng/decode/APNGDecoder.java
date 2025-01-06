package com.github.penfeizhou.animation.apng.decode;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.Log;
import com.github.penfeizhou.animation.apng.io.APNGReader;
import com.github.penfeizhou.animation.apng.io.APNGWriter;
import com.github.penfeizhou.animation.decode.Frame;
import com.github.penfeizhou.animation.decode.FrameSeqDecoder;
import com.github.penfeizhou.animation.io.Reader;
import com.github.penfeizhou.animation.loader.Loader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class APNGDecoder extends FrameSeqDecoder<APNGReader, APNGWriter> {
    private static final String TAG = "APNGDecoder";
    private APNGWriter apngWriter;
    private int mLoopCount;
    private final Paint paint;
    private final SnapShot snapShot;

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected int getLoopCount() {
        return this.mLoopCount;
    }

    private static class SnapShot {
        ByteBuffer byteBuffer;
        byte dispose_op;
        Rect dstRect;

        private SnapShot() {
            this.dstRect = new Rect();
        }
    }

    public APNGDecoder(Loader loader, FrameSeqDecoder.RenderListener renderListener) {
        super(loader, renderListener);
        Paint paint = new Paint();
        this.paint = paint;
        this.snapShot = new SnapShot();
        paint.setAntiAlias(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public APNGWriter getWriter() {
        if (this.apngWriter == null) {
            this.apngWriter = new APNGWriter();
        }
        return this.apngWriter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public APNGReader getReader(Reader reader) {
        return new APNGReader(reader);
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected void release() {
        this.snapShot.byteBuffer = null;
        this.apngWriter = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    public Rect read(APNGReader aPNGReader) throws IOException {
        List<Chunk> parse = APNGParser.parse(aPNGReader);
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[0];
        Iterator<Chunk> it = parse.iterator();
        APNGFrame aPNGFrame = null;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Chunk next = it.next();
            boolean z2 = next instanceof IENDChunk;
            if (z2) {
                Log.e(TAG, "chunk read reach to end");
                break;
            }
            if (next instanceof ACTLChunk) {
                this.mLoopCount = ((ACTLChunk) next).num_plays;
                z = true;
            } else if (next instanceof FCTLChunk) {
                aPNGFrame = new APNGFrame(aPNGReader, (FCTLChunk) next);
                aPNGFrame.prefixChunks = arrayList;
                aPNGFrame.ihdrData = bArr;
                this.frames.add(aPNGFrame);
            } else if (next instanceof FDATChunk) {
                if (aPNGFrame != null) {
                    aPNGFrame.imageChunks.add(next);
                }
            } else if (next instanceof IDATChunk) {
                if (!z) {
                    StillFrame stillFrame = new StillFrame(aPNGReader);
                    stillFrame.frameWidth = i;
                    stillFrame.frameHeight = i2;
                    this.frames.add(stillFrame);
                    this.mLoopCount = 1;
                    break;
                }
                if (aPNGFrame != null) {
                    aPNGFrame.imageChunks.add(next);
                }
            } else if (next instanceof IHDRChunk) {
                IHDRChunk iHDRChunk = (IHDRChunk) next;
                i = iHDRChunk.width;
                i2 = iHDRChunk.height;
                bArr = iHDRChunk.data;
            } else if (!z2) {
                arrayList.add(next);
            }
        }
        int i3 = i * i2;
        this.frameBuffer = ByteBuffer.allocate(((i3 / (this.sampleSize * this.sampleSize)) + 1) * 4);
        this.snapShot.byteBuffer = ByteBuffer.allocate(((i3 / (this.sampleSize * this.sampleSize)) + 1) * 4);
        return new Rect(0, 0, i, i2);
    }

    @Override // com.github.penfeizhou.animation.decode.FrameSeqDecoder
    protected void renderFrame(Frame<APNGReader, APNGWriter> frame) {
        if (frame == null || this.fullRect == null) {
            return;
        }
        try {
            Bitmap obtainBitmap = obtainBitmap(this.fullRect.width() / this.sampleSize, this.fullRect.height() / this.sampleSize);
            Canvas canvas = this.cachedCanvas.get(obtainBitmap);
            if (canvas == null) {
                canvas = new Canvas(obtainBitmap);
                this.cachedCanvas.put(obtainBitmap, canvas);
            }
            Canvas canvas2 = canvas;
            if (frame instanceof APNGFrame) {
                this.frameBuffer.rewind();
                obtainBitmap.copyPixelsFromBuffer(this.frameBuffer);
                if (this.frameIndex == 0) {
                    canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
                } else {
                    canvas2.save();
                    canvas2.clipRect(this.snapShot.dstRect);
                    byte b = this.snapShot.dispose_op;
                    if (b == 1) {
                        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
                    } else if (b == 2) {
                        this.snapShot.byteBuffer.rewind();
                        obtainBitmap.copyPixelsFromBuffer(this.snapShot.byteBuffer);
                    }
                    canvas2.restore();
                }
                if (((APNGFrame) frame).dispose_op == 2 && this.snapShot.dispose_op != 2) {
                    this.snapShot.byteBuffer.rewind();
                    obtainBitmap.copyPixelsToBuffer(this.snapShot.byteBuffer);
                }
                this.snapShot.dispose_op = ((APNGFrame) frame).dispose_op;
                canvas2.save();
                if (((APNGFrame) frame).blend_op == 0) {
                    canvas2.clipRect(frame.frameX / this.sampleSize, frame.frameY / this.sampleSize, (frame.frameX + frame.frameWidth) / this.sampleSize, (frame.frameY + frame.frameHeight) / this.sampleSize);
                    canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
                }
                this.snapShot.dstRect.set(frame.frameX / this.sampleSize, frame.frameY / this.sampleSize, (frame.frameX + frame.frameWidth) / this.sampleSize, (frame.frameY + frame.frameHeight) / this.sampleSize);
                canvas2.restore();
            }
            Bitmap obtainBitmap2 = obtainBitmap(frame.frameWidth, frame.frameHeight);
            recycleBitmap(frame.draw(canvas2, this.paint, this.sampleSize, obtainBitmap2, getWriter()));
            recycleBitmap(obtainBitmap2);
            this.frameBuffer.rewind();
            obtainBitmap.copyPixelsToBuffer(this.frameBuffer);
            recycleBitmap(obtainBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.github.penfeizhou.animation.apng.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.penfeizhou.animation.apng.io.APNGReader;
import com.github.penfeizhou.animation.apng.io.APNGWriter;
import com.github.penfeizhou.animation.decode.Frame;
import java.io.IOException;

/* loaded from: classes.dex */
public class StillFrame extends Frame<APNGReader, APNGWriter> {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public StillFrame(APNGReader aPNGReader) {
        super(aPNGReader);
    }

    @Override // com.github.penfeizhou.animation.decode.Frame
    public Bitmap draw(Canvas canvas, Paint paint, int i, Bitmap bitmap, APNGWriter aPNGWriter) {
        Bitmap decodeStream;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        options.inMutable = true;
        options.inBitmap = bitmap;
        Bitmap bitmap2 = null;
        try {
            ((APNGReader) this.reader).reset();
            try {
                decodeStream = BitmapFactory.decodeStream(((APNGReader) this.reader).toInputStream(), null, options);
            } catch (IllegalArgumentException unused) {
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inJustDecodeBounds = false;
                options2.inSampleSize = i;
                options2.inMutable = true;
                decodeStream = BitmapFactory.decodeStream(((APNGReader) this.reader).toInputStream(), null, options2);
            }
        } catch (IOException e) {
            e = e;
            e.printStackTrace();
            return bitmap2;
        }
        try {
            paint.setXfermode(null);
            canvas.drawBitmap(decodeStream, 0.0f, 0.0f, paint);
            return decodeStream;
        } catch (IOException e2) {
            e = e2;
            bitmap2 = decodeStream;
            e.printStackTrace();
            return bitmap2;
        }
    }
}

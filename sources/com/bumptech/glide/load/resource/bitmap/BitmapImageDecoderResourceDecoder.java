package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.resource.DefaultOnHeaderDecodedListener;
import com.facebook.hermes.intl.Intl$$ExternalSyntheticApiModelOutline0;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.IOException;

/* loaded from: classes.dex */
public final class BitmapImageDecoderResourceDecoder implements ResourceDecoder<ImageDecoder.Source, Bitmap> {
    private static final String TAG = "BitmapImageDecoder";
    private final BitmapPool bitmapPool = new BitmapPoolAdapter();

    /* renamed from: handles, reason: avoid collision after fix types in other method */
    public boolean handles2(ImageDecoder.Source source, Options options) throws IOException {
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ Resource<Bitmap> decode(ImageDecoder.Source source, int i, int i2, Options options) throws IOException {
        return decode2(Intl$$ExternalSyntheticApiModelOutline0.m((Object) source), i, i2, options);
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ boolean handles(ImageDecoder.Source source, Options options) throws IOException {
        return handles2(Intl$$ExternalSyntheticApiModelOutline0.m((Object) source), options);
    }

    /* renamed from: decode, reason: avoid collision after fix types in other method */
    public Resource<Bitmap> decode2(ImageDecoder.Source source, int i, int i2, Options options) throws IOException {
        Bitmap decodeBitmap;
        decodeBitmap = ImageDecoder.decodeBitmap(source, new DefaultOnHeaderDecodedListener(i, i2, options));
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Decoded [" + decodeBitmap.getWidth() + ViewHierarchyNode.JsonKeys.X + decodeBitmap.getHeight() + "] for [" + i + ViewHierarchyNode.JsonKeys.X + i2 + "]");
        }
        return new BitmapResource(decodeBitmap, this.bitmapPool);
    }
}

package com.bumptech.glide.integration.avif;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.module.LibraryGlideModule;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class AvifGlideModule extends LibraryGlideModule {
    @Override // com.bumptech.glide.module.LibraryGlideModule, com.bumptech.glide.module.RegistersComponents
    public void registerComponents(Context context, Glide glide, Registry registry) {
        AvifByteBufferBitmapDecoder avifByteBufferBitmapDecoder = new AvifByteBufferBitmapDecoder(glide.getBitmapPool());
        registry.prepend(ByteBuffer.class, Bitmap.class, avifByteBufferBitmapDecoder);
        registry.prepend(InputStream.class, Bitmap.class, new AvifStreamBitmapDecoder(registry.getImageHeaderParsers(), avifByteBufferBitmapDecoder, glide.getArrayPool()));
    }
}

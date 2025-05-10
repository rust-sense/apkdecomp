package com.github.penfeizhou.animation.glide;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.github.penfeizhou.animation.apng.decode.APNGDecoder;
import com.github.penfeizhou.animation.apng.decode.APNGParser;
import com.github.penfeizhou.animation.avif.decode.AVIFDecoder;
import com.github.penfeizhou.animation.avif.decode.AVIFParser;
import com.github.penfeizhou.animation.decode.FrameSeqDecoder;
import com.github.penfeizhou.animation.gif.decode.GifDecoder;
import com.github.penfeizhou.animation.gif.decode.GifParser;
import com.github.penfeizhou.animation.io.ByteBufferReader;
import com.github.penfeizhou.animation.loader.ByteBufferLoader;
import com.github.penfeizhou.animation.webp.decode.WebPDecoder;
import com.github.penfeizhou.animation.webp.decode.WebPParser;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ByteBufferAnimationDecoder implements ResourceDecoder<ByteBuffer, FrameSeqDecoder> {
    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(ByteBuffer byteBuffer, Options options) {
        return (!((Boolean) options.get(AnimationDecoderOption.DISABLE_ANIMATION_WEBP_DECODER)).booleanValue() && WebPParser.isAWebP(new ByteBufferReader(byteBuffer))) || (!((Boolean) options.get(AnimationDecoderOption.DISABLE_ANIMATION_APNG_DECODER)).booleanValue() && APNGParser.isAPNG(new ByteBufferReader(byteBuffer))) || ((!((Boolean) options.get(AnimationDecoderOption.DISABLE_ANIMATION_GIF_DECODER)).booleanValue() && GifParser.isGif(new ByteBufferReader(byteBuffer))) || (!((Boolean) options.get(AnimationDecoderOption.DISABLE_ANIMATION_AVIF_DECODER)).booleanValue() && AVIFParser.isAVIF(new ByteBufferReader(byteBuffer))));
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public Resource<FrameSeqDecoder> decode(final ByteBuffer byteBuffer, int i, int i2, Options options) throws IOException {
        FrameSeqDecoder aVIFDecoder;
        ByteBufferLoader byteBufferLoader = new ByteBufferLoader() { // from class: com.github.penfeizhou.animation.glide.ByteBufferAnimationDecoder.1
            @Override // com.github.penfeizhou.animation.loader.ByteBufferLoader
            public ByteBuffer getByteBuffer() {
                byteBuffer.position(0);
                return byteBuffer;
            }
        };
        if (WebPParser.isAWebP(new ByteBufferReader(byteBuffer))) {
            aVIFDecoder = new WebPDecoder(byteBufferLoader, null);
        } else if (APNGParser.isAPNG(new ByteBufferReader(byteBuffer))) {
            aVIFDecoder = new APNGDecoder(byteBufferLoader, null);
        } else if (GifParser.isGif(new ByteBufferReader(byteBuffer))) {
            aVIFDecoder = new GifDecoder(byteBufferLoader, null);
        } else {
            if (!AVIFParser.isAVIF(new ByteBufferReader(byteBuffer))) {
                return null;
            }
            aVIFDecoder = new AVIFDecoder(byteBufferLoader, null);
        }
        return new FrameSeqDecoderResource(aVIFDecoder, byteBuffer.limit());
    }

    private static class FrameSeqDecoderResource implements Resource<FrameSeqDecoder> {
        private final FrameSeqDecoder decoder;
        private final int size;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.load.engine.Resource
        public FrameSeqDecoder get() {
            return this.decoder;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public int getSize() {
            return this.size;
        }

        FrameSeqDecoderResource(FrameSeqDecoder frameSeqDecoder, int i) {
            this.decoder = frameSeqDecoder;
            this.size = i;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public Class<FrameSeqDecoder> getResourceClass() {
            return FrameSeqDecoder.class;
        }

        @Override // com.bumptech.glide.load.engine.Resource
        public void recycle() {
            this.decoder.stop();
        }
    }
}

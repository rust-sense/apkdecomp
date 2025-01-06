package com.github.penfeizhou.animation.webp.decode;

import com.github.penfeizhou.animation.webp.io.WebPReader;
import java.io.IOException;

/* loaded from: classes.dex */
public class ANMFChunk extends BaseChunk {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FLAG_BLENDING_METHOD = 2;
    private static final int FLAG_DISPOSAL_METHOD = 1;
    static final int ID = BaseChunk.fourCCToInt("ANMF");
    ALPHChunk alphChunk;
    byte flags;
    int frameDuration;
    int frameHeight;
    int frameWidth;
    int frameX;
    int frameY;
    VP8Chunk vp8Chunk;
    VP8LChunk vp8LChunk;

    boolean blendingMethod() {
        return (this.flags & 2) == 2;
    }

    boolean disposalMethod() {
        return (this.flags & 1) == 1;
    }

    @Override // com.github.penfeizhou.animation.webp.decode.BaseChunk
    void innerParse(WebPReader webPReader) throws IOException {
        int available = webPReader.available();
        this.frameX = webPReader.getUInt24();
        this.frameY = webPReader.getUInt24();
        this.frameWidth = webPReader.get1Based();
        this.frameHeight = webPReader.get1Based();
        this.frameDuration = webPReader.getUInt24();
        this.flags = webPReader.peek();
        long j = available - this.payloadSize;
        while (webPReader.available() > j) {
            BaseChunk parseChunk = WebPParser.parseChunk(webPReader);
            if (parseChunk instanceof ALPHChunk) {
                this.alphChunk = (ALPHChunk) parseChunk;
            } else if (parseChunk instanceof VP8Chunk) {
                this.vp8Chunk = (VP8Chunk) parseChunk;
            } else if (parseChunk instanceof VP8LChunk) {
                this.vp8LChunk = (VP8LChunk) parseChunk;
            }
        }
    }
}

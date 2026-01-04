package com.github.penfeizhou.animation.webp.decode;

import com.github.penfeizhou.animation.webp.io.WebPReader;
import java.io.IOException;

/* loaded from: classes.dex */
public class VP8XChunk extends BaseChunk {
    private static final int FLAG_ALPHA = 16;
    private static final int FLAG_ANIMATION = 2;
    static final int ID = BaseChunk.fourCCToInt("VP8X");
    public int canvasHeight;
    public int canvasWidth;
    byte flags;

    boolean alpha() {
        return (this.flags & 16) == 16;
    }

    boolean animation() {
        return (this.flags & 2) == 2;
    }

    @Override // com.github.penfeizhou.animation.webp.decode.BaseChunk
    void innerParse(WebPReader webPReader) throws IOException {
        this.flags = webPReader.peek();
        webPReader.skip(3L);
        this.canvasWidth = webPReader.get1Based();
        this.canvasHeight = webPReader.get1Based();
    }
}

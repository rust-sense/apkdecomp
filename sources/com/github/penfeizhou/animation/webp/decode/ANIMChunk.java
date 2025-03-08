package com.github.penfeizhou.animation.webp.decode;

import com.github.penfeizhou.animation.webp.io.WebPReader;
import java.io.IOException;

/* loaded from: classes.dex */
public class ANIMChunk extends BaseChunk {
    static final int ID = BaseChunk.fourCCToInt("ANIM");
    int backgroundColor;
    int loopCount;

    @Override // com.github.penfeizhou.animation.webp.decode.BaseChunk
    void innerParse(WebPReader webPReader) throws IOException {
        this.backgroundColor = webPReader.getUInt32();
        this.loopCount = webPReader.getUInt16();
    }
}

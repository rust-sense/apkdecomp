package com.github.penfeizhou.animation.apng.decode;

import com.github.penfeizhou.animation.apng.io.APNGReader;
import java.io.IOException;

/* loaded from: classes.dex */
class FDATChunk extends Chunk {
    static final int ID = Chunk.fourCCToInt("fdAT");
    int sequence_number;

    FDATChunk() {
    }

    @Override // com.github.penfeizhou.animation.apng.decode.Chunk
    void innerParse(APNGReader aPNGReader) throws IOException {
        this.sequence_number = aPNGReader.readInt();
    }
}

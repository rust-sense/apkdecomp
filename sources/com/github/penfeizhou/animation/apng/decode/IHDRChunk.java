package com.github.penfeizhou.animation.apng.decode;

import com.github.penfeizhou.animation.apng.io.APNGReader;
import java.io.IOException;

/* loaded from: classes.dex */
class IHDRChunk extends Chunk {
    static final int ID = Chunk.fourCCToInt("IHDR");
    byte[] data = new byte[5];
    int height;
    int width;

    IHDRChunk() {
    }

    @Override // com.github.penfeizhou.animation.apng.decode.Chunk
    void innerParse(APNGReader aPNGReader) throws IOException {
        this.width = aPNGReader.readInt();
        this.height = aPNGReader.readInt();
        byte[] bArr = this.data;
        aPNGReader.read(bArr, 0, bArr.length);
    }
}

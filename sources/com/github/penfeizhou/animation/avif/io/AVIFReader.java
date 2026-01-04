package com.github.penfeizhou.animation.avif.io;

import com.github.penfeizhou.animation.io.FilterReader;
import com.github.penfeizhou.animation.io.Reader;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class AVIFReader extends FilterReader {
    private ByteBuffer cachedBuffer;

    public AVIFReader(Reader reader) {
        super(reader);
        this.cachedBuffer = null;
    }

    public ByteBuffer toDirectByteBuffer() throws IOException {
        if (this.cachedBuffer == null) {
            int available = available();
            byte[] bArr = new byte[available];
            read(bArr, 0, available);
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(available);
            this.cachedBuffer = allocateDirect;
            allocateDirect.put(bArr);
        }
        this.cachedBuffer.flip();
        return this.cachedBuffer;
    }
}

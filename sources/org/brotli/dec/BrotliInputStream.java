package org.brotli.dec;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class BrotliInputStream extends InputStream {
    public static final int DEFAULT_INTERNAL_BUFFER_SIZE = 16384;
    private byte[] buffer;
    private int bufferOffset;
    private int remainingBufferBytes;
    private final State state;

    public BrotliInputStream(InputStream inputStream) throws IOException {
        this(inputStream, 16384, null);
    }

    public BrotliInputStream(InputStream inputStream, int i) throws IOException {
        this(inputStream, i, null);
    }

    public BrotliInputStream(InputStream inputStream, int i, byte[] bArr) throws IOException {
        State state = new State();
        this.state = state;
        if (i <= 0) {
            throw new IllegalArgumentException("Bad buffer size:" + i);
        }
        if (inputStream == null) {
            throw new IllegalArgumentException("source is null");
        }
        this.buffer = new byte[i];
        this.remainingBufferBytes = 0;
        this.bufferOffset = 0;
        try {
            State.setInput(state, inputStream);
            if (bArr != null) {
                Decode.setCustomDictionary(state, bArr);
            }
        } catch (BrotliRuntimeException e) {
            throw new IOException("Brotli decoder initialization failed", e);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        State.close(this.state);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.bufferOffset >= this.remainingBufferBytes) {
            byte[] bArr = this.buffer;
            int read = read(bArr, 0, bArr.length);
            this.remainingBufferBytes = read;
            this.bufferOffset = 0;
            if (read == -1) {
                return -1;
            }
        }
        byte[] bArr2 = this.buffer;
        int i = this.bufferOffset;
        this.bufferOffset = i + 1;
        return bArr2[i] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("Bad offset: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Bad length: " + i2);
        }
        int i3 = i + i2;
        if (i3 > bArr.length) {
            throw new IllegalArgumentException("Buffer overflow: " + i3 + " > " + bArr.length);
        }
        if (i2 == 0) {
            return 0;
        }
        int max = Math.max(this.remainingBufferBytes - this.bufferOffset, 0);
        if (max != 0) {
            max = Math.min(max, i2);
            System.arraycopy(this.buffer, this.bufferOffset, bArr, i, max);
            this.bufferOffset += max;
            i += max;
            i2 -= max;
            if (i2 == 0) {
                return max;
            }
        }
        try {
            this.state.output = bArr;
            this.state.outputOffset = i;
            this.state.outputLength = i2;
            this.state.outputUsed = 0;
            Decode.decompress(this.state);
            if (this.state.outputUsed == 0) {
                return -1;
            }
            return this.state.outputUsed + max;
        } catch (BrotliRuntimeException e) {
            throw new IOException("Brotli stream decoding failed", e);
        }
    }
}

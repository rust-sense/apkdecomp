package com.github.penfeizhou.animation.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class StreamReader extends FilterInputStream implements Reader {
    private int position;

    @Override // com.github.penfeizhou.animation.io.Reader
    public int position() {
        return this.position;
    }

    @Override // com.github.penfeizhou.animation.io.Reader
    public InputStream toInputStream() throws IOException {
        return this;
    }

    public StreamReader(InputStream inputStream) {
        super(inputStream);
        try {
            inputStream.reset();
        } catch (IOException unused) {
        }
    }

    @Override // com.github.penfeizhou.animation.io.Reader
    public byte peek() throws IOException {
        byte read = (byte) read();
        this.position++;
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, com.github.penfeizhou.animation.io.Reader
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        this.position += Math.max(0, read);
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, com.github.penfeizhou.animation.io.Reader
    public synchronized void reset() throws IOException {
        super.reset();
        this.position = 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, com.github.penfeizhou.animation.io.Reader
    public long skip(long j) throws IOException {
        long j2 = j;
        while (j2 > 0) {
            long skip = super.skip(j2);
            if (skip > 0) {
                j2 -= skip;
            } else {
                if (super.read() == -1) {
                    break;
                }
                j2--;
            }
        }
        long j3 = j - j2;
        this.position = (int) (this.position + j3);
        return j3;
    }
}

package com.github.penfeizhou.animation.io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public class ByteBufferWriter implements Writer {
    protected ByteBuffer byteBuffer;

    @Override // com.github.penfeizhou.animation.io.Writer
    public void close() {
    }

    public ByteBufferWriter() {
        reset(10240);
    }

    @Override // com.github.penfeizhou.animation.io.Writer
    public void putByte(byte b) {
        this.byteBuffer.put(b);
    }

    @Override // com.github.penfeizhou.animation.io.Writer
    public void putBytes(byte[] bArr) {
        this.byteBuffer.put(bArr);
    }

    @Override // com.github.penfeizhou.animation.io.Writer
    public int position() {
        return this.byteBuffer.position();
    }

    @Override // com.github.penfeizhou.animation.io.Writer
    public void skip(int i) {
        this.byteBuffer.position(i + position());
    }

    @Override // com.github.penfeizhou.animation.io.Writer
    public byte[] toByteArray() {
        return this.byteBuffer.array();
    }

    @Override // com.github.penfeizhou.animation.io.Writer
    public void reset(int i) {
        ByteBuffer byteBuffer = this.byteBuffer;
        if (byteBuffer == null || i > byteBuffer.capacity()) {
            ByteBuffer allocate = ByteBuffer.allocate(i);
            this.byteBuffer = allocate;
            allocate.order(ByteOrder.LITTLE_ENDIAN);
        }
        this.byteBuffer.clear();
    }
}

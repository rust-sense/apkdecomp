package org.brotli.dec;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
final class BitReader {
    private static final int BYTE_BUFFER_SIZE = 4160;
    private static final int BYTE_READ_SIZE = 4096;
    private static final int CAPACITY = 1024;
    private static final int INT_BUFFER_SIZE = 1040;
    private static final int SLACK = 16;
    long accumulator;
    int bitOffset;
    private boolean endOfStreamReached;
    private InputStream input;
    private int intOffset;
    private final byte[] byteBuffer = new byte[BYTE_BUFFER_SIZE];
    private final int[] intBuffer = new int[INT_BUFFER_SIZE];
    private final IntReader intReader = new IntReader();
    private int tailBytes = 0;

    BitReader() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0037, code lost:
    
        r4.endOfStreamReached = true;
        r4.tailBytes = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003c, code lost:
    
        r1 = r1 + 3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void readMoreInput(org.brotli.dec.BitReader r4) {
        /*
            int r0 = r4.intOffset
            r1 = 1015(0x3f7, float:1.422E-42)
            if (r0 > r1) goto L7
            return
        L7:
            boolean r1 = r4.endOfStreamReached
            if (r1 == 0) goto L1b
            int r4 = intAvailable(r4)
            r0 = -2
            if (r4 < r0) goto L13
            return
        L13:
            org.brotli.dec.BrotliRuntimeException r4 = new org.brotli.dec.BrotliRuntimeException
            java.lang.String r0 = "No more input"
            r4.<init>(r0)
            throw r4
        L1b:
            int r0 = r0 << 2
            int r1 = 4096 - r0
            byte[] r2 = r4.byteBuffer
            r3 = 0
            java.lang.System.arraycopy(r2, r0, r2, r3, r1)
            r4.intOffset = r3
        L27:
            r0 = 4096(0x1000, float:5.74E-42)
            if (r1 >= r0) goto L4a
            java.io.InputStream r0 = r4.input     // Catch: java.io.IOException -> L41
            byte[] r2 = r4.byteBuffer     // Catch: java.io.IOException -> L41
            int r3 = 4096 - r1
            int r0 = r0.read(r2, r1, r3)     // Catch: java.io.IOException -> L41
            if (r0 > 0) goto L3f
            r0 = 1
            r4.endOfStreamReached = r0     // Catch: java.io.IOException -> L41
            r4.tailBytes = r1     // Catch: java.io.IOException -> L41
            int r1 = r1 + 3
            goto L4a
        L3f:
            int r1 = r1 + r0
            goto L27
        L41:
            r4 = move-exception
            org.brotli.dec.BrotliRuntimeException r0 = new org.brotli.dec.BrotliRuntimeException
            java.lang.String r1 = "Failed to read input"
            r0.<init>(r1, r4)
            throw r0
        L4a:
            org.brotli.dec.IntReader r4 = r4.intReader
            int r0 = r1 >> 2
            org.brotli.dec.IntReader.convert(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.brotli.dec.BitReader.readMoreInput(org.brotli.dec.BitReader):void");
    }

    static void checkHealth(BitReader bitReader, boolean z) {
        if (bitReader.endOfStreamReached) {
            int i = ((bitReader.intOffset << 2) + ((bitReader.bitOffset + 7) >> 3)) - 8;
            int i2 = bitReader.tailBytes;
            if (i > i2) {
                throw new BrotliRuntimeException("Read after end");
            }
            if (z && i != i2) {
                throw new BrotliRuntimeException("Unused bytes after end");
            }
        }
    }

    static void fillBitWindow(BitReader bitReader) {
        int i = bitReader.bitOffset;
        if (i >= 32) {
            int[] iArr = bitReader.intBuffer;
            bitReader.intOffset = bitReader.intOffset + 1;
            bitReader.accumulator = (iArr[r3] << 32) | (bitReader.accumulator >>> 32);
            bitReader.bitOffset = i - 32;
        }
    }

    static int readBits(BitReader bitReader, int i) {
        fillBitWindow(bitReader);
        long j = bitReader.accumulator;
        int i2 = bitReader.bitOffset;
        int i3 = ((int) (j >>> i2)) & ((1 << i) - 1);
        bitReader.bitOffset = i2 + i;
        return i3;
    }

    static void init(BitReader bitReader, InputStream inputStream) {
        if (bitReader.input != null) {
            throw new IllegalStateException("Bit reader already has associated input stream");
        }
        IntReader.init(bitReader.intReader, bitReader.byteBuffer, bitReader.intBuffer);
        bitReader.input = inputStream;
        bitReader.accumulator = 0L;
        bitReader.bitOffset = 64;
        bitReader.intOffset = 1024;
        bitReader.endOfStreamReached = false;
        prepare(bitReader);
    }

    private static void prepare(BitReader bitReader) {
        readMoreInput(bitReader);
        checkHealth(bitReader, false);
        fillBitWindow(bitReader);
        fillBitWindow(bitReader);
    }

    static void reload(BitReader bitReader) {
        if (bitReader.bitOffset == 64) {
            prepare(bitReader);
        }
    }

    static void close(BitReader bitReader) throws IOException {
        InputStream inputStream = bitReader.input;
        bitReader.input = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    static void jumpToByteBoundary(BitReader bitReader) {
        int i = (64 - bitReader.bitOffset) & 7;
        if (i != 0 && readBits(bitReader, i) != 0) {
            throw new BrotliRuntimeException("Corrupted padding bits");
        }
    }

    static int intAvailable(BitReader bitReader) {
        return (bitReader.endOfStreamReached ? (bitReader.tailBytes + 3) >> 2 : 1024) - bitReader.intOffset;
    }

    static void copyBytes(BitReader bitReader, byte[] bArr, int i, int i2) {
        if ((bitReader.bitOffset & 7) != 0) {
            throw new BrotliRuntimeException("Unaligned copyBytes");
        }
        while (true) {
            int i3 = bitReader.bitOffset;
            if (i3 == 64 || i2 == 0) {
                break;
            }
            bArr[i] = (byte) (bitReader.accumulator >>> i3);
            bitReader.bitOffset = i3 + 8;
            i2--;
            i++;
        }
        if (i2 == 0) {
            return;
        }
        int min = Math.min(intAvailable(bitReader), i2 >> 2);
        if (min > 0) {
            int i4 = min << 2;
            System.arraycopy(bitReader.byteBuffer, bitReader.intOffset << 2, bArr, i, i4);
            i += i4;
            i2 -= i4;
            bitReader.intOffset += min;
        }
        if (i2 == 0) {
            return;
        }
        if (intAvailable(bitReader) <= 0) {
            while (i2 > 0) {
                try {
                    int read = bitReader.input.read(bArr, i, i2);
                    if (read == -1) {
                        throw new BrotliRuntimeException("Unexpected end of input");
                    }
                    i += read;
                    i2 -= read;
                } catch (IOException e) {
                    throw new BrotliRuntimeException("Failed to read input", e);
                }
            }
            return;
        }
        fillBitWindow(bitReader);
        while (i2 != 0) {
            long j = bitReader.accumulator;
            int i5 = bitReader.bitOffset;
            bArr[i] = (byte) (j >>> i5);
            bitReader.bitOffset = i5 + 8;
            i2--;
            i++;
        }
        checkHealth(bitReader, false);
    }
}

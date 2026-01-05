package org.apache.commons.fileupload.util.mime;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes3.dex */
final class QuotedPrintableDecoder {
    private static final int UPPER_NIBBLE_SHIFT = 4;

    private QuotedPrintableDecoder() {
    }

    public static int decode(byte[] bArr, OutputStream outputStream) throws IOException {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            int i4 = bArr[i];
            if (i4 == 95) {
                outputStream.write(32);
            } else if (i4 == 61) {
                int i5 = i + 2;
                if (i5 >= length) {
                    throw new IOException("Invalid quoted printable encoding; truncated escape sequence");
                }
                byte b = bArr[i3];
                i += 3;
                byte b2 = bArr[i5];
                if (b != 13) {
                    outputStream.write((hexToBinary(b) << 4) | hexToBinary(b2));
                    i2++;
                } else if (b2 != 10) {
                    throw new IOException("Invalid quoted printable encoding; CR must be followed by LF");
                }
            } else {
                outputStream.write(i4);
                i2++;
            }
            i = i3;
        }
        return i2;
    }

    private static int hexToBinary(byte b) throws IOException {
        int digit = Character.digit((char) b, 16);
        if (digit != -1) {
            return digit;
        }
        throw new IOException("Invalid quoted printable encoding: not a valid hex digit: " + ((int) b));
    }
}

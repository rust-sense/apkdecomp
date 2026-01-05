package com.github.penfeizhou.animation.apng.io;

import android.text.TextUtils;
import com.github.penfeizhou.animation.io.FilterReader;
import com.github.penfeizhou.animation.io.Reader;
import java.io.IOException;

/* loaded from: classes.dex */
public class APNGReader extends FilterReader {
    private static ThreadLocal<byte[]> __intBytes = new ThreadLocal<>();

    protected static byte[] ensureBytes() {
        byte[] bArr = __intBytes.get();
        if (bArr != null) {
            return bArr;
        }
        byte[] bArr2 = new byte[4];
        __intBytes.set(bArr2);
        return bArr2;
    }

    public APNGReader(Reader reader) {
        super(reader);
    }

    public int readInt() throws IOException {
        byte[] ensureBytes = ensureBytes();
        read(ensureBytes, 0, 4);
        return ((ensureBytes[0] & 255) << 24) | (ensureBytes[3] & 255) | ((ensureBytes[2] & 255) << 8) | ((ensureBytes[1] & 255) << 16);
    }

    public short readShort() throws IOException {
        byte[] ensureBytes = ensureBytes();
        read(ensureBytes, 0, 2);
        return (short) (((ensureBytes[0] & 255) << 8) | (ensureBytes[1] & 255));
    }

    public boolean matchFourCC(String str) throws IOException {
        if (TextUtils.isEmpty(str) || str.length() != 4) {
            return false;
        }
        int readFourCC = readFourCC();
        for (int i = 0; i < 4; i++) {
            if (((readFourCC >> (i * 8)) & 255) != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public int readFourCC() throws IOException {
        byte[] ensureBytes = ensureBytes();
        read(ensureBytes, 0, 4);
        return ((ensureBytes[3] & 255) << 24) | (ensureBytes[0] & 255) | ((ensureBytes[1] & 255) << 8) | ((ensureBytes[2] & 255) << 16);
    }
}

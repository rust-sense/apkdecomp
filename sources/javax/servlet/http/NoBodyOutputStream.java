package javax.servlet.http;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/* compiled from: HttpServlet.java */
/* loaded from: classes2.dex */
class NoBodyOutputStream extends ServletOutputStream {
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);
    private int contentLength = 0;

    int getContentLength() {
        return this.contentLength;
    }

    @Override // javax.servlet.ServletOutputStream
    public boolean isReady() {
        return false;
    }

    @Override // javax.servlet.ServletOutputStream
    public void setWriteListener(WriteListener writeListener) {
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.contentLength++;
    }

    NoBodyOutputStream() {
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException(lStrings.getString("err.io.nullArray"));
        }
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException(MessageFormat.format(lStrings.getString("err.io.indexOutOfBounds"), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)));
        }
        this.contentLength += i2;
    }
}

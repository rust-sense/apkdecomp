package javax.servlet.http;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
import javax.servlet.ServletOutputStream;

/* compiled from: HttpServlet.java */
/* loaded from: classes2.dex */
class NoBodyResponse extends HttpServletResponseWrapper {
    private static final ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");
    private boolean didSetContentLength;
    private NoBodyOutputStream noBody;
    private boolean usingOutputStream;
    private PrintWriter writer;

    NoBodyResponse(HttpServletResponse httpServletResponse) {
        super(httpServletResponse);
        this.noBody = new NoBodyOutputStream();
    }

    void setContentLength() {
        if (this.didSetContentLength) {
            return;
        }
        PrintWriter printWriter = this.writer;
        if (printWriter != null) {
            printWriter.flush();
        }
        setContentLength(this.noBody.getContentLength());
    }

    @Override // javax.servlet.ServletResponseWrapper, javax.servlet.ServletResponse
    public void setContentLength(int i) {
        super.setContentLength(i);
        this.didSetContentLength = true;
    }

    @Override // javax.servlet.ServletResponseWrapper, javax.servlet.ServletResponse
    public void setContentLengthLong(long j) {
        super.setContentLengthLong(j);
        this.didSetContentLength = true;
    }

    @Override // javax.servlet.http.HttpServletResponseWrapper, javax.servlet.http.HttpServletResponse
    public void setHeader(String str, String str2) {
        super.setHeader(str, str2);
        checkHeader(str);
    }

    @Override // javax.servlet.http.HttpServletResponseWrapper, javax.servlet.http.HttpServletResponse
    public void addHeader(String str, String str2) {
        super.addHeader(str, str2);
        checkHeader(str);
    }

    @Override // javax.servlet.http.HttpServletResponseWrapper, javax.servlet.http.HttpServletResponse
    public void setIntHeader(String str, int i) {
        super.setIntHeader(str, i);
        checkHeader(str);
    }

    @Override // javax.servlet.http.HttpServletResponseWrapper, javax.servlet.http.HttpServletResponse
    public void addIntHeader(String str, int i) {
        super.addIntHeader(str, i);
        checkHeader(str);
    }

    private void checkHeader(String str) {
        if ("content-length".equalsIgnoreCase(str)) {
            this.didSetContentLength = true;
        }
    }

    @Override // javax.servlet.ServletResponseWrapper, javax.servlet.ServletResponse
    public ServletOutputStream getOutputStream() throws IOException {
        if (this.writer != null) {
            throw new IllegalStateException(lStrings.getString("err.ise.getOutputStream"));
        }
        this.usingOutputStream = true;
        return this.noBody;
    }

    @Override // javax.servlet.ServletResponseWrapper, javax.servlet.ServletResponse
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        if (this.usingOutputStream) {
            throw new IllegalStateException(lStrings.getString("err.ise.getWriter"));
        }
        if (this.writer == null) {
            this.writer = new PrintWriter(new OutputStreamWriter(this.noBody, getCharacterEncoding()));
        }
        return this.writer;
    }
}

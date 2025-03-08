package javax.portlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/* loaded from: classes2.dex */
public interface HeaderResponse extends MimeResponse {
    void addDependency(String str, String str2, String str3);

    void addDependency(String str, String str2, String str3, String str4);

    @Override // javax.portlet.MimeResponse
    OutputStream getPortletOutputStream() throws IOException;

    @Override // javax.portlet.MimeResponse
    PrintWriter getWriter() throws IOException;

    @Override // javax.portlet.MimeResponse
    void setContentType(String str);

    void setTitle(String str);
}

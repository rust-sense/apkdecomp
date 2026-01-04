package javax.portlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import javax.servlet.http.Part;

/* loaded from: classes2.dex */
public interface ClientDataRequest extends PortletRequest {
    String getCharacterEncoding();

    int getContentLength();

    long getContentLengthLong();

    String getContentType();

    String getMethod();

    Part getPart(String str) throws IOException, PortletException;

    Collection<Part> getParts() throws IOException, PortletException;

    InputStream getPortletInputStream() throws IOException;

    BufferedReader getReader() throws UnsupportedEncodingException, IOException;

    void setCharacterEncoding(String str) throws UnsupportedEncodingException;
}

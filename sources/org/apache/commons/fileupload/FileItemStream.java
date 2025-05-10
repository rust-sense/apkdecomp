package org.apache.commons.fileupload;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public interface FileItemStream extends FileItemHeadersSupport {

    public static class ItemSkippedException extends IOException {
        private static final long serialVersionUID = -7280778431581963740L;
    }

    String getContentType();

    String getFieldName();

    String getName();

    boolean isFormField();

    InputStream openStream() throws IOException;
}

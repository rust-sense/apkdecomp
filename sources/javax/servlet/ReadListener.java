package javax.servlet;

import java.io.IOException;
import java.util.EventListener;

/* loaded from: classes2.dex */
public interface ReadListener extends EventListener {
    void onAllDataRead() throws IOException;

    void onDataAvailable() throws IOException;

    void onError(Throwable th);
}

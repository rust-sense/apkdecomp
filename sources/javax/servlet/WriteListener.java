package javax.servlet;

import java.io.IOException;
import java.util.EventListener;

/* loaded from: classes2.dex */
public interface WriteListener extends EventListener {
    void onError(Throwable th);

    void onWritePossible() throws IOException;
}

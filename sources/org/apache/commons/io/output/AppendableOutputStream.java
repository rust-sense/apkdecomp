package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.Appendable;

/* loaded from: classes3.dex */
public class AppendableOutputStream<T extends Appendable> extends OutputStream {
    private final T appendable;

    public T getAppendable() {
        return this.appendable;
    }

    public AppendableOutputStream(T t) {
        this.appendable = t;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.appendable.append((char) i);
    }
}

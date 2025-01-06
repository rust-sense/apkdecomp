package com.github.penfeizhou.animation.loader;

import com.github.penfeizhou.animation.io.Reader;
import java.io.IOException;

/* loaded from: classes.dex */
public interface Loader {
    Reader obtain() throws IOException;
}

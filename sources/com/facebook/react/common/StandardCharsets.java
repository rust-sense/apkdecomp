package com.facebook.react.common;

import java.nio.charset.Charset;

@Deprecated(forRemoval = true, since = "Deprecated class since v0.73.0, please use java.nio.charset.StandardCharsets instead.")
/* loaded from: classes.dex */
public final class StandardCharsets {
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Charset UTF_16 = Charset.forName("UTF-16");
    public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
    public static final Charset UTF_16LE = Charset.forName("UTF-16LE");

    private StandardCharsets() {
    }
}

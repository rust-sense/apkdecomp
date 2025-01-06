package com.bumptech.glide.load;

import java.io.IOException;

/* loaded from: classes.dex */
public final class HttpException extends IOException {
    public static final int UNKNOWN = -1;
    private static final long serialVersionUID = 1;
    private final int statusCode;

    public int getStatusCode() {
        return this.statusCode;
    }

    public HttpException(int i) {
        this("Http request failed", i);
    }

    @Deprecated
    public HttpException(String str) {
        this(str, -1);
    }

    public HttpException(String str, int i) {
        this(str, i, null);
    }

    public HttpException(String str, int i, Throwable th) {
        super(str + ", status code: " + i, th);
        this.statusCode = i;
    }
}

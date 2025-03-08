package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* loaded from: classes2.dex */
public class Response<T extends Result> {
    private T zzap;

    public Response() {
    }

    protected T getResult() {
        return this.zzap;
    }

    public void setResult(T t) {
        this.zzap = t;
    }

    protected Response(T t) {
        this.zzap = t;
    }
}

package com.google.android.gms.common;

import android.content.Intent;

/* loaded from: classes2.dex */
public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zzag;

    public GooglePlayServicesRepairableException(int i, String str, Intent intent) {
        super(str, intent);
        this.zzag = i;
    }

    public int getConnectionStatusCode() {
        return this.zzag;
    }
}

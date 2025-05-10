package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@16.0.0 */
/* loaded from: classes2.dex */
abstract class zzq<T> {
    final int zza;
    final TaskCompletionSource<T> zzb = new TaskCompletionSource<>();
    final int zzc;
    final Bundle zzd;

    zzq(int i, int i2, Bundle bundle) {
        this.zza = i;
        this.zzc = i2;
        this.zzd = bundle;
    }

    abstract void zza(Bundle bundle);

    abstract boolean zza();

    final void zza(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(t);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16 + String.valueOf(valueOf2).length());
            sb.append("Finishing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzb.setResult(t);
    }

    final void zza(zzp zzpVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzpVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14 + String.valueOf(valueOf2).length());
            sb.append("Failing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzb.setException(zzpVar);
    }

    public String toString() {
        int i = this.zzc;
        int i2 = this.zza;
        boolean zza = zza();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=");
        sb.append(zza);
        sb.append("}");
        return sb.toString();
    }
}

package com.google.android.gms.tasks;

/* loaded from: classes2.dex */
final class zzb implements OnSuccessListener<Void> {
    private final /* synthetic */ OnTokenCanceledListener zzb;

    zzb(zza zzaVar, OnTokenCanceledListener onTokenCanceledListener) {
        this.zzb = onTokenCanceledListener;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Void r1) {
        this.zzb.onCanceled();
    }
}

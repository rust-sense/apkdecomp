package com.google.android.gms.internal.firebase_messaging;

import java.io.PrintStream;

/* compiled from: com.google.firebase:firebase-messaging@@22.0.0 */
/* loaded from: classes2.dex */
public final class zzt {
    static final zzn zza;

    static {
        zzn zzrVar;
        Integer num = null;
        try {
            try {
                num = (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Exception e) {
                System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
                e.printStackTrace(System.err);
            }
            zzrVar = (num == null || num.intValue() < 19) ? !Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? new zzq() : new zzr() : new zzs();
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = zzr.class.getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 133);
            sb.append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
            sb.append(name);
            sb.append("will be used. The error is: ");
            printStream.println(sb.toString());
            th.printStackTrace(System.err);
            zzrVar = new zzr();
        }
        zza = zzrVar;
        if (num == null) {
            return;
        }
        num.intValue();
    }

    public static void zza(Throwable th, Throwable th2) {
        zza.zza(th, th2);
    }
}

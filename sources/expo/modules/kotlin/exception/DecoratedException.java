package expo.modules.kotlin.exception;

import kotlin.Metadata;

/* compiled from: CodedException.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/kotlin/exception/DecoratedException;", "Lexpo/modules/kotlin/exception/CodedException;", "message", "", "cause", "(Ljava/lang/String;Lexpo/modules/kotlin/exception/CodedException;)V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DecoratedException extends CodedException {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DecoratedException(java.lang.String r5, expo.modules.kotlin.exception.CodedException r6) {
        /*
            r4 = this;
            java.lang.String r0 = "message"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "cause"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = r6.getCode()
            java.lang.String r1 = java.lang.System.lineSeparator()
            java.lang.String r2 = r6.getLocalizedMessage()
            if (r2 != 0) goto L1c
            r2 = r6
            java.io.Serializable r2 = (java.io.Serializable) r2
            goto L1e
        L1c:
            java.io.Serializable r2 = (java.io.Serializable) r2
        L1e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r5)
            r3.append(r1)
            java.lang.String r5 = "→ Caused by: "
            r3.append(r5)
            r3.append(r2)
            java.lang.String r5 = r3.toString()
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            r4.<init>(r0, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.exception.DecoratedException.<init>(java.lang.String, expo.modules.kotlin.exception.CodedException):void");
    }
}

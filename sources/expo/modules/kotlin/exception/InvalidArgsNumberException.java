package expo.modules.kotlin.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: CodedException.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/kotlin/exception/InvalidArgsNumberException;", "Lexpo/modules/kotlin/exception/CodedException;", "received", "", "expected", "required", "(III)V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InvalidArgsNumberException extends CodedException {
    public /* synthetic */ InvalidArgsNumberException(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? i2 : i3);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public InvalidArgsNumberException(int r4, int r5, int r6) {
        /*
            r3 = this;
            java.lang.String r0 = " arguments, but "
            java.lang.String r1 = "Received "
            if (r6 >= r5) goto L26
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            r2.append(r4)
            r2.append(r0)
            r2.append(r5)
            java.lang.String r4 = " was expected and at least "
            r2.append(r4)
            r2.append(r6)
            java.lang.String r4 = " is required"
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            goto L3d
        L26:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r1)
            r6.append(r4)
            r6.append(r0)
            r6.append(r5)
            java.lang.String r4 = " was expected"
            r6.append(r4)
            java.lang.String r4 = r6.toString()
        L3d:
            r5 = 2
            r6 = 0
            r3.<init>(r4, r6, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.exception.InvalidArgsNumberException.<init>(int, int, int):void");
    }
}

package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: utfEncoding.kt */
/* loaded from: classes3.dex */
public final class UtfEncodingKt {
    public static final byte[] stringsToBytes(String[] strings) {
        Intrinsics.checkNotNullParameter(strings, "strings");
        int i = 0;
        for (String str : strings) {
            i += str.length();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (String str2 : strings) {
            int length = str2.length();
            int i3 = 0;
            while (i3 < length) {
                bArr[i2] = (byte) str2.charAt(i3);
                i3++;
                i2++;
            }
        }
        return bArr;
    }
}

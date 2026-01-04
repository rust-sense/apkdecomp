package expo.modules.network;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkUtils.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¨\u0006\u0003"}, d2 = {"frontPadWithZeros", "", "inputArray", "expo-network_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkUtilsKt {
    public static final byte[] frontPadWithZeros(byte[] inputArray) {
        Intrinsics.checkNotNullParameter(inputArray, "inputArray");
        byte[] bArr = {0, 0, 0, 0};
        System.arraycopy(inputArray, 0, bArr, 4 - inputArray.length, inputArray.length);
        return bArr;
    }
}

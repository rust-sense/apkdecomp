package expo.modules.network;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkExceptions.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/network/NetworkAccessException;", "Lexpo/modules/kotlin/exception/CodedException;", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "expo-network_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkAccessException extends CodedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkAccessException(Exception e) {
        super("Unable to access network information", e.getCause());
        Intrinsics.checkNotNullParameter(e, "e");
    }
}

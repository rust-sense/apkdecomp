package expo.modules.kotlin.exception;

import kotlin.Metadata;

/* compiled from: CodedException.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/kotlin/exception/NullArgumentException;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NullArgumentException extends CodedException {
    public NullArgumentException() {
        super("Cannot assigned null to not nullable type.", null, 2, null);
    }
}

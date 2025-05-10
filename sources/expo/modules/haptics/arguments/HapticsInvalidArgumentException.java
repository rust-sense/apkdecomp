package expo.modules.haptics.arguments;

import expo.modules.core.errors.CodedException;
import kotlin.Metadata;

/* compiled from: HapticsInvalidArgumentException.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016¨\u0006\u0006"}, d2 = {"Lexpo/modules/haptics/arguments/HapticsInvalidArgumentException;", "Lexpo/modules/core/errors/CodedException;", "message", "", "(Ljava/lang/String;)V", "getCode", "expo-haptics_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HapticsInvalidArgumentException extends CodedException {
    @Override // expo.modules.core.errors.CodedException, expo.modules.core.interfaces.CodedThrowable
    public String getCode() {
        return "E_HAPTICS_INVALID_ARGUMENT";
    }

    public HapticsInvalidArgumentException(String str) {
        super(str);
    }
}

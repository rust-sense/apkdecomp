package expo.modules.keepawake;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KeepAwakeExceptions.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/keepawake/MissingModuleException;", "Lexpo/modules/kotlin/exception/CodedException;", "moduleName", "", "(Ljava/lang/String;)V", "expo-keep-awake_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MissingModuleException extends CodedException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingModuleException(String moduleName) {
        super("Module '" + moduleName + "' not found. Are you sure all modules are linked correctly?", null, 2, null);
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
    }
}

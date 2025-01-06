package expo.modules.core.logging;

import kotlin.Metadata;

/* compiled from: LogHandler.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH ¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/core/logging/LogHandler;", "", "()V", "log", "", "type", "Lexpo/modules/core/logging/LogType;", "message", "", "cause", "", "log$expo_modules_core_release", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class LogHandler {
    public abstract void log$expo_modules_core_release(LogType type, String message, Throwable cause);

    public static /* synthetic */ void log$expo_modules_core_release$default(LogHandler logHandler, LogType logType, String str, Throwable th, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }
        if ((i & 4) != 0) {
            th = null;
        }
        logHandler.log$expo_modules_core_release(logType, str, th);
    }
}

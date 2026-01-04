package expo.modules.core.logging;

import com.google.firebase.messaging.Constants;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Logger.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001a\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u001a\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ$\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J)\u0010\u0014\u001a\u00020\u00152!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u000b0\u0017J\u000e\u0010\u001c\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001a\u0010\u001d\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lexpo/modules/core/logging/Logger;", "", "logHandlers", "", "Lexpo/modules/core/logging/LogHandler;", "(Ljava/util/List;)V", "minOSLevel", "", "debug", "", "message", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "cause", "", "fatal", "info", "log", "type", "Lexpo/modules/core/logging/LogType;", "startTimer", "Lexpo/modules/core/logging/LoggerTimer;", "logFormatter", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "duration", "trace", "warn", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Logger {
    private final List<LogHandler> logHandlers;
    private final int minOSLevel;

    /* JADX WARN: Multi-variable type inference failed */
    public Logger(List<? extends LogHandler> logHandlers) {
        Intrinsics.checkNotNullParameter(logHandlers, "logHandlers");
        this.logHandlers = logHandlers;
        this.minOSLevel = 4;
    }

    public final void trace(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        log$default(this, LogType.Trace, message, null, 4, null);
    }

    public final void debug(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        log$default(this, LogType.Debug, message, null, 4, null);
    }

    public final void info(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        log$default(this, LogType.Info, message, null, 4, null);
    }

    public static /* synthetic */ void warn$default(Logger logger, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        logger.warn(str, th);
    }

    public final void warn(String message, Throwable cause) {
        Intrinsics.checkNotNullParameter(message, "message");
        log(LogType.Warn, message, cause);
    }

    public static /* synthetic */ void error$default(Logger logger, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        logger.error(str, th);
    }

    public final void error(String message, Throwable cause) {
        Intrinsics.checkNotNullParameter(message, "message");
        log(LogType.Error, message, cause);
    }

    public static /* synthetic */ void fatal$default(Logger logger, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        logger.fatal(str, th);
    }

    public final void fatal(String message, Throwable cause) {
        Intrinsics.checkNotNullParameter(message, "message");
        log(LogType.Fatal, message, cause);
    }

    public final LoggerTimer startTimer(final Function1<? super Long, String> logFormatter) {
        Intrinsics.checkNotNullParameter(logFormatter, "logFormatter");
        final long currentTimeMillis = System.currentTimeMillis();
        return new LoggerTimer() { // from class: expo.modules.core.logging.Logger$startTimer$1
            @Override // expo.modules.core.logging.LoggerTimer
            public void stop() {
                Logger.log$default(Logger.this, LogType.Timer, logFormatter.invoke(Long.valueOf(System.currentTimeMillis() - currentTimeMillis)), null, 4, null);
            }
        };
    }

    static /* synthetic */ void log$default(Logger logger, LogType logType, String str, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        logger.log(logType, str, th);
    }

    private final void log(LogType type, String message, Throwable cause) {
        if (LogType.INSTANCE.toOSLogType(type) >= this.minOSLevel) {
            Iterator<T> it = this.logHandlers.iterator();
            while (it.hasNext()) {
                ((LogHandler) it.next()).log$expo_modules_core_release(type, message, cause);
            }
        }
    }
}

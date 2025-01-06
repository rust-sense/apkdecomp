package expo.modules.updates.logging;

import android.content.Context;
import com.google.firebase.messaging.Constants;
import expo.modules.core.logging.LogHandler;
import expo.modules.core.logging.LogHandlers;
import expo.modules.core.logging.LogType;
import expo.modules.core.logging.Logger;
import expo.modules.core.logging.LoggerTimer;
import io.sentry.SentryEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesLogger.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nJ*\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0010\b\u0002\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012J>\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012J*\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0010\b\u0002\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012J>\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012J\u0018\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ,\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nJU\u0010\u0015\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012H\u0002¢\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\nJ\u0018\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ,\u0010\u001e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nJ\u0018\u0010\u001f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fJ,\u0010\u001f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lexpo/modules/updates/logging/UpdatesLogger;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", SentryEvent.JsonKeys.LOGGER, "Lexpo/modules/core/logging/Logger;", "debug", "", "message", "", "code", "Lexpo/modules/updates/logging/UpdatesErrorCode;", "updateId", "assetId", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "fatal", "info", "logEntryString", "level", "Lexpo/modules/core/logging/LogType;", "duration", "", "(Ljava/lang/String;Lexpo/modules/updates/logging/UpdatesErrorCode;Lexpo/modules/core/logging/LogType;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)Ljava/lang/String;", "startTimer", "Lexpo/modules/core/logging/LoggerTimer;", "label", "trace", "warn", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesLogger {
    public static final String EXPO_UPDATES_LOGGING_TAG = "dev.expo.updates";
    public static final int MAX_FRAMES_IN_STACKTRACE = 20;
    private final Logger logger;

    /* compiled from: UpdatesLogger.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LogType.values().length];
            try {
                iArr[LogType.Error.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LogType.Fatal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public UpdatesLogger(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.logger = new Logger(CollectionsKt.listOf((Object[]) new LogHandler[]{LogHandlers.INSTANCE.createOSLogHandler(EXPO_UPDATES_LOGGING_TAG), LogHandlers.INSTANCE.createPersistentFileLogHandler(context, EXPO_UPDATES_LOGGING_TAG)}));
    }

    public static /* synthetic */ void trace$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.trace(str, updatesErrorCode);
    }

    public final void trace(String message, UpdatesErrorCode code) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        trace(message, code, null, null);
    }

    public static /* synthetic */ void trace$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.trace(str, updatesErrorCode, str2, str3);
    }

    public final void trace(String message, UpdatesErrorCode code, String updateId, String assetId) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        this.logger.trace(logEntryString$default(this, message, code, LogType.Trace, null, updateId, assetId, null, 64, null));
    }

    public static /* synthetic */ void debug$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.debug(str, updatesErrorCode);
    }

    public final void debug(String message, UpdatesErrorCode code) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        debug(message, code, null, null);
    }

    public static /* synthetic */ void debug$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.debug(str, updatesErrorCode, str2, str3);
    }

    public final void debug(String message, UpdatesErrorCode code, String updateId, String assetId) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        this.logger.debug(logEntryString$default(this, message, code, LogType.Debug, null, updateId, assetId, null, 64, null));
    }

    public static /* synthetic */ void info$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.info(str, updatesErrorCode);
    }

    public final void info(String message, UpdatesErrorCode code) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        info(message, code, null, null);
    }

    public static /* synthetic */ void info$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.info(str, updatesErrorCode, str2, str3);
    }

    public final void info(String message, UpdatesErrorCode code, String updateId, String assetId) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        this.logger.info(logEntryString$default(this, message, code, LogType.Info, null, updateId, assetId, null, 64, null));
    }

    public static /* synthetic */ void warn$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.warn(str, updatesErrorCode);
    }

    public final void warn(String message, UpdatesErrorCode code) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        warn(message, code, null, null);
    }

    public static /* synthetic */ void warn$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        updatesLogger.warn(str, updatesErrorCode, str2, str3);
    }

    public final void warn(String message, UpdatesErrorCode code, String updateId, String assetId) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Logger.warn$default(this.logger, logEntryString$default(this, message, code, LogType.Warn, null, updateId, assetId, null, 64, null), null, 2, null);
    }

    public static /* synthetic */ void error$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, Exception exc, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        if ((i & 4) != 0) {
            exc = null;
        }
        updatesLogger.error(str, updatesErrorCode, exc);
    }

    public final void error(String message, UpdatesErrorCode code, Exception exception) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        error(message, code, null, null, exception);
    }

    public static /* synthetic */ void error$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, Exception exc, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        UpdatesErrorCode updatesErrorCode2 = updatesErrorCode;
        if ((i & 16) != 0) {
            exc = null;
        }
        updatesLogger.error(str, updatesErrorCode2, str2, str3, exc);
    }

    public final void error(String message, UpdatesErrorCode code, String updateId, String assetId, Exception exception) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Logger.error$default(this.logger, logEntryString(message, code, LogType.Error, null, updateId, assetId, exception), null, 2, null);
    }

    public static /* synthetic */ void fatal$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, Exception exc, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        if ((i & 4) != 0) {
            exc = null;
        }
        updatesLogger.fatal(str, updatesErrorCode, exc);
    }

    public final void fatal(String message, UpdatesErrorCode code, Exception exception) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        fatal(message, code, null, null, exception);
    }

    public static /* synthetic */ void fatal$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, String str2, String str3, Exception exc, int i, Object obj) {
        if ((i & 2) != 0) {
            updatesErrorCode = UpdatesErrorCode.None;
        }
        UpdatesErrorCode updatesErrorCode2 = updatesErrorCode;
        if ((i & 16) != 0) {
            exc = null;
        }
        updatesLogger.fatal(str, updatesErrorCode2, str2, str3, exc);
    }

    public final void fatal(String message, UpdatesErrorCode code, String updateId, String assetId, Exception exception) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Logger.fatal$default(this.logger, logEntryString(message, code, LogType.Fatal, null, updateId, assetId, exception), null, 2, null);
    }

    public final LoggerTimer startTimer(final String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        return this.logger.startTimer(new Function1<Long, String>() { // from class: expo.modules.updates.logging.UpdatesLogger$startTimer$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(Long l) {
                return invoke(l.longValue());
            }

            public final String invoke(long j) {
                String logEntryString;
                logEntryString = UpdatesLogger.this.logEntryString(label, UpdatesErrorCode.None, LogType.Timer, Long.valueOf(j), null, null, null);
                return logEntryString;
            }
        });
    }

    static /* synthetic */ String logEntryString$default(UpdatesLogger updatesLogger, String str, UpdatesErrorCode updatesErrorCode, LogType logType, Long l, String str2, String str3, Exception exc, int i, Object obj) {
        return updatesLogger.logEntryString(str, updatesErrorCode, logType, l, str2, str3, (i & 64) != 0 ? null : exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String logEntryString(String message, UpdatesErrorCode code, LogType level, Long duration, String updateId, String assetId, Exception exception) {
        ArrayList arrayList;
        ArrayList arrayList2;
        long time = new Date().getTime();
        Exception exc = exception instanceof Throwable ? exception : null;
        if (exc == null) {
            exc = new Throwable();
        }
        int i = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
        if (i == 1) {
            StackTraceElement[] stackTrace = exc.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
            List take = ArraysKt.take(stackTrace, 20);
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(take, 10));
            Iterator it = take.iterator();
            while (it.hasNext()) {
                arrayList3.add(((StackTraceElement) it.next()).toString());
            }
            arrayList = arrayList3;
        } else if (i == 2) {
            StackTraceElement[] stackTrace2 = exc.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace2, "getStackTrace(...)");
            List take2 = ArraysKt.take(stackTrace2, 20);
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(take2, 10));
            Iterator it2 = take2.iterator();
            while (it2.hasNext()) {
                arrayList4.add(((StackTraceElement) it2.next()).toString());
            }
            arrayList = arrayList4;
        } else {
            arrayList2 = null;
            return new UpdatesLogEntry(time, message, code.getCode(), level.getType(), duration, updateId, assetId, arrayList2).asString();
        }
        arrayList2 = arrayList;
        return new UpdatesLogEntry(time, message, code.getCode(), level.getType(), duration, updateId, assetId, arrayList2).asString();
    }
}

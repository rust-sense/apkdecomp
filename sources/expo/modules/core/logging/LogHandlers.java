package expo.modules.core.logging;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogHandlers.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lexpo/modules/core/logging/LogHandlers;", "", "()V", "createOSLogHandler", "Lexpo/modules/core/logging/LogHandler;", "category", "", "createPersistentFileLogHandler", "context", "Landroid/content/Context;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LogHandlers {
    public static final LogHandlers INSTANCE = new LogHandlers();

    private LogHandlers() {
    }

    public final LogHandler createOSLogHandler(String category) {
        Intrinsics.checkNotNullParameter(category, "category");
        return new OSLogHandler(category);
    }

    public final LogHandler createPersistentFileLogHandler(Context context, String category) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(category, "category");
        return new PersistentFileLogHandler(category, context);
    }
}

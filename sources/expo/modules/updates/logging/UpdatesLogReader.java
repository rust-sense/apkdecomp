package expo.modules.updates.logging;

import android.content.Context;
import expo.modules.core.logging.PersistentFileLog;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesLogReader.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\bH\u0002J5\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\n2#\u0010\u0016\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00140\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lexpo/modules/updates/logging/UpdatesLogReader;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "persistentLog", "Lexpo/modules/core/logging/PersistentFileLog;", "epochFromDateOrOneDayAgo", "", "date", "Ljava/util/Date;", "getLogEntries", "", "", "newerThan", "isEntryStringLaterThanTimestamp", "", "entryString", "timestamp", "purgeLogEntries", "", "olderThan", "completionHandler", "Lkotlin/Function1;", "Ljava/lang/Error;", "Lkotlin/ParameterName;", "name", "_", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesLogReader {
    private static final int ONE_DAY_MILLISECONDS = 86400;
    private final PersistentFileLog persistentLog;

    public UpdatesLogReader(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.persistentLog = new PersistentFileLog(UpdatesLogger.EXPO_UPDATES_LOGGING_TAG, context);
    }

    public static /* synthetic */ void purgeLogEntries$default(UpdatesLogReader updatesLogReader, Date date, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            date = new Date(new Date().getTime() - ONE_DAY_MILLISECONDS);
        }
        updatesLogReader.purgeLogEntries(date, function1);
    }

    public final void purgeLogEntries(Date olderThan, Function1<? super Error, Unit> completionHandler) {
        Intrinsics.checkNotNullParameter(olderThan, "olderThan");
        Intrinsics.checkNotNullParameter(completionHandler, "completionHandler");
        final long epochFromDateOrOneDayAgo = epochFromDateOrOneDayAgo(olderThan);
        this.persistentLog.purgeEntriesNotMatchingFilter(new Function1<String, Boolean>() { // from class: expo.modules.updates.logging.UpdatesLogReader$purgeLogEntries$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String entryString) {
                boolean isEntryStringLaterThanTimestamp;
                Intrinsics.checkNotNullParameter(entryString, "entryString");
                isEntryStringLaterThanTimestamp = UpdatesLogReader.this.isEntryStringLaterThanTimestamp(entryString, epochFromDateOrOneDayAgo);
                return Boolean.valueOf(isEntryStringLaterThanTimestamp);
            }
        }, completionHandler);
    }

    public final List<String> getLogEntries(Date newerThan) {
        Intrinsics.checkNotNullParameter(newerThan, "newerThan");
        long epochFromDateOrOneDayAgo = epochFromDateOrOneDayAgo(newerThan);
        List<String> readEntries = this.persistentLog.readEntries();
        ArrayList arrayList = new ArrayList();
        for (Object obj : readEntries) {
            if (isEntryStringLaterThanTimestamp((String) obj, epochFromDateOrOneDayAgo)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isEntryStringLaterThanTimestamp(String entryString, long timestamp) {
        UpdatesLogEntry create = UpdatesLogEntry.INSTANCE.create(entryString);
        return create != null && create.getTimestamp() >= timestamp;
    }

    private final long epochFromDateOrOneDayAgo(Date date) {
        return Math.max(date.getTime(), new Date().getTime() - ONE_DAY_MILLISECONDS);
    }
}

package expo.modules.updates.db;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DatabaseHolder.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0005\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lexpo/modules/updates/db/DatabaseHolder;", "", "mDatabase", "Lexpo/modules/updates/db/UpdatesDatabase;", "(Lexpo/modules/updates/db/UpdatesDatabase;)V", "database", "getDatabase", "()Lexpo/modules/updates/db/UpdatesDatabase;", "isInUse", "", "releaseDatabase", "", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DatabaseHolder {
    private static final String TAG = "DatabaseHolder";
    private boolean isInUse;
    private final UpdatesDatabase mDatabase;

    public DatabaseHolder(UpdatesDatabase mDatabase) {
        Intrinsics.checkNotNullParameter(mDatabase, "mDatabase");
        this.mDatabase = mDatabase;
    }

    public final synchronized UpdatesDatabase getDatabase() {
        while (this.isInUse) {
            try {
                Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                wait();
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted while waiting for database", e);
            }
        }
        this.isInUse = true;
        return this.mDatabase;
    }

    public final synchronized void releaseDatabase() {
        this.isInUse = false;
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
        notify();
    }
}

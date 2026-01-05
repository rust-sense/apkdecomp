package expo.modules.updates.statemachine;

import java.util.Date;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesStateContextRollback.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateContextRollback;", "", "commitTime", "Ljava/util/Date;", "(Ljava/util/Date;)V", "getCommitTime", "()Ljava/util/Date;", "commitTimeString", "", "getCommitTimeString", "()Ljava/lang/String;", "json", "", "getJson", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class UpdatesStateContextRollback {
    private final Date commitTime;

    public static /* synthetic */ UpdatesStateContextRollback copy$default(UpdatesStateContextRollback updatesStateContextRollback, Date date, int i, Object obj) {
        if ((i & 1) != 0) {
            date = updatesStateContextRollback.commitTime;
        }
        return updatesStateContextRollback.copy(date);
    }

    /* renamed from: component1, reason: from getter */
    public final Date getCommitTime() {
        return this.commitTime;
    }

    public final UpdatesStateContextRollback copy(Date commitTime) {
        Intrinsics.checkNotNullParameter(commitTime, "commitTime");
        return new UpdatesStateContextRollback(commitTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof UpdatesStateContextRollback) && Intrinsics.areEqual(this.commitTime, ((UpdatesStateContextRollback) other).commitTime);
    }

    public final Date getCommitTime() {
        return this.commitTime;
    }

    public int hashCode() {
        return this.commitTime.hashCode();
    }

    public String toString() {
        return "UpdatesStateContextRollback(commitTime=" + this.commitTime + ")";
    }

    public UpdatesStateContextRollback(Date commitTime) {
        Intrinsics.checkNotNullParameter(commitTime, "commitTime");
        this.commitTime = commitTime;
    }

    public final String getCommitTimeString() {
        String format = UpdatesStateContext.INSTANCE.getDATE_FORMATTER().format(this.commitTime);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final Map<String, Object> getJson() {
        return MapsKt.mapOf(TuplesKt.to("commitTime", getCommitTimeString()));
    }
}

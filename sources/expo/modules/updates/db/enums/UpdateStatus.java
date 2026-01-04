package expo.modules.updates.db.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: UpdateStatus.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/db/enums/UpdateStatus;", "", "(Ljava/lang/String;I)V", "READY", "PENDING", "EMBEDDED", "DEVELOPMENT", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdateStatus {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ UpdateStatus[] $VALUES;
    public static final UpdateStatus READY = new UpdateStatus("READY", 0);
    public static final UpdateStatus PENDING = new UpdateStatus("PENDING", 1);
    public static final UpdateStatus EMBEDDED = new UpdateStatus("EMBEDDED", 2);
    public static final UpdateStatus DEVELOPMENT = new UpdateStatus("DEVELOPMENT", 3);

    private static final /* synthetic */ UpdateStatus[] $values() {
        return new UpdateStatus[]{READY, PENDING, EMBEDDED, DEVELOPMENT};
    }

    public static EnumEntries<UpdateStatus> getEntries() {
        return $ENTRIES;
    }

    public static UpdateStatus valueOf(String str) {
        return (UpdateStatus) Enum.valueOf(UpdateStatus.class, str);
    }

    public static UpdateStatus[] values() {
        return (UpdateStatus[]) $VALUES.clone();
    }

    private UpdateStatus(String str, int i) {
    }

    static {
        UpdateStatus[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

package expo.modules.updates.statemachine;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: UpdatesStateEventType.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateEventType;", "", "type", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "Check", "CheckCompleteUnavailable", "CheckCompleteAvailable", "CheckError", "Download", "DownloadComplete", "DownloadError", "Restart", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesStateEventType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ UpdatesStateEventType[] $VALUES;
    private final String type;
    public static final UpdatesStateEventType Check = new UpdatesStateEventType("Check", 0, "check");
    public static final UpdatesStateEventType CheckCompleteUnavailable = new UpdatesStateEventType("CheckCompleteUnavailable", 1, "checkCompleteUnavailable");
    public static final UpdatesStateEventType CheckCompleteAvailable = new UpdatesStateEventType("CheckCompleteAvailable", 2, "checkCompleteAvailable");
    public static final UpdatesStateEventType CheckError = new UpdatesStateEventType("CheckError", 3, "checkError");
    public static final UpdatesStateEventType Download = new UpdatesStateEventType("Download", 4, "download");
    public static final UpdatesStateEventType DownloadComplete = new UpdatesStateEventType("DownloadComplete", 5, "downloadComplete");
    public static final UpdatesStateEventType DownloadError = new UpdatesStateEventType("DownloadError", 6, "downloadError");
    public static final UpdatesStateEventType Restart = new UpdatesStateEventType("Restart", 7, "restart");

    private static final /* synthetic */ UpdatesStateEventType[] $values() {
        return new UpdatesStateEventType[]{Check, CheckCompleteUnavailable, CheckCompleteAvailable, CheckError, Download, DownloadComplete, DownloadError, Restart};
    }

    public static EnumEntries<UpdatesStateEventType> getEntries() {
        return $ENTRIES;
    }

    public static UpdatesStateEventType valueOf(String str) {
        return (UpdatesStateEventType) Enum.valueOf(UpdatesStateEventType.class, str);
    }

    public static UpdatesStateEventType[] values() {
        return (UpdatesStateEventType[]) $VALUES.clone();
    }

    public final String getType() {
        return this.type;
    }

    private UpdatesStateEventType(String str, int i, String str2) {
        this.type = str2;
    }

    static {
        UpdatesStateEventType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

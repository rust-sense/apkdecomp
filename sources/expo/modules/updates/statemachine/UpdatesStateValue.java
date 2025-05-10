package expo.modules.updates.statemachine;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: UpdatesStateValue.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateValue;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "Idle", "Checking", "Downloading", "Restarting", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesStateValue {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ UpdatesStateValue[] $VALUES;
    private final String value;
    public static final UpdatesStateValue Idle = new UpdatesStateValue("Idle", 0, "idle");
    public static final UpdatesStateValue Checking = new UpdatesStateValue("Checking", 1, "checking");
    public static final UpdatesStateValue Downloading = new UpdatesStateValue("Downloading", 2, "downloading");
    public static final UpdatesStateValue Restarting = new UpdatesStateValue("Restarting", 3, "restarting");

    private static final /* synthetic */ UpdatesStateValue[] $values() {
        return new UpdatesStateValue[]{Idle, Checking, Downloading, Restarting};
    }

    public static EnumEntries<UpdatesStateValue> getEntries() {
        return $ENTRIES;
    }

    public static UpdatesStateValue valueOf(String str) {
        return (UpdatesStateValue) Enum.valueOf(UpdatesStateValue.class, str);
    }

    public static UpdatesStateValue[] values() {
        return (UpdatesStateValue[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private UpdatesStateValue(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        UpdatesStateValue[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

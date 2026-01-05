package expo.modules.updates.logging;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: UpdatesErrorCode.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lexpo/modules/updates/logging/UpdatesErrorCode;", "", "code", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getCode", "()Ljava/lang/String;", "None", "NoUpdatesAvailable", "UpdateAssetsNotAvailable", "UpdateServerUnreachable", "UpdateHasInvalidSignature", "UpdateCodeSigningError", "UpdateFailedToLoad", "AssetsFailedToLoad", "JSRuntimeError", "InitializationError", "Unknown", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesErrorCode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ UpdatesErrorCode[] $VALUES;
    private final String code;
    public static final UpdatesErrorCode None = new UpdatesErrorCode("None", 0, "None");
    public static final UpdatesErrorCode NoUpdatesAvailable = new UpdatesErrorCode("NoUpdatesAvailable", 1, "NoUpdatesAvailable");
    public static final UpdatesErrorCode UpdateAssetsNotAvailable = new UpdatesErrorCode("UpdateAssetsNotAvailable", 2, "UpdateAssetsNotAvailable");
    public static final UpdatesErrorCode UpdateServerUnreachable = new UpdatesErrorCode("UpdateServerUnreachable", 3, "UpdateServerUnreachable");
    public static final UpdatesErrorCode UpdateHasInvalidSignature = new UpdatesErrorCode("UpdateHasInvalidSignature", 4, "UpdateHasInvalidSignature");
    public static final UpdatesErrorCode UpdateCodeSigningError = new UpdatesErrorCode("UpdateCodeSigningError", 5, "UpdateCodeSigningError");
    public static final UpdatesErrorCode UpdateFailedToLoad = new UpdatesErrorCode("UpdateFailedToLoad", 6, "UpdateFailedToLoad");
    public static final UpdatesErrorCode AssetsFailedToLoad = new UpdatesErrorCode("AssetsFailedToLoad", 7, "AssetsFailedToLoad");
    public static final UpdatesErrorCode JSRuntimeError = new UpdatesErrorCode("JSRuntimeError", 8, "JSRuntimeError");
    public static final UpdatesErrorCode InitializationError = new UpdatesErrorCode("InitializationError", 9, "InitializationError");
    public static final UpdatesErrorCode Unknown = new UpdatesErrorCode("Unknown", 10, "Unknown");

    private static final /* synthetic */ UpdatesErrorCode[] $values() {
        return new UpdatesErrorCode[]{None, NoUpdatesAvailable, UpdateAssetsNotAvailable, UpdateServerUnreachable, UpdateHasInvalidSignature, UpdateCodeSigningError, UpdateFailedToLoad, AssetsFailedToLoad, JSRuntimeError, InitializationError, Unknown};
    }

    public static EnumEntries<UpdatesErrorCode> getEntries() {
        return $ENTRIES;
    }

    public static UpdatesErrorCode valueOf(String str) {
        return (UpdatesErrorCode) Enum.valueOf(UpdatesErrorCode.class, str);
    }

    public static UpdatesErrorCode[] values() {
        return (UpdatesErrorCode[]) $VALUES.clone();
    }

    public final String getCode() {
        return this.code;
    }

    private UpdatesErrorCode(String str, int i, String str2) {
        this.code = str2;
    }

    static {
        UpdatesErrorCode[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

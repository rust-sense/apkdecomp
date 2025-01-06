package expo.modules.updates.errorrecovery;

import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.launcher.Launcher;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* compiled from: ErrorRecoveryDelegate.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0013J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\tH&J\b\u0010\u000b\u001a\u00020\tH&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH&J\u0014\u0010\u000f\u001a\u00020\t2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H&¨\u0006\u0014"}, d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate;", "", "getCheckAutomaticallyConfiguration", "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "getLaunchedUpdateSuccessfulLaunchCount", "", "getRemoteLoadStatus", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "loadRemoteUpdate", "", "markFailedLaunchForLaunchedUpdate", "markSuccessfulLaunchForLaunchedUpdate", "relaunch", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "throwException", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "RemoteLoadStatus", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ErrorRecoveryDelegate {
    UpdatesConfiguration.CheckAutomaticallyConfiguration getCheckAutomaticallyConfiguration();

    int getLaunchedUpdateSuccessfulLaunchCount();

    RemoteLoadStatus getRemoteLoadStatus();

    void loadRemoteUpdate();

    void markFailedLaunchForLaunchedUpdate();

    void markSuccessfulLaunchForLaunchedUpdate();

    void relaunch(Launcher.LauncherCallback callback);

    void throwException(Exception exception);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: ErrorRecoveryDelegate.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "", "(Ljava/lang/String;I)V", "IDLE", "NEW_UPDATE_LOADING", "NEW_UPDATE_LOADED", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class RemoteLoadStatus {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ RemoteLoadStatus[] $VALUES;
        public static final RemoteLoadStatus IDLE = new RemoteLoadStatus("IDLE", 0);
        public static final RemoteLoadStatus NEW_UPDATE_LOADING = new RemoteLoadStatus("NEW_UPDATE_LOADING", 1);
        public static final RemoteLoadStatus NEW_UPDATE_LOADED = new RemoteLoadStatus("NEW_UPDATE_LOADED", 2);

        private static final /* synthetic */ RemoteLoadStatus[] $values() {
            return new RemoteLoadStatus[]{IDLE, NEW_UPDATE_LOADING, NEW_UPDATE_LOADED};
        }

        public static EnumEntries<RemoteLoadStatus> getEntries() {
            return $ENTRIES;
        }

        public static RemoteLoadStatus valueOf(String str) {
            return (RemoteLoadStatus) Enum.valueOf(RemoteLoadStatus.class, str);
        }

        public static RemoteLoadStatus[] values() {
            return (RemoteLoadStatus[]) $VALUES.clone();
        }

        private RemoteLoadStatus(String str, int i) {
        }

        static {
            RemoteLoadStatus[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }
}

package expo.modules.updates.loader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.db.DatabaseHolder;
import expo.modules.updates.db.Reaper;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.launcher.DatabaseLauncher;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.LoaderTask;
import expo.modules.updates.manifest.EmbeddedManifestUtils;
import expo.modules.updates.manifest.EmbeddedUpdate;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.manifest.Update;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import io.sentry.ProfilingTraceData;
import io.sentry.clientreport.DiscardedEvent;
import java.io.File;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: LoaderTask.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 -2\u00020\u0001:\u0006-./012B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\u001e\u001a\n\u0018\u00010\u001fj\u0004\u0018\u0001` H\u0002J\u0018\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010&\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010'\u001a\u00020%H\u0002J\b\u0010(\u001a\u00020\u001dH\u0002J\b\u0010)\u001a\u00020\u001dH\u0002J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#J\b\u0010+\u001a\u00020\u001dH\u0002J\b\u0010,\u001a\u00020\u001dH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lexpo/modules/updates/loader/LoaderTask;", "", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "directory", "Ljava/io/File;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "callback", "Lexpo/modules/updates/loader/LoaderTask$LoaderTaskCallback;", "(Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/DatabaseHolder;Ljava/io/File;Lexpo/modules/updates/loader/FileDownloader;Lexpo/modules/updates/selectionpolicy/SelectionPolicy;Lexpo/modules/updates/loader/LoaderTask$LoaderTaskCallback;)V", "candidateLauncher", "Lexpo/modules/updates/launcher/Launcher;", "finalizedLauncher", "handlerThread", "Landroid/os/HandlerThread;", "hasLaunched", "", "isReadyToLaunch", "<set-?>", "isRunning", "()Z", "isUpToDate", "timeoutFinished", "finish", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "launchFallbackUpdateFromDisk", "context", "Landroid/content/Context;", "diskUpdateCallback", "Lexpo/modules/updates/loader/LoaderTask$LaunchUpdateCallback;", "launchRemoteUpdateInBackground", "remoteUpdateCallback", "maybeFinish", "runReaper", ViewProps.START, "stopTimer", ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, "Companion", "LaunchUpdateCallback", "LoaderTaskCallback", "RemoteCheckResult", "RemoteCheckResultNotAvailableReason", "RemoteUpdateStatus", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LoaderTask {
    private static final String TAG = "LoaderTask";
    private final LoaderTaskCallback callback;
    private Launcher candidateLauncher;
    private final UpdatesConfiguration configuration;
    private final DatabaseHolder databaseHolder;
    private final File directory;
    private final FileDownloader fileDownloader;
    private Launcher finalizedLauncher;
    private final HandlerThread handlerThread;
    private boolean hasLaunched;
    private boolean isReadyToLaunch;
    private boolean isRunning;
    private boolean isUpToDate;
    private final SelectionPolicy selectionPolicy;
    private boolean timeoutFinished;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LoaderTask.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bb\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, d2 = {"Lexpo/modules/updates/loader/LoaderTask$LaunchUpdateCallback;", "", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    interface LaunchUpdateCallback {
        void onFailure(Exception e);

        void onSuccess();
    }

    /* compiled from: LoaderTask.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nH&J\b\u0010\u000b\u001a\u00020\u0007H&J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0007H\u0016J(\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J*\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u001a\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\nH&J\b\u0010\u001b\u001a\u00020\u0007H\u0016J\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u001dH&J\u0018\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0003H&¨\u0006\""}, d2 = {"Lexpo/modules/updates/loader/LoaderTask$LoaderTaskCallback;", "", "onCachedUpdateLoaded", "", "update", "Lexpo/modules/updates/db/entity/UpdateEntity;", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onFinishedAllLoading", "onRemoteCheckForUpdateFinished", "result", "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult;", "onRemoteCheckForUpdateStarted", "onRemoteUpdateAssetLoaded", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "successfulAssetCount", "", "failedAssetCount", "totalAssetCount", "onRemoteUpdateFinished", "status", "Lexpo/modules/updates/loader/LoaderTask$RemoteUpdateStatus;", "exception", "onRemoteUpdateLoadStarted", "onRemoteUpdateManifestResponseUpdateLoaded", "Lexpo/modules/updates/manifest/Update;", "onSuccess", "launcher", "Lexpo/modules/updates/launcher/Launcher;", "isUpToDate", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface LoaderTaskCallback {

        /* compiled from: LoaderTask.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class DefaultImpls {
            public static void onRemoteCheckForUpdateFinished(LoaderTaskCallback loaderTaskCallback, RemoteCheckResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
            }

            public static void onRemoteCheckForUpdateStarted(LoaderTaskCallback loaderTaskCallback) {
            }

            public static void onRemoteUpdateAssetLoaded(LoaderTaskCallback loaderTaskCallback, AssetEntity asset, int i, int i2, int i3) {
                Intrinsics.checkNotNullParameter(asset, "asset");
            }

            public static void onRemoteUpdateLoadStarted(LoaderTaskCallback loaderTaskCallback) {
            }
        }

        boolean onCachedUpdateLoaded(UpdateEntity update);

        void onFailure(Exception e);

        void onFinishedAllLoading();

        void onRemoteCheckForUpdateFinished(RemoteCheckResult result);

        void onRemoteCheckForUpdateStarted();

        void onRemoteUpdateAssetLoaded(AssetEntity asset, int successfulAssetCount, int failedAssetCount, int totalAssetCount);

        void onRemoteUpdateFinished(RemoteUpdateStatus status, UpdateEntity update, Exception exception);

        void onRemoteUpdateLoadStarted();

        void onRemoteUpdateManifestResponseUpdateLoaded(Update update);

        void onSuccess(Launcher launcher, boolean isUpToDate);
    }

    /* renamed from: isRunning, reason: from getter */
    public final boolean getIsRunning() {
        return this.isRunning;
    }

    public LoaderTask(UpdatesConfiguration configuration, DatabaseHolder databaseHolder, File directory, FileDownloader fileDownloader, SelectionPolicy selectionPolicy, LoaderTaskCallback callback) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(databaseHolder, "databaseHolder");
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.configuration = configuration;
        this.databaseHolder = databaseHolder;
        this.directory = directory;
        this.fileDownloader = fileDownloader;
        this.selectionPolicy = selectionPolicy;
        this.callback = callback;
        this.handlerThread = new HandlerThread("expo-updates-timer");
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: LoaderTask.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/updates/loader/LoaderTask$RemoteUpdateStatus;", "", "(Ljava/lang/String;I)V", "ERROR", "NO_UPDATE_AVAILABLE", "UPDATE_AVAILABLE", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class RemoteUpdateStatus {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ RemoteUpdateStatus[] $VALUES;
        public static final RemoteUpdateStatus ERROR = new RemoteUpdateStatus("ERROR", 0);
        public static final RemoteUpdateStatus NO_UPDATE_AVAILABLE = new RemoteUpdateStatus("NO_UPDATE_AVAILABLE", 1);
        public static final RemoteUpdateStatus UPDATE_AVAILABLE = new RemoteUpdateStatus("UPDATE_AVAILABLE", 2);

        private static final /* synthetic */ RemoteUpdateStatus[] $values() {
            return new RemoteUpdateStatus[]{ERROR, NO_UPDATE_AVAILABLE, UPDATE_AVAILABLE};
        }

        public static EnumEntries<RemoteUpdateStatus> getEntries() {
            return $ENTRIES;
        }

        public static RemoteUpdateStatus valueOf(String str) {
            return (RemoteUpdateStatus) Enum.valueOf(RemoteUpdateStatus.class, str);
        }

        public static RemoteUpdateStatus[] values() {
            return (RemoteUpdateStatus[]) $VALUES.clone();
        }

        private RemoteUpdateStatus(String str, int i) {
        }

        static {
            RemoteUpdateStatus[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: LoaderTask.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResultNotAvailableReason;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "NO_UPDATE_AVAILABLE_ON_SERVER", "UPDATE_REJECTED_BY_SELECTION_POLICY", "UPDATE_PREVIOUSLY_FAILED", "ROLLBACK_REJECTED_BY_SELECTION_POLICY", "ROLLBACK_NO_EMBEDDED", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class RemoteCheckResultNotAvailableReason {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ RemoteCheckResultNotAvailableReason[] $VALUES;
        private final String value;
        public static final RemoteCheckResultNotAvailableReason NO_UPDATE_AVAILABLE_ON_SERVER = new RemoteCheckResultNotAvailableReason("NO_UPDATE_AVAILABLE_ON_SERVER", 0, "noUpdateAvailableOnServer");
        public static final RemoteCheckResultNotAvailableReason UPDATE_REJECTED_BY_SELECTION_POLICY = new RemoteCheckResultNotAvailableReason("UPDATE_REJECTED_BY_SELECTION_POLICY", 1, "updateRejectedBySelectionPolicy");
        public static final RemoteCheckResultNotAvailableReason UPDATE_PREVIOUSLY_FAILED = new RemoteCheckResultNotAvailableReason("UPDATE_PREVIOUSLY_FAILED", 2, "updatePreviouslyFailed");
        public static final RemoteCheckResultNotAvailableReason ROLLBACK_REJECTED_BY_SELECTION_POLICY = new RemoteCheckResultNotAvailableReason("ROLLBACK_REJECTED_BY_SELECTION_POLICY", 3, "rollbackRejectedBySelectionPolicy");
        public static final RemoteCheckResultNotAvailableReason ROLLBACK_NO_EMBEDDED = new RemoteCheckResultNotAvailableReason("ROLLBACK_NO_EMBEDDED", 4, "rollbackNoEmbeddedConfiguration");

        private static final /* synthetic */ RemoteCheckResultNotAvailableReason[] $values() {
            return new RemoteCheckResultNotAvailableReason[]{NO_UPDATE_AVAILABLE_ON_SERVER, UPDATE_REJECTED_BY_SELECTION_POLICY, UPDATE_PREVIOUSLY_FAILED, ROLLBACK_REJECTED_BY_SELECTION_POLICY, ROLLBACK_NO_EMBEDDED};
        }

        public static EnumEntries<RemoteCheckResultNotAvailableReason> getEntries() {
            return $ENTRIES;
        }

        public static RemoteCheckResultNotAvailableReason valueOf(String str) {
            return (RemoteCheckResultNotAvailableReason) Enum.valueOf(RemoteCheckResultNotAvailableReason.class, str);
        }

        public static RemoteCheckResultNotAvailableReason[] values() {
            return (RemoteCheckResultNotAvailableReason[]) $VALUES.clone();
        }

        public final String getValue() {
            return this.value;
        }

        private RemoteCheckResultNotAvailableReason(String str, int i, String str2) {
            this.value = str2;
        }

        static {
            RemoteCheckResultNotAvailableReason[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* compiled from: LoaderTask.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0005\u0006\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0003\t\n\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult;", "", "status", "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult$Status;", "(Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult$Status;)V", "NoUpdateAvailable", "RollBackToEmbedded", "Status", "UpdateAvailable", "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult$NoUpdateAvailable;", "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult$RollBackToEmbedded;", "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult$UpdateAvailable;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class RemoteCheckResult {
        private final Status status;

        public /* synthetic */ RemoteCheckResult(Status status, DefaultConstructorMarker defaultConstructorMarker) {
            this(status);
        }

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: LoaderTask.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult$Status;", "", "(Ljava/lang/String;I)V", "NO_UPDATE_AVAILABLE", "UPDATE_AVAILABLE", "ROLL_BACK_TO_EMBEDDED", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        private static final class Status {
            private static final /* synthetic */ EnumEntries $ENTRIES;
            private static final /* synthetic */ Status[] $VALUES;
            public static final Status NO_UPDATE_AVAILABLE = new Status("NO_UPDATE_AVAILABLE", 0);
            public static final Status UPDATE_AVAILABLE = new Status("UPDATE_AVAILABLE", 1);
            public static final Status ROLL_BACK_TO_EMBEDDED = new Status("ROLL_BACK_TO_EMBEDDED", 2);

            private static final /* synthetic */ Status[] $values() {
                return new Status[]{NO_UPDATE_AVAILABLE, UPDATE_AVAILABLE, ROLL_BACK_TO_EMBEDDED};
            }

            public static EnumEntries<Status> getEntries() {
                return $ENTRIES;
            }

            public static Status valueOf(String str) {
                return (Status) Enum.valueOf(Status.class, str);
            }

            public static Status[] values() {
                return (Status[]) $VALUES.clone();
            }

            private Status(String str, int i) {
            }

            static {
                Status[] $values = $values();
                $VALUES = $values;
                $ENTRIES = EnumEntriesKt.enumEntries($values);
            }
        }

        private RemoteCheckResult(Status status) {
            this.status = status;
        }

        /* compiled from: LoaderTask.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult$NoUpdateAvailable;", "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult;", DiscardedEvent.JsonKeys.REASON, "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResultNotAvailableReason;", "(Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResultNotAvailableReason;)V", "getReason", "()Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResultNotAvailableReason;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NoUpdateAvailable extends RemoteCheckResult {
            private final RemoteCheckResultNotAvailableReason reason;

            public final RemoteCheckResultNotAvailableReason getReason() {
                return this.reason;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NoUpdateAvailable(RemoteCheckResultNotAvailableReason reason) {
                super(Status.NO_UPDATE_AVAILABLE, null);
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.reason = reason;
            }
        }

        /* compiled from: LoaderTask.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult$UpdateAvailable;", "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult;", "manifest", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getManifest", "()Lorg/json/JSONObject;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class UpdateAvailable extends RemoteCheckResult {
            private final JSONObject manifest;

            public final JSONObject getManifest() {
                return this.manifest;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public UpdateAvailable(JSONObject manifest) {
                super(Status.UPDATE_AVAILABLE, null);
                Intrinsics.checkNotNullParameter(manifest, "manifest");
                this.manifest = manifest;
            }
        }

        /* compiled from: LoaderTask.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult$RollBackToEmbedded;", "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResult;", "commitTime", "Ljava/util/Date;", "(Ljava/util/Date;)V", "getCommitTime", "()Ljava/util/Date;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class RollBackToEmbedded extends RemoteCheckResult {
            private final Date commitTime;

            public final Date getCommitTime() {
                return this.commitTime;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public RollBackToEmbedded(Date commitTime) {
                super(Status.ROLL_BACK_TO_EMBEDDED, null);
                Intrinsics.checkNotNullParameter(commitTime, "commitTime");
                this.commitTime = commitTime;
            }
        }
    }

    public final void start(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.isRunning = true;
        final boolean shouldCheckForUpdateOnLaunch = UpdatesUtils.INSTANCE.shouldCheckForUpdateOnLaunch(this.configuration, context);
        int launchWaitMs = this.configuration.getLaunchWaitMs();
        if (launchWaitMs <= 0 || !shouldCheckForUpdateOnLaunch) {
            this.timeoutFinished = true;
        } else {
            this.handlerThread.start();
            new Handler(this.handlerThread.getLooper()).postDelayed(new Runnable() { // from class: expo.modules.updates.loader.LoaderTask$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    LoaderTask.start$lambda$0(LoaderTask.this);
                }
            }, launchWaitMs);
        }
        launchFallbackUpdateFromDisk(context, new LaunchUpdateCallback() { // from class: expo.modules.updates.loader.LoaderTask$start$2
            private final void launchRemoteUpdate() {
                final LoaderTask loaderTask = LoaderTask.this;
                loaderTask.launchRemoteUpdateInBackground(context, new LoaderTask.LaunchUpdateCallback() { // from class: expo.modules.updates.loader.LoaderTask$start$2$launchRemoteUpdate$1
                    @Override // expo.modules.updates.loader.LoaderTask.LaunchUpdateCallback
                    public void onFailure(Exception e) {
                        LoaderTask.LoaderTaskCallback loaderTaskCallback;
                        Intrinsics.checkNotNullParameter(e, "e");
                        LoaderTask.this.finish(e);
                        LoaderTask.this.isRunning = false;
                        LoaderTask.this.runReaper();
                        loaderTaskCallback = LoaderTask.this.callback;
                        loaderTaskCallback.onFinishedAllLoading();
                    }

                    @Override // expo.modules.updates.loader.LoaderTask.LaunchUpdateCallback
                    public void onSuccess() {
                        LoaderTask.LoaderTaskCallback loaderTaskCallback;
                        LoaderTask loaderTask2 = LoaderTask.this;
                        synchronized (loaderTask2) {
                            loaderTask2.isReadyToLaunch = true;
                            Unit unit = Unit.INSTANCE;
                        }
                        LoaderTask.this.finish(null);
                        LoaderTask.this.isRunning = false;
                        LoaderTask.this.runReaper();
                        loaderTaskCallback = LoaderTask.this.callback;
                        loaderTaskCallback.onFinishedAllLoading();
                    }
                });
            }

            @Override // expo.modules.updates.loader.LoaderTask.LaunchUpdateCallback
            public void onFailure(Exception e) {
                String str;
                LoaderTask.LoaderTaskCallback loaderTaskCallback;
                Intrinsics.checkNotNullParameter(e, "e");
                if (!shouldCheckForUpdateOnLaunch) {
                    LoaderTask.this.finish(e);
                    LoaderTask.this.isRunning = false;
                    loaderTaskCallback = LoaderTask.this.callback;
                    loaderTaskCallback.onFinishedAllLoading();
                } else {
                    launchRemoteUpdate();
                }
                str = LoaderTask.TAG;
                Log.e(str, "Failed to launch embedded or launchable update", e);
            }

            @Override // expo.modules.updates.loader.LoaderTask.LaunchUpdateCallback
            public void onSuccess() {
                Launcher launcher;
                LoaderTask.LoaderTaskCallback loaderTaskCallback;
                LoaderTask.LoaderTaskCallback loaderTaskCallback2;
                Launcher launcher2;
                launcher = LoaderTask.this.candidateLauncher;
                Intrinsics.checkNotNull(launcher);
                if (launcher.getLaunchedUpdate() != null) {
                    loaderTaskCallback2 = LoaderTask.this.callback;
                    launcher2 = LoaderTask.this.candidateLauncher;
                    Intrinsics.checkNotNull(launcher2);
                    UpdateEntity launchedUpdate = launcher2.getLaunchedUpdate();
                    Intrinsics.checkNotNull(launchedUpdate);
                    if (!loaderTaskCallback2.onCachedUpdateLoaded(launchedUpdate)) {
                        LoaderTask.this.stopTimer();
                        LoaderTask.this.candidateLauncher = null;
                        launchRemoteUpdate();
                        return;
                    }
                }
                LoaderTask loaderTask = LoaderTask.this;
                synchronized (loaderTask) {
                    loaderTask.isReadyToLaunch = true;
                    loaderTask.maybeFinish();
                    Unit unit = Unit.INSTANCE;
                }
                if (!shouldCheckForUpdateOnLaunch) {
                    LoaderTask.this.isRunning = false;
                    LoaderTask.this.runReaper();
                    loaderTaskCallback = LoaderTask.this.callback;
                    loaderTaskCallback.onFinishedAllLoading();
                    return;
                }
                launchRemoteUpdate();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start$lambda$0(LoaderTask this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.timeout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003f A[Catch: all -> 0x004f, TryCatch #0 {, blocks: (B:3:0x0001, B:9:0x0008, B:12:0x0014, B:15:0x001e, B:16:0x003b, B:18:0x003f, B:20:0x0044, B:24:0x002b, B:26:0x002f, B:27:0x0038), top: B:2:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044 A[Catch: all -> 0x004f, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:9:0x0008, B:12:0x0014, B:15:0x001e, B:16:0x003b, B:18:0x003f, B:20:0x0044, B:24:0x002b, B:26:0x002f, B:27:0x0038), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void finish(java.lang.Exception r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.hasLaunched     // Catch: java.lang.Throwable -> L4f
            if (r0 == 0) goto L7
            monitor-exit(r3)
            return
        L7:
            r0 = 1
            r3.hasLaunched = r0     // Catch: java.lang.Throwable -> L4f
            expo.modules.updates.launcher.Launcher r0 = r3.candidateLauncher     // Catch: java.lang.Throwable -> L4f
            r3.finalizedLauncher = r0     // Catch: java.lang.Throwable -> L4f
            boolean r1 = r3.isReadyToLaunch     // Catch: java.lang.Throwable -> L4f
            if (r1 == 0) goto L2b
            if (r0 == 0) goto L2b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Throwable -> L4f
            expo.modules.updates.db.entity.UpdateEntity r0 = r0.getLaunchedUpdate()     // Catch: java.lang.Throwable -> L4f
            if (r0 != 0) goto L1e
            goto L2b
        L1e:
            expo.modules.updates.loader.LoaderTask$LoaderTaskCallback r0 = r3.callback     // Catch: java.lang.Throwable -> L4f
            expo.modules.updates.launcher.Launcher r1 = r3.finalizedLauncher     // Catch: java.lang.Throwable -> L4f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Throwable -> L4f
            boolean r2 = r3.isUpToDate     // Catch: java.lang.Throwable -> L4f
            r0.onSuccess(r1, r2)     // Catch: java.lang.Throwable -> L4f
            goto L3b
        L2b:
            expo.modules.updates.loader.LoaderTask$LoaderTaskCallback r0 = r3.callback     // Catch: java.lang.Throwable -> L4f
            if (r4 != 0) goto L37
            java.lang.Exception r1 = new java.lang.Exception     // Catch: java.lang.Throwable -> L4f
            java.lang.String r2 = "LoaderTask encountered an unexpected error and could not launch an update."
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L4f
            goto L38
        L37:
            r1 = r4
        L38:
            r0.onFailure(r1)     // Catch: java.lang.Throwable -> L4f
        L3b:
            boolean r0 = r3.timeoutFinished     // Catch: java.lang.Throwable -> L4f
            if (r0 != 0) goto L42
            r3.stopTimer()     // Catch: java.lang.Throwable -> L4f
        L42:
            if (r4 == 0) goto L4d
            java.lang.String r0 = expo.modules.updates.loader.LoaderTask.TAG     // Catch: java.lang.Throwable -> L4f
            java.lang.String r1 = "Unexpected error encountered while loading this app"
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch: java.lang.Throwable -> L4f
            android.util.Log.e(r0, r1, r4)     // Catch: java.lang.Throwable -> L4f
        L4d:
            monitor-exit(r3)
            return
        L4f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.updates.loader.LoaderTask.finish(java.lang.Exception):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void maybeFinish() {
        if (this.isReadyToLaunch && this.timeoutFinished) {
            finish(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void stopTimer() {
        this.timeoutFinished = true;
        this.handlerThread.quitSafely();
    }

    private final synchronized void timeout() {
        if (!this.timeoutFinished) {
            this.timeoutFinished = true;
            maybeFinish();
        }
        stopTimer();
    }

    private final void launchFallbackUpdateFromDisk(final Context context, final LaunchUpdateCallback diskUpdateCallback) {
        final UpdatesDatabase database = this.databaseHolder.getDatabase();
        final DatabaseLauncher databaseLauncher = new DatabaseLauncher(this.configuration, this.directory, this.fileDownloader, this.selectionPolicy);
        this.candidateLauncher = databaseLauncher;
        final Launcher.LauncherCallback launcherCallback = new Launcher.LauncherCallback() { // from class: expo.modules.updates.loader.LoaderTask$launchFallbackUpdateFromDisk$launcherCallback$1
            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onFailure(Exception e) {
                DatabaseHolder databaseHolder;
                Intrinsics.checkNotNullParameter(e, "e");
                databaseHolder = LoaderTask.this.databaseHolder;
                databaseHolder.releaseDatabase();
                diskUpdateCallback.onFailure(e);
            }

            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onSuccess() {
                DatabaseHolder databaseHolder;
                databaseHolder = LoaderTask.this.databaseHolder;
                databaseHolder.releaseDatabase();
                diskUpdateCallback.onSuccess();
            }
        };
        if (this.configuration.getHasEmbeddedUpdate()) {
            EmbeddedUpdate embeddedUpdate = EmbeddedManifestUtils.INSTANCE.getEmbeddedUpdate(context, this.configuration);
            Intrinsics.checkNotNull(embeddedUpdate);
            if (this.selectionPolicy.shouldLoadNewUpdate(embeddedUpdate.getUpdateEntity(), databaseLauncher.getLaunchableUpdate(database, context), ManifestMetadata.INSTANCE.getManifestFilters(database, this.configuration))) {
                new EmbeddedLoader(context, this.configuration, database, this.directory).start(new Loader.LoaderCallback() { // from class: expo.modules.updates.loader.LoaderTask$launchFallbackUpdateFromDisk$1
                    @Override // expo.modules.updates.loader.Loader.LoaderCallback
                    public void onAssetLoaded(AssetEntity asset, int successfulAssetCount, int failedAssetCount, int totalAssetCount) {
                        Intrinsics.checkNotNullParameter(asset, "asset");
                    }

                    @Override // expo.modules.updates.loader.Loader.LoaderCallback
                    public void onFailure(Exception e) {
                        String str;
                        Intrinsics.checkNotNullParameter(e, "e");
                        str = LoaderTask.TAG;
                        Log.e(str, "Unexpected error copying embedded update", e);
                        DatabaseLauncher.this.launch(database, context, launcherCallback);
                    }

                    @Override // expo.modules.updates.loader.Loader.LoaderCallback
                    public void onSuccess(Loader.LoaderResult loaderResult) {
                        Intrinsics.checkNotNullParameter(loaderResult, "loaderResult");
                        DatabaseLauncher.this.launch(database, context, launcherCallback);
                    }

                    @Override // expo.modules.updates.loader.Loader.LoaderCallback
                    public Loader.OnUpdateResponseLoadedResult onUpdateResponseLoaded(UpdateResponse updateResponse) {
                        Intrinsics.checkNotNullParameter(updateResponse, "updateResponse");
                        return new Loader.OnUpdateResponseLoadedResult(true);
                    }
                });
                return;
            } else {
                databaseLauncher.launch(database, context, launcherCallback);
                return;
            }
        }
        databaseLauncher.launch(database, context, launcherCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchRemoteUpdateInBackground(final Context context, final LaunchUpdateCallback remoteUpdateCallback) {
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.loader.LoaderTask$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LoaderTask.launchRemoteUpdateInBackground$lambda$1(LoaderTask.this, context, remoteUpdateCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void launchRemoteUpdateInBackground$lambda$1(LoaderTask this$0, Context context, LaunchUpdateCallback remoteUpdateCallback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(remoteUpdateCallback, "$remoteUpdateCallback");
        UpdatesDatabase database = this$0.databaseHolder.getDatabase();
        this$0.callback.onRemoteCheckForUpdateStarted();
        UpdatesConfiguration updatesConfiguration = this$0.configuration;
        FileDownloader fileDownloader = this$0.fileDownloader;
        File file = this$0.directory;
        Launcher launcher = this$0.candidateLauncher;
        new RemoteLoader(context, updatesConfiguration, database, fileDownloader, file, launcher != null ? launcher.getLaunchedUpdate() : null).start(new LoaderTask$launchRemoteUpdateInBackground$1$1(this$0, remoteUpdateCallback, context, database));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runReaper() {
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.loader.LoaderTask$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                LoaderTask.runReaper$lambda$3(LoaderTask.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runReaper$lambda$3(LoaderTask this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        synchronized (this$0) {
            Launcher launcher = this$0.finalizedLauncher;
            UpdateEntity launchedUpdate = launcher != null ? launcher.getLaunchedUpdate() : null;
            if (launchedUpdate != null) {
                Reaper.reapUnusedUpdates(this$0.configuration, this$0.databaseHolder.getDatabase(), this$0.directory, launchedUpdate, this$0.selectionPolicy);
                this$0.databaseHolder.releaseDatabase();
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}

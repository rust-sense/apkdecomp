package expo.modules.updates.procedures;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.DatabaseHolder;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.errorrecovery.ErrorRecovery;
import expo.modules.updates.errorrecovery.ErrorRecoveryDelegate;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.launcher.NoDatabaseLauncher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.LoaderTask;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.Update;
import expo.modules.updates.procedures.StateMachineProcedure;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import expo.modules.updates.statemachine.UpdatesStateEvent;
import expo.modules.updates.statemachine.UpdatesStateValue;
import io.sentry.SentryEvent;
import java.io.File;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StartupProcedure.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001JBE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\b\u0010=\u001a\u00020>H\u0002J\b\u0010?\u001a\u00020>H\u0002J\b\u0010@\u001a\u00020>H\u0002J\u000e\u0010A\u001a\u00020>2\u0006\u0010B\u001a\u00020CJ\u0012\u0010D\u001a\u00020>2\n\u0010E\u001a\u00060\u001cj\u0002`\u001dJ\u0010\u0010F\u001a\u00020>2\u0006\u00109\u001a\u00020:H\u0016J\u000e\u0010G\u001a\u00020>2\u0006\u0010-\u001a\u00020,J\u0010\u0010H\u001a\u00020>2\u0006\u0010I\u001a\u00020<H\u0002R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u001e\u001a\n\u0018\u00010\u001cj\u0004\u0018\u0001`\u001d2\u000e\u0010\u001b\u001a\n\u0018\u00010\u001cj\u0004\u0018\u0001`\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010#\u001a\u00020$8F¢\u0006\u0006\u001a\u0004\b#\u0010%R\u0013\u0010&\u001a\u0004\u0018\u00010\u00148F¢\u0006\u0006\u001a\u0004\b'\u0010\u0016R\u0013\u0010(\u001a\u0004\u0018\u00010)8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\"\u0010-\u001a\u0004\u0018\u00010,2\b\u0010\u001b\u001a\u0004\u0018\u00010,@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00102\u001a\u0010\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u0014\u0018\u0001038F¢\u0006\u0006\u001a\u0004\b5\u00106R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\u00020\u0014X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0016R\u000e\u00109\u001a\u00020:X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lexpo/modules/updates/procedures/StartupProcedure;", "Lexpo/modules/updates/procedures/StateMachineProcedure;", "context", "Landroid/content/Context;", "updatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "updatesDirectory", "Ljava/io/File;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", SentryEvent.JsonKeys.LOGGER, "Lexpo/modules/updates/logging/UpdatesLogger;", "callback", "Lexpo/modules/updates/procedures/StartupProcedure$StartupProcedureCallback;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/DatabaseHolder;Ljava/io/File;Lexpo/modules/updates/loader/FileDownloader;Lexpo/modules/updates/selectionpolicy/SelectionPolicy;Lexpo/modules/updates/logging/UpdatesLogger;Lexpo/modules/updates/procedures/StartupProcedure$StartupProcedureCallback;)V", "bundleAssetName", "", "getBundleAssetName", "()Ljava/lang/String;", "databaseHandler", "Landroid/os/Handler;", "databaseHandlerThread", "Landroid/os/HandlerThread;", "<set-?>", "Ljava/lang/Exception;", "Lkotlin/Exception;", "emergencyLaunchException", "getEmergencyLaunchException", "()Ljava/lang/Exception;", "errorRecovery", "Lexpo/modules/updates/errorrecovery/ErrorRecovery;", "isUsingEmbeddedAssets", "", "()Z", "launchAssetFile", "getLaunchAssetFile", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getLaunchedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "Lexpo/modules/updates/launcher/Launcher;", "launcher", "getLauncher", "()Lexpo/modules/updates/launcher/Launcher;", "loaderTask", "Lexpo/modules/updates/loader/LoaderTask;", "localAssetFiles", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "getLocalAssetFiles", "()Ljava/util/Map;", "loggerTimerLabel", "getLoggerTimerLabel", "procedureContext", "Lexpo/modules/updates/procedures/StateMachineProcedure$ProcedureContext;", "remoteLoadStatus", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "initializeDatabaseHandler", "", "initializeErrorRecovery", "notifyController", "onDidCreateDevSupportManager", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "onReactInstanceException", "exception", "run", "setLauncher", "setRemoteLoadStatus", "status", "StartupProcedureCallback", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class StartupProcedure extends StateMachineProcedure {
    private final StartupProcedureCallback callback;
    private final Context context;
    private Handler databaseHandler;
    private final HandlerThread databaseHandlerThread;
    private final DatabaseHolder databaseHolder;
    private Exception emergencyLaunchException;
    private final ErrorRecovery errorRecovery;
    private final FileDownloader fileDownloader;
    private Launcher launcher;
    private final LoaderTask loaderTask;
    private final UpdatesLogger logger;
    private final String loggerTimerLabel;
    private StateMachineProcedure.ProcedureContext procedureContext;
    private ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
    private final SelectionPolicy selectionPolicy;
    private final UpdatesConfiguration updatesConfiguration;
    private final File updatesDirectory;

    /* compiled from: StartupProcedure.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lexpo/modules/updates/procedures/StartupProcedure$StartupProcedureCallback;", "", "onFinished", "", "onRequestRelaunch", "shouldRunReaper", "", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface StartupProcedureCallback {
        void onFinished();

        void onRequestRelaunch(boolean shouldRunReaper, Launcher.LauncherCallback callback);
    }

    public final Exception getEmergencyLaunchException() {
        return this.emergencyLaunchException;
    }

    public final Launcher getLauncher() {
        return this.launcher;
    }

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public String getLoggerTimerLabel() {
        return this.loggerTimerLabel;
    }

    public final void setLauncher(Launcher launcher) {
        Intrinsics.checkNotNullParameter(launcher, "launcher");
        this.launcher = launcher;
    }

    public StartupProcedure(Context context, UpdatesConfiguration updatesConfiguration, DatabaseHolder databaseHolder, File updatesDirectory, FileDownloader fileDownloader, SelectionPolicy selectionPolicy, UpdatesLogger logger, StartupProcedureCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(databaseHolder, "databaseHolder");
        Intrinsics.checkNotNullParameter(updatesDirectory, "updatesDirectory");
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.context = context;
        this.updatesConfiguration = updatesConfiguration;
        this.databaseHolder = databaseHolder;
        this.updatesDirectory = updatesDirectory;
        this.fileDownloader = fileDownloader;
        this.selectionPolicy = selectionPolicy;
        this.logger = logger;
        this.callback = callback;
        this.loggerTimerLabel = "timer-startup";
        this.errorRecovery = new ErrorRecovery(context);
        this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
        this.databaseHandlerThread = new HandlerThread("expo-updates-database");
        this.loaderTask = new LoaderTask(updatesConfiguration, databaseHolder, updatesDirectory, fileDownloader, selectionPolicy, new LoaderTask.LoaderTaskCallback() { // from class: expo.modules.updates.procedures.StartupProcedure$loaderTask$1

            /* compiled from: StartupProcedure.kt */
            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;
                public static final /* synthetic */ int[] $EnumSwitchMapping$1;

                static {
                    int[] iArr = new int[UpdatesStateValue.values().length];
                    try {
                        iArr[UpdatesStateValue.Idle.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[UpdatesStateValue.Checking.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                    int[] iArr2 = new int[LoaderTask.RemoteUpdateStatus.values().length];
                    try {
                        iArr2[LoaderTask.RemoteUpdateStatus.ERROR.ordinal()] = 1;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr2[LoaderTask.RemoteUpdateStatus.UPDATE_AVAILABLE.ordinal()] = 2;
                    } catch (NoSuchFieldError unused4) {
                    }
                    try {
                        iArr2[LoaderTask.RemoteUpdateStatus.NO_UPDATE_AVAILABLE.ordinal()] = 3;
                    } catch (NoSuchFieldError unused5) {
                    }
                    $EnumSwitchMapping$1 = iArr2;
                }
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public boolean onCachedUpdateLoaded(UpdateEntity update) {
                Intrinsics.checkNotNullParameter(update, "update");
                return true;
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public void onFailure(Exception e) {
                UpdatesLogger updatesLogger;
                Context context2;
                Intrinsics.checkNotNullParameter(e, "e");
                updatesLogger = StartupProcedure.this.logger;
                UpdatesLogger.error$default(updatesLogger, "UpdatesController loaderTask onFailure: " + e.getLocalizedMessage(), UpdatesErrorCode.None, null, 4, null);
                StartupProcedure startupProcedure = StartupProcedure.this;
                context2 = StartupProcedure.this.context;
                startupProcedure.launcher = new NoDatabaseLauncher(context2, e);
                StartupProcedure.this.emergencyLaunchException = e;
                StartupProcedure.this.notifyController();
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public void onSuccess(Launcher launcher, boolean isUpToDate) {
                ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
                Intrinsics.checkNotNullParameter(launcher, "launcher");
                remoteLoadStatus = StartupProcedure.this.remoteLoadStatus;
                if (remoteLoadStatus == ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADING && isUpToDate) {
                    StartupProcedure.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
                }
                StartupProcedure.this.launcher = launcher;
                StartupProcedure.this.notifyController();
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public void onFinishedAllLoading() {
                StateMachineProcedure.ProcedureContext procedureContext;
                procedureContext = StartupProcedure.this.procedureContext;
                if (procedureContext == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                    procedureContext = null;
                }
                procedureContext.onComplete();
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public void onRemoteCheckForUpdateStarted() {
                StateMachineProcedure.ProcedureContext procedureContext;
                procedureContext = StartupProcedure.this.procedureContext;
                if (procedureContext == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                    procedureContext = null;
                }
                procedureContext.processStateEvent(new UpdatesStateEvent.Check());
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public void onRemoteCheckForUpdateFinished(LoaderTask.RemoteCheckResult result) {
                UpdatesStateEvent.CheckCompleteWithRollback checkCompleteWithRollback;
                StateMachineProcedure.ProcedureContext procedureContext;
                Intrinsics.checkNotNullParameter(result, "result");
                if (result instanceof LoaderTask.RemoteCheckResult.NoUpdateAvailable) {
                    checkCompleteWithRollback = new UpdatesStateEvent.CheckCompleteUnavailable();
                } else if (result instanceof LoaderTask.RemoteCheckResult.UpdateAvailable) {
                    checkCompleteWithRollback = new UpdatesStateEvent.CheckCompleteWithUpdate(((LoaderTask.RemoteCheckResult.UpdateAvailable) result).getManifest());
                } else {
                    if (!(result instanceof LoaderTask.RemoteCheckResult.RollBackToEmbedded)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    checkCompleteWithRollback = new UpdatesStateEvent.CheckCompleteWithRollback(((LoaderTask.RemoteCheckResult.RollBackToEmbedded) result).getCommitTime());
                }
                procedureContext = StartupProcedure.this.procedureContext;
                if (procedureContext == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                    procedureContext = null;
                }
                procedureContext.processStateEvent(checkCompleteWithRollback);
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public void onRemoteUpdateManifestResponseUpdateLoaded(Update update) {
                Intrinsics.checkNotNullParameter(update, "update");
                StartupProcedure.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADING;
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public void onRemoteUpdateLoadStarted() {
                StateMachineProcedure.ProcedureContext procedureContext;
                procedureContext = StartupProcedure.this.procedureContext;
                if (procedureContext == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                    procedureContext = null;
                }
                procedureContext.processStateEvent(new UpdatesStateEvent.Download());
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public void onRemoteUpdateAssetLoaded(AssetEntity asset, int successfulAssetCount, int failedAssetCount, int totalAssetCount) {
                UpdatesLogger updatesLogger;
                Intrinsics.checkNotNullParameter(asset, "asset");
                Map mapOf = MapsKt.mapOf(TuplesKt.to("assetInfo", MapsKt.mapOf(TuplesKt.to("name", asset.getEmbeddedAssetFilename()), TuplesKt.to("successfulAssetCount", Integer.valueOf(successfulAssetCount)), TuplesKt.to("failedAssetCount", Integer.valueOf(failedAssetCount)), TuplesKt.to("totalAssetCount", Integer.valueOf(totalAssetCount)))));
                updatesLogger = StartupProcedure.this.logger;
                updatesLogger.info("AppController appLoaderTask didLoadAsset: " + mapOf, UpdatesErrorCode.None, null, asset.getExpectedHash());
            }

            @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
            public void onRemoteUpdateFinished(LoaderTask.RemoteUpdateStatus status, UpdateEntity update, Exception exception) {
                UpdatesLogger updatesLogger;
                StateMachineProcedure.ProcedureContext procedureContext;
                StateMachineProcedure.ProcedureContext procedureContext2;
                StateMachineProcedure.ProcedureContext procedureContext3;
                StateMachineProcedure.ProcedureContext procedureContext4;
                StateMachineProcedure.ProcedureContext procedureContext5;
                ErrorRecovery errorRecovery;
                ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
                UpdatesLogger updatesLogger2;
                StateMachineProcedure.ProcedureContext procedureContext6;
                UpdatesLogger updatesLogger3;
                StateMachineProcedure.ProcedureContext procedureContext7;
                StateMachineProcedure.ProcedureContext procedureContext8;
                Intrinsics.checkNotNullParameter(status, "status");
                int i = WhenMappings.$EnumSwitchMapping$1[status.ordinal()];
                StateMachineProcedure.ProcedureContext procedureContext9 = null;
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3) {
                            StartupProcedure.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
                            updatesLogger3 = StartupProcedure.this.logger;
                            UpdatesLogger.error$default(updatesLogger3, "UpdatesController onBackgroundUpdateFinished: No update available", UpdatesErrorCode.NoUpdatesAvailable, null, 4, null);
                            procedureContext7 = StartupProcedure.this.procedureContext;
                            if (procedureContext7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                                procedureContext7 = null;
                            }
                            if (procedureContext7.getCurrentState() == UpdatesStateValue.Downloading) {
                                procedureContext8 = StartupProcedure.this.procedureContext;
                                if (procedureContext8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                                } else {
                                    procedureContext9 = procedureContext8;
                                }
                                procedureContext9.processStateEvent(new UpdatesStateEvent.DownloadComplete());
                            }
                        }
                    } else if (update != null) {
                        StartupProcedure.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADED;
                        updatesLogger2 = StartupProcedure.this.logger;
                        updatesLogger2.info("UpdatesController onBackgroundUpdateFinished: Update available", UpdatesErrorCode.None);
                        procedureContext6 = StartupProcedure.this.procedureContext;
                        if (procedureContext6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                        } else {
                            procedureContext9 = procedureContext6;
                        }
                        procedureContext9.processStateEvent(new UpdatesStateEvent.DownloadCompleteWithUpdate(update.getManifest()));
                    } else {
                        throw new AssertionError("Background update with error status must have a nonnull update object");
                    }
                } else if (exception != null) {
                    updatesLogger = StartupProcedure.this.logger;
                    updatesLogger.error("UpdatesController onBackgroundUpdateFinished: Error: " + exception.getLocalizedMessage(), UpdatesErrorCode.Unknown, exception);
                    StartupProcedure.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
                    procedureContext = StartupProcedure.this.procedureContext;
                    if (procedureContext == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                        procedureContext = null;
                    }
                    int i2 = WhenMappings.$EnumSwitchMapping$0[procedureContext.getCurrentState().ordinal()];
                    if (i2 == 1) {
                        procedureContext2 = StartupProcedure.this.procedureContext;
                        if (procedureContext2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                            procedureContext2 = null;
                        }
                        procedureContext2.processStateEvent(new UpdatesStateEvent.Download());
                        procedureContext3 = StartupProcedure.this.procedureContext;
                        if (procedureContext3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                        } else {
                            procedureContext9 = procedureContext3;
                        }
                        String message = exception.getMessage();
                        procedureContext9.processStateEvent(new UpdatesStateEvent.DownloadError(message != null ? message : ""));
                    } else if (i2 != 2) {
                        procedureContext5 = StartupProcedure.this.procedureContext;
                        if (procedureContext5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                        } else {
                            procedureContext9 = procedureContext5;
                        }
                        String message2 = exception.getMessage();
                        procedureContext9.processStateEvent(new UpdatesStateEvent.DownloadError(message2 != null ? message2 : ""));
                    } else {
                        procedureContext4 = StartupProcedure.this.procedureContext;
                        if (procedureContext4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("procedureContext");
                        } else {
                            procedureContext9 = procedureContext4;
                        }
                        String message3 = exception.getMessage();
                        procedureContext9.processStateEvent(new UpdatesStateEvent.CheckError(message3 != null ? message3 : ""));
                    }
                } else {
                    throw new AssertionError("Background update with error status must have a nonnull exception object");
                }
                errorRecovery = StartupProcedure.this.errorRecovery;
                remoteLoadStatus = StartupProcedure.this.remoteLoadStatus;
                errorRecovery.notifyNewRemoteLoadStatus(remoteLoadStatus);
            }
        });
    }

    public final String getLaunchAssetFile() {
        Launcher launcher = this.launcher;
        if (launcher != null) {
            return launcher.getLaunchAssetFile();
        }
        return null;
    }

    public final String getBundleAssetName() {
        Launcher launcher = this.launcher;
        if (launcher != null) {
            return launcher.getBundleAssetName();
        }
        return null;
    }

    public final Map<AssetEntity, String> getLocalAssetFiles() {
        Launcher launcher = this.launcher;
        if (launcher != null) {
            return launcher.mo753getLocalAssetFiles();
        }
        return null;
    }

    public final boolean isUsingEmbeddedAssets() {
        Launcher launcher = this.launcher;
        if (launcher != null) {
            return launcher.getIsUsingEmbeddedAssets();
        }
        return false;
    }

    public final UpdateEntity getLaunchedUpdate() {
        Launcher launcher = this.launcher;
        if (launcher != null) {
            return launcher.getLaunchedUpdate();
        }
        return null;
    }

    private final void initializeDatabaseHandler() {
        if (this.databaseHandler == null) {
            this.databaseHandlerThread.start();
            this.databaseHandler = new Handler(this.databaseHandlerThread.getLooper());
        }
    }

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public void run(StateMachineProcedure.ProcedureContext procedureContext) {
        Intrinsics.checkNotNullParameter(procedureContext, "procedureContext");
        this.procedureContext = procedureContext;
        initializeDatabaseHandler();
        initializeErrorRecovery();
        this.loaderTask.start(this.context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void notifyController() {
        if (this.launcher == null) {
            throw new AssertionError("UpdatesController.notifyController was called with a null launcher, which is an error. This method should only be called when an update is ready to launch.");
        }
        this.callback.onFinished();
    }

    public final void onDidCreateDevSupportManager(DevSupportManager devSupportManager) {
        Intrinsics.checkNotNullParameter(devSupportManager, "devSupportManager");
        if (this.emergencyLaunchException != null) {
            return;
        }
        this.errorRecovery.startMonitoring(devSupportManager);
    }

    public final void onReactInstanceException(Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.errorRecovery.onReactInstanceException$expo_updates_release(exception);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRemoteLoadStatus(ErrorRecoveryDelegate.RemoteLoadStatus status) {
        this.remoteLoadStatus = status;
        this.errorRecovery.notifyNewRemoteLoadStatus(status);
    }

    private final void initializeErrorRecovery() {
        this.errorRecovery.initialize(new StartupProcedure$initializeErrorRecovery$1(this));
    }
}

package expo.modules.updates.procedures;

import android.content.Context;
import android.os.Handler;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.DatabaseHolder;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.errorrecovery.ErrorRecoveryDelegate;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.LoaderTask;
import expo.modules.updates.loader.RemoteLoader;
import expo.modules.updates.loader.UpdateDirective;
import expo.modules.updates.loader.UpdateResponse;
import expo.modules.updates.loader.UpdateResponsePart;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.ResponseHeaderData;
import expo.modules.updates.manifest.Update;
import expo.modules.updates.procedures.StartupProcedure;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StartupProcedure.kt */
@Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0014\u0010\u000f\u001a\u00020\t2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H\u0016¨\u0006\u0013"}, d2 = {"expo/modules/updates/procedures/StartupProcedure$initializeErrorRecovery$1", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate;", "getCheckAutomaticallyConfiguration", "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "getLaunchedUpdateSuccessfulLaunchCount", "", "getRemoteLoadStatus", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "loadRemoteUpdate", "", "markFailedLaunchForLaunchedUpdate", "markSuccessfulLaunchForLaunchedUpdate", "relaunch", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "throwException", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class StartupProcedure$initializeErrorRecovery$1 implements ErrorRecoveryDelegate {
    final /* synthetic */ StartupProcedure this$0;

    StartupProcedure$initializeErrorRecovery$1(StartupProcedure startupProcedure) {
        this.this$0 = startupProcedure;
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void loadRemoteUpdate() {
        LoaderTask loaderTask;
        Context context;
        UpdatesConfiguration updatesConfiguration;
        DatabaseHolder databaseHolder;
        FileDownloader fileDownloader;
        File file;
        loaderTask = this.this$0.loaderTask;
        if (loaderTask.getIsRunning()) {
            return;
        }
        this.this$0.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADING;
        context = this.this$0.context;
        updatesConfiguration = this.this$0.updatesConfiguration;
        databaseHolder = this.this$0.databaseHolder;
        UpdatesDatabase database = databaseHolder.getDatabase();
        fileDownloader = this.this$0.fileDownloader;
        file = this.this$0.updatesDirectory;
        RemoteLoader remoteLoader = new RemoteLoader(context, updatesConfiguration, database, fileDownloader, file, this.this$0.getLaunchedUpdate());
        final StartupProcedure startupProcedure = this.this$0;
        remoteLoader.start(new Loader.LoaderCallback() { // from class: expo.modules.updates.procedures.StartupProcedure$initializeErrorRecovery$1$loadRemoteUpdate$1
            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onAssetLoaded(AssetEntity asset, int successfulAssetCount, int failedAssetCount, int totalAssetCount) {
                Intrinsics.checkNotNullParameter(asset, "asset");
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onFailure(Exception e) {
                UpdatesLogger updatesLogger;
                DatabaseHolder databaseHolder2;
                Intrinsics.checkNotNullParameter(e, "e");
                updatesLogger = StartupProcedure.this.logger;
                String str = "UpdatesController loadRemoteUpdate onFailure: " + e.getLocalizedMessage();
                UpdatesErrorCode updatesErrorCode = UpdatesErrorCode.UpdateFailedToLoad;
                UpdateEntity launchedUpdate = StartupProcedure.this.getLaunchedUpdate();
                UpdatesLogger.error$default(updatesLogger, str, updatesErrorCode, launchedUpdate != null ? launchedUpdate.getLoggingId() : null, null, null, 16, null);
                StartupProcedure.this.setRemoteLoadStatus(ErrorRecoveryDelegate.RemoteLoadStatus.IDLE);
                databaseHolder2 = StartupProcedure.this.databaseHolder;
                databaseHolder2.releaseDatabase();
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onSuccess(Loader.LoaderResult loaderResult) {
                ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
                DatabaseHolder databaseHolder2;
                Intrinsics.checkNotNullParameter(loaderResult, "loaderResult");
                StartupProcedure startupProcedure2 = StartupProcedure.this;
                if (loaderResult.getUpdateEntity() != null || (loaderResult.getUpdateDirective() instanceof UpdateDirective.RollBackToEmbeddedUpdateDirective)) {
                    remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADED;
                } else {
                    remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
                }
                startupProcedure2.setRemoteLoadStatus(remoteLoadStatus);
                databaseHolder2 = StartupProcedure.this.databaseHolder;
                databaseHolder2.releaseDatabase();
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public Loader.OnUpdateResponseLoadedResult onUpdateResponseLoaded(UpdateResponse updateResponse) {
                Update update;
                SelectionPolicy selectionPolicy;
                Intrinsics.checkNotNullParameter(updateResponse, "updateResponse");
                UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart = updateResponse.getDirectiveUpdateResponsePart();
                UpdateDirective updateDirective = directiveUpdateResponsePart != null ? directiveUpdateResponsePart.getUpdateDirective() : null;
                if (updateDirective != null) {
                    if ((updateDirective instanceof UpdateDirective.RollBackToEmbeddedUpdateDirective) || (updateDirective instanceof UpdateDirective.NoUpdateAvailableUpdateDirective)) {
                        return new Loader.OnUpdateResponseLoadedResult(false);
                    }
                    throw new NoWhenBranchMatchedException();
                }
                UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart = updateResponse.getManifestUpdateResponsePart();
                if (manifestUpdateResponsePart != null && (update = manifestUpdateResponsePart.getUpdate()) != null) {
                    selectionPolicy = StartupProcedure.this.selectionPolicy;
                    UpdateEntity updateEntity = update.getUpdateEntity();
                    UpdateEntity launchedUpdate = StartupProcedure.this.getLaunchedUpdate();
                    ResponseHeaderData responseHeaderData = updateResponse.getResponseHeaderData();
                    return new Loader.OnUpdateResponseLoadedResult(selectionPolicy.shouldLoadNewUpdate(updateEntity, launchedUpdate, responseHeaderData != null ? responseHeaderData.getManifestFilters() : null));
                }
                return new Loader.OnUpdateResponseLoadedResult(false);
            }
        });
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void relaunch(Launcher.LauncherCallback callback) {
        StartupProcedure.StartupProcedureCallback startupProcedureCallback;
        Intrinsics.checkNotNullParameter(callback, "callback");
        startupProcedureCallback = this.this$0.callback;
        startupProcedureCallback.onRequestRelaunch(false, callback);
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void throwException(Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        throw exception;
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void markFailedLaunchForLaunchedUpdate() {
        Handler handler;
        if (this.this$0.getEmergencyLaunchException() != null) {
            return;
        }
        handler = this.this$0.databaseHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseHandler");
            handler = null;
        }
        final StartupProcedure startupProcedure = this.this$0;
        handler.post(new Runnable() { // from class: expo.modules.updates.procedures.StartupProcedure$initializeErrorRecovery$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                StartupProcedure$initializeErrorRecovery$1.markFailedLaunchForLaunchedUpdate$lambda$0(StartupProcedure.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void markFailedLaunchForLaunchedUpdate$lambda$0(StartupProcedure this$0) {
        DatabaseHolder databaseHolder;
        DatabaseHolder databaseHolder2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UpdateEntity launchedUpdate = this$0.getLaunchedUpdate();
        if (launchedUpdate == null) {
            return;
        }
        databaseHolder = this$0.databaseHolder;
        databaseHolder.getDatabase().updateDao().incrementFailedLaunchCount(launchedUpdate);
        databaseHolder2 = this$0.databaseHolder;
        databaseHolder2.releaseDatabase();
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void markSuccessfulLaunchForLaunchedUpdate() {
        Handler handler;
        if (this.this$0.getEmergencyLaunchException() != null) {
            return;
        }
        handler = this.this$0.databaseHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseHandler");
            handler = null;
        }
        final StartupProcedure startupProcedure = this.this$0;
        handler.post(new Runnable() { // from class: expo.modules.updates.procedures.StartupProcedure$initializeErrorRecovery$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                StartupProcedure$initializeErrorRecovery$1.markSuccessfulLaunchForLaunchedUpdate$lambda$1(StartupProcedure.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void markSuccessfulLaunchForLaunchedUpdate$lambda$1(StartupProcedure this$0) {
        DatabaseHolder databaseHolder;
        DatabaseHolder databaseHolder2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UpdateEntity launchedUpdate = this$0.getLaunchedUpdate();
        if (launchedUpdate == null) {
            return;
        }
        databaseHolder = this$0.databaseHolder;
        databaseHolder.getDatabase().updateDao().incrementSuccessfulLaunchCount(launchedUpdate);
        databaseHolder2 = this$0.databaseHolder;
        databaseHolder2.releaseDatabase();
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public ErrorRecoveryDelegate.RemoteLoadStatus getRemoteLoadStatus() {
        ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
        remoteLoadStatus = this.this$0.remoteLoadStatus;
        return remoteLoadStatus;
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public UpdatesConfiguration.CheckAutomaticallyConfiguration getCheckAutomaticallyConfiguration() {
        UpdatesConfiguration updatesConfiguration;
        updatesConfiguration = this.this$0.updatesConfiguration;
        return updatesConfiguration.getCheckOnLaunch();
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public int getLaunchedUpdateSuccessfulLaunchCount() {
        UpdateEntity launchedUpdate = this.this$0.getLaunchedUpdate();
        if (launchedUpdate != null) {
            return launchedUpdate.getSuccessfulLaunchCount();
        }
        return 0;
    }
}

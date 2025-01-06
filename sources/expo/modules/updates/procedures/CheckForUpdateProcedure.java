package expo.modules.updates.procedures;

import android.content.Context;
import android.os.AsyncTask;
import expo.modules.updates.IUpdatesController;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.DatabaseHolder;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.LoaderTask;
import expo.modules.updates.loader.UpdateDirective;
import expo.modules.updates.loader.UpdateResponse;
import expo.modules.updates.loader.UpdateResponsePart;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.EmbeddedManifestUtils;
import expo.modules.updates.manifest.EmbeddedUpdate;
import expo.modules.updates.manifest.ResponseHeaderData;
import expo.modules.updates.manifest.Update;
import expo.modules.updates.procedures.StateMachineProcedure;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import expo.modules.updates.statemachine.UpdatesStateEvent;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CheckForUpdateProcedure.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lexpo/modules/updates/procedures/CheckForUpdateProcedure;", "Lexpo/modules/updates/procedures/StateMachineProcedure;", "context", "Landroid/content/Context;", "updatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "updatesLogger", "Lexpo/modules/updates/logging/UpdatesLogger;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "callback", "Lkotlin/Function1;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", "", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/DatabaseHolder;Lexpo/modules/updates/logging/UpdatesLogger;Lexpo/modules/updates/loader/FileDownloader;Lexpo/modules/updates/selectionpolicy/SelectionPolicy;Lexpo/modules/updates/db/entity/UpdateEntity;Lkotlin/jvm/functions/Function1;)V", "loggerTimerLabel", "", "getLoggerTimerLabel", "()Ljava/lang/String;", "run", "procedureContext", "Lexpo/modules/updates/procedures/StateMachineProcedure$ProcedureContext;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CheckForUpdateProcedure extends StateMachineProcedure {
    private final Function1<IUpdatesController.CheckForUpdateResult, Unit> callback;
    private final Context context;
    private final DatabaseHolder databaseHolder;
    private final FileDownloader fileDownloader;
    private final UpdateEntity launchedUpdate;
    private final String loggerTimerLabel;
    private final SelectionPolicy selectionPolicy;
    private final UpdatesConfiguration updatesConfiguration;
    private final UpdatesLogger updatesLogger;

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public String getLoggerTimerLabel() {
        return this.loggerTimerLabel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CheckForUpdateProcedure(Context context, UpdatesConfiguration updatesConfiguration, DatabaseHolder databaseHolder, UpdatesLogger updatesLogger, FileDownloader fileDownloader, SelectionPolicy selectionPolicy, UpdateEntity updateEntity, Function1<? super IUpdatesController.CheckForUpdateResult, Unit> callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(databaseHolder, "databaseHolder");
        Intrinsics.checkNotNullParameter(updatesLogger, "updatesLogger");
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.context = context;
        this.updatesConfiguration = updatesConfiguration;
        this.databaseHolder = databaseHolder;
        this.updatesLogger = updatesLogger;
        this.fileDownloader = fileDownloader;
        this.selectionPolicy = selectionPolicy;
        this.launchedUpdate = updateEntity;
        this.callback = callback;
        this.loggerTimerLabel = "timer-check-for-update";
    }

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public void run(final StateMachineProcedure.ProcedureContext procedureContext) {
        Intrinsics.checkNotNullParameter(procedureContext, "procedureContext");
        procedureContext.processStateEvent(new UpdatesStateEvent.Check());
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.procedures.CheckForUpdateProcedure$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CheckForUpdateProcedure.run$lambda$0(CheckForUpdateProcedure.this, procedureContext);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void run$lambda$0(final CheckForUpdateProcedure this$0, final StateMachineProcedure.ProcedureContext procedureContext) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(procedureContext, "$procedureContext");
        EmbeddedUpdate embeddedUpdate = EmbeddedManifestUtils.INSTANCE.getEmbeddedUpdate(this$0.context, this$0.updatesConfiguration);
        final UpdateEntity updateEntity = embeddedUpdate != null ? embeddedUpdate.getUpdateEntity() : null;
        JSONObject extraHeadersForRemoteUpdateRequest = FileDownloader.INSTANCE.getExtraHeadersForRemoteUpdateRequest(this$0.databaseHolder.getDatabase(), this$0.updatesConfiguration, this$0.launchedUpdate, updateEntity);
        this$0.databaseHolder.releaseDatabase();
        this$0.fileDownloader.downloadRemoteUpdate(extraHeadersForRemoteUpdateRequest, this$0.context, new FileDownloader.RemoteUpdateDownloadCallback() { // from class: expo.modules.updates.procedures.CheckForUpdateProcedure$run$1$1
            @Override // expo.modules.updates.loader.FileDownloader.RemoteUpdateDownloadCallback
            public void onFailure(String message, Exception e) {
                Function1 function1;
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(e, "e");
                StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckError(message));
                function1 = this$0.callback;
                function1.invoke(new IUpdatesController.CheckForUpdateResult.ErrorResult(e, message));
                StateMachineProcedure.ProcedureContext.this.onComplete();
            }

            @Override // expo.modules.updates.loader.FileDownloader.RemoteUpdateDownloadCallback
            public void onSuccess(UpdateResponse updateResponse) {
                UpdateEntity updateEntity2;
                SelectionPolicy selectionPolicy;
                UpdateEntity updateEntity3;
                boolean z;
                LoaderTask.RemoteCheckResultNotAvailableReason remoteCheckResultNotAvailableReason;
                Function1 function1;
                Function1 function12;
                DatabaseHolder databaseHolder;
                DatabaseHolder databaseHolder2;
                UpdatesLogger updatesLogger;
                Function1 function13;
                Function1 function14;
                UpdatesConfiguration updatesConfiguration;
                SelectionPolicy selectionPolicy2;
                UpdateEntity updateEntity4;
                Function1 function15;
                Function1 function16;
                Function1 function17;
                Function1 function18;
                Function1 function19;
                Intrinsics.checkNotNullParameter(updateResponse, "updateResponse");
                UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart = updateResponse.getDirectiveUpdateResponsePart();
                UpdateDirective updateDirective = directiveUpdateResponsePart != null ? directiveUpdateResponsePart.getUpdateDirective() : null;
                UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart = updateResponse.getManifestUpdateResponsePart();
                Update update = manifestUpdateResponsePart != null ? manifestUpdateResponsePart.getUpdate() : null;
                if (updateDirective != null) {
                    if (updateDirective instanceof UpdateDirective.NoUpdateAvailableUpdateDirective) {
                        function19 = this$0.callback;
                        function19.invoke(new IUpdatesController.CheckForUpdateResult.NoUpdateAvailable(LoaderTask.RemoteCheckResultNotAvailableReason.NO_UPDATE_AVAILABLE_ON_SERVER));
                        StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckCompleteUnavailable());
                        StateMachineProcedure.ProcedureContext.this.onComplete();
                        return;
                    }
                    if (updateDirective instanceof UpdateDirective.RollBackToEmbeddedUpdateDirective) {
                        updatesConfiguration = this$0.updatesConfiguration;
                        if (!updatesConfiguration.getHasEmbeddedUpdate()) {
                            function18 = this$0.callback;
                            function18.invoke(new IUpdatesController.CheckForUpdateResult.NoUpdateAvailable(LoaderTask.RemoteCheckResultNotAvailableReason.ROLLBACK_NO_EMBEDDED));
                            StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckCompleteUnavailable());
                            StateMachineProcedure.ProcedureContext.this.onComplete();
                            return;
                        }
                        if (updateEntity == null) {
                            function17 = this$0.callback;
                            function17.invoke(new IUpdatesController.CheckForUpdateResult.NoUpdateAvailable(LoaderTask.RemoteCheckResultNotAvailableReason.ROLLBACK_NO_EMBEDDED));
                            StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckCompleteUnavailable());
                            StateMachineProcedure.ProcedureContext.this.onComplete();
                            return;
                        }
                        selectionPolicy2 = this$0.selectionPolicy;
                        UpdateDirective.RollBackToEmbeddedUpdateDirective rollBackToEmbeddedUpdateDirective = (UpdateDirective.RollBackToEmbeddedUpdateDirective) updateDirective;
                        UpdateEntity updateEntity5 = updateEntity;
                        updateEntity4 = this$0.launchedUpdate;
                        ResponseHeaderData responseHeaderData = updateResponse.getResponseHeaderData();
                        if (!selectionPolicy2.shouldLoadRollBackToEmbeddedDirective(rollBackToEmbeddedUpdateDirective, updateEntity5, updateEntity4, responseHeaderData != null ? responseHeaderData.getManifestFilters() : null)) {
                            function16 = this$0.callback;
                            function16.invoke(new IUpdatesController.CheckForUpdateResult.NoUpdateAvailable(LoaderTask.RemoteCheckResultNotAvailableReason.ROLLBACK_REJECTED_BY_SELECTION_POLICY));
                            StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckCompleteUnavailable());
                            StateMachineProcedure.ProcedureContext.this.onComplete();
                            return;
                        }
                        function15 = this$0.callback;
                        function15.invoke(new IUpdatesController.CheckForUpdateResult.RollBackToEmbedded(rollBackToEmbeddedUpdateDirective.getCommitTime()));
                        StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckCompleteWithRollback(rollBackToEmbeddedUpdateDirective.getCommitTime()));
                        StateMachineProcedure.ProcedureContext.this.onComplete();
                        return;
                    }
                    throw new NoWhenBranchMatchedException();
                }
                if (update == null) {
                    function14 = this$0.callback;
                    function14.invoke(new IUpdatesController.CheckForUpdateResult.NoUpdateAvailable(LoaderTask.RemoteCheckResultNotAvailableReason.NO_UPDATE_AVAILABLE_ON_SERVER));
                    StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckCompleteUnavailable());
                    StateMachineProcedure.ProcedureContext.this.onComplete();
                    return;
                }
                updateEntity2 = this$0.launchedUpdate;
                if (updateEntity2 == null) {
                    function13 = this$0.callback;
                    function13.invoke(new IUpdatesController.CheckForUpdateResult.UpdateAvailable(update));
                    StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckCompleteWithUpdate(update.getManifest().getRawJson()));
                    StateMachineProcedure.ProcedureContext.this.onComplete();
                    return;
                }
                selectionPolicy = this$0.selectionPolicy;
                UpdateEntity updateEntity6 = update.getUpdateEntity();
                updateEntity3 = this$0.launchedUpdate;
                ResponseHeaderData responseHeaderData2 = updateResponse.getResponseHeaderData();
                boolean shouldLoadNewUpdate = selectionPolicy.shouldLoadNewUpdate(updateEntity6, updateEntity3, responseHeaderData2 != null ? responseHeaderData2.getManifestFilters() : null);
                if (shouldLoadNewUpdate) {
                    UpdateEntity updateEntity7 = update.getUpdateEntity();
                    if (updateEntity7 != null) {
                        CheckForUpdateProcedure checkForUpdateProcedure = this$0;
                        databaseHolder = checkForUpdateProcedure.databaseHolder;
                        UpdateEntity loadUpdateWithId = databaseHolder.getDatabase().updateDao().loadUpdateWithId(updateEntity7.getId());
                        databaseHolder2 = checkForUpdateProcedure.databaseHolder;
                        databaseHolder2.releaseDatabase();
                        if (loadUpdateWithId != null) {
                            r0 = loadUpdateWithId.getFailedLaunchCount() == 0;
                            updatesLogger = checkForUpdateProcedure.updatesLogger;
                            UpdatesLogger.info$default(updatesLogger, "Stored update found: ID = " + updateEntity7.getId() + ", failureCount = " + loadUpdateWithId.getFailedLaunchCount(), null, 2, null);
                            z = r0 ^ true;
                        }
                    }
                    z = false;
                    r0 = true;
                } else {
                    z = false;
                }
                if (r0) {
                    function12 = this$0.callback;
                    function12.invoke(new IUpdatesController.CheckForUpdateResult.UpdateAvailable(update));
                    StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckCompleteWithUpdate(update.getManifest().getRawJson()));
                    StateMachineProcedure.ProcedureContext.this.onComplete();
                    return;
                }
                if (z) {
                    remoteCheckResultNotAvailableReason = LoaderTask.RemoteCheckResultNotAvailableReason.UPDATE_PREVIOUSLY_FAILED;
                } else {
                    remoteCheckResultNotAvailableReason = LoaderTask.RemoteCheckResultNotAvailableReason.UPDATE_REJECTED_BY_SELECTION_POLICY;
                }
                function1 = this$0.callback;
                function1.invoke(new IUpdatesController.CheckForUpdateResult.NoUpdateAvailable(remoteCheckResultNotAvailableReason));
                StateMachineProcedure.ProcedureContext.this.processStateEvent(new UpdatesStateEvent.CheckCompleteUnavailable());
                StateMachineProcedure.ProcedureContext.this.onComplete();
            }
        });
    }
}

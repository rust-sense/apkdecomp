package expo.modules.updates.procedures;

import android.content.Context;
import android.os.AsyncTask;
import expo.modules.updates.IUpdatesController;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.DatabaseHolder;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.RemoteLoader;
import expo.modules.updates.loader.UpdateDirective;
import expo.modules.updates.loader.UpdateResponse;
import expo.modules.updates.loader.UpdateResponsePart;
import expo.modules.updates.manifest.ResponseHeaderData;
import expo.modules.updates.manifest.Update;
import expo.modules.updates.procedures.StateMachineProcedure;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import expo.modules.updates.statemachine.UpdatesStateEvent;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FetchUpdateProcedure.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lexpo/modules/updates/procedures/FetchUpdateProcedure;", "Lexpo/modules/updates/procedures/StateMachineProcedure;", "context", "Landroid/content/Context;", "updatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "updatesDirectory", "Ljava/io/File;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "callback", "Lkotlin/Function1;", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", "", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/DatabaseHolder;Ljava/io/File;Lexpo/modules/updates/loader/FileDownloader;Lexpo/modules/updates/selectionpolicy/SelectionPolicy;Lexpo/modules/updates/db/entity/UpdateEntity;Lkotlin/jvm/functions/Function1;)V", "loggerTimerLabel", "", "getLoggerTimerLabel", "()Ljava/lang/String;", "run", "procedureContext", "Lexpo/modules/updates/procedures/StateMachineProcedure$ProcedureContext;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FetchUpdateProcedure extends StateMachineProcedure {
    private final Function1<IUpdatesController.FetchUpdateResult, Unit> callback;
    private final Context context;
    private final DatabaseHolder databaseHolder;
    private final FileDownloader fileDownloader;
    private final UpdateEntity launchedUpdate;
    private final String loggerTimerLabel;
    private final SelectionPolicy selectionPolicy;
    private final UpdatesConfiguration updatesConfiguration;
    private final File updatesDirectory;

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public String getLoggerTimerLabel() {
        return this.loggerTimerLabel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FetchUpdateProcedure(Context context, UpdatesConfiguration updatesConfiguration, DatabaseHolder databaseHolder, File updatesDirectory, FileDownloader fileDownloader, SelectionPolicy selectionPolicy, UpdateEntity updateEntity, Function1<? super IUpdatesController.FetchUpdateResult, Unit> callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(databaseHolder, "databaseHolder");
        Intrinsics.checkNotNullParameter(updatesDirectory, "updatesDirectory");
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.context = context;
        this.updatesConfiguration = updatesConfiguration;
        this.databaseHolder = databaseHolder;
        this.updatesDirectory = updatesDirectory;
        this.fileDownloader = fileDownloader;
        this.selectionPolicy = selectionPolicy;
        this.launchedUpdate = updateEntity;
        this.callback = callback;
        this.loggerTimerLabel = "timer-fetch-update";
    }

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public void run(final StateMachineProcedure.ProcedureContext procedureContext) {
        Intrinsics.checkNotNullParameter(procedureContext, "procedureContext");
        procedureContext.processStateEvent(new UpdatesStateEvent.Download());
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.procedures.FetchUpdateProcedure$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FetchUpdateProcedure.run$lambda$0(FetchUpdateProcedure.this, procedureContext);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void run$lambda$0(final FetchUpdateProcedure this$0, final StateMachineProcedure.ProcedureContext procedureContext) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(procedureContext, "$procedureContext");
        final UpdatesDatabase database = this$0.databaseHolder.getDatabase();
        new RemoteLoader(this$0.context, this$0.updatesConfiguration, database, this$0.fileDownloader, this$0.updatesDirectory, this$0.launchedUpdate).start(new Loader.LoaderCallback() { // from class: expo.modules.updates.procedures.FetchUpdateProcedure$run$1$1
            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onAssetLoaded(AssetEntity asset, int successfulAssetCount, int failedAssetCount, int totalAssetCount) {
                Intrinsics.checkNotNullParameter(asset, "asset");
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onFailure(Exception e) {
                DatabaseHolder databaseHolder;
                Function1 function1;
                Intrinsics.checkNotNullParameter(e, "e");
                databaseHolder = FetchUpdateProcedure.this.databaseHolder;
                databaseHolder.releaseDatabase();
                function1 = FetchUpdateProcedure.this.callback;
                function1.invoke(new IUpdatesController.FetchUpdateResult.ErrorResult(e));
                procedureContext.processStateEvent(new UpdatesStateEvent.DownloadError("Failed to download new update: " + e.getMessage()));
                procedureContext.onComplete();
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public Loader.OnUpdateResponseLoadedResult onUpdateResponseLoaded(UpdateResponse updateResponse) {
                Update update;
                SelectionPolicy selectionPolicy;
                UpdateEntity updateEntity;
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
                    selectionPolicy = FetchUpdateProcedure.this.selectionPolicy;
                    UpdateEntity updateEntity2 = update.getUpdateEntity();
                    updateEntity = FetchUpdateProcedure.this.launchedUpdate;
                    ResponseHeaderData responseHeaderData = updateResponse.getResponseHeaderData();
                    return new Loader.OnUpdateResponseLoadedResult(selectionPolicy.shouldLoadNewUpdate(updateEntity2, updateEntity, responseHeaderData != null ? responseHeaderData.getManifestFilters() : null));
                }
                return new Loader.OnUpdateResponseLoadedResult(false);
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onSuccess(Loader.LoaderResult loaderResult) {
                Context context;
                UpdatesConfiguration updatesConfiguration;
                SelectionPolicy selectionPolicy;
                File file;
                UpdateEntity updateEntity;
                Intrinsics.checkNotNullParameter(loaderResult, "loaderResult");
                RemoteLoader.Companion companion = RemoteLoader.INSTANCE;
                context = FetchUpdateProcedure.this.context;
                updatesConfiguration = FetchUpdateProcedure.this.updatesConfiguration;
                UpdatesDatabase updatesDatabase = database;
                selectionPolicy = FetchUpdateProcedure.this.selectionPolicy;
                file = FetchUpdateProcedure.this.updatesDirectory;
                updateEntity = FetchUpdateProcedure.this.launchedUpdate;
                final FetchUpdateProcedure fetchUpdateProcedure = FetchUpdateProcedure.this;
                final StateMachineProcedure.ProcedureContext procedureContext2 = procedureContext;
                companion.processSuccessLoaderResult(context, updatesConfiguration, updatesDatabase, selectionPolicy, file, updateEntity, loaderResult, new Function2<UpdateEntity, Boolean, Unit>() { // from class: expo.modules.updates.procedures.FetchUpdateProcedure$run$1$1$onSuccess$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(UpdateEntity updateEntity2, Boolean bool) {
                        invoke(updateEntity2, bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(UpdateEntity updateEntity2, boolean z) {
                        DatabaseHolder databaseHolder;
                        Function1 function1;
                        Function1 function12;
                        Function1 function13;
                        databaseHolder = FetchUpdateProcedure.this.databaseHolder;
                        databaseHolder.releaseDatabase();
                        if (z) {
                            function13 = FetchUpdateProcedure.this.callback;
                            function13.invoke(new IUpdatesController.FetchUpdateResult.RollBackToEmbedded());
                            procedureContext2.processStateEvent(new UpdatesStateEvent.DownloadCompleteWithRollback());
                            procedureContext2.onComplete();
                            return;
                        }
                        if (updateEntity2 == null) {
                            function12 = FetchUpdateProcedure.this.callback;
                            function12.invoke(new IUpdatesController.FetchUpdateResult.Failure());
                            procedureContext2.processStateEvent(new UpdatesStateEvent.DownloadComplete());
                            procedureContext2.onComplete();
                            return;
                        }
                        function1 = FetchUpdateProcedure.this.callback;
                        function1.invoke(new IUpdatesController.FetchUpdateResult.Success(updateEntity2));
                        procedureContext2.processStateEvent(new UpdatesStateEvent.DownloadCompleteWithUpdate(updateEntity2.getManifest()));
                        procedureContext2.onComplete();
                    }
                });
            }
        });
    }
}

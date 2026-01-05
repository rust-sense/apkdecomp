package expo.modules.updates.loader;

import android.content.Context;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.DatabaseHolder;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.launcher.DatabaseLauncher;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.LoaderTask;
import expo.modules.updates.loader.RemoteLoader;
import expo.modules.updates.loader.UpdateDirective;
import expo.modules.updates.loader.UpdateResponsePart;
import expo.modules.updates.manifest.ResponseHeaderData;
import expo.modules.updates.manifest.Update;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import java.io.File;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoaderTask.kt */
@Metadata(d1 = {"\u0000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J\u0014\u0010\r\u001a\u00020\u00032\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¨\u0006\u0018"}, d2 = {"expo/modules/updates/loader/LoaderTask$launchRemoteUpdateInBackground$1$1", "Lexpo/modules/updates/loader/Loader$LoaderCallback;", "launchUpdate", "", "availableUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "onAssetLoaded", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "successfulAssetCount", "", "failedAssetCount", "totalAssetCount", "onFailure", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "loaderResult", "Lexpo/modules/updates/loader/Loader$LoaderResult;", "onUpdateResponseLoaded", "Lexpo/modules/updates/loader/Loader$OnUpdateResponseLoadedResult;", "updateResponse", "Lexpo/modules/updates/loader/UpdateResponse;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LoaderTask$launchRemoteUpdateInBackground$1$1 implements Loader.LoaderCallback {
    final /* synthetic */ Context $context;
    final /* synthetic */ UpdatesDatabase $database;
    final /* synthetic */ LoaderTask.LaunchUpdateCallback $remoteUpdateCallback;
    final /* synthetic */ LoaderTask this$0;

    LoaderTask$launchRemoteUpdateInBackground$1$1(LoaderTask loaderTask, LoaderTask.LaunchUpdateCallback launchUpdateCallback, Context context, UpdatesDatabase updatesDatabase) {
        this.this$0 = loaderTask;
        this.$remoteUpdateCallback = launchUpdateCallback;
        this.$context = context;
        this.$database = updatesDatabase;
    }

    @Override // expo.modules.updates.loader.Loader.LoaderCallback
    public void onFailure(Exception e) {
        DatabaseHolder databaseHolder;
        LoaderTask.LoaderTaskCallback loaderTaskCallback;
        String str;
        Intrinsics.checkNotNullParameter(e, "e");
        databaseHolder = this.this$0.databaseHolder;
        databaseHolder.releaseDatabase();
        loaderTaskCallback = this.this$0.callback;
        loaderTaskCallback.onRemoteUpdateFinished(LoaderTask.RemoteUpdateStatus.ERROR, null, e);
        str = LoaderTask.TAG;
        Log.e(str, "Failed to download remote update", e);
        this.$remoteUpdateCallback.onFailure(e);
    }

    @Override // expo.modules.updates.loader.Loader.LoaderCallback
    public void onAssetLoaded(AssetEntity asset, int successfulAssetCount, int failedAssetCount, int totalAssetCount) {
        LoaderTask.LoaderTaskCallback loaderTaskCallback;
        Intrinsics.checkNotNullParameter(asset, "asset");
        loaderTaskCallback = this.this$0.callback;
        loaderTaskCallback.onRemoteUpdateAssetLoaded(asset, successfulAssetCount, failedAssetCount, totalAssetCount);
    }

    @Override // expo.modules.updates.loader.Loader.LoaderCallback
    public Loader.OnUpdateResponseLoadedResult onUpdateResponseLoaded(UpdateResponse updateResponse) {
        SelectionPolicy selectionPolicy;
        Launcher launcher;
        LoaderTask.LoaderTaskCallback loaderTaskCallback;
        LoaderTask.LoaderTaskCallback loaderTaskCallback2;
        LoaderTask.LoaderTaskCallback loaderTaskCallback3;
        LoaderTask.LoaderTaskCallback loaderTaskCallback4;
        LoaderTask.LoaderTaskCallback loaderTaskCallback5;
        LoaderTask.LoaderTaskCallback loaderTaskCallback6;
        LoaderTask.LoaderTaskCallback loaderTaskCallback7;
        Intrinsics.checkNotNullParameter(updateResponse, "updateResponse");
        UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart = updateResponse.getDirectiveUpdateResponsePart();
        UpdateDirective updateDirective = directiveUpdateResponsePart != null ? directiveUpdateResponsePart.getUpdateDirective() : null;
        if (updateDirective != null) {
            if (updateDirective instanceof UpdateDirective.RollBackToEmbeddedUpdateDirective) {
                this.this$0.isUpToDate = true;
                loaderTaskCallback7 = this.this$0.callback;
                loaderTaskCallback7.onRemoteCheckForUpdateFinished(new LoaderTask.RemoteCheckResult.RollBackToEmbedded(((UpdateDirective.RollBackToEmbeddedUpdateDirective) updateDirective).getCommitTime()));
                return new Loader.OnUpdateResponseLoadedResult(false);
            }
            if (!(updateDirective instanceof UpdateDirective.NoUpdateAvailableUpdateDirective)) {
                throw new NoWhenBranchMatchedException();
            }
            this.this$0.isUpToDate = true;
            loaderTaskCallback6 = this.this$0.callback;
            loaderTaskCallback6.onRemoteCheckForUpdateFinished(new LoaderTask.RemoteCheckResult.NoUpdateAvailable(LoaderTask.RemoteCheckResultNotAvailableReason.NO_UPDATE_AVAILABLE_ON_SERVER));
            return new Loader.OnUpdateResponseLoadedResult(false);
        }
        UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart = updateResponse.getManifestUpdateResponsePart();
        Update update = manifestUpdateResponsePart != null ? manifestUpdateResponsePart.getUpdate() : null;
        if (update == null) {
            this.this$0.isUpToDate = true;
            loaderTaskCallback5 = this.this$0.callback;
            loaderTaskCallback5.onRemoteCheckForUpdateFinished(new LoaderTask.RemoteCheckResult.NoUpdateAvailable(LoaderTask.RemoteCheckResultNotAvailableReason.NO_UPDATE_AVAILABLE_ON_SERVER));
            return new Loader.OnUpdateResponseLoadedResult(false);
        }
        selectionPolicy = this.this$0.selectionPolicy;
        UpdateEntity updateEntity = update.getUpdateEntity();
        launcher = this.this$0.candidateLauncher;
        UpdateEntity launchedUpdate = launcher != null ? launcher.getLaunchedUpdate() : null;
        ResponseHeaderData responseHeaderData = updateResponse.getResponseHeaderData();
        if (selectionPolicy.shouldLoadNewUpdate(updateEntity, launchedUpdate, responseHeaderData != null ? responseHeaderData.getManifestFilters() : null)) {
            this.this$0.isUpToDate = false;
            loaderTaskCallback2 = this.this$0.callback;
            loaderTaskCallback2.onRemoteUpdateManifestResponseUpdateLoaded(update);
            loaderTaskCallback3 = this.this$0.callback;
            loaderTaskCallback3.onRemoteCheckForUpdateFinished(new LoaderTask.RemoteCheckResult.UpdateAvailable(update.getManifest().getRawJson()));
            loaderTaskCallback4 = this.this$0.callback;
            loaderTaskCallback4.onRemoteUpdateLoadStarted();
            return new Loader.OnUpdateResponseLoadedResult(true);
        }
        this.this$0.isUpToDate = true;
        loaderTaskCallback = this.this$0.callback;
        loaderTaskCallback.onRemoteCheckForUpdateFinished(new LoaderTask.RemoteCheckResult.NoUpdateAvailable(LoaderTask.RemoteCheckResultNotAvailableReason.UPDATE_REJECTED_BY_SELECTION_POLICY));
        return new Loader.OnUpdateResponseLoadedResult(false);
    }

    @Override // expo.modules.updates.loader.Loader.LoaderCallback
    public void onSuccess(Loader.LoaderResult loaderResult) {
        UpdatesConfiguration updatesConfiguration;
        SelectionPolicy selectionPolicy;
        File file;
        Launcher launcher;
        Intrinsics.checkNotNullParameter(loaderResult, "loaderResult");
        RemoteLoader.Companion companion = RemoteLoader.INSTANCE;
        Context context = this.$context;
        updatesConfiguration = this.this$0.configuration;
        UpdatesDatabase updatesDatabase = this.$database;
        selectionPolicy = this.this$0.selectionPolicy;
        file = this.this$0.directory;
        launcher = this.this$0.candidateLauncher;
        companion.processSuccessLoaderResult(context, updatesConfiguration, updatesDatabase, selectionPolicy, file, launcher != null ? launcher.getLaunchedUpdate() : null, loaderResult, new Function2<UpdateEntity, Boolean, Unit>() { // from class: expo.modules.updates.loader.LoaderTask$launchRemoteUpdateInBackground$1$1$onSuccess$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(UpdateEntity updateEntity, Boolean bool) {
                invoke(updateEntity, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(UpdateEntity updateEntity, boolean z) {
                LoaderTask$launchRemoteUpdateInBackground$1$1.this.launchUpdate(updateEntity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchUpdate(final UpdateEntity availableUpdate) {
        UpdatesConfiguration updatesConfiguration;
        File file;
        FileDownloader fileDownloader;
        SelectionPolicy selectionPolicy;
        updatesConfiguration = this.this$0.configuration;
        file = this.this$0.directory;
        fileDownloader = this.this$0.fileDownloader;
        selectionPolicy = this.this$0.selectionPolicy;
        final DatabaseLauncher databaseLauncher = new DatabaseLauncher(updatesConfiguration, file, fileDownloader, selectionPolicy);
        UpdatesDatabase updatesDatabase = this.$database;
        Context context = this.$context;
        final LoaderTask loaderTask = this.this$0;
        final LoaderTask.LaunchUpdateCallback launchUpdateCallback = this.$remoteUpdateCallback;
        databaseLauncher.launch(updatesDatabase, context, new Launcher.LauncherCallback() { // from class: expo.modules.updates.loader.LoaderTask$launchRemoteUpdateInBackground$1$1$launchUpdate$1
            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onFailure(Exception e) {
                DatabaseHolder databaseHolder;
                String str;
                Intrinsics.checkNotNullParameter(e, "e");
                databaseHolder = LoaderTask.this.databaseHolder;
                databaseHolder.releaseDatabase();
                launchUpdateCallback.onFailure(e);
                str = LoaderTask.TAG;
                Log.e(str, "Loaded new update but it failed to launch", e);
            }

            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onSuccess() {
                DatabaseHolder databaseHolder;
                boolean z;
                boolean z2;
                LoaderTask.LoaderTaskCallback loaderTaskCallback;
                LoaderTask.LoaderTaskCallback loaderTaskCallback2;
                databaseHolder = LoaderTask.this.databaseHolder;
                databaseHolder.releaseDatabase();
                LoaderTask loaderTask2 = LoaderTask.this;
                DatabaseLauncher databaseLauncher2 = databaseLauncher;
                synchronized (loaderTask2) {
                    z = loaderTask2.hasLaunched;
                    if (!z) {
                        loaderTask2.candidateLauncher = databaseLauncher2;
                        loaderTask2.isUpToDate = true;
                    }
                    z2 = loaderTask2.hasLaunched;
                }
                if (z2) {
                    if (availableUpdate == null) {
                        loaderTaskCallback2 = LoaderTask.this.callback;
                        loaderTaskCallback2.onRemoteUpdateFinished(LoaderTask.RemoteUpdateStatus.NO_UPDATE_AVAILABLE, null, null);
                    } else {
                        loaderTaskCallback = LoaderTask.this.callback;
                        loaderTaskCallback.onRemoteUpdateFinished(LoaderTask.RemoteUpdateStatus.UPDATE_AVAILABLE, availableUpdate, null);
                    }
                }
                launchUpdateCallback.onSuccess();
            }
        });
    }
}

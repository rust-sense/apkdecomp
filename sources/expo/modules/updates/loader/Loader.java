package expo.modules.updates.loader;

import android.content.Context;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.dao.AssetDao;
import expo.modules.updates.db.dao.UpdateDao;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.db.enums.UpdateStatus;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.UpdateResponsePart;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.manifest.ResponseHeaderData;
import expo.modules.updates.manifest.Update;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Loader.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 32\u00020\u0001:\u000523456B/\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0016\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u001dH\u0002J\u001c\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\n\u0010!\u001a\u00060\"j\u0002`#H\u0002J\b\u0010$\u001a\u00020\u001bH\u0002J\u0018\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020(H\u0002J2\u0010)\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u00132\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020*H$J(\u0010+\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020,H$J\u0010\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020\u001bH\u0002J\u000e\u00101\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lexpo/modules/updates/loader/Loader;", "", "context", "Landroid/content/Context;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "updatesDirectory", "Ljava/io/File;", "loaderFiles", "Lexpo/modules/updates/loader/LoaderFiles;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/UpdatesDatabase;Ljava/io/File;Lexpo/modules/updates/loader/LoaderFiles;)V", "assetTotal", "", "callback", "Lexpo/modules/updates/loader/Loader$LoaderCallback;", "erroredAssetList", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "existingAssetList", "finishedAssetList", "updateEntity", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updateResponse", "Lexpo/modules/updates/loader/UpdateResponse;", "downloadAllAssets", "", "assetList", "", "finishWithError", "message", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "finishWithSuccess", "handleAssetDownloadCompleted", "assetEntity", "result", "Lexpo/modules/updates/loader/Loader$AssetLoadResult;", "loadAsset", "Lexpo/modules/updates/loader/FileDownloader$AssetDownloadCallback;", "loadRemoteUpdate", "Lexpo/modules/updates/loader/FileDownloader$RemoteUpdateDownloadCallback;", "processUpdate", "update", "Lexpo/modules/updates/manifest/Update;", "reset", ViewProps.START, "AssetLoadResult", "Companion", "LoaderCallback", "LoaderResult", "OnUpdateResponseLoadedResult", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class Loader {
    private static final String TAG = "Loader";
    private int assetTotal;
    private LoaderCallback callback;
    private final UpdatesConfiguration configuration;
    private final Context context;
    private final UpdatesDatabase database;
    private List<AssetEntity> erroredAssetList;
    private List<AssetEntity> existingAssetList;
    private List<AssetEntity> finishedAssetList;
    private final LoaderFiles loaderFiles;
    private UpdateEntity updateEntity;
    private UpdateResponse updateResponse;
    private final File updatesDirectory;

    /* compiled from: Loader.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&J\u0014\u0010\n\u001a\u00020\u00032\n\u0010\u000b\u001a\u00060\fj\u0002`\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H&¨\u0006\u0015"}, d2 = {"Lexpo/modules/updates/loader/Loader$LoaderCallback;", "", "onAssetLoaded", "", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "successfulAssetCount", "", "failedAssetCount", "totalAssetCount", "onFailure", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "loaderResult", "Lexpo/modules/updates/loader/Loader$LoaderResult;", "onUpdateResponseLoaded", "Lexpo/modules/updates/loader/Loader$OnUpdateResponseLoadedResult;", "updateResponse", "Lexpo/modules/updates/loader/UpdateResponse;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface LoaderCallback {
        void onAssetLoaded(AssetEntity asset, int successfulAssetCount, int failedAssetCount, int totalAssetCount);

        void onFailure(Exception e);

        void onSuccess(LoaderResult loaderResult);

        OnUpdateResponseLoadedResult onUpdateResponseLoaded(UpdateResponse updateResponse);
    }

    /* compiled from: Loader.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AssetLoadResult.values().length];
            try {
                iArr[AssetLoadResult.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AssetLoadResult.ALREADY_EXISTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AssetLoadResult.ERRORED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    protected abstract void loadAsset(Context context, AssetEntity assetEntity, File updatesDirectory, UpdatesConfiguration configuration, FileDownloader.AssetDownloadCallback callback);

    protected abstract void loadRemoteUpdate(Context context, UpdatesDatabase database, UpdatesConfiguration configuration, FileDownloader.RemoteUpdateDownloadCallback callback);

    protected Loader(Context context, UpdatesConfiguration configuration, UpdatesDatabase database, File updatesDirectory, LoaderFiles loaderFiles) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(updatesDirectory, "updatesDirectory");
        Intrinsics.checkNotNullParameter(loaderFiles, "loaderFiles");
        this.context = context;
        this.configuration = configuration;
        this.database = database;
        this.updatesDirectory = updatesDirectory;
        this.loaderFiles = loaderFiles;
        this.erroredAssetList = new ArrayList();
        this.existingAssetList = new ArrayList();
        this.finishedAssetList = new ArrayList();
    }

    /* compiled from: Loader.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lexpo/modules/updates/loader/Loader$LoaderResult;", "", "updateEntity", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updateDirective", "Lexpo/modules/updates/loader/UpdateDirective;", "(Lexpo/modules/updates/db/entity/UpdateEntity;Lexpo/modules/updates/loader/UpdateDirective;)V", "getUpdateDirective", "()Lexpo/modules/updates/loader/UpdateDirective;", "getUpdateEntity", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class LoaderResult {
        private final UpdateDirective updateDirective;
        private final UpdateEntity updateEntity;

        public static /* synthetic */ LoaderResult copy$default(LoaderResult loaderResult, UpdateEntity updateEntity, UpdateDirective updateDirective, int i, Object obj) {
            if ((i & 1) != 0) {
                updateEntity = loaderResult.updateEntity;
            }
            if ((i & 2) != 0) {
                updateDirective = loaderResult.updateDirective;
            }
            return loaderResult.copy(updateEntity, updateDirective);
        }

        /* renamed from: component1, reason: from getter */
        public final UpdateEntity getUpdateEntity() {
            return this.updateEntity;
        }

        /* renamed from: component2, reason: from getter */
        public final UpdateDirective getUpdateDirective() {
            return this.updateDirective;
        }

        public final LoaderResult copy(UpdateEntity updateEntity, UpdateDirective updateDirective) {
            return new LoaderResult(updateEntity, updateDirective);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LoaderResult)) {
                return false;
            }
            LoaderResult loaderResult = (LoaderResult) other;
            return Intrinsics.areEqual(this.updateEntity, loaderResult.updateEntity) && Intrinsics.areEqual(this.updateDirective, loaderResult.updateDirective);
        }

        public final UpdateDirective getUpdateDirective() {
            return this.updateDirective;
        }

        public final UpdateEntity getUpdateEntity() {
            return this.updateEntity;
        }

        public int hashCode() {
            UpdateEntity updateEntity = this.updateEntity;
            int hashCode = (updateEntity == null ? 0 : updateEntity.hashCode()) * 31;
            UpdateDirective updateDirective = this.updateDirective;
            return hashCode + (updateDirective != null ? updateDirective.hashCode() : 0);
        }

        public String toString() {
            return "LoaderResult(updateEntity=" + this.updateEntity + ", updateDirective=" + this.updateDirective + ")";
        }

        public LoaderResult(UpdateEntity updateEntity, UpdateDirective updateDirective) {
            this.updateEntity = updateEntity;
            this.updateDirective = updateDirective;
        }
    }

    /* compiled from: Loader.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lexpo/modules/updates/loader/Loader$OnUpdateResponseLoadedResult;", "", "shouldDownloadManifestIfPresentInResponse", "", "(Z)V", "getShouldDownloadManifestIfPresentInResponse", "()Z", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class OnUpdateResponseLoadedResult {
        private final boolean shouldDownloadManifestIfPresentInResponse;

        public static /* synthetic */ OnUpdateResponseLoadedResult copy$default(OnUpdateResponseLoadedResult onUpdateResponseLoadedResult, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = onUpdateResponseLoadedResult.shouldDownloadManifestIfPresentInResponse;
            }
            return onUpdateResponseLoadedResult.copy(z);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getShouldDownloadManifestIfPresentInResponse() {
            return this.shouldDownloadManifestIfPresentInResponse;
        }

        public final OnUpdateResponseLoadedResult copy(boolean shouldDownloadManifestIfPresentInResponse) {
            return new OnUpdateResponseLoadedResult(shouldDownloadManifestIfPresentInResponse);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof OnUpdateResponseLoadedResult) && this.shouldDownloadManifestIfPresentInResponse == ((OnUpdateResponseLoadedResult) other).shouldDownloadManifestIfPresentInResponse;
        }

        public final boolean getShouldDownloadManifestIfPresentInResponse() {
            return this.shouldDownloadManifestIfPresentInResponse;
        }

        public int hashCode() {
            return SourceMap$$ExternalSyntheticBackport0.m(this.shouldDownloadManifestIfPresentInResponse);
        }

        public String toString() {
            return "OnUpdateResponseLoadedResult(shouldDownloadManifestIfPresentInResponse=" + this.shouldDownloadManifestIfPresentInResponse + ")";
        }

        public OnUpdateResponseLoadedResult(boolean z) {
            this.shouldDownloadManifestIfPresentInResponse = z;
        }
    }

    public final void start(LoaderCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.callback != null) {
            callback.onFailure(new Exception("RemoteLoader has already started. Create a new instance in order to load multiple URLs in parallel."));
        } else {
            this.callback = callback;
            loadRemoteUpdate(this.context, this.database, this.configuration, new FileDownloader.RemoteUpdateDownloadCallback() { // from class: expo.modules.updates.loader.Loader$start$1
                @Override // expo.modules.updates.loader.FileDownloader.RemoteUpdateDownloadCallback
                public void onFailure(String message, Exception e) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    Intrinsics.checkNotNullParameter(e, "e");
                    Loader.this.finishWithError(message, e);
                }

                @Override // expo.modules.updates.loader.FileDownloader.RemoteUpdateDownloadCallback
                public void onSuccess(UpdateResponse updateResponse) {
                    Loader.LoaderCallback loaderCallback;
                    Intrinsics.checkNotNullParameter(updateResponse, "updateResponse");
                    Loader.this.updateResponse = updateResponse;
                    UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart = updateResponse.getManifestUpdateResponsePart();
                    Update update = manifestUpdateResponsePart != null ? manifestUpdateResponsePart.getUpdate() : null;
                    loaderCallback = Loader.this.callback;
                    Intrinsics.checkNotNull(loaderCallback);
                    Loader.OnUpdateResponseLoadedResult onUpdateResponseLoaded = loaderCallback.onUpdateResponseLoaded(updateResponse);
                    if (update == null || !onUpdateResponseLoaded.getShouldDownloadManifestIfPresentInResponse()) {
                        Loader.this.updateEntity = null;
                        Loader.this.finishWithSuccess();
                    } else {
                        Loader.this.processUpdate(update);
                    }
                }
            });
        }
    }

    private final void reset() {
        this.updateResponse = null;
        this.updateEntity = null;
        this.callback = null;
        this.assetTotal = 0;
        this.erroredAssetList = new ArrayList();
        this.existingAssetList = new ArrayList();
        this.finishedAssetList = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithSuccess() {
        if (this.callback == null) {
            Log.e(TAG, getClass().getSimpleName() + " tried to finish but it already finished or was never initialized.");
            return;
        }
        UpdateResponse updateResponse = this.updateResponse;
        Intrinsics.checkNotNull(updateResponse);
        ResponseHeaderData responseHeaderData = updateResponse.getResponseHeaderData();
        if (responseHeaderData != null) {
            ManifestMetadata.INSTANCE.saveMetadata(responseHeaderData, this.database, this.configuration);
        }
        UpdateResponse updateResponse2 = this.updateResponse;
        Intrinsics.checkNotNull(updateResponse2);
        UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart = updateResponse2.getDirectiveUpdateResponsePart();
        UpdateDirective updateDirective = directiveUpdateResponsePart != null ? directiveUpdateResponsePart.getUpdateDirective() : null;
        LoaderCallback loaderCallback = this.callback;
        Intrinsics.checkNotNull(loaderCallback);
        loaderCallback.onSuccess(new LoaderResult(this.updateEntity, updateDirective));
        reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithError(String message, Exception e) {
        String str = TAG;
        Log.e(str, message, e);
        LoaderCallback loaderCallback = this.callback;
        if (loaderCallback == null) {
            Log.e(str, getClass().getSimpleName() + " tried to finish but it already finished or was never initialized.");
            return;
        }
        Intrinsics.checkNotNull(loaderCallback);
        loaderCallback.onFailure(e);
        reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processUpdate(Update update) {
        if (update.getIsDevelopmentMode()) {
            UpdateEntity updateEntity = update.getUpdateEntity();
            UpdateDao updateDao = this.database.updateDao();
            Intrinsics.checkNotNull(updateEntity);
            updateDao.insertUpdate(updateEntity);
            this.database.updateDao().markUpdateFinished(updateEntity);
            finishWithSuccess();
            return;
        }
        UpdateEntity updateEntity2 = update.getUpdateEntity();
        UpdateDao updateDao2 = this.database.updateDao();
        Intrinsics.checkNotNull(updateEntity2);
        UpdateEntity loadUpdateWithId = updateDao2.loadUpdateWithId(updateEntity2.getId());
        if (loadUpdateWithId != null && !Intrinsics.areEqual(loadUpdateWithId.getScopeKey(), updateEntity2.getScopeKey())) {
            this.database.updateDao().setUpdateScopeKey(loadUpdateWithId, updateEntity2.getScopeKey());
            Log.e(TAG, "Loaded an update with the same ID but a different scopeKey than one we already have on disk. This is a server error. Overwriting the scopeKey and loading the existing update.");
        }
        if (loadUpdateWithId != null && loadUpdateWithId.getStatus() == UpdateStatus.READY) {
            this.updateEntity = loadUpdateWithId;
            finishWithSuccess();
            return;
        }
        if (loadUpdateWithId == null) {
            this.updateEntity = updateEntity2;
            UpdateDao updateDao3 = this.database.updateDao();
            UpdateEntity updateEntity3 = this.updateEntity;
            Intrinsics.checkNotNull(updateEntity3);
            updateDao3.insertUpdate(updateEntity3);
        } else {
            this.updateEntity = loadUpdateWithId;
        }
        downloadAllAssets(update.getAssetEntityList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Loader.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/updates/loader/Loader$AssetLoadResult;", "", "(Ljava/lang/String;I)V", "FINISHED", "ALREADY_EXISTS", "ERRORED", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class AssetLoadResult {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ AssetLoadResult[] $VALUES;
        public static final AssetLoadResult FINISHED = new AssetLoadResult("FINISHED", 0);
        public static final AssetLoadResult ALREADY_EXISTS = new AssetLoadResult("ALREADY_EXISTS", 1);
        public static final AssetLoadResult ERRORED = new AssetLoadResult("ERRORED", 2);

        private static final /* synthetic */ AssetLoadResult[] $values() {
            return new AssetLoadResult[]{FINISHED, ALREADY_EXISTS, ERRORED};
        }

        public static EnumEntries<AssetLoadResult> getEntries() {
            return $ENTRIES;
        }

        public static AssetLoadResult valueOf(String str) {
            return (AssetLoadResult) Enum.valueOf(AssetLoadResult.class, str);
        }

        public static AssetLoadResult[] values() {
            return (AssetLoadResult[]) $VALUES.clone();
        }

        private AssetLoadResult(String str, int i) {
        }

        static {
            AssetLoadResult[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    private final void downloadAllAssets(List<AssetEntity> assetList) {
        AssetEntity assetEntity;
        this.assetTotal = assetList.size();
        for (AssetEntity assetEntity2 : assetList) {
            AssetEntity loadAssetWithKey = this.database.assetDao().loadAssetWithKey(assetEntity2.getKey());
            if (loadAssetWithKey != null) {
                this.database.assetDao().mergeAndUpdateAsset(loadAssetWithKey, assetEntity2);
                assetEntity = loadAssetWithKey;
            } else {
                assetEntity = assetEntity2;
            }
            if (assetEntity.getRelativePath() != null && this.loaderFiles.fileExists(new File(this.updatesDirectory, assetEntity.getRelativePath()))) {
                handleAssetDownloadCompleted(assetEntity, AssetLoadResult.ALREADY_EXISTS);
            } else {
                loadAsset(this.context, assetEntity, this.updatesDirectory, this.configuration, new FileDownloader.AssetDownloadCallback() { // from class: expo.modules.updates.loader.Loader$downloadAllAssets$1
                    @Override // expo.modules.updates.loader.FileDownloader.AssetDownloadCallback
                    public void onFailure(Exception e, AssetEntity assetEntity3) {
                        String str;
                        String str2;
                        Intrinsics.checkNotNullParameter(e, "e");
                        Intrinsics.checkNotNullParameter(assetEntity3, "assetEntity");
                        if (assetEntity3.getHash() != null) {
                            UpdatesUtils updatesUtils = UpdatesUtils.INSTANCE;
                            byte[] hash = assetEntity3.getHash();
                            Intrinsics.checkNotNull(hash);
                            str = "hash " + updatesUtils.bytesToHex(hash);
                        } else {
                            str = "key " + assetEntity3.getKey();
                        }
                        str2 = Loader.TAG;
                        Log.e(str2, "Failed to download asset with " + str, e);
                        Loader.this.handleAssetDownloadCompleted(assetEntity3, Loader.AssetLoadResult.ERRORED);
                    }

                    @Override // expo.modules.updates.loader.FileDownloader.AssetDownloadCallback
                    public void onSuccess(AssetEntity assetEntity3, boolean isNew) {
                        Intrinsics.checkNotNullParameter(assetEntity3, "assetEntity");
                        Loader.this.handleAssetDownloadCompleted(assetEntity3, isNew ? Loader.AssetLoadResult.FINISHED : Loader.AssetLoadResult.ALREADY_EXISTS);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void handleAssetDownloadCompleted(AssetEntity assetEntity, AssetLoadResult result) {
        byte[] bArr;
        int i = WhenMappings.$EnumSwitchMapping$0[result.ordinal()];
        if (i == 1) {
            this.finishedAssetList.add(assetEntity);
        } else if (i == 2) {
            this.existingAssetList.add(assetEntity);
        } else if (i == 3) {
            this.erroredAssetList.add(assetEntity);
        } else {
            throw new AssertionError("Missing implementation for AssetLoadResult value");
        }
        LoaderCallback loaderCallback = this.callback;
        Intrinsics.checkNotNull(loaderCallback);
        loaderCallback.onAssetLoaded(assetEntity, this.finishedAssetList.size() + this.existingAssetList.size(), this.erroredAssetList.size(), this.assetTotal);
        if (this.finishedAssetList.size() + this.erroredAssetList.size() + this.existingAssetList.size() == this.assetTotal) {
            try {
                for (AssetEntity assetEntity2 : this.existingAssetList) {
                    AssetDao assetDao = this.database.assetDao();
                    UpdateEntity updateEntity = this.updateEntity;
                    Intrinsics.checkNotNull(updateEntity);
                    if (!assetDao.addExistingAssetToUpdate(updateEntity, assetEntity2, assetEntity2.getIsLaunchAsset())) {
                        try {
                            bArr = UpdatesUtils.INSTANCE.sha256(new File(this.updatesDirectory, assetEntity2.getRelativePath()));
                        } catch (Exception unused) {
                            bArr = null;
                        }
                        assetEntity2.setDownloadTime(new Date());
                        assetEntity2.setHash(bArr);
                        this.finishedAssetList.add(assetEntity2);
                    }
                }
                AssetDao assetDao2 = this.database.assetDao();
                List<AssetEntity> list = this.finishedAssetList;
                UpdateEntity updateEntity2 = this.updateEntity;
                Intrinsics.checkNotNull(updateEntity2);
                assetDao2.insertAssets(list, updateEntity2);
                if (this.erroredAssetList.size() == 0) {
                    UpdateDao updateDao = this.database.updateDao();
                    UpdateEntity updateEntity3 = this.updateEntity;
                    Intrinsics.checkNotNull(updateEntity3);
                    updateDao.markUpdateFinished(updateEntity3);
                }
                if (this.erroredAssetList.size() > 0) {
                    finishWithError("Failed to load all assets", new Exception("Failed to load all assets"));
                } else {
                    finishWithSuccess();
                }
            } catch (Exception e) {
                finishWithError("Error while adding new update to database", e);
            }
        }
    }
}

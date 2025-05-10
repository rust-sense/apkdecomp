package expo.modules.updates.launcher;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.util.UriUtil;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.dao.AssetDao;
import expo.modules.updates.db.dao.UpdateDao;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.db.enums.UpdateStatus;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.LoaderFiles;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.EmbeddedManifestUtils;
import expo.modules.updates.manifest.EmbeddedUpdate;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import io.sentry.SentryEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DatabaseLauncher.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 62\u00020\u0001:\u00016B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001c\u0010*\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000f0#2\u0006\u0010+\u001a\u00020,H\u0002J \u0010-\u001a\u0004\u0018\u00010\u00052\u0006\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u0002002\u0006\u0010+\u001a\u00020,J\u0018\u00101\u001a\u0004\u0018\u00010\u001d2\u0006\u0010/\u001a\u0002002\u0006\u0010+\u001a\u00020,J \u00102\u001a\u0002032\u0006\u0010/\u001a\u0002002\u0006\u0010+\u001a\u00020,2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u001a\u00104\u001a\u0002032\u0006\u0010.\u001a\u00020$2\b\u00105\u001a\u0004\u0018\u00010\u0005H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0017R\u0016\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001b\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\"\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u000e\u001a\u0004\u0018\u00010\u001d@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R:\u0010%\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000f\u0018\u00010#2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000f\u0018\u00010#@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lexpo/modules/updates/launcher/DatabaseLauncher;", "Lexpo/modules/updates/launcher/Launcher;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "updatesDirectory", "Ljava/io/File;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "(Lexpo/modules/updates/UpdatesConfiguration;Ljava/io/File;Lexpo/modules/updates/loader/FileDownloader;Lexpo/modules/updates/selectionpolicy/SelectionPolicy;)V", "assetsToDownload", "", "assetsToDownloadFinished", "<set-?>", "", "bundleAssetName", "getBundleAssetName", "()Ljava/lang/String;", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "isUsingEmbeddedAssets", "", "()Z", "launchAssetException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "launchAssetFile", "getLaunchAssetFile", "Lexpo/modules/updates/db/entity/UpdateEntity;", "launchedUpdate", "getLaunchedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "loaderFiles", "Lexpo/modules/updates/loader/LoaderFiles;", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "localAssetFiles", "getLocalAssetFiles", "()Ljava/util/Map;", SentryEvent.JsonKeys.LOGGER, "Lexpo/modules/updates/logging/UpdatesLogger;", "embeddedAssetFileMap", "context", "Landroid/content/Context;", "ensureAssetExists", UriUtil.LOCAL_ASSET_SCHEME, "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "getLaunchableUpdate", "launch", "", "maybeFinish", "assetFile", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DatabaseLauncher implements Launcher {
    private static final String TAG = "DatabaseLauncher";
    private int assetsToDownload;
    private int assetsToDownloadFinished;
    private String bundleAssetName;
    private Launcher.LauncherCallback callback;
    private final UpdatesConfiguration configuration;
    private final FileDownloader fileDownloader;
    private Exception launchAssetException;
    private String launchAssetFile;
    private UpdateEntity launchedUpdate;
    private final LoaderFiles loaderFiles;
    private Map<AssetEntity, String> localAssetFiles;
    private UpdatesLogger logger;
    private final SelectionPolicy selectionPolicy;
    private final File updatesDirectory;

    @Override // expo.modules.updates.launcher.Launcher
    public String getBundleAssetName() {
        return this.bundleAssetName;
    }

    @Override // expo.modules.updates.launcher.Launcher
    public String getLaunchAssetFile() {
        return this.launchAssetFile;
    }

    @Override // expo.modules.updates.launcher.Launcher
    public UpdateEntity getLaunchedUpdate() {
        return this.launchedUpdate;
    }

    @Override // expo.modules.updates.launcher.Launcher
    /* renamed from: getLocalAssetFiles */
    public Map<AssetEntity, String> mo753getLocalAssetFiles() {
        return this.localAssetFiles;
    }

    public DatabaseLauncher(UpdatesConfiguration configuration, File file, FileDownloader fileDownloader, SelectionPolicy selectionPolicy) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        this.configuration = configuration;
        this.updatesDirectory = file;
        this.fileDownloader = fileDownloader;
        this.selectionPolicy = selectionPolicy;
        this.loaderFiles = new LoaderFiles();
    }

    @Override // expo.modules.updates.launcher.Launcher
    /* renamed from: isUsingEmbeddedAssets */
    public boolean getIsUsingEmbeddedAssets() {
        return mo753getLocalAssetFiles() == null;
    }

    public final synchronized void launch(UpdatesDatabase database, Context context, Launcher.LauncherCallback callback) {
        File ensureAssetExists;
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.callback != null) {
            throw new AssertionError("DatabaseLauncher has already started. Create a new instance in order to launch a new version.");
        }
        this.callback = callback;
        this.logger = new UpdatesLogger(context);
        this.launchedUpdate = getLaunchableUpdate(database, context);
        if (getLaunchedUpdate() == null) {
            Launcher.LauncherCallback launcherCallback = this.callback;
            Intrinsics.checkNotNull(launcherCallback);
            launcherCallback.onFailure(new Exception("No launchable update was found. If this is a generic app, ensure expo-updates is configured correctly."));
            return;
        }
        UpdateDao updateDao = database.updateDao();
        UpdateEntity launchedUpdate = getLaunchedUpdate();
        Intrinsics.checkNotNull(launchedUpdate);
        updateDao.markUpdateAccessed(launchedUpdate);
        UpdateEntity launchedUpdate2 = getLaunchedUpdate();
        Intrinsics.checkNotNull(launchedUpdate2);
        if (launchedUpdate2.getStatus() == UpdateStatus.DEVELOPMENT) {
            Launcher.LauncherCallback launcherCallback2 = this.callback;
            Intrinsics.checkNotNull(launcherCallback2);
            launcherCallback2.onSuccess();
            return;
        }
        UpdateDao updateDao2 = database.updateDao();
        UpdateEntity launchedUpdate3 = getLaunchedUpdate();
        Intrinsics.checkNotNull(launchedUpdate3);
        AssetEntity loadLaunchAssetForUpdate = updateDao2.loadLaunchAssetForUpdate(launchedUpdate3.getId());
        if (loadLaunchAssetForUpdate == null) {
            Launcher.LauncherCallback launcherCallback3 = this.callback;
            Intrinsics.checkNotNull(launcherCallback3);
            UpdateEntity launchedUpdate4 = getLaunchedUpdate();
            Intrinsics.checkNotNull(launchedUpdate4);
            launcherCallback3.onFailure(new Exception("Launch asset not found for update; this should never happen. Debug info: " + launchedUpdate4.debugInfo()));
            return;
        }
        if (loadLaunchAssetForUpdate.getRelativePath() == null) {
            Launcher.LauncherCallback launcherCallback4 = this.callback;
            Intrinsics.checkNotNull(launcherCallback4);
            UpdateEntity launchedUpdate5 = getLaunchedUpdate();
            Intrinsics.checkNotNull(launchedUpdate5);
            launcherCallback4.onFailure(new Exception("Launch asset relative path should not be null. Debug info: " + launchedUpdate5.debugInfo()));
        }
        File ensureAssetExists2 = ensureAssetExists(loadLaunchAssetForUpdate, database, context);
        if (ensureAssetExists2 != null) {
            this.launchAssetFile = ensureAssetExists2.toString();
        }
        AssetDao assetDao = database.assetDao();
        UpdateEntity launchedUpdate6 = getLaunchedUpdate();
        Intrinsics.checkNotNull(launchedUpdate6);
        List<AssetEntity> loadAssetsForUpdate = assetDao.loadAssetsForUpdate(launchedUpdate6.getId());
        Map<AssetEntity, String> embeddedAssetFileMap = embeddedAssetFileMap(context);
        for (AssetEntity assetEntity : loadAssetsForUpdate) {
            if (assetEntity.getId() != loadLaunchAssetForUpdate.getId() && assetEntity.getRelativePath() != null && (ensureAssetExists = ensureAssetExists(assetEntity, database, context)) != null) {
                String uri = Uri.fromFile(ensureAssetExists).toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                embeddedAssetFileMap.put(assetEntity, uri);
            }
        }
        this.localAssetFiles = embeddedAssetFileMap;
        if (this.assetsToDownload == 0) {
            if (getLaunchAssetFile() == null) {
                Launcher.LauncherCallback launcherCallback5 = this.callback;
                Intrinsics.checkNotNull(launcherCallback5);
                UpdateEntity launchedUpdate7 = getLaunchedUpdate();
                Intrinsics.checkNotNull(launchedUpdate7);
                launcherCallback5.onFailure(new Exception("Launch asset file was null with no assets to download reported; this should never happen. Debug info: " + launchedUpdate7.debugInfo()));
            } else {
                Launcher.LauncherCallback launcherCallback6 = this.callback;
                Intrinsics.checkNotNull(launcherCallback6);
                launcherCallback6.onSuccess();
            }
        }
    }

    public final UpdateEntity getLaunchableUpdate(UpdatesDatabase database, Context context) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(context, "context");
        List<UpdateEntity> loadLaunchableUpdatesForScope = database.updateDao().loadLaunchableUpdatesForScope(this.configuration.getScopeKey());
        EmbeddedUpdate embeddedUpdate = EmbeddedManifestUtils.INSTANCE.getEmbeddedUpdate(context, this.configuration);
        ArrayList arrayList = new ArrayList();
        for (UpdateEntity updateEntity : loadLaunchableUpdatesForScope) {
            if (updateEntity.getStatus() != UpdateStatus.EMBEDDED || embeddedUpdate == null || Intrinsics.areEqual(embeddedUpdate.getUpdateEntity().getId(), updateEntity.getId())) {
                arrayList.add(updateEntity);
            }
        }
        return this.selectionPolicy.selectUpdateToLaunch(arrayList, ManifestMetadata.INSTANCE.getManifestFilters(database, this.configuration));
    }

    private final Map<AssetEntity, String> embeddedAssetFileMap(Context context) {
        List<AssetEntity> emptyList;
        EmbeddedUpdate embeddedUpdate = EmbeddedManifestUtils.INSTANCE.getEmbeddedUpdate(context, this.configuration);
        if (embeddedUpdate == null || (emptyList = embeddedUpdate.getAssetEntityList()) == null) {
            emptyList = CollectionsKt.emptyList();
        }
        UpdatesLogger updatesLogger = this.logger;
        if (updatesLogger != null) {
            UpdatesLogger.info$default(updatesLogger, "embeddedAssetFileMap: embeddedAssets count = " + emptyList.size(), null, 2, null);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (AssetEntity assetEntity : emptyList) {
            if (!assetEntity.getIsLaunchAsset()) {
                String createFilenameForAsset = UpdatesUtils.INSTANCE.createFilenameForAsset(assetEntity);
                assetEntity.setRelativePath(createFilenameForAsset);
                File file = new File(this.updatesDirectory, createFilenameForAsset);
                if (!file.exists()) {
                    this.loaderFiles.copyAssetAndGetHash(assetEntity, file, context);
                }
                if (file.exists()) {
                    String uri = Uri.fromFile(file).toString();
                    Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                    linkedHashMap.put(assetEntity, uri);
                    UpdatesLogger updatesLogger2 = this.logger;
                    if (updatesLogger2 != null) {
                        UpdatesLogger.info$default(updatesLogger2, "embeddedAssetFileMap: " + assetEntity.getKey() + "," + assetEntity.getType() + " => " + linkedHashMap.get(assetEntity), null, 2, null);
                    }
                } else {
                    UpdatesLogger updatesLogger3 = this.logger;
                    if (updatesLogger3 != null) {
                        UpdatesLogger.error$default(updatesLogger3, "embeddedAssetFileMap: no file for " + assetEntity.getKey() + "," + assetEntity.getType(), UpdatesErrorCode.AssetsFailedToLoad, null, 4, null);
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public final File ensureAssetExists(AssetEntity asset, final UpdatesDatabase database, Context context) {
        EmbeddedUpdate embeddedUpdate;
        AssetEntity assetEntity;
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(context, "context");
        File file = this.updatesDirectory;
        String relativePath = asset.getRelativePath();
        if (relativePath == null) {
            relativePath = "";
        }
        File file2 = new File(file, relativePath);
        boolean exists = file2.exists();
        if (!exists && (embeddedUpdate = EmbeddedManifestUtils.INSTANCE.getEmbeddedUpdate(context, this.configuration)) != null) {
            Iterator<AssetEntity> it = embeddedUpdate.getAssetEntityList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    assetEntity = null;
                    break;
                }
                assetEntity = it.next();
                if (assetEntity.getKey() != null && Intrinsics.areEqual(assetEntity.getKey(), asset.getKey())) {
                    break;
                }
            }
            if (assetEntity != null) {
                try {
                    if (Arrays.equals(this.loaderFiles.copyAssetAndGetHash(assetEntity, file2, context), asset.getHash())) {
                        return file2;
                    }
                } catch (Exception e) {
                    UpdatesLogger updatesLogger = this.logger;
                    if (updatesLogger != null) {
                        updatesLogger.error("Failed to copy matching embedded asset", UpdatesErrorCode.AssetsFailedToLoad, e);
                    }
                }
            }
        }
        if (exists) {
            return file2;
        }
        this.assetsToDownload++;
        this.fileDownloader.downloadAsset(asset, this.updatesDirectory, context, new FileDownloader.AssetDownloadCallback() { // from class: expo.modules.updates.launcher.DatabaseLauncher$ensureAssetExists$1
            @Override // expo.modules.updates.loader.FileDownloader.AssetDownloadCallback
            public void onFailure(Exception e2, AssetEntity assetEntity2) {
                UpdatesLogger updatesLogger2;
                Intrinsics.checkNotNullParameter(e2, "e");
                Intrinsics.checkNotNullParameter(assetEntity2, "assetEntity");
                updatesLogger2 = DatabaseLauncher.this.logger;
                if (updatesLogger2 != null) {
                    updatesLogger2.error("Failed to load asset from disk or network", UpdatesErrorCode.AssetsFailedToLoad, e2);
                }
                if (assetEntity2.getIsLaunchAsset()) {
                    DatabaseLauncher.this.launchAssetException = e2;
                }
                DatabaseLauncher.this.maybeFinish(assetEntity2, null);
            }

            @Override // expo.modules.updates.loader.FileDownloader.AssetDownloadCallback
            public void onSuccess(AssetEntity assetEntity2, boolean isNew) {
                File file3;
                Intrinsics.checkNotNullParameter(assetEntity2, "assetEntity");
                database.assetDao().updateAsset(assetEntity2);
                file3 = DatabaseLauncher.this.updatesDirectory;
                String relativePath2 = assetEntity2.getRelativePath();
                Intrinsics.checkNotNull(relativePath2);
                File file4 = new File(file3, relativePath2);
                DatabaseLauncher databaseLauncher = DatabaseLauncher.this;
                if (!file4.exists()) {
                    file4 = null;
                }
                databaseLauncher.maybeFinish(assetEntity2, file4);
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void maybeFinish(AssetEntity asset, File assetFile) {
        String file;
        this.assetsToDownloadFinished++;
        if (asset.getIsLaunchAsset()) {
            if (assetFile == null) {
                UpdatesLogger updatesLogger = this.logger;
                if (updatesLogger != null) {
                    UpdatesLogger.error$default(updatesLogger, "Could not launch; failed to load update from disk or network", UpdatesErrorCode.UpdateFailedToLoad, null, 4, null);
                }
                file = null;
            } else {
                file = assetFile.toString();
            }
            this.launchAssetFile = file;
        } else if (assetFile != null) {
            Map<AssetEntity, String> mo753getLocalAssetFiles = mo753getLocalAssetFiles();
            Intrinsics.checkNotNull(mo753getLocalAssetFiles);
            String file2 = assetFile.toString();
            Intrinsics.checkNotNullExpressionValue(file2, "toString(...)");
            mo753getLocalAssetFiles.put(asset, file2);
        }
        if (this.assetsToDownloadFinished == this.assetsToDownload) {
            if (getLaunchAssetFile() == null) {
                if (this.launchAssetException == null) {
                    this.launchAssetException = new Exception("Launcher mLaunchAssetFile is unexpectedly null");
                }
                Launcher.LauncherCallback launcherCallback = this.callback;
                Intrinsics.checkNotNull(launcherCallback);
                Exception exc = this.launchAssetException;
                Intrinsics.checkNotNull(exc);
                launcherCallback.onFailure(exc);
            } else {
                Launcher.LauncherCallback launcherCallback2 = this.callback;
                Intrinsics.checkNotNull(launcherCallback2);
                launcherCallback2.onSuccess();
            }
        }
    }
}

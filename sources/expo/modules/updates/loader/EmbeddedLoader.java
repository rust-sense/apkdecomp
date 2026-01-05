package expo.modules.updates.loader;

import android.content.Context;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.UpdateResponsePart;
import expo.modules.updates.manifest.Update;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EmbeddedLoader.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ2\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J(\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0015H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lexpo/modules/updates/loader/EmbeddedLoader;", "Lexpo/modules/updates/loader/Loader;", "context", "Landroid/content/Context;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "updatesDirectory", "Ljava/io/File;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/UpdatesDatabase;Ljava/io/File;)V", "loaderFiles", "Lexpo/modules/updates/loader/LoaderFiles;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/UpdatesDatabase;Ljava/io/File;Lexpo/modules/updates/loader/LoaderFiles;)V", "loadAsset", "", "assetEntity", "Lexpo/modules/updates/db/entity/AssetEntity;", "callback", "Lexpo/modules/updates/loader/FileDownloader$AssetDownloadCallback;", "loadRemoteUpdate", "Lexpo/modules/updates/loader/FileDownloader$RemoteUpdateDownloadCallback;", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EmbeddedLoader extends Loader {
    public static final String BARE_BUNDLE_FILENAME = "index.android.bundle";
    public static final String BUNDLE_FILENAME = "app.bundle";
    private static final String TAG = "EmbeddedLoader";
    private final UpdatesConfiguration configuration;
    private final Context context;
    private final LoaderFiles loaderFiles;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmbeddedLoader(Context context, UpdatesConfiguration configuration, UpdatesDatabase database, File updatesDirectory, LoaderFiles loaderFiles) {
        super(context, configuration, database, updatesDirectory, loaderFiles);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(updatesDirectory, "updatesDirectory");
        Intrinsics.checkNotNullParameter(loaderFiles, "loaderFiles");
        this.context = context;
        this.configuration = configuration;
        this.loaderFiles = loaderFiles;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public EmbeddedLoader(Context context, UpdatesConfiguration configuration, UpdatesDatabase database, File updatesDirectory) {
        this(context, configuration, database, updatesDirectory, new LoaderFiles());
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(updatesDirectory, "updatesDirectory");
    }

    @Override // expo.modules.updates.loader.Loader
    protected void loadRemoteUpdate(Context context, UpdatesDatabase database, UpdatesConfiguration configuration, FileDownloader.RemoteUpdateDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Update readEmbeddedUpdate = this.loaderFiles.readEmbeddedUpdate(this.context, this.configuration);
        if (readEmbeddedUpdate != null) {
            callback.onSuccess(new UpdateResponse(null, new UpdateResponsePart.ManifestUpdateResponsePart(readEmbeddedUpdate), null));
        } else {
            callback.onFailure("Embedded manifest is null", new Exception("Embedded manifest is null"));
        }
    }

    @Override // expo.modules.updates.loader.Loader
    protected void loadAsset(Context context, AssetEntity assetEntity, File updatesDirectory, UpdatesConfiguration configuration, FileDownloader.AssetDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetEntity, "assetEntity");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String createFilenameForAsset = UpdatesUtils.INSTANCE.createFilenameForAsset(assetEntity);
        File file = new File(updatesDirectory, createFilenameForAsset);
        if (this.loaderFiles.fileExists(file)) {
            assetEntity.setRelativePath(createFilenameForAsset);
            callback.onSuccess(assetEntity, false);
            return;
        }
        try {
            assetEntity.setHash(this.loaderFiles.copyAssetAndGetHash(assetEntity, file, context));
            assetEntity.setDownloadTime(new Date());
            assetEntity.setRelativePath(createFilenameForAsset);
            callback.onSuccess(assetEntity, true);
        } catch (FileNotFoundException unused) {
            throw new AssertionError("APK bundle must contain the expected embedded asset " + (assetEntity.getEmbeddedAssetFilename() != null ? assetEntity.getEmbeddedAssetFilename() : assetEntity.getResourcesFilename()));
        } catch (Exception e) {
            callback.onFailure(e, assetEntity);
        }
    }
}

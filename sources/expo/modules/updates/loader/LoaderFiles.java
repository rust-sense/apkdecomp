package expo.modules.updates.loader;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.manifest.EmbeddedManifestUtils;
import expo.modules.updates.manifest.Update;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoaderFiles.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ%\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\fJ%\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014¨\u0006\u0016"}, d2 = {"Lexpo/modules/updates/loader/LoaderFiles;", "", "()V", "copyAssetAndGetHash", "", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "destination", "Ljava/io/File;", "context", "Landroid/content/Context;", "copyContextAssetAndGetHash", "copyContextAssetAndGetHash$expo_updates_release", "copyResourceAndGetHash", "copyResourceAndGetHash$expo_updates_release", "fileExists", "", "readEmbeddedUpdate", "Lexpo/modules/updates/manifest/Update;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LoaderFiles {
    private static final String TAG = "LoaderFiles";

    public final boolean fileExists(File destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        return destination.exists();
    }

    public final Update readEmbeddedUpdate(Context context, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return EmbeddedManifestUtils.INSTANCE.getEmbeddedUpdate(context, configuration);
    }

    public final byte[] copyAssetAndGetHash(AssetEntity asset, File destination, Context context) throws NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(context, "context");
        if (asset.getEmbeddedAssetFilename() != null) {
            return copyContextAssetAndGetHash$expo_updates_release(asset, destination, context);
        }
        if (asset.getResourcesFilename() != null && asset.getResourcesFolder() != null) {
            return copyResourceAndGetHash$expo_updates_release(asset, destination, context);
        }
        throw new AssertionError("Failed to copy embedded asset " + asset.getKey() + " from APK assets or resources because not enough information was provided.");
    }

    public final byte[] copyContextAssetAndGetHash$expo_updates_release(AssetEntity asset, File destination, Context context) throws NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            AssetManager assets = context.getAssets();
            String embeddedAssetFilename = asset.getEmbeddedAssetFilename();
            Intrinsics.checkNotNull(embeddedAssetFilename);
            InputStream open = assets.open(embeddedAssetFilename);
            try {
                InputStream inputStream = open;
                UpdatesUtils updatesUtils = UpdatesUtils.INSTANCE;
                Intrinsics.checkNotNull(inputStream);
                byte[] verifySHA256AndWriteToFile = updatesUtils.verifySHA256AndWriteToFile(inputStream, destination, null);
                CloseableKt.closeFinally(open, null);
                return verifySHA256AndWriteToFile;
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to copy asset " + asset.getEmbeddedAssetFilename(), e);
            throw e;
        }
    }

    public final byte[] copyResourceAndGetHash$expo_updates_release(AssetEntity asset, File destination, Context context) throws NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            InputStream openRawResource = context.getResources().openRawResource(context.getResources().getIdentifier(asset.getResourcesFilename(), asset.getResourcesFolder(), context.getPackageName()));
            try {
                InputStream inputStream = openRawResource;
                UpdatesUtils updatesUtils = UpdatesUtils.INSTANCE;
                Intrinsics.checkNotNull(inputStream);
                byte[] verifySHA256AndWriteToFile = updatesUtils.verifySHA256AndWriteToFile(inputStream, destination, null);
                CloseableKt.closeFinally(openRawResource, null);
                return verifySHA256AndWriteToFile;
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to copy asset " + asset.getEmbeddedAssetFilename(), e);
            throw e;
        }
    }
}

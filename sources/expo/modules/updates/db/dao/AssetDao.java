package expo.modules.updates.db.dao;

import com.facebook.common.util.UriUtil;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateAssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssetDao.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H'J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH'J\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH'J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\rH'J\b\u0010\u0011\u001a\u00020\u0004H'J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H'J\b\u0010\u0016\u001a\u00020\u0004H'J\b\u0010\u0017\u001a\u00020\u0004H'J\b\u0010\u0018\u001a\u00020\u0004H'J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001aH\u0017J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0017J\u001e\u0010\u001f\u001a\u00020\u00042\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\r2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\rH'J\u0012\u0010\"\u001a\u0004\u0018\u00010\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\b0\r2\u0006\u0010$\u001a\u00020\u0015H'J\u0016\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\bJ\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\bH'¨\u0006)"}, d2 = {"Lexpo/modules/updates/db/dao/AssetDao;", "", "()V", "_deleteAssetsMarkedForDeletion", "", "_insertAsset", "", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "_insertUpdateAsset", "updateAsset", "Lexpo/modules/updates/db/entity/UpdateAssetEntity;", "_loadAssetWithKey", "", "key", "", "_loadAssetsMarkedForDeletion", "_markAllAssetsForDeletion", "_setUpdateLaunchAsset", "assetId", "updateId", "Ljava/util/UUID;", "_unmarkDuplicateUsedAssetsFromDeletion", "_unmarkUsedAssetsFromDeletion", "_unmarkUsedLaunchAssetsFromDeletion", "addExistingAssetToUpdate", "", "update", "Lexpo/modules/updates/db/entity/UpdateEntity;", "isLaunchAsset", "deleteUnusedAssets", "insertAssets", "assets", "loadAllAssets", "loadAssetWithKey", "loadAssetsForUpdate", "id", "mergeAndUpdateAsset", "existingEntity", "newEntity", "assetEntity", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class AssetDao {
    public abstract void _deleteAssetsMarkedForDeletion();

    public abstract long _insertAsset(AssetEntity asset);

    public abstract void _insertUpdateAsset(UpdateAssetEntity updateAsset);

    public abstract List<AssetEntity> _loadAssetWithKey(String key);

    public abstract List<AssetEntity> _loadAssetsMarkedForDeletion();

    public abstract void _markAllAssetsForDeletion();

    public abstract void _setUpdateLaunchAsset(long assetId, UUID updateId);

    public abstract void _unmarkDuplicateUsedAssetsFromDeletion();

    public abstract void _unmarkUsedAssetsFromDeletion();

    public abstract void _unmarkUsedLaunchAssetsFromDeletion();

    public abstract List<AssetEntity> loadAllAssets();

    public abstract List<AssetEntity> loadAssetsForUpdate(UUID id);

    public abstract void updateAsset(AssetEntity assetEntity);

    public void insertAssets(List<AssetEntity> assets, UpdateEntity update) {
        Intrinsics.checkNotNullParameter(assets, "assets");
        Intrinsics.checkNotNullParameter(update, "update");
        for (AssetEntity assetEntity : assets) {
            long _insertAsset = _insertAsset(assetEntity);
            _insertUpdateAsset(new UpdateAssetEntity(update.getId(), _insertAsset));
            if (assetEntity.getIsLaunchAsset()) {
                _setUpdateLaunchAsset(_insertAsset, update.getId());
            }
        }
    }

    public final AssetEntity loadAssetWithKey(String key) {
        List<AssetEntity> _loadAssetWithKey = _loadAssetWithKey(key);
        if (!_loadAssetWithKey.isEmpty()) {
            return _loadAssetWithKey.get(0);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004c, code lost:
    
        if (r0 != false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void mergeAndUpdateAsset(expo.modules.updates.db.entity.AssetEntity r4, expo.modules.updates.db.entity.AssetEntity r5) {
        /*
            r3 = this;
            java.lang.String r0 = "existingEntity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "newEntity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            android.net.Uri r0 = r5.getUrl()
            if (r0 == 0) goto L2d
            android.net.Uri r0 = r4.getUrl()
            if (r0 == 0) goto L24
            android.net.Uri r0 = r5.getUrl()
            android.net.Uri r1 = r4.getUrl()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 != 0) goto L2d
        L24:
            android.net.Uri r0 = r5.getUrl()
            r4.setUrl(r0)
            r0 = 1
            goto L2e
        L2d:
            r0 = 0
        L2e:
            org.json.JSONObject r1 = r5.getExtraRequestHeaders()
            if (r1 == 0) goto L4c
            org.json.JSONObject r2 = r4.getExtraRequestHeaders()
            if (r2 == 0) goto L44
            org.json.JSONObject r2 = r4.getExtraRequestHeaders()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            if (r1 != 0) goto L4c
        L44:
            org.json.JSONObject r0 = r5.getExtraRequestHeaders()
            r4.setExtraRequestHeaders(r0)
            goto L4e
        L4c:
            if (r0 == 0) goto L51
        L4e:
            r3.updateAsset(r4)
        L51:
            boolean r0 = r5.getIsLaunchAsset()
            r4.setLaunchAsset(r0)
            java.lang.String r0 = r5.getEmbeddedAssetFilename()
            r4.setEmbeddedAssetFilename(r0)
            java.lang.String r0 = r5.getResourcesFilename()
            r4.setResourcesFilename(r0)
            java.lang.String r0 = r5.getResourcesFolder()
            r4.setResourcesFolder(r0)
            java.lang.Float r0 = r5.getScale()
            r4.setScale(r0)
            java.lang.Float[] r5 = r5.getScales()
            r4.setScales(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.updates.db.dao.AssetDao.mergeAndUpdateAsset(expo.modules.updates.db.entity.AssetEntity, expo.modules.updates.db.entity.AssetEntity):void");
    }

    public boolean addExistingAssetToUpdate(UpdateEntity update, AssetEntity asset, boolean isLaunchAsset) {
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(asset, "asset");
        AssetEntity loadAssetWithKey = loadAssetWithKey(asset.getKey());
        if (loadAssetWithKey == null) {
            return false;
        }
        long id = loadAssetWithKey.getId();
        _insertUpdateAsset(new UpdateAssetEntity(update.getId(), id));
        if (!isLaunchAsset) {
            return true;
        }
        _setUpdateLaunchAsset(id, update.getId());
        return true;
    }

    public List<AssetEntity> deleteUnusedAssets() {
        _markAllAssetsForDeletion();
        _unmarkUsedAssetsFromDeletion();
        _unmarkUsedLaunchAssetsFromDeletion();
        _unmarkDuplicateUsedAssetsFromDeletion();
        List<AssetEntity> _loadAssetsMarkedForDeletion = _loadAssetsMarkedForDeletion();
        _deleteAssetsMarkedForDeletion();
        return _loadAssetsMarkedForDeletion;
    }
}

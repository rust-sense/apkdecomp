package expo.modules.updates.db.dao;

import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.db.enums.UpdateStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdateDao.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0005\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0006H'J$\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rH'J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H'J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001e\u0010\u0019\u001a\u00020\u00042\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\r2\u0006\u0010\u0018\u001a\u00020\u0012H'J\u0018\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0016H'J\u0018\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0010H'J\u0016\u0010 \u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH'J\u000e\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u000eJ\u000e\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u000eJ\u0010\u0010%\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u000eH'J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\u0006\u0010\u0018\u001a\u00020\u0012H'J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH'J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0018\u001a\u00020\u0012H'J\u0010\u0010)\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0006J\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00060\rH'J\u0010\u0010,\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010-\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u000eJ\u000e\u0010.\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u000eJ\u0018\u0010.\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010/\u001a\u000200H\u0017J\u0014\u00101\u001a\u00020\u00042\f\u00102\u001a\b\u0012\u0004\u0012\u00020\n0\rJ\u0016\u00103\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0016J\u0016\u00104\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u0010¨\u00065"}, d2 = {"Lexpo/modules/updates/db/dao/UpdateDao;", "", "()V", "_incrementFailedLaunchCount", "", "id", "Ljava/util/UUID;", "_incrementSuccessfulLaunchCount", "_keepUpdate", "_loadLaunchAssetForUpdate", "Lexpo/modules/updates/db/entity/AssetEntity;", "updateId", "_loadLaunchableUpdatesForProjectWithStatuses", "", "Lexpo/modules/updates/db/entity/UpdateEntity;", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, "", "statuses", "Lexpo/modules/updates/db/enums/UpdateStatus;", "_loadUpdatesWithId", "_markUpdateAccessed", "lastAccessed", "Ljava/util/Date;", "_markUpdateWithStatus", "status", "_markUpdatesWithMissingAssets", "missingAssetIds", "", "_setUpdateCommitTime", "commitTime", "_setUpdateScopeKeyInternal", "newScopeKey", "deleteUpdates", "updates", "incrementFailedLaunchCount", "update", "incrementSuccessfulLaunchCount", "insertUpdate", "loadAllUpdateIdsWithStatus", "loadAllUpdates", "loadAllUpdatesWithStatus", "loadLaunchAssetForUpdate", "loadLaunchableUpdatesForScope", "loadRecentUpdateIdsWithFailedLaunch", "loadUpdateWithId", "markUpdateAccessed", "markUpdateFinished", "hasSkippedEmbeddedAssets", "", "markUpdatesWithMissingAssets", "missingAssets", "setUpdateCommitTime", "setUpdateScopeKey", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class UpdateDao {
    public abstract void _incrementFailedLaunchCount(UUID id);

    public abstract void _incrementSuccessfulLaunchCount(UUID id);

    public abstract void _keepUpdate(UUID id);

    public abstract AssetEntity _loadLaunchAssetForUpdate(UUID updateId);

    public abstract List<UpdateEntity> _loadLaunchableUpdatesForProjectWithStatuses(String scopeKey, List<? extends UpdateStatus> statuses);

    public abstract List<UpdateEntity> _loadUpdatesWithId(UUID id);

    public abstract void _markUpdateAccessed(UUID id, Date lastAccessed);

    public abstract void _markUpdateWithStatus(UpdateStatus status, UUID id);

    public abstract void _markUpdatesWithMissingAssets(List<Long> missingAssetIds, UpdateStatus status);

    public abstract void _setUpdateCommitTime(UUID id, Date commitTime);

    public abstract void _setUpdateScopeKeyInternal(UUID id, String newScopeKey);

    public abstract void deleteUpdates(List<UpdateEntity> updates);

    public abstract void insertUpdate(UpdateEntity update);

    public abstract List<UUID> loadAllUpdateIdsWithStatus(UpdateStatus status);

    public abstract List<UpdateEntity> loadAllUpdates();

    public abstract List<UpdateEntity> loadAllUpdatesWithStatus(UpdateStatus status);

    public abstract List<UUID> loadRecentUpdateIdsWithFailedLaunch();

    public final List<UpdateEntity> loadLaunchableUpdatesForScope(String scopeKey) {
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        return _loadLaunchableUpdatesForProjectWithStatuses(scopeKey, CollectionsKt.listOf((Object[]) new UpdateStatus[]{UpdateStatus.READY, UpdateStatus.EMBEDDED, UpdateStatus.DEVELOPMENT}));
    }

    public final UpdateEntity loadUpdateWithId(UUID id) {
        Intrinsics.checkNotNullParameter(id, "id");
        List<UpdateEntity> _loadUpdatesWithId = _loadUpdatesWithId(id);
        if (!_loadUpdatesWithId.isEmpty()) {
            return _loadUpdatesWithId.get(0);
        }
        return null;
    }

    public final AssetEntity loadLaunchAssetForUpdate(UUID updateId) {
        Intrinsics.checkNotNullParameter(updateId, "updateId");
        AssetEntity _loadLaunchAssetForUpdate = _loadLaunchAssetForUpdate(updateId);
        if (_loadLaunchAssetForUpdate == null) {
            return null;
        }
        _loadLaunchAssetForUpdate.setLaunchAsset(true);
        return _loadLaunchAssetForUpdate;
    }

    public final void setUpdateScopeKey(UpdateEntity update, String newScopeKey) {
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(newScopeKey, "newScopeKey");
        update.setScopeKey(newScopeKey);
        _setUpdateScopeKeyInternal(update.getId(), newScopeKey);
    }

    public final void setUpdateCommitTime(UpdateEntity update, Date commitTime) {
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(commitTime, "commitTime");
        update.setCommitTime(commitTime);
        _setUpdateCommitTime(update.getId(), commitTime);
    }

    public void markUpdateFinished(UpdateEntity update, boolean hasSkippedEmbeddedAssets) {
        Intrinsics.checkNotNullParameter(update, "update");
        UpdateStatus updateStatus = UpdateStatus.READY;
        if (update.getStatus() == UpdateStatus.DEVELOPMENT) {
            updateStatus = UpdateStatus.DEVELOPMENT;
        } else if (hasSkippedEmbeddedAssets) {
            updateStatus = UpdateStatus.EMBEDDED;
        }
        _markUpdateWithStatus(updateStatus, update.getId());
        _keepUpdate(update.getId());
    }

    public final void markUpdateFinished(UpdateEntity update) {
        Intrinsics.checkNotNullParameter(update, "update");
        markUpdateFinished(update, false);
    }

    public final void markUpdateAccessed(UpdateEntity update) {
        Intrinsics.checkNotNullParameter(update, "update");
        Date date = new Date();
        update.setLastAccessed(date);
        _markUpdateAccessed(update.getId(), date);
    }

    public final void incrementSuccessfulLaunchCount(UpdateEntity update) {
        Intrinsics.checkNotNullParameter(update, "update");
        update.setSuccessfulLaunchCount(update.getSuccessfulLaunchCount() + 1);
        _incrementSuccessfulLaunchCount(update.getId());
    }

    public final void incrementFailedLaunchCount(UpdateEntity update) {
        Intrinsics.checkNotNullParameter(update, "update");
        update.setFailedLaunchCount(update.getFailedLaunchCount() + 1);
        _incrementFailedLaunchCount(update.getId());
    }

    public final void markUpdatesWithMissingAssets(List<AssetEntity> missingAssets) {
        Intrinsics.checkNotNullParameter(missingAssets, "missingAssets");
        ArrayList arrayList = new ArrayList();
        Iterator<AssetEntity> it = missingAssets.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(it.next().getId()));
        }
        _markUpdatesWithMissingAssets(arrayList, UpdateStatus.PENDING);
    }
}

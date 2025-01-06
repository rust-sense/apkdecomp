package expo.modules.updates.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import expo.modules.updates.db.Converters;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.db.enums.UpdateStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class UpdateDao_Impl extends UpdateDao {
    private final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<UpdateEntity> __deletionAdapterOfUpdateEntity;
    private final EntityInsertionAdapter<UpdateEntity> __insertionAdapterOfUpdateEntity;
    private final SharedSQLiteStatement __preparedStmtOf_incrementFailedLaunchCount;
    private final SharedSQLiteStatement __preparedStmtOf_incrementSuccessfulLaunchCount;
    private final SharedSQLiteStatement __preparedStmtOf_keepUpdate;
    private final SharedSQLiteStatement __preparedStmtOf_markUpdateAccessed;
    private final SharedSQLiteStatement __preparedStmtOf_markUpdateWithStatus;
    private final SharedSQLiteStatement __preparedStmtOf_setUpdateCommitTime;
    private final SharedSQLiteStatement __preparedStmtOf_setUpdateScopeKeyInternal;

    public UpdateDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUpdateEntity = new EntityInsertionAdapter<UpdateEntity>(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR ABORT INTO `updates` (`id`,`commit_time`,`runtime_version`,`scope_key`,`manifest`,`launch_asset_id`,`status`,`keep`,`last_accessed`,`successful_launch_count`,`failed_launch_count`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, UpdateEntity updateEntity) {
                byte[] uuidToBytes = UpdateDao_Impl.this.__converters.uuidToBytes(updateEntity.getId());
                if (uuidToBytes == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindBlob(1, uuidToBytes);
                }
                Long dateToLong = UpdateDao_Impl.this.__converters.dateToLong(updateEntity.getCommitTime());
                if (dateToLong == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, dateToLong.longValue());
                }
                if (updateEntity.getRuntimeVersion() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, updateEntity.getRuntimeVersion());
                }
                if (updateEntity.getScopeKey() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, updateEntity.getScopeKey());
                }
                String jsonObjectToString = UpdateDao_Impl.this.__converters.jsonObjectToString(updateEntity.getManifest());
                if (jsonObjectToString == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, jsonObjectToString);
                }
                if (updateEntity.getLaunchAssetId() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindLong(6, updateEntity.getLaunchAssetId().longValue());
                }
                supportSQLiteStatement.bindLong(7, UpdateDao_Impl.this.__converters.statusToInt(updateEntity.getStatus()));
                supportSQLiteStatement.bindLong(8, updateEntity.getKeep() ? 1L : 0L);
                Long dateToLong2 = UpdateDao_Impl.this.__converters.dateToLong(updateEntity.getLastAccessed());
                if (dateToLong2 == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindLong(9, dateToLong2.longValue());
                }
                supportSQLiteStatement.bindLong(10, updateEntity.getSuccessfulLaunchCount());
                supportSQLiteStatement.bindLong(11, updateEntity.getFailedLaunchCount());
            }
        };
        this.__deletionAdapterOfUpdateEntity = new EntityDeletionOrUpdateAdapter<UpdateEntity>(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "DELETE FROM `updates` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final UpdateEntity entity) {
                byte[] uuidToBytes = UpdateDao_Impl.this.__converters.uuidToBytes(entity.getId());
                if (uuidToBytes == null) {
                    statement.bindNull(1);
                } else {
                    statement.bindBlob(1, uuidToBytes);
                }
            }
        };
        this.__preparedStmtOf_keepUpdate = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET keep = 1 WHERE id = ?;";
            }
        };
        this.__preparedStmtOf_markUpdateWithStatus = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET status = ? WHERE id = ?;";
            }
        };
        this.__preparedStmtOf_setUpdateScopeKeyInternal = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET scope_key = ? WHERE id = ?;";
            }
        };
        this.__preparedStmtOf_setUpdateCommitTime = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET commit_time = ? WHERE id = ?;";
            }
        };
        this.__preparedStmtOf_markUpdateAccessed = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET last_accessed = ? WHERE id = ?;";
            }
        };
        this.__preparedStmtOf_incrementSuccessfulLaunchCount = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET successful_launch_count = successful_launch_count + 1 WHERE id = ?;";
            }
        };
        this.__preparedStmtOf_incrementFailedLaunchCount = new SharedSQLiteStatement(__db) { // from class: expo.modules.updates.db.dao.UpdateDao_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE updates SET failed_launch_count = failed_launch_count + 1 WHERE id = ?;";
            }
        };
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void insertUpdate(final UpdateEntity update) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUpdateEntity.insert((EntityInsertionAdapter<UpdateEntity>) update);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void deleteUpdates(final List<UpdateEntity> updates) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfUpdateEntity.handleMultiple(updates);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void markUpdateFinished(final UpdateEntity update, final boolean hasSkippedEmbeddedAssets) {
        this.__db.beginTransaction();
        try {
            super.markUpdateFinished(update, hasSkippedEmbeddedAssets);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void _keepUpdate(final UUID id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_keepUpdate.acquire();
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, uuidToBytes);
        }
        try {
            this.__db.beginTransaction();
            try {
                acquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOf_keepUpdate.release(acquire);
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void _markUpdateWithStatus(final UpdateStatus status, final UUID id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_markUpdateWithStatus.acquire();
        acquire.bindLong(1, this.__converters.statusToInt(status));
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindBlob(2, uuidToBytes);
        }
        try {
            this.__db.beginTransaction();
            try {
                acquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOf_markUpdateWithStatus.release(acquire);
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void _setUpdateScopeKeyInternal(final UUID id, final String newScopeKey) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_setUpdateScopeKeyInternal.acquire();
        if (newScopeKey == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, newScopeKey);
        }
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindBlob(2, uuidToBytes);
        }
        try {
            this.__db.beginTransaction();
            try {
                acquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOf_setUpdateScopeKeyInternal.release(acquire);
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void _setUpdateCommitTime(final UUID id, final Date commitTime) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_setUpdateCommitTime.acquire();
        Long dateToLong = this.__converters.dateToLong(commitTime);
        if (dateToLong == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindLong(1, dateToLong.longValue());
        }
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindBlob(2, uuidToBytes);
        }
        try {
            this.__db.beginTransaction();
            try {
                acquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOf_setUpdateCommitTime.release(acquire);
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void _markUpdateAccessed(final UUID id, final Date lastAccessed) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_markUpdateAccessed.acquire();
        Long dateToLong = this.__converters.dateToLong(lastAccessed);
        if (dateToLong == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindLong(1, dateToLong.longValue());
        }
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindBlob(2, uuidToBytes);
        }
        try {
            this.__db.beginTransaction();
            try {
                acquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOf_markUpdateAccessed.release(acquire);
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void _incrementSuccessfulLaunchCount(final UUID id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_incrementSuccessfulLaunchCount.acquire();
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, uuidToBytes);
        }
        try {
            this.__db.beginTransaction();
            try {
                acquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOf_incrementSuccessfulLaunchCount.release(acquire);
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void _incrementFailedLaunchCount(final UUID id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOf_incrementFailedLaunchCount.acquire();
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, uuidToBytes);
        }
        try {
            this.__db.beginTransaction();
            try {
                acquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOf_incrementFailedLaunchCount.release(acquire);
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public List<UpdateEntity> _loadLaunchableUpdatesForProjectWithStatuses(final String scopeKey, final List<? extends UpdateStatus> statuses) {
        byte[] blob;
        int i;
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT * FROM updates WHERE scope_key = ");
        newStringBuilder.append("?");
        newStringBuilder.append(" AND (successful_launch_count > 0 OR failed_launch_count < 1) AND status IN (");
        int size = statuses.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(");");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 1);
        if (scopeKey == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, scopeKey);
        }
        Iterator<? extends UpdateStatus> it = statuses.iterator();
        int i2 = 2;
        while (it.hasNext()) {
            acquire.bindLong(i2, this.__converters.statusToInt(it.next()));
            i2++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "commit_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "runtime_version");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "scope_key");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "manifest");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "launch_asset_id");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "status");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "keep");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "last_accessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "successful_launch_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "failed_launch_count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    i = columnIndexOrThrow;
                    blob = null;
                } else {
                    blob = query.getBlob(columnIndexOrThrow);
                    i = columnIndexOrThrow;
                }
                UpdateEntity updateEntity = new UpdateEntity(this.__converters.bytesToUuid(blob), this.__converters.longToDate(query.isNull(columnIndexOrThrow2) ? null : Long.valueOf(query.getLong(columnIndexOrThrow2))), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                updateEntity.setLaunchAssetId(query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6)));
                int i3 = columnIndexOrThrow2;
                updateEntity.setStatus(this.__converters.intToStatus(query.getInt(columnIndexOrThrow7)));
                updateEntity.setKeep(query.getInt(columnIndexOrThrow8) != 0);
                updateEntity.setLastAccessed(this.__converters.longToDate(query.isNull(columnIndexOrThrow9) ? null : Long.valueOf(query.getLong(columnIndexOrThrow9))));
                updateEntity.setSuccessfulLaunchCount(query.getInt(columnIndexOrThrow10));
                updateEntity.setFailedLaunchCount(query.getInt(columnIndexOrThrow11));
                arrayList.add(updateEntity);
                columnIndexOrThrow = i;
                columnIndexOrThrow2 = i3;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public List<UpdateEntity> _loadUpdatesWithId(final UUID id) {
        byte[] blob;
        int i;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM updates WHERE id = ?;", 1);
        byte[] uuidToBytes = this.__converters.uuidToBytes(id);
        if (uuidToBytes == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, uuidToBytes);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "commit_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "runtime_version");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "scope_key");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "manifest");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "launch_asset_id");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "status");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "keep");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "last_accessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "successful_launch_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "failed_launch_count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    i = columnIndexOrThrow;
                    blob = null;
                } else {
                    blob = query.getBlob(columnIndexOrThrow);
                    i = columnIndexOrThrow;
                }
                UpdateEntity updateEntity = new UpdateEntity(this.__converters.bytesToUuid(blob), this.__converters.longToDate(query.isNull(columnIndexOrThrow2) ? null : Long.valueOf(query.getLong(columnIndexOrThrow2))), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                updateEntity.setLaunchAssetId(query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6)));
                int i2 = columnIndexOrThrow2;
                updateEntity.setStatus(this.__converters.intToStatus(query.getInt(columnIndexOrThrow7)));
                updateEntity.setKeep(query.getInt(columnIndexOrThrow8) != 0);
                updateEntity.setLastAccessed(this.__converters.longToDate(query.isNull(columnIndexOrThrow9) ? null : Long.valueOf(query.getLong(columnIndexOrThrow9))));
                updateEntity.setSuccessfulLaunchCount(query.getInt(columnIndexOrThrow10));
                updateEntity.setFailedLaunchCount(query.getInt(columnIndexOrThrow11));
                arrayList.add(updateEntity);
                columnIndexOrThrow = i;
                columnIndexOrThrow2 = i2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public AssetEntity _loadLaunchAssetForUpdate(final UUID updateId) {
        RoomSQLiteQuery roomSQLiteQuery;
        AssetEntity assetEntity;
        String string;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT assets.* FROM assets INNER JOIN updates ON updates.launch_asset_id = assets.id WHERE updates.id = ?;", 1);
        byte[] uuidToBytes = this.__converters.uuidToBytes(updateId);
        if (uuidToBytes == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, uuidToBytes);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "key");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "url");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "headers");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "extra_request_headers");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "metadata");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "download_time");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "relative_path");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "hash");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "hash_type");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "expected_hash");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "marked_for_deletion");
            if (query.moveToFirst()) {
                String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                if (query.isNull(columnIndexOrThrow2)) {
                    roomSQLiteQuery = acquire;
                    string = null;
                } else {
                    string = query.getString(columnIndexOrThrow2);
                    roomSQLiteQuery = acquire;
                }
                try {
                    AssetEntity assetEntity2 = new AssetEntity(string2, string);
                    assetEntity2.setId(query.getLong(columnIndexOrThrow3));
                    assetEntity2.setUrl(this.__converters.stringToUri(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4)));
                    assetEntity2.setHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                    assetEntity2.setExtraRequestHeaders(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                    assetEntity2.setMetadata(this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7)));
                    assetEntity2.setDownloadTime(this.__converters.longToDate(query.isNull(columnIndexOrThrow8) ? null : Long.valueOf(query.getLong(columnIndexOrThrow8))));
                    assetEntity2.setRelativePath(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    assetEntity2.setHash(query.isNull(columnIndexOrThrow10) ? null : query.getBlob(columnIndexOrThrow10));
                    assetEntity2.setHashType(this.__converters.intToHashType(query.getInt(columnIndexOrThrow11)));
                    assetEntity2.setExpectedHash(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    assetEntity2.setMarkedForDeletion(query.getInt(columnIndexOrThrow13) != 0);
                    assetEntity = assetEntity2;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } else {
                roomSQLiteQuery = acquire;
                assetEntity = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return assetEntity;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public List<UpdateEntity> loadAllUpdates() {
        byte[] blob;
        int i;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM updates;", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "commit_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "runtime_version");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "scope_key");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "manifest");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "launch_asset_id");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "status");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "keep");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "last_accessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "successful_launch_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "failed_launch_count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    i = columnIndexOrThrow;
                    blob = null;
                } else {
                    blob = query.getBlob(columnIndexOrThrow);
                    i = columnIndexOrThrow;
                }
                UpdateEntity updateEntity = new UpdateEntity(this.__converters.bytesToUuid(blob), this.__converters.longToDate(query.isNull(columnIndexOrThrow2) ? null : Long.valueOf(query.getLong(columnIndexOrThrow2))), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                updateEntity.setLaunchAssetId(query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6)));
                int i2 = columnIndexOrThrow2;
                updateEntity.setStatus(this.__converters.intToStatus(query.getInt(columnIndexOrThrow7)));
                updateEntity.setKeep(query.getInt(columnIndexOrThrow8) != 0);
                updateEntity.setLastAccessed(this.__converters.longToDate(query.isNull(columnIndexOrThrow9) ? null : Long.valueOf(query.getLong(columnIndexOrThrow9))));
                updateEntity.setSuccessfulLaunchCount(query.getInt(columnIndexOrThrow10));
                updateEntity.setFailedLaunchCount(query.getInt(columnIndexOrThrow11));
                arrayList.add(updateEntity);
                columnIndexOrThrow = i;
                columnIndexOrThrow2 = i2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public List<UpdateEntity> loadAllUpdatesWithStatus(final UpdateStatus status) {
        byte[] blob;
        int i;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM updates WHERE status = ?;", 1);
        acquire.bindLong(1, this.__converters.statusToInt(status));
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "commit_time");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "runtime_version");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "scope_key");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "manifest");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "launch_asset_id");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "status");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "keep");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "last_accessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "successful_launch_count");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "failed_launch_count");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    i = columnIndexOrThrow;
                    blob = null;
                } else {
                    blob = query.getBlob(columnIndexOrThrow);
                    i = columnIndexOrThrow;
                }
                UpdateEntity updateEntity = new UpdateEntity(this.__converters.bytesToUuid(blob), this.__converters.longToDate(query.isNull(columnIndexOrThrow2) ? null : Long.valueOf(query.getLong(columnIndexOrThrow2))), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3), query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4), this.__converters.stringToJsonObject(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5)));
                updateEntity.setLaunchAssetId(query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6)));
                int i2 = columnIndexOrThrow2;
                updateEntity.setStatus(this.__converters.intToStatus(query.getInt(columnIndexOrThrow7)));
                updateEntity.setKeep(query.getInt(columnIndexOrThrow8) != 0);
                updateEntity.setLastAccessed(this.__converters.longToDate(query.isNull(columnIndexOrThrow9) ? null : Long.valueOf(query.getLong(columnIndexOrThrow9))));
                updateEntity.setSuccessfulLaunchCount(query.getInt(columnIndexOrThrow10));
                updateEntity.setFailedLaunchCount(query.getInt(columnIndexOrThrow11));
                arrayList.add(updateEntity);
                columnIndexOrThrow = i;
                columnIndexOrThrow2 = i2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public List<UUID> loadAllUpdateIdsWithStatus(final UpdateStatus status) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM updates WHERE status = ?;", 1);
        acquire.bindLong(1, this.__converters.statusToInt(status));
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(this.__converters.bytesToUuid(query.isNull(0) ? null : query.getBlob(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public List<UUID> loadRecentUpdateIdsWithFailedLaunch() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM updates WHERE failed_launch_count > 0 ORDER BY commit_time DESC LIMIT 5;", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(this.__converters.bytesToUuid(query.isNull(0) ? null : query.getBlob(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // expo.modules.updates.db.dao.UpdateDao
    public void _markUpdatesWithMissingAssets(final List<Long> missingAssetIds, final UpdateStatus status) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE updates SET status = ");
        newStringBuilder.append("?");
        newStringBuilder.append(" WHERE id IN (SELECT DISTINCT update_id FROM updates_assets WHERE asset_id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, missingAssetIds.size());
        newStringBuilder.append("));");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        compileStatement.bindLong(1, this.__converters.statusToInt(status));
        int i = 2;
        for (Long l : missingAssetIds) {
            if (l == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindLong(i, l.longValue());
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}

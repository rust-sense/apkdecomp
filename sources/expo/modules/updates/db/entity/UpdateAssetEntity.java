package expo.modules.updates.db.entity;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdateAssetEntity.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lexpo/modules/updates/db/entity/UpdateAssetEntity;", "", "updateId", "Ljava/util/UUID;", "assetId", "", "(Ljava/util/UUID;J)V", "getAssetId", "()J", "setAssetId", "(J)V", "getUpdateId", "()Ljava/util/UUID;", "setUpdateId", "(Ljava/util/UUID;)V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdateAssetEntity {
    private long assetId;
    private UUID updateId;

    public final long getAssetId() {
        return this.assetId;
    }

    public final UUID getUpdateId() {
        return this.updateId;
    }

    public final void setAssetId(long j) {
        this.assetId = j;
    }

    public final void setUpdateId(UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<set-?>");
        this.updateId = uuid;
    }

    public UpdateAssetEntity(UUID updateId, long j) {
        Intrinsics.checkNotNullParameter(updateId, "updateId");
        this.updateId = updateId;
        this.assetId = j;
    }
}

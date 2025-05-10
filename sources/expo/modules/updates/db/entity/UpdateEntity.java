package expo.modules.updates.db.entity;

import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.enums.UpdateStatus;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: UpdateEntity.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010?\u001a\u00020\u0007R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\r\"\u0004\b\"\u0010\u000fR\"\u0010#\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0011\u0010*\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010,\"\u0004\b2\u00103R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010,\"\u0004\b5\u00103R\u001a\u00106\u001a\u000207X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001e\u0010<\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0013\"\u0004\b>\u0010\u0015¨\u0006@"}, d2 = {"Lexpo/modules/updates/db/entity/UpdateEntity;", "", "id", "Ljava/util/UUID;", "commitTime", "Ljava/util/Date;", UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY, "", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, "manifest", "Lorg/json/JSONObject;", "(Ljava/util/UUID;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;)V", "getCommitTime", "()Ljava/util/Date;", "setCommitTime", "(Ljava/util/Date;)V", "failedLaunchCount", "", "getFailedLaunchCount", "()I", "setFailedLaunchCount", "(I)V", "getId", "()Ljava/util/UUID;", "setId", "(Ljava/util/UUID;)V", "keep", "", "getKeep", "()Z", "setKeep", "(Z)V", "lastAccessed", "getLastAccessed", "setLastAccessed", "launchAssetId", "", "getLaunchAssetId", "()Ljava/lang/Long;", "setLaunchAssetId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "loggingId", "getLoggingId", "()Ljava/lang/String;", "getManifest", "()Lorg/json/JSONObject;", "setManifest", "(Lorg/json/JSONObject;)V", "getRuntimeVersion", "setRuntimeVersion", "(Ljava/lang/String;)V", "getScopeKey", "setScopeKey", "status", "Lexpo/modules/updates/db/enums/UpdateStatus;", "getStatus", "()Lexpo/modules/updates/db/enums/UpdateStatus;", "setStatus", "(Lexpo/modules/updates/db/enums/UpdateStatus;)V", "successfulLaunchCount", "getSuccessfulLaunchCount", "setSuccessfulLaunchCount", "debugInfo", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdateEntity {
    private Date commitTime;
    private int failedLaunchCount;
    private UUID id;
    private boolean keep;
    private Date lastAccessed;
    private Long launchAssetId;
    private JSONObject manifest;
    private String runtimeVersion;
    private String scopeKey;
    private UpdateStatus status;
    private int successfulLaunchCount;

    public final Date getCommitTime() {
        return this.commitTime;
    }

    public final int getFailedLaunchCount() {
        return this.failedLaunchCount;
    }

    public final UUID getId() {
        return this.id;
    }

    public final boolean getKeep() {
        return this.keep;
    }

    public final Date getLastAccessed() {
        return this.lastAccessed;
    }

    public final Long getLaunchAssetId() {
        return this.launchAssetId;
    }

    public final JSONObject getManifest() {
        return this.manifest;
    }

    public final String getRuntimeVersion() {
        return this.runtimeVersion;
    }

    public final String getScopeKey() {
        return this.scopeKey;
    }

    public final UpdateStatus getStatus() {
        return this.status;
    }

    public final int getSuccessfulLaunchCount() {
        return this.successfulLaunchCount;
    }

    public final void setCommitTime(Date date) {
        Intrinsics.checkNotNullParameter(date, "<set-?>");
        this.commitTime = date;
    }

    public final void setFailedLaunchCount(int i) {
        this.failedLaunchCount = i;
    }

    public final void setId(UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "<set-?>");
        this.id = uuid;
    }

    public final void setKeep(boolean z) {
        this.keep = z;
    }

    public final void setLastAccessed(Date date) {
        Intrinsics.checkNotNullParameter(date, "<set-?>");
        this.lastAccessed = date;
    }

    public final void setLaunchAssetId(Long l) {
        this.launchAssetId = l;
    }

    public final void setManifest(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<set-?>");
        this.manifest = jSONObject;
    }

    public final void setRuntimeVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.runtimeVersion = str;
    }

    public final void setScopeKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.scopeKey = str;
    }

    public final void setStatus(UpdateStatus updateStatus) {
        Intrinsics.checkNotNullParameter(updateStatus, "<set-?>");
        this.status = updateStatus;
    }

    public final void setSuccessfulLaunchCount(int i) {
        this.successfulLaunchCount = i;
    }

    public UpdateEntity(UUID id, Date commitTime, String runtimeVersion, String scopeKey, JSONObject manifest) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(commitTime, "commitTime");
        Intrinsics.checkNotNullParameter(runtimeVersion, "runtimeVersion");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        this.id = id;
        this.commitTime = commitTime;
        this.runtimeVersion = runtimeVersion;
        this.scopeKey = scopeKey;
        this.manifest = manifest;
        this.status = UpdateStatus.PENDING;
        this.lastAccessed = new Date();
    }

    public final String getLoggingId() {
        String uuid = this.id.toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        String lowerCase = uuid.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    public final String debugInfo() {
        String jSONObject = new JSONObject(MapsKt.mapOf(TuplesKt.to("id", this.id.toString()), TuplesKt.to("status", this.status.name()))).toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
        return jSONObject;
    }
}

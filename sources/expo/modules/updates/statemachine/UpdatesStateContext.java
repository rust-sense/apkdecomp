package expo.modules.updates.statemachine;

import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.time.TimeZones;
import org.json.JSONObject;

/* compiled from: UpdatesStateContext.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 <2\u00020\u0001:\u0001<B\u007f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0083\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001J\u0013\u00107\u001a\u00020\u00032\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020:HÖ\u0001J\t\u0010;\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u001cR\u001d\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006="}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateContext;", "", "isUpdateAvailable", "", "isUpdatePending", "isChecking", "isDownloading", "isRestarting", "latestManifest", "Lorg/json/JSONObject;", "downloadedManifest", "rollback", "Lexpo/modules/updates/statemachine/UpdatesStateContextRollback;", "checkError", "Lexpo/modules/updates/statemachine/UpdatesStateError;", "downloadError", "lastCheckForUpdateTime", "Ljava/util/Date;", "(ZZZZZLorg/json/JSONObject;Lorg/json/JSONObject;Lexpo/modules/updates/statemachine/UpdatesStateContextRollback;Lexpo/modules/updates/statemachine/UpdatesStateError;Lexpo/modules/updates/statemachine/UpdatesStateError;Ljava/util/Date;)V", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "getCheckError", "()Lexpo/modules/updates/statemachine/UpdatesStateError;", "getDownloadError", "getDownloadedManifest", "()Lorg/json/JSONObject;", "()Z", "json", "", "", "getJson", "()Ljava/util/Map;", "getLastCheckForUpdateTime", "()Ljava/util/Date;", "getLatestManifest", "getRollback", "()Lexpo/modules/updates/statemachine/UpdatesStateContextRollback;", "writableMap", "Lcom/facebook/react/bridge/WritableMap;", "getWritableMap", "()Lcom/facebook/react/bridge/WritableMap;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class UpdatesStateContext {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<SimpleDateFormat> DATE_FORMATTER$delegate = LazyKt.lazy(new Function0<SimpleDateFormat>() { // from class: expo.modules.updates.statemachine.UpdatesStateContext$Companion$DATE_FORMATTER$2
        @Override // kotlin.jvm.functions.Function0
        public final SimpleDateFormat invoke() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
            return simpleDateFormat;
        }
    });
    private final UpdatesStateError checkError;
    private final UpdatesStateError downloadError;
    private final JSONObject downloadedManifest;
    private final boolean isChecking;
    private final boolean isDownloading;
    private final boolean isRestarting;
    private final boolean isUpdateAvailable;
    private final boolean isUpdatePending;
    private final Date lastCheckForUpdateTime;
    private final JSONObject latestManifest;
    private final UpdatesStateContextRollback rollback;

    public UpdatesStateContext() {
        this(false, false, false, false, false, null, null, null, null, null, null, 2047, null);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsUpdateAvailable() {
        return this.isUpdateAvailable;
    }

    /* renamed from: component10, reason: from getter */
    public final UpdatesStateError getDownloadError() {
        return this.downloadError;
    }

    /* renamed from: component11, reason: from getter */
    public final Date getLastCheckForUpdateTime() {
        return this.lastCheckForUpdateTime;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsUpdatePending() {
        return this.isUpdatePending;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsChecking() {
        return this.isChecking;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsDownloading() {
        return this.isDownloading;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsRestarting() {
        return this.isRestarting;
    }

    /* renamed from: component6, reason: from getter */
    public final JSONObject getLatestManifest() {
        return this.latestManifest;
    }

    /* renamed from: component7, reason: from getter */
    public final JSONObject getDownloadedManifest() {
        return this.downloadedManifest;
    }

    /* renamed from: component8, reason: from getter */
    public final UpdatesStateContextRollback getRollback() {
        return this.rollback;
    }

    /* renamed from: component9, reason: from getter */
    public final UpdatesStateError getCheckError() {
        return this.checkError;
    }

    public final UpdatesStateContext copy(boolean isUpdateAvailable, boolean isUpdatePending, boolean isChecking, boolean isDownloading, boolean isRestarting, JSONObject latestManifest, JSONObject downloadedManifest, UpdatesStateContextRollback rollback, UpdatesStateError checkError, UpdatesStateError downloadError, Date lastCheckForUpdateTime) {
        return new UpdatesStateContext(isUpdateAvailable, isUpdatePending, isChecking, isDownloading, isRestarting, latestManifest, downloadedManifest, rollback, checkError, downloadError, lastCheckForUpdateTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdatesStateContext)) {
            return false;
        }
        UpdatesStateContext updatesStateContext = (UpdatesStateContext) other;
        return this.isUpdateAvailable == updatesStateContext.isUpdateAvailable && this.isUpdatePending == updatesStateContext.isUpdatePending && this.isChecking == updatesStateContext.isChecking && this.isDownloading == updatesStateContext.isDownloading && this.isRestarting == updatesStateContext.isRestarting && Intrinsics.areEqual(this.latestManifest, updatesStateContext.latestManifest) && Intrinsics.areEqual(this.downloadedManifest, updatesStateContext.downloadedManifest) && Intrinsics.areEqual(this.rollback, updatesStateContext.rollback) && Intrinsics.areEqual(this.checkError, updatesStateContext.checkError) && Intrinsics.areEqual(this.downloadError, updatesStateContext.downloadError) && Intrinsics.areEqual(this.lastCheckForUpdateTime, updatesStateContext.lastCheckForUpdateTime);
    }

    public final UpdatesStateError getCheckError() {
        return this.checkError;
    }

    public final UpdatesStateError getDownloadError() {
        return this.downloadError;
    }

    public final JSONObject getDownloadedManifest() {
        return this.downloadedManifest;
    }

    public final Date getLastCheckForUpdateTime() {
        return this.lastCheckForUpdateTime;
    }

    public final JSONObject getLatestManifest() {
        return this.latestManifest;
    }

    public final UpdatesStateContextRollback getRollback() {
        return this.rollback;
    }

    public int hashCode() {
        int m = ((((((((SourceMap$$ExternalSyntheticBackport0.m(this.isUpdateAvailable) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.isUpdatePending)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.isChecking)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.isDownloading)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.isRestarting)) * 31;
        JSONObject jSONObject = this.latestManifest;
        int hashCode = (m + (jSONObject == null ? 0 : jSONObject.hashCode())) * 31;
        JSONObject jSONObject2 = this.downloadedManifest;
        int hashCode2 = (hashCode + (jSONObject2 == null ? 0 : jSONObject2.hashCode())) * 31;
        UpdatesStateContextRollback updatesStateContextRollback = this.rollback;
        int hashCode3 = (hashCode2 + (updatesStateContextRollback == null ? 0 : updatesStateContextRollback.hashCode())) * 31;
        UpdatesStateError updatesStateError = this.checkError;
        int hashCode4 = (hashCode3 + (updatesStateError == null ? 0 : updatesStateError.hashCode())) * 31;
        UpdatesStateError updatesStateError2 = this.downloadError;
        int hashCode5 = (hashCode4 + (updatesStateError2 == null ? 0 : updatesStateError2.hashCode())) * 31;
        Date date = this.lastCheckForUpdateTime;
        return hashCode5 + (date != null ? date.hashCode() : 0);
    }

    public final boolean isChecking() {
        return this.isChecking;
    }

    public final boolean isDownloading() {
        return this.isDownloading;
    }

    public final boolean isRestarting() {
        return this.isRestarting;
    }

    public final boolean isUpdateAvailable() {
        return this.isUpdateAvailable;
    }

    public final boolean isUpdatePending() {
        return this.isUpdatePending;
    }

    public String toString() {
        return "UpdatesStateContext(isUpdateAvailable=" + this.isUpdateAvailable + ", isUpdatePending=" + this.isUpdatePending + ", isChecking=" + this.isChecking + ", isDownloading=" + this.isDownloading + ", isRestarting=" + this.isRestarting + ", latestManifest=" + this.latestManifest + ", downloadedManifest=" + this.downloadedManifest + ", rollback=" + this.rollback + ", checkError=" + this.checkError + ", downloadError=" + this.downloadError + ", lastCheckForUpdateTime=" + this.lastCheckForUpdateTime + ")";
    }

    public UpdatesStateContext(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, JSONObject jSONObject, JSONObject jSONObject2, UpdatesStateContextRollback updatesStateContextRollback, UpdatesStateError updatesStateError, UpdatesStateError updatesStateError2, Date date) {
        this.isUpdateAvailable = z;
        this.isUpdatePending = z2;
        this.isChecking = z3;
        this.isDownloading = z4;
        this.isRestarting = z5;
        this.latestManifest = jSONObject;
        this.downloadedManifest = jSONObject2;
        this.rollback = updatesStateContextRollback;
        this.checkError = updatesStateError;
        this.downloadError = updatesStateError2;
        this.lastCheckForUpdateTime = date;
    }

    public /* synthetic */ UpdatesStateContext(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, JSONObject jSONObject, JSONObject jSONObject2, UpdatesStateContextRollback updatesStateContextRollback, UpdatesStateError updatesStateError, UpdatesStateError updatesStateError2, Date date, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) == 0 ? z5 : false, (i & 32) != 0 ? null : jSONObject, (i & 64) != 0 ? null : jSONObject2, (i & 128) != 0 ? null : updatesStateContextRollback, (i & 256) != 0 ? null : updatesStateError, (i & 512) != 0 ? null : updatesStateError2, (i & 1024) == 0 ? date : null);
    }

    public final Map<String, Object> getJson() {
        Map<String, Object> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to("isUpdateAvailable", Boolean.valueOf(this.isUpdateAvailable)), TuplesKt.to("isUpdatePending", Boolean.valueOf(this.isUpdatePending)), TuplesKt.to("isChecking", Boolean.valueOf(this.isChecking)), TuplesKt.to("isDownloading", Boolean.valueOf(this.isDownloading)), TuplesKt.to("isRestarting", Boolean.valueOf(this.isRestarting)));
        JSONObject jSONObject = this.latestManifest;
        if (jSONObject != null) {
            mutableMapOf.put("latestManifest", jSONObject);
        }
        JSONObject jSONObject2 = this.downloadedManifest;
        if (jSONObject2 != null) {
            mutableMapOf.put("downloadedManifest", jSONObject2);
        }
        UpdatesStateContextRollback updatesStateContextRollback = this.rollback;
        if (updatesStateContextRollback != null) {
            mutableMapOf.put("rollback", updatesStateContextRollback.getJson());
        }
        UpdatesStateError updatesStateError = this.checkError;
        if (updatesStateError != null) {
            mutableMapOf.put("checkError", updatesStateError.getJson());
        }
        UpdatesStateError updatesStateError2 = this.downloadError;
        if (updatesStateError2 != null) {
            mutableMapOf.put("downloadError", updatesStateError2.getJson());
        }
        Date date = this.lastCheckForUpdateTime;
        if (date != null) {
            mutableMapOf.put("lastCheckForUpdateTime", date);
        }
        return mutableMapOf;
    }

    public final WritableMap getWritableMap() {
        WritableMap createMap = Arguments.createMap();
        createMap.putMap("context", Arguments.fromBundle(getBundle()));
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }

    public final Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isUpdateAvailable", this.isUpdateAvailable);
        bundle.putBoolean("isUpdatePending", this.isUpdatePending);
        bundle.putBoolean("isChecking", this.isChecking);
        bundle.putBoolean("isDownloading", this.isDownloading);
        bundle.putBoolean("isRestarting", this.isRestarting);
        JSONObject jSONObject = this.latestManifest;
        if (jSONObject != null) {
            bundle.putString("latestManifestString", jSONObject.toString());
        }
        JSONObject jSONObject2 = this.downloadedManifest;
        if (jSONObject2 != null) {
            bundle.putString("downloadedManifestString", jSONObject2.toString());
        }
        if (this.rollback != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("commitTime", this.rollback.getCommitTimeString());
            Unit unit = Unit.INSTANCE;
            bundle.putBundle("rollback", bundle2);
        }
        if (this.checkError != null) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("message", this.checkError.getMessage());
            bundle.putBundle("checkError", bundle3);
        }
        if (this.downloadError != null) {
            Bundle bundle4 = new Bundle();
            bundle4.putString("message", this.downloadError.getMessage());
            bundle.putBundle("downloadError", bundle4);
        }
        if (this.lastCheckForUpdateTime != null) {
            bundle.putString("lastCheckForUpdateTimeString", INSTANCE.getDATE_FORMATTER().format(this.lastCheckForUpdateTime));
        }
        return bundle;
    }

    /* compiled from: UpdatesStateContext.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateContext$Companion;", "", "()V", "DATE_FORMATTER", "Ljava/text/SimpleDateFormat;", "getDATE_FORMATTER", "()Ljava/text/SimpleDateFormat;", "DATE_FORMATTER$delegate", "Lkotlin/Lazy;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SimpleDateFormat getDATE_FORMATTER() {
            return (SimpleDateFormat) UpdatesStateContext.DATE_FORMATTER$delegate.getValue();
        }
    }
}

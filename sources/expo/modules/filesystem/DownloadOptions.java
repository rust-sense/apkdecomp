package expo.modules.filesystem;

import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileSystemRecords.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003JF\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0007HÖ\u0001R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR*\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\f\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\f\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\f\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lexpo/modules/filesystem/DownloadOptions;", "Lexpo/modules/kotlin/records/Record;", "md5", "", "cache", "headers", "", "", "sessionType", "Lexpo/modules/filesystem/SessionType;", "(ZLjava/lang/Boolean;Ljava/util/Map;Lexpo/modules/filesystem/SessionType;)V", "getCache$annotations", "()V", "getCache", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getHeaders$annotations", "getHeaders", "()Ljava/util/Map;", "getMd5$annotations", "getMd5", "()Z", "getSessionType$annotations", "getSessionType", "()Lexpo/modules/filesystem/SessionType;", "component1", "component2", "component3", "component4", "copy", "(ZLjava/lang/Boolean;Ljava/util/Map;Lexpo/modules/filesystem/SessionType;)Lexpo/modules/filesystem/DownloadOptions;", "equals", "other", "", "hashCode", "", "toString", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DownloadOptions implements Record {
    private final Boolean cache;
    private final Map<String, String> headers;
    private final boolean md5;
    private final SessionType sessionType;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DownloadOptions copy$default(DownloadOptions downloadOptions, boolean z, Boolean bool, Map map, SessionType sessionType, int i, Object obj) {
        if ((i & 1) != 0) {
            z = downloadOptions.md5;
        }
        if ((i & 2) != 0) {
            bool = downloadOptions.cache;
        }
        if ((i & 4) != 0) {
            map = downloadOptions.headers;
        }
        if ((i & 8) != 0) {
            sessionType = downloadOptions.sessionType;
        }
        return downloadOptions.copy(z, bool, map, sessionType);
    }

    @Field
    public static /* synthetic */ void getCache$annotations() {
    }

    @Field
    public static /* synthetic */ void getHeaders$annotations() {
    }

    @Field
    public static /* synthetic */ void getMd5$annotations() {
    }

    @Field
    public static /* synthetic */ void getSessionType$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getMd5() {
        return this.md5;
    }

    /* renamed from: component2, reason: from getter */
    public final Boolean getCache() {
        return this.cache;
    }

    public final Map<String, String> component3() {
        return this.headers;
    }

    /* renamed from: component4, reason: from getter */
    public final SessionType getSessionType() {
        return this.sessionType;
    }

    public final DownloadOptions copy(boolean md5, Boolean cache, Map<String, String> headers, SessionType sessionType) {
        Intrinsics.checkNotNullParameter(sessionType, "sessionType");
        return new DownloadOptions(md5, cache, headers, sessionType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadOptions)) {
            return false;
        }
        DownloadOptions downloadOptions = (DownloadOptions) other;
        return this.md5 == downloadOptions.md5 && Intrinsics.areEqual(this.cache, downloadOptions.cache) && Intrinsics.areEqual(this.headers, downloadOptions.headers) && this.sessionType == downloadOptions.sessionType;
    }

    public final Boolean getCache() {
        return this.cache;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final boolean getMd5() {
        return this.md5;
    }

    public final SessionType getSessionType() {
        return this.sessionType;
    }

    public int hashCode() {
        int m = SourceMap$$ExternalSyntheticBackport0.m(this.md5) * 31;
        Boolean bool = this.cache;
        int hashCode = (m + (bool == null ? 0 : bool.hashCode())) * 31;
        Map<String, String> map = this.headers;
        return ((hashCode + (map != null ? map.hashCode() : 0)) * 31) + this.sessionType.hashCode();
    }

    public String toString() {
        return "DownloadOptions(md5=" + this.md5 + ", cache=" + this.cache + ", headers=" + this.headers + ", sessionType=" + this.sessionType + ")";
    }

    public DownloadOptions(boolean z, Boolean bool, Map<String, String> map, SessionType sessionType) {
        Intrinsics.checkNotNullParameter(sessionType, "sessionType");
        this.md5 = z;
        this.cache = bool;
        this.headers = map;
        this.sessionType = sessionType;
    }

    public /* synthetic */ DownloadOptions(boolean z, Boolean bool, Map map, SessionType sessionType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, bool, map, (i & 8) != 0 ? SessionType.BACKGROUND : sessionType);
    }
}

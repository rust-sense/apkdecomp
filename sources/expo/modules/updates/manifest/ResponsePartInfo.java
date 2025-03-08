package expo.modules.updates.manifest;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ResponseHeaderData.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lexpo/modules/updates/manifest/ResponsePartInfo;", "", "responseHeaderData", "Lexpo/modules/updates/manifest/ResponseHeaderData;", "responsePartHeaderData", "Lexpo/modules/updates/manifest/ResponsePartHeaderData;", "body", "", "(Lexpo/modules/updates/manifest/ResponseHeaderData;Lexpo/modules/updates/manifest/ResponsePartHeaderData;Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "getResponseHeaderData", "()Lexpo/modules/updates/manifest/ResponseHeaderData;", "getResponsePartHeaderData", "()Lexpo/modules/updates/manifest/ResponsePartHeaderData;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ResponsePartInfo {
    private final String body;
    private final ResponseHeaderData responseHeaderData;
    private final ResponsePartHeaderData responsePartHeaderData;

    public static /* synthetic */ ResponsePartInfo copy$default(ResponsePartInfo responsePartInfo, ResponseHeaderData responseHeaderData, ResponsePartHeaderData responsePartHeaderData, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            responseHeaderData = responsePartInfo.responseHeaderData;
        }
        if ((i & 2) != 0) {
            responsePartHeaderData = responsePartInfo.responsePartHeaderData;
        }
        if ((i & 4) != 0) {
            str = responsePartInfo.body;
        }
        return responsePartInfo.copy(responseHeaderData, responsePartHeaderData, str);
    }

    /* renamed from: component1, reason: from getter */
    public final ResponseHeaderData getResponseHeaderData() {
        return this.responseHeaderData;
    }

    /* renamed from: component2, reason: from getter */
    public final ResponsePartHeaderData getResponsePartHeaderData() {
        return this.responsePartHeaderData;
    }

    /* renamed from: component3, reason: from getter */
    public final String getBody() {
        return this.body;
    }

    public final ResponsePartInfo copy(ResponseHeaderData responseHeaderData, ResponsePartHeaderData responsePartHeaderData, String body) {
        Intrinsics.checkNotNullParameter(responseHeaderData, "responseHeaderData");
        Intrinsics.checkNotNullParameter(responsePartHeaderData, "responsePartHeaderData");
        Intrinsics.checkNotNullParameter(body, "body");
        return new ResponsePartInfo(responseHeaderData, responsePartHeaderData, body);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResponsePartInfo)) {
            return false;
        }
        ResponsePartInfo responsePartInfo = (ResponsePartInfo) other;
        return Intrinsics.areEqual(this.responseHeaderData, responsePartInfo.responseHeaderData) && Intrinsics.areEqual(this.responsePartHeaderData, responsePartInfo.responsePartHeaderData) && Intrinsics.areEqual(this.body, responsePartInfo.body);
    }

    public final String getBody() {
        return this.body;
    }

    public final ResponseHeaderData getResponseHeaderData() {
        return this.responseHeaderData;
    }

    public final ResponsePartHeaderData getResponsePartHeaderData() {
        return this.responsePartHeaderData;
    }

    public int hashCode() {
        return (((this.responseHeaderData.hashCode() * 31) + this.responsePartHeaderData.hashCode()) * 31) + this.body.hashCode();
    }

    public String toString() {
        return "ResponsePartInfo(responseHeaderData=" + this.responseHeaderData + ", responsePartHeaderData=" + this.responsePartHeaderData + ", body=" + this.body + ")";
    }

    public ResponsePartInfo(ResponseHeaderData responseHeaderData, ResponsePartHeaderData responsePartHeaderData, String body) {
        Intrinsics.checkNotNullParameter(responseHeaderData, "responseHeaderData");
        Intrinsics.checkNotNullParameter(responsePartHeaderData, "responsePartHeaderData");
        Intrinsics.checkNotNullParameter(body, "body");
        this.responseHeaderData = responseHeaderData;
        this.responsePartHeaderData = responsePartHeaderData;
        this.body = body;
    }
}

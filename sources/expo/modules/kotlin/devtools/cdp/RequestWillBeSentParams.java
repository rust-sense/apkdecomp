package expo.modules.kotlin.devtools.cdp;

import com.google.common.net.HttpHeaders;
import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import io.sentry.SentryBaseEvent;
import java.math.BigDecimal;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CdpNetworkTypes.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB\u007f\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u000e\u0012\n\u0010\u000f\u001a\u00060\u0003j\u0002`\u0010\u0012\n\u0010\u0011\u001a\u00060\u0003j\u0002`\u0012\u0012\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ\r\u0010.\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u001aHÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u000eHÆ\u0003J\r\u00104\u001a\u00060\u0003j\u0002`\u0010HÆ\u0003J\r\u00105\u001a\u00060\u0003j\u0002`\u0012HÆ\u0003J\u0015\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014HÆ\u0003J\t\u00107\u001a\u00020\u0016HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u0091\u0001\u00109\u001a\u00020\u00002\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u000e2\f\b\u0002\u0010\u000f\u001a\u00060\u0003j\u0002`\u00102\f\b\u0002\u0010\u0011\u001a\u00060\u0003j\u0002`\u00122\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u001aHÆ\u0001J\u0013\u0010:\u001a\u00020\u00162\b\u0010;\u001a\u0004\u0018\u00010<HÖ\u0003J\t\u0010=\u001a\u00020>HÖ\u0001J\b\u0010?\u001a\u00020@H\u0016J\t\u0010A\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u0015\u0010\u000f\u001a\u00060\u0003j\u0002`\u0010¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0015\u0010\u0011\u001a\u00060\u0003j\u0002`\u0012¢\u0006\b\n\u0000\u001a\u0004\b-\u0010*¨\u0006B"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/RequestWillBeSentParams;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "now", "Ljava/math/BigDecimal;", "requestId", "", "Lexpo/modules/kotlin/devtools/cdp/RequestId;", SentryBaseEvent.JsonKeys.REQUEST, "Lokhttp3/Request;", "redirectResponse", "Lokhttp3/Response;", "(Ljava/math/BigDecimal;Ljava/lang/String;Lokhttp3/Request;Lokhttp3/Response;)V", "loaderId", "documentURL", "Lexpo/modules/kotlin/devtools/cdp/Request;", "timestamp", "Lexpo/modules/kotlin/devtools/cdp/MonotonicTime;", "wallTime", "Lexpo/modules/kotlin/devtools/cdp/TimeSinceEpoch;", "initiator", "", "redirectHasExtraInfo", "", "Lexpo/modules/kotlin/devtools/cdp/Response;", "referrerPolicy", "type", "Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lexpo/modules/kotlin/devtools/cdp/Request;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/util/Map;ZLexpo/modules/kotlin/devtools/cdp/Response;Ljava/lang/String;Lexpo/modules/kotlin/devtools/cdp/ResourceType;)V", "getDocumentURL", "()Ljava/lang/String;", "getInitiator", "()Ljava/util/Map;", "getLoaderId", "getRedirectHasExtraInfo", "()Z", "getRedirectResponse", "()Lexpo/modules/kotlin/devtools/cdp/Response;", "getReferrerPolicy", "getRequest", "()Lexpo/modules/kotlin/devtools/cdp/Request;", "getRequestId", "getTimestamp", "()Ljava/math/BigDecimal;", "getType", "()Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "getWallTime", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toJSONObject", "Lorg/json/JSONObject;", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class RequestWillBeSentParams implements JsonSerializable {
    private final String documentURL;
    private final Map<String, String> initiator;
    private final String loaderId;
    private final boolean redirectHasExtraInfo;
    private final Response redirectResponse;
    private final String referrerPolicy;
    private final Request request;
    private final String requestId;
    private final BigDecimal timestamp;
    private final ResourceType type;
    private final BigDecimal wallTime;

    /* renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* renamed from: component10, reason: from getter */
    public final String getReferrerPolicy() {
        return this.referrerPolicy;
    }

    /* renamed from: component11, reason: from getter */
    public final ResourceType getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLoaderId() {
        return this.loaderId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDocumentURL() {
        return this.documentURL;
    }

    /* renamed from: component4, reason: from getter */
    public final Request getRequest() {
        return this.request;
    }

    /* renamed from: component5, reason: from getter */
    public final BigDecimal getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component6, reason: from getter */
    public final BigDecimal getWallTime() {
        return this.wallTime;
    }

    public final Map<String, String> component7() {
        return this.initiator;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getRedirectHasExtraInfo() {
        return this.redirectHasExtraInfo;
    }

    /* renamed from: component9, reason: from getter */
    public final Response getRedirectResponse() {
        return this.redirectResponse;
    }

    public final RequestWillBeSentParams copy(String requestId, String loaderId, String documentURL, Request request, BigDecimal timestamp, BigDecimal wallTime, Map<String, String> initiator, boolean redirectHasExtraInfo, Response redirectResponse, String referrerPolicy, ResourceType type) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(loaderId, "loaderId");
        Intrinsics.checkNotNullParameter(documentURL, "documentURL");
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(wallTime, "wallTime");
        Intrinsics.checkNotNullParameter(initiator, "initiator");
        Intrinsics.checkNotNullParameter(referrerPolicy, "referrerPolicy");
        Intrinsics.checkNotNullParameter(type, "type");
        return new RequestWillBeSentParams(requestId, loaderId, documentURL, request, timestamp, wallTime, initiator, redirectHasExtraInfo, redirectResponse, referrerPolicy, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RequestWillBeSentParams)) {
            return false;
        }
        RequestWillBeSentParams requestWillBeSentParams = (RequestWillBeSentParams) other;
        return Intrinsics.areEqual(this.requestId, requestWillBeSentParams.requestId) && Intrinsics.areEqual(this.loaderId, requestWillBeSentParams.loaderId) && Intrinsics.areEqual(this.documentURL, requestWillBeSentParams.documentURL) && Intrinsics.areEqual(this.request, requestWillBeSentParams.request) && Intrinsics.areEqual(this.timestamp, requestWillBeSentParams.timestamp) && Intrinsics.areEqual(this.wallTime, requestWillBeSentParams.wallTime) && Intrinsics.areEqual(this.initiator, requestWillBeSentParams.initiator) && this.redirectHasExtraInfo == requestWillBeSentParams.redirectHasExtraInfo && Intrinsics.areEqual(this.redirectResponse, requestWillBeSentParams.redirectResponse) && Intrinsics.areEqual(this.referrerPolicy, requestWillBeSentParams.referrerPolicy) && this.type == requestWillBeSentParams.type;
    }

    public final String getDocumentURL() {
        return this.documentURL;
    }

    public final Map<String, String> getInitiator() {
        return this.initiator;
    }

    public final String getLoaderId() {
        return this.loaderId;
    }

    public final boolean getRedirectHasExtraInfo() {
        return this.redirectHasExtraInfo;
    }

    public final Response getRedirectResponse() {
        return this.redirectResponse;
    }

    public final String getReferrerPolicy() {
        return this.referrerPolicy;
    }

    public final Request getRequest() {
        return this.request;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final BigDecimal getTimestamp() {
        return this.timestamp;
    }

    public final ResourceType getType() {
        return this.type;
    }

    public final BigDecimal getWallTime() {
        return this.wallTime;
    }

    public int hashCode() {
        int hashCode = ((((((((((((((this.requestId.hashCode() * 31) + this.loaderId.hashCode()) * 31) + this.documentURL.hashCode()) * 31) + this.request.hashCode()) * 31) + this.timestamp.hashCode()) * 31) + this.wallTime.hashCode()) * 31) + this.initiator.hashCode()) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.redirectHasExtraInfo)) * 31;
        Response response = this.redirectResponse;
        return ((((hashCode + (response == null ? 0 : response.hashCode())) * 31) + this.referrerPolicy.hashCode()) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "RequestWillBeSentParams(requestId=" + this.requestId + ", loaderId=" + this.loaderId + ", documentURL=" + this.documentURL + ", request=" + this.request + ", timestamp=" + this.timestamp + ", wallTime=" + this.wallTime + ", initiator=" + this.initiator + ", redirectHasExtraInfo=" + this.redirectHasExtraInfo + ", redirectResponse=" + this.redirectResponse + ", referrerPolicy=" + this.referrerPolicy + ", type=" + this.type + ")";
    }

    public RequestWillBeSentParams(String requestId, String loaderId, String documentURL, Request request, BigDecimal timestamp, BigDecimal wallTime, Map<String, String> initiator, boolean z, Response response, String referrerPolicy, ResourceType type) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(loaderId, "loaderId");
        Intrinsics.checkNotNullParameter(documentURL, "documentURL");
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(wallTime, "wallTime");
        Intrinsics.checkNotNullParameter(initiator, "initiator");
        Intrinsics.checkNotNullParameter(referrerPolicy, "referrerPolicy");
        Intrinsics.checkNotNullParameter(type, "type");
        this.requestId = requestId;
        this.loaderId = loaderId;
        this.documentURL = documentURL;
        this.request = request;
        this.timestamp = timestamp;
        this.wallTime = wallTime;
        this.initiator = initiator;
        this.redirectHasExtraInfo = z;
        this.redirectResponse = response;
        this.referrerPolicy = referrerPolicy;
        this.type = type;
    }

    public /* synthetic */ RequestWillBeSentParams(String str, String str2, String str3, Request request, BigDecimal bigDecimal, BigDecimal bigDecimal2, Map map, boolean z, Response response, String str4, ResourceType resourceType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "mobile" : str3, request, bigDecimal, bigDecimal2, (i & 64) != 0 ? MapsKt.mapOf(TuplesKt.to("type", "script")) : map, z, response, (i & 512) != 0 ? HttpHeaders.ReferrerPolicyValues.NO_REFERRER : str4, resourceType);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RequestWillBeSentParams(BigDecimal now, String requestId, okhttp3.Request request, okhttp3.Response response) {
        this(requestId, null, null, new Request(request), now, now, null, response != null, response != null ? new Response(response) : null, null, ResourceType.OTHER, 582, null);
        Intrinsics.checkNotNullParameter(now, "now");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(request, "request");
    }

    @Override // expo.modules.kotlin.devtools.cdp.JsonSerializable
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestId", this.requestId);
        jSONObject.put("loaderId", this.loaderId);
        jSONObject.put("documentURL", this.documentURL);
        jSONObject.put(SentryBaseEvent.JsonKeys.REQUEST, this.request.toJSONObject());
        jSONObject.put("timestamp", this.timestamp);
        jSONObject.put("wallTime", this.wallTime);
        jSONObject.put("initiator", new JSONObject(this.initiator));
        jSONObject.put("redirectHasExtraInfo", this.redirectHasExtraInfo);
        Response response = this.redirectResponse;
        if (response != null) {
            jSONObject.put("redirectResponse", response.toJSONObject());
        }
        jSONObject.put("referrerPolicy", this.referrerPolicy);
        jSONObject.put("type", this.type.getValue());
        return jSONObject;
    }
}

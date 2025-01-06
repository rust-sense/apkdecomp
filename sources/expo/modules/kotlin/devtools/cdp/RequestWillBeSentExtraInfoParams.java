package expo.modules.kotlin.devtools.cdp;

import expo.modules.kotlin.devtools.OkHttpHeadersExtensionKt;
import io.sentry.SentryBaseEvent;
import java.math.BigDecimal;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CdpNetworkTypes.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tBG\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b\u0012\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000bj\u0002`\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\r\u0010\u0018\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J\u0015\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000bHÆ\u0003J\u0019\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000bj\u0002`\rHÆ\u0003J\t\u0010\u001b\u001a\u00020\u000fHÆ\u0003JQ\u0010\u001c\u001a\u00020\u00002\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b2\u0018\b\u0002\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000bj\u0002`\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\b\u0010#\u001a\u00020$H\u0016J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R!\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000bj\u0002`\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006&"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/RequestWillBeSentExtraInfoParams;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "now", "Ljava/math/BigDecimal;", "requestId", "", "Lexpo/modules/kotlin/devtools/cdp/RequestId;", SentryBaseEvent.JsonKeys.REQUEST, "Lokhttp3/Request;", "(Ljava/math/BigDecimal;Ljava/lang/String;Lokhttp3/Request;)V", "associatedCookies", "", "headers", "Lexpo/modules/kotlin/devtools/cdp/Headers;", "connectTiming", "Lexpo/modules/kotlin/devtools/cdp/ConnectTiming;", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Lexpo/modules/kotlin/devtools/cdp/ConnectTiming;)V", "getAssociatedCookies", "()Ljava/util/Map;", "getConnectTiming", "()Lexpo/modules/kotlin/devtools/cdp/ConnectTiming;", "getHeaders", "getRequestId", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toJSONObject", "Lorg/json/JSONObject;", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class RequestWillBeSentExtraInfoParams implements JsonSerializable {
    private final Map<String, String> associatedCookies;
    private final ConnectTiming connectTiming;
    private final Map<String, String> headers;
    private final String requestId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RequestWillBeSentExtraInfoParams copy$default(RequestWillBeSentExtraInfoParams requestWillBeSentExtraInfoParams, String str, Map map, Map map2, ConnectTiming connectTiming, int i, Object obj) {
        if ((i & 1) != 0) {
            str = requestWillBeSentExtraInfoParams.requestId;
        }
        if ((i & 2) != 0) {
            map = requestWillBeSentExtraInfoParams.associatedCookies;
        }
        if ((i & 4) != 0) {
            map2 = requestWillBeSentExtraInfoParams.headers;
        }
        if ((i & 8) != 0) {
            connectTiming = requestWillBeSentExtraInfoParams.connectTiming;
        }
        return requestWillBeSentExtraInfoParams.copy(str, map, map2, connectTiming);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    public final Map<String, String> component2() {
        return this.associatedCookies;
    }

    public final Map<String, String> component3() {
        return this.headers;
    }

    /* renamed from: component4, reason: from getter */
    public final ConnectTiming getConnectTiming() {
        return this.connectTiming;
    }

    public final RequestWillBeSentExtraInfoParams copy(String requestId, Map<String, String> associatedCookies, Map<String, String> headers, ConnectTiming connectTiming) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(associatedCookies, "associatedCookies");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(connectTiming, "connectTiming");
        return new RequestWillBeSentExtraInfoParams(requestId, associatedCookies, headers, connectTiming);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RequestWillBeSentExtraInfoParams)) {
            return false;
        }
        RequestWillBeSentExtraInfoParams requestWillBeSentExtraInfoParams = (RequestWillBeSentExtraInfoParams) other;
        return Intrinsics.areEqual(this.requestId, requestWillBeSentExtraInfoParams.requestId) && Intrinsics.areEqual(this.associatedCookies, requestWillBeSentExtraInfoParams.associatedCookies) && Intrinsics.areEqual(this.headers, requestWillBeSentExtraInfoParams.headers) && Intrinsics.areEqual(this.connectTiming, requestWillBeSentExtraInfoParams.connectTiming);
    }

    public final Map<String, String> getAssociatedCookies() {
        return this.associatedCookies;
    }

    public final ConnectTiming getConnectTiming() {
        return this.connectTiming;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        return (((((this.requestId.hashCode() * 31) + this.associatedCookies.hashCode()) * 31) + this.headers.hashCode()) * 31) + this.connectTiming.hashCode();
    }

    public String toString() {
        return "RequestWillBeSentExtraInfoParams(requestId=" + this.requestId + ", associatedCookies=" + this.associatedCookies + ", headers=" + this.headers + ", connectTiming=" + this.connectTiming + ")";
    }

    public RequestWillBeSentExtraInfoParams(String requestId, Map<String, String> associatedCookies, Map<String, String> headers, ConnectTiming connectTiming) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(associatedCookies, "associatedCookies");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(connectTiming, "connectTiming");
        this.requestId = requestId;
        this.associatedCookies = associatedCookies;
        this.headers = headers;
        this.connectTiming = connectTiming;
    }

    public /* synthetic */ RequestWillBeSentExtraInfoParams(String str, Map map, Map map2, ConnectTiming connectTiming, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? MapsKt.emptyMap() : map, map2, connectTiming);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RequestWillBeSentExtraInfoParams(BigDecimal now, String requestId, okhttp3.Request request) {
        this(requestId, null, OkHttpHeadersExtensionKt.toSingleMap(request.headers()), new ConnectTiming(now), 2, null);
        Intrinsics.checkNotNullParameter(now, "now");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(request, "request");
    }

    @Override // expo.modules.kotlin.devtools.cdp.JsonSerializable
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestId", this.requestId);
        jSONObject.put("associatedCookies", new JSONObject(this.associatedCookies));
        jSONObject.put("headers", new JSONObject(this.headers));
        jSONObject.put("connectTiming", this.connectTiming.toJSONObject());
        return jSONObject;
    }
}

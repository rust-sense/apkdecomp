package expo.modules.kotlin.devtools.cdp;

import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CdpNetworkTypes.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BE\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000bj\u0002`\f\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\u0019\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000bj\u0002`\fHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u000fHÆ\u0003JU\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000bj\u0002`\f2\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\bHÖ\u0001J\b\u0010'\u001a\u00020(H\u0016J\t\u0010)\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R!\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000bj\u0002`\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016¨\u0006*"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/Response;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", io.sentry.protocol.Response.TYPE, "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "url", "", "status", "", "statusText", "headers", "", "Lexpo/modules/kotlin/devtools/cdp/Headers;", "mimeType", "encodedDataLength", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Ljava/lang/String;J)V", "getEncodedDataLength", "()J", "getHeaders", "()Ljava/util/Map;", "getMimeType", "()Ljava/lang/String;", "getStatus", "()I", "getStatusText", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toJSONObject", "Lorg/json/JSONObject;", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Response implements JsonSerializable {
    private final long encodedDataLength;
    private final Map<String, String> headers;
    private final String mimeType;
    private final int status;
    private final String statusText;
    private final String url;

    public static /* synthetic */ Response copy$default(Response response, String str, int i, String str2, Map map, String str3, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = response.url;
        }
        if ((i2 & 2) != 0) {
            i = response.status;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            str2 = response.statusText;
        }
        String str4 = str2;
        if ((i2 & 8) != 0) {
            map = response.headers;
        }
        Map map2 = map;
        if ((i2 & 16) != 0) {
            str3 = response.mimeType;
        }
        String str5 = str3;
        if ((i2 & 32) != 0) {
            j = response.encodedDataLength;
        }
        return response.copy(str, i3, str4, map2, str5, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component2, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* renamed from: component3, reason: from getter */
    public final String getStatusText() {
        return this.statusText;
    }

    public final Map<String, String> component4() {
        return this.headers;
    }

    /* renamed from: component5, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    /* renamed from: component6, reason: from getter */
    public final long getEncodedDataLength() {
        return this.encodedDataLength;
    }

    public final Response copy(String url, int status, String statusText, Map<String, String> headers, String mimeType, long encodedDataLength) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(statusText, "statusText");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        return new Response(url, status, statusText, headers, mimeType, encodedDataLength);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Response)) {
            return false;
        }
        Response response = (Response) other;
        return Intrinsics.areEqual(this.url, response.url) && this.status == response.status && Intrinsics.areEqual(this.statusText, response.statusText) && Intrinsics.areEqual(this.headers, response.headers) && Intrinsics.areEqual(this.mimeType, response.mimeType) && this.encodedDataLength == response.encodedDataLength;
    }

    public final long getEncodedDataLength() {
        return this.encodedDataLength;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getStatusText() {
        return this.statusText;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return (((((((((this.url.hashCode() * 31) + this.status) * 31) + this.statusText.hashCode()) * 31) + this.headers.hashCode()) * 31) + this.mimeType.hashCode()) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.encodedDataLength);
    }

    public String toString() {
        return "Response(url=" + this.url + ", status=" + this.status + ", statusText=" + this.statusText + ", headers=" + this.headers + ", mimeType=" + this.mimeType + ", encodedDataLength=" + this.encodedDataLength + ")";
    }

    public Response(String url, int i, String statusText, Map<String, String> headers, String mimeType, long j) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(statusText, "statusText");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        this.url = url;
        this.status = i;
        this.statusText = statusText;
        this.headers = headers;
        this.mimeType = mimeType;
        this.encodedDataLength = j;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Response(okhttp3.Response r10) {
        /*
            r9 = this;
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            okhttp3.Request r0 = r10.request()
            okhttp3.HttpUrl r0 = r0.url()
            java.lang.String r2 = r0.getUrl()
            int r3 = r10.code()
            java.lang.String r4 = r10.message()
            okhttp3.Headers r0 = r10.headers()
            java.util.Map r5 = expo.modules.kotlin.devtools.OkHttpHeadersExtensionKt.toSingleMap(r0)
            java.lang.String r0 = "Content-Type"
            java.lang.String r1 = ""
            java.lang.String r0 = r10.header(r0, r1)
            if (r0 != 0) goto L2d
            r6 = r1
            goto L2e
        L2d:
            r6 = r0
        L2e:
            okhttp3.ResponseBody r10 = r10.body()
            if (r10 == 0) goto L39
            long r0 = r10.getContentLength()
            goto L3b
        L39:
            r0 = 0
        L3b:
            r7 = r0
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.Response.<init>(okhttp3.Response):void");
    }

    @Override // expo.modules.kotlin.devtools.cdp.JsonSerializable
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", this.url);
        jSONObject.put("status", this.status);
        jSONObject.put("statusText", this.statusText);
        jSONObject.put("headers", new JSONObject(this.headers));
        jSONObject.put("mimeType", this.mimeType);
        jSONObject.put("encodedDataLength", this.encodedDataLength);
        return jSONObject;
    }
}

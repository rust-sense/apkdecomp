package expo.modules.kotlin.devtools.cdp;

import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CdpNetworkTypes.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\r\u0010\u0014\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J+\u0010\u0017\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010¨\u0006 "}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/ExpoReceivedResponseBodyParams;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "requestId", "", "Lexpo/modules/kotlin/devtools/cdp/RequestId;", "body", "Lokhttp3/ResponseBody;", "(Ljava/lang/String;Lokhttp3/ResponseBody;)V", "base64Encoded", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getBase64Encoded", "()Z", "setBase64Encoded", "(Z)V", "getBody", "()Ljava/lang/String;", "setBody", "(Ljava/lang/String;)V", "getRequestId", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toJSONObject", "Lorg/json/JSONObject;", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ExpoReceivedResponseBodyParams implements JsonSerializable {
    private boolean base64Encoded;
    private String body;
    private final String requestId;

    public static /* synthetic */ ExpoReceivedResponseBodyParams copy$default(ExpoReceivedResponseBodyParams expoReceivedResponseBodyParams, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = expoReceivedResponseBodyParams.requestId;
        }
        if ((i & 2) != 0) {
            str2 = expoReceivedResponseBodyParams.body;
        }
        if ((i & 4) != 0) {
            z = expoReceivedResponseBodyParams.base64Encoded;
        }
        return expoReceivedResponseBodyParams.copy(str, str2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getBody() {
        return this.body;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getBase64Encoded() {
        return this.base64Encoded;
    }

    public final ExpoReceivedResponseBodyParams copy(String requestId, String body, boolean base64Encoded) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(body, "body");
        return new ExpoReceivedResponseBodyParams(requestId, body, base64Encoded);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExpoReceivedResponseBodyParams)) {
            return false;
        }
        ExpoReceivedResponseBodyParams expoReceivedResponseBodyParams = (ExpoReceivedResponseBodyParams) other;
        return Intrinsics.areEqual(this.requestId, expoReceivedResponseBodyParams.requestId) && Intrinsics.areEqual(this.body, expoReceivedResponseBodyParams.body) && this.base64Encoded == expoReceivedResponseBodyParams.base64Encoded;
    }

    public final boolean getBase64Encoded() {
        return this.base64Encoded;
    }

    public final String getBody() {
        return this.body;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        return (((this.requestId.hashCode() * 31) + this.body.hashCode()) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.base64Encoded);
    }

    public final void setBase64Encoded(boolean z) {
        this.base64Encoded = z;
    }

    public final void setBody(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.body = str;
    }

    public String toString() {
        return "ExpoReceivedResponseBodyParams(requestId=" + this.requestId + ", body=" + this.body + ", base64Encoded=" + this.base64Encoded + ")";
    }

    public ExpoReceivedResponseBodyParams(String requestId, String body, boolean z) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(body, "body");
        this.requestId = requestId;
        this.body = body;
        this.base64Encoded = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003e, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r5.subtype(), "json") != false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ExpoReceivedResponseBodyParams(java.lang.String r5, okhttp3.ResponseBody r6) {
        /*
            r4 = this;
            java.lang.String r0 = "requestId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "body"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = ""
            r1 = 0
            r4.<init>(r5, r0, r1)
            okhttp3.MediaType r5 = r6.get$contentType()
            r0 = 0
            if (r5 == 0) goto L1c
            java.lang.String r2 = r5.type()
            goto L1d
        L1c:
            r2 = r0
        L1d:
            java.lang.String r3 = "text"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            r3 = 1
            if (r2 != 0) goto L40
            if (r5 == 0) goto L2c
            java.lang.String r0 = r5.type()
        L2c:
            java.lang.String r2 = "application"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r0 == 0) goto L41
            java.lang.String r5 = r5.subtype()
            java.lang.String r0 = "json"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            if (r5 == 0) goto L41
        L40:
            r1 = r3
        L41:
            if (r1 == 0) goto L48
            java.lang.String r5 = r6.string()
            goto L54
        L48:
            okio.BufferedSource r5 = r6.getBodySource()
            okio.ByteString r5 = r5.readByteString()
            java.lang.String r5 = r5.base64()
        L54:
            r4.body = r5
            r5 = r1 ^ 1
            r4.base64Encoded = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.ExpoReceivedResponseBodyParams.<init>(java.lang.String, okhttp3.ResponseBody):void");
    }

    @Override // expo.modules.kotlin.devtools.cdp.JsonSerializable
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestId", this.requestId);
        jSONObject.put("body", this.body);
        jSONObject.put("base64Encoded", this.base64Encoded);
        return jSONObject;
    }
}

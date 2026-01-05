package expo.modules.kotlin.devtools.cdp;

import io.sentry.protocol.Message;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: CdpNetworkTypes.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\r\u0010\r\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0006\u0010\u0014\u001a\u00020\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/Event;", "", Request.JsonKeys.METHOD, "", Message.JsonKeys.PARAMS, "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "Lexpo/modules/kotlin/devtools/cdp/EventParams;", "(Ljava/lang/String;Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;)V", "getMethod", "()Ljava/lang/String;", "getParams", "()Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toJson", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Event {
    private final String method;
    private final JsonSerializable params;

    public static /* synthetic */ Event copy$default(Event event, String str, JsonSerializable jsonSerializable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = event.method;
        }
        if ((i & 2) != 0) {
            jsonSerializable = event.params;
        }
        return event.copy(str, jsonSerializable);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMethod() {
        return this.method;
    }

    /* renamed from: component2, reason: from getter */
    public final JsonSerializable getParams() {
        return this.params;
    }

    public final Event copy(String method, JsonSerializable params) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(params, "params");
        return new Event(method, params);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Event)) {
            return false;
        }
        Event event = (Event) other;
        return Intrinsics.areEqual(this.method, event.method) && Intrinsics.areEqual(this.params, event.params);
    }

    public final String getMethod() {
        return this.method;
    }

    public final JsonSerializable getParams() {
        return this.params;
    }

    public int hashCode() {
        return (this.method.hashCode() * 31) + this.params.hashCode();
    }

    public String toString() {
        return "Event(method=" + this.method + ", params=" + this.params + ")";
    }

    public Event(String method, JsonSerializable params) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(params, "params");
        this.method = method;
        this.params = params;
    }

    public final String toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Request.JsonKeys.METHOD, this.method);
        jSONObject.put(Message.JsonKeys.PARAMS, this.params.toJSONObject());
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return jSONObject2;
    }
}

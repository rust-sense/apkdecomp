package expo.modules.kotlin.devtools;

import kotlin.Metadata;
import okhttp3.Response;

/* compiled from: ExpoNetworkInspectOkHttpInterceptors.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lexpo/modules/kotlin/devtools/RedirectResponse;", "", "()V", "priorResponse", "Lokhttp3/Response;", "getPriorResponse", "()Lokhttp3/Response;", "setPriorResponse", "(Lokhttp3/Response;)V", "requestId", "", "getRequestId", "()Ljava/lang/String;", "setRequestId", "(Ljava/lang/String;)V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RedirectResponse {
    private Response priorResponse;
    private String requestId;

    public final Response getPriorResponse() {
        return this.priorResponse;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final void setPriorResponse(Response response) {
        this.priorResponse = response;
    }

    public final void setRequestId(String str) {
        this.requestId = str;
    }
}

package expo.modules.kotlin.devtools;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: ExpoNetworkInspectOkHttpInterceptors.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpNetworkInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoNetworkInspectOkHttpNetworkInterceptor implements Interceptor {
    public static final long MAX_BODY_SIZE = 1048576;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        String valueOf;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        try {
            RedirectResponse redirectResponse = (RedirectResponse) request.tag(RedirectResponse.class);
            if (redirectResponse == null || (valueOf = redirectResponse.getRequestId()) == null) {
                valueOf = String.valueOf(request.hashCode());
            }
            ExpoNetworkInspectOkHttpInterceptorsKt.getDelegate().willSendRequest(valueOf, request, redirectResponse != null ? redirectResponse.getPriorResponse() : null);
            if (proceed.isRedirect()) {
                RedirectResponse redirectResponse2 = (RedirectResponse) proceed.request().tag(RedirectResponse.class);
                if (redirectResponse2 != null) {
                    redirectResponse2.setRequestId(valueOf);
                    redirectResponse2.setPriorResponse(proceed);
                }
            } else {
                ResponseBody peekResponseBody$default = ExpoNetworkInspectOkHttpInterceptorsKt.peekResponseBody$default(proceed, 0L, 2, null);
                ExpoNetworkInspectOkHttpInterceptorsKt.getDelegate().didReceiveResponse(valueOf, request, proceed, peekResponseBody$default);
                if (peekResponseBody$default != null) {
                    peekResponseBody$default.close();
                }
            }
        } catch (Exception e) {
            Log.e("ExpoNetworkInspector", "Failed to send network request CDP event", e);
        }
        return proceed;
    }
}

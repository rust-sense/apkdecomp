package expo.modules.kotlin.devtools;

import androidx.core.app.NotificationCompat;
import expo.modules.kotlin.devtools.cdp.Event;
import expo.modules.kotlin.devtools.cdp.ExpoReceivedResponseBodyParams;
import expo.modules.kotlin.devtools.cdp.LoadingFinishedParams;
import expo.modules.kotlin.devtools.cdp.RequestWillBeSentExtraInfoParams;
import expo.modules.kotlin.devtools.cdp.RequestWillBeSentParams;
import expo.modules.kotlin.devtools.cdp.ResponseReceivedParams;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Request;
import okhttp3.ResponseBody;

/* compiled from: ExpoRequestCdpInterceptor.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\"\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u0012H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoRequestCdpInterceptor;", "Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpInterceptorsDelegate;", "()V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope$expo_modules_core_release", "()Lkotlinx/coroutines/CoroutineScope;", "setCoroutineScope$expo_modules_core_release", "(Lkotlinx/coroutines/CoroutineScope;)V", "delegate", "Lexpo/modules/kotlin/devtools/ExpoRequestCdpInterceptor$Delegate;", "didReceiveResponse", "", "requestId", "", SentryBaseEvent.JsonKeys.REQUEST, "Lokhttp3/Request;", Response.TYPE, "Lokhttp3/Response;", "body", "Lokhttp3/ResponseBody;", "dispatchEvent", NotificationCompat.CATEGORY_EVENT, "Lexpo/modules/kotlin/devtools/cdp/Event;", "setDelegate", "willSendRequest", "redirectResponse", "Delegate", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoRequestCdpInterceptor implements ExpoNetworkInspectOkHttpInterceptorsDelegate {
    public static final ExpoRequestCdpInterceptor INSTANCE = new ExpoRequestCdpInterceptor();
    private static CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
    private static Delegate delegate;

    /* compiled from: ExpoRequestCdpInterceptor.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoRequestCdpInterceptor$Delegate;", "", "dispatch", "", NotificationCompat.CATEGORY_EVENT, "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Delegate {
        void dispatch(String event);
    }

    public final CoroutineScope getCoroutineScope$expo_modules_core_release() {
        return coroutineScope;
    }

    public final void setCoroutineScope$expo_modules_core_release(CoroutineScope coroutineScope2) {
        Intrinsics.checkNotNullParameter(coroutineScope2, "<set-?>");
        coroutineScope = coroutineScope2;
    }

    private ExpoRequestCdpInterceptor() {
    }

    public final void setDelegate(Delegate delegate2) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ExpoRequestCdpInterceptor$setDelegate$1(this, delegate2, null), 3, null);
    }

    private final void dispatchEvent(Event event) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ExpoRequestCdpInterceptor$dispatchEvent$1(event, null), 3, null);
    }

    @Override // expo.modules.kotlin.devtools.ExpoNetworkInspectOkHttpInterceptorsDelegate
    public void willSendRequest(String requestId, Request request, okhttp3.Response redirectResponse) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(request, "request");
        BigDecimal scale = new BigDecimal(System.currentTimeMillis() / 1000.0d).setScale(3, RoundingMode.CEILING);
        Intrinsics.checkNotNull(scale);
        dispatchEvent(new Event("Network.requestWillBeSent", new RequestWillBeSentParams(scale, requestId, request, redirectResponse)));
        dispatchEvent(new Event("Network.requestWillBeSentExtraInfo", new RequestWillBeSentExtraInfoParams(scale, requestId, request)));
    }

    @Override // expo.modules.kotlin.devtools.ExpoNetworkInspectOkHttpInterceptorsDelegate
    public void didReceiveResponse(String requestId, Request request, okhttp3.Response response, ResponseBody body) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(response, "response");
        BigDecimal scale = new BigDecimal(System.currentTimeMillis() / 1000.0d).setScale(3, RoundingMode.CEILING);
        Intrinsics.checkNotNull(scale);
        dispatchEvent(new Event("Network.responseReceived", new ResponseReceivedParams(scale, requestId, response)));
        if (body != null) {
            dispatchEvent(new Event("Expo(Network.receivedResponseBody)", new ExpoReceivedResponseBodyParams(requestId, body)));
        }
        dispatchEvent(new Event("Network.loadingFinished", new LoadingFinishedParams(scale, requestId, response)));
    }
}

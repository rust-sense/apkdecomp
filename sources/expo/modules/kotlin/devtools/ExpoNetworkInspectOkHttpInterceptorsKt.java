package expo.modules.kotlin.devtools;

import com.google.common.net.HttpHeaders;
import io.sentry.protocol.Response;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Source;

/* compiled from: ExpoNetworkInspectOkHttpInterceptors.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"TAG", "", "delegate", "Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpInterceptorsDelegate;", "getDelegate", "()Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpInterceptorsDelegate;", "peekResponseBody", "Lokhttp3/ResponseBody;", Response.TYPE, "Lokhttp3/Response;", "byteCount", "", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoNetworkInspectOkHttpInterceptorsKt {
    private static final String TAG = "ExpoNetworkInspector";
    private static final ExpoNetworkInspectOkHttpInterceptorsDelegate delegate = ExpoRequestCdpInterceptor.INSTANCE;

    public static final ExpoNetworkInspectOkHttpInterceptorsDelegate getDelegate() {
        return delegate;
    }

    public static /* synthetic */ ResponseBody peekResponseBody$default(okhttp3.Response response, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 1048576;
        }
        return peekResponseBody(response, j);
    }

    public static final ResponseBody peekResponseBody(okhttp3.Response response, long j) {
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        BufferedSource peek = body.getBodySource().peek();
        try {
            if (peek.request(1 + j)) {
                return null;
            }
        } catch (IOException unused) {
        }
        if (StringsKt.equals(okhttp3.Response.header$default(response, HttpHeaders.CONTENT_ENCODING, null, 2, null), "gzip", true)) {
            peek = Okio.buffer(new GzipSource(peek));
            peek.request(j);
        }
        Buffer buffer = new Buffer();
        buffer.write((Source) peek, Math.min(j, peek.getBuffer().size()));
        return ResponseBody.INSTANCE.create(buffer, body.get$contentType(), buffer.size());
    }
}

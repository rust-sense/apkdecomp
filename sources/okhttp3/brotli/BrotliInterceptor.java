package okhttp3.brotli;

import com.google.common.net.HttpHeaders;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import org.brotli.dec.BrotliInputStream;

/* compiled from: BrotliInterceptor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Lokhttp3/brotli/BrotliInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "uncompress", Response.TYPE, "uncompress$okhttp_brotli", "okhttp-brotli"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class BrotliInterceptor implements Interceptor {
    public static final BrotliInterceptor INSTANCE = new BrotliInterceptor();

    private BrotliInterceptor() {
    }

    @Override // okhttp3.Interceptor
    public okhttp3.Response intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        if (chain.request().header(HttpHeaders.ACCEPT_ENCODING) == null) {
            return uncompress$okhttp_brotli(chain.proceed(chain.request().newBuilder().header(HttpHeaders.ACCEPT_ENCODING, "br,gzip").build()));
        }
        return chain.proceed(chain.request());
    }

    public final okhttp3.Response uncompress$okhttp_brotli(okhttp3.Response response) {
        ResponseBody body;
        String header$default;
        BufferedSource buffer;
        Intrinsics.checkNotNullParameter(response, "response");
        if (!okhttp3.internal.http.HttpHeaders.promisesBody(response) || (body = response.body()) == null || (header$default = okhttp3.Response.header$default(response, HttpHeaders.CONTENT_ENCODING, null, 2, null)) == null) {
            return response;
        }
        if (StringsKt.equals(header$default, "br", true)) {
            buffer = Okio.buffer(Okio.source(new BrotliInputStream(body.getSource().inputStream())));
        } else {
            if (!StringsKt.equals(header$default, "gzip", true)) {
                return response;
            }
            buffer = Okio.buffer(new GzipSource(body.getSource()));
        }
        return response.newBuilder().removeHeader(HttpHeaders.CONTENT_ENCODING).removeHeader(HttpHeaders.CONTENT_LENGTH).body(ResponseBody.INSTANCE.create(buffer, body.get$contentType(), -1L)).build();
    }
}

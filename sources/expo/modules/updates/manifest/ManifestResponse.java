package expo.modules.updates.manifest;

import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ManifestResponse.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lexpo/modules/updates/manifest/ManifestResponse;", "", Response.TYPE, "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "header", "", "name", "defaultValue", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ManifestResponse {
    private final okhttp3.Response response;

    public ManifestResponse(okhttp3.Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        this.response = response;
    }

    public final String header(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return okhttp3.Response.header$default(this.response, name, null, 2, null);
    }

    public final String header(String name, String defaultValue) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        return this.response.header(name, defaultValue);
    }
}

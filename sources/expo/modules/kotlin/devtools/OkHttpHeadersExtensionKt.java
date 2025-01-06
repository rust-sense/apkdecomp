package expo.modules.kotlin.devtools;

import androidx.collection.ArrayMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;

/* compiled from: OkHttpHeadersExtension.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003¨\u0006\u0004"}, d2 = {"toSingleMap", "", "", "Lokhttp3/Headers;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OkHttpHeadersExtensionKt {
    public static final Map<String, String> toSingleMap(Headers headers) {
        Intrinsics.checkNotNullParameter(headers, "<this>");
        ArrayMap arrayMap = new ArrayMap();
        for (String str : headers.names()) {
            arrayMap.put(str, headers.get(str));
        }
        return arrayMap;
    }
}

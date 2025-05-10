package expo.modules.updates;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: UpdatesConfiguration.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u001a\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a(\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0082\b¢\u0006\u0002\u0010\u000f\u001a4\u0010\u0010\u001a\u0004\u0018\u0001H\u000b\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\f*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\u000e\u001a\u00020\u0003H\u0082\b¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"getDefaultPortForScheme", "", "scheme", "", "getNormalizedUrlOrigin", "url", "Landroid/net/Uri;", "maybeGetDefaultScopeKey", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY, "getMetadataValue", ExifInterface.GPS_DIRECTION_TRUE, "", "Landroid/content/Context;", "key", "(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/Object;", "readValueCheckingType", "", "(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/Object;", "expo-updates_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesConfigurationKt {
    private static final /* synthetic */ <T> T getMetadataValue(Context context, String str) {
        Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        if (!bundle.containsKey(str)) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            CharSequence string = bundle.getString(str);
            Intrinsics.reifiedOperationMarker(1, "T?");
            return (T) string;
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            Object valueOf = Boolean.valueOf(bundle.getBoolean(str));
            Intrinsics.reifiedOperationMarker(1, "T?");
            return (T) valueOf;
        }
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            Object valueOf2 = Integer.valueOf(bundle.getInt(str));
            Intrinsics.reifiedOperationMarker(1, "T?");
            return (T) valueOf2;
        }
        T t = (T) bundle.get(str);
        Intrinsics.reifiedOperationMarker(1, "T?");
        return t;
    }

    private static final /* synthetic */ <T> T readValueCheckingType(Map<String, ? extends Object> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        T t = (T) map.get(str);
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        if (t instanceof Object) {
            return t;
        }
        Intrinsics.checkNotNull(t);
        throw new AssertionError("UpdatesConfiguration failed to initialize: bad value of type " + t.getClass().getSimpleName() + " provided for key " + str);
    }

    private static final int getDefaultPortForScheme(String str) {
        if (Intrinsics.areEqual("http", str) || Intrinsics.areEqual("ws", str)) {
            return 80;
        }
        if (Intrinsics.areEqual("https", str) || Intrinsics.areEqual("wss", str)) {
            return 443;
        }
        return Intrinsics.areEqual("ftp", str) ? 21 : -1;
    }

    public static final String getNormalizedUrlOrigin(Uri url) {
        Intrinsics.checkNotNullParameter(url, "url");
        String scheme = url.getScheme();
        int port = url.getPort();
        if (port == getDefaultPortForScheme(scheme)) {
            port = -1;
        }
        String host = url.getHost();
        if (port <= -1) {
            return scheme + "://" + host;
        }
        return scheme + "://" + host + ":" + port;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String maybeGetDefaultScopeKey(String str, Uri uri) {
        return str == null ? getNormalizedUrlOrigin(uri) : str;
    }
}

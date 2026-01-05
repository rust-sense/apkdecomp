package expo.modules.updates.manifest;

import android.content.Context;
import android.util.Log;
import expo.modules.updates.UpdatesConfiguration;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/* compiled from: EmbeddedManifestUtils.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lexpo/modules/updates/manifest/EmbeddedManifestUtils;", "", "()V", "MANIFEST_FILENAME", "", "TAG", "kotlin.jvm.PlatformType", "sEmbeddedUpdate", "Lexpo/modules/updates/manifest/EmbeddedUpdate;", "getEmbeddedUpdate", "context", "Landroid/content/Context;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EmbeddedManifestUtils {
    private static final String MANIFEST_FILENAME = "app.manifest";
    private static EmbeddedUpdate sEmbeddedUpdate;
    public static final EmbeddedManifestUtils INSTANCE = new EmbeddedManifestUtils();
    private static final String TAG = "EmbeddedManifestUtils";

    private EmbeddedManifestUtils() {
    }

    public final EmbeddedUpdate getEmbeddedUpdate(Context context, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        if (!configuration.getHasEmbeddedUpdate()) {
            return null;
        }
        if (sEmbeddedUpdate == null) {
            try {
                InputStream open = context.getAssets().open(MANIFEST_FILENAME);
                try {
                    JSONObject jSONObject = new JSONObject(IOUtils.toString(open, StandardCharsets.UTF_8));
                    jSONObject.put("isVerified", true);
                    sEmbeddedUpdate = UpdateFactory.INSTANCE.getEmbeddedUpdate(jSONObject, configuration);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(open, null);
                } finally {
                }
            } catch (Exception e) {
                Log.e(TAG, "Could not read embedded manifest", e);
                throw new AssertionError("The embedded manifest is invalid or could not be read. Make sure you have configured expo-updates correctly in android/app/build.gradle. " + e.getMessage());
            }
        }
        EmbeddedUpdate embeddedUpdate = sEmbeddedUpdate;
        Intrinsics.checkNotNull(embeddedUpdate);
        return embeddedUpdate;
    }
}

package expo.modules.updates.manifest;

import expo.modules.manifests.core.EmbeddedManifest;
import expo.modules.manifests.core.ExpoUpdatesManifest;
import expo.modules.updates.UpdatesConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UpdateFactory.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ(\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lexpo/modules/updates/manifest/UpdateFactory;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getEmbeddedUpdate", "Lexpo/modules/updates/manifest/EmbeddedUpdate;", "manifestJson", "Lorg/json/JSONObject;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "getUpdate", "Lexpo/modules/updates/manifest/Update;", "responseHeaderData", "Lexpo/modules/updates/manifest/ResponseHeaderData;", "extensions", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdateFactory {
    public static final UpdateFactory INSTANCE = new UpdateFactory();
    private static final String TAG = "UpdateFactory";

    private UpdateFactory() {
    }

    public final Update getUpdate(JSONObject manifestJson, ResponseHeaderData responseHeaderData, JSONObject extensions, UpdatesConfiguration configuration) throws Exception {
        Intrinsics.checkNotNullParameter(manifestJson, "manifestJson");
        Intrinsics.checkNotNullParameter(responseHeaderData, "responseHeaderData");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Integer protocolVersion = responseHeaderData.getProtocolVersion();
        if (protocolVersion == null) {
            throw new Exception("Legacy manifests are no longer supported");
        }
        if (protocolVersion.intValue() == 0 || protocolVersion.intValue() == 1) {
            return ExpoUpdatesUpdate.INSTANCE.fromExpoUpdatesManifest(new ExpoUpdatesManifest(manifestJson), extensions, configuration);
        }
        throw new Exception("Unsupported expo-protocol-version: " + protocolVersion);
    }

    public final EmbeddedUpdate getEmbeddedUpdate(JSONObject manifestJson, UpdatesConfiguration configuration) throws JSONException {
        Intrinsics.checkNotNullParameter(manifestJson, "manifestJson");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return EmbeddedUpdate.INSTANCE.fromEmbeddedManifest(new EmbeddedManifest(manifestJson), configuration);
    }
}

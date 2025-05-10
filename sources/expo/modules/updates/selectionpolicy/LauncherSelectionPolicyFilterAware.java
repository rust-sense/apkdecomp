package expo.modules.updates.selectionpolicy;

import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.entity.UpdateEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: LauncherSelectionPolicyFilterAware.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicyFilterAware;", "Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicy;", UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY, "", "(Ljava/lang/String;)V", "selectUpdateToLaunch", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updates", "", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LauncherSelectionPolicyFilterAware implements LauncherSelectionPolicy {
    private final String runtimeVersion;

    public LauncherSelectionPolicyFilterAware(String runtimeVersion) {
        Intrinsics.checkNotNullParameter(runtimeVersion, "runtimeVersion");
        this.runtimeVersion = runtimeVersion;
    }

    @Override // expo.modules.updates.selectionpolicy.LauncherSelectionPolicy
    public UpdateEntity selectUpdateToLaunch(List<UpdateEntity> updates, JSONObject filters) {
        Intrinsics.checkNotNullParameter(updates, "updates");
        UpdateEntity updateEntity = null;
        for (UpdateEntity updateEntity2 : updates) {
            if (Intrinsics.areEqual(this.runtimeVersion, updateEntity2.getRuntimeVersion()) && SelectionPolicies.INSTANCE.matchesFilters(updateEntity2, filters) && (updateEntity == null || updateEntity.getCommitTime().before(updateEntity2.getCommitTime()))) {
                updateEntity = updateEntity2;
            }
        }
        return updateEntity;
    }
}

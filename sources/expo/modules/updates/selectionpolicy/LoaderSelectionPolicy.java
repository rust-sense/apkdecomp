package expo.modules.updates.selectionpolicy;

import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.loader.UpdateDirective;
import kotlin.Metadata;
import org.json.JSONObject;

/* compiled from: LoaderSelectionPolicy.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J,\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\r"}, d2 = {"Lexpo/modules/updates/selectionpolicy/LoaderSelectionPolicy;", "", "shouldLoadNewUpdate", "", "newUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "launchedUpdate", "filters", "Lorg/json/JSONObject;", "shouldLoadRollBackToEmbeddedDirective", "directive", "Lexpo/modules/updates/loader/UpdateDirective$RollBackToEmbeddedUpdateDirective;", "embeddedUpdate", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface LoaderSelectionPolicy {
    boolean shouldLoadNewUpdate(UpdateEntity newUpdate, UpdateEntity launchedUpdate, JSONObject filters);

    boolean shouldLoadRollBackToEmbeddedDirective(UpdateDirective.RollBackToEmbeddedUpdateDirective directive, UpdateEntity embeddedUpdate, UpdateEntity launchedUpdate, JSONObject filters);
}

package expo.modules.updates.selectionpolicy;

import expo.modules.updates.db.entity.UpdateEntity;
import java.util.List;
import kotlin.Metadata;
import org.json.JSONObject;

/* compiled from: LauncherSelectionPolicy.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicy;", "", "selectUpdateToLaunch", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updates", "", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface LauncherSelectionPolicy {
    UpdateEntity selectUpdateToLaunch(List<UpdateEntity> updates, JSONObject filters);
}

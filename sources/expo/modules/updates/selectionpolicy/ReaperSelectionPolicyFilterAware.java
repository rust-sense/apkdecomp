package expo.modules.updates.selectionpolicy;

import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.db.enums.UpdateStatus;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: ReaperSelectionPolicyFilterAware.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, d2 = {"Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicyFilterAware;", "Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicy;", "()V", "selectUpdatesToDelete", "", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updates", "launchedUpdate", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReaperSelectionPolicyFilterAware implements ReaperSelectionPolicy {
    @Override // expo.modules.updates.selectionpolicy.ReaperSelectionPolicy
    public List<UpdateEntity> selectUpdatesToDelete(List<UpdateEntity> updates, UpdateEntity launchedUpdate, JSONObject filters) {
        Intrinsics.checkNotNullParameter(updates, "updates");
        if (launchedUpdate == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        UpdateEntity updateEntity = null;
        UpdateEntity updateEntity2 = null;
        for (UpdateEntity updateEntity3 : updates) {
            if (Intrinsics.areEqual(updateEntity3.getScopeKey(), launchedUpdate.getScopeKey()) && updateEntity3.getCommitTime().before(launchedUpdate.getCommitTime())) {
                arrayList.add(updateEntity3);
                if (updateEntity2 == null || updateEntity2.getCommitTime().before(updateEntity3.getCommitTime())) {
                    updateEntity2 = updateEntity3;
                }
                if (SelectionPolicies.INSTANCE.matchesFilters(updateEntity3, filters) && (updateEntity == null || updateEntity.getCommitTime().before(updateEntity3.getCommitTime()))) {
                    updateEntity = updateEntity3;
                }
            }
        }
        if (updateEntity != null) {
            arrayList.remove(updateEntity);
        } else if (updateEntity2 != null) {
            arrayList.remove(updateEntity2);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((UpdateEntity) obj).getStatus() != UpdateStatus.EMBEDDED) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }
}

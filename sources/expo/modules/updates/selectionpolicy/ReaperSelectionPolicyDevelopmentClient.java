package expo.modules.updates.selectionpolicy;

import expo.modules.updates.db.entity.UpdateEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: ReaperSelectionPolicyDevelopmentClient.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicyDevelopmentClient;", "Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicy;", "maxUpdatesToKeep", "", "(I)V", "selectUpdatesToDelete", "", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updates", "launchedUpdate", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReaperSelectionPolicyDevelopmentClient implements ReaperSelectionPolicy {
    private final int maxUpdatesToKeep;

    public ReaperSelectionPolicyDevelopmentClient() {
        this(0, 1, null);
    }

    public ReaperSelectionPolicyDevelopmentClient(int i) {
        this.maxUpdatesToKeep = i;
        if (i <= 0) {
            throw new AssertionError("Cannot initialize ReaperSelectionPolicyDevelopmentClient with maxUpdatesToKeep <= 0");
        }
    }

    public /* synthetic */ ReaperSelectionPolicyDevelopmentClient(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }

    @Override // expo.modules.updates.selectionpolicy.ReaperSelectionPolicy
    public List<UpdateEntity> selectUpdatesToDelete(List<UpdateEntity> updates, UpdateEntity launchedUpdate, JSONObject filters) {
        Intrinsics.checkNotNullParameter(updates, "updates");
        if (launchedUpdate == null || updates.size() <= this.maxUpdatesToKeep) {
            return new ArrayList();
        }
        List mutableList = CollectionsKt.toMutableList((Collection) updates);
        final ReaperSelectionPolicyDevelopmentClient$selectUpdatesToDelete$1 reaperSelectionPolicyDevelopmentClient$selectUpdatesToDelete$1 = new Function2<UpdateEntity, UpdateEntity, Integer>() { // from class: expo.modules.updates.selectionpolicy.ReaperSelectionPolicyDevelopmentClient$selectUpdatesToDelete$1
            @Override // kotlin.jvm.functions.Function2
            public final Integer invoke(UpdateEntity u1, UpdateEntity u2) {
                Intrinsics.checkNotNullParameter(u1, "u1");
                Intrinsics.checkNotNullParameter(u2, "u2");
                int compareTo = u1.getLastAccessed().compareTo(u2.getLastAccessed());
                if (compareTo == 0) {
                    compareTo = u1.getCommitTime().compareTo(u2.getCommitTime());
                }
                return Integer.valueOf(compareTo);
            }
        };
        CollectionsKt.sortWith(mutableList, new Comparator() { // from class: expo.modules.updates.selectionpolicy.ReaperSelectionPolicyDevelopmentClient$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int selectUpdatesToDelete$lambda$0;
                selectUpdatesToDelete$lambda$0 = ReaperSelectionPolicyDevelopmentClient.selectUpdatesToDelete$lambda$0(Function2.this, obj, obj2);
                return selectUpdatesToDelete$lambda$0;
            }
        });
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (mutableList.size() > this.maxUpdatesToKeep) {
            UpdateEntity updateEntity = (UpdateEntity) mutableList.remove(0);
            if (!Intrinsics.areEqual(updateEntity.getId(), launchedUpdate.getId())) {
                arrayList.add(updateEntity);
            } else {
                if (z) {
                    throw new AssertionError("Multiple updates with the same ID were passed into ReaperSelectionPolicyDevelopmentClient");
                }
                mutableList.add(updateEntity);
                z = true;
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int selectUpdatesToDelete$lambda$0(Function2 tmp0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }
}

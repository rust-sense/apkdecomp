package expo.modules.updates.selectionpolicy;

import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.loader.UpdateDirective;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: SelectionPolicy.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ \u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J,\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\u0006\u0010\u0016\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J$\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J*\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "", "launcherSelectionPolicy", "Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicy;", "loaderSelectionPolicy", "Lexpo/modules/updates/selectionpolicy/LoaderSelectionPolicy;", "reaperSelectionPolicy", "Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicy;", "(Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicy;Lexpo/modules/updates/selectionpolicy/LoaderSelectionPolicy;Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicy;)V", "getLauncherSelectionPolicy", "()Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicy;", "getLoaderSelectionPolicy", "()Lexpo/modules/updates/selectionpolicy/LoaderSelectionPolicy;", "getReaperSelectionPolicy", "()Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicy;", "selectUpdateToLaunch", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updates", "", "filters", "Lorg/json/JSONObject;", "selectUpdatesToDelete", "launchedUpdate", "shouldLoadNewUpdate", "", "newUpdate", "shouldLoadRollBackToEmbeddedDirective", "directive", "Lexpo/modules/updates/loader/UpdateDirective$RollBackToEmbeddedUpdateDirective;", "embeddedUpdate", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SelectionPolicy {
    private final LauncherSelectionPolicy launcherSelectionPolicy;
    private final LoaderSelectionPolicy loaderSelectionPolicy;
    private final ReaperSelectionPolicy reaperSelectionPolicy;

    public final LauncherSelectionPolicy getLauncherSelectionPolicy() {
        return this.launcherSelectionPolicy;
    }

    public final LoaderSelectionPolicy getLoaderSelectionPolicy() {
        return this.loaderSelectionPolicy;
    }

    public final ReaperSelectionPolicy getReaperSelectionPolicy() {
        return this.reaperSelectionPolicy;
    }

    public SelectionPolicy(LauncherSelectionPolicy launcherSelectionPolicy, LoaderSelectionPolicy loaderSelectionPolicy, ReaperSelectionPolicy reaperSelectionPolicy) {
        Intrinsics.checkNotNullParameter(launcherSelectionPolicy, "launcherSelectionPolicy");
        Intrinsics.checkNotNullParameter(loaderSelectionPolicy, "loaderSelectionPolicy");
        Intrinsics.checkNotNullParameter(reaperSelectionPolicy, "reaperSelectionPolicy");
        this.launcherSelectionPolicy = launcherSelectionPolicy;
        this.loaderSelectionPolicy = loaderSelectionPolicy;
        this.reaperSelectionPolicy = reaperSelectionPolicy;
    }

    public final UpdateEntity selectUpdateToLaunch(List<UpdateEntity> updates, JSONObject filters) {
        Intrinsics.checkNotNullParameter(updates, "updates");
        return this.launcherSelectionPolicy.selectUpdateToLaunch(updates, filters);
    }

    public final List<UpdateEntity> selectUpdatesToDelete(List<UpdateEntity> updates, UpdateEntity launchedUpdate, JSONObject filters) {
        Intrinsics.checkNotNullParameter(updates, "updates");
        Intrinsics.checkNotNullParameter(launchedUpdate, "launchedUpdate");
        return this.reaperSelectionPolicy.selectUpdatesToDelete(updates, launchedUpdate, filters);
    }

    public final boolean shouldLoadNewUpdate(UpdateEntity newUpdate, UpdateEntity launchedUpdate, JSONObject filters) {
        return this.loaderSelectionPolicy.shouldLoadNewUpdate(newUpdate, launchedUpdate, filters);
    }

    public final boolean shouldLoadRollBackToEmbeddedDirective(UpdateDirective.RollBackToEmbeddedUpdateDirective directive, UpdateEntity embeddedUpdate, UpdateEntity launchedUpdate, JSONObject filters) {
        Intrinsics.checkNotNullParameter(directive, "directive");
        Intrinsics.checkNotNullParameter(embeddedUpdate, "embeddedUpdate");
        return this.loaderSelectionPolicy.shouldLoadRollBackToEmbeddedDirective(directive, embeddedUpdate, launchedUpdate, filters);
    }
}

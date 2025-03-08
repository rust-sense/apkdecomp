package expo.modules.updates;

import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.updates.IUpdatesController;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesModule.kt */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0015\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"expo/modules/updates/UpdatesModule$definition$1$5$1", "Lexpo/modules/updates/IUpdatesController$ModuleCallback;", "", "onFailure", "exception", "Lexpo/modules/kotlin/exception/CodedException;", "onSuccess", "result", "(Lkotlin/Unit;)V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesModule$definition$1$5$1 implements IUpdatesController.ModuleCallback<Unit> {
    final /* synthetic */ Promise $promise;

    UpdatesModule$definition$1$5$1(Promise promise) {
        this.$promise = promise;
    }

    @Override // expo.modules.updates.IUpdatesController.ModuleCallback
    public void onSuccess(Unit result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.$promise.resolve((Object) null);
    }

    @Override // expo.modules.updates.IUpdatesController.ModuleCallback
    public void onFailure(CodedException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.$promise.reject(exception);
    }
}

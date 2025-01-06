package expo.modules.updates;

import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.updates.IUpdatesController;
import expo.modules.updates.statemachine.UpdatesStateContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesModule.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"expo/modules/updates/UpdatesModule$definition$1$6$1", "Lexpo/modules/updates/IUpdatesController$ModuleCallback;", "Lexpo/modules/updates/statemachine/UpdatesStateContext;", "onFailure", "", "exception", "Lexpo/modules/kotlin/exception/CodedException;", "onSuccess", "result", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesModule$definition$1$6$1 implements IUpdatesController.ModuleCallback<UpdatesStateContext> {
    final /* synthetic */ Promise $promise;

    UpdatesModule$definition$1$6$1(Promise promise) {
        this.$promise = promise;
    }

    @Override // expo.modules.updates.IUpdatesController.ModuleCallback
    public void onSuccess(UpdatesStateContext result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.$promise.resolve(result.getBundle());
    }

    @Override // expo.modules.updates.IUpdatesController.ModuleCallback
    public void onFailure(CodedException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.$promise.reject(exception);
    }
}

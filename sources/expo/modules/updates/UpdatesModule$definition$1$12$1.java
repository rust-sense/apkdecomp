package expo.modules.updates;

import android.content.Context;
import expo.modules.kotlin.Promise;
import expo.modules.updates.UpdatesModule;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: UpdatesModule.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class UpdatesModule$definition$1$12$1 implements Runnable {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ UpdatesModule this$0;

    UpdatesModule$definition$1$12$1(UpdatesModule updatesModule, Promise promise) {
        this.this$0 = updatesModule;
        this.$promise = promise;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        UpdatesModule.Companion companion = UpdatesModule.INSTANCE;
        context = this.this$0.getContext();
        final Promise promise = this.$promise;
        companion.clearLogEntries$expo_updates_release(context, new Function1<Error, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$1$12$1.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Error error) {
                invoke2(error);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Error error) {
                if (error != null) {
                    Promise.this.reject("ERR_UPDATES_READ_LOGS", "There was an error when clearing the expo-updates log file", error);
                } else {
                    Promise.this.resolve((Object) null);
                }
            }
        });
    }
}

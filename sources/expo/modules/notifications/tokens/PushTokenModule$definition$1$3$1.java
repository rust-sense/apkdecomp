package expo.modules.notifications.tokens;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PushTokenModule.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "task", "Lcom/google/android/gms/tasks/Task;", "", "kotlin.jvm.PlatformType", "onComplete"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class PushTokenModule$definition$1$3$1<TResult> implements OnCompleteListener {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ PushTokenModule this$0;

    PushTokenModule$definition$1$3$1(Promise promise, PushTokenModule pushTokenModule) {
        this.$promise = promise;
        this.this$0 = pushTokenModule;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<String> task) {
        String str;
        Intrinsics.checkNotNullParameter(task, "task");
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            Promise promise = this.$promise;
            if (exception == null || (str = exception.getMessage()) == null) {
                str = "unknown";
            }
            promise.reject("E_REGISTRATION_FAILED", "Fetching the token failed: " + str, exception);
            return;
        }
        String result = task.getResult();
        if (result == null) {
            this.$promise.reject("E_REGISTRATION_FAILED", "Fetching the token failed. Invalid token.", null);
        } else {
            this.$promise.resolve(result);
            this.this$0.onNewToken(result);
        }
    }
}

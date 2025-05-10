package expo.modules.updates.procedures;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.react.ReactApplication;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.procedures.StateMachineProcedure;
import expo.modules.updates.statemachine.UpdatesStateEvent;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecreateReactContextProcedure.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lexpo/modules/updates/procedures/RecreateReactContextProcedure;", "Lexpo/modules/updates/procedures/StateMachineProcedure;", "context", "Landroid/content/Context;", "weakActivity", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "(Landroid/content/Context;Ljava/lang/ref/WeakReference;Lexpo/modules/updates/launcher/Launcher$LauncherCallback;)V", "loggerTimerLabel", "", "getLoggerTimerLabel", "()Ljava/lang/String;", "run", "", "procedureContext", "Lexpo/modules/updates/procedures/StateMachineProcedure$ProcedureContext;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RecreateReactContextProcedure extends StateMachineProcedure {
    private final Launcher.LauncherCallback callback;
    private final Context context;
    private final String loggerTimerLabel;
    private final WeakReference<Activity> weakActivity;

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public String getLoggerTimerLabel() {
        return this.loggerTimerLabel;
    }

    public RecreateReactContextProcedure(Context context, WeakReference<Activity> weakReference, Launcher.LauncherCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.context = context;
        this.weakActivity = weakReference;
        this.callback = callback;
        this.loggerTimerLabel = "timer-recreate-react-context";
    }

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public void run(StateMachineProcedure.ProcedureContext procedureContext) {
        Intrinsics.checkNotNullParameter(procedureContext, "procedureContext");
        Object applicationContext = this.context.getApplicationContext();
        final ReactApplication reactApplication = applicationContext instanceof ReactApplication ? (ReactApplication) applicationContext : null;
        if (reactApplication == null) {
            this.callback.onFailure(new Exception("Could not reload application. Ensure you have passed the correct instance of ReactApplication into UpdatesController.initialize()."));
            return;
        }
        procedureContext.processStateEvent(new UpdatesStateEvent.Restart());
        this.callback.onSuccess();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: expo.modules.updates.procedures.RecreateReactContextProcedure$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RecreateReactContextProcedure.run$lambda$1(ReactApplication.this, this);
            }
        });
        procedureContext.resetState();
        procedureContext.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void run$lambda$1(ReactApplication reactApplication, RecreateReactContextProcedure this$0) {
        Intrinsics.checkNotNullParameter(reactApplication, "$reactApplication");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WeakReference<Activity> weakReference = this$0.weakActivity;
        RestartReactAppExtensionsKt.restart(reactApplication, weakReference != null ? weakReference.get() : null, "Restart from RecreateReactContextProcedure");
    }
}

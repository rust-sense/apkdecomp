package expo.modules.updates.procedures;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.config.ReactFeatureFlags;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.DatabaseHolder;
import expo.modules.updates.db.Reaper;
import expo.modules.updates.launcher.DatabaseLauncher;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.procedures.StateMachineProcedure;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import expo.modules.updates.statemachine.UpdatesStateEvent;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RelaunchProcedure.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 +2\u00020\u0001:\u0001+B\u0086\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0015\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eJ\u0018\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020 H\u0002J\u0010\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0019H\u0002R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020 X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lexpo/modules/updates/procedures/RelaunchProcedure;", "Lexpo/modules/updates/procedures/StateMachineProcedure;", "context", "Landroid/content/Context;", "weakActivity", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "updatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "updatesDirectory", "Ljava/io/File;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "getCurrentLauncher", "Lkotlin/Function0;", "Lexpo/modules/updates/launcher/Launcher;", "setCurrentLauncher", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "launcher", "", "shouldRunReaper", "", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "(Landroid/content/Context;Ljava/lang/ref/WeakReference;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/DatabaseHolder;Ljava/io/File;Lexpo/modules/updates/loader/FileDownloader;Lexpo/modules/updates/selectionpolicy/SelectionPolicy;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;ZLexpo/modules/updates/launcher/Launcher$LauncherCallback;)V", "loggerTimerLabel", "", "getLoggerTimerLabel", "()Ljava/lang/String;", "replaceLaunchAssetFileIfNeeded", "reactApplication", "Lcom/facebook/react/ReactApplication;", "launchAssetFile", "run", "procedureContext", "Lexpo/modules/updates/procedures/StateMachineProcedure$ProcedureContext;", "runReaper", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RelaunchProcedure extends StateMachineProcedure {
    private static final String TAG = "RelaunchProcedure";
    private final Launcher.LauncherCallback callback;
    private final Context context;
    private final DatabaseHolder databaseHolder;
    private final FileDownloader fileDownloader;
    private final Function0<Launcher> getCurrentLauncher;
    private final String loggerTimerLabel;
    private final SelectionPolicy selectionPolicy;
    private final Function1<Launcher, Unit> setCurrentLauncher;
    private final boolean shouldRunReaper;
    private final UpdatesConfiguration updatesConfiguration;
    private final File updatesDirectory;
    private final WeakReference<Activity> weakActivity;

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public String getLoggerTimerLabel() {
        return this.loggerTimerLabel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RelaunchProcedure(Context context, WeakReference<Activity> weakReference, UpdatesConfiguration updatesConfiguration, DatabaseHolder databaseHolder, File updatesDirectory, FileDownloader fileDownloader, SelectionPolicy selectionPolicy, Function0<? extends Launcher> getCurrentLauncher, Function1<? super Launcher, Unit> setCurrentLauncher, boolean z, Launcher.LauncherCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(databaseHolder, "databaseHolder");
        Intrinsics.checkNotNullParameter(updatesDirectory, "updatesDirectory");
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        Intrinsics.checkNotNullParameter(getCurrentLauncher, "getCurrentLauncher");
        Intrinsics.checkNotNullParameter(setCurrentLauncher, "setCurrentLauncher");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.context = context;
        this.weakActivity = weakReference;
        this.updatesConfiguration = updatesConfiguration;
        this.databaseHolder = databaseHolder;
        this.updatesDirectory = updatesDirectory;
        this.fileDownloader = fileDownloader;
        this.selectionPolicy = selectionPolicy;
        this.getCurrentLauncher = getCurrentLauncher;
        this.setCurrentLauncher = setCurrentLauncher;
        this.shouldRunReaper = z;
        this.callback = callback;
        this.loggerTimerLabel = "timer-relaunch";
    }

    @Override // expo.modules.updates.procedures.StateMachineProcedure
    public void run(StateMachineProcedure.ProcedureContext procedureContext) {
        Intrinsics.checkNotNullParameter(procedureContext, "procedureContext");
        Object obj = this.context;
        ReactApplication reactApplication = obj instanceof ReactApplication ? (ReactApplication) obj : null;
        if (reactApplication == null) {
            this.callback.onFailure(new Exception("Could not reload application. Ensure you have passed the correct instance of ReactApplication into UpdatesController.initialize()."));
            return;
        }
        procedureContext.processStateEvent(new UpdatesStateEvent.Restart());
        String launchAssetFile = this.getCurrentLauncher.invoke().getLaunchAssetFile();
        DatabaseLauncher databaseLauncher = new DatabaseLauncher(this.updatesConfiguration, this.updatesDirectory, this.fileDownloader, this.selectionPolicy);
        databaseLauncher.launch(this.databaseHolder.getDatabase(), this.context, new RelaunchProcedure$run$1(this, procedureContext, databaseLauncher, launchAssetFile, reactApplication));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runReaper() {
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.procedures.RelaunchProcedure$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RelaunchProcedure.runReaper$lambda$1(RelaunchProcedure.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runReaper$lambda$1(RelaunchProcedure this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Reaper.reapUnusedUpdates(this$0.updatesConfiguration, this$0.databaseHolder.getDatabase(), this$0.updatesDirectory, this$0.getCurrentLauncher.invoke().getLaunchedUpdate(), this$0.selectionPolicy);
        this$0.databaseHolder.releaseDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void replaceLaunchAssetFileIfNeeded(ReactApplication reactApplication, String launchAssetFile) {
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            return;
        }
        ReactInstanceManager reactInstanceManager = reactApplication.getReactNativeHost().getReactInstanceManager();
        Field declaredField = reactInstanceManager.getClass().getDeclaredField("mBundleLoader");
        declaredField.setAccessible(true);
        declaredField.set(reactInstanceManager, JSBundleLoader.createFileLoader(launchAssetFile));
    }
}

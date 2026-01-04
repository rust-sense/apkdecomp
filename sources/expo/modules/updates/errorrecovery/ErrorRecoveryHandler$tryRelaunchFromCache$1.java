package expo.modules.updates.errorrecovery;

import expo.modules.updates.errorrecovery.ErrorRecoveryHandler;
import expo.modules.updates.launcher.Launcher;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ErrorRecoveryHandler.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, d2 = {"expo/modules/updates/errorrecovery/ErrorRecoveryHandler$tryRelaunchFromCache$1", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ErrorRecoveryHandler$tryRelaunchFromCache$1 implements Launcher.LauncherCallback {
    final /* synthetic */ ErrorRecoveryHandler this$0;

    ErrorRecoveryHandler$tryRelaunchFromCache$1(ErrorRecoveryHandler errorRecoveryHandler) {
        this.this$0 = errorRecoveryHandler;
    }

    @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
    public void onFailure(final Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        final ErrorRecoveryHandler errorRecoveryHandler = this.this$0;
        errorRecoveryHandler.post(new Runnable() { // from class: expo.modules.updates.errorrecovery.ErrorRecoveryHandler$tryRelaunchFromCache$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ErrorRecoveryHandler$tryRelaunchFromCache$1.onFailure$lambda$0(ErrorRecoveryHandler.this, e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(ErrorRecoveryHandler this$0, Exception e) {
        ArrayList arrayList;
        ArrayList arrayList2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "$e");
        arrayList = this$0.encounteredErrors;
        arrayList.add(e);
        arrayList2 = this$0.pipeline;
        arrayList2.removeAll(SetsKt.setOf((Object[]) new ErrorRecoveryHandler.Task[]{ErrorRecoveryHandler.Task.LAUNCH_NEW_UPDATE, ErrorRecoveryHandler.Task.LAUNCH_CACHED_UPDATE}));
        this$0.runNextTask();
    }

    @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
    public void onSuccess() {
        final ErrorRecoveryHandler errorRecoveryHandler = this.this$0;
        errorRecoveryHandler.post(new Runnable() { // from class: expo.modules.updates.errorrecovery.ErrorRecoveryHandler$tryRelaunchFromCache$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ErrorRecoveryHandler$tryRelaunchFromCache$1.onSuccess$lambda$1(ErrorRecoveryHandler.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSuccess$lambda$1(ErrorRecoveryHandler this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.isPipelineRunning = false;
    }
}

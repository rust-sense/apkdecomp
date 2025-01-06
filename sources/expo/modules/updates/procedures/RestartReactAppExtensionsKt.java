package expo.modules.updates.procedures;

import android.app.Activity;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactHost;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.config.ReactFeatureFlags;
import io.sentry.clientreport.DiscardedEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RestartReactAppExtensions.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"restart", "", "Lcom/facebook/react/ReactApplication;", "activity", "Landroid/app/Activity;", DiscardedEvent.JsonKeys.REASON, "", "expo-updates_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RestartReactAppExtensionsKt {
    public static final void restart(ReactApplication reactApplication, Activity activity, String reason) {
        Intrinsics.checkNotNullParameter(reactApplication, "<this>");
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (ReactFeatureFlags.enableBridgelessArchitecture) {
            ReactHost reactHost = reactApplication.getReactHost();
            if (reactHost == null) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (reactHost.getLifecycleState() != LifecycleState.RESUMED && activity != null) {
                reactHost.onHostResume(activity);
            }
            reactHost.reload(reason);
            return;
        }
        reactApplication.getReactNativeHost().getReactInstanceManager().recreateReactContextInBackground();
    }
}

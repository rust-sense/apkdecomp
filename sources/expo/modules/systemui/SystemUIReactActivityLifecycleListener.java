package expo.modules.systemui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.systemui.singletons.SystemUI;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystemUIReactActivityLifecycleListener.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"Lexpo/modules/systemui/SystemUIReactActivityLifecycleListener;", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "()V", "getUserInterfaceStyle", "", "context", "Landroid/content/Context;", "onCreate", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "expo-system-ui_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SystemUIReactActivityLifecycleListener implements ReactActivityLifecycleListener {
    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ boolean onBackPressed() {
        return ReactActivityLifecycleListener.CC.$default$onBackPressed(this);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onContentChanged(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onContentChanged(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onDestroy(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onDestroy(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ boolean onNewIntent(Intent intent) {
        return ReactActivityLifecycleListener.CC.$default$onNewIntent(this, intent);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onPause(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onPause(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onResume(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onResume(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public void onCreate(Activity activity, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        SystemUI.setUserInterfaceStyle(getUserInterfaceStyle(activity));
    }

    private final String getUserInterfaceStyle(Context context) {
        String string = context.getString(R.string.expo_system_ui_user_interface_style);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }
}

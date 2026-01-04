package expo.modules.navigationbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.navigationbar.singletons.NavigationBar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: NavigationBarReactActivityLifecycleListener.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016¨\u0006\u0014"}, d2 = {"Lexpo/modules/navigationbar/NavigationBarReactActivityLifecycleListener;", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "()V", "getBehavior", "", "context", "Landroid/content/Context;", "getBorderColor", "", "(Landroid/content/Context;)Ljava/lang/Integer;", "getLegacyVisible", "getPosition", "getVisibility", "onCreate", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "expo-navigation-bar_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NavigationBarReactActivityLifecycleListener implements ReactActivityLifecycleListener {
    private static final String ERROR_TAG = "ERR_NAVIGATION_BAR";

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
        Activity activity2 = activity;
        Integer borderColor = getBorderColor(activity2);
        if (borderColor != null) {
            NavigationBar.setBorderColor(activity, borderColor.intValue());
        }
        String visibility = getVisibility(activity2);
        if (!StringsKt.isBlank(visibility)) {
            NavigationBar.setVisibility(activity, visibility);
        }
        String position = getPosition(activity2);
        if (!StringsKt.isBlank(position)) {
            NavigationBar.setPosition(activity, position);
        }
        String behavior = getBehavior(activity2);
        if (!StringsKt.isBlank(behavior)) {
            NavigationBar.setBehavior(activity, behavior);
        }
        String legacyVisible = getLegacyVisible(activity2);
        if (!StringsKt.isBlank(legacyVisible)) {
            NavigationBar.setLegacyVisible(activity, legacyVisible);
        }
    }

    private final Integer getBorderColor(Context context) {
        String string = context.getString(R.string.expo_navigation_bar_border_color);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Integer intOrNull = StringsKt.toIntOrNull(string);
        if ((!StringsKt.isBlank(string)) && intOrNull == null) {
            Log.e(ERROR_TAG, "Invalid XML value \"" + string + "\" for string \"expo_navigation_bar_border_color\". Expected a valid color int like \"-12177173\". Ensure the value of \"borderColor\" in the \"expo-navigation-bar\" config plugin is a valid CSS color. Skipping initial border color.");
        }
        return intOrNull;
    }

    private final String getVisibility(Context context) {
        String string = context.getString(R.string.expo_navigation_bar_visibility);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    private final String getPosition(Context context) {
        String string = context.getString(R.string.expo_navigation_bar_position);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    private final String getBehavior(Context context) {
        String string = context.getString(R.string.expo_navigation_bar_behavior);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    private final String getLegacyVisible(Context context) {
        String string = context.getString(R.string.expo_navigation_bar_legacy_visible);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }
}

package expo.modules.webbrowser;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.browser.customtabs.CustomTabsClient;
import expo.modules.core.errors.CurrentActivityNotFoundException;
import expo.modules.core.interfaces.ActivityProvider;
import io.sentry.protocol.SdkVersion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomTabsActivitiesHelper.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\u0010\u0010\u001c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\u001dJ\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u001aR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lexpo/modules/webbrowser/CustomTabsActivitiesHelper;", "", "activityProvider", "Lexpo/modules/core/interfaces/ActivityProvider;", "(Lexpo/modules/core/interfaces/ActivityProvider;)V", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "customTabsResolvingActivities", "Ljava/util/ArrayList;", "", "getCustomTabsResolvingActivities", "()Ljava/util/ArrayList;", "customTabsResolvingServices", "getCustomTabsResolvingServices", "defaultCustomTabsResolvingActivity", "getDefaultCustomTabsResolvingActivity", "()Ljava/lang/String;", "packageManager", "Landroid/content/pm/PackageManager;", "getPackageManager", "()Landroid/content/pm/PackageManager;", "canResolveIntent", "", "intent", "Landroid/content/Intent;", "getPreferredCustomTabsResolvingActivity", SdkVersion.JsonKeys.PACKAGES, "", "getResolvingActivities", "Landroid/content/pm/ResolveInfo;", "startCustomTabs", "", "expo-web-browser_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CustomTabsActivitiesHelper {
    private final ActivityProvider activityProvider;

    public CustomTabsActivitiesHelper(ActivityProvider activityProvider) {
        this.activityProvider = activityProvider;
    }

    public final boolean canResolveIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return !getResolvingActivities(intent).isEmpty();
    }

    public final ArrayList<String> getCustomTabsResolvingActivities() {
        Intent createDefaultCustomTabsIntent;
        createDefaultCustomTabsIntent = CustomTabsActivitiesHelperKt.createDefaultCustomTabsIntent();
        List<ResolveInfo> resolvingActivities = getResolvingActivities(createDefaultCustomTabsIntent);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<ResolveInfo> it = resolvingActivities.iterator();
        while (it.hasNext()) {
            String packageName = it.next().activityInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            linkedHashSet.add(packageName);
        }
        return new ArrayList<>(linkedHashSet);
    }

    public final ArrayList<String> getCustomTabsResolvingServices() {
        Intent createDefaultCustomTabsServiceIntent;
        PackageManager packageManager = getPackageManager();
        createDefaultCustomTabsServiceIntent = CustomTabsActivitiesHelperKt.createDefaultCustomTabsServiceIntent();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(createDefaultCustomTabsServiceIntent, 0);
        Intrinsics.checkNotNullExpressionValue(queryIntentServices, "queryIntentServices(...)");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<ResolveInfo> it = queryIntentServices.iterator();
        while (it.hasNext()) {
            String packageName = it.next().serviceInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            linkedHashSet.add(packageName);
        }
        return new ArrayList<>(linkedHashSet);
    }

    public final String getPreferredCustomTabsResolvingActivity(List<String> packages) {
        if (packages == null) {
            packages = getCustomTabsResolvingActivities();
        }
        return CustomTabsClient.getPackageName(getCurrentActivity(), packages);
    }

    public final String getDefaultCustomTabsResolvingActivity() {
        Intent createDefaultCustomTabsIntent;
        ActivityInfo activityInfo;
        PackageManager packageManager = getPackageManager();
        createDefaultCustomTabsIntent = CustomTabsActivitiesHelperKt.createDefaultCustomTabsIntent();
        ResolveInfo resolveActivity = packageManager.resolveActivity(createDefaultCustomTabsIntent, 0);
        if (resolveActivity == null || (activityInfo = resolveActivity.activityInfo) == null) {
            return null;
        }
        return activityInfo.packageName;
    }

    public final void startCustomTabs(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        getCurrentActivity().startActivity(intent);
    }

    private final List<ResolveInfo> getResolvingActivities(Intent intent) {
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 0);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "queryIntentActivities(...)");
        return queryIntentActivities;
    }

    private final PackageManager getPackageManager() {
        PackageManager packageManager = getCurrentActivity().getPackageManager();
        if (packageManager != null) {
            return packageManager;
        }
        throw new PackageManagerNotFoundException();
    }

    private final Activity getCurrentActivity() {
        ActivityProvider activityProvider = this.activityProvider;
        Activity currentActivity = activityProvider != null ? activityProvider.getCurrentActivity() : null;
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new CurrentActivityNotFoundException();
    }
}

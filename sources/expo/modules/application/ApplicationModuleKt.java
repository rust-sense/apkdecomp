package expo.modules.application;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApplicationModule.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u001e\u0010\u0004\u001a\u00020\u0003*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0002¨\u0006\n"}, d2 = {"getLongVersionCode", "", "info", "Landroid/content/pm/PackageInfo;", "getPackageInfoCompat", "Landroid/content/pm/PackageManager;", "packageName", "", NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY, "", "expo-application_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ApplicationModuleKt {
    static /* synthetic */ PackageInfo getPackageInfoCompat$default(PackageManager packageManager, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return getPackageInfoCompat(packageManager, str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PackageInfo getPackageInfoCompat(PackageManager packageManager, String str, int i) {
        PackageInfo packageInfo;
        PackageManager.PackageInfoFlags of;
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                of = PackageManager.PackageInfoFlags.of(i);
                packageInfo = packageManager.getPackageInfo(str, of);
            } else {
                packageInfo = packageManager.getPackageInfo(str, i);
            }
            Intrinsics.checkNotNull(packageInfo);
            return packageInfo;
        } catch (PackageManager.NameNotFoundException e) {
            throw new ApplicationPackageNameNotFoundException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getLongVersionCode(PackageInfo packageInfo) {
        long longVersionCode;
        if (Build.VERSION.SDK_INT >= 28) {
            longVersionCode = packageInfo.getLongVersionCode();
            return longVersionCode;
        }
        return packageInfo.versionCode;
    }
}

package expo.modules.notifications.badge;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import expo.modules.notifications.service.NotificationsService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.ShortcutBadger;

/* compiled from: BadgeHelper.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004R&\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lexpo/modules/notifications/badge/BadgeHelper;", "", "()V", "value", "", "badgeCount", "getBadgeCount", "()I", "setBadgeCount", "(I)V", "", "context", "Landroid/content/Context;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BadgeHelper {
    public static final BadgeHelper INSTANCE = new BadgeHelper();
    private static int badgeCount;

    private BadgeHelper() {
    }

    public final int getBadgeCount() {
        int i;
        synchronized (this) {
            i = badgeCount;
        }
        return i;
    }

    private final void setBadgeCount(int i) {
        synchronized (this) {
            badgeCount = i;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean setBadgeCount(Context context, int badgeCount2) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            if (badgeCount2 == 0) {
                Object systemService = context.getSystemService(NotificationsService.NOTIFICATION_KEY);
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                ((NotificationManager) systemService).cancelAll();
            } else {
                ShortcutBadger.applyCountOrThrow(context.getApplicationContext(), badgeCount2);
            }
            INSTANCE.setBadgeCount(badgeCount2);
            return true;
        } catch (ShortcutBadgeException e) {
            Log.d("expo-notifications", "Could not have set badge count: " + e.getMessage(), e);
            e.printStackTrace();
            return false;
        }
    }
}

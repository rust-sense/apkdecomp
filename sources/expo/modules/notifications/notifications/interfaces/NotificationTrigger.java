package expo.modules.notifications.notifications.interfaces;

import android.os.Parcelable;

/* loaded from: classes2.dex */
public interface NotificationTrigger extends Parcelable {

    /* renamed from: expo.modules.notifications.notifications.interfaces.NotificationTrigger$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static String $default$getNotificationChannel(NotificationTrigger _this) {
            return null;
        }
    }

    String getNotificationChannel();
}

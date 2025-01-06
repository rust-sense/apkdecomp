package expo.modules.notifications.service.delegates;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import expo.modules.notifications.notifications.NotificationManager;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.service.NotificationForwarderActivity;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.interfaces.HandlingDelegate;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoHandlingDelegate.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\u0006\u0010\u0012\u001a\u00020\u0013J\f\u0010\u0014\u001a\u00020\u0013*\u00020\rH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoHandlingDelegate;", "Lexpo/modules/notifications/service/interfaces/HandlingDelegate;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getListeners", "", "Lexpo/modules/notifications/notifications/NotificationManager;", "handleNotification", "", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "handleNotificationResponse", NotificationsService.NOTIFICATION_RESPONSE_KEY, "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "handleNotificationsDropped", "isAppInForeground", "", "shouldPresent", "Companion", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoHandlingDelegate implements HandlingDelegate {
    public static final String OPEN_APP_INTENT_ACTION = "expo.modules.notifications.OPEN_APP_ACTION";
    private final Context context;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Collection<NotificationResponse> sPendingNotificationResponses = new ArrayList();
    private static WeakHashMap<NotificationManager, WeakReference<NotificationManager>> sListenersReferences = new WeakHashMap<>();

    protected final Context getContext() {
        return this.context;
    }

    /* compiled from: ExpoHandlingDelegate.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007J\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0016\u0010 \u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R,\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoHandlingDelegate$Companion;", "", "()V", "OPEN_APP_INTENT_ACTION", "", "sListenersReferences", "Ljava/util/WeakHashMap;", "Lexpo/modules/notifications/notifications/NotificationManager;", "Ljava/lang/ref/WeakReference;", "getSListenersReferences", "()Ljava/util/WeakHashMap;", "setSListenersReferences", "(Ljava/util/WeakHashMap;)V", "sPendingNotificationResponses", "", "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "getSPendingNotificationResponses", "()Ljava/util/Collection;", "setSPendingNotificationResponses", "(Ljava/util/Collection;)V", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "createPendingIntentForOpeningApp", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "broadcastIntent", "Landroid/content/Intent;", NotificationsService.NOTIFICATION_RESPONSE_KEY, "getMainActivityLauncher", "getNotificationActionLauncher", "openAppToForeground", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        protected final Collection<NotificationResponse> getSPendingNotificationResponses() {
            return ExpoHandlingDelegate.sPendingNotificationResponses;
        }

        protected final void setSPendingNotificationResponses(Collection<NotificationResponse> collection) {
            Intrinsics.checkNotNullParameter(collection, "<set-?>");
            ExpoHandlingDelegate.sPendingNotificationResponses = collection;
        }

        protected final WeakHashMap<NotificationManager, WeakReference<NotificationManager>> getSListenersReferences() {
            return ExpoHandlingDelegate.sListenersReferences;
        }

        protected final void setSListenersReferences(WeakHashMap<NotificationManager, WeakReference<NotificationManager>> weakHashMap) {
            Intrinsics.checkNotNullParameter(weakHashMap, "<set-?>");
            ExpoHandlingDelegate.sListenersReferences = weakHashMap;
        }

        public final void addListener(NotificationManager listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            if (getSListenersReferences().containsKey(listener)) {
                return;
            }
            getSListenersReferences().put(listener, new WeakReference<>(listener));
            if (getSPendingNotificationResponses().isEmpty()) {
                return;
            }
            Iterator<NotificationResponse> it = getSPendingNotificationResponses().iterator();
            while (it.hasNext()) {
                listener.onNotificationResponseReceived(it.next());
                it.remove();
            }
        }

        public final PendingIntent createPendingIntentForOpeningApp(Context context, Intent broadcastIntent, NotificationResponse notificationResponse) {
            String className;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(broadcastIntent, "broadcastIntent");
            Intrinsics.checkNotNullParameter(notificationResponse, "notificationResponse");
            int i = Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728;
            Intent intent = new Intent(context, (Class<?>) NotificationForwarderActivity.class);
            intent.setData(broadcastIntent.getData());
            intent.setFlags(402653184);
            intent.putExtras(broadcastIntent);
            ComponentName component = broadcastIntent.getComponent();
            PendingIntent activity = PendingIntent.getActivity(context, (component == null || (className = component.getClassName()) == null) ? NotificationsService.class.hashCode() : className.hashCode(), intent, i);
            Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
            return activity;
        }

        public final void openAppToForeground(Context context, NotificationResponse notificationResponse) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notificationResponse, "notificationResponse");
            Intent notificationActionLauncher = getNotificationActionLauncher(context);
            if (notificationActionLauncher == null) {
                notificationActionLauncher = getMainActivityLauncher(context);
            }
            if (notificationActionLauncher != null) {
                NotificationsService.INSTANCE.setNotificationResponseToIntent(notificationActionLauncher, notificationResponse);
                context.startActivity(notificationActionLauncher);
            } else {
                Log.w("expo-notifications", "No launch intent found for application. Interacting with the notification won't open the app. The implementation uses `getLaunchIntentForPackage` to find appropriate activity.");
            }
        }

        private final Intent getNotificationActionLauncher(Context context) {
            Intent intent = new Intent(ExpoHandlingDelegate.OPEN_APP_INTENT_ACTION);
            intent.addFlags(268435456);
            intent.setPackage(context.getApplicationContext().getPackageName());
            if (context.getPackageManager().resolveActivity(intent, 0) != null) {
                return intent;
            }
            return null;
        }

        private final Intent getMainActivityLauncher(Context context) {
            return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        }
    }

    public ExpoHandlingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final boolean isAppInForeground() {
        return ProcessLifecycleOwner.get().getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
    }

    public final List<NotificationManager> getListeners() {
        Collection<WeakReference<NotificationManager>> values = sListenersReferences.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            NotificationManager notificationManager = (NotificationManager) ((WeakReference) it.next()).get();
            if (notificationManager != null) {
                arrayList.add(notificationManager);
            }
        }
        return arrayList;
    }

    @Override // expo.modules.notifications.service.interfaces.HandlingDelegate
    public void handleNotification(Notification notification) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        if (!isAppInForeground()) {
            if (shouldPresent(notification)) {
                NotificationsService.Companion.present$default(NotificationsService.INSTANCE, this.context, notification, null, null, 12, null);
            }
        } else {
            Iterator<T> it = getListeners().iterator();
            while (it.hasNext()) {
                ((NotificationManager) it.next()).onNotificationReceived(notification);
            }
        }
    }

    private final boolean shouldPresent(Notification notification) {
        String text;
        String title = notification.getNotificationRequest().getContent().getTitle();
        return ((title == null || title.length() == 0) && ((text = notification.getNotificationRequest().getContent().getText()) == null || text.length() == 0)) ? false : true;
    }

    @Override // expo.modules.notifications.service.interfaces.HandlingDelegate
    public void handleNotificationResponse(NotificationResponse notificationResponse) {
        Intrinsics.checkNotNullParameter(notificationResponse, "notificationResponse");
        if (notificationResponse.getAction().opensAppToForeground()) {
            INSTANCE.openAppToForeground(this.context, notificationResponse);
        }
        if (getListeners().isEmpty()) {
            sPendingNotificationResponses.add(notificationResponse);
            return;
        }
        Iterator<T> it = getListeners().iterator();
        while (it.hasNext()) {
            ((NotificationManager) it.next()).onNotificationResponseReceived(notificationResponse);
        }
    }

    @Override // expo.modules.notifications.service.interfaces.HandlingDelegate
    public void handleNotificationsDropped() {
        Iterator<T> it = getListeners().iterator();
        while (it.hasNext()) {
            ((NotificationManager) it.next()).onNotificationsDropped();
        }
    }
}

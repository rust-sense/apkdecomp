package expo.modules.notifications.service.delegates;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.core.app.AlarmManagerCompat;
import androidx.core.app.NotificationCompat;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.interfaces.SchedulingDelegate;
import io.sentry.SentryBaseEvent;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoSchedulingDelegate.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0016\u0010\u0017\u001a\u00020\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u0010H\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0011H\u0016J\u0018\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u0016H\u0016J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\""}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoSchedulingDelegate;", "Lexpo/modules/notifications/service/interfaces/SchedulingDelegate;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "alarmManager", "Landroid/app/AlarmManager;", "getAlarmManager", "()Landroid/app/AlarmManager;", "getContext", "()Landroid/content/Context;", "store", "Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationsStore;", "getStore", "()Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationsStore;", "getAllScheduledNotifications", "", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "getScheduledNotification", "identifier", "", "removeAllScheduledNotifications", "", "removeScheduledNotifications", NotificationsService.IDENTIFIERS_KEY, "scheduleNotification", SentryBaseEvent.JsonKeys.REQUEST, "setupAlarm", "triggerAtMillis", "", "operation", "Landroid/app/PendingIntent;", "setupScheduledNotifications", "triggerNotification", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExpoSchedulingDelegate implements SchedulingDelegate {
    private final AlarmManager alarmManager;
    private final Context context;
    private final SharedPreferencesNotificationsStore store;

    protected final AlarmManager getAlarmManager() {
        return this.alarmManager;
    }

    protected final Context getContext() {
        return this.context;
    }

    protected final SharedPreferencesNotificationsStore getStore() {
        return this.store;
    }

    public ExpoSchedulingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.store = new SharedPreferencesNotificationsStore(context);
        Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AlarmManager");
        this.alarmManager = (AlarmManager) systemService;
    }

    @Override // expo.modules.notifications.service.interfaces.SchedulingDelegate
    public void setupScheduledNotifications() {
        for (NotificationRequest notificationRequest : this.store.getAllNotificationRequests()) {
            try {
                scheduleNotification(notificationRequest);
            } catch (Exception e) {
                Log.w("expo-notifications", "Notification " + notificationRequest.getIdentifier() + " could not have been scheduled: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override // expo.modules.notifications.service.interfaces.SchedulingDelegate
    public Collection<NotificationRequest> getAllScheduledNotifications() {
        return this.store.getAllNotificationRequests();
    }

    @Override // expo.modules.notifications.service.interfaces.SchedulingDelegate
    public NotificationRequest getScheduledNotification(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        try {
            return this.store.getNotificationRequest(identifier);
        } catch (IOException | ClassNotFoundException | NullPointerException unused) {
            return null;
        }
    }

    @Override // expo.modules.notifications.service.interfaces.SchedulingDelegate
    public void scheduleNotification(NotificationRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (request.getTrigger() == null) {
            NotificationsService.Companion.receive$default(NotificationsService.INSTANCE, this.context, new Notification(request), null, 4, null);
            return;
        }
        if (!(request.getTrigger() instanceof SchedulableNotificationTrigger)) {
            throw new IllegalArgumentException("Notification request \"" + request.getIdentifier() + "\" does not have a schedulable trigger (it's " + request.getTrigger() + "). Refusing to schedule.");
        }
        NotificationTrigger trigger = request.getTrigger();
        Intrinsics.checkNotNull(trigger, "null cannot be cast to non-null type expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger");
        Date nextTriggerDate = ((SchedulableNotificationTrigger) trigger).nextTriggerDate();
        if (nextTriggerDate == null) {
            Log.d("expo-notifications", "Notification request \"" + request.getIdentifier() + "\" will not trigger in the future, removing.");
            NotificationsService.Companion companion = NotificationsService.INSTANCE;
            Context context = this.context;
            String identifier = request.getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            NotificationsService.Companion.removeScheduledNotification$default(companion, context, identifier, null, 4, null);
            return;
        }
        this.store.saveNotificationRequest(request);
        long time = nextTriggerDate.getTime();
        NotificationsService.Companion companion2 = NotificationsService.INSTANCE;
        Context context2 = this.context;
        String identifier2 = request.getIdentifier();
        Intrinsics.checkNotNullExpressionValue(identifier2, "getIdentifier(...)");
        setupAlarm(time, companion2.createNotificationTrigger(context2, identifier2));
    }

    @Override // expo.modules.notifications.service.interfaces.SchedulingDelegate
    public void triggerNotification(String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        try {
            NotificationRequest notificationRequest = this.store.getNotificationRequest(identifier);
            Intrinsics.checkNotNull(notificationRequest);
            NotificationsService.Companion.receive$default(NotificationsService.INSTANCE, this.context, new Notification(notificationRequest), null, 4, null);
            NotificationsService.Companion.schedule$default(NotificationsService.INSTANCE, this.context, notificationRequest, null, 4, null);
        } catch (InvalidClassException e) {
            Log.e("expo-notifications", "An exception occurred while triggering notification " + identifier + ", removing. " + e.getMessage());
            e.printStackTrace();
            NotificationsService.Companion.removeScheduledNotification$default(NotificationsService.INSTANCE, this.context, identifier, null, 4, null);
        } catch (ClassNotFoundException e2) {
            Log.e("expo-notifications", "An exception occurred while triggering notification " + identifier + ", removing. " + e2.getMessage());
            e2.printStackTrace();
            NotificationsService.Companion.removeScheduledNotification$default(NotificationsService.INSTANCE, this.context, identifier, null, 4, null);
        } catch (NullPointerException e3) {
            Log.e("expo-notifications", "An exception occurred while triggering notification " + identifier + ", removing. " + e3.getMessage());
            e3.printStackTrace();
            NotificationsService.Companion.removeScheduledNotification$default(NotificationsService.INSTANCE, this.context, identifier, null, 4, null);
        }
    }

    @Override // expo.modules.notifications.service.interfaces.SchedulingDelegate
    public void removeScheduledNotifications(Collection<String> identifiers) {
        Intrinsics.checkNotNullParameter(identifiers, "identifiers");
        for (String str : identifiers) {
            this.alarmManager.cancel(NotificationsService.INSTANCE.createNotificationTrigger(this.context, str));
            this.store.removeNotificationRequest(str);
        }
    }

    @Override // expo.modules.notifications.service.interfaces.SchedulingDelegate
    public void removeAllScheduledNotifications() {
        Iterator<T> it = this.store.removeAllNotificationRequests().iterator();
        while (it.hasNext()) {
            this.alarmManager.cancel(NotificationsService.INSTANCE.createNotificationTrigger(this.context, (String) it.next()));
        }
    }

    private final void setupAlarm(long triggerAtMillis, PendingIntent operation) {
        boolean canScheduleExactAlarms;
        if (Build.VERSION.SDK_INT >= 31) {
            canScheduleExactAlarms = this.alarmManager.canScheduleExactAlarms();
            if (!canScheduleExactAlarms) {
                AlarmManagerCompat.setAndAllowWhileIdle(this.alarmManager, 0, triggerAtMillis, operation);
                return;
            }
        }
        AlarmManagerCompat.setExactAndAllowWhileIdle(this.alarmManager, 0, triggerAtMillis, operation);
    }
}

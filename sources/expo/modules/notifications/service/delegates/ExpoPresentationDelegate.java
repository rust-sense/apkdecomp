package expo.modules.notifications.service.delegates;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.Pair;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.presentation.builders.CategoryAwareNotificationBuilder;
import expo.modules.notifications.notifications.presentation.builders.ExpoNotificationBuilder;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.interfaces.PresentationDelegate;
import io.sentry.SentryBaseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ExpoPresentationDelegate.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 !2\u00020\u0001:\u0001!B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0016\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u001a\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010\fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\""}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoPresentationDelegate;", "Lexpo/modules/notifications/service/interfaces/PresentationDelegate;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "createNotification", "Landroid/app/Notification;", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", NotificationsService.NOTIFICATION_BEHAVIOR_KEY, "Lexpo/modules/notifications/notifications/model/NotificationBehavior;", "dismissAllNotifications", "", "dismissNotifications", NotificationsService.IDENTIFIERS_KEY, "", "", "fromBundle", "Lorg/json/JSONObject;", "bundle", "Landroid/os/Bundle;", "getAllPresentedNotifications", "getNotification", "statusBarNotification", "Landroid/service/notification/StatusBarNotification;", "getNotifyId", "", SentryBaseEvent.JsonKeys.REQUEST, "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "presentNotification", "behavior", "Companion", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ExpoPresentationDelegate implements PresentationDelegate {
    protected static final int ANDROID_NOTIFICATION_ID = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    protected static final String INTERNAL_IDENTIFIER_AUTHORITY = "foreign_notifications";
    protected static final String INTERNAL_IDENTIFIER_ID_KEY = "id";
    protected static final String INTERNAL_IDENTIFIER_SCHEME = "expo-notifications";
    protected static final String INTERNAL_IDENTIFIER_TAG_KEY = "tag";
    private final Context context;

    protected final Context getContext() {
        return this.context;
    }

    protected int getNotifyId(NotificationRequest request) {
        return 0;
    }

    public ExpoPresentationDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* compiled from: ExpoPresentationDelegate.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0004J\u001e\u0010\r\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoPresentationDelegate$Companion;", "", "()V", "ANDROID_NOTIFICATION_ID", "", "INTERNAL_IDENTIFIER_AUTHORITY", "", "INTERNAL_IDENTIFIER_ID_KEY", "INTERNAL_IDENTIFIER_SCHEME", "INTERNAL_IDENTIFIER_TAG_KEY", "getInternalIdentifierKey", NotificationsService.NOTIFICATION_KEY, "Landroid/service/notification/StatusBarNotification;", "parseNotificationIdentifier", "Landroid/util/Pair;", "identifier", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Pair<String, Integer> parseNotificationIdentifier(String identifier) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            try {
                Uri parse = Uri.parse(identifier);
                if (!Intrinsics.areEqual(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_SCHEME, parse.getScheme()) || !Intrinsics.areEqual(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_AUTHORITY, parse.getAuthority())) {
                    return null;
                }
                String queryParameter = parse.getQueryParameter("tag");
                String queryParameter2 = parse.getQueryParameter("id");
                Intrinsics.checkNotNull(queryParameter2);
                return new Pair<>(queryParameter, Integer.valueOf(Integer.parseInt(queryParameter2)));
            } catch (NullPointerException e) {
                Log.e(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_SCHEME, "Malformed foreign notification identifier: " + identifier, e);
                return null;
            } catch (NumberFormatException e2) {
                Log.e(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_SCHEME, "Malformed foreign notification identifier: " + identifier, e2);
                return null;
            } catch (UnsupportedOperationException e3) {
                Log.e(ExpoPresentationDelegate.INTERNAL_IDENTIFIER_SCHEME, "Malformed foreign notification identifier: " + identifier, e3);
                return null;
            }
        }

        protected final String getInternalIdentifierKey(StatusBarNotification notification) {
            Intrinsics.checkNotNullParameter(notification, "notification");
            Uri.Builder buildUpon = Uri.parse("expo-notifications://foreign_notifications").buildUpon();
            String tag = notification.getTag();
            if (tag != null) {
                Intrinsics.checkNotNull(tag);
                buildUpon.appendQueryParameter("tag", tag);
            }
            buildUpon.appendQueryParameter("id", String.valueOf(notification.getId()));
            String builder = buildUpon.toString();
            Intrinsics.checkNotNullExpressionValue(builder, "with(...)");
            return builder;
        }
    }

    @Override // expo.modules.notifications.service.interfaces.PresentationDelegate
    public void presentNotification(Notification notification, NotificationBehavior behavior) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        if (behavior != null && !behavior.shouldShowAlert()) {
            if (behavior.shouldPlaySound()) {
                Context context = this.context;
                Uri sound = notification.getNotificationRequest().getContent().getSound();
                if (sound == null) {
                    sound = Settings.System.DEFAULT_NOTIFICATION_URI;
                }
                RingtoneManager.getRingtone(context, sound).play();
                return;
            }
            return;
        }
        NotificationManagerCompat.from(this.context).notify(notification.getNotificationRequest().getIdentifier(), getNotifyId(notification.getNotificationRequest()), createNotification(notification, behavior));
    }

    @Override // expo.modules.notifications.service.interfaces.PresentationDelegate
    public Collection<Notification> getAllPresentedNotifications() {
        Object systemService = this.context.getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        StatusBarNotification[] activeNotifications = ((NotificationManager) systemService).getActiveNotifications();
        Intrinsics.checkNotNullExpressionValue(activeNotifications, "getActiveNotifications(...)");
        ArrayList arrayList = new ArrayList();
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            Intrinsics.checkNotNull(statusBarNotification);
            Notification notification = getNotification(statusBarNotification);
            if (notification != null) {
                arrayList.add(notification);
            }
        }
        return arrayList;
    }

    @Override // expo.modules.notifications.service.interfaces.PresentationDelegate
    public void dismissNotifications(Collection<String> identifiers) {
        Object obj;
        Intrinsics.checkNotNullParameter(identifiers, "identifiers");
        for (String str : identifiers) {
            Pair<String, Integer> parseNotificationIdentifier = INSTANCE.parseNotificationIdentifier(str);
            if (parseNotificationIdentifier != null) {
                NotificationManagerCompat from = NotificationManagerCompat.from(this.context);
                String str2 = (String) parseNotificationIdentifier.first;
                Object second = parseNotificationIdentifier.second;
                Intrinsics.checkNotNullExpressionValue(second, "second");
                from.cancel(str2, ((Number) second).intValue());
            } else {
                Iterator<T> it = getAllPresentedNotifications().iterator();
                while (true) {
                    if (it.hasNext()) {
                        obj = it.next();
                        if (Intrinsics.areEqual(((Notification) obj).getNotificationRequest().getIdentifier(), str)) {
                            break;
                        }
                    } else {
                        obj = null;
                        break;
                    }
                }
                Notification notification = (Notification) obj;
                NotificationManagerCompat.from(this.context).cancel(str, getNotifyId(notification != null ? notification.getNotificationRequest() : null));
            }
        }
    }

    @Override // expo.modules.notifications.service.interfaces.PresentationDelegate
    public void dismissAllNotifications() {
        NotificationManagerCompat.from(this.context).cancelAll();
    }

    protected android.app.Notification createNotification(Notification notification, NotificationBehavior notificationBehavior) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        CategoryAwareNotificationBuilder categoryAwareNotificationBuilder = new CategoryAwareNotificationBuilder(this.context, new SharedPreferencesNotificationCategoriesStore(this.context));
        categoryAwareNotificationBuilder.setNotification(notification);
        categoryAwareNotificationBuilder.setAllowedBehavior(notificationBehavior);
        android.app.Notification build = categoryAwareNotificationBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    protected Notification getNotification(StatusBarNotification statusBarNotification) {
        Intrinsics.checkNotNullParameter(statusBarNotification, "statusBarNotification");
        android.app.Notification notification = statusBarNotification.getNotification();
        byte[] byteArray = notification.extras.getByteArray(ExpoNotificationBuilder.EXTRAS_MARSHALLED_NOTIFICATION_REQUEST_KEY);
        if (byteArray != null) {
            try {
                Parcel obtain = Parcel.obtain();
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                NotificationRequest createFromParcel = NotificationRequest.CREATOR.createFromParcel(obtain);
                Intrinsics.checkNotNullExpressionValue(createFromParcel, "createFromParcel(...)");
                obtain.recycle();
                return new Notification(createFromParcel, new Date(statusBarNotification.getPostTime()));
            } catch (Exception unused) {
                Log.e(INTERNAL_IDENTIFIER_SCHEME, "Could not have unmarshalled NotificationRequest from (" + statusBarNotification.getTag() + ", " + statusBarNotification.getId() + ").");
            }
        }
        NotificationContent.Builder sticky = new NotificationContent.Builder().setTitle(notification.extras.getString(NotificationCompat.EXTRA_TITLE)).setText(notification.extras.getString(NotificationCompat.EXTRA_TEXT)).setSubtitle(notification.extras.getString(NotificationCompat.EXTRA_SUB_TEXT)).setPriority(NotificationPriority.fromNativeValue(notification.priority)).setVibrationPattern(notification.vibrate).setSound(notification.sound).setAutoDismiss((notification.flags & 16) != 0).setSticky((notification.flags & 2) != 0);
        Bundle extras = notification.extras;
        Intrinsics.checkNotNullExpressionValue(extras, "extras");
        return new Notification(new NotificationRequest(INSTANCE.getInternalIdentifierKey(statusBarNotification), sticky.setBody(fromBundle(extras)).build(), null), new Date(statusBarNotification.getPostTime()));
    }

    protected JSONObject fromBundle(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, JSONObject.wrap(bundle.get(str)));
            } catch (JSONException e) {
                Log.d(INTERNAL_IDENTIFIER_SCHEME, "Error encountered while serializing Android notification extras: " + str + " -> " + bundle.get(str), e);
            }
        }
        return jSONObject;
    }
}

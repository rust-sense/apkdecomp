package expo.modules.notifications.service.delegates;

import android.content.Context;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.notifications.JSONNotificationContentBuilder;
import expo.modules.notifications.notifications.RemoteMessageSerializer;
import expo.modules.notifications.notifications.background.BackgroundRemoteNotificationTaskConsumer;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.model.triggers.FirebaseNotificationTrigger;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.tokens.interfaces.FirebaseTokenListener;
import io.sentry.protocol.ViewHierarchyNode;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: FirebaseMessagingDelegate.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0004J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000eH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001d"}, d2 = {"Lexpo/modules/notifications/service/delegates/FirebaseMessagingDelegate;", "Lexpo/modules/notifications/service/interfaces/FirebaseMessagingDelegate;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "createNotification", "Lexpo/modules/notifications/notifications/model/Notification;", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "createNotificationRequest", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "identifier", "", UriUtil.LOCAL_CONTENT_SCHEME, "Lexpo/modules/notifications/notifications/model/NotificationContent;", "notificationTrigger", "Lexpo/modules/notifications/notifications/model/triggers/FirebaseNotificationTrigger;", "getBackgroundTasks", "", "Lexpo/modules/notifications/notifications/background/BackgroundRemoteNotificationTaskConsumer;", "getNotificationIdentifier", "onDeletedMessages", "", "onMessageReceived", "onNewToken", "token", "Companion", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class FirebaseMessagingDelegate implements expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate {
    private static String sLastToken;
    private final Context context;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final WeakHashMap<FirebaseTokenListener, WeakReference<FirebaseTokenListener>> sTokenListenersReferences = new WeakHashMap<>();
    private static WeakHashMap<BackgroundRemoteNotificationTaskConsumer, WeakReference<BackgroundRemoteNotificationTaskConsumer>> sBackgroundTaskConsumerReferences = new WeakHashMap<>();

    @JvmStatic
    public static final void addTokenListener(FirebaseTokenListener firebaseTokenListener) {
        INSTANCE.addTokenListener(firebaseTokenListener);
    }

    protected final Context getContext() {
        return this.context;
    }

    /* compiled from: FirebaseMessagingDelegate.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0012H\u0007R,\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R*\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00060\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\b¨\u0006\u0019"}, d2 = {"Lexpo/modules/notifications/service/delegates/FirebaseMessagingDelegate$Companion;", "", "()V", "sBackgroundTaskConsumerReferences", "Ljava/util/WeakHashMap;", "Lexpo/modules/notifications/notifications/background/BackgroundRemoteNotificationTaskConsumer;", "Ljava/lang/ref/WeakReference;", "getSBackgroundTaskConsumerReferences", "()Ljava/util/WeakHashMap;", "setSBackgroundTaskConsumerReferences", "(Ljava/util/WeakHashMap;)V", "sLastToken", "", "getSLastToken", "()Ljava/lang/String;", "setSLastToken", "(Ljava/lang/String;)V", "sTokenListenersReferences", "Lexpo/modules/notifications/tokens/interfaces/FirebaseTokenListener;", "getSTokenListenersReferences", "addBackgroundTaskConsumer", "", "taskConsumer", "addTokenListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        protected final String getSLastToken() {
            return FirebaseMessagingDelegate.sLastToken;
        }

        protected final void setSLastToken(String str) {
            FirebaseMessagingDelegate.sLastToken = str;
        }

        protected final WeakHashMap<FirebaseTokenListener, WeakReference<FirebaseTokenListener>> getSTokenListenersReferences() {
            return FirebaseMessagingDelegate.sTokenListenersReferences;
        }

        @JvmStatic
        public final void addTokenListener(FirebaseTokenListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            if (getSTokenListenersReferences().containsKey(listener)) {
                return;
            }
            getSTokenListenersReferences().put(listener, new WeakReference<>(listener));
            if (getSLastToken() != null) {
                listener.onNewToken(getSLastToken());
            }
        }

        protected final WeakHashMap<BackgroundRemoteNotificationTaskConsumer, WeakReference<BackgroundRemoteNotificationTaskConsumer>> getSBackgroundTaskConsumerReferences() {
            return FirebaseMessagingDelegate.sBackgroundTaskConsumerReferences;
        }

        protected final void setSBackgroundTaskConsumerReferences(WeakHashMap<BackgroundRemoteNotificationTaskConsumer, WeakReference<BackgroundRemoteNotificationTaskConsumer>> weakHashMap) {
            Intrinsics.checkNotNullParameter(weakHashMap, "<set-?>");
            FirebaseMessagingDelegate.sBackgroundTaskConsumerReferences = weakHashMap;
        }

        public final void addBackgroundTaskConsumer(BackgroundRemoteNotificationTaskConsumer taskConsumer) {
            Intrinsics.checkNotNullParameter(taskConsumer, "taskConsumer");
            if (getSBackgroundTaskConsumerReferences().containsKey(taskConsumer)) {
                return;
            }
            getSBackgroundTaskConsumerReferences().put(taskConsumer, new WeakReference<>(taskConsumer));
        }
    }

    public FirebaseMessagingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate
    public void onNewToken(String token) {
        FirebaseTokenListener firebaseTokenListener;
        Intrinsics.checkNotNullParameter(token, "token");
        for (WeakReference<FirebaseTokenListener> weakReference : sTokenListenersReferences.values()) {
            if (weakReference != null && (firebaseTokenListener = weakReference.get()) != null) {
                firebaseTokenListener.onNewToken(token);
            }
        }
        sLastToken = token;
    }

    public final List<BackgroundRemoteNotificationTaskConsumer> getBackgroundTasks() {
        Collection<WeakReference<BackgroundRemoteNotificationTaskConsumer>> values = sBackgroundTaskConsumerReferences.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = values.iterator();
        while (it.hasNext()) {
            BackgroundRemoteNotificationTaskConsumer backgroundRemoteNotificationTaskConsumer = (BackgroundRemoteNotificationTaskConsumer) ((WeakReference) it.next()).get();
            if (backgroundRemoteNotificationTaskConsumer != null) {
                arrayList.add(backgroundRemoteNotificationTaskConsumer);
            }
        }
        return arrayList;
    }

    @Override // expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        NotificationsService.Companion.receive$default(NotificationsService.INSTANCE, this.context, createNotification(remoteMessage), null, 4, null);
        Iterator<T> it = getBackgroundTasks().iterator();
        while (it.hasNext()) {
            ((BackgroundRemoteNotificationTaskConsumer) it.next()).scheduleJob(RemoteMessageSerializer.toBundle(remoteMessage));
        }
    }

    protected final Notification createNotification(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        String notificationIdentifier = getNotificationIdentifier(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type kotlin.collections.Map<*, *>");
        NotificationContent build = new JSONNotificationContentBuilder(this.context).setPayload(new JSONObject(data)).build();
        Intrinsics.checkNotNull(build);
        return new Notification(createNotificationRequest(notificationIdentifier, build, new FirebaseNotificationTrigger(remoteMessage)), new Date(remoteMessage.getSentTime()));
    }

    protected final String getNotificationIdentifier(RemoteMessage remoteMessage) {
        String str;
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        Map<String, String> data = remoteMessage.getData();
        if (data != null && (str = data.get(ViewHierarchyNode.JsonKeys.TAG)) != null) {
            return str;
        }
        String messageId = remoteMessage.getMessageId();
        if (messageId != null) {
            return messageId;
        }
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        return uuid;
    }

    protected NotificationRequest createNotificationRequest(String identifier, NotificationContent content, FirebaseNotificationTrigger notificationTrigger) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(notificationTrigger, "notificationTrigger");
        return new NotificationRequest(identifier, content, notificationTrigger);
    }

    @Override // expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate
    public void onDeletedMessages() {
        NotificationsService.INSTANCE.handleDropped(this.context);
    }
}

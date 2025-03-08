package expo.modules.notifications.notifications.emitting;

import android.os.Bundle;
import androidx.tracing.Trace;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.notifications.notifications.NotificationSerializer;
import expo.modules.notifications.notifications.interfaces.NotificationListener;
import expo.modules.notifications.notifications.interfaces.NotificationManager;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.service.NotificationsService;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationsEmitter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lexpo/modules/notifications/notifications/emitting/NotificationsEmitter;", "Lexpo/modules/kotlin/modules/Module;", "Lexpo/modules/notifications/notifications/interfaces/NotificationListener;", "()V", "lastNotificationResponseBundle", "Landroid/os/Bundle;", "notificationManager", "Lexpo/modules/notifications/notifications/interfaces/NotificationManager;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "onNotificationReceived", "", NotificationsService.NOTIFICATION_KEY, "Lexpo/modules/notifications/notifications/model/Notification;", "onNotificationResponseIntentReceived", "extras", "onNotificationResponseReceived", "", Response.TYPE, "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "onNotificationsDropped", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class NotificationsEmitter extends Module implements NotificationListener {
    private Bundle lastNotificationResponseBundle;
    private NotificationManager notificationManager;

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        NotificationsEmitter notificationsEmitter = this;
        Trace.beginSection("[ExpoModulesCore] " + (notificationsEmitter.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(notificationsEmitter);
            moduleDefinitionBuilder.Name("ExpoNotificationsEmitter");
            moduleDefinitionBuilder.Events("onDidReceiveNotification", "onNotificationsDeleted", "onDidReceiveNotificationResponse");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.notifications.notifications.emitting.NotificationsEmitter$definition$lambda$3$$inlined$OnCreate$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    NotificationManager notificationManager;
                    NotificationsEmitter notificationsEmitter2 = NotificationsEmitter.this;
                    Object singletonModule = notificationsEmitter2.getAppContext().getLegacyModuleRegistry().getSingletonModule("NotificationManager", NotificationManager.class);
                    if (singletonModule != null) {
                        notificationsEmitter2.notificationManager = (NotificationManager) singletonModule;
                        notificationManager = NotificationsEmitter.this.notificationManager;
                        if (notificationManager == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
                            notificationManager = null;
                        }
                        notificationManager.addListener(NotificationsEmitter.this);
                        return;
                    }
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
            }));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new Function0<Unit>() { // from class: expo.modules.notifications.notifications.emitting.NotificationsEmitter$definition$lambda$3$$inlined$OnDestroy$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    NotificationManager notificationManager;
                    notificationManager = NotificationsEmitter.this.notificationManager;
                    if (notificationManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
                        notificationManager = null;
                    }
                    notificationManager.removeListener(NotificationsEmitter.this);
                }
            }));
            moduleDefinitionBuilder.getAsyncFunctions().put("getLastNotificationResponseAsync", new AsyncFunctionComponent("getLastNotificationResponseAsync", new AnyType[0], new Function1<Object[], Bundle>() { // from class: expo.modules.notifications.notifications.emitting.NotificationsEmitter$definition$lambda$3$$inlined$AsyncFunction$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Bundle invoke(Object[] it) {
                    Bundle bundle;
                    Intrinsics.checkNotNullParameter(it, "it");
                    bundle = NotificationsEmitter.this.lastNotificationResponseBundle;
                    return bundle;
                }
            }));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationListener
    public void onNotificationReceived(Notification notification) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        sendEvent("onDidReceiveNotification", NotificationSerializer.toBundle(notification));
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationListener
    public boolean onNotificationResponseReceived(NotificationResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        Bundle bundle = NotificationSerializer.toBundle(response);
        this.lastNotificationResponseBundle = bundle;
        sendEvent("onDidReceiveNotificationResponse", bundle);
        return true;
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationListener
    public void onNotificationResponseIntentReceived(Bundle extras) {
        Bundle responseBundleFromExtras = NotificationSerializer.toResponseBundleFromExtras(extras);
        this.lastNotificationResponseBundle = responseBundleFromExtras;
        sendEvent("onDidReceiveNotificationResponse", responseBundleFromExtras);
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationListener
    public void onNotificationsDropped() {
        sendEvent("onNotificationsDeleted", Bundle.EMPTY);
    }
}

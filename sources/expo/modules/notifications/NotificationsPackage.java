package expo.modules.notifications;

import android.content.Context;
import expo.modules.core.BasePackage;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.core.interfaces.SingletonModule;
import expo.modules.notifications.notifications.NotificationManager;
import expo.modules.notifications.notifications.categories.serializers.ExpoNotificationsCategoriesSerializer;
import expo.modules.notifications.notifications.channels.AndroidXNotificationsChannelsProvider;
import expo.modules.notifications.service.delegates.ExpoNotificationLifecycleListener;
import expo.modules.notifications.tokens.PushTokenManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class NotificationsPackage extends BasePackage {
    private NotificationManager mNotificationManager = new NotificationManager();

    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<SingletonModule> createSingletonModules(Context context) {
        return Arrays.asList(new PushTokenManager(), this.mNotificationManager);
    }

    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<InternalModule> createInternalModules(Context context) {
        return Arrays.asList(new AndroidXNotificationsChannelsProvider(context), new ExpoNotificationsCategoriesSerializer());
    }

    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<ReactActivityLifecycleListener> createReactActivityLifecycleListeners(Context context) {
        return Collections.singletonList(new ExpoNotificationLifecycleListener(context, this.mNotificationManager));
    }
}

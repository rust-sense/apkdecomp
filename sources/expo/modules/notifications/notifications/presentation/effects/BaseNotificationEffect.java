package expo.modules.notifications.notifications.presentation.effects;

import android.app.Notification;
import android.content.Context;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.notifications.notifications.interfaces.NotificationPresentationEffect;
import expo.modules.notifications.notifications.interfaces.NotificationPresentationEffectsManager;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseNotificationEffect implements InternalModule, NotificationPresentationEffect {
    private Context mContext;
    private NotificationPresentationEffectsManager mManager;

    protected Context getContext() {
        return this.mContext;
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationPresentationEffect
    public boolean onNotificationPresentationFailed(String str, int i, Notification notification) {
        return false;
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationPresentationEffect
    public boolean onNotificationPresented(String str, int i, Notification notification) {
        return false;
    }

    public BaseNotificationEffect(Context context) {
        this.mContext = context;
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(getClass());
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        NotificationPresentationEffectsManager notificationPresentationEffectsManager = (NotificationPresentationEffectsManager) moduleRegistry.getModule(NotificationPresentationEffectsManager.class);
        this.mManager = notificationPresentationEffectsManager;
        notificationPresentationEffectsManager.addEffect(this);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onDestroy() {
        this.mManager.removeEffect(this);
    }
}

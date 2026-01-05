package expo.modules.notifications.notifications.presentation;

import android.app.Notification;
import android.util.Log;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.notifications.notifications.interfaces.NotificationPresentationEffect;
import expo.modules.notifications.notifications.interfaces.NotificationPresentationEffectsManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class ExpoNotificationPresentationEffectsManager implements InternalModule, NotificationPresentationEffectsManager {
    private Collection<NotificationPresentationEffect> mEffects = new ArrayList();

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onCreate(ModuleRegistry moduleRegistry) {
        RegistryLifecycleListener.CC.$default$onCreate(this, moduleRegistry);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(NotificationPresentationEffectsManager.class);
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationPresentationEffectsManager
    public void addEffect(NotificationPresentationEffect notificationPresentationEffect) {
        removeEffect(notificationPresentationEffect);
        this.mEffects.add(notificationPresentationEffect);
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationPresentationEffectsManager
    public void removeEffect(NotificationPresentationEffect notificationPresentationEffect) {
        this.mEffects.remove(notificationPresentationEffect);
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationPresentationEffect
    public boolean onNotificationPresented(String str, int i, Notification notification) {
        while (true) {
            boolean z = false;
            for (NotificationPresentationEffect notificationPresentationEffect : this.mEffects) {
                try {
                } catch (Exception e) {
                    Log.w("expo-notifications", String.format("Notification presentation effector %s threw an exception for notification (%s, %d): %s", notificationPresentationEffect.getClass(), str, Integer.valueOf(i), e.getMessage()));
                    e.printStackTrace();
                }
                if (notificationPresentationEffect.onNotificationPresented(str, i, notification) || z) {
                    z = true;
                }
            }
            return z;
        }
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationPresentationEffect
    public boolean onNotificationPresentationFailed(String str, int i, Notification notification) {
        while (true) {
            boolean z = false;
            for (NotificationPresentationEffect notificationPresentationEffect : this.mEffects) {
                try {
                } catch (Exception e) {
                    Log.w("expo-notifications", String.format("Notification presentation effector %s threw an exception for notification (%s, %d): %s", notificationPresentationEffect.getClass(), str, Integer.valueOf(i), e.getMessage()));
                    e.printStackTrace();
                }
                if (notificationPresentationEffect.onNotificationPresentationFailed(str, i, notification) || z) {
                    z = true;
                }
            }
            return z;
        }
    }
}

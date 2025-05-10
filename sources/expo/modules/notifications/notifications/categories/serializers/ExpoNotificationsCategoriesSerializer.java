package expo.modules.notifications.notifications.categories.serializers;

import android.os.Bundle;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.notifications.notifications.model.NotificationAction;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.notifications.model.TextInputNotificationAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ExpoNotificationsCategoriesSerializer implements NotificationsCategoriesSerializer, InternalModule {
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
        return Collections.singletonList(NotificationsCategoriesSerializer.class);
    }

    @Override // expo.modules.notifications.notifications.categories.serializers.NotificationsCategoriesSerializer
    public Bundle toBundle(NotificationCategory notificationCategory) {
        if (notificationCategory == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("identifier", getIdentifier(notificationCategory));
        bundle.putParcelableArrayList("actions", toBundleList(notificationCategory.getActions()));
        bundle.putBundle("options", new Bundle());
        return bundle;
    }

    protected String getIdentifier(NotificationCategory notificationCategory) {
        return notificationCategory.getIdentifier();
    }

    private ArrayList<Bundle> toBundleList(List<NotificationAction> list) {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        Iterator<NotificationAction> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(toBundle(it.next()));
        }
        return arrayList;
    }

    private Bundle toBundle(NotificationAction notificationAction) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("opensAppToForeground", notificationAction.opensAppToForeground());
        Bundle bundle2 = new Bundle();
        bundle2.putString("identifier", notificationAction.getIdentifier());
        bundle2.putString("buttonTitle", notificationAction.getTitle());
        bundle2.putBundle("options", bundle);
        if (notificationAction instanceof TextInputNotificationAction) {
            Bundle bundle3 = new Bundle();
            bundle3.putString(ReactTextInputShadowNode.PROP_PLACEHOLDER, ((TextInputNotificationAction) notificationAction).getPlaceholder());
            bundle2.putBundle("textInput", bundle3);
        }
        return bundle2;
    }
}

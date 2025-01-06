package expo.modules.notifications.notifications.channels;

import android.content.Context;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class AbstractNotificationsChannelsProvider implements NotificationsChannelsProvider, InternalModule {
    private NotificationsChannelGroupManager mChannelGroupManager;
    private NotificationsChannelGroupSerializer mChannelGroupSerializer;
    private NotificationsChannelManager mChannelManager;
    private NotificationsChannelSerializer mChannelSerializer;
    protected final Context mContext;
    private ModuleRegistry mModuleRegistry;

    protected abstract NotificationsChannelGroupManager createChannelGroupManager();

    protected abstract NotificationsChannelGroupSerializer createChannelGroupSerializer();

    protected abstract NotificationsChannelManager createChannelManager();

    protected abstract NotificationsChannelSerializer createChannelSerializer();

    public final ModuleRegistry getModuleRegistry() {
        return this.mModuleRegistry;
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        this.mModuleRegistry = moduleRegistry;
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    public AbstractNotificationsChannelsProvider(Context context) {
        this.mContext = context;
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(NotificationsChannelsProvider.class);
    }

    @Override // expo.modules.notifications.notifications.channels.NotificationsChannelsProvider
    public final NotificationsChannelManager getChannelManager() {
        if (this.mChannelManager == null) {
            this.mChannelManager = createChannelManager();
        }
        return this.mChannelManager;
    }

    @Override // expo.modules.notifications.notifications.channels.NotificationsChannelsProvider
    public final NotificationsChannelGroupManager getGroupManager() {
        if (this.mChannelGroupManager == null) {
            this.mChannelGroupManager = createChannelGroupManager();
        }
        return this.mChannelGroupManager;
    }

    @Override // expo.modules.notifications.notifications.channels.NotificationsChannelsProvider
    public final NotificationsChannelSerializer getChannelSerializer() {
        if (this.mChannelSerializer == null) {
            this.mChannelSerializer = createChannelSerializer();
        }
        return this.mChannelSerializer;
    }

    @Override // expo.modules.notifications.notifications.channels.NotificationsChannelsProvider
    public final NotificationsChannelGroupSerializer getGroupSerializer() {
        if (this.mChannelGroupSerializer == null) {
            this.mChannelGroupSerializer = createChannelGroupSerializer();
        }
        return this.mChannelGroupSerializer;
    }
}

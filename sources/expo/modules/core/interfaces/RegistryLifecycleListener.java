package expo.modules.core.interfaces;

import expo.modules.core.ModuleRegistry;

/* loaded from: classes2.dex */
public interface RegistryLifecycleListener {

    /* renamed from: expo.modules.core.interfaces.RegistryLifecycleListener$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onCreate(RegistryLifecycleListener _this, ModuleRegistry moduleRegistry) {
        }

        public static void $default$onDestroy(RegistryLifecycleListener _this) {
        }
    }

    void onCreate(ModuleRegistry moduleRegistry);

    void onDestroy();
}

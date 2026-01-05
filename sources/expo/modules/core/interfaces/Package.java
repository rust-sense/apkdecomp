package expo.modules.core.interfaces;

import android.content.Context;
import java.util.List;

/* loaded from: classes2.dex */
public interface Package {
    List<? extends ApplicationLifecycleListener> createApplicationLifecycleListeners(Context context);

    List<? extends InternalModule> createInternalModules(Context context);

    List<? extends ReactActivityHandler> createReactActivityHandlers(Context context);

    List<? extends ReactActivityLifecycleListener> createReactActivityLifecycleListeners(Context context);

    List<? extends ReactNativeHostHandler> createReactNativeHostHandlers(Context context);

    List<? extends SingletonModule> createSingletonModules(Context context);

    /* renamed from: expo.modules.core.interfaces.Package$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}

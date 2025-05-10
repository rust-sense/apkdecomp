package expo.modules.updatesinterface;

import java.lang.ref.WeakReference;
import kotlin.Metadata;

/* compiled from: UpdatesControllerRegistry.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/updatesinterface/UpdatesControllerRegistry;", "", "()V", "controller", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/updatesinterface/UpdatesInterface;", "getController", "()Ljava/lang/ref/WeakReference;", "setController", "(Ljava/lang/ref/WeakReference;)V", "expo-updates-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesControllerRegistry {
    public static final UpdatesControllerRegistry INSTANCE = new UpdatesControllerRegistry();
    private static WeakReference<UpdatesInterface> controller;

    public final WeakReference<UpdatesInterface> getController() {
        return controller;
    }

    public final void setController(WeakReference<UpdatesInterface> weakReference) {
        controller = weakReference;
    }

    private UpdatesControllerRegistry() {
    }
}

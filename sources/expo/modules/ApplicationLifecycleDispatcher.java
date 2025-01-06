package expo.modules;

import android.app.Application;
import android.content.res.Configuration;
import expo.modules.core.interfaces.ApplicationLifecycleListener;
import expo.modules.core.interfaces.Package;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApplicationLifecycleDispatcher.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0007R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lexpo/modules/ApplicationLifecycleDispatcher;", "", "()V", "listeners", "", "Lexpo/modules/core/interfaces/ApplicationLifecycleListener;", "getCachedListeners", "application", "Landroid/app/Application;", "onApplicationCreate", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "expo_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ApplicationLifecycleDispatcher {
    public static final ApplicationLifecycleDispatcher INSTANCE = new ApplicationLifecycleDispatcher();
    private static List<? extends ApplicationLifecycleListener> listeners;

    private ApplicationLifecycleDispatcher() {
    }

    private final List<ApplicationLifecycleListener> getCachedListeners(Application application) {
        List list = listeners;
        if (list != null) {
            return list;
        }
        List<Package> packageList = ExpoModulesPackage.INSTANCE.getPackageList();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = packageList.iterator();
        while (it.hasNext()) {
            List<? extends ApplicationLifecycleListener> createApplicationLifecycleListeners = ((Package) it.next()).createApplicationLifecycleListeners(application);
            Intrinsics.checkNotNullExpressionValue(createApplicationLifecycleListeners, "createApplicationLifecycleListeners(...)");
            CollectionsKt.addAll(arrayList, createApplicationLifecycleListeners);
        }
        ArrayList arrayList2 = arrayList;
        listeners = arrayList2;
        return arrayList2;
    }

    @JvmStatic
    public static final void onApplicationCreate(Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        Iterator<T> it = INSTANCE.getCachedListeners(application).iterator();
        while (it.hasNext()) {
            ((ApplicationLifecycleListener) it.next()).onCreate(application);
        }
    }

    @JvmStatic
    public static final void onConfigurationChanged(Application application, Configuration newConfig) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        Iterator<T> it = INSTANCE.getCachedListeners(application).iterator();
        while (it.hasNext()) {
            ((ApplicationLifecycleListener) it.next()).onConfigurationChanged(newConfig);
        }
    }
}

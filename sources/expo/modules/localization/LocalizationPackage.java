package expo.modules.localization;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import expo.modules.core.interfaces.ApplicationLifecycleListener;
import expo.modules.core.interfaces.Package;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: LocalizationPackage.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"Lexpo/modules/localization/LocalizationPackage;", "Lexpo/modules/core/interfaces/Package;", "()V", "createApplicationLifecycleListeners", "", "Lexpo/modules/core/interfaces/ApplicationLifecycleListener;", "context", "Landroid/content/Context;", "expo-localization_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LocalizationPackage implements Package {
    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createInternalModules(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createReactActivityHandlers(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createReactActivityLifecycleListeners(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createReactNativeHostHandlers(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createSingletonModules(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public List<ApplicationLifecycleListener> createApplicationLifecycleListeners(Context context) {
        return CollectionsKt.listOf(new ApplicationLifecycleListener() { // from class: expo.modules.localization.LocalizationPackage$createApplicationLifecycleListeners$1
            @Override // expo.modules.core.interfaces.ApplicationLifecycleListener
            public /* synthetic */ void onCreate(Application application) {
                ApplicationLifecycleListener.CC.$default$onCreate(this, application);
            }

            @Override // expo.modules.core.interfaces.ApplicationLifecycleListener
            public void onConfigurationChanged(Configuration newConfig) {
                ApplicationLifecycleListener.CC.$default$onConfigurationChanged(this, newConfig);
                Notifier.INSTANCE.onConfigurationChanged();
            }
        });
    }
}

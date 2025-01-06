package expo.modules.updates;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import expo.modules.core.interfaces.ApplicationLifecycleListener;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactActivityHandler;
import expo.modules.core.interfaces.ReactNativeHostHandler;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesPackage.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\u0006\u0010\f\u001a\u00020\tH\u0016J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lexpo/modules/updates/UpdatesPackage;", "Lexpo/modules/core/interfaces/Package;", "()V", "useNativeDebug", "", "createApplicationLifecycleListeners", "", "Lexpo/modules/core/interfaces/ApplicationLifecycleListener;", "context", "Landroid/content/Context;", "createReactActivityHandlers", "Lexpo/modules/core/interfaces/ReactActivityHandler;", "activityContext", "createReactNativeHostHandlers", "Lexpo/modules/core/interfaces/ReactNativeHostHandler;", "isRunningAndroidTest", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesPackage implements Package {
    private static final String TAG = "UpdatesPackage";
    private final boolean useNativeDebug;

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createInternalModules(Context context) {
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
    public /* synthetic */ List createSingletonModules(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public List<ReactNativeHostHandler> createReactNativeHostHandlers(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CollectionsKt.listOf(new ReactNativeHostHandler() { // from class: expo.modules.updates.UpdatesPackage$createReactNativeHostHandlers$handler$1
            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public /* synthetic */ Object getDevSupportManagerFactory() {
                return ReactNativeHostHandler.CC.$default$getDevSupportManagerFactory(this);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public /* synthetic */ JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
                return ReactNativeHostHandler.CC.$default$getJavaScriptExecutorFactory(this);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public /* synthetic */ Boolean getUseDeveloperSupport() {
                return ReactNativeHostHandler.CC.$default$getUseDeveloperSupport(this);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public String getJSBundleFile(boolean useDeveloperSupport) {
                if (UpdatesController.Companion.getInstance().isActiveController()) {
                    return UpdatesController.Companion.getInstance().getLaunchAssetFile();
                }
                return null;
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public String getBundleAssetName(boolean useDeveloperSupport) {
                if (UpdatesController.Companion.getInstance().isActiveController()) {
                    return UpdatesController.Companion.getInstance().getBundleAssetName();
                }
                return null;
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public void onWillCreateReactInstance(boolean useDeveloperSupport) {
                UpdatesController.Companion.initialize(context);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public void onDidCreateDevSupportManager(DevSupportManager devSupportManager) {
                Intrinsics.checkNotNullParameter(devSupportManager, "devSupportManager");
                UpdatesController.Companion.getInstance().onDidCreateDevSupportManager(devSupportManager);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public void onDidCreateReactInstance(boolean useDeveloperSupport, ReactContext reactContext) {
                Intrinsics.checkNotNullParameter(reactContext, "reactContext");
                UpdatesController.Companion.getInstance().onDidCreateReactInstance(reactContext);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public void onReactInstanceException(boolean useDeveloperSupport, Exception exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                UpdatesController.Companion.getInstance().onReactInstanceException(exception);
            }
        });
    }

    @Override // expo.modules.core.interfaces.Package
    public List<ReactActivityHandler> createReactActivityHandlers(Context activityContext) {
        Intrinsics.checkNotNullParameter(activityContext, "activityContext");
        return CollectionsKt.listOf(new UpdatesPackage$createReactActivityHandlers$handler$1());
    }

    @Override // expo.modules.core.interfaces.Package
    public List<ApplicationLifecycleListener> createApplicationLifecycleListeners(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CollectionsKt.listOf(new ApplicationLifecycleListener() { // from class: expo.modules.updates.UpdatesPackage$createApplicationLifecycleListeners$handler$1
            @Override // expo.modules.core.interfaces.ApplicationLifecycleListener
            public /* synthetic */ void onConfigurationChanged(Configuration configuration) {
                ApplicationLifecycleListener.CC.$default$onConfigurationChanged(this, configuration);
            }

            @Override // expo.modules.core.interfaces.ApplicationLifecycleListener
            public void onCreate(Application application) {
                boolean isRunningAndroidTest;
                Intrinsics.checkNotNullParameter(application, "application");
                ApplicationLifecycleListener.CC.$default$onCreate(this, application);
                isRunningAndroidTest = UpdatesPackage.this.isRunningAndroidTest();
                if (isRunningAndroidTest) {
                    UpdatesController.Companion.initialize(context);
                    UpdatesController.Companion.getInstance().getLaunchAssetFile();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isRunningAndroidTest() {
        try {
            Class.forName("androidx.test.espresso.Espresso");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}

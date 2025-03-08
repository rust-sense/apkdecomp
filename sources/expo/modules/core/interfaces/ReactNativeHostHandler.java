package expo.modules.core.interfaces;

import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.devsupport.interfaces.DevSupportManager;

/* loaded from: classes2.dex */
public interface ReactNativeHostHandler {

    /* renamed from: expo.modules.core.interfaces.ReactNativeHostHandler$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static String $default$getBundleAssetName(ReactNativeHostHandler _this, boolean z) {
            return null;
        }

        public static Object $default$getDevSupportManagerFactory(ReactNativeHostHandler _this) {
            return null;
        }

        public static String $default$getJSBundleFile(ReactNativeHostHandler _this, boolean z) {
            return null;
        }

        public static JavaScriptExecutorFactory $default$getJavaScriptExecutorFactory(ReactNativeHostHandler _this) {
            return null;
        }

        public static Boolean $default$getUseDeveloperSupport(ReactNativeHostHandler _this) {
            return null;
        }

        public static void $default$onDidCreateDevSupportManager(ReactNativeHostHandler _this, DevSupportManager devSupportManager) {
        }

        public static void $default$onDidCreateReactInstance(ReactNativeHostHandler _this, boolean z, ReactContext reactContext) {
        }

        public static void $default$onReactInstanceException(ReactNativeHostHandler _this, boolean z, Exception exc) {
        }

        public static void $default$onWillCreateReactInstance(ReactNativeHostHandler _this, boolean z) {
        }
    }

    String getBundleAssetName(boolean z);

    Object getDevSupportManagerFactory();

    String getJSBundleFile(boolean z);

    JavaScriptExecutorFactory getJavaScriptExecutorFactory();

    Boolean getUseDeveloperSupport();

    void onDidCreateDevSupportManager(DevSupportManager devSupportManager);

    void onDidCreateReactInstance(boolean z, ReactContext reactContext);

    void onReactInstanceException(boolean z, Exception exc);

    void onWillCreateReactInstance(boolean z);
}

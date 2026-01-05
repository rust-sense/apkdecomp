package expo.modules.adapters.react;

import android.util.SparseArray;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.core.ModuleRegistry;
import expo.modules.kotlin.CoreLoggerKt;
import expo.modules.kotlin.ExpoModulesHelper;
import expo.modules.kotlin.KPromiseWrapper;
import expo.modules.kotlin.KotlinInteropModuleRegistry;
import expo.modules.kotlin.ModulesProvider;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class NativeModulesProxy extends ReactContextBaseJavaModule {
    private static final String EXPORTED_METHODS_KEY = "exportedMethods";
    private static final String METHOD_INFO_KEY = "key";
    private static final String METHOD_INFO_NAME = "name";
    private static final String MODULES_CONSTANTS_KEY = "modulesConstants";
    private static final String NAME = "NativeUnimoduleProxy";
    private static final String UNDEFINED_METHOD_ERROR = "E_UNDEFINED_METHOD";
    private static final String UNEXPECTED_ERROR = "E_UNEXPECTED_ERROR";
    private static final String VIEW_MANAGERS_METADATA_KEY = "viewManagersMetadata";
    private Map<String, Object> cachedConstants;
    private Map<String, Map<String, Integer>> mExportedMethodsKeys;
    private Map<String, SparseArray<String>> mExportedMethodsReverseKeys;
    private KotlinInteropModuleRegistry mKotlinInteropModuleRegistry;
    private ModuleRegistry mModuleRegistry;

    public KotlinInteropModuleRegistry getKotlinInteropModuleRegistry() {
        return this.mKotlinInteropModuleRegistry;
    }

    ModuleRegistry getModuleRegistry() {
        return this.mModuleRegistry;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public NativeModulesProxy(ReactApplicationContext reactApplicationContext, ModuleRegistry moduleRegistry) {
        super(reactApplicationContext);
        this.mModuleRegistry = moduleRegistry;
        this.mExportedMethodsKeys = new HashMap();
        this.mExportedMethodsReverseKeys = new HashMap();
        this.mKotlinInteropModuleRegistry = new KotlinInteropModuleRegistry((ModulesProvider) Objects.requireNonNull(ExpoModulesHelper.INSTANCE.getModulesProvider()), moduleRegistry, new WeakReference(reactApplicationContext));
    }

    public NativeModulesProxy(ReactApplicationContext reactApplicationContext, ModuleRegistry moduleRegistry, ModulesProvider modulesProvider) {
        super(reactApplicationContext);
        this.mModuleRegistry = moduleRegistry;
        this.mExportedMethodsKeys = new HashMap();
        this.mExportedMethodsReverseKeys = new HashMap();
        this.mKotlinInteropModuleRegistry = new KotlinInteropModuleRegistry((ModulesProvider) Objects.requireNonNull(modulesProvider), moduleRegistry, new WeakReference(reactApplicationContext));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        Map<String, Object> map = this.cachedConstants;
        if (map != null) {
            return map;
        }
        this.mModuleRegistry.ensureIsInitialized();
        KotlinInteropModuleRegistry kotlinInteropModuleRegistry = getKotlinInteropModuleRegistry();
        kotlinInteropModuleRegistry.installJSIInterop();
        kotlinInteropModuleRegistry.emitOnCreate();
        HashMap hashMap = new HashMap(3);
        hashMap.put(MODULES_CONSTANTS_KEY, new HashMap());
        hashMap.put(EXPORTED_METHODS_KEY, new HashMap());
        hashMap.put(VIEW_MANAGERS_METADATA_KEY, this.mKotlinInteropModuleRegistry.viewManagersMetadata());
        CoreLoggerKt.getLogger().info("✅ Constants were exported");
        this.cachedConstants = hashMap;
        return hashMap;
    }

    @ReactMethod
    public void callMethod(String str, int i, ReadableArray readableArray, Promise promise) {
        callMethod(str, this.mExportedMethodsReverseKeys.get(str).get(i), readableArray, promise);
    }

    public void callMethod(String str, String str2, ReadableArray readableArray, Promise promise) {
        if (this.mKotlinInteropModuleRegistry.hasModule(str)) {
            this.mKotlinInteropModuleRegistry.callMethod(str, str2, readableArray, new KPromiseWrapper(promise));
            return;
        }
        promise.reject(UNDEFINED_METHOD_ERROR, "Method " + str2 + " of Java module " + str + " is undefined.");
    }

    private void assignExportedMethodsKeys(String str, List<Map<String, Object>> list) {
        if (this.mExportedMethodsKeys.get(str) == null) {
            this.mExportedMethodsKeys.put(str, new HashMap());
        }
        if (this.mExportedMethodsReverseKeys.get(str) == null) {
            this.mExportedMethodsReverseKeys.put(str, new SparseArray<>());
        }
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            if (map.get("name") == null || !(map.get("name") instanceof String)) {
                throw new RuntimeException("No method name in MethodInfo - " + map.toString());
            }
            String str2 = (String) map.get("name");
            Integer num = this.mExportedMethodsKeys.get(str).get(str2);
            if (num == null) {
                int size = this.mExportedMethodsKeys.get(str).values().size();
                map.put(METHOD_INFO_KEY, Integer.valueOf(size));
                this.mExportedMethodsKeys.get(str).put(str2, Integer.valueOf(size));
                this.mExportedMethodsReverseKeys.get(str).put(size, str2);
            } else {
                map.put(METHOD_INFO_KEY, Integer.valueOf(num.intValue()));
            }
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        super.invalidate();
        this.mModuleRegistry.onDestroy();
        this.mKotlinInteropModuleRegistry.onDestroy();
    }

    ReactApplicationContext getReactContext() {
        return getReactApplicationContext();
    }
}

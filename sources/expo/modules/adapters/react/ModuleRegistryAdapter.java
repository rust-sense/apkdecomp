package expo.modules.adapters.react;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.Consumer;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.Package;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.CoreLoggerKt;
import expo.modules.kotlin.ExpoBridgeModule;
import expo.modules.kotlin.KotlinInteropModuleRegistry;
import expo.modules.kotlin.ModulesProvider;
import expo.modules.kotlin.views.ViewWrapperDelegateHolder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class ModuleRegistryAdapter implements ReactPackage {
    protected ReactModuleRegistryProvider mModuleRegistryProvider;
    protected ModulesProvider mModulesProvider;
    private NativeModulesProxy mModulesProxy;
    protected ReactAdapterPackage mReactAdapterPackage = new ReactAdapterPackage();
    private List<ViewWrapperDelegateHolder> mWrapperDelegateHolders = null;
    private FabricComponentsRegistry mFabricComponentsRegistry = null;

    @Override // com.facebook.react.ReactPackage
    public /* synthetic */ NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        return ReactPackage.CC.$default$getModule(this, str, reactApplicationContext);
    }

    private void setModulesProxy(NativeModulesProxy nativeModulesProxy) {
        this.mModulesProxy = nativeModulesProxy;
        if (nativeModulesProxy != null) {
            nativeModulesProxy.getKotlinInteropModuleRegistry().setLegacyModulesProxy(this.mModulesProxy);
        }
    }

    public ModuleRegistryAdapter(List<Package> list) {
        this.mModuleRegistryProvider = new ReactModuleRegistryProvider(list, null);
    }

    public ModuleRegistryAdapter(ReactModuleRegistryProvider reactModuleRegistryProvider) {
        this.mModuleRegistryProvider = reactModuleRegistryProvider;
    }

    public ModuleRegistryAdapter(ReactModuleRegistryProvider reactModuleRegistryProvider, ModulesProvider modulesProvider) {
        this.mModuleRegistryProvider = reactModuleRegistryProvider;
        this.mModulesProvider = modulesProvider;
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        NativeModulesProxy orCreateNativeModulesProxy = getOrCreateNativeModulesProxy(reactApplicationContext, null);
        ModuleRegistry moduleRegistry = orCreateNativeModulesProxy.getModuleRegistry();
        Iterator<InternalModule> it = this.mReactAdapterPackage.createInternalModules(reactApplicationContext).iterator();
        while (it.hasNext()) {
            moduleRegistry.registerInternalModule(it.next());
        }
        List<NativeModule> nativeModulesFromModuleRegistry = getNativeModulesFromModuleRegistry(reactApplicationContext, moduleRegistry, null);
        if (this.mWrapperDelegateHolders != null) {
            orCreateNativeModulesProxy.getKotlinInteropModuleRegistry().updateModuleHoldersInViewManagers(this.mWrapperDelegateHolders);
        }
        return nativeModulesFromModuleRegistry;
    }

    protected List<NativeModule> getNativeModulesFromModuleRegistry(ReactApplicationContext reactApplicationContext, ModuleRegistry moduleRegistry, Consumer<AppContext> consumer) {
        ArrayList arrayList = new ArrayList(2);
        NativeModulesProxy orCreateNativeModulesProxy = getOrCreateNativeModulesProxy(reactApplicationContext, moduleRegistry);
        if (consumer != null) {
            consumer.apply(orCreateNativeModulesProxy.getKotlinInteropModuleRegistry().getAppContext());
        }
        arrayList.add(orCreateNativeModulesProxy);
        arrayList.add(new ModuleRegistryReadyNotifier(moduleRegistry));
        Iterator<ReactPackage> it = ((ReactPackagesProvider) moduleRegistry.getModule(ReactPackagesProvider.class)).getReactPackages().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().createNativeModules(reactApplicationContext));
        }
        arrayList.add(new ExpoBridgeModule(reactApplicationContext, new WeakReference(orCreateNativeModulesProxy)));
        return arrayList;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList(this.mModuleRegistryProvider.getReactViewManagers(reactApplicationContext));
        KotlinInteropModuleRegistry kotlinInteropModuleRegistry = ((NativeModulesProxy) Objects.requireNonNull(getOrCreateNativeModulesProxy(reactApplicationContext, null))).getKotlinInteropModuleRegistry();
        List<ViewManager<?, ?>> exportViewManagers = kotlinInteropModuleRegistry.exportViewManagers();
        this.mWrapperDelegateHolders = kotlinInteropModuleRegistry.extractViewManagersDelegateHolders(exportViewManagers);
        arrayList.addAll(exportViewManagers);
        return arrayList;
    }

    private synchronized NativeModulesProxy getOrCreateNativeModulesProxy(ReactApplicationContext reactApplicationContext, ModuleRegistry moduleRegistry) {
        NativeModulesProxy nativeModulesProxy = this.mModulesProxy;
        if (nativeModulesProxy != null && nativeModulesProxy.getReactContext() != reactApplicationContext) {
            setModulesProxy(null);
        }
        if (this.mModulesProxy == null) {
            ModuleRegistry moduleRegistry2 = moduleRegistry != null ? moduleRegistry : this.mModuleRegistryProvider.get(reactApplicationContext);
            if (this.mModulesProvider != null) {
                setModulesProxy(new NativeModulesProxy(reactApplicationContext, moduleRegistry2, this.mModulesProvider));
            } else {
                setModulesProxy(new NativeModulesProxy(reactApplicationContext, moduleRegistry2));
            }
        }
        if (moduleRegistry != null && moduleRegistry != this.mModulesProxy.getModuleRegistry()) {
            CoreLoggerKt.getLogger().error("❌ NativeModuleProxy was configured with a different instance of the modules registry.", null);
        }
        return this.mModulesProxy;
    }
}

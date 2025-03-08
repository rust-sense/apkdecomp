package com.facebook.react;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Deprecated(forRemoval = true, since = "CompositeReactPackage is deprecated and will be deleted, use ReactPackage instead")
/* loaded from: classes.dex */
public class CompositeReactPackage implements ViewManagerOnDemandReactPackage, ReactPackage {
    private final List<ReactPackage> mChildReactPackages;

    @Override // com.facebook.react.ReactPackage
    public /* synthetic */ NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        return ReactPackage.CC.$default$getModule(this, str, reactApplicationContext);
    }

    public CompositeReactPackage(ReactPackage reactPackage, ReactPackage reactPackage2, ReactPackage... reactPackageArr) {
        ArrayList arrayList = new ArrayList();
        this.mChildReactPackages = arrayList;
        arrayList.add(reactPackage);
        arrayList.add(reactPackage2);
        Collections.addAll(arrayList, reactPackageArr);
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        HashMap hashMap = new HashMap();
        for (ReactPackage reactPackage : this.mChildReactPackages) {
            if (reactPackage instanceof BaseReactPackage) {
                BaseReactPackage baseReactPackage = (BaseReactPackage) reactPackage;
                for (String str : baseReactPackage.getReactModuleInfoProvider().getReactModuleInfos().keySet()) {
                    hashMap.put(str, baseReactPackage.getModule(str, reactApplicationContext));
                }
            } else {
                for (NativeModule nativeModule : reactPackage.createNativeModules(reactApplicationContext)) {
                    hashMap.put(nativeModule.getName(), nativeModule);
                }
            }
        }
        return new ArrayList(hashMap.values());
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        HashMap hashMap = new HashMap();
        Iterator<ReactPackage> it = this.mChildReactPackages.iterator();
        while (it.hasNext()) {
            for (ViewManager viewManager : it.next().createViewManagers(reactApplicationContext)) {
                hashMap.put(viewManager.getName(), viewManager);
            }
        }
        return new ArrayList(hashMap.values());
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public Collection<String> getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        Collection<String> viewManagerNames;
        HashSet hashSet = new HashSet();
        for (ReactPackage reactPackage : this.mChildReactPackages) {
            if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (viewManagerNames = ((ViewManagerOnDemandReactPackage) reactPackage).getViewManagerNames(reactApplicationContext)) != null) {
                hashSet.addAll(viewManagerNames);
            }
        }
        return hashSet;
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str) {
        ViewManager createViewManager;
        List<ReactPackage> list = this.mChildReactPackages;
        ListIterator<ReactPackage> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            ReactPackage previous = listIterator.previous();
            if ((previous instanceof ViewManagerOnDemandReactPackage) && (createViewManager = ((ViewManagerOnDemandReactPackage) previous).createViewManager(reactApplicationContext, str)) != null) {
                return createViewManager;
            }
        }
        return null;
    }
}

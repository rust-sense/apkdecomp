package expo.modules.kotlin;

import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ThemedReactContext;
import expo.modules.adapters.react.NativeModulesProxy;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactExtensions.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"getUnimoduleProxy", "Lexpo/modules/adapters/react/NativeModulesProxy;", "Lcom/facebook/react/bridge/ReactContext;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactExtensionsKt {
    public static final NativeModulesProxy getUnimoduleProxy(ReactContext reactContext) {
        NativeModule nativeModule;
        Object obj;
        Intrinsics.checkNotNullParameter(reactContext, "<this>");
        if (!reactContext.isBridgeless()) {
            CatalystInstance catalystInstance = reactContext.getCatalystInstance();
            NativeModule nativeModule2 = catalystInstance != null ? catalystInstance.getNativeModule("NativeUnimoduleProxy") : null;
            if (nativeModule2 instanceof NativeModulesProxy) {
                return (NativeModulesProxy) nativeModule2;
            }
            return null;
        }
        Collection<NativeModule> nativeModules = ((ThemedReactContext) reactContext).getReactApplicationContext().getNativeModules();
        if (nativeModules != null) {
            Iterator<T> it = nativeModules.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((NativeModule) obj) instanceof NativeModulesProxy) {
                    break;
                }
            }
            nativeModule = (NativeModule) obj;
        } else {
            nativeModule = null;
        }
        if (nativeModule instanceof NativeModulesProxy) {
            return (NativeModulesProxy) nativeModule;
        }
        return null;
    }
}

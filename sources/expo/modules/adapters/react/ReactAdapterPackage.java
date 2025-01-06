package expo.modules.adapters.react;

import android.content.Context;
import com.facebook.react.bridge.ReactContext;
import expo.modules.adapters.react.permissions.PermissionsService;
import expo.modules.adapters.react.services.EventEmitterModule;
import expo.modules.adapters.react.services.FontManagerModule;
import expo.modules.adapters.react.services.RuntimeEnvironmentModule;
import expo.modules.adapters.react.services.UIManagerModuleWrapper;
import expo.modules.core.BasePackage;
import expo.modules.core.interfaces.InternalModule;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class ReactAdapterPackage extends BasePackage {
    @Override // expo.modules.core.BasePackage, expo.modules.core.interfaces.Package
    public List<InternalModule> createInternalModules(Context context) {
        ReactContext reactContext = (ReactContext) context;
        return Arrays.asList(new UIManagerModuleWrapper(reactContext), new EventEmitterModule(reactContext), new FontManagerModule(), new RuntimeEnvironmentModule(), new PermissionsService(reactContext));
    }
}

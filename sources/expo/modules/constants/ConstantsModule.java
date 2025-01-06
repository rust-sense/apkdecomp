package expo.modules.constants;

import androidx.tracing.Trace;
import expo.modules.interfaces.constants.ConstantsInterface;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConstantsModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/constants/ConstantsModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-constants_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ConstantsModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        ConstantsModule constantsModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (constantsModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(constantsModule);
            moduleDefinitionBuilder.Name("ExponentConstants");
            moduleDefinitionBuilder.Constants(new Function0<Map<String, ? extends Object>>() { // from class: expo.modules.constants.ConstantsModule$definition$1$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Map<String, ? extends Object> invoke() {
                    ConstantsInterface constants = ConstantsModule.this.getAppContext().getConstants();
                    Map<String, ? extends Object> constants2 = constants != null ? constants.getConstants() : null;
                    return constants2 == null ? MapsKt.emptyMap() : constants2;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("getWebViewUserAgentAsync", new AsyncFunctionComponent("getWebViewUserAgentAsync", new AnyType[0], new Function1<Object[], String>() { // from class: expo.modules.constants.ConstantsModule$definition$lambda$1$$inlined$AsyncFunction$1
                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return System.getProperty("http.agent");
                }
            }));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}

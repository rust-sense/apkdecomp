package expo.modules.kotlin.defaultmodules;

import androidx.tracing.Trace;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.adapters.react.NativeModulesProxy;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.PromiseKt;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: NativeModulesProxyModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/kotlin/defaultmodules/NativeModulesProxyModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NativeModulesProxyModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        NativeModulesProxyModule nativeModulesProxyModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (nativeModulesProxyModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(nativeModulesProxyModule);
            moduleDefinitionBuilder.Name(NativeModulesProxyModuleKt.NativeModulesProxyModuleName);
            moduleDefinitionBuilder.Constants(new Function0<Map<String, ? extends Object>>() { // from class: expo.modules.kotlin.defaultmodules.NativeModulesProxyModule$definition$1$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Map<String, ? extends Object> invoke() {
                    BaseJavaModule baseJavaModule;
                    WeakReference<NativeModulesProxy> legacyModulesProxyHolder$expo_modules_core_release = NativeModulesProxyModule.this.getAppContext().getLegacyModulesProxyHolder$expo_modules_core_release();
                    Map<String, Object> constants = (legacyModulesProxyHolder$expo_modules_core_release == null || (baseJavaModule = (NativeModulesProxy) legacyModulesProxyHolder$expo_modules_core_release.get()) == null) ? null : baseJavaModule.getConstants();
                    return constants == null ? MapsKt.emptyMap() : constants;
                }
            });
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("callMethod", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.kotlin.defaultmodules.NativeModulesProxyModule$definition$lambda$1$$inlined$AsyncFunctionWithPromise$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.kotlin.defaultmodules.NativeModulesProxyModule$definition$lambda$1$$inlined$AsyncFunctionWithPromise$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArray.class), false, new Function0<KType>() { // from class: expo.modules.kotlin.defaultmodules.NativeModulesProxyModule$definition$lambda$1$$inlined$AsyncFunctionWithPromise$3
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(ReadableArray.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.defaultmodules.NativeModulesProxyModule$definition$lambda$1$$inlined$AsyncFunctionWithPromise$4
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    NativeModulesProxy nativeModulesProxy;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    Object obj2 = objArr[1];
                    ReadableArray readableArray = (ReadableArray) objArr[2];
                    String str = (String) obj2;
                    String str2 = (String) obj;
                    com.facebook.react.bridge.Promise bridgePromise = PromiseKt.toBridgePromise(promise);
                    WeakReference<NativeModulesProxy> legacyModulesProxyHolder$expo_modules_core_release = NativeModulesProxyModule.this.getAppContext().getLegacyModulesProxyHolder$expo_modules_core_release();
                    if (legacyModulesProxyHolder$expo_modules_core_release == null || (nativeModulesProxy = legacyModulesProxyHolder$expo_modules_core_release.get()) == null) {
                        throw new UnexpectedException("The legacy modules proxy holder has been lost");
                    }
                    Intrinsics.checkNotNull(nativeModulesProxy);
                    nativeModulesProxy.callMethod(str2, str, readableArray, bridgePromise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("callMethod", asyncFunctionWithPromiseComponent);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2 = asyncFunctionWithPromiseComponent;
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}

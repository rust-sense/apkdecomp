package expo.modules.kotlin.defaultmodules;

import android.app.Activity;
import androidx.tracing.Trace;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.DisabledDevSupportManager;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.KModuleEventEmitterWrapperKt;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.uuidv5.InvalidNamespaceException;
import expo.modules.kotlin.uuidv5.Uuidv5Kt;
import expo.modules.kotlin.views.CallbacksDefinition;
import expo.modules.kotlin.views.ViewManagerDefinition;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KType;

/* compiled from: CoreModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/kotlin/defaultmodules/CoreModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CoreModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        CoreModule coreModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (coreModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(coreModule);
            moduleDefinitionBuilder.getSyncFunctions().put("uuidv4", new SyncFunctionComponent("uuidv4", new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.defaultmodules.CoreModule$definition$lambda$8$$inlined$FunctionWithoutArgs$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return UUID.randomUUID().toString();
                }
            }));
            moduleDefinitionBuilder.getSyncFunctions().put("uuidv5", new SyncFunctionComponent("uuidv5", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.kotlin.defaultmodules.CoreModule$definition$lambda$8$$inlined$Function$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.kotlin.defaultmodules.CoreModule$definition$lambda$8$$inlined$Function$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            }))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.defaultmodules.CoreModule$definition$lambda$8$$inlined$Function$3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Object obj = objArr[0];
                    String str = (String) objArr[1];
                    String str2 = (String) obj;
                    try {
                        UUID fromString = UUID.fromString(str);
                        Intrinsics.checkNotNull(fromString);
                        return Uuidv5Kt.uuidv5(fromString, str2).toString();
                    } catch (IllegalArgumentException unused) {
                        throw new InvalidNamespaceException(str);
                    }
                }
            }));
            moduleDefinitionBuilder.getSyncFunctions().put("getViewConfig", new SyncFunctionComponent("getViewConfig", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.kotlin.defaultmodules.CoreModule$definition$lambda$8$$inlined$Function$4
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            }))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.defaultmodules.CoreModule$definition$lambda$8$$inlined$Function$5
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] objArr) {
                    ViewManagerDefinition viewManagerDefinition;
                    String[] names;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    ModuleHolder<?> moduleHolder = CoreModule.this.getAppContext().getRegistry().getModuleHolder((String) objArr[0]);
                    LinkedHashMap linkedHashMap = null;
                    if (moduleHolder == null || (viewManagerDefinition = moduleHolder.getDefinition().getViewManagerDefinition()) == null) {
                        return null;
                    }
                    Set<String> keySet = viewManagerDefinition.getProps$expo_modules_core_release().keySet();
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(keySet, 10)), 16));
                    for (Object obj : keySet) {
                        linkedHashMap2.put(obj, true);
                    }
                    LinkedHashMap linkedHashMap3 = linkedHashMap2;
                    CallbacksDefinition callbacksDefinition = viewManagerDefinition.getCallbacksDefinition();
                    if (callbacksDefinition != null && (names = callbacksDefinition.getNames()) != null) {
                        linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(names.length), 16));
                        for (String str : names) {
                            Pair pair = TuplesKt.to(KModuleEventEmitterWrapperKt.normalizeEventName(str), MapsKt.mapOf(TuplesKt.to("registrationName", str)));
                            linkedHashMap.put(pair.getFirst(), pair.getSecond());
                        }
                    }
                    return MapsKt.mapOf(TuplesKt.to("validAttributes", linkedHashMap3), TuplesKt.to("directEventTypes", linkedHashMap));
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("reloadAppAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.defaultmodules.CoreModule$definition$lambda$8$$inlined$AsyncFunction$1
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        Activity currentActivity = CoreModule.this.getAppContext().getCurrentActivity();
                        ReactActivity reactActivity = currentActivity instanceof ReactActivity ? (ReactActivity) currentActivity : null;
                        if (reactActivity == null) {
                            return;
                        }
                        Field declaredField = ReactActivity.class.getDeclaredField("mDelegate");
                        declaredField.setAccessible(true);
                        Object obj = declaredField.get(reactActivity);
                        Method declaredMethod = obj.getClass().getDeclaredMethod("getReactDelegate", new Class[0]);
                        declaredMethod.setAccessible(true);
                        Object invoke = declaredMethod.invoke(obj, new Object[0]);
                        ReactDelegate reactDelegate = invoke instanceof ReactDelegate ? (ReactDelegate) invoke : null;
                        if (reactDelegate == null) {
                            return;
                        }
                        if (!ReactFeatureFlags.enableBridgelessArchitecture) {
                            ReactInstanceManager reactInstanceManager = reactDelegate.getReactInstanceManager();
                            if (reactInstanceManager.getDevSupportManager() instanceof DisabledDevSupportManager) {
                                UiThreadUtil.runOnUiThread(new CoreModule$definition$1$4$1(reactInstanceManager));
                                return;
                            }
                        }
                        reactDelegate.reload();
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.kotlin.defaultmodules.CoreModule$definition$lambda$8$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))};
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.kotlin.defaultmodules.CoreModule$definition$lambda$8$$inlined$AsyncFunction$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        Activity currentActivity = CoreModule.this.getAppContext().getCurrentActivity();
                        ReactActivity reactActivity = currentActivity instanceof ReactActivity ? (ReactActivity) currentActivity : null;
                        if (reactActivity != null) {
                            Field declaredField = ReactActivity.class.getDeclaredField("mDelegate");
                            declaredField.setAccessible(true);
                            Object obj = declaredField.get(reactActivity);
                            Method declaredMethod = obj.getClass().getDeclaredMethod("getReactDelegate", new Class[0]);
                            declaredMethod.setAccessible(true);
                            Object invoke = declaredMethod.invoke(obj, new Object[0]);
                            ReactDelegate reactDelegate = invoke instanceof ReactDelegate ? (ReactDelegate) invoke : null;
                            if (reactDelegate != null) {
                                if (!ReactFeatureFlags.enableBridgelessArchitecture) {
                                    ReactInstanceManager reactInstanceManager = reactDelegate.getReactInstanceManager();
                                    if (reactInstanceManager.getDevSupportManager() instanceof DisabledDevSupportManager) {
                                        UiThreadUtil.runOnUiThread(new CoreModule$definition$1$4$1(reactInstanceManager));
                                    }
                                }
                                reactDelegate.reload();
                            }
                        }
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent = new StringAsyncFunctionComponent("reloadAppAsync", anyTypeArr, function1);
                                } else {
                                    asyncFunctionComponent = new AsyncFunctionComponent("reloadAppAsync", anyTypeArr, function1);
                                }
                            } else {
                                asyncFunctionComponent = new FloatAsyncFunctionComponent("reloadAppAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new DoubleAsyncFunctionComponent("reloadAppAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new BoolAsyncFunctionComponent("reloadAppAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new IntAsyncFunctionComponent("reloadAppAsync", anyTypeArr, function1);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent;
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("reloadAppAsync", asyncFunctionWithPromiseComponent);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}

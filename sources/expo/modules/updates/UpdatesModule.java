package expo.modules.updates;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.updates.IUpdatesController;
import expo.modules.updates.UpdatesModule;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogEntry;
import expo.modules.updates.logging.UpdatesLogReader;
import expo.modules.updates.logging.UpdatesLogger;
import io.sentry.SentryEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: UpdatesModule.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lexpo/modules/updates/UpdatesModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", SentryEvent.JsonKeys.LOGGER, "Lexpo/modules/updates/logging/UpdatesLogger;", "getLogger", "()Lexpo/modules/updates/logging/UpdatesLogger;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesModule extends Module {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "UpdatesModule";

    /* JADX INFO: Access modifiers changed from: private */
    public final UpdatesLogger getLogger() {
        return new UpdatesLogger(getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        AsyncFunctionComponent asyncFunctionComponent3;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3;
        AsyncFunctionComponent asyncFunctionComponent4;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent4;
        AsyncFunctionComponent asyncFunctionComponent5;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent5;
        AsyncFunctionComponent asyncFunctionComponent6;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent6;
        AsyncFunctionComponent asyncFunctionComponent7;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent7;
        AsyncFunctionComponent asyncFunctionComponent8;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent8;
        UpdatesModule updatesModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (updatesModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(updatesModule);
            moduleDefinitionBuilder.Name("ExpoUpdates");
            moduleDefinitionBuilder.Events(UpdatesJsEventsKt.UPDATES_STATE_CHANGE_EVENT_NAME);
            moduleDefinitionBuilder.Constants(new Function0<Map<String, ? extends Object>>() { // from class: expo.modules.updates.UpdatesModule$definition$1$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Map<String, ? extends Object> invoke() {
                    Context context;
                    UUID id;
                    context = UpdatesModule.this.getContext();
                    new UpdatesLogger(context).info("UpdatesModule: getConstants called", UpdatesErrorCode.None);
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    IUpdatesController.UpdatesModuleConstants constantsForModule = UpdatesController.INSTANCE.getInstance().getConstantsForModule();
                    UpdateEntity launchedUpdate = constantsForModule.getLaunchedUpdate();
                    UpdateEntity embeddedUpdate = constantsForModule.getEmbeddedUpdate();
                    boolean equals = (launchedUpdate == null || (id = launchedUpdate.getId()) == null) ? false : id.equals(embeddedUpdate != null ? embeddedUpdate.getId() : null);
                    linkedHashMap.put("isEmergencyLaunch", Boolean.valueOf(constantsForModule.getEmergencyLaunchException() != null));
                    Exception emergencyLaunchException = constantsForModule.getEmergencyLaunchException();
                    linkedHashMap.put("emergencyLaunchReason", emergencyLaunchException != null ? emergencyLaunchException.getMessage() : null);
                    linkedHashMap.put("isEmbeddedLaunch", Boolean.valueOf(equals));
                    linkedHashMap.put("isEnabled", Boolean.valueOf(constantsForModule.isEnabled()));
                    linkedHashMap.put("isUsingEmbeddedAssets", Boolean.valueOf(constantsForModule.isUsingEmbeddedAssets()));
                    String runtimeVersion = constantsForModule.getRuntimeVersion();
                    if (runtimeVersion == null) {
                        runtimeVersion = "";
                    }
                    linkedHashMap.put(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY, runtimeVersion);
                    linkedHashMap.put("checkAutomatically", constantsForModule.getCheckOnLaunch().toJSString());
                    String str = constantsForModule.getRequestHeaders().get("expo-channel-name");
                    linkedHashMap.put("channel", str != null ? str : "");
                    linkedHashMap.put("shouldDeferToNativeForAPIMethodAvailabilityInDevelopment", Boolean.valueOf(constantsForModule.getShouldDeferToNativeForAPIMethodAvailabilityInDevelopment()));
                    if (launchedUpdate != null) {
                        linkedHashMap.put("updateId", launchedUpdate.getId().toString());
                        linkedHashMap.put("commitTime", Long.valueOf(launchedUpdate.getCommitTime().getTime()));
                        linkedHashMap.put("manifestString", launchedUpdate.getManifest().toString());
                    }
                    Map<AssetEntity, String> localAssetFiles = constantsForModule.getLocalAssetFiles();
                    if (localAssetFiles != null) {
                        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                        for (AssetEntity assetEntity : localAssetFiles.keySet()) {
                            if (assetEntity.getKey() != null) {
                                String key = assetEntity.getKey();
                                Intrinsics.checkNotNull(key);
                                String str2 = localAssetFiles.get(assetEntity);
                                Intrinsics.checkNotNull(str2);
                                linkedHashMap2.put(key, str2);
                            }
                        }
                        linkedHashMap.put("localAssets", linkedHashMap2);
                    }
                    return linkedHashMap;
                }
            });
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$OnCreate$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    UpdatesController.INSTANCE.bindAppContext$expo_updates_release(new WeakReference<>(UpdatesModule.this.getAppContext()), UpdatesModule.this.getAppContext().eventEmitter(UpdatesModule.this));
                }
            }));
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("startObserving", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$OnStartObserving$1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        UpdatesController.INSTANCE.setShouldEmitJsEvents$expo_updates_release(true);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$OnStartObserving$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }))};
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$OnStartObserving$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        UpdatesController.INSTANCE.setShouldEmitJsEvents$expo_updates_release(true);
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent = new StringAsyncFunctionComponent("startObserving", anyTypeArr, function1);
                                } else {
                                    asyncFunctionComponent = new AsyncFunctionComponent("startObserving", anyTypeArr, function1);
                                }
                            } else {
                                asyncFunctionComponent = new FloatAsyncFunctionComponent("startObserving", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new DoubleAsyncFunctionComponent("startObserving", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new BoolAsyncFunctionComponent("startObserving", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new IntAsyncFunctionComponent("startObserving", anyTypeArr, function1);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent;
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("startObserving", asyncFunctionWithPromiseComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("stopObserving", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$OnStopObserving$1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        UpdatesController.INSTANCE.setShouldEmitJsEvents$expo_updates_release(false);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$OnStopObserving$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }))};
                Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$OnStopObserving$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        UpdatesController.INSTANCE.setShouldEmitJsEvents$expo_updates_release(false);
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent2 = new StringAsyncFunctionComponent("stopObserving", anyTypeArr2, function12);
                                } else {
                                    asyncFunctionComponent2 = new AsyncFunctionComponent("stopObserving", anyTypeArr2, function12);
                                }
                            } else {
                                asyncFunctionComponent2 = new FloatAsyncFunctionComponent("stopObserving", anyTypeArr2, function12);
                            }
                        } else {
                            asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("stopObserving", anyTypeArr2, function12);
                        }
                    } else {
                        asyncFunctionComponent2 = new BoolAsyncFunctionComponent("stopObserving", anyTypeArr2, function12);
                    }
                } else {
                    asyncFunctionComponent2 = new IntAsyncFunctionComponent("stopObserving", anyTypeArr2, function12);
                }
                asyncFunctionWithPromiseComponent2 = asyncFunctionComponent2;
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("stopObserving", asyncFunctionWithPromiseComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("reload", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        UpdatesController.INSTANCE.getInstance().relaunchReactApplicationForModule(new UpdatesModule$definition$1$5$1(promise));
                    }
                });
            } else {
                AnyType[] anyTypeArr3 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Promise.class);
                    }
                }))};
                Function1<Object[], Unit> function13 = new Function1<Object[], Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$3
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        UpdatesController.INSTANCE.getInstance().relaunchReactApplicationForModule(new UpdatesModule$definition$1$5$1((Promise) objArr[0]));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent3 = new StringAsyncFunctionComponent("reload", anyTypeArr3, function13);
                                } else {
                                    asyncFunctionComponent3 = new AsyncFunctionComponent("reload", anyTypeArr3, function13);
                                }
                            } else {
                                asyncFunctionComponent3 = new FloatAsyncFunctionComponent("reload", anyTypeArr3, function13);
                            }
                        } else {
                            asyncFunctionComponent3 = new DoubleAsyncFunctionComponent("reload", anyTypeArr3, function13);
                        }
                    } else {
                        asyncFunctionComponent3 = new BoolAsyncFunctionComponent("reload", anyTypeArr3, function13);
                    }
                } else {
                    asyncFunctionComponent3 = new IntAsyncFunctionComponent("reload", anyTypeArr3, function13);
                }
                asyncFunctionWithPromiseComponent3 = asyncFunctionComponent3;
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("reload", asyncFunctionWithPromiseComponent3);
            ModuleDefinitionBuilder moduleDefinitionBuilder5 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent4 = new AsyncFunctionWithPromiseComponent("getNativeStateMachineContextAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$4
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        UpdatesController.INSTANCE.getInstance().getNativeStateMachineContext(new UpdatesModule$definition$1$6$1(promise));
                    }
                });
            } else {
                AnyType[] anyTypeArr4 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Promise.class);
                    }
                }))};
                Function1<Object[], Unit> function14 = new Function1<Object[], Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$6
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        UpdatesController.INSTANCE.getInstance().getNativeStateMachineContext(new UpdatesModule$definition$1$6$1((Promise) objArr[0]));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent4 = new StringAsyncFunctionComponent("getNativeStateMachineContextAsync", anyTypeArr4, function14);
                                } else {
                                    asyncFunctionComponent4 = new AsyncFunctionComponent("getNativeStateMachineContextAsync", anyTypeArr4, function14);
                                }
                            } else {
                                asyncFunctionComponent4 = new FloatAsyncFunctionComponent("getNativeStateMachineContextAsync", anyTypeArr4, function14);
                            }
                        } else {
                            asyncFunctionComponent4 = new DoubleAsyncFunctionComponent("getNativeStateMachineContextAsync", anyTypeArr4, function14);
                        }
                    } else {
                        asyncFunctionComponent4 = new BoolAsyncFunctionComponent("getNativeStateMachineContextAsync", anyTypeArr4, function14);
                    }
                } else {
                    asyncFunctionComponent4 = new IntAsyncFunctionComponent("getNativeStateMachineContextAsync", anyTypeArr4, function14);
                }
                asyncFunctionWithPromiseComponent4 = asyncFunctionComponent4;
            }
            moduleDefinitionBuilder5.getAsyncFunctions().put("getNativeStateMachineContextAsync", asyncFunctionWithPromiseComponent4);
            ModuleDefinitionBuilder moduleDefinitionBuilder6 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent5 = new AsyncFunctionWithPromiseComponent("checkForUpdateAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$7
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        UpdatesController.INSTANCE.getInstance().checkForUpdate(new UpdatesModule$definition$1$7$1(promise));
                    }
                });
            } else {
                AnyType[] anyTypeArr5 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$8
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Promise.class);
                    }
                }))};
                Function1<Object[], Unit> function15 = new Function1<Object[], Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$9
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        UpdatesController.INSTANCE.getInstance().checkForUpdate(new UpdatesModule$definition$1$7$1((Promise) objArr[0]));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent5 = new StringAsyncFunctionComponent("checkForUpdateAsync", anyTypeArr5, function15);
                                } else {
                                    asyncFunctionComponent5 = new AsyncFunctionComponent("checkForUpdateAsync", anyTypeArr5, function15);
                                }
                            } else {
                                asyncFunctionComponent5 = new FloatAsyncFunctionComponent("checkForUpdateAsync", anyTypeArr5, function15);
                            }
                        } else {
                            asyncFunctionComponent5 = new DoubleAsyncFunctionComponent("checkForUpdateAsync", anyTypeArr5, function15);
                        }
                    } else {
                        asyncFunctionComponent5 = new BoolAsyncFunctionComponent("checkForUpdateAsync", anyTypeArr5, function15);
                    }
                } else {
                    asyncFunctionComponent5 = new IntAsyncFunctionComponent("checkForUpdateAsync", anyTypeArr5, function15);
                }
                asyncFunctionWithPromiseComponent5 = asyncFunctionComponent5;
            }
            moduleDefinitionBuilder6.getAsyncFunctions().put("checkForUpdateAsync", asyncFunctionWithPromiseComponent5);
            ModuleDefinitionBuilder moduleDefinitionBuilder7 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent6 = new AsyncFunctionWithPromiseComponent("fetchUpdateAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$10
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        UpdatesController.INSTANCE.getInstance().fetchUpdate(new UpdatesModule$definition$1$8$1(promise));
                    }
                });
            } else {
                AnyType[] anyTypeArr6 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$11
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Promise.class);
                    }
                }))};
                Function1<Object[], Unit> function16 = new Function1<Object[], Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$12
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        UpdatesController.INSTANCE.getInstance().fetchUpdate(new UpdatesModule$definition$1$8$1((Promise) objArr[0]));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent6 = new StringAsyncFunctionComponent("fetchUpdateAsync", anyTypeArr6, function16);
                                } else {
                                    asyncFunctionComponent6 = new AsyncFunctionComponent("fetchUpdateAsync", anyTypeArr6, function16);
                                }
                            } else {
                                asyncFunctionComponent6 = new FloatAsyncFunctionComponent("fetchUpdateAsync", anyTypeArr6, function16);
                            }
                        } else {
                            asyncFunctionComponent6 = new DoubleAsyncFunctionComponent("fetchUpdateAsync", anyTypeArr6, function16);
                        }
                    } else {
                        asyncFunctionComponent6 = new BoolAsyncFunctionComponent("fetchUpdateAsync", anyTypeArr6, function16);
                    }
                } else {
                    asyncFunctionComponent6 = new IntAsyncFunctionComponent("fetchUpdateAsync", anyTypeArr6, function16);
                }
                asyncFunctionWithPromiseComponent6 = asyncFunctionComponent6;
            }
            moduleDefinitionBuilder7.getAsyncFunctions().put("fetchUpdateAsync", asyncFunctionWithPromiseComponent6);
            ModuleDefinitionBuilder moduleDefinitionBuilder8 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent7 = new AsyncFunctionWithPromiseComponent("getExtraParamsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$13
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        UpdatesLogger logger;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        logger = UpdatesModule.this.getLogger();
                        UpdatesLogger.debug$default(logger, "Called getExtraParamsAsync", null, 2, null);
                        UpdatesController.INSTANCE.getInstance().getExtraParams(new UpdatesModule$definition$1$9$1(promise));
                    }
                });
            } else {
                AnyType[] anyTypeArr7 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$14
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Promise.class);
                    }
                }))};
                Function1<Object[], Unit> function17 = new Function1<Object[], Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$15
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        UpdatesLogger logger;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        Promise promise = (Promise) objArr[0];
                        logger = UpdatesModule.this.getLogger();
                        UpdatesLogger.debug$default(logger, "Called getExtraParamsAsync", null, 2, null);
                        UpdatesController.INSTANCE.getInstance().getExtraParams(new UpdatesModule$definition$1$9$1(promise));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent7 = new StringAsyncFunctionComponent("getExtraParamsAsync", anyTypeArr7, function17);
                                } else {
                                    asyncFunctionComponent7 = new AsyncFunctionComponent("getExtraParamsAsync", anyTypeArr7, function17);
                                }
                            } else {
                                asyncFunctionComponent7 = new FloatAsyncFunctionComponent("getExtraParamsAsync", anyTypeArr7, function17);
                            }
                        } else {
                            asyncFunctionComponent7 = new DoubleAsyncFunctionComponent("getExtraParamsAsync", anyTypeArr7, function17);
                        }
                    } else {
                        asyncFunctionComponent7 = new BoolAsyncFunctionComponent("getExtraParamsAsync", anyTypeArr7, function17);
                    }
                } else {
                    asyncFunctionComponent7 = new IntAsyncFunctionComponent("getExtraParamsAsync", anyTypeArr7, function17);
                }
                asyncFunctionWithPromiseComponent7 = asyncFunctionComponent7;
            }
            moduleDefinitionBuilder8.getAsyncFunctions().put("getExtraParamsAsync", asyncFunctionWithPromiseComponent7);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent9 = new AsyncFunctionWithPromiseComponent("setExtraParamAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunctionWithPromise$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            })), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunctionWithPromise$2
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(String.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunctionWithPromise$3
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    UpdatesLogger logger;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = objArr[0];
                    String str = (String) objArr[1];
                    String str2 = (String) obj;
                    logger = UpdatesModule.this.getLogger();
                    UpdatesLogger.debug$default(logger, "Called setExtraParamAsync with key = " + str2 + ", value = " + str, null, 2, null);
                    UpdatesController.INSTANCE.getInstance().setExtraParam(str2, str, new IUpdatesController.ModuleCallback<Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$1$10$1
                        @Override // expo.modules.updates.IUpdatesController.ModuleCallback
                        public void onSuccess(Unit result) {
                            Intrinsics.checkNotNullParameter(result, "result");
                            Promise.this.resolve((Object) null);
                        }

                        @Override // expo.modules.updates.IUpdatesController.ModuleCallback
                        public void onFailure(CodedException exception) {
                            Intrinsics.checkNotNullParameter(exception, "exception");
                            Promise.this.reject(exception);
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("setExtraParamAsync", asyncFunctionWithPromiseComponent9);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent10 = asyncFunctionWithPromiseComponent9;
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent11 = new AsyncFunctionWithPromiseComponent("readLogEntriesAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Long.class), false, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunctionWithPromise$4
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(Long.TYPE);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunctionWithPromise$5
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    final long longValue = ((Number) objArr[0]).longValue();
                    final UpdatesModule updatesModule2 = UpdatesModule.this;
                    AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.UpdatesModule$definition$1$11$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Context context;
                            Promise promise2 = Promise.this;
                            UpdatesModule.Companion companion = UpdatesModule.INSTANCE;
                            context = updatesModule2.getContext();
                            promise2.resolve(companion.readLogEntries$expo_updates_release(context, longValue));
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("readLogEntriesAsync", asyncFunctionWithPromiseComponent11);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent12 = asyncFunctionWithPromiseComponent11;
            ModuleDefinitionBuilder moduleDefinitionBuilder9 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent8 = new AsyncFunctionWithPromiseComponent("clearLogEntriesAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$16
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        AsyncTask.execute(new UpdatesModule$definition$1$12$1(UpdatesModule.this, promise));
                    }
                });
            } else {
                AnyType[] anyTypeArr8 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$17
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Promise.class);
                    }
                }))};
                Function1<Object[], Unit> function18 = new Function1<Object[], Unit>() { // from class: expo.modules.updates.UpdatesModule$definition$lambda$11$$inlined$AsyncFunction$18
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        AsyncTask.execute(new UpdatesModule$definition$1$12$1(UpdatesModule.this, (Promise) objArr[0]));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent8 = new StringAsyncFunctionComponent("clearLogEntriesAsync", anyTypeArr8, function18);
                                } else {
                                    asyncFunctionComponent8 = new AsyncFunctionComponent("clearLogEntriesAsync", anyTypeArr8, function18);
                                }
                            } else {
                                asyncFunctionComponent8 = new FloatAsyncFunctionComponent("clearLogEntriesAsync", anyTypeArr8, function18);
                            }
                        } else {
                            asyncFunctionComponent8 = new DoubleAsyncFunctionComponent("clearLogEntriesAsync", anyTypeArr8, function18);
                        }
                    } else {
                        asyncFunctionComponent8 = new BoolAsyncFunctionComponent("clearLogEntriesAsync", anyTypeArr8, function18);
                    }
                } else {
                    asyncFunctionComponent8 = new IntAsyncFunctionComponent("clearLogEntriesAsync", anyTypeArr8, function18);
                }
                asyncFunctionWithPromiseComponent8 = asyncFunctionComponent8;
            }
            moduleDefinitionBuilder9.getAsyncFunctions().put("clearLogEntriesAsync", asyncFunctionWithPromiseComponent8);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* compiled from: UpdatesModule.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2)\u0010\n\u001a%\u0012\u001b\u0012\u0019\u0018\u00010\fj\u0004\u0018\u0001`\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00070\u000bH\u0000¢\u0006\u0002\b\u0011J#\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0017R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lexpo/modules/updates/UpdatesModule$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "clearLogEntries", "", "context", "Landroid/content/Context;", "completionHandler", "Lkotlin/Function1;", "Ljava/lang/Error;", "Lkotlin/Error;", "Lkotlin/ParameterName;", "name", "_", "clearLogEntries$expo_updates_release", "readLogEntries", "", "Landroid/os/Bundle;", "maxAge", "", "readLogEntries$expo_updates_release", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<Bundle> readLogEntries$expo_updates_release(Context context, long maxAge) {
            Intrinsics.checkNotNullParameter(context, "context");
            List<String> logEntries = new UpdatesLogReader(context).getLogEntries(new Date(new Date().getTime() - maxAge));
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = logEntries.iterator();
            while (it.hasNext()) {
                UpdatesLogEntry create = UpdatesLogEntry.INSTANCE.create((String) it.next());
                if (create != null) {
                    arrayList.add(create);
                }
            }
            ArrayList<UpdatesLogEntry> arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            for (UpdatesLogEntry updatesLogEntry : arrayList2) {
                Bundle bundle = new Bundle();
                bundle.putLong("timestamp", updatesLogEntry.getTimestamp());
                bundle.putString("message", updatesLogEntry.getMessage());
                bundle.putString("code", updatesLogEntry.getCode());
                bundle.putString("level", updatesLogEntry.getLevel());
                if (updatesLogEntry.getUpdateId() != null) {
                    bundle.putString("updateId", updatesLogEntry.getUpdateId());
                }
                if (updatesLogEntry.getAssetId() != null) {
                    bundle.putString("assetId", updatesLogEntry.getAssetId());
                }
                if (updatesLogEntry.getStacktrace() != null) {
                    bundle.putStringArray("stacktrace", (String[]) updatesLogEntry.getStacktrace().toArray(new String[0]));
                }
                arrayList3.add(bundle);
            }
            return arrayList3;
        }

        public final void clearLogEntries$expo_updates_release(Context context, Function1<? super Error, Unit> completionHandler) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(completionHandler, "completionHandler");
            new UpdatesLogReader(context).purgeLogEntries(new Date(), completionHandler);
        }
    }
}

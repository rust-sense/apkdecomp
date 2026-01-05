package expo.modules.haptics;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.tracing.Trace;
import expo.modules.haptics.arguments.HapticsImpactType;
import expo.modules.haptics.arguments.HapticsNotificationType;
import expo.modules.haptics.arguments.HapticsSelectionTypeKt;
import expo.modules.haptics.arguments.HapticsVibrationType;
import expo.modules.kotlin.Promise;
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
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: HapticsModule.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lexpo/modules/haptics/HapticsModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "vibrator", "Landroid/os/Vibrator;", "getVibrator", "()Landroid/os/Vibrator;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "vibrate", "", "type", "Lexpo/modules/haptics/arguments/HapticsVibrationType;", "expo-haptics_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HapticsModule extends Module {
    private final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    private final Vibrator getVibrator() {
        Vibrator defaultVibrator;
        if (Build.VERSION.SDK_INT >= 31) {
            Object systemService = getContext().getSystemService("vibrator_manager");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.VibratorManager");
            defaultVibrator = HapticsModule$$ExternalSyntheticApiModelOutline0.m662m(systemService).getDefaultVibrator();
            Intrinsics.checkNotNull(defaultVibrator);
            return defaultVibrator;
        }
        Object systemService2 = getContext().getSystemService("vibrator");
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.os.Vibrator");
        return (Vibrator) systemService2;
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionComponent asyncFunctionComponent3;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        HapticsModule hapticsModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (hapticsModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(hapticsModule);
            moduleDefinitionBuilder.Name("ExpoHaptics");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("notificationAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.haptics.HapticsModule$definition$lambda$3$$inlined$AsyncFunction$1
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        HapticsModule.this.vibrate(HapticsNotificationType.INSTANCE.fromString((String) promise));
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.haptics.HapticsModule$definition$lambda$3$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))};
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.haptics.HapticsModule$definition$lambda$3$$inlined$AsyncFunction$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        HapticsModule.this.vibrate(HapticsNotificationType.INSTANCE.fromString((String) objArr[0]));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent = new StringAsyncFunctionComponent("notificationAsync", anyTypeArr, function1);
                                } else {
                                    asyncFunctionComponent = new AsyncFunctionComponent("notificationAsync", anyTypeArr, function1);
                                }
                            } else {
                                asyncFunctionComponent = new FloatAsyncFunctionComponent("notificationAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new DoubleAsyncFunctionComponent("notificationAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new BoolAsyncFunctionComponent("notificationAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new IntAsyncFunctionComponent("notificationAsync", anyTypeArr, function1);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent;
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("notificationAsync", asyncFunctionWithPromiseComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr2 = new AnyType[0];
            Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.haptics.HapticsModule$definition$lambda$3$$inlined$AsyncFunction$4
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    HapticsModule.this.vibrate(HapticsSelectionTypeKt.getHapticsSelectionType());
                    return Unit.INSTANCE;
                }
            };
            if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Unit.class, String.class)) {
                                asyncFunctionComponent2 = new StringAsyncFunctionComponent("selectionAsync", anyTypeArr2, function12);
                            } else {
                                asyncFunctionComponent2 = new AsyncFunctionComponent("selectionAsync", anyTypeArr2, function12);
                            }
                        } else {
                            asyncFunctionComponent2 = new FloatAsyncFunctionComponent("selectionAsync", anyTypeArr2, function12);
                        }
                    } else {
                        asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("selectionAsync", anyTypeArr2, function12);
                    }
                } else {
                    asyncFunctionComponent2 = new BoolAsyncFunctionComponent("selectionAsync", anyTypeArr2, function12);
                }
            } else {
                asyncFunctionComponent2 = new IntAsyncFunctionComponent("selectionAsync", anyTypeArr2, function12);
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("selectionAsync", asyncFunctionComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("impactAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.haptics.HapticsModule$definition$lambda$3$$inlined$AsyncFunction$5
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        HapticsModule.this.vibrate(HapticsImpactType.INSTANCE.fromString((String) promise));
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr3 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.haptics.HapticsModule$definition$lambda$3$$inlined$AsyncFunction$6
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))};
                Function1<Object[], Unit> function13 = new Function1<Object[], Unit>() { // from class: expo.modules.haptics.HapticsModule$definition$lambda$3$$inlined$AsyncFunction$7
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        HapticsModule.this.vibrate(HapticsImpactType.INSTANCE.fromString((String) objArr[0]));
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent3 = new StringAsyncFunctionComponent("impactAsync", anyTypeArr3, function13);
                                } else {
                                    asyncFunctionComponent3 = new AsyncFunctionComponent("impactAsync", anyTypeArr3, function13);
                                }
                            } else {
                                asyncFunctionComponent3 = new FloatAsyncFunctionComponent("impactAsync", anyTypeArr3, function13);
                            }
                        } else {
                            asyncFunctionComponent3 = new DoubleAsyncFunctionComponent("impactAsync", anyTypeArr3, function13);
                        }
                    } else {
                        asyncFunctionComponent3 = new BoolAsyncFunctionComponent("impactAsync", anyTypeArr3, function13);
                    }
                } else {
                    asyncFunctionComponent3 = new IntAsyncFunctionComponent("impactAsync", anyTypeArr3, function13);
                }
                asyncFunctionWithPromiseComponent2 = asyncFunctionComponent3;
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("impactAsync", asyncFunctionWithPromiseComponent2);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void vibrate(HapticsVibrationType type) {
        VibrationEffect createWaveform;
        if (Build.VERSION.SDK_INT >= 26) {
            Vibrator vibrator = getVibrator();
            createWaveform = VibrationEffect.createWaveform(type.getTimings(), type.getAmplitudes(), -1);
            vibrator.vibrate(createWaveform);
            return;
        }
        getVibrator().vibrate(type.getOldSDKPattern(), -1);
    }
}

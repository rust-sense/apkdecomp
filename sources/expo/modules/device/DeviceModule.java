package expo.modules.device;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.graphics.Rect;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.tracing.Trace;
import com.facebook.device.yearclass.YearClass;
import expo.modules.core.utilities.EmulatorUtilities;
import expo.modules.device.DeviceModule;
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
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.text.StringsKt;

/* compiled from: DeviceModule.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lexpo/modules/device/DeviceModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "deviceYearClass", "", "getDeviceYearClass", "()I", "systemName", "", "getSystemName", "()Ljava/lang/String;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "Companion", "DeviceType", "expo-device_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DeviceModule extends Module {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: DeviceModule.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/device/DeviceModule$DeviceType;", "", "JSValue", "", "(Ljava/lang/String;II)V", "getJSValue", "()I", "UNKNOWN", "PHONE", "TABLET", "DESKTOP", "TV", "expo-device_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DeviceType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ DeviceType[] $VALUES;
        private final int JSValue;
        public static final DeviceType UNKNOWN = new DeviceType("UNKNOWN", 0, 0);
        public static final DeviceType PHONE = new DeviceType("PHONE", 1, 1);
        public static final DeviceType TABLET = new DeviceType("TABLET", 2, 2);
        public static final DeviceType DESKTOP = new DeviceType("DESKTOP", 3, 3);
        public static final DeviceType TV = new DeviceType("TV", 4, 4);

        private static final /* synthetic */ DeviceType[] $values() {
            return new DeviceType[]{UNKNOWN, PHONE, TABLET, DESKTOP, TV};
        }

        public static EnumEntries<DeviceType> getEntries() {
            return $ENTRIES;
        }

        public static DeviceType valueOf(String str) {
            return (DeviceType) Enum.valueOf(DeviceType.class, str);
        }

        public static DeviceType[] values() {
            return (DeviceType[]) $VALUES.clone();
        }

        public final int getJSValue() {
            return this.JSValue;
        }

        private DeviceType(String str, int i, int i2) {
            this.JSValue = i2;
        }

        static {
            DeviceType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
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
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionComponent asyncFunctionComponent3;
        AsyncFunctionComponent asyncFunctionComponent4;
        AsyncFunctionComponent asyncFunctionComponent5;
        AsyncFunctionComponent asyncFunctionComponent6;
        AsyncFunctionComponent asyncFunctionComponent7;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        DeviceModule deviceModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (deviceModule.getClass() + ".ModuleDefinition"));
        try {
            final ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(deviceModule);
            moduleDefinitionBuilder.Name("ExpoDevice");
            moduleDefinitionBuilder.Constants(new Function0<Map<String, ? extends Object>>() { // from class: expo.modules.device.DeviceModule$definition$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Code restructure failed: missing block: B:7:0x00bc, code lost:
                
                    if (java.lang.Boolean.valueOf(true ^ (r1.length == 0)).booleanValue() != false) goto L11;
                 */
                @Override // kotlin.jvm.functions.Function0
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.util.Map<java.lang.String, ? extends java.lang.Object> invoke() {
                    /*
                        Method dump skipped, instructions count: 336
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.device.DeviceModule$definition$1$1.invoke():java.util.Map");
                }
            });
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr = new AnyType[0];
            Function1<Object[], Integer> function1 = new Function1<Object[], Integer>() { // from class: expo.modules.device.DeviceModule$definition$lambda$8$$inlined$AsyncFunction$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Integer invoke(Object[] it) {
                    Context context;
                    DeviceModule.DeviceType deviceType;
                    Intrinsics.checkNotNullParameter(it, "it");
                    DeviceModule.Companion companion = DeviceModule.INSTANCE;
                    context = DeviceModule.this.getContext();
                    deviceType = companion.getDeviceType(context);
                    return Integer.valueOf(deviceType.getJSValue());
                }
            };
            if (!Intrinsics.areEqual(Integer.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Integer.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Integer.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Integer.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Integer.class, String.class)) {
                                asyncFunctionComponent = new StringAsyncFunctionComponent("getDeviceTypeAsync", anyTypeArr, function1);
                            } else {
                                asyncFunctionComponent = new AsyncFunctionComponent("getDeviceTypeAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new FloatAsyncFunctionComponent("getDeviceTypeAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new DoubleAsyncFunctionComponent("getDeviceTypeAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new BoolAsyncFunctionComponent("getDeviceTypeAsync", anyTypeArr, function1);
                }
            } else {
                asyncFunctionComponent = new IntAsyncFunctionComponent("getDeviceTypeAsync", anyTypeArr, function1);
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getDeviceTypeAsync", asyncFunctionComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr2 = new AnyType[0];
            Function1<Object[], Double> function12 = new Function1<Object[], Double>() { // from class: expo.modules.device.DeviceModule$definition$lambda$8$$inlined$AsyncFunction$2
                @Override // kotlin.jvm.functions.Function1
                public final Double invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Double.valueOf(SystemClock.uptimeMillis());
                }
            };
            if (!Intrinsics.areEqual(Double.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Double.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Double.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Double.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Double.class, String.class)) {
                                asyncFunctionComponent2 = new StringAsyncFunctionComponent("getUptimeAsync", anyTypeArr2, function12);
                            } else {
                                asyncFunctionComponent2 = new AsyncFunctionComponent("getUptimeAsync", anyTypeArr2, function12);
                            }
                        } else {
                            asyncFunctionComponent2 = new FloatAsyncFunctionComponent("getUptimeAsync", anyTypeArr2, function12);
                        }
                    } else {
                        asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("getUptimeAsync", anyTypeArr2, function12);
                    }
                } else {
                    asyncFunctionComponent2 = new BoolAsyncFunctionComponent("getUptimeAsync", anyTypeArr2, function12);
                }
            } else {
                asyncFunctionComponent2 = new IntAsyncFunctionComponent("getUptimeAsync", anyTypeArr2, function12);
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("getUptimeAsync", asyncFunctionComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr3 = new AnyType[0];
            Function1<Object[], Double> function13 = new Function1<Object[], Double>() { // from class: expo.modules.device.DeviceModule$definition$lambda$8$$inlined$AsyncFunction$3
                @Override // kotlin.jvm.functions.Function1
                public final Double invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    long maxMemory = Runtime.getRuntime().maxMemory();
                    return Double.valueOf(maxMemory != Long.MAX_VALUE ? maxMemory : -1.0d);
                }
            };
            if (!Intrinsics.areEqual(Double.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Double.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Double.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Double.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Double.class, String.class)) {
                                asyncFunctionComponent3 = new StringAsyncFunctionComponent("getMaxMemoryAsync", anyTypeArr3, function13);
                            } else {
                                asyncFunctionComponent3 = new AsyncFunctionComponent("getMaxMemoryAsync", anyTypeArr3, function13);
                            }
                        } else {
                            asyncFunctionComponent3 = new FloatAsyncFunctionComponent("getMaxMemoryAsync", anyTypeArr3, function13);
                        }
                    } else {
                        asyncFunctionComponent3 = new DoubleAsyncFunctionComponent("getMaxMemoryAsync", anyTypeArr3, function13);
                    }
                } else {
                    asyncFunctionComponent3 = new BoolAsyncFunctionComponent("getMaxMemoryAsync", anyTypeArr3, function13);
                }
            } else {
                asyncFunctionComponent3 = new IntAsyncFunctionComponent("getMaxMemoryAsync", anyTypeArr3, function13);
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("getMaxMemoryAsync", asyncFunctionComponent3);
            ModuleDefinitionBuilder moduleDefinitionBuilder5 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr4 = new AnyType[0];
            Function1<Object[], Boolean> function14 = new Function1<Object[], Boolean>() { // from class: expo.modules.device.DeviceModule$definition$lambda$8$$inlined$AsyncFunction$4
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object[] it) {
                    boolean isRunningOnEmulator;
                    Intrinsics.checkNotNullParameter(it, "it");
                    isRunningOnEmulator = DeviceModule.INSTANCE.isRunningOnEmulator();
                    boolean z = true;
                    boolean z2 = !isRunningOnEmulator;
                    String str = Build.TAGS;
                    if ((!z2 || str == null || !StringsKt.contains$default((CharSequence) str, (CharSequence) "test-keys", false, 2, (Object) null)) && !new File("/system/app/Superuser.apk").exists() && (!z2 || !new File("/system/xbin/su").exists())) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
            };
            if (!Intrinsics.areEqual(Boolean.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Boolean.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Boolean.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Boolean.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Boolean.class, String.class)) {
                                asyncFunctionComponent4 = new StringAsyncFunctionComponent("isRootedExperimentalAsync", anyTypeArr4, function14);
                            } else {
                                asyncFunctionComponent4 = new AsyncFunctionComponent("isRootedExperimentalAsync", anyTypeArr4, function14);
                            }
                        } else {
                            asyncFunctionComponent4 = new FloatAsyncFunctionComponent("isRootedExperimentalAsync", anyTypeArr4, function14);
                        }
                    } else {
                        asyncFunctionComponent4 = new DoubleAsyncFunctionComponent("isRootedExperimentalAsync", anyTypeArr4, function14);
                    }
                } else {
                    asyncFunctionComponent4 = new BoolAsyncFunctionComponent("isRootedExperimentalAsync", anyTypeArr4, function14);
                }
            } else {
                asyncFunctionComponent4 = new IntAsyncFunctionComponent("isRootedExperimentalAsync", anyTypeArr4, function14);
            }
            moduleDefinitionBuilder5.getAsyncFunctions().put("isRootedExperimentalAsync", asyncFunctionComponent4);
            ModuleDefinitionBuilder moduleDefinitionBuilder6 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr5 = new AnyType[0];
            Function1<Object[], Boolean> function15 = new Function1<Object[], Boolean>() { // from class: expo.modules.device.DeviceModule$definition$lambda$8$$inlined$AsyncFunction$5
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Object[] it) {
                    Context context;
                    boolean canRequestPackageInstalls;
                    Context context2;
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (Build.VERSION.SDK_INT < 26) {
                        context2 = DeviceModule.this.getContext();
                        canRequestPackageInstalls = false;
                        if (Settings.Global.getInt(context2.getApplicationContext().getContentResolver(), "install_non_market_apps", 0) == 1) {
                            canRequestPackageInstalls = true;
                        }
                    } else {
                        context = DeviceModule.this.getContext();
                        canRequestPackageInstalls = context.getApplicationContext().getPackageManager().canRequestPackageInstalls();
                    }
                    return Boolean.valueOf(canRequestPackageInstalls);
                }
            };
            if (!Intrinsics.areEqual(Boolean.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Boolean.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Boolean.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Boolean.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Boolean.class, String.class)) {
                                asyncFunctionComponent5 = new StringAsyncFunctionComponent("isSideLoadingEnabledAsync", anyTypeArr5, function15);
                            } else {
                                asyncFunctionComponent5 = new AsyncFunctionComponent("isSideLoadingEnabledAsync", anyTypeArr5, function15);
                            }
                        } else {
                            asyncFunctionComponent5 = new FloatAsyncFunctionComponent("isSideLoadingEnabledAsync", anyTypeArr5, function15);
                        }
                    } else {
                        asyncFunctionComponent5 = new DoubleAsyncFunctionComponent("isSideLoadingEnabledAsync", anyTypeArr5, function15);
                    }
                } else {
                    asyncFunctionComponent5 = new BoolAsyncFunctionComponent("isSideLoadingEnabledAsync", anyTypeArr5, function15);
                }
            } else {
                asyncFunctionComponent5 = new IntAsyncFunctionComponent("isSideLoadingEnabledAsync", anyTypeArr5, function15);
            }
            moduleDefinitionBuilder6.getAsyncFunctions().put("isSideLoadingEnabledAsync", asyncFunctionComponent5);
            ModuleDefinitionBuilder moduleDefinitionBuilder7 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr6 = new AnyType[0];
            Function1<Object[], List<? extends String>> function16 = new Function1<Object[], List<? extends String>>() { // from class: expo.modules.device.DeviceModule$definition$lambda$8$$inlined$AsyncFunction$6
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final List<? extends String> invoke(Object[] it) {
                    Context context;
                    Intrinsics.checkNotNullParameter(it, "it");
                    context = DeviceModule.this.getContext();
                    FeatureInfo[] systemAvailableFeatures = context.getApplicationContext().getPackageManager().getSystemAvailableFeatures();
                    Intrinsics.checkNotNullExpressionValue(systemAvailableFeatures, "getSystemAvailableFeatures(...)");
                    List filterNotNull = ArraysKt.filterNotNull(systemAvailableFeatures);
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(filterNotNull, 10));
                    Iterator it2 = filterNotNull.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(((FeatureInfo) it2.next()).name);
                    }
                    return arrayList;
                }
            };
            if (!Intrinsics.areEqual(List.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(List.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(List.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(List.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(List.class, String.class)) {
                                asyncFunctionComponent6 = new StringAsyncFunctionComponent("getPlatformFeaturesAsync", anyTypeArr6, function16);
                            } else {
                                asyncFunctionComponent6 = new AsyncFunctionComponent("getPlatformFeaturesAsync", anyTypeArr6, function16);
                            }
                        } else {
                            asyncFunctionComponent6 = new FloatAsyncFunctionComponent("getPlatformFeaturesAsync", anyTypeArr6, function16);
                        }
                    } else {
                        asyncFunctionComponent6 = new DoubleAsyncFunctionComponent("getPlatformFeaturesAsync", anyTypeArr6, function16);
                    }
                } else {
                    asyncFunctionComponent6 = new BoolAsyncFunctionComponent("getPlatformFeaturesAsync", anyTypeArr6, function16);
                }
            } else {
                asyncFunctionComponent6 = new IntAsyncFunctionComponent("getPlatformFeaturesAsync", anyTypeArr6, function16);
            }
            moduleDefinitionBuilder7.getAsyncFunctions().put("getPlatformFeaturesAsync", asyncFunctionComponent6);
            ModuleDefinitionBuilder moduleDefinitionBuilder8 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("hasPlatformFeatureAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.device.DeviceModule$definition$lambda$8$$inlined$AsyncFunction$7
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Context context;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        context = DeviceModule.this.getContext();
                        context.getApplicationContext().getPackageManager().hasSystemFeature((String) promise);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr7 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.device.DeviceModule$definition$lambda$8$$inlined$AsyncFunction$8
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(String.class);
                    }
                }))};
                Function1<Object[], Boolean> function17 = new Function1<Object[], Boolean>() { // from class: expo.modules.device.DeviceModule$definition$lambda$8$$inlined$AsyncFunction$9
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(Object[] objArr) {
                        Context context;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        String str = (String) objArr[0];
                        context = DeviceModule.this.getContext();
                        return Boolean.valueOf(context.getApplicationContext().getPackageManager().hasSystemFeature(str));
                    }
                };
                if (!Intrinsics.areEqual(Boolean.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Boolean.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Boolean.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Boolean.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Boolean.class, String.class)) {
                                    asyncFunctionComponent7 = new StringAsyncFunctionComponent("hasPlatformFeatureAsync", anyTypeArr7, function17);
                                } else {
                                    asyncFunctionComponent7 = new AsyncFunctionComponent("hasPlatformFeatureAsync", anyTypeArr7, function17);
                                }
                            } else {
                                asyncFunctionComponent7 = new FloatAsyncFunctionComponent("hasPlatformFeatureAsync", anyTypeArr7, function17);
                            }
                        } else {
                            asyncFunctionComponent7 = new DoubleAsyncFunctionComponent("hasPlatformFeatureAsync", anyTypeArr7, function17);
                        }
                    } else {
                        asyncFunctionComponent7 = new BoolAsyncFunctionComponent("hasPlatformFeatureAsync", anyTypeArr7, function17);
                    }
                } else {
                    asyncFunctionComponent7 = new IntAsyncFunctionComponent("hasPlatformFeatureAsync", anyTypeArr7, function17);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent7;
            }
            moduleDefinitionBuilder8.getAsyncFunctions().put("hasPlatformFeatureAsync", asyncFunctionWithPromiseComponent);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDeviceYearClass() {
        return YearClass.get(getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSystemName() {
        String str = Build.VERSION.BASE_OS;
        Intrinsics.checkNotNull(str);
        if (str.length() <= 0) {
            str = null;
        }
        return str == null ? "Android" : str;
    }

    /* compiled from: DeviceModule.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005¨\u0006\f"}, d2 = {"Lexpo/modules/device/DeviceModule$Companion;", "", "()V", "isRunningOnEmulator", "", "()Z", "getDeviceType", "Lexpo/modules/device/DeviceModule$DeviceType;", "context", "Landroid/content/Context;", "getDeviceTypeFromPhysicalSize", "getDeviceTypeFromResourceConfiguration", "expo-device_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isRunningOnEmulator() {
            return EmulatorUtilities.INSTANCE.isRunningOnEmulator();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final DeviceType getDeviceType(Context context) {
            if (context.getApplicationContext().getPackageManager().hasSystemFeature("amazon.hardware.fire_tv")) {
                return DeviceType.TV;
            }
            UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
            if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
                return DeviceType.TV;
            }
            DeviceType deviceTypeFromResourceConfiguration = getDeviceTypeFromResourceConfiguration(context);
            return deviceTypeFromResourceConfiguration != DeviceType.UNKNOWN ? deviceTypeFromResourceConfiguration : getDeviceTypeFromPhysicalSize(context);
        }

        private final DeviceType getDeviceTypeFromResourceConfiguration(Context context) {
            int i = context.getResources().getConfiguration().smallestScreenWidthDp;
            if (i == 0) {
                return DeviceType.UNKNOWN;
            }
            if (i >= 600) {
                return DeviceType.TABLET;
            }
            return DeviceType.PHONE;
        }

        private final DeviceType getDeviceTypeFromPhysicalSize(Context context) {
            double d;
            double d2;
            WindowMetrics currentWindowMetrics;
            Rect bounds;
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return DeviceType.UNKNOWN;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                currentWindowMetrics = windowManager.getCurrentWindowMetrics();
                bounds = currentWindowMetrics.getBounds();
                Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
                double d3 = context.getResources().getConfiguration().densityDpi;
                d = bounds.width() / d3;
                d2 = bounds.height() / d3;
            } else {
                windowManager.getDefaultDisplay().getRealMetrics(new DisplayMetrics());
                d = r8.widthPixels / r8.xdpi;
                d2 = r8.heightPixels / r8.ydpi;
            }
            double sqrt = Math.sqrt(Math.pow(d, 2.0d) + Math.pow(d2, 2.0d));
            if (3.0d <= sqrt && sqrt <= 6.9d) {
                return DeviceType.PHONE;
            }
            if (sqrt > 6.9d && sqrt <= 18.0d) {
                return DeviceType.TABLET;
            }
            return DeviceType.UNKNOWN;
        }
    }
}

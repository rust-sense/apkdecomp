package expo.modules.screenorientation;

import android.app.Activity;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.tracing.Trace;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.core.errors.InvalidArgumentException;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
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
import expo.modules.screenorientation.enums.Orientation;
import expo.modules.screenorientation.enums.OrientationAttr;
import expo.modules.screenorientation.enums.OrientationLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: ScreenOrientationModule.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0007¨\u0006\u001f"}, d2 = {"Lexpo/modules/screenorientation/ScreenOrientationModule;", "Lexpo/modules/kotlin/modules/Module;", "Lexpo/modules/core/interfaces/LifecycleEventListener;", "()V", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "initialOrientation", "", "Ljava/lang/Integer;", "uiManager", "Lexpo/modules/core/interfaces/services/UIManager;", "getUiManager", "()Lexpo/modules/core/interfaces/services/UIManager;", "weakCurrentActivity", "getWeakCurrentActivity", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getScreenOrientation", "Lexpo/modules/screenorientation/enums/Orientation;", "activity", "isPortraitNaturalOrientation", "", ViewProps.ROTATION, "width", "height", "onHostDestroy", "", "onHostPause", "onHostResume", "expo-screen-orientation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ScreenOrientationModule extends Module implements LifecycleEventListener {
    private Integer initialOrientation;

    private final boolean isPortraitNaturalOrientation(int rotation, int width, int height) {
        if ((rotation == 0 || rotation == 2) && height > width) {
            return true;
        }
        return (rotation == 1 || rotation == 3) && width > height;
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Activity getWeakCurrentActivity() {
        ActivityProvider activityProvider = getAppContext().getActivityProvider();
        if (activityProvider != null) {
            return activityProvider.getCurrentActivity();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Activity getCurrentActivity() {
        Activity weakCurrentActivity = getWeakCurrentActivity();
        if (weakCurrentActivity != null) {
            return weakCurrentActivity;
        }
        throw new Exceptions.MissingActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UIManager getUiManager() {
        UIManager uIManager = (UIManager) getAppContext().getLegacyModuleRegistry().getModule(UIManager.class);
        if (uIManager != null) {
            return uIManager;
        }
        throw new IllegalStateException("Could not find implementation for UIManager.");
    }

    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent;
        AsyncFunctionComponent asyncFunctionComponent2;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2;
        AsyncFunctionComponent asyncFunctionComponent3;
        AsyncFunctionComponent asyncFunctionComponent4;
        AsyncFunctionComponent asyncFunctionComponent5;
        AsyncFunctionComponent asyncFunctionComponent6;
        AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3;
        ScreenOrientationModule screenOrientationModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (screenOrientationModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(screenOrientationModule);
            moduleDefinitionBuilder.Name("ExpoScreenOrientation");
            moduleDefinitionBuilder.Events("expoDidUpdateDimensions");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(OrientationLock.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("lockAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$1
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Activity currentActivity;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        OrientationLock orientationLock = (OrientationLock) promise;
                        try {
                            currentActivity = ScreenOrientationModule.this.getCurrentActivity();
                            currentActivity.setRequestedOrientation(orientationLock.toPlatformInt$expo_screen_orientation_release());
                        } catch (InvalidArgumentException e) {
                            throw new InvalidOrientationLockException(orientationLock.getValue(), e);
                        }
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(OrientationLock.class), false, new Function0<KType>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(OrientationLock.class);
                    }
                }))};
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Activity currentActivity;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        OrientationLock orientationLock = (OrientationLock) objArr[0];
                        try {
                            currentActivity = ScreenOrientationModule.this.getCurrentActivity();
                            currentActivity.setRequestedOrientation(orientationLock.toPlatformInt$expo_screen_orientation_release());
                            return Unit.INSTANCE;
                        } catch (InvalidArgumentException e) {
                            throw new InvalidOrientationLockException(orientationLock.getValue(), e);
                        }
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent = new StringAsyncFunctionComponent("lockAsync", anyTypeArr, function1);
                                } else {
                                    asyncFunctionComponent = new AsyncFunctionComponent("lockAsync", anyTypeArr, function1);
                                }
                            } else {
                                asyncFunctionComponent = new FloatAsyncFunctionComponent("lockAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new DoubleAsyncFunctionComponent("lockAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new BoolAsyncFunctionComponent("lockAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new IntAsyncFunctionComponent("lockAsync", anyTypeArr, function1);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent;
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("lockAsync", asyncFunctionWithPromiseComponent);
            ModuleDefinitionBuilder moduleDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(OrientationAttr.class, Promise.class)) {
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("lockPlatformAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$4
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Activity currentActivity;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        currentActivity = ScreenOrientationModule.this.getCurrentActivity();
                        currentActivity.setRequestedOrientation(((OrientationAttr) promise).getValue());
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(OrientationAttr.class), false, new Function0<KType>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$5
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(OrientationAttr.class);
                    }
                }))};
                Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$6
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Activity currentActivity;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        OrientationAttr orientationAttr = (OrientationAttr) objArr[0];
                        currentActivity = ScreenOrientationModule.this.getCurrentActivity();
                        currentActivity.setRequestedOrientation(orientationAttr.getValue());
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent2 = new StringAsyncFunctionComponent("lockPlatformAsync", anyTypeArr2, function12);
                                } else {
                                    asyncFunctionComponent2 = new AsyncFunctionComponent("lockPlatformAsync", anyTypeArr2, function12);
                                }
                            } else {
                                asyncFunctionComponent2 = new FloatAsyncFunctionComponent("lockPlatformAsync", anyTypeArr2, function12);
                            }
                        } else {
                            asyncFunctionComponent2 = new DoubleAsyncFunctionComponent("lockPlatformAsync", anyTypeArr2, function12);
                        }
                    } else {
                        asyncFunctionComponent2 = new BoolAsyncFunctionComponent("lockPlatformAsync", anyTypeArr2, function12);
                    }
                } else {
                    asyncFunctionComponent2 = new IntAsyncFunctionComponent("lockPlatformAsync", anyTypeArr2, function12);
                }
                asyncFunctionWithPromiseComponent2 = asyncFunctionComponent2;
            }
            moduleDefinitionBuilder3.getAsyncFunctions().put("lockPlatformAsync", asyncFunctionWithPromiseComponent2);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr3 = new AnyType[0];
            Function1<Object[], Integer> function13 = new Function1<Object[], Integer>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$7
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Integer invoke(Object[] it) {
                    Activity currentActivity;
                    Orientation screenOrientation;
                    Intrinsics.checkNotNullParameter(it, "it");
                    ScreenOrientationModule screenOrientationModule2 = ScreenOrientationModule.this;
                    currentActivity = screenOrientationModule2.getCurrentActivity();
                    screenOrientation = screenOrientationModule2.getScreenOrientation(currentActivity);
                    return Integer.valueOf(screenOrientation.getValue());
                }
            };
            if (!Intrinsics.areEqual(Integer.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Integer.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Integer.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Integer.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Integer.class, String.class)) {
                                asyncFunctionComponent3 = new StringAsyncFunctionComponent("getOrientationAsync", anyTypeArr3, function13);
                            } else {
                                asyncFunctionComponent3 = new AsyncFunctionComponent("getOrientationAsync", anyTypeArr3, function13);
                            }
                        } else {
                            asyncFunctionComponent3 = new FloatAsyncFunctionComponent("getOrientationAsync", anyTypeArr3, function13);
                        }
                    } else {
                        asyncFunctionComponent3 = new DoubleAsyncFunctionComponent("getOrientationAsync", anyTypeArr3, function13);
                    }
                } else {
                    asyncFunctionComponent3 = new BoolAsyncFunctionComponent("getOrientationAsync", anyTypeArr3, function13);
                }
            } else {
                asyncFunctionComponent3 = new IntAsyncFunctionComponent("getOrientationAsync", anyTypeArr3, function13);
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("getOrientationAsync", asyncFunctionComponent3);
            ModuleDefinitionBuilder moduleDefinitionBuilder5 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr4 = new AnyType[0];
            Function1<Object[], OrientationLock> function14 = new Function1<Object[], OrientationLock>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$8
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final OrientationLock invoke(Object[] it) {
                    Activity currentActivity;
                    Intrinsics.checkNotNullParameter(it, "it");
                    try {
                        OrientationLock.Companion companion = OrientationLock.INSTANCE;
                        currentActivity = ScreenOrientationModule.this.getCurrentActivity();
                        return companion.fromPlatformInt(currentActivity.getRequestedOrientation());
                    } catch (Exception e) {
                        throw new GetOrientationLockException(e);
                    }
                }
            };
            if (!Intrinsics.areEqual(OrientationLock.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(OrientationLock.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(OrientationLock.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(OrientationLock.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(OrientationLock.class, String.class)) {
                                asyncFunctionComponent4 = new StringAsyncFunctionComponent("getOrientationLockAsync", anyTypeArr4, function14);
                            } else {
                                asyncFunctionComponent4 = new AsyncFunctionComponent("getOrientationLockAsync", anyTypeArr4, function14);
                            }
                        } else {
                            asyncFunctionComponent4 = new FloatAsyncFunctionComponent("getOrientationLockAsync", anyTypeArr4, function14);
                        }
                    } else {
                        asyncFunctionComponent4 = new DoubleAsyncFunctionComponent("getOrientationLockAsync", anyTypeArr4, function14);
                    }
                } else {
                    asyncFunctionComponent4 = new BoolAsyncFunctionComponent("getOrientationLockAsync", anyTypeArr4, function14);
                }
            } else {
                asyncFunctionComponent4 = new IntAsyncFunctionComponent("getOrientationLockAsync", anyTypeArr4, function14);
            }
            moduleDefinitionBuilder5.getAsyncFunctions().put("getOrientationLockAsync", asyncFunctionComponent4);
            ModuleDefinitionBuilder moduleDefinitionBuilder6 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr5 = new AnyType[0];
            Function1<Object[], Integer> function15 = new Function1<Object[], Integer>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$9
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Integer invoke(Object[] it) {
                    Activity currentActivity;
                    Intrinsics.checkNotNullParameter(it, "it");
                    try {
                        currentActivity = ScreenOrientationModule.this.getCurrentActivity();
                        return Integer.valueOf(currentActivity.getRequestedOrientation());
                    } catch (Exception e) {
                        throw new GetPlatformOrientationLockException(e);
                    }
                }
            };
            if (!Intrinsics.areEqual(Integer.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(Integer.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(Integer.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(Integer.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(Integer.class, String.class)) {
                                asyncFunctionComponent5 = new StringAsyncFunctionComponent("getPlatformOrientationLockAsync", anyTypeArr5, function15);
                            } else {
                                asyncFunctionComponent5 = new AsyncFunctionComponent("getPlatformOrientationLockAsync", anyTypeArr5, function15);
                            }
                        } else {
                            asyncFunctionComponent5 = new FloatAsyncFunctionComponent("getPlatformOrientationLockAsync", anyTypeArr5, function15);
                        }
                    } else {
                        asyncFunctionComponent5 = new DoubleAsyncFunctionComponent("getPlatformOrientationLockAsync", anyTypeArr5, function15);
                    }
                } else {
                    asyncFunctionComponent5 = new BoolAsyncFunctionComponent("getPlatformOrientationLockAsync", anyTypeArr5, function15);
                }
            } else {
                asyncFunctionComponent5 = new IntAsyncFunctionComponent("getPlatformOrientationLockAsync", anyTypeArr5, function15);
            }
            moduleDefinitionBuilder6.getAsyncFunctions().put("getPlatformOrientationLockAsync", asyncFunctionComponent5);
            ModuleDefinitionBuilder moduleDefinitionBuilder7 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Integer.class, Promise.class)) {
                asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("supportsOrientationLockAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$10
                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        OrientationLock.INSTANCE.supportsOrientationLock(((Integer) promise).intValue());
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr6 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, new Function0<KType>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$11
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Integer.TYPE);
                    }
                }))};
                Function1<Object[], Boolean> function16 = new Function1<Object[], Boolean>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$AsyncFunction$12
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(Object[] objArr) {
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        return Boolean.valueOf(OrientationLock.INSTANCE.supportsOrientationLock(((Number) objArr[0]).intValue()));
                    }
                };
                if (!Intrinsics.areEqual(Boolean.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Boolean.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Boolean.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Boolean.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Boolean.class, String.class)) {
                                    asyncFunctionComponent6 = new StringAsyncFunctionComponent("supportsOrientationLockAsync", anyTypeArr6, function16);
                                } else {
                                    asyncFunctionComponent6 = new AsyncFunctionComponent("supportsOrientationLockAsync", anyTypeArr6, function16);
                                }
                            } else {
                                asyncFunctionComponent6 = new FloatAsyncFunctionComponent("supportsOrientationLockAsync", anyTypeArr6, function16);
                            }
                        } else {
                            asyncFunctionComponent6 = new DoubleAsyncFunctionComponent("supportsOrientationLockAsync", anyTypeArr6, function16);
                        }
                    } else {
                        asyncFunctionComponent6 = new BoolAsyncFunctionComponent("supportsOrientationLockAsync", anyTypeArr6, function16);
                    }
                } else {
                    asyncFunctionComponent6 = new IntAsyncFunctionComponent("supportsOrientationLockAsync", anyTypeArr6, function16);
                }
                asyncFunctionWithPromiseComponent3 = asyncFunctionComponent6;
            }
            moduleDefinitionBuilder7.getAsyncFunctions().put("supportsOrientationLockAsync", asyncFunctionWithPromiseComponent3);
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new Function0<Unit>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$OnCreate$1
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
                    UIManager uiManager;
                    uiManager = ScreenOrientationModule.this.getUiManager();
                    uiManager.registerLifecycleEventListener(ScreenOrientationModule.this);
                }
            }));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new Function0<Unit>() { // from class: expo.modules.screenorientation.ScreenOrientationModule$definition$lambda$9$$inlined$OnDestroy$1
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
                    UIManager uiManager;
                    Integer num;
                    Activity weakCurrentActivity;
                    uiManager = ScreenOrientationModule.this.getUiManager();
                    uiManager.unregisterLifecycleEventListener(ScreenOrientationModule.this);
                    num = ScreenOrientationModule.this.initialOrientation;
                    if (num != null) {
                        int intValue = num.intValue();
                        weakCurrentActivity = ScreenOrientationModule.this.getWeakCurrentActivity();
                        if (weakCurrentActivity == null) {
                            return;
                        }
                        weakCurrentActivity.setRequestedOrientation(intValue);
                    }
                }
            }));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        Integer num = this.initialOrientation;
        if (num == null) {
            Activity weakCurrentActivity = getWeakCurrentActivity();
            num = weakCurrentActivity != null ? Integer.valueOf(weakCurrentActivity.getRequestedOrientation()) : null;
        }
        this.initialOrientation = num;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Orientation getScreenOrientation(Activity activity) {
        int rotation;
        DisplayMetrics displayMetrics;
        WindowMetrics currentWindowMetrics;
        WindowInsets windowInsets;
        int systemBars;
        Insets insetsIgnoringVisibility;
        Rect bounds;
        int i;
        int i2;
        Rect bounds2;
        int i3;
        int i4;
        Display display;
        WindowManager windowManager = activity.getWindowManager();
        if (windowManager == null) {
            return Orientation.UNKNOWN;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            display = getCurrentActivity().getWindow().getContext().getDisplay();
            if (display == null) {
                return Orientation.UNKNOWN;
            }
            rotation = display.getRotation();
        } else {
            rotation = windowManager.getDefaultDisplay().getRotation();
        }
        if (Build.VERSION.SDK_INT >= 30) {
            currentWindowMetrics = windowManager.getCurrentWindowMetrics();
            Intrinsics.checkNotNullExpressionValue(currentWindowMetrics, "getCurrentWindowMetrics(...)");
            windowInsets = currentWindowMetrics.getWindowInsets();
            systemBars = WindowInsets.Type.systemBars();
            insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(systemBars);
            Intrinsics.checkNotNullExpressionValue(insetsIgnoringVisibility, "getInsetsIgnoringVisibility(...)");
            displayMetrics = new DisplayMetrics();
            bounds = currentWindowMetrics.getBounds();
            int width = bounds.width();
            i = insetsIgnoringVisibility.left;
            int i5 = width - i;
            i2 = insetsIgnoringVisibility.right;
            displayMetrics.widthPixels = i5 - i2;
            bounds2 = currentWindowMetrics.getBounds();
            int height = bounds2.height();
            i3 = insetsIgnoringVisibility.top;
            i4 = insetsIgnoringVisibility.bottom;
            displayMetrics.heightPixels = (height - i3) - i4;
        } else {
            displayMetrics = new DisplayMetrics();
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Intrinsics.checkNotNullExpressionValue(defaultDisplay, "getDefaultDisplay(...)");
            defaultDisplay.getMetrics(displayMetrics);
        }
        if (isPortraitNaturalOrientation(rotation, displayMetrics.widthPixels, displayMetrics.heightPixels)) {
            if (rotation == 0) {
                return Orientation.PORTRAIT_UP;
            }
            if (rotation == 1) {
                return Orientation.LANDSCAPE_RIGHT;
            }
            if (rotation == 2) {
                return Orientation.PORTRAIT_DOWN;
            }
            if (rotation == 3) {
                return Orientation.LANDSCAPE_LEFT;
            }
            return Orientation.UNKNOWN;
        }
        if (rotation == 0) {
            return Orientation.LANDSCAPE_RIGHT;
        }
        if (rotation == 1) {
            return Orientation.PORTRAIT_DOWN;
        }
        if (rotation == 2) {
            return Orientation.LANDSCAPE_LEFT;
        }
        if (rotation == 3) {
            return Orientation.PORTRAIT_UP;
        }
        return Orientation.UNKNOWN;
    }
}

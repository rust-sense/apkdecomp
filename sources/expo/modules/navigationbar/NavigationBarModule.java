package expo.modules.navigationbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.tracing.Trace;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.BoolAsyncFunctionComponent;
import expo.modules.kotlin.functions.DoubleAsyncFunctionComponent;
import expo.modules.kotlin.functions.FloatAsyncFunctionComponent;
import expo.modules.kotlin.functions.IntAsyncFunctionComponent;
import expo.modules.kotlin.functions.Queues;
import expo.modules.kotlin.functions.StringAsyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.navigationbar.NavigationBarModule;
import expo.modules.navigationbar.singletons.NavigationBar;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.reflect.KType;

/* compiled from: NavigationBarModule.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lexpo/modules/navigationbar/NavigationBarModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "Companion", "expo-navigation-bar_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NavigationBarModule extends Module {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String VISIBILITY_EVENT_NAME = "ExpoNavigationBar.didChange";

    /* JADX INFO: Access modifiers changed from: private */
    public final Activity getActivity() {
        Activity currentActivity = getAppContext().getCurrentActivity();
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new Exceptions.MissingActivity();
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
        AsyncFunctionComponent asyncFunctionComponent7;
        AsyncFunctionComponent asyncFunctionComponent8;
        NavigationBarModule navigationBarModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (navigationBarModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(navigationBarModule);
            moduleDefinitionBuilder.Name("ExpoNavigationBar");
            moduleDefinitionBuilder.Events(VISIBILITY_EVENT_NAME);
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(String.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("startObserving", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$OnStartObserving$1
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Activity activity;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        activity = NavigationBarModule.this.getActivity();
                        View decorView = activity.getWindow().getDecorView();
                        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
                        decorView.post(new NavigationBarModule$definition$1$1$1(decorView, NavigationBarModule.this));
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$OnStartObserving$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }))};
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$OnStartObserving$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Activity activity;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        activity = NavigationBarModule.this.getActivity();
                        View decorView = activity.getWindow().getDecorView();
                        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
                        decorView.post(new NavigationBarModule$definition$1$1$1(decorView, NavigationBarModule.this));
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
                asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("stopObserving", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$OnStopObserving$1
                    {
                        super(2);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Object[] objArr, Promise promise) {
                        Activity activity;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        activity = NavigationBarModule.this.getActivity();
                        View decorView = activity.getWindow().getDecorView();
                        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
                        decorView.post(new NavigationBarModule$definition$1$2$1(decorView));
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                        invoke2(objArr, promise);
                        return Unit.INSTANCE;
                    }
                });
            } else {
                AnyType[] anyTypeArr2 = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, new Function0<KType>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$OnStopObserving$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.nullableTypeOf(String.class);
                    }
                }))};
                Function1<Object[], Unit> function12 = new Function1<Object[], Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$OnStopObserving$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Activity activity;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        activity = NavigationBarModule.this.getActivity();
                        View decorView = activity.getWindow().getDecorView();
                        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
                        decorView.post(new NavigationBarModule$definition$1$2$1(decorView));
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
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3 = new AsyncFunctionWithPromiseComponent("setBackgroundColorAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, new Function0<KType>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(Integer.TYPE);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$2
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Activity activity;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    int intValue = ((Number) objArr[0]).intValue();
                    NavigationBar navigationBar = NavigationBar.INSTANCE;
                    activity = NavigationBarModule.this.getActivity();
                    navigationBar.setBackgroundColor(activity, intValue, new Function0<Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$3$1
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
                            Promise.this.resolve((Object) null);
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("setBackgroundColorAsync", asyncFunctionWithPromiseComponent3);
            asyncFunctionWithPromiseComponent3.runOnQueue(Queues.MAIN);
            ModuleDefinitionBuilder moduleDefinitionBuilder4 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr3 = new AnyType[0];
            Function1<Object[], String> function13 = new Function1<Object[], String>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    Activity activity;
                    Intrinsics.checkNotNullParameter(it, "it");
                    NavigationBarModule.Companion companion = NavigationBarModule.INSTANCE;
                    activity = NavigationBarModule.this.getActivity();
                    return companion.colorToHex(activity.getWindow().getNavigationBarColor());
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent3 = new StringAsyncFunctionComponent("getBackgroundColorAsync", anyTypeArr3, function13);
                            } else {
                                asyncFunctionComponent3 = new AsyncFunctionComponent("getBackgroundColorAsync", anyTypeArr3, function13);
                            }
                        } else {
                            asyncFunctionComponent3 = new FloatAsyncFunctionComponent("getBackgroundColorAsync", anyTypeArr3, function13);
                        }
                    } else {
                        asyncFunctionComponent3 = new DoubleAsyncFunctionComponent("getBackgroundColorAsync", anyTypeArr3, function13);
                    }
                } else {
                    asyncFunctionComponent3 = new BoolAsyncFunctionComponent("getBackgroundColorAsync", anyTypeArr3, function13);
                }
            } else {
                asyncFunctionComponent3 = new IntAsyncFunctionComponent("getBackgroundColorAsync", anyTypeArr3, function13);
            }
            moduleDefinitionBuilder4.getAsyncFunctions().put("getBackgroundColorAsync", asyncFunctionComponent3);
            asyncFunctionComponent3.runOnQueue(Queues.MAIN);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent4 = new AsyncFunctionWithPromiseComponent("setBorderColorAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, new Function0<KType>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$3
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(Integer.TYPE);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$4
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Activity activity;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    int intValue = ((Number) objArr[0]).intValue();
                    NavigationBar navigationBar = NavigationBar.INSTANCE;
                    activity = NavigationBarModule.this.getActivity();
                    navigationBar.setBorderColor(activity, intValue, new Function0<Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$5$1
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
                            Promise.this.resolve((Object) null);
                        }
                    }, new Function1<String, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$5$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str) {
                            invoke2(str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String m) {
                            Intrinsics.checkNotNullParameter(m, "m");
                            Promise.this.reject(new NavigationBarException(m));
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("setBorderColorAsync", asyncFunctionWithPromiseComponent4);
            asyncFunctionWithPromiseComponent4.runOnQueue(Queues.MAIN);
            ModuleDefinitionBuilder moduleDefinitionBuilder5 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr4 = new AnyType[0];
            Function1<Object[], String> function14 = new Function1<Object[], String>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    Activity activity;
                    int navigationBarDividerColor;
                    Intrinsics.checkNotNullParameter(it, "it");
                    if (Build.VERSION.SDK_INT >= 28) {
                        NavigationBarModule.Companion companion = NavigationBarModule.INSTANCE;
                        activity = NavigationBarModule.this.getActivity();
                        navigationBarDividerColor = activity.getWindow().getNavigationBarDividerColor();
                        return companion.colorToHex(navigationBarDividerColor);
                    }
                    throw new NavigationBarException("'getBorderColorAsync' is only available on Android API 28 or higher");
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent4 = new StringAsyncFunctionComponent("getBorderColorAsync", anyTypeArr4, function14);
                            } else {
                                asyncFunctionComponent4 = new AsyncFunctionComponent("getBorderColorAsync", anyTypeArr4, function14);
                            }
                        } else {
                            asyncFunctionComponent4 = new FloatAsyncFunctionComponent("getBorderColorAsync", anyTypeArr4, function14);
                        }
                    } else {
                        asyncFunctionComponent4 = new DoubleAsyncFunctionComponent("getBorderColorAsync", anyTypeArr4, function14);
                    }
                } else {
                    asyncFunctionComponent4 = new BoolAsyncFunctionComponent("getBorderColorAsync", anyTypeArr4, function14);
                }
            } else {
                asyncFunctionComponent4 = new IntAsyncFunctionComponent("getBorderColorAsync", anyTypeArr4, function14);
            }
            moduleDefinitionBuilder5.getAsyncFunctions().put("getBorderColorAsync", asyncFunctionComponent4);
            asyncFunctionComponent4.runOnQueue(Queues.MAIN);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent5 = new AsyncFunctionWithPromiseComponent("setButtonStyleAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$5
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$6
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Activity activity;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    String str = (String) objArr[0];
                    NavigationBar navigationBar = NavigationBar.INSTANCE;
                    activity = NavigationBarModule.this.getActivity();
                    navigationBar.setButtonStyle(activity, str, new Function0<Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$7$1
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
                            Promise.this.resolve((Object) null);
                        }
                    }, new Function1<String, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$7$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                            invoke2(str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String m) {
                            Intrinsics.checkNotNullParameter(m, "m");
                            Promise.this.reject(new NavigationBarException(m));
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("setButtonStyleAsync", asyncFunctionWithPromiseComponent5);
            asyncFunctionWithPromiseComponent5.runOnQueue(Queues.MAIN);
            ModuleDefinitionBuilder moduleDefinitionBuilder6 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr5 = new AnyType[0];
            Function1<Object[], String> function15 = new Function1<Object[], String>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    Activity activity;
                    Activity activity2;
                    Intrinsics.checkNotNullParameter(it, "it");
                    activity = NavigationBarModule.this.getActivity();
                    Window window = activity.getWindow();
                    activity2 = NavigationBarModule.this.getActivity();
                    return new WindowInsetsControllerCompat(window, activity2.getWindow().getDecorView()).isAppearanceLightNavigationBars() ? "dark" : "light";
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent5 = new StringAsyncFunctionComponent("getButtonStyleAsync", anyTypeArr5, function15);
                            } else {
                                asyncFunctionComponent5 = new AsyncFunctionComponent("getButtonStyleAsync", anyTypeArr5, function15);
                            }
                        } else {
                            asyncFunctionComponent5 = new FloatAsyncFunctionComponent("getButtonStyleAsync", anyTypeArr5, function15);
                        }
                    } else {
                        asyncFunctionComponent5 = new DoubleAsyncFunctionComponent("getButtonStyleAsync", anyTypeArr5, function15);
                    }
                } else {
                    asyncFunctionComponent5 = new BoolAsyncFunctionComponent("getButtonStyleAsync", anyTypeArr5, function15);
                }
            } else {
                asyncFunctionComponent5 = new IntAsyncFunctionComponent("getButtonStyleAsync", anyTypeArr5, function15);
            }
            moduleDefinitionBuilder6.getAsyncFunctions().put("getButtonStyleAsync", asyncFunctionComponent5);
            asyncFunctionComponent5.runOnQueue(Queues.MAIN);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent6 = new AsyncFunctionWithPromiseComponent("setVisibilityAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$7
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$8
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Activity activity;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    String str = (String) objArr[0];
                    NavigationBar navigationBar = NavigationBar.INSTANCE;
                    activity = NavigationBarModule.this.getActivity();
                    navigationBar.setVisibility(activity, str, new Function0<Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$9$1
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
                            Promise.this.resolve((Object) null);
                        }
                    }, new Function1<String, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$9$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                            invoke2(str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String m) {
                            Intrinsics.checkNotNullParameter(m, "m");
                            Promise.this.reject(new NavigationBarException(m));
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("setVisibilityAsync", asyncFunctionWithPromiseComponent6);
            asyncFunctionWithPromiseComponent6.runOnQueue(Queues.MAIN);
            ModuleDefinitionBuilder moduleDefinitionBuilder7 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr6 = new AnyType[0];
            Function1<Object[], String> function16 = new Function1<Object[], String>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$4
                {
                    super(1);
                }

                /* JADX WARN: Removed duplicated region for block: B:5:0x0041 A[ORIG_RETURN, RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:7:0x003e A[RETURN, SYNTHETIC] */
                @Override // kotlin.jvm.functions.Function1
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.String invoke(java.lang.Object[] r2) {
                    /*
                        r1 = this;
                        java.lang.String r0 = "it"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                        int r2 = android.os.Build.VERSION.SDK_INT
                        r0 = 30
                        if (r2 < r0) goto L28
                        expo.modules.navigationbar.NavigationBarModule r2 = expo.modules.navigationbar.NavigationBarModule.this
                        android.app.Activity r2 = expo.modules.navigationbar.NavigationBarModule.access$getActivity(r2)
                        android.view.Window r2 = r2.getWindow()
                        android.view.View r2 = r2.getDecorView()
                        android.view.WindowInsets r2 = r2.getRootWindowInsets()
                        int r0 = expo.modules.haptics.HapticsModule$$ExternalSyntheticApiModelOutline0.m$2()
                        boolean r2 = expo.modules.haptics.HapticsModule$$ExternalSyntheticApiModelOutline0.m676m(r2, r0)
                        if (r2 == 0) goto L41
                        goto L3e
                    L28:
                        expo.modules.navigationbar.NavigationBarModule r2 = expo.modules.navigationbar.NavigationBarModule.this
                        android.app.Activity r2 = expo.modules.navigationbar.NavigationBarModule.access$getActivity(r2)
                        android.view.Window r2 = r2.getWindow()
                        android.view.View r2 = r2.getDecorView()
                        int r2 = r2.getSystemUiVisibility()
                        r2 = r2 & 2
                        if (r2 != 0) goto L41
                    L3e:
                        java.lang.String r2 = "visible"
                        goto L43
                    L41:
                        java.lang.String r2 = "hidden"
                    L43:
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$4.invoke(java.lang.Object[]):java.lang.Object");
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent6 = new StringAsyncFunctionComponent("getVisibilityAsync", anyTypeArr6, function16);
                            } else {
                                asyncFunctionComponent6 = new AsyncFunctionComponent("getVisibilityAsync", anyTypeArr6, function16);
                            }
                        } else {
                            asyncFunctionComponent6 = new FloatAsyncFunctionComponent("getVisibilityAsync", anyTypeArr6, function16);
                        }
                    } else {
                        asyncFunctionComponent6 = new DoubleAsyncFunctionComponent("getVisibilityAsync", anyTypeArr6, function16);
                    }
                } else {
                    asyncFunctionComponent6 = new BoolAsyncFunctionComponent("getVisibilityAsync", anyTypeArr6, function16);
                }
            } else {
                asyncFunctionComponent6 = new IntAsyncFunctionComponent("getVisibilityAsync", anyTypeArr6, function16);
            }
            moduleDefinitionBuilder7.getAsyncFunctions().put("getVisibilityAsync", asyncFunctionComponent6);
            asyncFunctionComponent6.runOnQueue(Queues.MAIN);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent7 = new AsyncFunctionWithPromiseComponent("setPositionAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$9
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$10
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Activity activity;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    String str = (String) objArr[0];
                    NavigationBar navigationBar = NavigationBar.INSTANCE;
                    activity = NavigationBarModule.this.getActivity();
                    navigationBar.setPosition(activity, str, new Function0<Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$11$1
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
                            Promise.this.resolve((Object) null);
                        }
                    }, new Function1<String, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$11$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                            invoke2(str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String m) {
                            Intrinsics.checkNotNullParameter(m, "m");
                            Promise.this.reject(new NavigationBarException(m));
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("setPositionAsync", asyncFunctionWithPromiseComponent7);
            asyncFunctionWithPromiseComponent7.runOnQueue(Queues.MAIN);
            ModuleDefinitionBuilder moduleDefinitionBuilder8 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr7 = new AnyType[0];
            Function1<Object[], String> function17 = new Function1<Object[], String>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$5
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    Activity activity;
                    Intrinsics.checkNotNullParameter(it, "it");
                    activity = NavigationBarModule.this.getActivity();
                    return ViewCompat.getFitsSystemWindows(activity.getWindow().getDecorView()) ? "relative" : "absolute";
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent7 = new StringAsyncFunctionComponent("unstable_getPositionAsync", anyTypeArr7, function17);
                            } else {
                                asyncFunctionComponent7 = new AsyncFunctionComponent("unstable_getPositionAsync", anyTypeArr7, function17);
                            }
                        } else {
                            asyncFunctionComponent7 = new FloatAsyncFunctionComponent("unstable_getPositionAsync", anyTypeArr7, function17);
                        }
                    } else {
                        asyncFunctionComponent7 = new DoubleAsyncFunctionComponent("unstable_getPositionAsync", anyTypeArr7, function17);
                    }
                } else {
                    asyncFunctionComponent7 = new BoolAsyncFunctionComponent("unstable_getPositionAsync", anyTypeArr7, function17);
                }
            } else {
                asyncFunctionComponent7 = new IntAsyncFunctionComponent("unstable_getPositionAsync", anyTypeArr7, function17);
            }
            moduleDefinitionBuilder8.getAsyncFunctions().put("unstable_getPositionAsync", asyncFunctionComponent7);
            asyncFunctionComponent7.runOnQueue(Queues.MAIN);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent8 = new AsyncFunctionWithPromiseComponent("setBehaviorAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, new Function0<KType>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$11
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.typeOf(String.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithPromise$12
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, final Promise promise) {
                    Activity activity;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    String str = (String) objArr[0];
                    NavigationBar navigationBar = NavigationBar.INSTANCE;
                    activity = NavigationBarModule.this.getActivity();
                    navigationBar.setBehavior(activity, str, new Function0<Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$13$1
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
                            Promise.this.resolve((Object) null);
                        }
                    }, new Function1<String, Unit>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$1$13$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                            invoke2(str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(String m) {
                            Intrinsics.checkNotNullParameter(m, "m");
                            Promise.this.reject(new NavigationBarException(m));
                        }
                    });
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("setBehaviorAsync", asyncFunctionWithPromiseComponent8);
            asyncFunctionWithPromiseComponent8.runOnQueue(Queues.MAIN);
            ModuleDefinitionBuilder moduleDefinitionBuilder9 = moduleDefinitionBuilder;
            AnyType[] anyTypeArr8 = new AnyType[0];
            Function1<Object[], String> function18 = new Function1<Object[], String>() { // from class: expo.modules.navigationbar.NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$6
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final String invoke(Object[] it) {
                    Activity activity;
                    Activity activity2;
                    Intrinsics.checkNotNullParameter(it, "it");
                    activity = NavigationBarModule.this.getActivity();
                    Window window = activity.getWindow();
                    activity2 = NavigationBarModule.this.getActivity();
                    int systemBarsBehavior = new WindowInsetsControllerCompat(window, activity2.getWindow().getDecorView()).getSystemBarsBehavior();
                    return systemBarsBehavior != 1 ? systemBarsBehavior != 2 ? "inset-touch" : "overlay-swipe" : "inset-swipe";
                }
            };
            if (!Intrinsics.areEqual(String.class, Integer.TYPE)) {
                if (!Intrinsics.areEqual(String.class, Boolean.TYPE)) {
                    if (!Intrinsics.areEqual(String.class, Double.TYPE)) {
                        if (!Intrinsics.areEqual(String.class, Float.TYPE)) {
                            if (Intrinsics.areEqual(String.class, String.class)) {
                                asyncFunctionComponent8 = new StringAsyncFunctionComponent("getBehaviorAsync", anyTypeArr8, function18);
                            } else {
                                asyncFunctionComponent8 = new AsyncFunctionComponent("getBehaviorAsync", anyTypeArr8, function18);
                            }
                        } else {
                            asyncFunctionComponent8 = new FloatAsyncFunctionComponent("getBehaviorAsync", anyTypeArr8, function18);
                        }
                    } else {
                        asyncFunctionComponent8 = new DoubleAsyncFunctionComponent("getBehaviorAsync", anyTypeArr8, function18);
                    }
                } else {
                    asyncFunctionComponent8 = new BoolAsyncFunctionComponent("getBehaviorAsync", anyTypeArr8, function18);
                }
            } else {
                asyncFunctionComponent8 = new IntAsyncFunctionComponent("getBehaviorAsync", anyTypeArr8, function18);
            }
            moduleDefinitionBuilder9.getAsyncFunctions().put("getBehaviorAsync", asyncFunctionComponent8);
            asyncFunctionComponent8.runOnQueue(Queues.MAIN);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* compiled from: NavigationBarModule.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lexpo/modules/navigationbar/NavigationBarModule$Companion;", "", "()V", "VISIBILITY_EVENT_NAME", "", "colorToHex", ViewProps.COLOR, "", "expo-navigation-bar_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String colorToHex(int color) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("#%02x%02x%02x", Arrays.copyOf(new Object[]{Integer.valueOf(Color.red(color)), Integer.valueOf(Color.green(color)), Integer.valueOf(Color.blue(color))}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        }
    }
}

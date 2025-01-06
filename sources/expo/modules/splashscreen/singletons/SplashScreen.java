package expo.modules.splashscreen.singletons;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.core.interfaces.SingletonModule;
import expo.modules.splashscreen.NativeResourcesBasedSplashScreenViewProvider;
import expo.modules.splashscreen.SplashScreenImageResizeMode;
import expo.modules.splashscreen.SplashScreenViewController;
import expo.modules.splashscreen.SplashScreenViewProvider;
import io.sentry.clientreport.DiscardedEvent;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreen.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J5\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0007JT\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\n0\u00172!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\n0\u0017J\u0010\u0010\u001d\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0007JT\u0010\u001d\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\n0\u00172!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\n0\u0017Jo\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u001f\u001a\u00020 2\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0!2#\b\u0002\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\n0\u0017H\u0007JU\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0!2#\b\u0002\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\n0\u0017H\u0007Je\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 2\u000e\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0!2#\b\u0002\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\n0\u0017H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lexpo/modules/splashscreen/singletons/SplashScreen;", "Lexpo/modules/core/interfaces/SingletonModule;", "()V", "TAG", "", "controllers", "Ljava/util/WeakHashMap;", "Landroid/app/Activity;", "Lexpo/modules/splashscreen/SplashScreenViewController;", "ensureShown", "", "activity", ViewProps.RESIZE_MODE, "Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "rootViewClass", "Ljava/lang/Class;", "Landroid/view/ViewGroup;", "statusBarTranslucent", "", "ensureShown$expo_splash_screen_release", "getName", "hide", "successCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "hasEffect", "failureCallback", DiscardedEvent.JsonKeys.REASON, "preventAutoHide", "show", "splashScreenViewProvider", "Lexpo/modules/splashscreen/SplashScreenViewProvider;", "Lkotlin/Function0;", "splashScreenViewController", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SplashScreen implements SingletonModule {
    private static final String TAG = "SplashScreen";
    public static final SplashScreen INSTANCE = new SplashScreen();
    private static final WeakHashMap<Activity, SplashScreenViewController> controllers = new WeakHashMap<>();

    @JvmStatic
    public static final void show(Activity activity, SplashScreenImageResizeMode resizeMode, Class<? extends ViewGroup> rootViewClass, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(resizeMode, "resizeMode");
        Intrinsics.checkNotNullParameter(rootViewClass, "rootViewClass");
        show$default(activity, resizeMode, rootViewClass, z, null, null, null, 112, null);
    }

    @JvmStatic
    public static final void show(Activity activity, SplashScreenImageResizeMode resizeMode, Class<? extends ViewGroup> rootViewClass, boolean z, SplashScreenViewProvider splashScreenViewProvider) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(resizeMode, "resizeMode");
        Intrinsics.checkNotNullParameter(rootViewClass, "rootViewClass");
        Intrinsics.checkNotNullParameter(splashScreenViewProvider, "splashScreenViewProvider");
        show$default(activity, resizeMode, rootViewClass, z, splashScreenViewProvider, null, null, 96, null);
    }

    @JvmStatic
    public static final void show(Activity activity, SplashScreenImageResizeMode resizeMode, Class<? extends ViewGroup> rootViewClass, boolean z, SplashScreenViewProvider splashScreenViewProvider, Function0<Unit> successCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(resizeMode, "resizeMode");
        Intrinsics.checkNotNullParameter(rootViewClass, "rootViewClass");
        Intrinsics.checkNotNullParameter(splashScreenViewProvider, "splashScreenViewProvider");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        show$default(activity, resizeMode, rootViewClass, z, splashScreenViewProvider, successCallback, null, 64, null);
    }

    @JvmStatic
    public static final void show(Activity activity, SplashScreenViewController splashScreenViewController, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(splashScreenViewController, "splashScreenViewController");
        show$default(activity, splashScreenViewController, z, null, null, 24, null);
    }

    @JvmStatic
    public static final void show(Activity activity, SplashScreenViewController splashScreenViewController, boolean z, Function0<Unit> successCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(splashScreenViewController, "splashScreenViewController");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        show$default(activity, splashScreenViewController, z, successCallback, null, 16, null);
    }

    @JvmStatic
    public static final void show(Activity activity, SplashScreenViewProvider splashScreenViewProvider, Class<? extends ViewGroup> rootViewClass, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(splashScreenViewProvider, "splashScreenViewProvider");
        Intrinsics.checkNotNullParameter(rootViewClass, "rootViewClass");
        show$default(activity, splashScreenViewProvider, rootViewClass, z, null, null, 48, null);
    }

    @JvmStatic
    public static final void show(Activity activity, SplashScreenViewProvider splashScreenViewProvider, Class<? extends ViewGroup> rootViewClass, boolean z, Function0<Unit> successCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(splashScreenViewProvider, "splashScreenViewProvider");
        Intrinsics.checkNotNullParameter(rootViewClass, "rootViewClass");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        show$default(activity, splashScreenViewProvider, rootViewClass, z, successCallback, null, 32, null);
    }

    @Override // expo.modules.core.interfaces.SingletonModule
    public String getName() {
        return TAG;
    }

    private SplashScreen() {
    }

    public static /* synthetic */ void show$default(Activity activity, SplashScreenViewProvider splashScreenViewProvider, Class cls, boolean z, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            function0 = new Function0<Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$show$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function02 = function0;
        if ((i & 32) != 0) {
            function1 = new Function1<String, Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$show$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Log.w("SplashScreen", it);
                }
            };
        }
        show(activity, splashScreenViewProvider, (Class<? extends ViewGroup>) cls, z, (Function0<Unit>) function02, (Function1<? super String, Unit>) function1);
    }

    @JvmStatic
    public static final void show(Activity activity, SplashScreenViewProvider splashScreenViewProvider, Class<? extends ViewGroup> rootViewClass, boolean statusBarTranslucent, Function0<Unit> successCallback, Function1<? super String, Unit> failureCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(splashScreenViewProvider, "splashScreenViewProvider");
        Intrinsics.checkNotNullParameter(rootViewClass, "rootViewClass");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(failureCallback, "failureCallback");
        SplashScreenStatusBar.INSTANCE.configureTranslucent(activity, Boolean.valueOf(statusBarTranslucent));
        show(activity, new SplashScreenViewController(activity, rootViewClass, splashScreenViewProvider.createSplashScreenView(activity)), statusBarTranslucent, successCallback, failureCallback);
    }

    public static /* synthetic */ void show$default(Activity activity, SplashScreenImageResizeMode splashScreenImageResizeMode, Class cls, boolean z, SplashScreenViewProvider splashScreenViewProvider, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            splashScreenViewProvider = new NativeResourcesBasedSplashScreenViewProvider(splashScreenImageResizeMode);
        }
        SplashScreenViewProvider splashScreenViewProvider2 = splashScreenViewProvider;
        if ((i & 32) != 0) {
            function0 = new Function0<Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$show$3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 function02 = function0;
        if ((i & 64) != 0) {
            function1 = new Function1<String, Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$show$4
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Log.w("SplashScreen", it);
                }
            };
        }
        show(activity, splashScreenImageResizeMode, cls, z, splashScreenViewProvider2, function02, function1);
    }

    @JvmStatic
    public static final void show(Activity activity, SplashScreenImageResizeMode resizeMode, Class<? extends ViewGroup> rootViewClass, boolean statusBarTranslucent, SplashScreenViewProvider splashScreenViewProvider, Function0<Unit> successCallback, Function1<? super String, Unit> failureCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(resizeMode, "resizeMode");
        Intrinsics.checkNotNullParameter(rootViewClass, "rootViewClass");
        Intrinsics.checkNotNullParameter(splashScreenViewProvider, "splashScreenViewProvider");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(failureCallback, "failureCallback");
        show(activity, splashScreenViewProvider, rootViewClass, statusBarTranslucent, successCallback, failureCallback);
    }

    public static /* synthetic */ void show$default(Activity activity, SplashScreenViewController splashScreenViewController, boolean z, Function0 function0, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function0 = new Function0<Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$show$5
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 16) != 0) {
            function1 = new Function1<String, Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$show$6
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Log.w("SplashScreen", it);
                }
            };
        }
        show(activity, splashScreenViewController, z, (Function0<Unit>) function0, (Function1<? super String, Unit>) function1);
    }

    @JvmStatic
    public static final void show(Activity activity, SplashScreenViewController splashScreenViewController, boolean statusBarTranslucent, Function0<Unit> successCallback, Function1<? super String, Unit> failureCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(splashScreenViewController, "splashScreenViewController");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(failureCallback, "failureCallback");
        WeakHashMap<Activity, SplashScreenViewController> weakHashMap = controllers;
        if (weakHashMap.containsKey(activity)) {
            failureCallback.invoke("'SplashScreen.show' has already been called for this activity.");
            return;
        }
        SplashScreenStatusBar.INSTANCE.configureTranslucent(activity, Boolean.valueOf(statusBarTranslucent));
        weakHashMap.put(activity, splashScreenViewController);
        splashScreenViewController.showSplashScreen(successCallback);
    }

    public final void preventAutoHide(Activity activity, Function1<? super Boolean, Unit> successCallback, Function1<? super String, Unit> failureCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(failureCallback, "failureCallback");
        WeakHashMap<Activity, SplashScreenViewController> weakHashMap = controllers;
        if (!weakHashMap.containsKey(activity)) {
            failureCallback.invoke("No native splash screen registered for provided activity. Please configure your application's main Activity to call 'SplashScreen.show' (https://github.com/expo/expo/tree/main/packages/expo-splash-screen#-configure-android).");
            return;
        }
        SplashScreenViewController splashScreenViewController = weakHashMap.get(activity);
        if (splashScreenViewController != null) {
            splashScreenViewController.preventAutoHide(successCallback, failureCallback);
        }
    }

    @JvmStatic
    public static final void preventAutoHide(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        INSTANCE.preventAutoHide(activity, new Function1<Boolean, Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$preventAutoHide$1
            public final void invoke(boolean z) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }
        }, new Function1<String, Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$preventAutoHide$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }
        });
    }

    public final void hide(Activity activity, Function1<? super Boolean, Unit> successCallback, Function1<? super String, Unit> failureCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(failureCallback, "failureCallback");
        WeakHashMap<Activity, SplashScreenViewController> weakHashMap = controllers;
        if (!weakHashMap.containsKey(activity)) {
            failureCallback.invoke("No native splash screen registered for provided activity. Please configure your application's main Activity to call 'SplashScreen.show' (https://github.com/expo/expo/tree/main/packages/expo-splash-screen#-configure-android).");
            return;
        }
        SplashScreenViewController splashScreenViewController = weakHashMap.get(activity);
        if (splashScreenViewController != null) {
            splashScreenViewController.hideSplashScreen(successCallback, failureCallback);
        }
    }

    @JvmStatic
    public static final void hide(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        INSTANCE.hide(activity, new Function1<Boolean, Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$hide$1
            public final void invoke(boolean z) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }
        }, new Function1<String, Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$hide$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }
        });
    }

    public final void ensureShown$expo_splash_screen_release(Activity activity, SplashScreenImageResizeMode resizeMode, Class<? extends ViewGroup> rootViewClass, boolean statusBarTranslucent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(resizeMode, "resizeMode");
        Intrinsics.checkNotNullParameter(rootViewClass, "rootViewClass");
        SplashScreenViewController splashScreenViewController = controllers.get(activity);
        if (splashScreenViewController == null) {
            show$default(activity, resizeMode, rootViewClass, statusBarTranslucent, null, null, null, 112, null);
        } else {
            splashScreenViewController.showSplashScreen(new Function0<Unit>() { // from class: expo.modules.splashscreen.singletons.SplashScreen$ensureShown$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            });
        }
    }
}

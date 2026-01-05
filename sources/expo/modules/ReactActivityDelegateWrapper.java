package expo.modules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactDelegate;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.modules.core.PermissionListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactActivityHandler;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.kotlin.Utils;
import expo.modules.kotlin.exception.Exceptions;
import io.sentry.protocol.App;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: ReactActivityDelegateWrapper.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\bJ\n\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\n\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020$H\u0014J\n\u0010%\u001a\u0004\u0018\u00010 H\u0014J\n\u0010&\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010'\u001a\u00020(H\u0014J\n\u0010)\u001a\u0004\u0018\u00010*H\u0014J\n\u0010+\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020\u0010H\u0014J\u001b\u0010/\u001a\u0002H0\"\u0004\b\u0000\u001002\u0006\u00101\u001a\u00020\u0016H\u0002¢\u0006\u0002\u00102JA\u0010/\u001a\u0002H0\"\u0004\b\u0000\u00100\"\u0004\b\u0001\u001032\u0006\u00101\u001a\u00020\u00162\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u000306052\f\u00107\u001a\b\u0012\u0004\u0012\u0002H305H\u0002¢\u0006\u0002\u00108J\b\u00109\u001a\u00020\u0007H\u0014J\u0012\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0016H\u0014J\"\u0010=\u001a\u00020;2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\b\u0010C\u001a\u00020\u0007H\u0016J\u0012\u0010D\u001a\u00020;2\b\u0010E\u001a\u0004\u0018\u00010FH\u0016J\u0012\u0010G\u001a\u00020;2\b\u0010H\u001a\u0004\u0018\u00010 H\u0014J\b\u0010I\u001a\u00020;H\u0014J\u001a\u0010J\u001a\u00020\u00072\u0006\u0010K\u001a\u00020?2\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\u001a\u0010N\u001a\u00020\u00072\u0006\u0010K\u001a\u00020?2\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\u001a\u0010O\u001a\u00020\u00072\u0006\u0010K\u001a\u00020?2\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\u0012\u0010P\u001a\u00020\u00072\b\u0010Q\u001a\u0004\u0018\u00010BH\u0016J\b\u0010R\u001a\u00020;H\u0014J1\u0010S\u001a\u00020;2\u0006\u0010>\u001a\u00020?2\u0010\u0010T\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0016\u0018\u0001052\b\u0010U\u001a\u0004\u0018\u00010VH\u0016¢\u0006\u0002\u0010WJ\b\u0010X\u001a\u00020;H\u0014J\u0010\u0010Y\u001a\u00020;2\u0006\u0010Z\u001a\u00020\u0007H\u0016J1\u0010[\u001a\u00020;2\u0010\u0010T\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0016\u0018\u0001052\u0006\u0010>\u001a\u00020?2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0016¢\u0006\u0002\u0010^R\u001d\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u001a0\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0010\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u001d0\u001d0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Lexpo/modules/ReactActivityDelegateWrapper;", "Lcom/facebook/react/ReactActivityDelegate;", "activity", "Lcom/facebook/react/ReactActivity;", "delegate", "(Lcom/facebook/react/ReactActivity;Lcom/facebook/react/ReactActivityDelegate;)V", "isNewArchitectureEnabled", "", "(Lcom/facebook/react/ReactActivity;ZLcom/facebook/react/ReactActivityDelegate;)V", "_reactHost", "Lcom/facebook/react/ReactHost;", "get_reactHost", "()Lcom/facebook/react/ReactHost;", "_reactHost$delegate", "Lkotlin/Lazy;", "_reactNativeHost", "Lcom/facebook/react/ReactNativeHost;", "get_reactNativeHost", "()Lcom/facebook/react/ReactNativeHost;", "_reactNativeHost$delegate", "methodMap", "Landroidx/collection/ArrayMap;", "", "Ljava/lang/reflect/Method;", "reactActivityHandlers", "", "Lexpo/modules/core/interfaces/ReactActivityHandler;", "kotlin.jvm.PlatformType", "reactActivityLifecycleListeners", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "shouldEmitPendingResume", "composeLaunchOptions", "Landroid/os/Bundle;", "createRootView", "Lcom/facebook/react/ReactRootView;", "getContext", "Landroid/content/Context;", "getLaunchOptions", "getMainComponentName", "getPlainActivity", "Landroid/app/Activity;", "getReactDelegate", "Lcom/facebook/react/ReactDelegate;", "getReactHost", "getReactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "getReactNativeHost", "invokeDelegateMethod", ExifInterface.GPS_DIRECTION_TRUE, "name", "(Ljava/lang/String;)Ljava/lang/Object;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "argTypes", "", "Ljava/lang/Class;", "args", "(Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;", "isFabricEnabled", "loadApp", "", "appKey", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "onDestroy", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onKeyLongPress", "onKeyUp", "onNewIntent", "intent", "onPause", "onRequestPermissionsResult", App.JsonKeys.APP_PERMISSIONS, "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onWindowFocusChanged", "hasFocus", "requestPermissions", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/facebook/react/modules/core/PermissionListener;", "([Ljava/lang/String;ILcom/facebook/react/modules/core/PermissionListener;)V", "expo_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactActivityDelegateWrapper extends ReactActivityDelegate {

    /* renamed from: _reactHost$delegate, reason: from kotlin metadata */
    private final Lazy _reactHost;

    /* renamed from: _reactNativeHost$delegate, reason: from kotlin metadata */
    private final Lazy _reactNativeHost;
    private final ReactActivity activity;
    private ReactActivityDelegate delegate;
    private final boolean isNewArchitectureEnabled;
    private final ArrayMap<String, Method> methodMap;
    private final List<ReactActivityHandler> reactActivityHandlers;
    private final List<ReactActivityLifecycleListener> reactActivityLifecycleListeners;
    private boolean shouldEmitPendingResume;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper(ReactActivity activity, boolean z, ReactActivityDelegate delegate) {
        super(activity, (String) null);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.activity = activity;
        this.isNewArchitectureEnabled = z;
        this.delegate = delegate;
        List<Package> packageList = ExpoModulesPackage.INSTANCE.getPackageList();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = packageList.iterator();
        while (it.hasNext()) {
            List<? extends ReactActivityLifecycleListener> createReactActivityLifecycleListeners = ((Package) it.next()).createReactActivityLifecycleListeners(this.activity);
            Intrinsics.checkNotNullExpressionValue(createReactActivityLifecycleListeners, "createReactActivityLifecycleListeners(...)");
            CollectionsKt.addAll(arrayList, createReactActivityLifecycleListeners);
        }
        this.reactActivityLifecycleListeners = arrayList;
        List<Package> packageList2 = ExpoModulesPackage.INSTANCE.getPackageList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = packageList2.iterator();
        while (it2.hasNext()) {
            List<? extends ReactActivityHandler> createReactActivityHandlers = ((Package) it2.next()).createReactActivityHandlers(this.activity);
            Intrinsics.checkNotNullExpressionValue(createReactActivityHandlers, "createReactActivityHandlers(...)");
            CollectionsKt.addAll(arrayList2, createReactActivityHandlers);
        }
        this.reactActivityHandlers = arrayList2;
        this.methodMap = new ArrayMap<>();
        this._reactNativeHost = LazyKt.lazy(new Function0<ReactNativeHost>() { // from class: expo.modules.ReactActivityDelegateWrapper$_reactNativeHost$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ReactNativeHost invoke() {
                Object invokeDelegateMethod;
                invokeDelegateMethod = ReactActivityDelegateWrapper.this.invokeDelegateMethod("getReactNativeHost");
                return (ReactNativeHost) invokeDelegateMethod;
            }
        });
        this._reactHost = LazyKt.lazy(new Function0<ReactHost>() { // from class: expo.modules.ReactActivityDelegateWrapper$_reactHost$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ReactHost invoke() {
                ReactActivityDelegate reactActivityDelegate;
                reactActivityDelegate = ReactActivityDelegateWrapper.this.delegate;
                return reactActivityDelegate.getReactHost();
            }
        });
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper(ReactActivity activity, ReactActivityDelegate delegate) {
        this(activity, false, delegate);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
    }

    private final ReactNativeHost get_reactNativeHost() {
        return (ReactNativeHost) this._reactNativeHost.getValue();
    }

    private final ReactHost get_reactHost() {
        return (ReactHost) this._reactHost.getValue();
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected Bundle getLaunchOptions() {
        return (Bundle) invokeDelegateMethod("getLaunchOptions");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected ReactRootView createRootView() {
        return (ReactRootView) invokeDelegateMethod("createRootView");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected ReactDelegate getReactDelegate() {
        return (ReactDelegate) invokeDelegateMethod("getReactDelegate");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected ReactNativeHost getReactNativeHost() {
        return get_reactNativeHost();
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public ReactHost getReactHost() {
        return get_reactHost();
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public ReactInstanceManager getReactInstanceManager() {
        ReactInstanceManager reactInstanceManager = this.delegate.getReactInstanceManager();
        Intrinsics.checkNotNullExpressionValue(reactInstanceManager, "getReactInstanceManager(...)");
        return reactInstanceManager;
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public String getMainComponentName() {
        return this.delegate.getMainComponentName();
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected void loadApp(final String appKey) {
        ViewGroup viewGroup = (ViewGroup) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new Function1<ReactActivityHandler, ViewGroup>() { // from class: expo.modules.ReactActivityDelegateWrapper$loadApp$rootViewContainer$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ViewGroup invoke(ReactActivityHandler reactActivityHandler) {
                ReactActivity reactActivity;
                reactActivity = ReactActivityDelegateWrapper.this.activity;
                return reactActivityHandler.createReactRootViewContainer(reactActivity);
            }
        }));
        if (viewGroup != null) {
            Field declaredField = ReactActivityDelegate.class.getDeclaredField("mReactDelegate");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this.delegate);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.react.ReactDelegate");
            ReactDelegate reactDelegate = (ReactDelegate) obj;
            reactDelegate.loadApp(appKey);
            ReactRootView reactRootView = reactDelegate.getReactRootView();
            ViewParent parent = reactRootView != null ? reactRootView.getParent() : null;
            ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup2 != null) {
                viewGroup2.removeView(reactRootView);
            }
            viewGroup.addView(reactRootView, -1);
            this.activity.setContentView(viewGroup);
            Iterator<T> it = this.reactActivityLifecycleListeners.iterator();
            while (it.hasNext()) {
                ((ReactActivityLifecycleListener) it.next()).onContentChanged(this.activity);
            }
            return;
        }
        ReactActivityHandler.DelayLoadAppHandler delayLoadAppHandler = (ReactActivityHandler.DelayLoadAppHandler) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new Function1<ReactActivityHandler, ReactActivityHandler.DelayLoadAppHandler>() { // from class: expo.modules.ReactActivityDelegateWrapper$loadApp$delayLoadAppHandler$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ReactActivityHandler.DelayLoadAppHandler invoke(ReactActivityHandler reactActivityHandler) {
                ReactActivity reactActivity;
                reactActivity = ReactActivityDelegateWrapper.this.activity;
                return reactActivityHandler.getDelayLoadAppHandler(reactActivity, ReactActivityDelegateWrapper.this.getReactNativeHost());
            }
        }));
        if (delayLoadAppHandler != null) {
            this.shouldEmitPendingResume = true;
            delayLoadAppHandler.whenReady(new Runnable() { // from class: expo.modules.ReactActivityDelegateWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ReactActivityDelegateWrapper.loadApp$lambda$4(ReactActivityDelegateWrapper.this, appKey);
                }
            });
        } else {
            invokeDelegateMethod("loadApp", new Class[]{String.class}, new String[]{appKey});
            Iterator<T> it2 = this.reactActivityLifecycleListeners.iterator();
            while (it2.hasNext()) {
                ((ReactActivityLifecycleListener) it2.next()).onContentChanged(this.activity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadApp$lambda$4(ReactActivityDelegateWrapper this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Utils utils = Utils.INSTANCE;
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            String name = Thread.currentThread().getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            String name2 = Looper.getMainLooper().getThread().getName();
            Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
            throw new Exceptions.IncorrectThreadException(name, name2);
        }
        this$0.invokeDelegateMethod("loadApp", new Class[]{String.class}, new String[]{str});
        Iterator<T> it = this$0.reactActivityLifecycleListeners.iterator();
        while (it.hasNext()) {
            ((ReactActivityLifecycleListener) it.next()).onContentChanged(this$0.activity);
        }
        this$0.shouldEmitPendingResume = false;
        this$0.onResume();
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected void onCreate(Bundle savedInstanceState) {
        ReactDelegate reactDelegate;
        ReactActivityDelegate reactActivityDelegate = (ReactActivityDelegate) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new Function1<ReactActivityHandler, ReactActivityDelegate>() { // from class: expo.modules.ReactActivityDelegateWrapper$onCreate$newDelegate$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ReactActivityDelegate invoke(ReactActivityHandler reactActivityHandler) {
                ReactActivity reactActivity;
                reactActivity = ReactActivityDelegateWrapper.this.activity;
                return reactActivityHandler.onDidCreateReactActivityDelegate(reactActivity, ReactActivityDelegateWrapper.this);
            }
        }));
        if (reactActivityDelegate != null && !Intrinsics.areEqual(reactActivityDelegate, this)) {
            Field declaredField = ReactActivity.class.getDeclaredField("mDelegate");
            declaredField.setAccessible(true);
            Field declaredField2 = Field.class.getDeclaredField("accessFlags");
            declaredField2.setAccessible(true);
            declaredField2.setInt(declaredField, declaredField.getModifiers() & (-17));
            declaredField.set(this.activity, reactActivityDelegate);
            this.delegate = reactActivityDelegate;
            invokeDelegateMethod("onCreate", new Class[]{Bundle.class}, new Bundle[]{savedInstanceState});
        } else {
            final Bundle composeLaunchOptions = composeLaunchOptions();
            if (ReactFeatureFlags.enableBridgelessArchitecture) {
                reactDelegate = new ReactDelegate(getPlainActivity(), getReactHost(), getMainComponentName(), composeLaunchOptions);
            } else {
                final Activity plainActivity = getPlainActivity();
                final ReactNativeHost reactNativeHost = getReactNativeHost();
                final String mainComponentName = getMainComponentName();
                reactDelegate = new ReactDelegate(composeLaunchOptions, plainActivity, reactNativeHost, mainComponentName) { // from class: expo.modules.ReactActivityDelegateWrapper$onCreate$1
                    @Override // com.facebook.react.ReactDelegate
                    protected ReactRootView createRootView() {
                        ReactRootView createRootView = this.createRootView();
                        if (createRootView != null) {
                            return createRootView;
                        }
                        ReactRootView createRootView2 = super.createRootView();
                        Intrinsics.checkNotNullExpressionValue(createRootView2, "createRootView(...)");
                        return createRootView2;
                    }
                };
            }
            Field declaredField3 = ReactActivityDelegate.class.getDeclaredField("mReactDelegate");
            declaredField3.setAccessible(true);
            declaredField3.set(this.delegate, reactDelegate);
            if (getMainComponentName() != null) {
                loadApp(getMainComponentName());
            }
        }
        Iterator<T> it = this.reactActivityLifecycleListeners.iterator();
        while (it.hasNext()) {
            ((ReactActivityLifecycleListener) it.next()).onCreate(this.activity, savedInstanceState);
        }
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected void onResume() {
        if (this.shouldEmitPendingResume) {
            return;
        }
        invokeDelegateMethod("onResume");
        Iterator<T> it = this.reactActivityLifecycleListeners.iterator();
        while (it.hasNext()) {
            ((ReactActivityLifecycleListener) it.next()).onResume(this.activity);
        }
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected void onPause() {
        if (this.shouldEmitPendingResume) {
            this.shouldEmitPendingResume = false;
        }
        Iterator<T> it = this.reactActivityLifecycleListeners.iterator();
        while (it.hasNext()) {
            ((ReactActivityLifecycleListener) it.next()).onPause(this.activity);
        }
        invokeDelegateMethod("onPause");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected void onDestroy() {
        if (this.shouldEmitPendingResume) {
            this.shouldEmitPendingResume = false;
        }
        Iterator<T> it = this.reactActivityLifecycleListeners.iterator();
        while (it.hasNext()) {
            ((ReactActivityLifecycleListener) it.next()).onDestroy(this.activity);
        }
        invokeDelegateMethod("onDestroy");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (!ReactFeatureFlags.enableBridgelessArchitecture && this.delegate.getReactInstanceManager().getCurrentReactContext() == null) {
            this.delegate.getReactInstanceManager().addReactInstanceEventListener(new ReactInstanceEventListener() { // from class: expo.modules.ReactActivityDelegateWrapper$onActivityResult$reactContextListener$1
                @Override // com.facebook.react.ReactInstanceEventListener
                public void onReactContextInitialized(ReactContext context) {
                    ReactActivityDelegate reactActivityDelegate;
                    ReactActivityDelegate reactActivityDelegate2;
                    Intrinsics.checkNotNullParameter(context, "context");
                    reactActivityDelegate = ReactActivityDelegateWrapper.this.delegate;
                    reactActivityDelegate.getReactInstanceManager().removeReactInstanceEventListener(this);
                    reactActivityDelegate2 = ReactActivityDelegateWrapper.this.delegate;
                    reactActivityDelegate2.onActivityResult(requestCode, resultCode, data);
                }
            });
        } else {
            this.delegate.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean z;
        List<ReactActivityHandler> list = this.reactActivityHandlers;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((ReactActivityHandler) it.next()).onKeyDown(keyCode, event)));
        }
        Iterator it2 = arrayList.iterator();
        loop1: while (true) {
            z = false;
            while (it2.hasNext()) {
                boolean booleanValue = ((Boolean) it2.next()).booleanValue();
                if (z || booleanValue) {
                    z = true;
                }
            }
        }
        return z || this.delegate.onKeyDown(keyCode, event);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean z;
        List<ReactActivityHandler> list = this.reactActivityHandlers;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((ReactActivityHandler) it.next()).onKeyUp(keyCode, event)));
        }
        Iterator it2 = arrayList.iterator();
        loop1: while (true) {
            z = false;
            while (it2.hasNext()) {
                boolean booleanValue = ((Boolean) it2.next()).booleanValue();
                if (z || booleanValue) {
                    z = true;
                }
            }
        }
        return z || this.delegate.onKeyUp(keyCode, event);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        boolean z;
        List<ReactActivityHandler> list = this.reactActivityHandlers;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((ReactActivityHandler) it.next()).onKeyLongPress(keyCode, event)));
        }
        Iterator it2 = arrayList.iterator();
        loop1: while (true) {
            z = false;
            while (it2.hasNext()) {
                boolean booleanValue = ((Boolean) it2.next()).booleanValue();
                if (z || booleanValue) {
                    z = true;
                }
            }
        }
        return z || this.delegate.onKeyLongPress(keyCode, event);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onBackPressed() {
        boolean z;
        List<ReactActivityLifecycleListener> list = this.reactActivityLifecycleListeners;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((ReactActivityLifecycleListener) it.next()).onBackPressed()));
        }
        Iterator it2 = arrayList.iterator();
        loop1: while (true) {
            z = false;
            while (it2.hasNext()) {
                boolean booleanValue = ((Boolean) it2.next()).booleanValue();
                if (z || booleanValue) {
                    z = true;
                }
            }
        }
        return z || this.delegate.onBackPressed();
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public boolean onNewIntent(Intent intent) {
        boolean z;
        List<ReactActivityLifecycleListener> list = this.reactActivityLifecycleListeners;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((ReactActivityLifecycleListener) it.next()).onNewIntent(intent)));
        }
        Iterator it2 = arrayList.iterator();
        loop1: while (true) {
            z = false;
            while (it2.hasNext()) {
                boolean booleanValue = ((Boolean) it2.next()).booleanValue();
                if (z || booleanValue) {
                    z = true;
                }
            }
        }
        return z || this.delegate.onNewIntent(intent);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public void onWindowFocusChanged(boolean hasFocus) {
        this.delegate.onWindowFocusChanged(hasFocus);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        this.delegate.requestPermissions(permissions, requestCode, listener);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.delegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected Context getContext() {
        return (Context) invokeDelegateMethod("getContext");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected Activity getPlainActivity() {
        return (Activity) invokeDelegateMethod("getPlainActivity");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    /* renamed from: isFabricEnabled */
    protected boolean getFabricEnabled() {
        return ((Boolean) invokeDelegateMethod("isFabricEnabled")).booleanValue();
    }

    @Override // com.facebook.react.ReactActivityDelegate
    protected Bundle composeLaunchOptions() {
        return (Bundle) invokeDelegateMethod("composeLaunchOptions");
    }

    @Override // com.facebook.react.ReactActivityDelegate
    public void onConfigurationChanged(Configuration newConfig) {
        this.delegate.onConfigurationChanged(newConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> T invokeDelegateMethod(String name) {
        Method method = this.methodMap.get(name);
        if (method == null) {
            method = ReactActivityDelegate.class.getDeclaredMethod(name, new Class[0]);
            method.setAccessible(true);
            this.methodMap.put(name, method);
        }
        Intrinsics.checkNotNull(method);
        return (T) method.invoke(this.delegate, new Object[0]);
    }

    private final <T, A> T invokeDelegateMethod(String name, Class<?>[] argTypes, A[] args) {
        Method method = this.methodMap.get(name);
        if (method == null) {
            method = ReactActivityDelegate.class.getDeclaredMethod(name, (Class[]) Arrays.copyOf(argTypes, argTypes.length));
            method.setAccessible(true);
            this.methodMap.put(name, method);
        }
        Intrinsics.checkNotNull(method);
        return (T) method.invoke(this.delegate, Arrays.copyOf(args, args.length));
    }
}

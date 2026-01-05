package expo.modules.notifications.permissions;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.os.BundleKt;
import androidx.tracing.Trace;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.interfaces.permissions.PermissionsResponseListener;
import expo.modules.interfaces.permissions.PermissionsStatus;
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
import expo.modules.notifications.ModuleNotFoundException;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.service.NotificationsService;
import io.sentry.protocol.App;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

/* compiled from: NotificationPermissionsModule.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0003J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0003R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lexpo/modules/notifications/permissions/NotificationPermissionsModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", App.JsonKeys.APP_PERMISSIONS, "Lexpo/modules/interfaces/permissions/Permissions;", "getPermissions", "()Lexpo/modules/interfaces/permissions/Permissions;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getPermissionsWithPromiseImplApi33", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "getPermissionsWithPromiseImplClassic", "requestPermissionsWithPromiseImplApi33", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NotificationPermissionsModule extends Module {
    private final Permissions getPermissions() {
        Permissions permissions = getAppContext().getPermissions();
        if (permissions != null) {
            return permissions;
        }
        throw new ModuleNotFoundException(Reflection.getOrCreateKotlinClass(Permissions.class));
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
        NotificationPermissionsModule notificationPermissionsModule = this;
        Trace.beginSection("[ExpoModulesCore] " + (notificationPermissionsModule.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(notificationPermissionsModule);
            moduleDefinitionBuilder.Name("ExpoNotificationPermissionsModule");
            ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Intrinsics.areEqual(Promise.class, Promise.class)) {
                asyncFunctionWithPromiseComponent = new AsyncFunctionWithPromiseComponent("getPermissionsAsync", new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.permissions.NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$1
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
                        Context context;
                        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(promise, "promise");
                        context = NotificationPermissionsModule.this.getContext();
                        if (context.getApplicationContext().getApplicationInfo().targetSdkVersion < 33 || Build.VERSION.SDK_INT < 33) {
                            NotificationPermissionsModule.this.getPermissionsWithPromiseImplClassic(promise);
                        } else {
                            NotificationPermissionsModule.this.getPermissionsWithPromiseImplApi33(promise);
                        }
                    }
                });
            } else {
                AnyType[] anyTypeArr = {new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, new Function0<KType>() { // from class: expo.modules.notifications.permissions.NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$2
                    @Override // kotlin.jvm.functions.Function0
                    public final KType invoke() {
                        return Reflection.typeOf(Promise.class);
                    }
                }))};
                Function1<Object[], Unit> function1 = new Function1<Object[], Unit>() { // from class: expo.modules.notifications.permissions.NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Object[] objArr) {
                        Context context;
                        Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                        Promise promise = (Promise) objArr[0];
                        context = NotificationPermissionsModule.this.getContext();
                        if (context.getApplicationContext().getApplicationInfo().targetSdkVersion < 33 || Build.VERSION.SDK_INT < 33) {
                            NotificationPermissionsModule.this.getPermissionsWithPromiseImplClassic(promise);
                        } else {
                            NotificationPermissionsModule.this.getPermissionsWithPromiseImplApi33(promise);
                        }
                        return Unit.INSTANCE;
                    }
                };
                if (!Intrinsics.areEqual(Unit.class, Integer.TYPE)) {
                    if (!Intrinsics.areEqual(Unit.class, Boolean.TYPE)) {
                        if (!Intrinsics.areEqual(Unit.class, Double.TYPE)) {
                            if (!Intrinsics.areEqual(Unit.class, Float.TYPE)) {
                                if (Intrinsics.areEqual(Unit.class, String.class)) {
                                    asyncFunctionComponent = new StringAsyncFunctionComponent("getPermissionsAsync", anyTypeArr, function1);
                                } else {
                                    asyncFunctionComponent = new AsyncFunctionComponent("getPermissionsAsync", anyTypeArr, function1);
                                }
                            } else {
                                asyncFunctionComponent = new FloatAsyncFunctionComponent("getPermissionsAsync", anyTypeArr, function1);
                            }
                        } else {
                            asyncFunctionComponent = new DoubleAsyncFunctionComponent("getPermissionsAsync", anyTypeArr, function1);
                        }
                    } else {
                        asyncFunctionComponent = new BoolAsyncFunctionComponent("getPermissionsAsync", anyTypeArr, function1);
                    }
                } else {
                    asyncFunctionComponent = new IntAsyncFunctionComponent("getPermissionsAsync", anyTypeArr, function1);
                }
                asyncFunctionWithPromiseComponent = asyncFunctionComponent;
            }
            moduleDefinitionBuilder2.getAsyncFunctions().put("getPermissionsAsync", asyncFunctionWithPromiseComponent);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent2 = new AsyncFunctionWithPromiseComponent("requestPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true, new Function0<KType>() { // from class: expo.modules.notifications.permissions.NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunctionWithPromise$1
                @Override // kotlin.jvm.functions.Function0
                public final KType invoke() {
                    return Reflection.nullableTypeOf(ReadableArguments.class);
                }
            }))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.notifications.permissions.NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunctionWithPromise$2
                {
                    super(2);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] objArr, Promise promise) {
                    Context context;
                    Intrinsics.checkNotNullParameter(objArr, "<name for destructuring parameter 0>");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    context = NotificationPermissionsModule.this.getContext();
                    if (context.getApplicationContext().getApplicationInfo().targetSdkVersion < 33 || Build.VERSION.SDK_INT < 33) {
                        NotificationPermissionsModule.this.getPermissionsWithPromiseImplClassic(promise);
                    } else {
                        NotificationPermissionsModule.this.requestPermissionsWithPromiseImplApi33(promise);
                    }
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
            moduleDefinitionBuilder.getAsyncFunctions().put("requestPermissionsAsync", asyncFunctionWithPromiseComponent2);
            AsyncFunctionWithPromiseComponent asyncFunctionWithPromiseComponent3 = asyncFunctionWithPromiseComponent2;
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getPermissionsWithPromiseImplApi33(final Promise promise) {
        String[] strArr;
        Permissions permissions = getPermissions();
        PermissionsResponseListener permissionsResponseListener = new PermissionsResponseListener() { // from class: expo.modules.notifications.permissions.NotificationPermissionsModule$$ExternalSyntheticLambda0
            @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
            public final void onResult(Map map) {
                NotificationPermissionsModule.getPermissionsWithPromiseImplApi33$lambda$7(NotificationPermissionsModule.this, promise, map);
            }
        };
        strArr = NotificationPermissionsModuleKt.PERMISSIONS;
        permissions.getPermissions(permissionsResponseListener, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getPermissionsWithPromiseImplApi33$lambda$7(NotificationPermissionsModule this$0, Promise promise, Map permissionsMap) {
        boolean z;
        boolean z2;
        boolean z3;
        String status;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(permissionsMap, "permissionsMap");
        NotificationManagerCompat from = NotificationManagerCompat.from(this$0.getContext());
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        boolean areNotificationsEnabled = from.areNotificationsEnabled();
        Bundle bundleOf = BundleKt.bundleOf(TuplesKt.to(NotificationsChannelSerializer.IMPORTANCE_KEY, Integer.valueOf(from.getImportance())));
        Object systemService = this$0.getContext().getSystemService(NotificationsService.NOTIFICATION_KEY);
        NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
        if (notificationManager != null) {
            bundleOf.putInt("interruptionFilter", notificationManager.getCurrentInterruptionFilter());
        }
        if (!permissionsMap.isEmpty()) {
            Iterator it = permissionsMap.entrySet().iterator();
            while (it.hasNext()) {
                if (((PermissionsResponse) ((Map.Entry) it.next()).getValue()).getStatus() != PermissionsStatus.GRANTED) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        if (!permissionsMap.isEmpty()) {
            Iterator it2 = permissionsMap.entrySet().iterator();
            while (it2.hasNext()) {
                if (((PermissionsResponse) ((Map.Entry) it2.next()).getValue()).getStatus() != PermissionsStatus.DENIED) {
                    z2 = false;
                    break;
                }
            }
        }
        z2 = true;
        if (!permissionsMap.isEmpty()) {
            Iterator it3 = permissionsMap.entrySet().iterator();
            while (it3.hasNext()) {
                if (!((PermissionsResponse) ((Map.Entry) it3.next()).getValue()).getCanAskAgain()) {
                    z3 = false;
                    break;
                }
            }
        }
        z3 = true;
        if (z2) {
            status = PermissionsStatus.DENIED.getStatus();
        } else if (!areNotificationsEnabled) {
            status = PermissionsStatus.DENIED.getStatus();
        } else if (z) {
            status = PermissionsStatus.GRANTED.getStatus();
        } else {
            status = PermissionsStatus.UNDETERMINED.getStatus();
        }
        promise.resolve(BundleKt.bundleOf(TuplesKt.to(PermissionsResponse.EXPIRES_KEY, "never"), TuplesKt.to("status", status), TuplesKt.to(PermissionsResponse.CAN_ASK_AGAIN_KEY, Boolean.valueOf(z3)), TuplesKt.to(PermissionsResponse.GRANTED_KEY, Boolean.valueOf(z)), TuplesKt.to("android", bundleOf)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getPermissionsWithPromiseImplClassic(Promise promise) {
        NotificationManagerCompat from = NotificationManagerCompat.from(getContext());
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        boolean areNotificationsEnabled = from.areNotificationsEnabled();
        PermissionsStatus permissionsStatus = areNotificationsEnabled ? PermissionsStatus.GRANTED : PermissionsStatus.DENIED;
        Bundle bundleOf = BundleKt.bundleOf(TuplesKt.to(NotificationsChannelSerializer.IMPORTANCE_KEY, Integer.valueOf(from.getImportance())));
        Object systemService = getContext().getSystemService(NotificationsService.NOTIFICATION_KEY);
        NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
        if (notificationManager != null) {
            bundleOf.putInt("interruptionFilter", notificationManager.getCurrentInterruptionFilter());
        }
        Pair[] pairArr = new Pair[5];
        pairArr[0] = TuplesKt.to(PermissionsResponse.EXPIRES_KEY, "never");
        pairArr[1] = TuplesKt.to("status", permissionsStatus.getStatus());
        pairArr[2] = TuplesKt.to(PermissionsResponse.CAN_ASK_AGAIN_KEY, Boolean.valueOf(areNotificationsEnabled));
        pairArr[3] = TuplesKt.to(PermissionsResponse.GRANTED_KEY, Boolean.valueOf(permissionsStatus == PermissionsStatus.GRANTED));
        pairArr[4] = TuplesKt.to("android", bundleOf);
        promise.resolve(BundleKt.bundleOf(pairArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestPermissionsWithPromiseImplApi33(final Promise promise) {
        String[] strArr;
        Permissions permissions = getPermissions();
        PermissionsResponseListener permissionsResponseListener = new PermissionsResponseListener() { // from class: expo.modules.notifications.permissions.NotificationPermissionsModule$$ExternalSyntheticLambda1
            @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
            public final void onResult(Map map) {
                NotificationPermissionsModule.requestPermissionsWithPromiseImplApi33$lambda$9(NotificationPermissionsModule.this, promise, map);
            }
        };
        strArr = NotificationPermissionsModuleKt.PERMISSIONS;
        permissions.askForPermissions(permissionsResponseListener, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionsWithPromiseImplApi33$lambda$9(NotificationPermissionsModule this$0, Promise promise, Map map) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        this$0.getPermissionsWithPromiseImplApi33(promise);
    }
}

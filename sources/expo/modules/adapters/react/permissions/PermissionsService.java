package expo.modules.adapters.react.permissions;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.interfaces.permissions.PermissionsResponseListener;
import expo.modules.interfaces.permissions.PermissionsStatus;
import io.sentry.protocol.App;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionsService.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0019\u001a\u00020\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0002\u0010\u001cJ%\u0010\u001d\u001a\u00020\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e2\u0006\u0010\u001e\u001a\u00020\fH\u0014¢\u0006\u0002\u0010\u001fJ)\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\f2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010\"J)\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010&J\b\u0010'\u001a\u00020\u001aH\u0002J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000fH\u0002J\b\u0010*\u001a\u00020+H\u0002J%\u0010,\u001a\u00020\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e2\u0006\u0010\u001e\u001a\u00020\fH\u0004¢\u0006\u0002\u0010\u001fJ\u0010\u0010-\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000fH\u0002J\u0016\u0010.\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u000201000/H\u0016J\u0010\u00102\u001a\u0002032\u0006\u0010)\u001a\u00020\u000fH\u0002J\u0010\u00104\u001a\u0002032\u0006\u0010)\u001a\u00020\u000fH\u0014J\u0018\u00105\u001a\u0002062\u0006\u0010)\u001a\u00020\u000f2\u0006\u00107\u001a\u000203H\u0002J)\u00108\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\f2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010\"J)\u00109\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010&J!\u0010:\u001a\u00020\u00182\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\"\u00020\u000fH\u0016¢\u0006\u0002\u0010;J\b\u0010<\u001a\u00020\u0018H\u0002J\u0010\u0010=\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000fH\u0002J\u0010\u0010>\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u000fH\u0016J\u0010\u0010?\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020\u001aH\u0016J\b\u0010C\u001a\u00020\u001aH\u0016J\b\u0010D\u001a\u00020\u001aH\u0016J1\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002060F2\u000e\u0010G\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e2\u0006\u0010H\u001a\u00020IH\u0002¢\u0006\u0002\u0010JR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0014\u001a\u001c\u0012\u0018\u0012\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\f0\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lexpo/modules/adapters/react/permissions/PermissionsService;", "Lexpo/modules/core/interfaces/InternalModule;", "Lexpo/modules/interfaces/permissions/Permissions;", "Lexpo/modules/core/interfaces/LifecycleEventListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mActivityProvider", "Lexpo/modules/core/interfaces/ActivityProvider;", "mAskAsyncListener", "Lexpo/modules/interfaces/permissions/PermissionsResponseListener;", "mAskAsyncRequestedPermissions", "", "", "[Ljava/lang/String;", "mAskedPermissionsCache", "Landroid/content/SharedPreferences;", "mCurrentPermissionListener", "mPendingPermissionCalls", "Ljava/util/Queue;", "Lkotlin/Pair;", "mWriteSettingsPermissionBeingAsked", "", "addToAskedPermissionsCache", "", App.JsonKeys.APP_PERMISSIONS, "([Ljava/lang/String;)V", "askForManifestPermissions", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "([Ljava/lang/String;Lexpo/modules/interfaces/permissions/PermissionsResponseListener;)V", "askForPermissions", "responseListener", "(Lexpo/modules/interfaces/permissions/PermissionsResponseListener;[Ljava/lang/String;)V", "askForPermissionsWithPromise", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "(Lexpo/modules/core/Promise;[Ljava/lang/String;)V", "askForWriteSettingsPermissionFirst", PermissionsResponse.CAN_ASK_AGAIN_KEY, "permission", "createListenerWithPendingPermissionsRequest", "Lcom/facebook/react/modules/core/PermissionListener;", "delegateRequestToActivity", "didAsk", "getExportedInterfaces", "", "Ljava/lang/Class;", "", "getManifestPermission", "", "getManifestPermissionFromContext", "getPermissionResponseFromNativeResponse", "Lexpo/modules/interfaces/permissions/PermissionsResponse;", "result", "getPermissions", "getPermissionsWithPromise", "hasGrantedPermissions", "([Ljava/lang/String;)Z", "hasWriteSettingsPermission", "isPermissionGranted", "isPermissionPresentInManifest", "onCreate", "moduleRegistry", "Lexpo/modules/core/ModuleRegistry;", "onHostDestroy", "onHostPause", "onHostResume", "parseNativeResult", "", "permissionsString", "grantResults", "", "([Ljava/lang/String;[I)Ljava/util/Map;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class PermissionsService implements InternalModule, Permissions, LifecycleEventListener {
    private final Context context;
    private ActivityProvider mActivityProvider;
    private PermissionsResponseListener mAskAsyncListener;
    private String[] mAskAsyncRequestedPermissions;
    private SharedPreferences mAskedPermissionsCache;
    private PermissionsResponseListener mCurrentPermissionListener;
    private final Queue<Pair<String[], PermissionsResponseListener>> mPendingPermissionCalls;
    private boolean mWriteSettingsPermissionBeingAsked;

    public final Context getContext() {
        return this.context;
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
    }

    public PermissionsService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mPendingPermissionCalls = new LinkedList();
    }

    private final boolean didAsk(String permission) {
        SharedPreferences sharedPreferences = this.mAskedPermissionsCache;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAskedPermissionsCache");
            sharedPreferences = null;
        }
        return sharedPreferences.getBoolean(permission, false);
    }

    private final void addToAskedPermissionsCache(String[] permissions) {
        SharedPreferences sharedPreferences = this.mAskedPermissionsCache;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAskedPermissionsCache");
            sharedPreferences = null;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (String str : permissions) {
            edit.putBoolean(str, true);
        }
        edit.apply();
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class<? extends Object>> getExportedInterfaces() {
        return CollectionsKt.listOf(Permissions.class);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        ActivityProvider activityProvider = (ActivityProvider) moduleRegistry.getModule(ActivityProvider.class);
        if (activityProvider == null) {
            throw new IllegalStateException("Couldn't find implementation for ActivityProvider.");
        }
        this.mActivityProvider = activityProvider;
        ((UIManager) moduleRegistry.getModule(UIManager.class)).registerLifecycleEventListener(this);
        SharedPreferences sharedPreferences = this.context.getApplicationContext().getSharedPreferences("expo.modules.permissions.asked", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.mAskedPermissionsCache = sharedPreferences;
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public void getPermissionsWithPromise(final Promise promise, String... permissions) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        getPermissions(new PermissionsResponseListener() { // from class: expo.modules.adapters.react.permissions.PermissionsService$$ExternalSyntheticLambda2
            @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
            public final void onResult(Map map) {
                PermissionsService.getPermissionsWithPromise$lambda$6(Promise.this, map);
            }
        }, (String[]) Arrays.copyOf(permissions, permissions.length));
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public void askForPermissionsWithPromise(final Promise promise, final String... permissions) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        askForPermissions(new PermissionsResponseListener() { // from class: expo.modules.adapters.react.permissions.PermissionsService$$ExternalSyntheticLambda1
            @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
            public final void onResult(Map map) {
                PermissionsService.askForPermissionsWithPromise$lambda$7(PermissionsService.this, promise, permissions, map);
            }
        }, (String[]) Arrays.copyOf(permissions, permissions.length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askForPermissionsWithPromise$lambda$7(PermissionsService this$0, Promise promise, String[] permissions, Map map) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        this$0.getPermissionsWithPromise(promise, (String[]) Arrays.copyOf(permissions, permissions.length));
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public void askForPermissions(final PermissionsResponseListener responseListener, String... permissions) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (permissions.length == 0) {
            responseListener.onResult(new LinkedHashMap());
            return;
        }
        if (ArraysKt.contains(permissions, "android.permission.WRITE_SETTINGS")) {
            List mutableList = ArraysKt.toMutableList(permissions);
            mutableList.remove("android.permission.WRITE_SETTINGS");
            String[] strArr = (String[]) mutableList.toArray(new String[0]);
            PermissionsResponseListener permissionsResponseListener = new PermissionsResponseListener() { // from class: expo.modules.adapters.react.permissions.PermissionsService$$ExternalSyntheticLambda3
                @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
                public final void onResult(Map map) {
                    PermissionsService.askForPermissions$lambda$10(PermissionsService.this, responseListener, map);
                }
            };
            if (hasWriteSettingsPermission()) {
                if (strArr.length == 0) {
                    permissionsResponseListener.onResult(new LinkedHashMap());
                    return;
                } else {
                    askForManifestPermissions(strArr, permissionsResponseListener);
                    return;
                }
            }
            if (this.mAskAsyncListener != null) {
                throw new IllegalStateException("Another permissions request is in progress. Await the old request and then try again.");
            }
            this.mAskAsyncListener = permissionsResponseListener;
            this.mAskAsyncRequestedPermissions = strArr;
            addToAskedPermissionsCache(new String[]{"android.permission.WRITE_SETTINGS"});
            askForWriteSettingsPermissionFirst();
            return;
        }
        askForManifestPermissions(permissions, responseListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void askForPermissions$lambda$10(PermissionsService this$0, PermissionsResponseListener responseListener, Map map) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(responseListener, "$responseListener");
        int i = this$0.hasWriteSettingsPermission() ? 0 : -1;
        Intrinsics.checkNotNull(map);
        map.put("android.permission.WRITE_SETTINGS", this$0.getPermissionResponseFromNativeResponse("android.permission.WRITE_SETTINGS", i));
        responseListener.onResult(map);
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public boolean isPermissionPresentInManifest(String permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 4096);
            if (packageInfo != null) {
                String[] requestedPermissions = packageInfo.requestedPermissions;
                Intrinsics.checkNotNullExpressionValue(requestedPermissions, "requestedPermissions");
                return ArraysKt.contains(requestedPermissions, permission);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    private final boolean isPermissionGranted(String permission) {
        return Intrinsics.areEqual(permission, "android.permission.WRITE_SETTINGS") ? hasWriteSettingsPermission() : getManifestPermission(permission) == 0;
    }

    private final int getManifestPermission(String permission) {
        Activity currentActivity;
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider != null && (currentActivity = activityProvider.getCurrentActivity()) != null && (currentActivity instanceof PermissionAwareActivity)) {
            return ContextCompat.checkSelfPermission(currentActivity, permission);
        }
        return getManifestPermissionFromContext(permission);
    }

    protected int getManifestPermissionFromContext(String permission) {
        Intrinsics.checkNotNullParameter(permission, "permission");
        return ContextCompat.checkSelfPermission(this.context, permission);
    }

    private final boolean canAskAgain(String permission) {
        Activity currentActivity;
        ActivityProvider activityProvider = this.mActivityProvider;
        if (activityProvider == null || (currentActivity = activityProvider.getCurrentActivity()) == null) {
            return false;
        }
        return ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, permission);
    }

    private final Map<String, PermissionsResponse> parseNativeResult(String[] permissionsString, int[] grantResults) {
        HashMap hashMap = new HashMap();
        for (Pair pair : ArraysKt.zip(grantResults, (Object[]) permissionsString)) {
            int intValue = ((Number) pair.component1()).intValue();
            String str = (String) pair.component2();
            hashMap.put(str, getPermissionResponseFromNativeResponse(str, intValue));
        }
        return hashMap;
    }

    private final PermissionsResponse getPermissionResponseFromNativeResponse(String permission, int result) {
        PermissionsStatus permissionsStatus;
        if (result == 0) {
            permissionsStatus = PermissionsStatus.GRANTED;
        } else {
            permissionsStatus = didAsk(permission) ? PermissionsStatus.DENIED : PermissionsStatus.UNDETERMINED;
        }
        return new PermissionsResponse(permissionsStatus, permissionsStatus == PermissionsStatus.DENIED ? canAskAgain(permission) : true);
    }

    protected void askForManifestPermissions(String[] permissions, PermissionsResponseListener listener) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(listener, "listener");
        delegateRequestToActivity(permissions, listener);
    }

    protected final void delegateRequestToActivity(String[] permissions, PermissionsResponseListener listener) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(listener, "listener");
        addToAskedPermissionsCache(permissions);
        ActivityProvider activityProvider = this.mActivityProvider;
        ComponentCallbacks2 currentActivity = activityProvider != null ? activityProvider.getCurrentActivity() : null;
        if (currentActivity instanceof PermissionAwareActivity) {
            synchronized (this) {
                if (this.mCurrentPermissionListener != null) {
                    Boolean.valueOf(this.mPendingPermissionCalls.add(TuplesKt.to(permissions, listener)));
                } else {
                    this.mCurrentPermissionListener = listener;
                    ((PermissionAwareActivity) currentActivity).requestPermissions(permissions, 13, createListenerWithPendingPermissionsRequest());
                    Unit unit = Unit.INSTANCE;
                }
            }
            return;
        }
        int length = permissions.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = -1;
        }
        listener.onResult(parseNativeResult(permissions, iArr));
    }

    private final PermissionListener createListenerWithPendingPermissionsRequest() {
        return new PermissionListener() { // from class: expo.modules.adapters.react.permissions.PermissionsService$$ExternalSyntheticLambda0
            @Override // com.facebook.react.modules.core.PermissionListener
            public final boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
                boolean createListenerWithPendingPermissionsRequest$lambda$21;
                createListenerWithPendingPermissionsRequest$lambda$21 = PermissionsService.createListenerWithPendingPermissionsRequest$lambda$21(PermissionsService.this, i, strArr, iArr);
                return createListenerWithPendingPermissionsRequest$lambda$21;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean createListenerWithPendingPermissionsRequest$lambda$21(PermissionsService this$0, int i, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 13) {
            return false;
        }
        synchronized (this$0) {
            PermissionsResponseListener permissionsResponseListener = this$0.mCurrentPermissionListener;
            if (permissionsResponseListener == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            Intrinsics.checkNotNull(strArr);
            Intrinsics.checkNotNull(iArr);
            permissionsResponseListener.onResult(this$0.parseNativeResult(strArr, iArr));
            this$0.mCurrentPermissionListener = null;
            Pair<String[], PermissionsResponseListener> poll = this$0.mPendingPermissionCalls.poll();
            if (poll != null) {
                Intrinsics.checkNotNull(poll);
                ActivityProvider activityProvider = this$0.mActivityProvider;
                Object currentActivity = activityProvider != null ? activityProvider.getCurrentActivity() : null;
                PermissionAwareActivity permissionAwareActivity = currentActivity instanceof PermissionAwareActivity ? (PermissionAwareActivity) currentActivity : null;
                if (permissionAwareActivity == null) {
                    PermissionsResponseListener second = poll.getSecond();
                    String[] first = poll.getFirst();
                    int length = poll.getFirst().length;
                    int[] iArr2 = new int[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr2[i2] = -1;
                    }
                    second.onResult(this$0.parseNativeResult(first, iArr2));
                    Iterator<T> it = this$0.mPendingPermissionCalls.iterator();
                    while (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        PermissionsResponseListener permissionsResponseListener2 = (PermissionsResponseListener) pair.getSecond();
                        String[] strArr2 = (String[]) pair.getFirst();
                        int length2 = ((Object[]) pair.getFirst()).length;
                        int[] iArr3 = new int[length2];
                        for (int i3 = 0; i3 < length2; i3++) {
                            iArr3[i3] = -1;
                        }
                        permissionsResponseListener2.onResult(this$0.parseNativeResult(strArr2, iArr3));
                    }
                    this$0.mPendingPermissionCalls.clear();
                } else {
                    this$0.mCurrentPermissionListener = poll.getSecond();
                    permissionAwareActivity.requestPermissions(poll.getFirst(), 13, this$0.createListenerWithPendingPermissionsRequest());
                    return false;
                }
            }
            return true;
        }
    }

    private final void askForWriteSettingsPermissionFirst() {
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        intent.setData(Uri.parse("package:" + this.context.getPackageName()));
        intent.addFlags(268435456);
        this.mWriteSettingsPermissionBeingAsked = true;
        this.context.startActivity(intent);
    }

    private final boolean hasWriteSettingsPermission() {
        return Settings.System.canWrite(this.context.getApplicationContext());
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        if (this.mWriteSettingsPermissionBeingAsked) {
            this.mWriteSettingsPermissionBeingAsked = false;
            PermissionsResponseListener permissionsResponseListener = this.mAskAsyncListener;
            Intrinsics.checkNotNull(permissionsResponseListener);
            String[] strArr = this.mAskAsyncRequestedPermissions;
            Intrinsics.checkNotNull(strArr);
            this.mAskAsyncListener = null;
            this.mAskAsyncRequestedPermissions = null;
            if (!(strArr.length == 0)) {
                askForManifestPermissions(strArr, permissionsResponseListener);
            } else {
                permissionsResponseListener.onResult(new LinkedHashMap());
            }
        }
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public void getPermissions(PermissionsResponseListener responseListener, String... permissions) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        ArrayList arrayList = new ArrayList(permissions.length);
        for (String str : permissions) {
            arrayList.add(Integer.valueOf(isPermissionGranted(str) ? 0 : -1));
        }
        responseListener.onResult(parseNativeResult(permissions, CollectionsKt.toIntArray(arrayList)));
    }

    @Override // expo.modules.interfaces.permissions.Permissions
    public boolean hasGrantedPermissions(String... permissions) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        for (String str : permissions) {
            if (!isPermissionGranted(str)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void getPermissionsWithPromise$lambda$6(expo.modules.core.Promise r6, java.util.Map r7) {
        /*
            java.lang.String r0 = "$promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "permissionsMap"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            boolean r0 = r7.isEmpty()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L14
        L12:
            r0 = r2
            goto L38
        L14:
            java.util.Set r0 = r7.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L1c:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L12
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r3 = r3.getValue()
            expo.modules.interfaces.permissions.PermissionsResponse r3 = (expo.modules.interfaces.permissions.PermissionsResponse) r3
            expo.modules.interfaces.permissions.PermissionsStatus r3 = r3.getStatus()
            expo.modules.interfaces.permissions.PermissionsStatus r4 = expo.modules.interfaces.permissions.PermissionsStatus.GRANTED
            if (r3 != r4) goto L37
            goto L1c
        L37:
            r0 = r1
        L38:
            boolean r3 = r7.isEmpty()
            r3 = r3 ^ r2
            if (r3 == 0) goto L6b
            boolean r3 = r7.isEmpty()
            if (r3 == 0) goto L46
            goto L69
        L46:
            java.util.Set r3 = r7.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L4e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L69
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r4 = r4.getValue()
            expo.modules.interfaces.permissions.PermissionsResponse r4 = (expo.modules.interfaces.permissions.PermissionsResponse) r4
            expo.modules.interfaces.permissions.PermissionsStatus r4 = r4.getStatus()
            expo.modules.interfaces.permissions.PermissionsStatus r5 = expo.modules.interfaces.permissions.PermissionsStatus.DENIED
            if (r4 != r5) goto L6b
            goto L4e
        L69:
            r3 = r2
            goto L6c
        L6b:
            r3 = r1
        L6c:
            boolean r4 = r7.isEmpty()
            if (r4 == 0) goto L74
        L72:
            r1 = r2
            goto L94
        L74:
            java.util.Set r7 = r7.entrySet()
            java.util.Iterator r7 = r7.iterator()
        L7c:
            boolean r4 = r7.hasNext()
            if (r4 == 0) goto L72
            java.lang.Object r4 = r7.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r4 = r4.getValue()
            expo.modules.interfaces.permissions.PermissionsResponse r4 = (expo.modules.interfaces.permissions.PermissionsResponse) r4
            boolean r4 = r4.getCanAskAgain()
            if (r4 != 0) goto L7c
        L94:
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
            java.lang.String r2 = "expires"
            java.lang.String r4 = "never"
            r7.putString(r2, r4)
            if (r0 == 0) goto La9
            expo.modules.interfaces.permissions.PermissionsStatus r2 = expo.modules.interfaces.permissions.PermissionsStatus.GRANTED
            java.lang.String r2 = r2.getStatus()
            goto Lb8
        La9:
            if (r3 == 0) goto Lb2
            expo.modules.interfaces.permissions.PermissionsStatus r2 = expo.modules.interfaces.permissions.PermissionsStatus.DENIED
            java.lang.String r2 = r2.getStatus()
            goto Lb8
        Lb2:
            expo.modules.interfaces.permissions.PermissionsStatus r2 = expo.modules.interfaces.permissions.PermissionsStatus.UNDETERMINED
            java.lang.String r2 = r2.getStatus()
        Lb8:
            java.lang.String r3 = "status"
            r7.putString(r3, r2)
            java.lang.String r2 = "canAskAgain"
            r7.putBoolean(r2, r1)
            java.lang.String r1 = "granted"
            r7.putBoolean(r1, r0)
            r6.resolve(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.adapters.react.permissions.PermissionsService.getPermissionsWithPromise$lambda$6(expo.modules.core.Promise, java.util.Map):void");
    }
}

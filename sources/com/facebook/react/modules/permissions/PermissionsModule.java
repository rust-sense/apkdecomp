package com.facebook.react.modules.permissions;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativePermissionsAndroidSpec;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import expo.modules.interfaces.permissions.PermissionsResponse;
import java.util.ArrayList;

@ReactModule(name = NativePermissionsAndroidSpec.NAME)
/* loaded from: classes.dex */
public class PermissionsModule extends NativePermissionsAndroidSpec implements PermissionListener {
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    private final String DENIED;
    private final String GRANTED;
    private final String NEVER_ASK_AGAIN;
    private final SparseArray<Callback> mCallbacks;
    private int mRequestCode;

    public PermissionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mRequestCode = 0;
        this.GRANTED = PermissionsResponse.GRANTED_KEY;
        this.DENIED = "denied";
        this.NEVER_ASK_AGAIN = "never_ask_again";
        this.mCallbacks = new SparseArray<>();
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void checkPermission(String str, Promise promise) {
        promise.resolve(Boolean.valueOf(getReactApplicationContext().getBaseContext().checkSelfPermission(str) == 0));
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void shouldShowRequestPermissionRationale(String str, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity().shouldShowRequestPermissionRationale(str)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void requestPermission(final String str, final Promise promise) {
        if (getReactApplicationContext().getBaseContext().checkSelfPermission(str) == 0) {
            promise.resolve(PermissionsResponse.GRANTED_KEY);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, new Callback() { // from class: com.facebook.react.modules.permissions.PermissionsModule.1
                @Override // com.facebook.react.bridge.Callback
                public void invoke(Object... objArr) {
                    int[] iArr = (int[]) objArr[0];
                    if (iArr.length > 0 && iArr[0] == 0) {
                        promise.resolve(PermissionsResponse.GRANTED_KEY);
                    } else if (((PermissionAwareActivity) objArr[1]).shouldShowRequestPermissionRationale(str)) {
                        promise.resolve("denied");
                    } else {
                        promise.resolve("never_ask_again");
                    }
                }
            });
            permissionAwareActivity.requestPermissions(new String[]{str}, this.mRequestCode, this);
            this.mRequestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void requestMultiplePermissions(ReadableArray readableArray, final Promise promise) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        final ArrayList arrayList = new ArrayList();
        Context baseContext = getReactApplicationContext().getBaseContext();
        int i = 0;
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            String string = readableArray.getString(i2);
            if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, PermissionsResponse.GRANTED_KEY);
                i++;
            } else {
                arrayList.add(string);
            }
        }
        if (readableArray.size() == i) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, new Callback() { // from class: com.facebook.react.modules.permissions.PermissionsModule.2
                @Override // com.facebook.react.bridge.Callback
                public void invoke(Object... objArr) {
                    int[] iArr = (int[]) objArr[0];
                    PermissionAwareActivity permissionAwareActivity2 = (PermissionAwareActivity) objArr[1];
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        String str = (String) arrayList.get(i3);
                        if (iArr.length > 0 && iArr[i3] == 0) {
                            writableNativeMap.putString(str, PermissionsResponse.GRANTED_KEY);
                        } else if (permissionAwareActivity2.shouldShowRequestPermissionRationale(str)) {
                            writableNativeMap.putString(str, "denied");
                        } else {
                            writableNativeMap.putString(str, "never_ask_again");
                        }
                    }
                    promise.resolve(writableNativeMap);
                }
            });
            permissionAwareActivity.requestPermissions((String[]) arrayList.toArray(new String[0]), this.mRequestCode, this);
            this.mRequestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @Override // com.facebook.react.modules.core.PermissionListener
    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        try {
            Callback callback = this.mCallbacks.get(i);
            if (callback != null) {
                callback.invoke(iArr, getPermissionAwareActivity());
                this.mCallbacks.remove(i);
            } else {
                FLog.w("PermissionsModule", "Unable to find callback with requestCode %d", Integer.valueOf(i));
            }
            return this.mCallbacks.size() == 0;
        } catch (IllegalStateException e) {
            FLog.e("PermissionsModule", e, "Unexpected invocation of `onRequestPermissionsResult` with invalid current activity", new Object[0]);
            return false;
        }
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        ComponentCallbacks2 currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        }
        if (!(currentActivity instanceof PermissionAwareActivity)) {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
        return (PermissionAwareActivity) currentActivity;
    }
}

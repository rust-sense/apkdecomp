package expo.modules.core.interfaces;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;

/* loaded from: classes2.dex */
public interface ReactActivityHandler {

    /* renamed from: expo.modules.core.interfaces.ReactActivityHandler$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static ViewGroup $default$createReactRootViewContainer(ReactActivityHandler _this, Activity activity) {
            return null;
        }

        public static DelayLoadAppHandler $default$getDelayLoadAppHandler(ReactActivityHandler _this, ReactActivity reactActivity, ReactNativeHost reactNativeHost) {
            return null;
        }

        public static ReactActivityDelegate $default$onDidCreateReactActivityDelegate(ReactActivityHandler _this, ReactActivity reactActivity, ReactActivityDelegate reactActivityDelegate) {
            return null;
        }

        public static boolean $default$onKeyDown(ReactActivityHandler _this, int i, KeyEvent keyEvent) {
            return false;
        }

        public static boolean $default$onKeyLongPress(ReactActivityHandler _this, int i, KeyEvent keyEvent) {
            return false;
        }

        public static boolean $default$onKeyUp(ReactActivityHandler _this, int i, KeyEvent keyEvent) {
            return false;
        }
    }

    public interface DelayLoadAppHandler {
        void whenReady(Runnable runnable);
    }

    ViewGroup createReactRootViewContainer(Activity activity);

    DelayLoadAppHandler getDelayLoadAppHandler(ReactActivity reactActivity, ReactNativeHost reactNativeHost);

    ReactActivityDelegate onDidCreateReactActivityDelegate(ReactActivity reactActivity, ReactActivityDelegate reactActivityDelegate);

    boolean onKeyDown(int i, KeyEvent keyEvent);

    boolean onKeyLongPress(int i, KeyEvent keyEvent);

    boolean onKeyUp(int i, KeyEvent keyEvent);
}

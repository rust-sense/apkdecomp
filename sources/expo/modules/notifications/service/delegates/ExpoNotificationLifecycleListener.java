package expo.modules.notifications.service.delegates;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.notifications.notifications.NotificationManager;
import expo.modules.notifications.service.NotificationsService;

/* loaded from: classes2.dex */
public class ExpoNotificationLifecycleListener implements ReactActivityLifecycleListener {
    private NotificationManager mNotificationManager;

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ boolean onBackPressed() {
        return ReactActivityLifecycleListener.CC.$default$onBackPressed(this);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onContentChanged(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onContentChanged(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onDestroy(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onDestroy(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onPause(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onPause(this, activity);
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public /* synthetic */ void onResume(Activity activity) {
        ReactActivityLifecycleListener.CC.$default$onResume(this, activity);
    }

    public ExpoNotificationLifecycleListener(Context context, NotificationManager notificationManager) {
        this.mNotificationManager = notificationManager;
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public void onCreate(Activity activity, Bundle bundle) {
        Bundle extras;
        Intent intent = activity.getIntent();
        if (intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        if (extras.containsKey(NotificationsService.NOTIFICATION_RESPONSE_KEY)) {
            Log.d("ReactNativeJS", "[native] ExpoNotificationLifecycleListener contains an unmarshaled notification response. Skipping.");
        } else {
            this.mNotificationManager.onNotificationResponseFromExtras(extras);
        }
    }

    @Override // expo.modules.core.interfaces.ReactActivityLifecycleListener
    public boolean onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey(NotificationsService.NOTIFICATION_RESPONSE_KEY)) {
                Log.d("ReactNativeJS", "[native] ExpoNotificationLifecycleListener contains an unmarshaled notification response. Skipping.");
                intent.removeExtra(NotificationsService.NOTIFICATION_RESPONSE_KEY);
                return ReactActivityLifecycleListener.CC.$default$onNewIntent(this, intent);
            }
            this.mNotificationManager.onNotificationResponseFromExtras(extras);
        }
        return ReactActivityLifecycleListener.CC.$default$onNewIntent(this, intent);
    }
}

package expo.modules.notifications.notifications.handling;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.notifications.notifications.NotificationSerializer;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.service.NotificationsService;

/* loaded from: classes2.dex */
public class SingleNotificationHandlerTask {
    private static final String HANDLE_NOTIFICATION_EVENT_NAME = "onHandleNotification";
    private static final String HANDLE_NOTIFICATION_TIMEOUT_EVENT_NAME = "onHandleNotificationTimeout";
    private static final int SECONDS_TO_TIMEOUT = 3;
    private NotificationBehavior mBehavior;
    private Context mContext;
    private NotificationsHandler mDelegate;
    private EventEmitter mEventEmitter;
    private Handler mHandler;
    private Notification mNotification;
    private Runnable mTimeoutRunnable = new Runnable() { // from class: expo.modules.notifications.notifications.handling.SingleNotificationHandlerTask$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            SingleNotificationHandlerTask.this.handleTimeout();
        }
    };

    SingleNotificationHandlerTask(Context context, EventEmitter eventEmitter, Handler handler, Notification notification, NotificationsHandler notificationsHandler) {
        this.mContext = context;
        this.mHandler = handler;
        this.mEventEmitter = eventEmitter;
        this.mNotification = notification;
        this.mDelegate = notificationsHandler;
    }

    String getIdentifier() {
        return this.mNotification.getNotificationRequest().getIdentifier();
    }

    void start() {
        Bundle bundle = new Bundle();
        bundle.putString("id", getIdentifier());
        bundle.putBundle(NotificationsService.NOTIFICATION_KEY, NotificationSerializer.toBundle(this.mNotification));
        this.mEventEmitter.emit(HANDLE_NOTIFICATION_EVENT_NAME, bundle);
        this.mHandler.postDelayed(this.mTimeoutRunnable, 3000L);
    }

    void stop() {
        finish();
    }

    void handleResponse(NotificationBehavior notificationBehavior, final Promise promise) {
        this.mBehavior = notificationBehavior;
        this.mHandler.post(new Runnable() { // from class: expo.modules.notifications.notifications.handling.SingleNotificationHandlerTask.1
            @Override // java.lang.Runnable
            public void run() {
                NotificationsService.INSTANCE.present(SingleNotificationHandlerTask.this.mContext, SingleNotificationHandlerTask.this.mNotification, SingleNotificationHandlerTask.this.mBehavior, new ResultReceiver(SingleNotificationHandlerTask.this.mHandler) { // from class: expo.modules.notifications.notifications.handling.SingleNotificationHandlerTask.1.1
                    @Override // android.os.ResultReceiver
                    protected void onReceiveResult(int i, Bundle bundle) {
                        super.onReceiveResult(i, bundle);
                        if (i == 0) {
                            promise.resolve(null);
                        } else {
                            promise.reject("ERR_NOTIFICATION_PRESENTATION_FAILED", "Notification presentation failed.", (Exception) bundle.getSerializable("exception"));
                        }
                    }
                });
                SingleNotificationHandlerTask.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTimeout() {
        Bundle bundle = new Bundle();
        bundle.putString("id", getIdentifier());
        bundle.putBundle(NotificationsService.NOTIFICATION_KEY, NotificationSerializer.toBundle(this.mNotification));
        this.mEventEmitter.emit(HANDLE_NOTIFICATION_TIMEOUT_EVENT_NAME, bundle);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish() {
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mDelegate.onTaskFinished(this);
    }
}

package expo.modules.notifications.service.interfaces;

import com.google.firebase.messaging.RemoteMessage;
import kotlin.Metadata;

/* compiled from: FirebaseMessagingDelegate.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lexpo/modules/notifications/service/interfaces/FirebaseMessagingDelegate;", "", "onDeletedMessages", "", "onMessageReceived", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface FirebaseMessagingDelegate {
    void onDeletedMessages();

    void onMessageReceived(RemoteMessage remoteMessage);

    void onNewToken(String token);
}

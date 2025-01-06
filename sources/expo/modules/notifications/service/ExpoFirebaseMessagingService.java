package expo.modules.notifications.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.service.delegates.FirebaseMessagingDelegate;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoFirebaseMessagingService.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048TX\u0094\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lexpo/modules/notifications/service/ExpoFirebaseMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "firebaseMessagingDelegate", "Lexpo/modules/notifications/service/interfaces/FirebaseMessagingDelegate;", "getFirebaseMessagingDelegate", "()Lexpo/modules/notifications/service/interfaces/FirebaseMessagingDelegate;", "firebaseMessagingDelegate$delegate", "Lkotlin/Lazy;", "onDeletedMessages", "", "onMessageReceived", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ExpoFirebaseMessagingService extends FirebaseMessagingService {

    /* renamed from: firebaseMessagingDelegate$delegate, reason: from kotlin metadata */
    private final Lazy firebaseMessagingDelegate = LazyKt.lazy(new Function0<FirebaseMessagingDelegate>() { // from class: expo.modules.notifications.service.ExpoFirebaseMessagingService$firebaseMessagingDelegate$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FirebaseMessagingDelegate invoke() {
            return new FirebaseMessagingDelegate(ExpoFirebaseMessagingService.this);
        }
    });

    protected expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate getFirebaseMessagingDelegate() {
        return (expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate) this.firebaseMessagingDelegate.getValue();
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        getFirebaseMessagingDelegate().onMessageReceived(remoteMessage);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        getFirebaseMessagingDelegate().onNewToken(token);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onDeletedMessages() {
        getFirebaseMessagingDelegate().onDeletedMessages();
    }
}

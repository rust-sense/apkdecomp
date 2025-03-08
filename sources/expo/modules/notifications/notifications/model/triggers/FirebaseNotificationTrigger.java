package expo.modules.notifications.notifications.model.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;

/* loaded from: classes2.dex */
public class FirebaseNotificationTrigger implements NotificationTrigger {
    public static final Parcelable.Creator<FirebaseNotificationTrigger> CREATOR = new Parcelable.Creator<FirebaseNotificationTrigger>() { // from class: expo.modules.notifications.notifications.model.triggers.FirebaseNotificationTrigger.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FirebaseNotificationTrigger createFromParcel(Parcel parcel) {
            return new FirebaseNotificationTrigger(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FirebaseNotificationTrigger[] newArray(int i) {
            return new FirebaseNotificationTrigger[i];
        }
    };
    private RemoteMessage mRemoteMessage;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RemoteMessage getRemoteMessage() {
        return this.mRemoteMessage;
    }

    public FirebaseNotificationTrigger(RemoteMessage remoteMessage) {
        this.mRemoteMessage = remoteMessage;
    }

    private FirebaseNotificationTrigger(Parcel parcel) {
        this.mRemoteMessage = (RemoteMessage) parcel.readParcelable(getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mRemoteMessage, 0);
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationTrigger
    public String getNotificationChannel() {
        if (getRemoteMessage().getData().containsKey("channelId")) {
            return getRemoteMessage().getData().get("channelId");
        }
        return NotificationTrigger.CC.$default$getNotificationChannel(this);
    }
}

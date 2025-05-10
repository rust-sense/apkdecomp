package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class NotificationRequest implements Parcelable, Serializable {
    public static final Parcelable.Creator<NotificationRequest> CREATOR = new Parcelable.Creator<NotificationRequest>() { // from class: expo.modules.notifications.notifications.model.NotificationRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationRequest createFromParcel(Parcel parcel) {
            return new NotificationRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationRequest[] newArray(int i) {
            return new NotificationRequest[i];
        }
    };
    private NotificationContent mContent;
    private String mIdentifier;
    private NotificationTrigger mTrigger;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NotificationContent getContent() {
        return this.mContent;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public NotificationTrigger getTrigger() {
        return this.mTrigger;
    }

    public NotificationRequest(String str, NotificationContent notificationContent, NotificationTrigger notificationTrigger) {
        this.mIdentifier = str;
        this.mContent = notificationContent;
        this.mTrigger = notificationTrigger;
    }

    protected NotificationRequest(Parcel parcel) {
        this.mIdentifier = parcel.readString();
        this.mContent = (NotificationContent) parcel.readParcelable(getClass().getClassLoader());
        this.mTrigger = (NotificationTrigger) parcel.readParcelable(getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIdentifier);
        parcel.writeParcelable(this.mContent, 0);
        parcel.writeParcelable(this.mTrigger, 0);
    }
}

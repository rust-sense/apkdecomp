package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

/* loaded from: classes2.dex */
public class Notification implements Parcelable {
    public static final Parcelable.Creator<Notification> CREATOR = new Parcelable.Creator<Notification>() { // from class: expo.modules.notifications.notifications.model.Notification.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Notification createFromParcel(Parcel parcel) {
            return new Notification(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Notification[] newArray(int i) {
            return new Notification[i];
        }
    };
    private Date mDate;
    private NotificationRequest mRequest;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Date getDate() {
        return this.mDate;
    }

    public NotificationRequest getNotificationRequest() {
        return this.mRequest;
    }

    public Notification(NotificationRequest notificationRequest) {
        this(notificationRequest, new Date());
    }

    public Notification(NotificationRequest notificationRequest, Date date) {
        this.mRequest = notificationRequest;
        this.mDate = date;
    }

    protected Notification(Parcel parcel) {
        this.mRequest = (NotificationRequest) parcel.readParcelable(getClass().getClassLoader());
        this.mDate = new Date(parcel.readLong());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mRequest, 0);
        parcel.writeLong(this.mDate.getTime());
    }
}

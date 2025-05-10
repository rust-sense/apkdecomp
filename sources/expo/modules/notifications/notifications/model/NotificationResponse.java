package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class NotificationResponse implements Parcelable {
    public static final Parcelable.Creator<NotificationResponse> CREATOR = new Parcelable.Creator<NotificationResponse>() { // from class: expo.modules.notifications.notifications.model.NotificationResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationResponse createFromParcel(Parcel parcel) {
            return new NotificationResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationResponse[] newArray(int i) {
            return new NotificationResponse[i];
        }
    };
    public static final String DEFAULT_ACTION_IDENTIFIER = "expo.modules.notifications.actions.DEFAULT";
    private NotificationAction mAction;
    private Notification mNotification;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NotificationAction getAction() {
        return this.mAction;
    }

    public Notification getNotification() {
        return this.mNotification;
    }

    public NotificationResponse(NotificationAction notificationAction, Notification notification) {
        this.mAction = notificationAction;
        this.mNotification = notification;
    }

    public String getActionIdentifier() {
        return this.mAction.getIdentifier();
    }

    protected NotificationResponse(Parcel parcel) {
        this.mAction = (NotificationAction) parcel.readParcelable(getClass().getClassLoader());
        this.mNotification = (Notification) parcel.readParcelable(getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAction, 0);
        parcel.writeParcelable(this.mNotification, 0);
    }
}

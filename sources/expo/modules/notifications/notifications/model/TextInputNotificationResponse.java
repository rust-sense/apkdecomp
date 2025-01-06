package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class TextInputNotificationResponse extends NotificationResponse {
    public static final Parcelable.Creator<TextInputNotificationResponse> CREATOR = new Parcelable.Creator<TextInputNotificationResponse>() { // from class: expo.modules.notifications.notifications.model.TextInputNotificationResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextInputNotificationResponse createFromParcel(Parcel parcel) {
            return new TextInputNotificationResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextInputNotificationResponse[] newArray(int i) {
            return new TextInputNotificationResponse[i];
        }
    };
    private String mUserText;

    public String getUserText() {
        return this.mUserText;
    }

    public TextInputNotificationResponse(NotificationAction notificationAction, Notification notification, String str) {
        super(notificationAction, notification);
        this.mUserText = str;
    }

    protected TextInputNotificationResponse(Parcel parcel) {
        super(parcel);
        this.mUserText = parcel.readString();
    }

    @Override // expo.modules.notifications.notifications.model.NotificationResponse, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mUserText);
    }
}

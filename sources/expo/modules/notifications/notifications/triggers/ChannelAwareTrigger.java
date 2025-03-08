package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class ChannelAwareTrigger implements NotificationTrigger, Serializable {
    public static final Parcelable.Creator<ChannelAwareTrigger> CREATOR = new Parcelable.Creator<ChannelAwareTrigger>() { // from class: expo.modules.notifications.notifications.triggers.ChannelAwareTrigger.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChannelAwareTrigger createFromParcel(Parcel parcel) {
            return new ChannelAwareTrigger(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChannelAwareTrigger[] newArray(int i) {
            return new ChannelAwareTrigger[i];
        }
    };
    private String mChannelId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // expo.modules.notifications.notifications.interfaces.NotificationTrigger
    public String getNotificationChannel() {
        return this.mChannelId;
    }

    public ChannelAwareTrigger(String str) {
        this.mChannelId = str;
    }

    public ChannelAwareTrigger(Parcel parcel) {
        this.mChannelId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mChannelId);
    }
}

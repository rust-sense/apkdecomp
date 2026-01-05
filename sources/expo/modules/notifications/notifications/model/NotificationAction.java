package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class NotificationAction implements Parcelable, Serializable {
    public static final Parcelable.Creator<NotificationAction> CREATOR = new Parcelable.Creator<NotificationAction>() { // from class: expo.modules.notifications.notifications.model.NotificationAction.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationAction createFromParcel(Parcel parcel) {
            return new NotificationAction(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationAction[] newArray(int i) {
            return new NotificationAction[i];
        }
    };
    private final String mIdentifier;
    private final boolean mOpensAppToForeground;
    private final String mTitle;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean opensAppToForeground() {
        return this.mOpensAppToForeground;
    }

    public NotificationAction(String str, String str2, boolean z) {
        this.mIdentifier = str;
        this.mTitle = str2;
        this.mOpensAppToForeground = z;
    }

    protected NotificationAction(Parcel parcel) {
        this.mIdentifier = parcel.readString();
        this.mTitle = parcel.readString();
        this.mOpensAppToForeground = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIdentifier);
        parcel.writeString(this.mTitle);
        parcel.writeByte(this.mOpensAppToForeground ? (byte) 1 : (byte) 0);
    }
}

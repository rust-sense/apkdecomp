package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.enums.NotificationPriority;

/* loaded from: classes2.dex */
public class NotificationBehavior implements Parcelable {
    public static final Parcelable.Creator<NotificationBehavior> CREATOR = new Parcelable.Creator<NotificationBehavior>() { // from class: expo.modules.notifications.notifications.model.NotificationBehavior.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationBehavior createFromParcel(Parcel parcel) {
            return new NotificationBehavior(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationBehavior[] newArray(int i) {
            return new NotificationBehavior[i];
        }
    };
    private final String mPriorityOverride;
    private final boolean mShouldPlaySound;
    private final boolean mShouldSetBadge;
    private final boolean mShouldShowAlert;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean shouldPlaySound() {
        return this.mShouldPlaySound;
    }

    public boolean shouldSetBadge() {
        return this.mShouldSetBadge;
    }

    public boolean shouldShowAlert() {
        return this.mShouldShowAlert;
    }

    public NotificationBehavior(boolean z, boolean z2, boolean z3, String str) {
        this.mShouldShowAlert = z;
        this.mShouldPlaySound = z2;
        this.mShouldSetBadge = z3;
        this.mPriorityOverride = str;
    }

    private NotificationBehavior(Parcel parcel) {
        this.mShouldShowAlert = parcel.readByte() != 0;
        this.mShouldPlaySound = parcel.readByte() != 0;
        this.mShouldSetBadge = parcel.readByte() != 0;
        this.mPriorityOverride = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mShouldShowAlert ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mShouldPlaySound ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mShouldSetBadge ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mPriorityOverride);
    }

    public NotificationPriority getPriorityOverride() {
        String str = this.mPriorityOverride;
        if (str == null) {
            return null;
        }
        return NotificationPriority.fromEnumValue(str);
    }
}

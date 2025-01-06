package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Date;

/* loaded from: classes2.dex */
public class DateTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
    public static final Parcelable.Creator<DateTrigger> CREATOR = new Parcelable.Creator<DateTrigger>() { // from class: expo.modules.notifications.notifications.triggers.DateTrigger.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DateTrigger createFromParcel(Parcel parcel) {
            return new DateTrigger(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DateTrigger[] newArray(int i) {
            return new DateTrigger[i];
        }
    };
    private Date mTriggerDate;

    @Override // expo.modules.notifications.notifications.triggers.ChannelAwareTrigger, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Date getTriggerDate() {
        return this.mTriggerDate;
    }

    public DateTrigger(long j, String str) {
        super(str);
        this.mTriggerDate = new Date(j);
    }

    private DateTrigger(Parcel parcel) {
        super(parcel);
        this.mTriggerDate = new Date(parcel.readLong());
    }

    @Override // expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger
    public Date nextTriggerDate() {
        if (this.mTriggerDate.before(new Date())) {
            return null;
        }
        return this.mTriggerDate;
    }

    @Override // expo.modules.notifications.notifications.triggers.ChannelAwareTrigger, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mTriggerDate.getTime());
    }
}

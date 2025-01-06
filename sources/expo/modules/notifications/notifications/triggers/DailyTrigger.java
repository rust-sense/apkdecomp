package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes2.dex */
public class DailyTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
    public static final Parcelable.Creator<DailyTrigger> CREATOR = new Parcelable.Creator<DailyTrigger>() { // from class: expo.modules.notifications.notifications.triggers.DailyTrigger.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DailyTrigger createFromParcel(Parcel parcel) {
            return new DailyTrigger(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DailyTrigger[] newArray(int i) {
            return new DailyTrigger[i];
        }
    };
    private int mHour;
    private int mMinute;

    @Override // expo.modules.notifications.notifications.triggers.ChannelAwareTrigger, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getHour() {
        return this.mHour;
    }

    public int getMinute() {
        return this.mMinute;
    }

    public DailyTrigger(int i, int i2, String str) {
        super(str);
        this.mHour = i;
        this.mMinute = i2;
    }

    private DailyTrigger(Parcel parcel) {
        super(parcel);
        this.mHour = parcel.readInt();
        this.mMinute = parcel.readInt();
    }

    @Override // expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger
    public Date nextTriggerDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, this.mHour);
        calendar.set(12, this.mMinute);
        calendar.set(13, 0);
        calendar.set(14, 0);
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(5, 1);
        }
        return calendar.getTime();
    }

    @Override // expo.modules.notifications.notifications.triggers.ChannelAwareTrigger, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mHour);
        parcel.writeInt(this.mMinute);
    }
}

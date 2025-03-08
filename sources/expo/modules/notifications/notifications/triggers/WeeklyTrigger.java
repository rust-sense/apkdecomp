package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes2.dex */
public class WeeklyTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
    public static final Parcelable.Creator<WeeklyTrigger> CREATOR = new Parcelable.Creator<WeeklyTrigger>() { // from class: expo.modules.notifications.notifications.triggers.WeeklyTrigger.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeeklyTrigger createFromParcel(Parcel parcel) {
            return new WeeklyTrigger(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WeeklyTrigger[] newArray(int i) {
            return new WeeklyTrigger[i];
        }
    };
    private int mHour;
    private int mMinute;
    private int mWeekday;

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

    public int getWeekday() {
        return this.mWeekday;
    }

    public WeeklyTrigger(int i, int i2, int i3, String str) {
        super(str);
        this.mWeekday = i;
        this.mHour = i2;
        this.mMinute = i3;
    }

    private WeeklyTrigger(Parcel parcel) {
        super(parcel);
        this.mWeekday = parcel.readInt();
        this.mHour = parcel.readInt();
        this.mMinute = parcel.readInt();
    }

    @Override // expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger
    public Date nextTriggerDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(7, this.mWeekday);
        calendar.set(11, this.mHour);
        calendar.set(12, this.mMinute);
        calendar.set(13, 0);
        calendar.set(14, 0);
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(8, 1);
        }
        return calendar.getTime();
    }

    @Override // expo.modules.notifications.notifications.triggers.ChannelAwareTrigger, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mWeekday);
        parcel.writeInt(this.mHour);
        parcel.writeInt(this.mMinute);
    }
}

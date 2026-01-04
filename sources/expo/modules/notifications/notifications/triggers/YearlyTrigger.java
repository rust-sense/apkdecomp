package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes2.dex */
public class YearlyTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
    public static final Parcelable.Creator<YearlyTrigger> CREATOR = new Parcelable.Creator<YearlyTrigger>() { // from class: expo.modules.notifications.notifications.triggers.YearlyTrigger.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public YearlyTrigger createFromParcel(Parcel parcel) {
            return new YearlyTrigger(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public YearlyTrigger[] newArray(int i) {
            return new YearlyTrigger[i];
        }
    };
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mMonth;

    @Override // expo.modules.notifications.notifications.triggers.ChannelAwareTrigger, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getDay() {
        return this.mDay;
    }

    public int getHour() {
        return this.mHour;
    }

    public int getMinute() {
        return this.mMinute;
    }

    public int getMonth() {
        return this.mMonth;
    }

    public YearlyTrigger(int i, int i2, int i3, int i4, String str) {
        super(str);
        this.mDay = i;
        this.mMonth = i2;
        this.mHour = i3;
        this.mMinute = i4;
    }

    private YearlyTrigger(Parcel parcel) {
        super(parcel);
        this.mDay = parcel.readInt();
        this.mMonth = parcel.readInt();
        this.mHour = parcel.readInt();
        this.mMinute = parcel.readInt();
    }

    @Override // expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger
    public Date nextTriggerDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, this.mDay);
        calendar.set(2, this.mMonth);
        calendar.set(11, this.mHour);
        calendar.set(12, this.mMinute);
        calendar.set(13, 0);
        calendar.set(14, 0);
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(1, 1);
        }
        return calendar.getTime();
    }

    @Override // expo.modules.notifications.notifications.triggers.ChannelAwareTrigger, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mDay);
        parcel.writeInt(this.mMonth);
        parcel.writeInt(this.mHour);
        parcel.writeInt(this.mMinute);
    }
}

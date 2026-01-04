package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Date;

/* loaded from: classes2.dex */
public class TimeIntervalTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
    public static final Parcelable.Creator<TimeIntervalTrigger> CREATOR = new Parcelable.Creator<TimeIntervalTrigger>() { // from class: expo.modules.notifications.notifications.triggers.TimeIntervalTrigger.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeIntervalTrigger createFromParcel(Parcel parcel) {
            return new TimeIntervalTrigger(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeIntervalTrigger[] newArray(int i) {
            return new TimeIntervalTrigger[i];
        }
    };
    private boolean mRepeats;
    private long mTimeInterval;
    private Date mTriggerDate;

    @Override // expo.modules.notifications.notifications.triggers.ChannelAwareTrigger, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getTimeInterval() {
        return this.mTimeInterval;
    }

    public boolean isRepeating() {
        return this.mRepeats;
    }

    public TimeIntervalTrigger(long j, boolean z, String str) {
        super(str);
        this.mTimeInterval = j;
        this.mTriggerDate = new Date(new Date().getTime() + (this.mTimeInterval * 1000));
        this.mRepeats = z;
    }

    private TimeIntervalTrigger(Parcel parcel) {
        super(parcel);
        this.mTriggerDate = new Date(parcel.readLong());
        this.mTimeInterval = parcel.readLong();
        this.mRepeats = parcel.readByte() == 1;
    }

    @Override // expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger
    public Date nextTriggerDate() {
        Date date = new Date();
        if (this.mRepeats) {
            while (this.mTriggerDate.before(date)) {
                Date date2 = this.mTriggerDate;
                date2.setTime(date2.getTime() + (this.mTimeInterval * 1000));
            }
        }
        if (this.mTriggerDate.before(date)) {
            return null;
        }
        return this.mTriggerDate;
    }

    @Override // expo.modules.notifications.notifications.triggers.ChannelAwareTrigger, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mTriggerDate.getTime());
        parcel.writeLong(this.mTimeInterval);
        parcel.writeByte(this.mRepeats ? (byte) 1 : (byte) 0);
    }
}

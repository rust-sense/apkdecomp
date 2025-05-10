package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class TextInputNotificationAction extends NotificationAction {
    public static final Parcelable.Creator<TextInputNotificationAction> CREATOR = new Parcelable.Creator<TextInputNotificationAction>() { // from class: expo.modules.notifications.notifications.model.TextInputNotificationAction.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextInputNotificationAction createFromParcel(Parcel parcel) {
            return new TextInputNotificationAction(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TextInputNotificationAction[] newArray(int i) {
            return new TextInputNotificationAction[i];
        }
    };
    private final String mPlaceholder;

    public String getPlaceholder() {
        return this.mPlaceholder;
    }

    public TextInputNotificationAction(String str, String str2, boolean z, String str3) {
        super(str, str2, z);
        this.mPlaceholder = str3;
    }

    private TextInputNotificationAction(Parcel parcel) {
        super(parcel);
        this.mPlaceholder = parcel.readString();
    }

    @Override // expo.modules.notifications.notifications.model.NotificationAction, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mPlaceholder);
    }
}

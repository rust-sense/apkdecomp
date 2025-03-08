package expo.modules.image.records;

import com.google.firebase.messaging.Constants;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: events.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lexpo/modules/image/records/ImageErrorEvent;", "Lexpo/modules/kotlin/records/Record;", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "(Ljava/lang/String;)V", "getError$annotations", "()V", "getError", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ImageErrorEvent implements Record {
    private final String error;

    public static /* synthetic */ ImageErrorEvent copy$default(ImageErrorEvent imageErrorEvent, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = imageErrorEvent.error;
        }
        return imageErrorEvent.copy(str);
    }

    @Field
    public static /* synthetic */ void getError$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final ImageErrorEvent copy(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        return new ImageErrorEvent(error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ImageErrorEvent) && Intrinsics.areEqual(this.error, ((ImageErrorEvent) other).error);
    }

    public final String getError() {
        return this.error;
    }

    public int hashCode() {
        return this.error.hashCode();
    }

    public String toString() {
        return "ImageErrorEvent(error=" + this.error + ")";
    }

    public ImageErrorEvent(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.error = error;
    }
}

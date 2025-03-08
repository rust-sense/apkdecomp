package expo.modules.gl;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;

/* compiled from: GLView.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lexpo/modules/gl/OnSurfaceCreateRecord;", "Lexpo/modules/kotlin/records/Record;", "exglCtxId", "", "(I)V", "getExglCtxId$annotations", "()V", "getExglCtxId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "expo-gl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class OnSurfaceCreateRecord implements Record {
    private final int exglCtxId;

    public static /* synthetic */ OnSurfaceCreateRecord copy$default(OnSurfaceCreateRecord onSurfaceCreateRecord, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = onSurfaceCreateRecord.exglCtxId;
        }
        return onSurfaceCreateRecord.copy(i);
    }

    @Field
    public static /* synthetic */ void getExglCtxId$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final int getExglCtxId() {
        return this.exglCtxId;
    }

    public final OnSurfaceCreateRecord copy(int exglCtxId) {
        return new OnSurfaceCreateRecord(exglCtxId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof OnSurfaceCreateRecord) && this.exglCtxId == ((OnSurfaceCreateRecord) other).exglCtxId;
    }

    public final int getExglCtxId() {
        return this.exglCtxId;
    }

    public int hashCode() {
        return this.exglCtxId;
    }

    public String toString() {
        return "OnSurfaceCreateRecord(exglCtxId=" + this.exglCtxId + ")";
    }

    public OnSurfaceCreateRecord(int i) {
        this.exglCtxId = i;
    }
}

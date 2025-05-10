package expo.modules.filesystem;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileSystemRecords.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ&\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\n\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\n\u0012\u0004\b\u000b\u0010\u0007\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lexpo/modules/filesystem/InfoOptions;", "Lexpo/modules/kotlin/records/Record;", "md5", "", "size", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getMd5$annotations", "()V", "getMd5", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSize$annotations", "getSize", "component1", "component2", "copy", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)Lexpo/modules/filesystem/InfoOptions;", "equals", "other", "", "hashCode", "", "toString", "", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class InfoOptions implements Record {
    private final Boolean md5;
    private final Boolean size;

    public static /* synthetic */ InfoOptions copy$default(InfoOptions infoOptions, Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = infoOptions.md5;
        }
        if ((i & 2) != 0) {
            bool2 = infoOptions.size;
        }
        return infoOptions.copy(bool, bool2);
    }

    @Field
    public static /* synthetic */ void getMd5$annotations() {
    }

    @Field
    public static /* synthetic */ void getSize$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final Boolean getMd5() {
        return this.md5;
    }

    /* renamed from: component2, reason: from getter */
    public final Boolean getSize() {
        return this.size;
    }

    public final InfoOptions copy(Boolean md5, Boolean size) {
        return new InfoOptions(md5, size);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InfoOptions)) {
            return false;
        }
        InfoOptions infoOptions = (InfoOptions) other;
        return Intrinsics.areEqual(this.md5, infoOptions.md5) && Intrinsics.areEqual(this.size, infoOptions.size);
    }

    public final Boolean getMd5() {
        return this.md5;
    }

    public final Boolean getSize() {
        return this.size;
    }

    public int hashCode() {
        Boolean bool = this.md5;
        int hashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.size;
        return hashCode + (bool2 != null ? bool2.hashCode() : 0);
    }

    public String toString() {
        return "InfoOptions(md5=" + this.md5 + ", size=" + this.size + ")";
    }

    public InfoOptions(Boolean bool, Boolean bool2) {
        this.md5 = bool;
        this.size = bool2;
    }
}

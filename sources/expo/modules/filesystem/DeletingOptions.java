package expo.modules.filesystem;

import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: FileSystemRecords.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lexpo/modules/filesystem/DeletingOptions;", "Lexpo/modules/kotlin/records/Record;", "idempotent", "", "(Z)V", "getIdempotent$annotations", "()V", "getIdempotent", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DeletingOptions implements Record {
    private final boolean idempotent;

    public DeletingOptions() {
        this(false, 1, null);
    }

    public static /* synthetic */ DeletingOptions copy$default(DeletingOptions deletingOptions, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = deletingOptions.idempotent;
        }
        return deletingOptions.copy(z);
    }

    @Field
    public static /* synthetic */ void getIdempotent$annotations() {
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIdempotent() {
        return this.idempotent;
    }

    public final DeletingOptions copy(boolean idempotent) {
        return new DeletingOptions(idempotent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DeletingOptions) && this.idempotent == ((DeletingOptions) other).idempotent;
    }

    public final boolean getIdempotent() {
        return this.idempotent;
    }

    public int hashCode() {
        return SourceMap$$ExternalSyntheticBackport0.m(this.idempotent);
    }

    public String toString() {
        return "DeletingOptions(idempotent=" + this.idempotent + ")";
    }

    public DeletingOptions(boolean z) {
        this.idempotent = z;
    }

    public /* synthetic */ DeletingOptions(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }
}

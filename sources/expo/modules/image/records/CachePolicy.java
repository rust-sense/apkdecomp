package expo.modules.image.records;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CachePolicy.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/image/records/CachePolicy;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "NONE", "DISK", "MEMORY", "MEMORY_AND_DISK", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CachePolicy implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CachePolicy[] $VALUES;
    private final String value;
    public static final CachePolicy NONE = new CachePolicy("NONE", 0, "none");
    public static final CachePolicy DISK = new CachePolicy("DISK", 1, "disk");
    public static final CachePolicy MEMORY = new CachePolicy("MEMORY", 2, "memory");
    public static final CachePolicy MEMORY_AND_DISK = new CachePolicy("MEMORY_AND_DISK", 3, "memory-disk");

    private static final /* synthetic */ CachePolicy[] $values() {
        return new CachePolicy[]{NONE, DISK, MEMORY, MEMORY_AND_DISK};
    }

    public static EnumEntries<CachePolicy> getEntries() {
        return $ENTRIES;
    }

    public static CachePolicy valueOf(String str) {
        return (CachePolicy) Enum.valueOf(CachePolicy.class, str);
    }

    public static CachePolicy[] values() {
        return (CachePolicy[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private CachePolicy(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        CachePolicy[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

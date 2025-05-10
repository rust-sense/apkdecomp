package expo.modules.filesystem;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: FileSystemRecords.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lexpo/modules/filesystem/EncodingType;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "UTF8", "BASE64", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EncodingType implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ EncodingType[] $VALUES;
    private final String value;
    public static final EncodingType UTF8 = new EncodingType("UTF8", 0, "utf8");
    public static final EncodingType BASE64 = new EncodingType("BASE64", 1, "base64");

    private static final /* synthetic */ EncodingType[] $values() {
        return new EncodingType[]{UTF8, BASE64};
    }

    public static EnumEntries<EncodingType> getEntries() {
        return $ENTRIES;
    }

    public static EncodingType valueOf(String str) {
        return (EncodingType) Enum.valueOf(EncodingType.class, str);
    }

    public static EncodingType[] values() {
        return (EncodingType[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private EncodingType(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        EncodingType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

package expo.modules.filesystem;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: FileSystemRecords.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lexpo/modules/filesystem/FileSystemUploadType;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "BINARY_CONTENT", "MULTIPART", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FileSystemUploadType implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ FileSystemUploadType[] $VALUES;
    public static final FileSystemUploadType BINARY_CONTENT = new FileSystemUploadType("BINARY_CONTENT", 0, 0);
    public static final FileSystemUploadType MULTIPART = new FileSystemUploadType("MULTIPART", 1, 1);
    private final int value;

    private static final /* synthetic */ FileSystemUploadType[] $values() {
        return new FileSystemUploadType[]{BINARY_CONTENT, MULTIPART};
    }

    public static EnumEntries<FileSystemUploadType> getEntries() {
        return $ENTRIES;
    }

    public static FileSystemUploadType valueOf(String str) {
        return (FileSystemUploadType) Enum.valueOf(FileSystemUploadType.class, str);
    }

    public static FileSystemUploadType[] values() {
        return (FileSystemUploadType[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }

    private FileSystemUploadType(String str, int i, int i2) {
        this.value = i2;
    }

    static {
        FileSystemUploadType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

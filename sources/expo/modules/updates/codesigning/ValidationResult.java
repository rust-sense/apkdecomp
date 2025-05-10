package expo.modules.updates.codesigning;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CodeSigningConfiguration.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/updates/codesigning/ValidationResult;", "", "(Ljava/lang/String;I)V", "VALID", "INVALID", "SKIPPED", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ValidationResult {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ValidationResult[] $VALUES;
    public static final ValidationResult VALID = new ValidationResult("VALID", 0);
    public static final ValidationResult INVALID = new ValidationResult("INVALID", 1);
    public static final ValidationResult SKIPPED = new ValidationResult("SKIPPED", 2);

    private static final /* synthetic */ ValidationResult[] $values() {
        return new ValidationResult[]{VALID, INVALID, SKIPPED};
    }

    public static EnumEntries<ValidationResult> getEntries() {
        return $ENTRIES;
    }

    public static ValidationResult valueOf(String str) {
        return (ValidationResult) Enum.valueOf(ValidationResult.class, str);
    }

    public static ValidationResult[] values() {
        return (ValidationResult[]) $VALUES.clone();
    }

    private ValidationResult(String str, int i) {
    }

    static {
        ValidationResult[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

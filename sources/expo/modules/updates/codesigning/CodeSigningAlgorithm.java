package expo.modules.updates.codesigning;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CodeSigningAlgorithm.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "", "algorithmName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAlgorithmName", "()Ljava/lang/String;", "RSA_SHA256", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CodeSigningAlgorithm {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CodeSigningAlgorithm[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final CodeSigningAlgorithm RSA_SHA256 = new CodeSigningAlgorithm("RSA_SHA256", 0, "rsa-v1_5-sha256");
    private final String algorithmName;

    private static final /* synthetic */ CodeSigningAlgorithm[] $values() {
        return new CodeSigningAlgorithm[]{RSA_SHA256};
    }

    public static EnumEntries<CodeSigningAlgorithm> getEntries() {
        return $ENTRIES;
    }

    public static CodeSigningAlgorithm valueOf(String str) {
        return (CodeSigningAlgorithm) Enum.valueOf(CodeSigningAlgorithm.class, str);
    }

    public static CodeSigningAlgorithm[] values() {
        return (CodeSigningAlgorithm[]) $VALUES.clone();
    }

    public final String getAlgorithmName() {
        return this.algorithmName;
    }

    private CodeSigningAlgorithm(String str, int i, String str2) {
        this.algorithmName = str2;
    }

    static {
        CodeSigningAlgorithm[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: CodeSigningAlgorithm.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/codesigning/CodeSigningAlgorithm$Companion;", "", "()V", "parseFromString", "Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "str", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CodeSigningAlgorithm parseFromString(String str) {
            if (Intrinsics.areEqual(str, CodeSigningAlgorithm.RSA_SHA256.getAlgorithmName())) {
                return CodeSigningAlgorithm.RSA_SHA256;
            }
            if (str == null) {
                return CodeSigningAlgorithm.RSA_SHA256;
            }
            throw new Exception("Invalid code signing algorithm name: " + str);
        }
    }
}

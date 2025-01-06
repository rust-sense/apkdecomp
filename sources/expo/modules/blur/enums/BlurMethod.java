package expo.modules.blur.enums;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: BlurMethod.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lexpo/modules/blur/enums/BlurMethod;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "NONE", "DIMEZIS_BLUR_VIEW", "expo-blur_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BlurMethod implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ BlurMethod[] $VALUES;
    private final String value;
    public static final BlurMethod NONE = new BlurMethod("NONE", 0, "none");
    public static final BlurMethod DIMEZIS_BLUR_VIEW = new BlurMethod("DIMEZIS_BLUR_VIEW", 1, "dimezisBlurView");

    private static final /* synthetic */ BlurMethod[] $values() {
        return new BlurMethod[]{NONE, DIMEZIS_BLUR_VIEW};
    }

    public static EnumEntries<BlurMethod> getEntries() {
        return $ENTRIES;
    }

    public static BlurMethod valueOf(String str) {
        return (BlurMethod) Enum.valueOf(BlurMethod.class, str);
    }

    public static BlurMethod[] values() {
        return (BlurMethod[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private BlurMethod(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        BlurMethod[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

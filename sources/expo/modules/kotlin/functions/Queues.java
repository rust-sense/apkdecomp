package expo.modules.kotlin.functions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: BaseAsyncFunctionComponent.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/kotlin/functions/Queues;", "", "(Ljava/lang/String;I)V", "MAIN", "DEFAULT", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Queues {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Queues[] $VALUES;
    public static final Queues MAIN = new Queues("MAIN", 0);
    public static final Queues DEFAULT = new Queues("DEFAULT", 1);

    private static final /* synthetic */ Queues[] $values() {
        return new Queues[]{MAIN, DEFAULT};
    }

    public static EnumEntries<Queues> getEntries() {
        return $ENTRIES;
    }

    public static Queues valueOf(String str) {
        return (Queues) Enum.valueOf(Queues.class, str);
    }

    public static Queues[] values() {
        return (Queues[]) $VALUES.clone();
    }

    private Queues(String str, int i) {
    }

    static {
        Queues[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

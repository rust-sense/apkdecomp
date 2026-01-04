package expo.modules.screenorientation.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Orientation.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/screenorientation/enums/Orientation;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "UNKNOWN", "PORTRAIT_UP", "PORTRAIT_DOWN", "LANDSCAPE_LEFT", "LANDSCAPE_RIGHT", "expo-screen-orientation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Orientation {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Orientation[] $VALUES;
    private final int value;
    public static final Orientation UNKNOWN = new Orientation("UNKNOWN", 0, 0);
    public static final Orientation PORTRAIT_UP = new Orientation("PORTRAIT_UP", 1, 1);
    public static final Orientation PORTRAIT_DOWN = new Orientation("PORTRAIT_DOWN", 2, 2);
    public static final Orientation LANDSCAPE_LEFT = new Orientation("LANDSCAPE_LEFT", 3, 3);
    public static final Orientation LANDSCAPE_RIGHT = new Orientation("LANDSCAPE_RIGHT", 4, 4);

    private static final /* synthetic */ Orientation[] $values() {
        return new Orientation[]{UNKNOWN, PORTRAIT_UP, PORTRAIT_DOWN, LANDSCAPE_LEFT, LANDSCAPE_RIGHT};
    }

    public static EnumEntries<Orientation> getEntries() {
        return $ENTRIES;
    }

    public static Orientation valueOf(String str) {
        return (Orientation) Enum.valueOf(Orientation.class, str);
    }

    public static Orientation[] values() {
        return (Orientation[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }

    private Orientation(String str, int i, int i2) {
        this.value = i2;
    }

    static {
        Orientation[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

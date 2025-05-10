package expo.modules.image.enums;

import expo.modules.kotlin.types.Enumerable;
import io.sentry.ProfilingTraceData;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Priority.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lexpo/modules/image/enums/Priority;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toGlidePriority", "Lcom/bumptech/glide/Priority;", "toGlidePriority$expo_image_release", "LOW", "NORMAL", "HIGH", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Priority implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Priority[] $VALUES;
    private final String value;
    public static final Priority LOW = new Priority("LOW", 0, "low");
    public static final Priority NORMAL = new Priority("NORMAL", 1, ProfilingTraceData.TRUNCATION_REASON_NORMAL);
    public static final Priority HIGH = new Priority("HIGH", 2, "high");

    /* compiled from: Priority.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Priority.values().length];
            try {
                iArr[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ Priority[] $values() {
        return new Priority[]{LOW, NORMAL, HIGH};
    }

    public static EnumEntries<Priority> getEntries() {
        return $ENTRIES;
    }

    public static Priority valueOf(String str) {
        return (Priority) Enum.valueOf(Priority.class, str);
    }

    public static Priority[] values() {
        return (Priority[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private Priority(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        Priority[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final com.bumptech.glide.Priority toGlidePriority$expo_image_release() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return com.bumptech.glide.Priority.LOW;
        }
        if (i == 2) {
            return com.bumptech.glide.Priority.NORMAL;
        }
        if (i == 3) {
            return com.bumptech.glide.Priority.IMMEDIATE;
        }
        throw new NoWhenBranchMatchedException();
    }
}

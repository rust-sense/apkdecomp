package expo.modules.image.records;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: DecodeFormat.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lexpo/modules/image/records/DecodeFormat;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toBytes", "", "toGlideFormat", "Lcom/bumptech/glide/load/DecodeFormat;", "ARGB_8888", "RGB_565", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DecodeFormat implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DecodeFormat[] $VALUES;
    public static final DecodeFormat ARGB_8888 = new DecodeFormat("ARGB_8888", 0, "argb");
    public static final DecodeFormat RGB_565 = new DecodeFormat("RGB_565", 1, "rgb");
    private final String value;

    /* compiled from: DecodeFormat.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DecodeFormat.values().length];
            try {
                iArr[DecodeFormat.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DecodeFormat.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ DecodeFormat[] $values() {
        return new DecodeFormat[]{ARGB_8888, RGB_565};
    }

    public static EnumEntries<DecodeFormat> getEntries() {
        return $ENTRIES;
    }

    public static DecodeFormat valueOf(String str) {
        return (DecodeFormat) Enum.valueOf(DecodeFormat.class, str);
    }

    public static DecodeFormat[] values() {
        return (DecodeFormat[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private DecodeFormat(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        DecodeFormat[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final com.bumptech.glide.load.DecodeFormat toGlideFormat() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;
        }
        if (i == 2) {
            return com.bumptech.glide.load.DecodeFormat.PREFER_RGB_565;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final int toBytes() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 4;
        }
        if (i == 2) {
            return 2;
        }
        throw new NoWhenBranchMatchedException();
    }
}

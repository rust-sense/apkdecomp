package expo.modules.blur.enums;

import androidx.core.view.ViewCompat;
import com.facebook.hermes.intl.Constants;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: TintStyle.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0017\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!¨\u0006\""}, d2 = {"Lexpo/modules/blur/enums/TintStyle;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toBlurEffect", "", "blurRadius", "", "toColorInt", "DEFAULT", "EXTRA_LIGHT", "LIGHT", "DARK", "REGULAR", "PROMINENT", "SYSTEM_ULTRA_THIN_MATERIAL", "SYSTEM_THIN_MATERIAL", "SYSTEM_MATERIAL", "SYSTEM_THICK_MATERIAL", "SYSTEM_CHROME_MATERIAL", "SYSTEM_ULTRA_THIN_MATERIAL_LIGHT", "SYSTEM_THICK_MATERIAL_LIGHT", "SYSTEM_THIN_MATERIAL_LIGHT", "SYSTEM_MATERIAL_LIGHT", "SYSTEM_CHROME_MATERIAL_LIGHT", "SYSTEM_ULTRA_THIN_MATERIAL_DARK", "SYSTEM_THIN_MATERIAL_DARK", "SYSTEM_MATERIAL_DARK", "SYSTEM_THICK_MATERIAL_DARK", "SYSTEM_CHROME_MATERIAL_DARK", "expo-blur_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TintStyle implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TintStyle[] $VALUES;
    private final String value;
    public static final TintStyle DEFAULT = new TintStyle("DEFAULT", 0, Constants.COLLATION_DEFAULT);
    public static final TintStyle EXTRA_LIGHT = new TintStyle("EXTRA_LIGHT", 1, "extraLight");
    public static final TintStyle LIGHT = new TintStyle("LIGHT", 2, "light");
    public static final TintStyle DARK = new TintStyle("DARK", 3, "dark");
    public static final TintStyle REGULAR = new TintStyle("REGULAR", 4, "regular");
    public static final TintStyle PROMINENT = new TintStyle("PROMINENT", 5, "prominent");
    public static final TintStyle SYSTEM_ULTRA_THIN_MATERIAL = new TintStyle("SYSTEM_ULTRA_THIN_MATERIAL", 6, "systemUltraThinMaterial");
    public static final TintStyle SYSTEM_THIN_MATERIAL = new TintStyle("SYSTEM_THIN_MATERIAL", 7, "systemThinMaterial");
    public static final TintStyle SYSTEM_MATERIAL = new TintStyle("SYSTEM_MATERIAL", 8, "systemMaterial");
    public static final TintStyle SYSTEM_THICK_MATERIAL = new TintStyle("SYSTEM_THICK_MATERIAL", 9, "systemThickMaterial");
    public static final TintStyle SYSTEM_CHROME_MATERIAL = new TintStyle("SYSTEM_CHROME_MATERIAL", 10, "systemChromeMaterial");
    public static final TintStyle SYSTEM_ULTRA_THIN_MATERIAL_LIGHT = new TintStyle("SYSTEM_ULTRA_THIN_MATERIAL_LIGHT", 11, "systemUltraThinMaterialLight");
    public static final TintStyle SYSTEM_THICK_MATERIAL_LIGHT = new TintStyle("SYSTEM_THICK_MATERIAL_LIGHT", 12, "systemThickMaterialLight");
    public static final TintStyle SYSTEM_THIN_MATERIAL_LIGHT = new TintStyle("SYSTEM_THIN_MATERIAL_LIGHT", 13, "systemThinMaterialLight");
    public static final TintStyle SYSTEM_MATERIAL_LIGHT = new TintStyle("SYSTEM_MATERIAL_LIGHT", 14, "systemMaterialLight");
    public static final TintStyle SYSTEM_CHROME_MATERIAL_LIGHT = new TintStyle("SYSTEM_CHROME_MATERIAL_LIGHT", 15, "systemChromeMaterialLight");
    public static final TintStyle SYSTEM_ULTRA_THIN_MATERIAL_DARK = new TintStyle("SYSTEM_ULTRA_THIN_MATERIAL_DARK", 16, "systemUltraThinMaterialDark");
    public static final TintStyle SYSTEM_THIN_MATERIAL_DARK = new TintStyle("SYSTEM_THIN_MATERIAL_DARK", 17, "systemThinMaterialDark");
    public static final TintStyle SYSTEM_MATERIAL_DARK = new TintStyle("SYSTEM_MATERIAL_DARK", 18, "systemMaterialDark");
    public static final TintStyle SYSTEM_THICK_MATERIAL_DARK = new TintStyle("SYSTEM_THICK_MATERIAL_DARK", 19, "systemThickMaterialDark");
    public static final TintStyle SYSTEM_CHROME_MATERIAL_DARK = new TintStyle("SYSTEM_CHROME_MATERIAL_DARK", 20, "systemChromeMaterialDark");

    /* compiled from: TintStyle.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TintStyle.values().length];
            try {
                iArr[TintStyle.EXTRA_LIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TintStyle.LIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TintStyle.SYSTEM_MATERIAL_LIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TintStyle.SYSTEM_ULTRA_THIN_MATERIAL_LIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TintStyle.SYSTEM_THICK_MATERIAL_LIGHT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TintStyle.PROMINENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[TintStyle.DEFAULT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[TintStyle.SYSTEM_MATERIAL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[TintStyle.DARK.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[TintStyle.SYSTEM_MATERIAL_DARK.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[TintStyle.REGULAR.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[TintStyle.SYSTEM_ULTRA_THIN_MATERIAL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[TintStyle.SYSTEM_THICK_MATERIAL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[TintStyle.SYSTEM_CHROME_MATERIAL.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[TintStyle.SYSTEM_CHROME_MATERIAL_LIGHT.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[TintStyle.SYSTEM_THICK_MATERIAL_DARK.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[TintStyle.SYSTEM_THIN_MATERIAL_LIGHT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[TintStyle.SYSTEM_THIN_MATERIAL_DARK.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[TintStyle.SYSTEM_ULTRA_THIN_MATERIAL_DARK.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[TintStyle.SYSTEM_CHROME_MATERIAL_DARK.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[TintStyle.SYSTEM_THIN_MATERIAL.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final /* synthetic */ TintStyle[] $values() {
        return new TintStyle[]{DEFAULT, EXTRA_LIGHT, LIGHT, DARK, REGULAR, PROMINENT, SYSTEM_ULTRA_THIN_MATERIAL, SYSTEM_THIN_MATERIAL, SYSTEM_MATERIAL, SYSTEM_THICK_MATERIAL, SYSTEM_CHROME_MATERIAL, SYSTEM_ULTRA_THIN_MATERIAL_LIGHT, SYSTEM_THICK_MATERIAL_LIGHT, SYSTEM_THIN_MATERIAL_LIGHT, SYSTEM_MATERIAL_LIGHT, SYSTEM_CHROME_MATERIAL_LIGHT, SYSTEM_ULTRA_THIN_MATERIAL_DARK, SYSTEM_THIN_MATERIAL_DARK, SYSTEM_MATERIAL_DARK, SYSTEM_THICK_MATERIAL_DARK, SYSTEM_CHROME_MATERIAL_DARK};
    }

    public static EnumEntries<TintStyle> getEntries() {
        return $ENTRIES;
    }

    public static TintStyle valueOf(String str) {
        return (TintStyle) Enum.valueOf(TintStyle.class, str);
    }

    public static TintStyle[] values() {
        return (TintStyle[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    private TintStyle(String str, int i, String str2) {
        this.value = str2;
    }

    static {
        TintStyle[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public final int toBlurEffect(float blurRadius) {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return LIGHT.toColorInt(blurRadius);
            case 6:
            case 7:
            case 8:
                return DEFAULT.toColorInt(blurRadius);
            case 9:
            case 10:
                return DARK.toColorInt(blurRadius);
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return toColorInt(blurRadius);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final int toColorInt(float blurRadius) {
        int i;
        int i2;
        double d;
        double d2;
        double d3;
        int i3;
        float f = blurRadius / 100;
        int i4 = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i4 == 2) {
            i = ((int) ((255 * f) * 0.78d)) << 24;
            i2 = 16382457;
        } else if (i4 != 9) {
            switch (i4) {
                case 11:
                    i = ((int) ((255 * f) * 0.82d)) << 24;
                    i2 = 11776947;
                    break;
                case 12:
                    i = ((int) ((255 * f) * 0.44d)) << 24;
                    i2 = 12566463;
                    break;
                case 13:
                    i = ((int) ((255 * f) * 0.97d)) << 24;
                    i2 = 10066329;
                    break;
                case 14:
                    d = 255 * f * 0.75d;
                    return (((int) d) << 24) + ViewCompat.MEASURED_SIZE_MASK;
                case 15:
                    d = 255 * f * 0.97d;
                    return (((int) d) << 24) + ViewCompat.MEASURED_SIZE_MASK;
                case 16:
                    d2 = 255 * f;
                    d3 = 0.9d;
                    return (((int) (d2 * d3)) << 24) + 2434341;
                case 17:
                    i3 = (int) (255 * f * 0.78d);
                    return (i3 << 24) + 13092807;
                case 18:
                    d2 = 255 * f;
                    d3 = 0.7d;
                    return (((int) (d2 * d3)) << 24) + 2434341;
                case 19:
                    d2 = 255 * f;
                    d3 = 0.55d;
                    return (((int) (d2 * d3)) << 24) + 2434341;
                case 20:
                    return ((int) ((255 * f) * 0.75d)) << 24;
                case 21:
                    i3 = (int) (255 * f * 0.97d);
                    return (i3 << 24) + 13092807;
                default:
                    d = 255 * f * 0.44d;
                    return (((int) d) << 24) + ViewCompat.MEASURED_SIZE_MASK;
            }
        } else {
            i = ((int) ((255 * f) * 0.69d)) << 24;
            i2 = 1644825;
        }
        return i + i2;
    }
}

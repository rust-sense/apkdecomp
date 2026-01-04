package expo.modules.screenorientation.enums;

import expo.modules.core.errors.InvalidArgumentException;
import expo.modules.kotlin.types.Enumerable;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: OrientationAttr.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u001bB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001c"}, d2 = {"Lexpo/modules/screenorientation/enums/OrientationAttr;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "toOrientationLock", "Lexpo/modules/screenorientation/enums/OrientationLock;", "toOrientationLock$expo_screen_orientation_release", "Behind", "Landscape", "Portrait", "FullSensor", "Unspecified", "Locked", "FullUser", "NoSensor", "ReverseLandscape", "ReversePortrait", "Sensor", "SensorPortrait", "SensorLandscape", "User", "UserPortrait", "UserLandscape", "Companion", "expo-screen-orientation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OrientationAttr implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ OrientationAttr[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final int value;
    public static final OrientationAttr Behind = new OrientationAttr("Behind", 0, 3);
    public static final OrientationAttr Landscape = new OrientationAttr("Landscape", 1, 0);
    public static final OrientationAttr Portrait = new OrientationAttr("Portrait", 2, 1);
    public static final OrientationAttr FullSensor = new OrientationAttr("FullSensor", 3, 10);
    public static final OrientationAttr Unspecified = new OrientationAttr("Unspecified", 4, -1);
    public static final OrientationAttr Locked = new OrientationAttr("Locked", 5, 14);
    public static final OrientationAttr FullUser = new OrientationAttr("FullUser", 6, 13);
    public static final OrientationAttr NoSensor = new OrientationAttr("NoSensor", 7, 5);
    public static final OrientationAttr ReverseLandscape = new OrientationAttr("ReverseLandscape", 8, 8);
    public static final OrientationAttr ReversePortrait = new OrientationAttr("ReversePortrait", 9, 9);
    public static final OrientationAttr Sensor = new OrientationAttr("Sensor", 10, 4);
    public static final OrientationAttr SensorPortrait = new OrientationAttr("SensorPortrait", 11, 7);
    public static final OrientationAttr SensorLandscape = new OrientationAttr("SensorLandscape", 12, 6);
    public static final OrientationAttr User = new OrientationAttr("User", 13, 2);
    public static final OrientationAttr UserPortrait = new OrientationAttr("UserPortrait", 14, 12);
    public static final OrientationAttr UserLandscape = new OrientationAttr("UserLandscape", 15, 11);

    private static final /* synthetic */ OrientationAttr[] $values() {
        return new OrientationAttr[]{Behind, Landscape, Portrait, FullSensor, Unspecified, Locked, FullUser, NoSensor, ReverseLandscape, ReversePortrait, Sensor, SensorPortrait, SensorLandscape, User, UserPortrait, UserLandscape};
    }

    public static EnumEntries<OrientationAttr> getEntries() {
        return $ENTRIES;
    }

    public static OrientationAttr valueOf(String str) {
        return (OrientationAttr) Enum.valueOf(OrientationAttr.class, str);
    }

    public static OrientationAttr[] values() {
        return (OrientationAttr[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }

    private OrientationAttr(String str, int i, int i2) {
        this.value = i2;
    }

    static {
        OrientationAttr[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    public final OrientationLock toOrientationLock$expo_screen_orientation_release() {
        try {
            for (OrientationLock orientationLock : OrientationLock.values()) {
                if (Intrinsics.areEqual(orientationLock.name(), name())) {
                    return orientationLock;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        } catch (NoSuchElementException unused) {
            return OrientationLock.Other;
        }
    }

    /* compiled from: OrientationAttr.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/screenorientation/enums/OrientationAttr$Companion;", "", "()V", "fromInt", "Lexpo/modules/screenorientation/enums/OrientationAttr;", "value", "", "expo-screen-orientation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OrientationAttr fromInt(int value) throws InvalidArgumentException {
            try {
                for (OrientationAttr orientationAttr : OrientationAttr.values()) {
                    if (orientationAttr.getValue() == value) {
                        return orientationAttr;
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            } catch (NoSuchElementException unused) {
                throw new InvalidArgumentException("Platform orientation " + value + " is not a valid Android orientation attr");
            }
        }
    }
}

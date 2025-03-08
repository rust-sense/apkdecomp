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
/* compiled from: OrientationLock.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0017B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\r\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0018"}, d2 = {"Lexpo/modules/screenorientation/enums/OrientationLock;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "toOrientationAttr", "Lexpo/modules/screenorientation/enums/OrientationAttr;", "toOrientationAttr$expo_screen_orientation_release", "toPlatformInt", "toPlatformInt$expo_screen_orientation_release", "Unspecified", "FullSensor", "SensorPortrait", "Portrait", "ReversePortrait", "SensorLandscape", "ReverseLandscape", "Landscape", "Other", "Unknown", "Companion", "expo-screen-orientation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OrientationLock implements Enumerable {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ OrientationLock[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final int value;
    public static final OrientationLock Unspecified = new OrientationLock("Unspecified", 0, 0);
    public static final OrientationLock FullSensor = new OrientationLock("FullSensor", 1, 1);
    public static final OrientationLock SensorPortrait = new OrientationLock("SensorPortrait", 2, 2);
    public static final OrientationLock Portrait = new OrientationLock("Portrait", 3, 3);
    public static final OrientationLock ReversePortrait = new OrientationLock("ReversePortrait", 4, 4);
    public static final OrientationLock SensorLandscape = new OrientationLock("SensorLandscape", 5, 5);
    public static final OrientationLock ReverseLandscape = new OrientationLock("ReverseLandscape", 6, 6);
    public static final OrientationLock Landscape = new OrientationLock("Landscape", 7, 7);
    public static final OrientationLock Other = new OrientationLock("Other", 8, 8);
    public static final OrientationLock Unknown = new OrientationLock("Unknown", 9, 9);

    private static final /* synthetic */ OrientationLock[] $values() {
        return new OrientationLock[]{Unspecified, FullSensor, SensorPortrait, Portrait, ReversePortrait, SensorLandscape, ReverseLandscape, Landscape, Other, Unknown};
    }

    public static EnumEntries<OrientationLock> getEntries() {
        return $ENTRIES;
    }

    public static OrientationLock valueOf(String str) {
        return (OrientationLock) Enum.valueOf(OrientationLock.class, str);
    }

    public static OrientationLock[] values() {
        return (OrientationLock[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }

    private OrientationLock(String str, int i, int i2) {
        this.value = i2;
    }

    static {
        OrientationLock[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        INSTANCE = new Companion(null);
    }

    public final OrientationAttr toOrientationAttr$expo_screen_orientation_release() throws InvalidArgumentException {
        try {
            for (OrientationAttr orientationAttr : OrientationAttr.values()) {
                if (Intrinsics.areEqual(orientationAttr.name(), name())) {
                    return orientationAttr;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        } catch (NoSuchElementException unused) {
            throw new InvalidArgumentException("OrientationLock " + this + " is not mappable to a native Android orientation attr");
        }
    }

    public final int toPlatformInt$expo_screen_orientation_release() throws InvalidArgumentException {
        return toOrientationAttr$expo_screen_orientation_release().getValue();
    }

    /* compiled from: OrientationLock.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lexpo/modules/screenorientation/enums/OrientationLock$Companion;", "", "()V", "fromPlatformInt", "Lexpo/modules/screenorientation/enums/OrientationLock;", "value", "", "supportsOrientationLock", "", "expo-screen-orientation_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OrientationLock fromPlatformInt(int value) throws InvalidArgumentException {
            return OrientationAttr.INSTANCE.fromInt(value).toOrientationLock$expo_screen_orientation_release();
        }

        public final boolean supportsOrientationLock(int value) {
            for (OrientationLock orientationLock : OrientationLock.values()) {
                if (orientationLock.getValue() == value) {
                    return (value == OrientationLock.Other.getValue() || value == OrientationLock.Unknown.getValue()) ? false : true;
                }
            }
            return false;
        }
    }
}

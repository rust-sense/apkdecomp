package expo.modules.kotlin.events;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: EventName.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lexpo/modules/kotlin/events/EventName;", "", "(Ljava/lang/String;I)V", "MODULE_CREATE", "MODULE_DESTROY", "ACTIVITY_ENTERS_FOREGROUND", "ACTIVITY_ENTERS_BACKGROUND", "ACTIVITY_DESTROYS", "ON_NEW_INTENT", "ON_ACTIVITY_RESULT", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EventName {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ EventName[] $VALUES;
    public static final EventName MODULE_CREATE = new EventName("MODULE_CREATE", 0);
    public static final EventName MODULE_DESTROY = new EventName("MODULE_DESTROY", 1);
    public static final EventName ACTIVITY_ENTERS_FOREGROUND = new EventName("ACTIVITY_ENTERS_FOREGROUND", 2);
    public static final EventName ACTIVITY_ENTERS_BACKGROUND = new EventName("ACTIVITY_ENTERS_BACKGROUND", 3);
    public static final EventName ACTIVITY_DESTROYS = new EventName("ACTIVITY_DESTROYS", 4);
    public static final EventName ON_NEW_INTENT = new EventName("ON_NEW_INTENT", 5);
    public static final EventName ON_ACTIVITY_RESULT = new EventName("ON_ACTIVITY_RESULT", 6);

    private static final /* synthetic */ EventName[] $values() {
        return new EventName[]{MODULE_CREATE, MODULE_DESTROY, ACTIVITY_ENTERS_FOREGROUND, ACTIVITY_ENTERS_BACKGROUND, ACTIVITY_DESTROYS, ON_NEW_INTENT, ON_ACTIVITY_RESULT};
    }

    public static EnumEntries<EventName> getEntries() {
        return $ENTRIES;
    }

    public static EventName valueOf(String str) {
        return (EventName) Enum.valueOf(EventName.class, str);
    }

    public static EventName[] values() {
        return (EventName[]) $VALUES.clone();
    }

    private EventName(String str, int i) {
    }

    static {
        EventName[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}

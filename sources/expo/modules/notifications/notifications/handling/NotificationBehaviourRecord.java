package expo.modules.notifications.notifications.handling;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;

/* compiled from: NotificationsHandler.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\u000f\u0010\fR\u001c\u0010\u0010\u001a\u00020\t8\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\f¨\u0006\u0013"}, d2 = {"Lexpo/modules/notifications/notifications/handling/NotificationBehaviourRecord;", "Lexpo/modules/kotlin/records/Record;", "()V", SentryThread.JsonKeys.PRIORITY, "", "getPriority$annotations", "getPriority", "()Ljava/lang/String;", "shouldPlaySound", "", "getShouldPlaySound$annotations", "getShouldPlaySound", "()Z", "shouldSetBadge", "getShouldSetBadge$annotations", "getShouldSetBadge", "shouldShowAlert", "getShouldShowAlert$annotations", "getShouldShowAlert", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NotificationBehaviourRecord implements Record {
    private final String priority;
    private final boolean shouldPlaySound;
    private final boolean shouldSetBadge;
    private final boolean shouldShowAlert;

    @Field
    public static /* synthetic */ void getPriority$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldPlaySound$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldSetBadge$annotations() {
    }

    @Field
    public static /* synthetic */ void getShouldShowAlert$annotations() {
    }

    public final String getPriority() {
        return this.priority;
    }

    public final boolean getShouldPlaySound() {
        return this.shouldPlaySound;
    }

    public final boolean getShouldSetBadge() {
        return this.shouldSetBadge;
    }

    public final boolean getShouldShowAlert() {
        return this.shouldShowAlert;
    }
}

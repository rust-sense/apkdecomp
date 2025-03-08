package expo.modules.notifications;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Exceptions.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/notifications/NotificationWasAlreadyHandledException;", "Lexpo/modules/kotlin/exception/CodedException;", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NotificationWasAlreadyHandledException extends CodedException {
    private final String id;

    public final String getId() {
        return this.id;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationWasAlreadyHandledException(String id) {
        super("Failed to handle notification " + id + ", it has already been handled.", null, 2, null);
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }
}

package expo.modules.kotlin.events;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EventListener.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010\n\u001a\u00020\u0006R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lexpo/modules/kotlin/events/BasicEventListener;", "Lexpo/modules/kotlin/events/EventListener;", "eventName", "Lexpo/modules/kotlin/events/EventName;", "body", "Lkotlin/Function0;", "", "(Lexpo/modules/kotlin/events/EventName;Lkotlin/jvm/functions/Function0;)V", "getBody", "()Lkotlin/jvm/functions/Function0;", NotificationCompat.CATEGORY_CALL, "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BasicEventListener extends EventListener {
    private final Function0<Unit> body;

    public final Function0<Unit> getBody() {
        return this.body;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicEventListener(EventName eventName, Function0<Unit> body) {
        super(eventName, null);
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(body, "body");
        this.body = body;
    }

    public final void call() {
        this.body.invoke();
    }
}

package expo.modules.updates.statemachine;

import kotlin.Metadata;

/* compiled from: UpdatesStateChangeEventSender.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateChangeEventSender;", "", "sendUpdateStateChangeEventToAppContext", "", "eventType", "Lexpo/modules/updates/statemachine/UpdatesStateEventType;", "context", "Lexpo/modules/updates/statemachine/UpdatesStateContext;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UpdatesStateChangeEventSender {
    void sendUpdateStateChangeEventToAppContext(UpdatesStateEventType eventType, UpdatesStateContext context);
}
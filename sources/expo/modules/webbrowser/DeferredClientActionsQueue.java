package expo.modules.webbrowser;

import androidx.exifinterface.media.ExifInterface;
import expo.modules.core.interfaces.Consumer;
import java.util.LinkedList;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeferredClientActionsQueue.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0002J\u0006\u0010\f\u001a\u00020\nJ\u0014\u0010\r\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006J\b\u0010\u000f\u001a\u00020\nH\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u0013\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0002\u0010\u0013R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0014"}, d2 = {"Lexpo/modules/webbrowser/DeferredClientActionsQueue;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "actions", "Ljava/util/Queue;", "Lexpo/modules/core/interfaces/Consumer;", "client", "Ljava/lang/Object;", "addActionToQueue", "", "consumer", "clear", "executeOrQueueAction", "action", "executeQueuedActions", "hasClient", "", "setClient", "(Ljava/lang/Object;)V", "expo-web-browser_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DeferredClientActionsQueue<T> {
    private final Queue<Consumer<T>> actions = new LinkedList();
    private T client;

    public final boolean hasClient() {
        return this.client != null;
    }

    public final void setClient(T client) {
        this.client = client;
        executeQueuedActions();
    }

    public final void executeOrQueueAction(Consumer<T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        T t = this.client;
        if (t != null) {
            action.apply(t);
        } else {
            addActionToQueue(action);
        }
    }

    public final void clear() {
        this.client = null;
        this.actions.clear();
    }

    private final void executeQueuedActions() {
        if (this.client == null) {
            return;
        }
        Consumer<T> poll = this.actions.poll();
        while (poll != null) {
            poll.apply(this.client);
            poll = this.actions.poll();
        }
    }

    private final void addActionToQueue(Consumer<T> consumer) {
        this.actions.add(consumer);
    }
}

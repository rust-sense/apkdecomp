package expo.modules.updates.statemachine;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.procedures.StateMachineProcedure;
import expo.modules.updates.procedures.StateMachineSerialExecutorQueue;
import expo.modules.updates.statemachine.UpdatesStateEvent;
import io.sentry.SentryEvent;
import io.sentry.protocol.SentryThread;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesStateMachine.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u0015H\u0002J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateMachine;", "", "androidContext", "Landroid/content/Context;", "changeEventSender", "Lexpo/modules/updates/statemachine/UpdatesStateChangeEventSender;", "validUpdatesStateValues", "", "Lexpo/modules/updates/statemachine/UpdatesStateValue;", "(Landroid/content/Context;Lexpo/modules/updates/statemachine/UpdatesStateChangeEventSender;Ljava/util/Set;)V", "<set-?>", "Lexpo/modules/updates/statemachine/UpdatesStateContext;", "context", "getContext", "()Lexpo/modules/updates/statemachine/UpdatesStateContext;", SentryEvent.JsonKeys.LOGGER, "Lexpo/modules/updates/logging/UpdatesLogger;", "serialExecutorQueue", "Lexpo/modules/updates/procedures/StateMachineSerialExecutorQueue;", SentryThread.JsonKeys.STATE, "processEvent", "", NotificationCompat.CATEGORY_EVENT, "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "queueExecution", "stateMachineProcedure", "Lexpo/modules/updates/procedures/StateMachineProcedure;", "reset", "sendChangeEventToJS", "transition", "", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesStateMachine {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<UpdatesStateValue, Set<UpdatesStateEventType>> updatesStateAllowedEvents = MapsKt.mapOf(TuplesKt.to(UpdatesStateValue.Idle, SetsKt.setOf((Object[]) new UpdatesStateEventType[]{UpdatesStateEventType.Check, UpdatesStateEventType.Download, UpdatesStateEventType.Restart})), TuplesKt.to(UpdatesStateValue.Checking, SetsKt.setOf((Object[]) new UpdatesStateEventType[]{UpdatesStateEventType.CheckCompleteAvailable, UpdatesStateEventType.CheckCompleteUnavailable, UpdatesStateEventType.CheckError})), TuplesKt.to(UpdatesStateValue.Downloading, SetsKt.setOf((Object[]) new UpdatesStateEventType[]{UpdatesStateEventType.DownloadComplete, UpdatesStateEventType.DownloadError})), TuplesKt.to(UpdatesStateValue.Restarting, SetsKt.emptySet()));
    private static final Map<UpdatesStateEventType, UpdatesStateValue> updatesStateTransitions = MapsKt.mapOf(TuplesKt.to(UpdatesStateEventType.Check, UpdatesStateValue.Checking), TuplesKt.to(UpdatesStateEventType.CheckCompleteAvailable, UpdatesStateValue.Idle), TuplesKt.to(UpdatesStateEventType.CheckCompleteUnavailable, UpdatesStateValue.Idle), TuplesKt.to(UpdatesStateEventType.CheckError, UpdatesStateValue.Idle), TuplesKt.to(UpdatesStateEventType.Download, UpdatesStateValue.Downloading), TuplesKt.to(UpdatesStateEventType.DownloadComplete, UpdatesStateValue.Idle), TuplesKt.to(UpdatesStateEventType.DownloadError, UpdatesStateValue.Idle), TuplesKt.to(UpdatesStateEventType.Restart, UpdatesStateValue.Restarting));
    private final UpdatesStateChangeEventSender changeEventSender;
    private UpdatesStateContext context;
    private final UpdatesLogger logger;
    private final StateMachineSerialExecutorQueue serialExecutorQueue;
    private UpdatesStateValue state;
    private final Set<UpdatesStateValue> validUpdatesStateValues;

    public final UpdatesStateContext getContext() {
        return this.context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public UpdatesStateMachine(Context androidContext, UpdatesStateChangeEventSender changeEventSender, Set<? extends UpdatesStateValue> validUpdatesStateValues) {
        Intrinsics.checkNotNullParameter(androidContext, "androidContext");
        Intrinsics.checkNotNullParameter(changeEventSender, "changeEventSender");
        Intrinsics.checkNotNullParameter(validUpdatesStateValues, "validUpdatesStateValues");
        this.changeEventSender = changeEventSender;
        this.validUpdatesStateValues = validUpdatesStateValues;
        UpdatesLogger updatesLogger = new UpdatesLogger(androidContext);
        this.logger = updatesLogger;
        this.serialExecutorQueue = new StateMachineSerialExecutorQueue(updatesLogger, new StateMachineProcedure.StateMachineProcedureContext() { // from class: expo.modules.updates.statemachine.UpdatesStateMachine$serialExecutorQueue$1
            @Override // expo.modules.updates.procedures.StateMachineProcedure.StateMachineProcedureContext
            public void processStateEvent(UpdatesStateEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                UpdatesStateMachine.this.processEvent(event);
            }

            @Override // expo.modules.updates.procedures.StateMachineProcedure.StateMachineProcedureContext
            public UpdatesStateValue getCurrentState() {
                UpdatesStateValue updatesStateValue;
                updatesStateValue = UpdatesStateMachine.this.state;
                return updatesStateValue;
            }

            @Override // expo.modules.updates.procedures.StateMachineProcedure.StateMachineProcedureContext
            public void resetState() {
                UpdatesStateMachine.this.reset();
            }
        });
        this.state = UpdatesStateValue.Idle;
        this.context = new UpdatesStateContext(false, false, false, false, false, null, null, null, null, null, null, 2047, null);
    }

    public final void queueExecution(StateMachineProcedure stateMachineProcedure) {
        Intrinsics.checkNotNullParameter(stateMachineProcedure, "stateMachineProcedure");
        this.serialExecutorQueue.queueExecution(stateMachineProcedure);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reset() {
        this.state = UpdatesStateValue.Idle;
        UpdatesStateContext updatesStateContext = new UpdatesStateContext(false, false, false, false, false, null, null, null, null, null, null, 2047, null);
        this.context = updatesStateContext;
        UpdatesLogger.info$default(this.logger, "Updates state change: reset, context = " + updatesStateContext.getJson(), null, 2, null);
        sendChangeEventToJS(new UpdatesStateEvent.Restart());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processEvent(UpdatesStateEvent event) {
        if (transition(event)) {
            this.context = INSTANCE.reduceContext(this.context, event);
            UpdatesLogger.info$default(this.logger, "Updates state change: " + event.getType() + ", context = " + this.context.getJson(), null, 2, null);
            sendChangeEventToJS(event);
        }
    }

    private final boolean transition(UpdatesStateEvent event) {
        Set<UpdatesStateEventType> set = updatesStateAllowedEvents.get(this.state);
        if (set == null) {
            set = SetsKt.emptySet();
        }
        if (!set.contains(event.getType())) {
            return false;
        }
        UpdatesStateValue updatesStateValue = updatesStateTransitions.get(event.getType());
        if (updatesStateValue == null) {
            updatesStateValue = UpdatesStateValue.Idle;
        }
        if (!this.validUpdatesStateValues.contains(updatesStateValue)) {
            return false;
        }
        this.state = updatesStateValue;
        return true;
    }

    private final void sendChangeEventToJS(UpdatesStateEvent event) {
        UpdatesStateContext copy;
        UpdatesStateChangeEventSender updatesStateChangeEventSender = this.changeEventSender;
        UpdatesStateEventType type = event.getType();
        copy = r3.copy((r24 & 1) != 0 ? r3.isUpdateAvailable : false, (r24 & 2) != 0 ? r3.isUpdatePending : false, (r24 & 4) != 0 ? r3.isChecking : false, (r24 & 8) != 0 ? r3.isDownloading : false, (r24 & 16) != 0 ? r3.isRestarting : false, (r24 & 32) != 0 ? r3.latestManifest : null, (r24 & 64) != 0 ? r3.downloadedManifest : null, (r24 & 128) != 0 ? r3.rollback : null, (r24 & 256) != 0 ? r3.checkError : null, (r24 & 512) != 0 ? r3.downloadError : null, (r24 & 1024) != 0 ? this.context.lastCheckForUpdateTime : null);
        updatesStateChangeEventSender.sendUpdateStateChangeEventToAppContext(type, copy);
    }

    /* compiled from: UpdatesStateMachine.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R#\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0011"}, d2 = {"Lexpo/modules/updates/statemachine/UpdatesStateMachine$Companion;", "", "()V", "updatesStateAllowedEvents", "", "Lexpo/modules/updates/statemachine/UpdatesStateValue;", "", "Lexpo/modules/updates/statemachine/UpdatesStateEventType;", "getUpdatesStateAllowedEvents", "()Ljava/util/Map;", "updatesStateTransitions", "getUpdatesStateTransitions", "reduceContext", "Lexpo/modules/updates/statemachine/UpdatesStateContext;", "context", NotificationCompat.CATEGORY_EVENT, "Lexpo/modules/updates/statemachine/UpdatesStateEvent;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<UpdatesStateValue, Set<UpdatesStateEventType>> getUpdatesStateAllowedEvents() {
            return UpdatesStateMachine.updatesStateAllowedEvents;
        }

        public final Map<UpdatesStateEventType, UpdatesStateValue> getUpdatesStateTransitions() {
            return UpdatesStateMachine.updatesStateTransitions;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final UpdatesStateContext reduceContext(UpdatesStateContext context, UpdatesStateEvent event) {
            UpdatesStateContext copy;
            UpdatesStateContext copy2;
            UpdatesStateContext copy3;
            UpdatesStateContext copy4;
            UpdatesStateContext copy5;
            UpdatesStateContext copy6;
            UpdatesStateContext copy7;
            UpdatesStateContext copy8;
            UpdatesStateContext copy9;
            UpdatesStateContext copy10;
            UpdatesStateContext copy11;
            if (event instanceof UpdatesStateEvent.Check) {
                copy11 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : false, (r24 & 2) != 0 ? context.isUpdatePending : false, (r24 & 4) != 0 ? context.isChecking : true, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : null, (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : null);
                return copy11;
            }
            if (event instanceof UpdatesStateEvent.CheckCompleteUnavailable) {
                copy10 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : false, (r24 & 2) != 0 ? context.isUpdatePending : false, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : null, (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : new Date());
                return copy10;
            }
            if (event instanceof UpdatesStateEvent.CheckCompleteWithRollback) {
                copy9 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : true, (r24 & 2) != 0 ? context.isUpdatePending : false, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : null, (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : new UpdatesStateContextRollback(((UpdatesStateEvent.CheckCompleteWithRollback) event).getCommitTime()), (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : new Date());
                return copy9;
            }
            if (event instanceof UpdatesStateEvent.CheckCompleteWithUpdate) {
                copy8 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : true, (r24 & 2) != 0 ? context.isUpdatePending : false, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : ((UpdatesStateEvent.CheckCompleteWithUpdate) event).getManifest(), (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : new Date());
                return copy8;
            }
            if (event instanceof UpdatesStateEvent.CheckError) {
                copy7 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : false, (r24 & 2) != 0 ? context.isUpdatePending : false, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : null, (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : ((UpdatesStateEvent.CheckError) event).getError(), (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : new Date());
                return copy7;
            }
            if (event instanceof UpdatesStateEvent.Download) {
                copy6 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : false, (r24 & 2) != 0 ? context.isUpdatePending : false, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : true, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : null, (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : null);
                return copy6;
            }
            if (event instanceof UpdatesStateEvent.DownloadComplete) {
                copy5 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : false, (r24 & 2) != 0 ? context.isUpdatePending : true, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : null, (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : null);
                return copy5;
            }
            if (event instanceof UpdatesStateEvent.DownloadCompleteWithRollback) {
                copy4 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : false, (r24 & 2) != 0 ? context.isUpdatePending : true, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : null, (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : null);
                return copy4;
            }
            if (event instanceof UpdatesStateEvent.DownloadCompleteWithUpdate) {
                UpdatesStateEvent.DownloadCompleteWithUpdate downloadCompleteWithUpdate = (UpdatesStateEvent.DownloadCompleteWithUpdate) event;
                copy3 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : true, (r24 & 2) != 0 ? context.isUpdatePending : true, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : downloadCompleteWithUpdate.getManifest(), (r24 & 64) != 0 ? context.downloadedManifest : downloadCompleteWithUpdate.getManifest(), (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : null);
                return copy3;
            }
            if (event instanceof UpdatesStateEvent.DownloadError) {
                copy2 = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : false, (r24 & 2) != 0 ? context.isUpdatePending : false, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : false, (r24 & 32) != 0 ? context.latestManifest : null, (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : ((UpdatesStateEvent.DownloadError) event).getError(), (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : null);
                return copy2;
            }
            if (!(event instanceof UpdatesStateEvent.Restart)) {
                throw new NoWhenBranchMatchedException();
            }
            copy = context.copy((r24 & 1) != 0 ? context.isUpdateAvailable : false, (r24 & 2) != 0 ? context.isUpdatePending : false, (r24 & 4) != 0 ? context.isChecking : false, (r24 & 8) != 0 ? context.isDownloading : false, (r24 & 16) != 0 ? context.isRestarting : true, (r24 & 32) != 0 ? context.latestManifest : null, (r24 & 64) != 0 ? context.downloadedManifest : null, (r24 & 128) != 0 ? context.rollback : null, (r24 & 256) != 0 ? context.checkError : null, (r24 & 512) != 0 ? context.downloadError : null, (r24 & 1024) != 0 ? context.lastCheckForUpdateTime : null);
            return copy;
        }
    }
}

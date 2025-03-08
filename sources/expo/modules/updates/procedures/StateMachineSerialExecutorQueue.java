package expo.modules.updates.procedures;

import expo.modules.core.logging.LoggerTimer;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.procedures.StateMachineProcedure;
import expo.modules.updates.procedures.StateMachineSerialExecutorQueue;
import expo.modules.updates.statemachine.UpdatesStateEvent;
import expo.modules.updates.statemachine.UpdatesStateValue;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StateMachineSerialExecutorQueue.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lexpo/modules/updates/procedures/StateMachineSerialExecutorQueue;", "", "updatesLogger", "Lexpo/modules/updates/logging/UpdatesLogger;", "stateMachineProcedureContext", "Lexpo/modules/updates/procedures/StateMachineProcedure$StateMachineProcedureContext;", "(Lexpo/modules/updates/logging/UpdatesLogger;Lexpo/modules/updates/procedures/StateMachineProcedure$StateMachineProcedureContext;)V", "currentMethodInvocation", "Lexpo/modules/updates/procedures/StateMachineSerialExecutorQueue$MethodInvocationHolder;", "internalQueue", "Lkotlin/collections/ArrayDeque;", "maybeProcessQueue", "", "queueExecution", "stateMachineProcedure", "Lexpo/modules/updates/procedures/StateMachineProcedure;", "MethodInvocationHolder", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class StateMachineSerialExecutorQueue {
    private MethodInvocationHolder currentMethodInvocation;
    private final ArrayDeque<MethodInvocationHolder> internalQueue;
    private final StateMachineProcedure.StateMachineProcedureContext stateMachineProcedureContext;
    private final UpdatesLogger updatesLogger;

    public StateMachineSerialExecutorQueue(UpdatesLogger updatesLogger, StateMachineProcedure.StateMachineProcedureContext stateMachineProcedureContext) {
        Intrinsics.checkNotNullParameter(updatesLogger, "updatesLogger");
        Intrinsics.checkNotNullParameter(stateMachineProcedureContext, "stateMachineProcedureContext");
        this.updatesLogger = updatesLogger;
        this.stateMachineProcedureContext = stateMachineProcedureContext;
        this.internalQueue = new ArrayDeque<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: StateMachineSerialExecutorQueue.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B.\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\t¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001a\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tHÆ\u0003J8\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0019\b\u0002\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\"\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, d2 = {"Lexpo/modules/updates/procedures/StateMachineSerialExecutorQueue$MethodInvocationHolder;", "", "updatesLogger", "Lexpo/modules/updates/logging/UpdatesLogger;", "procedure", "Lexpo/modules/updates/procedures/StateMachineProcedure;", "onMethodInvocationComplete", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lexpo/modules/updates/logging/UpdatesLogger;Lexpo/modules/updates/procedures/StateMachineProcedure;Lkotlin/jvm/functions/Function1;)V", "getOnMethodInvocationComplete", "()Lkotlin/jvm/functions/Function1;", "getProcedure", "()Lexpo/modules/updates/procedures/StateMachineProcedure;", "getUpdatesLogger", "()Lexpo/modules/updates/logging/UpdatesLogger;", "component1", "component2", "component3", "copy", "equals", "", "other", "execute", "procedureContext", "Lexpo/modules/updates/procedures/StateMachineProcedure$StateMachineProcedureContext;", "hashCode", "", "toString", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final /* data */ class MethodInvocationHolder {
        private final Function1<MethodInvocationHolder, Unit> onMethodInvocationComplete;
        private final StateMachineProcedure procedure;
        private final UpdatesLogger updatesLogger;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MethodInvocationHolder copy$default(MethodInvocationHolder methodInvocationHolder, UpdatesLogger updatesLogger, StateMachineProcedure stateMachineProcedure, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                updatesLogger = methodInvocationHolder.updatesLogger;
            }
            if ((i & 2) != 0) {
                stateMachineProcedure = methodInvocationHolder.procedure;
            }
            if ((i & 4) != 0) {
                function1 = methodInvocationHolder.onMethodInvocationComplete;
            }
            return methodInvocationHolder.copy(updatesLogger, stateMachineProcedure, function1);
        }

        /* renamed from: component1, reason: from getter */
        public final UpdatesLogger getUpdatesLogger() {
            return this.updatesLogger;
        }

        /* renamed from: component2, reason: from getter */
        public final StateMachineProcedure getProcedure() {
            return this.procedure;
        }

        public final Function1<MethodInvocationHolder, Unit> component3() {
            return this.onMethodInvocationComplete;
        }

        public final MethodInvocationHolder copy(UpdatesLogger updatesLogger, StateMachineProcedure procedure, Function1<? super MethodInvocationHolder, Unit> onMethodInvocationComplete) {
            Intrinsics.checkNotNullParameter(updatesLogger, "updatesLogger");
            Intrinsics.checkNotNullParameter(procedure, "procedure");
            Intrinsics.checkNotNullParameter(onMethodInvocationComplete, "onMethodInvocationComplete");
            return new MethodInvocationHolder(updatesLogger, procedure, onMethodInvocationComplete);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MethodInvocationHolder)) {
                return false;
            }
            MethodInvocationHolder methodInvocationHolder = (MethodInvocationHolder) other;
            return Intrinsics.areEqual(this.updatesLogger, methodInvocationHolder.updatesLogger) && Intrinsics.areEqual(this.procedure, methodInvocationHolder.procedure) && Intrinsics.areEqual(this.onMethodInvocationComplete, methodInvocationHolder.onMethodInvocationComplete);
        }

        public final Function1<MethodInvocationHolder, Unit> getOnMethodInvocationComplete() {
            return this.onMethodInvocationComplete;
        }

        public final StateMachineProcedure getProcedure() {
            return this.procedure;
        }

        public final UpdatesLogger getUpdatesLogger() {
            return this.updatesLogger;
        }

        public int hashCode() {
            return (((this.updatesLogger.hashCode() * 31) + this.procedure.hashCode()) * 31) + this.onMethodInvocationComplete.hashCode();
        }

        public String toString() {
            return "MethodInvocationHolder(updatesLogger=" + this.updatesLogger + ", procedure=" + this.procedure + ", onMethodInvocationComplete=" + this.onMethodInvocationComplete + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public MethodInvocationHolder(UpdatesLogger updatesLogger, StateMachineProcedure procedure, Function1<? super MethodInvocationHolder, Unit> onMethodInvocationComplete) {
            Intrinsics.checkNotNullParameter(updatesLogger, "updatesLogger");
            Intrinsics.checkNotNullParameter(procedure, "procedure");
            Intrinsics.checkNotNullParameter(onMethodInvocationComplete, "onMethodInvocationComplete");
            this.updatesLogger = updatesLogger;
            this.procedure = procedure;
            this.onMethodInvocationComplete = onMethodInvocationComplete;
        }

        public final void execute(final StateMachineProcedure.StateMachineProcedureContext procedureContext) {
            Intrinsics.checkNotNullParameter(procedureContext, "procedureContext");
            final LoggerTimer startTimer = this.updatesLogger.startTimer(this.procedure.getLoggerTimerLabel());
            this.procedure.run(new StateMachineProcedure.ProcedureContext() { // from class: expo.modules.updates.procedures.StateMachineSerialExecutorQueue$MethodInvocationHolder$execute$1
                private boolean isCompleted;

                @Override // expo.modules.updates.procedures.StateMachineProcedure.ProcedureContext
                public void onComplete() {
                    this.isCompleted = true;
                    LoggerTimer.this.stop();
                    this.getOnMethodInvocationComplete().invoke(this);
                }

                @Override // expo.modules.updates.procedures.StateMachineProcedure.StateMachineProcedureContext
                public void processStateEvent(UpdatesStateEvent event) {
                    Intrinsics.checkNotNullParameter(event, "event");
                    if (this.isCompleted) {
                        throw new Exception("Cannot set state after procedure completion");
                    }
                    procedureContext.processStateEvent(event);
                }

                @Override // expo.modules.updates.procedures.StateMachineProcedure.StateMachineProcedureContext
                @Deprecated(message = "Avoid needing to access current state to know how to transition to next state")
                public UpdatesStateValue getCurrentState() {
                    if (this.isCompleted) {
                        throw new Exception("Cannot get state after procedure completion");
                    }
                    return procedureContext.getCurrentState();
                }

                @Override // expo.modules.updates.procedures.StateMachineProcedure.StateMachineProcedureContext
                public void resetState() {
                    if (this.isCompleted) {
                        throw new Exception("Cannot reset state after procedure completion");
                    }
                    procedureContext.resetState();
                }
            });
        }
    }

    public final void queueExecution(StateMachineProcedure stateMachineProcedure) {
        Intrinsics.checkNotNullParameter(stateMachineProcedure, "stateMachineProcedure");
        this.internalQueue.add(new MethodInvocationHolder(this.updatesLogger, stateMachineProcedure, new Function1<MethodInvocationHolder, Unit>() { // from class: expo.modules.updates.procedures.StateMachineSerialExecutorQueue$queueExecution$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(StateMachineSerialExecutorQueue.MethodInvocationHolder methodInvocationHolder) {
                invoke2(methodInvocationHolder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(StateMachineSerialExecutorQueue.MethodInvocationHolder $receiver) {
                StateMachineSerialExecutorQueue.MethodInvocationHolder methodInvocationHolder;
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                methodInvocationHolder = StateMachineSerialExecutorQueue.this.currentMethodInvocation;
                Intrinsics.areEqual(methodInvocationHolder, $receiver);
                StateMachineSerialExecutorQueue.this.currentMethodInvocation = null;
                StateMachineSerialExecutorQueue.this.maybeProcessQueue();
            }
        }));
        maybeProcessQueue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void maybeProcessQueue() {
        if (this.currentMethodInvocation != null) {
            return;
        }
        MethodInvocationHolder removeFirstOrNull = this.internalQueue.removeFirstOrNull();
        if (removeFirstOrNull == null) {
            return;
        }
        this.currentMethodInvocation = removeFirstOrNull;
        removeFirstOrNull.execute(this.stateMachineProcedureContext);
    }
}

package expo.modules.updates;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.events.EventEmitter;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.updates.IUpdatesController;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.launcher.NoDatabaseLauncher;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.procedures.RecreateReactContextProcedure;
import expo.modules.updates.statemachine.UpdatesStateChangeEventSender;
import expo.modules.updates.statemachine.UpdatesStateContext;
import expo.modules.updates.statemachine.UpdatesStateEventType;
import expo.modules.updates.statemachine.UpdatesStateMachine;
import expo.modules.updates.statemachine.UpdatesStateValue;
import io.sentry.SentryEvent;
import io.sentry.protocol.Message;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DisabledUpdatesController.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 U2\u00020\u00012\u00020\u0002:\u0002UVB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\u0016\u00104\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020807H\u0016J\u0016\u00109\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020:07H\u0016J\b\u0010;\u001a\u00020<H\u0016J\u0016\u0010=\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020>07H\u0016J\u0016\u0010?\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020@07H\u0016J\b\u0010A\u001a\u000205H\u0002J\u0010\u0010B\u001a\u0002052\u0006\u0010C\u001a\u00020DH\u0016J\u0010\u0010E\u001a\u0002052\u0006\u0010F\u001a\u00020GH\u0016J\u0010\u0010H\u001a\u0002052\u0006\u0010I\u001a\u00020\u0006H\u0016J\u0016\u0010J\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00020507H\u0016J\"\u0010K\u001a\u0002052\u0006\u0010L\u001a\u00020\u00112\u0006\u0010M\u001a\u00020\u00112\b\u0010N\u001a\u0004\u0018\u00010OH\u0002J\u0018\u0010P\u001a\u0002052\u0006\u0010M\u001a\u00020Q2\u0006\u0010\u0003\u001a\u00020@H\u0016J(\u0010R\u001a\u0002052\u0006\u0010S\u001a\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010\u00112\f\u00106\u001a\b\u0012\u0004\u0012\u00020507H\u0016J\b\u0010T\u001a\u000205H\u0016R\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0013R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010&\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001b@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001c\"\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0016\u00102\u001a\n\u0012\u0004\u0012\u000203\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lexpo/modules/updates/DisabledUpdatesController;", "Lexpo/modules/updates/IUpdatesController;", "Lexpo/modules/updates/statemachine/UpdatesStateChangeEventSender;", "context", "Landroid/content/Context;", "fatalException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Landroid/content/Context;Ljava/lang/Exception;)V", "appContext", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "getAppContext", "()Ljava/lang/ref/WeakReference;", "setAppContext", "(Ljava/lang/ref/WeakReference;)V", "bundleAssetName", "", "getBundleAssetName", "()Ljava/lang/String;", "eventEmitter", "Lexpo/modules/kotlin/events/EventEmitter;", "getEventEmitter", "()Lexpo/modules/kotlin/events/EventEmitter;", "setEventEmitter", "(Lexpo/modules/kotlin/events/EventEmitter;)V", "isActiveController", "", "()Z", "isLoaderTaskFinished", "isStarted", "launchAssetFile", "getLaunchAssetFile", "launcher", "Lexpo/modules/updates/launcher/Launcher;", SentryEvent.JsonKeys.LOGGER, "Lexpo/modules/updates/logging/UpdatesLogger;", "value", "shouldEmitJsEvents", "getShouldEmitJsEvents", "setShouldEmitJsEvents", "(Z)V", "stateMachine", "Lexpo/modules/updates/statemachine/UpdatesStateMachine;", "updatesDirectory", "Ljava/io/File;", "getUpdatesDirectory", "()Ljava/io/File;", "setUpdatesDirectory", "(Ljava/io/File;)V", "weakActivity", "Landroid/app/Activity;", "checkForUpdate", "", "callback", "Lexpo/modules/updates/IUpdatesController$ModuleCallback;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", "fetchUpdate", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", "getConstantsForModule", "Lexpo/modules/updates/IUpdatesController$UpdatesModuleConstants;", "getExtraParams", "Landroid/os/Bundle;", "getNativeStateMachineContext", "Lexpo/modules/updates/statemachine/UpdatesStateContext;", "notifyController", "onDidCreateDevSupportManager", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "onDidCreateReactInstance", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "onReactInstanceException", "exception", "relaunchReactApplicationForModule", "sendEventToJS", "eventName", "eventType", Message.JsonKeys.PARAMS, "Lcom/facebook/react/bridge/WritableMap;", "sendUpdateStateChangeEventToAppContext", "Lexpo/modules/updates/statemachine/UpdatesStateEventType;", "setExtraParam", "key", ViewProps.START, "Companion", "UpdatesDisabledException", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DisabledUpdatesController implements IUpdatesController, UpdatesStateChangeEventSender {
    private static final String TAG = "DisabledUpdatesController";
    private WeakReference<AppContext> appContext;
    private final Context context;
    private EventEmitter eventEmitter;
    private final Exception fatalException;
    private final boolean isActiveController;
    private boolean isLoaderTaskFinished;
    private boolean isStarted;
    private Launcher launcher;
    private final UpdatesLogger logger;
    private boolean shouldEmitJsEvents;
    private final UpdatesStateMachine stateMachine;
    private File updatesDirectory;
    private WeakReference<Activity> weakActivity;

    @Override // expo.modules.updates.IUpdatesController
    public WeakReference<AppContext> getAppContext() {
        return this.appContext;
    }

    @Override // expo.modules.updates.IUpdatesController
    public EventEmitter getEventEmitter() {
        return this.eventEmitter;
    }

    @Override // expo.modules.updates.IUpdatesController
    public boolean getShouldEmitJsEvents() {
        return this.shouldEmitJsEvents;
    }

    @Override // expo.modules.updates.IUpdatesController
    public File getUpdatesDirectory() {
        return this.updatesDirectory;
    }

    @Override // expo.modules.updates.IUpdatesController
    /* renamed from: isActiveController, reason: from getter */
    public boolean getIsActiveController() {
        return this.isActiveController;
    }

    @Override // expo.modules.updates.IUpdatesController
    public void onDidCreateDevSupportManager(DevSupportManager devSupportManager) {
        Intrinsics.checkNotNullParameter(devSupportManager, "devSupportManager");
    }

    @Override // expo.modules.updates.IUpdatesController
    public void onReactInstanceException(Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setAppContext(WeakReference<AppContext> weakReference) {
        this.appContext = weakReference;
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setEventEmitter(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
    }

    public void setUpdatesDirectory(File file) {
        this.updatesDirectory = file;
    }

    public DisabledUpdatesController(Context context, Exception exc) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.fatalException = exc;
        this.logger = new UpdatesLogger(context);
        this.stateMachine = new UpdatesStateMachine(context, this, SetsKt.setOf((Object[]) new UpdatesStateValue[]{UpdatesStateValue.Idle, UpdatesStateValue.Restarting}));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setShouldEmitJsEvents(boolean z) {
        this.shouldEmitJsEvents = z;
        UpdatesUtils.INSTANCE.sendQueuedEventsToAppContext(z, getAppContext(), this.logger);
    }

    @Override // expo.modules.updates.IUpdatesController
    public synchronized String getLaunchAssetFile() {
        Launcher launcher;
        while (!this.isLoaderTaskFinished) {
            try {
                Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                wait();
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted while waiting for launch asset file", e);
            }
        }
        launcher = this.launcher;
        return launcher != null ? launcher.getLaunchAssetFile() : null;
    }

    @Override // expo.modules.updates.IUpdatesController
    public String getBundleAssetName() {
        Launcher launcher = this.launcher;
        if (launcher != null) {
            return launcher.getBundleAssetName();
        }
        return null;
    }

    @Override // expo.modules.updates.IUpdatesController
    public void onDidCreateReactInstance(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.weakActivity = new WeakReference<>(reactContext.getCurrentActivity());
    }

    @Override // expo.modules.updates.IUpdatesController
    public synchronized void start() {
        if (this.isStarted) {
            return;
        }
        this.isStarted = true;
        this.launcher = new NoDatabaseLauncher(this.context, this.fatalException);
        notifyController();
    }

    /* compiled from: DisabledUpdatesController.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/updates/DisabledUpdatesController$UpdatesDisabledException;", "Lexpo/modules/kotlin/exception/CodedException;", "message", "", "(Ljava/lang/String;)V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class UpdatesDisabledException extends CodedException {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UpdatesDisabledException(String message) {
            super(message, null, 2, null);
            Intrinsics.checkNotNullParameter(message, "message");
        }
    }

    @Override // expo.modules.updates.IUpdatesController
    public IUpdatesController.UpdatesModuleConstants getConstantsForModule() {
        Launcher launcher = this.launcher;
        UpdateEntity launchedUpdate = launcher != null ? launcher.getLaunchedUpdate() : null;
        Exception exc = this.fatalException;
        Launcher launcher2 = this.launcher;
        boolean isUsingEmbeddedAssets = launcher2 != null ? launcher2.getIsUsingEmbeddedAssets() : false;
        UpdatesConfiguration.CheckAutomaticallyConfiguration checkAutomaticallyConfiguration = UpdatesConfiguration.CheckAutomaticallyConfiguration.NEVER;
        Map emptyMap = MapsKt.emptyMap();
        Launcher launcher3 = this.launcher;
        return new IUpdatesController.UpdatesModuleConstants(launchedUpdate, null, exc, false, isUsingEmbeddedAssets, null, checkAutomaticallyConfiguration, emptyMap, launcher3 != null ? launcher3.mo753getLocalAssetFiles() : null, false);
    }

    @Override // expo.modules.updates.IUpdatesController
    public void relaunchReactApplicationForModule(final IUpdatesController.ModuleCallback<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.stateMachine.queueExecution(new RecreateReactContextProcedure(this.context, this.weakActivity, new Launcher.LauncherCallback() { // from class: expo.modules.updates.DisabledUpdatesController$relaunchReactApplicationForModule$procedure$1
            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onFailure(Exception e) {
                UnexpectedException unexpectedException;
                Intrinsics.checkNotNullParameter(e, "e");
                IUpdatesController.ModuleCallback<Unit> moduleCallback = callback;
                Exception exc = e;
                if (exc instanceof CodedException) {
                    unexpectedException = (CodedException) exc;
                } else if (exc instanceof expo.modules.core.errors.CodedException) {
                    String code = ((expo.modules.core.errors.CodedException) exc).getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                    unexpectedException = new CodedException(code, exc.getMessage(), exc.getCause());
                } else {
                    unexpectedException = new UnexpectedException(exc);
                }
                moduleCallback.onFailure(unexpectedException);
            }

            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onSuccess() {
                callback.onSuccess(Unit.INSTANCE);
            }
        }));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void getNativeStateMachineContext(IUpdatesController.ModuleCallback<UpdatesStateContext> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onSuccess(this.stateMachine.getContext());
    }

    @Override // expo.modules.updates.IUpdatesController
    public void checkForUpdate(IUpdatesController.ModuleCallback<IUpdatesController.CheckForUpdateResult> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onFailure(new UpdatesDisabledException("Updates.checkForUpdateAsync() is not supported when expo-updates is not enabled."));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void fetchUpdate(IUpdatesController.ModuleCallback<IUpdatesController.FetchUpdateResult> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onFailure(new UpdatesDisabledException("Updates.fetchUpdateAsync() is not supported when expo-updates is not enabled."));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void getExtraParams(IUpdatesController.ModuleCallback<Bundle> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onFailure(new UpdatesDisabledException("Updates.getExtraParamsAsync() is not supported when expo-updates is not enabled."));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setExtraParam(String key, String value, IUpdatesController.ModuleCallback<Unit> callback) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onFailure(new UpdatesDisabledException("Updates.setExtraParamAsync() is not supported when expo-updates is not enabled."));
    }

    private final synchronized void notifyController() {
        if (this.launcher == null) {
            throw new AssertionError("UpdatesController.notifyController was called with a null launcher, which is an error. This method should only be called when an update is ready to launch.");
        }
        this.isLoaderTaskFinished = true;
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
        notify();
    }

    @Override // expo.modules.updates.statemachine.UpdatesStateChangeEventSender
    public void sendUpdateStateChangeEventToAppContext(UpdatesStateEventType eventType, UpdatesStateContext context) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        Intrinsics.checkNotNullParameter(context, "context");
        sendEventToJS(UpdatesJsEventsKt.UPDATES_STATE_CHANGE_EVENT_NAME, eventType.getType(), context.getWritableMap());
    }

    private final void sendEventToJS(String eventName, String eventType, WritableMap params) {
        UpdatesUtils.INSTANCE.sendEvent(getEventEmitter(), getShouldEmitJsEvents(), this.logger, eventName, eventType, params);
    }
}

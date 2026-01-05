package expo.modules.updates;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
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
import expo.modules.updates.db.BuildData;
import expo.modules.updates.db.DatabaseHolder;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.logging.UpdatesLogReader;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.EmbeddedManifestUtils;
import expo.modules.updates.manifest.EmbeddedUpdate;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.procedures.CheckForUpdateProcedure;
import expo.modules.updates.procedures.FetchUpdateProcedure;
import expo.modules.updates.procedures.RelaunchProcedure;
import expo.modules.updates.procedures.StartupProcedure;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import expo.modules.updates.selectionpolicy.SelectionPolicyFactory;
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
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EnabledUpdatesController.kt */
@Metadata(d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 h2\u00020\u00012\u00020\u0002:\u0001hB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0016\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020E0DH\u0016J\u0016\u0010F\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020G0DH\u0016J\b\u0010H\u001a\u00020IH\u0016J\u0016\u0010J\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020K0DH\u0016J\u0016\u0010L\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020M0DH\u0016J\u0010\u0010N\u001a\u00020B2\u0006\u0010O\u001a\u00020PH\u0016J\u0010\u0010Q\u001a\u00020B2\u0006\u0010R\u001a\u00020SH\u0016J\u0014\u0010T\u001a\u00020B2\n\u0010U\u001a\u00060Vj\u0002`WH\u0016J\b\u0010X\u001a\u00020BH\u0002J\b\u0010Y\u001a\u00020BH\u0002J\u0018\u0010Z\u001a\u00020B2\u0006\u0010[\u001a\u00020 2\u0006\u0010C\u001a\u00020\\H\u0002J\u0016\u0010]\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0DH\u0016J\"\u0010^\u001a\u00020B2\u0006\u0010_\u001a\u00020\u00122\u0006\u0010`\u001a\u00020\u00122\b\u0010a\u001a\u0004\u0018\u00010bH\u0002J\u0018\u0010c\u001a\u00020B2\u0006\u0010`\u001a\u00020d2\u0006\u0010\u0003\u001a\u00020MH\u0016J(\u0010e\u001a\u00020B2\u0006\u0010f\u001a\u00020\u00122\b\u00104\u001a\u0004\u0018\u00010\u00122\f\u0010C\u001a\b\u0012\u0004\u0012\u00020B0DH\u0016J\b\u0010g\u001a\u00020BH\u0016R\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020 X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010!R\u000e\u0010\"\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010!R\u0016\u0010%\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0014R\u0016\u0010'\u001a\u0004\u0018\u00010(8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\"\u0010+\u001a\u0010\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u0012\u0018\u00010,8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R$\u00105\u001a\u00020 2\u0006\u00104\u001a\u00020 @VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010!\"\u0004\b7\u00108R\u000e\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0016\u0010?\u001a\n\u0012\u0004\u0012\u00020@\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006i"}, d2 = {"Lexpo/modules/updates/EnabledUpdatesController;", "Lexpo/modules/updates/IUpdatesController;", "Lexpo/modules/updates/statemachine/UpdatesStateChangeEventSender;", "context", "Landroid/content/Context;", "updatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "updatesDirectory", "Ljava/io/File;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Ljava/io/File;)V", "appContext", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "getAppContext", "()Ljava/lang/ref/WeakReference;", "setAppContext", "(Ljava/lang/ref/WeakReference;)V", "bundleAssetName", "", "getBundleAssetName", "()Ljava/lang/String;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "eventEmitter", "Lexpo/modules/kotlin/events/EventEmitter;", "getEventEmitter", "()Lexpo/modules/kotlin/events/EventEmitter;", "setEventEmitter", "(Lexpo/modules/kotlin/events/EventEmitter;)V", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "isActiveController", "", "()Z", "isStarted", "isStartupFinished", "isUsingEmbeddedAssets", "launchAssetFile", "getLaunchAssetFile", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getLaunchedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "localAssetFiles", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "getLocalAssetFiles", "()Ljava/util/Map;", SentryEvent.JsonKeys.LOGGER, "Lexpo/modules/updates/logging/UpdatesLogger;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "value", "shouldEmitJsEvents", "getShouldEmitJsEvents", "setShouldEmitJsEvents", "(Z)V", "startupProcedure", "Lexpo/modules/updates/procedures/StartupProcedure;", "stateMachine", "Lexpo/modules/updates/statemachine/UpdatesStateMachine;", "getUpdatesDirectory", "()Ljava/io/File;", "weakActivity", "Landroid/app/Activity;", "checkForUpdate", "", "callback", "Lexpo/modules/updates/IUpdatesController$ModuleCallback;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", "fetchUpdate", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", "getConstantsForModule", "Lexpo/modules/updates/IUpdatesController$UpdatesModuleConstants;", "getExtraParams", "Landroid/os/Bundle;", "getNativeStateMachineContext", "Lexpo/modules/updates/statemachine/UpdatesStateContext;", "onDidCreateDevSupportManager", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "onDidCreateReactInstance", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "onReactInstanceException", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onStartupProcedureFinished", "purgeUpdatesLogsOlderThanOneDay", "relaunchReactApplication", "shouldRunReaper", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "relaunchReactApplicationForModule", "sendEventToJS", "eventName", "eventType", Message.JsonKeys.PARAMS, "Lcom/facebook/react/bridge/WritableMap;", "sendUpdateStateChangeEventToAppContext", "Lexpo/modules/updates/statemachine/UpdatesStateEventType;", "setExtraParam", "key", ViewProps.START, "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EnabledUpdatesController implements IUpdatesController, UpdatesStateChangeEventSender {
    private static final String TAG = "EnabledUpdatesController";
    private WeakReference<AppContext> appContext;
    private final Context context;
    private final DatabaseHolder databaseHolder;
    private EventEmitter eventEmitter;
    private final FileDownloader fileDownloader;
    private final boolean isActiveController;
    private boolean isStarted;
    private boolean isStartupFinished;
    private final UpdatesLogger logger;
    private final SelectionPolicy selectionPolicy;
    private boolean shouldEmitJsEvents;
    private final StartupProcedure startupProcedure;
    private final UpdatesStateMachine stateMachine;
    private final UpdatesConfiguration updatesConfiguration;
    private final File updatesDirectory;
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
    public void setAppContext(WeakReference<AppContext> weakReference) {
        this.appContext = weakReference;
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setEventEmitter(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
    }

    public EnabledUpdatesController(Context context, UpdatesConfiguration updatesConfiguration, File updatesDirectory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(updatesDirectory, "updatesDirectory");
        this.context = context;
        this.updatesConfiguration = updatesConfiguration;
        this.updatesDirectory = updatesDirectory;
        UpdatesLogger updatesLogger = new UpdatesLogger(context);
        this.logger = updatesLogger;
        FileDownloader fileDownloader = new FileDownloader(context, updatesConfiguration);
        this.fileDownloader = fileDownloader;
        SelectionPolicy createFilterAwarePolicy = SelectionPolicyFactory.createFilterAwarePolicy(updatesConfiguration.getRuntimeVersion());
        this.selectionPolicy = createFilterAwarePolicy;
        this.stateMachine = new UpdatesStateMachine(context, this, ArraysKt.toSet(UpdatesStateValue.values()));
        DatabaseHolder databaseHolder = new DatabaseHolder(UpdatesDatabase.INSTANCE.getInstance(context));
        this.databaseHolder = databaseHolder;
        this.startupProcedure = new StartupProcedure(context, updatesConfiguration, databaseHolder, getUpdatesDirectory(), fileDownloader, createFilterAwarePolicy, updatesLogger, new StartupProcedure.StartupProcedureCallback() { // from class: expo.modules.updates.EnabledUpdatesController$startupProcedure$1
            @Override // expo.modules.updates.procedures.StartupProcedure.StartupProcedureCallback
            public void onFinished() {
                EnabledUpdatesController.this.onStartupProcedureFinished();
            }

            @Override // expo.modules.updates.procedures.StartupProcedure.StartupProcedureCallback
            public void onRequestRelaunch(boolean shouldRunReaper, Launcher.LauncherCallback callback) {
                Intrinsics.checkNotNullParameter(callback, "callback");
                EnabledUpdatesController.this.relaunchReactApplication(shouldRunReaper, callback);
            }
        });
        this.isActiveController = true;
    }

    private final void purgeUpdatesLogsOlderThanOneDay() {
        UpdatesLogReader.purgeLogEntries$default(new UpdatesLogReader(this.context), null, new Function1<Error, Unit>() { // from class: expo.modules.updates.EnabledUpdatesController$purgeUpdatesLogsOlderThanOneDay$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Error error) {
                invoke2(error);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Error error) {
                String str;
                if (error != null) {
                    str = EnabledUpdatesController.TAG;
                    Log.e(str, "UpdatesLogReader: error in purgeLogEntries", error);
                }
            }
        }, 1, null);
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setShouldEmitJsEvents(boolean z) {
        this.shouldEmitJsEvents = z;
        UpdatesUtils.INSTANCE.sendQueuedEventsToAppContext(z, getAppContext(), this.logger);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void onStartupProcedureFinished() {
        this.isStartupFinished = true;
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
        notify();
        UpdatesUtils.INSTANCE.sendQueuedEventsToAppContext(getShouldEmitJsEvents(), getAppContext(), this.logger);
    }

    private final UpdateEntity getLaunchedUpdate() {
        return this.startupProcedure.getLaunchedUpdate();
    }

    private final boolean isUsingEmbeddedAssets() {
        return this.startupProcedure.isUsingEmbeddedAssets();
    }

    private final Map<AssetEntity, String> getLocalAssetFiles() {
        return this.startupProcedure.getLocalAssetFiles();
    }

    @Override // expo.modules.updates.IUpdatesController
    public synchronized String getLaunchAssetFile() {
        while (!this.isStartupFinished) {
            try {
                Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                wait();
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted while waiting for launch asset file", e);
            }
        }
        return this.startupProcedure.getLaunchAssetFile();
    }

    @Override // expo.modules.updates.IUpdatesController
    public String getBundleAssetName() {
        return this.startupProcedure.getBundleAssetName();
    }

    @Override // expo.modules.updates.IUpdatesController
    public void onDidCreateDevSupportManager(DevSupportManager devSupportManager) {
        Intrinsics.checkNotNullParameter(devSupportManager, "devSupportManager");
        this.startupProcedure.onDidCreateDevSupportManager(devSupportManager);
    }

    @Override // expo.modules.updates.IUpdatesController
    public void onDidCreateReactInstance(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.weakActivity = new WeakReference<>(reactContext.getCurrentActivity());
    }

    @Override // expo.modules.updates.IUpdatesController
    public void onReactInstanceException(Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.startupProcedure.onReactInstanceException(exception);
    }

    @Override // expo.modules.updates.IUpdatesController
    public synchronized void start() {
        if (this.isStarted) {
            return;
        }
        this.isStarted = true;
        purgeUpdatesLogsOlderThanOneDay();
        BuildData.INSTANCE.ensureBuildDataIsConsistent(this.updatesConfiguration, this.databaseHolder.getDatabase());
        this.databaseHolder.releaseDatabase();
        this.stateMachine.queueExecution(this.startupProcedure);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void relaunchReactApplication(boolean shouldRunReaper, Launcher.LauncherCallback callback) {
        this.stateMachine.queueExecution(new RelaunchProcedure(this.context, this.weakActivity, this.updatesConfiguration, this.databaseHolder, getUpdatesDirectory(), this.fileDownloader, this.selectionPolicy, new Function0<Launcher>() { // from class: expo.modules.updates.EnabledUpdatesController$relaunchReactApplication$procedure$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Launcher invoke() {
                StartupProcedure startupProcedure;
                startupProcedure = EnabledUpdatesController.this.startupProcedure;
                Launcher launcher = startupProcedure.getLauncher();
                Intrinsics.checkNotNull(launcher);
                return launcher;
            }
        }, new Function1<Launcher, Unit>() { // from class: expo.modules.updates.EnabledUpdatesController$relaunchReactApplication$procedure$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Launcher launcher) {
                invoke2(launcher);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Launcher currentLauncher) {
                StartupProcedure startupProcedure;
                Intrinsics.checkNotNullParameter(currentLauncher, "currentLauncher");
                startupProcedure = EnabledUpdatesController.this.startupProcedure;
                startupProcedure.setLauncher(currentLauncher);
            }
        }, shouldRunReaper, callback));
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

    @Override // expo.modules.updates.IUpdatesController
    public IUpdatesController.UpdatesModuleConstants getConstantsForModule() {
        UpdateEntity launchedUpdate = getLaunchedUpdate();
        EmbeddedUpdate embeddedUpdate = EmbeddedManifestUtils.INSTANCE.getEmbeddedUpdate(this.context, this.updatesConfiguration);
        return new IUpdatesController.UpdatesModuleConstants(launchedUpdate, embeddedUpdate != null ? embeddedUpdate.getUpdateEntity() : null, this.startupProcedure.getEmergencyLaunchException(), true, isUsingEmbeddedAssets(), this.updatesConfiguration.getRuntimeVersionRaw(), this.updatesConfiguration.getCheckOnLaunch(), this.updatesConfiguration.getRequestHeaders(), getLocalAssetFiles(), false);
    }

    @Override // expo.modules.updates.IUpdatesController
    public void relaunchReactApplicationForModule(final IUpdatesController.ModuleCallback<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (getLaunchedUpdate() == null) {
            callback.onFailure(new CodedException() { // from class: expo.modules.updates.EnabledUpdatesController$relaunchReactApplicationForModule$1
            });
        } else {
            relaunchReactApplication(true, new Launcher.LauncherCallback() { // from class: expo.modules.updates.EnabledUpdatesController$relaunchReactApplicationForModule$2
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
            });
        }
    }

    @Override // expo.modules.updates.IUpdatesController
    public void getNativeStateMachineContext(IUpdatesController.ModuleCallback<UpdatesStateContext> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onSuccess(this.stateMachine.getContext());
    }

    @Override // expo.modules.updates.IUpdatesController
    public void checkForUpdate(final IUpdatesController.ModuleCallback<IUpdatesController.CheckForUpdateResult> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.stateMachine.queueExecution(new CheckForUpdateProcedure(this.context, this.updatesConfiguration, this.databaseHolder, this.logger, this.fileDownloader, this.selectionPolicy, getLaunchedUpdate(), new Function1<IUpdatesController.CheckForUpdateResult, Unit>() { // from class: expo.modules.updates.EnabledUpdatesController$checkForUpdate$procedure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IUpdatesController.CheckForUpdateResult checkForUpdateResult) {
                invoke2(checkForUpdateResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IUpdatesController.CheckForUpdateResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                callback.onSuccess(it);
            }
        }));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void fetchUpdate(final IUpdatesController.ModuleCallback<IUpdatesController.FetchUpdateResult> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.stateMachine.queueExecution(new FetchUpdateProcedure(this.context, this.updatesConfiguration, this.databaseHolder, getUpdatesDirectory(), this.fileDownloader, this.selectionPolicy, getLaunchedUpdate(), new Function1<IUpdatesController.FetchUpdateResult, Unit>() { // from class: expo.modules.updates.EnabledUpdatesController$fetchUpdate$procedure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IUpdatesController.FetchUpdateResult fetchUpdateResult) {
                invoke2(fetchUpdateResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IUpdatesController.FetchUpdateResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                callback.onSuccess(it);
            }
        }));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void getExtraParams(final IUpdatesController.ModuleCallback<Bundle> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.EnabledUpdatesController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                EnabledUpdatesController.getExtraParams$lambda$2(EnabledUpdatesController.this, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getExtraParams$lambda$2(EnabledUpdatesController this$0, IUpdatesController.ModuleCallback callback) {
        UnexpectedException unexpectedException;
        Bundle bundle;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        try {
            Map<String, String> extraParams = ManifestMetadata.INSTANCE.getExtraParams(this$0.databaseHolder.getDatabase(), this$0.updatesConfiguration);
            this$0.databaseHolder.releaseDatabase();
            if (extraParams == null) {
                bundle = new Bundle();
            } else {
                Bundle bundle2 = new Bundle();
                for (Map.Entry<String, String> entry : extraParams.entrySet()) {
                    bundle2.putString(entry.getKey(), entry.getValue());
                }
                bundle = bundle2;
            }
            callback.onSuccess(bundle);
        } catch (Exception e) {
            this$0.databaseHolder.releaseDatabase();
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
            callback.onFailure(unexpectedException);
        }
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setExtraParam(final String key, final String value, final IUpdatesController.ModuleCallback<Unit> callback) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.EnabledUpdatesController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EnabledUpdatesController.setExtraParam$lambda$3(EnabledUpdatesController.this, key, value, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setExtraParam$lambda$3(EnabledUpdatesController this$0, String key, String str, IUpdatesController.ModuleCallback callback) {
        UnexpectedException unexpectedException;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(key, "$key");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        try {
            ManifestMetadata.INSTANCE.setExtraParam(this$0.databaseHolder.getDatabase(), this$0.updatesConfiguration, key, str);
            this$0.databaseHolder.releaseDatabase();
            callback.onSuccess(Unit.INSTANCE);
        } catch (Exception e) {
            this$0.databaseHolder.releaseDatabase();
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
            callback.onFailure(unexpectedException);
        }
    }
}

package expo.modules.updates;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.events.EventEmitter;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.updates.IUpdatesController;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.DatabaseHolder;
import expo.modules.updates.db.Reaper;
import expo.modules.updates.db.UpdatesDatabase;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.launcher.DatabaseLauncher;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.RemoteLoader;
import expo.modules.updates.loader.UpdateDirective;
import expo.modules.updates.loader.UpdateResponse;
import expo.modules.updates.loader.UpdateResponsePart;
import expo.modules.updates.manifest.Update;
import expo.modules.updates.selectionpolicy.LauncherSelectionPolicySingleUpdate;
import expo.modules.updates.selectionpolicy.ReaperSelectionPolicyDevelopmentClient;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import expo.modules.updates.selectionpolicy.SelectionPolicyFactory;
import expo.modules.updates.statemachine.UpdatesStateContext;
import expo.modules.updatesinterface.UpdatesInterface;
import expo.modules.updatesinterface.UpdatesInterfaceCallbacks;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: UpdatesDevLauncherController.kt */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 r2\u00020\u00012\u00020\u0002:\u0002rsB1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\t\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000b¢\u0006\u0002\u0010\fJ\u0016\u0010C\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020G0FH\u0016J4\u0010H\u001a\u00020\u00062\"\u0010I\u001a\u001e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020K0Jj\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020K`L2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0016\u0010M\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020N0FH\u0016J<\u0010O\u001a\u00020D2\"\u0010I\u001a\u001e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020K0Jj\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020K`L2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010E\u001a\u00020PH\u0016J\b\u0010Q\u001a\u00020RH\u0016J\b\u0010S\u001a\u00020TH\u0002J\u0016\u0010U\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020V0FH\u0016J\u0016\u0010W\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020X0FH\u0016J4\u0010Y\u001a\u00020#2\"\u0010I\u001a\u001e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020K0Jj\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020K`L2\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J0\u0010Z\u001a\u00020D2\u0006\u0010[\u001a\u00020)2\u0006\u0010I\u001a\u00020\u00062\u0006\u0010\\\u001a\u00020]2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010E\u001a\u00020PH\u0002J\u0010\u0010^\u001a\u00020D2\u0006\u0010_\u001a\u00020`H\u0016J\u0010\u0010a\u001a\u00020D2\u0006\u0010b\u001a\u00020cH\u0016J\u0010\u0010d\u001a\u00020D2\u0006\u0010e\u001a\u00020\nH\u0016J\u0016\u0010f\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020D0FH\u0016J\b\u0010g\u001a\u00020DH\u0002J\b\u0010h\u001a\u00020DH\u0016J\b\u0010i\u001a\u00020DH\u0002J\b\u0010j\u001a\u00020DH\u0002J\u0010\u0010k\u001a\u00020D2\u0006\u00105\u001a\u00020\u001bH\u0002J\b\u0010l\u001a\u00020DH\u0002J(\u0010m\u001a\u00020D2\u0006\u0010n\u001a\u00020\u00152\b\u0010o\u001a\u0004\u0018\u00010\u00152\f\u0010E\u001a\b\u0012\u0004\u0012\u00020D0FH\u0016J\u0012\u0010p\u001a\u00020D2\b\u00105\u001a\u0004\u0018\u00010\u001bH\u0002J\b\u0010q\u001a\u00020DH\u0016R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010$R\u0014\u0010%\u001a\u00020#8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010$R\u0014\u0010&\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0017R\u0013\u0010(\u001a\u0004\u0018\u00010)8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010.\u001a\u0010\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0015\u0018\u00010/8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0010\u00103\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u00020#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010$\"\u0004\b:\u0010;R\u0010\u0010<\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0016\u0010\t\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010?\u001a\n\u0012\u0004\u0012\u00020@\u0018\u00010\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0011\"\u0004\bB\u0010\u0013¨\u0006t"}, d2 = {"Lexpo/modules/updates/UpdatesDevLauncherController;", "Lexpo/modules/updates/IUpdatesController;", "Lexpo/modules/updatesinterface/UpdatesInterface;", "context", "Landroid/content/Context;", "initialUpdatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "updatesDirectory", "Ljava/io/File;", "updatesDirectoryException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Ljava/io/File;Ljava/lang/Exception;)V", "appContext", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "getAppContext", "()Ljava/lang/ref/WeakReference;", "setAppContext", "(Ljava/lang/ref/WeakReference;)V", "bundleAssetName", "", "getBundleAssetName", "()Ljava/lang/String;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "defaultSelectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "eventEmitter", "Lexpo/modules/kotlin/events/EventEmitter;", "getEventEmitter", "()Lexpo/modules/kotlin/events/EventEmitter;", "setEventEmitter", "(Lexpo/modules/kotlin/events/EventEmitter;)V", "isActiveController", "", "()Z", "isUsingEmbeddedAssets", "launchAssetFile", "getLaunchAssetFile", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getLaunchedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "launcher", "Lexpo/modules/updates/launcher/Launcher;", "localAssetFiles", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "getLocalAssetFiles", "()Ljava/util/Map;", "mSelectionPolicy", "previousUpdatesConfiguration", "selectionPolicy", "getSelectionPolicy", "()Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "shouldEmitJsEvents", "getShouldEmitJsEvents", "setShouldEmitJsEvents", "(Z)V", "updatesConfiguration", "getUpdatesDirectory", "()Ljava/io/File;", "updatesInterfaceCallbacks", "Lexpo/modules/updatesinterface/UpdatesInterfaceCallbacks;", "getUpdatesInterfaceCallbacks", "setUpdatesInterfaceCallbacks", "checkForUpdate", "", "callback", "Lexpo/modules/updates/IUpdatesController$ModuleCallback;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", "createUpdatesConfiguration", "configuration", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "fetchUpdate", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", "fetchUpdateWithConfiguration", "Lexpo/modules/updatesinterface/UpdatesInterface$UpdateCallback;", "getConstantsForModule", "Lexpo/modules/updates/IUpdatesController$UpdatesModuleConstants;", "getDatabase", "Lexpo/modules/updates/db/UpdatesDatabase;", "getExtraParams", "Landroid/os/Bundle;", "getNativeStateMachineContext", "Lexpo/modules/updates/statemachine/UpdatesStateContext;", "isValidUpdatesConfiguration", "launchUpdate", "update", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "onDidCreateDevSupportManager", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "onDidCreateReactInstance", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "onReactInstanceException", "exception", "relaunchReactApplicationForModule", "releaseDatabase", "reset", "resetSelectionPolicyToDefault", "runReaper", "setDefaultSelectionPolicy", "setDevelopmentSelectionPolicy", "setExtraParam", "key", "value", "setNextSelectionPolicy", ViewProps.START, "Companion", "NotAvailableInDevClientException", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesDevLauncherController implements IUpdatesController, UpdatesInterface {
    private static final String TAG = "UpdatesDevLauncherController";
    private WeakReference<AppContext> appContext;
    private final Context context;
    private final DatabaseHolder databaseHolder;
    private SelectionPolicy defaultSelectionPolicy;
    private EventEmitter eventEmitter;
    private final boolean isActiveController;
    private Launcher launcher;
    private SelectionPolicy mSelectionPolicy;
    private UpdatesConfiguration previousUpdatesConfiguration;
    private boolean shouldEmitJsEvents;
    private UpdatesConfiguration updatesConfiguration;
    private final File updatesDirectory;
    private final Exception updatesDirectoryException;
    private WeakReference<UpdatesInterfaceCallbacks> updatesInterfaceCallbacks;

    /* compiled from: UpdatesDevLauncherController.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[UpdatesConfigurationValidationResult.values().length];
            try {
                iArr[UpdatesConfigurationValidationResult.VALID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[UpdatesConfigurationValidationResult.INVALID_NOT_ENABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[UpdatesConfigurationValidationResult.INVALID_MISSING_URL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[UpdatesConfigurationValidationResult.INVALID_MISSING_RUNTIME_VERSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final SelectionPolicy getSelectionPolicy() {
        SelectionPolicy selectionPolicy = this.mSelectionPolicy;
        return selectionPolicy == null ? this.defaultSelectionPolicy : selectionPolicy;
    }

    private final void resetSelectionPolicyToDefault() {
        this.mSelectionPolicy = null;
    }

    private final void setDefaultSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.defaultSelectionPolicy = selectionPolicy;
    }

    private final void setNextSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.mSelectionPolicy = selectionPolicy;
    }

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

    @Override // expo.modules.updatesinterface.UpdatesInterface
    public WeakReference<UpdatesInterfaceCallbacks> getUpdatesInterfaceCallbacks() {
        return this.updatesInterfaceCallbacks;
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
    public void onDidCreateReactInstance(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
    }

    @Override // expo.modules.updates.IUpdatesController
    public void onReactInstanceException(Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
    }

    @Override // expo.modules.updatesinterface.UpdatesInterface
    public void reset() {
        this.launcher = null;
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setAppContext(WeakReference<AppContext> weakReference) {
        this.appContext = weakReference;
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setEventEmitter(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setShouldEmitJsEvents(boolean z) {
        this.shouldEmitJsEvents = z;
    }

    @Override // expo.modules.updatesinterface.UpdatesInterface
    public void setUpdatesInterfaceCallbacks(WeakReference<UpdatesInterfaceCallbacks> weakReference) {
        this.updatesInterfaceCallbacks = weakReference;
    }

    @Override // expo.modules.updates.IUpdatesController
    public void start() {
    }

    public UpdatesDevLauncherController(Context context, UpdatesConfiguration updatesConfiguration, File file, Exception exc) {
        String runtimeVersion;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.updatesDirectory = file;
        this.updatesDirectoryException = exc;
        this.updatesConfiguration = updatesConfiguration;
        this.databaseHolder = new DatabaseHolder(UpdatesDatabase.INSTANCE.getInstance(context));
        this.defaultSelectionPolicy = SelectionPolicyFactory.createFilterAwarePolicy((updatesConfiguration == null || (runtimeVersion = updatesConfiguration.getRuntimeVersion()) == null) ? "1" : runtimeVersion);
    }

    @Override // expo.modules.updates.IUpdatesController
    public synchronized String getLaunchAssetFile() {
        throw new Exception("IUpdatesController.launchAssetFile should not be called in dev client");
    }

    @Override // expo.modules.updates.IUpdatesController
    public String getBundleAssetName() {
        throw new Exception("IUpdatesController.bundleAssetName should not be called in dev client");
    }

    public final UpdateEntity getLaunchedUpdate() {
        Launcher launcher = this.launcher;
        if (launcher != null) {
            return launcher.getLaunchedUpdate();
        }
        return null;
    }

    private final Map<AssetEntity, String> getLocalAssetFiles() {
        Launcher launcher = this.launcher;
        if (launcher != null) {
            return launcher.mo753getLocalAssetFiles();
        }
        return null;
    }

    private final boolean isUsingEmbeddedAssets() {
        Launcher launcher = this.launcher;
        if (launcher != null) {
            return launcher.getIsUsingEmbeddedAssets();
        }
        return false;
    }

    @Override // expo.modules.updatesinterface.UpdatesInterface
    public void fetchUpdateWithConfiguration(HashMap<String, Object> configuration, final Context context, final UpdatesInterface.UpdateCallback callback) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            UpdatesConfiguration createUpdatesConfiguration = createUpdatesConfiguration(configuration, context);
            if (getUpdatesDirectory() == null) {
                throw new IllegalStateException("Check failed.".toString());
            }
            this.previousUpdatesConfiguration = this.updatesConfiguration;
            this.updatesConfiguration = createUpdatesConfiguration;
            setDevelopmentSelectionPolicy();
            UpdatesConfiguration updatesConfiguration = this.updatesConfiguration;
            Intrinsics.checkNotNull(updatesConfiguration);
            final FileDownloader fileDownloader = new FileDownloader(context, updatesConfiguration);
            UpdatesConfiguration updatesConfiguration2 = this.updatesConfiguration;
            Intrinsics.checkNotNull(updatesConfiguration2);
            new RemoteLoader(context, updatesConfiguration2, this.databaseHolder.getDatabase(), fileDownloader, getUpdatesDirectory(), null).start(new Loader.LoaderCallback() { // from class: expo.modules.updates.UpdatesDevLauncherController$fetchUpdateWithConfiguration$1
                @Override // expo.modules.updates.loader.Loader.LoaderCallback
                public void onFailure(Exception e) {
                    DatabaseHolder databaseHolder;
                    UpdatesConfiguration updatesConfiguration3;
                    Intrinsics.checkNotNullParameter(e, "e");
                    databaseHolder = UpdatesDevLauncherController.this.databaseHolder;
                    databaseHolder.releaseDatabase();
                    UpdatesDevLauncherController updatesDevLauncherController = UpdatesDevLauncherController.this;
                    updatesConfiguration3 = updatesDevLauncherController.previousUpdatesConfiguration;
                    updatesDevLauncherController.updatesConfiguration = updatesConfiguration3;
                    callback.onFailure(e);
                }

                @Override // expo.modules.updates.loader.Loader.LoaderCallback
                public void onSuccess(Loader.LoaderResult loaderResult) {
                    DatabaseHolder databaseHolder;
                    UpdatesConfiguration updatesConfiguration3;
                    Intrinsics.checkNotNullParameter(loaderResult, "loaderResult");
                    databaseHolder = UpdatesDevLauncherController.this.databaseHolder;
                    databaseHolder.releaseDatabase();
                    if (loaderResult.getUpdateEntity() == null) {
                        callback.onSuccess(null);
                        return;
                    }
                    UpdatesDevLauncherController updatesDevLauncherController = UpdatesDevLauncherController.this;
                    UpdateEntity updateEntity = loaderResult.getUpdateEntity();
                    updatesConfiguration3 = UpdatesDevLauncherController.this.updatesConfiguration;
                    Intrinsics.checkNotNull(updatesConfiguration3);
                    updatesDevLauncherController.launchUpdate(updateEntity, updatesConfiguration3, fileDownloader, context, callback);
                }

                @Override // expo.modules.updates.loader.Loader.LoaderCallback
                public void onAssetLoaded(AssetEntity asset, int successfulAssetCount, int failedAssetCount, int totalAssetCount) {
                    Intrinsics.checkNotNullParameter(asset, "asset");
                    callback.onProgress(successfulAssetCount, failedAssetCount, totalAssetCount);
                }

                @Override // expo.modules.updates.loader.Loader.LoaderCallback
                public Loader.OnUpdateResponseLoadedResult onUpdateResponseLoaded(UpdateResponse updateResponse) {
                    Update update;
                    Intrinsics.checkNotNullParameter(updateResponse, "updateResponse");
                    UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart = updateResponse.getDirectiveUpdateResponsePart();
                    UpdateDirective updateDirective = directiveUpdateResponsePart != null ? directiveUpdateResponsePart.getUpdateDirective() : null;
                    if (updateDirective != null) {
                        if ((updateDirective instanceof UpdateDirective.RollBackToEmbeddedUpdateDirective) || (updateDirective instanceof UpdateDirective.NoUpdateAvailableUpdateDirective)) {
                            return new Loader.OnUpdateResponseLoadedResult(false);
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                    UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart = updateResponse.getManifestUpdateResponsePart();
                    if (manifestUpdateResponsePart == null || (update = manifestUpdateResponsePart.getUpdate()) == null) {
                        return new Loader.OnUpdateResponseLoadedResult(false);
                    }
                    return new Loader.OnUpdateResponseLoadedResult(callback.onManifestLoaded(update.getManifest().getRawJson()));
                }
            });
        } catch (Exception e) {
            callback.onFailure(e);
        }
    }

    @Override // expo.modules.updatesinterface.UpdatesInterface
    public boolean isValidUpdatesConfiguration(HashMap<String, Object> configuration, Context context) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            createUpdatesConfiguration(configuration, context);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Invalid updates configuration: " + e.getLocalizedMessage());
            return false;
        }
    }

    private final UpdatesConfiguration createUpdatesConfiguration(HashMap<String, Object> configuration, Context context) throws Exception {
        if (getUpdatesDirectory() == null) {
            Exception exc = this.updatesDirectoryException;
            Intrinsics.checkNotNull(exc);
            throw exc;
        }
        HashMap<String, Object> hashMap = configuration;
        int i = WhenMappings.$EnumSwitchMapping$0[UpdatesConfiguration.INSTANCE.getUpdatesConfigurationValidationResult(context, hashMap).ordinal()];
        if (i == 1) {
            return new UpdatesConfiguration(context, hashMap);
        }
        if (i == 2) {
            throw new Exception("Failed to load update: UpdatesConfiguration object is not enabled");
        }
        if (i == 3) {
            throw new Exception("Failed to load update: UpdatesConfiguration object must include a valid update URL");
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        throw new Exception("Failed to load update: UpdatesConfiguration object must include a valid runtime version");
    }

    private final void setDevelopmentSelectionPolicy() {
        resetSelectionPolicyToDefault();
        SelectionPolicy selectionPolicy = getSelectionPolicy();
        setDefaultSelectionPolicy(new SelectionPolicy(selectionPolicy.getLauncherSelectionPolicy(), selectionPolicy.getLoaderSelectionPolicy(), new ReaperSelectionPolicyDevelopmentClient(0, 1, null)));
        resetSelectionPolicyToDefault();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchUpdate(UpdateEntity update, UpdatesConfiguration configuration, FileDownloader fileDownloader, Context context, final UpdatesInterface.UpdateCallback callback) {
        SelectionPolicy selectionPolicy = getSelectionPolicy();
        setNextSelectionPolicy(new SelectionPolicy(new LauncherSelectionPolicySingleUpdate(update.getId()), selectionPolicy.getLoaderSelectionPolicy(), selectionPolicy.getReaperSelectionPolicy()));
        File updatesDirectory = getUpdatesDirectory();
        Intrinsics.checkNotNull(updatesDirectory);
        final DatabaseLauncher databaseLauncher = new DatabaseLauncher(configuration, updatesDirectory, fileDownloader, getSelectionPolicy());
        databaseLauncher.launch(this.databaseHolder.getDatabase(), context, new Launcher.LauncherCallback() { // from class: expo.modules.updates.UpdatesDevLauncherController$launchUpdate$1
            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onFailure(Exception e) {
                DatabaseHolder databaseHolder;
                UpdatesConfiguration updatesConfiguration;
                Intrinsics.checkNotNullParameter(e, "e");
                databaseHolder = UpdatesDevLauncherController.this.databaseHolder;
                databaseHolder.releaseDatabase();
                UpdatesDevLauncherController updatesDevLauncherController = UpdatesDevLauncherController.this;
                updatesConfiguration = updatesDevLauncherController.previousUpdatesConfiguration;
                updatesDevLauncherController.updatesConfiguration = updatesConfiguration;
                callback.onFailure(e);
            }

            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onSuccess() {
                DatabaseHolder databaseHolder;
                databaseHolder = UpdatesDevLauncherController.this.databaseHolder;
                databaseHolder.releaseDatabase();
                UpdatesDevLauncherController.this.launcher = databaseLauncher;
                UpdatesInterface.UpdateCallback updateCallback = callback;
                final DatabaseLauncher databaseLauncher2 = databaseLauncher;
                updateCallback.onSuccess(new UpdatesInterface.Update() { // from class: expo.modules.updates.UpdatesDevLauncherController$launchUpdate$1$onSuccess$1
                    @Override // expo.modules.updatesinterface.UpdatesInterface.Update
                    public JSONObject getManifest() {
                        UpdateEntity launchedUpdate = DatabaseLauncher.this.getLaunchedUpdate();
                        Intrinsics.checkNotNull(launchedUpdate);
                        return launchedUpdate.getManifest();
                    }

                    @Override // expo.modules.updatesinterface.UpdatesInterface.Update
                    public String getLaunchAssetPath() {
                        String launchAssetFile = DatabaseLauncher.this.getLaunchAssetFile();
                        Intrinsics.checkNotNull(launchAssetFile);
                        return launchAssetFile;
                    }
                });
                UpdatesDevLauncherController.this.runReaper();
            }
        });
    }

    private final UpdatesDatabase getDatabase() {
        return this.databaseHolder.getDatabase();
    }

    private final void releaseDatabase() {
        this.databaseHolder.releaseDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runReaper() {
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.UpdatesDevLauncherController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpdatesDevLauncherController.runReaper$lambda$1(UpdatesDevLauncherController.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runReaper$lambda$1(UpdatesDevLauncherController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UpdatesConfiguration updatesConfiguration = this$0.updatesConfiguration;
        if (updatesConfiguration != null) {
            Reaper.reapUnusedUpdates(updatesConfiguration, this$0.getDatabase(), this$0.getUpdatesDirectory(), this$0.getLaunchedUpdate(), this$0.getSelectionPolicy());
            this$0.releaseDatabase();
        }
    }

    /* compiled from: UpdatesDevLauncherController.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/updates/UpdatesDevLauncherController$NotAvailableInDevClientException;", "Lexpo/modules/kotlin/exception/CodedException;", "message", "", "(Ljava/lang/String;)V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NotAvailableInDevClientException extends CodedException {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotAvailableInDevClientException(String message) {
            super(message, null, 2, null);
            Intrinsics.checkNotNullParameter(message, "message");
        }
    }

    @Override // expo.modules.updates.IUpdatesController
    public IUpdatesController.UpdatesModuleConstants getConstantsForModule() {
        String str;
        UpdatesConfiguration.CheckAutomaticallyConfiguration checkAutomaticallyConfiguration;
        Map<String, String> emptyMap;
        UpdateEntity launchedUpdate = getLaunchedUpdate();
        Exception exc = this.updatesDirectoryException;
        boolean isUsingEmbeddedAssets = isUsingEmbeddedAssets();
        UpdatesConfiguration updatesConfiguration = this.updatesConfiguration;
        if (updatesConfiguration == null || (str = updatesConfiguration.getRuntimeVersionRaw()) == null) {
            str = "1";
        }
        String str2 = str;
        UpdatesConfiguration updatesConfiguration2 = this.updatesConfiguration;
        if (updatesConfiguration2 == null || (checkAutomaticallyConfiguration = updatesConfiguration2.getCheckOnLaunch()) == null) {
            checkAutomaticallyConfiguration = UpdatesConfiguration.CheckAutomaticallyConfiguration.ALWAYS;
        }
        UpdatesConfiguration.CheckAutomaticallyConfiguration checkAutomaticallyConfiguration2 = checkAutomaticallyConfiguration;
        UpdatesConfiguration updatesConfiguration3 = this.updatesConfiguration;
        if (updatesConfiguration3 == null || (emptyMap = updatesConfiguration3.getRequestHeaders()) == null) {
            emptyMap = MapsKt.emptyMap();
        }
        return new IUpdatesController.UpdatesModuleConstants(launchedUpdate, null, exc, true, isUsingEmbeddedAssets, str2, checkAutomaticallyConfiguration2, emptyMap, getLocalAssetFiles(), true);
    }

    @Override // expo.modules.updates.IUpdatesController
    public void relaunchReactApplicationForModule(IUpdatesController.ModuleCallback<Unit> callback) {
        UpdatesInterfaceCallbacks updatesInterfaceCallbacks;
        Intrinsics.checkNotNullParameter(callback, "callback");
        WeakReference<UpdatesInterfaceCallbacks> updatesInterfaceCallbacks2 = getUpdatesInterfaceCallbacks();
        if (updatesInterfaceCallbacks2 != null && (updatesInterfaceCallbacks = updatesInterfaceCallbacks2.get()) != null) {
            updatesInterfaceCallbacks.onRequestRelaunch();
        }
        callback.onSuccess(Unit.INSTANCE);
    }

    @Override // expo.modules.updates.IUpdatesController
    public void getNativeStateMachineContext(IUpdatesController.ModuleCallback<UpdatesStateContext> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onSuccess(new UpdatesStateContext(false, false, false, false, false, null, null, null, null, null, null, 2047, null));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void checkForUpdate(IUpdatesController.ModuleCallback<IUpdatesController.CheckForUpdateResult> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onFailure(new NotAvailableInDevClientException("Updates.checkForUpdateAsync() is not supported in development builds."));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void fetchUpdate(IUpdatesController.ModuleCallback<IUpdatesController.FetchUpdateResult> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onFailure(new NotAvailableInDevClientException("Updates.fetchUpdateAsync() is not supported in development builds."));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void getExtraParams(IUpdatesController.ModuleCallback<Bundle> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onFailure(new NotAvailableInDevClientException("Updates.getExtraParamsAsync() is not supported in development builds."));
    }

    @Override // expo.modules.updates.IUpdatesController
    public void setExtraParam(String key, String value, IUpdatesController.ModuleCallback<Unit> callback) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onFailure(new NotAvailableInDevClientException("Updates.setExtraParamAsync() is not supported in development builds."));
    }
}

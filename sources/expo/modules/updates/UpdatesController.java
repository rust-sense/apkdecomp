package expo.modules.updates;

import android.content.Context;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.events.EventEmitter;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogger;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesController.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lexpo/modules/updates/UpdatesController;", "", "()V", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UpdatesController {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static UpdatesConfiguration overrideConfiguration;
    private static IUpdatesController singletonInstance;

    public static final IUpdatesController getInstance() {
        return INSTANCE.getInstance();
    }

    @JvmStatic
    public static final void initialize(Context context) {
        INSTANCE.initialize(context);
    }

    @JvmStatic
    public static final void initializeWithoutStarting(Context context) {
        INSTANCE.initializeWithoutStarting(context);
    }

    @JvmStatic
    public static final void overrideConfiguration(Context context, Map<String, ? extends Object> map) {
        INSTANCE.overrideConfiguration(context, map);
    }

    /* compiled from: UpdatesController.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0000¢\u0006\u0002\b\u0019J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J$\u0010\b\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00010!H\u0007R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lexpo/modules/updates/UpdatesController$Companion;", "", "()V", "instance", "Lexpo/modules/updates/IUpdatesController;", "getInstance$annotations", "getInstance", "()Lexpo/modules/updates/IUpdatesController;", "overrideConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "value", "", "shouldEmitJsEvents", "getShouldEmitJsEvents$expo_updates_release", "()Z", "setShouldEmitJsEvents$expo_updates_release", "(Z)V", "singletonInstance", "bindAppContext", "", "appContext", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "eventEmitter", "Lexpo/modules/kotlin/events/EventEmitter;", "bindAppContext$expo_updates_release", "initialize", "context", "Landroid/content/Context;", "initializeAsDevLauncherWithoutStarting", "Lexpo/modules/updates/UpdatesDevLauncherController;", "initializeWithoutStarting", "configuration", "", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {

        /* compiled from: UpdatesController.kt */
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

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final IUpdatesController getInstance() {
            IUpdatesController iUpdatesController = UpdatesController.singletonInstance;
            if (iUpdatesController != null) {
                return iUpdatesController;
            }
            throw new IllegalStateException("UpdatesController.instance was called before the module was initialized".toString());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final void initializeWithoutStarting(Context context) {
            DisabledUpdatesController disabledUpdatesController;
            ReactNativeHost reactNativeHost;
            Intrinsics.checkNotNullParameter(context, "context");
            if (UpdatesController.singletonInstance != null) {
                return;
            }
            ReactApplication reactApplication = context instanceof ReactApplication ? (ReactApplication) context : null;
            if (reactApplication != null && (reactNativeHost = reactApplication.getReactNativeHost()) != null && reactNativeHost.getUseDeveloperSupport()) {
                UpdatesController.singletonInstance = new DisabledUpdatesController(context, null);
                return;
            }
            UpdatesLogger updatesLogger = new UpdatesLogger(context);
            try {
                File orCreateUpdatesDirectory = UpdatesUtils.INSTANCE.getOrCreateUpdatesDirectory(context);
                UpdatesConfiguration updatesConfiguration = UpdatesController.overrideConfiguration;
                if (updatesConfiguration == null) {
                    int i = WhenMappings.$EnumSwitchMapping$0[UpdatesConfiguration.INSTANCE.getUpdatesConfigurationValidationResult(context, null).ordinal()];
                    if (i != 1) {
                        if (i == 2) {
                            updatesLogger.warn("The expo-updates system is explicitly disabled. To enable it, set the enabled setting to true.", UpdatesErrorCode.InitializationError);
                        } else if (i == 3) {
                            updatesLogger.warn("The expo-updates system is disabled due to an invalid configuration. Ensure a valid URL is supplied.", UpdatesErrorCode.InitializationError);
                        } else if (i == 4) {
                            updatesLogger.warn("The expo-updates system is disabled due to an invalid configuration. Ensure a runtime version is supplied.", UpdatesErrorCode.InitializationError);
                        }
                        updatesConfiguration = null;
                    } else {
                        updatesConfiguration = new UpdatesConfiguration(context, null);
                    }
                }
                if (updatesConfiguration != null) {
                    disabledUpdatesController = new EnabledUpdatesController(context, updatesConfiguration, orCreateUpdatesDirectory);
                } else {
                    disabledUpdatesController = new DisabledUpdatesController(context, null);
                }
                UpdatesController.singletonInstance = disabledUpdatesController;
            } catch (Exception e) {
                UpdatesLogger.error$default(updatesLogger, "The expo-updates system is disabled due to a storage access error: " + e.getMessage(), UpdatesErrorCode.InitializationError, null, 4, null);
                UpdatesController.singletonInstance = new DisabledUpdatesController(context, e);
            }
        }

        private final UpdatesDevLauncherController initializeAsDevLauncherWithoutStarting(Context context) {
            Exception exc;
            File file;
            if (UpdatesController.singletonInstance != null) {
                throw new IllegalStateException("UpdatesController must not be initialized prior to calling initializeAsDevLauncherWithoutStarting".toString());
            }
            try {
                file = UpdatesUtils.INSTANCE.getOrCreateUpdatesDirectory(context);
                exc = null;
            } catch (Exception e) {
                exc = e;
                file = null;
            }
            UpdatesConfiguration updatesConfiguration = UpdatesController.overrideConfiguration;
            if (updatesConfiguration == null) {
                updatesConfiguration = UpdatesConfiguration.INSTANCE.getUpdatesConfigurationValidationResult(context, null) == UpdatesConfigurationValidationResult.VALID ? new UpdatesConfiguration(context, null) : null;
            }
            UpdatesDevLauncherController updatesDevLauncherController = new UpdatesDevLauncherController(context, updatesConfiguration, file, exc);
            UpdatesController.singletonInstance = updatesDevLauncherController;
            return updatesDevLauncherController;
        }

        @JvmStatic
        public final void initialize(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (UpdatesController.singletonInstance == null) {
                initializeWithoutStarting(context);
                IUpdatesController iUpdatesController = UpdatesController.singletonInstance;
                Intrinsics.checkNotNull(iUpdatesController);
                iUpdatesController.start();
            }
        }

        @JvmStatic
        public final void overrideConfiguration(Context context, Map<String, ? extends Object> configuration) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            if (UpdatesController.singletonInstance != null) {
                throw new AssertionError("The method should be called before UpdatesController.initialize()");
            }
            UpdatesConfigurationValidationResult updatesConfigurationValidationResult = UpdatesConfiguration.INSTANCE.getUpdatesConfigurationValidationResult(context, configuration);
            if (updatesConfigurationValidationResult == UpdatesConfigurationValidationResult.VALID) {
                UpdatesController.overrideConfiguration = new UpdatesConfiguration(context, configuration);
                return;
            }
            UpdatesLogger.warn$default(new UpdatesLogger(context), "Failed to overrideConfiguration: invalid configuration: " + updatesConfigurationValidationResult.name(), null, 2, null);
        }

        public final boolean getShouldEmitJsEvents$expo_updates_release() {
            IUpdatesController iUpdatesController = UpdatesController.singletonInstance;
            if (iUpdatesController != null) {
                return iUpdatesController.getShouldEmitJsEvents();
            }
            return false;
        }

        public final void setShouldEmitJsEvents$expo_updates_release(boolean z) {
            IUpdatesController iUpdatesController = UpdatesController.singletonInstance;
            if (iUpdatesController != null) {
                iUpdatesController.setShouldEmitJsEvents(z);
            }
        }

        public final void bindAppContext$expo_updates_release(WeakReference<AppContext> appContext, EventEmitter eventEmitter) {
            Intrinsics.checkNotNullParameter(appContext, "appContext");
            IUpdatesController iUpdatesController = UpdatesController.singletonInstance;
            if (iUpdatesController != null) {
                iUpdatesController.setAppContext(appContext);
                iUpdatesController.setEventEmitter(eventEmitter);
            }
        }
    }
}

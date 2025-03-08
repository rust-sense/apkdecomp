package expo.modules.updates;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.messaging.Constants;
import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.events.EventEmitter;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.db.entity.AssetEntity;
import expo.modules.updates.db.entity.UpdateEntity;
import expo.modules.updates.loader.LoaderTask;
import expo.modules.updates.manifest.Update;
import expo.modules.updates.statemachine.UpdatesStateContext;
import io.sentry.clientreport.DiscardedEvent;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IUpdatesController.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001:\u0004;<=>J\u0016\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H&J\u0016\u0010%\u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020&0#H&J\b\u0010'\u001a\u00020(H&J\u0016\u0010)\u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020*0#H&J\u0016\u0010+\u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020,0#H&J\u0010\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020/H&J\u0010\u00100\u001a\u00020!2\u0006\u00101\u001a\u000202H&J\u0010\u00103\u001a\u00020!2\u0006\u00104\u001a\u000205H&J\u0016\u00106\u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0#H&J(\u00107\u001a\u00020!2\u0006\u00108\u001a\u00020\n2\b\u00109\u001a\u0004\u0018\u00010\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0#H&J\b\u0010:\u001a\u00020!H&R \u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u0004\u0018\u00010\u000eX¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\fR\u0018\u0010\u0018\u001a\u00020\u0014X¦\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001dX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006?"}, d2 = {"Lexpo/modules/updates/IUpdatesController;", "", "appContext", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "getAppContext", "()Ljava/lang/ref/WeakReference;", "setAppContext", "(Ljava/lang/ref/WeakReference;)V", "bundleAssetName", "", "getBundleAssetName", "()Ljava/lang/String;", "eventEmitter", "Lexpo/modules/kotlin/events/EventEmitter;", "getEventEmitter", "()Lexpo/modules/kotlin/events/EventEmitter;", "setEventEmitter", "(Lexpo/modules/kotlin/events/EventEmitter;)V", "isActiveController", "", "()Z", "launchAssetFile", "getLaunchAssetFile", "shouldEmitJsEvents", "getShouldEmitJsEvents", "setShouldEmitJsEvents", "(Z)V", "updatesDirectory", "Ljava/io/File;", "getUpdatesDirectory", "()Ljava/io/File;", "checkForUpdate", "", "callback", "Lexpo/modules/updates/IUpdatesController$ModuleCallback;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", "fetchUpdate", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", "getConstantsForModule", "Lexpo/modules/updates/IUpdatesController$UpdatesModuleConstants;", "getExtraParams", "Landroid/os/Bundle;", "getNativeStateMachineContext", "Lexpo/modules/updates/statemachine/UpdatesStateContext;", "onDidCreateDevSupportManager", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "onDidCreateReactInstance", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "onReactInstanceException", "exception", "Ljava/lang/Exception;", "relaunchReactApplicationForModule", "setExtraParam", "key", "value", ViewProps.START, "CheckForUpdateResult", "FetchUpdateResult", "ModuleCallback", "UpdatesModuleConstants", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface IUpdatesController {

    /* compiled from: IUpdatesController.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/updates/IUpdatesController$ModuleCallback;", ExifInterface.GPS_DIRECTION_TRUE, "", "onFailure", "", "exception", "Lexpo/modules/kotlin/exception/CodedException;", "onSuccess", "result", "(Ljava/lang/Object;)V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ModuleCallback<T> {
        void onFailure(CodedException exception);

        void onSuccess(T result);
    }

    void checkForUpdate(ModuleCallback<CheckForUpdateResult> callback);

    void fetchUpdate(ModuleCallback<FetchUpdateResult> callback);

    WeakReference<AppContext> getAppContext();

    String getBundleAssetName();

    UpdatesModuleConstants getConstantsForModule();

    EventEmitter getEventEmitter();

    void getExtraParams(ModuleCallback<Bundle> callback);

    String getLaunchAssetFile();

    void getNativeStateMachineContext(ModuleCallback<UpdatesStateContext> callback);

    boolean getShouldEmitJsEvents();

    File getUpdatesDirectory();

    boolean isActiveController();

    void onDidCreateDevSupportManager(DevSupportManager devSupportManager);

    void onDidCreateReactInstance(ReactContext reactContext);

    void onReactInstanceException(Exception exception);

    void relaunchReactApplicationForModule(ModuleCallback<Unit> callback);

    void setAppContext(WeakReference<AppContext> weakReference);

    void setEventEmitter(EventEmitter eventEmitter);

    void setExtraParam(String key, String value, ModuleCallback<Unit> callback);

    void setShouldEmitJsEvents(boolean z);

    void start();

    /* compiled from: IUpdatesController.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B}\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f\u0018\u00010\u0010\u0012\u0006\u0010\u0013\u001a\u00020\t¢\u0006\u0002\u0010\u0014J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010&\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\tHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\fHÆ\u0003J\t\u0010*\u001a\u00020\u000eHÆ\u0003J\u0015\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0010HÆ\u0003J\u0017\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f\u0018\u00010\u0010HÆ\u0003J\u0095\u0001\u0010-\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00102\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f\u0018\u00010\u00102\b\b\u0002\u0010\u0013\u001a\u00020\tHÆ\u0001J\u0013\u0010.\u001a\u00020\t2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\fHÖ\u0001R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u001bR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u001bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u001f\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0013\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001b¨\u00063"}, d2 = {"Lexpo/modules/updates/IUpdatesController$UpdatesModuleConstants;", "", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "embeddedUpdate", "emergencyLaunchException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "isEnabled", "", "isUsingEmbeddedAssets", UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY, "", UpdatesConfiguration.UPDATES_CONFIGURATION_CHECK_ON_LAUNCH_KEY, "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY, "", "localAssetFiles", "Lexpo/modules/updates/db/entity/AssetEntity;", "shouldDeferToNativeForAPIMethodAvailabilityInDevelopment", "(Lexpo/modules/updates/db/entity/UpdateEntity;Lexpo/modules/updates/db/entity/UpdateEntity;Ljava/lang/Exception;ZZLjava/lang/String;Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;Ljava/util/Map;Ljava/util/Map;Z)V", "getCheckOnLaunch", "()Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "getEmbeddedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "getEmergencyLaunchException", "()Ljava/lang/Exception;", "()Z", "getLaunchedUpdate", "getLocalAssetFiles", "()Ljava/util/Map;", "getRequestHeaders", "getRuntimeVersion", "()Ljava/lang/String;", "getShouldDeferToNativeForAPIMethodAvailabilityInDevelopment", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class UpdatesModuleConstants {
        private final UpdatesConfiguration.CheckAutomaticallyConfiguration checkOnLaunch;
        private final UpdateEntity embeddedUpdate;
        private final Exception emergencyLaunchException;
        private final boolean isEnabled;
        private final boolean isUsingEmbeddedAssets;
        private final UpdateEntity launchedUpdate;
        private final Map<AssetEntity, String> localAssetFiles;
        private final Map<String, String> requestHeaders;
        private final String runtimeVersion;
        private final boolean shouldDeferToNativeForAPIMethodAvailabilityInDevelopment;

        /* renamed from: component1, reason: from getter */
        public final UpdateEntity getLaunchedUpdate() {
            return this.launchedUpdate;
        }

        /* renamed from: component10, reason: from getter */
        public final boolean getShouldDeferToNativeForAPIMethodAvailabilityInDevelopment() {
            return this.shouldDeferToNativeForAPIMethodAvailabilityInDevelopment;
        }

        /* renamed from: component2, reason: from getter */
        public final UpdateEntity getEmbeddedUpdate() {
            return this.embeddedUpdate;
        }

        /* renamed from: component3, reason: from getter */
        public final Exception getEmergencyLaunchException() {
            return this.emergencyLaunchException;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsEnabled() {
            return this.isEnabled;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsUsingEmbeddedAssets() {
            return this.isUsingEmbeddedAssets;
        }

        /* renamed from: component6, reason: from getter */
        public final String getRuntimeVersion() {
            return this.runtimeVersion;
        }

        /* renamed from: component7, reason: from getter */
        public final UpdatesConfiguration.CheckAutomaticallyConfiguration getCheckOnLaunch() {
            return this.checkOnLaunch;
        }

        public final Map<String, String> component8() {
            return this.requestHeaders;
        }

        public final Map<AssetEntity, String> component9() {
            return this.localAssetFiles;
        }

        public final UpdatesModuleConstants copy(UpdateEntity launchedUpdate, UpdateEntity embeddedUpdate, Exception emergencyLaunchException, boolean isEnabled, boolean isUsingEmbeddedAssets, String runtimeVersion, UpdatesConfiguration.CheckAutomaticallyConfiguration checkOnLaunch, Map<String, String> requestHeaders, Map<AssetEntity, String> localAssetFiles, boolean shouldDeferToNativeForAPIMethodAvailabilityInDevelopment) {
            Intrinsics.checkNotNullParameter(checkOnLaunch, "checkOnLaunch");
            Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
            return new UpdatesModuleConstants(launchedUpdate, embeddedUpdate, emergencyLaunchException, isEnabled, isUsingEmbeddedAssets, runtimeVersion, checkOnLaunch, requestHeaders, localAssetFiles, shouldDeferToNativeForAPIMethodAvailabilityInDevelopment);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UpdatesModuleConstants)) {
                return false;
            }
            UpdatesModuleConstants updatesModuleConstants = (UpdatesModuleConstants) other;
            return Intrinsics.areEqual(this.launchedUpdate, updatesModuleConstants.launchedUpdate) && Intrinsics.areEqual(this.embeddedUpdate, updatesModuleConstants.embeddedUpdate) && Intrinsics.areEqual(this.emergencyLaunchException, updatesModuleConstants.emergencyLaunchException) && this.isEnabled == updatesModuleConstants.isEnabled && this.isUsingEmbeddedAssets == updatesModuleConstants.isUsingEmbeddedAssets && Intrinsics.areEqual(this.runtimeVersion, updatesModuleConstants.runtimeVersion) && this.checkOnLaunch == updatesModuleConstants.checkOnLaunch && Intrinsics.areEqual(this.requestHeaders, updatesModuleConstants.requestHeaders) && Intrinsics.areEqual(this.localAssetFiles, updatesModuleConstants.localAssetFiles) && this.shouldDeferToNativeForAPIMethodAvailabilityInDevelopment == updatesModuleConstants.shouldDeferToNativeForAPIMethodAvailabilityInDevelopment;
        }

        public final UpdatesConfiguration.CheckAutomaticallyConfiguration getCheckOnLaunch() {
            return this.checkOnLaunch;
        }

        public final UpdateEntity getEmbeddedUpdate() {
            return this.embeddedUpdate;
        }

        public final Exception getEmergencyLaunchException() {
            return this.emergencyLaunchException;
        }

        public final UpdateEntity getLaunchedUpdate() {
            return this.launchedUpdate;
        }

        public final Map<AssetEntity, String> getLocalAssetFiles() {
            return this.localAssetFiles;
        }

        public final Map<String, String> getRequestHeaders() {
            return this.requestHeaders;
        }

        public final String getRuntimeVersion() {
            return this.runtimeVersion;
        }

        public final boolean getShouldDeferToNativeForAPIMethodAvailabilityInDevelopment() {
            return this.shouldDeferToNativeForAPIMethodAvailabilityInDevelopment;
        }

        public int hashCode() {
            UpdateEntity updateEntity = this.launchedUpdate;
            int hashCode = (updateEntity == null ? 0 : updateEntity.hashCode()) * 31;
            UpdateEntity updateEntity2 = this.embeddedUpdate;
            int hashCode2 = (hashCode + (updateEntity2 == null ? 0 : updateEntity2.hashCode())) * 31;
            Exception exc = this.emergencyLaunchException;
            int hashCode3 = (((((hashCode2 + (exc == null ? 0 : exc.hashCode())) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.isEnabled)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.isUsingEmbeddedAssets)) * 31;
            String str = this.runtimeVersion;
            int hashCode4 = (((((hashCode3 + (str == null ? 0 : str.hashCode())) * 31) + this.checkOnLaunch.hashCode()) * 31) + this.requestHeaders.hashCode()) * 31;
            Map<AssetEntity, String> map = this.localAssetFiles;
            return ((hashCode4 + (map != null ? map.hashCode() : 0)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.shouldDeferToNativeForAPIMethodAvailabilityInDevelopment);
        }

        public final boolean isEnabled() {
            return this.isEnabled;
        }

        public final boolean isUsingEmbeddedAssets() {
            return this.isUsingEmbeddedAssets;
        }

        public String toString() {
            return "UpdatesModuleConstants(launchedUpdate=" + this.launchedUpdate + ", embeddedUpdate=" + this.embeddedUpdate + ", emergencyLaunchException=" + this.emergencyLaunchException + ", isEnabled=" + this.isEnabled + ", isUsingEmbeddedAssets=" + this.isUsingEmbeddedAssets + ", runtimeVersion=" + this.runtimeVersion + ", checkOnLaunch=" + this.checkOnLaunch + ", requestHeaders=" + this.requestHeaders + ", localAssetFiles=" + this.localAssetFiles + ", shouldDeferToNativeForAPIMethodAvailabilityInDevelopment=" + this.shouldDeferToNativeForAPIMethodAvailabilityInDevelopment + ")";
        }

        public UpdatesModuleConstants(UpdateEntity updateEntity, UpdateEntity updateEntity2, Exception exc, boolean z, boolean z2, String str, UpdatesConfiguration.CheckAutomaticallyConfiguration checkOnLaunch, Map<String, String> requestHeaders, Map<AssetEntity, String> map, boolean z3) {
            Intrinsics.checkNotNullParameter(checkOnLaunch, "checkOnLaunch");
            Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
            this.launchedUpdate = updateEntity;
            this.embeddedUpdate = updateEntity2;
            this.emergencyLaunchException = exc;
            this.isEnabled = z;
            this.isUsingEmbeddedAssets = z2;
            this.runtimeVersion = str;
            this.checkOnLaunch = checkOnLaunch;
            this.requestHeaders = requestHeaders;
            this.localAssetFiles = map;
            this.shouldDeferToNativeForAPIMethodAvailabilityInDevelopment = z3;
        }
    }

    /* compiled from: IUpdatesController.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0005\u0006\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0004\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", "", "status", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$Status;", "(Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$Status;)V", "ErrorResult", "NoUpdateAvailable", "RollBackToEmbedded", "Status", "UpdateAvailable", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$ErrorResult;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$NoUpdateAvailable;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$RollBackToEmbedded;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$UpdateAvailable;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class CheckForUpdateResult {
        private final Status status;

        public /* synthetic */ CheckForUpdateResult(Status status, DefaultConstructorMarker defaultConstructorMarker) {
            this(status);
        }

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$Status;", "", "(Ljava/lang/String;I)V", "NO_UPDATE_AVAILABLE", "UPDATE_AVAILABLE", "ROLL_BACK_TO_EMBEDDED", "ERROR", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        private static final class Status {
            private static final /* synthetic */ EnumEntries $ENTRIES;
            private static final /* synthetic */ Status[] $VALUES;
            public static final Status NO_UPDATE_AVAILABLE = new Status("NO_UPDATE_AVAILABLE", 0);
            public static final Status UPDATE_AVAILABLE = new Status("UPDATE_AVAILABLE", 1);
            public static final Status ROLL_BACK_TO_EMBEDDED = new Status("ROLL_BACK_TO_EMBEDDED", 2);
            public static final Status ERROR = new Status("ERROR", 3);

            private static final /* synthetic */ Status[] $values() {
                return new Status[]{NO_UPDATE_AVAILABLE, UPDATE_AVAILABLE, ROLL_BACK_TO_EMBEDDED, ERROR};
            }

            public static EnumEntries<Status> getEntries() {
                return $ENTRIES;
            }

            public static Status valueOf(String str) {
                return (Status) Enum.valueOf(Status.class, str);
            }

            public static Status[] values() {
                return (Status[]) $VALUES.clone();
            }

            private Status(String str, int i) {
            }

            static {
                Status[] $values = $values();
                $VALUES = $values;
                $ENTRIES = EnumEntriesKt.enumEntries($values);
            }
        }

        private CheckForUpdateResult(Status status) {
            this.status = status;
        }

        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$NoUpdateAvailable;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", DiscardedEvent.JsonKeys.REASON, "Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResultNotAvailableReason;", "(Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResultNotAvailableReason;)V", "getReason", "()Lexpo/modules/updates/loader/LoaderTask$RemoteCheckResultNotAvailableReason;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class NoUpdateAvailable extends CheckForUpdateResult {
            private final LoaderTask.RemoteCheckResultNotAvailableReason reason;

            public final LoaderTask.RemoteCheckResultNotAvailableReason getReason() {
                return this.reason;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NoUpdateAvailable(LoaderTask.RemoteCheckResultNotAvailableReason reason) {
                super(Status.NO_UPDATE_AVAILABLE, null);
                Intrinsics.checkNotNullParameter(reason, "reason");
                this.reason = reason;
            }
        }

        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$UpdateAvailable;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", "update", "Lexpo/modules/updates/manifest/Update;", "(Lexpo/modules/updates/manifest/Update;)V", "getUpdate", "()Lexpo/modules/updates/manifest/Update;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class UpdateAvailable extends CheckForUpdateResult {
            private final Update update;

            public final Update getUpdate() {
                return this.update;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public UpdateAvailable(Update update) {
                super(Status.UPDATE_AVAILABLE, null);
                Intrinsics.checkNotNullParameter(update, "update");
                this.update = update;
            }
        }

        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$RollBackToEmbedded;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", "commitTime", "Ljava/util/Date;", "(Ljava/util/Date;)V", "getCommitTime", "()Ljava/util/Date;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class RollBackToEmbedded extends CheckForUpdateResult {
            private final Date commitTime;

            public final Date getCommitTime() {
                return this.commitTime;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public RollBackToEmbedded(Date commitTime) {
                super(Status.ROLL_BACK_TO_EMBEDDED, null);
                Intrinsics.checkNotNullParameter(commitTime, "commitTime");
                this.commitTime = commitTime;
            }
        }

        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult$ErrorResult;", "Lexpo/modules/updates/IUpdatesController$CheckForUpdateResult;", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/Exception;Ljava/lang/String;)V", "getError", "()Ljava/lang/Exception;", "getMessage", "()Ljava/lang/String;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ErrorResult extends CheckForUpdateResult {
            private final Exception error;
            private final String message;

            public final Exception getError() {
                return this.error;
            }

            public final String getMessage() {
                return this.message;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ErrorResult(Exception error, String message) {
                super(Status.ERROR, null);
                Intrinsics.checkNotNullParameter(error, "error");
                Intrinsics.checkNotNullParameter(message, "message");
                this.error = error;
                this.message = message;
            }
        }
    }

    /* compiled from: IUpdatesController.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0005\u0006\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0004\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", "", "status", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$Status;", "(Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$Status;)V", "ErrorResult", "Failure", "RollBackToEmbedded", "Status", "Success", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$ErrorResult;", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$Failure;", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$RollBackToEmbedded;", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$Success;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class FetchUpdateResult {
        private final Status status;

        public /* synthetic */ FetchUpdateResult(Status status, DefaultConstructorMarker defaultConstructorMarker) {
            this(status);
        }

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$Status;", "", "(Ljava/lang/String;I)V", "SUCCESS", "FAILURE", "ROLL_BACK_TO_EMBEDDED", "ERROR", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        private static final class Status {
            private static final /* synthetic */ EnumEntries $ENTRIES;
            private static final /* synthetic */ Status[] $VALUES;
            public static final Status SUCCESS = new Status("SUCCESS", 0);
            public static final Status FAILURE = new Status("FAILURE", 1);
            public static final Status ROLL_BACK_TO_EMBEDDED = new Status("ROLL_BACK_TO_EMBEDDED", 2);
            public static final Status ERROR = new Status("ERROR", 3);

            private static final /* synthetic */ Status[] $values() {
                return new Status[]{SUCCESS, FAILURE, ROLL_BACK_TO_EMBEDDED, ERROR};
            }

            public static EnumEntries<Status> getEntries() {
                return $ENTRIES;
            }

            public static Status valueOf(String str) {
                return (Status) Enum.valueOf(Status.class, str);
            }

            public static Status[] values() {
                return (Status[]) $VALUES.clone();
            }

            private Status(String str, int i) {
            }

            static {
                Status[] $values = $values();
                $VALUES = $values;
                $ENTRIES = EnumEntriesKt.enumEntries($values);
            }
        }

        private FetchUpdateResult(Status status) {
            this.status = status;
        }

        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$Success;", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", "update", "Lexpo/modules/updates/db/entity/UpdateEntity;", "(Lexpo/modules/updates/db/entity/UpdateEntity;)V", "getUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Success extends FetchUpdateResult {
            private final UpdateEntity update;

            public final UpdateEntity getUpdate() {
                return this.update;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Success(UpdateEntity update) {
                super(Status.SUCCESS, null);
                Intrinsics.checkNotNullParameter(update, "update");
                this.update = update;
            }
        }

        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$Failure;", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", "()V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Failure extends FetchUpdateResult {
            public Failure() {
                super(Status.FAILURE, null);
            }
        }

        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$RollBackToEmbedded;", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", "()V", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class RollBackToEmbedded extends FetchUpdateResult {
            public RollBackToEmbedded() {
                super(Status.ROLL_BACK_TO_EMBEDDED, null);
            }
        }

        /* compiled from: IUpdatesController.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/updates/IUpdatesController$FetchUpdateResult$ErrorResult;", "Lexpo/modules/updates/IUpdatesController$FetchUpdateResult;", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "getError", "()Ljava/lang/Exception;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class ErrorResult extends FetchUpdateResult {
            private final Exception error;

            public final Exception getError() {
                return this.error;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ErrorResult(Exception error) {
                super(Status.ERROR, null);
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }
        }
    }
}

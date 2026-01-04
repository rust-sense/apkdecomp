package expo.modules.updates;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import expo.modules.core.errors.InvalidArgumentException;
import expo.modules.image.records.SourceMap$$ExternalSyntheticBackport0;
import expo.modules.updates.codesigning.CodeSigningConfiguration;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: UpdatesConfiguration.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b&\b\u0086\b\u0018\u0000 C2\u00020\u0001:\u0002BCB'\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007B\u0083\u0001\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u0012\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0011\u0012\u0006\u0010\u0016\u001a\u00020\u0011\u0012\u0006\u0010\u0017\u001a\u00020\u0011¢\u0006\u0002\u0010\u0018J\t\u00100\u001a\u00020\u0006HÆ\u0003J\t\u00101\u001a\u00020\u0011HÆ\u0003J\t\u00102\u001a\u00020\u0011HÂ\u0003J\t\u00103\u001a\u00020\u0011HÆ\u0003J\t\u00104\u001a\u00020\nHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u00106\u001a\u00020\rHÆ\u0003J\t\u00107\u001a\u00020\u000fHÆ\u0003J\t\u00108\u001a\u00020\u0011HÆ\u0003J\u0015\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0017\u0010;\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u009f\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u0011HÆ\u0001J\u0013\u0010=\u001a\u00020\u00112\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010?\u001a\u00020\u0006J\t\u0010@\u001a\u00020\rHÖ\u0001J\t\u0010A\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u001d\u001a\u0004\u0018\u00010\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0015\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001f\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0017\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b(\u0010$R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010&R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001cR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/¨\u0006D"}, d2 = {"Lexpo/modules/updates/UpdatesConfiguration;", "", "context", "Landroid/content/Context;", "overrideMap", "", "", "(Landroid/content/Context;Ljava/util/Map;)V", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY, "Landroid/net/Uri;", "runtimeVersionRaw", UpdatesConfiguration.UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_KEY, "", UpdatesConfiguration.UPDATES_CONFIGURATION_CHECK_ON_LAUNCH_KEY, "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", UpdatesConfiguration.UPDATES_CONFIGURATION_HAS_EMBEDDED_UPDATE_KEY, "", UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY, UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_CERTIFICATE, UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_METADATA, UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_INCLUDE_MANIFEST_RESPONSE_CERTIFICATE_CHAIN, UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_ALLOW_UNSIGNED_MANIFESTS, "enableExpoUpdatesProtocolV0CompatibilityMode", "(Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;ILexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;ZLjava/util/Map;Ljava/lang/String;Ljava/util/Map;ZZZ)V", "getCheckOnLaunch", "()Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "getCodeSigningCertificate", "()Ljava/lang/String;", "codeSigningConfiguration", "Lexpo/modules/updates/codesigning/CodeSigningConfiguration;", "getCodeSigningConfiguration", "()Lexpo/modules/updates/codesigning/CodeSigningConfiguration;", "codeSigningConfiguration$delegate", "Lkotlin/Lazy;", "getCodeSigningIncludeManifestResponseCertificateChain", "()Z", "getCodeSigningMetadata", "()Ljava/util/Map;", "getEnableExpoUpdatesProtocolV0CompatibilityMode", "getHasEmbeddedUpdate", "getLaunchWaitMs", "()I", "getRequestHeaders", "getRuntimeVersionRaw", "getScopeKey", "getUpdateUrl", "()Landroid/net/Uri;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getRuntimeVersion", "hashCode", "toString", "CheckAutomaticallyConfiguration", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class UpdatesConfiguration {
    private static final String FINGERPRINT_FILE_NAME = "fingerprint";
    public static final String UPDATES_CONFIGURATION_CHECK_ON_LAUNCH_KEY = "checkOnLaunch";
    public static final String UPDATES_CONFIGURATION_CODE_SIGNING_ALLOW_UNSIGNED_MANIFESTS = "codeSigningAllowUnsignedManifests";
    public static final String UPDATES_CONFIGURATION_CODE_SIGNING_CERTIFICATE = "codeSigningCertificate";
    public static final String UPDATES_CONFIGURATION_CODE_SIGNING_INCLUDE_MANIFEST_RESPONSE_CERTIFICATE_CHAIN = "codeSigningIncludeManifestResponseCertificateChain";
    public static final String UPDATES_CONFIGURATION_CODE_SIGNING_METADATA = "codeSigningMetadata";
    public static final String UPDATES_CONFIGURATION_ENABLED_KEY = "enabled";
    public static final String UPDATES_CONFIGURATION_ENABLE_EXPO_UPDATES_PROTOCOL_V0_COMPATIBILITY_MODE = "enableExpoUpdatesProtocolCompatibilityMode";
    public static final String UPDATES_CONFIGURATION_HAS_EMBEDDED_UPDATE_KEY = "hasEmbeddedUpdate";
    private static final int UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_DEFAULT_VALUE = 0;
    public static final String UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_KEY = "launchWaitMs";
    public static final String UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY = "requestHeaders";
    public static final String UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY = "runtimeVersion";
    public static final String UPDATES_CONFIGURATION_RUNTIME_VERSION_READ_FINGERPRINT_FILE_SENTINEL = "file:fingerprint";
    public static final String UPDATES_CONFIGURATION_SCOPE_KEY_KEY = "scopeKey";
    public static final String UPDATES_CONFIGURATION_UPDATE_URL_KEY = "updateUrl";
    private final CheckAutomaticallyConfiguration checkOnLaunch;
    private final boolean codeSigningAllowUnsignedManifests;
    private final String codeSigningCertificate;

    /* renamed from: codeSigningConfiguration$delegate, reason: from kotlin metadata */
    private final Lazy codeSigningConfiguration;
    private final boolean codeSigningIncludeManifestResponseCertificateChain;
    private final Map<String, String> codeSigningMetadata;
    private final boolean enableExpoUpdatesProtocolV0CompatibilityMode;
    private final boolean hasEmbeddedUpdate;
    private final int launchWaitMs;
    private final Map<String, String> requestHeaders;
    private final String runtimeVersionRaw;
    private final String scopeKey;
    private final Uri updateUrl;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "UpdatesConfiguration";

    /* renamed from: component11, reason: from getter */
    private final boolean getCodeSigningAllowUnsignedManifests() {
        return this.codeSigningAllowUnsignedManifests;
    }

    /* renamed from: component1, reason: from getter */
    public final String getScopeKey() {
        return this.scopeKey;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getCodeSigningIncludeManifestResponseCertificateChain() {
        return this.codeSigningIncludeManifestResponseCertificateChain;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getEnableExpoUpdatesProtocolV0CompatibilityMode() {
        return this.enableExpoUpdatesProtocolV0CompatibilityMode;
    }

    /* renamed from: component2, reason: from getter */
    public final Uri getUpdateUrl() {
        return this.updateUrl;
    }

    /* renamed from: component3, reason: from getter */
    public final String getRuntimeVersionRaw() {
        return this.runtimeVersionRaw;
    }

    /* renamed from: component4, reason: from getter */
    public final int getLaunchWaitMs() {
        return this.launchWaitMs;
    }

    /* renamed from: component5, reason: from getter */
    public final CheckAutomaticallyConfiguration getCheckOnLaunch() {
        return this.checkOnLaunch;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getHasEmbeddedUpdate() {
        return this.hasEmbeddedUpdate;
    }

    public final Map<String, String> component7() {
        return this.requestHeaders;
    }

    /* renamed from: component8, reason: from getter */
    public final String getCodeSigningCertificate() {
        return this.codeSigningCertificate;
    }

    public final Map<String, String> component9() {
        return this.codeSigningMetadata;
    }

    public final UpdatesConfiguration copy(String scopeKey, Uri updateUrl, String runtimeVersionRaw, int launchWaitMs, CheckAutomaticallyConfiguration checkOnLaunch, boolean hasEmbeddedUpdate, Map<String, String> requestHeaders, String codeSigningCertificate, Map<String, String> codeSigningMetadata, boolean codeSigningIncludeManifestResponseCertificateChain, boolean codeSigningAllowUnsignedManifests, boolean enableExpoUpdatesProtocolV0CompatibilityMode) {
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        Intrinsics.checkNotNullParameter(updateUrl, "updateUrl");
        Intrinsics.checkNotNullParameter(checkOnLaunch, "checkOnLaunch");
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        return new UpdatesConfiguration(scopeKey, updateUrl, runtimeVersionRaw, launchWaitMs, checkOnLaunch, hasEmbeddedUpdate, requestHeaders, codeSigningCertificate, codeSigningMetadata, codeSigningIncludeManifestResponseCertificateChain, codeSigningAllowUnsignedManifests, enableExpoUpdatesProtocolV0CompatibilityMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdatesConfiguration)) {
            return false;
        }
        UpdatesConfiguration updatesConfiguration = (UpdatesConfiguration) other;
        return Intrinsics.areEqual(this.scopeKey, updatesConfiguration.scopeKey) && Intrinsics.areEqual(this.updateUrl, updatesConfiguration.updateUrl) && Intrinsics.areEqual(this.runtimeVersionRaw, updatesConfiguration.runtimeVersionRaw) && this.launchWaitMs == updatesConfiguration.launchWaitMs && this.checkOnLaunch == updatesConfiguration.checkOnLaunch && this.hasEmbeddedUpdate == updatesConfiguration.hasEmbeddedUpdate && Intrinsics.areEqual(this.requestHeaders, updatesConfiguration.requestHeaders) && Intrinsics.areEqual(this.codeSigningCertificate, updatesConfiguration.codeSigningCertificate) && Intrinsics.areEqual(this.codeSigningMetadata, updatesConfiguration.codeSigningMetadata) && this.codeSigningIncludeManifestResponseCertificateChain == updatesConfiguration.codeSigningIncludeManifestResponseCertificateChain && this.codeSigningAllowUnsignedManifests == updatesConfiguration.codeSigningAllowUnsignedManifests && this.enableExpoUpdatesProtocolV0CompatibilityMode == updatesConfiguration.enableExpoUpdatesProtocolV0CompatibilityMode;
    }

    public final CheckAutomaticallyConfiguration getCheckOnLaunch() {
        return this.checkOnLaunch;
    }

    public final String getCodeSigningCertificate() {
        return this.codeSigningCertificate;
    }

    public final boolean getCodeSigningIncludeManifestResponseCertificateChain() {
        return this.codeSigningIncludeManifestResponseCertificateChain;
    }

    public final Map<String, String> getCodeSigningMetadata() {
        return this.codeSigningMetadata;
    }

    public final boolean getEnableExpoUpdatesProtocolV0CompatibilityMode() {
        return this.enableExpoUpdatesProtocolV0CompatibilityMode;
    }

    public final boolean getHasEmbeddedUpdate() {
        return this.hasEmbeddedUpdate;
    }

    public final int getLaunchWaitMs() {
        return this.launchWaitMs;
    }

    public final Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    public final String getRuntimeVersionRaw() {
        return this.runtimeVersionRaw;
    }

    public final String getScopeKey() {
        return this.scopeKey;
    }

    public final Uri getUpdateUrl() {
        return this.updateUrl;
    }

    public int hashCode() {
        int hashCode = ((this.scopeKey.hashCode() * 31) + this.updateUrl.hashCode()) * 31;
        String str = this.runtimeVersionRaw;
        int hashCode2 = (((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.launchWaitMs) * 31) + this.checkOnLaunch.hashCode()) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.hasEmbeddedUpdate)) * 31) + this.requestHeaders.hashCode()) * 31;
        String str2 = this.codeSigningCertificate;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map<String, String> map = this.codeSigningMetadata;
        return ((((((hashCode3 + (map != null ? map.hashCode() : 0)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.codeSigningIncludeManifestResponseCertificateChain)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.codeSigningAllowUnsignedManifests)) * 31) + SourceMap$$ExternalSyntheticBackport0.m(this.enableExpoUpdatesProtocolV0CompatibilityMode);
    }

    public String toString() {
        return "UpdatesConfiguration(scopeKey=" + this.scopeKey + ", updateUrl=" + this.updateUrl + ", runtimeVersionRaw=" + this.runtimeVersionRaw + ", launchWaitMs=" + this.launchWaitMs + ", checkOnLaunch=" + this.checkOnLaunch + ", hasEmbeddedUpdate=" + this.hasEmbeddedUpdate + ", requestHeaders=" + this.requestHeaders + ", codeSigningCertificate=" + this.codeSigningCertificate + ", codeSigningMetadata=" + this.codeSigningMetadata + ", codeSigningIncludeManifestResponseCertificateChain=" + this.codeSigningIncludeManifestResponseCertificateChain + ", codeSigningAllowUnsignedManifests=" + this.codeSigningAllowUnsignedManifests + ", enableExpoUpdatesProtocolV0CompatibilityMode=" + this.enableExpoUpdatesProtocolV0CompatibilityMode + ")";
    }

    public UpdatesConfiguration(String scopeKey, Uri updateUrl, String str, int i, CheckAutomaticallyConfiguration checkOnLaunch, boolean z, Map<String, String> requestHeaders, String str2, Map<String, String> map, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        Intrinsics.checkNotNullParameter(updateUrl, "updateUrl");
        Intrinsics.checkNotNullParameter(checkOnLaunch, "checkOnLaunch");
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        this.scopeKey = scopeKey;
        this.updateUrl = updateUrl;
        this.runtimeVersionRaw = str;
        this.launchWaitMs = i;
        this.checkOnLaunch = checkOnLaunch;
        this.hasEmbeddedUpdate = z;
        this.requestHeaders = requestHeaders;
        this.codeSigningCertificate = str2;
        this.codeSigningMetadata = map;
        this.codeSigningIncludeManifestResponseCertificateChain = z2;
        this.codeSigningAllowUnsignedManifests = z3;
        this.enableExpoUpdatesProtocolV0CompatibilityMode = z4;
        this.codeSigningConfiguration = LazyKt.lazy(new Function0<CodeSigningConfiguration>() { // from class: expo.modules.updates.UpdatesConfiguration$codeSigningConfiguration$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CodeSigningConfiguration invoke() {
                boolean z5;
                String codeSigningCertificate = UpdatesConfiguration.this.getCodeSigningCertificate();
                if (codeSigningCertificate == null) {
                    return null;
                }
                UpdatesConfiguration updatesConfiguration = UpdatesConfiguration.this;
                Map<String, String> codeSigningMetadata = updatesConfiguration.getCodeSigningMetadata();
                boolean codeSigningIncludeManifestResponseCertificateChain = updatesConfiguration.getCodeSigningIncludeManifestResponseCertificateChain();
                z5 = updatesConfiguration.codeSigningAllowUnsignedManifests;
                return new CodeSigningConfiguration(codeSigningCertificate, codeSigningMetadata, codeSigningIncludeManifestResponseCertificateChain, z5);
            }
        });
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: UpdatesConfiguration.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "", "(Ljava/lang/String;I)V", "toJSString", "", "NEVER", "ERROR_RECOVERY_ONLY", "WIFI_ONLY", "ALWAYS", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class CheckAutomaticallyConfiguration {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ CheckAutomaticallyConfiguration[] $VALUES;
        public static final CheckAutomaticallyConfiguration NEVER = new NEVER("NEVER", 0);
        public static final CheckAutomaticallyConfiguration ERROR_RECOVERY_ONLY = new ERROR_RECOVERY_ONLY("ERROR_RECOVERY_ONLY", 1);
        public static final CheckAutomaticallyConfiguration WIFI_ONLY = new WIFI_ONLY("WIFI_ONLY", 2);
        public static final CheckAutomaticallyConfiguration ALWAYS = new ALWAYS("ALWAYS", 3);

        private static final /* synthetic */ CheckAutomaticallyConfiguration[] $values() {
            return new CheckAutomaticallyConfiguration[]{NEVER, ERROR_RECOVERY_ONLY, WIFI_ONLY, ALWAYS};
        }

        public /* synthetic */ CheckAutomaticallyConfiguration(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i);
        }

        public static EnumEntries<CheckAutomaticallyConfiguration> getEntries() {
            return $ENTRIES;
        }

        public static CheckAutomaticallyConfiguration valueOf(String str) {
            return (CheckAutomaticallyConfiguration) Enum.valueOf(CheckAutomaticallyConfiguration.class, str);
        }

        public static CheckAutomaticallyConfiguration[] values() {
            return (CheckAutomaticallyConfiguration[]) $VALUES.clone();
        }

        /* compiled from: UpdatesConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration$NEVER;", "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "toJSString", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        static final class NEVER extends CheckAutomaticallyConfiguration {
            @Override // expo.modules.updates.UpdatesConfiguration.CheckAutomaticallyConfiguration
            public String toJSString() {
                return "NEVER";
            }

            NEVER(String str, int i) {
                super(str, i, null);
            }
        }

        private CheckAutomaticallyConfiguration(String str, int i) {
        }

        static {
            CheckAutomaticallyConfiguration[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        /* compiled from: UpdatesConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration$ERROR_RECOVERY_ONLY;", "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "toJSString", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        static final class ERROR_RECOVERY_ONLY extends CheckAutomaticallyConfiguration {
            @Override // expo.modules.updates.UpdatesConfiguration.CheckAutomaticallyConfiguration
            public String toJSString() {
                return "ERROR_RECOVERY_ONLY";
            }

            ERROR_RECOVERY_ONLY(String str, int i) {
                super(str, i, null);
            }
        }

        /* compiled from: UpdatesConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration$WIFI_ONLY;", "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "toJSString", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        static final class WIFI_ONLY extends CheckAutomaticallyConfiguration {
            @Override // expo.modules.updates.UpdatesConfiguration.CheckAutomaticallyConfiguration
            public String toJSString() {
                return "WIFI_ONLY";
            }

            WIFI_ONLY(String str, int i) {
                super(str, i, null);
            }
        }

        /* compiled from: UpdatesConfiguration.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration$ALWAYS;", "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "toJSString", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        static final class ALWAYS extends CheckAutomaticallyConfiguration {
            @Override // expo.modules.updates.UpdatesConfiguration.CheckAutomaticallyConfiguration
            public String toJSString() {
                return "ALWAYS";
            }

            ALWAYS(String str, int i) {
                super(str, i, null);
            }
        }

        public String toJSString() {
            throw new InvalidArgumentException("Unsupported CheckAutomaticallyConfiguration value");
        }
    }

    public final CodeSigningConfiguration getCodeSigningConfiguration() {
        return (CodeSigningConfiguration) this.codeSigningConfiguration.getValue();
    }

    public final String getRuntimeVersion() {
        String str = this.runtimeVersionRaw;
        if (str == null || str.length() == 0) {
            throw new Exception("No runtime version provided in configuration");
        }
        return this.runtimeVersionRaw;
    }

    /* compiled from: UpdatesConfiguration.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001cH\u0002J*\u0010\u001d\u001a\u0004\u0018\u00010\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001cH\u0002J&\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001cJ*\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lexpo/modules/updates/UpdatesConfiguration$Companion;", "", "()V", "FINGERPRINT_FILE_NAME", "", "TAG", "kotlin.jvm.PlatformType", "UPDATES_CONFIGURATION_CHECK_ON_LAUNCH_KEY", "UPDATES_CONFIGURATION_CODE_SIGNING_ALLOW_UNSIGNED_MANIFESTS", "UPDATES_CONFIGURATION_CODE_SIGNING_CERTIFICATE", "UPDATES_CONFIGURATION_CODE_SIGNING_INCLUDE_MANIFEST_RESPONSE_CERTIFICATE_CHAIN", "UPDATES_CONFIGURATION_CODE_SIGNING_METADATA", "UPDATES_CONFIGURATION_ENABLED_KEY", "UPDATES_CONFIGURATION_ENABLE_EXPO_UPDATES_PROTOCOL_V0_COMPATIBILITY_MODE", "UPDATES_CONFIGURATION_HAS_EMBEDDED_UPDATE_KEY", "UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_DEFAULT_VALUE", "", "UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_KEY", "UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY", "UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY", "UPDATES_CONFIGURATION_RUNTIME_VERSION_READ_FINGERPRINT_FILE_SENTINEL", "UPDATES_CONFIGURATION_SCOPE_KEY_KEY", "UPDATES_CONFIGURATION_UPDATE_URL_KEY", "getIsEnabled", "", "context", "Landroid/content/Context;", "overrideMap", "", "getRuntimeVersion", "getUpdatesConfigurationValidationResult", "Lexpo/modules/updates/UpdatesConfigurationValidationResult;", "getUpdatesUrl", "Landroid/net/Uri;", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UpdatesConfigurationValidationResult getUpdatesConfigurationValidationResult(Context context, Map<String, ? extends Object> overrideMap) {
            if (!getIsEnabled(context, overrideMap)) {
                return UpdatesConfigurationValidationResult.INVALID_NOT_ENABLED;
            }
            if (getUpdatesUrl(context, overrideMap) == null) {
                return UpdatesConfigurationValidationResult.INVALID_MISSING_URL;
            }
            String runtimeVersion = getRuntimeVersion(context, overrideMap);
            if (runtimeVersion == null || runtimeVersion.length() == 0) {
                return UpdatesConfigurationValidationResult.INVALID_MISSING_RUNTIME_VERSION;
            }
            return UpdatesConfigurationValidationResult.VALID;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Uri getUpdatesUrl(Context context, Map<String, ? extends Object> overrideMap) {
            String str;
            Object obj;
            if (overrideMap != null) {
                if (overrideMap.containsKey(UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY)) {
                    obj = overrideMap.get(UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY);
                    if (!(obj instanceof Uri)) {
                        Intrinsics.checkNotNull(obj);
                        throw new AssertionError("UpdatesConfiguration failed to initialize: bad value of type " + obj.getClass().getSimpleName() + " provided for key updateUrl");
                    }
                } else {
                    obj = null;
                }
                Uri uri = (Uri) obj;
                if (uri != null) {
                    return uri;
                }
            }
            if (context == null) {
                return null;
            }
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle.containsKey("expo.modules.updates.EXPO_UPDATE_URL")) {
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                    str = bundle.getString("expo.modules.updates.EXPO_UPDATE_URL");
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str = (String) Boolean.valueOf(bundle.getBoolean("expo.modules.updates.EXPO_UPDATE_URL"));
                } else {
                    str = Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE)) ? (String) Integer.valueOf(bundle.getInt("expo.modules.updates.EXPO_UPDATE_URL")) : (String) bundle.get("expo.modules.updates.EXPO_UPDATE_URL");
                }
            } else {
                str = null;
            }
            if (str != null) {
                return Uri.parse(str);
            }
            return null;
        }

        private final boolean getIsEnabled(Context context, Map<String, ? extends Object> overrideMap) {
            Boolean bool;
            Object obj;
            Boolean bool2 = null;
            if (overrideMap != null) {
                if (overrideMap.containsKey("enabled")) {
                    obj = overrideMap.get("enabled");
                    if (!(obj instanceof Boolean)) {
                        Intrinsics.checkNotNull(obj);
                        throw new AssertionError("UpdatesConfiguration failed to initialize: bad value of type " + obj.getClass().getSimpleName() + " provided for key enabled");
                    }
                } else {
                    obj = null;
                }
                Boolean bool3 = (Boolean) obj;
                if (bool3 != null) {
                    return bool3.booleanValue();
                }
            }
            if (context != null) {
                Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                if (bundle.containsKey("expo.modules.updates.ENABLED")) {
                    KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                        bool = (Boolean) bundle.getString("expo.modules.updates.ENABLED");
                    } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        bool = Boolean.valueOf(bundle.getBoolean("expo.modules.updates.ENABLED"));
                    } else {
                        bool = Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE)) ? (Boolean) Integer.valueOf(bundle.getInt("expo.modules.updates.ENABLED")) : (Boolean) bundle.get("expo.modules.updates.ENABLED");
                    }
                    bool2 = bool;
                }
            }
            if (bool2 != null) {
                return bool2.booleanValue();
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
        
            if (r6 == null) goto L14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.String getRuntimeVersion(android.content.Context r5, java.util.Map<java.lang.String, ? extends java.lang.Object> r6) {
            /*
                r4 = this;
                r0 = 0
                if (r6 == 0) goto L3e
                java.lang.String r1 = "runtimeVersion"
                boolean r2 = r6.containsKey(r1)
                if (r2 != 0) goto Ld
                r6 = r0
                goto L15
            Ld:
                java.lang.Object r6 = r6.get(r1)
                boolean r1 = r6 instanceof java.lang.String
                if (r1 == 0) goto L1a
            L15:
                java.lang.String r6 = (java.lang.String) r6
                if (r6 != 0) goto Lbe
                goto L3e
            L1a:
                java.lang.AssertionError r5 = new java.lang.AssertionError
                kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                java.lang.Class r6 = r6.getClass()
                java.lang.String r6 = r6.getSimpleName()
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "UpdatesConfiguration failed to initialize: bad value of type "
                r0.<init>(r1)
                r0.append(r6)
                java.lang.String r6 = " provided for key runtimeVersion"
                r0.append(r6)
                java.lang.String r6 = r0.toString()
                r5.<init>(r6)
                throw r5
            L3e:
                if (r5 == 0) goto Lbd
                android.content.pm.PackageManager r6 = r5.getPackageManager()
                java.lang.String r1 = r5.getPackageName()
                r2 = 128(0x80, float:1.8E-43)
                android.content.pm.ApplicationInfo r6 = r6.getApplicationInfo(r1, r2)
                android.os.Bundle r6 = r6.metaData
                java.lang.String r1 = "expo.modules.updates.EXPO_RUNTIME_VERSION"
                boolean r2 = r6.containsKey(r1)
                if (r2 != 0) goto L5a
                r6 = r0
                goto La5
            L5a:
                java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
                kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
                java.lang.Class<java.lang.String> r3 = java.lang.String.class
                kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
                if (r3 == 0) goto L73
                java.lang.String r6 = r6.getString(r1)
                java.lang.Object r6 = (java.lang.Object) r6
                goto La5
            L73:
                java.lang.Class r3 = java.lang.Boolean.TYPE
                kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
                if (r3 == 0) goto L8a
                boolean r6 = r6.getBoolean(r1)
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
                java.lang.Object r6 = (java.lang.Object) r6
                goto La5
            L8a:
                java.lang.Class r3 = java.lang.Integer.TYPE
                kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
                if (r2 == 0) goto La1
                int r6 = r6.getInt(r1)
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                java.lang.Object r6 = (java.lang.Object) r6
                goto La5
            La1:
                java.lang.Object r6 = r6.get(r1)
            La5:
                if (r6 == 0) goto Lbd
                java.lang.String r6 = r6.toString()
                if (r6 == 0) goto Lbd
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                kotlin.text.Regex r1 = new kotlin.text.Regex
                java.lang.String r2 = "^string:"
                r1.<init>(r2)
                java.lang.String r2 = ""
                java.lang.String r6 = r1.replaceFirst(r6, r2)
                goto Lbe
            Lbd:
                r6 = r0
            Lbe:
                if (r5 == 0) goto Le8
                java.lang.String r1 = "file:fingerprint"
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r1)
                if (r1 == 0) goto Le8
                android.content.res.AssetManager r5 = r5.getAssets()
                java.lang.String r6 = "fingerprint"
                java.io.InputStream r5 = r5.open(r6)
                java.io.Closeable r5 = (java.io.Closeable) r5
                r6 = r5
                java.io.InputStream r6 = (java.io.InputStream) r6     // Catch: java.lang.Throwable -> Le1
                java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> Le1
                java.lang.String r6 = org.apache.commons.io.IOUtils.toString(r6, r1)     // Catch: java.lang.Throwable -> Le1
                kotlin.io.CloseableKt.closeFinally(r5, r0)
                return r6
            Le1:
                r6 = move-exception
                throw r6     // Catch: java.lang.Throwable -> Le3
            Le3:
                r0 = move-exception
                kotlin.io.CloseableKt.closeFinally(r5, r6)
                throw r0
            Le8:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.updates.UpdatesConfiguration.Companion.getRuntimeVersion(android.content.Context, java.util.Map):java.lang.String");
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(47:0|1|(3:3|(1:5)(2:268|(2:270|271))|6)|(2:273|(43:275|(1:277)(2:278|(1:280)(2:281|(1:283)(1:284)))|8|(3:10|(1:12)(2:248|(2:250|251))|13)|(2:253|(39:255|(1:257)(2:260|(1:262)(2:263|(1:265)(1:266)))|(36:259|(4:17|(1:19)(2:224|(2:226|227))|20|(2:22|23))|(2:229|(1:231)(2:237|(1:239)(2:240|(1:242)(2:243|(1:245)(1:246)))))|247|233|234|25|(3:27|(1:29)(2:201|(2:203|204))|30)|(2:206|(28:208|(1:210)(2:213|(1:215)(2:216|(1:218)(1:219)))|(25:212|33|(4:35|(1:37)(2:180|(2:182|183))|38|(20:40|(3:42|(1:44)(2:162|(2:164|165))|45)|(2:167|(19:169|(1:171)(2:172|(1:174)(2:175|(1:177)(1:178)))|47|(3:49|(1:51)(2:141|(2:143|144))|52)|(3:146|(1:148)(2:151|(1:153)(2:154|(1:156)(2:157|(1:159)(1:160))))|(1:150))|161|(3:55|(1:57)(2:121|(2:123|124))|58)|(2:126|(12:128|(1:130)(2:133|(1:135)(2:136|(1:138)(1:139)))|(9:132|(4:62|(1:64)(2:100|(2:102|103))|65|(5:67|(4:69|(1:71)(2:79|(2:81|82))|72|(4:74|75|76|77))|(2:84|(3:86|(1:88)(2:92|(1:94)(2:95|(1:97)(1:98)))|(4:90|75|76|77)(3:91|76|77)))|99|(0)(0)))|(2:105|(7:107|(1:109)(2:113|(1:115)(2:116|(1:118)(1:119)))|(1:111)(1:112)|(0)|(0)|99|(0)(0)))|120|(0)(0)|(0)|(0)|99|(0)(0))|60|(0)|(0)|120|(0)(0)|(0)|(0)|99|(0)(0)))|140|(0)|60|(0)|(0)|120|(0)(0)|(0)|(0)|99|(0)(0)))|179|(0)|(0)|161|(0)|(0)|140|(0)|60|(0)|(0)|120|(0)(0)|(0)|(0)|99|(0)(0)))|(2:185|(1:187)(2:190|(1:192)(2:193|(1:195)(2:196|(1:198)(1:199)))))|200|189|(0)|(0)|179|(0)|(0)|161|(0)|(0)|140|(0)|60|(0)|(0)|120|(0)(0)|(0)|(0)|99|(0)(0))|32|33|(0)|(0)|200|189|(0)|(0)|179|(0)|(0)|161|(0)|(0)|140|(0)|60|(0)|(0)|120|(0)(0)|(0)|(0)|99|(0)(0)))|220|(0)|32|33|(0)|(0)|200|189|(0)|(0)|179|(0)|(0)|161|(0)|(0)|140|(0)|60|(0)|(0)|120|(0)(0)|(0)|(0)|99|(0)(0))|15|(0)|(0)|247|233|234|25|(0)|(0)|220|(0)|32|33|(0)|(0)|200|189|(0)|(0)|179|(0)|(0)|161|(0)|(0)|140|(0)|60|(0)|(0)|120|(0)(0)|(0)|(0)|99|(0)(0)))|267|(0)|15|(0)|(0)|247|233|234|25|(0)|(0)|220|(0)|32|33|(0)|(0)|200|189|(0)|(0)|179|(0)|(0)|161|(0)|(0)|140|(0)|60|(0)|(0)|120|(0)(0)|(0)|(0)|99|(0)(0)))|285|8|(0)|(0)|267|(0)|15|(0)|(0)|247|233|234|25|(0)|(0)|220|(0)|32|33|(0)|(0)|200|189|(0)|(0)|179|(0)|(0)|161|(0)|(0)|140|(0)|60|(0)|(0)|120|(0)(0)|(0)|(0)|99|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00d7, code lost:
    
        if (r6 != null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x039f, code lost:
    
        if (r4 == null) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x022b, code lost:
    
        if (r6 == null) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x0234, code lost:
    
        android.util.Log.e(expo.modules.updates.UpdatesConfiguration.TAG, "Invalid value " + r6 + " for expo.modules.updates.EXPO_UPDATES_CHECK_ON_LAUNCH in AndroidManifest; defaulting to ALWAYS");
        r6 = expo.modules.updates.UpdatesConfiguration.CheckAutomaticallyConfiguration.ALWAYS;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0188, code lost:
    
        if (r6 == null) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0265, code lost:
    
        if (r6 != null) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x03bf, code lost:
    
        if (r4 == null) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0467, code lost:
    
        if (r4 == null) goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0511, code lost:
    
        if (r5 != null) goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001e, code lost:
    
        if (r5 == null) goto L14;
     */
    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Removed duplicated region for block: B:105:0x05e9  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0656  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0539  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x05a4  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x05a7  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x065a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0709  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public UpdatesConfiguration(android.content.Context r22, java.util.Map<java.lang.String, ? extends java.lang.Object> r23) {
        /*
            Method dump skipped, instructions count: 1813
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.updates.UpdatesConfiguration.<init>(android.content.Context, java.util.Map):void");
    }
}

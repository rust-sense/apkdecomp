package expo.modules.updates.codesigning;

import android.util.Base64;
import android.util.Log;
import expo.modules.structuredheaders.BooleanItem;
import expo.modules.structuredheaders.Dictionary;
import expo.modules.structuredheaders.StringItem;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.codesigning.CodeSigningAlgorithm;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CodeSigningConfiguration.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010\u0014\u001a\u00020\u0003J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003J\"\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u0002R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lexpo/modules/updates/codesigning/CodeSigningConfiguration;", "", "embeddedCertificateString", "", UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_METADATA, "", "includeManifestResponseCertificateChain", "", "allowUnsignedManifests", "(Ljava/lang/String;Ljava/util/Map;ZZ)V", "algorithmFromMetadata", "Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "getAlgorithmFromMetadata", "()Lexpo/modules/updates/codesigning/CodeSigningAlgorithm;", "algorithmFromMetadata$delegate", "Lkotlin/Lazy;", "keyIdFromMetadata", "getKeyIdFromMetadata", "()Ljava/lang/String;", "keyIdFromMetadata$delegate", "getAcceptSignatureHeader", "validateSignature", "Lexpo/modules/updates/codesigning/SignatureValidationResult;", "signature", "bodyBytes", "", "manifestResponseCertificateChain", "validateSignatureInternal", "info", "Lexpo/modules/updates/codesigning/SignatureHeaderInfo;", "Companion", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CodeSigningConfiguration {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* renamed from: algorithmFromMetadata$delegate, reason: from kotlin metadata */
    private final Lazy algorithmFromMetadata;
    private final boolean allowUnsignedManifests;
    private final Map<String, String> codeSigningMetadata;
    private final String embeddedCertificateString;
    private final boolean includeManifestResponseCertificateChain;

    /* renamed from: keyIdFromMetadata$delegate, reason: from kotlin metadata */
    private final Lazy keyIdFromMetadata;

    public CodeSigningConfiguration(String embeddedCertificateString, Map<String, String> map, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(embeddedCertificateString, "embeddedCertificateString");
        this.embeddedCertificateString = embeddedCertificateString;
        this.codeSigningMetadata = map;
        this.includeManifestResponseCertificateChain = z;
        this.allowUnsignedManifests = z2;
        this.algorithmFromMetadata = LazyKt.lazy(new Function0<CodeSigningAlgorithm>() { // from class: expo.modules.updates.codesigning.CodeSigningConfiguration$algorithmFromMetadata$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CodeSigningAlgorithm invoke() {
                Map map2;
                CodeSigningAlgorithm.Companion companion = CodeSigningAlgorithm.INSTANCE;
                map2 = CodeSigningConfiguration.this.codeSigningMetadata;
                return companion.parseFromString(map2 != null ? (String) map2.get("alg") : null);
            }
        });
        this.keyIdFromMetadata = LazyKt.lazy(new Function0<String>() { // from class: expo.modules.updates.codesigning.CodeSigningConfiguration$keyIdFromMetadata$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Map map2;
                String str;
                map2 = CodeSigningConfiguration.this.codeSigningMetadata;
                return (map2 == null || (str = (String) map2.get("keyid")) == null) ? CodeSigningAlgorithmKt.CODE_SIGNING_METADATA_DEFAULT_KEY_ID : str;
            }
        });
    }

    private final CodeSigningAlgorithm getAlgorithmFromMetadata() {
        return (CodeSigningAlgorithm) this.algorithmFromMetadata.getValue();
    }

    private final String getKeyIdFromMetadata() {
        return (String) this.keyIdFromMetadata.getValue();
    }

    public final SignatureValidationResult validateSignature(String signature, byte[] bodyBytes, String manifestResponseCertificateChain) {
        Intrinsics.checkNotNullParameter(bodyBytes, "bodyBytes");
        if (signature != null) {
            return validateSignatureInternal(SignatureHeaderInfo.INSTANCE.parseSignatureHeader(signature), bodyBytes, manifestResponseCertificateChain);
        }
        if (!this.allowUnsignedManifests) {
            throw new Exception("No expo-signature header specified");
        }
        return new SignatureValidationResult(ValidationResult.SKIPPED, null);
    }

    private final SignatureValidationResult validateSignatureInternal(SignatureHeaderInfo info, byte[] bodyBytes, String manifestResponseCertificateChain) {
        CertificateChain certificateChain;
        if (this.includeManifestResponseCertificateChain) {
            Companion companion = INSTANCE;
            if (manifestResponseCertificateChain == null) {
                manifestResponseCertificateChain = "";
            }
            certificateChain = new CertificateChain(CollectionsKt.plus((Collection<? extends String>) companion.separateCertificateChain(manifestResponseCertificateChain), this.embeddedCertificateString));
        } else {
            if (!Intrinsics.areEqual(info.getKeyId(), getKeyIdFromMetadata())) {
                throw new Exception("Key with keyid=" + info.getKeyId() + " from signature not found in client configuration");
            }
            if (info.getAlgorithm() != getAlgorithmFromMetadata()) {
                Log.i("CodeSigning", "Key with alg=" + info.getAlgorithm() + " from signature does not match client configuration algorithm, continuing");
            }
            certificateChain = new CertificateChain(CollectionsKt.listOf(this.embeddedCertificateString));
        }
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(certificateChain.getCodeSigningCertificate().getPublicKey());
        signature.update(bodyBytes);
        return new SignatureValidationResult(signature.verify(Base64.decode(info.getSignature(), 0)) ? ValidationResult.VALID : ValidationResult.INVALID, CertificateChain.INSTANCE.expoProjectInformation(certificateChain.getCodeSigningCertificate()));
    }

    public final String getAcceptSignatureHeader() {
        String serialize = Dictionary.valueOf(MapsKt.mapOf(TuplesKt.to(SignatureHeaderInfoKt.CODE_SIGNING_SIGNATURE_STRUCTURED_FIELD_KEY_SIGNATURE, BooleanItem.valueOf(true)), TuplesKt.to("keyid", StringItem.valueOf(getKeyIdFromMetadata())), TuplesKt.to("alg", StringItem.valueOf(getAlgorithmFromMetadata().getAlgorithmName())))).serialize();
        Intrinsics.checkNotNullExpressionValue(serialize, "serialize(...)");
        return serialize;
    }

    /* compiled from: CodeSigningConfiguration.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"Lexpo/modules/updates/codesigning/CodeSigningConfiguration$Companion;", "", "()V", "separateCertificateChain", "", "", "certificateChainInManifestResponse", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> separateCertificateChain(String certificateChainInManifestResponse) {
            Intrinsics.checkNotNullParameter(certificateChainInManifestResponse, "certificateChainInManifestResponse");
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                String str = certificateChainInManifestResponse;
                int i2 = i;
                int indexOf$default = StringsKt.indexOf$default((CharSequence) str, "-----BEGIN CERTIFICATE-----", i2, false, 4, (Object) null);
                int indexOf$default2 = StringsKt.indexOf$default((CharSequence) str, "-----END CERTIFICATE-----", i2, false, 4, (Object) null);
                if (indexOf$default == -1 || indexOf$default2 == -1) {
                    break;
                }
                i = indexOf$default2 + 25;
                String substring = certificateChainInManifestResponse.substring(indexOf$default, i);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                arrayList.add(substring);
            }
            return arrayList;
        }
    }
}

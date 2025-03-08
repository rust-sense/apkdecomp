package expo.modules.updates.codesigning;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CodeSigningConfiguration.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lexpo/modules/updates/codesigning/SignatureValidationResult;", "", "validationResult", "Lexpo/modules/updates/codesigning/ValidationResult;", "expoProjectInformation", "Lexpo/modules/updates/codesigning/ExpoProjectInformation;", "(Lexpo/modules/updates/codesigning/ValidationResult;Lexpo/modules/updates/codesigning/ExpoProjectInformation;)V", "getExpoProjectInformation", "()Lexpo/modules/updates/codesigning/ExpoProjectInformation;", "getValidationResult", "()Lexpo/modules/updates/codesigning/ValidationResult;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class SignatureValidationResult {
    private final ExpoProjectInformation expoProjectInformation;
    private final ValidationResult validationResult;

    public static /* synthetic */ SignatureValidationResult copy$default(SignatureValidationResult signatureValidationResult, ValidationResult validationResult, ExpoProjectInformation expoProjectInformation, int i, Object obj) {
        if ((i & 1) != 0) {
            validationResult = signatureValidationResult.validationResult;
        }
        if ((i & 2) != 0) {
            expoProjectInformation = signatureValidationResult.expoProjectInformation;
        }
        return signatureValidationResult.copy(validationResult, expoProjectInformation);
    }

    /* renamed from: component1, reason: from getter */
    public final ValidationResult getValidationResult() {
        return this.validationResult;
    }

    /* renamed from: component2, reason: from getter */
    public final ExpoProjectInformation getExpoProjectInformation() {
        return this.expoProjectInformation;
    }

    public final SignatureValidationResult copy(ValidationResult validationResult, ExpoProjectInformation expoProjectInformation) {
        Intrinsics.checkNotNullParameter(validationResult, "validationResult");
        return new SignatureValidationResult(validationResult, expoProjectInformation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SignatureValidationResult)) {
            return false;
        }
        SignatureValidationResult signatureValidationResult = (SignatureValidationResult) other;
        return this.validationResult == signatureValidationResult.validationResult && Intrinsics.areEqual(this.expoProjectInformation, signatureValidationResult.expoProjectInformation);
    }

    public final ExpoProjectInformation getExpoProjectInformation() {
        return this.expoProjectInformation;
    }

    public final ValidationResult getValidationResult() {
        return this.validationResult;
    }

    public int hashCode() {
        int hashCode = this.validationResult.hashCode() * 31;
        ExpoProjectInformation expoProjectInformation = this.expoProjectInformation;
        return hashCode + (expoProjectInformation == null ? 0 : expoProjectInformation.hashCode());
    }

    public String toString() {
        return "SignatureValidationResult(validationResult=" + this.validationResult + ", expoProjectInformation=" + this.expoProjectInformation + ")";
    }

    public SignatureValidationResult(ValidationResult validationResult, ExpoProjectInformation expoProjectInformation) {
        Intrinsics.checkNotNullParameter(validationResult, "validationResult");
        this.validationResult = validationResult;
        this.expoProjectInformation = expoProjectInformation;
    }
}

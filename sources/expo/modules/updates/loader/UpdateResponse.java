package expo.modules.updates.loader;

import expo.modules.updates.loader.UpdateResponsePart;
import expo.modules.updates.manifest.ResponseHeaderData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RemoteUpdate.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lexpo/modules/updates/loader/UpdateResponse;", "", "responseHeaderData", "Lexpo/modules/updates/manifest/ResponseHeaderData;", "manifestUpdateResponsePart", "Lexpo/modules/updates/loader/UpdateResponsePart$ManifestUpdateResponsePart;", "directiveUpdateResponsePart", "Lexpo/modules/updates/loader/UpdateResponsePart$DirectiveUpdateResponsePart;", "(Lexpo/modules/updates/manifest/ResponseHeaderData;Lexpo/modules/updates/loader/UpdateResponsePart$ManifestUpdateResponsePart;Lexpo/modules/updates/loader/UpdateResponsePart$DirectiveUpdateResponsePart;)V", "getDirectiveUpdateResponsePart", "()Lexpo/modules/updates/loader/UpdateResponsePart$DirectiveUpdateResponsePart;", "getManifestUpdateResponsePart", "()Lexpo/modules/updates/loader/UpdateResponsePart$ManifestUpdateResponsePart;", "getResponseHeaderData", "()Lexpo/modules/updates/manifest/ResponseHeaderData;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class UpdateResponse {
    private final UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart;
    private final UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart;
    private final ResponseHeaderData responseHeaderData;

    public static /* synthetic */ UpdateResponse copy$default(UpdateResponse updateResponse, ResponseHeaderData responseHeaderData, UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart, UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart, int i, Object obj) {
        if ((i & 1) != 0) {
            responseHeaderData = updateResponse.responseHeaderData;
        }
        if ((i & 2) != 0) {
            manifestUpdateResponsePart = updateResponse.manifestUpdateResponsePart;
        }
        if ((i & 4) != 0) {
            directiveUpdateResponsePart = updateResponse.directiveUpdateResponsePart;
        }
        return updateResponse.copy(responseHeaderData, manifestUpdateResponsePart, directiveUpdateResponsePart);
    }

    /* renamed from: component1, reason: from getter */
    public final ResponseHeaderData getResponseHeaderData() {
        return this.responseHeaderData;
    }

    /* renamed from: component2, reason: from getter */
    public final UpdateResponsePart.ManifestUpdateResponsePart getManifestUpdateResponsePart() {
        return this.manifestUpdateResponsePart;
    }

    /* renamed from: component3, reason: from getter */
    public final UpdateResponsePart.DirectiveUpdateResponsePart getDirectiveUpdateResponsePart() {
        return this.directiveUpdateResponsePart;
    }

    public final UpdateResponse copy(ResponseHeaderData responseHeaderData, UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart, UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart) {
        return new UpdateResponse(responseHeaderData, manifestUpdateResponsePart, directiveUpdateResponsePart);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdateResponse)) {
            return false;
        }
        UpdateResponse updateResponse = (UpdateResponse) other;
        return Intrinsics.areEqual(this.responseHeaderData, updateResponse.responseHeaderData) && Intrinsics.areEqual(this.manifestUpdateResponsePart, updateResponse.manifestUpdateResponsePart) && Intrinsics.areEqual(this.directiveUpdateResponsePart, updateResponse.directiveUpdateResponsePart);
    }

    public final UpdateResponsePart.DirectiveUpdateResponsePart getDirectiveUpdateResponsePart() {
        return this.directiveUpdateResponsePart;
    }

    public final UpdateResponsePart.ManifestUpdateResponsePart getManifestUpdateResponsePart() {
        return this.manifestUpdateResponsePart;
    }

    public final ResponseHeaderData getResponseHeaderData() {
        return this.responseHeaderData;
    }

    public int hashCode() {
        ResponseHeaderData responseHeaderData = this.responseHeaderData;
        int hashCode = (responseHeaderData == null ? 0 : responseHeaderData.hashCode()) * 31;
        UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart = this.manifestUpdateResponsePart;
        int hashCode2 = (hashCode + (manifestUpdateResponsePart == null ? 0 : manifestUpdateResponsePart.hashCode())) * 31;
        UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart = this.directiveUpdateResponsePart;
        return hashCode2 + (directiveUpdateResponsePart != null ? directiveUpdateResponsePart.hashCode() : 0);
    }

    public String toString() {
        return "UpdateResponse(responseHeaderData=" + this.responseHeaderData + ", manifestUpdateResponsePart=" + this.manifestUpdateResponsePart + ", directiveUpdateResponsePart=" + this.directiveUpdateResponsePart + ")";
    }

    public UpdateResponse(ResponseHeaderData responseHeaderData, UpdateResponsePart.ManifestUpdateResponsePart manifestUpdateResponsePart, UpdateResponsePart.DirectiveUpdateResponsePart directiveUpdateResponsePart) {
        this.responseHeaderData = responseHeaderData;
        this.manifestUpdateResponsePart = manifestUpdateResponsePart;
        this.directiveUpdateResponsePart = directiveUpdateResponsePart;
    }
}

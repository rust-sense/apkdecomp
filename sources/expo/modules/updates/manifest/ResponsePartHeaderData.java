package expo.modules.updates.manifest;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ResponseHeaderData.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lexpo/modules/updates/manifest/ResponsePartHeaderData;", "", "signature", "", "(Ljava/lang/String;)V", "getSignature", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "expo-updates_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ResponsePartHeaderData {
    private final String signature;

    /* JADX WARN: Multi-variable type inference failed */
    public ResponsePartHeaderData() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ ResponsePartHeaderData copy$default(ResponsePartHeaderData responsePartHeaderData, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = responsePartHeaderData.signature;
        }
        return responsePartHeaderData.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSignature() {
        return this.signature;
    }

    public final ResponsePartHeaderData copy(String signature) {
        return new ResponsePartHeaderData(signature);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ResponsePartHeaderData) && Intrinsics.areEqual(this.signature, ((ResponsePartHeaderData) other).signature);
    }

    public final String getSignature() {
        return this.signature;
    }

    public int hashCode() {
        String str = this.signature;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public String toString() {
        return "ResponsePartHeaderData(signature=" + this.signature + ")";
    }

    public ResponsePartHeaderData(String str) {
        this.signature = str;
    }

    public /* synthetic */ ResponsePartHeaderData(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }
}

package expo.modules.image;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlideModel.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0005\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0004¨\u0006\u0011"}, d2 = {"Lexpo/modules/image/GlideThumbhashModel;", "Lexpo/modules/image/GlideModel;", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;)V", "glideData", "getGlideData", "()Lexpo/modules/image/GlideThumbhashModel;", "getUri", "()Landroid/net/Uri;", "setUri", "equals", "", "other", "", "hashCode", "", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GlideThumbhashModel extends GlideModel {
    private final GlideThumbhashModel glideData;
    private Uri uri;

    @Override // expo.modules.image.GlideModel
    public GlideThumbhashModel getGlideData() {
        return this.glideData;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final void setUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<set-?>");
        this.uri = uri;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlideThumbhashModel(Uri uri) {
        super(null);
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.uri = uri;
        this.glideData = this;
    }

    @Override // expo.modules.image.GlideModel
    public boolean equals(Object other) {
        return this == other || ((other instanceof GlideThumbhashModel) && Intrinsics.areEqual(this.uri, ((GlideThumbhashModel) other).uri));
    }

    @Override // expo.modules.image.GlideModel
    public int hashCode() {
        return this.uri.hashCode();
    }
}

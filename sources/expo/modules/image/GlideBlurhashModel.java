package expo.modules.image;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlideModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0005H\u0016R\u0014\u0010\b\u001a\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0015"}, d2 = {"Lexpo/modules/image/GlideBlurhashModel;", "Lexpo/modules/image/GlideModel;", "uri", "Landroid/net/Uri;", "width", "", "height", "(Landroid/net/Uri;II)V", "glideData", "getGlideData", "()Lexpo/modules/image/GlideBlurhashModel;", "getHeight", "()I", "getUri", "()Landroid/net/Uri;", "getWidth", "equals", "", "other", "", "hashCode", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GlideBlurhashModel extends GlideModel {
    private final GlideBlurhashModel glideData;
    private final int height;
    private final Uri uri;
    private final int width;

    @Override // expo.modules.image.GlideModel
    public GlideBlurhashModel getGlideData() {
        return this.glideData;
    }

    public final int getHeight() {
        return this.height;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final int getWidth() {
        return this.width;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlideBlurhashModel(Uri uri, int i, int i2) {
        super(null);
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.uri = uri;
        this.width = i;
        this.height = i2;
        this.glideData = this;
    }

    @Override // expo.modules.image.GlideModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof GlideBlurhashModel) {
            GlideBlurhashModel glideBlurhashModel = (GlideBlurhashModel) other;
            if (Intrinsics.areEqual(this.uri, glideBlurhashModel.uri) && this.width == glideBlurhashModel.width && this.height == glideBlurhashModel.height) {
                return true;
            }
        }
        return false;
    }

    @Override // expo.modules.image.GlideModel
    public int hashCode() {
        return (((this.uri.hashCode() * 31) + this.width) * 31) + this.height;
    }
}

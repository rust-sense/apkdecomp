package expo.modules.image;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GlideModel.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/image/GlideUriModel;", "Lexpo/modules/image/GlideModel;", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;)V", "glideData", "getGlideData", "()Landroid/net/Uri;", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GlideUriModel extends GlideModel {
    private final Uri glideData;

    @Override // expo.modules.image.GlideModel
    public Uri getGlideData() {
        return this.glideData;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlideUriModel(Uri uri) {
        super(null);
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.glideData = uri;
    }
}

package expo.modules.image.okhttp;

import com.bumptech.glide.load.model.GlideUrl;
import expo.modules.image.events.OkHttpProgressListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoImageOkHttpClientGlideModule.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lexpo/modules/image/okhttp/GlideUrlWrapper;", "", "glideUrl", "Lcom/bumptech/glide/load/model/GlideUrl;", "(Lcom/bumptech/glide/load/model/GlideUrl;)V", "getGlideUrl", "()Lcom/bumptech/glide/load/model/GlideUrl;", "progressListener", "Lexpo/modules/image/events/OkHttpProgressListener;", "getProgressListener", "()Lexpo/modules/image/events/OkHttpProgressListener;", "setProgressListener", "(Lexpo/modules/image/events/OkHttpProgressListener;)V", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class GlideUrlWrapper {
    private final GlideUrl glideUrl;
    private OkHttpProgressListener progressListener;

    public static /* synthetic */ GlideUrlWrapper copy$default(GlideUrlWrapper glideUrlWrapper, GlideUrl glideUrl, int i, Object obj) {
        if ((i & 1) != 0) {
            glideUrl = glideUrlWrapper.glideUrl;
        }
        return glideUrlWrapper.copy(glideUrl);
    }

    /* renamed from: component1, reason: from getter */
    public final GlideUrl getGlideUrl() {
        return this.glideUrl;
    }

    public final GlideUrlWrapper copy(GlideUrl glideUrl) {
        Intrinsics.checkNotNullParameter(glideUrl, "glideUrl");
        return new GlideUrlWrapper(glideUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof GlideUrlWrapper) && Intrinsics.areEqual(this.glideUrl, ((GlideUrlWrapper) other).glideUrl);
    }

    public final GlideUrl getGlideUrl() {
        return this.glideUrl;
    }

    public final OkHttpProgressListener getProgressListener() {
        return this.progressListener;
    }

    public int hashCode() {
        return this.glideUrl.hashCode();
    }

    public final void setProgressListener(OkHttpProgressListener okHttpProgressListener) {
        this.progressListener = okHttpProgressListener;
    }

    public GlideUrlWrapper(GlideUrl glideUrl) {
        Intrinsics.checkNotNullParameter(glideUrl, "glideUrl");
        this.glideUrl = glideUrl;
    }

    public String toString() {
        String glideUrl = this.glideUrl.toString();
        Intrinsics.checkNotNullExpressionValue(glideUrl, "toString(...)");
        return glideUrl;
    }
}

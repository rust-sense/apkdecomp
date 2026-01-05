package expo.modules.image.svg;

import android.content.Context;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGStylerKt;
import expo.modules.image.CustomOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SVGDrawableTranscoder.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J&\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lexpo/modules/image/svg/SVGDrawableTranscoder;", "Lcom/bumptech/glide/load/resource/transcode/ResourceTranscoder;", "Lcom/caverock/androidsvg/SVG;", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "transcode", "Lcom/bumptech/glide/load/engine/Resource;", "toTranscode", "options", "Lcom/bumptech/glide/load/Options;", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SVGDrawableTranscoder implements ResourceTranscoder<SVG, Drawable> {
    private final Context context;

    public final Context getContext() {
        return this.context;
    }

    public SVGDrawableTranscoder(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.bumptech.glide.load.resource.transcode.ResourceTranscoder
    public Resource<Drawable> transcode(Resource<SVG> toTranscode, Options options) {
        Intrinsics.checkNotNullParameter(toTranscode, "toTranscode");
        Intrinsics.checkNotNullParameter(options, "options");
        SVG svg = toTranscode.get();
        Intrinsics.checkNotNullExpressionValue(svg, "get(...)");
        SVG svg2 = svg;
        RectF documentViewBox = svg2.getDocumentViewBox();
        int width = documentViewBox != null ? (int) documentViewBox.width() : 512;
        RectF documentViewBox2 = svg2.getDocumentViewBox();
        int height = documentViewBox2 != null ? (int) documentViewBox2.height() : 512;
        Integer num = (Integer) options.get(CustomOptions.INSTANCE.getTintColor());
        if (num != null) {
            SVGStylerKt.applyTintColor(svg2, num.intValue());
        }
        Picture renderToPicture = svg2.renderToPicture();
        Intrinsics.checkNotNullExpressionValue(renderToPicture, "renderToPicture(...)");
        return new SimpleResource(new SVGPictureDrawable(renderToPicture, width, height));
    }
}

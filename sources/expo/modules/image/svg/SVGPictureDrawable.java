package expo.modules.image.svg;

import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SVGDrawableTranscoder.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lexpo/modules/image/svg/SVGPictureDrawable;", "Landroid/graphics/drawable/PictureDrawable;", "picture", "Landroid/graphics/Picture;", "svgIntrinsicWidth", "", "svgIntrinsicHeight", "(Landroid/graphics/Picture;II)V", "getSvgIntrinsicHeight", "()I", "getSvgIntrinsicWidth", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SVGPictureDrawable extends PictureDrawable {
    private final int svgIntrinsicHeight;
    private final int svgIntrinsicWidth;

    public final int getSvgIntrinsicHeight() {
        return this.svgIntrinsicHeight;
    }

    public final int getSvgIntrinsicWidth() {
        return this.svgIntrinsicWidth;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SVGPictureDrawable(Picture picture, int i, int i2) {
        super(picture);
        Intrinsics.checkNotNullParameter(picture, "picture");
        this.svgIntrinsicWidth = i;
        this.svgIntrinsicHeight = i2;
    }
}

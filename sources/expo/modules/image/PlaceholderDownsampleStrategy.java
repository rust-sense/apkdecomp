package expo.modules.image;

import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.facebook.react.uimanager.events.TouchesHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomDownsampleStrategy.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0016J(\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lexpo/modules/image/PlaceholderDownsampleStrategy;", "Lexpo/modules/image/CustomDownsampleStrategy;", TouchesHelper.TARGET_KEY, "Lexpo/modules/image/ImageViewWrapperTarget;", "(Lexpo/modules/image/ImageViewWrapperTarget;)V", "wasTriggered", "", "getSampleSizeRounding", "Lcom/bumptech/glide/load/resource/bitmap/DownsampleStrategy$SampleSizeRounding;", "sourceWidth", "", "sourceHeight", "requestedWidth", "requestedHeight", "getScaleFactor", "", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PlaceholderDownsampleStrategy extends CustomDownsampleStrategy {
    private final ImageViewWrapperTarget target;
    private boolean wasTriggered;

    public PlaceholderDownsampleStrategy(ImageViewWrapperTarget target) {
        Intrinsics.checkNotNullParameter(target, "target");
        this.target = target;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
    public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        if (this.wasTriggered) {
            return 1.0f;
        }
        this.target.setPlaceholderWidth(sourceWidth);
        this.target.setPlaceholderHeight(sourceHeight);
        this.wasTriggered = true;
        return 1.0f;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
    public DownsampleStrategy.SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        return DownsampleStrategy.SampleSizeRounding.QUALITY;
    }
}

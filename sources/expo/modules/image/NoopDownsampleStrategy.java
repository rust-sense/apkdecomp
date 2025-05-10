package expo.modules.image;

import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import kotlin.Metadata;

/* compiled from: CustomDownsampleStrategy.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0016J(\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"Lexpo/modules/image/NoopDownsampleStrategy;", "Lcom/bumptech/glide/load/resource/bitmap/DownsampleStrategy;", "()V", "getSampleSizeRounding", "Lcom/bumptech/glide/load/resource/bitmap/DownsampleStrategy$SampleSizeRounding;", "sourceWidth", "", "sourceHeight", "requestedWidth", "requestedHeight", "getScaleFactor", "", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NoopDownsampleStrategy extends DownsampleStrategy {
    public static final NoopDownsampleStrategy INSTANCE = new NoopDownsampleStrategy();

    @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
    public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        return 1.0f;
    }

    private NoopDownsampleStrategy() {
    }

    @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
    public DownsampleStrategy.SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        return DownsampleStrategy.SampleSizeRounding.QUALITY;
    }
}

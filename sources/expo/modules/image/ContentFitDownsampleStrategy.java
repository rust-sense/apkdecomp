package expo.modules.image;

import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.facebook.react.uimanager.events.TouchesHelper;
import expo.modules.image.enums.ContentFit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomDownsampleStrategy.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0002J(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0011H\u0016J(\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lexpo/modules/image/ContentFitDownsampleStrategy;", "Lexpo/modules/image/CustomDownsampleStrategy;", TouchesHelper.TARGET_KEY, "Lexpo/modules/image/ImageViewWrapperTarget;", "contentFit", "Lexpo/modules/image/enums/ContentFit;", "(Lexpo/modules/image/ImageViewWrapperTarget;Lexpo/modules/image/enums/ContentFit;)V", "wasTriggered", "", "calculateScaleFactor", "", "sourceWidth", "sourceHeight", "requestedWidth", "requestedHeight", "getSampleSizeRounding", "Lcom/bumptech/glide/load/resource/bitmap/DownsampleStrategy$SampleSizeRounding;", "", "getScaleFactor", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ContentFitDownsampleStrategy extends CustomDownsampleStrategy {
    private final ContentFit contentFit;
    private final ImageViewWrapperTarget target;
    private boolean wasTriggered;

    /* compiled from: CustomDownsampleStrategy.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContentFit.values().length];
            try {
                iArr[ContentFit.Contain.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ContentFit.Cover.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ContentFit.ScaleDown.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ContentFitDownsampleStrategy(ImageViewWrapperTarget target, ContentFit contentFit) {
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(contentFit, "contentFit");
        this.target = target;
        this.contentFit = contentFit;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
    public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        if (!this.wasTriggered) {
            this.target.setSourceWidth(sourceWidth);
            this.target.setSourceHeight(sourceHeight);
            this.wasTriggered = true;
        }
        if (requestedWidth == Integer.MIN_VALUE || requestedHeight == Integer.MIN_VALUE) {
            return 1.0f;
        }
        return Math.min(1.0f, calculateScaleFactor(sourceWidth, sourceHeight, requestedWidth, requestedHeight));
    }

    private final float calculateScaleFactor(float sourceWidth, float sourceHeight, float requestedWidth, float requestedHeight) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.contentFit.ordinal()];
        if (i == 1) {
            return Math.min(requestedWidth / sourceWidth, requestedHeight / sourceHeight);
        }
        if (i == 2) {
            return Math.max(requestedWidth / sourceWidth, requestedHeight / sourceHeight);
        }
        if (i != 3) {
            return 1.0f;
        }
        if (requestedWidth < sourceWidth || requestedHeight < sourceHeight) {
            return Math.min(requestedWidth / sourceWidth, requestedHeight / sourceHeight);
        }
        return 1.0f;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
    public DownsampleStrategy.SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        return DownsampleStrategy.SampleSizeRounding.QUALITY;
    }
}

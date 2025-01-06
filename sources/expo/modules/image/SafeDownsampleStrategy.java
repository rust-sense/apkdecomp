package expo.modules.image;

import android.os.Build;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import expo.modules.image.records.DecodeFormat;
import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: CustomDownsampleStrategy.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J(\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J\b\u0010\u0017\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Lexpo/modules/image/SafeDownsampleStrategy;", "Lexpo/modules/image/CustomDownsampleStrategy;", "decodeFormat", "Lexpo/modules/image/records/DecodeFormat;", "(Lexpo/modules/image/records/DecodeFormat;)V", "maxBitmapSize", "", "getMaxBitmapSize", "()I", "maxBitmapSize$delegate", "Lkotlin/Lazy;", "equals", "", "other", "", "getSampleSizeRounding", "Lcom/bumptech/glide/load/resource/bitmap/DownsampleStrategy$SampleSizeRounding;", "sourceWidth", "sourceHeight", "requestedWidth", "requestedHeight", "getScaleFactor", "", "hashCode", "expo-image_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SafeDownsampleStrategy extends CustomDownsampleStrategy {
    private final DecodeFormat decodeFormat;

    /* renamed from: maxBitmapSize$delegate, reason: from kotlin metadata */
    private final Lazy maxBitmapSize;

    public SafeDownsampleStrategy(DecodeFormat decodeFormat) {
        Intrinsics.checkNotNullParameter(decodeFormat, "decodeFormat");
        this.decodeFormat = decodeFormat;
        this.maxBitmapSize = LazyKt.lazy(new Function0<Integer>() { // from class: expo.modules.image.SafeDownsampleStrategy$maxBitmapSize$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                int i = -1;
                if (Build.VERSION.SDK_INT < 29) {
                    return -1;
                }
                try {
                    Method method = Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE);
                    method.setAccessible(true);
                    Object invoke = method.invoke(null, "ro.hwui.max_texture_allocation_size", 104857600);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.Int");
                    i = RangesKt.coerceAtLeast(((Integer) invoke).intValue(), 104857600);
                } catch (Throwable unused) {
                }
                return Integer.valueOf(i);
            }
        });
    }

    @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
    public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        if (getMaxBitmapSize() <= 0 || sourceWidth * sourceHeight * this.decodeFormat.toBytes() <= getMaxBitmapSize()) {
            return 1.0f;
        }
        return (float) (((int) Math.floor(Math.sqrt((getMaxBitmapSize() / this.decodeFormat.toBytes()) / (Math.min(sourceWidth, sourceHeight) / Math.max(sourceWidth, sourceHeight))))) / Math.max(sourceWidth, sourceHeight));
    }

    @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
    public DownsampleStrategy.SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
        return DownsampleStrategy.SampleSizeRounding.MEMORY;
    }

    private final int getMaxBitmapSize() {
        return ((Number) this.maxBitmapSize.getValue()).intValue();
    }

    @Override // expo.modules.image.CustomDownsampleStrategy
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SafeDownsampleStrategy) && this.decodeFormat == ((SafeDownsampleStrategy) other).decodeFormat;
    }

    @Override // expo.modules.image.CustomDownsampleStrategy
    public int hashCode() {
        return (super.hashCode() * 31) + this.decodeFormat.hashCode();
    }
}

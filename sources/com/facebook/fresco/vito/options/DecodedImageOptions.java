package com.facebook.fresco.vito.options;

import android.graphics.Bitmap;
import android.graphics.PointF;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.common.internal.Objects;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.fresco.vito.options.EncodedImageOptions;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.Postprocessor;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DecodedImageOptions.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001=B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0006\u00102\u001a\u00020\u001aJ\u0010\u00103\u001a\u00020\u001a2\u0006\u00104\u001a\u00020\u0000H\u0004J\u0013\u00105\u001a\u00020\u001a2\b\u00104\u001a\u0004\u0018\u000106H\u0096\u0002J\b\u00107\u001a\u000208H\u0016J\b\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u00020<H\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u0019\u0010\u001bR\u0011\u0010\u001d\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0013\u0010\"\u001a\u0004\u0018\u00010#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010&\u001a\u0004\u0018\u00010'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0013\u0010*\u001a\u0004\u0018\u00010+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010.\u001a\u0004\u0018\u00010/¢\u0006\b\n\u0000\u001a\u0004\b0\u00101¨\u0006>"}, d2 = {"Lcom/facebook/fresco/vito/options/DecodedImageOptions;", "Lcom/facebook/fresco/vito/options/EncodedImageOptions;", "builder", "Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "(Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;)V", "actualImageFocusPoint", "Landroid/graphics/PointF;", "getActualImageFocusPoint", "()Landroid/graphics/PointF;", "actualImageScaleType", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "getActualImageScaleType", "()Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "borderOptions", "Lcom/facebook/fresco/vito/options/BorderOptions;", "getBorderOptions", "()Lcom/facebook/fresco/vito/options/BorderOptions;", "imageDecodeOptions", "Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "getImageDecodeOptions", "()Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "isProgressiveDecodingEnabled", "", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "loadThumbnailOnly", "getLoadThumbnailOnly", "()Z", "mLocalThumbnailPreviewsEnabled", "getMLocalThumbnailPreviewsEnabled", "postprocessor", "Lcom/facebook/imagepipeline/request/Postprocessor;", "getPostprocessor", "()Lcom/facebook/imagepipeline/request/Postprocessor;", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "getResizeOptions", "()Lcom/facebook/imagepipeline/common/ResizeOptions;", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "getRotationOptions", "()Lcom/facebook/imagepipeline/common/RotationOptions;", "roundingOptions", "Lcom/facebook/fresco/vito/options/RoundingOptions;", "getRoundingOptions", "()Lcom/facebook/fresco/vito/options/RoundingOptions;", "areLocalThumbnailPreviewsEnabled", "equalDecodedOptions", "other", "equals", "", "hashCode", "", "toString", "", "toStringHelper", "Lcom/facebook/common/internal/Objects$ToStringHelper;", "Builder", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public class DecodedImageOptions extends EncodedImageOptions {
    private final PointF actualImageFocusPoint;
    private final ScalingUtils.ScaleType actualImageScaleType;
    private final Bitmap.Config bitmapConfig;
    private final BorderOptions borderOptions;
    private final ImageDecodeOptions imageDecodeOptions;
    private final Boolean isProgressiveDecodingEnabled;
    private final boolean loadThumbnailOnly;
    private final boolean mLocalThumbnailPreviewsEnabled;
    private final Postprocessor postprocessor;
    private final ResizeOptions resizeOptions;
    private final RotationOptions rotationOptions;
    private final RoundingOptions roundingOptions;

    /* renamed from: areLocalThumbnailPreviewsEnabled, reason: from getter */
    public final boolean getMLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public final PointF getActualImageFocusPoint() {
        return this.actualImageFocusPoint;
    }

    public final ScalingUtils.ScaleType getActualImageScaleType() {
        return this.actualImageScaleType;
    }

    public final Bitmap.Config getBitmapConfig() {
        return this.bitmapConfig;
    }

    public final BorderOptions getBorderOptions() {
        return this.borderOptions;
    }

    public final ImageDecodeOptions getImageDecodeOptions() {
        return this.imageDecodeOptions;
    }

    public final boolean getLoadThumbnailOnly() {
        return this.loadThumbnailOnly;
    }

    public final boolean getMLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public final Postprocessor getPostprocessor() {
        return this.postprocessor;
    }

    public final ResizeOptions getResizeOptions() {
        return this.resizeOptions;
    }

    public final RotationOptions getRotationOptions() {
        return this.rotationOptions;
    }

    public final RoundingOptions getRoundingOptions() {
        return this.roundingOptions;
    }

    /* renamed from: isProgressiveDecodingEnabled, reason: from getter */
    public final Boolean getIsProgressiveDecodingEnabled() {
        return this.isProgressiveDecodingEnabled;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DecodedImageOptions(Builder<?> builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.resizeOptions = builder.getResizeOptions();
        this.rotationOptions = builder.getRotationOptions();
        this.postprocessor = builder.getPostprocessor();
        this.imageDecodeOptions = builder.getImageDecodeOptions();
        this.roundingOptions = builder.getRoundingOptions();
        this.borderOptions = builder.getBorderOptions();
        this.actualImageScaleType = builder.getActualImageScaleType();
        this.actualImageFocusPoint = builder.getActualFocusPoint();
        this.mLocalThumbnailPreviewsEnabled = builder.getLocalThumbnailPreviewsEnabled();
        this.loadThumbnailOnly = builder.getLoadThumbnailOnly();
        this.bitmapConfig = builder.getBitmapConfig();
        this.isProgressiveDecodingEnabled = builder.getProgressiveDecodingEnabled();
    }

    @Override // com.facebook.fresco.vito.options.EncodedImageOptions
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        return equalDecodedOptions((DecodedImageOptions) other);
    }

    protected final boolean equalDecodedOptions(DecodedImageOptions other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (Objects.equal(this.resizeOptions, other.resizeOptions) && Objects.equal(this.rotationOptions, other.rotationOptions) && Objects.equal(this.postprocessor, other.postprocessor) && Objects.equal(this.imageDecodeOptions, other.imageDecodeOptions) && Objects.equal(this.roundingOptions, other.roundingOptions) && Objects.equal(this.borderOptions, other.borderOptions) && Objects.equal(this.actualImageScaleType, other.actualImageScaleType) && Objects.equal(this.actualImageFocusPoint, other.actualImageFocusPoint) && this.mLocalThumbnailPreviewsEnabled == other.mLocalThumbnailPreviewsEnabled && this.loadThumbnailOnly == other.loadThumbnailOnly && this.isProgressiveDecodingEnabled == other.isProgressiveDecodingEnabled && Objects.equal(this.bitmapConfig, other.bitmapConfig)) {
            return equalEncodedOptions(other);
        }
        return false;
    }

    @Override // com.facebook.fresco.vito.options.EncodedImageOptions
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        ResizeOptions resizeOptions = this.resizeOptions;
        int hashCode2 = (hashCode + (resizeOptions != null ? resizeOptions.hashCode() : 0)) * 31;
        RotationOptions rotationOptions = this.rotationOptions;
        int hashCode3 = (hashCode2 + (rotationOptions != null ? rotationOptions.hashCode() : 0)) * 31;
        Postprocessor postprocessor = this.postprocessor;
        int hashCode4 = (hashCode3 + (postprocessor != null ? postprocessor.hashCode() : 0)) * 31;
        ImageDecodeOptions imageDecodeOptions = this.imageDecodeOptions;
        int hashCode5 = (hashCode4 + (imageDecodeOptions != null ? imageDecodeOptions.hashCode() : 0)) * 31;
        RoundingOptions roundingOptions = this.roundingOptions;
        int hashCode6 = (hashCode5 + (roundingOptions != null ? roundingOptions.hashCode() : 0)) * 31;
        BorderOptions borderOptions = this.borderOptions;
        int hashCode7 = (((hashCode6 + (borderOptions != null ? borderOptions.hashCode() : 0)) * 31) + this.actualImageScaleType.hashCode()) * 31;
        PointF pointF = this.actualImageFocusPoint;
        int hashCode8 = (((((hashCode7 + (pointF != null ? pointF.hashCode() : 0)) * 31) + (this.mLocalThumbnailPreviewsEnabled ? 1 : 0)) * 31) + (this.loadThumbnailOnly ? 1 : 0)) * 31;
        Bitmap.Config config = this.bitmapConfig;
        int hashCode9 = (hashCode8 + (config != null ? config.hashCode() : 0)) * 31;
        Boolean bool = this.isProgressiveDecodingEnabled;
        return hashCode9 + (bool != null ? bool.hashCode() : 0);
    }

    @Override // com.facebook.fresco.vito.options.EncodedImageOptions
    public String toString() {
        return "DecodedImageOptions{" + toStringHelper() + '}';
    }

    @Override // com.facebook.fresco.vito.options.EncodedImageOptions
    protected Objects.ToStringHelper toStringHelper() {
        Objects.ToStringHelper add = super.toStringHelper().add("resizeOptions", this.resizeOptions).add("rotationOptions", this.resizeOptions).add("postprocessor", this.postprocessor).add("imageDecodeOptions", this.imageDecodeOptions).add("roundingOptions", this.roundingOptions).add("borderOptions", this.borderOptions).add("actualImageScaleType", this.actualImageScaleType).add("actualImageFocusPoint", this.actualImageFocusPoint).add("localThumbnailPreviewsEnabled", this.mLocalThumbnailPreviewsEnabled).add("loadThumbnailOnly", this.loadThumbnailOnly).add("bitmapConfig", this.bitmapConfig).add("progressiveRenderingEnabled", this.isProgressiveDecodingEnabled);
        Intrinsics.checkNotNullExpressionValue(add, "super.toStringHelper()\n …ogressiveDecodingEnabled)");
        return add;
    }

    /* compiled from: DecodedImageOptions.kt */
    @Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0013\u001a\u00028\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010LJ\u0015\u0010M\u001a\u00028\u00002\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\u0002\u0010NJ\b\u0010O\u001a\u00020PH\u0016J\u0015\u0010Q\u001a\u00028\u00002\b\u0010Q\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010RJ\u0015\u0010\u001f\u001a\u00028\u00002\b\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\u0002\u0010SJ\u0013\u0010%\u001a\u00028\u00002\u0006\u0010%\u001a\u00020&¢\u0006\u0002\u0010TJ\u0013\u0010+\u001a\u00028\u00002\u0006\u0010+\u001a\u00020&¢\u0006\u0002\u0010TJ-\u0010U\u001a\u00028\u00002\u001d\u0010V\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0012\u0004\u0012\u00020X0W¢\u0006\u0002\bYH\u0082\b¢\u0006\u0002\u0010ZJ\u0015\u0010[\u001a\u00028\u00002\b\u0010.\u001a\u0004\u0018\u00010/¢\u0006\u0002\u0010\\J\u0015\u0010]\u001a\u00028\u00002\b\u00104\u001a\u0004\u0018\u00010&¢\u0006\u0002\u0010^J\u0015\u0010_\u001a\u00028\u00002\b\u0010:\u001a\u0004\u0018\u00010;¢\u0006\u0002\u0010`J\u0015\u0010a\u001a\u00028\u00002\b\u0010@\u001a\u0004\u0018\u00010A¢\u0006\u0002\u0010bJ\u0015\u0010c\u001a\u00028\u00002\b\u0010F\u001a\u0004\u0018\u00010G¢\u0006\u0002\u0010dJ\u0015\u0010e\u001a\u00028\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*R\u001c\u0010.\u001a\u0004\u0018\u00010/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001e\u00104\u001a\u0004\u0018\u00010&X\u0080\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u0010:\u001a\u0004\u0018\u00010;X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001c\u0010F\u001a\u0004\u0018\u00010GX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010K¨\u0006g"}, d2 = {"Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/facebook/fresco/vito/options/EncodedImageOptions$Builder;", "()V", "defaultOptions", "Lcom/facebook/fresco/vito/options/ImageOptions;", "(Lcom/facebook/fresco/vito/options/ImageOptions;)V", "actualFocusPoint", "Landroid/graphics/PointF;", "getActualFocusPoint$options_release", "()Landroid/graphics/PointF;", "setActualFocusPoint$options_release", "(Landroid/graphics/PointF;)V", "actualImageScaleType", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "getActualImageScaleType$options_release", "()Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "setActualImageScaleType$options_release", "(Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;)V", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig$options_release", "()Landroid/graphics/Bitmap$Config;", "setBitmapConfig$options_release", "(Landroid/graphics/Bitmap$Config;)V", "borderOptions", "Lcom/facebook/fresco/vito/options/BorderOptions;", "getBorderOptions$options_release", "()Lcom/facebook/fresco/vito/options/BorderOptions;", "setBorderOptions$options_release", "(Lcom/facebook/fresco/vito/options/BorderOptions;)V", "imageDecodeOptions", "Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "getImageDecodeOptions$options_release", "()Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "setImageDecodeOptions$options_release", "(Lcom/facebook/imagepipeline/common/ImageDecodeOptions;)V", "loadThumbnailOnly", "", "getLoadThumbnailOnly$options_release", "()Z", "setLoadThumbnailOnly$options_release", "(Z)V", "localThumbnailPreviewsEnabled", "getLocalThumbnailPreviewsEnabled$options_release", "setLocalThumbnailPreviewsEnabled$options_release", "postprocessor", "Lcom/facebook/imagepipeline/request/Postprocessor;", "getPostprocessor$options_release", "()Lcom/facebook/imagepipeline/request/Postprocessor;", "setPostprocessor$options_release", "(Lcom/facebook/imagepipeline/request/Postprocessor;)V", "progressiveDecodingEnabled", "getProgressiveDecodingEnabled$options_release", "()Ljava/lang/Boolean;", "setProgressiveDecodingEnabled$options_release", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "getResizeOptions$options_release", "()Lcom/facebook/imagepipeline/common/ResizeOptions;", "setResizeOptions$options_release", "(Lcom/facebook/imagepipeline/common/ResizeOptions;)V", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "getRotationOptions$options_release", "()Lcom/facebook/imagepipeline/common/RotationOptions;", "setRotationOptions$options_release", "(Lcom/facebook/imagepipeline/common/RotationOptions;)V", "roundingOptions", "Lcom/facebook/fresco/vito/options/RoundingOptions;", "getRoundingOptions$options_release", "()Lcom/facebook/fresco/vito/options/RoundingOptions;", "setRoundingOptions$options_release", "(Lcom/facebook/fresco/vito/options/RoundingOptions;)V", "(Landroid/graphics/Bitmap$Config;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "borders", "(Lcom/facebook/fresco/vito/options/BorderOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", OperatingSystem.JsonKeys.BUILD, "Lcom/facebook/fresco/vito/options/DecodedImageOptions;", "focusPoint", "(Landroid/graphics/PointF;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "(Lcom/facebook/imagepipeline/common/ImageDecodeOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "(Z)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "modify", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "postprocess", "(Lcom/facebook/imagepipeline/request/Postprocessor;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "progressiveRendering", "(Ljava/lang/Boolean;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "resize", "(Lcom/facebook/imagepipeline/common/ResizeOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "rotate", "(Lcom/facebook/imagepipeline/common/RotationOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "round", "(Lcom/facebook/fresco/vito/options/RoundingOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "scale", "(Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static class Builder<T extends Builder<T>> extends EncodedImageOptions.Builder<T> {
        private PointF actualFocusPoint;
        private ScalingUtils.ScaleType actualImageScaleType;
        private Bitmap.Config bitmapConfig;
        private BorderOptions borderOptions;
        private ImageDecodeOptions imageDecodeOptions;
        private boolean loadThumbnailOnly;
        private boolean localThumbnailPreviewsEnabled;
        private Postprocessor postprocessor;
        private Boolean progressiveDecodingEnabled;
        private ResizeOptions resizeOptions;
        private RotationOptions rotationOptions;
        private RoundingOptions roundingOptions;

        /* renamed from: getActualFocusPoint$options_release, reason: from getter */
        public final PointF getActualFocusPoint() {
            return this.actualFocusPoint;
        }

        /* renamed from: getActualImageScaleType$options_release, reason: from getter */
        public final ScalingUtils.ScaleType getActualImageScaleType() {
            return this.actualImageScaleType;
        }

        /* renamed from: getBitmapConfig$options_release, reason: from getter */
        public final Bitmap.Config getBitmapConfig() {
            return this.bitmapConfig;
        }

        /* renamed from: getBorderOptions$options_release, reason: from getter */
        public final BorderOptions getBorderOptions() {
            return this.borderOptions;
        }

        /* renamed from: getImageDecodeOptions$options_release, reason: from getter */
        public final ImageDecodeOptions getImageDecodeOptions() {
            return this.imageDecodeOptions;
        }

        /* renamed from: getLoadThumbnailOnly$options_release, reason: from getter */
        public final boolean getLoadThumbnailOnly() {
            return this.loadThumbnailOnly;
        }

        /* renamed from: getLocalThumbnailPreviewsEnabled$options_release, reason: from getter */
        public final boolean getLocalThumbnailPreviewsEnabled() {
            return this.localThumbnailPreviewsEnabled;
        }

        /* renamed from: getPostprocessor$options_release, reason: from getter */
        public final Postprocessor getPostprocessor() {
            return this.postprocessor;
        }

        /* renamed from: getProgressiveDecodingEnabled$options_release, reason: from getter */
        public final Boolean getProgressiveDecodingEnabled() {
            return this.progressiveDecodingEnabled;
        }

        /* renamed from: getResizeOptions$options_release, reason: from getter */
        public final ResizeOptions getResizeOptions() {
            return this.resizeOptions;
        }

        /* renamed from: getRotationOptions$options_release, reason: from getter */
        public final RotationOptions getRotationOptions() {
            return this.rotationOptions;
        }

        /* renamed from: getRoundingOptions$options_release, reason: from getter */
        public final RoundingOptions getRoundingOptions() {
            return this.roundingOptions;
        }

        public final void setActualFocusPoint$options_release(PointF pointF) {
            this.actualFocusPoint = pointF;
        }

        public final void setActualImageScaleType$options_release(ScalingUtils.ScaleType scaleType) {
            Intrinsics.checkNotNullParameter(scaleType, "<set-?>");
            this.actualImageScaleType = scaleType;
        }

        public final void setBitmapConfig$options_release(Bitmap.Config config) {
            this.bitmapConfig = config;
        }

        public final void setBorderOptions$options_release(BorderOptions borderOptions) {
            this.borderOptions = borderOptions;
        }

        public final void setImageDecodeOptions$options_release(ImageDecodeOptions imageDecodeOptions) {
            this.imageDecodeOptions = imageDecodeOptions;
        }

        public final void setLoadThumbnailOnly$options_release(boolean z) {
            this.loadThumbnailOnly = z;
        }

        public final void setLocalThumbnailPreviewsEnabled$options_release(boolean z) {
            this.localThumbnailPreviewsEnabled = z;
        }

        public final void setPostprocessor$options_release(Postprocessor postprocessor) {
            this.postprocessor = postprocessor;
        }

        public final void setProgressiveDecodingEnabled$options_release(Boolean bool) {
            this.progressiveDecodingEnabled = bool;
        }

        public final void setResizeOptions$options_release(ResizeOptions resizeOptions) {
            this.resizeOptions = resizeOptions;
        }

        public final void setRotationOptions$options_release(RotationOptions rotationOptions) {
            this.rotationOptions = rotationOptions;
        }

        public final void setRoundingOptions$options_release(RoundingOptions roundingOptions) {
            this.roundingOptions = roundingOptions;
        }

        public Builder() {
            ScalingUtils.ScaleType CENTER_CROP = ScalingUtils.ScaleType.CENTER_CROP;
            Intrinsics.checkNotNullExpressionValue(CENTER_CROP, "CENTER_CROP");
            this.actualImageScaleType = CENTER_CROP;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(ImageOptions defaultOptions) {
            super(defaultOptions);
            Intrinsics.checkNotNullParameter(defaultOptions, "defaultOptions");
            ScalingUtils.ScaleType CENTER_CROP = ScalingUtils.ScaleType.CENTER_CROP;
            Intrinsics.checkNotNullExpressionValue(CENTER_CROP, "CENTER_CROP");
            this.actualImageScaleType = CENTER_CROP;
            this.resizeOptions = defaultOptions.getResizeOptions();
            this.rotationOptions = defaultOptions.getRotationOptions();
            this.postprocessor = defaultOptions.getPostprocessor();
            this.imageDecodeOptions = defaultOptions.getImageDecodeOptions();
            this.roundingOptions = defaultOptions.getRoundingOptions();
            this.borderOptions = defaultOptions.getBorderOptions();
            this.actualImageScaleType = defaultOptions.getActualImageScaleType();
            this.actualFocusPoint = defaultOptions.getActualImageFocusPoint();
            this.localThumbnailPreviewsEnabled = defaultOptions.getMLocalThumbnailPreviewsEnabled();
            this.loadThumbnailOnly = defaultOptions.getLoadThumbnailOnly();
            this.bitmapConfig = defaultOptions.getBitmapConfig();
            this.progressiveDecodingEnabled = defaultOptions.getIsProgressiveDecodingEnabled();
        }

        @Override // com.facebook.fresco.vito.options.EncodedImageOptions.Builder
        public DecodedImageOptions build() {
            return new DecodedImageOptions(this);
        }

        private final T modify(Function1<? super Builder<T>, Unit> block) {
            block.invoke(this);
            return (T) getThis();
        }

        public final T resize(ResizeOptions resizeOptions) {
            this.resizeOptions = resizeOptions;
            return (T) getThis();
        }

        public final T rotate(RotationOptions rotationOptions) {
            this.rotationOptions = rotationOptions;
            return (T) getThis();
        }

        public final T postprocess(Postprocessor postprocessor) {
            this.postprocessor = postprocessor;
            return (T) getThis();
        }

        public final T imageDecodeOptions(ImageDecodeOptions imageDecodeOptions) {
            this.imageDecodeOptions = imageDecodeOptions;
            return (T) getThis();
        }

        public final T round(RoundingOptions roundingOptions) {
            this.roundingOptions = roundingOptions;
            return (T) getThis();
        }

        public final T borders(BorderOptions borderOptions) {
            this.borderOptions = borderOptions;
            return (T) getThis();
        }

        public final T scale(ScalingUtils.ScaleType actualImageScaleType) {
            if (actualImageScaleType == null) {
                actualImageScaleType = ImageOptions.INSTANCE.defaults().getActualImageScaleType();
            }
            this.actualImageScaleType = actualImageScaleType;
            return (T) getThis();
        }

        public final T focusPoint(PointF focusPoint) {
            this.actualFocusPoint = focusPoint;
            return (T) getThis();
        }

        public final T localThumbnailPreviewsEnabled(boolean localThumbnailPreviewsEnabled) {
            this.localThumbnailPreviewsEnabled = localThumbnailPreviewsEnabled;
            return (T) getThis();
        }

        public final T loadThumbnailOnly(boolean loadThumbnailOnly) {
            this.loadThumbnailOnly = loadThumbnailOnly;
            return (T) getThis();
        }

        public final T bitmapConfig(Bitmap.Config bitmapConfig) {
            this.bitmapConfig = bitmapConfig;
            return (T) getThis();
        }

        public final T progressiveRendering(Boolean progressiveDecodingEnabled) {
            this.progressiveDecodingEnabled = progressiveDecodingEnabled;
            return (T) getThis();
        }
    }
}

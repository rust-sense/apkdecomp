package com.facebook.fresco.vito.options;

import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import androidx.core.app.NotificationCompat;
import com.facebook.common.internal.Objects;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.fresco.vito.options.DecodedImageOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageOptions.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 R2\u00020\u0001:\u0002QRB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010C\u001a\u00020\u00062\b\u0010D\u001a\u0004\u0018\u00010EH\u0096\u0002J\u000e\u0010F\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u0000J\u0006\u0010H\u001a\u00020\u0003J\b\u0010I\u001a\u00020\u0015H\u0016J\u0006\u0010J\u001a\u00020\u0006J\u0006\u0010K\u001a\u00020\u0006J\u0006\u0010L\u001a\u00020\u0006J\b\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u00020PH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00158GX\u0087\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u00158GX\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010$\u001a\u0004\u0018\u00010%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0013R\u0011\u0010*\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b+\u0010#R\u0011\u0010,\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0013R\u0013\u0010-\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0016\u0010/\u001a\u00020\u00158GX\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010#R\u0011\u00101\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0013R\u001a\u00103\u001a\u0004\u0018\u00010\u00158GX\u0087\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b4\u0010\u0017R\u0013\u00105\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001cR\u0013\u00107\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b8\u0010 R\u0016\u00109\u001a\u00020\u00158GX\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010#R\u0013\u0010;\u001a\u0004\u0018\u00010%¢\u0006\b\n\u0000\u001a\u0004\b<\u0010'R\u0013\u0010=\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u001cR\u0016\u0010?\u001a\u00020\u00158GX\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010#R\u0013\u0010A\u001a\u0004\u0018\u00010%¢\u0006\b\n\u0000\u001a\u0004\bB\u0010'¨\u0006S"}, d2 = {"Lcom/facebook/fresco/vito/options/ImageOptions;", "Lcom/facebook/fresco/vito/options/DecodedImageOptions;", "builder", "Lcom/facebook/fresco/vito/options/ImageOptions$Builder;", "(Lcom/facebook/fresco/vito/options/ImageOptions$Builder;)V", "_autoPlay", "", "_autoStop", "_resizeToViewport", "actualImageColorFilter", "Landroid/graphics/ColorFilter;", "getActualImageColorFilter", "()Landroid/graphics/ColorFilter;", "customDrawableFactory", "Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;", "getCustomDrawableFactory", "()Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;", "errorApplyRoundingOptions", "getErrorApplyRoundingOptions", "()Z", "errorColor", "", "getErrorColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "errorDrawable", "Landroid/graphics/drawable/Drawable;", "getErrorDrawable", "()Landroid/graphics/drawable/Drawable;", "errorFocusPoint", "Landroid/graphics/PointF;", "getErrorFocusPoint", "()Landroid/graphics/PointF;", "errorRes", "getErrorRes", "()I", "errorScaleType", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "getErrorScaleType", "()Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "experimentalDynamicSize", "getExperimentalDynamicSize", "fadeDurationMs", "getFadeDurationMs", "isPerfMediaRemountInstrumentationFix", "overlayDrawable", "getOverlayDrawable", "overlayRes", "getOverlayRes", "placeholderApplyRoundingOptions", "getPlaceholderApplyRoundingOptions", "placeholderColor", "getPlaceholderColor", "placeholderDrawable", "getPlaceholderDrawable", "placeholderFocusPoint", "getPlaceholderFocusPoint", "placeholderRes", "getPlaceholderRes", "placeholderScaleType", "getPlaceholderScaleType", "progressDrawable", "getProgressDrawable", "progressRes", "getProgressRes", "progressScaleType", "getProgressScaleType", "equals", "otherObject", "", "equalsForActualImage", "other", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_EXTEND, "hashCode", "shouldAutoPlay", "shouldAutoStop", "shouldResizeToViewport", "toString", "", "toStringHelper", "Lcom/facebook/common/internal/Objects$ToStringHelper;", "Builder", "Companion", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageOptions extends DecodedImageOptions {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static ImageOptions defaultImageOptions = ((Builder) new Builder().placeholderScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).progressScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).errorScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).priority(Priority.HIGH)).build();
    private final boolean _autoPlay;
    private final boolean _autoStop;
    private final boolean _resizeToViewport;
    private final ColorFilter actualImageColorFilter;
    private final ImageOptionsDrawableFactory customDrawableFactory;
    private final boolean errorApplyRoundingOptions;
    private final Integer errorColor;
    private final Drawable errorDrawable;
    private final PointF errorFocusPoint;
    private final int errorRes;
    private final ScalingUtils.ScaleType errorScaleType;
    private final boolean experimentalDynamicSize;
    private final int fadeDurationMs;
    private final boolean isPerfMediaRemountInstrumentationFix;
    private final Drawable overlayDrawable;
    private final int overlayRes;
    private final boolean placeholderApplyRoundingOptions;
    private final Integer placeholderColor;
    private final Drawable placeholderDrawable;
    private final PointF placeholderFocusPoint;
    private final int placeholderRes;
    private final ScalingUtils.ScaleType placeholderScaleType;
    private final Drawable progressDrawable;
    private final int progressRes;
    private final ScalingUtils.ScaleType progressScaleType;

    @JvmStatic
    public static final Builder create() {
        return INSTANCE.create();
    }

    @JvmStatic
    public static final ImageOptions defaults() {
        return INSTANCE.defaults();
    }

    @JvmStatic
    public static final Builder extend(ImageOptions imageOptions) {
        return INSTANCE.extend(imageOptions);
    }

    @JvmStatic
    public static final void setDefaults(ImageOptions imageOptions) {
        INSTANCE.setDefaults(imageOptions);
    }

    public final ColorFilter getActualImageColorFilter() {
        return this.actualImageColorFilter;
    }

    public final ImageOptionsDrawableFactory getCustomDrawableFactory() {
        return this.customDrawableFactory;
    }

    public final boolean getErrorApplyRoundingOptions() {
        return this.errorApplyRoundingOptions;
    }

    public final Integer getErrorColor() {
        return this.errorColor;
    }

    public final Drawable getErrorDrawable() {
        return this.errorDrawable;
    }

    public final PointF getErrorFocusPoint() {
        return this.errorFocusPoint;
    }

    public final int getErrorRes() {
        return this.errorRes;
    }

    public final ScalingUtils.ScaleType getErrorScaleType() {
        return this.errorScaleType;
    }

    public final boolean getExperimentalDynamicSize() {
        return this.experimentalDynamicSize;
    }

    public final int getFadeDurationMs() {
        return this.fadeDurationMs;
    }

    public final Drawable getOverlayDrawable() {
        return this.overlayDrawable;
    }

    public final int getOverlayRes() {
        return this.overlayRes;
    }

    public final boolean getPlaceholderApplyRoundingOptions() {
        return this.placeholderApplyRoundingOptions;
    }

    public final Integer getPlaceholderColor() {
        return this.placeholderColor;
    }

    public final Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public final PointF getPlaceholderFocusPoint() {
        return this.placeholderFocusPoint;
    }

    public final int getPlaceholderRes() {
        return this.placeholderRes;
    }

    public final ScalingUtils.ScaleType getPlaceholderScaleType() {
        return this.placeholderScaleType;
    }

    public final Drawable getProgressDrawable() {
        return this.progressDrawable;
    }

    public final int getProgressRes() {
        return this.progressRes;
    }

    public final ScalingUtils.ScaleType getProgressScaleType() {
        return this.progressScaleType;
    }

    /* renamed from: isPerfMediaRemountInstrumentationFix, reason: from getter */
    public final boolean getIsPerfMediaRemountInstrumentationFix() {
        return this.isPerfMediaRemountInstrumentationFix;
    }

    /* renamed from: shouldAutoPlay, reason: from getter */
    public final boolean get_autoPlay() {
        return this._autoPlay;
    }

    /* renamed from: shouldAutoStop, reason: from getter */
    public final boolean get_autoStop() {
        return this._autoStop;
    }

    /* renamed from: shouldResizeToViewport, reason: from getter */
    public final boolean get_resizeToViewport() {
        return this._resizeToViewport;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageOptions(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.placeholderColor = builder.get_placeholderColor();
        this.placeholderRes = builder.get_placeholderRes();
        this.placeholderDrawable = builder.get_placeholderDrawable();
        this.placeholderScaleType = builder.get_placeholderScaleType();
        this.placeholderFocusPoint = builder.get_placeholderFocusPoint();
        this.placeholderApplyRoundingOptions = builder.get_placeholderApplyRoundingOptions();
        this.progressRes = builder.get_progressRes();
        this.progressDrawable = builder.get_progressDrawable();
        this.progressScaleType = builder.get_progressScaleType();
        this.errorColor = builder.get_errorColor();
        this.errorRes = builder.get_errorRes();
        this.errorScaleType = builder.get_errorScaleType();
        this.errorFocusPoint = builder.get_errorFocusPoint();
        this.errorDrawable = builder.get_errorDrawable();
        this.errorApplyRoundingOptions = builder.get_errorApplyRoundingOptions();
        this.actualImageColorFilter = builder.get_actualImageColorFilter();
        this.overlayRes = builder.get_overlayRes();
        this.overlayDrawable = builder.get_overlayDrawable();
        this._resizeToViewport = builder.get_resizeToViewport();
        this.fadeDurationMs = builder.get_fadeDurationMs();
        this._autoPlay = builder.get_autoPlay();
        this._autoStop = builder.get_autoStop();
        this.isPerfMediaRemountInstrumentationFix = builder.get_perfMediaRemountInstrumentationFix();
        this.customDrawableFactory = builder.get_customDrawableFactory();
        this.experimentalDynamicSize = builder.get_experimentalDynamicSize();
    }

    public final Builder extend() {
        return INSTANCE.extend(this);
    }

    public final boolean equalsForActualImage(ImageOptions other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (this == other) {
            return true;
        }
        if (this.isPerfMediaRemountInstrumentationFix) {
            if (this.overlayRes != other.overlayRes || !Objects.equal(this.overlayDrawable, other.overlayDrawable) || !Objects.equal(this.actualImageColorFilter, other.actualImageColorFilter) || this._resizeToViewport != other._resizeToViewport || this._autoPlay != other._autoPlay || this._autoStop != other._autoStop || !Objects.equal(this.customDrawableFactory, other.customDrawableFactory) || this.isPerfMediaRemountInstrumentationFix != other.isPerfMediaRemountInstrumentationFix) {
                return false;
            }
        } else if (this.overlayRes != other.overlayRes || !Objects.equal(this.overlayDrawable, other.overlayDrawable) || !Objects.equal(this.actualImageColorFilter, other.actualImageColorFilter) || this._resizeToViewport != other._resizeToViewport || !Objects.equal(this.customDrawableFactory, other.customDrawableFactory)) {
            return false;
        }
        return equalDecodedOptions(other);
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0192, code lost:
    
        if (r3.errorDrawable == r4.errorDrawable) goto L108;
     */
    @Override // com.facebook.fresco.vito.options.DecodedImageOptions, com.facebook.fresco.vito.options.EncodedImageOptions
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r4) {
        /*
            Method dump skipped, instructions count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.vito.options.ImageOptions.equals(java.lang.Object):boolean");
    }

    @Override // com.facebook.fresco.vito.options.DecodedImageOptions, com.facebook.fresco.vito.options.EncodedImageOptions
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Integer num = this.placeholderColor;
        int intValue = (((hashCode + (num != null ? num.intValue() : 0)) * 31) + this.placeholderRes) * 31;
        Drawable drawable = this.placeholderDrawable;
        int hashCode2 = (intValue + (drawable != null ? drawable.hashCode() : 0)) * 31;
        ScalingUtils.ScaleType scaleType = this.placeholderScaleType;
        int hashCode3 = (hashCode2 + (scaleType != null ? scaleType.hashCode() : 0)) * 31;
        PointF pointF = this.placeholderFocusPoint;
        int hashCode4 = (((hashCode3 + (pointF != null ? pointF.hashCode() : 0)) * 31) + (this.placeholderApplyRoundingOptions ? 1 : 0)) * 31;
        Integer num2 = this.errorColor;
        int intValue2 = (((hashCode4 + (num2 != null ? num2.intValue() : 0)) * 31) + this.errorRes) * 31;
        ScalingUtils.ScaleType scaleType2 = this.errorScaleType;
        int hashCode5 = (intValue2 + (scaleType2 != null ? scaleType2.hashCode() : 0)) * 31;
        PointF pointF2 = this.errorFocusPoint;
        int hashCode6 = (hashCode5 + (pointF2 != null ? pointF2.hashCode() : 0)) * 31;
        Drawable drawable2 = this.errorDrawable;
        int hashCode7 = (((((hashCode6 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31) + (this.errorApplyRoundingOptions ? 1 : 0)) * 31) + this.overlayRes) * 31;
        Drawable drawable3 = this.overlayDrawable;
        int hashCode8 = (hashCode7 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31;
        Drawable drawable4 = this.progressDrawable;
        int hashCode9 = (hashCode8 + (drawable4 != null ? drawable4.hashCode() : 0)) * 31;
        ScalingUtils.ScaleType scaleType3 = this.progressScaleType;
        int hashCode10 = (hashCode9 + (scaleType3 != null ? scaleType3.hashCode() : 0)) * 31;
        ColorFilter colorFilter = this.actualImageColorFilter;
        int hashCode11 = (((((((((((((hashCode10 + (colorFilter != null ? colorFilter.hashCode() : 0)) * 31) + (this._resizeToViewport ? 1 : 0)) * 31) + this.fadeDurationMs) * 31) + (this._autoPlay ? 1 : 0)) * 31) + (this._autoStop ? 1 : 0)) * 31) + (this.isPerfMediaRemountInstrumentationFix ? 1 : 0)) * 31) + this.progressRes) * 31;
        ImageOptionsDrawableFactory imageOptionsDrawableFactory = this.customDrawableFactory;
        return hashCode11 + (imageOptionsDrawableFactory != null ? imageOptionsDrawableFactory.hashCode() : 0);
    }

    @Override // com.facebook.fresco.vito.options.DecodedImageOptions, com.facebook.fresco.vito.options.EncodedImageOptions
    public String toString() {
        return "ImageOptions{" + toStringHelper() + '}';
    }

    @Override // com.facebook.fresco.vito.options.DecodedImageOptions, com.facebook.fresco.vito.options.EncodedImageOptions
    protected Objects.ToStringHelper toStringHelper() {
        Objects.ToStringHelper add = super.toStringHelper().add("placeholderColor", this.placeholderColor).add("placeholderRes", this.placeholderRes).add("placeholderDrawable", this.placeholderDrawable).add("placeholderScaleType", this.placeholderScaleType).add("placeholderFocusPoint", this.placeholderFocusPoint).add("placeholderApplyRoundingOptions", this.placeholderApplyRoundingOptions).add("progressRes", this.progressRes).add("progressDrawable", this.progressDrawable).add("progressScaleType", this.progressScaleType).add("errorColor", this.errorColor).add("errorRes", this.errorRes).add("errorScaleType", this.errorScaleType).add("errorFocusPoint", this.errorFocusPoint).add("errorDrawable", this.errorDrawable).add("errorApplyRoundingOptions", this.errorApplyRoundingOptions).add("actualImageColorFilter", this.actualImageColorFilter).add("overlayRes", this.overlayRes).add("overlayDrawable", this.overlayDrawable).add("resizeToViewport", this._resizeToViewport).add("autoPlay", this._autoPlay).add("autoStop", this._autoStop).add("mPerfMediaRemountInstrumentationFix", this.isPerfMediaRemountInstrumentationFix).add("fadeDurationMs", this.fadeDurationMs).add("customDrawableFactory", this.customDrawableFactory);
        Intrinsics.checkNotNullExpressionValue(add, "super.toStringHelper()\n …\", customDrawableFactory)");
        return add;
    }

    /* compiled from: ImageOptions.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\bC\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0010¢\u0006\u0002\u0010\u0002B\u000f\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010i\u001a\u00020\u00002\u0006\u0010i\u001a\u00020\rJ\u000e\u0010j\u001a\u00020\u00002\u0006\u0010j\u001a\u00020\rJ\b\u0010k\u001a\u00020\u0004H\u0016J\u0010\u0010l\u001a\u00020\u00002\b\u0010l\u001a\u0004\u0018\u00010\u0007J\u0010\u0010m\u001a\u00020\u00002\b\u0010n\u001a\u0004\u0018\u00010\u0016J\u000e\u0010o\u001a\u00020\u00002\u0006\u0010o\u001a\u00020\rJ\u0010\u0010p\u001a\u00020\u00002\b\b\u0001\u0010p\u001a\u00020\u001fJ\u0010\u0010q\u001a\u00020\u00002\b\u0010q\u001a\u0004\u0018\u00010&J\u0010\u0010r\u001a\u00020\u00002\b\u0010r\u001a\u0004\u0018\u00010,J\u0010\u0010s\u001a\u00020\u00002\b\b\u0001\u0010s\u001a\u00020\u001fJ\u0010\u0010t\u001a\u00020\u00002\b\u0010t\u001a\u0004\u0018\u000107J\u000e\u0010u\u001a\u00020\u00002\u0006\u0010v\u001a\u00020\rJ\u000e\u0010w\u001a\u00020\u00002\u0006\u0010x\u001a\u00020\u001fJ\"\u0010y\u001a\u00020\u00002\u0017\u0010z\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020|0{¢\u0006\u0002\b}H\u0082\bJ\u0010\u0010~\u001a\u00020\u00002\b\u0010\u007f\u001a\u0004\u0018\u00010&J\u0012\u0010\u0080\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0080\u0001\u001a\u00020\u001fJ\u0010\u0010\u0081\u0001\u001a\u00020\u00002\u0007\u0010\u0082\u0001\u001a\u00020\rJ\u0012\u0010\u0083\u0001\u001a\u00020\u00002\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010&J\u001d\u0010\u0083\u0001\u001a\u00020\u00002\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010&2\t\u0010\u0084\u0001\u001a\u0004\u0018\u000107J\u0010\u0010\u0085\u0001\u001a\u00020\u00002\u0007\u0010\u0085\u0001\u001a\u00020\rJ\u0012\u0010\u0086\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0086\u0001\u001a\u00020\u001fJ\u0012\u0010\u0087\u0001\u001a\u00020\u00002\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010,J\u0012\u0010\u0088\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0088\u0001\u001a\u00020\u001fJ\u001d\u0010\u0088\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0088\u0001\u001a\u00020\u001f2\t\u0010\u0084\u0001\u001a\u0004\u0018\u000107J\u0012\u0010\u0084\u0001\u001a\u00020\u00002\t\u0010\u0084\u0001\u001a\u0004\u0018\u000107J\u0012\u0010\u0089\u0001\u001a\u00020\u00002\t\u0010\u0089\u0001\u001a\u0004\u0018\u00010&J\u001d\u0010\u0089\u0001\u001a\u00020\u00002\t\u0010\u0089\u0001\u001a\u0004\u0018\u00010&2\t\u0010\u008a\u0001\u001a\u0004\u0018\u000107J\u0012\u0010\u008b\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u008b\u0001\u001a\u00020\u001fJ\u001d\u0010\u008b\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u008b\u0001\u001a\u00020\u001f2\t\u0010\u008a\u0001\u001a\u0004\u0018\u000107J\u0012\u0010\u008a\u0001\u001a\u00020\u00002\t\u0010\u008a\u0001\u001a\u0004\u0018\u000107J\u0010\u0010\u008c\u0001\u001a\u00020\u00002\u0007\u0010\u008c\u0001\u001a\u00020\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\"\u0010\u001e\u001a\u0004\u0018\u00010\u001f8\u0000@\u0000X\u0081\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001e\u00101\u001a\u00020\u001f8\u0000@\u0000X\u0081\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00106\u001a\u0004\u0018\u000107X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u000f\"\u0004\b>\u0010\u0011R\u001a\u0010?\u001a\u00020\u001fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u00103\"\u0004\bA\u00105R\u001c\u0010B\u001a\u0004\u0018\u00010&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010(\"\u0004\bD\u0010*R\u001e\u0010E\u001a\u00020\u001f8\u0000@\u0000X\u0081\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u00103\"\u0004\bG\u00105R\u001a\u0010H\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u000f\"\u0004\bJ\u0010\u0011R\u001a\u0010K\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u000f\"\u0004\bM\u0010\u0011R\"\u0010N\u001a\u0004\u0018\u00010\u001f8\u0000@\u0000X\u0081\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\bO\u0010!\"\u0004\bP\u0010#R\u001c\u0010Q\u001a\u0004\u0018\u00010&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010(\"\u0004\bS\u0010*R\u001c\u0010T\u001a\u0004\u0018\u00010,X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010.\"\u0004\bV\u00100R\u001e\u0010W\u001a\u00020\u001f8\u0000@\u0000X\u0081\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u00103\"\u0004\bY\u00105R\u001c\u0010Z\u001a\u0004\u0018\u000107X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u00109\"\u0004\b\\\u0010;R\u001c\u0010]\u001a\u0004\u0018\u00010&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010(\"\u0004\b_\u0010*R\u001e\u0010`\u001a\u00020\u001f8\u0000@\u0000X\u0081\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u00103\"\u0004\bb\u00105R\u001c\u0010c\u001a\u0004\u0018\u000107X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u00109\"\u0004\be\u0010;R\u001a\u0010f\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010\u000f\"\u0004\bh\u0010\u0011¨\u0006\u008d\u0001"}, d2 = {"Lcom/facebook/fresco/vito/options/ImageOptions$Builder;", "Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "()V", "defaultOptions", "Lcom/facebook/fresco/vito/options/ImageOptions;", "(Lcom/facebook/fresco/vito/options/ImageOptions;)V", "_actualImageColorFilter", "Landroid/graphics/ColorFilter;", "get_actualImageColorFilter$options_release", "()Landroid/graphics/ColorFilter;", "set_actualImageColorFilter$options_release", "(Landroid/graphics/ColorFilter;)V", "_autoPlay", "", "get_autoPlay$options_release", "()Z", "set_autoPlay$options_release", "(Z)V", "_autoStop", "get_autoStop$options_release", "set_autoStop$options_release", "_customDrawableFactory", "Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;", "get_customDrawableFactory$options_release", "()Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;", "set_customDrawableFactory$options_release", "(Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;)V", "_errorApplyRoundingOptions", "get_errorApplyRoundingOptions$options_release", "set_errorApplyRoundingOptions$options_release", "_errorColor", "", "get_errorColor$options_release", "()Ljava/lang/Integer;", "set_errorColor$options_release", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "_errorDrawable", "Landroid/graphics/drawable/Drawable;", "get_errorDrawable$options_release", "()Landroid/graphics/drawable/Drawable;", "set_errorDrawable$options_release", "(Landroid/graphics/drawable/Drawable;)V", "_errorFocusPoint", "Landroid/graphics/PointF;", "get_errorFocusPoint$options_release", "()Landroid/graphics/PointF;", "set_errorFocusPoint$options_release", "(Landroid/graphics/PointF;)V", "_errorRes", "get_errorRes$options_release", "()I", "set_errorRes$options_release", "(I)V", "_errorScaleType", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "get_errorScaleType$options_release", "()Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "set_errorScaleType$options_release", "(Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;)V", "_experimentalDynamicSize", "get_experimentalDynamicSize$options_release", "set_experimentalDynamicSize$options_release", "_fadeDurationMs", "get_fadeDurationMs$options_release", "set_fadeDurationMs$options_release", "_overlayDrawable", "get_overlayDrawable$options_release", "set_overlayDrawable$options_release", "_overlayRes", "get_overlayRes$options_release", "set_overlayRes$options_release", "_perfMediaRemountInstrumentationFix", "get_perfMediaRemountInstrumentationFix$options_release", "set_perfMediaRemountInstrumentationFix$options_release", "_placeholderApplyRoundingOptions", "get_placeholderApplyRoundingOptions$options_release", "set_placeholderApplyRoundingOptions$options_release", "_placeholderColor", "get_placeholderColor$options_release", "set_placeholderColor$options_release", "_placeholderDrawable", "get_placeholderDrawable$options_release", "set_placeholderDrawable$options_release", "_placeholderFocusPoint", "get_placeholderFocusPoint$options_release", "set_placeholderFocusPoint$options_release", "_placeholderRes", "get_placeholderRes$options_release", "set_placeholderRes$options_release", "_placeholderScaleType", "get_placeholderScaleType$options_release", "set_placeholderScaleType$options_release", "_progressDrawable", "get_progressDrawable$options_release", "set_progressDrawable$options_release", "_progressRes", "get_progressRes$options_release", "set_progressRes$options_release", "_progressScaleType", "get_progressScaleType$options_release", "set_progressScaleType$options_release", "_resizeToViewport", "get_resizeToViewport$options_release", "set_resizeToViewport$options_release", "autoPlay", "autoStop", OperatingSystem.JsonKeys.BUILD, "colorFilter", "customDrawableFactory", "drawableFactory", "errorApplyRoundingOptions", "errorColor", "errorDrawable", "errorFocusPoint", "errorRes", "errorScaleType", "experimentalDynamicSize", "dynamicSize", "fadeDurationMs", "fadeInDurationMs", "modify", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "overlay", "overlayDrawable", "overlayRes", "perfMediaRemountInstrumentationFix", "fix", ReactTextInputShadowNode.PROP_PLACEHOLDER, "placeholderScaleType", "placeholderApplyRoundingOptions", "placeholderColor", "placeholderFocusPoint", "placeholderRes", NotificationCompat.CATEGORY_PROGRESS, "progressScaleType", "progressRes", "resizeToViewport", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder extends DecodedImageOptions.Builder<Builder> {
        private ColorFilter _actualImageColorFilter;
        private boolean _autoPlay;
        private boolean _autoStop;
        private ImageOptionsDrawableFactory _customDrawableFactory;
        private boolean _errorApplyRoundingOptions;
        private Integer _errorColor;
        private Drawable _errorDrawable;
        private PointF _errorFocusPoint;
        private int _errorRes;
        private ScalingUtils.ScaleType _errorScaleType;
        private boolean _experimentalDynamicSize;
        private int _fadeDurationMs;
        private Drawable _overlayDrawable;
        private int _overlayRes;
        private boolean _perfMediaRemountInstrumentationFix;
        private boolean _placeholderApplyRoundingOptions;
        private Integer _placeholderColor;
        private Drawable _placeholderDrawable;
        private PointF _placeholderFocusPoint;
        private int _placeholderRes;
        private ScalingUtils.ScaleType _placeholderScaleType;
        private Drawable _progressDrawable;
        private int _progressRes;
        private ScalingUtils.ScaleType _progressScaleType;
        private boolean _resizeToViewport;

        /* renamed from: get_actualImageColorFilter$options_release, reason: from getter */
        public final ColorFilter get_actualImageColorFilter() {
            return this._actualImageColorFilter;
        }

        /* renamed from: get_autoPlay$options_release, reason: from getter */
        public final boolean get_autoPlay() {
            return this._autoPlay;
        }

        /* renamed from: get_autoStop$options_release, reason: from getter */
        public final boolean get_autoStop() {
            return this._autoStop;
        }

        /* renamed from: get_customDrawableFactory$options_release, reason: from getter */
        public final ImageOptionsDrawableFactory get_customDrawableFactory() {
            return this._customDrawableFactory;
        }

        /* renamed from: get_errorApplyRoundingOptions$options_release, reason: from getter */
        public final boolean get_errorApplyRoundingOptions() {
            return this._errorApplyRoundingOptions;
        }

        /* renamed from: get_errorColor$options_release, reason: from getter */
        public final Integer get_errorColor() {
            return this._errorColor;
        }

        /* renamed from: get_errorDrawable$options_release, reason: from getter */
        public final Drawable get_errorDrawable() {
            return this._errorDrawable;
        }

        /* renamed from: get_errorFocusPoint$options_release, reason: from getter */
        public final PointF get_errorFocusPoint() {
            return this._errorFocusPoint;
        }

        /* renamed from: get_errorRes$options_release, reason: from getter */
        public final int get_errorRes() {
            return this._errorRes;
        }

        /* renamed from: get_errorScaleType$options_release, reason: from getter */
        public final ScalingUtils.ScaleType get_errorScaleType() {
            return this._errorScaleType;
        }

        /* renamed from: get_experimentalDynamicSize$options_release, reason: from getter */
        public final boolean get_experimentalDynamicSize() {
            return this._experimentalDynamicSize;
        }

        /* renamed from: get_fadeDurationMs$options_release, reason: from getter */
        public final int get_fadeDurationMs() {
            return this._fadeDurationMs;
        }

        /* renamed from: get_overlayDrawable$options_release, reason: from getter */
        public final Drawable get_overlayDrawable() {
            return this._overlayDrawable;
        }

        /* renamed from: get_overlayRes$options_release, reason: from getter */
        public final int get_overlayRes() {
            return this._overlayRes;
        }

        /* renamed from: get_perfMediaRemountInstrumentationFix$options_release, reason: from getter */
        public final boolean get_perfMediaRemountInstrumentationFix() {
            return this._perfMediaRemountInstrumentationFix;
        }

        /* renamed from: get_placeholderApplyRoundingOptions$options_release, reason: from getter */
        public final boolean get_placeholderApplyRoundingOptions() {
            return this._placeholderApplyRoundingOptions;
        }

        /* renamed from: get_placeholderColor$options_release, reason: from getter */
        public final Integer get_placeholderColor() {
            return this._placeholderColor;
        }

        /* renamed from: get_placeholderDrawable$options_release, reason: from getter */
        public final Drawable get_placeholderDrawable() {
            return this._placeholderDrawable;
        }

        /* renamed from: get_placeholderFocusPoint$options_release, reason: from getter */
        public final PointF get_placeholderFocusPoint() {
            return this._placeholderFocusPoint;
        }

        /* renamed from: get_placeholderRes$options_release, reason: from getter */
        public final int get_placeholderRes() {
            return this._placeholderRes;
        }

        /* renamed from: get_placeholderScaleType$options_release, reason: from getter */
        public final ScalingUtils.ScaleType get_placeholderScaleType() {
            return this._placeholderScaleType;
        }

        /* renamed from: get_progressDrawable$options_release, reason: from getter */
        public final Drawable get_progressDrawable() {
            return this._progressDrawable;
        }

        /* renamed from: get_progressRes$options_release, reason: from getter */
        public final int get_progressRes() {
            return this._progressRes;
        }

        /* renamed from: get_progressScaleType$options_release, reason: from getter */
        public final ScalingUtils.ScaleType get_progressScaleType() {
            return this._progressScaleType;
        }

        /* renamed from: get_resizeToViewport$options_release, reason: from getter */
        public final boolean get_resizeToViewport() {
            return this._resizeToViewport;
        }

        public final void set_actualImageColorFilter$options_release(ColorFilter colorFilter) {
            this._actualImageColorFilter = colorFilter;
        }

        public final void set_autoPlay$options_release(boolean z) {
            this._autoPlay = z;
        }

        public final void set_autoStop$options_release(boolean z) {
            this._autoStop = z;
        }

        public final void set_customDrawableFactory$options_release(ImageOptionsDrawableFactory imageOptionsDrawableFactory) {
            this._customDrawableFactory = imageOptionsDrawableFactory;
        }

        public final void set_errorApplyRoundingOptions$options_release(boolean z) {
            this._errorApplyRoundingOptions = z;
        }

        public final void set_errorColor$options_release(Integer num) {
            this._errorColor = num;
        }

        public final void set_errorDrawable$options_release(Drawable drawable) {
            this._errorDrawable = drawable;
        }

        public final void set_errorFocusPoint$options_release(PointF pointF) {
            this._errorFocusPoint = pointF;
        }

        public final void set_errorRes$options_release(int i) {
            this._errorRes = i;
        }

        public final void set_errorScaleType$options_release(ScalingUtils.ScaleType scaleType) {
            this._errorScaleType = scaleType;
        }

        public final void set_experimentalDynamicSize$options_release(boolean z) {
            this._experimentalDynamicSize = z;
        }

        public final void set_fadeDurationMs$options_release(int i) {
            this._fadeDurationMs = i;
        }

        public final void set_overlayDrawable$options_release(Drawable drawable) {
            this._overlayDrawable = drawable;
        }

        public final void set_overlayRes$options_release(int i) {
            this._overlayRes = i;
        }

        public final void set_perfMediaRemountInstrumentationFix$options_release(boolean z) {
            this._perfMediaRemountInstrumentationFix = z;
        }

        public final void set_placeholderApplyRoundingOptions$options_release(boolean z) {
            this._placeholderApplyRoundingOptions = z;
        }

        public final void set_placeholderColor$options_release(Integer num) {
            this._placeholderColor = num;
        }

        public final void set_placeholderDrawable$options_release(Drawable drawable) {
            this._placeholderDrawable = drawable;
        }

        public final void set_placeholderFocusPoint$options_release(PointF pointF) {
            this._placeholderFocusPoint = pointF;
        }

        public final void set_placeholderRes$options_release(int i) {
            this._placeholderRes = i;
        }

        public final void set_placeholderScaleType$options_release(ScalingUtils.ScaleType scaleType) {
            this._placeholderScaleType = scaleType;
        }

        public final void set_progressDrawable$options_release(Drawable drawable) {
            this._progressDrawable = drawable;
        }

        public final void set_progressRes$options_release(int i) {
            this._progressRes = i;
        }

        public final void set_progressScaleType$options_release(ScalingUtils.ScaleType scaleType) {
            this._progressScaleType = scaleType;
        }

        public final void set_resizeToViewport$options_release(boolean z) {
            this._resizeToViewport = z;
        }

        public Builder() {
            this._autoStop = true;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(ImageOptions defaultOptions) {
            super(defaultOptions);
            Intrinsics.checkNotNullParameter(defaultOptions, "defaultOptions");
            this._autoStop = true;
            this._placeholderColor = defaultOptions.getPlaceholderColor();
            this._placeholderRes = defaultOptions.getPlaceholderRes();
            this._placeholderDrawable = defaultOptions.getPlaceholderDrawable();
            this._placeholderScaleType = defaultOptions.getPlaceholderScaleType();
            this._placeholderFocusPoint = defaultOptions.getPlaceholderFocusPoint();
            this._placeholderApplyRoundingOptions = defaultOptions.getPlaceholderApplyRoundingOptions();
            this._progressRes = defaultOptions.getProgressRes();
            this._progressDrawable = defaultOptions.getProgressDrawable();
            this._progressScaleType = defaultOptions.getProgressScaleType();
            this._errorColor = defaultOptions.getErrorColor();
            this._errorRes = defaultOptions.getErrorRes();
            this._errorScaleType = defaultOptions.getErrorScaleType();
            this._errorFocusPoint = defaultOptions.getErrorFocusPoint();
            this._errorDrawable = defaultOptions.getErrorDrawable();
            this._errorApplyRoundingOptions = defaultOptions.getErrorApplyRoundingOptions();
            this._actualImageColorFilter = defaultOptions.getActualImageColorFilter();
            this._overlayRes = defaultOptions.getOverlayRes();
            this._overlayDrawable = defaultOptions.getOverlayDrawable();
            this._resizeToViewport = defaultOptions.get_resizeToViewport();
            this._autoPlay = defaultOptions.get_autoPlay();
            this._autoStop = defaultOptions.get_autoStop();
            this._fadeDurationMs = defaultOptions.getFadeDurationMs();
            this._customDrawableFactory = defaultOptions.getCustomDrawableFactory();
            this._experimentalDynamicSize = defaultOptions.getExperimentalDynamicSize();
        }

        @Override // com.facebook.fresco.vito.options.DecodedImageOptions.Builder, com.facebook.fresco.vito.options.EncodedImageOptions.Builder
        public ImageOptions build() {
            return new ImageOptions(this);
        }

        private final Builder modify(Function1<? super Builder, Unit> block) {
            block.invoke(this);
            return this;
        }

        public final Builder placeholder(Drawable placeholder) {
            this._placeholderDrawable = placeholder;
            this._placeholderColor = null;
            this._placeholderRes = 0;
            return this;
        }

        public final Builder placeholder(Drawable placeholder, ScalingUtils.ScaleType placeholderScaleType) {
            this._placeholderDrawable = placeholder;
            this._placeholderScaleType = placeholderScaleType;
            this._placeholderColor = null;
            this._placeholderRes = 0;
            return this;
        }

        public final Builder placeholderColor(int placeholderColor) {
            this._placeholderColor = Integer.valueOf(placeholderColor);
            this._placeholderRes = 0;
            this._placeholderDrawable = null;
            return this;
        }

        public final Builder placeholderRes(int placeholderRes) {
            this._placeholderRes = placeholderRes;
            this._placeholderColor = null;
            this._placeholderDrawable = null;
            return this;
        }

        public final Builder placeholderRes(int placeholderRes, ScalingUtils.ScaleType placeholderScaleType) {
            this._placeholderRes = placeholderRes;
            this._placeholderScaleType = placeholderScaleType;
            this._placeholderColor = null;
            this._placeholderDrawable = null;
            return this;
        }

        public final Builder placeholderScaleType(ScalingUtils.ScaleType placeholderScaleType) {
            this._placeholderScaleType = placeholderScaleType;
            return this;
        }

        public final Builder placeholderFocusPoint(PointF placeholderFocusPoint) {
            this._placeholderFocusPoint = placeholderFocusPoint;
            return this;
        }

        public final Builder placeholderApplyRoundingOptions(boolean placeholderApplyRoundingOptions) {
            this._placeholderApplyRoundingOptions = placeholderApplyRoundingOptions;
            return this;
        }

        public final Builder errorColor(int errorColor) {
            this._errorColor = Integer.valueOf(errorColor);
            this._errorRes = 0;
            this._errorDrawable = null;
            return this;
        }

        public final Builder errorRes(int errorRes) {
            this._errorColor = null;
            this._errorRes = errorRes;
            this._errorDrawable = null;
            return this;
        }

        public final Builder errorScaleType(ScalingUtils.ScaleType errorScaleType) {
            this._errorScaleType = errorScaleType;
            return this;
        }

        public final Builder errorFocusPoint(PointF errorFocusPoint) {
            this._errorFocusPoint = errorFocusPoint;
            return this;
        }

        public final Builder errorDrawable(Drawable errorDrawable) {
            this._errorColor = null;
            this._errorRes = 0;
            this._errorDrawable = errorDrawable;
            return this;
        }

        public final Builder errorApplyRoundingOptions(boolean errorApplyRoundingOptions) {
            this._errorApplyRoundingOptions = errorApplyRoundingOptions;
            return this;
        }

        public final Builder progress(Drawable progress) {
            this._progressDrawable = progress;
            return this;
        }

        public final Builder progress(Drawable progress, ScalingUtils.ScaleType progressScaleType) {
            this._progressDrawable = progress;
            this._progressScaleType = progressScaleType;
            return this;
        }

        public final Builder progressRes(int progressRes) {
            this._progressRes = progressRes;
            return this;
        }

        public final Builder progressRes(int progressRes, ScalingUtils.ScaleType progressScaleType) {
            this._progressRes = progressRes;
            this._progressScaleType = progressScaleType;
            return this;
        }

        public final Builder progressScaleType(ScalingUtils.ScaleType progressScaleType) {
            this._progressScaleType = progressScaleType;
            return this;
        }

        public final Builder overlayRes(int overlayRes) {
            this._overlayRes = overlayRes;
            this._overlayDrawable = null;
            return this;
        }

        public final Builder overlay(Drawable overlayDrawable) {
            this._overlayDrawable = overlayDrawable;
            this._overlayRes = 0;
            return this;
        }

        public final Builder colorFilter(ColorFilter colorFilter) {
            this._actualImageColorFilter = colorFilter;
            return this;
        }

        public final Builder autoPlay(boolean autoPlay) {
            this._autoPlay = autoPlay;
            return this;
        }

        public final Builder autoStop(boolean autoStop) {
            this._autoStop = autoStop;
            return this;
        }

        public final Builder perfMediaRemountInstrumentationFix(boolean fix) {
            this._perfMediaRemountInstrumentationFix = fix;
            return this;
        }

        public final Builder resizeToViewport(boolean resizeToViewport) {
            this._resizeToViewport = resizeToViewport;
            return this;
        }

        public final Builder fadeDurationMs(int fadeInDurationMs) {
            this._fadeDurationMs = fadeInDurationMs;
            return this;
        }

        public final Builder customDrawableFactory(ImageOptionsDrawableFactory drawableFactory) {
            this._customDrawableFactory = drawableFactory;
            return this;
        }

        public final Builder experimentalDynamicSize(boolean dynamicSize) {
            this._experimentalDynamicSize = dynamicSize;
            return this;
        }
    }

    /* compiled from: ImageOptions.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0004H\u0007J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/fresco/vito/options/ImageOptions$Companion;", "", "()V", "defaultImageOptions", "Lcom/facebook/fresco/vito/options/ImageOptions;", "create", "Lcom/facebook/fresco/vito/options/ImageOptions$Builder;", "defaults", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_EXTEND, "imageOptions", "setDefaults", "", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ImageOptions defaults() {
            return ImageOptions.defaultImageOptions;
        }

        @JvmStatic
        public final void setDefaults(ImageOptions imageOptions) {
            Intrinsics.checkNotNullParameter(imageOptions, "imageOptions");
            ImageOptions.defaultImageOptions = imageOptions;
        }

        @JvmStatic
        public final Builder extend(ImageOptions imageOptions) {
            Intrinsics.checkNotNullParameter(imageOptions, "imageOptions");
            return new Builder(imageOptions);
        }

        @JvmStatic
        public final Builder create() {
            return extend(defaults());
        }
    }
}

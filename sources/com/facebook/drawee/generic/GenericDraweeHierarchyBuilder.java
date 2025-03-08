package com.facebook.drawee.generic;

import android.R;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.ScalingUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class GenericDraweeHierarchyBuilder {
    public static final int DEFAULT_FADE_DURATION = 300;

    @Nullable
    private ColorFilter mActualImageColorFilter;

    @Nullable
    private PointF mActualImageFocusPoint;

    @Nullable
    private Matrix mActualImageMatrix;

    @Nullable
    private ScalingUtils.ScaleType mActualImageScaleType;

    @Nullable
    private Drawable mBackground;
    private float mDesiredAspectRatio;
    private int mFadeDuration;

    @Nullable
    private Drawable mFailureImage;

    @Nullable
    private ScalingUtils.ScaleType mFailureImageScaleType;

    @Nullable
    private List<Drawable> mOverlays;

    @Nullable
    private Drawable mPlaceholderImage;

    @Nullable
    private ScalingUtils.ScaleType mPlaceholderImageScaleType;

    @Nullable
    private Drawable mPressedStateOverlay;

    @Nullable
    private Drawable mProgressBarImage;

    @Nullable
    private ScalingUtils.ScaleType mProgressBarImageScaleType;
    private Resources mResources;

    @Nullable
    private Drawable mRetryImage;

    @Nullable
    private ScalingUtils.ScaleType mRetryImageScaleType;

    @Nullable
    private RoundingParams mRoundingParams;
    public static final ScalingUtils.ScaleType DEFAULT_SCALE_TYPE = ScalingUtils.ScaleType.CENTER_INSIDE;
    public static final ScalingUtils.ScaleType DEFAULT_ACTUAL_IMAGE_SCALE_TYPE = ScalingUtils.ScaleType.CENTER_CROP;

    private void init() {
        this.mFadeDuration = 300;
        this.mDesiredAspectRatio = 0.0f;
        this.mPlaceholderImage = null;
        ScalingUtils.ScaleType scaleType = DEFAULT_SCALE_TYPE;
        this.mPlaceholderImageScaleType = scaleType;
        this.mRetryImage = null;
        this.mRetryImageScaleType = scaleType;
        this.mFailureImage = null;
        this.mFailureImageScaleType = scaleType;
        this.mProgressBarImage = null;
        this.mProgressBarImageScaleType = scaleType;
        this.mActualImageScaleType = DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
        this.mActualImageMatrix = null;
        this.mActualImageFocusPoint = null;
        this.mActualImageColorFilter = null;
        this.mBackground = null;
        this.mOverlays = null;
        this.mPressedStateOverlay = null;
        this.mRoundingParams = null;
    }

    @Nullable
    public ColorFilter getActualImageColorFilter() {
        return this.mActualImageColorFilter;
    }

    @Nullable
    public PointF getActualImageFocusPoint() {
        return this.mActualImageFocusPoint;
    }

    @Nullable
    public ScalingUtils.ScaleType getActualImageScaleType() {
        return this.mActualImageScaleType;
    }

    @Nullable
    public Drawable getBackground() {
        return this.mBackground;
    }

    public float getDesiredAspectRatio() {
        return this.mDesiredAspectRatio;
    }

    public int getFadeDuration() {
        return this.mFadeDuration;
    }

    @Nullable
    public Drawable getFailureImage() {
        return this.mFailureImage;
    }

    @Nullable
    public ScalingUtils.ScaleType getFailureImageScaleType() {
        return this.mFailureImageScaleType;
    }

    @Nullable
    public List<Drawable> getOverlays() {
        return this.mOverlays;
    }

    @Nullable
    public Drawable getPlaceholderImage() {
        return this.mPlaceholderImage;
    }

    @Nullable
    public ScalingUtils.ScaleType getPlaceholderImageScaleType() {
        return this.mPlaceholderImageScaleType;
    }

    @Nullable
    public Drawable getPressedStateOverlay() {
        return this.mPressedStateOverlay;
    }

    @Nullable
    public Drawable getProgressBarImage() {
        return this.mProgressBarImage;
    }

    @Nullable
    public ScalingUtils.ScaleType getProgressBarImageScaleType() {
        return this.mProgressBarImageScaleType;
    }

    public Resources getResources() {
        return this.mResources;
    }

    @Nullable
    public Drawable getRetryImage() {
        return this.mRetryImage;
    }

    @Nullable
    public ScalingUtils.ScaleType getRetryImageScaleType() {
        return this.mRetryImageScaleType;
    }

    @Nullable
    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }

    public GenericDraweeHierarchyBuilder setActualImageColorFilter(@Nullable ColorFilter colorFilter) {
        this.mActualImageColorFilter = colorFilter;
        return this;
    }

    public GenericDraweeHierarchyBuilder setActualImageFocusPoint(@Nullable PointF pointF) {
        this.mActualImageFocusPoint = pointF;
        return this;
    }

    public GenericDraweeHierarchyBuilder setActualImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mActualImageScaleType = scaleType;
        this.mActualImageMatrix = null;
        return this;
    }

    public GenericDraweeHierarchyBuilder setBackground(@Nullable Drawable drawable) {
        this.mBackground = drawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setDesiredAspectRatio(float f) {
        this.mDesiredAspectRatio = f;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFadeDuration(int i) {
        this.mFadeDuration = i;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(@Nullable Drawable drawable) {
        this.mFailureImage = drawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mFailureImage = drawable;
        this.mFailureImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mFailureImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setOverlays(@Nullable List<Drawable> list) {
        this.mOverlays = list;
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(@Nullable Drawable drawable) {
        this.mPlaceholderImage = drawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mPlaceholderImage = drawable;
        this.mPlaceholderImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mPlaceholderImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(@Nullable Drawable drawable) {
        this.mProgressBarImage = drawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mProgressBarImage = drawable;
        this.mProgressBarImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mProgressBarImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(@Nullable Drawable drawable) {
        this.mRetryImage = drawable;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(Drawable drawable, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mRetryImage = drawable;
        this.mRetryImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImageScaleType(@Nullable ScalingUtils.ScaleType scaleType) {
        this.mRetryImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRoundingParams(@Nullable RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        return this;
    }

    public GenericDraweeHierarchyBuilder(Resources resources) {
        this.mResources = resources;
        init();
    }

    public static GenericDraweeHierarchyBuilder newInstance(Resources resources) {
        return new GenericDraweeHierarchyBuilder(resources);
    }

    public GenericDraweeHierarchyBuilder reset() {
        init();
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(int i) {
        this.mPlaceholderImage = this.mResources.getDrawable(i);
        return this;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(int i, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mPlaceholderImage = this.mResources.getDrawable(i);
        this.mPlaceholderImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(int i) {
        this.mRetryImage = this.mResources.getDrawable(i);
        return this;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(int i, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mRetryImage = this.mResources.getDrawable(i);
        this.mRetryImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(int i) {
        this.mFailureImage = this.mResources.getDrawable(i);
        return this;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(int i, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mFailureImage = this.mResources.getDrawable(i);
        this.mFailureImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(int i) {
        this.mProgressBarImage = this.mResources.getDrawable(i);
        return this;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(int i, @Nullable ScalingUtils.ScaleType scaleType) {
        this.mProgressBarImage = this.mResources.getDrawable(i);
        this.mProgressBarImageScaleType = scaleType;
        return this;
    }

    public GenericDraweeHierarchyBuilder setOverlay(@Nullable Drawable drawable) {
        if (drawable == null) {
            this.mOverlays = null;
        } else {
            this.mOverlays = Arrays.asList(drawable);
        }
        return this;
    }

    public GenericDraweeHierarchyBuilder setPressedStateOverlay(@Nullable Drawable drawable) {
        if (drawable == null) {
            this.mPressedStateOverlay = null;
        } else {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{R.attr.state_pressed}, drawable);
            this.mPressedStateOverlay = stateListDrawable;
        }
        return this;
    }

    private void validate() {
        List<Drawable> list = this.mOverlays;
        if (list != null) {
            Iterator<Drawable> it = list.iterator();
            while (it.hasNext()) {
                Preconditions.checkNotNull(it.next());
            }
        }
    }

    public GenericDraweeHierarchy build() {
        validate();
        return new GenericDraweeHierarchy(this);
    }
}

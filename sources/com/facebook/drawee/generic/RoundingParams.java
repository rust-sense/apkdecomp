package com.facebook.drawee.generic;

import com.facebook.common.internal.Preconditions;
import java.util.Arrays;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class RoundingParams {
    private RoundingMethod mRoundingMethod = RoundingMethod.BITMAP_ONLY;
    private boolean mRoundAsCircle = false;

    @Nullable
    private float[] mCornersRadii = null;
    private int mOverlayColor = 0;
    private float mBorderWidth = 0.0f;
    private int mBorderColor = 0;
    private float mPadding = 0.0f;
    private boolean mScaleDownInsideBorders = false;
    private boolean mPaintFilterBitmap = false;
    private boolean mRepeatEdgePixels = false;

    public enum RoundingMethod {
        OVERLAY_COLOR,
        BITMAP_ONLY
    }

    private float[] getOrCreateRoundedCornersRadii() {
        if (this.mCornersRadii == null) {
            this.mCornersRadii = new float[8];
        }
        return this.mCornersRadii;
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    @Nullable
    public float[] getCornersRadii() {
        return this.mCornersRadii;
    }

    public int getOverlayColor() {
        return this.mOverlayColor;
    }

    public float getPadding() {
        return this.mPadding;
    }

    public boolean getPaintFilterBitmap() {
        return this.mPaintFilterBitmap;
    }

    public boolean getRepeatEdgePixels() {
        return this.mRepeatEdgePixels;
    }

    public boolean getRoundAsCircle() {
        return this.mRoundAsCircle;
    }

    public RoundingMethod getRoundingMethod() {
        return this.mRoundingMethod;
    }

    public boolean getScaleDownInsideBorders() {
        return this.mScaleDownInsideBorders;
    }

    public RoundingParams setBorderColor(int i) {
        this.mBorderColor = i;
        return this;
    }

    public RoundingParams setPaintFilterBitmap(boolean z) {
        this.mPaintFilterBitmap = z;
        return this;
    }

    public RoundingParams setRepeatEdgePixels(boolean z) {
        this.mRepeatEdgePixels = z;
        return this;
    }

    public RoundingParams setRoundAsCircle(boolean z) {
        this.mRoundAsCircle = z;
        return this;
    }

    public RoundingParams setRoundingMethod(RoundingMethod roundingMethod) {
        this.mRoundingMethod = roundingMethod;
        return this;
    }

    public RoundingParams setScaleDownInsideBorders(boolean z) {
        this.mScaleDownInsideBorders = z;
        return this;
    }

    public RoundingParams setCornersRadius(float f) {
        Arrays.fill(getOrCreateRoundedCornersRadii(), f);
        return this;
    }

    public RoundingParams setCornersRadii(float f, float f2, float f3, float f4) {
        float[] orCreateRoundedCornersRadii = getOrCreateRoundedCornersRadii();
        orCreateRoundedCornersRadii[1] = f;
        orCreateRoundedCornersRadii[0] = f;
        orCreateRoundedCornersRadii[3] = f2;
        orCreateRoundedCornersRadii[2] = f2;
        orCreateRoundedCornersRadii[5] = f3;
        orCreateRoundedCornersRadii[4] = f3;
        orCreateRoundedCornersRadii[7] = f4;
        orCreateRoundedCornersRadii[6] = f4;
        return this;
    }

    public RoundingParams setCornersRadii(float[] fArr) {
        Preconditions.checkNotNull(fArr);
        Preconditions.checkArgument(fArr.length == 8, "radii should have exactly 8 values");
        System.arraycopy(fArr, 0, getOrCreateRoundedCornersRadii(), 0, 8);
        return this;
    }

    public RoundingParams setOverlayColor(int i) {
        this.mOverlayColor = i;
        this.mRoundingMethod = RoundingMethod.OVERLAY_COLOR;
        return this;
    }

    public static RoundingParams asCircle() {
        return new RoundingParams().setRoundAsCircle(true);
    }

    public static RoundingParams fromCornersRadius(float f) {
        return new RoundingParams().setCornersRadius(f);
    }

    public static RoundingParams fromCornersRadii(float f, float f2, float f3, float f4) {
        return new RoundingParams().setCornersRadii(f, f2, f3, f4);
    }

    public static RoundingParams fromCornersRadii(float[] fArr) {
        return new RoundingParams().setCornersRadii(fArr);
    }

    public RoundingParams setBorderWidth(float f) {
        Preconditions.checkArgument(f >= 0.0f, "the border width cannot be < 0");
        this.mBorderWidth = f;
        return this;
    }

    public RoundingParams setBorder(int i, float f) {
        Preconditions.checkArgument(f >= 0.0f, "the border width cannot be < 0");
        this.mBorderWidth = f;
        this.mBorderColor = i;
        return this;
    }

    public RoundingParams setPadding(float f) {
        Preconditions.checkArgument(f >= 0.0f, "the padding cannot be < 0");
        this.mPadding = f;
        return this;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RoundingParams roundingParams = (RoundingParams) obj;
        if (this.mRoundAsCircle == roundingParams.mRoundAsCircle && this.mOverlayColor == roundingParams.mOverlayColor && Float.compare(roundingParams.mBorderWidth, this.mBorderWidth) == 0 && this.mBorderColor == roundingParams.mBorderColor && Float.compare(roundingParams.mPadding, this.mPadding) == 0 && this.mRoundingMethod == roundingParams.mRoundingMethod && this.mScaleDownInsideBorders == roundingParams.mScaleDownInsideBorders && this.mPaintFilterBitmap == roundingParams.mPaintFilterBitmap) {
            return Arrays.equals(this.mCornersRadii, roundingParams.mCornersRadii);
        }
        return false;
    }

    public int hashCode() {
        RoundingMethod roundingMethod = this.mRoundingMethod;
        int hashCode = (((roundingMethod != null ? roundingMethod.hashCode() : 0) * 31) + (this.mRoundAsCircle ? 1 : 0)) * 31;
        float[] fArr = this.mCornersRadii;
        int hashCode2 = (((hashCode + (fArr != null ? Arrays.hashCode(fArr) : 0)) * 31) + this.mOverlayColor) * 31;
        float f = this.mBorderWidth;
        int floatToIntBits = (((hashCode2 + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31) + this.mBorderColor) * 31;
        float f2 = this.mPadding;
        return ((((floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31) + (this.mScaleDownInsideBorders ? 1 : 0)) * 31) + (this.mPaintFilterBitmap ? 1 : 0);
    }
}

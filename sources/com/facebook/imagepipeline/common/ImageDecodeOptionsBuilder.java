package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImageDecodeOptionsBuilder<T extends ImageDecodeOptionsBuilder> {

    @Nullable
    private BitmapTransformation mBitmapTransformation;

    @Nullable
    private ColorSpace mColorSpace;

    @Nullable
    private ImageDecoder mCustomImageDecoder;
    private boolean mDecodeAllFrames;
    private boolean mDecodePreviewFrame;
    private boolean mExcludeBitmapConfigFromComparison;
    private boolean mForceStaticImage;
    private boolean mUseLastFrameForPreview;
    private int mMinDecodeIntervalMs = 100;
    private int mMaxDimensionPx = Integer.MAX_VALUE;
    private Bitmap.Config mBitmapConfig = Bitmap.Config.ARGB_8888;
    private Bitmap.Config mAnimatedBitmapConfig = Bitmap.Config.ARGB_8888;

    public Bitmap.Config getAnimatedBitmapConfig() {
        return this.mAnimatedBitmapConfig;
    }

    public Bitmap.Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    @Nullable
    public BitmapTransformation getBitmapTransformation() {
        return this.mBitmapTransformation;
    }

    @Nullable
    public ColorSpace getColorSpace() {
        return this.mColorSpace;
    }

    @Nullable
    public ImageDecoder getCustomImageDecoder() {
        return this.mCustomImageDecoder;
    }

    public boolean getDecodeAllFrames() {
        return this.mDecodeAllFrames;
    }

    public boolean getDecodePreviewFrame() {
        return this.mDecodePreviewFrame;
    }

    public boolean getExcludeBitmapConfigFromComparison() {
        return this.mExcludeBitmapConfigFromComparison;
    }

    public boolean getForceStaticImage() {
        return this.mForceStaticImage;
    }

    public int getMaxDimensionPx() {
        return this.mMaxDimensionPx;
    }

    public int getMinDecodeIntervalMs() {
        return this.mMinDecodeIntervalMs;
    }

    protected T getThis() {
        return this;
    }

    public boolean getUseLastFrameForPreview() {
        return this.mUseLastFrameForPreview;
    }

    public ImageDecodeOptionsBuilder setFrom(ImageDecodeOptions imageDecodeOptions) {
        this.mMinDecodeIntervalMs = imageDecodeOptions.minDecodeIntervalMs;
        this.mMaxDimensionPx = imageDecodeOptions.maxDimensionPx;
        this.mDecodePreviewFrame = imageDecodeOptions.decodePreviewFrame;
        this.mUseLastFrameForPreview = imageDecodeOptions.useLastFrameForPreview;
        this.mDecodeAllFrames = imageDecodeOptions.decodeAllFrames;
        this.mForceStaticImage = imageDecodeOptions.forceStaticImage;
        this.mBitmapConfig = imageDecodeOptions.bitmapConfig;
        this.mAnimatedBitmapConfig = imageDecodeOptions.animatedBitmapConfig;
        this.mCustomImageDecoder = imageDecodeOptions.customImageDecoder;
        this.mBitmapTransformation = imageDecodeOptions.bitmapTransformation;
        this.mColorSpace = imageDecodeOptions.colorSpace;
        return getThis();
    }

    public T setMinDecodeIntervalMs(int i) {
        this.mMinDecodeIntervalMs = i;
        return getThis();
    }

    public T setMaxDimensionPx(int i) {
        this.mMaxDimensionPx = i;
        return getThis();
    }

    public T setDecodePreviewFrame(boolean z) {
        this.mDecodePreviewFrame = z;
        return getThis();
    }

    public T setUseLastFrameForPreview(boolean z) {
        this.mUseLastFrameForPreview = z;
        return getThis();
    }

    public T setDecodeAllFrames(boolean z) {
        this.mDecodeAllFrames = z;
        return getThis();
    }

    public T setForceStaticImage(boolean z) {
        this.mForceStaticImage = z;
        return getThis();
    }

    public T setCustomImageDecoder(@Nullable ImageDecoder imageDecoder) {
        this.mCustomImageDecoder = imageDecoder;
        return getThis();
    }

    public T setBitmapConfig(Bitmap.Config config) {
        this.mBitmapConfig = config;
        return getThis();
    }

    public T setAnimatedBitmapConfig(Bitmap.Config config) {
        this.mAnimatedBitmapConfig = config;
        return getThis();
    }

    public T setBitmapTransformation(@Nullable BitmapTransformation bitmapTransformation) {
        this.mBitmapTransformation = bitmapTransformation;
        return getThis();
    }

    public T setColorSpace(ColorSpace colorSpace) {
        this.mColorSpace = colorSpace;
        return getThis();
    }

    public T setExcludeBitmapConfigFromComparison(boolean z) {
        this.mExcludeBitmapConfigFromComparison = z;
        return getThis();
    }

    public ImageDecodeOptions build() {
        return new ImageDecodeOptions(this);
    }
}

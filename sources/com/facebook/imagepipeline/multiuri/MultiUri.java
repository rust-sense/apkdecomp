package com.facebook.imagepipeline.multiuri;

import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class MultiUri {

    @Nullable
    private ImageRequest mHighResImageRequest;

    @Nullable
    private ImageRequest mLowResImageRequest;

    @Nullable
    private ImageRequest[] mMultiImageRequests;

    @Nullable
    public ImageRequest getHighResImageRequest() {
        return this.mHighResImageRequest;
    }

    @Nullable
    public ImageRequest getLowResImageRequest() {
        return this.mLowResImageRequest;
    }

    @Nullable
    public ImageRequest[] getMultiImageRequests() {
        return this.mMultiImageRequests;
    }

    private MultiUri(Builder builder) {
        this.mLowResImageRequest = builder.mLowResImageRequest;
        this.mHighResImageRequest = builder.mHighResImageRequest;
        this.mMultiImageRequests = builder.mMultiImageRequests;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        @Nullable
        private ImageRequest mHighResImageRequest;

        @Nullable
        private ImageRequest mLowResImageRequest;

        @Nullable
        private ImageRequest[] mMultiImageRequests;

        public Builder setHighResImageRequest(@Nullable ImageRequest imageRequest) {
            this.mHighResImageRequest = imageRequest;
            return this;
        }

        public Builder setImageRequests(@Nullable ImageRequest... imageRequestArr) {
            this.mMultiImageRequests = imageRequestArr;
            return this;
        }

        public Builder setLowResImageRequest(@Nullable ImageRequest imageRequest) {
            this.mLowResImageRequest = imageRequest;
            return this;
        }

        private Builder() {
        }

        public MultiUri build() {
            return new MultiUri(this);
        }
    }
}

package com.facebook.react.shell;

import com.facebook.imagepipeline.core.ImagePipelineConfig;

/* loaded from: classes.dex */
public class MainPackageConfig {
    private final ImagePipelineConfig mFrescoConfig;

    public ImagePipelineConfig getFrescoConfig() {
        return this.mFrescoConfig;
    }

    private MainPackageConfig(Builder builder) {
        this.mFrescoConfig = builder.mFrescoConfig;
    }

    public static class Builder {
        private ImagePipelineConfig mFrescoConfig;

        public Builder setFrescoConfig(ImagePipelineConfig imagePipelineConfig) {
            this.mFrescoConfig = imagePipelineConfig;
            return this;
        }

        public MainPackageConfig build() {
            return new MainPackageConfig(this);
        }
    }
}

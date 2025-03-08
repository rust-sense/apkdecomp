package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.listener.BaseRequestListener;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImageOriginRequestListener extends BaseRequestListener {
    private String mControllerId;

    @Nullable
    private final ImageOriginListener mImageOriginLister;

    public void init(String str) {
        this.mControllerId = str;
    }

    public ImageOriginRequestListener(String str, @Nullable ImageOriginListener imageOriginListener) {
        this.mImageOriginLister = imageOriginListener;
        init(str);
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onUltimateProducerReached(String str, String str2, boolean z) {
        ImageOriginListener imageOriginListener = this.mImageOriginLister;
        if (imageOriginListener != null) {
            imageOriginListener.onImageLoaded(this.mControllerId, ImageOriginUtils.mapProducerNameToImageOrigin(str2), z, str2);
        }
    }
}

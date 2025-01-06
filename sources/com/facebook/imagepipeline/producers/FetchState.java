package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class FetchState {
    private final Consumer<EncodedImage> mConsumer;
    private final ProducerContext mContext;
    private long mLastIntermediateResultTimeMs = 0;
    private int mOnNewResultStatusFlags;

    @Nullable
    private BytesRange mResponseBytesRange;

    public Consumer<EncodedImage> getConsumer() {
        return this.mConsumer;
    }

    public ProducerContext getContext() {
        return this.mContext;
    }

    public long getLastIntermediateResultTimeMs() {
        return this.mLastIntermediateResultTimeMs;
    }

    public int getOnNewResultStatusFlags() {
        return this.mOnNewResultStatusFlags;
    }

    @Nullable
    public BytesRange getResponseBytesRange() {
        return this.mResponseBytesRange;
    }

    public void setLastIntermediateResultTimeMs(long j) {
        this.mLastIntermediateResultTimeMs = j;
    }

    public void setOnNewResultStatusFlags(int i) {
        this.mOnNewResultStatusFlags = i;
    }

    public void setResponseBytesRange(@Nullable BytesRange bytesRange) {
        this.mResponseBytesRange = bytesRange;
    }

    public FetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mConsumer = consumer;
        this.mContext = producerContext;
    }

    public String getId() {
        return this.mContext.getId();
    }

    public ProducerListener2 getListener() {
        return this.mContext.getProducerListener();
    }

    public Uri getUri() {
        return this.mContext.getImageRequest().getSourceUri();
    }
}

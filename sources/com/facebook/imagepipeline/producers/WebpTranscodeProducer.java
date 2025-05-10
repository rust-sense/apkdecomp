package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.nativecode.WebpTranscoderFactory;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class WebpTranscodeProducer implements Producer<EncodedImage> {
    private static final int DEFAULT_JPEG_QUALITY = 80;
    public static final String PRODUCER_NAME = "WebpTranscodeProducer";
    private final Executor mExecutor;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public WebpTranscodeProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, Producer<EncodedImage> producer) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = (PooledByteBufferFactory) Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new WebpTranscodeConsumer(consumer, producerContext), producerContext);
    }

    private class WebpTranscodeConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final ProducerContext mContext;
        private TriState mShouldTranscodeWhenFinished;

        public WebpTranscodeConsumer(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer);
            this.mContext = producerContext;
            this.mShouldTranscodeWhenFinished = TriState.UNSET;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage encodedImage, int i) {
            if (this.mShouldTranscodeWhenFinished == TriState.UNSET && encodedImage != null) {
                this.mShouldTranscodeWhenFinished = WebpTranscodeProducer.shouldTranscode(encodedImage);
            }
            if (this.mShouldTranscodeWhenFinished == TriState.NO) {
                getConsumer().onNewResult(encodedImage, i);
                return;
            }
            if (isLast(i)) {
                if (this.mShouldTranscodeWhenFinished == TriState.YES && encodedImage != null) {
                    WebpTranscodeProducer.this.transcodeLastResult(encodedImage, getConsumer(), this.mContext);
                } else {
                    getConsumer().onNewResult(encodedImage, i);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transcodeLastResult(EncodedImage encodedImage, Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        Preconditions.checkNotNull(encodedImage);
        final EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
        this.mExecutor.execute(new StatefulProducerRunnable<EncodedImage>(consumer, producerContext.getProducerListener(), producerContext, PRODUCER_NAME) { // from class: com.facebook.imagepipeline.producers.WebpTranscodeProducer.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.common.executors.StatefulRunnable
            public EncodedImage getResult() throws Exception {
                PooledByteBufferOutputStream newOutputStream = WebpTranscodeProducer.this.mPooledByteBufferFactory.newOutputStream();
                try {
                    Preconditions.checkNotNull(cloneOrNull);
                    WebpTranscodeProducer.doTranscode(cloneOrNull, newOutputStream);
                    CloseableReference of = CloseableReference.of(newOutputStream.toByteBuffer());
                    try {
                        EncodedImage encodedImage2 = new EncodedImage((CloseableReference<PooledByteBuffer>) of);
                        encodedImage2.copyMetaDataFrom(cloneOrNull);
                        return encodedImage2;
                    } finally {
                        CloseableReference.closeSafely((CloseableReference<?>) of);
                    }
                } finally {
                    newOutputStream.close();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void disposeResult(@Nullable EncodedImage encodedImage2) {
                EncodedImage.closeSafely(encodedImage2);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void onSuccess(@Nullable EncodedImage encodedImage2) {
                EncodedImage.closeSafely(cloneOrNull);
                super.onSuccess((AnonymousClass1) encodedImage2);
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            protected void onFailure(Exception exc) {
                EncodedImage.closeSafely(cloneOrNull);
                super.onFailure(exc);
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            protected void onCancellation() {
                EncodedImage.closeSafely(cloneOrNull);
                super.onCancellation();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TriState shouldTranscode(EncodedImage encodedImage) {
        Preconditions.checkNotNull(encodedImage);
        ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException((InputStream) Preconditions.checkNotNull(encodedImage.getInputStream()));
        if (DefaultImageFormats.isStaticWebpFormat(imageFormat_WrapIOException)) {
            if (WebpTranscoderFactory.getWebpTranscoder() == null) {
                return TriState.NO;
            }
            return TriState.valueOf(!r0.isWebpNativelySupported(imageFormat_WrapIOException));
        }
        if (imageFormat_WrapIOException == ImageFormat.UNKNOWN) {
            return TriState.UNSET;
        }
        return TriState.NO;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doTranscode(EncodedImage encodedImage, PooledByteBufferOutputStream pooledByteBufferOutputStream) throws Exception {
        InputStream inputStream = (InputStream) Preconditions.checkNotNull(encodedImage.getInputStream());
        ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(inputStream);
        if (imageFormat_WrapIOException == DefaultImageFormats.WEBP_SIMPLE || imageFormat_WrapIOException == DefaultImageFormats.WEBP_EXTENDED) {
            WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToJpeg(inputStream, pooledByteBufferOutputStream, 80);
            encodedImage.setImageFormat(DefaultImageFormats.JPEG);
        } else {
            if (imageFormat_WrapIOException == DefaultImageFormats.WEBP_LOSSLESS || imageFormat_WrapIOException == DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA) {
                WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToPng(inputStream, pooledByteBufferOutputStream);
                encodedImage.setImageFormat(DefaultImageFormats.PNG);
                return;
            }
            throw new IllegalArgumentException("Wrong image format");
        }
    }
}

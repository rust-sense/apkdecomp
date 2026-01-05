package com.facebook.imagepipeline.core;

import android.content.Context;
import android.os.Build;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.internal.AndroidPredicates;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedFactoryProvider;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactoryProvider;
import com.facebook.imagepipeline.cache.AnimatedCache;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.EncodedCountingMemoryCacheFactory;
import com.facebook.imagepipeline.cache.EncodedMemoryCacheFactory;
import com.facebook.imagepipeline.cache.InstrumentedMemoryCache;
import com.facebook.imagepipeline.cache.InstrumentedMemoryCacheBitmapMemoryCacheFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.decoder.DefaultImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.platform.PlatformDecoderFactory;
import com.facebook.imagepipeline.producers.ExperimentalThreadHandoffProducerQueueImpl;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueueImpl;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import com.facebook.imagepipeline.transcoder.MultiImageTranscoderFactory;
import com.facebook.imagepipeline.transcoder.SimpleImageTranscoderFactory;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImagePipelineFactory {
    private static final Class<?> TAG = ImagePipelineFactory.class;
    private static boolean sForceSingleInstance;
    private static ImagePipeline sImagePipeline;

    @Nullable
    private static ImagePipelineFactory sInstance;

    @Nullable
    private AnimatedCache mAnimatedCache;

    @Nullable
    private AnimatedFactory mAnimatedFactory;

    @Nullable
    private CountingMemoryCache<CacheKey, CloseableImage> mBitmapCountingMemoryCache;

    @Nullable
    private InstrumentedMemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CloseableReferenceFactory mCloseableReferenceFactory;
    private final ImagePipelineConfigInterface mConfig;

    @Nullable
    private CountingMemoryCache<CacheKey, PooledByteBuffer> mEncodedCountingMemoryCache;

    @Nullable
    private InstrumentedMemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;

    @Nullable
    private ImageDecoder mImageDecoder;

    @Nullable
    private ImageTranscoderFactory mImageTranscoderFactory;

    @Nullable
    private BufferedDiskCache mMainBufferedDiskCache;

    @Nullable
    private FileCache mMainFileCache;

    @Nullable
    private PlatformBitmapFactory mPlatformBitmapFactory;

    @Nullable
    private PlatformDecoder mPlatformDecoder;

    @Nullable
    private ProducerFactory mProducerFactory;

    @Nullable
    private ProducerSequenceFactory mProducerSequenceFactory;

    @Nullable
    private BufferedDiskCache mSmallImageBufferedDiskCache;

    @Nullable
    private FileCache mSmallImageFileCache;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public static void setInstance(ImagePipelineFactory imagePipelineFactory) {
        sInstance = imagePipelineFactory;
    }

    public CloseableReferenceFactory getCloseableReferenceFactory() {
        return this.mCloseableReferenceFactory;
    }

    public static ImagePipelineFactory getInstance() {
        return (ImagePipelineFactory) Preconditions.checkNotNull(sInstance, "ImagePipelineFactory was not initialized!");
    }

    public static synchronized void initialize(Context context) {
        synchronized (ImagePipelineFactory.class) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("ImagePipelineFactory#initialize");
            }
            initialize(ImagePipelineConfig.newBuilder(context).build());
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    public static synchronized void initialize(ImagePipelineConfigInterface imagePipelineConfigInterface) {
        synchronized (ImagePipelineFactory.class) {
            if (sInstance != null) {
                FLog.w(TAG, "ImagePipelineFactory has already been initialized! `ImagePipelineFactory.initialize(...)` should only be called once to avoid unexpected behavior.");
                if (sForceSingleInstance) {
                    return;
                }
            }
            sInstance = new ImagePipelineFactory(imagePipelineConfigInterface);
        }
    }

    public static synchronized void forceSingleInstance() {
        synchronized (ImagePipelineFactory.class) {
            sForceSingleInstance = true;
        }
    }

    public static synchronized boolean hasBeenInitialized() {
        boolean z;
        synchronized (ImagePipelineFactory.class) {
            z = sInstance != null;
        }
        return z;
    }

    public static synchronized void shutDown() {
        synchronized (ImagePipelineFactory.class) {
            ImagePipelineFactory imagePipelineFactory = sInstance;
            if (imagePipelineFactory != null) {
                imagePipelineFactory.getBitmapMemoryCache().removeAll(AndroidPredicates.True());
                sInstance.getEncodedMemoryCache().removeAll(AndroidPredicates.True());
                sInstance = null;
            }
        }
    }

    public ImagePipelineFactory(ImagePipelineConfigInterface imagePipelineConfigInterface) {
        ThreadHandoffProducerQueue threadHandoffProducerQueueImpl;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipelineConfig()");
        }
        ImagePipelineConfigInterface imagePipelineConfigInterface2 = (ImagePipelineConfigInterface) Preconditions.checkNotNull(imagePipelineConfigInterface);
        this.mConfig = imagePipelineConfigInterface2;
        if (imagePipelineConfigInterface2.getExperiments().getIsExperimentalThreadHandoffQueueEnabled()) {
            threadHandoffProducerQueueImpl = new ExperimentalThreadHandoffProducerQueueImpl(imagePipelineConfigInterface.getExecutorSupplier().getLightWeightBackgroundExecutor());
        } else {
            threadHandoffProducerQueueImpl = new ThreadHandoffProducerQueueImpl(imagePipelineConfigInterface.getExecutorSupplier().getLightWeightBackgroundExecutor());
        }
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueueImpl;
        this.mCloseableReferenceFactory = new CloseableReferenceFactory(imagePipelineConfigInterface.getCloseableReferenceLeakTracker());
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    @Nullable
    private AnimatedFactory getAnimatedFactory() {
        if (this.mAnimatedFactory == null) {
            this.mAnimatedFactory = AnimatedFactoryProvider.getAnimatedFactory(getPlatformBitmapFactory(), this.mConfig.getExecutorSupplier(), getBitmapCountingMemoryCache(), getAnimatedCache(this.mConfig.getExperiments().getAnimatedCacheMemoryPercentage()), this.mConfig.getExperiments().getDownscaleFrameToDrawableDimensions(), this.mConfig.getExperiments().getUseBalancedAnimationStrategy(), this.mConfig.getExperiments().getBalancedStrategyPreparationMs(), this.mConfig.getExperiments().getAnimationRenderFpsLimit(), this.mConfig.getExecutorServiceForAnimatedImages());
        }
        return this.mAnimatedFactory;
    }

    @Nullable
    public DrawableFactory getAnimatedDrawableFactory(@Nullable Context context) {
        AnimatedFactory animatedFactory = getAnimatedFactory();
        if (animatedFactory == null) {
            return null;
        }
        return animatedFactory.getAnimatedDrawableFactory(context);
    }

    public CountingMemoryCache<CacheKey, CloseableImage> getBitmapCountingMemoryCache() {
        if (this.mBitmapCountingMemoryCache == null) {
            this.mBitmapCountingMemoryCache = this.mConfig.getBitmapMemoryCacheFactory().create(this.mConfig.getBitmapMemoryCacheParamsSupplier(), this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getBitmapMemoryCacheTrimStrategy(), this.mConfig.getExperiments().getShouldStoreCacheEntrySize(), this.mConfig.getExperiments().getShouldIgnoreCacheSizeMismatch(), this.mConfig.getBitmapMemoryCacheEntryStateObserver());
        }
        return this.mBitmapCountingMemoryCache;
    }

    public AnimatedCache getAnimatedCache(int i) {
        if (this.mAnimatedCache == null) {
            this.mAnimatedCache = AnimatedCache.getInstance((int) (((Math.min(Runtime.getRuntime().maxMemory(), 2147483647L) / 100) * i) / 1048576));
        }
        return this.mAnimatedCache;
    }

    public InstrumentedMemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        if (this.mBitmapMemoryCache == null) {
            this.mBitmapMemoryCache = InstrumentedMemoryCacheBitmapMemoryCacheFactory.get(getBitmapCountingMemoryCache(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mBitmapMemoryCache;
    }

    public CountingMemoryCache<CacheKey, PooledByteBuffer> getEncodedCountingMemoryCache() {
        if (this.mEncodedCountingMemoryCache == null) {
            this.mEncodedCountingMemoryCache = EncodedCountingMemoryCacheFactory.get(this.mConfig.getEncodedMemoryCacheParamsSupplier(), this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getEncodedMemoryCacheTrimStrategy());
        }
        return this.mEncodedCountingMemoryCache;
    }

    public InstrumentedMemoryCache<CacheKey, PooledByteBuffer> getEncodedMemoryCache() {
        MemoryCache<CacheKey, PooledByteBuffer> encodedCountingMemoryCache;
        if (this.mEncodedMemoryCache == null) {
            if (this.mConfig.getEncodedMemoryCacheOverride() != null) {
                encodedCountingMemoryCache = this.mConfig.getEncodedMemoryCacheOverride();
            } else {
                encodedCountingMemoryCache = getEncodedCountingMemoryCache();
            }
            this.mEncodedMemoryCache = EncodedMemoryCacheFactory.get(encodedCountingMemoryCache, this.mConfig.getImageCacheStatsTracker());
        }
        return this.mEncodedMemoryCache;
    }

    private ImageDecoder getImageDecoder() {
        ImageDecoder imageDecoder;
        ImageDecoder imageDecoder2;
        if (this.mImageDecoder == null) {
            if (this.mConfig.getImageDecoder() != null) {
                this.mImageDecoder = this.mConfig.getImageDecoder();
            } else {
                AnimatedFactory animatedFactory = getAnimatedFactory();
                if (animatedFactory != null) {
                    imageDecoder = animatedFactory.getGifDecoder();
                    imageDecoder2 = animatedFactory.getWebPDecoder();
                } else {
                    imageDecoder = null;
                    imageDecoder2 = null;
                }
                if (this.mConfig.getImageDecoderConfig() == null) {
                    this.mImageDecoder = new DefaultImageDecoder(imageDecoder, imageDecoder2, getPlatformDecoder());
                } else {
                    this.mImageDecoder = new DefaultImageDecoder(imageDecoder, imageDecoder2, getPlatformDecoder(), this.mConfig.getImageDecoderConfig().getCustomImageDecoders());
                    ImageFormatChecker.getInstance().setCustomImageFormatCheckers(this.mConfig.getImageDecoderConfig().getCustomImageFormats());
                }
            }
        }
        return this.mImageDecoder;
    }

    public BufferedDiskCache getMainBufferedDiskCache() {
        if (this.mMainBufferedDiskCache == null) {
            this.mMainBufferedDiskCache = new BufferedDiskCache(getMainFileCache(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(this.mConfig.getMemoryChunkType()), this.mConfig.getPoolFactory().getPooledByteStreams(), this.mConfig.getExecutorSupplier().getIoBoundExecutor(), this.mConfig.getExecutorSupplier().forLocalStorageWrite(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mMainBufferedDiskCache;
    }

    public FileCache getMainFileCache() {
        if (this.mMainFileCache == null) {
            this.mMainFileCache = this.mConfig.getFileCacheFactory().get(this.mConfig.getMainDiskCacheConfig());
        }
        return this.mMainFileCache;
    }

    public ImagePipeline getImagePipeline() {
        if (sImagePipeline == null) {
            sImagePipeline = createImagePipeline();
        }
        return sImagePipeline;
    }

    private ImagePipeline createImagePipeline() {
        return new ImagePipeline(getProducerSequenceFactory(), this.mConfig.getRequestListeners(), this.mConfig.getRequestListener2s(), this.mConfig.isPrefetchEnabledSupplier(), getBitmapMemoryCache(), getEncodedMemoryCache(), getMainBufferedDiskCache(), getSmallImageBufferedDiskCache(), this.mConfig.getCacheKeyFactory(), this.mThreadHandoffProducerQueue, this.mConfig.getExperiments().getSuppressBitmapPrefetchingSupplier(), this.mConfig.getExperiments().isLazyDataSource(), this.mConfig.getCallerContextVerifier(), this.mConfig);
    }

    public PlatformBitmapFactory getPlatformBitmapFactory() {
        if (this.mPlatformBitmapFactory == null) {
            this.mPlatformBitmapFactory = PlatformBitmapFactoryProvider.buildPlatformBitmapFactory(this.mConfig.getPoolFactory(), getPlatformDecoder(), getCloseableReferenceFactory());
        }
        return this.mPlatformBitmapFactory;
    }

    public PlatformDecoder getPlatformDecoder() {
        if (this.mPlatformDecoder == null) {
            this.mPlatformDecoder = PlatformDecoderFactory.buildPlatformDecoder(this.mConfig.getPoolFactory(), this.mConfig.getExperiments().getIsGingerbreadDecoderEnabled(), this.mConfig.getExperiments().getShouldUseDecodingBufferHelper(), this.mConfig.getExperiments().getPlatformDecoderOptions());
        }
        return this.mPlatformDecoder;
    }

    private ProducerFactory getProducerFactory() {
        if (this.mProducerFactory == null) {
            this.mProducerFactory = this.mConfig.getExperiments().getProducerFactoryMethod().createProducerFactory(this.mConfig.getContext(), this.mConfig.getPoolFactory().getSmallByteArrayPool(), getImageDecoder(), this.mConfig.getProgressiveJpegConfig(), this.mConfig.getIsDownsampleEnabled(), this.mConfig.getIsResizeAndRotateEnabledForNetwork(), this.mConfig.getExperiments().getIsDecodeCancellationEnabled(), this.mConfig.getExecutorSupplier(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(this.mConfig.getMemoryChunkType()), this.mConfig.getPoolFactory().getPooledByteStreams(), getBitmapMemoryCache(), getEncodedMemoryCache(), getMainBufferedDiskCache(), getSmallImageBufferedDiskCache(), this.mConfig.getCacheKeyFactory(), getPlatformBitmapFactory(), this.mConfig.getExperiments().getBitmapPrepareToDrawMinSizeBytes(), this.mConfig.getExperiments().getBitmapPrepareToDrawMaxSizeBytes(), this.mConfig.getExperiments().getBitmapPrepareToDrawForPrefetch(), this.mConfig.getExperiments().getMaxBitmapSize(), getCloseableReferenceFactory(), this.mConfig.getExperiments().getKeepCancelledFetchAsLowPriority(), this.mConfig.getExperiments().getTrackedKeysSize());
        }
        return this.mProducerFactory;
    }

    private ProducerSequenceFactory getProducerSequenceFactory() {
        boolean z = Build.VERSION.SDK_INT >= 24 && this.mConfig.getExperiments().getUseBitmapPrepareToDraw();
        if (this.mProducerSequenceFactory == null) {
            this.mProducerSequenceFactory = new ProducerSequenceFactory(this.mConfig.getContext().getApplicationContext().getContentResolver(), getProducerFactory(), this.mConfig.getNetworkFetcher(), this.mConfig.getIsResizeAndRotateEnabledForNetwork(), this.mConfig.getExperiments().getIsWebpSupportEnabled(), this.mThreadHandoffProducerQueue, this.mConfig.getIsDownsampleEnabled(), z, this.mConfig.getExperiments().getIsPartialImageCachingEnabled(), this.mConfig.getIsDiskCacheEnabled(), getImageTranscoderFactory(), this.mConfig.getExperiments().getIsEncodedMemoryCacheProbingEnabled(), this.mConfig.getExperiments().getIsDiskCacheProbingEnabled(), this.mConfig.getExperiments().getAllowDelay(), this.mConfig.getCustomProducerSequenceFactories());
        }
        return this.mProducerSequenceFactory;
    }

    public FileCache getSmallImageFileCache() {
        if (this.mSmallImageFileCache == null) {
            this.mSmallImageFileCache = this.mConfig.getFileCacheFactory().get(this.mConfig.getSmallImageDiskCacheConfig());
        }
        return this.mSmallImageFileCache;
    }

    private BufferedDiskCache getSmallImageBufferedDiskCache() {
        if (this.mSmallImageBufferedDiskCache == null) {
            this.mSmallImageBufferedDiskCache = new BufferedDiskCache(getSmallImageFileCache(), this.mConfig.getPoolFactory().getPooledByteBufferFactory(this.mConfig.getMemoryChunkType()), this.mConfig.getPoolFactory().getPooledByteStreams(), this.mConfig.getExecutorSupplier().getIoBoundExecutor(), this.mConfig.getExecutorSupplier().forLocalStorageWrite(), this.mConfig.getImageCacheStatsTracker());
        }
        return this.mSmallImageBufferedDiskCache;
    }

    private ImageTranscoderFactory getImageTranscoderFactory() {
        if (this.mImageTranscoderFactory == null) {
            if (this.mConfig.getImageTranscoderFactory() == null && this.mConfig.getImageTranscoderType() == null && this.mConfig.getExperiments().getIsNativeCodeDisabled()) {
                this.mImageTranscoderFactory = new SimpleImageTranscoderFactory(this.mConfig.getExperiments().getMaxBitmapSize());
            } else {
                this.mImageTranscoderFactory = new MultiImageTranscoderFactory(this.mConfig.getExperiments().getMaxBitmapSize(), this.mConfig.getExperiments().getUseDownsamplingRatioForResizing(), this.mConfig.getImageTranscoderFactory(), this.mConfig.getImageTranscoderType(), this.mConfig.getExperiments().getIsEnsureTranscoderLibraryLoaded());
            }
        }
        return this.mImageTranscoderFactory;
    }

    @Nullable
    public String reportData() {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper("ImagePipelineFactory");
        CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache = this.mBitmapCountingMemoryCache;
        if (countingMemoryCache != null) {
            stringHelper.add("bitmapCountingMemoryCache", countingMemoryCache.getDebugData());
        }
        CountingMemoryCache<CacheKey, PooledByteBuffer> countingMemoryCache2 = this.mEncodedCountingMemoryCache;
        if (countingMemoryCache2 != null) {
            stringHelper.add("encodedCountingMemoryCache", countingMemoryCache2.getDebugData());
        }
        return stringHelper.toString();
    }
}
